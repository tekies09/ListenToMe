<template>
  <div id="profile" class="col-10 d-flex flex-column align-items-center pt-5 overflow-auto">
    <div class="mb-4 px-4 py-3 part-profile">
      <!-- header -->
      <div class="d-flex align-items-center row">
        <!-- name -->
        <div class="col-md-3 d-flex">
          <h2 class="mb-0 me-5 mx-5 fw-bold">{{ name }}</h2>
        </div>
     
        <div class="col-md-9 d-flex row">
          <div class="col-sm-12 d-flex mt-2 mb-2">
            <div class="col-10">
              <input type="text" v-model="shortGreeting" class="form-control f-normal sec-profile rounded" required />
            </div>
            <p class="ms-4 mb-0 text-center part-cat f-normal co1-2" style="width: 3vw; background: #CFE7EB" @click="updateShortGreeting(shortGreeting)">수정</p>
          </div>
          <div class="col-sm-11 d-flex">
            <p class="f-subtitle mt-2 mb-0">주요 상담 분야</p>
            <div v-if="category.length === 0">
              <p class="mb-0 mt-2 mx-4 text-center part-cat f-normal">미정</p>
            </div>
            <div v-else class="d-flex justify-content-between">
              <div v-for="(c, idx) in category" :key="idx">
                <p class="mb-0 mt-2 mx-4 text-center part-cat f-normal" style="background: #FFC688;">{{ c.category.category }}</p>
              </div>
            </div>
            <p class="mb-0 mt-2 mx-4 text-center part-cat f-normal" style="width: 3vw; background: #CFE7EB" @click="isModalViewed = true">수정</p>
          </div>
          <category-modal v-if="isModalViewed" @close-modal="isModalViewed = false"/>
        </div>
      </div>
      
      <hr class="my-4">

      <div class="d-flex">
        <div class="col-4 px-2 d-flex flex-column justify-content-between">
          <!-- profile image -->
          <p class="mb-0 me-2 text-center part-cat f-normal" style="width: 3vw; background: #CFE7EB" @click="updatePhoto()">수정</p>
          <div class="mb-3 d-flex justify-content-center">
            <div v-if="photo">
              <!-- <img :src="photoURL" alt="profile-image" class="form-profile-img"> -->
              <img :src="getImgUrl(photo)" class="card-img-top" alt="counselor" style="width: 18vw;">
            </div>
            <div v-else>
              <img :src="require('@/assets/images/counselor.png')" class="card-img-top" alt="counselor" style="width: 14vw;">
            </div>
          </div>

          <div class="mb-3">
            <input @change="fileSelect" type="file" ref="photo" id="myFile" class="form-control f-normal">
          </div>

          <!-- <div class="mb-5 text-center">
            <img :src="require('@/assets/images/counselor.png')" class="card-img-top" alt="counselor" style="width: 14vw;">
            <input type="file" name="file" ref="photo">
          </div> -->

          <!-- history -->
          <div class="sec-profile">
            <p class="mb-0 p-3 f-normal">{{ degree }}</p>
          </div>
        </div>

        <div class="col-8 px-2 d-flex flex-column justify-content-between">
          <!-- content -->
          <div class="mb-5">
            <div class="d-flex">
              <p class="f-subtitle me-2">소개</p>
              <div>
                <p class="mb-0 mb-2 text-center part-cat f-normal" style="width: 3vw; background: #CFE7EB" @click="updateGreeting(greeting)">수정</p>
              </div>
            </div>
            <div class="sec-profile">
              <input type="text" v-model="greeting" class="form-control f-normal" required />
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
              <p @click="changeStatus" class="mb-2 text-center part-cat f-normal">더보기</p>
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
    </div>

    <div v-if="active" class="mb-5 px-4 py-3 part-profile">
      <!-- review -->
      <div class="">
        <div class="d-flex">
          <div v-if="sumReviews === 0">
            <p class="me-2 f-subtitle">평점 0</p>
          </div>
          <div v-else>
            <p class="me-2 f-subtitle">평점 {{ sumReviews / totalReviews }}</p>
          </div>
          <p @click="closeStatus" class="mb-0 text-center part-cat f-normal">닫기</p>
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
import axios from 'axios'
import SERVER from '@/api/index.js'
import CategoryModal from '@/components/profile/CategoryModal.vue'

export default {
  name: 'Profile',
  components: {
    CategoryModal
  },
  data: function () {
    return {
      active: false,
      name:'',
      greeting: '',
      shortGreeting: '',
      degree: '',

      totalReviews: 1,
      sumReviews: 0,
      reviews: '',

      photo: '',
      photoURL: '',
      category: [],
      isModalViewed: false,
    }
  },
  methods: {
    changeStatus: function () {
      this.active = !this.active
    },
    closeStatus: function () {
      this.active = false
    },

    fileSelect() {
      // console.log(e)
      console.log(this.$refs)
      // this.photo = e.target.files[0];
      this.photo = this.$refs.photo.files[0];
      console.log(this.photo);
      this.photoURL = URL.createObjectURL(this.photo);
      // this.credentials_signup.photo = file.name;
    },
    
    getCounselorData() {
      axios({
        method: 'get',
        url: SERVER.URL + `/counselor-api/user/${this.$store.state.userEmail}`,
      })
      .then((res) => {
        console.log(res.data)
        this.name = res.data.userInfo.name;
        this.greeting = res.data.userInfo.greeting;
        this.shortGreeting = res.data.userInfo.shortGreeting;
        this.degree = res.data.userInfo.degree;
        this.photo = res.data.userInfo.photo;
        this.category = res.data.category
        console.log(this.category)
      })
      .catch((err) => console.log(err));
    },

    // 인사말 수정
    updateShortGreeting() {
      axios({
        method: 'post',
        url: SERVER.URL + '/counselor-api/shortgreeting',
        headers: {
          'Content-Type': 'application/json;',
          'access-token': `${this.$store.state.authToken}`
        },
        data: {
          shortgreeting: this.shortGreeting
        }
      })
      .then(() => {
        console.log('변경완료')
      })
      .catch((err) => console.log(err));
    },
    getReviews: function () {
      axios({
        method: 'get',
        url: SERVER.URL + SERVER.ROUTES.reviewsSelection + `${this.$store.state.userEmail}/`,
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
    },
    // 소개 수정
    updateGreeting: function () {
      axios({
        method: 'post',
        url: SERVER.URL + '/counselor-api/greeting',
        headers: {
          'Content-Type': 'application/json;',
          'access-token': `${this.$store.state.authToken}`
        },
        data: {
          greeting: this.greeting
        }
      })
      .then(() => {
        console.log('변경완료')
      })
      .catch((err) => console.log(err));
    },

    // 사진 수정
    updatePhoto() {
      const formdata = new FormData();
      formdata.append('multipartFile', this.photo);
      axios({
        method: 'post',
        url: SERVER.URL + '/counselor-api/user/image',
        data: formdata,
        headers: {
          'access-token': `${this.$store.state.authToken}`,
          'Content-Type': 'multipart/form-data',
        }
      })
      .then(() => {
        console.log('변경완료')
        this.$router.push("/");
      })
      .catch((err) => console.log(err));
    },

    // 사진 조회
    getImgUrl(con) {
      var images = SERVER.URL + `/counselor-api/user/image/${con}`
      return images
    },
  },
  created() {
    this.getCounselorData()
    this.getReviews()
  }
}
</script>
