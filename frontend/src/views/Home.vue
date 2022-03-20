<template>
  <div id="home" class="col-10 p-routing">
    <!-- title -->
    <div class="mb-5 d-flex justify-content-between align-items-center">
      <p class="mb-0 f-title">오늘의 리스너</p>
      <button @click="moveToCounselors" class="mb-0 p-0 btn-more f-normal">more</button>
    </div>

    <!-- body -->
    <div class="d-flex justify-content-around">
      <div v-for="listener in todayListners" :key=listener.id>
        <!-- content -->
        <div @click="LoadCounselorProfile(listener)" class="p-2 card part-counselor">
          <!-- image -->
          <div class="py-3 text-center">
            <!-- <img
              :src="require(`@/assets/images/follower1.png`)" alt="counselor"
              class="card-img-top" style="width: 10vw;"
            > -->
            <img
              :src="getImgUrl(listener)" alt="counselor"
              class="card-img-top" style="width: 10vw;"
            >
          </div>

          <div class="card-body d-flex justify-content-between">
            <!-- 이름 -->
            <p class="mb-0 f-normal">{{ listener.name }}</p>
            <!-- 평점 -->
            <p class="mb-0 f-normal">{{ listener.startScore }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/index.js'
import { mapActions } from 'vuex'

export default {
  name: 'Home',

  data: function () {
    return {
      todayListners: '',
    }
  },

  methods: {
    ...mapActions([
      'LoadCounselorProfile',
    ]),

    moveToCounselors: function () {
      this.$router.push({name: 'Counselors'})
    },
    getCounselorList() {
      axios({
        methods: 'get',
        url: SERVER.URL + '/counselor-api/list/0' 
      })
      .then((res) => {
        this.todayListners = res.data.counselor.slice(0, 5)
        console.log(this.todayListners)
      })
    },
    getImgUrl(con) {
      var images = SERVER.URL + `/counselor-api/user/image/${con.photo}`
      return images
    }
  },

  created () {
    this.getCounselorList()
  }
}
</script>
