// import router from '@/router/index.js'
import router from '@/router'
import axios from 'axios'

export default ({
    state: {
			allDiaries: [],
			regionDiaries: [],
			searchDiaries: [],
    },
    getters: {
			allDiaries: state => state.allDiaries,
			regionDiaries: state => state.regionDiaries,
			searchDiaries: state => state.searchDiaries,
    },
    mutations: {
			// SET_REGION_DIARIES: (state, regionDiaries) => state.regionDiaries = regionDiaries,
			// SET_SEARCH_DIARIES: (state, searchDiaries) => state.searchDiaries = searchDiaries,
			GET_ALL_DIARIES (state, allDiaries) {
				allDiaries.forEach((diary) => {
					diary.detailLocations.forEach((location) => {
						if (location.filename != null) {
							diary.representativeImg = location.filepath
							return false
						}
					})
				})
				state.allDiaries = allDiaries
			},
			SET_REGION_DIARIES (state, regionDiaries) {
				regionDiaries.forEach((diary) => {
					diary.detailLocations.forEach((location) => {
						if (location.filename != null) {
							diary.representativeImg = location.filepath
							return false
						}
					})
				})
				state.regionDiaries = regionDiaries
			},
			SET_SEARCH_DIARIES (state, searchDiaries) {
				searchDiaries.forEach((diary) => {
					diary.detailLocations.forEach((location) => {
						if (location.filename != null) {
							diary.representativeImg = location.filepath
							return false
						}
					})
				})
				state.searchDiaries = searchDiaries
			}
    },
    actions: {
			fetchAllDiaries({ commit }) {
				axios({
					url: 'http://i7a506.p.ssafy.io:8080/api/posts',
					method: 'get',
				})
				.then( res => {
					console.log('successfully got all diaries')
					const diaries = res.data
					console.log(diaries)
					commit('GET_ALL_DIARIES', diaries)
				})
				.catch( res => {
					console.log(res.data)
				})
			},

			fetchRegionDiaries({ commit }, region) {
				const country = region.country
				const city = region.city
				axios({
					url: `http://i7a506.p.ssafy.io:8080/api/posts/${country}/${city}`,
					method: 'get',
					params: country, city
				})
				.then( res => {
					console.log(res.data)
					commit('SET_REGION_DIARIES', res.data)
					console.log('successfully fetched requested diaries')
				})
				.catch( err => {
					console.error(err)
					if ( err.response.data === "해당 게시물을 찾을 수 없습니다." ) {
						alert(' 해당 지역에 대한 게시물을 찾을 수 없습니다. 전체 목록으로 돌아갑니다 ')
						router.push({ name: 'home' })
					}
				})
			},

			searchDiary({commit}, searchInput){
				const title = searchInput.title
				const company = searchInput.company
				const transport = searchInput.transportId
				console.log(title)
				console.log(company)
				console.log(transport)
				axios({
					url: `http://i7a506.p.ssafy.io:8080/api/search?title=${title}&company=${company}&transportId=${transport}`,
					// url: `http://i7a506.p.ssafy.io:8080/api/search?title=${title}`,
					// url: 'http://i7a506.p.ssafy.io:8080/api/search' + '?' + `${title}` + '?' + `${company}` + '?' + `${transport}`,
					method: 'get',
					params: title, company, transport
				})
				.then( res => {
					console.log(res.data)
					commit('SET_SEARCH_DIARIES', res.data),
					console.log('search completed')
					router.push({ name: 'search'})
					}
				)
			}
    },
    modules: {
    }
  })
  