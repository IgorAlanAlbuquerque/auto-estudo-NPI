<template>
  <v-container>
    <h1 class="text-h5 mb-4">Lista de {{ titulo }}</h1>
    <v-data-table
      :headers="headers"
      :items="dados"
      :items-per-page="5"
      class="elevation-1"
    ></v-data-table>
  </v-container>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { useRoute } from 'vue-router'
import api from '@/api/axios'

const route = useRoute()
const entidade = route.params.entidade // 'usuarios' ou 'cursos'

const dados = ref([])

const headersMap = {
  usuarios: [
    { text: 'ID', value: 'id' },
    { text: 'Nome', value: 'name' },
    { text: 'Email', value: 'email' },
    { text: 'Ativo', value: 'ativo' },
    { text: 'Curso', value: 'curso.name' },
  ],
  cursos: [
    { text: 'ID', value: 'id' },
    { text: 'Nome', value: 'name' },
    { text: 'Sigla', value: 'sigla' },
    { text: 'Turno', value: 'turno' },
  ],
}

const headers = computed(() => headersMap[entidade])
const titulo = computed(() => (entidade === 'usuarios' ? 'Usuários' : 'Cursos'))

onMounted(async () => {
  const token = localStorage.getItem('token')
  const response = await api.get(`/${entidade}`, {
    headers: { Authorization: `Bearer ${token}` },
  })
  dados.value = response.data
})
</script>
