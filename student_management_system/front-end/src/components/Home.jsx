import React from "react";

// react router dom
import { useNavigate } from "react-router-dom";

// components
import Courses from "./Courses";

function Home() {
  const navigate = useNavigate();
  const addNewCourse = () => {
    console.log("add course clicked");
    navigate("/new-form");
  };
  return (
    <>
      <div className="flex justify-center">
        <h1>Student Management System</h1>
      </div>
      <div className="flex justify-end m-3">
        <button
          className="bg-green-500 text-white hover:text-black hover:bg-white"
          onClick={addNewCourse}
        >
          Add Course
        </button>
      </div>
      <Courses />
    </>
  );
}

export default Home;
