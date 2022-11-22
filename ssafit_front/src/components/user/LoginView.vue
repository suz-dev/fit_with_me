<template>
  <div class="container">
    <h2>LOG IN</h2>

    <div>
      <b-container>
        <b-form-group label-for="id">
          <b-form-input
            id="id"
            v-model="user.id"
            trim
            placeholder="ID"
          ></b-form-input>
        </b-form-group>
        <b-form-group label-for="pw">
          <b-form-input
            id="pw"
            type="password"
            v-model="user.password"
            trim
            placeholder="Password"
          ></b-form-input>
        </b-form-group>
        <br />

        <b-button to="/user/regist" variant="outline-secondary"
          >회원가입</b-button
        >
        <b-button type="submit" variant="outline-primary" @click="login"
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

<style></style>
