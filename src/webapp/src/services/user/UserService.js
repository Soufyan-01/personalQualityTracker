import axios from "axios";

const GET_ALL_USERS = `http://localhost:8081/api/employee/employees`;
const ADD_SOFT_SKILL_COURSE_TO_EMPLOYEE = `http://localhost:8081/api/employee/addSoftSkillCourse/`;
const ADD_HARD_SKILL_COURSE_TO_EMPLOYEE = `http://localhost:8081/api/employee/addHardSkillCourse/`;



class UserService {
  getAllUsers() {
    return axios.get(GET_ALL_USERS, {headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
  }

  AddSoftSkillCourseToEmployee(employeeId, courseId) {
    return axios.post(ADD_SOFT_SKILL_COURSE_TO_EMPLOYEE + employeeId + "/" + courseId, null, {
      headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}
    });
  }

  AddHardSkillCourseToEmployee(employeeId, courseId) {
    return axios.post(ADD_HARD_SKILL_COURSE_TO_EMPLOYEE + employeeId + "/" + courseId, null, {
      headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}
    });
  }
}

export default new UserService();