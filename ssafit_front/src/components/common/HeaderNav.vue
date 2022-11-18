<template>
  <div>
    <b-navbar type="primary" variant="primary">
      <!-- 홈 링크 -->
      <b-button to="/" variant="light">Home</b-button> |
      <!-- 로그인/로그아웃 -->
      <!-- 로그인 전: 로그인 버튼 -->
      <!-- 로그인 후: 로그아웃 버튼 + UserView로 이동할 수 있는 뭐.. 마이페이지?..이런거  -->
      <div>
        <div v-if="user">
          <span>{{ user }}님 환영합니다!</span>
          <b-button @click="logout">로그아웃</b-button>
        </div>
        <div v-else>
          <b-button to="/user/login" variant="light">로그인</b-button>
          <!-- 회원가입 -->
          <b-button to="/user/regist" variant="light">회원가입</b-button>
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
    ...mapState(["user"]),
  },

  methods: {
    logout() {
      this.$store.dispatch("logout");
    },
  },
};
</script>

<style></style>
