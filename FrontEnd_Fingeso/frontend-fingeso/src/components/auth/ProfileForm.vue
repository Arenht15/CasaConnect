<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12" sm="8" md="6">
        <h1 class="text-h4 mb-6">Perfil de Usuario</h1>
        <v-card class="pa-6">
          <div class="text-center mb-2">
            <v-avatar size="100" class="mb-4">
              <v-img :src="avatarPreview || 'https://fakeimg.pl/100x100/'" alt="Avatar"></v-img>
            </v-avatar>
            <v-file-input
              v-model="avatarFile"
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
import { ref, onMounted } from 'vue'
import { useUserStore } from '@/stores/user'

const userStore = useUserStore()
const profile = ref({})
const avatarFile = ref(null)
const avatarPreview = ref(null)

onMounted(() => {
  profile.value = { ...userStore }
})

const previewAvatar = (event) => {
  const file = event.target.files[0]
  if (file) {
    avatarPreview.value = URL.createObjectURL(file)
  }
}

const updateProfile = async () => {
  try {
    // Aquí iría la lógica para enviar los datos al backend
    console.log('Perfil actualizado', profile.value)
    if (avatarFile.value) {
      console.log('Avatar a subir', avatarFile.value)
    }
  } catch (error) {
    console.error('Error al actualizar perfil', error)
  }
}

const confirmDelete = () => {
  // Aquí iría la lógica para confirmar y eliminar el perfil
  console.log('Confirmar eliminación de perfil')
}
</script>

<style scoped>
  h1 {
    font-weight: bold;
    color: #4CAF50;
  }
</style>