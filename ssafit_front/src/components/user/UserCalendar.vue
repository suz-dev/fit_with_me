<template>
  <v-calendar :attributes="attributes" />
</template>

<script>
import axios from "axios";

import { mapState } from "vuex";

const REST_API = `http://localhost:9999`;

export default {
  data() {
    // const date = new Date();
    // const year = date.getFullYear();
    // const month = date.getMonth();
    return {
      haveExercise: [],
    };
  },
  computed: {
    ...mapState(["loginUser"]),
    attributes() {
      return [
        // Attributes for todos
        {
          dates: this.haveExercise,
          dot: {
            color: "purple",
          },
        },
      ];
    },
  },

  created() {
    const API_URL = `${REST_API}/calendarapi/calendar/${this.loginUser.userId}`;
    axios({
      url: API_URL,
      method: "GET",
    }).then((res) => {
      const arr = res.data;
      this.haveExercise = [];
      arr.forEach((element) => {
        const date = element.date.split("-");
        console.log(date);
        this.haveExercise.push(new Date(date[0], date[1] - 1, date[2]));
      });
      console.log(this.haveExercise);
    });
  },
};
</script>

<style></style>
