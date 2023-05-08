<template>
  <div id="chart">
    <apexchart type="bar" height="350" :options="chartOptions" :series="series"></apexchart>
  </div>
</template>

<script>
import VueApexCharts from "vue3-apexcharts";
import AssessmentService from "@/services/assessment/AssessmentService";
import AdviseService from "@/services/Advise/AdviseService";

export default {
  name: "CareerPathOverview",
  components: {
    apexchart: VueApexCharts,
  },
  data() {
    return {
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

    this.generateReport();
  }

}
</script>

<style scoped>

</style>