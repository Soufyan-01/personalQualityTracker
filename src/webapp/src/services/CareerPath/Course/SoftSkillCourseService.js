import axios from "axios";

const GENERATE_SOFTSKILL_COURSE = `http://localhost:8081/api/softskillcourse/newCourse/`;
const CONNECT_SOFTSKILLCOURSE_WITH_CAREER_PATH = `http://localhost:8081/api/softskillcourse/finishCourseOption/`;
const GET_ALL_SOFT_SKILL_COURSES = `http://localhost:8081/api/softskillcourse/allCourses/`;
const GET_SOFT_SKILL_COURSE_BY_ID = `http://localhost:8081/api/softskillcourse/softskillcourses/`


class SoftSkillCourseService{

    GenerateNewSoftSkillCourse(data) {
        return axios.post(GENERATE_SOFTSKILL_COURSE , data, {headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }

    ConnectSoftSkillWithPath(softSkillCourseId, data) {
        return axios.post(CONNECT_SOFTSKILLCOURSE_WITH_CAREER_PATH + softSkillCourseId, data,{headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }

    GetAllSoftSkillCourses(){
        return axios.get(GET_ALL_SOFT_SKILL_COURSES, {headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }

    GetSoftSkillCourseById(softSkillCourseId){
        return axios.get(GET_SOFT_SKILL_COURSE_BY_ID + softSkillCourseId, {headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }

}

export default new SoftSkillCourseService();