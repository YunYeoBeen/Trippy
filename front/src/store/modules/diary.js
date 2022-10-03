import router from "@/router"
import axios from "axios"
import VueCookies from 'vue-cookies'
// import _ from 'lodash'

export default ({
  state: {
    diaries: [],
    diary: {},
    // images: [],
    // image: {},

    comment: '',
    comments: [],
    isChild: false,
    isUpdating: false,
    parentComment: '',
  },
  getters: {
    diaries: state => state.diaries,
    diary: state => state.diary,
    // images: state => state.images,
    // image: state => state.image,
    comment: state => state.comment,
    comments: state => state.comments,
    isChild: state => state.isChild,
    isUpdating: state => state.isUpdating,
    parentComment: state => state.parentComment,
    // 이 친구 긴가민가
    isAuthor: (state, getters) => {
      return state.diary?.name === getters.currentUser.name
    },
    // isDiary: state => !_.isEmpty(state.diary)
  },
  mutations: {

    SET_DIARY(state, diary) {
      diary.detailLocations.forEach((location) => {
        if (location.filename != null) {
          diary.representativeImg = location.filepath
          return false
        }
      })
      state.diary = diary
      console.log(state.diary)
    },

    // SET_IMAGES(state, image) {
    //   state.image = image
    //   state.images.push(image)
    //   console.log(state.images)
    // },

    SET_COMMENT(state, comment) {
      state.comment = comment
      console.log(state.comment)
    },

    SET_STATUS(state) {
      state.isUpdating = true
    },

    SET_COMMENTS(state, comments) {
      state.comments = comments
    },

    // 홈화면에 추천(일단은 전부 띄우는 것)
    // 근데 diaries에 써도 되는건지 잘 모르겠음?
    GET_ALL_DIARY(state, diaries) {
      diaries.forEach((diary) => {
        console.log(diary.detailLocations)
        diary.detailLocations.forEach((location) => {
          if (location.filepath.substr(-1) != '/') {
            diary.representativeImg = location.filepath
            return false
          }
        })
      })
      state.diaries = diaries
    },

    SHOW_PARENT(state, parentComment) {
      state.isChild = true
      state.parentComment = parentComment
    },

    HIDE_PARENT(state) {
      state.isChild = false
    }
  },
  actions: {
    // 일지 CREATE
    // 일지 저장
    createDiary({ commit }, diary) {
      // commit('CREATE_DIARY', diary)
      // console.log(1)
      console.log(diary)
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/posts',
        method: 'post',
        data: diary,
        headers: {
          "Content-Type": "multipart/form-data",
          'X-AUTH-TOKEN': `${VueCookies.get('accessToken')}`
        }
      })
      .then(res => {
        console.log(res.data)
        commit('SET_DIARY', diary)
        // console.log(3)
        // console.log(getters.diary)
        router.push({
          name: 'diaryDetail',
          params: { diaryPk: res.data }
        })
      })
    },

    // 일지 READ
    // 단일 일지
    fetchDiary({ commit, getters }, diaryPk) {
      axios({
        url: `http://i7a506.p.ssafy.io:8080/api/posts/detail/${diaryPk}`,
        method: 'get',
        headers: getters.authHeader
      })
      .then(res => {
        commit('SET_DIARY', res.data)
        // const diary = res.data
        // diary.detailLocations.forEach((location) => {
        //   const imagePk = location.id
        //   dispatch('fetchImage', imagePk)
        // })
      })
      .catch(err => {
        console.error(err.response)
        if (err.response.status === 404) {
          router.push({ name: 'notFound404' })
        }
      })
    },

    // fetchImage({ commit, getters }, imagePk) {
    //   axios({
    //     url: `http://i7a506.p.ssafy.io:8080/api/posts/images/${imagePk}`,
    //     method: 'get',
    //     headers: getters.authHeader
    //   })
    //   .then((res) => {
    //     console.log(res.data)
    //     commit('SET_IMAGES', res.data)
    //   })
    // },

    // 일지 UPDATE
    updateDiary({ commit, getters }, diary) {
      axios({
        url: `http://i7a506.p.ssafy.io:8080/api/auth/posts/${diary.id}`,
        method: 'put',
        data: diary.content,
        headers: getters.authHeader
      })
      .then(res => {
        console.log(res.data)
        commit('SET_DIARY', diary)
        router.push({
          name: 'DiaryDetail',
          parmas: { diaryPk: res.data }
        })
      })
    },

    // 일지 DELETE
    deleteDiary({ commit, getters }, diaryPk) {
      axios({
        url: `http://i7a506.p.ssafy.io:8080/api/auth/posts/${diaryPk}`,
        method: 'delete',
        headers: getters.authHeader
      })
      .then(res => {
        commit('SET_DIARY', {})
        console.log(res)
        router.push({ name: 'home' })
      })
      .catch(err => console.error(err.response))
    },

    // 일지 댓글 CREATE
    // createComment({ getters, commit }, diaryPk, content) {
    //   const comment = { content }
    //   axios({
    //     url: `http://localhost:8000/comments/post/${diaryPk}`,
    //     method: 'post',
    //     data: comment,
    //     headers: getters.authHeader
    //   })
    //   .then( res => {
    //     commit('SET_COMMENTS'),
    //     res.data
    //     router.push('DiaryCommentView')
    //   })
    //   .catch(err => console.error(err.response))
    // },
    createComment({ commit }, content) {
      commit('SET_COMMENT', content)
    },

    // updateComment({ getters, commit}, {diaryPk, commentPk, content}) {
    //   const comment = { content, diaryPk, commentPk }
    //   axios({
    //     url:'http://localhost:8000/comments/api/post',
    //     method: 'put',
    //     data: comment,
    //     headers: getters.authHeader,
    //   })
    //   .then(res => {
    //     commit('SET_COMMENTS', res.data)
    //   })
    //   .catch(err => console.error(err.response))
    // },
    updateComment({ commit }, content) {
      commit('SET_COMMENT', content)
      commit('SET_STATUS')
    },

    fetchComment({ getters, commit}, diaryPk) {
      axios({
        url: `http://localhost:8000/comments/post/${diaryPk}`,
        method: 'get',
        headers: getters.authHeader
      })
      .then( res => {
        commit('SET_COMMENTS', res.data)
      })
      .catch(err => {
        if (err.response.status === 404) {
          router.push({ name: 'notFound404'})
        }
      })
    },
  
    deleteComment({ getters, commit}, diaryPk, commentPk) {
      if (confirm('정말 삭제하시겠습니까?')) {
        axios({
          url: `http://localhost:8000/comments/post/`,
          method: 'delete',
          data: {diaryPk, commentPk},
          headers: getters.authHeader,
        })
        .then(res => {
          commit('SET_COMMENTS', res.data)
        })
        .catch(err => console.error(err.response))
      }
    },
  
    showParent({ commit }, commentUser) {
      commit('SHOW_PARENT', commentUser)
    },

    hideParent({ commit }) {
      commit('HIDE_PARENT')
    },
  
    showAllDiary({ commit }) {
      axios({
        url: 'http://localhost:8000/posts',
        method: 'get'
      })
      .then((res) => {
        const allDiary = res.data
        commit('SHOW_ALL_DIARY', allDiary)
      })
    },

    likeDiary({ commit, getters }, diaryPk) {
      axios({
        url: `http://i7a506.p.ssafy.io:8080/api/auth/likepost`,
        method: 'post',
        data: {post_id: diaryPk},
        headers: getters.authHeader
      })
      .then((res) => {
        commit('SET_DIARY', res.data)
        console.log(res.data)
        router.push({
          name: 'DiaryDetail',
          params: { diaryPk: diaryPk }
        })
      })
    }
  },

})
