<template>
  <div>
    <h2>회원 정보</h2>

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
            ref="idInput"
            id="id"
            v-model="userId"
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
          ref="pwInput"
          id="pw"
          v-model="password"
          type="password"
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
          ref="pw2Input"
          v-model="pw2"
          type="password"
          placeholder="Password 재입력"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="name" label-for="name">
        <b-form-input
          id="name"
          ref="nameInput"
          v-model="userName"
          type="text"
          placeholder="Name"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="email" label-for="email">
        <b-form-input
          id="email"
          ref="emailInput"
          v-model="email"
          type="email"
          class="form-control"
          placeholder="name@example.com"
          required
        ></b-form-input>
      </b-form-group>

      <b-form inline>
        <b-form-group id="birthDate" label-for="birthDate">
          <b-form-datepicker
            id="birthDate"
            v-model="birthDate"
            placeholder="생년월일 (선택)"
            required
          ></b-form-datepicker>
        </b-form-group>

        <!-- 성별 -->

        <b-form-select v-model="sex" :options="options"></b-form-select>
      </b-form>
    </b-container>
    <b-button class="btn btn-primary" to="/">취소</b-button>
    <button class="btn btn-primary" @click="createUser">등록</button>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "UserCreate",

  data() {
    return {
      profile: "basic",
      userId: "",
      password: "",
      pw2: "",
      userName: "",
      sex: null,
      email: "",
      birthDate: null,
      validId: false,
      validEmail: true,
      options: [
        { value: "null", text: "성별 (선택)", disabled: true },
        { value: "f", text: "여성" },
        { value: "m", text: "남성" },
      ],
    };
  },

  computed: {
    state() {
      return this.password == this.pw2 && this.pw2.length > 0;
    },
  },
  methods: {
    checkId() {
      if (this.userId.length == 0) {
        alert("아이디를 입력하세요.");
        return;
      }

      const API_URL = `http://localhost:9999/userapi/user/${this.userId}`;

      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          console.log(res);
          alert("이미 사용중인 아이디입니다");
        })
        .catch((err) => {
          alert("사용 가능한 아이디입니다");
          this.validId = true;
          console.log(err);
        });
    },

    createUser() {
      let user = {
        profile: this.profile,
        userId: this.userId,
        password: this.password,
        userName: this.userName,
        email: this.email,
        sex: this.sex,
        birthDate: this.birthDate,
      };

      if (!this.validId) {
        alert("아이디 중복 체크를 하지 않았습니다.");
        this.$refs.idInput.focus();
        return;
      }
      if (this.password.length == 0) {
        alert("비밀번호를 입력하세요");
        this.$refs.pwInput.focus();
        return;
      }
      if (!this.state) {
        alert("비밀번호가 일치하는지 확인해주세요");
        this.$refs.pw2Input.focus();
        return;
      }
      if (this.userName.length == 0) {
        alert("이름을 입력하세요");
        this.$refs.nameInput.focus();
        return;
      }
      if (this.email.indexOf("@") == -1) {
        alert("이메일 형식이 아닙니다.");
        this.$refs.emailInput.focus();
        this.validEmail = false;
        return;
      }
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
