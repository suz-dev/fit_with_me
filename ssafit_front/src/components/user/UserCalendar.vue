<template>
  <div>
    <b-container>
      <b-col>
        <v-date-picker v-model="date" :attributes="attributes" />

        <b-button v-b-modal.addCalendar variant="outline-secondary"
          >추가</b-button
        >
      </b-col></b-container
    >

    <b-modal
      id="addCalendar"
      title="캘린더 추가"
      ok-variant="outline-primary"
      @ok="addCalendar"
    >
      <b-form>
        <b-form-group id="startTime" label="시작 시간 :" label-for="startTime">
          <v-date-picker v-model="startTime" mode="time" />
        </b-form-group>
        <b-form-group id="endTime" label="종료 시간 :" label-for="endTime">
          <v-date-picker v-model="endTime" mode="time" />
        </b-form-group>
        <b-form-group id="part" label="부위 :" label-for="part">
          <b-form-select
            id="part"
            v-model="part"
            :options="options"
            size="sm"
          ></b-form-select>
        </b-form-group>
        <b-form-group id="memo" label="메모 :" label-for="memo">
          <b-form-input v-model="memo" id="memo" type="text"></b-form-input>
        </b-form-group>
        <b-form-group id="videoUrl" label="영상 링크 :" label-for="videoUrl">
          <b-form-input
            id="videoUrl"
            v-model="videoUrl"
            type="text"
          ></b-form-input>
        </b-form-group>
      </b-form>
    </b-modal>
  </div>
</template>

<script>
import axios from "axios";

import { mapState } from "vuex";

const REST_API = `http://localhost:9999`;

export default {
  data() {
    return {
      haveExercise: [],
      date: new Date(),
      startTime: null,
      endTime: null,
      part: "기타",
      videoUrl: "",
      memo: "",
      options: [
        { value: "골프", text: "골프" },
        {
          value: "러닝",
          text: "러닝",
        },
        {
          value: "복싱",
          text: "복싱",
        },
        {
          value: "사이클",
          text: "사이클",
        },
        {
          value: "수영",
          text: "수영",
        },
        {
          value: "요가",
          text: "요가",
        },
        {
          value: "웨이트",
          text: "웨이트",
        },
        {
          value: "테니스",
          text: "테니스",
        },
        {
          value: "필라테스",
          text: "필라테스",
        },
        {
          value: "기타",
          text: "기타",
        },
      ],
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
            color: "blue",
          },
        },
        {
          key: "today",
          highlight: "gray",
          dates: new Date(),
        },
      ];
    },
  },
  methods: {
    addCalendar() {
      const API_URL = `${REST_API}/calendarapi/calendar`;
      axios
        .post(
          API_URL,
          {
            date:
              this.date.getFullYear() +
              "-" +
              (this.date.getMonth() + 1) +
              "-" +
              this.date.getDate(),
            startTime:
              this.startTime.getHours() +
              ":" +
              this.startTime.getMinutes() +
              ":00",
            endTime:
              this.endTime.getHours() + ":" + this.endTime.getMinutes() + ":00",
            part: "기타",
            videoUrl: this.videoUrl,
            memo: this.memo,
            userId: this.loginUser.userId,
          },
          {
            headers: {
              "Content-Type": "application/x-www-form-urlencoded",
            },
          }
        )
        .then(() => {
          location.reload();
        });
    },
  },
  watch: {
    date: function (value, oldValue) {
      oldValue;
      this.startTime = value;
      this.endTime = value;
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
