import { API_URL } from "./apibooks";

export async function login(User, Password) {
  console.log({ User, Password });

  const res = await fetch(`${API_URL}/auth/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*",
    },
    body: JSON.stringify({ username: User, password: Password }),
  });

  console.log("status:", res.status);

  const data = await res.json();

  console.log(data);

  if (res.status != 200) {
    console.error(res.status);
    return;
  }
  /* const data = await res.json();

  console.log(data); */

  const token = data.accessToken;
  localStorage.setItem("token", token);

  const refreshToken = data.refreshToken;
  localStorage.setItem("rtoken", refreshToken);
  return data;
}
