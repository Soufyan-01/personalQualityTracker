<template>
  <h1 class="headerOne">Welcome ... here you can find and add Soft Skill courses</h1>


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
        @click="selectedCourse = item; dialog2 = true"
    >
      <td>{{ item.courseName }}</td>
      <td>{{ item.courseDescription }}</td>
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

<!--  Hier verder afmaken divjes in grid plaatsen
buttons list toevoegen moet dynamisch met careerPath chouices de value moet
de id zijn-->

  <v-row justify="center">
    <v-dialog
        v-model="dialog"
        persistent
        width="1024"
    >
      <template v-slot:activator="{ props }">
        <v-btn
            color="primary"
            v-bind="props"
        >
          Add course
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

<!--      <v-card-text>-->
<!--        {{ selectedCourse.id }}-->
<!--      </v-card-text>-->
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

</template>

<script>
import SoftSkillCourseService from "@/services/CareerPath/Course/SoftSkillCourseService";
import CareerPathService from "@/services/CareerPath/CareerPathService";

// Ergens get doen van careerpath gegevens

export default {
  name: "SoftSkillCourse",
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

    }
  },
  methods: {
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

  }
}
</script>

<style scoped>

.table-class{
  /*width: 700px;*/
  margin-left: 260px;
  /*margin-right: 250px;*/
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
}

</style>