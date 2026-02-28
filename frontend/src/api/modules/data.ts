import http from "../http";

export interface FormRecord {
  id: string;
  formId: string;
  createdAt: string;
  [key: string]: unknown;
}

export const dataApi = {
  submit: (payload: { formId: string; data: Record<string, unknown> }) =>
    http.post<FormRecord>("/data/submit", payload),
  listByForm: (formId: string) => http.get<FormRecord[]>(`/data/${formId}`)
};