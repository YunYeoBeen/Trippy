<template>
  <div class="container">

    <div class="image">
      <div v-if="profilePhoto && Object.keys(profilePhoto).length === 0 && profilePhoto.constructor === Object">
        <div>이미지</div>
        <div>
          <label for="file">사진 등록</label>
          <input type="file" id="file" ref="files" @change="uploadPhoto"/>
        </div>
      </div>
      <!-- 원래 등록되어 있는 이미지 -->
      <div v-else>
        <!-- <img :src="profilePhoto.preview" :alt="profilePhoto.preview"/> -->
        <br>
        <el-button @click="removePhoto">
          x
        </el-button>
      </div>
    </div>

    <div class="profil-edit-input">
      <div class="email">
        <p>이메일</p> 
        <el-input  id="email"
        v-model="userinfo.email" 
        placeholder="username@email.com"
        disabled></el-input>
      </div>

      <div class="nickname">
        <p>닉네임</p>
        <el-input id="nickname" v-model="userinfo.name" placeholder="사용할 별명을 입력해주세요" maxlength="10"  @blur="checkNickname"></el-input>
        <account-error-list :errorMessage="nicknameError" v-if="!nicknameFormat"></account-error-list>
      </div>

      <div class="gender">
        <p>성별</p>
        <el-select id="gender" v-model="userinfo.gender" class="m-2" placeholder="Select">
        <el-option 
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
          />
        </el-select>
      </div>

      <div class="birthdate">
        <p>생년월일</p>
        <div class="demo-date-picker">
            <el-date-picker
              id="birthdate"
              v-model="userinfo.birth"
              type="date"
              placeholder="생년월일을 선택해주세요!"
            />
        </div>
      </div>

      <div class="description">
        <p>소개</p>
        <el-input id="description" v-model="userinfo.desc" maxlength="50"></el-input>
        <account-error-list :errorMessage="nicknameError" v-if="!nicknameFormat"></account-error-list>
      </div>
    </div>
      <el-button type="primary">완료</el-button>
  </div>
</template>

<script>
import { userErrorMessage } from '@/common/constant.js'
import AccountErrorList from '@/components/account/AccountErrorList.vue'
import { mapActions, } from 'vuex'

export default {
  name: 'ProfileEditView',
  components: {
    AccountErrorList
  },
  data() {
    return {
        userinfo: {
            email: this.$store.getters.currentUser.email,
            name: this.$store.getters.currentUser.name,
            gender: this.$store.getters.currentUser.gender,
            birth: this.$store.getters.currentUser.birth,
            desc: this.$store.getters.currentUser.description,
        },
        options: [
            {
                value: 'Male',
                label: '남성',
            },
            {
                value: 'Female',
                label: '여성',
            },
        ], 
        emailError: userErrorMessage.emailError,
        nicknameError: userErrorMessage.nicknameError,
        alreadyRegistered: userErrorMessage.alreadyRegistered,
        emailFormat: true,
        nicknameFormat: true,
    }
  },
  methods: {
    ...mapActions(['fetchCurrentUser',]),
    mounted() {
      this.fetchCurrentUser()
      this.genderParsing()
    },

    checkNickname() {
      var regNickname = /^([0-9]|[a-z]|[A-Z]|[가-힣]).{1,10}$/;
      var blank = /''/
      if (regNickname.test(this.userinfo.name)) {
        this.nicknameFormat = true
      } else if (blank.test(this.userinfo.name)) {
        this.nicknameFormat = true
      } else {
        this.nicknameFormat = false
      }
    },

    // 원래 저장되어 있던 프로필 사진이 나타나게 해야함
    uploadPhoto() {
      console.log(this.$refs.files.files[0])
      let photo = this.$refs.files.files[0]
      if (photo.type.substr(0, 5) === "image") {
        this.profilePhoto = 
        {
          file: this.$refs.files.files[0],
          preview: URL.createObjectURL(this.$refs.files.files[0]),
        }
      } else {
        alert("사진 파일만 추가 가능합니다")
      }

      let fileInput = document.getElementById("file")
      fileInput.value = ''
    },
    removePhoto() {
      this.profilePhoto = {}
    },
  }
}
</script>

<style scoped>
  * {
    box-sizing: border-box;
    margin: 2%;
    }

  .container {
    display: flex;
    flex-direction: column;
    margin: 0;
  }

  .delete {
    cursor: pointer;
    text-decoration: underline;
  }

  p {
    display: flex;
  }

  .gender {
    display: flex;
    flex-direction: column;
  }

  .birthdate {
    display: flex;
    flex-direction: column; 
  }

  .el-input__wrapper {
    width: 100%;
  }

  .change-delete {
    display: flex;
    justify-content: center;
    color: #F16B51;
  }

  .change-delete a {
    color: #F16B51;
    font-weight: 500;
  }

  .change-delete p {
    color: #F16B51;
    font-weight: 500;
    
  }

</style>