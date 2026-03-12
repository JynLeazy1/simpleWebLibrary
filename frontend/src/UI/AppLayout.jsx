import { Outlet } from "react-router-dom";
import Footer from "./Footer";
import Header from "./Header";

function AppLayout() {
  return (
    <div className="grid h-[500px] grid-rows-[auto_1fr_auto] bg-light">
      <Header />
      <main>
        <Outlet />
      </main>
      <Footer />
    </div>
  );
}

export default AppLayout;
