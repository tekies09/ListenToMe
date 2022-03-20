<template>
  <div id="update-for-counselor" class="col-10 d-flex justify-content-center align-items-center">
    <div class="p-3 part-signup">
      <!-- header: title -->
      <header class="mb-5 text-center">
        <p class="mb-3 f-title-bold">정보 수정</p>
      </header>

      <section>
        <div class="d-flex">
          <div class="col-6 d-flex flex-column justify-content-between mb-4 px-4">
            <div>
              <!-- 프로필 사진 -->
              <div class="mb-3 d-flex justify-content-center">
                <div v-if="credentials_signup.photo">
                  <img :src="profile" alt="profile-image" class="form-profile-img">
                </div>

                <div v-else>
                  <img :src="counselorSample" alt="anonymous-image" class="form-profile-img">
                </div>
              </div>

              <!-- 프로필 사진 폼 -->
              <div class="mb-3">
                <input @change="onFileChange" type="file" id="myFile" class="form-control f-normal">
              </div>
            </div>

            <div>
              <!-- 학위 -->
              <div class="mb-3">
                <input v-model.trim="credentials_signup.degree" type="text" placeholder="학위" class="form-control f-normal" required />
              </div>

              <!-- 인사말 -->
              <div class="">
                <input v-model.trim="credentials_signup.greeting" type="text" placeholder="인사말" class="form-control f-normal" required />
              </div>
            </div>
          </div>

          <div class="col-6 mb-4 px-4">
            <!-- 이메일 -->
            <div class="mb-3">
              <input v-model.trim="credentials_signup.email" type="email" placeholder="이메일" class="form-control f-normal" required />
            </div>

            <!-- 비밀번호 -->
            <div class="mb-3">
              <input v-model.trim="credentials_signup.password" type="password" placeholder="비밀번호" class="form-control f-normal" required />
            </div>

            <!-- 이름 -->
            <div class="mb-3">
              <input v-model.trim="credentials_signup.name" type="text" placeholder="이름" class="form-control f-normal" required />
            </div>

            <!-- 핸드폰 번호 -->
            <div class="mb-3">
              <input
                @input="acceptNumber" v-model.trim="credentials_signup.phoneNumber"
                type="text" placeholder="핸드폰 번호" maxlength="13" class="form-control f-normal" required
              />
            </div>

            <!-- 성별 -->
            <div class="mb-3">
              <select v-model="credentials_signup.gender" class="form-select f-normal">
                <option value="" class="f-normal">성별</option>
                <option v-for="(item, index) in genderList" :key="index" :value="item.value" class="f-normal"> 
                  {{ item.text }}
                </option> 
              </select>
            </div>

            <!-- 생년월일 -->
            <div class="">
              <label for="date" class="f-normal">생년월일</label> 
              <input v-model="credentials_signup.birth" type="date" id="date" class="form-control f-normal" required/>
            </div>
          </div>
        </div>

        <!-- 소개 -->
        <div class="mb-5 px-4">
          <div>
            <input v-model.trim="credentials_signup.greeting" type="text" placeholder="소개" class="form-control f-normal" required />
          </div>
        </div>

        <!-- 버튼: ok -->
        <div class="d-flex justify-content-center">
          <button class="btn-ok f-btn" @click="SignupForCounselor(credentials_signup)">가입</button>
        </div>
      </section>
    </div>    
  </div>
</template>

<script>
import counselorSample from '@/assets/images/counselor.png'
import { mapActions } from 'vuex'

export default {
  name: 'SignupForCounselor',

  data: function() {
    return {
      counselorSample,
      profile: '',
      credentials_signup: {
        photo: '',
        email: '',
        password: '',
        name: '',
        phoneNumber: '',
        shortGreeting: '',
        gender: '',
        birth: '',
        degree: '',
        greeting: '',
        point: 1000
      },
      genderList: [
        { 
          value: "m", 
          text: "남성", 
        }, 
        { 
          value: "f", 
          text: "여성", 
        }, 
      ],
    }
  },

  methods: {
    ...mapActions([
      'SignupForCounselor',
    ]),

    onFileChange(e) {
      const file = e.target.files[0];
      this.profile = URL.createObjectURL(file);
      this.credentials_signup.photo = file.name;
    },
    acceptNumber() {
      var x = this.credentials_signup.phoneNumber.replace(/\D/g, '').match(/(\d{0,3})(\d{0,4})(\d{0,4})/);
      this.credentials_signup.phoneNumber = !x[2] ? x[1] : x[1] + '-' + x[2] + (x[3] ? '-' + x[3] : '');
    },
    submitForm: function () {  
      console.log("photo = " + this.credentials_signup.photo); 
      console.log("email = " + this.credentials_signup.email); 
      console.log("password = " + this.credentials_signup.password);
      console.log("name = " + this.credentials_signup.name);
      console.log("phoneNumber = " + this.credentials_signup.phoneNumber);
      console.log("nickname = " + this.credentials_signup.nickname); 
      console.log("gender = " + this.credentials_signup.gender); 
      console.log("birth = " + this.credentials_signup.birth);
    },
  }
}
</script>
