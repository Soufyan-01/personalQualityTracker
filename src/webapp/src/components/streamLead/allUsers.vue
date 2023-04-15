<template>
  <h1>T</h1>
<!--  <div class="EmployeeInformation">-->
<!--    <template class="employee" v-for="employee in Employees" v-bind:key="employee.id">-->
<!--      <article class="employee">-->
<!--        <router-link :to="'/Employee/' + employee.id"-->
<!--                     custom-->
<!--                     v-slot="{ navigate }">-->
<!--          <div class="employee-content" @click="navigate">-->

<!--            <p class="email">{{ employee.email }}</p>-->

<!--          </div>-->
<!--        </router-link>-->

<!--      </article>-->
<!--    </template>-->
<!--  </div>-->
</template>

<script>
// import UserService from "@/services/user/UserService";

export default {
  name: "allUsers",
  data() {
    return {
      Employees: []
    };
  },
  methods: {
  //   getEmployees() {
  //     if(localStorage.getItem('auth') !== null) {
  //       UserService.getAllUsers()
  //           .then((response) => {
  //             this.Employees = response.data;
  //           })
  //     } else {
  //       this.$router.push('/login');
  //     }
  //   }
  },
  mounted() {
    this.getEmployees();
  }
}
</script>

<style scoped>

</style>