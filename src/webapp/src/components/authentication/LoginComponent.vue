<template>
  <div id="im">
    <img id="BackgroundImage" :src="require('../assets/CapLogo.png')" alt="capfoto">

    <div id="divSignInForm">

      <img id="layoutIMG" :src="require('../assets/CapSmallLogo.png')" alt="capfoto">

      <v-responsive
          class="mx-auto"
          max-width="344">
        <v-text-field class="text"
                      name="first-name" id="first-name" placeholder="Enter your capgemini email" v-model="form.username"
                      @input="form.username = form.username.toLowerCase()"
        ></v-text-field>
        <v-text-field class="text"
                      type="password" name="password" id="pass-word" placeholder="Enter your password"
                      v-model="form.password"
        ></v-text-field>

        <v-btn
            :disabled="!form"
            :loading="loading"
            block
            color="info"
            class="text-none mb-4"
            size="medium"
            variant="elevated"
            type="submit" id="login" v-if="isLoginForm" :to="{ path: '/myAccount/auth/register' }"
        >
          Register
        </v-btn>

        <v-btn
            :disabled="!form"
            :loading="loading"
            block
            class="text-none mb-4"
            color="success"
            size="large"
            variant="flat"
            type="submit" id="login" v-if="isLoginForm" @click="userLogin()"
        >
          Login
        </v-btn>

      </v-responsive>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import AuthenticationService from "@/services/authentication/AuthenticationService";
import AssessmentService from "@/services/assessment/AssessmentService";
export default {
  name: "LoginComponent",
  data() {
    return {
      form: {
        username: "",
        password: "",
        passWordCheck: "",
        positions: "",
      },
      isLoginForm: true,
      errorMsg: ''
    }
  },
  methods: {
    userLogin() {


      axios.post("http://localhost:8081/login", {
        username: this.form.username,
        password: this.form.password
      })
          .then((res) => {
            console.log(res);
            console.log(res.data);

            const tokenRole = res.data.split(',');

            localStorage.setItem("email", this.form.username)
            localStorage.setItem("auth", tokenRole[0]);
            localStorage.setItem("roles", tokenRole[1]);

            console.log(localStorage.getItem("roles"));

            if(localStorage.getItem("roles").includes("STREAM_LEAD")){
              AuthenticationService.getStreamLeadId(localStorage.getItem('email'))
                  .then(response => {
                    localStorage.setItem("id", response.data.id);

                    AssessmentService.CheckIfAssessmentIsMade(response.data.id)
                        .then((resp) => {
                          if(resp.data.includes("false")){
                            localStorage.setItem("assessment", "false");
                          } else {
                            localStorage.setItem("assessment", "true");
                          }
                        })

                    window.location.href = "/";
                  });
            } else if (localStorage.getItem("roles").includes("EMPLOYEE")){
              AuthenticationService.getEmployeeId(localStorage.getItem('email'))
                  .then(response => {
                    localStorage.setItem("id", response.data.id);

                    AssessmentService.CheckIfAssessmentIsMade(response.data.id)
                        .then((resp) => {
                          if(resp.data === false){
                            localStorage.setItem("assessment", "false");
                          } else {
                            localStorage.setItem("assessment", "true");
                          }
                        })

                    window.location.href = "/";
                  });
            }



          })
          .catch((err) => {
            if(err.response.status === 400){
              alert("Please fill in all the fields!")
            } else {
              alert("Your email or password is incorrect or you are not registered yet.")
            }
          })
    }
  }
}

</script>

<style scoped>

#layoutIMG {
  width: 5%;
  height: 10%;
  margin: 0;
  position: absolute;
  top: 10%;
  left: 50%;
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}

#BackgroundImage {
  width: 20%;
  height: 60%;
  margin-left: 80%;
  opacity: 0.7;
}

</style>