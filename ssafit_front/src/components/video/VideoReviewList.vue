<template>
  <div>
    <!-- table로 리뷰 목록 보여주기 -->
    <h2>리뷰 리스트</h2>
    <b-table striped hover :items="reviews" :fields="fields">
      <template #cell(title)="data">
        <router-link :to="'review/' + data.item.reviewId">{{
          data.item.title
        }}</router-link>
      </template>
      <template #cell(star)="data">
        <b-form-rating v-model="data.item.star"></b-form-rating>
      </template>
    </b-table>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      fields: [
        {
          key: "content",
          label: "내용",
        },
        {
          key: "userName",
          label: "유저 이름",
        },
        {
          key: "regDate",
          label: "등록날짜",
        },
        {
          key: "star",
          label: "별점",
        },
      ],
    };
  },
  computed: {
    ...mapState(["reviews"]),
  },
  created() {
    const pathName = new URL(document.location).pathname.split("/");
    const id = pathName[pathName.length - 1];
    this.$store.dispatch("getReviews", id);
  },
};
</script>

<style></style>
