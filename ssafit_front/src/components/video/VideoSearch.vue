<template>
  <div>
    <h3>영상 검색</h3>
    <b-container>
      <b-input-group class="mt-3">
        <b-form-input
          v-model="search"
          type="text"
          placeholder="검색어를 입력하세요."
        ></b-form-input>
        <b-input-group-append>
          <b-button variant="outline-primary" @click="searchVideos"
            >검색</b-button
          >
        </b-input-group-append>
      </b-input-group>
    </b-container>
    <hr />
    <b-container v-if="videos">
      <b-card-group deck id="card-group">
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
            <b-button variant="outline-danger" v-if="user"
              ><b-icon-suit-heart
                variant="danger"
                @click="createLike(video.id.videoId)"
              ></b-icon-suit-heart
            ></b-button>
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
      search: "",
      perPage: 3,
      currentPage: 1,
    };
  },
  computed: {
    ...mapState({ videos: "searchVideos" }),
    rows() {
      return this.videos.length;
    },
  },
  methods: {
    searchVideos() {
      console.log(this.search);
      this.$store.dispatch("searchVideos", this.search);
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
