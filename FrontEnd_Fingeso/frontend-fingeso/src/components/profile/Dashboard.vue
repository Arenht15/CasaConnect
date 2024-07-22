<template>
    <v-container>
      <v-tabs v-model="activeTab" color="primary" align-tabs="center">
        <v-tab value="profile">Perfil</v-tab>
        <v-tab value="properties" v-if="userStore.isVendor">Mis Propiedades</v-tab>
        <v-tab value="favorites" v-if="!userStore.isVendor">Favoritos</v-tab>
        <v-tab value="transactions">Pagos</v-tab>
        <v-tab value="messages">Mensajes</v-tab>
      </v-tabs>

      <v-window v-model="activeTab">
        <v-window-item value="profile">
          <ProfileForm />
        </v-window-item>
        <v-window-item value="properties">
          <VendedorViviendas/>
        </v-window-item>
        <v-window-item value="favorites">
          <h2>Mis Favoritos</h2>
          <!-- Aquí irá el componente de favoritos -->
        </v-window-item>
        <v-window-item value="transactions">
          <h2>Mis Pagos</h2>
          <!-- Aquí irá el componente de pagos/transacciones -->
        </v-window-item>
        <v-window-item value="messages">
          <h2>Mis Mensajes</h2>
          <!-- Aquí irá el componente de mensajes -->
        </v-window-item>
      </v-window>
    </v-container>
</template>

<script setup>
import { ref } from 'vue'
import { useUserStore } from '@/stores/user'
import ProfileForm from './ProfileForm.vue'
import VendedorViviendas from './VendedorViviendas.vue'

const userStore = useUserStore()
const activeTab = ref('profile')
</script>