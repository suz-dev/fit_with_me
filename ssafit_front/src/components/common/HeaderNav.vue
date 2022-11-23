<template>
  <div>
    <b-navbar class="navbar navbar-expand-sm" variant="dark">
      <!-- 홈 링크 -->

      <b-container>
        <div>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>

        <header>
          <a href="/"
            ><img src="../../assets/logo_test5.png" style="width: 150px" alt=""
          /></a>
        </header>

        <!-- 로그인/로그아웃 -->
        <main>
          <div v-if="loginUser.userName">
            <b-container>
              <div>
                <b-dropdown
                  no-caret
                  id="dropdown-right"
                  right
                  block
                  variant="none"
                  :text="loginUser.userName"
                >
                  <b-dropdown-item :to="'/user/userInfo/' + loginUser.userId"
                    >My Page</b-dropdown-item
                  >
                  <b-dropdown-item @click="logout"> 로그아웃 </b-dropdown-item>
                </b-dropdown>
              </div>
              <div>
                <img
                  style="border-radius: 50%; width: 60px"
                  :src="require(`@/assets/${loginUser.profile}.jpg`)"
                />
              </div>
            </b-container>
          </div>
          <div v-else>
            <b-button to="/user/login" variant="light">로그인</b-button>
          </div>
        </main>
      </b-container>
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
      if (location.href == "http://localhost:8080/") {
        this.$router.go();
      } else this.$router.push("/");
    },
  },
};
</script>

<style scoped>
main {
  float: right;
}
.navbar {
  background-image: url("../../assets/KakaoTalk_20221121_214834251.jpg");
}
</style>
