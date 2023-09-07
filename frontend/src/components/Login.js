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
import { NotificationContext } from "./layout/Layout";

function Login(props) {
  const { notificationVisible, setNotificationBody, setNotificationVisible } =
    useContext(NotificationContext);
  const { userSessionDetails } = useContext(UserSessionDetailsContext);
  const [submitting, setSubmitting] = useState(false);
  const firstInput = createRef();

  useEffect(() => {
    firstInput.current.focus();
    if (userSessionDetails.authenticated) {
      window.location.href = "/";
    }
  }, []);

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
        // get json response here
        let data = await response.json();
        setSubmitting(false);
        if (response.status === 200) {
          window.location.href = "/";
        } else {
          setNotificationBody({
            title: <FormattedMessage id="notification.title" />,
            message: data.error,
            kind: NotificationKinds.error,
          });
          setNotificationVisible(true);
        }
      })
      .catch((error) => {
        setSubmitting(false);
        console.log(error);
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
                      console.log(error);
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
                        className="inputText"
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
                        className="inputText"
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
                      </Button>
                      {submitting && (
                        <Loading small={true} withOverlay={false} />
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
