<template>
  <div id="signup-for-client" class="d-flex flex-column justify-content-between px-5">
    <!-- 이메일 -->
    <div class="mb-3 px-4">
      <input v-model.trim="credentials_signup.email" type="email" placeholder="이메일" class="form-control f-normal" required />
    </div>

    <!-- 비밀번호 -->
    <div class="mb-3 px-4">
      <input v-model.trim="credentials_signup.password" type="password" placeholder="비밀번호" class="form-control f-normal" required />
    </div>

    <!-- 이름 -->
    <div class="mb-3 px-4">
      <input v-model.trim="credentials_signup.name" type="text" placeholder="이름" class="form-control f-normal" required />
    </div>

    <!-- 핸드폰 번호 -->
    <div class="mb-3 px-4">
      <input
        @input="acceptNumber" v-model.trim="credentials_signup.phoneNumber"
        type="text" placeholder="핸드폰 번호" maxlength="13" class="form-control f-normal" required
      />
    </div>

    <!-- 닉네임 -->
    <div class="mb-3 px-4">
      <input v-model.trim="credentials_signup.nickname" type="text" placeholder="닉네임" class="form-control f-normal" required />
    </div>

    <!-- 성별 -->
    <div class="mb-3 px-4">
      <select v-model="credentials_signup.gender" class="form-select f-normal">
        <option value="" class="f-normal">성별</option>
        <option v-for="(item, index) in genderList" :key="index" :value="item.value" class="f-normal"> 
          {{ item.text }}
        </option> 
      </select>
    </div>

    <!-- 생년월일 -->
    <div class="mb-5 px-4">
      <!-- <label for="date" class="f-normal">생년월일</label> -->
      <input v-model="credentials_signup.birth" type="date" id="date" class="form-control f-normal" required/>
    </div>

    <!-- 버튼: ok -->
    <div class="d-flex justify-content-center">
      <button class="btn-ok f-btn" @click="Signup(credentials_signup)">가입</button>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'SignupForClient',

  data: function() {
    return {
      credentials_signup : {
        email: '',
        password: '',
        name: '',
        phoneNumber: '',
        nickname: '',
        gender: '',
        birth: '',
        point: '10000',
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
      'Signup',
    ]),

    acceptNumber() {
      var x = this.credentials_signup.phoneNumber.replace(/\D/g, '').match(/(\d{0,3})(\d{0,4})(\d{0,4})/);
      this.credentials_signup.phoneNumber = !x[2] ? x[1] : x[1] + '-' + x[2] + (x[3] ? '-' + x[3] : '');
    },

    submitForm: function () { 
      console.log("email = " + this.credentials_signup.email); 
      console.log("password = " + this.credentials_signup.password);
      console.log("name = " + this.credentials_signup.name);
      console.log("phoneNumber = " + this.credentials_signup.phoneNumber);
      console.log("nickname = " + this.credentials_signup.nickname); 
      console.log("gender = " + this.credentials_signup.gender); 
      console.log("birth = " + this.credentials_signup.birth);
      console.log("point = " + this.credentials_signup.point);
    },
  }
}
</script>
