<template>
  <div>
    <h3>파트 별 영상</h3>
    <b-container>
      <b-form-select
        @change="searchPartVideos"
        v-model="selected"
        :options="options"
        size="sm"
        class="mt-3"
      ></b-form-select>
      <div class="mt-3">
        <strong>{{ selected }}</strong>
      </div>
    </b-container>

    <b-container v-if="videos">
      <b-card-group deck>
        <div
          v-for="video in videos.slice(
            (currentPage - 1) * perPage,
            currentPage * perPage
          )"
          :key="video.id.videoId"
        >
          <b-card
            :title="video.snippet.title"
            :img-src="video.snippet.thumbnails.high.url"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
            id="card"
          >
            <b-card-text>
              {{ video.snippet.channelTitle }}
              <!-- <b-badge>{{ video.viewCnt }}</b-badge> -->
            </b-card-text>

            <b-button variant="primary" :to="video.id.videoId"
              >영상 상세</b-button
            >
          </b-card>
        </div>
      </b-card-group>

      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="card-group"
      ></b-pagination>
    </b-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "VideoList",
  data() {
    return {
      perPage: 3,
      currentPage: 1,
      selected: "골프",
      options: [
        { value: "골프", text: "골프" },
        {
          value: "러닝",
          text: "러닝",
        },
        {
          value: "복싱",
          text: "복싱",
        },
        {
          value: "사이클",
          text: "사이클",
        },
        {
          value: "수영",
          text: "수영",
        },
        {
          value: "요가",
          text: "요가",
        },
        {
          value: "웨이트",
          text: "웨이트",
        },
        {
          value: "테니스",
          text: "테니스",
        },
        {
          value: "필라테스",
          text: "필라테스",
        },
      ],
    };
  },
  methods: {
    searchPartVideos() {
      this.$store.dispatch("searchPartVideos", this.selected);
    },
  },

  computed: {
    rows() {
      return this.videos.length;
    },
    ...mapState({ videos: "partVideos" }),
  },
  created() {
    this.$store.dispatch("searchPartVideos", this.selected);
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
