import React, { createContext, useState, useEffect, useContext } from "react";
import Header from "./Header";
import Footer from "./Footer";
import { Content, Theme } from "@carbon/react";
import { NotificationKinds } from "../common/CustomNotification";
import UserSessionDetailsContext from "../../UserSessionDetailsContext";
import { getFromOpenElisServer } from "../utils/Utils";

export const UserInformationContext = createContext(null);
export const ConfigurationContext = createContext(null);
export const NotificationContext = createContext(null);

export default function Layout(props) {
  const { children } = props;
  const { userSessionDetails } = useContext(UserSessionDetailsContext);
  const [configurationProperties, setConfigurationProperties] = useState({});
  const [notificationVisible, setNotificationVisible] = useState(false);
  const [notificationBody, setNotificationBody] = useState({
    title: "",
    message: "",
    kind: NotificationKinds.info,
  });

  const fetchConfigurationProperties = (res) => {
    setConfigurationProperties(res);
  };

  useEffect(() => {
    if (userSessionDetails.authenticated) {
      getFromOpenElisServer(
        "/rest/configuration-properties",
        fetchConfigurationProperties,
      );
    } else {
      getFromOpenElisServer(
        "/rest/open-configuration-properties",
        fetchConfigurationProperties,
      );
    }
  }, [userSessionDetails.authenticated]);

  return (
    <ConfigurationContext.Provider value={configurationProperties}>
      <NotificationContext.Provider
        value={{
          notificationVisible,
          setNotificationVisible,
          notificationBody,
          setNotificationBody,
        }}
      >
        <div className="d-flex flex-column min-vh-100">
          <Header onChangeLanguage={props.onChangeLanguage} />
          <Theme theme="white">
            <Content>{children}</Content>
          </Theme>
          <Footer />
        </div>
      </NotificationContext.Provider>
    </ConfigurationContext.Provider>
  );
}
