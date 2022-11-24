<template>
  <div>
    <!-- 리뷰 등록 폼 -->
    <b-container>
      <b-list-group>
        <b-list-group-item class="border border-white">
          <b-row>
            <b-col cols="2">
              <img
                v-if="loginUser.userId"
                :src="require(`@/assets/${loginUser.profile}.jpg`)"
                style="border-radius: 50%; width: 50px"
              />
            </b-col>
            <b-col cols="8">
              <b-form-textarea
                id="input"
                v-model="review.content"
                type="text"
                rows="1"
                required
                placeholder="댓글을 입력하세요"
              ></b-form-textarea>
            </b-col>

            <b-col>
              <b-form-group id="star">
                <b-form-rating
                  variant="warning"
                  class="border border-white"
                  v-model="review.star"
                ></b-form-rating>
              </b-form-group>
              <b-button variant="none" @click="createReview"
                ><b-icon icon="plus-square" aria-hidden="true"></b-icon
              ></b-button>
            </b-col>
          </b-row>
        </b-list-group-item>
      </b-list-group>
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
      if (this.loginUser.userId == null) {
        alert("로그인이 필요합니다.");
        this.$router.push("/user/login");
        return;
      }

      this.$store.dispatch("createReview", this.review);
      this.$router.go();
    },
  },
};
</script>

<style scoped>
#input {
  border-top: none;
  border-left: none;
  border-right: none;
  border-bottom: 1px lightgrey solid;
  background-color: transparent;
  text-align: center;
  color: gray;
}
#input:focus {
  outline: none; /* 아웃라인 지우기 */
  color: gray;
}

#input::placeholder {
  color: gray;
  font-size: 15px;
}

/* #input::-webkit-input-placeholder {
  color: black(255, 255, 255);
} */
</style>
