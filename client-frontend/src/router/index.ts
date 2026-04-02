import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import CreateClientView from '../views/CreateClientView.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView,
  },
  {
    path: '/create',
    name: 'CreateClient',
    component: CreateClientView,
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
