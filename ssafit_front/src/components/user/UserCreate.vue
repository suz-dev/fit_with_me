<template>
  <div>
    <h2>회원가입</h2>

    <b-form @submit="createUser">
      <!-- 프로필 사진 -->

      <b-form-input
        id="id"
        v-model="userId"
        type="text"
        placeholder="ID"
        required
      ></b-form-input>

      <b-form-input
        id="pw"
        v-model="password"
        type="text"
        placeholder="Password"
        required
      ></b-form-input>

      <b-form-input
        id="name"
        v-model="userName"
        type="text"
        placeholder="Name"
        required
      ></b-form-input>
      <b-form-input
        id="email"
        v-model="email"
        type="text"
        placeholder="e-mail"
        required
      ></b-form-input>
      <b-form-input
        id="birthDate"
        v-model="birthDate"
        type="text"
        placeholder="생년월일"
        required
      ></b-form-input>
    </b-form>
    <!-- 성별 -->
    <div class="form-check">
      <input
        class="form-check-input"
        type="radio"
        name="flexRadioDefault"
        id="flexRadioDefault2"
        checked
      />
      <label class="form-check-label" for="flexRadioDefault2"> 여성 </label>
    </div>

    <div class="form-check">
      <input
        class="form-check-input"
        type="radio"
        name="flexRadioDefault"
        id="flexRadioDefault1"
      />
      <label class="form-check-label" for="flexRadioDefault1"> 남성 </label>
    </div>

    <b-button type="submit" variant="primary">가입</b-button>
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
      email: "",
    };
  },

  methods: {
    createUser() {
      let user = {
        userId: this.userId,
        password: this.password,
        userName: this.userName,
        email: this.email,
      };
      this.$store.dispatch("createUser", user);
    },
  },
};
</script>

<style></style>
