<template>
  <div class="container">
    <div class="background">
      <div class="profile-picture">
        <!-- {{ profile }} -->
        <el-avatar :size="90"> user </el-avatar>
      </div>
    </div>
    <div class="background-info">
      <div class="blank"></div>
      <div class="user-info">
        <div class="username-follow">
          <div class="username">
            <h2>{{ profile.name }}</h2>
            <div class="follow-button">
              <el-button v-if="isFollow===false" type="primary" @click="followNow()">팔로우</el-button>
              <el-button v-else type="primary" plain @click="unfollowNow()">팔로잉</el-button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="description">
      <p>{{ profile.description }}</p>
    </div>
    
    <div class="followers">
      <div class="user-follow">
        <followers-list/>
      </div>
      <div class="user-follow">
        <followings-list/>
      </div>
    </div>

    <el-tabs
    v-model="activeName"
    type="card"
    class="demo-tabs"
    @tab-click="handleClick"
    >
      <el-tab-pane label="My Diary">
        <!-- 내가 쓴 일지 목록 -->
        <my-diaries-list></my-diaries-list>
      </el-tab-pane>
      <el-tab-pane label="My Likes">
        <!-- 내가 좋아요 누른 일지 목록 -->
      </el-tab-pane>
      <el-tab-pane label="My Companions">
        <!-- 내가 북마크한 동행 찾기 목록 -->
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import FollowersList from '@/components/profile/FollowersList.vue'
import FollowingsList from '@/components/profile/FollowingsList.vue'
import MyDiariesList from '@/components/profile/MyDiariesList.vue'
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'ProfileView',
  components: {
    FollowersList,
    FollowingsList,
    MyDiariesList,
  },
  data() {
    return {
      isFollow: false,
      followerList: [],
      followingList: [],
    }
  },
  computed: {
    ...mapGetters(['profile', 'myDiaries'])
  },
  methods: {
    ...mapActions(['fetchProfile', 'fetchMyDiary']),
    followNow() {
      this.isFollow = !this.isFollow
    },
    unfollowNow() {
      this.isFollow = !this.isFollow
    }
  },
  mounted() {
    this.fetchProfile()
    this.fetchMyDiary()
  }
}
</script>

<style scoped>
  * {
    box-sizing: border-box;
    margin: 2%;
    }

  .container {
    display: flex;
    flex-direction: column;
    margin: 0;
  }
  
  .background {
    display: relative;
    background-color: bisque;
    width: 100%;
    height: 15vh;
    padding: 0;
    margin: 0;
  }

  .background-info {
    display: flex;
    margin: 0;
  }

  .username-follow {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }

  .username {
    display: flex;
  }

  .follow-button {
    margin-left: 5%;
  }


  .username h2 {
    margin: 0
  }

  .followers {
    display: flex;
    justify-content: flex-start;
    margin: 0;
    padding: 0;
    height: 10%;
  }

  .user-info {
    margin: 0;
  }

  .profile-picture {
    display: absolute;
    margin-top: 20%;
    margin-right: 65%;
  }

  .user-follow:first-child {
    margin-right: 15px;
  }

  .blank {
    width: 30%;
  }

  .description {
    display: flex;
    padding-top: 5%;
  }

</style>