<template>
  <div class="container">
    <el-row>
      <el-col :span="8" v-for="diary in myDiaries" :key="diary.id">
        <my-diaries-list-item :diary="diary"></my-diaries-list-item>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import MyDiariesListItem from '@/components/profile/MyDiariesListItem.vue'
import { mapGetters } from 'vuex'

export default {
  name: "RecommendList",
  components: { 
    MyDiariesListItem 
  },

  computed: {
    ...mapGetters(['myDiaries']),
  },
  methods: {
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