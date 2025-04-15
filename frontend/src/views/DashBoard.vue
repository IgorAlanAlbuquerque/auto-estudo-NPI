<template>
  <v-container class="dashboard-container">
    <h1 class="text-h4 mb-6">Dashboard</h1>

    <v-row>
      <v-col cols="12" md="6">
        <h2 class="text-h6 mb-4">Usuários</h2>
        <v-btn block class="mb-2" color="primary" @click="goTo('/usuarios')">Listar Usuários</v-btn>
        <v-btn block class="mb-2" color="primary" @click="goTo('/usuarios/novo')"
          >Cadastrar Usuário</v-btn
        >
        <v-btn block class="mb-2" color="primary" @click="goTo('/usuarios/buscar')"
          >Buscar Usuário</v-btn
        >
        <v-btn block class="mb-2" color="primary" @click="goTo('/usuarios/editar')"
          >Editar Usuário</v-btn
        >
        <v-btn block class="mb-2" color="primary" @click="goTo('/usuarios/excluir')"
          >Excluir Usuário</v-btn
        >
        <v-btn block class="mb-2" color="primary" @click="goTo('/usuarios/associar-curso')"
          >Associar Curso</v-btn
        >
      </v-col>

      <v-col cols="12" md="6">
        <h2 class="text-h6 mb-4">Cursos</h2>
        <v-btn block class="mb-2" color="secondary" @click="goTo('/cursos')">Listar Cursos</v-btn>
        <v-btn block class="mb-2" color="secondary" @click="goTo('/cursos/novo')"
          >Cadastrar Curso</v-btn
        >
        <v-btn block class="mb-2" color="secondary" @click="goTo('/cursos/buscar')"
          >Buscar Curso</v-btn
        >
        <v-btn block class="mb-2" color="secondary" @click="goTo('/cursos/editar')"
          >Editar Curso</v-btn
        >
        <v-btn block class="mb-2" color="secondary" @click="goTo('/cursos/excluir')"
          >Excluir Curso</v-btn
        >
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()

function goTo(path) {
  router.push(path)
}
</script>

<style scoped>
.dashboard-container {
  padding: 40px;
  font-family: 'Roboto', sans-serif;
}
</style>
