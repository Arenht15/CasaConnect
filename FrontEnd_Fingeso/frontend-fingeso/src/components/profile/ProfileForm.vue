<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12" sm="8" md="6">
        <v-alert
          v-if="messageInfo.show"
          :type="messageInfo.type"
          dismissible
          @click:close="messageInfo.show = false"
        >
          {{ messageInfo.text }}
        </v-alert>
        <v-card class="pa-6">
          <div class="text-center mb-2">
            <v-avatar size="100" class="mb-4">
              <v-img :src="avatarPreview" alt="Avatar"></v-img>
            </v-avatar>
            <v-file-input
              v-model="imagenPerfil"
              label="Cambiar foto de perfil"
              accept="image/*"
              prepend-icon="mdi-camera"
              @change="previewAvatar"
            ></v-file-input>
          </div>
          <v-form @submit.prevent="updateProfile">
            <v-text-field
              v-model="profile.userName"
              label="Nombre de usuario"
              required
            ></v-text-field>
            <v-text-field
              v-model="profile.userEmail"
              label="Correo electrónico"
              type="email"
              required
            ></v-text-field>
            <v-btn type="submit" color="primary" block class="mt-4">Guardar cambios</v-btn>
          </v-form>
          <v-btn color="error" block class="mt-4" @click="confirmDelete">Eliminar Perfil</v-btn>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { useUserStore } from '@/stores/user'

const userStore = useUserStore()
const profile = ref({})
const imagenPerfil = ref(null)
const messageInfo = ref({ show: false, text: '', type: 'info' })

const avatarPreview = computed(() => {
  if (imagenPerfil.value && typeof imagenPerfil.value === 'string') {
    return `http://localhost:8080/uploads/${imagenPerfil.value}`
  } else if (imagenPerfil.value instanceof File) {
    return URL.createObjectURL(imagenPerfil.value)
  }
  return 'https://fakeimg.pl/100x100/'
})

onMounted(() => {
  profile.value = { ...userStore }
  imagenPerfil.value = profile.value.userImage
})

const previewAvatar = (event) => {
  const file = event.target.files[0]
  if (file) {
    imagenPerfil.value = file
  }
}

const updateProfile = async () => {
  try {
    // Aquí iría la lógica para enviar los datos al backend
    subirImagen(profile.value)
    console.log('Perfil actualizado', profile.value)
    if (imagenPerfil.value) {
      console.log('Avatar a subir', imagenPerfil.value)
    }
    const response = await userStore.update(profile.value.userId, profile.value.userName, profile.value.userEmail, imagenPerfil.value)
    console.log(response)
    if (response) {
      userStore.userName = profile.value.userName
      userStore.userEmail = profile.value.userEmail
      userStore.userImage = imagenPerfil.value
      showMessage('Datos del usuario actualizados', 'success')
    }
  } catch (error) {
    console.error('Error al actualizar perfil', error)
    showMessage('Error al actualizar el perfil.', 'error')
  }
}

const confirmDelete = () => {
  // Aquí iría la lógica para confirmar y eliminar el perfil
  console.log('Confirmar eliminación de perfil')
}

const subirImagen = async (profile) => {
  // console.log('subirImagen', imagenPerfil.value)
  // console.log('userStore.userId', userStore.userId)
  // console.log('profile.value.userId', profile.value.userId)
  if (imagenPerfil.value instanceof File) {
    const formData = new FormData()
    formData.append('file', imagenPerfil.value)
    try {
      const response = await userStore.subirImagenPerfil(formData, profile.userId)
      // profile.value.userImage = response.data
      imagenPerfil.value = response.data
      // showMessage('Avatar actualizado', 'success')
    } catch (error) {
      console.error('Error al subir la imagen:', error)
      showMessage('Error al subir la imagen.', 'error')
    }
  }
}

const showMessage = (text, type) => {
  messageInfo.value = { show: true, text, type }
  setTimeout(() => messageInfo.value = { show: false, text: "", type: "" }, 2000)
}

</script>

<style scoped>
  h1 {
    font-weight: bold;
    color: #4CAF50;
  }
</style>