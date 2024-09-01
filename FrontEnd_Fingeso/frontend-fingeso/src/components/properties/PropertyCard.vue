<template>
  <v-card>
    <v-img :src="preview(propiedad.fotos[0])" height="200px" cover></v-img>
    <v-card-title>{{ propiedad.titulo }}</v-card-title>
    <v-card-text>
      <div>{{ propiedad.ubicacion }}</div>
      <div class="text-h6 primary--text">{{ formatPrecio(propiedad.precio) }}</div>
      <div>{{ propiedad.caracteristicas }}</div>
    </v-card-text>
    <v-card-actions>
      <v-btn color="primary" @click="verDetalles">Ver detalles</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script setup>
const props = defineProps({
  propiedad: {
    type: Object,
    required: true
  }
})

const emit = defineEmits(['ver-detalles'])


const preview = (image) => {
    if (image && typeof image === 'string') {
      return `http://localhost:8080/uploads/${image}`
    }
  }

const formatPrecio = (precio) => {
  return new Intl.NumberFormat('es-CL', {
    style: 'currency',
    currency: 'CLP',
    maximumFractionDigits: 0
  }).format(precio)
}

const verDetalles = () => {
  emit('ver-detalles', props.propiedad.idVivienda)
}
</script>