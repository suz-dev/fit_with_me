<template>
  <div>
    <!-- 유저 정보 -->
    <user-detail></user-detail>

    <br />
    <!-- 팔로우 페이지-->
    <div class="d-flex justify-content-center">
      <b-form inline>
        <!-- follow 컴포넌트-->
        <div>follower</div>
        |
        <div>following</div>
      </b-form>
    </div>

    <!-- 캘린더 / 찜 / 유저 추천 -->
    <div class="d-flex justify-content-center">cal / like / users</div>
  </div>
</template>

<script>
import UserDetail from "@/components/user/UserDetail.vue";
export default {
  components: {
    UserDetail,
  },
};
</script>

<style></style>
