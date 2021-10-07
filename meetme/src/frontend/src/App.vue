<template>
  <!-- router를 통해 보여주는 곳 -->
  <router-link to="/hello">Home</router-link> || 
  <router-link to="/createCourse">createCourse</router-link> || 
  <router-link to="/chat">chat</router-link>
  <router-view />
</template>

<script>
export default {
  name: 'App',
  components: {
  }
}
</script>

