<template>
  <div id="im">
    <img id="BackgroundImage" :src="require('../assets/CapLogo.png')" alt="capfoto">

    <div id="divSignInForm">
      <img id="layoutIMG" :src="require('../assets/CapSmallLogo.png')" alt="capfoto">

      <v-responsive
          class="mx-auto"
          max-width="344">
        <v-text-field
            v-model="form.username"
            label="Email"
            color="rgb(12,150,203)"
            :rules="firstNameRules"
            name="username" id="username" placeholder="Enter your capgemini email"
            @input="form.username = form.username.toLowerCase()"
        ></v-text-field>

        <v-text-field
            v-model="form.password"
            label="Password"
            :rules="lastNameRules"
            color="rgb(12,150,203)"
            type="password" name="password" id="pass-word" placeholder="Enter your password"
        ></v-text-field>

        <v-select
            v-model="form.positions"
            :items="['STREAM_LEAD', 'EMPLOYEE']"
            :rules="[v => !!v || 'Item is required']"
            label="Functions"
            color="rgb(12,150,203)"
            required
        >
          <template #prepend-item>
            <v-list-item disabled>Select your function</v-list-item>
          </template>
        </v-select>

        <v-btn
            :disabled="!form"
            :loading="loading"
            block
            color="info"
            class="text-none mb-4"
            size="medium"
            variant="elevated"
            type="submit" id="login" v-if="isLoginForm" :to="{ path: '/myAccount/auth/login' }"
        >
          Login
        </v-btn>

        <v-btn
            :disabled="!form"
            :loading="loading"
            block
            class="text-none mb-4"
            color="success"
            size="large"
            variant="flat"
            type="submit" id="registreer" @click="registerUser"
        >
          Register
        </v-btn>

      </v-responsive>
    </div>
  </div>
</template>

<script>
import AuthenticationService from "@/services/authentication/AuthenticationService";

export default {
  name: "AuthenticationComponent",
  data() {
    return {
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
        if (err.response.status === 403 || err.response.status === 400) {
          alert("Please fill in all the fields!")
        } else {
          alert("Your email or password is incorrect or you are not registered yet.")
        }
      });
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