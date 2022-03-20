<template>
  <div id="login-for-client" class="col-10 d-flex justify-content-center align-items-center">
    <div class="p-5 part-login">
      <!-- header: title -->
      <header class="mb-5 text-center">
        <img :src="require('@/assets/images/logo_blue.png')" alt="logo-image" class="img-logo-login">
        <p class="mt-3 f-title">User Login</p>
      </header>

      <section class="mb-5">
        <!-- 이메일 -->
        <div class="mb-3 px-4">
          <input v-model.trim="login.email" type="email" placeholder="이메일" class="form-control f-normal" required />
        </div>

        <!-- 비밀번호 -->
        <div class="px-4">
          <input v-model.trim="login.password" type="password" placeholder="비밀번호" class="form-control f-normal" required />
        </div>
      </section>

      <footer class="d-flex justify-content-center">
        <button @click.prevent="Login(login), saveCurrentUser" class="btn-ok f-btn me-5">로그인</button>
        <button @click="goToSignup" class="btn-ok f-btn" style="background: #ED9C9C">회원 가입</button>
      </footer>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'LoginForClient',

  data: function () {
    return {
      login: {
        email: '',
        password: ''
      },
    }
  },

  methods: {
    ...mapActions([
      'Login',
    ]),
    saveCurrentUser: function() {
      this.$store.state.dispatch('saveCurrentUser', this.email)
    },

    submitlogin() { 
      console.log("email = " + this.login.email); 
      console.log("pass = " + this.login.password);
    },
    goToSignup() {
      this.$router.push({name: 'SignupForClient'})
    }
  },
}
</script>
