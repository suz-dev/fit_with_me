<template>
  <header class="d-flex justify-content-center">
    <div>
      <!-- 전체 유저 리스트 -->
      <h2>팔로우 추천</h2>
      <b-container>
        <b-row
          v-for="user in users.slice((currentPage - 1) * perPage)"
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
          <b-button variant="outline-danger" @click="addFollow(user.userId)"
            >팔로우 하기
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
    ...mapState(["users", "loginUser"]),
    rows() {
      return this.users.length;
    },
  },

  created() {
    this.$store.dispatch("getAllUser");
  },
};
</script>

<style></style>
