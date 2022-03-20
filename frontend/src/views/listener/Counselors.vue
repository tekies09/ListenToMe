<template>
  <div id="counselors" class="col-10 p-routing overflow-auto">
    <!-- title -->
    <div class="mb-5 d-flex justify-content-between align-items-center">
      <p class="mb-0 f-title">전체 리스너</p>
      <!-- <button @click="moveToHome" class="mb-0 p-0 btn-more f-normal">back</button> -->
    </div>

    <!-- content -->
    <div class="row row-cols-5 g-5 mb-5">
      <div 
        v-for="counselor in counselorList" :key="counselor.id" 
        @click="LoadCounselorProfile(counselor)"
      >
        <div class="col">
          <div class="card part-counselor">
            <!-- image -->
            <div class="py-3 text-center">
              <!-- <img
                :src="require(`@/assets/images/follower1.png`)" alt="counselor"
                class="card-img-top" style="width: 10vw;"
              > -->
              <img
                :src="getImgUrl(counselor)" alt="counselor"
                class="card-img-top" style="width: 10vw;"
              >
            </div>

            <div class="card-body px-4">
              <!-- 카테고리 -->
              <div class="d-flex mb-3">
                <div v-if="counselor.category.length === 0">
                  <p class="mb-0 me-2 text-center part-cat f-normal">미정</p>
                </div>
                <div v-else class="d-flex justify-content-between">
                  <div v-for="(c, idx) in counselor.category" :key="idx">
                    <p class="mb-0 me-1 text-center part-cat f-normal" style="background: #FFC688;">{{ c }}</p>
                  </div>
                </div>
              </div>

              <div class="d-flex justify-content-between">
                <!-- 이름 -->
                <p class="mb-0 f-normal">{{ counselor.name }}</p>
                <!-- 평점 -->
                <p class="mb-0 f-normal">{{ counselor.startScore }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- pagination -->
    <ul class="d-flex justify-content-center pagination">
      <li class="page-item mb-0" style="width: 4vw;"><p @click="setBack" class="mb-0 page-link text-center f-normal">Prev</p></li>
      <li
        v-for="n in this.totalPages" :key=n class="mb-0 page-item" style="width: 2vw;"
      >
        <p @click="setPage(n)" class="mb-0 page-link text-center f-normal">{{ n }}</p>
      </li>
      <li class="mb-0 page-item" style="width: 4vw;"><p @click="setNext" class="mb-0 page-link text-center f-normal">Next</p></li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'
import SERVER from "@/api/index.js"
import { mapActions } from 'vuex'

export default {
  name: 'Counselors',

  data: function () {
    return {
      currentPage: 1,
      totalPages: 0,

      counselorList: [],
      counselorEmail: '',

      // 클릭한 상담사 기록
      counselorInfo: [],
    }
  },
  
  methods: {
    ...mapActions([
      'LoadCounselorProfile',
    ]),

    moveToHome: function () {
      this.$router.push({name: 'Home'})
    },

    getCounselorList(page) {
      axios({
        methods: 'get',
        url: SERVER.URL + '/counselor-api/list/' + `${page}/`,
        headers: {
          'Content-Type': 'application/json',
          'access-token': `${this.$store.state.authToken}`
        },
      })
        .then((res) => {
          console.log(res.data)
          this.totalPages = res.data.size + 1
          console.log(this.totalPages)
          this.counselorList = res.data.counselor
          // this.imgName = res.data.counselor.photo
        })
        .catch((err) => console.log(err))
    },

    setPage: function (page) {
      this.currentPage = page
      this.getCounselorList(this.currentPage - 1)
    },
    setBack: function () {
      if (this.currentPage !== 1) {
        this.currentPage--
      }
      this.getCounselorList(this.currentPage - 1)
      console.log(this.currentPage)
    },
    setNext: function () {
      if (this.currentPage !== this. totalPages) {
        this.currentPage++
      }
      this.getCounselorList(this.currentPage - 1)
      console.log(this.currentPage)
    },


    getImgUrl(con) {
      var images = SERVER.URL + `/counselor-api/user/image/${con.photo}`
      return images
    }
  },

  created() {
    this.getCounselorList(0)
  }
}
</script>
