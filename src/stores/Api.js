import axios from "axios";
const API_URL = "https://cehwapi.mymanu.com/api/v1";
const RESOURCE_NAME = "preference";
export default {
  UserLogin(payload) {
    return axios.post(`${API_URL}/user`, payload);
  },
  UserRegister(payload) {
    return axios.post(`${API_URL}/signup`, payload);
  },
  ForgotPassword(payload) {
    return axios.post(`${API_URL}/forgot-password`, payload);
  },
  UpdatePassword(payload) {
    return axios.post(`${API_URL}/${RESOURCE_NAME}/update-password`, payload);
  },
  UpdateEmail(payload) {
    return axios.post(`${API_URL}/${RESOURCE_NAME}/update-email`, payload);
  },
  UpdateName(payload) {
    return axios.post(`${API_URL}/${RESOURCE_NAME}/update-name`, payload);
  },
};
