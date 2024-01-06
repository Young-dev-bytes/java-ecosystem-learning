import { useState } from "react";
import { Link, useParams } from "react-router-dom";
import { retrieveHelloWorldPathVariable } from "./api/HelloWorldApiService";
import { useAuth } from "./security/AuthContext";

export default function WelcomeComponent() {
  const { username } = useParams();
  const [message, setMessage] = useState(null);
  const { token } = useAuth();

  const callHelloWorldRestApi = () => {
    // axios
    //   .get("http://localhost:8080/hello-world")
    //   .then((response) => successfulResponse(response))
    //   .catch((error) => errorResponse(error))
    //   .finally(() => console.log("clean up"));

    // retrieveHelloWorldBean()
    //   .then((response) => successfulResponse(response))
    //   .catch((error) => errorResponse(error))
    //   .finally(() => console.log("clean up"));

    retrieveHelloWorldPathVariable("Young", token)
      .then((response) => successfulResponse(response))
      .catch((error) => errorResponse(error))
      .finally(() => console.log("clean up"));
  };

  const successfulResponse = (response) => {
    console.log("successfulResponse");
    console.log(response);
    setMessage(response.data.message);
  };
  const errorResponse = (error) => {
    console.log("errorResponse");
    console.log(error);
  };
  return (
    <div className="welcomeComponent">
      <h1>Welcome {username}</h1>
      <div>
        {/* Manage your todos - <a href="/todos">Go here</a> */}
        Manage your todos - <Link to="/todos">Go here</Link>
      </div>
      <div>
        <button className="btn btn-success m-5" onClick={callHelloWorldRestApi}>
          Call Hello World
        </button>
        {message}
      </div>
    </div>
  );
}
