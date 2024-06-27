/**
 * plugins/vuetify.js
 *
 * Framework documentation: https://vuetifyjs.com`
 */

// Styles
import '@mdi/font/css/materialdesignicons.css'
import 'vuetify/styles'

// Composables
import { createVuetify } from 'vuetify'

// https://vuetifyjs.com/en/introduction/why-vuetify/#feature-guides
export default createVuetify({
  theme: {
    defaultTheme: 'casaConnectTheme',
    themes: {
      casaConnectTheme: {
        dark: false,
        colors: {
          primary: '#4CAF50',     // Verde principal
          secondary: '#133e4c', // '#1E2A38',   // Azul oscuro
          accent: '#8BC34A',      // Verde claro complementario
          error: '#FF5252',       // Rojo para errores
          info: '#2196F3',        // Azul para información
          success: '#4CAF50',     // Verde para éxito (igual que primary)
          warning: '#FFC107',     // Amarillo para advertencias

          // Colores adicionales para más variedad
          'primary-light': '#81C784',
          'primary-dark': '#388E3C',
          'secondary-light': '#37474F',
          'secondary-dark': '#10161D',
          background: '#FFFFFF',
          surface: '#F5F5F5',
        },
      },
    },
  },
})
