<template>
  <div id="app">
    <header class="topbar">
      <nav class="nav">
        <router-link to="/">Home</router-link>
        <router-link to="/create">Create client</router-link>
      </nav>
    </header>

    <main>
      <router-view />
    </main>
  </div>
</template>

<style scoped>
.topbar {
  background: #1f2937;
  color: #fff;
  padding: 0.75rem;
}
.nav {
  display: flex;
  gap: 1rem;
}
.nav a {
  color: #e5e7eb;
  text-decoration: none;
}
.nav a.router-link-active {
  font-weight: 700;
  color: #fff;
}
main {
  padding: 1.2rem;
}
</style>
