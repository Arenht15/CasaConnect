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
            <v-card>
              <v-img :src="propiedad.imagen" height="200px" cover></v-img>
              <v-card-title>{{ propiedad.titulo }}</v-card-title>
              <v-card-text>
                <div>{{ propiedad.ubicacion }}</div>
                <div class="text-h6">{{ propiedad.precio }}</div>
                <div>{{ propiedad.caracteristicas }}</div>
              </v-card-text>
              <v-card-actions>
                <v-btn color="primary">Ver detalles</v-btn>
                <v-btn color="secondary">Contactar</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>

        <!-- Paginación -->
        <v-pagination
          v-model="paginaActual"
          :length="totalPaginas"
          @input="cambiarPagina"
        ></v-pagination>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      tipoPropiedad: '',
      rangoPrecio: [0, 1000000],
      numeroHabitaciones: null,
      paginaActual: 1,
      totalPaginas: 10,
      propiedades: [
        // Aquí irían tus datos de propiedades
        // Ejemplo:
        {
          id: 1,
          titulo: 'Casa moderna en el centro',
          ubicacion: 'Santiago Centro',
          precio: '$200,000,000',
          caracteristicas: '3 habitaciones, 2 baños',
          imagen: 'url_de_la_imagen'
        },
        // ... más propiedades
      ]
    }
  },
  methods: {
    cambiarPagina(pagina) {
      // Lógica para cambiar de página
      console.log('Cambiando a página:', pagina)
    },
    formatPrecio(valor) {
      return new Intl.NumberFormat('es-CL', {
        style: 'currency',
        currency: 'CLP',
        maximumFractionDigits: 0
      }).format(valor);
    }
  }
}
</script>