import http from "../http";

export interface ReportSummary {
  formId: string;
  totalRecords: number;
}

export const reportApi = {
  formSummary: (formId: string) => http.get<ReportSummary>(`/reports/forms/${formId}/summary`)
};