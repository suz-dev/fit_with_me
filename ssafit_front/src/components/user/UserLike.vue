<template>
  <div>
    <h3>찜 리스트</h3>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState(["user"]),
  },
};
</script>

<style></style>
