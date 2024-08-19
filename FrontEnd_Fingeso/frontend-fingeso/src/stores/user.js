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
    userRol: '',
    userImage: ''
  }),

  actions: {
    toggleDrawer() {
      this.drawerOpen = !this.drawerOpen
    },
    async register(username, email, password, type) {
      // Validaciones
      if (!this.isUsernameValid(username)) {
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
        throw new Error(e.message)
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
          this.userId = response.data.idUsuario
          this.userName = response.data.nombre
          this.userEmail = response.data.email
          this.userRol = response.data.rol
          this.isVendor = response.data.rol === "vendedor" // 1 es el rol de vendedor
          this.userImage = response.data.imagenPerfil
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
    subirImagenPerfil(formData, id) {
      return axios.post(`http://localhost:8080/api/v1/usuario/${id}/imagen`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
    },
    async update(userid, username, useremail, userpicture) {
      // Validaciones
      if (!this.isUsernameValid(username)) {
        throw new Error("El nombre de usuario debe tener al menos 5 caracteres.")
      }
      if (!this.isEmailValid(useremail)) {
        throw new Error("Por favor, ingrese un correo electrónico válido.")
      }

      // Verifica en BD existencia de usuario o correo
      // try {
      //   const res = await axios.get(`http://localhost:8080/api/v1/usuario/validate/${username}/${useremail}`)
      //   if (res.data && res.data > 0) {
      //     throw new Error("Nombre de Usuario ya existe, no puede usar ese.")
      //   }
      // } catch (e) {
      //   console.log(e.message)
      //   throw new Error(e.message)
      // }

      const data = {
        'idUsuario': userid,
        'nombre': username,
        'email': useremail,
        'imagenPerfil': userpicture,
        'contrasena': '',
        'rol': this.userRol
      }

      // Guardado en BD
      try {
        await axios.post("http://localhost:8080/api/v1/usuario/", data)
        this.userName = username
        this.userEmail = useremail
        this.userImage = userpicture
        return true
      } catch (e) {
        console.log(e.message)
        throw new Error("Error en el registro")
      }
    },
  },
})