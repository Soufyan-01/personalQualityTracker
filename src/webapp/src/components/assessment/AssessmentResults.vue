<template>
  <h1 class="headerOne">Welcome ... here you can find the results of your assessment</h1>

  <div class="grid-container">
    <div class="grid-item">
      <h3>Personality's</h3>
      <p>The results of the assessment indicate that the individual possesses three key personality traits. These traits are clearly represented in the diagram provided with the assessment results. The assessment can provide a visual representation of the individual's unique personality profile.</p>

      <div id="chart">
        <apexchart type="donut" :options="chartOptions" :series="series"></apexchart>
      </div>
    </div>

    <div class="grid-item">
      <h3>Career path choices</h3>
      <p>Our assessment has identified your unique personality profile, including three key traits that are clearly represented in the diagram provided with your results. This visual representation can help you gain a better understanding of your strengths and tendencies, which can guide you in making informed decisions about your future career path.</p>

      <CareerPathOverview/>
    </div>
  </div>

</template>

<script>
import VueApexCharts from "vue3-apexcharts";
import AssessmentService from "@/services/assessment/AssessmentService";
import CareerPathOverview from "@/components/careerpath/CareerPathOverview.vue";

export default {
  name: "AssessmentResults",
  components: {
    apexchart: VueApexCharts,
    CareerPathOverview,
  },
  data() {
    return {
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
    }
  },
  methods: {
    getAssessmentResults(){
      let employeeId = localStorage.getItem("id")

      AssessmentService.GetAssessmentResults(employeeId)
          .then((resp) => {

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
    }

  },

  created() {
    this.getAssessmentResults();
  }

}
</script>

<style scoped>

.grid-container {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 20px;
  margin: 10px 75px; /* Set the margin to create grid gap on left and right */
}

.grid-item {
  background-color: rgb(14,173,220);
  padding: 20px;
  text-align: center;
  border-radius: 10px;
  color: rgb(230,231,233);
}


.headerOne{
  background-color: rgb(230,231,233);
  color: rgb(14,160,211);
  border-radius: 5px;
  padding: 5px;
  margin-bottom: 20px;
  text-align: center;
  font-family: 'Roboto', sans-serif; /* Change the font family to Roboto */

}


#chart {
  margin: auto;
  width: 400px;
  padding: 10px;
}
</style>