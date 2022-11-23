<template>
  <div>
    <br />
    <b-container v-if="videos">
      <b-list-group>
        <b-list-group-item v-for="video in videos" :key="video.id.videoId">
          <img
            :src="
              'https://img.youtube.com/vi/' +
              video.id.videoId +
              '/mqdefault.jpg'
            "
            style="max-width: 20rem"
            class="mb-2"
            id="card"
          />

          {{ video.snippet.title }}
          <br />
          {{ video.snippet.channelTitle }}
          <!-- <b-badge>{{ video.viewCnt }}</b-badge> -->

          <b-button variant="none" :to="video.id.videoId"
            ><b-icon icon="play-btn-fill" variant="danger"></b-icon
          ></b-button>
          <span v-if="loginUser.userName">
            <!-- 찜 등록-->
            <b-button
              v-if="validLike(video.id.videoId)"
              variant="none"
              @click="createLike(video.id.videoId)"
              ><b-icon-suit-heart variant="danger"></b-icon-suit-heart
            ></b-button>
            <!-- 찜 삭제-->
            <b-button
              v-else
              variant="none"
              @click="deleteLike(video.id.videoId)"
              ><b-icon-suit-heart-fill variant="danger"></b-icon-suit-heart-fill
            ></b-button>
          </span>
        </b-list-group-item>
      </b-list-group>

      <!-- <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="card-group"
      ></b-pagination> -->
    </b-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "VideoList",

  data() {
    return {
      search: "",
      perPage: 3,
      currentPage: 1,
    };
  },

  computed: {
    ...mapState({ videos: "searchVideos" }),
    ...mapState(["loginUser", "likeVideos"]),
    rows() {
      return this.videos.length;
    },
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
    searchVideos() {
      console.log(this.search);
      this.$store.dispatch("searchVideos", this.search);
    },
    createLike(videoId) {
      this.$store.dispatch("createLike", videoId);
    },
    deleteLike(videoId) {
      confirm("삭제하시겠습니까?");
      this.$store.dispatch("deleteLike", videoId);
    },
  },
};
</script>

<style scoped>
#card {
  border-radius: 10%;
}

img {
  border-radius: 10%;
}
</style>
