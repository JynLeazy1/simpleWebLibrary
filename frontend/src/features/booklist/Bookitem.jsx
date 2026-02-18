import { Link } from "react-router-dom";

function Bookitem({ book }) {
  const { id, title, imageUrl } = book;

  return (
    <li className="flex gap-4 py-2">
      <img src={imageUrl} alt={title} className="h-24" />
      <div className="flex grow flex-col pt-0.5">
        <Link to={`/bookDetails/${id}`} className="font medium">
          {title}
        </Link>
      </div>
    </li>
  );
}

export default Bookitem;
