import { defineStore } from 'pinia'
import axios from 'axios'

export const useHouseStore = defineStore('house', {
  state: () => ({
    houseId: 0,
    houseCode: '',
    houseType: '',
    houseUbication: '',
    houseHabitation: '',
    housePrice: '',
    houseState: '',
    houseDescription: ''
  }),

  actions: {
    toggleDrawer() {
      this.drawerOpen = !this.drawerOpen
    },
    async register(housecode, houseubication, househabitation, houseprice, housestate, housedescription, housetype, houseintention, housephotos) {

      if (!this.isPasswordValid(houseubication)) {
        throw new Error("La contraseña debe tener al menos 5 caracteres.")
      }

      // Verifica en BD existencia de usuario o correo
      try {
        const res = await axios.get(`http://localhost:8080/api/v1/vivienda/validate/${housecode}/`)
        if (res.data && res.data > 0) {
          throw new Error("Casa o arriendo ya existen")
        }
      } catch (e) {
        console.log(e.message)
        throw new Error(e.message)
      }

      const data = {
        'codigoUnico': housecode,
        'tipoVivienda': housetype, // Asegúrate de definir 'housetype' en tu código
        'ubicacion': houseubication,
        'numeroDeHabitaciones': househabitation,
        'precio': houseprice,
        'estado': housestate,
        'intencionVenta': houseintention, // Asegúrate de definir 'houseintention' en tu código
        'descripcion': housedescription,
        'fotos': housephotos // Asegúrate de definir 'housephotos' en tu código
      }

      // Registro/Guardado en BD
      try {
        console.log("Datos enviados:", data);
        await axios.post("http://localhost:8080/api/v1/vivienda/", data);
        return true;
      } catch (e) {
        console.log(e.message);
        throw new Error("Error en el registro");
      }
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
    subirImagen(formData, id) {
      return axios.post(`http://localhost:8080/api/v1/vivienda/${id}/imagen`, formData, {
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