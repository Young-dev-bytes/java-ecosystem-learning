import { useEffect, useState } from "react";
import {
  deleteTodoByIdApi,
  retrieveAllTodosForUsernameApi,
} from "./api/TodoApiService";
import { useAuth } from "./security/AuthContext";
import { useNavigate } from "react-router-dom";

export default function ListTodosComponent() {
  const { username } = useAuth();
  const [todos, setTodos] = useState([]);
  const [message, setMessage] = useState(null);
  const navigate = useNavigate();

  const refreshTodos = (username) => {
    retrieveAllTodosForUsernameApi(username)
      .then((response) => setTodos(response.data))
      .catch((error) => console.log(error))
      .finally(() => console.log("finally"));
  };

  const deleteTodo = (usernameParam, id) => {
    console.log(usernameParam, id);
    deleteTodoByIdApi(usernameParam, id)
      .then((response) => {
        console.log(response);
        setMessage(`delete of todo id is ${id} succeed !`);
        refreshTodos(username);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const updateTodo = (usernameParam, id) => {
    console.log(usernameParam, id);
    navigate(`/todo/${usernameParam}/${id}`);
  };

  const handleAddNewTodo = () => {
    navigate(`/todo/${username}/-1`);
  };

  useEffect(() => refreshTodos(username), [username]);

  return (
    <div className="container">
      <h1>Things You Want To Do!</h1>
      {message && <div className="alert alert-warning">{message}</div>}
      <div>
        <table className="table">
          <thead>
            <tr>
              {/* <th>Id</th> */}
              <th>Description</th>
              <th>Is Done?</th>
              <th>Target Date</th>
              <th>Delete</th>
              <th>Update</th>
            </tr>
          </thead>
          <tbody>
            {todos.map((element, index) => (
              <tr key={index}>
                {/* <td>{element.id}</td> */}
                <td>{element.description}</td>
                <td>{element.done.toString()}</td>
                <td>{element.targetDate}</td>
                <td>
                  <button
                    className="btn btn-warning"
                    onClick={() => deleteTodo(username, element.id)}
                  >
                    Delete
                  </button>
                </td>

                <td>
                  <button
                    className="btn btn-success"
                    onClick={() => updateTodo(username, element.id)}
                  >
                    Update
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div className="btn btn-success m-5" onClick={handleAddNewTodo}>
        Add New Todo
      </div>
    </div>
  );
}
