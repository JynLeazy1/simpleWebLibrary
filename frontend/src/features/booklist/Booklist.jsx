import { useLoaderData } from "react-router-dom";
import { Link } from "react-router-dom";
import Bookitem from "./Bookitem";

function Booklist() {
  const books = useLoaderData();
  console.log({ books });

  return (
    <ul role="list" className="space-y-1 divide-y divide-stone-200 p-4 px-2">
      {books.content.map((book) => (
        <Bookitem book={book} key={book.id}></Bookitem>
      ))}
    </ul>
  );
}

export default Booklist;
/* export async function loader() {
  console.log("loader");
  const books = await getBooks();
  return books;
} */
