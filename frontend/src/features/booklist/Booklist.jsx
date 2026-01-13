import { useRouteLoaderData } from "react-router-dom";
import { Link } from "react-router-dom";

function Booklist() {
  const books = useRouteLoaderData("books") ?? [];
  if (!books) {
    return <p>Cargando libros...</p>;
  }

  return (
    <ul role="list" className="p-4 space-y-1">
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
