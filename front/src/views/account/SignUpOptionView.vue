<template>
  <div>

    <div class="description">
      <p>소개</p>
      <el-input v-model="userinfo.description" placeholder="자신을 소개해주세요!(선택)"></el-input>
    </div>

    <br>
    <el-button type="primary">완료</el-button>
  </div>
</template>

<script>

export default {
    name: "SignUpOptionView",
    components: {

    },
    data() {
        return { userinfo: {
            description: '',
        }
    }
}
}
</script>

<style>

</style>