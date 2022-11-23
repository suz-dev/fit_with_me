<template>
  <div>
    <br />
    <!-- table로 리뷰 목록 보여주기 -->
    <b-container>
      <b-list-group>
        <b-list-group-item
          class="border border-white"
          fluid="sm"
          v-for="review in reviews"
          :key="review.reviewId"
        >
          <b-row>
            <b-col cols="2"
              ><img
                :src="require(`@/assets/${review.profile}.jpg`)"
                style="border-radius: 50%; width: 50px"
              />

              <!-- 이름 누르면 회원정보 페이지로 이동 -->
              <router-link
                v-if="loginUser.userId"
                :to="'/user/userInfo/' + review.userId"
                ><div>{{ review.userName }}</div></router-link
              >
              <span v-else>{{ review.userName }}</span>
            </b-col>
            <b-col cols="8" align="left">
              {{ review.content }}
            </b-col>
            <b-col>
              <b-form-rating
                inline
                variant="warning"
                :value="review.star"
                class="border border-white"
                readonly
              ></b-form-rating>
              <span class="reg_date">{{ review.regDate }} </span>
            </b-col>
          </b-row>

          <b-row>
            <b-col cols="2"></b-col>

            <b-col cols="8"></b-col>
            <b-col>
              <b-col v-if="review.userName == loginUser.userName">
                <b-button
                  variant="none"
                  v-b-modal.updateModal
                  @click="updateModal(review)"
                  ><b-icon icon="pencil-square" aria-hidden="true"></b-icon
                ></b-button>
                <b-button variant="none" @click="deleteReview(review)"
                  ><b-icon
                    icon="x-square"
                    aria-hidden="true"
                    variant="danger"
                  ></b-icon></b-button
              ></b-col>
            </b-col>
          </b-row>
        </b-list-group-item>
      </b-list-group>
    </b-container>

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

<style scoped>
.reg_date {
  font-size: 13px;
}
</style>
