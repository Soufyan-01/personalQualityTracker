<template>

  <div id="registerWord">
  <H1 class="registerWord">To use the Personal Quality Tracker app, you need to login or create a account</H1>
  </div>

  <div id="divSignInForm">
  <v-sheet width="300" class="mx-auto">
    <v-form fast-fail @submit.prevent>
      <v-text-field
          v-model="form.username"
          label="Email"
          :rules="firstNameRules"
          name="username" id="username" placeholder="Enter your capgemini email" @input="form.username = form.username.toLowerCase()"
      ></v-text-field>

      <v-text-field
          v-model="form.password"
          label="Password"
          :rules="lastNameRules"
          type="password" name="password" id="pass-word" placeholder="Enter your password"
      ></v-text-field>

      <v-select
          v-model="form.positions"
          :items="['STREAM_LEAD', 'EMPLOYEE']"
          :rules="[v => !!v || 'Item is required']"
          label="Functions"
          required
      >
        <template #prepend-item>
          <v-list-item disabled>Select your function</v-list-item>
        </template>
      </v-select>

      <v-btn type="submit" id="registreer" block class="mt-2" @click="registerUser">Submit</v-btn>
    </v-form>
  </v-sheet>
  </div>
</template>

<script>
import AuthenticationService from "@/services/authentication/AuthenticationService";
// import axios from "axios";

export default {
  name: "AuthenticationComponent",
  data(){
    return{
      form: {
        username: "",
        password: "",
        positions: ""
      },
      isLoginForm: true,
      errorMsg: '',
    }
  },
  methods: {
    registerUser() {
      const data = {
        id: null,
        username: this.form.username,
        password: this.form.password,
        positions: this.form.positions
      };


        AuthenticationService.registerUser(data)
            .then((response) => {
              this.id = response.data;
              window.location.reload();
            }).then((res) => {
          console.log(res)
          localStorage.setItem("form", res.headers.form);
          this.isLoginForm = true;
        }).catch((err) => {
          if (err.response.status === 400) {
            alert("Please fill in all the fields!")
          }
        });
    }
  }
}
</script>

<style scoped>

  #divSignInForm {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }

  .registerWord {
    text-align: center;
    font-size: 20px;
    top: -50px;
    font-weight: bold;
    color: rgb(33,33,33)
  }

  #registerWord {
    position: absolute;
    top: 20%;
    left: 50%;
    transform: translate(-50%, -50%);
  }
</style>