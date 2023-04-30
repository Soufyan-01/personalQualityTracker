<template>

  <div class="dialogClass">
  <v-row justify="center">
    <v-dialog
        v-model="dialog"
        width="800"
    >
      <template v-slot:activator="{ props }">
        <v-btn
            color="primary"
            v-bind="props"
        >
          Open Dialog
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="text-h4">Please fill in the assessment</span>
        </v-card-title>

        <v-card-text>What type of activities do you enjoy doing the most?</v-card-text>

        <v-radio-group inline v-model="form.questionOne">
          <v-radio label="Creative" value="0"></v-radio>
          <v-radio label="Analytic" value="1"></v-radio>
          <v-radio label="Social" value="2"></v-radio>
        </v-radio-group>

        <v-card-text>What are your strongest skills and abilities?</v-card-text>

        <v-radio-group inline v-model="form.questionTwo">
          <v-radio label="Interpersonal" value="0"></v-radio>
          <v-radio label="Technical" value="1"></v-radio>
          <v-radio label="Creative" value="2"></v-radio>
        </v-radio-group>

        <v-card-text>What motivates you in your work?</v-card-text>

        <v-radio-group inline v-model="form.questionThree">
          <v-radio label="Achievement" value="0"></v-radio>
          <v-radio label="Helping" value="1"></v-radio>
          <v-radio label="Independence" value="2"></v-radio>
        </v-radio-group>

        <v-card-text>What type of work environment do you prefer?</v-card-text>

        <v-radio-group inline v-model="form.questionFour">
          <v-radio label="Structured" value="0"></v-radio>
          <v-radio label="Flexible" value="1"></v-radio>
          <v-radio label="Supportive" value="2"></v-radio>
        </v-radio-group>

        <v-card-text>What are your personal values and how do they align with your career goals?</v-card-text>

        <v-radio-group inline v-model="form.questionFive">
          <v-radio label="Stability" value="0"></v-radio>
          <v-radio label="Challenge" value="1"></v-radio>
          <v-radio label="Autonomy" value="2"></v-radio>
        </v-radio-group>

        <v-card-text>How do you prefer to interact with others in a work setting?</v-card-text>

        <v-radio-group inline v-model="form.questionSix">
          <v-radio label="Competitive" value="0"></v-radio>
          <v-radio label="Collaborative" value="1"></v-radio>
          <v-radio label="Supportive" value="2"></v-radio>
        </v-radio-group>

        <v-card-text>What type of work schedule do you prefer?</v-card-text>

        <v-radio-group inline v-model="form.questionSeven">
          <v-radio label="Traditional" value="0"></v-radio>
          <v-radio label="Flexible" value="1"></v-radio>
          <v-radio label="Part-time" value="2"></v-radio>
        </v-radio-group>

        <v-card-text>How do you see your career progressing over the next several years?</v-card-text>

        <v-radio-group inline v-model="form.questionEight">
          <v-radio label="Advancement" value="0"></v-radio>
          <v-radio label="Diversity" value="1"></v-radio>
          <v-radio label="Stability" value="2"></v-radio>
        </v-radio-group>


        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="red"
              variant="text"
              @click="dialog = false"
          >
            Cancel
          </v-btn>

          <v-btn type="submit" color="green" variant="text" @click="completeAssessment" >Submit</v-btn>


        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
  </div>

  <div class="getResults">
  <v-btn  append-icon="mdi-check-circle" color="green" @click="getPersonalityOutOfAssessment">
    Get my results
  </v-btn>
  </div>
</template>

<script>
import AssessmentService from "@/services/assessment/AssessmentService";
export default {
  name: "EmployeeAssessment",
  data() {
    return{
      dialog: false,
      form: {
        questionOne: "",
        questionTwo: "",
        questionThree: "",
        questionFour: "",
        questionFive: "",
        questionSix: "",
        questionSeven: "",
        questionEight: ""
      }
    }
  },
  methods: {
    completeAssessment(){
      const data = {
        id: null,
        questionOne: this.form.questionOne,
        questionTwo: this.form.questionTwo,
        questionThree: this.form.questionThree,
        questionFour: this.form.questionFour,
        questionFive: this.form.questionFive,
        questionSix: this.form.questionSix,
        questionSeven: this.form.questionSeven,
        questionEight: this.form.questionEight
      };

      let employeeId = localStorage.getItem("id")

      AssessmentService.MakeAssessment(employeeId, data)
          .then((response) => {
            this.id = response.data;
            console.log(response.data);
            window.location.reload();
          }).then((res) => {
        console.log(res)
      }).catch(() => {
          alert("Please make sure u enter your capgemini email!")
      })
    },
    getPersonalityOutOfAssessment(){

      let employeeId = localStorage.getItem("id")

      AssessmentService.GetAssessmentInformation(employeeId)
          .then((response) => {
            this.id = response.data;
            window.location.reload();
          }).then((res) => {
        console.log(res)
      }).catch(() => {
        alert("Your results are already there!")
      })
    }
  }
}
</script>

<style scoped>

.dialogClass{
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 30px;
}

.getResults {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 50px;
}
</style>