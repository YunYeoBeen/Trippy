<template>
  <div class="container">
    <h2> 지금까지 모은 뱃지들 </h2>
    <div class="description">
      <p>일정 조건을 만족하면 뱃지를 얻을 수 있습니다</p>
      <p>자신이 얻은 뱃지에는 불이 들어오며</p>
      <p>아직 얻지 못한 배지는 흑백으로 보입니다</p>
      <p>모든 뱃지에 불이 들어올 수 있도록 열심히 활동해보세요!</p>
    </div>
    <!-- 전체 뱃지 목록 -->
    <div class="badge-list">
      <el-row>
        <el-col class="badge" :span="6" v-for="(badge, idx) in badgeList" :key="idx">
          <!-- 나중에 조건 만족 시 data의 obtained를 true로 바꿈(그러면 불 들어옴) - 
          근데 이게 개별 배지마다 설정을 어케하는지 아직 모르겠음
          영 모르겠으면 for 문 돌리지말고 그냥... 하나하나 하는 수밖에 없을 듯-->
          <img :src=badge.image :alt=badge.image @click="badge.drawer=true" :style="{ filter: unlocking }" >
          <p class="badge-name">{{ badge.name }}</p>
          <el-drawer v-model=badge.drawer direction="btt" size="50%">
            <img :src=badge.image :alt=badge.image style="width: 70px; height: 70px;">
            <p>{{ badge.name }}</p>
            <span>{{ badge.description }}</span><br>
            <span>앞으로도 Trippy에서 여행의 기억을 쌓아가세요!</span>
          </el-drawer>
        </el-col>
      </el-row>
    </div>

      <!-- 사용자의 뱃지 목록에 있다면 -->
      <!-- <div class="badge">
        <img class="obtained" :src=badge.image :alt=badge.image @click="badge.drawer=true">
        <p>{{ badge.name }}</p>
      </div>
      <el-drawer v-model=badge.drawer direction="btt" size="50%">
        <img :src=badge.image :alt=badge.image style="width: 70px; height: 90px;">
        <p>{{ badge.name }}</p>
        <span>{{ badge.description }}</span><br>
        <span>앞으로도 Trippy에서 여행의 기억을 쌓아가세요!</span>
      </el-drawer> -->
      
      <!-- 사용자의 뱃지 목록에 없다면 -->
      <!-- <div class="badge">
        <img class="not-obtained" :src=badge.image alt="not obtained" @click="badge.drawer=true">
        <p>{{ badge.name }}</p>
      </div>
      <el-drawer v-model=badge.drawer direction="btt" size="50%">
        <img :src=badge.image :alt=badge.image style="width: 70px; height: 90px;">
        <p>{{ badge.name }}</p>
        <span>{{ badge.description }}</span><br>
        <span>어떤 모양의 뱃지일까요~?</span>
      </el-drawer> -->
  </div>
</template>

<script>
export default {
  name: 'BadgeListView',
  computed: {
    unlocking() {
      if (this.obtained === false) {
        return this.unlocked
      } else {
        return this.locked
      }
    }
  },
  data () {
    return {
      badgeList: [
        {
          pk: 1,
          name: '여행의 시작',
          description: 'Trippy에 오신 것을 환영합니다!',
          image: require('@/assets/badge-start.png'),
          drawer: false
        },
        {
          pk: 2,
          name: '기록의 시작',
          description: '첫 여행 일지를 기록하셨군요!',
          image: require('@/assets/badge-diary.png'),
          drawer: false
        },
        {
          pk: 3,
          name: '만남의 시작',
          description: '첫 동행 글을 작성하셨군요!',
          image: require('@/assets/badge-chat.png'),
          drawer: false
        },
        {
          pk: 4,
          name: '???',
          description: '다음에는 어떤 뱃지가 있을까요?',
          image: require('@/assets/badge-lock.png'),
          drawer: false
        },
        {
          pk: 5,
          name: '???',
          description: '다음에는 어떤 뱃지가 있을까요?',
          image: require('@/assets/badge-lock.png'),
          drawer: false
        },
        {
          pk: 6,
          name: '???',
          description: '다음에는 어떤 뱃지가 있을까요?',
          image: require('@/assets/badge-lock.png'),
          drawer: false
        },
        {
          pk: 7,
          name: '???',
          description: '다음에는 어떤 뱃지가 있을까요?',
          image: require('@/assets/badge-lock.png'),
          drawer: false
        },
        {
          pk: 8,
          name: '???',
          description: '다음에는 어떤 뱃지가 있을까요?',
          image: require('@/assets/badge-lock.png'),
          drawer: false
        },
        {
          pk: 9,
          name: '???',
          description: '다음에는 어떤 뱃지가 있을까요?',
          image: require('@/assets/badge-lock.png'),
          drawer: false
        },
        {
          pk: 10,
          name: '???',
          description: '다음에는 어떤 뱃지가 있을까요?',
          image: require('@/assets/badge-lock.png'),
          drawer: false
        },
        {
          pk: 11,
          name: '???',
          description: '다음에는 어떤 뱃지가 있을까요?',
          image: require('@/assets/badge-lock.png'),
          drawer: false
        },
        {
          pk: 12,
          name: '???',
          description: '다음에는 어떤 뱃지가 있을까요?',
          image: require('@/assets/badge-lock.png'),
          drawer: false
        },
        {
          pk: 13,
          name: '???',
          description: '다음에는 어떤 뱃지가 있을까요?',
          image: require('@/assets/badge-lock.png'),
          drawer: false
        },
        {
          pk: 14,
          name: '???',
          description: '다음에는 어떤 뱃지가 있을까요?',
          image: require('@/assets/badge-lock.png'),
          drawer: false
        },
        {
          pk: 15,
          name: '???',
          description: '다음에는 어떤 뱃지가 있을까요?',
          image: require('@/assets/badge-lock.png'),
          drawer: false
        },
        {
          pk: 16,
          name: '???',
          description: '다음에는 어떤 뱃지가 있을까요?',
          image: require('@/assets/badge-lock.png'),
          drawer: false
        },
      ],
      obtained: false,
      unlocked: 'grayscale',
      locked: 'none'
    }
  }
}
</script>

<style scoped>
.container {
  position: relative;
  margin: 5%;
}

.description {
  height: 10vh;
  margin-top: 2%;
  font-size: 12px;
}

.badge-list {
  margin-top: 8%;
}


.badge {
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  margin-bottom: 5%;
}

.badge-name {
  display: flex;
  justify-content: center;
}

/* .obtained {
  width: 20vw;
  height: 20vw;
}

.not-obtained {
  width: 20vw;
  height: 20vw;
  filter: grayscale(100%)
} */

img {
  width: 20vw;
  height: 20vw;
  filter: grayscale(100%)
}

</style>