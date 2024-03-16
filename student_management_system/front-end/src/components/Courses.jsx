import axios from "axios";
import React, { useState, useEffect } from "react";
import { server } from "../server";
// import { useNavigate } from "react-router-dom";

function Courses(props) {
  const [courseData, setCourseData] = useState([]);
  // const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(`${server}`)
      .then((result) => {
        // console.log(result.data);
        setCourseData(result.data);
      })
      .catch((err) => {
        console.log("data recived failed due to Error: " + err);
      });
  });

  const showCourse = (event) => {
    console.log("show course clicked");
  };
  const editCourse = (event) => {
    console.log("edit course clicked");
  };
  const deleteCourse = (courseId) => {
    console.log("delete course clicked " + courseId);
    axios
      .delete(`${server}/${courseId}`)
      .then((result) => {
        console.log(`deleted ${result.data}`);
      })
      .catch((err) => {
        console.log(`Error deleting data`);
      });
  };

  return (
    <>
      <div>
        <table className="w-full border-2 overflow-hidden">
          <thead className="bg-orange-400 border-b-2 text-white">
            <tr>
              <th className="p-3 text-left font-semibold tracking-wide">
                Sr. No.
              </th>
              <th className="p-3 text-left  font-semibold tracking-wide">
                Title
              </th>
              <th className="p-3 text-left  font-semibold tracking-wide">
                Starting at
              </th>
              <th className="p-3 text-left  font-semibold tracking-wide">
                Ending at
              </th>
              <th className="p-3 text-left  font-semibold tracking-wide">
                Fees
              </th>
              <th className="p-3 text-left  font-semibold tracking-wide">
                Required Score
              </th>
              <th colSpan={3}>Action</th>
            </tr>
          </thead>
          <tbody className="divide-y-2">
            {courseData.map((course, index) => (
              <tr
                className={
                  (index % 2 == 0 ? "bg-gray-700" : "bg-gray-500") +
                  "cursor-pointer duration-100 hover:bg-white hover:text-black hover:scale-105 overflow-hidden"
                }
                key={course.courseId}
              >
                <td className="p-2 text-lg hover:bg-co">{index + 1}</td>
                <td className="p-2 text-lg hover:bg-co">
                  {course.courseTitle}
                </td>
                <td className="p-2 text-lg hover:bg-co">{course.startDate}</td>
                <td className="p-2 text-lg hover:bg-co">{course.endDate}</td>
                <td className="p-2 text-lg hover:bg-co">{course.fees}</td>
                <td className="p-2 text-lg hover:bg-co">{course.minScore}</td>
                <td>
                  <button
                    className="bg-green-600 m-3"
                    onClick={() => {
                      showCourse(course.courseId);
                    }}
                  >
                    Details
                  </button>
                </td>
                <td>
                  <button
                    className="bg-blue-600 m-3"
                    onClick={() => {
                      editCourse(course.courseId);
                    }}
                  >
                    Edit
                  </button>
                </td>
                <td>
                  <button
                    className="bg-red-600 m-3"
                    onClick={() => {
                      deleteCourse(course.courseId);
                    }}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
}

export default Courses;
