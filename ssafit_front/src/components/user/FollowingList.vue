<template>
  <header class="d-flex justify-content-center">
    <div>
      <!-- 팔로우 유저 정보 -->
      <h2>followings</h2>
      <b-container v-if="following">
        <b-row
          v-for="user in following.slice((currentPage - 1) * perPage)"
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
          <b-button variant="outline-danger" @click="unFllow(user.userId)"
            >팔로우 취소
          </b-button>
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
    ...mapState(["user", "following"]),
    rows() {
      return this.following.length;
    },
  },
  created() {
    this.$store.dispatch("getFollowing", this.user.userId);
  },

  methods: {
    // 언팔로우
    deleteReview(toUser) {
      this.$store.dispatch("unFollow", toUser);
    },
  },
};
</script>

<style></style>
