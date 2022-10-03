<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <router-link :to="{ name: 'diaryCreate' }">Create Diary</router-link>
    <router-link :to="{ name: 'login' }">Login</router-link>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'HomeView',
}
</script>
