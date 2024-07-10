<template>
    <v-app-bar :elevation="1">
      <template v-slot:prepend v-if="userStore.isAuthenticated">
        <v-app-bar-nav-icon @click="toggleDrawer" color="secondary"></v-app-bar-nav-icon>
      </template>

      <v-img
        src="../assets/CasaConnectLogo.png"
        max-height="40"
        max-width="40"
        alt="Casa Connect Logo"
        class="mr-2"
        @click="goToHome"
      ></v-img>
      <v-app-bar-title class="d-flex align-center">
        <span class="mr-2" @click="goToHome">Casa Connect</span>
      </v-app-bar-title>

      <v-spacer></v-spacer>

      <v-btn icon class="mr-2" v-if="userStore.isAuthenticated">
        <v-icon color="secondary">mdi-heart</v-icon>
        <v-tooltip
        activator="parent"
        location="top"
        >Favoritos</v-tooltip>
      </v-btn>

      <v-menu offset-y v-if="userStore.isAuthenticated">
        <template v-slot:activator="{ props }">
          <v-btn icon v-bind="props">
            <v-icon color="secondary">mdi-account</v-icon>
            <v-tooltip
            activator="parent"
            location="top"
            >{{ userStore.userName }}</v-tooltip>
          </v-btn>
        </template>
        <v-list>
          <v-list-item @click="goToProfile">
            <v-icon>mdi-home</v-icon>
            <v-list-item-title class="text-inline">Perfil</v-list-item-title>
          </v-list-item>
          <v-list-item @click="goToFavorites">
            <v-icon>mdi-heart</v-icon>
            <v-list-item-title class="text-inline">Favoritos</v-list-item-title>
          </v-list-item>
          <v-list-item v-if="isVendor" @click="goToPayments">
            <v-icon>mdi-cash</v-icon>
            <v-list-item-title class="text-inline">Pagos</v-list-item-title>
          </v-list-item>
          <v-list-item @click="logout">
            <v-icon>mdi-logout</v-icon>
            <v-list-item-title class="text-inline">Cerrar sesión</v-list-item-title>
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
  const goToHome = () => router.push('/')
  const logout = () => {
    userStore.logout()
    router.push('/')
  }
  </script>

<style scoped>
.v-img {
  cursor: pointer;
}
.v-app-bar-title {
  color: #4CAF50;
  font-weight: bold;
  cursor: pointer;
}
.v-icon {
  display: inline;
  vertical-align: top;
}
.v-icon::after {
  content: " ";
}
.text-inline {
  display: inline;
}
</style>
