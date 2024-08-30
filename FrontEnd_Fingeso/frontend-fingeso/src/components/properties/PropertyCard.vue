<template>
  <v-card class="property-card">
    <v-img :src="propiedad.imagen" height="200px" cover></v-img>
    <v-card-title>{{ propiedad.titulo }}</v-card-title>
    <v-card-text>
      <div>{{ propiedad.ubicacion }}</div>
      <div class="text-h6 primary--text">{{ formatPrecio(propiedad.precio) }}</div>
      <div>{{ propiedad.caracteristicas }}</div>
    </v-card-text>
    <v-card-actions>
      <v-row class="justify-space-between align-center">
        <v-col cols="12" class="d-flex justify-center">
          <v-btn color="primary" @click="verDetalles">Ver detalles</v-btn>
        </v-col>
        <v-col cols="12" class="d-flex justify-center">
          <v-btn color="secondary" @click="contactar">Contactar</v-btn>
        </v-col>
      </v-row>
    </v-card-actions>
  </v-card>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue'

const props = defineProps({
  propiedad: {
    type: Object,
    required: true
  }
})

const emit = defineEmits(['ver-detalles', 'contactar'])

const formatPrecio = (precio) => {
  return new Intl.NumberFormat('es-CL', {
    style: 'currency',
    currency: 'CLP',
    maximumFractionDigits: 0
  }).format(precio)
}

const verDetalles = () => {
  emit('ver-detalles', props.propiedad.id)
}

const contactar = () => {
  emit('contactar', props.propiedad.id)
}
</script>

<style scoped>
.property-card {
  max-width: 250px; /* Aumentar el ancho máximo de la tarjeta */
  margin: 20px auto; /* Añadir margen para separar las tarjetas */
  padding: 10px; /* Añadir padding para espaciar el contenido dentro de la tarjeta */
}

.v-card-title, .v-card-text, .v-card-actions {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>