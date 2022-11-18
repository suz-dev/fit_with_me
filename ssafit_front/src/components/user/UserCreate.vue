<template>
  <div>
    <fieldset>
      <legend>회원가입</legend>
      <label for="id">ID : </label>
      <input type="text" id="id" v-model="userId" />
      <label for="pw">PASSWORD : </label>
      <input type="text" id="pw" v-model="password" />
      <label for="pw">NAME : </label>
      <input type="text" id="pw" v-model="userName" />
      <button @click="createUser">가입</button>
    </fieldset>
  </div>
</template>

<script>
export default {
  name: "UserCreate",
  data() {
    return {
      userId: "",
      password: "",
      userName: "",
    };
  },

  methods: {
    createUser() {
      let user = {
        userId: 0,
        password: this.password,
        userName: this.userName,
      };

      this.$store.dispatch("createUser", user);
    },
  },
};
</script>

<style></style>
