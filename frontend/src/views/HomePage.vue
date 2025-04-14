<template>
  <v-container class="home-container fill-height pa-0 ma-0" fluid>
    <v-btn class="login-btn" @click="goToLogin"> Login </v-btn>

    <v-spacer></v-spacer>

    <h1 class="title">Auto Estudo NPI - Igor</h1>

    <v-spacer></v-spacer>
  </v-container>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

function goToLogin() {
  router.push('/login')
}
</script>

<style scoped>
.home-container {
  background-color: #1976d2;
  color: white;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  font-family: 'Roboto', sans-serif;
}

.title {
  font-size: 4rem;
  font-weight: bold;
}

.login-btn {
  position: absolute;
  top: 20px;
  right: 20px;
  background-color: #d32f2f;
  color: white;
  font-weight: bold;
  border-radius: 8px;
  padding: 10px 20px;
  font-size: 1.2rem;
  cursor: pointer;
}

.login-btn:hover {
  background-color: #c2185b;
}
</style>
