<template>
  <v-container>
    <h1 class="text-h4 mb-4">Mis Viviendas</h1>
    <v-btn color="primary" @click="openDialog()">Agregar Nueva Vivienda</v-btn>

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
              <v-img :src="vivienda.fotos[0]" height="100" contain></v-img>
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
                <v-btn icon @click="eliminarVivienda(vivienda.idVivienda)" density="compact">
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
                <v-img :src="foto" height="300" cover>
                  <template v-slot:placeholder>
                    <v-row class="fill-height ma-0" align="center" justify="center">
                      <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
                    </v-row>
                  </template>
                  <v-btn icon class="position-absolute top-0 right-0 mt-2 mr-2" @click.stop="eliminarFoto(index)">
                    <v-icon color="error">mdi-close</v-icon>
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
import { useUserStore } from '@/stores/user'

const userStore = useUserStore()
const viviendas = ref([])
const dialog = ref(false)
const editMode = ref(false)
const viviendaActual = ref({})
const nuevasFotos = ref([])

onMounted(async () => {
  // Aquí cargarías las viviendas del vendedor desde el backend
  // Por ahora, usaremos datos de ejemplo
  viviendas.value = [
    {
      idVivienda: 1,
      codigoUnico: 'CASA001',
      tipoVivienda: 'Casa',
      ubicacion: 'Santiago Centro',
      numeroDeHabitaciones: 3,
      precio: 200000000,
      estado: 1,
      intencionVenta: 1,
      descripcion: 'Hermosa casa en el centro',
      fotos: ['https://fakeimg.pl/100x100/', 'https://ejemplo.com/foto2.jpg']
    },
    // ... más viviendas
  ]
})

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

const guardarVivienda = async () => {
  // Aquí implementarías la lógica para guardar o actualizar la vivienda en el backend
  if (editMode.value) {
    // Actualizar vivienda existente
  } else {
    // Crear nueva vivienda
  }
  dialog.value = false
  // Recargar la lista de viviendas
}

const eliminarVivienda = async (id) => {
  // Aquí implementarías la lógica para eliminar la vivienda en el backend
  viviendas.value = viviendas.value.filter(v => v.idVivienda !== id)
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