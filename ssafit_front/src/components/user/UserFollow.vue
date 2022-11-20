<template>
  <header class="d-flex justify-content-center">
    <div>
      <!-- 팔로우 유저 정보 -->
      <h2>following</h2>
      <b-container>
        <b-row v-for="user in follow" :key="user.userId">
          <b-col>
            <img
              :src="require(`@/assets/${user.profile}.png`)"
              style="border-radius: 50%; width: 50px"
            />
          </b-col>
          <b-col> {{ user.userId }}</b-col>
          <b-col> {{ user.userName }}</b-col>
        </b-row>
      </b-container>
    </div>
  </header>
</template>

<script>
export default {};
</script>

<style></style>
