import { Link } from "react-router-dom";

function Bookitem({ book }) {
  const { id, title, imageUrl, author, price } = book;

  return (
    <li className="flex gap-4 py-2">
      <img src={imageUrl} alt={title} className="mb-10 w-24" />
      <div className="flex grow flex-col pt-0.5">
        <Link to={`/bookDetails/${id}`} className="font medium">
          {title}
        </Link>
      </div>
    </li>
  );
}

export default Bookitem;
