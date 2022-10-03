<template>
  <div>
    <span class="material-symbols-outlined location" @click="getLocation">my_location</span>

    <!-- <region-list></region-list> -->
    <div v-for="post in posts" :key="post.id">
       <router-link :to="{ name: 'communityDetail', params: { postPk: post.id } }">
        <el-card class="card">
          <div>
            <el-tag class="tag">{{ post.cityName }}</el-tag>
            <!-- <el-tag class="tag">{{ convertTag }}</el-tag> -->
            <el-tag class="tag">{{ post.category === 1 ? '식사' : post.category === 2 ? '동행' : post.category === 3 ? '파티' : post.category === 4 ? '이동수단 셰어' : '기타' }}</el-tag>
            <!-- <el-tag class="tag" v-for="(tag, idx) in convertTag" :key="idx">{{ tag }} </el-tag> -->
          </div>
          <div class="title">
            <span class="state">{{ recruitState }}</span>
            <h4>{{ post.title }}</h4>
          </div>
          <div class="options">
            <p class="option">
              <span class="material-symbols-outlined">groups</span>
              {{ post.startAge }}~{{ post.endAge }}세 | {{ post.gender }} 참여 가능
            </p>
            <p class="option">
              <span class="material-symbols-outlined">event_note</span>
              <!-- {{ convertDate }}, {{ convertTime }} -->
              <span>{{ post.endDate ? ''+post.startDate.slice(5, 10) + '~' + ''+post.endDate.slice(5,10) : ''+post.startDate.slice(5, 10) }},</span>
              <span>{{ ''+post.meetingTime.slice(11, 16) }}</span>
            </p>
            <p class="option">
              <span class="material-symbols-outlined">groups</span>
              {{ post.recruitCurrentVolume }} / {{ post.recruitVolume }}명 참여
            </p>
          </div>
          <div class="content">
            <!-- <p>{{ convertDesc }}</p> -->
            <p>{{ post.description.length > 50 ? ''+post.description.slice(0, 45) + '...' : post.description}}</p>
          </div>

          
        </el-card>
      </router-link>
    </div>

  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
// import RegionList from '@/components/common/RegionList.vue'

export default {
    name: 'CommunityView',
    components: {
      // RegionList
    },
    data() {
      return {
        position: {},
      }
    },
    computed: {
      ...mapGetters(['posts', 'post']),
      // convertTag() {
      //   const posts = this.posts
      //   let cat = ''
      //   posts.forEach((el) => {
      //     const category = el.category
      //     console.log(category)
      //     const categoryList = ['식사', '동행', '파티', '이동수단 셰어', '기타']
      //     cat = categoryList[category-1]
      //   })
      //   return cat
        // let cat = ''
        // for (let values of tag.values()) {
        //   cat = values
        // }
        // return cat
      // },
      recruitState() {
        return '모집중'
      },
      // convertDate() {
      //   const posts = this.posts
      //   const date = posts.forEach((el) => {
      //     const startDate = ''+el.startDate
      //     const endDate = ''+el.endDate
      //     console.log(startDate, endDate)
      //     console.log(el.day)
      //     return startDate
      //     // if (el.day) {
      //     //   return `${startDate.slice(5,10)}~${endDate.slice(5,10)}`
      //     // } else {
      //     //   return startDate
      //     // }
      //   })
      //   return date
      // },
      // convertTime() {
      //   const posts = this.posts
      //   const time = posts.forEach((el) => {
      //     const meetingTime = ''+el.meetingTime
      //     meetingTime.slice(11, 16)
      //   })
      //   return time
      // },
      // convertDesc() {
      //   let length = 50
      //   let desc = ''
      //   const posts = this.posts
      //   posts.forEach((el) => {
      //     if (el.description.length > length) {
      //       desc = el.description.substr(0, length - 2) + '...'
      //     }
      //   })
      //   return desc
      // }
    },
    methods: {
      ...mapActions(['fetchPosts']),
      getLocation() {
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(this.getLocationValue)
          console.log(this.position)
        } else {
          alert('위치 정보를 찾을 수 없습니다.')
        }
      },
      getLocationValue(position) {
        this.position.latitude = position.coords.latitude
        this.position.longitude = position.coords.longitude
      },
    },
    created() {
      this.fetchPosts()
      // console.log(this.posts)
      // // console.log(this.posts[1].category)
      // this.posts.forEach(i => {
      //   console.log(i.category)
      // })
      // console.log(post)
      // console.log(this.posts[0]['category'])
      // this.posts.forEach((el) => {
      //     const startDate = el.startDate
      //     console.log(typeof(startDate))
      // })
    }
  }
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  text-decoration: none;
}

.location {
  position: relative;
  right: -9.5rem;
  color: #F16B51;
}

.card {
  text-align: left;
  margin-bottom: 0.5rem;
}

.tag {
  margin-right: 0.3rem;
  margin-bottom: 0.5rem;
}

.title {
  display: flex;
  align-items: center;
  margin-bottom: 0.5rem;
}

.state {
  font-weight: bold;
  color: #F16B51;
  margin-right: 0.3rem;
}

.options {
  font-size: 0.9rem;
  margin-bottom: 0.5rem;
}

.option {
  display: flex;
  align-items: center;
  margin-right: 0.3rem;
  margin-bottom: 0.3rem;
}

.option > span {
  margin-right: 0.3rem;
}

.content {
  font-size: 0.9rem;
  border-radius: 10px;
  background-color: #d9d9d9;
  padding: 10px;
}

.create {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  width: 3rem;
  height: 3rem;;
  bottom: 1.5rem;
  right: 1.5rem;
  border-radius: 50%;
  background-color: #F16B51;
  color: #fff;
}
</style>