<template>
  <div class="home">
    <h1>Client Service UI</h1>
    <p>Use this UI to create a new client in your backend service.</p>
    <router-link to="/create" class="btn">Create client</router-link>
  </div>
</template>

<script setup lang="ts">
</script>

<style scoped>
.home {
  max-width: 640px;
  margin: 2rem auto;
  text-align: center;
}
.btn {
  display: inline-block;
  margin-top: 1rem;
  padding: 0.65rem 1.1rem;
  border-radius: 6px;
  background-color: #3b82f6;
  color: #fff;
  text-decoration: none;
}
</style>
