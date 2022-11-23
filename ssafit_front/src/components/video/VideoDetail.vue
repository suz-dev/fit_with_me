<template>
  <div>
    <h2>영상 상세</h2>

    <!-- iframe으로 재생할 수 있게 -->

    <iframe
      :src="'https://www.youtube-nocookie.com/embed/' + video.id"
      width="420"
      height="315"
    ></iframe>
    <div>
      {{ video.title }}
      {{ video.channelName }}
      {{ video.viewCnt }}
      <span v-if="loginUser.userName">
        <!-- 찜 등록-->
        <b-button
          v-if="validLike(video.id)"
          variant="outline-danger"
          @click="createLike(video.id)"
          ><b-icon-suit-heart variant="danger"></b-icon-suit-heart
        ></b-button>
        <!-- 찜 삭제-->
        <b-button v-else variant="outline-danger" @click="deleteLike(video.id)"
          ><b-icon-suit-heart-fill variant="danger"></b-icon-suit-heart-fill
        ></b-button>
      </span>
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
    ...mapState(["loginUser", "likeVideos"]),
  },
  created() {
    const pathName = new URL(document.location).pathname.split("/");
    const id = pathName[pathName.length - 1];
    console.log(id);
    this.$store.dispatch("getVideo", id);
    this.$store.dispatch("getReviews", id);
    this.$store.dispatch("getLikes", this.loginUser.userId);
  },
  methods: {
    validLike(videoId) {
      for (var key in this.likeVideos) {
        if (this.likeVideos[key].id == videoId) {
          console.log(videoId);
          return false;
        }
      }
      return true;
    },
    createLike(videoId) {
      this.$store.dispatch("createLike", videoId);
      return false;
    },
    deleteLike(videoId) {
      confirm("삭제하시겠습니까?");
      this.$store.dispatch("deleteLike", videoId);
    },
  },
};
</script>

<style></style>
