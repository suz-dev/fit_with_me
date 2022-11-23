<template>
  <div>
    <hr />
    <!-- table로 리뷰 목록 보여주기 -->
    <b-list-group>
      <b-list-group-item
        fluid="sm"
        v-for="review in reviews"
        :key="review.reviewId"
      >
        <b-row>
          <b-col
            ><img
              :src="require(`@/assets/${review.profile}.jpg`)"
              style="border-radius: 50%; width: 50px"
            />
            <!-- 이름 누르면 회원정보 페이지로 이동 -->
            <router-link
              v-if="loginUser.userId"
              :to="'/user/userInfo/' + review.userId"
              >{{ review.userName }}</router-link
            >
            <span v-else>{{ review.userName }}</span>
          </b-col>
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
              ><b-icon icon="x-square" aria-hidden="true"></b-icon
            ></b-button>
            <b-button variant="none" @click="deleteReview(review)"
              ><b-icon icon="x-square" aria-hidden="true"></b-icon></b-button
          ></b-col>
        </b-row>

        <b-row>
          <b-col>
            <div>
              {{ review.content }}
            </div>
          </b-col>
        </b-row>
      </b-list-group-item>
    </b-list-group>

    <b-modal id="updateModal" @ok="updateReview(selected)" title="수정">
      <b-form>
        <b-form-group id="content">
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

        <b-form-group inline id="userName">
          <b-form-input
            inline
            id="userName"
            v-model="selected.userName"
            readonly
            type="text"
          ></b-form-input>
        </b-form-group>

        <b-form-group inline id="star">
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
