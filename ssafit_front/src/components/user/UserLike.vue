<template>
  <div>
    <h3>찜 리스트</h3>

    <b-container v-if="likeVideos">
      <b-card-group deck>
        <div
          v-for="video in likeVideos.slice(
            (currentPage - 1) * perPage,
            currentPage * perPage
          )"
          :key="video.id"
        >
          <b-card
            :title="video.title"
            :img-src="
              'https://img.youtube.com/vi/' + video.id + '/mqdefault.jpg'
            "
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
            id="card"
          >
            <b-card-text>
              {{ video.channelName }}
              <!-- <b-badge>{{ video.viewCnt }}</b-badge> -->
            </b-card-text>

            <b-button variant="primary" :to="'/' + video.id"
              >영상 상세</b-button
            >
            <b-button variant="outline-danger" @click="deleteLike(video.id)"
              >찜 삭제
            </b-button>
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
  data() {
    return {
      perPage: 3,
      currentPage: 1,
    };
  },
  computed: {
    ...mapState(["user", "likeVideos"]),
    rows() {
      return this.likeVideos.length;
    },
  },

  created() {
    this.videos = this.$store.dispatch("getLikes", this.user.userId);
    console.log(this.videos);
  },
  methods: {
    deleteLike(videoId) {
      this.$store.dispatch("deleteLike", videoId);
    },
  },
};
</script>

<style></style>
