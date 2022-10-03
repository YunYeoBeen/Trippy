<template>
  <div>
    <el-form @submit.prevent="onSubmit" method="POST">
      <el-form-item label="제목">
        <el-input v-model="newPost.title" placeholder="제목을 입력하세요." />
      </el-form-item>
      <el-form-item label="카테고리">
        <el-select v-model="newPost.category" placeholder="어떤 활동을 같이 하고 싶나요?">
          <el-option v-for="(cat, idx) in categories" :key="idx" :label="cat.label" :value="cat.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="활동 내용">
        <el-input v-model="newPost.description" type="textarea" placeholder="간단히 하고 싶은 활동을 설명해주세요." />
      </el-form-item>
      <el-form-item label="날짜">
        <el-date-picker v-model="newPost.startDate" type="date" placeholder="동행 시작일을 선택해주세요." value-format="YYYY-MM-DD HH:mm:ss" :disabled-date="disabledStartDate"/>
        <!-- <el-switch class="switch" v-model="newPost.isDay" size="small" active-text="당일"></el-switch> -->
        <el-switch class="switch" v-model="newPost.day" size="small" active-text="당일"></el-switch>
      </el-form-item>
      <!-- <el-form-item class="end_date" v-if="!newPost.isDay"> -->
      <el-form-item class="end_date" v-if="!newPost.day">
        <el-date-picker v-model="newPost.endDate" type="date" placeholder="동행 종료일을 선택해주세요." value-format="YYYY-MM-DD HH:mm:ss" :disabled-date="disabledEndDate"/>
      </el-form-item>
      <el-form-item label="시간">
        <el-time-picker v-model="newPost.meetingTime" value-format="YYYY-MM-DD HH:mm:ss" :disabled-seconds="disabledSeconds"/>
      </el-form-item>
      <el-form-item label="인원">
        <el-input-number v-model="newPost.recruitVolume" :min="1" :max="10"/>
      </el-form-item>
      <el-form-item>
        <el-collapse class="collapse">
          <el-collapse-item title="장소">
            <div>
              {{ newPost.countryName }}, {{ newPost.cityName }}
            </div>
          </el-collapse-item>
          <el-collapse-item>
            <template #title>
              <div>
                <span>모집 조건</span>
                <el-tag class="option-tag" v-for="option in optionTag" :key="option" >{{ option }}</el-tag>
              </div>
            </template>
            <el-form-item label="성별">
              <el-radio-group v-model="newPost.gender">
                <el-radio label="누구나" />
                <el-radio label="여성만" />
                <el-radio label="남성만" />
              </el-radio-group>
            </el-form-item>
            <el-form-item label="나이">
              <el-slider class="slider" v-model="age" range :min="19" :max="70" @input="onInput"/>
            </el-form-item>
            <el-form-item label="지역 제한">
              <!-- <el-switch v-model="newPost.isLocal" /> -->
              <el-switch v-model="newPost.local" />
            </el-form-item>
          </el-collapse-item>
        </el-collapse>
      </el-form-item>
      <el-form-item label="모임 장소">
        <el-input v-model="newPost.place" placeholder="모임 장소를 입력하세요." />
      </el-form-item>
      <el-form-item v-if="action==='create'">
        <el-button @click="onSubmit">작성하기</el-button>
      </el-form-item>
      <el-form-item v-else-if="action==='update'">
        <el-button @click="onSubmit">수정하기</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'CommunityForm',
  props: {
    post: Object,
    action: String,
  },
  data() {
    return {
      categories: [
        {
          value: 1,
          label: '식사' 
        },
        {
          value: 2,
          label: '동행' 
        },
        {
          value: 3,
          label: '파티' 
        },
        {
          value: 4,
          label: '이동수단 셰어' 
        },
        {
          value: 5,
          label: '기타' 
        },
      ],
      age: [this.post.startAge, this.post.endAge],
      newPost: {
        id: this.post.id,
        title: this.post.title,
        category: this.post.category,
        description: this.post.description,
        startDate: this.post.startDate,
        // isDay: this.post.isDay,
        day: this.post.day,
        endDate: this.post.endDate,
        meetingTime: this.post.meetingTime,
        recruitCurrentVolume: this.post.recruitCurrentVolume,
        recruitVolume: this.post.recruitVolume,
        countryName: this.post.countryName,
        cityName: this.post.cityName,
        gender: this.post.gender,
        // isLocal: this.post.isLocal,
        local: this.post.local,
        place: this.post.place,
        // locationId: this.post.locationId,
      }
    }
  },
  computed: {
    optionTag() {
      const gender = this.newPost.gender
      const startAge = this.newPost.startAge
      const endAge = this.newPost.endAge
      let age = startAge + '~' + endAge + '살'
      if (startAge === undefined && endAge === undefined || startAge === 19 && endAge === 70) {
        age = '누구나'
      }
      // const isLocal = this.newPost.isLocal
      // let local = ''
      // if (isLocal === false) {
      //   local = '어디서나'
      // } else {
      //   local = '같은 지역만'
      // }
      // return [gender, age, local]
      const local = this.newPost.local
      let isLocal = ''
      if (local === false) {
        isLocal = '어디서나'
      } else {
        isLocal = '같은 지역만'
      }
      return [gender, age, isLocal]
    },
  },
  methods: {
    ...mapActions(['createPost', 'updatePost']),
    onInput() {
      this.newPost.startAge = this.age[0]
      this.newPost.endAge = this.age[1]
    },
    onSubmit() {
      const post = this.newPost
      // if (post.title && post.category && post.description && post.startDate && post.meetingTime && post.place && (post.isDay || post.endDate)) {
      if (post.title && post.category && post.description && post.startDate && post.meetingTime && post.place && (post.day || post.endDate)) {
        // console.log(this.newPost)
        if (this.action === 'create') {
          this.createPost(this.newPost)
        } else if (this.action === 'update') {
          const payload = {
            id: this.post.id,
            content: this.newPost
          }
          // console.log(1)
          console.log(payload.content)
          this.updatePost(payload)
        }
      } else {
        alert('빈 칸 없이 모든 필드를 채워주세요!')
      }
      
    },
    disabledStartDate(date) {
      return date.getTime() < Date.now() - 3600 * 1000 * 24 
    },
    disabledEndDate(date) {
      return date.getTime() < Date.now() 
    },
    makeRange(start, end) {
      const result = []
      for (let i = start; i <= end; i ++) {
        result.push(i)
      }
      return result
    },
    disabledSeconds() {
      return this.makeRange(1, 59)
    },
  },
}
</script>

<style scoped>
.switch {
  margin-left: 0.3rem;
}

.end_date {
  margin-left: 2.3rem;
}

.option-tag {
  margin-left: 0.3rem;
}

.collapse {
  width: 100%;
}

.slider {
  position: relative;
  left: 0.5rem;
  width: 90%;
}
</style>