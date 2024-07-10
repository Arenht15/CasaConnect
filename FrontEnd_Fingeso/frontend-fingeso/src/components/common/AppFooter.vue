<template>
  <v-footer app color="primary" dark>
    <v-container>
      <v-row justify="space-between" align="center">
        <v-col cols="12" sm="6">
          <div class="footer-contact-info">
            <span class="white--text">
              <v-icon>mdi-email</v-icon> CasaConect@example.cl
            </span>
            <span class="white--text ml-4">
              <v-icon>mdi-phone</v-icon> +1234567890
            </span>
            <span class="white--text ml-4">
              <v-icon>mdi-instagram</v-icon> @CasaConect
            </span>
          </div>
        </v-col>
        <v-col cols="12" sm="6" class="text-sm-right mt-4 mt-sm-0">
          <div class="footer-links">
            <router-link to="/financing" v-if="userStore.isAuthenticated">Financiamiento</router-link>
            <router-link to="/about" class="ml-4">Acerca de nosotros</router-link>
          </div>
        </v-col>
      </v-row>
    </v-container>
    <v-btn
      v-show="isAuthenticated && !showChatDrawer"
      @click="toggleChat"
      class="chat-button"
      icon
    >
      <v-icon>mdi-chat</v-icon>
      <v-badge
        v-if="hasUnreadMessages"
        color="red"
        dot
        offset-x="10"
        offset-y="10"
      ></v-badge>
    </v-btn>
  </v-footer>
</template>


<script setup>
import { computed } from 'vue'
import { useUserStore } from '@/stores/user' // Asegúrate de que la ruta sea correcta
import { useMessageStore } from '@/stores/message'

const props = defineProps({
  showChatDrawer: Boolean
})

const userStore = useUserStore()
const messageStore = useMessageStore()

const emit = defineEmits(['toggle-chat'])

const isAuthenticated = computed(() => userStore.isAuthenticated)
const hasUnreadMessages = computed(() => messageStore.hasUnreadMessages)

const toggleChat = () => {
  emit('toggle-chat')
}
</script>

<style scoped>
.footer-contact-info {
  font-weight: bold;
}
.footer-links > a {
  color: white;
  text-decoration: none;
  font-weight: bold;
  font-size: 0.75rem;
  opacity: 0.8;
}
.footer-links a:hover {
  opacity: 1;
}
.chat-button {
  position: absolute;
  bottom: 16px;
  right: 16px;
}
</style>