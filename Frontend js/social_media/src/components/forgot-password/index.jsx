import { Grid2 } from "@mui/material";
import { Form, Formik } from "formik";
import React, { useEffect } from "react";
import * as Yup from "yup";
import TextField from "../../form/basic-component/text-field-wrapper";
import SubmitButton from "../../form/basic-component/submit-button";
import { Link } from "react-router-dom";

const ForgotPassword = ({ authButtonStyle }) => {
  const INITIAL_VALUE = {
    email: "",
  };

  const VALIDATION_SCHEMA = Yup.object().shape({
    email: Yup.string().required("Please Enter Your Email Address."),
  });

  return (
    <>
      <Formik
        initialValues={INITIAL_VALUE}
        validationSchema={VALIDATION_SCHEMA}
        onSubmit={(value) => console.log(value)}
      >
        <Form className="w-full">
          <Grid2 container spacing={2}>
            <Grid2 size={12}>
              <TextField name="email" label="Email" />
            </Grid2>
            <Grid2 size={12}>
              <SubmitButton className="uppercase" sx={authButtonStyle}>
                Send Email
              </SubmitButton>
            </Grid2>
          </Grid2>
        </Form>
      </Formik>
    </>
  );
};

export default ForgotPassword;
