<template>

  <div class="text-center" id="titel">
    <h1>The matching soft skill courses</h1>
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
          Matching score
        </th>
        <th class="text-left">
          Select course
        </th>

      </tr>
      </thead>
      <tbody>
      <tr v-for="(percentage, name) in MatchingCourses" :key="name">
        <td>{{ name }}</td>
        <td>{{ percentage }} %</td>
        <v-btn  class="addBtn" append-icon="mdi-check-circle" size="small" color="#12ABDB">
          Select course
        </v-btn>
      </tr>
      </tbody>
    </v-table>
  </div>

</template>

<script>
import SoftSkillCourseService from "@/services/CareerPath/Course/SoftSkillCourseService";
export default {
  name: "ChooseSoftSkillCourse",
  data() {
    return{
      MatchingCourses: [],
    }
  },
  methods: {
    getAllMatchingSoftSkills(){
      let employeeId = localStorage.getItem("id");

      if (localStorage.getItem('auth') !== null) {
        SoftSkillCourseService.GetSoftSkillCourseMatches(employeeId)
            .then((response) => {
              this.MatchingCourses = response.data;
              console.log(this.MatchingCourses)
            })
      } else {
        console.log("Failed to load data in getAllCareerPaths in CareerPath.vue")
      }
    },

  },
  created() {
    this.getAllMatchingSoftSkills();

  }

}
</script>

<style scoped>

.tableDiv {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.table {
  width: 100%;
  border-collapse: collapse;
  /*background-color: #f7f7f9;*/
  box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.1);
  font-size: 14px;
  text-align: left;
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
</style>