<template>
  <div>
    <b-navbar type="primary" variant="primary">
      <!-- 홈 링크 -->
      <b-button to="/">FIT with ME</b-button> |
      <!-- 로그인/로그아웃 -->
      <!-- 로그인 전: 로그인 버튼 -->
      <!-- 로그인 후: 로그아웃 버튼 + UserView로 이동할 수 있는 뭐.. 마이페이지?..이런거  -->
      <div>
        <div v-if="loginUser.userName">
          <span>{{ loginUser.userName }}님 환영합니다!</span>
          <b-button @click="logout">로그아웃</b-button>
          <b-button to="/user/userInfo" variant="light">마이페이지</b-button>
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
