// stores/user.js
import { defineStore } from 'pinia'
import axios from 'axios'

export const useUserStore = defineStore('user', {
  state: () => ({
    isAuthenticated: false,
    isVendor: false,
    drawerOpen: false,
    userId: 0,
    userName: '',
    userEmail: '',
    userRol: ''
  }),

  actions: {
    toggleDrawer() {
      this.drawerOpen = !this.drawerOpen
    },
    async register(username, email, password, type) {
      // Validaciones
      if (!this.isUsernameValid(email)) {
        throw new Error("El nombre de usuario debe tener al menos 5 caracteres.")
      }
      if (!this.isEmailValid(email)) {
        throw new Error("Por favor, ingrese un correo electrónico válido.")
      }
      if (!this.isPasswordValid(password)) {
        throw new Error("La contraseña debe tener al menos 5 caracteres.")
      }

      // Verifica en BD existencia de usuario o correo
      try {
        const res = await axios.get(`http://localhost:8080/api/v1/usuario/validate/${username}/${email}`)
        if (res.data && res.data > 0) {
          throw new Error("Usuario o Email ya existen")
        }
      } catch (e) {
        console.log(e.message)
        throw new Error("Error en la validación")
      }

      const data = {
        'nombre': username,
        'email': email,
        'contrasena': password,
        'rol': type
      }

      // Registro/Guardado en BD
      try {
        await axios.post("http://localhost:8080/api/v1/usuario/", data)
        return true
      } catch (e) {
        console.log(e.message)
        throw new Error("Error en el registro")
      }
    },
    async login(email, password) {
      // Validaciones
      if (!this.isEmailValid(email)) {
        throw new Error("Por favor, ingrese un correo electrónico válido.")
      }
      if (!this.isPasswordValid(password)) {
        throw new Error("La contraseña debe tener al menos 5 caracteres.")
      }

      // Busca en BD
      try {
        const response = await axios.get(`http://localhost:8080/api/v1/usuario/${email}/${password}`)
        if (response.data) {
          this.isAuthenticated = true
          this.userId = response.data.id
          this.userName = response.data.nombre
          this.userEmail = response.data.email
          this.userRol = response.data.rol
          this.isVendor = response.data.rol === 1 // 1 es el rol de vendedor
          return true
        } else {
          throw new Error("Credenciales inválidas")
        }
      } catch (error) {
        console.error('Error al iniciar sesión:', error)
        // throw error
        throw new Error("Error al iniciar sesión")
      }
    },
    logout() {
      this.isAuthenticated = false
      this.isVendor = false
      this.userId = 0
      this.userName = ''
      this.userEmail = ''
      this.userRol = ''
    },
    isEmailValid(email) {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
      return emailRegex.test(email)
    },
    isPasswordValid(password) {
      return password.length >= 5
    },
    isUsernameValid(username) {
      return username.length >= 5
    },
  },
})