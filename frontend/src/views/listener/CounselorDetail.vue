<template>
  <div id="counselor-detail" class="col-10 d-flex flex-column align-items-center pt-5 overflow-auto">
    <div class="mb-4 px-4 py-3 part-profile">
      <!-- header -->
      <div class="d-flex align-items-center row">
        <!-- name -->
        <div class="col-md-3 d-flex">
          <h2 class="mb-0 me-5 mx-5 fw-bold">{{ this.counselorDetail.userInfo.name }}</h2>
        </div>

        <div class="col-md-7 d-flex row">
          <div class="col-12 d-flex mt-2 mb-2">
            <div>
              <p class="mb-0 me-2 f-subtitle">{{ this.counselorDetail.userInfo.shortGreeting }}</p>
            </div>
          </div>
          <!-- category -->
          <div class="col-sm-11 d-flex">
            <p class="f-subtitle mt-2 mb-0">주요 상담 분야</p>
            <div v-if="this.counselorDetail.category.length === 0">
              <p class="mb-0 mt-2 mx-4 text-center part-cat f-normal">미정</p>
            </div>
            <div v-else class="d-flex justify-content-between">
              <div v-for="(c, idx) in this.counselorDetail.category" :key="idx">
                <p class="mb-0 mt-1 mx-2 text-center part-cat f-normal" style="background: #FFC688;">{{ c.category.category }}</p>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-2 d-flex ms-4">
          <div v-if="$store.state.loginState">
            <button @click="setBookmark()" class="btn-func f-normal" style="background: #CFE7EB">북마크</button>
          </div>
        </div>
      </div>
      
      <hr class="my-4">

      <div class="d-flex mb-5">
        <div class="col-4 px-2 d-flex flex-column justify-content-between">
          <!-- profile image -->
          <div class="mb-5 text-center">
            <img :src="getImgUrl(this.counselorDetail.userInfo)" class="card-img-top" alt="counselor" style="width: 18vw;">
            <!-- <img :src="require('@/assets/images/counselor.png')" class="card-img-top" alt="counselor" style="width: 14vw;"> -->
          </div>

          <!-- history -->
          <div class="sec-profile">
            <p class="mb-0 p-3 f-normal">{{ this.counselorDetail.userInfo.degree }}</p>
          </div>
        </div>

        <div class="col-8 px-2 d-flex flex-column justify-content-between">
          <!-- content -->
          <div class="mb-5">
            <div class="d-flex">
              <p class="f-subtitle me-2">소개</p>
            </div>
            <div class="sec-profile">
              <p class="mb-0 p-3 f-normal">{{ this.counselorDetail.userInfo.greeting }}</p>
            </div>
          </div>

          <!-- review -->
          <div class="mb-1">
            <div class="d-flex">
              <div v-if="sumReviews === 0">
                <p class="me-2 f-subtitle">평점 0</p>
              </div>
              <div v-else>
                <p class="me-2 f-subtitle">평점 {{ sumReviews / totalReviews }}</p>
              </div>
              <p @click="changeStatus" class="me-2 text-center part-cat f-normal">더보기</p>
            </div>
            <div class="p-3 sec-profile">
              <div v-for="review in reviews" :key="review.id" class="d-flex flex-column">
                <div class="d-flex mb-1">
                  <p class="mb-0 me-2 f-normal">{{ review.nickname }}</p>
                  <p class="mb-0 me-2 f-normal">{{ review.startScore }}점</p>
                </div>
                <div>
                  <p class="mb-0 f-subtitle">{{ review.content }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <hr class="my-1">
      
      <div>
        <counselor-schedule :coEmail="coEmail"/>
      </div>
    </div>

    <div v-if="active" class="mb-5 p-4 part-profile">
      <!-- review -->
      <div class="">
        <div class="d-flex">
          <div v-if="sumReviews === 0">
            <p class="me-2 f-subtitle">평점 0</p>
          </div>
          <div v-else>
            <p class="me-2 f-subtitle">평점 {{ sumReviews / totalReviews }}</p>
          </div>
          <p @click="closeStatus" class="me-2 text-center part-cat f-normal">닫기</p>
        </div>
        <div class="p-3 sec-profile">
          <div v-for="review in reviews" :key="review.id" class="d-flex flex-column">
            <div class="d-flex mb-1">
              <p class="mb-0 me-2 f-normal">{{ review.nickname }}</p>
              <p class="mb-0 me-2 f-normal">{{ review.startScore }}점</p>
            </div>
            <div>
              <p class="mb-0 f-subtitle">{{ review.content }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import CounselorSchedule from '@/components/listener/CounselorSchedule.vue'

import axios from 'axios'
import SERVER from "@/api/index.js"

export default {
  name: 'CounselorDetail',

  components: {
    CounselorSchedule
  },

  props: {
    coEmail: {
      type: String,
    }
  },

  data: function () {
    return {
      active: false,
      conEmail: '',

      totalReviews: 1,
      sumReviews: 0,
      reviews: '',
    }
  },

  methods: {
    changeStatus: function () {
      this.active = !this.active
    },
    closeStatus: function () {
      this.active = false
    },

    getImgUrl(con) {
      var images = SERVER.URL + `/counselor-api/user/image/${con.photo}`
      return images
    },

    setBookmark: function () {
      axios({
        method: 'post',
        url: SERVER.URL + SERVER.ROUTES.bookmarkCreation + `${this.coEmail}/`,
        headers: {
          'Content-Type': 'application/json',
          'access-token': `${this.$store.state.authToken}`
        },
        data: {
          "counselor": this.conEmail
        },
      })
        .then(res => {
          console.log(res)
        })
    },
    getReviews: function () {
      axios({
        method: 'get',
        url: SERVER.URL + SERVER.ROUTES.reviewsSelection + `${this.coEmail}/`,
        headers: {
          'Content-Type': 'application/json',
          'access-token': `${this.$store.state.authToken}`
        },
      })
        .then(res => {
          this.totalReviews = res.data.content.length
          console.log(this.totalReviews)

          this.reviews = res.data.content
          console.log(this.reviews)
          
          this.reviews.forEach(review => {
            this.sumReviews += review.startScore
          })
        })
        .catch(err => {
          console.log(err)
        })
    }
  },

  computed: {
    ...mapState([
      'counselorDetail',
    ]),
  },

  created () {
    this.getReviews()
  }
}
</script>
