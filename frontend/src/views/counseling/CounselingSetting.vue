<template>
  <div id="counseling-main" class="col-10 d-flex justify-content-center align-items-center">
    <div class="p-5 part-room-setting">
      <!-- header: title -->
      <header class="mb-5 text-center">
        <div v-if="$store.state.loginState === 1">
          <p class="f-title-bold">상담실에 입장하기 <button @click="help()" class="btn-ok f-btn"> 도움말 </button></p>
        </div>
        <div v-else-if="$store.state.loginState === 2">
          <p class="f-title-bold">상담실 준비하기 <button @click="help()" class="btn-ok f-btn"> 도움말 </button></p>
        </div>
      </header>

      <!-- body: form -->
      <section class="mb-5">
        <!-- form: participant -->
        


        <!-- form: room -->


        <div class="d-flex mb-4">
          <label for="participant" class="col-2 form-label f-subtitle">Participant</label>
          <input v-model="myUserName" type="text" id="participant" class="form-control f-normal" required>
        </div>

        <!-- form: room -->
        <div class="d-flex mb-4">
          <label for="session" class="col-2 form-label f-subtitle">Room</label>
          <input v-model="mySessionId" type="text" id="session" class="form-control f-normal" required>
        </div>
      </section>

      <!-- footer: OK button -->
      <footer class="d-flex justify-content-center">
        <button @click="joinSession(), getCounselingInfo()" class="btn-ok f-btn">입장</button>
      </footer>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/index.js'
import { mapGetters } from 'vuex'

export default {
  name: 'CounselingSetting',

  data () {
    return {
      mySessionId: '',
      myUserName: '',  //here username
    }
  },

  computed: {
    ...mapGetters([ 
      'GE_USERSESSION',
      'GE_USERID',
      // {myName: 'getFirstItem'}, <-- getFirstItem을 myName으로 매핑
    ]),
  },

  methods: {
    help() {
      console.log(this.GE_USERSESSION);
      alert("해당 상담실의 방의 번호는 "+ this.GE_USERSESSION+ " 입니다. \n 해당 방 번호를 입력해주시고 Participant의 이름(별명)을 입력해 주시면됩니다. \n 입력이 완료되었다면 입장버튼을 누르시면 됩니다.");
    },
    joinSession () {
      this.$store.dispatch('SE_USERID', this.myUserName)
      this.$store.dispatch('SE_USERSESSION', this.mySessionId)
      this.$store.dispatch('toggleSideBar')  // side bar 토글링
      this.$router.push({name: 'Counseling'})
    },

    getCounselingInfo: function () {
      axios({
        method: 'get',
        url: SERVER.URL + SERVER.ROUTES.counseling + `${this.mySessionId}/`,
        headers: {
          'Content-Type': 'application/json',
          'access-token': `${this.$store.state.authToken}`
        },
      })
        .then(res => {
          console.log(res.data)
          console.log(this.$store.state.usersession)
          if (res.data.sticker === true) {
            this.$store.dispatch('toggleSticker')
            console.log(this.$store.state.isSticker)
          }

          this.$store.dispatch('checkCounselingState', res.data.state)
        })
    }
  },

  created () {
    this.mySessionId = this.GE_USERSESSION
    this.myUserName = this.GE_USERID
    this.mySessionId = ""
  },
}
</script>
