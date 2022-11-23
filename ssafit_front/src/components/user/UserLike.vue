<template>
  <div>
    <b-container v-if="likeVideos">
      <b-card-group deck>
        <div v-for="video in likeVideos" :key="video.id">
          <b-card
            :img-src="
              'https://img.youtube.com/vi/' + video.id + '/mqdefault.jpg'
            "
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem; margin: 20px"
            class="border border-white"
            id="card"
          >
            <b-card-text class="txt_line">
              <span class="video_title">
                <span v-html="video.snippet.title"></span>
              </span>
              <br />
              <div class="channel_title">
                <span v-html="video.snippet.channelTitle"></span>
              </div>
              <b-button variant="none" :to="video.id"
                ><b-icon icon="play-btn-fill" variant="danger"></b-icon
              ></b-button>
              <span>
                <b-button
                  v-if="loginUser.userName"
                  variant="none"
                  @click="deleteLike(video.id)"
                  ><b-icon-suit-heart-fill
                    variant="danger"
                  ></b-icon-suit-heart-fill
                ></b-button>
              </span>
            </b-card-text>
          </b-card>
        </div>
      </b-card-group>
    </b-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState(["user", "likeVideos", "loginUser"]),
  },

  created() {
    this.videos = this.$store.dispatch("getLikes", this.user.userId);
  },
  methods: {
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
  padding: 0 5px;
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
