import axios from "axios";

const GENERATE_CAREER_PATH = `http://localhost:8081/api/careerPath/careerPath/`;
const DELETE_CAREER_PATH = `http://localhost:8081/api/careerPath/deleteCareerPath/`;
const GET_ALL_CAREER_PATH_OPTIONS = `http://localhost:8081/api/careerPath/paths/`;
const CHOOSE_CAREER_PATH_FOR_EMPLOYEE = `http://localhost:8081/api/employee/addCareerPathToEmployee/`


class CareerPathService{

    GenerateCareerPath(data) {
        return axios.post(GENERATE_CAREER_PATH , data, {headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }

    DeleteCareerPath(data) {
        return axios.delete(DELETE_CAREER_PATH + data, {headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }

    GetAllCareerPathOptions(){
        return axios.get(GET_ALL_CAREER_PATH_OPTIONS, {headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }

    PostCareerPathChoice(employeeId, careerPathId) {
        const headers = {
            Authorization: 'Bearer ' + localStorage.getItem("auth")
        };
        return axios.post(CHOOSE_CAREER_PATH_FOR_EMPLOYEE + employeeId + "/" + careerPathId, {}, { headers });
    }


}

export default new CareerPathService();