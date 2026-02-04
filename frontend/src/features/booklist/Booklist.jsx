import { useLoaderData } from "react-router-dom";
import { Link } from "react-router-dom";

function Booklist() {
  const books = useLoaderData() ?? [];

  return (
    <ul role="list" className="space-y-1 p-4">
      {books.map((book) => (
        <li key={book.id}>
          <Link to={`/bookDetails/${book.id}`}>{book.title}</Link>
        </li>
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
