import * as React from "react";
import { useTheme } from "@mui/material/styles";
import Snackbar from "@mui/material/Snackbar";
import MuiAlert from "@mui/material/Alert";
import { useContext } from "react";
import { GlobleContext } from "../../contexts/my-globle-context";

export default function SnackbarMsg() {
  const { globleObject, setGlobleObject } = useContext(GlobleContext);

  const theme = useTheme(); // Access MUI Theme

  // const [snackBarState, setSnackBarState] = React.useState({
  //   open: false,
  //   message: "",
  //   severity: "success", // success | error
  //   Transition: SlideTransition,
  // });

  // const handleClickSnackBar = (message, severity) => {};

  // const handleClick = (message, severity) => () => {
  //   setSnackBarState({
  //     open: true,
  //     message,
  //     severity,
  //   });
  // };

  const handleClose = () => {
    setGlobleObject((prevState) => ({
      ...prevState,
      snackBarObject: {
        ...prevState.snackBarObject,
        open: false,
      },
    }));
  };

  return (
    <div>
      <Snackbar
        open={globleObject.snackBarObject.open}
        autoHideDuration={3000}
        onClose={handleClose}
        TransitionComponent={globleObject.snackBarObject.Transition}
        anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
      >
        <MuiAlert
          onClose={handleClose}
          severity={globleObject.snackBarObject.severity}
          variant="filled"
          sx={{
            bgcolor:
              globleObject.snackBarObject.severity === "success"
                ? theme.palette.success.main
                : theme.palette.error.main,
            color: theme.palette.common.white,
          }}
        >
          {globleObject.snackBarObject.message}
        </MuiAlert>
      </Snackbar>
    </div>
  );
}
