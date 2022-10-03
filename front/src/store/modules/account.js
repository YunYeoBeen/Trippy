import router from '@/router/index.js'
import axios from 'axios'
import VueCookies from 'vue-cookies'
// import jwt_decode from "jwt-decode"

export default {
  state: {
    // accessToken:  '',
    // refreshToken: '',
    currentUser: {},
    // profile: {},
    userData: {},
    // 임시 email
    email: localStorage.getItem('email') || '',
    authError: null,
    fromPasswordFindView: true,
    verificationCode: '',
    emailInfo: '',
  },
  getters: {
    isLoggedIn: () => !!VueCookies.get('accessToken'),
    // refreshToken: state => state.refreshToken,
    currentUser: state => state.currentUser,
    // profile: state => state.profile,
    userData: state => state.userData,
    authError: state => state.authError,
    authHeader: () => ({ 'X-AUTH-TOKEN': `${VueCookies.get('accessToken')}`}),
    fromPasswordFindView: state => state.fromPasswordFindView,
    verificationCode: state => state.verificationCode,
    emailInfo: state => state.emailInfo,
  },
  mutations: {
    // SET_ACCESS_TOKEN:(state, accessToken) => state.accessToken = accessToken,
    // SET_REFRESH_TOKEN: () => VueCookies.set('refreshToken'),
    SET_CURRENT_USER:(state, user) => state.currentUser = user,
    // SET_PROFILE: (state, profile) => state.profile = profile,
    SET_USER_DATA: (state, userData) => state.userData = userData,
    SET_AUTH_ERROR: (state, error) => state.error = error,
    FROM_PASSWORD_FIND_VIEW: (state) => state.fromPasswordFindView = true,
    SET_EMAIL_AUTH_CODE: (state, verificationCode) => state.verificationCode = verificationCode,
    SET_EMAIL_INFO: (state, emailInfo) => state.emailInfo = emailInfo,
  },
  actions: {
    // saveToken({ commit }, accessToken ) {
    //   commit('SET_ACCESS_TOKEN', accessToken)
    // },
    emailInfo({ commit }, emailInfo){
      commit('SET_EMAIL_INFO', emailInfo)
    },

    removeToken({ getters }, ) {
      console.log(getters)
      VueCookies.remove('accessToken')
      VueCookies.remove('refreshToken')
      localStorage.removeItem('email')
    },

    reissueToken({ dispatch, }) {
      // const data = { "email": localStorage.getItem('email') , "refreshToken": VueCookies.get('refreshToken') }
      // console.log(data)
      const email = localStorage.getItem('email')
      const refreshToken = VueCookies.get('refreshToken')
      axios({
        url: `http://i7a506.p.ssafy.io:8080/api/members/re-issue?email=${email}&refreshToken=${refreshToken}`,
        method: 'get',
        params: email
      })
      .then( res => {
        console.log('access token re-issued')
        // accessToken은 state에 저장.. 하지 않고 1시간 동안 쿠키에 저장
        const accessToken = res.data.accessToken
        // dispatch('saveToken', accessToken)
        VueCookies.set("accessToken", accessToken, '1h')
        // refreshToken은 7일동안 쿠키에 저장
        const refreshToken = res.data.refreshToken
        VueCookies.set("refreshToken", refreshToken, '7d')
        dispatch('fetchCurrentUser')
      })
      .catch(err => {
        console.error(err)
      })
    },

    login({ dispatch }, userinfo) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/members/login',
        method: 'post',
        data: userinfo,
      })
        .then( res => {
          // accessToken은 state에 저장.. 하지 않고 1시간 동안 쿠키에 저장
          const accessToken = res.data.accessToken
          // dispatch('saveToken', accessToken)
          VueCookies.set("accessToken", accessToken, '1h')
          // refreshToken은 7일동안 쿠키에 저장
          const refreshToken = res.data.refreshToken
          VueCookies.set("refreshToken", refreshToken, '7d')
          dispatch('fetchCurrentUser')
          console.log('successfully logged in')
          router.push({ name: 'home' })
        })
        .catch(err => {
          console.error(err)
        })
    },

    signupOne({ commit }, userData) {
      // 첫번째 signup 페이지에서의 data 를 state에 저장
      commit('SET_USER_DATA', userData)
    },
    
    signupTwo({commit,}, userData) {
      commit('SET_USER_DATA', userData)
      console.log(this.getters.userData.password)
      console.log(this.getters.userData.description)
        axios({
          url: 'http://i7a506.p.ssafy.io:8080/api/members/join',
          method: 'post',
          data: this.getters.userData,
        })
          .then( () => {
            console.log('successfully created an account')
            router.push({ name: 'login' })
          })
          .catch(err => {
            console.error(err.response)
            commit('SET_AUTH_ERROR', err.response)
          })
    },

    // 이메일 중복확인
    checkEmailDuplicate({ getters }, userData) {
      console.log(getters)
      const email = userData.email
      axios({
        url: `http://i7a506.p.ssafy.io:8080/api/members/duplicate?email=${email}`,
        method: 'get',
        param: email
      })
      .then(res => {
        if (res.data === true) {
          alert('이메일이 중복되었습니다')
        } else {
          alert('이메일을 사용하셔도 좋습니다')
        }
      })
      .catch(err => {
        console.error(err)
      })
    },

    // 인증 코드 요청하기
    emailCode({commit}, userinfo) {
      console.log(userinfo)
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/members/join/authmail',
        method: 'post',
        data: userinfo
      })
      .then( res => {
        console.log(res)
        commit('SET_EMAIL_AUTH_CODE', res.data)
      })
      .catch(err => {
        console.error(err)
      })
    },

      // 받아온 인증번호와, 입력한 인증번호가 동일한지 확인
    emailAuth() {
      console.log(this.verificationCode)
      if ( !this.verificationCode ) {
        alert('인증번호를 입력하세요') }
      else if ( this.$store.getters.verificationCode === this.verificationCode ){
        alert('인증이 완료되었습니다')
        this.verified = true
        this.fromPasswordFindView()
      } else {
        alert('인증번호가 일치하지 않습니다')
      }
    },

    fetchCurrentUser({ getters, dispatch, commit }, ) {
      if (getters.isLoggedIn) {
        axios({
          url: 'http://i7a506.p.ssafy.io:8080/api/auth/members',
          method: 'get',
          headers: getters.authHeader,
        })
        .then( res => {
          console.log('successfully fetched current user info')
          const email = res.data.email
          console.log(email)
          localStorage.setItem('email', email)
          let gender = res.data.gender
          if ( gender === 1) {
            res.data.gender = '남자'
          } else ( res.data.gender = '여자')
          commit('SET_CURRENT_USER', res.data)})
        .catch(err => {
          if (err.response.status === 400) {
            dispatch('removeToken')
            router.push({ name: 'login' })
          }
        })
      }
    },

    logout({ getters, dispatch }) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/members/logout',
        method: 'get',
        headers: getters.authHeader,
      })
      .then((res) => {
        dispatch('removeToken')
        localStorage.revmoveItem('email')
        console.log(getters.isLoggedIn)
        router.push({ name: 'home' })
        console.log(res.data)
        alert('성공적으로 로그아웃 되었습니다')
      })
      .catch( err => {
        console.error(err.response)
      })
    },


    fromPasswordFindView({commit}, ) {
      commit('FROM_PASSWORD_FIND_VIEW')
      console.log(this.getters.fromPasswordFindView)
      router.push('/passwordchange')
    },

    changePassword({ commit, dispatch }, userinfo) {
      console.log(userinfo)
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/members/change_pw',
        method: 'post',
        data: userinfo
      })
      .then((res) => {
        console.log(res)
        dispatch('fetchCurrentUser')
        commit('SET_USER_DATA')
        alert('비밀번호가 변경되었습니다')
        router.push({ name: 'login' })
      })
      .catch( err => {
        console.error(err.response)
      })       
    }, 

    deleteAccount({ getters }) {
      console.log(getters.authHeader)
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/members/remove',
        method: 'delete',
        headers: getters.authHeader,
      })
      .then( () => {
        VueCookies.remove('accessToken')
        VueCookies.remove('refreshToken')
        console.log('successfully deleted account')
        router.push({ name: 'login' })
      })
      .catch(err => {
        console.error(err)
      })
    }

  },
  modules: {
  }
}