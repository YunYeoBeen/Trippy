<template>
  <div class="container">
    <!-- 이메일로 인증번호 받기 전 "beforeEmailVerification" 노출 -->
    <div v-if="!emailSent" class="email">
      <h3>이메일</h3>
      <p>trippy에 가입한 이메일을 작성해주세요</p>
      <el-input v-model="userinfo.email" type="email" id="email" placeholder="username@email.com"></el-input>
      <!-- 타이머 외않되?ㅠㅠ -->
      <el-button type="primary" @click="countdownTimer(), emailCode(userinfo), checkEmail()">인증번호 받기</el-button>
      <account-error-list :errorMessage="emailError" v-if="!emailFormat"></account-error-list>
    </div>

    <!-- 이메일로 인증번호 보낸 이후 노출 -->
    <div class="verification-container" v-else>
      <div class="verification-code">
        <h3>인증번호 입력</h3>
        <span>{{ resTimeData }}</span>
        <el-input v-model="verificationCode" placeholder="발송된 인증번호를 입력하세요"></el-input>
        <!-- 3분 지나면 인증확인 버튼 disable되게 -->
        <el-button type="primary" v-if="this.timeCounter == 0" disabled >인증확인</el-button>
        <el-button type="primary" v-else-if="this.timeCounter > 0" @click="emailAuth(verificationCode), emailInfo(userinfo)">인증확인</el-button><br>
        <el-button type="primary" @click="countdownReset(), successMessage(), emailCode(userinfo)">인증번호 다시받기</el-button>        
      </div>

      <br>
      <div class="verification-guide">
        <span style="color: red">입력하신 이메일로 인증번호를 보냈습니다.</span>
        <span style="color: red">발송된 인증번호를 3분내로 입력하세요.</span>
        <br>
        <span>인증 번호를 받지 못하셨나요?</span>
        <span> - 이메일 주소를 올바르게 입력했는지 확인해주세요</span>
        <span> - 스팸 메일함을 확인해주세요</span>
      </div>
  
      <account-error-list :userErrorMessage="wrongVerificationCode"></account-error-list>
    </div>

  </div>
</template>

<script>
import { mapActions } from 'vuex'
import AccountErrorList from '@/components/account/AccountErrorList.vue'
import { ElMessage } from 'element-plus'
import { userErrorMessage } from '@/common/constant.js'

export default {
    name: "PasswordFindView",
    components: {
        AccountErrorList,
    },
    data() {
      return {
          userinfo: {
            email: '',
          },
          verificationCode: '',
          emailSent: false,
          timeCounter: 180,
          resTimeData: '',
          wrongVerificationCode: userErrorMessage.wrongVerificationCode,
          emailError: userErrorMessage.emailError,
          emailFormat: true,
      }
    },

	methods: {
    ...mapActions(['fromPasswordFindView', 'emailCode', 'emailInfo']),

    countdownTimer() {
      if (this.timeCounter >= 0) {
        setTimeout(() => {
          this.timeCounter -= 1
          this.countdownTimer()
          this.resTimeData = this.prettyTime()
        }, 1000)
      } else {
        this.timeCounter = 0
      }
    },
    
    countdownReset() {
      if (this.timeCounter <= 0){
        this.timeCounter = 180
        this.countdownTimer()
      } else {
        this.timeCounter = 180
      }
    },

    pad(n, width) {
          n = n + '';
          return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n
        },

    // 타이머 모양 맞추기
    prettyTime() {
      let time = this.timeCounter / 60
      let minutes = parseInt(time)
      let seconds = Math.round((time - minutes)*60)
      return this.pad(minutes, 2) + ":" + this.pad(seconds, 2)
    },

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
    
    // 받아온 인증번호와, 입력한 인증번호가 동일한지 확인
    emailAuth() {
      console.log(this.verificationCode)
      if ( !this.verificationCode ) {
        alert('인증번호를 입력하세요') 
      } else if ( this.$store.getters.verificationCode === this.verificationCode ){
        alert('인증이 완료되었습니다')
        this.fromPasswordFindView()
      } else {
        alert('인증번호가 일치하지 않습니다')
      }
    },

    successMessage() {
        ElMessage({
          message: '인증번호가 성공적으로 발송되었습니다',
          type: 'success',
      })
    },
	}
}
</script>

<style scoped>


  .container {
    display: flex;
    justify-content: center;
    margin: 40% 0;
    padding: 0;
  }

  h3 {
    display: flex;
    color: #F16B51;
    font-weight: 500;
  }

  .email p {
    display: flex;
  }

  .el-input {
    margin: 2% 0;
  }
  .email button {
    width: 100%;
    margin: 0
  }

  .verification-code {
    width: 100%;
    margin: 0;
  }

  .verification-guide {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    font-size: 12px;
    margin: 0;
  }

.verification-guide span{
    margin: 0;
  }
  .verification-code span {
    display: flex;
  }
  .verification-code button {
    width: 100%;
    margin: 1% 0;
  }
  .verification-code el-input {
    margin: 0;
  }


</style>