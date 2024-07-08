<template>
    <v-navigation-drawer
      :modelValue="showDrawer"
      @update:modelValue="$emit('update:showDrawer', $event)"
      location="right"
      temporary
      width="400"
      class="d-flex flex-column"
    >
      <v-card class="flex-grow-1 d-flex flex-column">
        <v-card-title class="py-2">
          <span class="text-h6">CasaConect Chat</span>
          <v-spacer></v-spacer>
          <v-btn icon @click="closeChat">
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-card-title>
        <v-card-text class="flex-grow-1 overflow-y-auto">
          <v-list>
            <v-list-item v-for="message in messages" :key="message.id">
              <template v-slot:prepend>
                <v-avatar color="primary" size="32">
                  {{ message.sender[0] }}
                </v-avatar>
              </template>
              <v-list-item-title>{{ message.text }}</v-list-item-title>
              <v-list-item-subtitle>{{ formatTimestamp(message.timestamp) }}</v-list-item-subtitle>
            </v-list-item>
          </v-list>
        </v-card-text>
        <v-card-actions class="pa-4">
          <v-text-field
            v-model="newMessage"
            label="Escribe un mensaje"
            @keyup.enter="sendMessage"
            hide-details
            density="compact"
            variant="outlined"
          ></v-text-field>
          <v-btn color="primary" class="ml-2" @click="sendMessage">Enviar</v-btn>
        </v-card-actions>
      </v-card>
    </v-navigation-drawer>
  </template>

  <script setup>
  import { ref, computed, onMounted, watch } from 'vue'
  import { useUserStore } from '@/stores/user'
  import { useMessageStore } from '@/stores/message'

  const props = defineProps({
    showDrawer: Boolean
  })

  const emit = defineEmits(['update:showDrawer'])

  const userStore = useUserStore()
  const messageStore = useMessageStore()

  const newMessage = ref('')

  const isAuthenticated = computed(() => userStore.isAuthenticated)
  const messages = computed(() => messageStore.messages)

  const sendMessage = () => {
    if (newMessage.value.trim() && isAuthenticated.value) {
      messageStore.sendMessage(newMessage.value)
      newMessage.value = ''
      // Aquí podrías agregar lógica para enviar el mensaje al backend
      // y luego recibir una respuesta del bot
      setTimeout(() => {
        messageStore.receiveMessage('Gracias por tu mensaje. ¿En qué más puedo ayudarte?')
      }, 1000)
    }
  }

  const toggleChat = () => {
    if (isAuthenticated.value) {
      emit('update:showDrawer', !props.showDrawer)
      if (!props.showDrawer && messages.value.length === 0) {
        messageStore.initializeChat()
      }
      if (!props.showDrawer) {
        messageStore.markAllAsRead()
      }
    }
  }

  const closeChat = () => {
    emit('update:showDrawer', false)
    messageStore.markAllAsRead()
  }

  const formatTimestamp = (timestamp) => {
    return new Date(timestamp).toLocaleTimeString()
  }

  onMounted(() => {
    if (isAuthenticated.value && messageStore.hasUnreadMessages) {
      emit('update:showDrawer', true)
    }
  })

  watch(() => messageStore.hasUnreadMessages, (newValue) => {
    if (newValue && isAuthenticated.value) {
      emit('update:showDrawer', true)
    }
  })

  defineExpose({ toggleChat })
  </script>

  <style scoped>
  .v-card-text {
    max-height: calc(100vh - 120px);
  }
  </style>