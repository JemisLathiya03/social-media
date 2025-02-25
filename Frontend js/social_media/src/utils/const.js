// const themeColor = {
//   primary: "#3d3e40",
//   secondary: "#f48fb1",
//   background: "#121212",
//   text: "#E0E0E0",
//   subBackground: "#2b2b2b", // Sub-background color
//   subText: "#E0E0E0", // Sub-text color
//   buttonBackgroud: "#24759b",
//   buttonText: "",
//   anchorText: "",
//   textFieldColor: "#E0E0E0",
// };

import { Slide } from "@mui/material";


// 24759b
// #703375

function SlideTransition(props) {
  return <Slide {...props} direction="up" />;
}

const themeColor = {
  primary: "#24759b",
  secondary: "#f48fb1",
  background: "#121212",
  text: "#E0E0E0",
  subBackground: "#2b2b2b", // Sub-background color
  subText: "#24759b", // Sub-text color
  buttonBackgroud: "#24759b",
  buttonText: "#E0E0E0",
  anchorText: "#E0E0E0",
  textFieldColor: "#E0E0E0",
};
const themeColor2 = {
  primary: "#703375",
  secondary: "#f48fb1",
  background: "#121212",
  text: "#E0E0E0",
  subBackground: "#2b2b2b", // Sub-background color
  subText: "#24759b", // Sub-text color
  buttonBackgroud: "#703375",
  buttonText: "#E0E0E0",
  anchorText: "#E0E0E0",
  textFieldColor: "#E0E0E0",
};

const regex = {
  emailRegex : /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/,
  passwordRegex : /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/,
  numberRegex : /^\d+$/,
}

const snackBarObject = {
  open: false,
  message: "",
  severity: "success", // success | error
  Transition: SlideTransition,
}

const myGlobleObject = {
  token : localStorage.getItem("token"),
  themeVal : {},
  snackBarObject : snackBarObject,
}

const routes = {
  register : "/register",
  login : "/login",
  verifyCode: "/verify-code",
  home : "/",
  login : "/"
}

export {themeColor,themeColor2,regex,myGlobleObject,snackBarObject,routes}