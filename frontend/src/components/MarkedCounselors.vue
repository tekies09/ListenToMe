<template>
  <div id="marked-counselors" class="col-10 p-routing overflow-auto">
    <!-- title -->
    <div class="mb-5 d-flex justify-content-between align-items-center">
      <p class="mb-0 f-title">전체 북마크된 리스너</p>
      <button @click="moveToBookmark()" class="mb-0 p-0 btn-more f-normal">back</button>
    </div>

    <!-- body -->
    <div class="row row-cols-5 g-5 mb-5">
      <div 
        v-for="listener in bookmarkedCounselors" :key="listener.counselor.id" 
        @click="LoadCounselorProfile(listener.counselor)"
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
                :src="getImgUrl(listener.counselor)" alt="counselor"
                class="card-img-top" style="width: 10vw;"
              >
            </div>

            <div class="card-body d-flex justify-content-center">
              <!-- 이름 -->
              <p class="mb-0 f-normal">{{ listener.counselor.name }}</p>
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
import { mapActions } from 'vuex'

export default {
  name: 'MarkedCounselors',

  data: function () {
    return {
      bookmarkedCounselors: '',
    }
  },

  methods: {
    ...mapActions([
      'LoadCounselorProfile',
    ]),

    moveToBookmark: function () {
      this.$router.push({name: 'Bookmark'})
    },
    moveToProfile: function () {
      this.$router.push({name: 'Profile'})
        .catch(() => {})
    },

    getBookmarkedCounselors() {
      axios({
        methods: 'get',
        url: SERVER.URL + SERVER.ROUTES.bookmarkSelection,
        headers: {
          'Content-Type': 'application/json',
          'access-token': `${this.$store.state.authToken}`
        },
      })
      .then((res) => {
        this.bookmarkedCounselors = res.data.filter(item => {
          if ( item.client.email == this.$store.state.userEmail ) {
            return true
          }
        })
        console.log(this.bookmarkedCounselors)
      })
    },
    getImgUrl(con) {
      var images = SERVER.URL + `/counselor-api/user/image/${con.photo}`
      return images
    }
  },

  created () {
    this.getBookmarkedCounselors()
  }
}
</script>
