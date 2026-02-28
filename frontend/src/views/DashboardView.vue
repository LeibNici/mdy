<script setup lang="ts">
import { computed, onMounted, ref } from "vue";
import CardPanel from "../components/CardPanel.vue";
import { formApi } from "../api/modules/forms";

const totalForms = ref(0);
const backendOrigin = computed(() => location.origin.replace(":5173", ":8080"));

onMounted(async () => {
  try {
    const forms = await formApi.list();
    totalForms.value = forms.length;
  } catch {
    totalForms.value = 0;
  }
});
</script>

<template>
  <div class="page-grid">
    <CardPanel title="系统概览">
      <p>当前已创建表单：{{ totalForms }}</p>
      <p>后端地址：{{ backendOrigin }}</p>
    </CardPanel>
    <CardPanel title="架构说明">
      <p>前端：Vue 3 + Vite + TypeScript + Pinia + Router</p>
      <p>后端：Spring Boot REST API（/api/*）</p>
    </CardPanel>
  </div>
</template>