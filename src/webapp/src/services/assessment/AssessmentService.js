import axios from "axios";

const MAKE_ASSESSMENT = `http://localhost:8081/api/assessment/assessment/`;
const GET_ASSESSMENT_INFORMATION = `http://localhost:8081/api/assessment/percentages/`;
const ASSESSMENT_Is_MADE = `http://localhost:8081/api/assessment/doesExists/`;


class AssessmentService {
    MakeAssessment(id, data) {
        return axios.post(MAKE_ASSESSMENT + id, data, {headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }

    GetAssessmentInformation(id, data){
        return axios.post(GET_ASSESSMENT_INFORMATION + id, data,{headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }

    CheckIfAssessmentIsMade(id){
        return axios.get(ASSESSMENT_Is_MADE + id,{headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});
    }
}

export default new AssessmentService();

