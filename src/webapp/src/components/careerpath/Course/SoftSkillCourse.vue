<template>
  <h1 class="headerOne">Welcome ... here you can find and add Soft Skill courses</h1>

  <div class="grid-container">
  <div class="table-class">
  <v-table
      fixed-header
      height="300px"
  >
    <thead>
    <tr>
      <th class="text-left">
        Course name
      </th>
      <th class="text-left">
        Course description
      </th>
      <th class="text-left">
        Course level
      </th>
      <th class="text-left">
        Course Interests
      </th>
      <th class="text-left">
        For career path
      </th>
    </tr>
    </thead>
    <tbody>
    <tr
        v-for="item in SoftSkillCourses"
        :key="item.name"
        @click="selectedCourse = item; getSoftSkillById(item.id); dialog2 = true"
    >
      <td>{{ item.courseName }}</td>
      <td>{{ item.courseDescription }}</td>
      <td>{{ item.courseLevel === 0 ? 'Beginner' : 'Professional' }}</td>
      <td>
      <ul>
        <li v-for="interest in item.interestList" :key="interest">{{ interest }}</li>
      </ul>
      </td>
      <td>
        <ul>
          <li v-for="careerPath in item.careerPaths" :key="careerPath">{{ careerPath.name }}</li>
        </ul>
      </td>
    </tr>
    </tbody>
  </v-table>
  </div>


    <div class="buttonDiv">
  <v-row justify="center">
    <v-dialog
        v-model="dialog"
        persistent
        width="1024"
    >
      <template v-slot:activator="{ props }">
        <v-btn text v-bind="props" size="small" color="green" class="addBtn">
          Add new course
          <v-icon>mdi-plus</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="text-h5">Course Profile</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col
                  cols="12"
                  sm="6"
                  md="4"
              >
                <v-text-field
                    label="Course name*"
                    required
                    v-model="form.courseName"
                ></v-text-field>
              </v-col>
              <v-col
                  cols="12"
                  sm="6"
                  md="4"
              >
                <v-autocomplete
                    v-model="form.interestList"
                    :items="interestListItems"
                    :item-title="'name'"
                    :item-value="'value'"
                    name="selectedCategory"
                    label="Select categories"
                    multiple
                ></v-autocomplete>

              </v-col>
              <v-col
                  cols="12"
                  sm="6"
                  md="4"
              >
                <v-autocomplete
                    v-model="form.courseLevel"
                    :items="levelItems"
                    :item-title="'name'"
                    :item-value="'value'"
                    name="selectedCategory"
                    label="Select level"
                ></v-autocomplete>

              </v-col>
              <v-col
                  cols="12"
                  sm="12"
                  md="12"
              >
                <v-text-field
                    label="Course description*"
                    required
                    v-model="form.courseDescription"
                ></v-text-field>
              </v-col>

            </v-row>
          </v-container>
          <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="error"
              variant="text"
              @click="dialog = false"
          >
            Close
          </v-btn>
          <v-btn
              color="green"
              variant="text"
              type="submit"
              @click="addCourse"
          >
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>

<!--  -->

  <v-dialog v-model="dialog2" max-width="500">
    <v-card>
      <v-card-title>
        Course Details
      </v-card-title>

<!--            <v-card-text>-->
<!--              {{ selectedCourse }}-->
<!--            </v-card-text>-->

      <p class="textP">In this chart you can find the interest fields that are hit by this course</p>


      <div id="chart">
        <apexchart type="radar" height="350" :options="chartOptions" :series="series">
        </apexchart>
      </div>

      <p class="textP">Know you now the interest fields that are hit you can select the career paths that fit with this course:</p>

      <v-card-actions>
                <v-autocomplete
                    v-model="form.careerPath"
                    :items="CareerPathOptions"
                    :item-title="'name'"
                    :item-value="'id'"
                    label="Select career path"
                    multiple
                ></v-autocomplete>

        <v-btn color="error" text @click="dialog2 = false">
          Close
        </v-btn>

        <v-btn color="#0EA0D3" text @click="postCareerPaths(selectedCourse.id)">
          Connect course
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
    </div>

  </div>

</template>

<script>
import SoftSkillCourseService from "@/services/CareerPath/Course/SoftSkillCourseService";
import CareerPathService from "@/services/CareerPath/CareerPathService";
import VueApexCharts from "vue3-apexcharts";

