<template>
  <v-app id="inspire">
    <v-main>
      <h1 class="title">Se parte de nuestra Comunidad</h1>
      <v-container fill-height>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="6" lg="4">
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>Registrarse</v-toolbar-title>
              </v-toolbar>
              <v-card-text>
                <v-form @submit.prevent="handleRegister">
                  <v-text-field
                    name="username"
                    label="Nombre de usuario"
                    type="text"
                    v-model="username"
                    :rules="[v => !!v || 'Campo requerido']"
                  ></v-text-field>
                  <v-text-field
                    name="email"
                    label="Correo electrónico"
                    placeholder="ejemplo@ejemplo.com"
                    type="text"
                    v-model="email"
                    :rules="[v => !!v || 'Campo requerido']"
                  ></v-text-field>
                  <v-text-field
                    id="password"
                    name="password"
                    label="Contraseña"
                    type="password"
                    v-model="password"
                    :rules="[v => !!v || 'Campo requerido']"
                  ></v-text-field>
                  <v-expansion-panels>
                    <v-expansion-panel>
                      <v-expansion-panel-header>Tipo de usuario</v-expansion-panel-header>
                      <v-expansion-panel-content>
                        <v-radio-group v-model="userType">
                          <v-radio label="Vendedor" value=1></v-radio>
                          <v-radio label="Comprador" value=2></v-radio>
                        </v-radio-group>
                      </v-expansion-panel-content>
                    </v-expansion-panel>
                  </v-expansion-panels>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="handleRegister" :disabled="!isValid">Registrarse</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import axios from "axios";
export default {
  name: 'Register',
  computed: {
    isValid() {
      return this.username && this.email && this.password && this.userType;
    },
  },
  data() {
    return {
      username: '',
      email: '',
      password: '',
      userType: '',
    };
  },
  methods: {
      async handleRegister() {
        if(!this.isValid){
          this.errorMessage = "Por favor, rellene todos los campos";
          return;
        }
        const data = {
          'nombre': this.username,
          'email': this.email,
          'contrasena': this.password,
          'rol': this.userType
        }
        console.log(data);
        try {
          //VER CONEXIÓN CON AXIOS
          const res = await axios.post("http://localhost:8080/api/v1/usuario/", data)
          console.log(res)
          await this.$router.push("/login");
        }
        catch(e) {
          console.log(e.message)
        }
      },
  },
};
</script>

<style scoped>
.register {
  width: 10000px;
  margin: 0 auto;
}
.input-group {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.input-group label {
  margin-right: 10px;
}

#inspire{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}.title{
   margin-top: 30px;
   text-align: center;
   color: #4CAF50;
 }
</style>
