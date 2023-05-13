import axios from "axios";

const GENERATE_HARDSKILL_COURSE = `http://localhost:8081/api/hardskillcourse/newCourse/`;
const CONNECT_HARDSKILLCOURSE_WITH_CAREER_PATH = `http://localhost:8081/api/hardskillcourse/finishCourseOption/`;
const GET_ALL_HARD_SKILL_COURSES = `http://localhost:8081/api/hardskillcourse/allCourses/`;
const GET_HARD_SKILL_COURSE_BY_ID = `http://localhost:8081/api/hardskillcourse/hardskillcourses/`;
const GET_HARD_SKILL_MATCHES = `http://localhost:8081/api/assessment/matchingcourseshardSkill/`;
const GET_HARD_SKILL_BY_NAME = `http://localhost:8081/api/hardskillcourse/hardskillcourseByName/`;


// @PostMapping("/addSoftHardCourse/{employeeId}/{courseId}")



class HardSkillCourseService{

    GenerateNewHardSkillCourse(data) {
        return axios.post(GENERATE_HARDSKILL_COURSE , data, {headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }

    ConnectHardSkillWithPath(softSkillCourseId, data) {
        return axios.post(CONNECT_HARDSKILLCOURSE_WITH_CAREER_PATH + softSkillCourseId, data,{headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }

    GetAllHardSkillCourses(){
        return axios.get(GET_ALL_HARD_SKILL_COURSES, {headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }

    GetHardSkillCourseById(hardSkillCourseId){
        return axios.get(GET_HARD_SKILL_COURSE_BY_ID + hardSkillCourseId, {headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }

    GetHardCourseMatches(employeeId){
        return axios.get(GET_HARD_SKILL_MATCHES + employeeId, {headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }

    GetHardSkillCourseByName(name){
        return axios.get(GET_HARD_SKILL_BY_NAME + name, {headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }

}

export default new HardSkillCourseService();