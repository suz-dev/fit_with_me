<template>
  <div class="d-inline-flex p-2">
    <div>
      <img
        style="border-radius: 50%; width: 100px"
        :src="require(`@/assets/${loginUser.profile}.png`)"
      />
      <p class="text-lg-start">
        {{ loginUser.userId }}
      </p>
    </div>

    <div>
      <div>
        {{ loginUser.userName }}
      </div>
      <div>
        <!-- 등록 폼 수정 폼으로 바꾸기 -->
        <b-button to="/user/regist" variant="outline-success"
          >회원정보 수정</b-button
        >
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
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

<style></style>
