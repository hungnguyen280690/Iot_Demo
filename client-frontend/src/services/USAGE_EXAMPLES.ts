/**
 * AXIOS API USAGE EXAMPLES
 * 
 * This file demonstrates how to use the axios API client and services
 * in your Vue components.
 */

// ============================================
// Example 1: LOGIN AND STORE TOKEN
// ============================================
import { clientApi, type Client , type LoginRequest} from '@/services/clientApi'

export const exampleLogin = async () => {
  try {
    const credentials: LoginRequest = {
      email: 'user@example.com',
      password: 'password123',
    }

    const response = await clientApi.login(credentials)
    // Token is automatically stored in localStorage
    console.log('Logged in user:', response.user)
  } catch (error) {
    console.error('Login failed:', error)
  }
}

// ============================================
// Example 2: GET ALL CLIENTS
// ============================================
export const exampleGetClients = async () => {
  try {
    const clients = await clientApi.getClients()
    // Token is automatically sent in Authorization header
    console.log('Clients:', clients)
  } catch (error) {
    console.error('Failed to fetch clients:', error)
  }
}

// ============================================
// Example 3: CREATE A CLIENT
// ============================================
export const exampleCreateClient = async () => {
  try {
    const newClient = await clientApi.createClient({
      name: 'John Doe',
      email: 'john@example.com',
    })
    console.log('Created client:', newClient)
  } catch (error) {
    console.error('Failed to create client:', error)
  }
}

// ============================================
// Example 4: UPDATE A CLIENT
// ============================================
export const exampleUpdateClient = async (clientId: string) => {
  try {
    const updated = await clientApi.updateClient(clientId, {
      name: 'Jane Doe',
    })
    console.log('Updated client:', updated)
  } catch (error) {
    console.error('Failed to update client:', error)
  }
}

// ============================================
// Example 5: DELETE A CLIENT
// ============================================
export const exampleDeleteClient = async (clientId: string) => {
  try {
    await clientApi.deleteClient(clientId)
    console.log('Client deleted')
  } catch (error) {
    console.error('Failed to delete client:', error)
  }
}

// ============================================
// Example 6: LOGOUT AND CLEAR TOKEN
// ============================================
export const exampleLogout = () => {
  clientApi.logout()
  // Token is removed from localStorage
  // Redirect to login page as needed
  window.location.href = '/login'
}

// ============================================
// VUE COMPONENT EXAMPLE
// ============================================
/**
<template>
  <div>
    <button @click="loadClients">Load Clients</button>
    <ul>
      <li v-for="client in clients" :key="client.id">
        {{ client.name }} ({{ client.email }})
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { clientApi, Client } from '@/services/clientApi'

const clients = ref<Client[]>([])

const loadClients = async () => {
  try {
    clients.value = await clientApi.getClients()
  } catch (error) {
    console.error('Failed to load clients:', error)
  }
}
</script>
*/
