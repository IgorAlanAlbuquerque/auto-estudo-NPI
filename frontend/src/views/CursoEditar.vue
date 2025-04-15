<template>
  <v-container>
    <h1 class="text-h5 mb4">Editar Curso</h1>

    <v-form @submit.prevent="editarCurso" ref="form">
      <v-text-field v-model="curso.id" label="ID" required></v-text-field>

      <v-text-field v-model="curso.name" label="Nome" required></v-text-field>

      <v-text-field v-model="curso.sigla" label="Sigla com duas letras" required></v-text-field>

      <v-text-field
        v-model="curso.turno"
        label="Turno (MANHA, TARDE ou NOITE)"
        required
      ></v-text-field>

      <v-btn type="submit" color="primary" class="mt-4">Atualizar</v-btn>
    </v-form>
  </v-container>
</template>

<script setup>
import { ref } from 'vue'
import api from '@/api/axios'
import { useRouter } from 'vue-router'

const router = useRouter()

const curso = ref({
  id: '',
  name: '',
  sigla: '',
  turno: '',
})

const editarCurso = async () => {
  try {
    const { id, ...cursoSemId } = curso.value
    console.log('cursoSemId:', cursoSemId)
    console.log('id:', id)
    const token = localStorage.getItem('token')
    await api.put(`/cursos/${id}`, cursoSemId, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    router.push('/dashboard')
  } catch (error) {
    console.error('Erro ao editar curso:', error)
  }
}
</script>
