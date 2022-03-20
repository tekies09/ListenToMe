<template>
  <div id="update-for-client" class="col-10 d-flex justify-content-center align-items-center">
    <div class="p-4 part-update">
      <!-- header: title -->
      <header class="mb-5 text-center">
        <p class="mb-3 f-title-bold">정보 수정</p>
      </header>

      <section>
        <!-- 이메일 -->
        <div class="mb-3 px-4">
          <input v-model.trim="credentials.email" type="email" class="form-control f-normal" required />
        </div>

        <!-- 비밀번호 -->
        <div class="mb-3 px-4">
          <input v-model.trim="credentials.password" type="password" placeholder="비밀번호" class="form-control f-normal" required />
        </div>

        <!-- 이름 -->
        <div class="mb-3 px-4">
          <input v-model.trim="credentials.name" type="text" class="form-control f-normal" required />
        </div>

        <!-- 핸드폰 번호 -->
        <div class="mb-3 px-4">
          <input
            @input="acceptNumber" v-model.trim="credentials.phoneNumber"
            type="text" maxlength="13" class="form-control f-normal" required
          />
        </div>

        <!-- 닉네임 -->
        <div class="mb-3 px-4">
          <input v-model.trim="credentials.nickname" type="text" placeholder="닉네임" class="form-control f-normal" required />
        </div>

        <!-- 성별 -->
        <div class="mb-3 px-4">
          <select v-model="credentials.gender" class="form-select f-normal">
            <option value="" class="f-normal">성별</option>
            <option v-for="(item, index) in genderList" :key="index" :value="item.value" class="f-normal"> 
              {{ item.text }}
            </option> 
          </select>
        </div>

        <!-- 생년월일 -->
        <div class="mb-5 px-4">
          <input v-model="credentials.birth" type="date" id="date" class="form-control f-normal" required/>
        </div>

        <!-- 버튼: ok -->
        <div class="d-flex justify-content-center">
          <button class="btn-ok f-btn" @click="updatePersonalInfo(credentials)">수정</button>
        </div>
      </section>
    </div>    
  </div>
</template>

<script>
import axios from "axios";
import SERVER from "@/api/index.js";
// import { mapActions } from 'vuex'

export default {
  name: 'UpdateForClient',

  data: function() {
    return {
      credentials : {
        email: '',
        password: '',
        name: '',
        phoneNumber: '',
        nickname: '',
        gender: '',
        birth: '',
        point: '',
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
    acceptNumber() {
      var x = this.credentials.phoneNumber.replace(/\D/g, '').match(/(\d{0,3})(\d{0,4})(\d{0,4})/);
      this.credentials.phoneNumber = !x[2] ? x[1] : x[1] + '-' + x[2] + (x[3] ? '-' + x[3] : '');
    },

    updatePersonalInfo() {
      axios({
        method: 'put',
        url: SERVER.URL + '/client-api/user',
        data: this.credentials,
      })
      .then((res) => {
        console.log(res)
        console.log('회원정보 수정 완료')
        this.$router.push("/");
      })
      .catch((err) => console.log(err));
    },
    
    getClienData() {
      axios({
        method: 'get',
        url: SERVER.URL + '/client-api/user',
        headers: {
          'access-token': `${this.$store.state.authToken}`
        },
      })
      .then((res) => {
        console.log(res.data.userInfo)
        this.credentials.email = res.data.userInfo.email
        this.credentials.name = res.data.userInfo.name
        this.credentials.phoneNumber = res.data.userInfo.phoneNumber
        this.credentials.nickname = res.data.userInfo.nickname
        this.credentials.gender = res.data.userInfo.gender
        this.credentials.birth = res.data.userInfo.birth
        this.credentials.point = res.data.userInfo.point
      })
      .catch((err) => console.log(err));
    },
  },
  created() {
    this.getClienData()
  }
}
</script>
