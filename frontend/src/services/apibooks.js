const API_URL = "http://localhost:8080/api";

export async function getBooks() {
  const res = await fetch(`${API_URL}/books`, { headers: { Accept: "*/*" } });

  // fetch won't throw error on 400 errors (e.g. when URL is wrong), so we need to do it manually. This will then go into the catch block, where the message is set
  if (!res.ok) throw Error("Failed getting books");

  const books = await res.json();
  return books;
}

export async function postBook(book) {
  const res = await fetch(`${API_URL}/book`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Accept: "*/*",
    },
    body: JSON.stringify(book),
  });

  if (!res.ok) {
    throw Error("Failed creating book");
  }

  return await res.json();
}
