import React, { useContext, useState } from "react";
import { Route, Routes, useNavigate, useNavigation } from "react-router-dom";
import {
  Button,
  Card,
  CardContent,
  Grid2,
  Typography,
  useTheme,
} from "@mui/material";
import SocialConnectionImg from "../../assets/images/social-connection.png";
import { authenticationStyle } from "./style";
import ForgotPassword from "../../components/forgot-password";
import { routes, themeColor, themeColor2 } from "../../utils/const";
import { ThemeGlobleContext } from "../../contexts/theme-globle-context";
import { Circles } from "react-loader-spinner";
import Loader from "../../components/utility-component/loader";
import { GlobleContext } from "../../contexts/my-globle-context";

const Login = React.lazy(() => import("../../components/login"));
const Register = React.lazy(() => import("../../components/register"));
const VerificationCode = React.lazy(() =>
  import("../../components/verification-code")
);

const Authentication = () => {
  const classes = authenticationStyle();

  const { changeTheme } = useContext(ThemeGlobleContext);

  const navigate = useNavigate();

  const [loading, setLoading] = useState(false);

  const handleForgotPassword = () => {
    navigate("/forgot-password");
  };

  const authButtonStyle = {
    padding: "10px 0px",
    fontSize: "15px",
    background: (theme) => theme.custom.buttonBackgroud,
    color: (theme) => theme.custom.buttonText,
  };

  const handleRegisterClick = () => {
    setLoading(true);
    setTimeout(() => {
      setLoading(false);
      navigate(routes.register);
    }, 3000);
  };

  const handleVerifyCode = () => {
    navigate(routes.verifyCode);
  };

  return (
    <>
      {loading && <Loader />}

      {!loading && (
        <Grid2
          container
          spacing={2}
          sx={{ background: (theme) => theme.palette.background.default }}
          className="h-screen"
        >
          <Grid2 size={7}>
            <img className="w-full h-full" src={SocialConnectionImg} />
          </Grid2>
          <Grid2 size={5}>
            <div className="px-20 w-full h-full flex items-center justify-center">
              <Card
                className={`w-full ${classes.authCard}`}
                sx={{ background: (theme) => theme.custom.subBackground }}
              >
                <CardContent className="flex flex-col items-center justify-center">
                  <div className="flex flex-col items-center justify-center">
                    <h1 className="text-3xl font-semibold mb-2">Jems Social</h1>
                    <p className="text-center leading-5 pl-2 pr-2">
                      Connecting people, sharing stories, and building a
                      community of meaningful relationships.
                    </p>
                  </div>
                  <div className="w-full mt-5 flex flex-col items-center justify-center">
                    <Routes>
                      <Route
                        path="/"
                        element={
                          <Login
                            authButtonStyle={authButtonStyle}
                            handleForgotPassword={handleForgotPassword}
                          />
                        }
                      />
                      <Route
                        path={routes.login}
                        element={
                          <Login
                            authButtonStyle={authButtonStyle}
                            handleForgotPassword={handleForgotPassword}
                          />
                        }
                      />
                      <Route
                        path={routes.register}
                        element={
                          <Register
                            setLoading={setLoading}
                            authButtonStyle={authButtonStyle}
                            handleForgotPassword={handleForgotPassword}
                          />
                        }
                      />
                      <Route
                        path="/forgot-password"
                        element={
                          <ForgotPassword authButtonStyle={authButtonStyle} />
                        }
                      />
                      <Route
                        path={routes.verifyCode}
                        element={
                          <VerificationCode
                            setLoading={setLoading}
                            authButtonStyle={authButtonStyle}
                          />
                        }
                      />
                    </Routes>
                  </div>
                </CardContent>
                <Button
                  onClick={handleRegisterClick} // Trigger loader when Register is clicked
                >
                  Register
                </Button>
                <Button
                  onClick={() => {
                    changeTheme(themeColor);
                  }}
                >
                  Change Theme
                </Button>
                <Button
                  onClick={handleVerifyCode} // Trigger loader when Register is clicked
                >
                  Verify Code
                </Button>
              </Card>
            </div>
          </Grid2>
        </Grid2>
      )}
    </>
  );
};

export default Authentication;
