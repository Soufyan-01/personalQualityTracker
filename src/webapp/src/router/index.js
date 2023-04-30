import {createRouter, createWebHashHistory } from 'vue-router'

import HelloWorld  from "@/components/HelloWorld.vue";
import AuthenticationComponent from "@/components/authentication/AuthenticationComponent.vue";
import LoginComponent from "@/components/authentication/LoginComponent.vue";
import AllUsers from "@/components/streamLead/allUsers.vue";
import EmployeeAssessment from "@/components/assessment/EmployeeAssessment.vue";

export default createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            name: 'HelloWorld',
            component: HelloWorld
        },
        {
            path: '/myAccount/auth/register',
            name: 'Register',
            component: AuthenticationComponent
        },
        {
            path: '/myAccount/auth/login',
            name: 'Login',
            component: LoginComponent
        },
        {
            path: '/capgemini/allUsers',
            name: 'Users',
            component: AllUsers
        },
        {
            path: '/capgemini/assessment',
            name: 'EmployeeAssessment',
            component: EmployeeAssessment
        }
    ],


});