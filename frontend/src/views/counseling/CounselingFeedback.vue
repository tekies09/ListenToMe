<template>
  <div id="counseling-feedback" class="col-10 d-flex justify-content-center align-items-center" style="background: black;">
    <div class="d-flex flex-column justify-content-between p-4 part-feedback">
      <!-- header: title -->
      <header class="text-center mb-5">
        <p class="mb-0 f-title-bold">상담은 만족스러우셨나요?</p>
      </header>

      <!-- body: form -->
      <section class="mb-5">
        <!-- form: review -->
        <textarea
          v-model="review.content"
          placeholder="내용" class="form-control border-2 mb-4 form-content-fb f-normal"
        >
        </textarea>

        <!-- form: rating -->
        <div v-if="!this.score" class="d-flex justify-content-around">
          <img @click="score1" :src="require('@/assets/images/star_blank.png')" alt="blank_star" class="img-star">
          <img @click="score2" :src="require('@/assets/images/star_blank.png')" alt="blank_star" class="img-star">
          <img @click="score3" :src="require('@/assets/images/star_blank.png')" alt="blank_star" class="img-star">
          <img @click="score4" :src="require('@/assets/images/star_blank.png')" alt="blank_star" class="img-star">
          <img @click="score5" :src="require('@/assets/images/star_blank.png')" alt="blank_star" class="img-star">
        </div>
        <div v-else class="d-flex justify-content-around">
          <div v-for="n in score" :key="`score ${n}`">
            <img @click="score0" :src="require('@/assets/images/star.png')" alt="star" class="img-star">
          </div>
          <div v-for="m in theLeft" :key=m>
            <img @click="score0" :src="require('@/assets/images/star_blank.png')" alt="blank_star" class="img-star">
          </div>
        </div>
      </section>

      <!-- footer: 퇴장 button -->
      <footer class="d-flex justify-content-center">
        <button @click="moveToHome(), postReview()" class="btn-ok f-btn" style="background: #ED9C9C">제출</button>
      </footer>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/index.js'

export default {
  name: 'CounselingFeedback',

  data: function () {
    return {
      score: 0,
      theLeft: 0,

      review: {
        startScore: 0,
        content: '',
      },
    }
  },

  methods: {
    moveToHome: function () {
      this.$router.push({name: 'Home'})
    },

    score1: function () {
      this.score = 1
      this.review.startScore = this.score
      this.theLeft = 5 - this.score
    },
    score2: function () {
      this.score = 2
      this.review.startScore = this.score
      this.theLeft = 5 - this.score
    },
    score3: function () {
      this.score = 3
      this.review.startScore = this.score
      this.theLeft = 5 - this.score
    },
    score4: function () {
      this.score = 4
      this.review.startScore = this.score
      this.theLeft = 5 - this.score
    },
    score5: function () {
      this.score = 5
      this.review.startScore = this.score
      this.theLeft = 5 - this.score
    },
    score0: function () {
      this.score = 0
      this.review.startScore = this.score
      this.theLeft = 5 - this.score
    },

    postReview: function () {
      if (this.review.content && this.review.startScore) {
        axios({
          method: 'post',
          url: SERVER.URL + SERVER.ROUTES.reviewCreation + `${this.$store.state.usersession}/`,
          headers: {
            'Content-Type': 'application/json',
            'access-token': `${this.$store.state.authToken}`
          },
          data: this.review,
        })
          .then(res => {
            console.log(res)
            console.log(this.review)
          })
          .catch(err => {
            console.log(err)
            console.log(this.review)
            console.log(this.$store.state.usersession)
          })
      } else {
        console.log('no review')
      }
    },
  },
}
</script>
