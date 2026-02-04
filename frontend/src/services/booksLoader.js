import { getBooks } from "./apibooks";

export async function booksLoader() {
  const books = await getBooks();
  console.log("BOOKS LOADER EXECUTED");
  return books;
}
