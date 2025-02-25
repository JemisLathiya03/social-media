import { Button } from "@mui/material";
import { useFormikContext } from "formik";
import React from "react";

const SubmitButton = ({ children, ...otherProps }) => {
  const { submitForm } = useFormikContext();

  const handleSubmit = (event) => {
    submitForm();
  };

  const configrationData = {
    ...otherProps,
    fullWidth: true,
    variant: "contained",
    onClick: handleSubmit,
  };

  return <Button {...configrationData} >{children}</Button>;
};

export default SubmitButton;
