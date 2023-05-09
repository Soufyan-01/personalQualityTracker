import axios from "axios";

const GENERATE_SOFTSKILL_COURSE = `http://localhost:8081/api/softskillcourse/newCourse/`;
const CONNECT_SOFTSKILLCOURSE_WITH_CAREER_PATH = `http://localhost:8081/api/softskillcourse/finishCourseOption/`;



class SoftSkillCourseService{

    GenerateNewSoftSkillCourse(data) {
        return axios.post(GENERATE_SOFTSKILL_COURSE , data, {headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }

    CONNECT_SOFTSKILLCOURSE_WITH_CAREER_PATH(softSkillCourseId, data) {
        return axios.post(CONNECT_SOFTSKILLCOURSE_WITH_CAREER_PATH + softSkillCourseId, data,{headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }

}

export default new SoftSkillCourseService();