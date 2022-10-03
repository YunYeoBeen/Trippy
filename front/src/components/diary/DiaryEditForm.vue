<template>
  <div>
    <form @submit.prevent="onSubmit">
      
      <!-- 제목 -->
      <div class="title-box">
        <p>제목</p>
        <el-input v-model="newDiary.title" class="input-box" placeholder="제목을 입력하세요." />
      </div>

      <div class="demo-collapse">
        <el-collapse>
          <!-- 장소 -->
          <el-collapse-item class="place-select" title="장소" name="1">
            <div>
              {{ newDiary.countryName }}, {{ newDiary.cityName }}
            </div>
          </el-collapse-item>

          <!-- 옵션 -->
          <el-collapse-item name="2">
            <template #title>
              <p class="option-p">옵션</p>
              <!-- 태그 -->
              <el-scrollbar>
                <div class="option-tag-div">
                  <el-tag class="option-tag" type=''>
                    {{ partyTag }}
                  </el-tag>
                  <el-tag v-for="trans in transportationTag" :key="trans" class="option-tag" 
                  closable :disable-transitions="false" type='' @close="handleClose(trans)">
                    {{ trans.transport.name }}
                  </el-tag>
                </div>
              </el-scrollbar>
            </template>

            <div class="option-div">
              <!-- 옵션:여행 기간 -->
              <div class="option-date">
                <span class="option-title">여행 기간</span>
                <div class="date-picker">
                  <el-date-picker
                    v-model="newDiary.startDate"
                    type="date"
                    value-format="YYYY-MM-DD HH:mm:ss"
                    placeholder="여행 시작일을 선택해주세요."
                  />
                  <el-date-picker
                    v-model="newDiary.endDate"
                    type="date"
                    value-format="YYYY-MM-DD HH:mm:ss"
                    placeholder="여행 종료일을 선택해주세요."
                  />
                </div>
              </div>

              <!-- 옵션: 일행 타입 -->
              <div class="option-party">
                <span class="option-title">일행 타입</span>
                <el-radio-group v-model="newDiary.company">
                  <el-radio class="party-option" :label=1>가족</el-radio>
                  <el-radio class="party-option" :label=2>커플</el-radio>
                  <el-radio class="party-option" :label=3>친구</el-radio>
                  <el-radio class="party-option" :label=4>개인</el-radio>
                </el-radio-group>
              </div>

              <!-- 옵션: 인원 수 -->
              <div class="option-member">
                <span class="option-title">인원 수</span>
                <el-input-number v-model="newDiary.count" :min="1" :max="10"/>
              </div>

            <!-- 옵션: 이동수단 -->
              <div class="option-transport">
                <span>이동 수단</span>
                <div class="option-checkbox">
                  <el-checkbox-group class="transport" v-model="newDiary.postTransports" v-for="(trans, idx) in transportList" :key="idx">
                    <el-checkbox class="transport-option" :label="trans">{{ trans.transport.name }}</el-checkbox>
                  </el-checkbox-group>
                </div>
              </div>
            </div>
          </el-collapse-item>

          <!-- 루트 -->
          <el-collapse-item class="route-select" name="3">
            <template #title>
              <p class="route-p">루트</p>
              <!-- 태그 -->
              <el-scrollbar>
                <div class="option-tag-div">
                  <el-tag class="option-tag" v-for="(route, idx) in newDiary.routes" :key="idx"
                  :disable-route="false" type=''>
                    {{ route.routeName }}
                  </el-tag>
                </div>
              </el-scrollbar>
            </template>
            
            <!-- 지도 -->
            <div>
              <div class="route-div">
                <input id="pac-input" class="route-input" type="text" placeholder="루트를 추가해주세요." v-show="flag === 0 && newDiary.routes.length < 10">
                <el-button-group>
                  <el-button type="primary" class="route-btn" @click="addMarkers" :disabled="newDiary.routes.length === 0">
                    <span class="material-symbols-outlined">push_pin</span>
                  </el-button>
                  <el-button type="primary" class="route-btn" @click="initMap" :disabled="flag === 0">
                    <span class="material-symbols-outlined">add_location</span>  
                  </el-button>
                </el-button-group>
              </div>
              <div id="map" style="height: 70vw; position: relative; overflow: hidden;"></div>
              <div class="route-tag-group">
                <el-tag v-for="(route, idx) in newDiary.routes" :key="idx" class="route-tag"
                closable :disable-route="false" type='' @close="removeRoute(idx)">
                  {{ route.routeName }}
                </el-tag>
              </div>
            </div>
          </el-collapse-item>
        </el-collapse>
      </div>

      <div class="story-form">
        <p class="story-p">스토리</p>
        <div>
          <div v-for="(newStory, k) in newStories" :key="k">
            <div class="story-title">
              <span>상세 장소</span>
              <el-input v-model="newStory.detailLocationName" class="input-box" id="title-input-box" placeholder="상세 장소를 입력하세요." />
            </div>

            <div class="story-rate">
              <span>별점</span>
              <el-rate v-model="newStory.rating" allow-half />
            </div>

            <div class="story-content">
              <span>내용</span>
              <el-input v-model="newStory.detailLocationContent" maxlength="500"
              placeholder="내용을 입력해주세요." show-word-limit type="textarea" rows=7 resize="none" class="content-input"/>
            </div>

            <div class="story-btn">
              <el-button @click="addStory()" v-show="newStories.length < 10" :disabled="k != newStories.length - 1" link>
                <span class="material-symbols-outlined">note_add</span>
              </el-button>
              <el-button @click="removeStory(k)" v-show="(newStories.length >= 1) && k!=0" link>
                <span class="material-symbols-outlined">delete</span>
              </el-button>
            </div>
            <hr>
          </div>
        </div>
      </div>

      <div class="submit-btn">
        <el-button @click="onSubmit">수정하기</el-button>
      </div>

    </form>
  </div>
