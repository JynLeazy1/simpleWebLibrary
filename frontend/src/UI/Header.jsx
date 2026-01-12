import { Link } from "react-router-dom";

function Header() {
  return (
    <header className="bg-lime-500">
      <form>
        <Link to="/">Login</Link>
        <Link to="/home">Home</Link>
        <Link to="/adminBooks">AdminBooks</Link>
      </form>
    </header>
  );
}

export default Header;
