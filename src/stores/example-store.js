import { defineStore } from "pinia";
import Api from "./Api";
import { LocalStorage } from "quasar";
export const useCounterStore = defineStore("user-auth", {
  state: () => ({
    userProfile: null,
    apiToken: null,
  }),
  getters: {},

  actions: {
    async UserRegister(payload) {
      return Api.UserRegister(payload);
    },
    async userLogin(payload) {
      const res = await Api.UserLogin(payload);
      this.userProfile = res.data?.data;
      this.apiToken = res.data?.data[0]?.login_access_token_translate;
      LocalStorage.set("userInfo", this.userProfile);
      return res;
    },
    async ForgotPassword(payload) {
      return Api.ForgotPassword(payload);
    },
    async UpdatePassword(payload) {
      const headers = {
        Authorization: `Bearer ${this.apiToken}`,
        "Content-Type": "application/json",
      };
      return Api.UpdatePassword(payload, headers);
    },
    async UpdateEmail(payload) {
      const headers = {
        Authorization: `Bearer ${this.apiToken}`,
        "Content-Type": "application/json",
      };
      return Api.UpdateEmail(payload, headers);
    },
    async UpdateName(payload) {
      const headers = {
        Authorization: `Bearer ${this.apiToken}`,
        "Content-Type": "application/json",
      };
      return Api.UpdateName(payload, headers);
    },
    logout() {
      this.$reset(); // Reset the state of the store
      LocalStorage.remove("userInfo"); // Remove the persisted user info from LocalStorage
    },
  },
  persist: {
    enabled: true,
    strategies: [
      {
        key: "mymanu_auth",
        storage: ["userAuthInfo", "apiToken"],
      },
    ],
  },
});
