<script setup lang="ts">
import { reactive, ref } from "vue";
import CardPanel from "../components/CardPanel.vue";
import { dataApi, type FormRecord } from "../api/modules/data";

const formId = ref("");
const records = ref<FormRecord[]>([]);
const payload = reactive({
  name: "张三",
  days: 1
});

async function submit() {
  if (!formId.value.trim()) return;
  await dataApi.submit({
    formId: formId.value,
    data: {
      name: payload.name,
      days: Number(payload.days)
    }
  });
  records.value = await dataApi.listByForm(formId.value);
}

async function query() {
  if (!formId.value.trim()) return;
  records.value = await dataApi.listByForm(formId.value);
}
</script>

<template>
  <div class="page-grid">
    <CardPanel title="数据提交">
      <div class="form-row">
        <input v-model="formId" placeholder="formId" />
      </div>
      <div class="form-row">
        <input v-model="payload.name" placeholder="name" />
      </div>
      <div class="form-row">
        <input v-model.number="payload.days" type="number" placeholder="days" />
      </div>
      <div class="row-inline">
        <button @click="submit">提交</button>
        <button @click="query">查询</button>
      </div>
    </CardPanel>

    <CardPanel title="记录列表">
      <pre class="code-block">{{ records }}</pre>
    </CardPanel>
  </div>
</template>