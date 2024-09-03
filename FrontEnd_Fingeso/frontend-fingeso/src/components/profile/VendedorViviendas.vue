<template>
  <v-container>
    <router-link to="/addHouse">
      <v-btn color="primary">Agregar Nueva Vivienda</v-btn>
    </router-link>
    <v-list density="compact" lines="one" slim>
      <v-list-item class="font-weight-bold" density="compact">
        <v-row align="center">
          <v-col cols="2">Fotos</v-col>
          <v-col cols="2">Código</v-col>
          <v-col cols="3">Descripción</v-col>
          <v-col cols="2">Características</v-col>
          <v-col cols="2">Precio</v-col>
          <v-col cols="1">Acciones</v-col>
        </v-row>
      </v-list-item>
      <v-list-item v-for="vivienda in viviendas" :key="vivienda.idVivienda" density="compact" slim>
          <v-row align="center">
            <v-col cols="2">
              <v-img :src="preview(vivienda.fotos[0])" height="100" contain></v-img>
            </v-col>
            <v-col cols="2">
              <v-list-item-title>{{ vivienda.codigoUnico }}</v-list-item-title>
            </v-col>
            <v-col cols="3">
              <v-list-item-subtitle>{{ vivienda.descripcion }}</v-list-item-subtitle>
            </v-col>
            <v-col cols="2">
              <v-list-item-subtitle>{{ vivienda.tipoVivienda }} - {{ vivienda.numeroDeHabitaciones }} hab.</v-list-item-subtitle>
            </v-col>
            <v-col cols="2">
              <v-list-item-subtitle>{{ formatPrice(vivienda.precio) }}</v-list-item-subtitle>
            </v-col>
            <v-col cols="1">
              <v-row justify="space-around">
                <v-btn icon @click="openDialog(vivienda)" density="compact">
                  <v-icon size="small" color="primary">mdi-pencil</v-icon>
                </v-btn>
                <v-btn icon @click="confirmarEliminacion(vivienda.idVivienda)" density="compact">
                  <v-icon size="small" color="error">mdi-delete</v-icon>
                </v-btn>
              </v-row>
            </v-col>
          </v-row>
      </v-list-item>
    </v-list>

    <v-dialog v-model="dialog" max-width="800px">
      <v-card>
        <v-card-title>
          <span class="text-h5">{{ editMode ? 'Editar Vivienda' : 'Agregar Nueva Vivienda' }}</span>
        </v-card-title>
        <v-card-text>
          <v-form @submit.prevent="guardarVivienda">
            <v-carousel v-if="viviendaActual.fotos && viviendaActual.fotos.length > 0" height="300">
              <v-carousel-item v-for="(foto, index) in viviendaActual.fotos" :key="index">
                <v-img :src="preview(foto)" height="300" cover>
                  <template v-slot:placeholder>
                    <v-row class="fill-height ma-0" align="center" justify="center">
                      <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
                    </v-row>
                  </template>
                  <v-btn icon class="position-absolute top-0 right-0 mt-2 mr-2" @click.stop="eliminarFoto(index)">
                    <v-icon color="error">mdi-delete</v-icon>
                  </v-btn>
                </v-img>
              </v-carousel-item>
            </v-carousel>
            <v-text-field v-model="viviendaActual.tipoVivienda" label="Tipo de Vivienda"></v-text-field>
            <v-text-field v-model="viviendaActual.ubicacion" label="Ubicación"></v-text-field>
            <v-text-field v-model="viviendaActual.numeroDeHabitaciones" label="Número de Habitaciones" type="number"></v-text-field>
            <v-text-field v-model="viviendaActual.precio" label="Precio" type="number"></v-text-field>
            <v-select v-model="viviendaActual.intencionVenta" :items="[{text: 'Venta', value: 1}, {text: 'Arriendo', value: 2}]" label="Intención"></v-select>
            <v-textarea v-model="viviendaActual.descripcion" label="Descripción"></v-textarea>
            <v-file-input v-model="nuevasFotos" label="Agregar Fotos" multiple accept="image/*"></v-file-input>
            <v-btn color="primary" type="submit">Guardar</v-btn>
            <v-btn color="error" @click="dialog = false">Cancelar</v-btn>
          </v-form>
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { useHouseStore } from '@/stores/house'

const houseStore = useHouseStore()
const viviendas = ref([])
const dialog = ref(false)
const editMode = ref(false)
const viviendaActual = ref({})
const nuevasFotos = ref(null)
//const imagen = ref(null)

const fetchViviendas = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/v1/vivienda/')
    viviendas.value = response.data
  } catch (error) {
    console.error('Error fetching properties:', error)
  }
}

onMounted(() => {
  fetchViviendas()
})

const preview = (image) => {
    if (image && typeof image === 'string') {
      return `http://localhost:8080/uploads/${image}`
    }
  }

const openDialog = (vivienda = null) => {
  if (vivienda) {
    viviendaActual.value = { ...vivienda }
    editMode.value = true
  } else {
    viviendaActual.value = { fotos: [] }
    editMode.value = false
  }
  dialog.value = true
}

const subirImagen = async (vivienda) => {
  console.log('subirImagen', nuevasFotos.value)
  console.log('houseStore.idVivienda', vivienda.idVivienda)
  // console.log('profile.value.userId', profile.value.userId)
  if (nuevasFotos.value[0] instanceof File) {
    console.log('entro a guardar')
    const formData = new FormData()
    formData.append('file', nuevasFotos.value[0])
    try {
      const response = await houseStore.subirImagen(formData, vivienda.idVivienda)
      // profile.value.userImage = response.data
      nuevasFotos.value = response.data
      viviendaActual.value.fotos.push(nuevasFotos.value);
      // showMessage('Avatar actualizado', 'success')
    } catch (error) {
      console.error('Error al subir la imagen:', error)
      // showMessage('Error al subir la imagen.', 'error')
    }
  }
}

const guardarVivienda = async () => {
  try {
    if (editMode.value) {
      // Actualizar vivienda existente
      subirImagen(viviendaActual.value)

      await axios.put(`http://localhost:8080/api/v1/vivienda/${viviendaActual.value.idVivienda}`, viviendaActual.value)
    } else {
      // Crear nueva vivienda
      await axios.post('http://localhost:8080/api/v1/vivienda/', viviendaActual.value)
    }
    dialog.value = false
    // Recargar la lista de viviendas
    fetchViviendas()
  } catch (error) {
    console.error('Error saving property:', error)
  }
}

const eliminarVivienda = async (id) => {
  // Aquí implementarías la lógica para eliminar la vivienda en el backend
  try {
    await axios.delete(`http://localhost:8080/api/v1/vivienda/validate/${id}/`)
  viviendas.value = viviendas.value.filter(v => v.idVivienda !== id)
  } catch (error) {
    console.error('Error deleting property:', error)
  }
}

const confirmarEliminacion = (id) => {
  const confirmacion = confirm('¿Estás seguro de que quieres eliminar esta vivienda?');
  if (confirmacion) {
     eliminarVivienda(id);
  }
}

const eliminarFoto = (index) => {
  viviendaActual.value.fotos.splice(index, 1)
}

const formatPrice = (price) => {
  return new Intl.NumberFormat('es-CL', { style: 'currency', currency: 'CLP' }).format(price)
}
</script>

<style scoped>
.position-absolute {
  position: absolute;
}
.top-0 {
  top: 0;
}
.right-0 {
  right: 0;
}
</style>
