// stores/user.js
import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', {
  state: () => ({
    isVendor: false,
    drawerOpen: false,
  }),
  actions: {
    toggleDrawer() {
      this.drawerOpen = !this.drawerOpen
    },
    logout() {
      // Implementa aquí la lógica de cierre de sesión
      this.isVendor = false
    },
  },
})