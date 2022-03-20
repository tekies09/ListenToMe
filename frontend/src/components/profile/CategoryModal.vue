<template>
  <div id="categorymodal">
    <div class="background">
      <div class="window">
        <div class="popup">
          <div class="modal-header d-flex align-items-center justify-content-center pb-2">
            <p>카테고리</p>
          </div>
          <div class="modal-body row" >
            <div v-for="(category, idx) in allCategory" :key="idx" class="col-lg-4 col-sm-4 col-xs-6 mb-3">
              <button class="c-button text-center border-0 part-cat w-100 mb-1" @click="chooseCategory= idx+1">{{ category.category }}</button>
            </div>
          </div>
          <div class="modal-footer">
            <button class="modal-button px-3 py-1" style="background: rgba(86, 160, 255, 0.93);" @click="saveCategory()">저장</button>
            <button class="modal-button px-3 py-1" style="background: rgba(255, 94, 94, 0.92);" @click="$emit('close-modal')">닫기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/index.js'

export default {
  name: 'CategoryModal',
  data: function () {
    return {
      allCategory: [],
      chooseCategory: 0
    }
  },
  methods: {
    getCategory() {
      axios({
        method: 'get',
        url: SERVER.URL + '/category-api'
      })
      .then((res) => {
        this.allCategory = res.data
        console.log(this.allCategory)
      })
      .catch((err) => console.log(err));
    },
    
    saveCategory() {
      console.log(this.chooseCategory)
      axios({
        method: 'get',
        url: SERVER.URL + `/category-api/${this.chooseCategory}`,
        headers: {
          'access-token': `${this.$store.state.authToken}`
        },
      })
      .then(() => {
        console.log('카테고리 저장')
        this.$emit('close-modal')
        this.$router.push("/");
      })
      .catch((err) => console.log(err));
    }
  },
  created() {
    this.getCategory()
  }
}
</script>

<style>
.c-button:focus {
  background-color:rgb(179, 179, 179);  
}
</style>
