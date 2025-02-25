import { useTheme } from "@mui/material";
import React from "react";
import { Circles } from "react-loader-spinner";

const loaderStyle = {
  position: "fixed",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  zIndex: 1000,
  display: "flex", // Show loader only when `loading` is true
  justifyContent: "center",
  alignItems: "center",
};

const Loader = () => {
  const theme = useTheme();

  // Use the primary color from the theme for the loader
  const loaderColor = theme.palette.primary.main;

  return (
    <div style={loaderStyle}>
      <Circles
        type="Circles"
        alignItems="center"
        color={loaderColor}
        height={100}
        width={100}
      />
    </div>
  );
};

export default Loader;
