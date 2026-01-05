import { useParams, useRouteLoaderData } from "react-router-dom";

function BookDetails() {
  const { bookId } = useParams();
  const books = useRouteLoaderData("books") ?? [];
  const book = books.find((b) => b.id === Number(bookId));
  if (!book) {
    return <p>Libo no encontrado</p>;
  }
  return (
    <div>
      <h1>{book.title}</h1>
      <p>
        <strong>Autor:</strong> {book.author}
      </p>
      <p>
        <strong>Precio:</strong> ${book.price}
      </p>
      <img src={book.imageUrl} alt={book.title} />
    </div>
  );
}

export default BookDetails;
