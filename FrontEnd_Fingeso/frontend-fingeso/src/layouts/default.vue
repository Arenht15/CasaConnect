<template>
  <AppHeader />
  <v-app>
    <v-main>
      <router-view />
    </v-main>
    <AppFooter :showChatDrawer="showChatDrawer" @toggle-chat="toggleChat" />
    <ChatDrawer v-model:showDrawer="showChatDrawer" />
  </v-app>
</template>

<script setup>
import { ref } from 'vue'
import ChatDrawer from '@/components/chat/ChatDrawer.vue'

const showChatDrawer = ref(false)

const toggleChat = () => {
  showChatDrawer.value = !showChatDrawer.value
}
</script>

<style scoped>

.v-main {
  flex: 1 0 auto;
}

.v-footer {
  flex-shrink: 0;
}
</style>