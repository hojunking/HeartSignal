<template>
  <Header />
  <!-- hello world 에다가 집어 넣으면 될 듯 함. -->
  <HelloWorld /> 
  <Footer />
</template>

<script>
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    Header,
    HelloWorld,
    Footer,
  }
}
</script>

