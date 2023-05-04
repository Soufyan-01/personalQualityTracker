import axios from 'axios';

const REGISTER_USER = `http://localhost:8081/register`
const Login_USER = `http://localhost:8081/login`
const COMPLETE_USER_ACCOUNT = `http://localhost:8081/api/employee/addEmployee`
const GET_EMPLOYEE_ID = `http://localhost:8081/api/employee/employeeId/`
const GET_STREAMLEAD_ID = `http://localhost:8081/api/employee/streamLead/`


class AuthenticationService {

    registerUser(data) {
        return axios.post(REGISTER_USER, data);
    }

    loginUser(data){
        return axios.post(Login_USER, data)
    }

    completeUserAccount(data){
        return axios.post(COMPLETE_USER_ACCOUNT, data, {headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}})
    }

    getEmployeeId(email) {
        return axios.get(GET_EMPLOYEE_ID + email, {headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }

    getStreamLeadId(email) {
        return axios.get(GET_STREAMLEAD_ID + email, {headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }

}

export default new AuthenticationService();