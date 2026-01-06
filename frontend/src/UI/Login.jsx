import { useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();
  function handleSubmit(e) {
    e.preventDefault();
    navigate("/home");
  }

  return (
    <form onSubmit={handleSubmit}>
      <h2>welcome</h2>
      <input placeholder="User" />
      <br></br>
      <input placeholder="Password" type="password" />
      <button type="submit">Login</button>
    </form>
  );
}

export default Login;
