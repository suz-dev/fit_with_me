<template>
  <b-navbar class="navbar navbar-expand-sm" variant="dark">
    <!-- 홈 링크 -->

    <b-container fluid>
      <div class="item">
        <b-container>
          <header>
            <a href="/"
              ><img
                src="../../assets/logo_test5.png"
                style="width: 120px"
                alt=""
            /></a>
          </header>
          <p>&nbsp;&nbsp;</p>
          <div>
            <b-input-group class="mt-3">
              <b-form-input
                v-model="search"
                @keyup.enter="searchVideos"
                type="text"
                placeholder="Search"
              ></b-form-input>
            </b-input-group>
          </div>
        </b-container>
      </div>

      <!-- 로그인/로그아웃 -->
      <main class="item">
        <div v-if="loginUser.userName">
          <div>
            <b-dropdown no-caret id="dropdown-right" right block variant="none"
              ><template #button-content>
                <img
                  style="border-radius: 50%; width: 50px"
                  :src="require(`@/assets/${loginUser.profile}.jpg`)"
                />
              </template>
              <b-dropdown-item disabled>
                {{ loginUser.userName }}님
              </b-dropdown-item>
              <b-dropdown-item :to="'/user/userInfo/' + loginUser.userId"
                >My Page</b-dropdown-item
              >
              <b-dropdown-item @click="logout"> 로그아웃 </b-dropdown-item>
            </b-dropdown>
          </div>
        </div>
        <div v-else>
          <b-button to="/user/login" variant="light">로그인</b-button>
        </div>
      </main>
    </b-container>
  </b-navbar>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "HeaderNav",

  data() {
    return {
      search: "",
      userId: "",
    };
  },

  computed: {
    ...mapState(["loginUser"]),
    ...mapState({ videos: "searchVideos" }),
  },

  methods: {
    logout() {
      this.$store.dispatch("logout");
      if (location.href == "http://localhost:8080/") {
        this.$router.go();
      } else this.$router.push("/");
    },
    searchVideos() {
      console.log(this.search);
      this.$store.dispatch("searchVideos", this.search);
      this.$router.pusth("/search");
    },
  },
};
</script>

<style scoped>
main {
  float: right;
}
.navbar {
  background-image: url("../../assets/배경2.jpg");
}
.container {
  display: flex;
  justify-content: space-around;
  margin-left: 0;
  margin-right: 0;
  width: 100%;
}
.disabled {
  color: black;
}
</style>
