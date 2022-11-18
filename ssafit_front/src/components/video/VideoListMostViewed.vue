<template>
  <div>
    <h3>많이 본 영상</h3>
    <b-container>
      <b-card-group deck>
        <div v-for="video in videos.slice(0, 3)" :key="video.id">
          <b-card
            :title="video.title"
            :img-src="
              'https://img.youtube.com/vi/' + video.id + '/hqdefault.jpg'
            "
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
          >
            <b-card-text>
              {{ video.channelName }}
              <b-badge>{{ video.viewCnt }}</b-badge>
            </b-card-text>

            <b-button variant="primary" :to="video.id">영상 상세</b-button>
          </b-card>
        </div>
      </b-card-group>
    </b-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "VideoList",
  data() {
    return {};
  },
  computed: {
    ...mapState({ videos: "mostViewedVideos" }),
  },
  created() {
    this.$store.dispatch("getMostViewedVideos");
  },
};
</script>

<style></style>
