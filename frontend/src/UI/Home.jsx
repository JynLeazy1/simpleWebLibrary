import { Link } from "react-router-dom";
import Adminbooks from "../features/adminbooks/Adminbooks";
import Booklist from "../features/booklist/Booklist";

function Home() {
  return (
    <div className="my-10 text-center">
      <h1 className="mb-8 text-xl font-semibold">
        Simple Web Library 📚
        <br />
        <span className="text-secondary">
        "Every book is a door to a new world. Open it, read, and experience
        stories that will stay with you beyond the last page."
        </span>
      </h1>
      <Link to="/bookList" className="">Booklist</Link>
    </div>
  );
}

export default Home;
