<template>
  <div id="introduction" class="card part-home-image">
    <img
      :src="require('@/assets/images/main-background.jpg')" alt="background-image"
      class="img-fluid" style="width: 100vw; height: 100vh;"
    >

    <div
      class="card-img-overlay mx-auto my-auto d-flex flex-column justify-content-center"
      style="width: 50vw; height: 50vh;"
    >
      <p class="mb-3 text-center text-white f-title-bold"> Listen to Me, 나의 이야기를 들어줄래?</p>
      <p class="mb-5 text-center text-white f-subtitle"> 더이상 혼자 고민하지 마세요</p>

      <div class="mb-5 d-flex justify-content-center">
        <button @click="moveDown" class="menu f-subtitle" style="background: #fff4e8">살펴보기</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Introductoin',

  methods: {
    moveDown: function () {
      this.$store.dispatch('moveDown')
    }
  },
}
</script>
