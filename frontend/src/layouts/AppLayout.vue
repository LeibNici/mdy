<script setup lang="ts">
import { RouterLink, RouterView, useRoute } from "vue-router";

const route = useRoute();
const menus = [
  { to: "/", label: "概览" },
  { to: "/forms", label: "表单设计" },
  { to: "/data", label: "数据中心" },
  { to: "/workflow", label: "流程中心" },
  { to: "/reports", label: "报表中心" }
];
</script>

<template>
  <div class="app-shell">
    <aside class="sidebar">
      <div class="brand">JianDaoYun</div>
      <RouterLink
        v-for="m in menus"
        :key="m.to"
        :to="m.to"
        class="nav-item"
        :class="{ active: route.path === m.to }"
      >
        {{ m.label }}
      </RouterLink>
    </aside>

    <main class="main-content">
      <RouterView />
    </main>
  </div>
</template>