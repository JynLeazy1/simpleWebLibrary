import { Link } from "react-router-dom";

function Header() {
  return (
    <header>
      <Link to="/">Home</Link>
      <p>Test space in Header</p>
    </header>
  );
}

export default Header;
