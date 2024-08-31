<template>
  <v-container class="fill-height">
    <v-row align="center" justify="center">
      <v-col cols="12" md="8" lg="6" class="text-center">
        <v-img
          src="../assets/CasaConnectLogo.png"
          alt="Casa Connect Logo"
          class="mx-auto mb-6"
          max-width="200"
        ></v-img>
        <h1 class="text-h2 font-weight-bold mb-4">Bienvenido a Casa Connect</h1>
        <p class="text-h5 mb-6">
          Encuentra tu hogar ideal o la mejor inversión inmobiliaria con nuestra plataforma inteligente.
        </p>
        <v-row justify="center">
          <v-col cols="12" sm="6" class="d-flex justify-center">
            <v-btn
              v-if="!userStore.isAuthenticated"
              color="primary"
              x-large
              class="mr-4"
              @click="handleMainAction"
            >
              Iniciar Sesión
            </v-btn>
          </v-col>
        </v-row>
        <v-row justify="center" class="mt-6" v-if="userStore.isAuthenticated">
          <v-col cols="12" md="5" lg="4" class="d-flex justify-center">
            <div class="info-box">
              <v-img
                src="../assets/Arriendos1.jpg"
                alt="Arriendos"
                class="mx-auto mb-4"
                max-width="100%"
              ></v-img>
              <h2 class="text-h5">Información Izquierda</h2>
              <p>Contenido de la caja izquierda.</p>
              <v-btn
                color="primary"
                @click="$router.push('/properties')"
              >
                Revisar arriendos
              </v-btn>
            </div>
          </v-col>
          <v-col cols="12" md="5" lg="4" class="d-flex justify-center">
            <div class="info-box">
              <v-img
                src="../assets/Grace-scaled.jpg"
                alt="Viviendas"
                class="mx-auto mb-4"
                max-width="100%"
              ></v-img>
              <h2 class="text-h5">Información Derecha</h2>
              <p>Contenido de la caja derecha.</p>
              <v-btn
                color="primary"
                @click="$router.push('/properties')"
              >
                Revisar viviendas
              </v-btn>
            </div>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import { computed } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'

const router = useRouter()
const userStore = useUserStore()

const handleMainAction = () => {
  if (userStore.isAuthenticated) {
    router.push('/properties')
  } else {
    router.push('/login')
  }
}
</script>

<style scoped>
.info-box {
  border: 1px solid #ccc;
  padding: 10px; /* Aumentar el padding para agrandar las cajas */
  border-radius: 8px;
  text-align: center;
  width: 100%;
}
</style>