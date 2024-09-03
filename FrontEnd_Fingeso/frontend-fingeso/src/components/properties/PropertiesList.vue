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
              :items="['Casa', 'Departamento']"
              label="Tipo de propiedad"
            ></v-select>
            <v-range-slider
              v-model="rangoPrecio"
              :max="100000000"
              :min="0"
              :step="100000"
              label="Rango de precio"
              color="primary"
            ></v-range-slider>
            <div class="d-flex justify-space-between">
              <span class="text-caption">{{ formatPrecio(rangoPrecio[0]) }}</span>
              <span class="text-caption">{{ formatPrecio(rangoPrecio[1]) }}</span>
            </div>
            <v-select
              v-model="numeroHabitaciones"
              :items="[1, 2, 3, 4, 5]"
              label="Número de habitaciones"
            ></v-select>
            <v-btn color="primary" class="mt-4" @click="aplicarFiltros">Aplicar Filtros</v-btn>
          </v-card-text>
        </v-card>

        <!-- Ordenar -->
        <v-card class="mt-4">
          <v-card-title>Ordenar por precio</v-card-title>
          <v-card-text>
            <v-btn color="primary" @click="ordenarPorPrecioMayorMenor">Mayor a menor</v-btn>
            <v-btn color="primary" @click="ordenarPorPrecioMenorMayor">Menor a mayor</v-btn>
          </v-card-text>
        </v-card>
      </v-col>

      <v-col cols="12" md="9">
        <!-- Lista de propiedades -->
        <v-row>
          <v-col v-for="propiedad in propiedades" :key="propiedad.idVivienda" cols="12" sm="6" md="4">
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
  import axios from 'axios'
  import { ref, onMounted } from 'vue'
  import { useRouter } from 'vue-router'
  import { useUserStore } from '@/stores/user'
  import PropertyCard from '@/components/properties/PropertyCard.vue'

  const router = useRouter()
  const userStore = useUserStore()

const tipoPropiedad = ref('')
const rangoPrecio = ref([0, 20000000])
const numeroHabitaciones = ref(null)
const paginaActual = ref(1)
const totalPaginas = ref(10)

const propiedades = ref([])

const fetchPropiedades = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/v1/vivienda/')
    propiedades.value = response.data
    agregarCaracteristicas(propiedades.value)
  } catch (error) {
    console.error('Error fetching properties:', error)
  }
}

const filtrarPropiedades = async (filtros = {}) => {
  try {
    console.log('Filtros enviados:', filtros); // Mostrar los filtros en la consola
    const response = await axios.get('http://localhost:8080/api/v1/vivienda/filtrar/', { params: filtros });
    propiedades.value = response.data;
    agregarCaracteristicas(propiedades.value)
  } catch (error) {
    console.error('Error fetching properties:', error);
  }
}

onMounted(() => {
  fetchPropiedades()
})

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

const agregarCaracteristicas = (propiedades) => {
  propiedades.forEach(p => {
    p.caracteristicas =
      p.numeroDeHabitaciones + " Hab., " +
      p.numeroDeBanos + " Baños, " +
      p.metrosCuadrados + " Mts2";
    p.propiedades =
      p.tipoVivienda + " - " +
      (p.intencionVenta  ? "Venta" : "Arriendo")
  });
}

const ordenarPorPrecioMayorMenor = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/v1/vivienda/ordenar/precio/MayoraMenor`)
    propiedades.value = response.data
    agregarCaracteristicas(propiedades.value)
  } catch (error) {
    console.error('Error ordenando propiedades:', error)
  }
}

const ordenarPorPrecioMenorMayor = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/v1/vivienda/ordenar/precio/MenoraMayor`)
    propiedades.value = response.data
    agregarCaracteristicas(propiedades.value)
  } catch (error) {
    console.error('Error ordenando propiedades:', error)
  }
}
const aplicarFiltros = () => {
  const filtros = {
    precioMin: rangoPrecio.value[0],
    precioMax: rangoPrecio.value[1]
  }
  if (tipoPropiedad.value) {
    filtros.tipoPropiedad = tipoPropiedad.value
  }
  if (numeroHabitaciones.value) {
    filtros.numeroHabitaciones = numeroHabitaciones.value
  }
  filtrarPropiedades(filtros)
}
</script>
