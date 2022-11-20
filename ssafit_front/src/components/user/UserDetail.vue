<template>
  <!-- 유저 정보 -->
  <header class="d-flex justify-content-center">
    <div>
      <img
        style="border-radius: 50%; width: 90px"
        :src="require(`@/assets/${loginUser.profile}.png`)"
      />
    </div>
    <p>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </p>
    <section>
      <div class="d-inline-flex">
        <h2>
          {{ loginUser.userName }}
        </h2>
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
        <div>
          <!-- 등록 폼 수정 폼으로 바꾸기 -->
          <b-button to="/user/regist" variant="outline-success"
            >회원 정보 수정</b-button
          >
        </div>
      </div>
<<<<<<< HEAD
    </div>
    <user-like></user-like>
  </div>
=======
      <div class="userId">{{ loginUser.userId }}</div>
    </section>
  </header>
>>>>>>> d4069adc0a1a19355dbef1f673b731cb03d55e54
</template>

<script>
import { mapState } from "vuex";
import UserLike from "./UserLike.vue";

export default {
  components: {
    UserLike,
  },
  computed: {
    ...mapState(["loginUser"]),
  },
  created() {
    const pathName = new URL(document.location).pathname.split("/");
    const id = pathName[pathName.length - 1];
    console.log(id);
    this.$store.dispatch("getUser", id);
  },
};
</script>

<style>
.userId {
  text-align: left;
  font-size: 3vw;
}
</style>
