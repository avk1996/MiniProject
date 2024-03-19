import "./App.css";

// components
import FormTest from "./components/FormTest";
import Home from "./components/Home";

//routes
import { Link, Routes, Route } from "react-router-dom";
//navigation
import { useNavigate } from "react-router-dom";

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route
          path="/new-form"
          element={
            <FormTest
              whichForm="new"
              formTitle="Add new Course"
              buttonType="Add Course"
            />
          }
        />
        <Route
          path="/edit-form/:id"
          element={
            <FormTest
              whichForm="edit"
              formTitle="Update the Course"
              buttonType="Update Course"
            />
          }
        />
      </Routes>
    </>
  );
}

export default App;
