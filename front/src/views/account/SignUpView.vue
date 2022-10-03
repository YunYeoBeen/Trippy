<template>
  <div>

    <form @submit.prevent="signupOne(userData)">
    <div class="email">
      <p>이메일</p>
      <el-input  id="email"
      class="input"
      v-model="userData.email" 
      placeholder="username@email.com"
      @blur="checkEmail()"
      ></el-input>
      <el-button type="primary" @click="checkEmailDuplicate(userData)">중복확인</el-button>
      <account-error-list :errorMessage="emailError" v-if="!emailFormat"></account-error-list>

    </div>

    <br>
    <div class="password">
      <p>비밀번호</p>
      <el-input v-model="userData.password" 
        type="password" 
        id="password"
        placeholder="비밀번호" 
        autocomplete="off"
        maxlength="20"
        class="input"
        @blur="checkPasswordValidity"
        show-password
        />
      <el-input v-model="passwordCheck" 
        type="password" 
        placeholder="비밀번호 확인" 
        autocomplete="off"
        maxlength="20"
        @blur="checkPasswordMatch"
        />

      <!-- 비밀번호와 비밀번호 확인되지 않으면 자동으로 매치되는지 확인하는 기능 -->
      <account-error-list :errorMessage="passwordValidityError" v-if="!passwordFormat"></account-error-list>
      <account-error-list :errorMessage="passwordMatchError" v-if="!passwordMatch"></account-error-list>
    </div>

    <br>
    <div class="nickname">
      <p>닉네임</p>
      <el-input 
      id="nickname" 
      class="input"
      v-model="userData.name" 
      placeholder="사용할 별명을 입력해주세요" 
      maxlength="10"  @blur="checkNickname"></el-input>
      <account-error-list :errorMessage="nicknameError" v-if="!nicknameFormat"></account-error-list>
    </div>

    <br>
    <div class="gender">
      <p>성별</p>
      <el-select id="gender" v-model="userData.gender" class="m-2 input" placeholder="Select">
      <el-option 

        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
        />
      </el-select>
    </div>

    <br> 
    <div class="birthdate">
      <p>생년월일</p>
      <div class="demo-date-picker input m-2">
        <el-date-picker
          class="date-input"
          id="birthdate"
          v-model="date"
          type="date"
          placeholder="생년월일을 선택해주세요!"
        />
      </div>
    </div>

    <br><br><br><br>
    <el-button type="primary" @click="checkBlank(), dateParsing(), signupOne(userData), youShallNotPass()">회원가입</el-button>
  </form>

  </div>
</template>

<script>
import AccountErrorList from '@/components/account/AccountErrorList.vue'
import { userErrorMessage } from '@/common/constant.js'
import { mapActions } from 'vuex'

export default {
  components: { 
    AccountErrorList 
    },
    name: "SignUpView",
    data() {
        return {
            userData: {
                birth: '',
                email: '',
                gender: '',
                name: '',
                password: '',
            },
            options: [
                {
                    value: 1,
                    label: '남성',
                },
                {
                    value: 0,
                    label: '여성',
                },
            ], 
            emailError: userErrorMessage.emailError,
            passwordMatchError: userErrorMessage.passwordMatchError,
            passwordValidityError: userErrorMessage.passwordValidityError,
            nicknameError: userErrorMessage.nicknameError,
            alreadyRegistered: userErrorMessage.alreadyRegistered,
            emailFormat: true,
            passwordFormat: true,
            passwordMatch: true,
            nicknameFormat: true,
            passwordCheck: '',
            date: '',
            pass: false,
        }
    },
    methods: {
      ...mapActions(['signupOne', 'checkEmailDuplicate']),

      // 받아온 날짜를 ISO string 형식으로 변환해주는 함수
      dateParsing() {
        const birthdate = new Date(this.date)
        this.userData.birth = birthdate.toISOString()
      },

      // 이메일 형식 확인 regex
      checkEmail() {
      var inputEmail = document.getElementById('email').value;
      var regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
      if (regEmail.test(inputEmail) === false) {
        this.emailFormat = false,   
        this.pass = false;                       
      } else {
        this.emailFormat = true
        this.pass = true
      }        
      },

      // 비밀번호 , 비밀번호 확인에 넣은 번호가 같은지 확인하는 함수
      checkPasswordMatch() {
        if (this.userData.password === this.passwordCheck) {
          this.passwordMatch = true
          this.pass = true
        } else {
          this.passwordMatch = false,
          this.pass = false
        }
      },

      // 비밀번호 형식 확인 regex
      checkPasswordValidity() {
        var inputPassword = document.getElementById('password').value;
        // 문자, 숫자, 그리고 최소 하나의 특수문자
        var regPassword = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d~$@$!%*#?&()+|=]{8,20}$/;
        if (regPassword.test(inputPassword)) {
          this.passwordFormat = true
          this.pass = true
          } else {
            this.passwordFormat = false
            this.pass = false
          }
      },

      // 닉네임 형식 확인 regex
      checkNickname() {
        var regNickname = /^([0-9]|[a-z]|[A-Z]|[가-힣]).{1,10}$/;
        var blank = /''/
        if (regNickname.test(this.userData.name)) {
          this.nicknameFormat = true
        } else if (blank.test(this.userData.name)) {
          this.nicknameFormat = true
          this.pass = true
        } else {
          this.nicknameFormat = false
          this.pass = false
        }
      },

      // 빈칸이 있는지 없는지 확인하는 함수
      checkBlank() {
        var emailBlank = document.getElementById('email').value
        var passwordBlank = document.getElementById('password').value
        var nicknameBlank = document.getElementById('nickname').value
        var genderBlank = document.getElementById('gender').value
        var birthdateBlank = document.getElementById('birthdate').value
        if ( emailBlank == '' | passwordBlank == '' | nicknameBlank == '' | genderBlank == '' | birthdateBlank == '' ) {
          alert("빈 칸 없이 모든 필드를 채워주세요!")
          console.log(this.userData)
        } 
      },

      // 회원가입2로 넘어갈 수 있는지 확인하는 함수
      // 중복되는 이메일을 가지고 있으면 안됨
      // 비밀번호 두 개가 같아야 함
      // 닉네임이 2글자 이상이어야 함
      youShallNotPass() {
        if ( this.pass ) { 
          console.log(this.pass)
          this.$router.push('/signup/option') 
        } else {
          console.log(this.pass)
          alert('형식에 맞는지 확인해주세요!')
        }
      }
    }
  }

</script>

<style scoped>

* {
  box-sizing: border-box;
  margin: 0; 
}

  .demo-date-picker {
    display: flex;
    width: 100%;
    padding: 0;
    flex-wrap: wrap;
  }

  .demo-date-picker .block:last-child {
    border-right: none;
  }

  button {
    width: 100%;
  }

  form {
    margin: 15% 3%;
  }

  p {
    display: flex;
  }

  .gender {
    display: flex;
    flex-direction: column;
  }

  .birthdate {
    display: flex;
    flex-direction: column; 
  }

  .input {
    margin: 2% 0;
  }

</style>