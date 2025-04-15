import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '@/views/HomePage.vue'
import LoginPage from '@/views/LoginPage.vue'
import DashBoard from '@/views/DashBoard.vue'
import EntidadesLista from '@/views/EntidadesLista.vue'
import UsuarioCadastro from '@/views/UsuarioCadastro.vue'
import EntidadesExcluir from '@/views/EntidadesExcluir.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomePage,
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginPage,
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: DashBoard,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: '/:entidade',
    name: 'ListaEntidades',
    component: EntidadesLista,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: '/usuarios/novo',
    name: 'UsuarioCadastro',
    component: UsuarioCadastro,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: '/:entidade/:id',
    name: 'EntidadesExcluir',
    component: EntidadesExcluir,
    meta: {
      requiresAuth: true,
    },
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

router.beforeEach((to, from, next) => {
  const isAuthenticated = localStorage.getItem('token')

  if (to.meta.requiresAuth && !isAuthenticated) {
    next('/login')
  } else {
    next()
  }
})

export default router
