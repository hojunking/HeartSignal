<template>
  <Header />
  <!-- router를 통해 보여주는 곳 -->
  <router-view></router-view>
  <Footer />
</template>

<script>
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'

export default {
  name: 'App',
  components: {
    Header,
    Footer,
  }
}
</script>

