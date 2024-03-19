import axios from "axios";
import React, { useState } from "react";
import { useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { server } from "../server";

function EditForm(props) {
  // from url id is extracted
  const { id } = useParams();
  const navigate = useNavigate();

  const [newData, setNewData] = useState({
    course_title: "",
    start_date: "",
    end_date: "",
    fees: 0.0,
    min_score: 0,
  });

  useEffect(() => {
    axios
      .get(`${server}/${id}`)
      .then((result) => {
        setNewData(result.data);
      })
      .catch((err) => {
        console.log("error getting data: " + err);
      });
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNewData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (event) => {
    console.log("handle submit clicked");
    console.log("new data: " + newData.courseTitle);
    event.preventDefault();
    const config = { headers: { "Content-Type": "application/json" } };
    axios
      .put(`${server}/${id}`, newData, config)
      .then((response) => {
        console.log(response.data + " Update successful");
        navigate("/");
      })
      .catch((err) => {
        console.log("Unable to update: " + err);
      });
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
                    value={newData.courseTitle}
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
                    value={newData.startDate}
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
                    value={newData.endDate}
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
                    type="number"
                    id="fees"
                    name="fees"
                    value={newData.fees}
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
                    type="number"
                    id="minScore"
                    name="minScore"
                    value={newData.minScore}
                    onChange={handleChange}
                  />
                </td>
              </tr>
              <tr>
                <td colSpan={2}>
                  <button
                    type="submit"
                    className="rounded-full p-3 m-3 border-4 border-orange-500 cursor-pointer duration-300 hover:bg-orange-500 hover:text-black hover:border-white"
                  >
                    {props.buttonName}
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </form>
      </div>
    </>
  );
}

export default EditForm;
