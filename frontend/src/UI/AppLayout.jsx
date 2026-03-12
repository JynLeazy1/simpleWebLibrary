import { Outlet, useNavigation } from "react-router-dom";
import Footer from "./Footer";
import Header from "./Header";
import Loader from "./Loader";
import { useEffect } from "react";

function AppLayout() {
  const navigation = useNavigation();
  useEffect(() => {
    console.log("NAV STATE:", navigation.state);
  }, [navigation.state]);

  const isLoading = navigation.state === "loading";
  return (
    <div className="grid h-[500px] grid-rows-[auto_1fr_auto] bg-light">
      {isLoading && <Loader />}
      <Header />
      <main>
        <Outlet />
      </main>
      <Footer />
    </div>
  );
}

export default AppLayout;
