import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Home from "./UI/Home";
import Adminbooks from "./features/adminbooks/Adminbooks";
import Booklist from "./features/booklist/Booklist";
import BookDetails from "./features/showbooks/BookDetails";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "/adminBooks",
    element: <Adminbooks />,
  },
  {
    path: "/bookList",
    element: <Booklist />,
  },
  {
    path: "/bookDetails",
    element: <BookDetails />,
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
