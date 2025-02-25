import React, { useEffect } from "react";
import { decodeToken } from "../../utils/globalHandler";

const HomePage = () => {
  useEffect(() => {
    debugger;
    console.log(process.env.REACT_APP_getHomeData);
    const requestOptions = {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${decodeToken().token}`,
      },
    };

    fetch(process.env.REACT_APP_getHomeData, requestOptions)
      .then(async (response) => {
        const isJson = response.headers
          .get("content-type")
          ?.includes("application/json");
        const data = isJson && (await response.json());
        const status = await response.status;

        if (status === 200) {
          console.log("there is success");
        }

        if (status === 401) {
        }
      })
      .catch((error) => {
        console.log("there is some error");
      });
  }, []);

  return <div>Home page</div>;
};

export default HomePage;
