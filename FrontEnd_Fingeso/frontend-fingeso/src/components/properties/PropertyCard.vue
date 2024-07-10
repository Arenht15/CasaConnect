<template>
    <v-card>
      <v-img :src="propiedad.imagen" height="200px" cover></v-img>
      <v-card-title>{{ propiedad.titulo }}</v-card-title>
      <v-card-text>
        <div>{{ propiedad.ubicacion }}</div>
        <div class="text-h6 primary--text">{{ formatPrecio(propiedad.precio) }}</div>
        <div>{{ propiedad.caracteristicas }}</div>
      </v-card-text>
      <v-card-actions>
        <v-btn color="primary" @click="verDetalles">Ver detalles</v-btn>
        <v-btn color="secondary" @click="contactar">Contactar</v-btn>
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