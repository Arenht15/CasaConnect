<template>
       <v-footer app color="primary" dark>
      <v-container>
        <v-row>
          <v-col>
            <div class="footer-contact-info">
                <span class="white--text">
                    <v-icon>mdi-email</v-icon> CasaConect@example.cl
                </span>
                <span class="white--text ml-4">
                    <v-icon>mdi-phone</v-icon> +1234567890
                </span>
                <span class= "white--text ml-4"> 
                    <v-icon>mdi-instagram</v-icon> @CasaConect
                </span>
            </div>
          </v-col>
        </v-row>
      </v-container>
    </v-footer>
</template>

<script>
export default {
  name: 'AcercaDe'
}
</script>

<style scoped>
p {
    font-weight: bold;
}
</style>
