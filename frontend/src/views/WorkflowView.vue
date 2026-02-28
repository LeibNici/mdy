<script setup lang="ts">
import { reactive, ref } from "vue";
import CardPanel from "../components/CardPanel.vue";
import { workflowApi, type WorkflowInstance } from "../api/modules/workflow";

const startForm = reactive({ formId: "", recordId: "", applicant: "admin" });
const approveForm = reactive({ instanceId: "", taskId: "", approver: "manager", approved: true });
const current = ref<WorkflowInstance | null>(null);

async function start() {
  const created = await workflowApi.start(startForm);
  current.value = created;
  approveForm.instanceId = created.id;
  approveForm.taskId = created.currentTaskId;
}

async function approve() {
  current.value = await workflowApi.approve(approveForm);
}
</script>

<template>
  <div class="page-grid">
    <CardPanel title="启动流程">
      <input v-model="startForm.formId" placeholder="formId" />
      <input v-model="startForm.recordId" placeholder="recordId" />
      <input v-model="startForm.applicant" placeholder="applicant" />
      <button @click="start">启动</button>
    </CardPanel>

    <CardPanel title="审批流程">
      <input v-model="approveForm.instanceId" placeholder="instanceId" />
      <input v-model="approveForm.taskId" placeholder="taskId" />
      <input v-model="approveForm.approver" placeholder="approver" />
      <select v-model="approveForm.approved">
        <option :value="true">通过</option>
        <option :value="false">驳回</option>
      </select>
      <button @click="approve">提交审批</button>
    </CardPanel>

    <CardPanel title="流程状态">
      <pre class="code-block">{{ current }}</pre>
    </CardPanel>
  </div>
</template>