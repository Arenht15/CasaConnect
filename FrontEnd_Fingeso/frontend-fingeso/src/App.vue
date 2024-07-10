<template>
  <v-app>
    <AppDrawer />
    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script setup>
import AppDrawer from '@/components/common/AppDrawer.vue'
</script>