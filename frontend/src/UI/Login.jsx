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
      className="flex min-h-screen items-center justify-center space-x-2 bg-black p-4 text-center"
    >
      <div className="flex h-[620px] w-[500px] flex-col items-center justify-start rounded-md bg-[#1c1c1c] pt-10">
        <img
          src="../../public/logobook.png"
          alt="logo"
          className="mb-10 w-24"
        />
        <h2 className="text-gray-300">welcome</h2>
        <div className="mb-4 w-72">
          <label className="text-gray-300">Username or email</label>
          <input
            name="User"
            className="mt-1 w-full rounded-full border border-gray-400 bg-transparent p-2 text-white focus:outline-none"
          />
        </div>
        <div className="mb-6 w-72">
          <label className="text-gray-300">Password</label>
          <input
            name="Password"
            type="password"
            className="mt-1 w-full rounded-full border border-gray-400 bg-transparent p-2 text-white focus:outline-none"
          />
        </div>
        <button
          type="submit"
          className="rounded-full border border-green-200 bg-green-400 px-6 py-2 text-white shadow-sm transition hover:bg-green-500"
        >
          Sing in
        </button>
      </div>
    </form>
  );
}

export default Login;
