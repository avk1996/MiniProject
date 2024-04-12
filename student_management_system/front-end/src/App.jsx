import "./App.css";

// components
import Form from "./components/courses/CourseForm";
import Home from "./components/pages/Home";
import StudentForm from "./components/students/StudentForm";

//routes
import { Routes, Route } from "react-router-dom";
import Students from "./components/students/Students";

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route
          path="/new-form"
          element={
            <Form
              whichForm="new"
              formTitle="Add new Course"
              buttonType="Add Course"
            />
          }
        />
        <Route
          path="/edit-form/:id"
          element={
            <Form
              whichForm="edit"
              formTitle="Update the Course"
              buttonType="Update Course"
            />
          }
        />
        <Route path="/student-table/:courseId" element={<Students />} />
        <Route
          path="/student-new-form/:courseId"
          element={
            <StudentForm
              whichForm="new"
              formTitle="Add Student Info"
              buttonType="Add Student"
            />
          }
        />
        <Route
          path="/student-edit-form/:courseId/:studentId"
          element={
            <StudentForm
              whichForm="edit"
              formTitle="Update Student Info"
              buttonType="Update Student"
            />
          }
        />
      </Routes>
    </>
  );
}

export default App;
