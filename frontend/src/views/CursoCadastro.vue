<template>
  <v-container>
    <h1 class="text-h5 mb-4">Cadastrar Novo Curso</h1>

    <v-form @submit.prevent="cadastrarCurso" ref="form">
      <v-text-field v-model="curso.name" label="Nome do Curso" required></v-text-field>

      <v-text-field v-model="curso.sigla" label="Sigla com duas letras" required></v-text-field>

      <v-text-field v-model="curso.turno" label="Turno" required></v-text-field>

      <v-btn type="submit" color="primary" class="mt-4">Cadastrar</v-btn>
    </v-form>
  </v-container>
</template>

<script setup>
import { ref } from 'vue'
import api from '@/api/axios'
import { useRouter } from 'vue-router'
const router = useRouter()
const curso = ref({
  name: '',
  sigla: '',
  turno: '',
})

const cadastrarCurso = async () => {
  try {
    const token = localStorage.getItem('token')
    console.log('Dados enviados:', curso.value)
    await api.post('/cursos', curso.value, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    router.push('/dashboard')
  } catch (error) {
    console.error('Erro ao cadastrar curso:', error)
    console.log('Detalhes do erro:', error.response?.data)
  }
}
</script>
