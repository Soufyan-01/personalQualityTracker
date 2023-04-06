<template>
  <v-app>
    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>

  <v-card>
    <v-layout>

      <v-navigation-drawer theme="dark"
                           :width="125"
                           permanent>
        <v-list-item
            class="d-flex justify-center"
            nav
            prepend-avatar="https://randomuser.me/api/portraits/women/75.jpg"
        ></v-list-item>

        <v-divider></v-divider>


        <v-list>
          <v-list-item class="d-flex justify-center" title="Home" :to="{ path: '/' }"></v-list-item>

          <v-list-item class="d-flex justify-center" v-if="(isLoggedIn && isStreamLead)" title="AllUsers" value="AllUsers" :to="{ path: '/capgemini/allUsers' }"></v-list-item>

          <v-list-item class="d-flex justify-center" title="Login" value="Login" :to="{ path: '/myAccount/auth/login' }"></v-list-item>

          <v-list-item class="d-flex justify-center" v-if="isLoggedIn" @click="logOut" title="Logout" :to="{ path: '/' }"></v-list-item>

        </v-list>
      </v-navigation-drawer>

      <v-main style="height: 300px"></v-main>
    </v-layout>
  </v-card>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {},
  data: () => ({
    showNavigation: false,
    id: null
  }),
  computed: {
    isLoggedIn() {
      let auth = localStorage.getItem("auth");
      return auth != null;
    },

    isStreamLead() {
      let roles = localStorage.getItem("roles");
      if (roles == null) return false
      return roles.includes("STREAM_LEAD")
    },

    isEmployee() {
      let roles = localStorage.getItem("roles");
      if (roles == null) return false
      return roles.includes("EMPLOYEE")
    }
  },
  methods: {
    getId() {
      this.id = localStorage.getItem('employeeId');
    },
    logOut() {
      localStorage.removeItem("auth");
      localStorage.removeItem("roles");
      localStorage.removeItem("email");
      localStorage.removeItem("employeeId")
      this.$router.push("/")
      window.location.reload();
    },
  },
  mounted() {
    this.getId();
  }
}
</script>
