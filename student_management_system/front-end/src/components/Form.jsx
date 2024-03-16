import React, { useState } from "react";
import axios from "axios";

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

  const url = "http://localhost:8080/stuwork/sms";
  const handleSubmit = async (event) => {
    console.log("clicked submit button");
    console.log(courseData);
    event.preventDefault();
    const config = { headers: { "Content-Type": "application/json" } };
    axios
      .post(url, courseData, config)
      .then((result) => {
        console.log(result);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <>
      <h1>{props.title}</h1>
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
          <button type="submit" className="bg-yellow-600 rounded-full">
            Add course
          </button>
        </form>
      </div>
    </>
  );
}

export default Form;
