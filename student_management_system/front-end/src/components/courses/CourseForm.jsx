import React, { useState, useEffect, useRef } from "react";
import axios from "axios";
import { server } from "../../server";
import { useNavigate, useParams } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCancel, faCow } from "@fortawesome/free-solid-svg-icons";

function FormTest(props) {
  // update form data:
  const navigate = useNavigate();

  const [newData, setNewData] = useState({
    courseTitle: "",
    startDate: "",
    endDate: "",
    fees: 0.0,
    minScore: 0,
  });

  const handleChange = (e) => {
    const { id, value } = e.target;
    setNewData((prevData) => ({
      ...prevData,
      [id]: value,
    }));
  };

  const { id } = useParams();

  if (props.whichForm === "edit") {
    // page loading data
    useEffect(() => {
      console.log(`inside ${props.whichForm}`);

      axios
        .get(`${server}/course/${id}`)
        .then((result) => {
          setNewData(result.data);
        })
        .catch((err) => {
          console.log("error getting data: " + err);
        });
    }, [id]);

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
        .post(`${server}/course`, newData, config)
        .then((result) => {
          console.log(result.data);
          navigate("/");
        })
        .catch((err) => {
          console.log(err);
        });
    } else if (props.whichForm === "edit") {
      console.log("in edit");
      // console.log(newData);
      await axios
        .put(`${server}/course/${id}`, newData, config)
        .then((response) => {
          console.log(response.data + " Update successful");
          navigate("/");
        })
        .catch((err) => {
          console.log("Unable to update: " + err);
        });
    }
  };

  const goHome = () => {
    navigate("/");
  };

  return (
    <>
      <div className="m-3 flex justify-end">
        <button
          className="bg-orange-600 text-lg p-2 rounded-full duration-150 hover:bg-black hover:scale-105"
          onClick={goHome}
        >
          Home
        </button>
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
                    htmlFor="courseTitle"
                  >
                    Course title:{" "}
                  </label>
                </td>
                <td>
                  <input
                    className="justify-start"
                    type="text"
                    id="courseTitle"
                    name="courseTitle"
                    placeholder={props.whichForm === "new" ? "course name" : ""}
                    value={
                      props.whichForm === "new"
                        ? undefined
                        : newData.courseTitle
                    }
                    onChange={handleChange}
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label className="text-left p-2 text-lg" htmlFor="startDate">
                    Start date:{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="date"
                    id="startDate"
                    name="startDate"
                    placeholder={
                      props.whichForm === "new"
                        ? "course start date"
                        : undefined
                    }
                    value={
                      props.whichForm === "new" ? undefined : newData.startDate
                    }
                    onChange={handleChange}
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label className="text-left p-2 text-lg" htmlFor="endDate">
                    End date:{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="date"
                    id="endDate"
                    name="endDate"
                    placeholder={
                      props.whichForm === "new" ? "course end date" : undefined
                    }
                    value={
                      props.whichForm === "new" ? undefined : newData.endDate
                    }
                    onChange={handleChange}
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label className="text-left p-2 text-lg" htmlFor="fees">
                    Fees:{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="text"
                    id="fees"
                    name="fees"
                    placeholder={props.whichForm === "new" ? "fees" : ""}
                    value={props.whichForm === "new" ? undefined : newData.fees}
                    onChange={handleChange}
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label className="text-left p-2 text-lg" htmlFor="minScore">
                    Min score:{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="text"
                    id="minScore"
                    name="minScore"
                    placeholder={
                      props.whichForm === "new" ? "min score" : undefined
                    }
                    value={
                      props.whichForm === "new" ? undefined : newData.minScore
                    }
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
                onClick={() => navigate("/")}
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
