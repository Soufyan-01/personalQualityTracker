import {createRouter, createWebHashHistory } from 'vue-router'

import HelloWorld  from "@/components/HelloWorld.vue";
import AuthenticationComponent from "@/components/authentication/AuthenticationComponent.vue";
import LoginComponent from "@/components/authentication/LoginComponent.vue";
import AllUsers from "@/components/streamLead/allUsers.vue";
import EmployeeAssessment from "@/components/assessment/EmployeeAssessment.vue";
import AssessmentResults from "@/components/assessment/AssessmentResults.vue";
import CareerPath from "@/components/careerpath/CareerPath.vue";
import SoftSkillCourse from "@/components/careerpath/Course/SoftSkillCourse.vue";
import SoftSkillRadarChart from "@/components/careerpath/Course/SoftSkillRadarChart.vue";
import HardSkillCourse from "@/components/careerpath/Course/HardSkillCourse.vue";
import ChooseCourse from "@/components/careerpath/ChooseCourse.vue";
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
        },
        {
            path: '/capgemini/assessment/results',
            name: 'AssessmentResults',
            component: AssessmentResults
        },
        {
            path: '/capgemini/careerPathOption',
            name: 'CareerPathOption',
            component: CareerPath
        },
        {
            path: '/capgemini/softSkillCourse',
            name: 'SoftSkillCourse',
            component: SoftSkillCourse
        },
        {
            path: '/capgemini/softSkillRadarChart',
            name: 'softSkillRadarChart',
            component: SoftSkillRadarChart
        },
        {
            path: '/capgemini/hardSkillCourse',
            name: 'HardSkillCourse',
            component: HardSkillCourse
        },
        {
            path: '/capgemini/chooseCourse',
            name: 'ChooseCourse',
            component: ChooseCourse
        },
    ],


});