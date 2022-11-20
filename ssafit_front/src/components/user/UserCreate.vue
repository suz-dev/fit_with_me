<template>
  <div>
    <h2>회원가입</h2>

    <b-container>
      <!-- 프로필 사진 -->
      <b-form-group id="id" label-for="id">
        <b-container>
          <b-form inline>
            <div>
              <label>
                <input type="radio" v-model="profile" value="bear" checked />
                <img src="@/assets/bear.png" alt="Option 2" />
              </label>
            </div>

            <div>
              <label>
                <input type="radio" v-model="profile" value="bird" checked />
                <img src="@/assets/bird.png" alt="Option 1" />
              </label>
            </div>
            <div>
              <label>
                <input type="radio" v-model="profile" value="chicken" checked />
                <img src="@/assets/chicken.png" alt="Option 1" />
              </label>
            </div>

            <div>
              <label>
                <input type="radio" v-model="profile" value="dog" checked />
                <img src="@/assets/dog.png" alt="Option 2" />
              </label>
            </div>

            <div>
              <label>
                <input type="radio" v-model="profile" value="fox" checked />
                <img src="@/assets/fox.png" alt="Option 1" />
              </label>
            </div>
            <div>
              <label>
                <input type="radio" v-model="profile" value="horse" checked />
                <img src="@/assets/horse.png" alt="Option 1" />
              </label>
            </div>

            <div>
              <label>
                <input type="radio" v-model="profile" value="leopard" checked />
                <img src="@/assets/leopard.png" alt="Option 2" />
              </label>
            </div>

            <div>
              <label>
                <input type="radio" v-model="profile" value="penguin" checked />
                <img src="@/assets/penguin.png" alt="Option 1" />
              </label>
            </div>
            <div>
              <label>
                <input type="radio" v-model="profile" value="turtle" checked />
                <img src="@/assets/turtle.png" alt="Option 1" />
              </label>
            </div>

            <div>
              <label>
                <input type="radio" v-model="profile" value="basic" checked />
                <img src="@/assets/basic.png" alt="Option 1" />
              </label>
            </div>
          </b-form>
        </b-container>
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
          <b-button @click="checkId">ID 중복체크</b-button>
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
          class="form-control"
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
    </b-container>
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
    checkId() {},
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

<style>
[type="radio"] {
  position: absolute;
  opacity: 0;
  width: 0;
  height: 0;
}

/* IMAGE STYLES */
[type="radio"] + img {
  width: 125px;
  margin: 5px;

  border-radius: 50%;
  cursor: pointer;
}

/* CHECKED STYLES */
[type="radio"]:checked + img {
  outline: 5px solid blue;
}
</style>
