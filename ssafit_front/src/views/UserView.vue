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
            ><b-icon icon="person-plus" variant="secondary"></b-icon
            >follower</b-button
          >
          <div>&nbsp;&nbsp;</div>
          <b-button @click="showFollowingModal" variant="outline-secondary"
            ><b-icon icon="person-check" variant="secondary"></b-icon
            >following</b-button
          >
          <div>&nbsp;&nbsp;</div>
          <b-button
            :to="'/user/userInfo/' + user.userId + '/users'"
            variant="outline-secondary"
            ><b-icon icon="person-plus" variant="secondary"></b-icon
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

    <router-view></router-view>

    <b-modal id="follower" title="follower" ok-variant="outline-primary">
      <b-container v-if="follower">
        <b-row v-for="user in follower" :key="user.userId">
          <b-col>
            <img
              :src="require(`@/assets/${user.profile}.jpg`)"
              style="border-radius: 50%; width: 50px"
            />
          </b-col>
          <!-- id 누르면 해당 유저 페이지로 -->
          <b-col> {{ user.userId }}</b-col>
          <b-col
            ><router-link :to="'/user/userInfo/' + user.userId">{{
              user.userName
            }}</router-link></b-col
          >

          <!-- 팔로우 안되어 있으면 'follow' / 되어 있으면 'followed'-->
          <span v-if="user.userId == loginUser.userId"></span>
          <b-button
            v-else-if="validFollow(user.userId)"
            variant="outline-danger"
            @click="addFollow(user.userId)"
            >Follow
          </b-button>
          <b-button
            v-else
            variant="outline-danger"
            @click="unFollow(user.userId)"
            >UnFollow</b-button
          >
        </b-row>
      </b-container>
    </b-modal>

    <b-modal id="following" title="following" ok-variant="outline-secondary">
      <b-container v-if="following">
        <b-row v-for="user in following" :key="user.userId">
          <b-col>
            <img
              :src="require(`@/assets/${user.profile}.jpg`)"
              style="border-radius: 50%; width: 50px"
            />
          </b-col>
          <!-- id 누르면 해당 유저 페이지로 -->
          <b-col> {{ user.userId }}</b-col>
          <b-col
            ><router-link :to="'/user/userInfo/' + user.userId">{{
              user.userName
            }}</router-link></b-col
          >

          <span v-if="user.userId == loginUser.userId"></span>
          <b-button
            v-else-if="validFollow(user.userId)"
            variant="outline-danger"
            @click="addFollow(user.userId)"
            >Follow
          </b-button>
          <b-button
            v-else
            variant="outline-danger"
            @click="unFollow(user.userId)"
            >UnFollow</b-button
          >
        </b-row>
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
    localStorage.setItem("date", JSON.stringify(new Date()));
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
