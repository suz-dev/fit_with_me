<template>
  <div>
    <!-- 리뷰 등록 폼 -->
    <h3>리뷰 등록</h3>

    <b-container>
      <b-row>
        <b-form inline>
          <div>
            <img
              :src="require(`@/assets/${loginUser.profile}.jpg`)"
              style="border-radius: 50%; width: 50px"
            />
          </div>

          <div>
            <b-form-group id="content">
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
          </div>
          <div>
            <b-form-group id="star">
              <b-form-rating
                variant="warning"
                v-model="review.star"
              ></b-form-rating>
            </b-form-group>
            <b-button variant="outline-secondary" @click="createReview"
              >등록</b-button
            >
          </div>
        </b-form>
      </b-row>
    </b-container>
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
      if (this.loginUser.userId == null) {
        alert("로그인이 필요합니다.");
      }

      this.$store.dispatch("createReview", this.review);
      this.$router.go();
    },
  },
};
</script>

<style></style>
