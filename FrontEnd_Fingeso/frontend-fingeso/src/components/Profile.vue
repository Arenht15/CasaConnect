<template>
  <v-container>
    <v-btn color="primary" @click="$router.push('/')" class="mb-4">
      <v-icon left>mdi-arrow-left</v-icon>
      Volver
    </v-btn>
    <div class="profile">
      <h1 class="profile__name">{{ userStore.userName }}</h1>
      <v-alert
        v-if="messageInfo.show"
        :type="messageInfo.type"
        dismissible
        @click:close="messageInfo.show = false"
      >
        {{ messageInfo.text }}
      </v-alert>
      <div class="profile__details">
          <p class="paragraph"><strong>Username:</strong> {{ userStore.userName }}</p>
          <p class="paragraph"><strong>Email:</strong> {{ userStore.userEmail }}</p>
          <p class="paragraph"><strong>Eliminar Perfil</strong></p>
          <v-btn color="error" @click="deleteUser" class="mb-4">
            <v-icon left>mdi-delete</v-icon>
            Eliminar perfil
          </v-btn>
      </div>
    </div>
  </v-container>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'

const router = useRouter()
const userStore = useUserStore()

const messageInfo = ref({ show: false, text: '', type: 'info' })

const deleteUser = () => {
  showMessage('Usuario eliminado', 'info')
  setTimeout(() => {
    messageInfo.value = { show: false, text: "", type: "" }
    userStore.logout()
    router.push('/')
  }
    , 2000)
}

const showMessage = (text, type) => {
  messageInfo.value = { show: true, text, type }
  setTimeout(() => messageInfo.value = { show: false, text: "", type: "" }, 2000)
}
</script>

<style scoped>
.profile {
  text-align: center;
}

.profile__name {
  font-weight: bold;
  text-align: center;
  margin-bottom: 20px;

}

.profile__avatar {
  text-align: center;
  margin-top: 100px;
}

.profile__details {

  text-align: center;
}.paragraph {
   margin-bottom: 25px;
}
</style>
