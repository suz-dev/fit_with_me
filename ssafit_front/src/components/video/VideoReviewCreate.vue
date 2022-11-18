<template>
  <div>
    <!-- 리뷰 등록 폼 -->
    <h3>리뷰 등록</h3>
    <div class="text-center">
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

      <b-form-group id="userId" label="작성자 :" label-for="userId">
        <b-form-textarea
          id="userId"
          v-model="review.userId"
          type="text"
        ></b-form-textarea>
      </b-form-group>
      <!-- 임시 -->
      <b-form-group id="userSeq" label="작성자번호 :" label-for="userSeq">
        <b-form-textarea
          id="userSeq"
          v-model="review.userSeq"
          type="text"
        ></b-form-textarea>
      </b-form-group>
      <b-form-group id="star" label="별점 :" label-for="star">
        <b-form-rating v-model="review.star"></b-form-rating>
        <p class="mt-2">Value: {{ review.star }}</p>
      </b-form-group>
      <button class="btn btn-primary" @click="createReview">등록</button>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      review: {
        title: "",
        content: "",
        userId: this.user,
        star: 0,
      },
    };
  },
  computed: {
    ...mapState(["user"]),
    ...mapState(["video"]),
  },
  methods: {
    createReview() {
      this.$store.dispatch("createReview", this.review);
    },
  },
};
</script>

<style></style>
