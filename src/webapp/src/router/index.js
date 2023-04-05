import {createRouter, createWebHashHistory } from 'vue-router'

import HelloWorld  from "@/components/HelloWorld.vue";
import AuthenticationComponent from "@/components/authentication/AuthenticationComponent.vue";

export default createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            name: 'HelloWorld',
            component: HelloWorld
        },
        {
            path: '/myAccount/auth/login',
            name: 'Login',
            component: AuthenticationComponent
        }
    ],


});