
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

    <h1 class="title">Acerca de Nosotros</h1>

    <div>
      <p class="text-center paragraph">
          Bienvenido a nuestra plataforma dedicada a la publicación de propiedades en venta y arriendo.
          En CasaConect, nos enorgullece ofrecerte una experiencia intuitiva y eficiente para encontrar
          la casa de tus sueños o publicar tu propiedad de manera rápida y segura.</p>
      <p class="text-center paragraph">
          Conectamos a propietarios, agentes inmobiliarios y potenciales compradores o arrendatarios en
          un entorno digital moderno y accesible. Nuestra misión es facilitar el proceso de búsqueda y publicación
          de propiedades, brindando herramientas poderosas y una interfaz amigable que garantiza una experiencia sin complicaciones.</p>
      <p class="text-center paragraph">
          En CasaConect, valoramos la transparencia, la confianza y la satisfacción de nuestros usuarios. Ya sea que estés buscando
          un lugar para llamar hogar o que desees ofrecer tu propiedad al mercado, estamos aquí para apoyarte en cada paso del camino.</p>
      <p class="text-center paragraph">
          Explora nuestro catálogo diverso de propiedades en venta y arriendo, y aprovecha nuestras funcionalidades diseñadas
          para simplificar la gestión de tu perfil y la interacción con los interesados. Nuestro compromiso es proporcionarte
          una plataforma robusta y segura donde puedas encontrar exactamente lo que necesitas.</p>
      <p class="text-center paragraph">
          Únete a nuestra comunidad creciente de usuarios satisfechos y descubre por qué CasaConect es la opción
          preferida para aquellos que buscan propiedades y desean publicarlas con facilidad. ¡Estamos aquí para hacer realidad tus
          objetivos inmobiliarios!</p>
      <p class="text-center paragraph">Gracias por elegirnos para tu próxima aventura en el mundo de bienes raíces.</p>
      <p class="text-center paragraph">Atentamente,</p>
      <p class="text-center paragraph">El equipo de CasaConect</p>
    </div>
  </v-container>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()
</script>

<style scoped>
.paragraph{
  font-size: 16px;
  margin-left: 200px;
  margin-right: 200px;
  margin-bottom: 20px;
}.title{
  text-align: center;
  font-size: 24px;
  margin-bottom: 15px;
 }
</style>
