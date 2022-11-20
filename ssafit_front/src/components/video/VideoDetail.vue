<template>
  <div>
    <h2>영상 상세</h2>

    <!-- iframe으로 재생할 수 있게 -->

    <iframe
      :src="'https://www.youtube.com/embed/' + video.id"
      width="420"
      height="315"
    ></iframe>
    <div>
      {{ video.title }}
      {{ video.channelName }}
      {{ video.viewCnt }}
      <b-button variant="outline-danger"
        ><b-icon-suit-heart
          variant="danger"
          @click="createLike(video.id)"
        ></b-icon-suit-heart
      ></b-button>
    </div>
    <!-- <video-item></video-item> -->
    <!-- 리뷰 목록 -->
    <b-container>
      <router-view></router-view>
      <!-- 리뷰 등록 form -->
      <video-review-create></video-review-create
    ></b-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
import VideoReviewCreate from "./VideoReviewCreate.vue";

export default {
  components: { VideoReviewCreate },
  computed: {
    ...mapState(["video"]),
  },
  created() {
    const pathName = new URL(document.location).pathname.split("/");
    const id = pathName[pathName.length - 1];
    console.log(id);
    this.$store.dispatch("getVideo", id);
    this.$store.dispatch("getReviews", id);
  },
  methods: {
    createLike(videoId) {
      this.$store.dispatch("createLike", videoId);
    },
  },
};
</script>

<style></style>
