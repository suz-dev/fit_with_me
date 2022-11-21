<template>
  <div>
    <!-- 헤더 -->
    <!-- 검색 -->
    <!-- 조회수 기준 영상 -->
    <router-view></router-view>

    <!-- 파트별 영상-->
    <video-part-list></video-part-list>
  </div>
</template>

<script>
// import VideoListMostViewed from "@/components/video/VideoListMostViewed.vue";
import VideoPartList from "@/components/video/VideoPartList.vue";

export default {
  components: {
    VideoPartList,
    // VideoListMostViewed
  },
};
</script>

<style></style>
