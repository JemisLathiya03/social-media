import {
  Button,
  Grid2,
  IconButton,
  InputAdornment,
  TextField,
  useTheme,
} from "@mui/material";
import { Form, Formik } from "formik";
import React, { useEffect } from "react";
import * as Yup from "yup";
import { Grid } from "react-loader-spinner";
import TextFieldx from "../../form/basic-component/text-field-wrapper";
import SubmitButton from "../../form/basic-component/submit-button";
import { Link, useNavigate } from "react-router-dom";
import { routes } from "../../utils/const";
import { Visibility, VisibilityOff } from "@mui/icons-material";
import { useState } from "react";

const Login = ({ authButtonStyle, handleForgotPassword }) => {
  // State to handle password visibility
  const [showPassword, setShowPassword] = useState(false);

  const togglePasswordVisibility = () => setShowPassword((prev) => !prev);

  const theme = useTheme();

  const INITIAL_VALUE = {
    email: "",
    password: "",
  };

  const VALIDATION_SCHEMA = Yup.object().shape({
    email: Yup.string().required("Please Enter Your Email Address."),
    password: Yup.string().required("Please Enter Your Password."),
  });

  return (
    <Formik
      initialValues={INITIAL_VALUE}
      validationSchema={VALIDATION_SCHEMA}
      onSubmit={(value) => console.log(value)}
    >
      <Form className="w-full">
        <Grid2 container spacing={2}>
          <Grid2 size={12}>
            <TextFieldx name="email" label="Email" />
          </Grid2>
          <Grid2 size={12}>
            <TextFieldx
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
            <SubmitButton className="uppercase" sx={authButtonStyle}>
              Login
            </SubmitButton>
          </Grid2>

          <Grid2
            size={12}
            className="flex flex-row gap-2 items-center justify-center font-medium text-lg leading-6"
          >
            <p>If you don't have an account ? </p>
            <Link
              className="uppercase text-base "
              to={{
                pathname: routes.register,
              }}
            >
              Register
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
  );
};

export default Login;
