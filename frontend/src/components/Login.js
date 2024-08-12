import React, { useContext, useEffect, useState, createRef } from "react";
import config from "../config.json";
import "./Style.css";
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
} from "@carbon/react";
import { Formik } from "formik";
import { AlertDialog, NotificationKinds } from "./common/CustomNotification";
import UserSessionDetailsContext from "../UserSessionDetailsContext";
import { ConfigurationContext, NotificationContext } from "./layout/Layout";

function Login(props) {
  const { notificationVisible, addNotification, setNotificationVisible } =
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

  const loginMessage = () => {
    return (
      <>
        <div>
          <picture>
            <img
              src={`images/openelis_logo_full.png`}
              alt="fullsize logo"
              width="300"
              height="56"
            />
          </picture>
        </div>
        <br></br>
        <div>
          <FormattedMessage id="login.notice.message" />
        </div>
      </>
    );
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
        setSubmitting(false);
        // get json response here
        let data = await response.json();
        if (response.status === 200) {
          window.location.href = "/";
        } else {
          addNotification({
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
        console.error(error);
        if (error instanceof SyntaxError) {
          addNotification({
            title: props.intl.formatMessage({
              id: "notification.title",
            }),
            message: props.intl.formatMessage({
              id: "notification.login.syntax.error",
            }),
            kind: NotificationKinds.error,
          });
          setNotificationVisible(true);
        } else {
          addNotification({
            title: props.intl.formatMessage({
              id: "notification.title",
            }),
            message: props.intl.formatMessage({
              id: "notification.login.generic.error",
            }),
            kind: NotificationKinds.error,
          });
          setNotificationVisible(true);
        }
      });
  };

  return (
    <>
      <div className="loginPageContent">
        {notificationVisible === true ? <AlertDialog /> : ""}
        <Grid fullWidth={true}>
          <Column lg={0} md={0} sm={4}>
            {loginMessage()}
          </Column>
          <Column lg={4} md={4} sm={4}>
            <Section>
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
                      console.error(error);
                    });
                }}
              >
                {({ isValid, handleChange, handleSubmit }) => (
                  <Form onSubmit={handleSubmit} onChange={handleChange}>
                    <Stack gap={5}>
                      <FormLabel>
                        <Heading>
                          <FormattedMessage id="login.title" />
                        </Heading>
                      </FormLabel>
                      <TextInput
                        id="loginName"
                        invalidText={props.intl.formatMessage({
                          id: "login.msg.username.missing",
                        })}
                        labelText={props.intl.formatMessage({
                          id: "login.msg.username",
                        })}
                        hideLabel={true}
                        placeholder={props.intl.formatMessage({
                          id: "login.msg.username",
                        })}
                        autoComplete="off"
                        ref={firstInput}
                      />
                      <TextInput.PasswordInput
                        id="password"
                        invalidText={props.intl.formatMessage({
                          id: "login.msg.password.missing",
                        })}
                        labelText={props.intl.formatMessage({
                          id: "login.msg.password",
                        })}
                        hideLabel={true}
                        placeholder={props.intl.formatMessage({
                          id: "login.msg.password",
                        })}
                      />
                      <Button type="submit" disabled={!isValid}>
                        <FormattedMessage id="label.button.submit" />
                        <Loading
                          small={true}
                          withOverlay={false}
                          className={submitting ? "show" : "hidden"}
                        />
                      </Button>
                      {configurationProperties?.useSaml == "true" && (
                        <Button
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
                        </Button>
                      )}
                    </Stack>
                  </Form>
                )}
              </Formik>
            </Section>
          </Column>
          <Column lg={8} md={4} sm={0}>
            {loginMessage()}
          </Column>
        </Grid>
      </div>
    </>
  );
}

export default injectIntl(Login);
