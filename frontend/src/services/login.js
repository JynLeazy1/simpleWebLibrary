import { API_URL } from "./apibooks";

export async function login(user, password) {
  const res = await fetch(`${API_URL}/book`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*",
    },
    body: JSON.stringify([user, password]),
  });
}
