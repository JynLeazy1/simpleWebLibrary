import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { booksLoader, bookDetailsLoader } from "./services/booksLoader";
import Home from "./UI/Home";
import Adminbooks, {
  action as createBookAction,
} from "./features/adminbooks/Adminbooks";
import Login from "./UI/Login";
import AppLayout from "./UI/AppLayout";
import Error from "./UI/Error";
import Booklist from "./features/booklist/Booklist";
import BookDetails from "./features/showbooks/BookDetails";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Login />,
  },
  {
    element: <AppLayout />,
    errorElement: <Error />,
    children: [
      {
        path: "/home",
        element: <Home />,
      },
      {
        path: "/adminBooks",
        element: <Adminbooks />,
        action: createBookAction,
        errorElement: <Error />,
      },
      {
        path: "/bookList",
        element: <Booklist />,
        loader: booksLoader,
        errorElement: <Error />,
      },
      {
        path: "/bookDetails/:bookId",
        element: <BookDetails />,
        loader: bookDetailsLoader,
        errorElement: <Error />,
      },
    ],
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
