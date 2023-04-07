import axios from 'axios';

const REGISTER_USER = `http://localhost:8081/register`
const Login_USER = `http://localhost:8081/login`
const COMPLETE_USER_ACCOUNT = `http://localhost:8081/api/employee/addEmployee`

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

}

export default new AuthenticationService();