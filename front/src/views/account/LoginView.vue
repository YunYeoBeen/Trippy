<template>
  <div class="login">
    <!-- 없는 회원정보 -->
    <div class="input-box">
      <h1>TRIPPY<span>.</span></h1>
      <form @submin.prevent="login()">
          <el-input class="input" v-model="userinfo.email" placeholder="이메일" id="email" ></el-input>
          <el-input class="input" v-model="userinfo.password" type="password" placeholder="비밀번호" show-password></el-input>
          <el-button class="input" type="primary" @click="checkEmail()">로그인</el-button>
        <br><br>
        <router-link :to="{ name: 'signUp' }">회원가입</router-link>
        <span>|</span>
        <router-link :to="{ name: 'passwordFind' }">비밀번호 찾기</router-link>
        <account-error-list :errorMessage="emailError" v-show="!emailFormat"></account-error-list>
        <account-error-list :errorMessage="loginError" v-show="authentication == false"></account-error-list>
      </form>
    </div>
  </div>
</template>

<script>
import AccountErrorList from '@/components/account/AccountErrorList.vue'
import { userErrorMessage } from '@/common/constant.js'
import { mapActions } from 'vuex'

export default {
    name: 'LoginView',
    components: {
        AccountErrorList
    },
    data() {
        return { userinfo: {
            'email': '',
            'password': '',
        },
        loginError: userErrorMessage.loginError,
        emailError: userErrorMessage.emailError,
        notMember: userErrorMessage.notMember,
        emailFormat: true,
        authentication: null,
        isMember: true,
      }
    },
    methods: {
      ...mapActions(['login']),
      checkEmail() {
      var inputEmail = document.getElementById('email').value;
      var regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
      if (regEmail.test(inputEmail) === false) {
        this.emailFormat = false;
      } else {
        this.login(this.userinfo)
        }        
      },  
      checkAuthentication() {
        if ( this.authentication == false) {
          this.authentication = false;
        }
      },
      // 일단 어떤 로직을 짜야할지 몰라서 ... 함수만 만들어놓음 (일단 다 넘어가긴 함)
      checkMember() {
        if (this.isMember == true) {
          this.isMember = false;
        }
      }
    }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Alfa+Slab+One&display=swap');

  * {
    box-sizing: border-box;
    margin: 0;
    }

  .login {
    text-align: center;
    height: 92vh;
    background-color: #F16B51;
    overflow: hidden;
  }

  .input-box {
    position: absolute;
    margin: 0 5%;
    padding-top: 50%
  }

  .input {
    margin: 2% 0;
  }

  button {
    width: 100%;
    margin: 0;
  }

  a {
    text-decoration: none;
    color: white;
  }

  span {
    color: white;
    margin: 3%;
  }


  h1 {
    position: relative;
    bottom: 3rem;
    font-family: 'Alfa Slab One', cursive;
    font-weight: 300;
    font-size: 8vh;
    text-decoration: none;
    color: white;
    }

  h1 span {
    margin: 0;
    font-size: 5vh;
    font-weight: 500;
  }
</style>