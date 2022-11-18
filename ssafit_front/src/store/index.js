import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
import router from "@/router";

Vue.use(Vuex);

const REST_API = `http://localhost:9999`;

export default new Vuex.Store({
  state: {
    searchVideos: [],
    mostViewedVideos: [],
    partVideos: [],
    video: {},
    reviews: [],
    review: {},
    loginUser: {},
  },
  getters: {},
  mutations: {
    SEARCH_VIDEO(state, payload) {
      state.searchVideos = payload;
    },
    SEARCH_PART_VIDEOS(state, payload) {
      state.partVideos = payload;
    },
    GET_VIDEO(state, payload) {
      state.video = payload;
    },
    GET_REVIEWS(state, payload) {
      state.reviews = payload;
    },
    GET_REVIEW(state, payload) {
      state.review = payload;
    },
    UPDATE_REVIEW(state, payload) {
      console.log(payload);
      state.review.title = payload.title;
      state.review.content = payload.content;
      state.review.star = payload.star;
    },

    USER_LOGIN(state) {
      state.loginUser = {
        userName: sessionStorage.getItem("userName"),
        userId: sessionStorage.getItem("userId"),
      };
    },
    LOGOUT(state) {
      state.loginUser = "";
    },

    CREATE_REVIEW(state, payload) {
      state.reviews.push(payload);
    },
  },
  actions: {
    searchVideos({ commit }, search) {
      const API_URL = `https://www.googleapis.com/youtube/v3/search`;
      const YOUTUBE_KEY = process.env.VUE_APP_YOUTUBE_API_KEY;

      axios({
        url: API_URL,
        method: "GET",
        params: {
          key: YOUTUBE_KEY,
          part: "snippet",
          q: search,
          type: "video",
          maxResults: 10,
        },
      })
        .then((res) => {
          console.log(res.data.items);
          commit("SEARCH_VIDEO", res.data.items);
        })

        .catch((err) => {
          console.log(err);
        });
    },
    searchPartVideos({ commit }, part) {
      const API_URL = `https://www.googleapis.com/youtube/v3/search`;
      const YOUTUBE_KEY = process.env.VUE_APP_YOUTUBE_API_KEY;

      axios({
        url: API_URL,
        method: "GET",
        params: {
          key: YOUTUBE_KEY,
          part: "snippet",
          q: part,
          type: "video",
          maxResults: 10,
        },
      })
        .then((res) => {
          console.log(res.data.items);
          commit("SEARCH_PART_VIDEOS", res.data.items);
        })

        .catch((err) => {
          console.log(err);
        });
    },
    createVideo({ dispatch }, videoId) {
      const API_URL = `https://www.googleapis.com/youtube/v3/videos`;
      const YOUTUBE_KEY = process.env.VUE_APP_YOUTUBE_API_KEY;
      axios({
        url: API_URL,
        method: "GET",
        params: {
          key: YOUTUBE_KEY,
          part: "snippet, statistics",
          id: videoId,
        },
      })
        .then((res) => {
          console.log("---insert----");
          console.log(res.data.items);
          const AXIOS_URL = `${REST_API}/videoapi/video`;
          axios
            .post(
              AXIOS_URL,
              {
                channelName: res.data.items[0].snippet.channelTitle,
                id: res.data.items[0].id,
                title: res.data.items[0].snippet.title,
                viewCnt: res.data.items[0].statistics.viewCount,
                part: "",
              },
              {
                headers: {
                  "Content-Type": "application/x-www-form-urlencoded",
                },
              }
            )
            .then(() => {
              console.log("삽입 완료");
              dispatch("getVideo", videoId);
            })
            .catch((err) => {
              console.log(err);
              console.log(res.data.items[0].snippet.title);
            });
        })

        .catch((err) => {
          console.log("삽입 실패");
          console.log(err);
        });
    },
    getVideo({ commit, dispatch }, id) {
      const API_URL = `${REST_API}/videoapi/video/${id}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          console.log(res.data);
          console.log("db에 있음");
          commit("GET_VIDEO", res.data);
        })
        .catch((err) => {
          console.log("db에 없음");
          dispatch("createVideo", id);
          console.log(err);
        });
    },
    getReviews({ commit }, id) {
      const API_URL = `${REST_API}/videoapi/review`;
      axios({
        url: API_URL,
        method: "GET",
        params: {
          videoId: id,
        },
      })
        .then((res) => {
          commit("GET_REVIEWS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getReview({ commit }, id) {
      const API_URL = `${REST_API}/videoapi/review/${id}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          commit("GET_REVIEW", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    updateReview({ commit }, updateReview) {
      const API_URL = `${REST_API}/videoapi/review`;
      axios({
        url: API_URL,
        method: "PUT",
        params: {
          reviewId: updateReview.reviewId,
          title: updateReview.title,
          content: updateReview.content,
          viewCnt: updateReview.viewCnt,
          star: updateReview.star,
        },
      })
        .then(() => {
          commit("UPDATE_REVIEW", updateReview);
          alert("수정 완료");
        })
        .catch((err) => {
          console.log(updateReview);
          console.log(err);
        });
    },
    deleteReview({ commit }, data) {
      const API_URL = `${REST_API}/videoapi/review/${data.id}`;
      axios
        .delete(API_URL)
        .then(() => {
          commit;
          router.push(`/${data.videoId}`);
        })
        .catch((err) => {
          console.log(data.id);
          console.log(err);
        });
    },

    // 로그인
    login({ commit }, payload) {
      const API_URL = `${REST_API}/userapi/login`;

      axios({
        url: API_URL,
        method: "POST",
        params: {
          userId: payload.id,
          password: payload.password,
        },
      })
        .then((res) => {
          console.log(res);
          sessionStorage.setItem("access-token", res.data["access-token"]);
          sessionStorage.setItem("userId", payload.id);
          sessionStorage.setItem("userName", res.data.userName);
          alert("로그인 성공!");
          commit("USER_LOGIN");
          router.push("/");
          return;
        })
        .catch((err) => {
          console.log(payload);
          console.log(err);
        });
    },

    // 로그아웃
    logout({ commit }) {
      sessionStorage.removeItem("access-token");
      sessionStorage.removeItem("userId");
      commit("LOGOUT");
    },

    // 회원가입
    createUser({ commit }, user) {
      const API_URL = `${REST_API}/userapi/user`;
      axios({
        url: API_URL,
        method: "POST",
        params: user,
      })
        .then((res) => {
          console.log(res);
          alert("회원가입 성공!");
          // commit("USER_CREATE", user); 팔로우할때 다시 구현
          commit;
          router.push("/");
          return;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    createReview({ commit }, review) {
      const API_URL = `${REST_API}/videoapi/review`;
      axios({
        url: API_URL,
        method: "POST",
        params: {
          content: review.content,
          star: review.star,
          title: review.title,
          userId: review.userId,
          userSeq: review.userSeq,
          videoId: this.state.video.id,
          viewCnt: 0,
        },
      })
        .then(() => {
          commit("CREATE_REVIEW", review);
          location.reload();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    createLike({ commit }, videoId) {
      const API_URL = `${REST_API}/likeapi/like`;
      axios({
        url: API_URL,
        method: "POST",
        params: {
          userId: this.state.user,
          videoId: videoId,
        },
      })
        .then(() => {
          commit;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  modules: {},
});
