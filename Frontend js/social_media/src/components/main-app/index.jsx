import {
  createTheme,
  CssBaseline,
  Snackbar,
  ThemeProvider,
  useTheme,
} from "@mui/material";
import React, { useContext } from "react";
import theme from "../../utils/theme";
import { GlobleContext } from "../../contexts/my-globle-context";
import SnackbarMsg from "../snackbar";

const ThemeWrraper = ({ children }) => {
  const { globleObject } = useContext(GlobleContext);
  const themeVal = globleObject.themeVal;

  console.log(themeVal);

  const newtheme = createTheme({
    ...theme,
    palette: {
      primary: {
        main: themeVal.primary, // Dynamically set primary color
      },
      secondary: {
        main: themeVal.secondary, // Dynamically set secondary color
      },
      background: {
        default: themeVal.background, // Dynamically set background color
      },
      text: {
        primary: themeVal.text, // Dynamically set text color
      },
    },
    custom: {
      subBackground: themeVal.subBackground, // Dynamically set subBackground
      subText: themeVal.subText, // Dynamically set subText
      buttonBackgroud: themeVal.buttonBackgroud,
      buttonText: themeVal.buttonText,
      anchorText: themeVal.anchorText,
      textFieldColor: themeVal.textFieldColor,
    },
  });

  console.log("new theme" + newtheme);

  return (
    <>
      {console.log(themeVal)}
      <ThemeProvider theme={newtheme}>
        <CssBaseline />
        {children}
      </ThemeProvider>
      <SnackbarMsg />
    </>
  );
};

export default ThemeWrraper;
