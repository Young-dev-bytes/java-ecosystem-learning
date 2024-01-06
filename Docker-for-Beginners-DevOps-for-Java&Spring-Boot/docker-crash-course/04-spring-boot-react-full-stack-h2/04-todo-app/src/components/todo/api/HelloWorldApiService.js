import { apiClient } from "./ApiClient";

// const apiClient = axios.create({
//   baseURL: "http://localhost:8080",
// });

export const retrieveHelloWorldBean = () => apiClient.get("/hello-world-bean");

export const retrieveHelloWorldPathVariable = (username, token) =>
  apiClient.get(`/hello-world/path-variable/${username}`);
