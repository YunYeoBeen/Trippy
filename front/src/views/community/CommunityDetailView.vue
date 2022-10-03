<template>
  <div>
    <div class="header">
      <div class="tags">
        <!-- {{ post }} -->
        <el-tag class="tag">{{ convertTag }}</el-tag>
        <el-tag class="tag">장소</el-tag>
      </div>
      <router-link class="router" :to="{ name: 'profile' }">
        <div class="profile">
          <el-avatar class="profile-image" :size="40" src="" />
          <span class="username">{{ post.name }}</span>
        </div>
      </router-link>
      <edit-delete-button class="edit-delete"></edit-delete-button>
      <hr>
    </div> 
    <div class="title">
      <span class="state">{{ recruitState }}</span>
      <h4>{{ post.title }}</h4>
    </div>
    <div class="options">
      <p class="option">
        <span class="material-symbols-outlined">groups</span>
        {{ post.startAge }}~{{ post.endAge }}세 | {{ post.gender }}
      </p>
      <p class="option">
        <span class="material-symbols-outlined">event_note</span>
        <span>{{ post.endDate ? `${post.startDate.slice(5, 10)}~${post.endDate.slice(5,10)}` : ''+post.startDate.slice(5, 10) }},</span>
        <span>{{ ''+post.meetingTime.slice(11, 16) }}</span>
      </p>
      <p class="option">
        <span class="material-symbols-outlined">location_on</span>
        {{ post.place }}
      </p>
    </div>
    
    <div class="content">
      <p class="description">{{ post.description }}</p>
      <hr>
    </div>
    <div class="members">
      <p class="member-count">
        <span>{{ post.recruitCurrentVolume }}</span>
        / {{ post.recruitVolume }}명 참여
      </p>
      <div class="users">
        <div class="user">
          <el-avatar :size="40" src="" />
          <span>나유저</span>
        </div>
        <!-- <div class="user">
          <el-avatar :size="40" src="" />
          <span>나유저</span>
        </div> -->
      </div>
    </div>
    <div class="participation">
      <el-button class="button">참가하기</el-button>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import EditDeleteButton from '@/components/common/EditDeleteButton.vue'

export default {
  name: 'CommunityDetailView',
  components: {
    EditDeleteButton
  },
  data() {
    return {
      isBookmark: true,
      postPk: this.$route.params.postPk
    }
  },
  computed: {
    ...mapGetters(['post']),
    recruitState() {
      return '모집중'
    },
    convertTag() {
      const category = this.post.category
      const categoryList = ['식사', '동행', '파티', '이동수단 셰어', '기타']
      return categoryList[category-1]
    },
    // convertDate() {
    //   let date = ''
    //   if (!this.post.isDay) {
    //     date = this.post.startDate.substr(5,5) + '~' + this.post.endDate.substr(5,5)
    //   } else {
    //     date = this.post.startDate.substr(5,5)
    //   }
    //   return date
    // },
    // convertTime() {
    //   let time = ''
    //   time = this.post.meetingTime.substr(11,5)
    //   return time
    // }
  },
  methods: {
    ...mapActions(['fetchPost'])
  },
  created() {
    this.fetchPost(this.postPk)
  }
}
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
}

hr {
  border: 0;
  height: 0;
  border-top: 1px solid #d9d9d9;
}

.header {
  position: relative;
  padding: 0.5rem;
}

.tags {
  text-align: left;
}

.tag {
  margin-right: 0.3rem;
  margin-bottom: 1rem;
}

.router {
  text-decoration: none;
}

.profile {
  display: flex;
  align-items: center;
  margin-bottom: 1rem;
}

.profile-image {
  margin-right: 0.3rem;
}

.username {
  font-weight: 400;
}

.edit-delete {
  position: absolute;
  top: 1rem;
  right: 1rem;
}

.title {
  display: flex;
  align-items: center;
  padding: 0.5rem;
  font-size: 1.2rem;
}

.state {
  font-weight: bold;
  color: #F16B51;
  margin-right: 0.3rem;
}

.options {
  padding: 0.5rem;
  font-size: 0.9rem;
}

.option {
  display: flex;
  align-items: center;
  margin-bottom: 0.3rem;
}

.option > span {
  margin-right: 0.3rem;
}

.content {
  padding: 0.5rem;
}

.description {
  text-align: left;
  margin-bottom: 1rem;
}

.members {
  padding: 0.5rem;
}

.member-count {
  text-align: left;
  font-size: 0.9rem;
  font-weight: 400;
  margin-bottom: 0.3rem;
}

.member-count > span {
  color: #F16B51;
  font-weight: 400;
}

.users {
  display: flex;
  align-items: center;
}

.user {
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-right: 0.3rem;
}

.user > span {
  font-size: 0.8rem;
  font-weight: 400;
}

.participation {
  display: flex;
  justify-content: center;
}

.button {
  position: fixed;
  width: 90%;
  bottom: 1rem;
}


</style>