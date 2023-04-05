<template>
  <v-app>
    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>

  <v-card>
    <v-layout>

      <v-navigation-drawer    theme="dark"
                              :width="125"
                              permanent>
        <v-list-item
            class="d-flex justify-center"
            nav
            prepend-avatar="https://randomuser.me/api/portraits/women/75.jpg"
        ></v-list-item>

        <v-divider></v-divider>


        <v-list>
          <v-list-item class="d-flex justify-center" title="Home" :to="{ path: '/' }">
          </v-list-item>

          <v-list-item class="d-flex justify-center" title="Login" value="Login" :to="{ path: '/myAccount/auth/login' }"></v-list-item>

        </v-list>
      </v-navigation-drawer>

      <v-main style="height: 300px"></v-main>
    </v-layout>
  </v-card>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'

import HelloWorld from "@/components/HelloWorld.vue";

export default {
  name: 'App',
  computed: {
    HelloWorld() {
      return HelloWorld
    }
  },

  components: {
    // HelloWorld,
  },

  data: () => ({
    //
  }),
}
</script>
