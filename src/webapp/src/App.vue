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
              :title="personName"
              :subtitle="email"
          ></v-list-item>
        </v-list>

        <v-divider></v-divider>

        <v-list density="compact" nav>

<!--          Home page-->
          <v-list-item class="d-flex justify-center"  v-if="(assessmentMade && isLoggedIn)" title="Home" :to="{ path: '/' }"></v-list-item>

<!--          All users for StreamLead-->
          <v-list-item prepend-icon="mdi-account-group-outline" class="d-flex justify-center" v-if="(isLoggedIn && isStreamLead)" title="Users" value="AllUsers" :to="{ path: '/capgemini/allUsers' }"></v-list-item>

<!--          Login-->
          <v-list-item class="d-flex justify-center" v-if="isLoggedOut" title="Login" value="Login" :to="{ path: '/myAccount/auth/login' }"></v-list-item>

<!--          Assessment-->
          <v-list-item prepend-icon="mdi-folder" class="d-flex justify-center" v-if="(isLoggedIn && isEmployee)" title="Assessment" :to="{ path: '/capgemini/assessment' }"></v-list-item>

          <!--          Resluts of assessment for Employee-->
          <v-list-item class="d-flex justify-center"  v-if="(assessmentMade && isLoggedIn && isEmployee)" title="Results" :to="{ path: '/capgemini/assessment/results' }"></v-list-item>

          <!--          CareerPathOption, RIGHT NEED TO BE ADDED-->
          <v-list-item class="d-flex justify-center"  title="Careerpath" :to="{ path: '/capgemini/careerPathOption' }"></v-list-item>

          <!--          CareerPathOption, RIGHT NEED TO BE ADDED IS FOR STREAMLEAD-->
          <v-list-item class="d-flex justify-center"  title="Soft skill courses" :to="{ path: '/capgemini/softSkillCourse' }"></v-list-item>

          <!--          CareerPathOption, RIGHT NEED TO BE ADDED IS FOR STREAMLEAD-->
          <v-list-item class="d-flex justify-center"  title="Soft skill radar" :to="{ path: '/capgemini/softSkillRadarChart' }"></v-list-item>

          <!--          CareerPathOption, RIGHT NEED TO BE ADDED IS FOR STREAMLEAD-->
          <v-list-item class="d-flex justify-center"  title="Select Soft Skill" :to="{ path: '/capgemini/chooseSoftSkillCourse' }"></v-list-item>


          <!--          CareerPathOption, RIGHT NEED TO BE ADDED IS FOR STREAMLEAD-->
          <v-list-item class="d-flex justify-center"  title="Hard skill radar" :to="{ path: '/capgemini/hardSkillCourse' }"></v-list-item>

          <!--          General-->
          <v-list-item class="d-flex justify-center" v-if="isLoggedIn" @click="logOut" title="Logout" :to="{ path: '/' }"></v-list-item>

        </v-list>
      </v-navigation-drawer>

      <v-main style="height: 250px"></v-main>
    </v-layout>
  </v-card>

  <footer class="footer">
    <p>© 2023 Personal Quality Tracker. All rights reserved.</p>
  </footer>
</template>

<script>

export default {
  name: 'App',
  components: {},
  data: () => ({
    showNavigation: false,
    id: null,
    personName: "",
    email: ""
  }),
  computed: {
    assessmentNotMade() {
      let assessment = localStorage.getItem("assessment");
      return assessment !== "true";
    },
    assessmentMade() {
      let assessment = localStorage.getItem("assessment");
      return assessment === "true";
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
    getPersonName(){
      let email = localStorage.getItem("email");
      let parts = email.split("@");
      this.personName = parts[0];
      this.email = email;
    },
    logOut() {
      localStorage.removeItem("auth");
      localStorage.removeItem("roles");
      localStorage.removeItem("email");
      localStorage.removeItem("id");
      localStorage.removeItem("assessment");
      localStorage.removeItem("position");

      window.location.href = "http://localhost:8080/myAccount/auth/login#/myAccount/auth/login";
    },
  },
  mounted() {
    this.getId();
    this.getPersonName();
  }
}
</script>


<style scoped>
.footer {
  position: fixed;
  bottom: 0;
  width: 100%;
  height: 50px; /* adjust the height to your preference */
  background-color: #f5f5f5;
  border-top: 1px solid #ddd;
  text-align: center;
  padding: 15px;
}
</style>