<template>
  <div>
    <hr />
    <!-- table로 리뷰 목록 보여주기 -->
    <h2>리뷰 리스트</h2>
    <b-container>
      <b-row v-for="review in reviews" :key="review.reviewId">
        <b-col
          ><img
            :src="require(`@/assets/${review.profile}.png`)"
            style="border-radius: 50%; width: 50px"
          />

          {{ review.userName }}
        </b-col>
        <b-col> {{ review.content }}</b-col>
        <b-col>
          {{ review.regDate }}
        </b-col>
        <b-col
          ><b-form-rating
            variant="warning"
            :value="review.star"
            readonly
          ></b-form-rating
        ></b-col>
        <b-col v-if="review.userName == loginUser.userName">
          <b-button v-b-modal.updateModal @click="updateModal(review)"
            >수정</b-button
          >
          <b-button @click="deleteReview(review)">삭제</b-button></b-col
        >
      </b-row>
    </b-container>

    <b-modal id="updateModal" @ok="updateReview(selected)" title="수정">
      <b-form>
        <b-form-group id="content" label="리뷰 내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="selected.content"
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
            v-model="selected.userName"
            readonly
            type="text"
          ></b-form-input>
        </b-form-group>

        <b-form-group inline id="star" label="별점 :" label-for="star">
          <b-form-rating
            variant="warning"
            v-model="selected.star"
          ></b-form-rating>
        </b-form-group>
      </b-form>
    </b-modal>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      selected: {},
    };
  },
  computed: {
    ...mapState(["reviews", "loginUser"]),
  },
  created() {
    const pathName = new URL(document.location).pathname.split("/");
    const id = pathName[pathName.length - 1];
    this.$store.dispatch("getReviews", id);
  },

  methods: {
    updateModal(review) {
      this.selected = review;
    },
    updateReview(review) {
      console.log(review);
      this.$store.dispatch("updateReview", review);
    },
    deleteReview(review) {
      this.$store.dispatch("deleteReview", {
        id: review.reviewId,
        videoId: review.videoId,
      });
    },
  },
};
</script>

<style scoped></style>
