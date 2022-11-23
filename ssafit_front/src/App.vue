<template>
  <div id="app">
    <header-nav></header-nav>
    <br />
    <br />
    <router-view :key="$route.fullPath"></router-view>
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
@font-face {
  font-family: "NanumBarunGothic";
  font-style: normal;
  font-weight: 400;
  src: url("//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWeb.eot");
  src: url("//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWeb.eot?#iefix")
      format("embedded-opentype"),
    url("//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWeb.woff")
      format("woff"),
    url("//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWeb.ttf")
      format("truetype");
}

#app {
  font-family: "NanumBarunGothic", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

img {
  border-radius: 50%;
}
</style>
