<template>
  <div id="sign-up" class="col-10 d-flex justify-content-center align-items-center">
    <div class="p-4 part-signup">
      <!-- header: title -->
      <header class="mb-5 text-center">
        <p class="mb-3 f-title-bold">회원 가입</p>

        <div class="form-list d-flex justify-content-around">
          <p @click="showForm" class="mb-3 f-normal-bold pointer">일반</p>
          <p @click="showFormPro" class="mb-3 f-normal-bold pointer">상담사</p>
        </div>

        <div v-if="active" class="select-list d-flex justify-content-around">
          <div class="form-list-on"/>
          <div class="form-list-off"/>
        </div>

        <div v-else class="select-list d-flex justify-content-around">
          <div class="form-list-off"/>
          <div class="form-list-on"/>
        </div>
      </header>

      <section>
        <div v-if="active">
          <signup-for-client/>
        </div>

        <div v-else>
          <signup-for-counselor/>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import SignupForClient from '@/components/accounts/SignupForClient.vue'
import SignupForCounselor from '@/components/accounts/SignupForCounselor.vue'

export default {
  name: 'Signup',

  components: {
    SignupForClient,
    SignupForCounselor
  },

  data: function () {
    return {
      active: true
    }
  },

  methods : {
    showForm() {
      this.active = true
      this.$router.push({name: 'SignupForClient'})
        .catch(() => {})
    },
    showFormPro() {
      this.active = false
      this.$router.push({name: 'SignupForCounselor'})
        .catch(() => {})
    },
  }
}
</script>
