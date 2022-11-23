<template>
  <header class="d-flex justify-content-center">
    <div>
      <!-- 전체 유저 리스트 -->
      <b-container>
        <b-row
          v-for="user in users.slice((currentPage - 1) * perPage)"
          :key="user.userId"
        >
          <b-col>
            <img
              :src="require(`@/assets/${user.profile}.jpg`)"
              style="border-radius: 50%; width: 50px"
            />
          </b-col>
          <!-- id 누르면 해당 유저 페이지로 -->
          <b-col> {{ user.userId }}</b-col>
          <b-col
            ><router-link :to="'/user/userInfo/' + user.userId">{{
              user.userName
            }}</router-link></b-col
          >

          <!-- 팔로우 안되어 있으면 'follow' / 되어 있으면 'followed'-->
          <span v-if="user.userId == loginUser.userId"></span>
          <b-button
            v-else-if="validFollow(user.userId)"
            variant="outline-danger"
            @click="addFollow(user.userId)"
            >Follow
          </b-button>
          <b-button
            v-else
            variant="outline-danger"
            @click="unFollow(user.userId)"
            >UnFollow</b-button
          >
        </b-row>
      </b-container>
    </div>
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

<style></style>
