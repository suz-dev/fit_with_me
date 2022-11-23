<template>
  <div>
    <br />
    <b-container v-if="videos">
      <b-list-group>
        <b-list-group-item
          class="border border-white"
          v-for="video in videos"
          :key="video.id.videoId"
        >
          <b-container fluid>
            <b-row align-v="center">
              <div>
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
              </div>
              <b-col class="txt_line">
                <b-col class="txt_line">
                  <span v-html="video.snippet.title"></span
                ></b-col>
                <b-col class="channel_title">
                  <span v-html="video.snippet.channelTitle"></span
                ></b-col>
                <b-col>
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
                      ><b-icon-suit-heart-fill
                        variant="danger"
                      ></b-icon-suit-heart-fill
                    ></b-button>
                  </span>
                </b-col>
              </b-col>
            </b-row>
          </b-container>
        </b-list-group-item>
      </b-list-group>
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
          return false;
        }
      }
      return true;
    },
    searchVideos() {
      this.$store.dispatch("searchVideos", this.search);
    },
    createLike(videoId) {
      this.$store.dispatch("createLike", videoId);
    },
    deleteLike(videoId) {
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

.txt_line {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.video_title {
  font-size: 18px;
}

.channel_title {
  color: gray;
  font-size: 13px;
}
</style>
