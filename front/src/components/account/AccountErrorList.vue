<template>
  <div>
    <p>아이디 혹은 비밀번호가 옳지 않습니다</p>
    <p>비밀번호가 일치하지 않습니다</p>
    <p>이메일 형식이 올바르지 않습니다</p>
    <p>별명은 10자 이내로 작성해주세요</p>
    <p>이미 가입이 된 이메일입니다</p>
    <p>인증번호가 일치하지 않습니다</p>
  </div>
</template>

<script>
export default {
    name: 'AccountErrorList.vue',
    computed: {
        
    }
}
</script>

<style scoped>
    div {
        color: red
    }
</style>