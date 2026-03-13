import { API_URL } from "./apibooks";

export async function login(User, Password) {
  console.log({ User, Password });

  const res = await fetch(`${API_URL}/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*",
    },
    body: JSON.stringify({ user: User, password: Password }),
  });

  console.log("status:", res.status);

  if (res.status != 200) {
    console.error(res.status);
    return;
  }
  const data = await res.json();

  console.log(data);

  const token = data.token;
  localStorage.setItem("token", token);
  return data;
}
