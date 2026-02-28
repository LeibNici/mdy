import http from "../http";

export interface WorkflowInstance {
  id: string;
  formId: string;
  recordId: string;
  applicant: string;
  currentTaskId: string;
  status: "PENDING" | "APPROVED" | "REJECTED";
}

export const workflowApi = {
  start: (payload: { formId: string; recordId: string; applicant: string }) =>
    http.post<WorkflowInstance>("/workflow/start", payload),
  approve: (payload: {
    instanceId: string;
    taskId: string;
    approver: string;
    approved: boolean;
    comment?: string;
  }) => http.post<WorkflowInstance>("/workflow/approve", payload),
  getById: (instanceId: string) => http.get<WorkflowInstance>(`/workflow/${instanceId}`)
};