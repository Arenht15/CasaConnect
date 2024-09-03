<template>
    <v-container>
      <h2>Mis Mensajes</h2>
      <v-list>
        <v-list-item v-for="chat in messageStore.chats" :key="chat.idChat" @click="openChat(chat)">
          <v-list-item-title>
            {{ chat.emisor.nombre === userStore.userName ? chat.receptor.nombre : chat.emisor.nombre }}
          </v-list-item-title>
          <v-list-item-subtitle>
            {{ chat.ultimoMensaje }}
          </v-list-item-subtitle>
          <v-list-item-action>
            <v-badge v-if="!chat.leido" color="error" dot></v-badge>
          </v-list-item-action>
        </v-list-item>
      </v-list>
      <ChatDrawer v-model:showDrawer="showChatDrawer" :chat="selectedChat" />
    </v-container>
  </template>

  <script setup>
  import { ref, onMounted } from 'vue'
  import { useUserStore } from '@/stores/user'
  import { useMessageStore } from '@/stores/message'
  import ChatDrawer from '@/components/chat/ChatDrawer.vue'

  const userStore = useUserStore()
  const messageStore = useMessageStore()

  const showChatDrawer = ref(false)
  const selectedChat = ref(null)

  const openChat = (chat) => {
    selectedChat.value = chat
    showChatDrawer.value = true
  }

  onMounted(async () => {
    // await messageStore.fetchChats(userStore.userId)
  })
  </script>