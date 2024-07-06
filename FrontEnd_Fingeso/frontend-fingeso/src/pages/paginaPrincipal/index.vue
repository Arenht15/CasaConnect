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
        <v-btn
          color="primary"
          x-large
          class="mr-4"
          @click="$router.push('/propiedades')"
        >
          Ver Propiedades
        </v-btn>
        <v-btn
          color="secondary"
          x-large
          outlined
          @click="$router.push('/acercaDe')"
        >
          Acerca de Nosotros
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()
</script>

<style scoped>
.fill-height {
  height: calc(100vh - 64px); /* Ajusta esto según la altura de tu AppBar */
}
</style>