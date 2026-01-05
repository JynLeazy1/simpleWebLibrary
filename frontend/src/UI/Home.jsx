import { Link } from "react-router-dom";
import Adminbooks from "../features/adminbooks/Adminbooks";
import Booklist from "../features/booklist/Booklist";

function Home() {
  return (
    <div>
      <h1>
        Simple Web Library 📚
        <br />
        "Every book is a door to a new world. Open it, read, and experience
        stories that will stay with you beyond the last page."
      </h1>
      <Link to="/bookList">Booklist</Link>
    </div>
  );
}

export default Home;
