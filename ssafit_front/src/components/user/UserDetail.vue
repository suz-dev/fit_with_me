<template>
  <div>
    <!-- 유저 정보 -->
    <b-container>
      <img
        style="border-radius: 50%; width: 50px"
        :src="require(`@/assets/${loginUser.profile}.png`)"
      />

      <div>
        {{ loginUser.profile }}
        {{ loginUser.userId }}
        {{ loginUser.userName }}
      </div>
    </b-container>
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
