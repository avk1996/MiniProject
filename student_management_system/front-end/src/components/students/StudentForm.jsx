import React, { useState, useEffect, useRef } from "react";
import axios from "axios";
import { server } from "../../server";
import { useNavigate, useParams } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faArrowRight,
  faCancel,
  faCow,
  faHomeAlt,
  faLeftLong,
  faSubscript,
} from "@fortawesome/free-solid-svg-icons";

function FormTest(props) {
  // update form data:
  const navigate = useNavigate();

  const [newData, setNewData] = useState({
    email: "",
    firstName: "",
    lastName: "",
    scoresObtained: 0.0,
  });

  const handleChange = (e) => {
    const { id, value } = e.target;
    setNewData((prevData) => ({
      ...prevData,
      [id]: value,
    }));
  };

  const { courseId } = useParams();
  const { studentId } = useParams();
  console.log(`ids are course ${courseId} & student ${studentId}`);

  if (props.whichForm === "edit") {
    // page loading data
    useEffect(() => {
      console.log(`inside ${props.whichForm}`);
      axios
        .get(`${server}/student/${studentId}`)
        .then((result) => {
          setNewData(result.data);
        })
        .catch((err) => {
          console.log("error getting data: " + err);
        });
    }, [studentId]);
    // console.log(
    //   `${newData.courseTitle}, ${newData.startDate}, ${newData.endDate}, ${newData.fees}, ${newData.minScore}`
    // );
  }

  const handleSubmit = async (event) => {
    console.log("clicked submit button");
    event.preventDefault();
    const config = { headers: { "Content-Type": "application/json" } };
    // console.log(newData);
    if (props.whichForm === "new") {
      await axios
        .post(`${server}/student`, newData, config)
        .then((result) => {
          console.log(result.data);
          navigate(`/student-table/${courseId}`);
        })
        .catch((err) => {
          console.log(err);
        });
    } else if (props.whichForm === "edit") {
      console.log("in edit");
      // console.log(newData);
      await axios
        .put(`${server}/student/${studentId}`, newData, config)
        .then((response) => {
          console.log(response.data + " Update successful");
          navigate(`/student-table/${courseId}`);
        })
        .catch((err) => {
          console.log("Unable to update: " + err);
        });
    }
  };

  const goHome = () => {
    navigate("/");
  };
  const studentsTable = () => {
    navigate(`/student-table/${courseId}`);
  };

  return (
    <>
      <div className="flex justify-between">
        <div className="m-3 justify-end">
          <button
            className="bg-orange-600 text-lg p-2 rounded-full duration-150 hover:bg-black hover:scale-105"
            onClick={studentsTable}
          >
            <FontAwesomeIcon icon={faLeftLong} />
            &nbsp; Students Table
          </button>
        </div>
        <div className="m-3 justify-start">
          <button
            className="bg-orange-600 text-lg p-2 rounded-full duration-150 hover:bg-black hover:scale-105"
            onClick={goHome}
          >
            <FontAwesomeIcon icon={faHomeAlt} /> &nbsp; Home
          </button>
        </div>
      </div>
      <div className="flex justify-start">
        <h1 className="p-2 rounded text-white cursor-pointer duration-100 hover:text-orange-500">
          {props.formTitle}
        </h1>
      </div>
      <div className="m-4 p-4">
        <form onSubmit={handleSubmit}>
          <table className="flex-auto">
            <tbody>
              <tr>
                <td>
                  <label
                    className="text-left p-2 text-lg justify-start"
                    htmlFor="firstName"
                  >
                    First name:
                  </label>
                </td>
                <td>
                  <input
                    className="justify-start"
                    type="text"
                    id="firstName"
                    name="firstName"
                    placeholder={props.whichForm === "new" ? "first name" : ""}
                    value={
                      props.whichForm === "new" ? undefined : newData.firstName
                    }
                    onChange={handleChange}
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label className="text-left p-2 text-lg" htmlFor="lastName">
                    Last Name:
                  </label>
                </td>
                <td>
                  <input
                    type="text"
                    id="lastName"
                    name="lastName"
                    placeholder={
                      props.whichForm === "new" ? "last name" : undefined
                    }
                    value={
                      props.whichForm === "new" ? undefined : newData.lastName
                    }
                    onChange={handleChange}
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label className="text-left p-2 text-lg" htmlFor="email">
                    Email:{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="email"
                    id="email"
                    name="email"
                    placeholder={
                      props.whichForm === "new" ? "enter email" : undefined
                    }
                    value={
                      props.whichForm === "new" ? undefined : newData.email
                    }
                    onChange={handleChange}
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label
                    className="text-left p-2 text-lg"
                    htmlFor="scoreObtained"
                  >
                    Score Obtained:{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="number"
                    id="scoreObtained"
                    name="scoreObtained"
                    placeholder={
                      props.whichForm === "new" ? "score obtained" : ""
                    }
                    value={
                      props.whichForm === "new"
                        ? undefined
                        : newData.scoreObtained
                    }
                    onChange={handleChange}
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label className="text-left p-2 text-lg" htmlFor="courseId">
                    Course ID:{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="number"
                    id="courseId"
                    name="courseId"
                    value={courseId}
                    readOnly
                    onChange={handleChange}
                  />
                </td>
              </tr>
            </tbody>
          </table>
          <div className="flex justify-between">
            <div className="justify-start">
              <button
                type="button"
                className="rounded-full p-3 m-3 border-4 border-orange-500 cursor-pointer duration-300 hover:bg-orange-500 hover:text-black hover:border-white"
                onClick={() => navigate(`/student-table/${courseId}`)}
              >
                <FontAwesomeIcon icon={faCancel} />
                &nbsp; Cancel
              </button>
            </div>
            <div className="justify-end">
              <button
                type="submit"
                className="rounded-full p-3 m-3 border-4 border-orange-500 cursor-pointer duration-300 hover:bg-orange-500 hover:text-black hover:border-white"
              >
                <FontAwesomeIcon icon={faCow} />
                &nbsp; {props.buttonType}
              </button>
            </div>
          </div>
        </form>
      </div>
    </>
  );
}

export default FormTest;
