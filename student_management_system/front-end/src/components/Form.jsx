import React, { useState } from "react";

function Form(props) {
  const [courseData, setCourseData] = useState({
    course_title: "",
    start_date: "",
    end_date: "",
    fees: 0.0,
    min_score: 0,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCourseData({ ...courseData, [name]: value });
  };

  const url = "http://localhost:8080/stuwork/sms";
  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log("inside submit");
    const response = fetch(url, {
      method: "POST",
      mode: "cors",
      credentials: "same-origin",
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
                    id="course_title"
                    name="course_title"
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
                    id="start_date"
                    name="start_date"
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
                    id="end_date"
                    name="end_date"
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
                    id="min_score"
                    name="min_score"
                    placeholder="min score"
                    onChange={handleChange}
                  />
                </td>
              </tr>
            </tbody>
          </table>
          <button type="button">Add course</button>
        </form>
      </div>
    </>
  );
}

export default Form;
