<template>
  <v-container>
    <h1 class="text-h5 mb-4">Excluir {{ titulo }}</h1>
    <v-form @submit.prevent="excluirEntidade">
      <v-text-field v-model="id" label="ID" type="number" required></v-text-field>
      <v-btn type="submit" color="primary" class="mt-4"> Excluir </v-btn>
    </v-form>
  </v-container>
</template>

<script setup>
import { ref, computed } from 'vue'
import api from '@/api/axios'
import { useRouter, useRoute } from 'vue-router'

const router = useRouter()
const route = useRoute()

const entidade = computed(() => route.path.split('/')[1]) // Pega o primeiro segmento da rota (cursos ou usuarios)
const titulo = computed(() => (entidade.value === 'usuarios' ? 'Usuário' : 'Curso'))
const id = ref(null)

const excluirEntidade = async () => {
  if (!id.value) {
    console.error('O ID é obrigatório.')
    return
  }
  try {
    const token = localStorage.getItem('token')
    await api.delete(`/${entidade.value}/${id.value}`, {
      headers: { Authorization: `Bearer ${token}` },
    })
    router.push('/dashboard')
  } catch (error) {
    console.error(`Erro ao excluir ${titulo.value.toLowerCase()}:`, error)
  }
}
</script>
