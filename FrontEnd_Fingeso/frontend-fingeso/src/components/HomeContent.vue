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
            @click="handleMainAction"
          >
            {{ mainButtonText }}
          </v-btn>
          <v-btn
            color="secondary"
            x-large
            outlined
            @click="$router.push('/about')"
          >
            Acerca de Nosotros
          </v-btn>
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

  const mainButtonText = computed(() =>
    userStore.isAuthenticated ? 'Ver Propiedades' : 'Iniciar Sesión'
  )

  const handleMainAction = () => {
    if (userStore.isAuthenticated) {
      router.push('/properties')
    } else {
      router.push('/login')
    }
  }
  </script>