<template>
    <v-container>
      <v-btn color="primary" @click="$router.push('/')" class="mb-4">
        <v-icon left>mdi-arrow-left</v-icon>
        Volver
      </v-btn>
      <v-img
        src="../assets/CasaConnectLogo.png"
        alt="Casa Connect Logo"
        class="mx-auto"
        max-width="150"
        :style="{ 'margin-bottom': '25px'}"
      >
      </v-img>

      <h1 class="title">Opciones de Financiamiento</h1>

      <div>
        <p class="text-center paragraph">
          En CasaConect, entendemos que la adquisición de una propiedad es una decisión importante que a menudo requiere apoyo financiero.
          Por eso, nos hemos asociado con diversas instituciones financieras de confianza para ofrecerte una variedad de opciones de créditos hipotecarios y financiamiento.
        </p>

        <v-card v-for="(partner, index) in financialPartners" :key="index" class="mb-6">
          <v-card-title>{{ partner.name }}</v-card-title>
          <v-card-text>
            <p>{{ partner.description }}</p>
            <p class="mt-2">
              <strong>Contacto:</strong> {{ partner.contact.name }} -
              <a :href="'mailto:' + partner.contact.email">{{ partner.contact.email }}</a> -
              {{ partner.contact.phone }}
            </p>
          </v-card-text>
        </v-card>

        <p class="text-center paragraph">
          Recuerda que cada situación financiera es única. Te recomendamos contactar directamente a estas instituciones para obtener información detallada y personalizada sobre las opciones que mejor se adapten a tus necesidades.
        </p>
      </div>
    </v-container>
  </template>

  <script setup>
  import { ref } from 'vue'
  import { useRouter } from 'vue-router'

  const router = useRouter()

  const financialPartners = ref([
    {
      name: "Banco Horizonte",
      description: "Ofrece créditos hipotecarios flexibles con tasas competitivas y plazos de hasta 30 años.",
      contact: {
        name: "Ana Martínez",
        email: "ana.martinez@bancohorizonte.com",
        phone: "+56 2 2345 6789"
      }
    },
    {
      name: "Cooperativa Financiera Futuro",
      description: "Especializada en créditos para primeros compradores, con opciones de pago inicial reducido.",
      contact: {
        name: "Carlos Rodríguez",
        email: "carlos.rodriguez@coopfuturo.cl",
        phone: "+56 2 3456 7890"
      }
    },
    {
      name: "Hipotecaria Nacional",
      description: "Ofrece una amplia gama de productos hipotecarios, incluyendo opciones de tasa fija y variable.",
      contact: {
        name: "Valentina Soto",
        email: "valentina.soto@hipotecarianacional.cl",
        phone: "+56 2 4567 8901"
      }
    },
    {
      name: "FinanHogar",
      description: "Especialistas en financiamiento para propiedades de segunda vivienda y de inversión.",
      contact: {
        name: "Diego Morales",
        email: "diego.morales@finanhogar.com",
        phone: "+56 2 5678 9012"
      }
    }
  ])
  </script>

  <style scoped>
  .paragraph {
    font-size: 16px;
    margin-left: 200px;
    margin-right: 200px;
    margin-bottom: 20px;
  }
  .title {
    text-align: center;
    font-size: 24px;
    margin-bottom: 15px;
    color: #4CAF50;
    font-weight: bold;
  }
  .v-card-title {
    color: #4CAF50;
  }
  </style>