<template>
  <v-app>
    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>

  <v-card>
    <v-layout>
      <v-navigation-drawer
          expand-on-hover
          rail
      >
        <v-list>
          <v-list-item
              prepend-avatar="https://randomuser.me/api/portraits/women/85.jpg"
              title="Sandra Adams"
              subtitle="sandra_a88@gmailcom"
          ></v-list-item>
        </v-list>

        <v-divider></v-divider>

        <v-list density="compact" nav>

          <v-list-item class="d-flex justify-center"  v-if="assessmentMade" title="Home" :to="{ path: '/' }"></v-list-item>
          <v-list-item prepend-icon="mdi-account-group-outline" class="d-flex justify-center" v-if="(isLoggedIn && isStreamLead)" title="Users" value="AllUsers" :to="{ path: '/capgemini/allUsers' }"></v-list-item>
          <v-list-item class="d-flex justify-center" v-if="isLoggedOut" title="Login" value="Login" :to="{ path: '/myAccount/auth/login' }"></v-list-item>
          <v-list-item prepend-icon="mdi-folder" class="d-flex justify-center" v-if="isLoggedIn" title="Assessment" :to="{ path: '/capgemini/assessment' }"></v-list-item>
          <v-list-item class="d-flex justify-center" v-if="isLoggedIn" @click="logOut" title="Logout" :to="{ path: '/' }"></v-list-item>

        </v-list>
      </v-navigation-drawer>

      <v-main style="height: 250px"></v-main>
    </v-layout>
  </v-card>
</template>

<script>

export default {
  name: 'App',
  components: {},
  data: () => ({
    showNavigation: false,
    id: null
  }),
  computed: {
    assessmentMade() {
      let assessment = localStorage.getItem("assessment");
      return assessment === true;
    },

    isLoggedIn() {
      let auth = localStorage.getItem("auth");
      return auth != null;
    },

    isLoggedOut() {
      let auth = localStorage.getItem("auth");
      return auth == null;
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
      localStorage.removeItem("id");
      localStorage.removeItem("assessment")
      this.$router.push("/")
      window.location.reload();
    },
  },
  mounted() {
    this.getId();
  }
}
</script>
