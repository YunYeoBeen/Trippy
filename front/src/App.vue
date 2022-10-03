<template>
  <div id="app">
    <the-header></the-header>
    <router-view/>
    <nav-bar v-if="this.$route.name !== 'login' && this.$route.name !== 'signUp' && this.$route.name !== 'signUpOption'"></nav-bar>
  </div>
</template>

<script>
import TheHeader from '@/components/common/TheHeader.vue'
import NavBar from '@/components/common/NavBar.vue'
// import { mapActions } from 'vuex';
export default ({
  name: 'App',
  components: {
    TheHeader,
    NavBar
  },
  methods: {
    // ...mapActions(['reissueToken'])
    // notShowLoginJoin() {
    //   this.$route.name !== 'login'
    //   this.$route.name !== 'signUp'
    // }
  },
  // mounted() {
  //   if (localStorage.getItem('reloaded')) {
  //       localStorage.removeItem('reloaded');
  //   } else {
  //       localStorage.setItem('reloaded', '1');
  //       location.reload();
  //   }
  //   this.reissueToken()
  // },
})
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@300;500&display=swap');

  * { 
      font-family: 'IBM Plex Sans KR', sans-serif;
      font-weight: 100;
    } 

:root {
  --el-color-primary: #F16B51 !important;
}

* {
  box-sizing: border-box;
  margin: 0;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
