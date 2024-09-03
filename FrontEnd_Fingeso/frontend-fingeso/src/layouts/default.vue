<template>
  <AppHeader />
  <v-app>
    <v-main class="container_image">
      <router-view />
    </v-main>
    <AppFooter :showChatDrawer="showChatDrawer" @toggle-chat="toggleChat" />
    <ChatDrawer v-model:showDrawer="showChatDrawer" />
  </v-app>
</template>

<script setup>
import { ref, provide } from 'vue'
import ChatDrawer from '@/components/chat/ChatDrawer.vue'

const showChatDrawer = ref(false)

const toggleChat = () => {
  showChatDrawer.value = !showChatDrawer.value
}

provide('toggleChat', toggleChat)
</script>

<style scoped>

.v-main {
  flex: 1 0 auto;
}

.v-footer {
  flex-shrink: 0;
}

.container_image {
  background-image: url(../assets/Grace-scaled.jpg);
  max-width: 100%;
  max-height: 100%;
  background-color: rgba(255, 255, 255, 0.8);
  background-position-y: center;
  position: relative;
}

.container_image::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(255, 255, 255, 0.7); /* Ajusta el valor alpha (0.5) para controlar la opacidad */
}

.container_image > * {
  position: relative;
  z-index: 1;
}
</style>