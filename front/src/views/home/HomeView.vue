<template>
  <div class="home">
    <search-bar></search-bar>
    <region-list></region-list>

    <recommend-list></recommend-list>
  

    <el-divider />
    <div class="following-diary-description">
      <p>정유송님이 팔로우 하고 있는</p>
      <p><span>정무송</span> 님이 추천하는 코스</p>
    </div>
    <div class="following-diary-list">
    </div>
    <br>
    <br>

  </div>
</template>

<script>
// @ is an alias to /src
import SearchBar from '@/components/common/SearchBar.vue'
import RecommendList from '@/components/home/RecommendList.vue'
import RegionList from '@/components/common/RegionList.vue'

export default {
  name: 'HomeView',
  components: {
    SearchBar,
    RecommendList,
    RegionList,
  },
  mounted() {
    if (localStorage.getItem('reloaded')) {
        localStorage.removeItem('reloaded');
    } else {
        localStorage.setItem('reloaded', '1');
        location.reload();
    }
  },
  methods: {

    }
  }

</script>


<style scoped>
* {
  padding:0;
  margin:0;
  }

body{
	font-family:Verdana, Geneva, sans-serif;
	font-size:18px;
	background-color:#CCC;
}

.following-diary-description {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin: 0 2%;
  margin-bottom: 3%;;
}

.following-diary-description p {
	font-size: 2.5vh;
	font-weight: 500;
}

.following-diary-description span {
  color: #F16B51;
	font-size: 2.5vh;
	font-weight: 500;
}

.following-diary-list {
  /* display: flex; */
  margin: 0 2%;
}

.float{
	position:fixed;
	width:60px;
	height:60px;
	bottom:40px;
	right:40px;
	background-color:#F16B51;
	color:#FFF;
	border-radius:50px;
	text-align:center;
	box-shadow: 2px 2px 3px #999;
}

.image {

}

.my-float{
	margin-top:22px;
}

</style>