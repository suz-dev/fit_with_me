<template>
  <div>
    <b-navbar type="primary" variant="primary">
      <!-- 홈 링크 -->
      <router-link to="/"
        ><img
          src="../../assets/logo_Cooper Black.png"
          style="width: 100px"
          alt="" /></router-link
      ><!-- 로그인/로그아웃 -->
      <div>
        <div v-if="loginUser.userName">
          <img
            style="border-radius: 50%; width: 50px"
            :src="require(`@/assets/${loginUser.profile}.png`)"
          />
          <span>{{ loginUser.userName }}님 환영합니다!</span>
          <b-button @click="logout">로그아웃</b-button>
          <b-button :to="'/user/userInfo/' + loginUser.userId" variant="light"
            >마이페이지</b-button
          >
        </div>
        <div v-else>
          <b-button to="/user/login" variant="light">로그인</b-button>
        </div>
      </div>
    </b-navbar>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "HeaderNav",

  data() {
    return {
      userId: "",
    };
  },

  computed: {
    ...mapState(["loginUser"]),
  },

  methods: {
    logout() {
      this.$store.dispatch("logout");
    },
  },
};
</script>

<style></style>
