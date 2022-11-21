<template>
  <div>
    <!-- 유저 정보 -->
    <user-detail></user-detail>

    <br />
    <!-- 팔로우 페이지-->
    <div class="d-flex justify-content-center">
      <b-form inline>
        <!-- follow 컴포넌트-->
        <div class="d-flex justify-content-center">
          <b-button :to="'/user/userInfo/follower/' + user.userId"
            >follower</b-button
          >
          <b-button :to="'/user/userInfo/following/' + user.userId"
            >following</b-button
          >
        </div>
      </b-form>
    </div>

    <!-- 캘린더 / 찜 / 유저 추천 -->
    <div class="d-flex justify-content-center">
      <b-button to="/user/userInfo/like">like</b-button>
      <b-button to="/user/userInfo/users">recommend</b-button>
    </div>

    <router-view></router-view>
  </div>
</template>

<script>
import { mapState } from "vuex";
import UserDetail from "@/components/user/UserDetail.vue";
export default {
  components: {
    UserDetail,
  },
  computed: {
    ...mapState(["user"]),
  },
};
</script>

<style></style>
