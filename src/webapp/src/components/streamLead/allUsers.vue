<template>
  <h1 class="headerOne">All stream leads</h1>

  <div class="centerTable">
  <v-table>
    <thead>
    <tr>
      <th class="text-left">
        Name
      </th>
      <th class="text-left">
        Surname
      </th>
      <th class="text-left">
        Edit
      </th>
      <th class="text-left">
        Delete
      </th>
    </tr>
    </thead>
    <tbody>
    <tr
        v-for="item in Employees"
        :key="item.name"
    >
      <td>{{ item.name }}</td>
      <td>{{ item.surname }}</td>
      <td>
        <v-btn class="mx-2" fab dark size="small" color="blue-lighten-3">
          Edit
          <v-icon dark>mdi-wrench</v-icon>
        </v-btn>
      </td>
      <td>
        <v-btn class="mx-2" fab dark size="small" color="error">
          Delete
          <v-icon dark>mdi-minus-circle</v-icon>
        </v-btn>
      </td>
    </tr>
    </tbody>
  </v-table>
  </div>
</template>

<script>
import UserService from "@/services/user/UserService";

export default {
  name: "allUsers",
  data() {
    return {
      Employees: []
    };
  },
  methods: {
    getEmployees() {
      if(localStorage.getItem('auth') !== null) {
        UserService.getAllUsers()
            .then((response) => {
              this.Employees = response.data;
              console.log(this.Employees);
            })
      } else {
        console.log("Gaat wat mis")
      }
    },
    // deleteEmployee(id) {
    //   if (localStorage.getItem('auth') !== null) {
    //     EmployeeService.deleteEmployee(id)
    //         .then((response) => {
    //           this.id = response.data;
    //           window.location.reload();
    //         })
    //         .catch(e => {
    //           alert(e)
    //         })
    //   } else {
    //     this.$router.push('NoRights')
    //   }
    // },
  },
  created() {
    this.getEmployees();
  }
}
</script>

<style scoped>

.centerTable{

  width: 500px;
  margin-left: 250px;
}

.headerOne{
  background-color: #0EA0D3;
  color: #fff;
  padding: 25px;
  text-align: center;
  height: 100px;
  margin-bottom: 20px;
}

.centerTable {
  text-align: center;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th, td {
  padding: 10px;
}

thead th {
  background-color: #f2f2f2;
}

tbody tr:nth-child(even) {
  background-color: #f9f9f9;
}



</style>