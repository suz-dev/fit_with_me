<template>
  <div class="container">
    <h2>로그인</h2>
    <div>
      <b-form-group label="id" label-for="id">
        <b-form-input id="id" v-model="user.id" trim></b-form-input>
      </b-form-group>
      <b-form-group label="pw" label-for="pw">
        <b-form-input id="pw" v-model="user.password" trim></b-form-input>
      </b-form-group>
      <b-button type="submit" variant="outline-success" @click="login"
        >로그인</b-button
      >
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
    },
  },
};
</script>

<style></style>
