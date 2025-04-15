<template>
  <v-container class="mt-4">
    <h1 class="text-h5 mb-4">Cadastrar Novo Usuário</h1>

    <v-form @submit.prevent="cadastrarUsuario" ref="form">
      <v-text-field v-model="usuario.name" label="Nome" required></v-text-field>

      <v-text-field v-model="usuario.email" label="Email" type="email" required></v-text-field>

      <v-text-field
        v-model="usuario.password"
        label="Senha"
        type="password"
        required
      ></v-text-field>

      <v-btn type="submit" color="primary" class="mt-4">Cadastrar</v-btn>
    </v-form>
  </v-container>
</template>

<script setup>
import { ref } from 'vue'
import api from '@/api/axios'
import { useRouter } from 'vue-router'

const router = useRouter()

const usuario = ref({
  name: '',
  email: '',
  password: '',
  ativo: true,
})

const cadastrarUsuario = async () => {
  try {
    const token = localStorage.getItem('token')
    console.log('Token:', token)
    const response = await api.post('/usuarios', usuario.value, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    console.log('Usuário cadastrado:', response.data)
    router.push('/dashboard')
  } catch (error) {
    console.error('Erro ao cadastrar usuário:', error)
  }
}
</script>
