<template>
  <div id="bookmark" class="col-10 p-routing">
    <!-- title -->
    <div class="mb-5 d-flex justify-content-between align-items-center">
      <p class="mb-0 f-title">북마크된 리스너</p>
      <button @click="moveToMarkedCounselors()" class="mb-0 p-0 btn-more f-normal">more</button>
    </div>

    <!-- body -->
    <div class="d-flex justify-content-around">
      <div v-for="listener in bookmarkedCounselors" :key=listener.counselor.id>
        <!-- content -->
        <div @click="LoadCounselorProfile(listener.counselor)" class="p-2 card part-counselor">
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
</template>

<script>
import { mapActions } from 'vuex'
import axios from 'axios'
import SERVER from '@/api/index.js'

export default {
  name: 'Bookmark',

  data: function () {
    return {
      bookmarkedCounselors: '',
    }
  },

  methods: {
    ...mapActions([
      'LoadCounselorProfile',
    ]),

    moveToMarkedCounselors: function () {
      this.$router.push({name: 'MarkedCounselors'})
    },
    
    moveToProfile: function () {
      this.$router.push({name: 'Profile'})
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
        console.log(this.bookmarkedCounselors.slice(0, 5))
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
