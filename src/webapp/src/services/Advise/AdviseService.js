import axios from "axios";

const GET_ADVISE_BY_PERCENTAGE_FOR_PATH = `http://localhost:8081/api/careerPathAdvise/advise/`;

class AdviseService{

    GetAdvisePerPathInPercentage(id){
        return axios.get(GET_ADVISE_BY_PERCENTAGE_FOR_PATH + id,{headers: {Authorization: 'Bearer ' + localStorage.getItem("auth")}});

    }
}

export default new AdviseService();