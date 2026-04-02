<template>
  <div class="form-root">
    <h2>Create a new client</h2>

    <form @submit.prevent="createClient">
      <label>
        Name
        <input v-model="form.name" type="text" required />
      </label>

      <label>
        Email
        <input v-model="form.email" type="email" required />
      </label>

      <label>
        maxCameras
        <input v-model.number="form.maxCameras" type="number" min="0" />
      </label>

      <label>
        storageLimitGb
        <input v-model.number="form.storageLimitGb" type="number" min="0" />
      </label>

      <button type="submit" :disabled="busy">Create client</button>
    </form>

    <p v-if="apiMessage" class="api-message">{{ apiMessage }}</p>

    <router-link to="/">Back to home</router-link>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { clientApi } from '../services/clientApi'

interface ClientForm {
  name: string
  email: string
  maxCameras: number | null
  storageLimitGb: number | null
}

const form = ref<ClientForm>({
  name: '',
  email: '',
  maxCameras: null,
  storageLimitGb: null,
})
const apiMessage = ref('')
const busy = ref(false)

const createClient = async () => {
  busy.value = true
  apiMessage.value = ''

  try {
    const data = await clientApi.createClient({
      name: form.value.name,
      email: form.value.email,
    })
    apiMessage.value = `Created client ID ${data.id}`
    form.value = { name: '', email: '', maxCameras: null, storageLimitGb: null }
  } catch (error: unknown) {
    if (error instanceof Error) {
      apiMessage.value = `Error: ${error.message}`
    } else {
      apiMessage.value = 'Error: Unknown error'
    }
  } finally {
    busy.value = false
  }
}
</script>

<style scoped>
.form-root {
  max-width: 520px;
  margin: 1.8rem auto;
  display: flex;
  flex-direction: column;
}
label {
  margin-bottom: 0.8rem;
  display: flex;
  flex-direction: column;
}
input {
  padding: 0.5rem;
  border: 1px solid #cbd5e1;
  border-radius: 6px;
}
button {
  margin-top: 1rem;
  padding: 0.65rem 1rem;
  background-color: #10b981;
  color: #fff;
  border: none;
  border-radius: 6px;
  cursor: pointer;
}
button:disabled {
  opacity: 0.7;
  cursor: not-allowed;
}
.api-message {
  margin: 1rem 0;
  color: #1f2937;
  background: #e5e7eb;
  border: 1px solid #cbd5e1;
  padding: 0.8rem;
  border-radius: 4px;
}
</style>