</template>

<script>
/* eslint-disable no-undef */

export default {
  name: 'DiaryEditForm',
  props: {
    diary: Object,
    story: Array,
    trans: Array
  },
  data() {
    return {
      transportList: [
        {
          transport: 
          {
            id: 1,
            name: '뚜벅이'
          }
        },
        {
          transport: 
          {
            id: 2,
            name: '대중교통'
          }
        },
        {
          transport: 
          {
            id: 3,
            name: '따릉이'
          }
        },
        {
          transport: 
          {
            id: 4,
            name: '택시'
          }
        },
        {
          transport: 
          {
            id: 5,
            name: '자차'
          }
        },
      ],
      newStories: [
        ...this.story,
        { 
          // pk: 0,
          detailLocationName: '',
          // dialogVisible: false,
          detailLocationContent: '',
          rating: null,
          preview: ''
        }
      ],   
      newDiary: {
        title: this.diary.title,
        countryName: this.diary.countryName,
        cityName: this.diary.cityName,
        startDate: this.diary.startDate,
        endDate: this.diary.endDate,
        company: this.diary.company,
        count: this.diary.count,
        postTransports: this.trans,
        routes: this.diary.routes,
        detailLocations: this.diary.detailLocations
      },
      images: []
    }
  },
  computed: {
    partyTag() {
      const party = this.newDiary.company
      const partyList = ['가족', '커플', '친구', '개인']
      return partyList[party-1]
    },
    transportationTag() {
      const transportation = this.newDiary.postTransports
      return transportation
    },
    // convertStories() {
    //   const stories = this.newDiary.detailLocations
    //   const convert = []
    //   stories.forEach((story) => {
    //     const each = {}
    //     each.detailLocationName = story.detailLocationName
    //     each.detailLocationContent = story.detailLocationContent
    //     each.rating = story.rating
    //     if (story.filename === null) {
    //       each.preview = ''
    //       this.images.push([])
    //     } else {
    //       const url = story.filepath
    //       const file = async () => {
    //         const response = await fetch(url)
    //         const data = await response.blob()
    //         const ext = url.split(".").pop()
    //         const filename = url.split("/").pop()
    //         const metadata = { type: `image/${ext}`}
    //         return new File([data], filename, metadata)
    //       }
    //       each.preview = URL.createObjectURL(file)
    //       this.images.push(file)
    //     }
    //     convert.push(each)
    //   })
    //   return convert
    // },
  },
  methods: {
    handleClose(tag) {
      this.newDiary.postTransports.splice(this.newDiary.postTransports.indexOf(tag), 1)
    },

    initMap() {
      this.flag = 0
      const map = new google.maps.Map(document.getElementById("map"), {
          center: { lat: 37.5642135 ,lng: 127.0016985 },
          zoom: 13,
          disableDefaultUI: true,
      });
      const input = document.getElementById("pac-input");
      const autocomplete = new google.maps.places.Autocomplete(input, {
          fields: ["place_id", "geometry", "name", "formatted_address"],
      });
      autocomplete.bindTo("bounds", map);

      const geocoder = new google.maps.Geocoder();
      const marker = new google.maps.Marker({ map: map });
      autocomplete.addListener("place_changed", () => {
          const place = autocomplete.getPlace();
          if (!place.place_id) {
              return;
          }
          geocoder
              .geocode({ placeId: place.place_id })
              .then(({ results }) => {
              map.setZoom(15);
              map.setCenter(results[0].geometry.location)

              marker.setPlace({
                  placeId: place.place_id,
                  location: results[0].geometry.location,
              })

              let routeName = place.name
              let geocode = { lat: place.geometry.location.lat(), lng: place.geometry.location.lng() }

              this.addRoute(routeName, geocode)

              marker.setVisible(true)
          })
              .catch((e) => window.alert("Geocoder failed due to: " + e))
      input.value = ''
      });
    },

    addRoute(routeName, geocode) {
      this.route.index = this.newDiary.routes.length + 1
      this.route.routeName = routeName
      this.route.lat = geocode.lat
      this.route.lng = geocode.lng
      this.newDiary.routes.push(this.route)
      console.log(this.newDiary.routes)
      this.route = {}
      // console.log(this.newDiary.routes)
    },

    addMarkers() {
      this.flag = 1
      const map = new google.maps.Map(document.getElementById("map"), {
          center: { lat: this.newDiary.routes[this.newDiary.routes.length - 1].lat, lng: this.newDiary.routes[this.newDiary.routes.length - 1].lng},
          zoom: 13,
          disableDefaultUI: true,
      });
      const geocodes = []
      // console.log(this.routeGeocodes)
      this.newDiary.routes.forEach((each) => {
        // console.log(each)
        let labelNum = (each.index).toString()
        geocodes.push({ lat: each.lat, lng: each.lng })
        new google.maps.Marker({
            position: { lat: each.lat, lng: each.lng },
            label: labelNum,
            map: map,
        });
      })
      // console.log(geocodes)
      const routePath = new google.maps.Polyline({
        path: geocodes,
        geodesic: true,
        strokeColor: '#FF0000',
        strokeOpacity: 1.0,
        strokeWeight: 2
      })
      routePath.setMap(map)
    },

    removeRoute(index) {
      this.newDiary.routes.splice(index, 1)
      this.newDiary.routes.forEach((each) => {
        each.index = this.newDiary.routes.indexOf(each) + 1
      })
      this.addMarkers()
    },

    addStory() {
      if (this.newStories[this.newStories.length - 1].detailLocationName === '' || this.newStories[this.newStories.length - 1].detailLocationContent === '') {
        alert('내용 작성 후 스토리를 추가해주세요!')
      } else {
        this.newStories.push({
          // pk: 0,
          detailLocationName: '',
          // images: [],
          // dialogVisible: false,
          detailLocationContent: '',
          rating: null,
          preview: ''
        }),
        this.images.push([])
      }
    },

    removeStory(index) {
      this.newStories.splice(index, 1)
    },

    onSubmit() {
      this.newStories.forEach((each) => {
        // each.pk = this.newStories.indexOf(each) + 1
        delete each.preview
      })
      this.newDiary.detailLocations = this.newStories

      if (this.newDiary.title && this.newDiary.startDate && this.newDiary.endDate && this.newDiary.postTransports.length
      && this.newDiary.routes.length && this.newDiary.detailLocations.length) {
        console.log(this.newDiary)
        const payload = {
          id: this.diary.id,
          content: this.newDiary
        }
        this.updateDiary(payload)
      } else {
        alert("빈 칸 없이 모든 필드를 채워주세요!")
      }
    }
  },
  mounted() {
    this.initMap()
  }
}
</script>

