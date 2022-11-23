<template>
  <div class="container1">
    <h2>LOG IN</h2>
    <br />

    <div>
      <b-container>
        <b-row>
          <b-col></b-col>
          <b-col cols="5">
            <b-form-group label-for="id">
              <b-form-input
                id="id"
                v-model="user.id"
                trim
                placeholder="ID"
              ></b-form-input>
            </b-form-group>
          </b-col>
          <b-col></b-col>
        </b-row>

        <b-row>
          <b-col></b-col>
          <b-col cols="5">
            <b-form-group label-for="pw">
              <b-form-input
                id="pw"
                type="password"
                v-model="user.password"
                trim
                placeholder="Password"
              ></b-form-input>
            </b-form-group>
          </b-col>
          <b-col></b-col>
        </b-row>
        <br />

        <b-button to="/user/regist" variant="secondary">회원 가입</b-button>
        <span>&nbsp;&nbsp;&nbsp;</span>
        <b-button type="submit" variant="outline-secondary" @click="login"
          >로그인</b-button
        >
      </b-container>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {
        id: "",
        password: "",
      },
    };
  },
  methods: {
    login() {
      if (this.user.id === "" || this.user.password === "") {
        alert("잘못된 입력입니다.");
        return;
      }

      this.$store.dispatch("login", this.user);
      this.$store.dispatch("getLoginFollowing", this.user.id);
    },
  },
};
</script>

<style scoped>
.container1 {
  margin-top: 150px;
}
</style>
