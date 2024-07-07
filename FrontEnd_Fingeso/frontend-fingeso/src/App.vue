<template>
  <v-app>

    <v-navigation-drawer v-model="userStore.drawerOpen" temporary>
      <v-list>
        <v-list-item class="drawer-header">
          <v-list-item-title class="text-h6">
            Menú
          </v-list-item-title>
          <template v-slot:append>
            <v-btn icon @click="userStore.toggleDrawer" color="primary">
              <v-icon color="secondary">mdi-close</v-icon>
            </v-btn>
          </template>
        </v-list-item>

        <v-divider></v-divider>

        <v-list-item @click="goToHome">
          <v-icon>mdi-home</v-icon>
          <v-list-item-title class="text-inline">Inicio</v-list-item-title>
        </v-list-item>
        <v-list-item @click="goToFavorites">
          <v-icon>mdi-heart</v-icon>
          <v-list-item-title class="text-inline">Favoritos</v-list-item-title>
        </v-list-item>
        <v-list-item @click="goToProfile">
          <v-icon>mdi-account</v-icon>
          <v-list-item-title class="text-inline">Perfil</v-list-item-title>
        </v-list-item>
        <v-list-item v-if="userStore.isVendor" @click="goToPayments">
          <v-icon>mdi-cash</v-icon>
          <v-list-item-title class="text-inline">Pagos</v-list-item-title>
        </v-list-item>
        <v-list-item @click="logout">
          <v-icon>mdi-logout</v-icon>
          <v-list-item-title class="text-inline">Cerrar sesión</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'

const router = useRouter()
const userStore = useUserStore()

const goToHome = () => router.push('/')
const goToProfile = () => router.push('/profile')
const goToFavorites = () => router.push('/favorites')
const goToPayments = () => router.push('/payments')
const logout = () => {
  userStore.logout()
  userStore.toggleDrawer()
  router.push('/')
}
</script>

<style scoped>
.drawer-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px;
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
