import { createTheme } from '@mui/material'
import React, { useContext } from 'react'
import { ThemeGlobleContext } from '../contexts/theme-globle-context';
import Cookies from "js-cookie";

// const { themeVal } = useContext(ThemeGlobleContext);

// const theme = createTheme({
// //   palette: {
// //     primary: {
// //         main: themeVal.primary,
// //     },
// //     secondary: {
// //         main: themeVal.secondary,
// //     },
// //     background: {
// //         default: themeVal.background,
// //     },
// //     text: {
// //         primary: themeVal.text,
// //     },
// // },
// // custom: {
// //     subBackground: themeVal.subBackground,
// //     subText: themeVal.subText,
// // },
// })



// Function to get the theme from cookies
export const getThemeFromCookie = () => {
  const theme = Cookies.get('themeColor');
  return theme ? JSON.parse(theme) : null;
};

// Function to set the theme in cookies
export const setThemeInCookie = (theme) => {
  Cookies.set('themeColor', JSON.stringify(theme), { expires: 365 });  // expires in 1 year
};



export default {getThemeFromCookie,setThemeInCookie}