import React, { useContext } from "react";
import { Route, Routes } from "react-router-dom";
import { GlobleContext } from "../contexts/my-globle-context";
import { ThemeProvider } from "@emotion/react";
import theme from "../utils/theme";
import { createTheme, CssBaseline } from "@mui/material";
import { ThemeGlobleContext } from "../contexts/theme-globle-context";
import { routes } from "../utils/const";
// import Authentication from "./views/authentication";

const Authentication = React.lazy(() => import("../views/authentication"));
const Login = React.lazy(() => import("../components/login"));
const HomePage = React.lazy(() => import("../views/homePage"));

const CustomRoutes = () => {
  const { globleObject, setGlobleObject } = useContext(GlobleContext);

  return (
    <>
      <div>
        {!globleObject.token ? (
          <Routes>
            <Route path="/*" element={<Authentication />}></Route>
            {/* <Route path="/login" element={<Login />}></Route> */}
          </Routes>
        ) : (
          <>
            {"hello"}
            <Routes>
              <Route path="/*" element={<HomePage />}></Route>
              {/* <Route path="/" element={<Login />}></Route> */}
            </Routes>
          </>
        )}
      </div>
    </>
  );
};

export default CustomRoutes;
