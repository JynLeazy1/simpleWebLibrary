import { getBooks, getBookById } from "./apibooks";

export async function booksLoader() {
  const books = await getBooks();
  console.log("BOOKS LOADER EXECUTED");
  return books;
}

export async function bookDetailsLoader({ params }) {
  return await getBookById(params.bookId);
}
