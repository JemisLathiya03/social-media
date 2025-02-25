import { Button, Grid2 } from "@mui/material";
import { Form, Formik } from "formik";
import React, { useContext, useEffect } from "react";
import TextField from "../../form/basic-component/text-field-wrapper";
import SubmitButton from "../../form/basic-component/submit-button";
import { Link, useNavigate } from "react-router-dom";
import * as Yup from "yup";
import RadioButtonWrapper from "../../form/basic-component/radio-button-wrapper";
import { regex, routes } from "../../utils/const";
import { GlobleContext } from "../../contexts/my-globle-context";

const VerificationCode = ({ authButtonStyle, setLoading }) => {
  const { globleObject, setGlobleObject } = useContext(GlobleContext);
  const navigate = useNavigate();

  const INITIAL_VALUE = {
    verifyCode: "",
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

  const handleVerifyCode = (verifyCodeData) => {
    const storedEmail = localStorage.getItem("userEmail");
    if (!storedEmail) {
      navigate(routes.register); // Redirect if no email is found
    } else {
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          email: storedEmail,
          verificationCode: verifyCodeData.verifyCode,
        }),
      };

      setLoading(true);
      fetch(process.env.REACT_APP_verifyUser, requestOptions)
        .then(async (response) => {
          const isJson = response.headers
            .get("content-type")
            ?.includes("application/json");
          const data = isJson && (await response.json());
          const status = response.status;

          // setLoadingSaveToCart(false);
          // setLoading(false);
          if (status == 200) {
            if (data.message === "success") {
              if (data.data.length > 0) {
                debugger;
                const jsonString = JSON.stringify(data.data[0]);
                const tokenEncodedString = btoa(encodeURIComponent(jsonString));
                localStorage.setItem("token", tokenEncodedString);
                localStorage.removeItem("userEmail");

                setGlobleObject((prevState) => ({
                  ...prevState,
                  token: tokenEncodedString,
                }));

                navigate(routes.home);
                setLoading(false);
                handleSnackBarClick(data.data[0].message, "success");
              }
            } else {
              if (data.data.length > 0) {
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
            console.log("Error :: " + data);
            setLoading(false);
            // Set Error
          }
        })
        .catch((error) => {
          setLoading(false);
          // setLoadingSaveToCart(false);
          // console.log("Error :: " + error);
        });
    }
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
    verifyCode: Yup.string()
      .matches(regex.numberRegex, "Please Enter Only Numeric Value.")
      .required("Please Enter Your Verification Code."),
  });

  return (
    <>
      <Formik
        initialValues={INITIAL_VALUE}
        validationSchema={VALIDATION_SCHEMA}
        onSubmit={(value) => handleVerifyCode(value)}
      >
        <Form className="w-full">
          <Grid2 container spacing={2}>
            <Grid2 size={12}>
              <TextField name="verifyCode" label="Verification Code" />
            </Grid2>

            <Grid2 size={12}>
              <SubmitButton className="uppercase" sx={authButtonStyle}>
                Verify Code
              </SubmitButton>
            </Grid2>

            <Grid2
              size={12}
              className="flex flex-row gap-2 items-center justify-center font-medium text-lg leading-6"
            >
              {/* <p>If you already have an account ? </p>
              <Link
                className="uppercase text-base "
                to={{
                  pathname: "/Login",
                }}
              >
                Login
              </Link> */}
            </Grid2>

            {/* <Grid2
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
            </Grid2> */}
          </Grid2>
        </Form>
      </Formik>
    </>
  );
};

export default VerificationCode;
