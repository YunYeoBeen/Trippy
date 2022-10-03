<template>
  <div>
    <div class="email">
      <p>이메일</p>
      <el-input v-model="userinfo.email" placeholder="username@email.com"></el-input>
      <el-button type="primary" >인증하기</el-button>
      <account-error-list></account-error-list>

    </div>

    <div class="password">
      <p>비밀번호</p>
      <el-input v-model="userinfo.password" type="password" placeholder="비밀번호" show-password></el-input>
      <el-input v-model="userinfo.passwordCheck" type="password" placeholder="비밀번호 확인" show-password></el-input>

      <!-- 비밀번호와 비밀번호 확인되지 않으면 자동으로 매치되는지 확인하는 기능 -->
      <account-error-list></account-error-list>
    </div>

    <div class="nickname">
      <p>닉네임</p>
      <el-input v-model="userinfo.nickname" placeholder="사용할 별명을 입력해주세요"></el-input>
      <account-error-list></account-error-list>
    </div>

    <div class="gender">
      <p>성별</p>
      <el-select v-model="userinfo.gender" class="m-2" placeholder="Select">
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
        />
      </el-select>
    </div>

    <div class="birthdate">
      <div class="demo-date-picker">
        <div class="block">
          <span class="demonstration">생년월일</span>
          <br>
            <el-date-picker
              v-model="userinfo.birthDate"
              type="date"
              placeholder="생년월일을 선택해주세요!"
            />
        </div>
      </div>
    </div>

    <br>
    <el-button type="primary" @click="toSignUpOption()">회원가입</el-button>

  </div>
</template>

<script>
import AccountErrorList from '@/components/account/AccountErrorList.vue'

export default {
  components: { 
    AccountErrorList 
    },
    name: "SignUpView",
    data() {
        return {
            userinfo: {
                email: '',
                password: '',
                passwordCheck: '',
                nickname: '',
                gender: '',
                birthDate: '',
            },
            options: [
                {
                    value: 'Male',
                    label: '남성',
                },
                {
                    value: 'Female',
                    label: '여성',
                },
            ], 
        }
    },
    methods: {
      toSignUpOption() {
        this.$router.push('/signup/option')
      }
    }
  }

</script>

<style scoped>
  .demo-date-picker {
    display: flex;
    width: 100%;
    padding: 0;
    flex-wrap: wrap;
  }
  .demo-date-picker .block {
    padding: 30px 0;
    text-align: center;
    border-right: solid 1px var(--el-border-color);
    flex: 1;
  }
  .demo-date-picker .block:last-child {
    border-right: none;
  }
  .demo-date-picker .demonstration {
    display: block;
    color: var(--el-text-color-secondary);
    font-size: 14px;
    margin-bottom: 20px;
  }
</style>