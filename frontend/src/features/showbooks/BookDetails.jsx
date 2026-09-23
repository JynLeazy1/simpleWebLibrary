import { useLoaderData } from "react-router-dom";

function BookDetails() {
  const book = useLoaderData();

  return (
    <div className="gap- px-5 py-3">
      <h1>{book.title}</h1>
      <p>
        <strong>Autor:</strong> {book.author}
      </p>
      <p>
        <strong>Precio:</strong> ${book.price}
      </p>
      <img src={book.imageUrl} alt={book.title} className="h-60" />
    </div>
  );
}

export default BookDetails;
