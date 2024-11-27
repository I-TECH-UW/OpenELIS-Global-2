import React, { useContext, useEffect, useState, createRef } from "react";
import config from "../../config.json";
//import "../Style.css";
import "./login.scss";
import styles from "./login.scss";
import KapsikiLogo from "./kapsiki-logo/kapsikiHeaderLogo.component";
import ProductOfLogo from "./kapsiki-logo/ProductOfLogo.component";
import classNames from "classnames";
import qs from "qs";
import { FormattedMessage, injectIntl } from "react-intl";
import {
  Form,
  Section,
  Heading,
  FormLabel,
  Grid,
  Column,
  TextInput,
  Button,
  Stack,
  Loading,
  InlineLoading,
  InlineNotification,
  PasswordInput,
  Tile,
  Link,
} from "@carbon/react";
import { Formik } from "formik";
import { AlertDialog, NotificationKinds } from "../common/CustomNotification";
import UserSessionDetailsContext from "../../UserSessionDetailsContext";
import { ConfigurationContext, NotificationContext } from "../layout/Layout";

function Login(props) {
  const { notificationVisible, setNotificationBody, setNotificationVisible } =
    useContext(NotificationContext);
  const { configurationProperties } = useContext(ConfigurationContext);

  const { userSessionDetails, refresh } = useContext(UserSessionDetailsContext);
  const [submitting, setSubmitting] = useState(false);
  const firstInput = createRef();

  useEffect(() => {
    firstInput.current.focus();

    const interval = setInterval(() => {
      checkLogin();
    }, 1000 * 3);

    return () => clearInterval(interval); // clear your interval to prevent memory leaks.
  }, []);

  useEffect(() => {
    if (userSessionDetails.authenticated) {
      window.location.href = "/";
    }
  }, [userSessionDetails]);

  const checkLogin = () => {
    refresh();
  };

  const doLogin = (data) => {
    setSubmitting(true);
    fetch(config.serverBaseUrl + "/ValidateLogin?apiCall=true", {
      //includes the browser sessionId in the Header for Authentication on the backend server
      credentials: "include",
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: qs.stringify(data),
    })
      .then(async (response) => {
        // get json response here
        let data = await response.json();
        setSubmitting(false);
        if (response.status === 200) {
          window.location.href = "/";
        } else {
          setNotificationBody({
            title: props.intl.formatMessage({
              id: "notification.title",
            }),
            message: props.intl.formatMessage({
              id: data.error,
            }),
            kind: NotificationKinds.error,
          });
          setNotificationVisible(true);
        }
      })
      .catch((error) => {
        setSubmitting(false);
        setNotificationBody({
          title: props.intl.formatMessage({
            id: "notification.title",
          }),
          message: props.intl.formatMessage({
            id: "notification.login.generic.error",
          }),
          kind: NotificationKinds.error,
        });
        setNotificationVisible(true);
        console.log(error);
      });
  };

  return (
    <>
      <div className="form-container">
      {notificationVisible === true ? <AlertDialog /> : ""}
      <KapsikiLogo />
        <Tile className="login-card">
          <h1 className="login-card-title">Login</h1>
          <Formik
            initialValues={{
              username: "",
              password: "",
            }}
            onSubmit={(values) => {
              fetch(config.serverBaseUrl + "/LoginPage", {
                //includes the browser sessionId in the Header for Authentication on the backend server
                credentials: "include",
                method: "GET",
              })
                .then((response) => response.status)
                .then(() => {
                  doLogin(values);
                })
                .catch((error) => {
                  console.log(error);
                });
            }}
          >
            {({ isValid, handleChange, handleSubmit }) => (
                  <Form onSubmit={handleSubmit} onChange={handleChange}>
                    <div className="input-group">
                      <TextInput
                        className="text-input"
                        id="loginName"
                        invalidText={props.intl.formatMessage({
                          id: "login.msg.username.missing",
                        })}
                        labelText={props.intl.formatMessage({
                          id: "login.msg.username",
                        })}
                        autoComplete="off"
                        ref={firstInput}
                      />
                      </div>
                      <div className="input-group">
                      <TextInput.PasswordInput
                        className="text-input"
                        id="password"
                        invalidText={props.intl.formatMessage({
                          id: "login.msg.password.missing",
                        })}
                        labelText={props.intl.formatMessage({
                          id: "login.msg.password",
                        })}
                      />
                      </div>
                      <div className="input-group">
                      <button type="submit" disabled={!isValid} className="continueButton">
                        <FormattedMessage id="label.button.submit" />
                        <Loading
                          small={true}
                          withOverlay={false}
                          className={submitting ? "show" : "hidden"}
                        />
                      </button>
                      {configurationProperties?.useSaml == "true" && (
                        <button
                        className="continueButton"
                          type="button"
                          onClick={() => {
                            const POPUP_HEIGHT = 700;
                            const POPUP_WIDTH = 600;
                            const top =
                              window.outerHeight / 2 +
                              window.screenY -
                              POPUP_HEIGHT / 2;
                            const left =
                              window.outerWidth / 2 +
                              window.screenX -
                              POPUP_WIDTH / 2;
                            window.open(
                              config.serverBaseUrl + "/LoginPage?useSAML=true",
                              "SAML Popup",
                              `height=${POPUP_HEIGHT},width=${POPUP_WIDTH},top=${top},left=${left}`,
                            );
                          }}
                        >
                          <FormattedMessage id="label.button.login.sso" />
                        </button>
                      )}
                      </div>
                  </Form>
                )}
          </Formik>
        </Tile>
        <div className="footer">
          <div className="ProductOfLogo-container">
            <ProductOfLogo />
          </div>
        </div>
      </div>
    </>
  );
}

export default injectIntl(Login);
