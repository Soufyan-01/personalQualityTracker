<template>

  <div class="text-center" id="titel">
    <h1>Your assessment</h1>
  </div>

  <div class="card-wrapper">
  <v-card title="Your Assessment" text=" Welcome!

  Please note that in order to access all the functionalities, you need to have completed your assessment. If you have not yet completed your assessment, please do so before proceeding to ensure that you have access to all the features and capabilities available. Thank you!

    Welcome to your assessment! This assessment is designed to help you identify your interests and provide advice on the types of activities or careers that may align with your passions. By gaining a better understanding of your interests, you can make more informed decisions about your future path.

    Throughout this assessment, you will be asked a series of questions about your preferences and interests. Please answer each question honestly and to the best of your ability. There are no right or wrong answers, and the results of this assessment will be kept confidential.

    At the end of the assessment, you will receive personalized advice based on your interests and preferences. This advice can help guide you in making decisions about your education, career, or hobbies. So, take your time, answer each question thoughtfully, and let's get started!" variant="tonal">
  </v-card>
  </div>

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
          Make your assessment
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
            localStorage.setItem("assessment", "true");
            window.location.reload();
          }).then((res) => {
        console.log(res)
      }).catch(() => {
          alert("Please make sure u filled in all the fields!")
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
        window.location.href = "http://localhost:8080/#/capgemini/assessment/results";
      })
    }
  }
}
</script>

<style scoped>
.card-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  max-width: 500px; /* set maximum width */
  margin: 0 auto; /* center horizontally with auto margins */
  border-radius: 10px;
  padding: 20px;
}

.dialogClass{
  display: flex;
  justify-content: center;
  align-items: center;
  max-width: 500px; /* set maximum width */
  margin: 0 auto; /* center horizontally with auto margins */
}

.getResults {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 50px;
}

#titel{
  background-color: rgb(230,231,233);
  color: rgb(14,160,211);
  border-radius: 5px;
  padding: 5px;
  margin-bottom: 20px;
  text-align: center;
  font-family: 'Roboto', sans-serif; /* Change the font family to Roboto */
}
</style>