import { createContext, useContext, useEffect, useState } from "react";
import { GlobleContext } from "./my-globle-context";
import { themeColor } from "../utils/const";
import { getThemeFromCookie, setThemeInCookie } from "../utils/theme";

export const ThemeGlobleContext = createContext();

export const ThemeGlobleProvider = ({ children }) => {
  const [isThemeReady, setIsThemeReady] = useState(false);

  const { globleObject, setGlobleObject } = useContext(GlobleContext);

  useEffect(() => {
    console.log(themeColor);
    console.log(globleObject.themeVal);
    const root = document.documentElement;
    Object.entries(globleObject.themeVal).forEach(([key, value]) => {
      root.style.setProperty(`--${key}-color`, value);
    });
  }, [globleObject.themeVal]);

  // const loadTheme = () => {

  // }

  const fetchTheme = () => {
    // loadTheme()
    const storedTheme = getThemeFromCookie();
    if (storedTheme) {
      setGlobleObject((prevState) => ({
        ...prevState, 
        themeVal: storedTheme, 
      }));
      setIsThemeReady(true); // Allow rendering when theme is ready
    }
  };

  useEffect(() => {
    console.log(globleObject.token);
    if (!globleObject.token) {
      const storedTheme = getThemeFromCookie();
      if (storedTheme) {
        setGlobleObject((prevState) => ({
          ...prevState, 
          themeVal: storedTheme, 
        }));
        setIsThemeReady(true); // Allow rendering when theme is ready
      } else {
        setThemeInCookie(themeColor);
        setGlobleObject((prevState) => ({
          ...prevState, 
          themeVal: themeColor, 
        }));
        setIsThemeReady(true); // Allow rendering when theme is ready
      }
    } else {
      fetchTheme();
    }
  }, []);

  // Update the theme when the theme is manually changed
  const changeTheme = (newTheme) => {
    setGlobleObject((prevState) => ({
      ...prevState, 
      themeVal: newTheme, 
    }));
    setThemeInCookie(newTheme); // Update cookie with new theme
    setIsThemeReady(true);
  };

  return (
    <ThemeGlobleContext.Provider value={{ changeTheme }}>
      {isThemeReady ? children : null}
    </ThemeGlobleContext.Provider>
  );
};
