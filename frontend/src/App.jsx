import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { booksLoader } from "./services/booksLoader";
import Home from "./UI/Home";
import Adminbooks, {
  action as createBookAction,
} from "./features/adminbooks/Adminbooks";
import Booklist from "./features/booklist/Booklist";
import BookDetails from "./features/showbooks/BookDetails";
import AppLayout from "./UI/AppLayout";
import Login from "./UI/Login";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Login />,
  },
  {
    element: <AppLayout />,
    id: "books",
    children: [
      {
        path: "/home",
        element: <Home />,
      },
      {
        path: "/adminBooks",
        element: <Adminbooks />,
        action: createBookAction,
      },
      {
        path: "/bookList",
        element: <Booklist />,
        loader: booksLoader,
      },
      {
        path: "/bookDetails/:bookId",
        element: <BookDetails />,
      },
    ],
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
