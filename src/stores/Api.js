import axios from "axios";
const API_URL = "https://dev.cehwapi.mymanu.com/api/v1";

const RESOURCE_NAME = "preference";
const headers = {
  Accept: "application/json",
  "Content-Type": "application/json",
  lang: "en",
};
export default {
  UserLogin(payload) {
    return axios.post(`${API_URL}/user/login`, JSON.stringify(payload), {
      headers: headers,
    });
  },
  UserRegister(payload) {
    return axios.post(`${API_URL}/user/signup`, JSON.stringify(payload), {
      headers: headers,
    });
  },
  ForgotPassword(payload) {
    return axios.post(`${API_URL}/user/forgot-password`, payload);
  },
  ResetPassword(payload) {
    return axios.post(`${API_URL}/user/reset-password`, payload);
  },
  UpdatePassword(payload, headers) {
    return axios.post(`${API_URL}/${RESOURCE_NAME}/update-password`, payload, {
      headers: headers,
    });
  },
  UpdateEmail(payload, headers) {
    return axios.post(`${API_URL}/${RESOURCE_NAME}/update-email`, payload, {
      headers: headers,
    });
  },
  UpdateName(payload, headers) {
    return axios.post(`${API_URL}/${RESOURCE_NAME}/update-name`, payload, {
      headers: headers,
    });
  },
};
