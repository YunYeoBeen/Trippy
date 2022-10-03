<template>
  <div>
    <el-popover class="popover" v-model:visible="isClicked" placement="left-start" trigger="click">
      <el-button class="button" link @click="goEdit">
        수정하기
        <span class="material-symbols-outlined icon">edit</span>
      </el-button>
      <el-button class="button" link @click="goDelete">
        삭제하기
        <span class="material-symbols-outlined icon">delete</span>
      </el-button>
      <template #reference>
        <el-button class="button" link ref="create">
          <span class="material-symbols-outlined">more_vert</span>
        </el-button>
      </template>
    </el-popover>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'DeleteEditButton',
  data() {
    return {
      isClicked: false
    }
  },
  methods: {
    ...mapActions(['deleteDiary', 'deletePost']),
    goEdit() {
      if (this.$route.name === 'diaryDetail') {
        this.$router.push({ name: 'diaryEdit' })
      } else {
        this.$router.push({ name: 'communityEdit' })
      }
    },
    goDelete() {
      if (confirm('정말 삭제하시겠습니까?')) {
        if (this.$route.name === 'diaryDetail') {
          console.log('다이어리 삭제')
          this.deleteDiary(this.$route.params.diaryPk)
        } else {
          console.log('동행찾기 삭제')
          this.deletePost(this.$route.params.postPk)
        }
      }
    }
  }
}
</script>

<style scoped>
.popover {
  display: flex;
  justify-content: center;
  align-items: center;
}

.button {
  font-size: 1rem;
}

.button:first-child {
  margin-bottom: 0.5rem;
}

.icon {
  font-size: 1rem;
  margin-left: 1rem;
}
</style>