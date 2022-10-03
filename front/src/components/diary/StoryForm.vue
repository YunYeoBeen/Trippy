<template>
  <div>
    <div v-for="(newStory, k) in newStories" :key="k">
      <div class="title-box">
        <span>상세 장소</span>
        <el-input v-model="newStory.place" class="input-box" id="title-input-box" placeholder="상세 장소를 입력하세요." />
      </div>

      <div class="photo-content-div">
        <div>
          <div v-if="newStory.photoList.length === 0" class="photo-div">
            <label :for=k>
              <icon-base viewBox="0 0 1024 1024" width="50" height="50" iconColor="#409EFF" icon-name="addphoto" class="photo-icon">
                <add-photo/>
              </icon-base>
            </label>
            <input class="photo-input" type="file" :id=k ref="files" accept="image/*" @change="uploadPhoto(k)" multiple />
          </div>
          <div v-else class="photo-preview">
            <input class="photo-input" type="file" :id=k ref="files" @change="addPhoto(k)" multiple />
            <el-carousel trigger="click" height="150px" :autoplay=false :initial-index=1 indicator-position="none">
              <el-carousel-item>
                <label :for=k>
                  <icon-base viewBox="0 0 1024 1024" width="50" height="50" iconColor="#409EFF" icon-name="addphoto" class="photo-icon">
                    <add-photo/>
                  </icon-base>
                </label>
              </el-carousel-item>
              <el-carousel-item v-for="(photo, index) in newStory.photoList" :key="index" class="file-preview-wrapper">
                <img :src="photo.preview" :alt="photo.preview"/>
                <el-button class="remove-photo" @click="removePhoto(k, index)">
                  x
                </el-button>
              </el-carousel-item>
            </el-carousel>
          </div>
        </div>

        <div>
          <el-input v-model="newStory.content" maxlength="500"
          placeholder="내용을 입력해주세요." show-word-limit type="textarea" rows=7 resize="none" class="content-input"/>
        </div>
      </div>

      <div class="rate-div">
        <span>별점</span>
        <el-rate v-model="newStory.rate" allow-half />
      </div>
      
      <el-button @click="saveStory(k)">save story</el-button>
      <el-button @click="removeStory(k)" v-show="(newStories.length >= 1) && k!=0">delete</el-button>
      <el-button @click="addStory()" v-show="newStories.length < 10" :disabled="k != newStories.length - 1">add story</el-button>
      <hr>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import AddPhoto from '@/components/icon/AddPhoto.vue'

export default {
  name: 'StoryForm',
  components: {
    AddPhoto
  },
  data() {
    return {
      newStories: [
        { 
          pk: 0,
          place: '',
          photoList: [],
          content: '',
          rate: null
        }
      ],
    }
  },
  computed: {
    // update할 때 diaryTemp 대신 해당 pk 다이어리 가져와야 함 -> 편집 창으로 들어오면 해당 pk 다이어리 내용 fetch하는 함수
    ...mapGetters(['stories', 'diaryTemp'])
  },
  methods: {
    ...mapActions(['createStory', 'deleteStory']),
    saveStory(index) {
      const addedOne = this.newStories[index]
      const newOne = {
        pk: index + 1,
        place: addedOne.place,
        photoList: addedOne.photoList,
        content: addedOne.content,
        rate: addedOne.rate
      }
      this.createStory([index, newOne])
    },

    addStory() {
      if (this.newStories[this.newStories.length - 1].place === '' || this.newStories[this.newStories.length - 1].content === '') {
        alert('내용 작성 후 스토리를 추가해주세요!')
      } else {
        this.newStories.push({
          pk: 0,
          place: '',
          photoList: [],
          content: '',
          rate: null
        })
      }
    },

    removeStory(index) {
      // console.log(index)
      this.deleteStory(index)
      this.newStories.splice(index, 1)
    },
    uploadPhoto(index) {
      // console.log(index)
      let addedPhotoList = this.newStories[index].photoList
      // console.log(this.$refs.files)
      // console.log(this.$refs.files[index].files)
      for (let i = 0; i < this.$refs.files[index].files.length; i++) {
        let photo = this.$refs.files[index].files[i]
        // console.log(photo)
        if (photo.type.substr(0, 5) === "image") {
          addedPhotoList = [
            ...addedPhotoList,
            {
              file: this.$refs.files[index].files[i],
              preview: URL.createObjectURL(this.$refs.files[index].files[i]),
            }
          ]
        } else {
          alert("사진 파일만 추가 가능합니다")
        }
      }
      this.newStories[index].photoList = addedPhotoList
      // let fileInput = document.getElementById("file")
      // fileInput.value = ''
      // console.log(this.newStories[index].photoList)
    },
    removePhoto(index, num) {
      this.newStories[index].photoList.splice(num, 1)
    },
    addPhoto(index) {
      // console.log('add', index)
      let addedPhotoList = this.newStories[index].photoList
      // console.log(this.$refs.files)
      console.log(this.$refs.files[index].files)
      for (let i = 0; i < this.$refs.files[index].files.length; i++) {
        let photo = this.$refs.files[index].files[i]
        if (photo.type.substr(0, 5) === "image") {
          addedPhotoList = [
            ...addedPhotoList,
            {
              file: this.$refs.files[index].files[i],
              preview: URL.createObjectURL(this.$refs.files[index].files[i]),
            }
          ]
        } else {
          alert("사진 파일만 추가 가능합니다")
        }
      }
      this.newStories[index].photoList = addedPhotoList
      // let fileInput = document.getElementById("file")
      // fileInput.value = ''
    },
  }
}
</script>

<style scoped>
.title-box {
  text-align: left;
  color: var(--el-text);
  font-size: 14px;
  /* margin-left: 85px; */
}
.input-box {
  width: 300px;
  margin-left: 15px;
  margin-bottom: 10px;
}
.photo-content-div {
  display: flex;
  justify-content: left;
  align-items: center;
}
.photo-div {
  text-align: center;
  align-content: center;
  width: 150px;
  height: 150px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
.photo-input {
  visibility: hidden;
}
.photo-icon {
  margin-top: 30px;
}
.photo-preview {
  width: 150px;
}
.file-preview-wrapper>img {
  position: absolute;
  top: 0px;
  right: 0px;
  width: 150px;
  height: 150px;
}
.remove-photo {
  position: absolute;
  top: 5px;
  right: 0px;
}
.add-photo {
  align-content: center;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}
.content-input {
  margin-left: 10px;
  height: 150px;
  width: 300px;
}
.rate-div {
  margin: 10px 0;
}
</style>