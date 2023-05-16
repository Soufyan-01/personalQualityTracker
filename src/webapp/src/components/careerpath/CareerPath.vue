<template>
  <div>
    <h1 class="headerOne">Welcome ... here you can find the top 3 results of your assessment</h1>
    <div class="grid-container">
      <div class="centerTable">
        <v-table fixed-header height="400px" class="table">
          <thead>
          <tr>
            <th class="text-left">Career path</th>
            <th class="text-left">Description</th>
            <th class="text-left">Interest-fields</th>
            <th class="text-left">Delete career path</th>
            <th></th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="item in CareerPathOptions" :key="item.name">
            <td>{{ item.name }}</td>
            <td>{{ item.description }}</td>
            <td>
              <ul>
                <li v-for="interest in item.interestList" :key="interest">{{ interest }}</li>
              </ul>
            </td>
            <td>
              <v-btn class="mx-2" fab dark size="small" color="error" @click="deleteCareerPath(item.name)">
                Delete
                <v-icon dark>mdi-minus-circle</v-icon>
              </v-btn>
            </td>
          </tr>
          </tbody>
        </v-table>
      </div>
      <div class="addButton">
        <v-row justify="center">
          <v-dialog v-model="dialog" persistent width="1024">
            <template v-slot:activator="{ props }">
<!--              <v-btn v-bind="props" icon="mdi-plus" size="small" color="green"></v-btn>-->
              <v-btn text v-bind="props" size="small" color="green" class="addBtn">
                Add new course
                <v-icon>mdi-plus</v-icon>
              </v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="text-h5">Add a new Career Path</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field label="Career path name*" required v-model="form.name"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field label="Description*" required v-model="form.description"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="8">
                      <v-autocomplete v-model="form.interestList" :items="interestListItems" :item-title="'name'" :item-value="'value'" name="selectedCategory" label="Select categories" multiple></v-autocomplete>
                    </v-col>
                  </v-row>
                </v-container>
                <small>*indicates required field</small>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="error" variant="text" @click="dialog = false">Close</v-btn>
                <v-btn color="green" variant="text" type="submit" @click="addCareerPath">Save</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </div>
    </div>
  </div>
</template>

<script>
import CareerPathService from "@/services/CareerPath/CareerPathService";

export default {
  name: "CareerPath",
  data() {
    return {
      CareerPathOptions: [],
      dialog: false,
      form: {
        name: "",
        description: "",
        interestList: []
      },
      interestListItems: [{ name: 'Realistic', value: 0 },{ name: 'Investigative', value: 1 },{ name: 'Artistic', value: 2 },{ name: 'Social', value: 3 },{ name: 'Enterprising', value: 4 }, { name: 'Conventional', value: 5 }],

    };
  },
  methods: {
    addCareerPath() {
      const data = {
        id: null,
        name: this.form.name,
        description: this.form.description,
        interestList: this.form.interestList
      };

      CareerPathService.GenerateCareerPath(data)
          .then((response) => {
            this.id = response.data;
            window.location.reload();
          }).then((res) => {
        console.log(res)
      }).catch(() => {
        alert("Please make sure u filled in all the fields!")
      })
    },
    getAllCareerPaths() {
      if (localStorage.getItem('auth') !== null) {
        CareerPathService.GetAllCareerPathOptions()
            .then((response) => {
              this.CareerPathOptions = response.data;
            })
      } else {
        console.log("Failed to load data in getAllCareerPaths in CareerPath.vue")
      }
    },
    deleteCareerPath(name) {
      if (localStorage.getItem('auth') !== null) {
        CareerPathService.DeleteCareerPath(name)
            .then((response) => {
              console.log(response)
              this.name = response.data;
              window.location.reload();
            })
            .catch(e => {
              alert(e)
            })
      } else {
        this.$router.push('NoRights')
      }
    },
  },
  created() {
    this.getAllCareerPaths();
  }
}
</script>

<style scoped>

.headerOne {
  background-color: rgb(230, 231, 233);
  color: rgb(14, 160, 211);
  border-radius: 5px;
  padding: 5px;
  margin-bottom: 20px;
  text-align: center;
  font-family: 'Roboto', sans-serif;
}

.table {
  border-collapse: collapse;
  border-spacing: 10px;
  width: 90%;
  border: 1px solid #ddd;
  margin-left: 260px;
  margin-right: 20px;
}

.table th,
.table td {
  text-align: left;
  padding: 8px;
}

.table tr:nth-child(even) {
  background-color: #f2f2f2;
}

.text-left {
  text-align: left;
}

.addButton v-btn {
  margin-right: 10px;
}


.mx-2{
  display: flex;
  justify-content: flex-end;
}

.text-h5 {
  font-size: 18px;
  font-weight: 500;
}

.addBtn{
  margin-top: 20px;
}

</style>