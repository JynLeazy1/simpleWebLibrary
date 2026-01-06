import { Link } from "react-router-dom";

function Header() {
  return (
    <header>
      <form>
        <Link to="/">Login</Link>
        <Link to="/home">Home</Link>
        <Link to="/adminBooks">AdminBooks</Link>
      </form>
    </header>
  );
}

export default Header;
