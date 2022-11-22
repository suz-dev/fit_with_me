import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import VCalendar from "v-calendar";
import VueTimepicker from "vue2-timepicker";

// Use v-calendar & v-date-picker components

Vue.use(VueTimepicker, {});

Vue.use(VCalendar, {
  // Use <vc-calendar /> instead of <v-calendar />
  // ...other defaults
});
// Import Bootstrap and BootstrapVue CSS files (order is important)
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

Vue.use(VCalendar, {});

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
