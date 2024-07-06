<template>
  <v-app id="inspire">
    <v-main>
      <h1 class="title">Bienvenido a Casa Connect</h1>
      <v-container fill-height>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="4">
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>Iniciar sesión</v-toolbar-title>
              </v-toolbar>
              <v-card-text>
                <v-form>
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
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="loginHandler" :disabled="!isValid">Iniciar sesión</v-btn>
                <router-link to="/register">
                  <v-btn color="secondary">Crear una cuenta</v-btn>
                </router-link>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import axios from 'axios';
export default {
  name: 'Login',
  computed : {
    isValid() {
      return this.email && this.password;
    }
  },
  data() {
    return {
      username: '',
      email: '',
      password: '',
    };
  },
  methods: {
    loginHandler() {
      if(!this.isValid){
        return;
      }
      const data = { 'email': this.email, 'contrasena': this.password }
      console.log("Datos recibidos del formulario: ", data);
      const correo = this.email;
      const clave = this.password;
      axios.get(`http://localhost:8080/api/v1/usuario/${correo}/${clave}`)
        .then(response => {
          console.log("Datos recibidos del backend", response.data);  // Aquí puedes manejar la respuesta del servidor
          if (response.data){
            this.$router.push("/paginaPrincipal");
          }
          else{
            alert("Error al iniciar sesión, las credenciales ingresadas no existen");
          }
        })
        .catch(error => {
          console.error('Error al buscar usuario:', error);
        });
    }
  },
};
</script>

<style scoped>
.login {
  width: 300px;
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
