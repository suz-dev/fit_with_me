<template>
  <div>
    <b-navbar class="navbar navbar-expand-sm" variant="none">
      <!-- 홈 링크 -->

      <div class="container-fluid">
        <div>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>

        <header>
          <router-link to="/"
            ><img src="../../assets/logo_gra.png" style="width: 100px" alt=""
          /></router-link>
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
                  style="border-radius: 50%; width: 50px"
                  :src="require(`@/assets/${loginUser.profile}.png`)"
                />
              </div>
            </b-container>
          </div>
          <div v-else>
            <b-button to="/user/login" variant="light">로그인</b-button>
          </div>
        </main>
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

<style scoped>
main {
  float: right;
}
</style>
