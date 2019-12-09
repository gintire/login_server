<template>
  <div>
    <nav class="teal">
      <div class="nav-wrapper container teal">
        <router-link to="/" class="brand-logo">USER_MANAGE</router-link>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
          <li><router-link to="/board?page=0">게시판</router-link></li>
          <li><router-link to="/user">회원 목록</router-link></li>
          <li v-if="!loggedIn"><router-link to="/signup">회원 가입</router-link></li>
          <li v-if="!loggedIn"><router-link to="/login">로그인</router-link>
          <li v-if="loggedIn"><router-link to="/user/modify">정보수정</router-link></li>
          <li v-if="loggedIn"><a @click="logout">로그아웃</a></li>
        </ul>
      </div>
    </nav>
  </div>
</template>

<script>
import qstr from 'query-string';
import axios from '../libs/axios.custom';

export default {
  async created() {
    await this.getUserDetails();
  },

  data: () => ({
    loggedIn: false,
    params: {
      response_type: 'token',
      client_id: 'vueclient',
      redirect_uri: `${process.env.VUE_APP_ORIGIN}/login?success`,
    },
    originHost: process.env.VUE_APP_ORIGIN,
  }),

  methods: {
    async getUserDetails() {
      try {
        const username = this.$store.getters.username;
        if (username === null) return;

        const res = await axios.get(`/api/v1/user/${username}`);
        if (res.status === 200) {
          this.$store.commit('setUserDetail', res.data);
          this.loggedIn = true;
        }
      } catch (err) {
        if (err.response.status === 401) {
          this.logout();
        }
      }
    },

    logout() {
      this.loggedIn = false;
      this.$store.commit('setUser', null);
      this.$store.commit('setUserDetail', null);
      this.$router.push('/');
    },
  },
};
</script>
