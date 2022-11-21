import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
import router from "@/router";

import createPersistedState from "vuex-persistedstate";

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
    user: {},
    users: [],
    likeVideos: [],
    following: [],
    loginFollowing: [],
    follower: [],
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
        profile: sessionStorage.getItem("profile"),
      };
      state.user = state.loginUser;
    },
    LOGOUT(state) {
      state.loginUser = {};
    },

    CREATE_REVIEW(state, payload) {
      state.reviews.push(payload);
    },
    GET_LIKES(state, payload) {
      state.likeVideos = payload;
    },
    GET_LOGIN_FOLLOWING(state, payload) {
      state.loginFollowing = payload;
    },

    GET_FOLLOWING(state, payload) {
      state.following = payload;
    },
    GET_FOLLOWER(state, payload) {
      state.follower = payload;
    },
    GET_USER(state, payload) {
      state.user = payload;
    },
    GET_USERS(state, payload) {
      state.users = payload;
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
          location.reload();
        })
        .catch((err) => {
          console.log(data.id);
          console.log(err);
        });
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

    // 회원정보 수정
    updateUser({ commit }, user) {
      const API_URL = `${REST_API}/userapi/user`;
      axios({
        url: API_URL,
        method: "PUT",
        params: user,
      })
        .then((res) => {
          console.log(user);
          console.log(res);
          alert("회원 정보 수정이 완료되었습니다");
          commit;
          location.reload();
          return;
        })
        .catch((err) => {
          console.log(user);
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
          sessionStorage.setItem("profile", res.data.profile);
          alert("로그인 성공!");
          commit("USER_LOGIN");
          router.push("/");
          return;
        })
        .catch((err) => {
          console.log(payload);
          console.log(err);
          alert("회원정보가 일치하지 않습니다.");
        });
    },
    // 로그아웃
    logout({ commit }) {
      sessionStorage.removeItem("access-token");
      sessionStorage.removeItem("userId");
      sessionStorage.removeItem("userName");
      commit("LOGOUT");
    },

    // 리뷰 작성
    createReview({ commit }, review) {
      const API_URL = `${REST_API}/videoapi/review`;
      axios
        .post(
          API_URL,
          {
            content: review.content,
            star: review.star,
            userId: review.userId,
            userName: review.userName,
            profile: review.profile,
            videoId: this.state.video.id,
            viewCnt: 0,
          },
          {
            headers: {
              "Content-Type": "application/x-www-form-urlencoded",
            },
          }
        )
        .then(() => {
          commit("CREATE_REVIEW", review);
          console.log(review);
          location.reload();
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 찜하기
    createLike({ commit, dispatch }, videoId) {
      const API_URL = `${REST_API}/likeapi/like`;
      axios({
        url: API_URL,
        method: "POST",
        params: {
          userId: this.state.loginUser.userId,
          videoId: videoId,
        },
      })
        .then(() => {
          console.log(videoId);
          alert("찜 완료");
          commit;
          dispatch("getLikes", this.state.loginUser.userId);
        })
        .catch((err) => {
          console.log(err);
          if (err.response.data.message.includes("Duplicate entry")) {
            // duplicate entry
            alert("이미 찜한 영상입니다.");
            return;
          } else if (err.response.data.message.includes("foreign key")) {
            console.log("멈춤?");
            dispatch("createVideo", videoId);
            setTimeout(() => {
              dispatch("createLike", videoId);
            }, 500);
            return;
          }
        });
    },

    // 찜 목록 가져오기
    getLikes({ commit }, userId) {
      const API_URL = `${REST_API}/likeapi/like/${userId}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          console.log(res.data);
          commit("GET_LIKES", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 찜 삭제
    deleteLike({ dispatch }, videoId) {
      const API_URL = `${REST_API}/likeapi/like`;
      axios({
        url: API_URL,
        method: "DELETE",
        params: {
          userId: this.state.loginUser.userId,
          videoId: videoId,
        },
      })
        .then(() => {
          alert("삭제 완료");
          dispatch("getLikes", this.state.loginUser.userId);
        })
        .catch((err) => {
          console.log(err);
        });
    }, // 팔로잉 리스트
    getLoginFollowing({ commit }, userId) {
      const API_URL = `${REST_API}/userapi/following/${userId}`;
      axios({
        url: API_URL,
        method: "GET",
      }).then((res) => {
        commit("GET_LOGIN_FOLLOWING", res.data);
      });
    },

    // 팔로잉 리스트
    getFollowing({ commit }, userId) {
      const API_URL = `${REST_API}/userapi/following/${userId}`;
      axios({
        url: API_URL,
        method: "GET",
      }).then((res) => {
        commit("GET_FOLLOWING", res.data);
      });
    },

    // 팔로워 리스트
    getFollower({ commit }, userId) {
      const API_URL = `${REST_API}/userapi/follower/${userId}`;
      axios({
        url: API_URL,
        method: "GET",
      }).then((res) => {
        commit("GET_FOLLOWER", res.data);
      });
    },

    // 팔로우
    addFollow({ commit }, fromUser) {
      const API_URL = `${REST_API}/userapi/following/${fromUser}`;
      axios({
        url: API_URL,
        method: "POST",
        params: {
          fromUser: fromUser,
          toUSer: this.state,
        },
      })
        .then(() => {
          commit;
          location.reload();
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 팔로우 취소
    unFollow({ dispatch }, toUser) {
      const API_URL = `${REST_API}/userapi/follow`;
      axios({
        url: API_URL,
        method: "DELETE",
        params: {
          fromUser: this.state.loginUser.userId,
          toUser: toUser,
        },
      })
        .then(() => {
          dispatch("getLoginFollowing", this.state.loginUser.userId);
          location.reload();
        })
        .catch((err) => {
          console.log(err);
          console.log(toUser);
          console.log(this.state.loginUser.userId);
        });
    },
    getUser({ commit }, userId) {
      const API_URL = `${REST_API}/userapi/user/${userId}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          commit("GET_USER", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 전체 유저 리스트
    getAllUser({ commit }) {
      const API_URL = `${REST_API}/userapi/user`;

      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          console.log(res.data);

          commit(
            "GET_USERS",
            res.data.filter((data) => {
              // 전체 리스트에서 로그인 유저 제외
              if (data.userId == this.state.loginUser.userId) return false;
              // 내가 보고 있는 프로필 유저 제외 (user)
              if (data.userId == this.state.user.userId) return false;
              // 로그인 유저가 팔로우하고 있는 유저 제외
              for (var key in this.state.loginFollowing) {
                if (this.state.loginFollowing[key].userId == data.userId) {
                  return false;
                }
              }
              return true;
            })
          );
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  modules: {},
  plugins: [createPersistedState()],
});
