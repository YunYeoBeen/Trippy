<template>
  <div>
    <form @submit.prevent="onSubmit">
      <div class="comment-form">
        <!-- 수정 상태인지 아닌지 체크 -->
				<el-input class="comment-input" type="text" id="comment" v-model="content" required/>
				<el-button class="btn" link>
          <span class="material-symbols-outlined">send</span>
        </el-button>
			</div>
    </form>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'

export default {
    name: 'CommentForm',
    data() {
      return {
        content: '',
      }
    },
    computed: {
      ...mapGetters(['comment', 'isUpdating']),
      // content() {
      //   return this.comment
      // }
    },
		methods: {
			...mapActions(['createComment']),
			onSubmit() {
        // diary.pk diarydetail에서 props로 받아와서 같이 넘겨주기
				this.createComment(this.content),
				this.content = ''
			}
		}
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@300;500&display=swap');

  * { 
      font-family: 'IBM Plex Sans KR', sans-serif;
    } 

button {
  margin-left: 12px;
}
.material-symbols-outlined {
  color: #F16B51;
}
.comment-form {
  display: flex;
  width: 90vw;
}
</style>