<template>
  <header class="d-flex justify-content-center">
    <!-- 전체 유저 리스트 -->
    <b-container>
      <div
        v-for="user in users.slice((currentPage - 1) * perPage)"
        :key="user.userId"
      >
        <b-row>
          <b-col cols="2">
            <img
              :src="require(`@/assets/${user.profile}.jpg`)"
              style="border-radius: 50%; width: 50px"
            />
          </b-col>
          <!-- 이름 누르면 해당 유저 페이지로 -->

          <b-col cols="3"
            ><router-link :to="'/user/userInfo/' + user.userId">{{
              user.userName
            }}</router-link></b-col
          >
          <b-col></b-col>
          <!-- 팔로우 안되어 있으면 'follow' / 되어 있으면 'followed'-->
          <span v-if="user.userId == loginUser.userId"></span>
          <b-button
            v-else-if="validFollow(user.userId)"
            variant="none"
            @click="addFollow(user.userId)"
            ><b-icon
              icon="person-plus"
              aria-hidden="true"
              variant="primary"
            ></b-icon>
          </b-button>
          <b-button v-else variant="none" @click="unFollow(user.userId)"
            ><b-icon
              icon="person-check"
              aria-hidden="true"
              variant="secondary"
            ></b-icon
          ></b-button>
        </b-row>
        <br />
      </div>
    </b-container>
  </header>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      perPage: 3,
      currentPage: 1,
    };
  },
  computed: {
    ...mapState(["users", "loginUser", "following", "loginFollowing"]),
    rows() {
      return this.users.length;
    },
  },
  methods: {
    addFollow(userId) {
      this.$store.dispatch("addFollow", userId);
    },
    validFollow(userId) {
      for (var key in this.loginFollowing) {
        if (this.loginFollowing[key].userId == userId) {
          // 이미 내가 팔로우하고 있음 -> 팔로우 취소 가능
          return false;
        }
      }
      // 팔로우 가능
      return true;
    }, // 언팔로우
    unFollow(toUser) {
      this.$store.dispatch("unFollow", toUser);
    },
  },
  created() {
    this.$store.dispatch("getAllUser");
  },
};
</script>

<style scoped>
.container {
  width: 400px;
}
</style>
