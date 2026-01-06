import { Form, redirect } from "react-router-dom";
import { postBook } from "../../services/apibooks";

function Adminbooks() {
  return (
    <Form method="POST">
      <h2>Add new book</h2>
      <div>
        <label>Title</label>
        <input type="text" name="title" />
      </div>

      <div>
        <label>Author</label>
        <input type="text" name="author" />
      </div>

      <div>
        <label>Price</label>
        <input
          type="number"
          name="price"
          required="true"
          min={0.01}
          step={0.01}
        />
      </div>

      <div>
        <label>Image URL</label>
        <input type="text" name="imageUrl" />
      </div>
      <button>Add Book</button>
    </Form>
  );
}

export async function action({ request }) {
  const formData = await request.formData();
  const data = Object.fromEntries(formData);
  console.log(data);

  const book = {
    id: 0,
    title: data.title,
    author: data.author,
    price: parseFloat(data.price),
    imageUrl: data.imageUrl,
  };
  console.log(book);

  await postBook(book);

  return redirect(`/bookDetails/${book.id}`);
}

export default Adminbooks;
