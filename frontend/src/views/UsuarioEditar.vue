<template>
  <v-container class="mt-4">
    <h1 class="text-h5 mb-4">Editar Usuário</h1>

    <v-form @submit.prevent="editarUsuario" ref="form">
      <v-text-field v-model="usuario.id" label="ID" required></v-text-field>

      <v-text-field v-model="usuario.name" label="Nome" required></v-text-field>

      <v-text-field v-model="usuario.email" label="Email" type="email" required></v-text-field>

      <v-text-field
        v-model="usuario.password"
        label="Senha"
        type="password"
        required
      ></v-text-field>

      <v-checkbox
        v-model="usuario.ativo"
        label="Usuário Ativo"
        :true-value="true"
        :false-value="false"
        class="mt-2"
      />

      <v-btn type="submit" color="primary" class="mt-4">Atualizar</v-btn>
    </v-form>
  </v-container>
</template>

<script setup>
import { ref } from 'vue'
import api from '@/api/axios'
import { useRouter } from 'vue-router'

const router = useRouter()
const usuario = ref({
  id: '',
  name: '',
  email: '',
  password: '',
  ativo: true,
})

const editarUsuario = async () => {
  try {
    const { id, ...usuarioSemId } = usuario.value
    const token = localStorage.getItem('token')
    await api.put(`/usuarios/${id}`, usuarioSemId, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    router.push('/dashboard')
  } catch (error) {
    console.error('Erro ao editar usuário:', error)
  }
}
</script>
