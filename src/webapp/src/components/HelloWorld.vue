<template>
  <div class="home">
    <div class="header">
      <h1>Welcome {{personName}} to my Website</h1>
      <p>Discover new courses and plan your career path</p>
    </div>
    <div class="main">
      <div class="course-list">
        <div class="course">
          <img class="imageL" style="width: 45%;" :src="logoTwo" alt="assessment">
          <h3>Assessment</h3>
          <p>Before you can use all the functions of the application, you need to finish the assessment</p>
          <a href="#/capgemini/assessment" class="btn">Go to assessment</a>
        </div>
        <div class="course">
          <img class="imageL" style="width: 60%;" :src="logo" alt="assessmentResults">
          <h3>Assessment results</h3>
          <p>See your assessment results, and select your preferred career path based on out advise</p>
          <a href="#/capgemini/assessment/results" class="btn">Go to my results</a>
        </div>
        <div class="course">
          <img class="imageL" style="width: 55%;" :src="logoThree" alt="softSkill">
          <h3>Soft Skill Courses</h3>
          <p>See all the Soft Skill courses that fit your career path based on the assessment</p>
          <a href="#" class="btn">Go to courses</a>
        </div>
        <div class="course">
          <img class="imageL" style="width: 30%;" :src="logoFour" alt="hardSkill">
          <h3>Hard Skill Courses</h3>
          <p>See all the Hard Skill courses that fit your career path based on the assessment</p>
          <a href="#" class="btn">Go to courses</a>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import pictureOne from '../assets/assessmentResult.jpg';
import pictureTwo from '../assets/assessment.jpg';
import pictureThree from '../assets/softSkill.jpg';
import pictureFour from '../assets/hardSkill.jpg';



export default {
  name: 'HelloWorld',

  data: () => ({
    logo: pictureOne,
    logoTwo: pictureTwo,
    logoThree: pictureThree,
    logoFour: pictureFour,
    personName: "",


    ecosystem: [
      {
        text: 'vuetify-loader',
        href: 'https://github.com/vuetifyjs/vuetify-loader/tree/next',
      },
      {
        text: 'github',
        href: 'https://github.com/vuetifyjs/vuetify/tree/next',
      },
      {
        text: 'awesome-vuetify',
        href: 'https://github.com/vuetifyjs/awesome-vuetify',
      },
    ],
    importantLinks: [
      {
        text: 'Chat',
        href: 'https://community.vuetifyjs.com',
      },
      {
        text: 'Made with Vuetify',
        href: 'https://madewithvuejs.com/vuetify',
      },
      {
        text: 'Twitter',
        href: 'https://twitter.com/vuetifyjs',
      },
      {
        text: 'Articles',
        href: 'https://medium.com/vuetify',
      },
    ],
    whatsNext: [
      {
        text: 'Explore components',
        href: 'https://vuetifyjs.com',
      },
      {
        text: 'Roadmap',
        href: 'https://vuetifyjs.com/introduction/roadmap/',
      },
      {
        text: 'Frequently Asked Questions',
        href: 'https://vuetifyjs.com/getting-started/frequently-asked-questions',
      },
    ],
  }),
  methods: {
    getPersonName(){
      let email = localStorage.getItem("email");
      let parts = email.split("@");
      this.personName = parts[0];
    }

  },
  created() {
    this.getPersonName();
  }
}
</script>

<style scoped>
.home {
display: flex;
flex-direction: column;
min-height: 100vh;
}

.header {
background-color: #0EA0D3;
color: #fff;
padding: 50px;
text-align: center;
}

.main {
flex: 1;
padding: 50px;
}

.course-list {
display: grid;
grid-template-columns: repeat(4, 1fr);
gap: 20px;
  padding-left: 50px;
}

.course {
border: 1px solid #ddd;
border-radius: 10px;
padding: 20px;
text-align: center;
}

.course img {
width: 100%;
max-width: 300px;
height: auto;
margin-bottom: 20px;
}

.course h3 {
font-size: 24px;
margin-bottom: 10px;
}

.course p {
margin-bottom: 20px;
}

.btn {
display: inline-block;
padding: 10px 20px;
background-color: #0EA0D3;
color: #fff;
border-radius: 5px;
text-decoration: none;
transition: background-color 0.3s;
}

.btn:hover {
background-color: #057191;
}

.imageL{
  height: 50px;
}

/*.footer {*/
/*background-color: #ddd;*/
/*padding: 20px;*/
/*text-align: center;*/
/*}*/
</style>