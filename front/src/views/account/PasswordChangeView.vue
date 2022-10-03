<template>
  <div class="container">
    <div class="new-password">
      <h3>새 비밀번호</h3>
      <!-- 이메일 인증때 넣었던 이메일이 bind 돼서 뜨게 만들자 -->
      <el-input v-model="userinfo.email" type="email" disabled></el-input>
      <el-input v-model="userinfo.password" id="password" type="password" placeholder="비밀번호" @blur="checkPasswordValidity"></el-input>
      <el-input v-model="newPasswordCheck" type="password" placeholder="비밀번호 확인" @blur="checkPasswordMatch"></el-input>
      <account-error-list :errorMessage="passwordValidityError" v-if="!passwordFormat"></account-error-list>
      <account-error-list :errorMessage="passwordMatchError" v-if="!passwordMatch"></account-error-list>
      <el-button type="primary" @click="changePassword(userinfo)">비밀번호 변경하기</el-button>

    </div>
  </div>
</template>

<script>
import AccountErrorList from '@/components/account/AccountErrorList.vue'
import { userErrorMessage } from '@/common/constant.js'
import { mapActions, mapGetters } from 'vuex'

export default {
  name: "PasswordChangeView",
  components: {
    AccountErrorList,
  },
  data() {
    return {
      userinfo: {
        email: '',
        password: '',
      }, 
      currentPassword: '',
      newPasswordCheck: '',
      fromPasswordFindView: this.$store.getters.fromPasswordFindView,
      passwordMatchError: userErrorMessage.passwordMatchError,
      passwordValidityError: userErrorMessage.passwordValidityError,
      passwordFormat: true,
      passwordMatch: true,
      verified: false,
    }
  },
  
  mounted() {
    console.log(this.emailInfo)
    this.userinfo.email = this.emailInfo.email
  },

  computed: {
    ...mapGetters(['emailInfo'])
  },

  methods: {
    ...mapActions(['changePassword', 'fetchCurrentUser']),

    checkPasswordMatch() {
      if (this.userinfo.password === this.newPasswordCheck) {
        this.passwordMatch = true
        this.verified = true
      } else {
        this.passwordMatch = false
      }
    },

    checkPasswordValidity() {
      var inputPassword = document.getElementById('password').value;
      var regPassword = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d~$@$!%*#?&()+|=]{8,20}$/;
      // 문자, 숫자, 특수문자의 조합(특수문자 필수 아님)
      // var regPassword = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d~!@#$%^&*()+|=]{8,20}$/
      if (regPassword.test(inputPassword)) {
        this.passwordFormat = true
        this.verified = true
        } else {
          this.passwordFormat = false
        }
    },

    // url로 들어왔거나, 비밀번호 찾기가 아닌 비밀번호 변경으로 들어온 경우 현재 비밀번호 확인 작업
    // PasswordVerification() {
    //   const currentPassword = this.$store.getters.userData.password
    //   if ( currentPassword !== this.currentPassword ) {
    //     alert('비밀번호를 다시 한 번 확인해주세요')
    //   }
    // },

    // 이메일 형식 검증하기
    checkEmail() {
      var inputEmail = document.getElementById('email').value;
      var regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
      if ( !this.userinfo.email ) {
          alert('이메일을 입력해주세요')
          this.emailSent = false
        } else if (regEmail.test(inputEmail) === false) {
        this.emailFormat = false;
        this.emailSent = false
        alert('이메일 형식을 확인해주세요')
        } else { 
          this.emailFormat = true
          this.emailSent = true
        }        
    },

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
    justify-content: center;
    margin-top: 30%;
  }

  button {
    width: 100%;
    margin-top: 20%;
  }

  h3 {
    display: flex;
    color: #F16B51;
    font-weight: 500;
  }


</style>