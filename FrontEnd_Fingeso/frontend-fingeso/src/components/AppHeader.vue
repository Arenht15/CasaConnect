<template>
    <v-app-bar :elevation="1">
      <template v-slot:prepend>
        <v-app-bar-nav-icon @click="toggleDrawer" color="secondary"></v-app-bar-nav-icon>
      </template>

      <v-img
        src="../assets/CasaConnectLogo.png"
        max-height="40"
        max-width="40"
        alt="Casa Connect Logo"
        class="mr-2"
      ></v-img>
      <v-app-bar-title class="d-flex align-center">
        <span class="mr-2">Casa Connect</span>
      </v-app-bar-title>

      <v-spacer></v-spacer>

      <v-btn icon class="mr-2">
        <v-icon color="secondary">mdi-star</v-icon>
      </v-btn>

      <v-menu offset-y>
        <template v-slot:activator="{ props }">
          <v-btn icon v-bind="props">
            <v-icon color="secondary">mdi-account</v-icon>
          </v-btn>
        </template>
        <v-list>
          <v-list-item @click="goToProfile">
            <v-list-item-title>Perfil</v-list-item-title>
          </v-list-item>
          <v-list-item @click="goToFavorites">
            <v-list-item-title>Favoritos</v-list-item-title>
          </v-list-item>
          <v-list-item v-if="isVendor" @click="goToPayments">
            <v-list-item-title>Pagos</v-list-item-title>
          </v-list-item>
          <v-list-item @click="logout">
            <v-list-item-title>Cerrar sesión</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
  </template>

  <script setup>
  import { computed } from 'vue'
  import { useRouter } from 'vue-router'
  import { useUserStore } from '@/stores/user' // Asegúrate de que la ruta sea correcta

  const router = useRouter()
  const userStore = useUserStore()

  const isVendor = computed(() => userStore.isVendor)

  const toggleDrawer = () => {
    userStore.toggleDrawer()
  }

  const goToProfile = () => router.push('/profile')
  const goToFavorites = () => router.push('/favorites')
  const goToPayments = () => router.push('/payments')
  const logout = () => {
    userStore.logout()
    router.push('/login')
  }
  </script>

  <style scoped>
  .v-app-bar-title {
    color: #4CAF50;
    font-weight: bold;
  }

  </style>