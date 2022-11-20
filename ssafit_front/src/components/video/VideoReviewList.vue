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
        <b-col v-if="review.userName == loginUser.userName"
          ><b-button>수정</b-button> <b-button>삭제</b-button></b-col
        >
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  computed: {
    ...mapState(["reviews", "loginUser"]),
  },
  created() {
    const pathName = new URL(document.location).pathname.split("/");
    const id = pathName[pathName.length - 1];
    this.$store.dispatch("getReviews", id);
  },
};
</script>

<style scoped></style>
