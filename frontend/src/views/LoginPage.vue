<template>
  <v-container class="fill-height d-flex justify-center align-center" fluid>
    <v-card class="pa-6" max-width="400" style="min-width: 300px">
      <v-card-title class="text-h5">Login</v-card-title>
      <v-card-text>
        <v-form @submit.prevent="handleLogin">
          <v-text-field
            v-model="email"
            label="Email"
            type="email"
            required
            style="width: 100%; min-width: 250px"
          ></v-text-field>

          <v-text-field
            v-model="senha"
            label="Senha"
            type="password"
            required
            style="width: 100%; min-width: 250px"
          ></v-text-field>

          <v-btn
            class="mt-4"
            type="submit"
            color="primary"
            block
            style="width: 100%; min-width: 150px"
          >
            Entrar
          </v-btn>
        </v-form>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const email = ref('')
const senha = ref('')
const router = useRouter()

async function handleLogin() {
  try {
    const response = await axios.post('http://localhost:8080/login', {
      email: email.value,
      senha: senha.value,
    })

    console.log(response)
    const token = response.data.token
    localStorage.setItem('token', token)

    router.push('/dashboard') // ou a rota que você quiser
  } catch {
    alert('Email ou senha inválidos')
  }
}
</script>

<style scoped>
.v-container {
  height: 100vh;
  background-color: #1976d2;
  margin: 0;
}

.v-text-field {
  width: 250px;
}

.v-btn {
  width: 150px;
}
</style>
