<template>
  <div>
    <!-- 리뷰 디테일+수정+삭제 -->
    <h2>리뷰 디테일</h2>
    <b-container>
      <b-form>
        <b-form-group id="title" label="리뷰 제목:" label-for="title">
          <b-form-input
            id="title"
            v-model="review.title"
            type="text"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group id="content" label="리뷰 내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="review.content"
            type="text"
            rows="3"
            max-rows="6"
            required
          ></b-form-textarea>
        </b-form-group>

        <b-form-group id="writer" label="작성자 :" label-for="writer">
          <b-form-textarea
            id="writer"
            v-model="review.userId"
            type="text"
            readonly
          ></b-form-textarea>
        </b-form-group>
        <b-form-group id="star" label="별점 :" label-for="star">
          <b-form-rating v-model="review.star"></b-form-rating>
          <p class="mt-2">Value: {{ review.star }}</p>
        </b-form-group>
        <b-button variant="primary" @click="updateReview">수정</b-button>
        <b-button variant="danger" @click="deleteReview">삭제</b-button>
      </b-form>
    </b-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState(["review"]),
  },

  created() {
    const pathName = new URL(document.location).pathname.split("/");
    const id = pathName[pathName.length - 1];
    console.log(id);

    this.$store.dispatch("getReview", id);
  },
  methods: {
    updateReview() {
      console.log(this.review);
      this.$store.dispatch("updateReview", this.review);
    },
    deleteReview() {
      this.$store.dispatch("deleteReview", {
        id: this.review.reviewId,
        videoId: this.review.videoId,
      });
    },
  },
};
</script>

<style></style>
