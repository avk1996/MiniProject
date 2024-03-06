import React from "react";

function Form(props) {
  return (
    <>
      <h1>{props.title}</h1>
      <div className="m-4 p-4">
        <table className="flex-auto">
          <tbody>
            <tr>
              <td>
                <label className="text-left p-2 text-lg" htmlFor="course_title">
                  Course title:{" "}
                </label>
              </td>
              <td>
                <input
                  type="text"
                  id="course_title"
                  placeholder="course name"
                />
              </td>
            </tr>
            <tr>
              <td>
                <label className="text-left p-2 text-lg" htmlFor="course_title">
                  Start date:{" "}
                </label>
              </td>
              <td>
                <input
                  type="text"
                  id="course_title"
                  placeholder="course start date"
                />
              </td>
            </tr>
            <tr>
              <td>
                <label className="text-left p-2 text-lg" htmlFor="course_title">
                  End date:{" "}
                </label>
              </td>
              <td>
                <input
                  type="text"
                  id="course_title"
                  placeholder="course end date"
                />
              </td>
            </tr>
            <tr>
              <td>
                <label className="text-left p-2 text-lg" htmlFor="course_title">
                  Fees:{" "}
                </label>
              </td>
              <td>
                <input type="text" id="course_title" placeholder="fees" />
              </td>
            </tr>
            <tr>
              <td>
                <label className="text-left p-2 text-lg" htmlFor="course_title">
                  Min score:{" "}
                </label>
              </td>
              <td>
                <input type="text" id="course_title" placeholder="min score" />
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </>
  );
}

export default Form;
