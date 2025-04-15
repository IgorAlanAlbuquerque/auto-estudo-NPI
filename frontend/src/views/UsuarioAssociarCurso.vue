<template>
  <v-container>
    <h1 class="text-h5 mb-4">Associar Curso</h1>
    <v-form @submit.prevent="associarCurso">
      <v-text-field
        v-model="usuario.id"
        label="ID do Usuário"
        type="number"
        required
      ></v-text-field>

      <v-select
        v-model="cursoSelecionado"
        :items="cursos"
        item-text="name"
        item-value="id"
        label="Selecione o Curso"
        required
      ></v-select>

      <v-btn type="submit" color="primary" class="mt-4"> Associar </v-btn>
    </v-form>
  </v-container>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import api from '@/api/axios'
import { useRouter } from 'vue-router'

const router = useRouter()

const usuario = ref({ id: null }) // Armazenando o ID do usuário
const cursoSelecionado = ref(null) // Armazenando o ID do curso selecionado
const cursos = ref([]) // Lista de cursos

onMounted(async () => {
  const token = localStorage.getItem('token')
  try {
    const response = await api.get('/cursos', {
      headers: { Authorization: `Bearer ${token}` },
    })
    cursos.value = response.data
  } catch (error) {
    console.error('Erro ao buscar cursos:', error)
  }
})

const associarCurso = async () => {
  const token = localStorage.getItem('token')
  if (usuario.value.id && cursoSelecionado.value) {
    try {
      await api.post(
        `/usuarios/${usuario.value.id}/curso/${cursoSelecionado.value}`,
        {},
        {
          headers: { Authorization: `Bearer ${token}` },
        },
      )
      alert('Curso associado com sucesso!')
      router.push('/dashboard')
    } catch (error) {
      console.error('Erro ao associar curso:', error)
    }
  }
}
</script>
