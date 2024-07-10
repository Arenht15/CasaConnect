<template>
  <v-btn
    v-show="isAuthenticated && !showDrawer"
    @click="toggleChat"
    color="primary"
    fab
    fixed
    bottom
    right
    class="mb-4 mr-4"
  >
    <v-icon>mdi-chat</v-icon>
    <v-badge
      v-if="hasUnreadMessages"
      color="red"
      content="!"
      offset-x="20"
      offset-y="20"
    ></v-badge>
  </v-btn>
</template>

<script setup>
import { computed } from 'vue'
import { useUserStore } from '@/stores/user'
import { useMessageStore } from '@/stores/message'

const props = defineProps({
  showDrawer: Boolean
})

const emit = defineEmits(['toggle-chat'])

const userStore = useUserStore()
const messageStore = useMessageStore()

const isAuthenticated = computed(() => userStore.isAuthenticated)
const hasUnreadMessages = computed(() => messageStore.hasUnreadMessages)

const toggleChat = () => {
  emit('toggle-chat')
}
</script>