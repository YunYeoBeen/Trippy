<template>
  <div class="container">
    <div class="menu">
      <p @click="goProfileEdit">
        <span class="material-symbols-outlined icon">person</span>
        프로필 수정
      </p>
      <span class="material-symbols-outlined arrow">arrow_forward_ios</span>
    </div>
    <div class="menu">
      <p @click="goPasswordChange">
        <span class="material-symbols-outlined icon">key</span>
        비밀번호 변경
      </p>
      <span class="material-symbols-outlined arrow">arrow_forward_ios</span>
    </div>
    <div class="menu">
      <p @click="logout">
        <span class="material-symbols-outlined icon">logout</span>
        로그아웃
      </p>
      <span class="material-symbols-outlined arrow">arrow_forward_ios</span>
    </div>
    <div class="menu">
      <p @click="open()">
        <span class="material-symbols-outlined icon">delete_forever</span>
        회원 탈퇴
      </p>
      <span class="material-symbols-outlined arrow">arrow_forward_ios</span>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import { ElMessage, ElMessageBox } from 'element-plus'

export default {
  name: 'SettingView',
  methods: {
    ...mapActions(['logout', 'deleteAccount']),
    goPasswordChange() {
      this.$router.push({ name: 'passwordFind' })
    },
    goProfileEdit() {
      this.$router.push({ name: 'profileEdit' })
    },
    open() {
      ElMessageBox.confirm(
        '정말로 탈퇴하시곘습니까?',
        'Warning',
        {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          type: 'warning',
        }
      )
      .then(() => {
        console.log('delete confirmation')
        this.deleteAccount()
        ElMessage({
          type: 'success',
          message: '탈퇴가 완료되었습니다',
        })
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '탈퇴가 취소되었습니다',
        })
      })
    }

  }
}
</script>

<style scoped>

.container {
  padding: 2rem 1rem;
}

.menu {
  margin: 1rem 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

p {
  display: flex;
  align-items: center;
}

.icon {
  margin-right: 0.6rem;
}

.arrow {
  font-size: 1rem;
  color: #F16B51;
}
</style>