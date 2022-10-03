<template>
  <div>
    <div class="container">
      <p @click="visible = true">
        FOLLOWERS
      </p>
      <el-dialog v-model="visible" :show-close="false">
        <template #header="{ close, titleId, titleClass }">
          <div class="my-header">
              <h4 :id="titleId" :class="titleClass">FOLLOWERS</h4>
              <el-button type="danger" @click="close">
              x
              </el-button>
          </div>
        </template>
        <div class="profileimage row row-cols-3 row-cols-md5-g-4">
        <followers-list-item v-for="follower in followers" :key="follower.id" :follower="follower"></followers-list-item>
        </div>
        
      </el-dialog>
        <p>{{ followerList.length }}</p>

    </div>
  </div>
</template>

<script>
import FollowersListItem from '@/components/profile/FollowersListItem.vue'

export default {
  name: 'FollowersList.vue',
  components: {
    FollowersListItem,
  },
	computed: {
		followers() {
			// 이부분에 데이터 들어오면 될듯
			return this.$store.state.profile.image
		}
	},
  data() {
    return {
      visible: false,
      followerList: [],
    }
  },
  methods: {
    

  }
}
</script>

<style scoped>
.my-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

</style>