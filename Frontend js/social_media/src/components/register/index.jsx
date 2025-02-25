import {
  Button,
  Grid2,
  IconButton,
  InputAdornment,
  useTheme,
} from "@mui/material";
import { Form, Formik } from "formik";
import React, { useEffect } from "react";
import TextField from "../../form/basic-component/text-field-wrapper";
import SubmitButton from "../../form/basic-component/submit-button";
import { Link, useNavigate } from "react-router-dom";
import * as Yup from "yup";
import RadioButtonWrapper from "../../form/basic-component/radio-button-wrapper";
import { regex, routes } from "../../utils/const";
import { GlobleContext } from "../../contexts/my-globle-context";
import { useContext } from "react";
import { useState } from "react";
import { Visibility, VisibilityOff } from "@mui/icons-material";

const Register = ({ authButtonStyle, handleForgotPassword, setLoading }) => {
  const { globleObject, setGlobleObject } = useContext(GlobleContext);
  // const [userName, setUserName] = useState("");

  const navigate = useNavigate();

  const theme = useTheme();

  // State to handle password visibility
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);

  const togglePasswordVisibility = () => setShowPassword((prev) => !prev);
  const toggleConfirmPasswordVisibility = () =>
    setShowConfirmPassword((prev) => !prev);

  const INITIAL_VALUE = {
    email: "",
    password: "",
    firstName: "",
    lastName: "",
    gender: "",
    confirmPassword: "",
  };

  const handleSnackBarClick = (message, severity) => {
    console.log("snack bar");

    setGlobleObject((prevState) => ({
      ...prevState,
      snackBarObject: {
        ...prevState.snackBarObject,
        open: true,
        message,
        severity,
      },
    }));
  };

  const handleRegister = (registerData) => {
    // setUserName(registerData.email);
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(registerData),
    };

    setLoading(true);
    fetch(process.env.REACT_APP_registerUser, requestOptions)
      .then(async (response) => {
        debugger;
        const isJson = response.headers
          .get("content-type")
          ?.includes("application/json");
        const data = isJson && (await response.json());
        const status = response.status;

        // setLoadingSaveToCart(false);
        if (status == 200) {
          if (data.message === "success") {
            if (data.data.length > 0) {
              localStorage.setItem("userEmail", registerData.email);
              navigate(routes.verifyCode);
              setLoading(false);
              handleSnackBarClick(data.data[0].message, "success");
            }
          } else {
            if (data.data.length > 0) {
              navigate(routes.register);
              setLoading(false);
              handleSnackBarClick(data.data[0].message, "error");
            }
          }
        } else if (status == 401) {
          navigate(routes.register);
          setLoading(false);
          handleSnackBarClick(data.data[0].message, "error");
          // isTokenExpired(decodeToken().token).then((status) => {
          //   if (status === true) {
          //     localStorage.removeItem("userSelectedAccount");
          //     localStorage.removeItem("token");
          //     window.open(routes.logout, "_self");
          //   }
          // });
        } else {
          setLoading(false);
          // Set Error
        }
      })
      .catch((error) => {
        setLoading(false);
        // setLoadingSaveToCart(false);
        // console.log("Error :: " + error);
      });
  };

  // const VALIDATION_SCHEMA = Yup.object().shape({
  //   firstName: Yup.string().required("Please Enter Your First Name."),
  //   lastName: Yup.string().required("Please Enter Your Last Name."),
  //   email: Yup.string().required("Please Enter Your Email Address."),
  //   password: Yup.string().required("Please Enter Your Password."),
  //   gender: Yup.string().required("Please Select Your Gender."),
  //   confirmPassword: Yup.string().required(
  //     "Please Enter Your Confirm Password."
  //   ),
  // });
  const VALIDATION_SCHEMA = Yup.object().shape({
    firstName: Yup.string().required("Please Enter Your First Name."),
    lastName: Yup.string().required("Please Enter Your Last Name."),
    email: Yup.string()
      .matches(regex.emailRegex, "Invalid email format.")
      .required("Please Enter Your Email Address."),
    password: Yup.string()
      .required("Please Enter Your Password.")
      .matches(
        regex.passwordRegex,
        "Password must be at least 8 characters long, include one uppercase letter, one digit, and one special character."
      ),
    confirmPassword: Yup.string()
      .required("Please Enter Your Confirm Password.")
      .matches(
        regex.passwordRegex,
        "Password must be at least 8 characters long, include one uppercase letter, one digit, and one special character."
      )
      .oneOf([Yup.ref("password"), null], "Passwords must match."),
    gender: Yup.string().required("Please Select Your Gender."),
  });

  return (
    <>
      <Formik
        initialValues={INITIAL_VALUE}
        validationSchema={VALIDATION_SCHEMA}
        onSubmit={(value) => handleRegister(value)}
      >
        <Form className="w-full">
          <Grid2 container spacing={2}>
            <Grid2 size={12}>
              <TextField name="firstName" label="First Name" />
            </Grid2>
            <Grid2 size={12}>
              <TextField name="lastName" label="Last Name" />
            </Grid2>
            <Grid2 size={12}>
              <TextField name="email" label="Email" />
            </Grid2>
            <Grid2 size={12}>
              <TextField
                name="password"
                label="Password"
                type={showPassword ? "text" : "password"}
                InputProps={{
                  endAdornment: (
                    <InputAdornment position="end">
                      <IconButton
                        sx={{ color: theme.custom.textFieldColor }}
                        onClick={togglePasswordVisibility}
                        edge="end"
                      >
                        {showPassword ? <VisibilityOff /> : <Visibility />}
                      </IconButton>
                    </InputAdornment>
                  ),
                }}
              />
            </Grid2>
            <Grid2 size={12}>
              <TextField
                name="confirmPassword"
                label="Confirm Password"
                type={showConfirmPassword ? "text" : "password"}
                InputProps={{
                  endAdornment: (
                    <InputAdornment position="end">
                      <IconButton
                        sx={{ color: theme.custom.textFieldColor }}
                        onClick={toggleConfirmPasswordVisibility}
                        edge="end"
                      >
                        {showConfirmPassword ? (
                          <VisibilityOff />
                        ) : (
                          <Visibility />
                        )}
                      </IconButton>
                    </InputAdornment>
                  ),
                }}
              />
            </Grid2>
            <Grid2 size={12}>
              <RadioButtonWrapper
                name="gender"
                label="Gender"
                options={[
                  { label: "Male", value: "male" },
                  { label: "Female", value: "female" },
                ]}
              />
            </Grid2>
            <Grid2 size={12}>
              <SubmitButton className="uppercase" sx={authButtonStyle}>
                Register
              </SubmitButton>
            </Grid2>

            <Grid2
              size={12}
              className="flex flex-row gap-2 items-center justify-center font-medium text-lg leading-6"
            >
              <p>If you already have an account ? </p>
              <Link
                className="uppercase text-base "
                to={{
                  pathname: routes.login,
                }}
              >
                Login
              </Link>
            </Grid2>

            <Grid2
              size={12}
              className="flex flex-row gap-2 items-center justify-center font-medium text-lg"
            >
              <Button
                fullWidth
                className={`uppercase`}
                sx={authButtonStyle}
                variant="contained"
                onClick={handleForgotPassword}
              >
                Forgot Password
              </Button>
            </Grid2>
          </Grid2>
        </Form>
      </Formik>
    </>
  );
};

export default Register;
