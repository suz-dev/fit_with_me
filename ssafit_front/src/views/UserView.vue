<template>
  <div>
    <!-- 유저 정보 -->
    <user-detail :key="$route.fullPath"></user-detail>

    <br />
    <!-- 팔로우 페이지-->
    <div class="d-flex justify-content-center">
      <b-form inline>
        <!-- follow 컴포넌트-->
        <div class="d-flex justify-content-center">
          <b-button @click="showFollowerModal" variant="outline-secondary"
            >follower</b-button
          >
          <div>&nbsp;&nbsp;</div>
          <b-button @click="showFollowingModal" variant="outline-secondary"
            >following</b-button
          >
          <div>&nbsp;&nbsp;</div>
          <b-button
            :to="'/user/userInfo/' + user.userId + '/users'"
            variant="outline-secondary"
            ><b-icon icon="person-lines-fill" variant="secondary"></b-icon
          ></b-button>
        </div>
      </b-form>
    </div>

    <br />

    <!-- 캘린더 / 찜 / 유저 추천 -->
    <div class="d-flex justify-content-center">
      <b-button
        variant="outline-secondary"
        :to="'/user/userInfo/' + user.userId"
        ><b-icon icon="calendar-check" variant="secondary"></b-icon
      ></b-button>

      <div>&nbsp;&nbsp;</div>
      <b-button
        :to="'/user/userInfo/' + user.userId + '/like'"
        variant="outline-secondary"
        ><b-icon icon="heart-fill" variant="secondary"></b-icon
      ></b-button>
      <div>&nbsp;&nbsp;</div>
    </div>
    <br />

    <router-view :key="user.userId"></router-view>

    <b-modal id="follower" title="follower" hide-footer>
      <b-container v-if="follower">
        <div v-for="user in follower" :key="user.userId">
          <b-row>
            <b-col cols="2">
              <img
                :src="require(`@/assets/${user.profile}.jpg`)"
                style="border-radius: 50%; width: 50px"
              />
            </b-col>
            <!-- name 누르면 해당 유저 페이지로 -->

            <b-col
              ><router-link :to="'/user/userInfo/' + user.userId">{{
                user.userName
              }}</router-link></b-col
            >

            <!-- 팔로우 안되어 있으면 'follow' / 되어 있으면 'followed'-->
            <span v-if="user.userId == loginUser.userId"></span>
            <b-button
              v-else-if="validFollow(user.userId)"
              variant="none"
              @click="addFollow(user.userId)"
              ><b-icon
                icon="person-plus"
                aria-hidden="true"
                variant="primary"
              ></b-icon>
            </b-button>
            <b-button v-else variant="none" @click="unFollow(user.userId)"
              ><b-icon
                icon="person-check"
                aria-hidden="true"
                variant="secondary"
              ></b-icon
            ></b-button>
          </b-row>
          <br />
        </div>
      </b-container>
    </b-modal>

    <b-modal id="following" title="following" hide-footer>
      <b-container v-if="following">
        <div v-for="user in following" :key="user.userId">
          <b-row>
            <b-col cols="2">
              <img
                :src="require(`@/assets/${user.profile}.jpg`)"
                style="border-radius: 50%; width: 50px"
              />
            </b-col>
            <!-- 이름 누르면 이동 -->
            <b-col
              ><router-link :to="'/user/userInfo/' + user.userId">{{
                user.userName
              }}</router-link></b-col
            >

            <span v-if="user.userId == loginUser.userId"></span>
            <b-button
              v-else-if="validFollow(user.userId)"
              variant="none"
              @click="addFollow(user.userId)"
              ><b-icon
                icon="person-plus"
                aria-hidden="true"
                variant="primary"
              ></b-icon>
            </b-button>
            <b-button v-else variant="none" @click="unFollow(user.userId)"
              ><b-icon
                icon="person-check"
                aria-hidden="true"
                variant="secondary"
              ></b-icon
            ></b-button>
          </b-row>
          <br />
        </div>
      </b-container>
    </b-modal>
  </div>
</template>

<script>
import { mapState } from "vuex";
import UserDetail from "@/components/user/UserDetail.vue";

export default {
  components: {
    UserDetail,
  },
  computed: {
    ...mapState([
      "loginUser",
      "user",
      "following",
      "loginFollowing",
      "follower",
    ]),
  },
  created() {
    this.$store.dispatch("getFollower", this.user.userId);
    this.$store.dispatch("getFollowing", this.user.userId);
  },

  methods: {
    showFollowerModal() {
      this.$store.dispatch("getFollower", this.user.userId);
      this.$bvModal.show("follower");
    },
    showFollowingModal() {
      this.$store.dispatch("getFollowing", this.user.userId);
      this.$bvModal.show("following");
    },
    addFollow(userId) {
      console.log(userId);
      this.$store.dispatch("addFollow", userId);
    },
    // 언팔로우
    unFollow(toUser) {
      this.$store.dispatch("unFollow", toUser);
    },
    validFollow(userId) {
      for (var key in this.loginFollowing) {
        if (this.loginFollowing[key].userId == userId) {
          // 이미 내가 팔로우하고 있음 -> 팔로우 취소 가능
          return false;
        }
      }
      // 팔로우 가능
      return true;
    },
  },
};
</script>

<style></style>
