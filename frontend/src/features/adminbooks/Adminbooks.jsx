import { Form, redirect } from "react-router-dom";
import { postBook } from "../../services/apibooks";

function Adminbooks() {
  return (
    <Form method="POST" >
      <h2 className="text-center">Add new book</h2>
      <form className="flex space-x-2 text-center p-2 justify-center">

      <div >
        <label >Title</label>
        <input type="text" name="title" className="bg-slate-100"/>
      </div>

      <div>
        <label>Author</label>
        <input type="text" name="author" className="bg-slate-100" />
      </div>

      <div>
        <label>Price</label>
        <input
          type="number"
          name="price"
          required="true"
          min={0.01}
          step={0.01}
          className="bg-slate-100"
          />
      </div>

      <div>
        <label>Image URL</label>
        <input type="text" name="imageUrl" className="bg-slate-100"/>
      </div>
          </form>
      <div className="flex justify-center">
      <button className="bg-success my-1 ">Add Book</button>

      </div>
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
