// stores/message.js
import { defineStore } from 'pinia'

export const useMessageStore = defineStore('message', {
  state: () => ({
    messages: [],
    hasUnreadMessages: false,
  }),

  actions: {
    sendMessage(text) {
      this.messages.push({
        id: Date.now(),
        text,
        sender: 'Usuario',
        timestamp: new Date(),
      })
    },

    receiveMessage(text) {
      this.messages.push({
        id: Date.now(),
        text,
        sender: 'Bot',
        timestamp: new Date(),
      })
      this.hasUnreadMessages = true
    },

    initializeChat() {
      this.receiveMessage('¡Bienvenido a CasaConect! ¿En qué puedo ayudarte?')
    },

    markAllAsRead() {
      this.hasUnreadMessages = false
    },

    clearMessages() {
      this.messages = []
      this.hasUnreadMessages = false
    },
  },
})