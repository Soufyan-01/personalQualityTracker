<template>
  <div id="chart">
    <apexchart type="radar" height="350" :options="chartOptions" :series="series"></apexchart>
  </div>
</template>

<script>
import VueApexCharts from "vue3-apexcharts";
import SoftSkillCourseService from "@/services/CareerPath/Course/SoftSkillCourseService";

export default {
  name: "SoftSkillRadarChart",
  components: {
    apexchart: VueApexCharts,
  },
  data() {
    return {
      CoursesSoftSkill: [],

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
          text: 'Radar with Polygon Fill'
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
    getAllSoftSkillCourses() {
      if (localStorage.getItem('auth') !== null) {

        let count = {
          REALISTIC: 0,
          INVESTIGATIVE: 0,
          ARTISTIC: 0,
          SOCIAL: 0,
          ENTERPRISING: 0,
          CONVENTIONAL: 0
        };

        SoftSkillCourseService.GetAllSoftSkillCourses()
            .then((response) => {
              this.CoursesSoftSkill = response.data;
              console.log(response.data)
              this.CoursesSoftSkill.forEach((course) => {
                course.interestList.forEach((information) => {
                  if (information === 'REALISTIC') {
                    count.REALISTIC += 1;
                  } else if (information === 'INVESTIGATIVE') {
                    count.INVESTIGATIVE += 1;
                  } else if (information === 'ARTISTIC') {
                    count.ARTISTIC += 1;
                  } else if (information === 'SOCIAL') {
                    count.SOCIAL += 1;
                  } else if (information === 'ENTERPRISING') {
                    count.ENTERPRISING += 1;
                  } else if (information === 'CONVENTIONAL') {
                    count.CONVENTIONAL += 1;
                  }
                })
              });
              this.series[0].data = Object.values(count);
            });

      }
    }
},
  created() {
    this.getAllSoftSkillCourses();

  }
}
</script>

<style scoped>

</style>