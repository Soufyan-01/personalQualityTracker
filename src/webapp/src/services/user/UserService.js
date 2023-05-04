import axios from "axios";

const GET_ALL_USERS = `http://localhost:8081/api/employee/employees`;

class UserService {
  getAllUsers() {
    return axios.get(GET_ALL_USERS, {headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
  }
}

export default new UserService();