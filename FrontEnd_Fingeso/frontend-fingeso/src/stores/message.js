// stores/message.js
import { defineStore } from 'pinia'
import axios from 'axios'

export const useMessageStore = defineStore('message', {
  state: () => ({
    chats: [],
    currentChat: null,
    unreadMessagesCount: 0,
  }),

  actions: {
    async buscarChatExistente(emisorId, receptorId, viviendaId) {
      try {
        const response = await axios.get(`http://localhost:8080/api/v1/chat/buscar`, {
          params: { emisorId, receptorId, viviendaId }
        })
        return response.data
      } catch (error) {
        console.error('Error al buscar chat existente:', error)
        return null
      }
    },

    async crearNuevoChat(emisorId, receptorId, viviendaId, message) {
      console.log('Creando nuevo chat con:', { emisorId, receptorId, viviendaId, message })
      try {
        const response = await axios.post(`http://localhost:8080/api/v1/chat/`, {
          // params: {
            emisorId: emisorId,
            receptorId: receptorId,
            viviendaId: viviendaId,
            mensajeInicial: message
          // }
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        })
        return response.data
      } catch (error) {
        console.error('Error al crear nuevo chat:', error)
        throw error
      }
    },

    setCurrentChat(chat) {
      this.currentChat = chat
    },

    async fetchUnreadMessagesCount(userId) {
      this.unreadMessagesCount = 0
      try {
        const response = await axios.get(`http://localhost:8080/api/v1/chat/unread/${userId}`)
        this.unreadMessagesCount = response.data
      } catch (error) {
        console.error('Error al obtener mensajes no leídos:', error)
      }
    },

    async getChats(userId) {
      try {
        const response = await axios.get(`http://localhost:8080/api/v1/chat/`)
        let chatsLocal = response.data


      } catch (error) {
        console.error('Error al obtener mensajes no leídos:', error)
      }
    },

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