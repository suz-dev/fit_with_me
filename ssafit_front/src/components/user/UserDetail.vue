<template>
  <!-- 유저 정보 -->
  <header class="d-flex justify-content-center">
    <div class="d-inline-flex p-2 bd-highlight">
      <img
        style="border-radius: 50%; width: 120px"
        :src="require(`@/assets/${user.profile}.jpg`)"
      />
    </div>

    <div class="d-inline-flex">
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </div>

    <section>
      <div><br /></div>
      <div class="d-inline-flex">
        <div>
          <h2>
            {{ user.userName }}
          </h2>
        </div>

        <div class="d-inline-flex">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        <div>
          <!-- 등록 폼 수정 폼으로 바꾸기 -->

          <b-button
            v-if="loginUser.userId == user.userId"
            to="/user/regist"
            variant="none"
            ><b-icon icon="gear" variant="secondary"></b-icon
          ></b-button>

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
        </div>
      </div>

      <div class="userId">{{ user.userId }}</div>
    </section>
  </header>
</template>

<script>
import { mapState } from "vuex";

export default {
  computed: {
    ...mapState(["loginUser", "user", "loginFollowing"]),
  },
  created() {
    const pathName = new URL(document.location).pathname.split("/");
    console.log(pathName);
    const id = pathName[3];
    console.log(id);
    this.$store.dispatch("getUser", id);
  },
  methods: {
    addFollow(userId) {
      this.$store.dispatch("addFollow", userId);
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
    }, // 언팔로우
    unFollow(toUser) {
      this.$store.dispatch("unFollow", toUser);
    },
  },
};
</script>

<style>
.userId {
  text-align: left;
  font-size: 20px;
}
</style>
