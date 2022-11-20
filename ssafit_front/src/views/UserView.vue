<template>
  <div>
    <h2>마이페이지</h2>

    <!-- 유저 정보 -->
    <user-detail></user-detail>

    <!-- 팔로우 페이지-->

    <!-- 캘린더 / 찜 / 유저 추천 -->
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
