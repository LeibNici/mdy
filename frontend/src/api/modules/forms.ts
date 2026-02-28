import http from "../http";

export interface FieldDef {
  key: string;
  label: string;
  type: "TEXT" | "NUMBER" | "BOOLEAN" | "DATE";
  required: boolean;
}

export interface FormDef {
  id: string;
  name: string;
  description?: string;
  fields: FieldDef[];
}

export const formApi = {
  list: () => http.get<FormDef[]>("/forms"),
  getById: (formId: string) => http.get<FormDef>(`/forms/${formId}`),
  create: (payload: { name: string; description?: string; fields: FieldDef[] }) =>
    http.post<FormDef>("/forms", payload)
};