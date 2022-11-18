<template>
  <div>
    <h2>회원가입</h2>

    <!-- 프로필 사진 -->
    <b-form-group id="profile" label-for="profile">
      <b-form-input
        id="profile"
        v-model="userId"
        type="text"
        placeholder="프로필 고르게 어떻게?"
        required
      ></b-form-input>
    </b-form-group>

    <!-- userId 입력 + 유효성 검사(아이디 중복 체크) -->
    <b-form-group id="id" label-for="id">
      <b-form inline>
        <b-form-input
          id="id"
          v-model="id"
          type="text"
          placeholder="ID"
          required
        ></b-form-input>

        <!-- 구현 -->
        <!-- 중복체크 확인 여부 -->
        <!-- 중복체크 해서 ok -> true / false-->
        <b-button>ID 중복체크</b-button>
      </b-form>
    </b-form-group>

    <b-form-group id="pw" label-for="pw">
      <b-form-input
        id="pw"
        v-model="password"
        type="text"
        placeholder="Password"
        required
      ></b-form-input>
    </b-form-group>

    <!-- pw 검증 -->
    <b-form-group
      id="pw"
      label-for="pw"
      valid-feedback="비밀번호가 일치합니다."
      invalid-feedback="비밀번호가 일치하지않습니다"
      :state="state"
    >
      <b-form-input
        id="pw"
        v-model="pw2"
        type="text"
        placeholder="Password 재입력"
        required
      ></b-form-input>
    </b-form-group>

    <b-form-group id="name" label-for="name">
      <b-form-input
        id="name"
        v-model="name"
        type="text"
        placeholder="Name"
        required
      ></b-form-input>
    </b-form-group>

    <b-form-group id="email" label-for="email">
      <b-form-input
        id="email"
        v-model="email"
        type="email"
        placeholder="name@example.com"
        required
      ></b-form-input>
    </b-form-group>

    <b-form-group id="birthDate" label-for="birthDate">
      <b-form-input
        id="birthDate"
        v-model="birthDate"
        type="date"
        required
      ></b-form-input>
    </b-form-group>

    <!-- 성별 -->
    <b-form inline>
      <div class="form-check">
        <input
          class="form-check-input"
          type="radio"
          name="flexRadioDefault"
          id="sex"
          checked
        />
        <label class="f" for="flexRadioDefault2"> 여성 </label>
      </div>

      <div class="form-check">
        <input
          class="form-check-input"
          type="radio"
          name="flexRadioDefault"
          id="sex"
        />
        <label class="m" for="flexRadioDefault1"> 남성 </label>
      </div>
    </b-form>
    <b-button class="btn btn-primary" to="/">취소</b-button>
    <button class="btn btn-primary" @click="createUser">등록</button>
  </div>
</template>

<script>
export default {
  name: "UserCreate",
  data() {
    return {
      profile: "",
      id: "",
      password: "",
      pw2: "",
      name: "",
      sex: "",
      email: "",
    };
  },

  computed: {
    state() {
      return this.password == this.pw2 && this.pw2.length > 0;
    },
  },
  methods: {
    createUser() {
      let user = {
        profile: this.profile,
        id: this.userId,
        password: this.password,
        name: this.userName,
        email: this.email,
        sex: this.sex,
        birthDate: this.birthDate,
      };
      this.$store.dispatch("createUser", user);
    },
  },
};
</script>

<style></style>