<style scoped>

.title-box {
  display: flex;
  justify-content: start;
  align-items: center;
  color: var(--el-text);
  font-size: 0.8rem;
  margin-bottom: 0.5rem;
}
.title-box > p {
  width: 10vw;
  text-align: left;
  font-weight: 500;
}
.title-box .input-box {
  width: 80vw;
  margin-left: 0.5rem;
}
.option-p {
  width: 10vw;
  text-align: left;
  font-weight: 500;
}
.option-tag-div {
  display: flex;
  /* flex-wrap: wrap; */
  align-items: center;
  width: 65vw;
}
.option-tag {
  margin-top: 0.7rem;
  margin-left: 0.5rem;
}
.option-div {
  display: flex;
  flex-direction: column;
  margin-top: 1.5rem;
}
.option-date {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  border-right: none;
  margin-bottom: 1rem;
  text-align: left;
}
.date-picker {
  display: flex;
  flex-direction: column;
}
.option-title {
  color: var(--el-text-color-secondary);
  width: 20vw;
  font-size: 0.8rem;
  font-weight: 500;
}
.option-party {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin-bottom: 1rem;
  text-align: left;
}
.option-member {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin-bottom: 1rem;
  text-align: left;
}
.party-option {
  width: 2rem;
  margin-right: 1.5rem;
}
.option-transport {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin-bottom: 1rem;
  text-align: left;
}
.option-transport span {
  color: var(--el-text-color-secondary);
  width: 25vw;
  font-size: 0.8rem;
  font-weight: 500;
}
.option-checkbox {
  display: flex;
  flex-wrap: wrap;
  margin-left: 1.5rem;
}
.transport-option {
  width: 5.2rem;
  margin-right: 1.5rem;
}
.route-p {
  width: 10vw;
  text-align: left;
  font-weight: 500;
}
.route-div {
  display: flex;
  margin-bottom: 10px;
}
#pac-input {
  background-color: #fff;
  font-size: 0.8rem;
  color: var(--el-text-color-secondary);
  padding: 0 11px 0 13px;
  text-overflow: ellipsis;
  width: 60vw;
  height: 1.8rem;
  box-shadow: none;
  border: solid 1px var(--el-border-color);
  border-radius: 5px;
  margin-right: 1rem;
}
.route-btn {
  width: 2rem;
}
.route-tag-group {
  text-align: left;
}
.route-tag {
  margin-top: 0.5rem;
  margin-left: 0.5rem;
}
.story-p {
  width: 10vw;
  text-align: left;
  font-weight: 500;
  font-size: 0.8rem;
  margin-top: 1rem;
}
.story-title {
  display: flex;
  align-items: center;
  text-align: left;
  margin-top: 1rem;
}
.story-title > span {
  color: var(--el-text-color-secondary);
  width: 17vw;
  font-size: 0.8rem;
  font-weight: 500;
}
.story-title .input-box {
  width: 70vw;
  /* margin-left: 0.7rem; */
}
.story-rate {
  display: flex;
  align-items: center;
  text-align: left;
  margin-top: 1rem;
}
.story-rate > span {
  color: var(--el-text-color-secondary);
  width: 17vw;
  font-size: 0.8rem;
  font-weight: 500;
}
.story-rate .el-rate {
  --el-rate-fill-color: #F16B51;
}
.story-content {
  display: flex;
  align-items: center;
  text-align: left;
  margin-top: 1rem;
}
.story-content > span {
  color: var(--el-text-color-secondary);
  width: 17vw;
  font-size: 0.8rem;
  font-weight: 500;
}
.story-content .content-input {
  height: 150px;
  width: 70vw;
}
.story-photo {
  display: flex;
  justify-content: left;
  align-items: center;
  text-align: left;
  margin-top: 1rem;
  margin-bottom: 1rem;
}
.story-photo-title {
  display: flex;
  flex-direction: column;
}
.story-photo-title span {
  color: var(--el-text-color-secondary);
  width: 17vw;
  font-size: 0.8rem;
  font-weight: 500;
}
.photo-div {
  display: flex;
  justify-content: flex-start;
  align-items: center;
}
.photo-div label {
  height: 2.25rem;
}
.photo-div .material-symbols-outlined {
  color: var(--el-text-color-secondary);
  font-size: 10vw;
  font-weight: 500;
}
.photo-des-div {
  display: flex;
  flex-direction: column;
}
.photo-description {
  color: var(--el-text-color-secondary);
  font-size: 0.8rem;
  margin-left: 0.5rem;
}
.photo-description-sec {
  color: var(--el-text-color-secondary);
  font-size: 0.6rem;
  margin-left: 0.5rem;
}
.photo-input {
  width: 0;
  visibility: hidden;
}
.photo-input-add {
  width: 0;
  visibility: hidden;
}
.photo-add-div {
  display: flex;
  justify-content: flex-start;
  align-items: center;
}
.photo-add-div .material-symbols-outlined {
  color:#F16B51;
  font-size: 10vw;
  font-weight: 500;
}
.my-header {
  display: flex;
  justify-content: space-between;
  margin-left: 1rem;
}
.my-header > div {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.my-header > div > h4 {
  font-weight: 500;
  margin-bottom: 0;
}
.my-header > div > p {
  color: var(--el-text-color-secondary);
  font-size: 0.7rem;
  margin-bottom: 0;
}
.my-header > div > p:last-child {
  color: var(--el-text-color-secondary);
  font-size: 0.7rem;
  margin-bottom: 0;
  margin-top: 0;
}
.photo-preview {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-wrap: wrap;
}
.photo-preview .material-symbols-outlined {
  font-size: 15vw;
}
.photo-preview label {
  height: 3.375rem;
}
.photo-preview-group {
  display: flex;
  align-items: center;
}
.photo-preview-group > img {
  width: 15vw;
  height: 20vw;
  margin-right: 0.5rem;
}
.story-btn {
  display: flex;
  justify-content: flex-end;
}
.story-btn > el-button {
  margin: 0;
}
.story-btn .material-symbols-outlined {
  color: #F16B51;
  font-size: 2rem;;
}
.submit-btn {
  margin-top: 1rem;
}

</style>