import { useNavigate } from "react-router-dom";
import { login } from "../services/login";

function Login() {
  const navigate = useNavigate();
  function handleSubmit(e) {
    e.preventDefault();

    const formData = new FormData(e.target);
    const User = formData.get("User");
    const Password = formData.get("Password");

    console.log(User, Password);

    login(User, Password);
    navigate("/home");
  }

  return (
    <form
      onSubmit={handleSubmit}
      className="flex justify-end space-x-2 bg-primary p-4 text-center"
    >
      <h2 className="">welcome</h2>
      <input name="User" placeholder="User" className="" />
      <input
        name="Password"
        placeholder="Password"
        type="password"
        className=""
      />
      <button type="submit" className="bg-success">
        Login
      </button>
    </form>
  );
}

export default Login;
