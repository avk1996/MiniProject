import "./App.css";
import Courses from "./components/Courses";
import Form from "./components/Form";

function App() {
  return (
    <>
      <div className="bg-yellow-600 text-white text-4xl font-mono rounded-full p-4">
        Student Management System
      </div>
      <Router>
        <Form title="Launch Course" />
        <Courses />
      </Router>
    </>
  );
}

export default App;