export default {
  name: "SoftSkillCourse",
  components: {
    apexchart: VueApexCharts,
  },
  data() {
    return {
      selectedCourse: [],
      dialog2: false,
      SoftSkillCourses: [],
      CareerPathOptions: [],
      dialog: false,
      form: {
        courseName: "",
        courseDescription: "",
        courseLevel: "",
        interestList: [],
        careerPath: []
      },
      levelItems: [{ name: 'Beginner', value: 1 },{ name: 'Professional', value: 2 }],
      interestListItems: [{ name: 'Realistic', value: 1 },{ name: 'Investigative', value: 2 },{ name: 'Artistic', value: 3 },{ name: 'Social', value: 4 },{ name: 'Enterprising', value: 5 }, { name: 'Conventional', value: 6 }],


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
          text: 'Interest fields tackled by this course, you can'
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
    getSoftSkillById(courseId) {
      if (localStorage.getItem('auth') !== null) {
        let count = {
          REALISTIC: 0,
          INVESTIGATIVE: 0,
          ARTISTIC: 0,
          SOCIAL: 0,
          ENTERPRISING: 0,
          CONVENTIONAL: 0
        };

        SoftSkillCourseService.GetSoftSkillCourseById(courseId)
            .then((response) => {
              this.CoursesSoftSkill = response.data;
              console.log(response.data.interestList)
              this.CoursesSoftSkill.interestList.forEach((information) => {
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
              });
              this.series[0].data = Object.values(count);
            });
      }
    },


    getAllCareerPaths(){
      if (localStorage.getItem('auth') !== null) {
        CareerPathService.GetAllCareerPathOptions()
            .then((response) => {
              this.CareerPathOptions = response.data;
              console.log(this.CareerPathOptions)
            })
      } else {
        console.log("Failed to load data in getAllCareerPaths in CareerPath.vue")
      }
    },

    postCareerPaths(softSkillCourseId){
      const data = {
        id: null,
        careerPath: this.form.careerPath
      };
      SoftSkillCourseService.ConnectSoftSkillWithPath(softSkillCourseId, data)
          .then((response)=>{
            this.id = response.data;
            window.location.reload();
          })
    },

    getAllSoftSkillCourses() {
      if (localStorage.getItem('auth') !== null) {
        SoftSkillCourseService.GetAllSoftSkillCourses()
            .then((response) => {
              this.SoftSkillCourses = response.data;
            })
      } else {
        console.log("Failed to load data in getAllCareerPaths in CareerPath.vue")
      }
    },
    addCourse(){
      const data = {
        id: null,
        courseName: this.form.courseName,
        courseDescription: this.form.courseDescription,
        courseLevel: this.courseLevel,
        interestList: this.form.interestList
      };

      SoftSkillCourseService.GenerateNewSoftSkillCourse(data)
          .then((response) => {
            this.id = response.data;
            window.location.reload();
          }).then((res) => {
        console.log(res)
      }).catch(() => {
        alert("Please make sure u filled in all the fields!")
      })
    }

  },
  created() {
    this.getAllSoftSkillCourses();
    this.getAllCareerPaths();

    // this.getSoftSkillById();


  }
}
</script>

<style scoped>

.grid-container {
  display: grid;
  grid-template-columns: 1fr;
  grid-gap: 20px;
  margin: 10px 75px; /* Set the margin to create grid gap on left and right */
}

.table-class {
  background-color: #f5f5f5;
  border-radius: 10px;
  box-shadow: 0 5px 10px rgba(0,0,0,0.1);
  overflow: hidden;
  margin-left: 180px;
}

.v-table th {
  text-align: left;
  background-color: #fafafa;
  font-weight: bold;
  border-bottom: 1px solid #f1f1f1;
  padding: 10px;
}

.v-table td {
  padding: 10px;
  border-bottom: 1px solid #f1f1f1;
}

.v-table tr:hover {
  background-color: #f9f9f9;
}

.v-table tr:nth-child(even) {
  background-color: #f2f2f2;
}

.headerOne {
  background-color: rgb(230, 231, 233);
  color: rgb(14, 160, 211);
  border-radius: 5px;
  padding: 5px;
  margin-bottom: 20px;
  text-align: center;
  font-family: 'Roboto', sans-serif; /* Change the font family to Roboto */
}

tr:hover {
  cursor: pointer;
  background-color: #F1F2F4;
}

:root {
  --main-column-width: 1000px;
}

.grid-container {
  display: grid;
  grid-template-columns: var(--main-column-width) 1fr;
  gap: 10px;
}


.textP{
  margin-left: 15px;
  margin-right: 15px;
}

.addBtn{
  margin-top: 20px;
}

</style>