<template>
  <v-app id="inspire">
    <v-main>
      <h1 class="title">Bienvenido a Casa Connect</h1>
      <v-container fill-height>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="10" md="6">
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>Iniciar sesión</v-toolbar-title>
                <v-spacer></v-spacer>
                <v-btn icon @click="$router.push('/')">
                  <v-icon>mdi-close</v-icon>
                </v-btn>
              </v-toolbar>
              <v-card-text>
                <v-alert
                  v-if="messageInfo.show"
                  :type="messageInfo.type"
                  dismissible
                  @click:close="messageInfo.show = false"
                >
                  {{ messageInfo.text }}
                </v-alert>
                <v-form>
                  <v-text-field
                    name="email"
                    label="Correo electrónico"
                    placeholder="ejemplo@ejemplo.com"
                    type="email"
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

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'

const router = useRouter()
const userStore = useUserStore()

// const username = ref('')
const email = ref('')
const password = ref('')
const messageInfo = ref({ show: false, text: '', type: 'info' })

const isValid = computed(() =>
  email.value && password.value
)

const loginHandler = async () => {
  try {
    const success = await userStore.login(email.value, password.value)
    if (success) {
      router.push('/')
    }
  } catch (error) {
    // alert.value = { show: true, type: 'error', message: error.message }
    showMessage(error.message, 'error')
  }
}

const showMessage = (text, type) => {
  messageInfo.value = { show: true, text, type }
  setTimeout(() => messageInfo.value = { show: false, text: "", type: "" }, 2000)
}
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
