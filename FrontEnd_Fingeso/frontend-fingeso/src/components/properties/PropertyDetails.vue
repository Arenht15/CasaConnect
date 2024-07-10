<template>
    <v-container>
      <v-btn color="primary" @click="volverAlListado" class="mb-4">
        <v-icon left>mdi-arrow-left</v-icon>
        Volver al listado
      </v-btn>

      <v-card v-if="propiedad">
        <v-carousel v-if="propiedad.imagenes && propiedad.imagenes.length > 0" height="400">
          <v-carousel-item
            v-for="(imagen, i) in propiedad.imagenes"
            :key="i"
            :src="imagen"
            cover
          ></v-carousel-item>
        </v-carousel>

        <v-card-title class="text-h4">{{ propiedad.titulo }}</v-card-title>

        <v-card-text>
          <v-row>
            <v-col cols="12" md="8">
              <p class="text-h6">{{ propiedad.ubicacion }}</p>
              <p class="text-h5 primary--text">{{ formatPrecio(propiedad.precio) }}</p>
              <v-chip-group>
                <v-chip v-for="(caracteristica, index) in propiedad.caracteristicas" :key="index">
                  {{ caracteristica }}
                </v-chip>
              </v-chip-group>
              <v-divider class="my-4"></v-divider>
              <h3 class="text-h6 mb-2">Descripción</h3>
              <p>{{ propiedad.descripcion }}</p>
            </v-col>

            <v-col cols="12" md="4">
              <v-card outlined>
                <v-card-title>Contacto</v-card-title>
                <v-card-text>
                  <p><strong>Nombre:</strong> {{ propiedad.contacto.nombre }}</p>
                  <p><strong>Teléfono:</strong> {{ propiedad.contacto.telefono }}</p>
                  <p><strong>Email:</strong> {{ propiedad.contacto.email }}</p>
                  <v-btn block color="primary" class="mt-4" @click="contactarPropietario">
                    Contactar propietario
                  </v-btn>
                </v-card-text>
              </v-card>

              <v-card outlined class="mt-4">
                <v-card-title>Acción</v-card-title>
                <v-card-text>
                  <v-btn block color="success" class="mb-2" @click="realizarAccion('comprar')" v-if="propiedad.venta">
                    Comprar
                  </v-btn>
                  <v-btn block color="info" @click="realizarAccion('alquilar')" v-if="propiedad.alquiler">
                    Alquilar
                  </v-btn>
                </v-card-text>
              </v-card>
            </v-col>
          </v-row>
        </v-card-text>
      </v-card>

      <v-alert v-else type="error">
        No se encontró la propiedad solicitada.
      </v-alert>
    </v-container>
  </template>

  <script setup>
  import { ref, onMounted } from 'vue'
  import { useRoute, useRouter } from 'vue-router'

  const route = useRoute()
  const router = useRouter()

  const propiedad = ref(null)

  onMounted(async () => {
    // Aquí normalmente harías una llamada a tu API para obtener los detalles de la propiedad
    // Por ahora, usaremos datos de ejemplo
    propiedad.value = {
      id: route.params.id,
      titulo: 'Casa moderna en el centro',
      ubicacion: 'Santiago Centro, Chile',
      precio: 200000000,
      caracteristicas: ['3 habitaciones', '2 baños', 'Cocina equipada', 'Terraza'],
      descripcion: 'Hermosa casa moderna ubicada en el corazón de Santiago. Perfecta para familias jóvenes o profesionales que buscan comodidad y estilo en el centro de la ciudad.',
      imagenes: [
        'https://ejemplo.com/imagen1.jpg',
        'https://ejemplo.com/imagen2.jpg',
        'https://ejemplo.com/imagen3.jpg'
      ],
      contacto: {
        nombre: 'Juan Pérez',
        telefono: '+56 9 1234 5678',
        email: 'juan.perez@ejemplo.com'
      },
      venta: true,
      alquiler: false
    }
  })

  const formatPrecio = (valor) => {
    return new Intl.NumberFormat('es-CL', {
      style: 'currency',
      currency: 'CLP',
      maximumFractionDigits: 0
    }).format(valor)
  }

  const volverAlListado = () => {
    router.push('/properties')
  }

  const contactarPropietario = () => {
    // Aquí puedes implementar la lógica para mostrar un formulario de contacto o abrir el correo electrónico
    console.log('Contactando al propietario...')
  }

  const realizarAccion = (accion) => {
    // Aquí puedes implementar la lógica para el proceso de compra o alquiler
    console.log(`Iniciando proceso de ${accion}...`)
  }
  </script>