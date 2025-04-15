<template>
  <v-container>
    <h1 class="text-h5 mb-4">Buscar {{ titulo }}</h1>
    <v-form @submit.prevent="buscarEntidade">
      <v-text-field v-model="id" label="ID" type="number" required></v-text-field>
      <v-btn type="submit" color="primary" class="mt-4"> Buscar </v-btn>
    </v-form>

    <div v-if="resultado">
      <h2 class="text-h6 mt-4">Resultado:</h2>
      <v-row v-if="resultado">
        <v-col v-if="entidade === 'usuarios'" cols="12" md="6">
          <div><strong>Nome:</strong> {{ resultado.name }}</div>
        </v-col>

        <v-col v-if="entidade === 'usuarios'" cols="12" md="6">
          <div><strong>Email:</strong> {{ resultado.email }}</div>
        </v-col>

        <v-col v-if="entidade === 'usuarios'" cols="12" md="6">
          <div>
            <strong>Status:</strong>
            <v-chip :color="resultado.ativo ? 'green' : 'red'">{{
              resultado.ativo ? 'Ativo' : 'Inativo'
            }}</v-chip>
          </div>
        </v-col>

        <v-col v-if="entidade === 'usuarios'" cols="12" md="6">
          <div><strong>Curso:</strong> {{ resultado.curso }}</div>
        </v-col>

        <v-col v-if="entidade === 'cursos'" cols="12" md="6">
          <div><strong>Nome do Curso:</strong> {{ resultado.name }}</div>
        </v-col>

        <v-col v-if="entidade === 'cursos'" cols="12" md="6">
          <div><strong>Sigla:</strong> {{ resultado.sigla }}</div>
        </v-col>

        <v-col v-if="entidade === 'cursos'" cols="12" md="6">
          <div><strong>Turno:</strong> {{ resultado.turno }}</div>
        </v-col>
      </v-row>
    </div>
  </v-container>
</template>

<script setup>
import { ref, computed } from 'vue'
import api from '@/api/axios'
import { useRoute } from 'vue-router'

const route = useRoute()

const entidade = computed(() => route.path.split('/')[1]) // Pega o primeiro segmento da rota (cursos ou usuarios)
const titulo = computed(() => (entidade.value === 'usuarios' ? 'Usuário' : 'Curso'))
const id = ref(null)
const resultado = ref(null)

const buscarEntidade = async () => {
  console.log(entidade.value)
  try {
    const token = localStorage.getItem('token')
    const response = await api.get(`/${entidade.value}/${id.value}`, {
      headers: { Authorization: `Bearer ${token}` },
    })
    resultado.value = response.data
  } catch (error) {
    console.error(`Erro ao buscar ${titulo.value.toLowerCase()}:`, error)
  }
}
</script>
