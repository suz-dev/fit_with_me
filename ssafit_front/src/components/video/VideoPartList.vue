<template>
  <div>
    <h3>부위 별 영상</h3>
    <div>
      <b-button variant="outline-primary" @click="getPartVideos('상체')"
        >상체</b-button
      >
      <b-button variant="outline-primary" @click="getPartVideos('하체')"
        >하체</b-button
      >
      <b-button variant="outline-primary" @click="getPartVideos('전신')"
        >전신</b-button
      >
      <b-button variant="outline-primary" @click="getPartVideos('복부')"
        >복부</b-button
      >
    </div>

    <b-container>
      <b-card-group deck class="text-center">
        <div v-for="video in videos" :key="video.id">
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
  methods: {
    getPartVideos(part) {
      this.$store.dispatch("getPartVideos", part);
    },
  },

  computed: {
    ...mapState({ videos: "partVideos" }),
  },
};
</script>

<style></style>
