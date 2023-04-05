import axios from 'axios';

const REGISTER_USER = `http://localhost:8081/register`
const Login_USER = `http://localhost:8081/login`

class AuthenticationService {

    registerUser(data) {
        return axios.post(REGISTER_USER, data);
    }

    loginUser(data){
        return axios.post(Login_USER, data)
    }

}

export default new AuthenticationService();