<template>
  <div class="container">
    <search-bar></search-bar>
    
    <h3 class="search-result">검색 결과</h3>
    <div class="search-list">
      <recommend-list></recommend-list> 
    </div>
  </div>
</template>

<script>
import SearchBar from '@/components/common/SearchBar.vue'
import RecommendList from '@/components/home/RecommendList.vue'

export default {
  name: 'SearchView',
  components: {
    SearchBar,
    RecommendList,
  }
}
</script>

<style>
  .container {
    display: flex;
    flex-direction: column;
  }

  .search-result {
    font-weight: 500;
    margin-left: 5%;
  }

  .search-list {
    margin: 0 2%;
  }
</style>