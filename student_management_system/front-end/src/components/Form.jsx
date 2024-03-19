import React, { useState } from "react";
import axios from "axios";
import { server } from "../server";
import { useNavigate } from "react-router-dom";

function Form(props) {
  const [courseData, setCourseData] = useState({
    course_title: "",
    start_date: "",
    end_date: "",
    fees: 0.0,
    min_score: 0,
  });

  const handleChange = (e) => {
    setCourseData({ ...courseData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (event) => {
    console.log("clicked submit button");
    console.log(courseData);
    event.preventDefault();
    const config = { headers: { "Content-Type": "application/json" } };
    await axios
      .post(`${server}`, courseData, config)
      .then((result) => {
        navigate("/");
        console.log(result);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const navigate = useNavigate();
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
      <div className="flex justify-center">
        <h1 className="p-2 rounded text-white cursor-pointer duration-100 hover:text-orange-500">
          {props.title}
        </h1>
      </div>
      <div className="m-4 p-4">
        <form onSubmit={handleSubmit}>
          <table className="flex-auto">
            <tbody>
              <tr>
                <td>
                  <label
                    className="text-left p-2 text-lg"
                    htmlFor="course_title"
                  >
                    Course title:{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="text"
                    id="courseTitle"
                    name="courseTitle"
                    placeholder="course name"
                    onChange={handleChange}
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label className="text-left p-2 text-lg" htmlFor="start_date">
                    Start date:{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="text"
                    id="startDate"
                    name="startDate"
                    placeholder="course start date"
                    onChange={handleChange}
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label className="text-left p-2 text-lg" htmlFor="end_date">
                    End date:{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="text"
                    id="endDate"
                    name="endDate"
                    placeholder="course end date"
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
                    placeholder="fees"
                    onChange={handleChange}
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label className="text-left p-2 text-lg" htmlFor="min_score">
                    Min score:{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="text"
                    id="minScore"
                    name="minScore"
                    placeholder="min score"
                    onChange={handleChange}
                  />
                </td>
              </tr>
            </tbody>
          </table>
          <div>
            <button
              type="submit"
              className="rounded-full p-3 m-3 border-4 border-orange-500 cursor-pointer duration-300 hover:bg-orange-500 hover:text-black hover:border-white"
            >
              Add course
            </button>
          </div>
        </form>
      </div>
    </>
  );
}

export default Form;
