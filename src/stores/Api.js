import axios from "axios";
const API_URL = "https://cehwapi.mymanu.com/api/v1";
const RESOURCE_NAME = "preference";
export default {
  UserLogin(payload) {
    return axios.post(`${API_URL}/user/login`, payload, {
      headers: {
        Accept: "application/json",
        "Content-Type": "multipart/form-data",
        "Access-Control-Allow-Origin": "*",
      },
    });
  },
  UserRegister(payload) {
    return axios.post(`${API_URL}/user/signup`, payload, {
      headers: {
        Accept: "application/json",
        "Content-Type": "multipart/form-data",
        "Access-Control-Allow-Origin": "*",
      },
    });
  },
  ForgotPassword(payload) {
    return axios.post(`${API_URL}/user/forgot-password`, payload);
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
