import React, { Suspense, useContext, useEffect, useState } from "react";
import "./App.css";
import { Circles } from "react-loader-spinner";
import { HashRouter, Route, Routes } from "react-router-dom";
import {
  MyGlobleContext,
  MyGlobleContextProvider,
} from "./contexts/my-globle-context";
import ErrorBoundary from "./ErrorBoundary";
import {
  ThemeGlobleContext,
  ThemeGlobleProvider,
} from "./contexts/theme-globle-context";
import { ThemeProvider } from "@emotion/react";
import { createTheme, CssBaseline, Slide, useTheme } from "@mui/material";
import ThemeWrraper from "./components/main-app";
import { myGlobleObject } from "./utils/const";

const CustomRoutes = React.lazy(() => import("./routes"));

const loaderStyle = {
  position: "fixed",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  zIndex: 1000,
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
};

function App() {

  const [globleObject, setGlobleObject] = useState(myGlobleObject)
  const contextVal = { globleObject, setGlobleObject };

  const theme = useTheme();
  
  const loaderColor = globleObject.themeVal.primary;

  return (
    <MyGlobleContextProvider value={contextVal}>
      <ThemeGlobleProvider>
        <ThemeWrraper>
          <HashRouter>
            <Suspense
              fallback={
                <div style={loaderStyle}>
                      <Circles
                        type="Circles"
                        alignItems="center"
                        color={loaderColor}
                        height={100}
                        width={100}
                      />
                    </div>
              }
            >
              <CustomRoutes />
            </Suspense>
          </HashRouter>
        </ThemeWrraper>
      </ThemeGlobleProvider>
    </MyGlobleContextProvider>
  );
}

export default App;
