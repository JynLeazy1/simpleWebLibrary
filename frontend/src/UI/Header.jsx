import { Link } from "react-router-dom";

function Header() {
  return (
    <header>
      <Link to="/">Home</Link>
      <Link to="/adminBooks">AdminBooks</Link>
    </header>
  );
}

export default Header;
