<template>
    <v-container>
      <v-row>
        <v-col cols="12" md="3">
          <!-- Filtros -->
          <v-card>
            <v-card-title>Filtros</v-card-title>
            <v-card-text>
              <v-select
                v-model="tipoPropiedad"
                :items="['Casa', 'Departamento', 'Oficina']"
                label="Tipo de propiedad"
              ></v-select>
              <v-range-slider
                v-model="rangoPrecio"
                :max="1000000"
                :min="0"
                :step="10000"
                label="Rango de precio"
                color="primary"
              ></v-range-slider>
              <div class="d-flex justify-space-between">
                <span class="text-caption">{{ formatPrecio(rangoPrecio[0]) }}</span>
                <span class="text-caption">{{ formatPrecio(rangoPrecio[1]) }}</span>
              </div>
              <v-select
                v-model="numeroHabitaciones"
                :items="[1, 2, 3, 4, '5+']"
                label="Número de habitaciones"
              ></v-select>
            </v-card-text>
          </v-card>
        </v-col>

        <v-col cols="12" md="9">
          <!-- Lista de propiedades -->
          <v-row>
            <v-col v-for="propiedad in propiedades" :key="propiedad.id" cols="12" sm="6" md="4">
              <PropertyCard
                :propiedad="propiedad"
                @ver-detalles="verDetallesPropiedad"
                @contactar="contactarPropietario"
              />
            </v-col>
          </v-row>

          <!-- Paginación -->
          <v-pagination
            v-model="paginaActual"
            :length="totalPaginas"
            @update:model-value="cambiarPagina"
          ></v-pagination>
        </v-col>
      </v-row>
    </v-container>
  </template>

  <script setup>
  import { ref, computed } from 'vue'
  import { useRouter } from 'vue-router'
  import { useUserStore } from '@/stores/user'
  import PropertyCard from '@/components/properties/PropertyCard.vue'

  const router = useRouter()
  const userStore = useUserStore()

  const tipoPropiedad = ref('')
  const rangoPrecio = ref([0, 1000000])
  const numeroHabitaciones = ref(null)
  const paginaActual = ref(1)
  const totalPaginas = ref(10)

  const propiedades = ref([
    {
      id: 1,
      titulo: 'Casa moderna en el centro',
      ubicacion: 'Santiago Centro',
      precio: 200000000,
      caracteristicas: '3 habitaciones, 2 baños',
      imagen: 'https://images.adsttc.com/media/images/623c/4fa0/3e4b/3145/3000/001b/newsletter/_d_ambrosio_07._copy.jpg?1648119692'
    },
    // ... más propiedades
  ])

  const cambiarPagina = (pagina) => {
    console.log('Cambiando a página:', pagina)
    // Lógica para cambiar de página
  }

  const formatPrecio = (valor) => {
    return new Intl.NumberFormat('es-CL', {
      style: 'currency',
      currency: 'CLP',
      maximumFractionDigits: 0
    }).format(valor)
  }

  const verDetallesPropiedad = (id) => {
    console.log('Ver detalles de la propiedad:', id)
    router.push(`/properties/${id}`)
  }

  const contactarPropietario = (id) => {
    console.log('Contactar al propietario de la propiedad:', id)
    // Aquí iría la lógica para mostrar un formulario de contacto o similar
  }
  </script>