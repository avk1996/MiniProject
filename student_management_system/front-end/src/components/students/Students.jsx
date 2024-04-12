import axios from "axios";
import React, { useState, useEffect } from "react";
import { server } from "../../server";
import { useNavigate, useParams } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faDesktop,
  faEdit,
  faLeftLong,
  faMagnifyingGlass,
  faPlusCircle,
  faTrash,
  faUserAstronaut,
} from "@fortawesome/free-solid-svg-icons";

function Students() {
  const [studentData, setStudentData] = useState([]);
  const { courseId } = useParams();

  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(`${server}/student`)
      .then((result) => {
        // console.log(result.data);
        setStudentData(result.data);
      })
      .catch((err) => {
        console.log("data recived failed due to Error: " + err);
      });
  }, []);

  const showStudent = (studentId) => {
    console.log("show student clicked " + studentId);
  };

  const editStudent = (studentId) => {
    console.log("edit student clicked");
    navigate(`/student-edit-form/${courseId}/${studentId}`);
  };

  const deleteStudent = (studentId) => {
    console.log("delete student clicked " + studentId);
    const confirm = window.confirm("Are you sure to delete this student?");
    if (confirm) {
      axios
        .delete(`${server}/student/${studentId}`)
        .then((result) => {
          console.log(`deleted ${result.data}`);
          window.location.reload();
        })
        .catch((err) => {
          console.log(`Error deleting data : ${err}`);
        });
    } else {
      console.log("data not deleted");
    }
  };

  const addNewStudent = () => {
    console.log("add new student clicked");
    navigate(`/student-new-form/${courseId}`);
  };

  const backToCourses = () => {
    navigate("/");
  };

  return (
    <>
      <div className="flex justify-between">
        <div className="justify-start m-3">
          <button
            className="bg-green-500 text-white hover:text-black hover:bg-white"
            onClick={backToCourses}
          >
            <FontAwesomeIcon icon={faLeftLong} /> &nbsp; Courses
          </button>
        </div>
        <div className="justify-end m-3">
          <button
            className="bg-green-500 text-white hover:text-black hover:bg-white"
            onClick={addNewStudent}
          >
            <FontAwesomeIcon icon={faPlusCircle} />
            &nbsp;Add Student
          </button>
        </div>
      </div>
      <table className="w-full border-2 overflow-hidden">
        <thead className="bg-orange-400 border-b-2 text-white">
          <tr>
            <th className="p-3 text-left font-semibold tracking-wide">
              Sr. No.
            </th>
            <th className="p-3 text-left  font-semibold tracking-wide">
              Email
            </th>
            <th className="p-3 text-left  font-semibold tracking-wide">
              First Name
            </th>
            <th className="p-3 text-left  font-semibold tracking-wide">
              Last Name
            </th>
            <th className="p-3 text-left  font-semibold tracking-wide">
              Scores Obtained
            </th>
            <th colSpan={3}>Action</th>
          </tr>
        </thead>
        <tbody className="divide-y-2">
          {studentData.map((student, index) => (
            <tr
              className={
                (index % 2 == 0 ? "bg-gray-700" : "bg-gray-500") +
                "cursor-pointer duration-100 hover:bg-white hover:text-black hover:scale-105 overflow-hidden"
              }
              key={student.studentsId}
            >
              <td className="p-2 text-lg hover:bg-gray-500">{index + 1}</td>
              <td className="p-2 text-lg hover:bg-gray-500">{student.email}</td>
              <td className="p-2 text-lg hover:bg-gray-500">
                {student.firstName}
              </td>
              <td className="p-2 text-lg hover:bg-gray-500">
                {student.lastName}
              </td>
              <td className="p-2 text-lg hover:bg-gray-500">
                {student.scoreObtained}
              </td>
              <td>
                <button
                  className="bg-green-600 m-3"
                  onClick={() => {
                    showStudent(student.studentsId);
                  }}
                >
                  <FontAwesomeIcon icon={faUserAstronaut} />
                </button>
              </td>
              <td>
                <button
                  className="bg-blue-600 m-3"
                  onClick={() => {
                    editStudent(student.studentsId);
                  }}
                >
                  <FontAwesomeIcon icon={faEdit} />
                </button>
              </td>
              <td>
                <button
                  className="bg-red-600 m-3"
                  onClick={() => {
                    deleteStudent(student.studentsId);
                  }}
                >
                  <FontAwesomeIcon icon={faTrash} />
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
}

export default Students;
