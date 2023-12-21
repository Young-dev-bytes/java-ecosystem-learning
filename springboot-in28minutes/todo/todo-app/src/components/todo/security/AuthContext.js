import { createContext, useContext, useState } from "react";
import { apiClient } from "../api/ApiClient";
import {
  executeBasicAuthenticationService,
  executeJwtAuthenticationService,
} from "../api/AuthenticationApiService";

export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({ children }) {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  const [username, setUsername] = useState(null);
  const [token, setToken] = useState(null);

  // const loginHandle = (username, password) => {
  //   if (username === "in28minutes" && password === "dummy") {
  //     setIsAuthenticated(true);
  //     setUsername(username);
  //     return true;
  //   } else {
  //     setIsAuthenticated(false);
  //     setUsername(null);
  //     return false;
  //   }
  // };

  /*
  async function loginHandle(username, password) {
    const baToken = "Basic " + window.btoa(username + ":" + password);

    try {
      const response = await executeBasicAuthenticationService(baToken);
      console.log(response);
      console.log(response.status == 200);
      if (response.status == 200) {
        setIsAuthenticated(true);
        setUsername(username);
        setToken(baToken);
        console.log("baToken", baToken);

        apiClient.interceptors.request.use((config) => {
          console.log("config");
          console.log("intercepting and adding a token");
          config.headers.Authorization = baToken;
          return config;
        });

        return true;
      } else {
        logoutHandle();
        return false;
      }
    } catch (error) {
      logoutHandle();
      return false;
    }
  }*/

  async function loginHandle(username, password) {
    try {
      const response = await executeJwtAuthenticationService(
        username,
        password
      );
      console.log(response);
      console.log(response.status == 200);
      if (response.status == 200) {
        const jwtToken = "Bearer " + response.data.token;
        setIsAuthenticated(true);
        setUsername(username);
        setToken(jwtToken);
        console.log("jwtToken:", jwtToken);

        apiClient.interceptors.request.use((config) => {
          console.log("config");
          console.log("intercepting and adding a token");
          config.headers.Authorization = jwtToken;
          return config;
        });

        return true;
      } else {
        logoutHandle();
        return false;
      }
    } catch (error) {
      logoutHandle();
      return false;
    }
  }

  const logoutHandle = () => {
    setIsAuthenticated(false);
    setUsername(null);
    setToken(null);
  };

  return (
    <AuthContext.Provider
      value={{
        isAuthenticated,
        loginHandle,
        logoutHandle,
        username,
        token,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}
