import { defineStore } from "pinia";

export const useAppStore = defineStore("app", {
  state: () => ({
    loading: false
  }),
  actions: {
    setLoading(v: boolean) {
      this.loading = v;
    }
  }
});