import { createRouter, createWebHistory } from "vue-router";
import AppLayout from "../layouts/AppLayout.vue";

const routes = [
  {
    path: "/",
    component: AppLayout,
    children: [
      { path: "", name: "dashboard", component: () => import("../views/DashboardView.vue") },
      { path: "forms", name: "forms", component: () => import("../views/FormDesignerView.vue") },
      { path: "data", name: "data", component: () => import("../views/DataCenterView.vue") },
      { path: "workflow", name: "workflow", component: () => import("../views/WorkflowView.vue") },
      { path: "reports", name: "reports", component: () => import("../views/ReportsView.vue") }
    ]
  }
];

export default createRouter({
  history: createWebHistory(),
  routes
});