<template>
  <div>
    <!-- 유저 정보 -->
    <user-detail></user-detail>

    <br />
    <br />
    <!-- 팔로우 페이지-->

    <div class="d-flex justify-content-center">
      <b-form inline>
        <!-- follow 컴포넌트-->
        <div class="d-flex justify-content-center">
          <b-button
            :to="'/user/userInfo/' + user.userId + '/follower'"
            variant="outline-secondary"
            >follower</b-button
          >
          <div>&nbsp;&nbsp;</div>
          <b-button
            :to="'/user/userInfo/' + user.userId + '/following'"
            variant="outline-secondary"
            >following</b-button
          >
        </div>
      </b-form>
    </div>

    <br />

    <!-- 캘린더 / 찜 / 유저 추천 -->
    <div class="d-flex justify-content-center">
      <b-button variant="outline-secondary">Calendar</b-button>
      <div>&nbsp;&nbsp;</div>
      <b-button
        :to="'/user/userInfo/' + user.userId + '/like'"
        variant="outline-secondary"
        >like</b-button
      >
      <div>&nbsp;&nbsp;</div>
      <b-button
        :to="'/user/userInfo/' + user.userId + '/users'"
        variant="outline-secondary"
        >recommend</b-button
      >
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
