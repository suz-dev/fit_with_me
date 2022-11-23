<template>
  <div>
    <!-- iframe으로 재생할 수 있게 -->
    <b-container>
      <b-row class="justify-content-md-center">
        <div class="embed-responsive embed-responsive-16by9" style="width: 50%">
          <iframe
            class="embed-responsive-item"
            :src="'https://www.youtube-nocookie.com/embed/' + video.id"
            width="420"
            height="315"
            frameborder="0"
          ></iframe>
        </div>
      </b-row>
    </b-container>
    <br />
    <b-container>
      <b-col class="video_title">
        {{ video.title }}
      </b-col>
      <b-col class="channel_title">
        {{ video.channelName }}
      </b-col>
      <b-col>
        {{ video.viewCnt }}
      </b-col>
      <b-col>
        <span v-if="loginUser.userName">
          <!-- 찜 등록-->
          <b-button
            v-if="validLike(video.id)"
            variant="none"
            @click="createLike(video.id)"
            ><b-icon-suit-heart variant="danger"></b-icon-suit-heart
          ></b-button>
          <!-- 찜 삭제-->
          <b-button v-else variant="none" @click="deleteLike(video.id)"
            ><b-icon-suit-heart-fill variant="danger"></b-icon-suit-heart-fill
          ></b-button>
        </span>
      </b-col>
    </b-container>

    <!-- <video-item></video-item> -->
    <!-- 리뷰 목록 -->
    <b-container>
      <video-review-create></video-review-create>
      <router-view></router-view>
      <!-- 리뷰 등록 form -->
    </b-container>
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

<style>
.video_title {
  font-size: 18px;
}

.channel_title {
  color: gray;
  font-size: 13px;
}
</style>
