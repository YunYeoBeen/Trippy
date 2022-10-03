<template>
  <div>
  <!-- <li class="comment-list-item">
    <router-link :to="{ name: 'profile', params: { username: comment.user.username } }">
      <i class="fa-solid fa-circle-user"></i>{{ comment.user.username }}
    </router-link>

    <span v-if="!isEditing">{{ payload.content }}</span>

    <span v-if="isEditing">
      <input type="text" v-model="payload.content">
      <a class="editdelete" @click="onUpdate"><i class="fa-solid fa-pen-clip"></i></a> |
      <a class="editdelete" @click="switchIsEditing"><i class="fa-solid fa-xmark"></i></a>
    </span>

    <span v-if="currentUser.username === comment.user.username && !isEditing">
      <a class="editdelete" @click="switchIsEditing"><i class="fa-solid fa-pen-clip"></i></a> |
      <a class="editdelete" @click="deleteComment(payload)"><i class="fa-solid fa-trash-can"></i></a>
    </span>
  </li> -->
    <div v-for="(comment, idx) in comments" :key="idx" class="parent-child">
      <!-- 댓글 -->
      <div class="parent-comment">
        <router-link :to="{ name: 'profile' }">
          <el-avatar :size="40" src="" />
        </router-link>
        <div>
          <p class="member">{{ comment.member }}</p>
          <p class="content">{{ comment.content }}</p>
          <!-- 나중에는 comment.pk로 바꿔서 보내야할 듯 -->
          <span class="leave-comment" @click="sendInfo(comment.member)">답글 달기</span>
          <span class="leave-comment" @click="editComment(comment.content)">수정</span>
          <span class="leave-comment">삭제</span>
        </div>
      </div>
      <!-- 대댓글 -->
      <div v-for="(child, index) in comment.children" :key="index">
        <div class="child-comment">
          <router-link :to="{ name: 'profile' }">
            <el-avatar :size="40" src="" />
          </router-link>
          <div>
            <p class="member">{{ child.member }}</p>
            <p class="content">{{ child.content }}</p>
            <span class="leave-comment" @click="editComment(child.content)">수정</span>
            <span class="leave-comment">삭제</span>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'CommentItem',
  props: { 
    comments: Array,
  },
  data() {
    return {
      isEditing: false,
      // payload: {
      //   diaryPk: this.comment.diary.pk,
      //   commentPk: this.comment.pk,
      //   content: this.comment.content
      // },
    }
  },
  computed: {
    ...mapGetters(['currentUser',]),
  },
  methods: {
    ...mapActions(['updateComment', 'deleteComment', 'showParent']),
    switchIsEditing() {
      this.isEditing = !this.isEditing
    },
    onUpdate() {
      this.updateComment(this.payload)
      this.isEditing = false
    },
    sendInfo(member) {
      console.log(member)
      this.showParent(member)
    },
    editComment(content) {
      console.log(content)
      this.updateComment(content)
    }
  },
}
</script>

<style scoped>
/* @import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@300;500&display=swap');
* { 
      font-family: 'IBM Plex Sans KR', sans-serif;
      font-weight: 100;
    } 
.editdelete {
  margin-left: 5px;
  cursor: pointer;
  text-decoration: none;
  color: rgb(251, 66, 66);
}
i {
  margin-right: 5px;
} */
.parent-child {
  margin-bottom: 1.2rem;
}

.parent-comment {
  display: flex;
  margin-bottom: 0.5rem;
}

.parent-comment > div {
  text-align: left;
  margin-left: 0.5rem;
}

.child-comment {
  display: flex;
  background-color: bisque;
  border-radius: 1rem;
  margin-left: 3rem;
  margin-bottom: 0.5rem;
  padding: 1rem;
}

.child-comment > div {
  text-align: left;
  margin-left: 0.5rem;
}

.member {
  color: black;
  font-size: 0.8rem;
}

.content {
  color: black;
  font-size: 0.8rem;
  font-weight: 100;
}

.leave-comment {
  font-size: 0.7rem;
  font-weight: 100;
  margin-right: 0.5rem;
}
</style>