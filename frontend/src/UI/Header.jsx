import { Link } from "react-router-dom";

function Header() {
  return (
    <header className="flex items-center justify-center border-b border-stone-200 bg-primary">
      <form className="space-x-4 px-4 py-3">
        <Link to="/">Login</Link>
        <Link to="/home">Home</Link>
        <Link to="/adminBooks">AdminBooks</Link>
      </form>
    </header>
  );
}

export default Header;
