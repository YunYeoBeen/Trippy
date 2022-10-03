<template>
  <div class="diary-create-div">
    <diary-form :diary="diary" action="create"></diary-form>
  </div>
</template>

<script>
import DiaryForm from "@/components/diary/DiaryForm.vue"

export default {
  name: 'DiaryCreateView',
  components: {
    DiaryForm,
  },
  data() {
    return {
      diary: {
        id: null,
        title: '',
        countryName: '대한민국',
        cityName: '서울특별시',
        startDate: '',
        endDate: '',
        company: 1,
        count: 1,
        postTransports: [],
        routes: [],
        detailLocations: [],
      }
    }
  }
}

</script>

<style scoped>
.diary-create-div {
  margin: 1rem 1rem 2rem 1rem;
}
</style>