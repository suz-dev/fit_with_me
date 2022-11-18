<template>
  <div id="app">
    <header-nav></header-nav>
    <hr />
    <router-view></router-view>
    <!-- <img src="@/assets/bear.png" /> -->
  </div>
</template>

<script>
import HeaderNav from "./components/common/HeaderNav.vue";

export default {
  data() {
    return {
      src: "@/assets/bear.png",
    };
  },
  name: "App",
  components: { HeaderNav },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

img {
  border-radius: 50%;
}
</style>
