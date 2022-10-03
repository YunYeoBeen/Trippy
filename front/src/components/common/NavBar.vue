<template>
  <div class="navbar">
    <div class="buttons">
      <el-button class="button" link @click="goHome">
        <div class="menu">
          <span class="material-symbols-outlined menu-icon">home</span>
          <span class="menu-desc">HOME</span>
        </div>
      </el-button>
      <el-button class="button" link @click="goFeed">
        <div class="menu">
          <span class="material-symbols-outlined menu-icon">explore</span>
          <span class="menu-desc">FEED</span>
        </div>
      </el-button>
      <el-popover v-model:visible="isClicked" placement="top" :width="150" trigger="click">
        <div class="popover">
          <el-button class="form-button diary" link @click="goDiaryForm">
            <span class="form-desc">다이어리</span>
            <span class="material-symbols-outlined menu-icon">description</span>
          </el-button>
          <el-button class="form-button community" link @click="goCommunityForm">
            <span class="form-desc">동행찾기</span>
            <span class="material-symbols-outlined menu-icon">groups_2</span>
          </el-button>
        </div>
        <template #reference>
          <el-button class="button" link ref="create">
            <div class="menu">
              <span class="material-symbols-outlined menu-icon">edit</span>
              <span class="menu-desc">WRITE</span>
            </div>
          </el-button>
        </template>
      </el-popover>
      <el-button class="button" link @click="goChat">
        <div class="menu">
          <span class="material-symbols-outlined menu-icon">forum</span>
          <span class="menu-desc">CHAT</span>
        </div>
      </el-button>
      <el-button class="button" link @click="goProfile">
        <div class="menu">
          <span class="material-symbols-outlined menu-icon profile">person</span>
          <span class="menu-desc">MY</span>
        </div>
      </el-button>
    </div>
  </div>
</template>

<script>
export default {
	name: 'NavBar',
	data() {
		return {
			isClicked: false,
		}
	},
	methods: {
		toggle() {
			this.isClicked = !this.isClicked
		},	
		goHome() {
      this.isClicked = false
			this.$router.push({ name: 'home' })
		},
		goFeed() {
      this.isClicked = false
		},
		goDiaryForm() {
      this.toggle()
			this.$router.push({ name: 'diaryCreate' })
		},
		goCommunityForm() {
      this.toggle()
			this.$router.push({ name: 'communityCreate' })
		},
    goChat() {
      this.isClicked = false
      this.$router.push({ name: 'chatList' })
    },
		goProfile() {
      this.isClicked = false
			this.$router.push({ name: 'profile' })
		}
	},
}
</script>

<style scoped>
.navbar {
  position: fixed;
  bottom: 0vh;
  width: 100%;
  /* height: 3rem; */
  border-top: 1px solid #d9d9d9;
  background-color: #fff;
  padding: 0.3rem;
}

.forms {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.popover {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.form-desc {
  margin-right: 0.5rem;
}

.community {
  margin: 0 !important;
}

.buttons {
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.button {
  margin: 0;
}

.menu {
  display: flex;
  flex-direction: column;
}

.menu-icon {
  font-size: 1.5rem;
  margin-bottom: 0.3rem;
}

.menu-desc {
  font-size: 0.6rem;
  /* font-weight: bold; */
}

</style>