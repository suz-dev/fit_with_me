<template>
  <div>
    <br />
    <b-container class="container">
      <b-row>
        <b-col cols="4">
          <b-row>
            <v-date-picker v-model="date" :attributes="attributes" />
          </b-row>

          <b-row>
            <b-col></b-col>
            <b-col cols="4">
              <b-button
                v-if="loginUser.userId == user.userId"
                v-b-modal.addCalendar
                variant="none"
                ><b-icon
                  icon="plus-square
"
                  variant="secondary"
                ></b-icon
              ></b-button>
            </b-col>
          </b-row>
        </b-col>
        <b-col>
          <b-table borderless :items="selectedDateItems" :fields="fields">
            <template #cell(part)="data">
              <img
                :src="require(`@/assets/exercise/${data.item.part}.png`)"
                style="width: 40px"
              />
            </template>
            <template #cell(startTime)="data">
              {{ data.item.startTime.slice(0, 5) }}
            </template>
            <template #cell(endTime)="data">
              {{ data.item.endTime.slice(0, 5) }}
            </template>
            <template #cell(videoUrl)="data">
              <b-button
                v-if="data.item.videoUrl"
                variant="none"
                @click="moveVideo(data.item.videoUrl)"
                ><b-icon icon="play-btn-fill" variant="danger"></b-icon
              ></b-button>
            </template>
            <template #cell(memo)="data">
              <b-button
                v-if="loginUser.userId == user.userId"
                @click="showModal(data.item)"
                variant="none"
                ><b-icon icon="sticky" variant="secondary"></b-icon
              ></b-button>
            </template>
          </b-table>
        </b-col>
      </b-row>
    </b-container>

    <b-modal
      id="addCalendar"
      title="캘린더 추가"
      ok-variant="outline-primary"
      @ok="addCalendar"
    >
      <b-form>
        <b-form-group id="startTime" label="start" label-for="startTime">
          <v-date-picker v-model="startTime" mode="time" />
        </b-form-group>
        <b-form-group id="endTime" label="end" label-for="endTime">
          <v-date-picker v-model="endTime" mode="time" />
        </b-form-group>
        <b-form-group id="part">
          <b-form-select
            id="part"
            v-model="part"
            :options="options"
            size="sm"
          ></b-form-select>
        </b-form-group>
        <b-form-group id="memo">
          <b-form-input
            placeholder="memo"
            v-model="memo"
            id="memo"
            type="text"
          ></b-form-input>
        </b-form-group>
        <b-form-group id="videoUrl" label="영상 링크 :" label-for="videoUrl">
          <b-form-input
            placeholder="URL"
            id="videoUrl"
            v-model="videoUrl"
            type="text"
          ></b-form-input>
        </b-form-group>
      </b-form>
    </b-modal>

    <b-modal
      hide-footer
      id="updateCalendar"
      title="캘린더 상세"
      ok-variant="outline-primary"
    >
      <b-form>
        <b-form-group id="startTime" label="start" label-for="startTime">
          <v-date-picker v-model="detail.startTime" mode="time"></v-date-picker>
        </b-form-group>

        <b-form-group id="endTime" label="end" label-for="endTime">
          <v-date-picker v-model="detail.endTime" mode="time" />
        </b-form-group>
        <b-form-group id="part">
          <b-form-select
            id="part"
            v-model="detail.part"
            :options="options"
            size="sm"
          ></b-form-select>
        </b-form-group>
        <b-form-group id="memo">
          <b-form-input
            v-model="detail.memo"
            id="memo"
            placeholder="memo"
            type="text"
          ></b-form-input>
        </b-form-group>
        <b-form-group id="videoUrl">
          <b-form-input
            id="videoUrl"
            v-model="detail.videoUrl"
            placeholder="URL"
            type="text"
          ></b-form-input>
        </b-form-group>
        <b-row>
          <b-col></b-col>
          <b-col cols="3">
            <b-button
              variant="none"
              v-if="loginUser.userId == user.userId"
              @click="updateCalendar"
              ><b-icon icon="pencil-square" aria-hidden="true"></b-icon
            ></b-button>
            <b-button
              variant="none"
              v-if="loginUser.userId == user.userId"
              @click="deleteCalendar"
              ><b-icon
                icon="x-square"
                aria-hidden="true"
                variant="danger"
              ></b-icon
            ></b-button>
          </b-col>
        </b-row>
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
      detail: {
        date: null,
        startTime: null,
        endTime: null,
        part: "",
        videoUrl: "",
        memo: "",
        calendarId: null,
      },
      perPage: 3,
      currentPage: 1,
      haveExercise: [],
      date: new Date(JSON.parse(localStorage.getItem("date"))),
      startTime: new Date(),
      endTime: new Date(),
      part: null,
      videoUrl: "",
      memo: "",
      options: [
        { value: null, text: "Please select part", disabled: true },
        { value: "golf", text: "골프" },
        {
          value: "running",
          text: "러닝",
        },
        {
          value: "boxing",
          text: "복싱",
        },
        {
          value: "cycle",
          text: "사이클",
        },
        {
          value: "swim",
          text: "수영",
        },
        {
          value: "yoga",
          text: "요가",
        },
        {
          value: "weight",
          text: "웨이트",
        },
        {
          value: "tennis",
          text: "테니스",
        },
        {
          value: "pilates",
          text: "필라테스",
        },
        {
          value: "etc",
          text: "기타",
        },
      ],
      fields: [
        {
          key: "part",
          label: "part",
        },
        {
          key: "startTime",
          label: "start",
        },
        {
          key: "endTime",
          label: "end",
        },
        {
          key: "videoUrl",
          label: "URL",
        },
        {
          key: "memo",
          label: "",
        },
      ],
      selectedDateItems: [],
    };
  },
  computed: {
    ...mapState(["loginUser", "user"]),
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
    moveVideo(videoUrl) {
      if (videoUrl.includes("=")) {
        this.$router.push("/" + videoUrl.split("=")[1]);
      } else {
        alert("잘못된 링크 형식입니다.");
      }
    },
    updateCalendar() {
      const API_URL = `${REST_API}/calendarapi/calendar`;
      axios({
        url: API_URL,
        method: "PUT",
        params: {
          startTime:
            this.detail.startTime.getHours() +
            ":" +
            this.detail.startTime.getMinutes() +
            ":00",
          endTime:
            this.detail.endTime.getHours() +
            ":" +
            this.detail.endTime.getMinutes() +
            ":00",
          part: this.detail.part,
          videoUrl: this.detail.videoUrl,
          memo: this.detail.memo,
          calendarId: this.detail.calendarId,
        },
      }).then(() => {
        this.$router.go();
      });
    },

    showModal(data) {
      this.detail.date = data.date;
      this.detail.startTime = new Date(data.date + " " + data.startTime);
      this.detail.endTime = new Date(data.date + " " + data.endTime);
      this.detail.part = data.part;
      this.detail.videoUrl = data.videoUrl;
      this.detail.memo = data.memo;
      this.detail.calendarId = data.calendarId;
      this.$bvModal.show("updateCalendar");
    },

    addCalendar() {
      if (this.part == null) {
        alert("운동 종류를 선택하세요.");
        return;
      }
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
            part: this.part,
            videoUrl: this.videoUrl,
            memo: this.memo,
            userId: this.user.userId,
          },
          {
            headers: {
              "Content-Type": "application/x-www-form-urlencoded",
            },
          }
        )
        .then(() => {
          this.$router.go();
        });
    },
    deleteCalendar() {
      const API_URL = `${REST_API}/calendarapi/calendar/${this.detail.calendarId}`;
      axios({
        url: API_URL,
        method: "DELETE",
      })
        .then(() => {
          alert("삭제 완료");
          this.$router.go();
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  watch: {
    date: function (value, oldValue) {
      oldValue;
      localStorage.setItem(
        "date",
        JSON.stringify(
          value.getFullYear() +
            "-" +
            (value.getMonth() + 1) +
            "-" +
            value.getDate()
        )
      );
      this.startTime = value;
      this.endTime = value;
      // date에 있는 리스트 가져오기
      const API_URL = `${REST_API}/calendarapi/calendar`;
      axios({
        url: API_URL,
        method: "GET",
        params: {
          date:
            value.getFullYear() +
            "-" +
            (value.getMonth() + 1) +
            "-" +
            value.getDate(),
          userId: this.user.userId,
        },
      }).then((res) => {
        this.selectedDateItems = res.data;
      });
    },
  },

  created() {
    if (localStorage.getItem("date") == null) {
      localStorage.setItem(
        "date",
        JSON.stringify(
          new Date().getFullYear() +
            "-" +
            (new Date().getMonth() + 1) +
            "-" +
            new Date().getDate()
        )
      );
      this.date = new Date();
    }

    const API_URL = `${REST_API}/calendarapi/calendar/${this.user.userId}`;
    axios({
      url: API_URL,
      method: "GET",
    }).then((res) => {
      const arr = res.data;
      this.haveExercise = [];
      arr.forEach((element) => {
        const date = element.date.split("-");
        this.haveExercise.push(new Date(date[0], date[1] - 1, date[2]));
      });
    });

    const API_URL2 = `${REST_API}/calendarapi/calendar`;
    axios({
      url: API_URL2,
      method: "GET",
      params: {
        date:
          this.date.getFullYear() +
          "-" +
          (this.date.getMonth() + 1) +
          "-" +
          this.date.getDate(),
        userId: this.user.userId,
      },
    }).then((res) => {
      this.selectedDateItems = res.data;
    });
  },
};
</script>

<style scoped>
.container {
  width: 850px;
}
</style>
