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
            label="Function"
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
  <v-row justify="center">
    <v-dialog
        v-model="dialog"
        persistent
        width="1024"
    >
<!--      <template v-slot:activator="{ props }">-->

<!--      </template>-->
      <v-card>
        <v-card-title>
          <span class="text-h5">My user Profile</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col
                  cols="12"
                  sm="6"
                  md="4"
              >
                <v-text-field
                    v-model="formTwo.name"
                    label="Legal first name*"
                    color="rgb(12,150,203)"
                    :rules="firstNameRules"
                    name="name" id="name" placeholder="Enter your name"
                ></v-text-field>
              </v-col>
              <v-col
                  cols="12"
                  sm="6"
                  md="4"
              >
                <v-text-field
                    label="Legal middle name"
                    hint="example of helper text only on focus"
                ></v-text-field>
              </v-col>
              <v-col
                  cols="12"
                  sm="6"
                  md="4"
              >
                <v-text-field
                    v-model="formTwo.surname"
                    label="Legal last name*"
                    :rules="lastNameRules"
                    color="rgb(12,150,203)"
                    type="surname" name="surname" id="surname" placeholder="surname"
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                    v-model="formTwo.email"
                    label="Email*"
                    :rules="lastNameRules"
                    color="rgb(12,150,203)"
                    type="email" name="email" id="email" placeholder="Make sure you enter your capgemini email"
                ></v-text-field>
              </v-col>
<!--              <v-col-->
<!--                  cols="12"-->
<!--                  sm="6"-->
<!--              >-->
<!--                <v-select-->
<!--                    :items="['0-17', '18-29', '30-54', '54+']"-->
<!--                    label="Age*"-->
<!--                    required-->
<!--                ></v-select>-->
<!--              </v-col>-->
<!--              <v-col-->
<!--                  cols="12"-->
<!--                  sm="6"-->
<!--              >-->
<!--                <v-autocomplete-->
<!--                    :items="['Skiing', 'Ice hockey', 'Soccer', 'Basketball', 'Hockey', 'Reading', 'Writing', 'Coding', 'Basejump']"-->
<!--                    label="Interests"-->
<!--                    multiple-->
<!--                ></v-autocomplete>-->
<!--              </v-col>-->
            </v-row>
          </v-container>
          <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="blue-darken-1"
              variant="text"
              @click="dialog = false"
          >
            Cancel
          </v-btn>
          <v-btn
              color="blue-darken-1"
              variant="text"
              id="completeAccount"
              @click="completeAccount"
          >
            Complete account
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
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
      formTwo: {
        name: "",
        surname: "",
        email: ""
      },
      isLoginForm: true,
      errorMsg: '',
      dialog: false
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
            localStorage.setItem("email", this.form.username);
            localStorage.setItem("position", this.form.positions);
            this.dialog = true;
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
    },
    completeAccount(){
      const data = {
        id: null,
        name: this.formTwo.name,
        surname: this.formTwo.surname,
        email: this.formTwo.email
      };
      AuthenticationService.completeUserAccount(data)
          .then((response) => {
            this.id = response.data;
            console.log(response.data.email);
            // window.location.reload();
            window.location.href = "/myAccount/auth/login";
          }).then((res) => {
            console.log(res)
            this.isLoginForm = true;
          }).catch(() => {
            if(localStorage.getItem("email") === data.email){
              alert("Your account is already completed!")
            } else {
              alert("Please make sure u enter your capgemini email!")
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