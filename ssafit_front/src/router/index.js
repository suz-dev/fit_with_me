import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "@/views/HomeView";
import UserView from "@/views/UserView";
import VideoSearch from "@/components/video/VideoSearch.vue";
import VideoDetail from "@/components/video/VideoDetail.vue";
import VideoReviewList from "@/components/video/VideoReviewList.vue";
import VideoReviewDetail from "@/components/video/VideoReviewDetail.vue";
import LoginView from "@/components/user/LoginView.vue";
import UserCreate from "@/components/user/UserCreate.vue";
import UserDetail from "@/components/user/UserDetail.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
    children: [
      {
        path: "",
        name: "videoList",
        component: VideoSearch,
      },
    ],
  },
  {
    path: "/:id",
    name: "videoDetail",
    component: VideoDetail,
    children: [
      {
        path: "",
        name: "videoReviews",
        component: VideoReviewList,
      },
    ],
  },
  {
    path: "/review/:id",
    name: "videoReviewDetail",
    component: VideoReviewDetail,
  },
  // 로그인
  {
    path: "/user/login",
    name: "LoginView",
    component: LoginView,
  },
  {
    path: "/user/regist",
    name: "userCreate",
    component: UserCreate,
  },

  // 마이페이지
  {
    path: "/user/userInfo",
    name: "myPage",
    component: UserView,
    children: [
      {
        path: "",
        name: "userDetail",
        component: UserDetail,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
