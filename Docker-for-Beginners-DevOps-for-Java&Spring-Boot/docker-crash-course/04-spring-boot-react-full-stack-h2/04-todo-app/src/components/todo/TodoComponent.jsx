import { useNavigate, useParams } from "react-router-dom";
import {
  createTodoApi,
  retrieveTodoByIdApi,
  updateTodoApi,
} from "./api/TodoApiService";
import { useEffect, useState } from "react";
import { ErrorMessage, Field, Form, Formik } from "formik";
import moment from "moment";

function TodoComponent() {
  const navigate = useNavigate();
  const { username, id } = useParams();
  const [description, setDescription] = useState("");
  const [targetDate, setTargetDate] = useState("");

  const retrieveTodo = () => {
    if (id != -1) {
      retrieveTodoByIdApi(username, id)
        .then((response) => {
          console.log(response);
          setDescription(response.data.description);
          setTargetDate(response.data.targetDate);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  const handleOnSubmit = (values) => {
    console.log("submit", values);
    const todo = {
      id,
      username,
      description: values.description,
      targetDate: values.targetDate,
      done: false,
    };

    console.log("todo", todo);

    console.log("id", id == -1);

    if (id == -1) {
      createTodoApi(username, todo)
        .then((response) => {
          console.log(response);
          navigate("/todos");
        })
        .catch((error) => console.log(error));
    } else {
      updateTodoApi(username, id, todo)
        .then((response) => {
          console.log(response);
          navigate("/todos");
        })
        .catch((error) => console.log(error));
    }
  };

  const handleValidate = (values) => {
    let errors = {
      // description: "Enter a valid description",
      // targetDate: "Enter a valid targetDate",
    };
    if (values.description.length <= 5) {
      errors.description = "Enter at least 5 characters";
    }

    if (
      values.targetDate == null ||
      values.targetDate == "" ||
      !moment(values.targetDate).isValid()
    ) {
      errors.targetDate = "Enter a target date";
    }
    console.log("validate", values);

    return errors;
  };

  useEffect(() => retrieveTodo(), [id]);

  return (
    <div className="container">
      <h1>Enter Todo Details</h1>
      <div>
        <Formik
          initialValues={{ description, targetDate }}
          enableReinitialize={true}
          onSubmit={handleOnSubmit}
          validate={handleValidate}
          validateOnChange={false}
          validateOnBlur={false}
        >
          {(props) => (
            <Form>
              <ErrorMessage
                name="description"
                component="div"
                className="alert alert-warning"
              />
              <ErrorMessage
                name="targetDate"
                component="div"
                className="alert alert-warning"
              />
              <fieldset className="form-group">
                <label>Description</label>
                <Field
                  type="text"
                  className="form-control"
                  name="description"
                />
              </fieldset>
              <fieldset className="form-group">
                <label>Target Date</label>
                <Field type="date" className="form-control" name="targetDate" />
              </fieldset>
              <div>
                <button className="btn btn-success m-5 " type="submit">
                  Save
                </button>
              </div>
            </Form>
          )}
        </Formik>
      </div>
    </div>
  );
}

export default TodoComponent;
