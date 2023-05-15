<template>
  <div class="text-center" id="titel">
    <h1>My profile</h1>
  </div>

  <div class="container">
    <div class="grid-item-1" style="background-color: #f2f2f2;">

      <div>
        <h2>Your Career Path: {{ careerPathName }}</h2>
        <p>{{ careerPathDescription }}</p>
        <br>
        <h4>The interest fields of this career Path:</h4>
        <ul>
          <li class="listItem" v-for="interest in careerPathInterestList" :key="interest.id">{{ interest }}</li>
        </ul>
      </div>
      <br>
    </div>



    <div class="grid-item-2" style="background-color: #e6e6e6;">
      <div>
        <h2>{{ `${name} ${surname}` }}</h2>
        <h3>{{ email }}</h3>
        <br>
    </div>
  </div>




    <div class="grid-item-3" style="background-color: #d9d9d9;">
      <h4>Your assessment results:</h4>

      <div id="chart">
        <apexchart type="donut" :options="chartOptions" :series="series"></apexchart>
      </div>

    </div>


    <div class="grid-item-4" style="background-color: #e5e3e3;">
      <h4>You have learned the following soft skill:</h4>

      <div class="center-wrapper">
        <SoftSkillRadarEmployee></SoftSkillRadarEmployee>
      </div>
    </div>


    <div class="grid-item-5" style="background-color: #bfbfbf;">
      <h4>Your courses:</h4>

      <v-table
          fixed-header
          height="350px"
          class="table"
      >
        <thead>
        <tr>
          <th class="text-left">
            Course
          </th>
          <th class="text-left">
            Type
          </th>

        </tr>
        </thead>
        <tbody>
        <tr v-for="item in SoftSkillCourses" :key="item">
          <td class="text-left">{{ item.courseName }}</td>
          <td class="text-left">Soft skill</td>
        </tr>
        <tr v-for="item in HardSkillCourses" :key="item">
          <td class="text-left">{{ item.courseName }}</td>
          <td class="text-left">Hard skill</td>
        </tr>
        </tbody>
      </v-table>

    </div>
  </div>




</template>

<script>
import UserService from "@/services/user/UserService";
import VueApexCharts from "vue3-apexcharts";
import SoftSkillRadarEmployee from "@/components/careerpath/Course/SoftSkillRadarEmployee.vue";

import AssessmentService from "@/services/assessment/AssessmentService";
import CareerPathOverview from "@/components/careerpath/CareerPathOverview.vue";
import {it} from "vuetify/locale";

export default {
  name: "MyInformation",
  components: {
    SoftSkillRadarEmployee,
    apexchart: VueApexCharts,
  },
  data() {
    return {
      careerPathName: "",
      careerPathDescription: "",
      careerPathInterestList: [],
      name: "",
      surname: "",
      email: "",

      ListOfFolowedCourses: [],

      InterestListFotTable: [],

      HardSkillCourses: [],
      SoftSkillCourses: [],

      series: [],
      chartOptions: {
        chart: {
          type: 'donut',
        },
        labels: [],

        responsive: [{
          breakpoint: 480,
          options: {
            chart: {
              width: 200
            },
            legend: {
              position: 'bottom'
            }
          }
        }],
        colors:["#43A047", "#FB8C00", "#E53935"]

      },
    };
  },
  methods: {
    getAssessmentResults(){
      let employeeId = localStorage.getItem("id")

      AssessmentService.GetAssessmentResults(employeeId)
          .then((resp) => {

            this.HardSkillCourses = resp.data.person.hardSkillCourses;
            this.SoftSkillCourses = resp.data.person.softSkillCourses;

            this.careerPathName = resp.data.careerPath.name;
            this.careerPathDescription = resp.data.careerPath.description;
            this.careerPathInterestList = resp.data.careerPath.interestList;

            this.name = resp.data.person.name;
            this.surname = resp.data.person.surname;
            this.email = resp.data.person.email;

            this.ListOfFolowedCourses = resp.data.person.softSkillCourses;

            this.ListOfFolowedCourses.forEach((element) => {
              element.interestList.forEach((item) => {
                if (!this.InterestListFotTable.includes(item)) {
                  this.InterestListFotTable.push(item);
                }
              });
            });


            let interestOne = resp.data.interestOne;
            let percentageOne = resp.data.percentageInterestOne;

            let interestTwo = resp.data.interestTwo;
            let percentageTwo = resp.data.percentageInterestTwo;


            let interestThree = resp.data.interestThree;
            let percentageThree = resp.data.percentageInterestThree;

            this.series.push(percentageOne);
            this.series.push(percentageTwo);
            this.series.push(percentageThree);

            this.chartOptions.labels.push(interestOne);
            this.chartOptions.labels.push(interestTwo);
            this.chartOptions.labels.push(interestThree);
          })
    },
  },
  created() {
    this.getAssessmentResults();
  }
}
</script>

<style scoped>

.container {
  display: grid;
  grid-template-columns: 60% 40%;
  grid-template-rows: 1fr 1fr;
  grid-gap: 10px;
  margin-left: 100px;
  margin-right: 20px;
}

.grid-item-1 {
  grid-row: 1 / span 2;
  height: 400px;
}

.grid-item-2 {
  grid-row: 1 / span 1;
  height: 100px;
}

.grid-item-3 {
  grid-row: 2 / span 1;
  margin-top: -100px;
}

.grid-item-4 {
  grid-column: 1 / span 1;
  height: 400px;
}

.grid-item-5 {
  grid-column: 2 / span 1;
  height: 400px;
}


#titel{
  background-color: #0EA0D3;
  color: #fff;
  padding: 25px;
  text-align: center;
  height: 100px;
  margin-bottom: 20px;
}

#chart {
  width: 400px;

}

.listItem{
  margin-left: 20px;
}

.center-wrapper {
  width: 100%;
  margin-left: 30px;
}

.table th {
  background-color: #f5f5f5;
  border: 1px solid #dddddd;
  padding: 8px;
  text-align: left;
}

.table td {
  border: 1px solid #dddddd;
  padding: 8px;
}

.table tr:nth-child(even) {
  background-color: #f9f9f9;
}

</style>