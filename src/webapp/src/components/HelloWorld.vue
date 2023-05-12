<!--<template>-->
<!--      <v-card>-->
<!--        <v-card-text>-->
<!--          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.-->
<!--        </v-card-text>-->
<!--        <v-card-actions>-->
<!--          <v-btn color="primary" block @click="dialog = false">Close Dialog</v-btn>-->
<!--        </v-card-actions>-->
<!--      </v-card>-->
<!--</template>-->

<template>
  <div class="home">
    <div class="header">
      <h1>Welcome to my Website</h1>
      <p>Discover new courses and plan your career path</p>
    </div>
    <div class="main">
      <h2>Featured Courses</h2>
      <div class="course-list">
        <div class="course">
          <img src="course1.jpg" alt="Course 1">
          <h3>Course 1</h3>
          <p>Description of course 1</p>
          <a href="#" class="btn">Learn More</a>
        </div>
        <div class="course">
          <img src="course2.jpg" alt="Course 2">
          <h3>Course 2</h3>
          <p>Description of course 2</p>
          <a href="#" class="btn">Learn More</a>
        </div>
        <div class="course">
          <img src="course3.jpg" alt="Course 3">
          <h3>Course 3</h3>
          <p>Description of course 3</p>
          <a href="#" class="btn">Learn More</a>
        </div>
      </div>
    </div>
    <div class="footer">
      <p>© 2023 MyWebsite. All rights reserved.</p>
    </div>
  </div>
</template>


<script>

export default {
  name: 'HelloWorld',

  data: () => ({
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
grid-template-columns: repeat(3, 1fr);
gap: 30px;
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

.footer {
background-color: #ddd;
padding: 20px;
text-align: center;
}
</style>