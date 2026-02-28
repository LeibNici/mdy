<script setup lang="ts">
import { reactive, ref } from "vue";
import CardPanel from "../components/CardPanel.vue";
import { formApi, type FormDef } from "../api/modules/forms";

const forms = ref<FormDef[]>([]);
const creating = ref(false);
const form = reactive({
  name: "请假申请",
  description: "默认演示表单"
});

async function loadForms() {
  forms.value = await formApi.list();
}

async function createDemoForm() {
  creating.value = true;
  try {
    await formApi.create({
      name: form.name,
      description: form.description,
      fields: [
        { key: "name", label: "姓名", type: "TEXT", required: true },
        { key: "days", label: "天数", type: "NUMBER", required: true }
      ]
    });
    await loadForms();
  } finally {
    creating.value = false;
  }
}

loadForms();
</script>

<template>
  <div class="page-grid">
    <CardPanel title="创建演示表单">
      <div class="form-row">
        <input v-model="form.name" placeholder="表单名称" />
      </div>
      <div class="form-row">
        <input v-model="form.description" placeholder="表单描述" />
      </div>
      <button :disabled="creating" @click="createDemoForm">{{ creating ? "创建中..." : "创建" }}</button>
    </CardPanel>

    <CardPanel title="表单列表">
      <ul class="list">
        <li v-for="f in forms" :key="f.id">{{ f.name }}（{{ f.id }}）</li>
      </ul>
    </CardPanel>
  </div>
</template>