import { getBooks } from "./apibooks";

export async function loader() {
  const books = await getBooks();

  return books;
}
