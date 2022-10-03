<template>
  <div>
    <input type="text">
    <input type="password">
    <button>로그인</button>
    <router-link :to="{ name: 'signUp' }">회원가입</router-link>
    <router-link :to="{ name: 'passwordFind' }">비밀번호 찾기</router-link>

    <account-error-list></account-error-list>
  </div>
</template>

<script>
import AccountErrorList from '@/components/account/AccountErrorList.vue'

export default {
    name: 'LoginView',
    components: {
        AccountErrorList
    }
}
</script>

<style>

</style>