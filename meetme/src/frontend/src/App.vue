<template>
  <Header />
  <!-- hello world 에다가 집어 넣으면 될 듯 함. -->
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

