import { useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();
  function handleSubmit(e) {
    e.preventDefault();
    navigate("/home");
  }

  return (
    <form onSubmit={handleSubmit} className="justify-end flex p-4 text-center bg-primary space-x-2">
      <h2 className="">welcome</h2>
        <input placeholder="User" className=""/>
        <input placeholder="Password" type="password" className="" />
        <button type="submit" className="bg-success">Login</button>
    </form>
  );
}

export default Login;
