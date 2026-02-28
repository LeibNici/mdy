import axios, { type AxiosRequestConfig } from "axios";
import type { ApiResponse } from "../types/api";

const instance = axios.create({
  baseURL: import.meta.env.VITE_API_BASE || "/api",
  timeout: 10000
});

async function request<T>(config: AxiosRequestConfig): Promise<T> {
  const resp = await instance.request<ApiResponse<T>>(config);
  const body = resp.data;
  if (typeof body?.code === "number") {
    if (body.code !== 200) {
      throw new Error(body.message || "request failed");
    }
    return body.data;
  }
  return resp.data as T;
}

const http = {
  get<T>(url: string, config?: AxiosRequestConfig): Promise<T> {
    return request<T>({ ...config, url, method: "GET" });
  },
  post<T>(url: string, data?: unknown, config?: AxiosRequestConfig): Promise<T> {
    return request<T>({ ...config, url, data, method: "POST" });
  },
  put<T>(url: string, data?: unknown, config?: AxiosRequestConfig): Promise<T> {
    return request<T>({ ...config, url, data, method: "PUT" });
  },
  del<T>(url: string, config?: AxiosRequestConfig): Promise<T> {
    return request<T>({ ...config, url, method: "DELETE" });
  }
};

export default http;