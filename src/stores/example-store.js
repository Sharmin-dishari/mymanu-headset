import { defineStore } from "pinia";
import Api from "./Api";
export const useCounterStore = defineStore("counter", {
  state: () => ({
    counter: 0,
  }),
  getters: {
    doubleCount: (state) => state.counter * 2,
  },
  actions: {
    UserRegister() {
      Api.UserRegister(payload).then((response) => {});
    },
    UserLogin(payload) {
      Api.UserLogin(payload).then((response) => {});
    },
    ForgotPassword(payload) {
      Api.ForgotPassword(payload).then((response) => {});
    },
    UpdatePassword(payload) {
      Api.UpdatePassword(payload).then((response) => {});
    },
    UpdateEmail(payload) {
      Api.UpdateEmail(payload).then((response) => {});
    },
    UpdateName(payload) {
      Api.UpdateName(payload).then((response) => {});
    },
  },
});
