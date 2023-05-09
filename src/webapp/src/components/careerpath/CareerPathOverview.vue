<template>
  <div id="chart">
    <apexchart type="bar" height="350" :options="chartOptions" :series="series"></apexchart>
  </div>

  <div class="tableDiv">
  <v-table
      fixed-header
      height="200px"
      class="table"
  >
    <thead>
    <tr>
      <th class="text-left">
        Career path
      </th>
      <th class="text-left">
        Select this career path
      </th>

    </tr>
    </thead>
    <tbody>
    <tr v-for="item in CareerPathOptions2" :key="item">
      <td class="text-left">{{ item.name }}</td>
      <td class="text-left">
        <v-btn class="mx-2" fab dark size="small" color="#0EA0D3" @click="makeCareerPathChoice(item.id)">
          Select
          <v-icon dark>mdi-plus-circle</v-icon>
        </v-btn>
      </td>
    </tr>
    </tbody>
  </v-table>
  </div>
</template>

<script>
import VueApexCharts from "vue3-apexcharts";
import AssessmentService from "@/services/assessment/AssessmentService";
import AdviseService from "@/services/Advise/AdviseService";
import CareerPathService from "@/services/CareerPath/CareerPathService";

export default {
  name: "CareerPathOverview",
  components: {
    apexchart: VueApexCharts,
  },
  data() {
    return {
      CareerPathOptions2: [],
      Percentages: [],
      Paths: [],

      series: [{
        data: []
      }],
      chartOptions: {
        chart: {
          height: 350,
          type: 'bar',
          events: {
            click: function (chart, w, e) {
              // console.log(chart, w, e)
            }
          }
        },
        colors: ["#43A047", "#FB8C00", "#E53935", "#00897B", "#FFB300", "#F4511E"],
        plotOptions: {
          bar: {
            columnWidth: '45%',
            distributed: true,
          }
        },
        dataLabels: {
          enabled: false
        },
        legend: {
          show: false
        },
        xaxis: {
          categories: [],
          labels: {
            style: {
              colors: ["#43A047", "#FB8C00", "#E53935", "#00897B", "#FFB300", "#F4511E"],
              fontSize: '12px'
            }
          }
        }
      },


    }
  }, methods: {

    getAllCareerPaths2() {
      if (localStorage.getItem('auth') !== null) {
        CareerPathService.GetAllCareerPathOptions()
            .then((response) => {
              this.CareerPathOptions2 = response.data;
              console.log(response.data)
            })
      } else {
        console.log("Failed to load data in getAllCareerPaths in CareerPath.vue")
      }
    },

    makeCareerPathChoice(careerPathChoiceId) {
      let employeeId = localStorage.getItem("id");

      CareerPathService.PostCareerPathChoice(employeeId, careerPathChoiceId).then(() => {

        alert("Your preference is saved, go to the course page to see the recommended courses");
      })
    },

    getPercentagesForTable() {
      let employeeId = localStorage.getItem("id");

      AdviseService.GetAdvisePerPathInPercentage(employeeId).then((resp) => {

        for (let key in resp.data) {
          this.Percentages.push({ key: key, value: resp.data[key] });
        }
      })
    },
    generateReport() {
      AssessmentService.GetCareerPaths().then(items => {
        this.data = [
          {
            name: "Number of items",
            data: items.data.map(r => r.name),
          },
        ];

        for (let i = 0; i < this.Percentages.length; i++) {
          console.log(this.Percentages[i].key + ": " + this.Percentages[i].value);

          const category = items.data.find(r => r.name === this.Percentages[i].key);
          if (category) {
            this.series[0].data.push(this.Percentages[i].value);
          }
        }

        this.updateAxis(items.data.map(r => r.name));
      }, error => {
        console.log(error);
      });
    },
    updateAxis(data) {
      this.chartOptions = {
        ...this.chartOptions,
        xaxis: {
          categories: data,
        }
      };
    },

  },

  created() {
    this.getPercentagesForTable();
    this.getAllCareerPaths2();
    this.generateReport();
  }

}
</script>

<style scoped>

.tableDiv{
  display: flex;
  justify-content: center; /* center horizontally */
  align-items: center; /* center vertically */
}

.table{
  width: 400px;
  border: 1px solid black;
  border-radius: 5px;
}

table tr td:first-child {
  text-align: left;
}
</style>