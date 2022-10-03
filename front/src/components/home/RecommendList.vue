<template>
  <div class="container">
    <div v-if="regionDiaries" class="region-diaries">
      <el-row>
        <el-col :span="8" v-for="diary in regionDiaries" :key="diary.id">
          <recommend-list-item :diary="diary"></recommend-list-item>
        </el-col>
      </el-row>
    </div>
    <div v-if="searchDiaries" class="search-diaries">
      <br>
      <el-row>
        <el-col :span="8" v-for="diary in searchDiaries" :key="diary.id">
          <recommend-list-item :diary="diary"></recommend-list-item>
        </el-col>
      </el-row>
    </div>
    <hr>
    <h3>전체 게시물</h3>
    <br>
    <div v-if="allDiaries" class="all-diaries">
      <el-row>
        <!-- infinite scroll -->
        <el-col :span="8" v-for="diary in allDiaries.slice(0,10)" :key="diary.id">
          <recommend-list-item :diary="diary"></recommend-list-item>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import RecommendListItem from '@/components/home/RecommendListItem.vue'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: "RecommendList",
  components: { 
      RecommendListItem 
  },

  created() {
    this.fetchAllDiaries()
  },

  computed: {
    ...mapGetters(['regionDiaries', 'allDiaries', 'searchDiaries']),
  },

  methods: {
    ...mapActions(['fetchAllDiaries', 'getRegionDiaries'])
  }
}
</script> 

<style scoped>
.container {
  display: flex;
  justify-content: center;
  margin: 0 3%;
}
.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

</style>