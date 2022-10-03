// import router from "@/router"
import axios from "axios"

export default {
  state: {
    profile: {},
    myDiaries: []
  },
  getters: {
    profile: state => state.profile,
    myDiaries: state => state.myDiaries
  },
  mutations: {
    SET_PROFILE: (state, profile) => state.profile = profile,
    FETCH_MY_DIARY: (state, myDiaries) => {
      myDiaries.forEach((diary) => {
        diary.detailLocations.forEach((location) => {
          if (location.filename != null) {
            diary.representativeImg = location.filepath
            return false
          }
        })
      })
      state.myDiaries = myDiaries
    }
  },
  actions: {
    fetchProfile({ commit, getters }) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/members',
        method: 'get',
        headers: getters.authHeader,
      })
      .then( res => {
        console.log(res.data)
        commit('SET_PROFILE', res.data)
      })
    },
    fetchMyDiary({ commit, getters }) {
      axios({
        url: 'http://i7a506.p.ssafy.io:8080/api/auth/posts/memberDetail',
        method: 'get',
        headers: getters.authHeader,
      })
      .then(res => {
        console.log(res.data)
        commit('FETCH_MY_DIARY', res.data)
      })
    } 
  }
}