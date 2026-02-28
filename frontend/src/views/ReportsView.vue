<script setup lang="ts">
import { ref } from "vue";
import CardPanel from "../components/CardPanel.vue";
import { reportApi, type ReportSummary } from "../api/modules/reports";

const formId = ref("");
const summary = ref<ReportSummary | null>(null);

async function query() {
  if (!formId.value.trim()) return;
  summary.value = await reportApi.formSummary(formId.value);
}
</script>

<template>
  <div class="page-grid">
    <CardPanel title="报表查询">
      <input v-model="formId" placeholder="formId" />
      <button @click="query">查询汇总</button>
    </CardPanel>

    <CardPanel title="汇总结果">
      <pre class="code-block">{{ summary }}</pre>
    </CardPanel>
  </div>
</template>