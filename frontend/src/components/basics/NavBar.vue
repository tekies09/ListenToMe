<template>
  <div id="navbar" class="fixed-top">
    <!-- login 되어 있을 때 -->
    <div
      v-if="$store.state.loginState"
      class="d-flex justify-content-between align-items-center px-5"
      :class="{'area-nav': $store.state.isNavbar, 'area-nav-top': !$store.state.isNavbar}"
    >
      <div class="d-flex align-items-center">
        <!-- image: logo -->
        <img
          @click="moveToHome" :src="require('@/assets/images/logo_white.png')" alt="logo-image"
          class="me-3 img-logo" :class="{'pointer': $store.state.isSideBar}" 
        >
        
        <!-- part: service name -->
        <p @click="moveToHome" class="mb-0 f-title-bold" :class="{'pointer': $store.state.isSideBar}">Listen to Me</p>
      </div>

      <!-- part: button -->
      <div v-if="$store.state.isSideBar" class="d-flex">
        <button @click="Logout" class="btn-tool f-normal" style="background: #ED9C9C">로그아웃</button>
      </div>
    </div>

    <!-- login "안"되어 있을 때 -->
    <div
      v-else
      class="d-flex justify-content-between align-items-center px-5"
      :class="{'area-nav': $store.state.isNavbar, 'area-nav-top': !$store.state.isNavbar}"
    >
      <div class="d-flex align-items-center">
        <!-- image: logo -->
        <img
          @click="moveToHome" :src="require('@/assets/images/logo_white.png')" alt="logo-image"
          class="me-3 img-logo"
        >
        
        <!-- part: service name -->
        <p @click="moveToHome" class="mb-0 f-title-bold">Listen to Me</p>
      </div>

      <!-- part: buttons -->
      <div class="d-flex">
        <button @click="moveToSignup" class="me-3 btn-tool f-normal" style="background: #95D0F1">회원가입</button>
        <button @click="moveToLogin" class="me-3 btn-tool f-normal" style="background: #95D0F1">로그인</button>
        <button @click="moveToLoginCounselor" class="btn-tool f-normal" style="width: 8vw; background: #95D0F1">상담사 로그인</button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'NavBar',

  methods: {
    ...mapActions([
      'Logout',
    ]),

    moveToHome: function () {
      this.$router.push({name: 'Home'})
        .catch(() => {})
    },

    moveToSignup: function () {
      this.$store.dispatch('moveDown')
      this.$router.push({name: 'SignupForClient'})
        .catch(() => {})
    },

    moveToLogin: function () {
      this.$store.dispatch('moveDown')
      this.$router.push({name: 'LoginForClient'})
        .catch(() => {})
    },

    moveToLoginCounselor: function () {
      this.$store.dispatch('moveDown')
      this.$router.push({name: 'LoginForCounselor'})
        .catch(() => {})
    }
  },
}
</script>
