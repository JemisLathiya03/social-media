import { TextField } from "@mui/material";
import { useField } from "formik";
import React from "react";

const TextFieldWrapper = ({ name, ...otherProps }) => {
  const [field, meta] = useField(name);

  const configrationData = {
    ...field,
    ...otherProps,
    fullWidth: true,
    variant: "outlined",
    sx: {
      "& .MuiInputLabel-root": {
        color: (theme) => theme.custom.textFieldColor, // White color for the label
      },
      "& .MuiOutlinedInput-root": {
        "& fieldset": {
          borderColor: (theme) => theme.custom.textFieldColor, // White color for the border
        },
        "&:hover fieldset": {
          borderColor: (theme) => theme.custom.textFieldColor, // White border on hover
        },
        "&.Mui-focused fieldset": {
          borderColor: (theme) => theme.custom.textFieldColor, // White border when focused
        },
        "&.Mui-focused fieldset": {
          borderColor: (theme) => theme.custom.textFieldColor, // Border color when focused (change to your preferred color)
        },
      },
      "& .MuiInputLabel-root.Mui-focused": {
        color: (theme) => theme.custom.textFieldColor, // Label color when focused (change to your preferred color)
      },
    },
  };

  if (meta && meta.touched && meta.error) {
    configrationData.error = true;
    configrationData.helperText = meta.error;
  }

  return <TextField {...configrationData} />;
};

export default TextFieldWrapper;
