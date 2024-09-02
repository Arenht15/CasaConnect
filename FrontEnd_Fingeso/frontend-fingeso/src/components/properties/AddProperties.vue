<template>
  <v-app id="inspire">
    <v-main>
      <h1 class="title">Se parte de nuestra Comunidad</h1>
      <v-container fill-height>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="6" lg="4">
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>Agrega tu vivienda</v-toolbar-title>
                <v-spacer></v-spacer>
                <v-btn icon @click="$router.push('/properties')">
                  <v-icon>mdi-close</v-icon>
                </v-btn>
              </v-toolbar>
              <v-card-text>
                <v-alert
                  v-if="messageInfo.show"
                  :type="messageInfo.type"
                  dismissible
                  @click:close="messageInfo.show = false"
                >
                  {{ messageInfo.text }}
                </v-alert>
                <v-form @submit.prevent="handleRegister">
                  <v-text-field
                    name="housecode"
                    label="Codigo del hogar"
                    type="housecode"
                    v-model="housecode"
                    :rules="[v => !!v || 'Campo requerido']"
                  ></v-text-field>
                  <v-autocomplete
                    v-model="ubicacion"
                    :items="ubicacionOptions"
                    label="Ubicación"
                    placeholder="Selecciona o agrega una ubicación"
                    :rules="[v => !!v || 'Campo requerido']"
                    clearable
                    chips
                    solo
                    hide-no-data
                    hide-details
                    @change="addUbicacionOption"
                  ></v-autocomplete>
                  <v-text-field
                    id="habitation"
                    name="habitation"
                    label="Habitación"
                    type="habitation"
                    v-model="habitation"
                    :rules="[v => !!v || 'Campo requerido']"
                  ></v-text-field>
                  <v-text-field
                    id="price"
                    name="price"
                    label="Precio"
                    type="price"
                    v-model="price"
                    :rules="[v => !!v || 'Campo requerido']"
                  ></v-text-field>
                  <v-text-field
                    id="state"
                    name="state"
                    label="Estado"
                    type="state"
                    v-model="state"
                    :rules="[v => !!v || 'Campo requerido']"
                  ></v-text-field>
                  <v-text-field
                    id="description"
                    name="description"
                    label="Descripción"
                    type="description"
                    v-model="description"
                    :rules="[v => !!v || 'Campo requerido']"
                  ></v-text-field>
                  <v-expansion-panels>
                    <v-expansion-panel>
                      <template v-slot:header>
                        <div>Tipo de propiedad</div>
                      </template>
                      <v-radio-group v-model="houseType">
                        <v-radio label="Departamento" value="Departamento"></v-radio>
                        <v-radio label="Casa" value="Casa"></v-radio>
                      </v-radio-group>
                    </v-expansion-panel>
                    <v-expansion-panel>
                      <template v-slot:header>
                        <div>Agregar Fotos</div>
                      </template>
                      <v-file-input
                        v-model="housePhotos"
                        label="Subir Fotos"
                        multiple
                        accept="image/*"
                        prepend-icon="mdi-camera"
                      ></v-file-input>
                    </v-expansion-panel>
                  </v-expansion-panels>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="handleRegister" :disabled="!isValid">Añadir</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useHouseStore } from '@/stores/house'

const router = useRouter()
const houseStore = useHouseStore()

const housecode = ref('')
const ubicacion = ref('')
const ubicacionOptions = ref(['Santiago', 'Valparaíso', 'Concepción']) // Opciones iniciales
const habitation = ref('')
const price = ref('')
const state = ref('')
const description = ref('')
const houseType = ref('')
const houseIntention = ref('')
const housePhotos = ref([])
const houseTitlle = ref('')
const messageInfo = ref({ show: false, text: '', type: 'info' })

const isValid = computed(() =>
  housecode.value && ubicacion.value && habitation.value && price.value && state.value && description.value
)

const handleRegister = async () => {
  try {
    const success = await houseStore.register(housecode.value, ubicacion.value, habitation.value, price.value, state.value, description.value, houseType.value, houseIntention.value, housePhotos.value, houseTitlle.value)
    if (success) {
      showMessage("Registro exitoso", 'success')
      setTimeout(() => router.push("/properties"), 2000) // Redirige después de 2 segundos
    }
  } catch (error) {
    showMessage(error.message, 'error')
  }
}

const showMessage = (text, type) => {
  messageInfo.value = { show: true, text, type }
  setTimeout(() => messageInfo.value = { show: false, text: "", type: "" }, 2000)
}

const addUbicacionOption = (newUbicacion) => {
  if (newUbicacion && !ubicacionOptions.value.includes(newUbicacion)) {
    ubicacionOptions.value.push(newUbicacion)
  }
}
</script>

<style scoped>
.register {
  width: 10000px;
  margin: 0 auto;
}
.input-group {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.input-group label {
  margin-right: 10px;
}

#inspire {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
.title {
  margin-top: 30px;
  text-align: center;
  color: #4CAF50;
}
</style>