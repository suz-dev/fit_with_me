<template>
  <div>
    <h2 v-if="loginUser.userId">회 원 정 보</h2>
    <h2 v-else>회 원 가 입</h2>
    <br />

    <b-container>
      <!-- 프로필 사진 -->
      <b-form-group>
        <div class="container1">
          <b-form inline>
            <div>
              <label>
                <input type="radio" v-model="profile" value="basic" checked />
                <img src="@/assets/basic.jpg" alt="Option 1" />
              </label>
            </div>
            <div>
              <label>
                <input type="radio" v-model="profile" value="sarang1" checked />
                <img src="@/assets/sarang1.jpg" alt="Option 2" />
              </label>
            </div>

            <div>
              <label>
                <input type="radio" v-model="profile" value="bong1" checked />
                <img src="@/assets/bong1.jpg" alt="Option 1" />
              </label>
            </div>

            <div>
              <label>
                <input type="radio" v-model="profile" value="eli" checked />
                <img src="@/assets/eli.jpg" alt="Option 1" />
              </label>
            </div>
            <div>
              <label>
                <input type="radio" v-model="profile" value="kong" checked />
                <img src="@/assets/kong.jpg" alt="Option 1" />
              </label>
            </div>

            <div>
              <label>
                <input type="radio" v-model="profile" value="som" checked />
                <img src="@/assets/som.jpg" alt="Option 2" />
              </label>
            </div>

            <div>
              <label>
                <input type="radio" v-model="profile" value="kang" checked />
                <img src="@/assets/kang.jpg" alt="Option 1" />
              </label>
            </div>

            <div>
              <label>
                <input type="radio" v-model="profile" value="yong" checked />
                <img src="@/assets/yong.jpg" alt="Option 1" />
              </label>
            </div>
            <div>
              <label>
                <input type="radio" v-model="profile" value="min" checked />
                <img src="@/assets/min.jpg" alt="Option 1" />
              </label>
            </div>
            <div>
              <label>
                <input type="radio" v-model="profile" value="thunder" checked />
                <img src="@/assets/thunder.jpg" alt="Option 1" />
              </label>
            </div>
          </b-form>
        </div>
      </b-form-group>
      <br />

      <!-- userId 입력 + 유효성 검사(아이디 중복 체크) -->
      <b-form-group id="id" label-for="id">
        <b-form inline>
          <b-form-input
            v-if="loginUser.userId"
            v-model="userId"
            type="text"
            readonly
          ></b-form-input>
          <b-form-input
            v-else
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
          <span>&nbsp;&nbsp;&nbsp;</span>
          <b-button v-if="loginUser.userId" disabled @click="checkId"
            >ID 중복체크</b-button
          >
          <b-button v-else @click="checkId">ID 중복체크</b-button>
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

      <!-- 이름 / 성별 / 생년월일 -->
      <b-form-group>
        <b-form inline>
          <b-form-input
            inline
            id="name"
            ref="nameInput"
            v-model="userName"
            type="text"
            placeholder="Name"
            required
          ></b-form-input>
          <span>&nbsp;&nbsp;&nbsp;</span>

          <!-- 성별 -->
          <b-form-select v-model="sex" :options="options"></b-form-select>

          <span>&nbsp;&nbsp;&nbsp;</span>
          <b-form-datepicker
            id="birthDate"
            v-model="birthDate"
            placeholder="생년월일 (선택)"
            required
          ></b-form-datepicker>
        </b-form>
      </b-form-group>

      <!-- 이메일 -->
      <b-form-group id="email" label-for="email">
        <b-form-input
          v-if="loginUser.userId"
          v-model="email"
          type="email"
          readonly
        ></b-form-input>
        <b-form-input
          v-else
          id="email"
          ref="emailInput"
          v-model="email"
          type="email"
          class="form-control"
          placeholder="name@example.com"
          required
        ></b-form-input>
      </b-form-group>
    </b-container>

    <br />

    <b-button class="btn" variant="secondary" to="/">취소</b-button>
    <span>&nbsp;&nbsp;&nbsp;</span>
    <span>
      <b-button
        v-if="loginUser.userId"
        variant="outline-primary"
        @click="updateUser"
      >
        수정
      </b-button>

      <b-button v-else variant="outline-secondary" @click="createUser">
        등록
      </b-button>
    </span>
    <span>&nbsp;&nbsp;&nbsp;</span>
    <b-button
      variant="outline-danger"
      v-if="loginUser.userId"
      @click="showDeleteModal"
      >탈퇴</b-button
    >

    <b-modal
      id="deleteModal"
      title="정말 탈퇴하시겠습니까?"
      ok-variant="outline-primary"
      @ok="deleteUser"
    >
      비밀번호를 다시 입력하세요.
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
    </b-modal>
  </div>
</template>

<script>
import { mapState } from "vuex";
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
    ...mapState(["loginUser"]),
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
        .then(() => {
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
      this.$router.push("/user/login");
    },

    updateUser() {
      let user = {
        profile: this.profile,
        userId: this.userId,
        password: this.password,
        userName: this.userName,
        email: this.email,
        sex: this.sex,
        birthDate: this.birthDate,
      };

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
      this.$store.dispatch("updateUser", user);
      this.$router.push(`/user/userInfo/${this.loginUser.userId}`);
    },
    showDeleteModal() {
      this.$bvModal.show("deleteModal");
    },
    // 회원 탈퇴
    deleteUser() {
      this.$store.dispatch("deleteUser", this.loginUser.userId);
    },
  },
  created() {
    if (this.loginUser.userId) {
      const API_URL = `http://localhost:9999/userapi/user/${this.loginUser.userId}`;

      axios({
        url: API_URL,
        method: "GET",
      }).then((res) => {
        let user = res.data;
        this.profile = user.profile;
        this.userId = user.userId;
        this.password = user.password;
        this.userName = user.userName;
        this.sex = user.sex;
        this.email = user.email;
        this.birthDate = user.birthDate;
        this.validId = true;
      });
    }
  },
};
</script>

<style scoped>
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
  /* outline: 5px solid blue;
   */
  background: linear-gradient(#fff, #fff) padding-box,
    linear-gradient(
        45deg,
        rgb(185, 174, 255),
        rgb(227, 157, 241),
        rgb(141, 179, 250)
      )
      border-box;
  border: 9px solid transparent;
  border-radius: 50%;
}

.container1 {
  width: 700px;
}
.container {
  width: 700px;
}
</style>
