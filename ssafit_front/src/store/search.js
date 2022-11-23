import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

export default new Vuex.Store({
  state: { searchVideos: [] },

  mutations: {
    SEARCH_VIDEO(state, payload) {
      state.searchVideos = payload;
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
          commit("SEARCH_VIDEO", res.data.items);
        })

        .catch((err) => {
          console.log(err);
        });
    },
  },
});
