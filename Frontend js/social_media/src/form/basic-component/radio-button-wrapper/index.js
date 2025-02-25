import React from "react";
import {
  RadioGroup,
  FormControlLabel,
  Radio,
  FormHelperText,
  FormControl,
  Typography,
} from "@mui/material";
import { useField } from "formik";

const RadioButtonWrapper = ({ name, options, label, ...otherProps }) => {
  const [field, meta, helpers] = useField(name);

  const handleChange = (event) => {
    helpers.setValue(event.target.value); // Ensure Formik updates the value
  };

  return (
    <FormControl error={meta.touched && Boolean(meta.error)}>
      <div style={{ display: "flex", alignItems: "center" }}>
        {/* Label with gender text */}
        <Typography variant="body1" component="span" sx={{ mr: 2, color: (theme) => theme.custom.textFieldColor }}>
          {label}:
        </Typography>

        {/* Radio buttons inline */}
        <RadioGroup {...field} row onChange={handleChange}>
          {options.map((option, index) => (
            <FormControlLabel
              key={index}
              value={option.value}
              control={
                <Radio
                  sx={{
                    color: (theme) => theme.custom.textFieldColor,
                    "&.Mui-checked": {
                      color: (theme) => theme.custom.textFieldColor,
                    },
                  }}
                />
              }
              label={option.label}
            />
          ))}
        </RadioGroup>
      </div>
      {meta.touched && meta.error && <FormHelperText>{meta.error}</FormHelperText>}
    </FormControl>
  );
};

export default RadioButtonWrapper;
