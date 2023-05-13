<template>
  <div class="text-center" id="titel">
    <h1>The matching soft skill courses</h1>
  </div>

  <div class="table-container">
    <div class="table-header">
      <h2>Matching Soft Skill Courses</h2>
    </div>
    <div class="table-body">
      <v-table fixed-header height="200px" class="table">
        <thead>
        <tr>
          <th class="text-left">Career path</th>
          <th class="text-left">Matching score</th>
          <th class="text-left">Select course</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(percentage, name) in MatchingCourses" :key="name">
          <td>{{ name }}</td>
          <td>{{ percentage }} %</td>
          <td>
            <v-btn
                class="addBtn"
                append-icon="mdi-check-circle"
                size="small"
                color="#12ABDB"
                @click="addSoftSkillCourseToEmployee(getSoftSkillCourseId(name))"
            >
              Select course
            </v-btn>
          </td>
        </tr>
        </tbody>
      </v-table>
    </div>

    <div class="table-header">
      <h2>Matching Hard Skill Courses</h2>
    </div>
    <div class="table-body">
      <v-table fixed-header height="200px" class="table">
        <thead>
        <tr>
          <th class="text-left">Career path</th>
          <th class="text-left">Select course</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(value, key) in MatchingCoursesHardSkill" :key="key">
          <td>{{ key }}</td>
          <td>
            <v-btn
                class="addBtn"
                append-icon="mdi-check-circle"
                size="small"
                color="#12ABDB"
                @click="addHardSkillCourseToEmployee(getHardSkillCourseId(key))"
            >
              Select course
            </v-btn>
          </td>
        </tr>
        </tbody>
      </v-table>
    </div>
  </div>
</template>

<script>
import UserService from "@/services/user/UserService";
import SoftSkillCourseService from "@/services/CareerPath/Course/SoftSkillCourseService";
import HardSkillCourseService from "@/services/CareerPath/Course/HardSkillCourseService";
export default {
  name: "ChooseCourse",
  data() {
    return{
      MatchingCourses: [],
      softSkillCourseIds: {},

      MatchingCoursesHardSkill: [],

    }
  },
  methods: {
    getAllMatchingSoftSkills(){
      let employeeId = localStorage.getItem("id");

      if (localStorage.getItem('auth') !== null) {
        SoftSkillCourseService.GetSoftSkillCourseMatches(employeeId)
            .then((response) => {
              this.MatchingCourses = response.data;
            })
      } else {
        console.log("Failed to load data in getAllCareerPaths in CareerPath.vue")
      }
    },
    getSoftSkillCourseId(name) {
      if (localStorage.getItem('auth') !== null) {
        SoftSkillCourseService.GetSoftSkillCourseByName(name)
            .then((response) => {
              const softSkillCourse = response.data;
              console.log(softSkillCourse);

              this.addSoftSkillCourseToEmployee(softSkillCourse);
            })
            .catch((error) => {
              console.log(`Error getting soft skill course by name: ${error}`);
            });
      } else {
        console.log("Failed to load data in getAllCareerPaths in CareerPath.vue");
      }
    },


    addSoftSkillCourseToEmployee(id) {
      let employeeId = localStorage.getItem("id");

      UserService.AddSoftSkillCourseToEmployee(employeeId, id)
          .then((response) => {
            // Optional: Do something with the response if needed
            console.log(`Added soft skill course with ID ${id} to employee ${employeeId}`);
          })
          .catch((error) => {
            console.log(`Error adding soft skill course with ID ${id} to employee ${employeeId}: ${error}`);
          });
    },



    getAllMatchingHardSkills(){
      let employeeId = localStorage.getItem("id");

      if (localStorage.getItem('auth') !== null) {
        HardSkillCourseService.GetHardCourseMatches(employeeId)
            .then((response) => {
              this.MatchingCoursesHardSkill = response.data;
              console.log(response)
            })
      } else {
        console.log("Failed to load data in getAllCareerPaths in CareerPath.vue")
      }
    },
    getHardSkillCourseId(name) {
      if (localStorage.getItem('auth') !== null) {
        HardSkillCourseService.GetHardSkillCourseByName(name)
            .then((response) => {
              const softSkillCourse = response.data;
              console.log(softSkillCourse);

              this.addHardSkillCourseToEmployee(softSkillCourse);
            })
            .catch((error) => {
              console.log(`Error getting soft skill course by name: ${error}`);
            });
      } else {
        console.log("Failed to load data in getAllCareerPaths in CareerPath.vue");
      }
    },


    addHardSkillCourseToEmployee(id) {
      let employeeId = localStorage.getItem("id");

      UserService.AddHardSkillCourseToEmployee(employeeId, id)
          .then((response) => {
            // Optional: Do something with the response if needed
            console.log(`Added soft skill course with ID ${id} to employee ${employeeId}`);
          })
          .catch((error) => {
            console.log(`Error adding soft skill course with ID ${id} to employee ${employeeId}: ${error}`);
          });
    },





  },
  created() {
    this.getAllMatchingSoftSkills();
    this.getAllMatchingHardSkills();

  }

}
</script>

<style scoped>


.table {
  width: 100%;
  border-collapse: collapse;
  /*background-color: #f7f7f9;*/
  box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.1);
  font-size: 14px;
  text-align: left;
  margin-left: 100px;
}

.table th {
  padding: 15px;
  font-weight: bold;
  /*background-color: #fafafa;*/
  border-bottom: 1px solid #eee;
}

.table td {
  padding: 15px;
  border-bottom: 1px solid #eee;
}

.table tr:last-child td {
  border-bottom: none;
}

.table tr:hover {
  background-color: #f5f5f5;
}

.table tr:nth-child(even) {
  background-color: #f2f2f2;
}

#titel{
  background-color: #0EA0D3;
  color: #fff;
  padding: 25px;
  text-align: center;
  height: 100px;
  margin-bottom: 20px;
}

.addBtn{
  margin-top: 10px;
}


 .table-container {
   display: flex;
   flex-direction: column;
   align-items: center;
   margin-top: 20px;
 }

.table-header {
  margin-bottom: 10px;
}

.table-body {
  width: 80%;
  margin-bottom: 20px;
}

.table {
  border: 1px solid #ccc;
  border-collapse: collapse;
  text-align: left;
  width: 100%;
}

.table td,
.table th {
  padding: 12px 15px;
  border: 1px solid #ccc;
}

.addBtn {
  margin: 0 0 0 5px;
  min-width: 80px;
  white-space: nowrap;
}

</style>