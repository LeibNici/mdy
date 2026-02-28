<template>
  <section class="card-panel">
    <header class="card-title">{{ title }}</header>
    <div class="card-body"><slot /></div>
  </section>
</template>

<script setup lang="ts">
defineProps<{ title: string }>();
</script>