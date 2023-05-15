<template>
  <div id="chart">
    <apexchart class="chart" type="radar" height="350" :options="chartOptions" :series="series"></apexchart>
  </div>
</template>

<script>
import VueApexCharts from "vue3-apexcharts";
import AssessmentService from "@/services/assessment/AssessmentService";

export default {
  name: "SoftSkillRadarEmployee",
  components: {
    apexchart: VueApexCharts,
  },
  data() {
    return {
      CoursesSoftSkill: [],


      ListOfFolowedCourses: [],

      InterestListFotTable: [],

      series: [{
        name: 'Series 1',
        data: [],
      }],
      chartOptions: {
        chart: {
          height: 350,
          type: 'radar',
        },
        dataLabels: {
          enabled: true
        },
        plotOptions: {
          radar: {
            size: 140,
            polygons: {
              strokeColors: '#e9e9e9',
              fill: {
                colors: ['#f8f8f8', '#fff']
              }
            }
          }
        },
        title: {
          text: ''
        },
        colors: ['#6eb5d2'],
        markers: {
          size: 4,
          colors: ['#fff'],
          strokeColor: '#6eb5d2',
          strokeWidth: 2,
        },
        tooltip: {
          y: {
            formatter: function(val) {
              return val
            }
          }
        },
        xaxis: {
          categories: ['Realistic', 'Investigative', 'Artistic', 'Social', 'Enterprising', 'Conventional']
        },
        yaxis: {
          tickAmount: 2,
          labels: {
            formatter: function(val, i) {
              if (i % 2 === 0) {
                return val
              } else {
                return ''
              }
            }
          }
        }
      },
    }
  },
  methods: {
    getAssessmentResults(){
      let employeeId = localStorage.getItem("id")

      AssessmentService.GetAssessmentResults(employeeId)
          .then((resp) => {

            console.log(resp.data)
            this.HardSkillCourses = resp.data.person.hardSkillCourses;
            this.SoftSkillCourses = resp.data.person.softSkillCourses;

            this.careerPathDescription = resp.data.careerPath.description;
            this.careerPathInterestList = resp.data.careerPath.interestList;

            this.ListOfFolowedCourses = resp.data.person.softSkillCourses;

            // This values can be used for the graphf
            this.ListOfFolowedCourses.forEach((element) => {
              element.interestList.forEach((item) => {
                if (!this.InterestListFotTable.includes(item)) {
                  this.InterestListFotTable.push({ name: item, level: element.courseLevel });
                }
              });
            });


            let count = {
              REALISTIC: 0,
              INVESTIGATIVE: 0,
              ARTISTIC: 0,
              SOCIAL: 0,
              ENTERPRISING: 0,
              CONVENTIONAL: 0
            };

            this.InterestListFotTable.forEach((element) => {
              if (element.name === 'REALISTIC') {
                count.REALISTIC += 1;
                if (count.REALISTIC > 2) {
                  count.REALISTIC = 2;
                }
                if (element.level === 1) {
                  count.REALISTIC += 1;
                  if (count.REALISTIC > 2) {
                    count.REALISTIC = 2;
                  }
                }
              } else if (element.name === 'INVESTIGATIVE') {
                count.INVESTIGATIVE += 1;
                if (count.INVESTIGATIVE > 2) {
                  count.INVESTIGATIVE = 2;
                }
                if (element.level === 1) {
                  count.INVESTIGATIVE += 1;
                  if (count.INVESTIGATIVE > 2) {
                    count.INVESTIGATIVE = 2;
                  }
                }
              } else if (element.name === 'ARTISTIC') {
                count.ARTISTIC += 1;
                if (count.ARTISTIC > 2) {
                  count.ARTISTIC = 2;
                }
                if (element.level === 1) {
                  count.ARTISTIC += 1;
                  if (count.ARTISTIC > 2) {
                    count.ARTISTIC = 2;
                  }
                }
              } else if (element.name === 'SOCIAL') {
                count.SOCIAL += 1;
                if (count.SOCIAL > 2) {
                  count.SOCIAL = 2;
                }
                if (element.level === 1) {
                  count.SOCIAL += 1;
                  if (count.SOCIAL > 2) {
                    count.SOCIAL = 2;
                  }
                }
              } else if (element.name === 'ENTERPRISING') {
                count.ENTERPRISING += 1;
                if (count.ENTERPRISING > 2) {
                  count.ENTERPRISING = 2;
                }
                if (element.level === 1) {
                  count.ENTERPRISING += 1;
                  if (count.ENTERPRISING > 2) {
                    count.ENTERPRISING = 2;
                  }
                }
              } else if (element.name === 'CONVENTIONAL') {
                count.CONVENTIONAL += 1;
                if (count.CONVENTIONAL > 2) {
                  count.CONVENTIONAL = 2;
                }
                if (element.level === 1) {
                  count.CONVENTIONAL += 1;
                  if (count.CONVENTIONAL > 2) {
                    count.CONVENTIONAL = 2;
                  }
                }
              }

            this.series[0].data = Object.values(count);

            });
          })
    },
  },
  created() {
    this.getAssessmentResults();

  }
}
</script>

<style scoped>

.chart{
  width: 600px;
}

</style>