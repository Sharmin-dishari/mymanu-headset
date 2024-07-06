import { defineStore } from "pinia";
import Api from "./Api";
import { LocalStorage } from "quasar";

export const useCounterStore = defineStore("counter", {
  state: () => ({
    counter: 0,
    userProfile: null,
    accessToken: null,
  }),
  getters: {
    doubleCount: (state) => state.counter * 2,
  },

  actions: {
    UserRegister(payload) {
      Api.UserRegister(payload).then((response) => {});
    },
    async UserLogin(payload) {
      const data = await Api.UserLogin(payload);
      console.log(data, "dd");
      return data;
      LocalStorage.set("userInfo", this.userProfile);
    },
    ForgotPassword(payload) {
      Api.ForgotPassword(payload).then((response) => {});
    },
    UpdatePassword(payload) {
      const headers = {
        Authorization: `Bearer ${this.accessToken}`,
        "Content-Type": "application/json",
      };
      Api.UpdatePassword(payload, headers).then((response) => {});
    },
    UpdateEmail(payload) {
      const headers = {
        Authorization: `Bearer ${this.accessToken}`,
        "Content-Type": "application/json",
      };
      Api.UpdateEmail(payload, headers).then((response) => {});
    },
    UpdateName(payload) {
      const headers = {
        Authorization: `Bearer ${this.accessToken}`,
        "Content-Type": "application/json",
      };
      Api.UpdateName(payload, headers).then((response) => {});
    },
  },
});
