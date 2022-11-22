<template>
  <div>
    <h3>영상 검색</h3>
    <b-container>
      <b-input-group class="mt-3" style="width: 50%">
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
    <br />
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
            :img-src="
              'https://img.youtube.com/vi/' +
              video.id.videoId +
              '/mqdefault.jpg'
            "
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
            id="card"
          >
            <b-card-text>
              {{ video.snippet.title }}
              <br />
              {{ video.snippet.channelTitle }}
              <!-- <b-badge>{{ video.viewCnt }}</b-badge> -->
            </b-card-text>

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
