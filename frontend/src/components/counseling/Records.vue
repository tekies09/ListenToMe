<template>
  <div id="records" class="px-3 overflow-auto">
    <!-- header: title -->
    <header class="d-flex align-items-center py-3" style="height: 10vh;">
      <p class="mb-0 f-subtitle">기록</p>
    </header>

    <!-- body: 전체 기록 -->
    <div v-if="active" id="record-item" class="d-flex justify-content-center align-items-center mb-3 p-3 part-record">
      <p class="mb-0 py-2 f-normal">기록이 없습니다.</p>
    </div>

    <section v-for="(memo, index) in memos" :key=index>
      <record-item :memo="memo"></record-item>
    </section>
  </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/index.js'

import RecordItem from '@/components/counseling/RecordItem.vue'

export default {
  name: 'Records',

  components: {
    RecordItem
  },

  data: function () {
    return {
      memos: '',
      active: false,
    }
  },

  methods: {
    getMemos: function () {
      axios({
        method: 'get',
        url: SERVER.URL + SERVER.ROUTES.memosSelection + `${this.$store.state.usersession}/`,
        headers: {
          'Content-Type': 'application/json',
          'access-token': `${this.$store.state.authToken}`
        },
      })
        .then(res => {
          console.log(res.data)
          if (res.data.length == 0) {
            this.active = true
          } else {
            this.active = false
            this.memos = res.data
          }
          console.log(this.memos)
        })
        .catch(err => {
          console.log(err)
        })
    }
  }, 

  created () {
    this.getMemos()
  }
}
</script>
