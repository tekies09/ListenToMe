<template>
  <div id="app">
    <header>
      <!-- navigation bar -->
      <nav-bar/>

      <!-- introduction with background -->
      <introduction/>
    </header>

    <div :class="{ 'd-flex': $store.state.isSideBar }">
      <!-- left side bar -->
      <side-bar v-if="$store.state.isSideBar" class="col-2 area-side"/>

      <!-- routing area -->
      <router-view/>
    </div>
  </div>
</template>

<script>
import NavBar from '@/components/basics/NavBar.vue'
import SideBar from '@/components/basics/SideBar.vue'
import Introduction from '@/components/basics/Introduction.vue'

export default {
  name: 'App',
  
  components: {
    NavBar,
    SideBar,
    Introduction
  },

  methods: {
    checkScroll: function () {
      let location = document.documentElement.scrollTop
      let innerLocation = window.innerHeight
      let bottomLocation = document.documentElement.scrollHeight

      if (location <= 400) {
        this.$store.dispatch('offNavbar')
      } else if (location < bottomLocation - innerLocation) {
        this.$store.dispatch('onNavbar')
      } 
      else if (location >= bottomLocation - innerLocation) {
        document.body.classList.add('overflow-hidden')
        console.log('touch down')
      }
    },
  },

  created () {
    this.$store.dispatch('offNavbar')
    window.addEventListener('scroll', this.checkScroll)
  }
}
</script>
