<template>
  <div>
    <!-- 리뷰 등록 폼 -->
    <h3>리뷰 등록</h3>
    <div class="text-center">
      <b-form inline>
        <b-form-group id="content" label="리뷰 내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="review.content"
            type="text"
            rows="3"
            cols="60"
            max-rows="6"
            required
          ></b-form-textarea>
        </b-form-group>

        <b-form-group inline id="userName" label="작성자 :" label-for="userId">
          <b-form-input
            inline
            id="userName"
            v-model="review.userName"
            readonly
            type="text"
          ></b-form-input>
        </b-form-group>

        <b-form-group inline id="star" label="별점 :" label-for="star">
          <b-form-rating v-model="review.star"></b-form-rating>
        </b-form-group>
        <button class="btn btn-primary" @click="createReview">등록</button>
      </b-form>
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
        userId: sessionStorage.getItem("userId"),
        userName: sessionStorage.getItem("userName"),
        profile: sessionStorage.getItem("profile"),
        star: 0,
      },
    };
  },
  computed: {
    ...mapState(["loginUser"]),
    ...mapState(["video"]),
  },
  methods: {
    createReview() {
      console.log(this.review);
      this.$store.dispatch("createReview", this.review);
    },
  },
};
</script>

<style></style>
