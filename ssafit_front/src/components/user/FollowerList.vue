<template>
  <header class="d-flex justify-content-center">
    <div>
      <!-- 팔로워 유저 정보 -->
      <h2>followers</h2>
      <b-container v-if="follower">
        <b-row
          v-for="user in follower.slice((currentPage - 1) * perPage)"
          :key="user.userId"
        >
          <b-col>
            <img
              :src="require(`@/assets/${user.profile}.png`)"
              style="border-radius: 50%; width: 50px"
            />
          </b-col>
          <!-- id 누르면 해당 유저 페이지로 -->
          <b-col> {{ user.userId }}</b-col>
          <b-col> {{ user.userName }}</b-col>

          <!-- 팔로우 안되어 있으면 'follow' / 되어 있으면 'followed'-->

          <b-button
            v-if="validFollow(user.userId)"
            variant="outline-danger"
            @click="addFollow(user.userId)"
            >팔로우 하기
          </b-button>
          <b-button
            v-else
            variant="outline-danger"
            @click="unFollow(user.userId)"
            >팔로우 취소</b-button
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
    ...mapState(["user", "follower", "following"]),
    rows() {
      return this.follower.length;
    },
  },
  created() {
    this.$store.dispatch("getFollower", this.user.userId);
    this.$store.dispatch("getFollowing", this.user.userId);
  },

  methods: {
    // 팔로우
    addFollow(fromUser) {
      this.$store.dispatch("addFollow", fromUser);
    },
    validFollow(userId) {
      for (var key in this.following) {
        if (this.following[key].userId == userId) {
          // 이미 내가 팔로우하고 있음 -> 팔로우 취소 가능
          return false;
        }
      }
      // 팔로우 가능
      return true;
    },
    unFollow(toUser) {
      this.$store.dispatch("unFollow", toUser);
    },
  },
};
</script>

<style></style>
