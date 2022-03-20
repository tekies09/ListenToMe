<template>
  <div id="side-bar" class="d-flex flex-column justify-content-between py-5">
    <div>
      <!-- button: 홈, loginState: ALL -->
      <div class="d-flex justify-content-center mb-4">
        <button @click="moveToHome" class="menu f-subtitle">홈</button>
      </div>

      <!-- button: 마이페이지, loginState: 1 -->
      <div v-if="$store.state.loginState === 1">
        <div class="d-flex justify-content-center mb-4">
          <button @click="toggleMypageBM" class="menu f-subtitle">마이 페이지</button>
        </div>

        <!-- button: 마이페이지 작은 메뉴 -->
        <div v-if="active">
          <!-- button: 북마크 -->
          <div class="d-flex justify-content-center mb-3">
            <button @click="moveToBookmark" class="menu-small f-normal-bold">북마크</button>
          </div>

          <!-- button: 상담 내역 -->
          <div class="d-flex justify-content-center mb-3">
            <button @click="moveToList" class="menu-small f-normal-bold">상담 내역</button>
          </div>

          <!-- button: 정보 수정 -->
          <div class="d-flex justify-content-center mb-4">
            <button @click="moveToUpdate" class="menu-small f-normal-bold">정보 수정</button>
          </div>
        </div>
      </div>

      <!-- button: 마이페이지, loginState: 2 -->
      <div v-else-if="$store.state.loginState === 2">
        <div class="d-flex justify-content-center mb-4">
          <button @click="toggleMypagePF" class="menu f-subtitle">마이 페이지</button>
        </div>

        <!-- button: 마이페이지 작은 메뉴 -->
        <div v-if="active">
          <!-- button: 일정 관리 -->
          <div class="d-flex justify-content-center mb-3">
            <button @click="moveToProfile" class="menu-small f-normal-bold">프로필</button>
          </div>

          <!-- button: 일정 관리 -->
          <div class="d-flex justify-content-center mb-3">
            <button @click="moveToSchedule" class="menu-small f-normal-bold">일정 관리</button>
          </div>

          <!-- button: 상담 내역 -->
          <div class="d-flex justify-content-center mb-3">
            <button @click="moveToList" class="menu-small f-normal-bold">상담 내역</button>
          </div>

          <!-- button: 통계 -->
          <div class="d-flex justify-content-center mb-4">
            <button @click="moveToStatistics" class="menu-small f-normal-bold">통계</button>
          </div>
        </div>
      </div>

      <!-- button: 상담실, loginState: 0 -->
      <div class="d-flex justify-content-center mb-3">
        <button @click="moveToCounselors" class="menu f-subtitle">리스너</button>
      </div>
    </div>

    <div>
      <!-- button: 위로 -->
      <div class="d-flex justify-content-center">
        <button @click="moveUp" class="menu-small f-normal-bold">서비스 소개</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SideBar',

  data: function () {
    return {
      active: false,
    }
  },
  
  methods: {
    moveToCounseling: function () {
      this.active = false
      this.$router.push({name: 'CounselingSetting'})
        .catch(() => {})
    },

    moveToHome: function () {
      this.active = false
      this.$router.push({name: 'Home'})
        .catch(() => {})
    },

    moveToBookmark: function () {
      this.$router.push({name: 'Bookmark'})
        .catch(() => {})
    },
    moveToProfile: function () {
      this.$router.push({name: 'Profile'})
        .catch(() => {})
    },
    moveToUpdate: function () {
      if (this.$store.state.loginState === 1){
        this.$router.push({name: 'UpdateForClient'})
          .catch(() => {})
      } else {
        this.$router.push({name: 'UpdateForCounselor'})
          .catch(() => {})
      }
    },

    toggleMypageBM: function () {
      this.active = !this.active
      this.moveToBookmark()
    },
    toggleMypagePF: function () {
      this.active = !this.active
      this.moveToProfile()
    },

    moveToList: function () {
      if (this.$store.state.loginState === 1) {
        console.log('show list of user')
        this.$router.push({name: 'List'})
          .catch(() => {})
      } else if (this.$store.state.loginState === 2) {
        console.log('show list of counselor')
        this.$router.push({name: 'ListFromCounselor'})
          .catch(() => {})
      }
    },

    moveToStatistics: function () {
      this.$router.push({name: 'Statistics'})
    },

    moveToCounselors: function () {
      this.$router.push({name: 'Counselors'})
        .catch(() => {})
    },

    moveToSchedule: function () {
      this.$router.push({name: 'Schedule'})
        .catch(() => {})
    },

    moveUp: function () {
      this.$store.dispatch('moveUp')
    },
  }
}
</script>
