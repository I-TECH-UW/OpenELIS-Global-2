import React, { useEffect } from "react";
import config from "./config.json";

function RedirectOldUI() {
  useEffect(() => {
    window.location.href = config.serverBaseUrl + window.location.pathname;
  }, []);

  return <></>;
}

export default RedirectOldUI;
