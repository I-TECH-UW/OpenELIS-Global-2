import React from "react";
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
  Stack
} from "@carbon/react";
import { Formik } from "formik";
import {AlertDialog, NotificationKinds} from "./common/CustomNotification";
import {NotificationContext} from "./layout/Layout";

class Login extends React.Component {
  static contextType = NotificationContext;

  constructor(props) {
    super(props);
    this.state = { formValid: false, loginError: "" };
    this.firstInput = React.createRef();
  }

  componentDidMount() {
    // CSL - this is a workaround for checking login state when NOT in a secure route
    // if more pages require this checking, we need to find a way to centralize checking login state
    // as App.js only has the correct state if mounting a SecureRoute
    fetch(
      config.serverBaseUrl + `/session`,
      //includes the browser sessionId in the Header for Authentication on the backend server
      { credentials: "include" }
    )
      .then((response) => response.json())
      .then((jsonResp) => {
        console.log(JSON.stringify(jsonResp));
        if (jsonResp.authenticated) {
          window.location.href = "/";
        }
      });

    this.firstInput.current.focus(); 
  }

  handlePost = (status) => {
    alert(status);
  };

  loginMessage = () => {
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

  doLogin = (data) => {
    fetch(config.serverBaseUrl + "/ValidateLogin?apiCall=true", {
      //includes the browser sessionId in the Header for Authentication on the backend server
      credentials: 'include',
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: qs.stringify(data),
    })
      .then(async (response) => {
        // get json response here
        let data = await response.json();

        if (response.status === 200) {
          window.location.href = "/";
        } else {
          console.log(this.context.notificationVisible);
          this.context.setNotificationBody({title: "Notification Message", message: data.error, kind: NotificationKinds.error})
          this.context.setNotificationVisible(true);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  render() {
    return (
      <>
        <div className="loginPageContent">
          {this.context.notificationVisible === true ? <AlertDialog/> : ""}
          <Grid fullWidth={true}>
            <Column lg={0} md={0} sm={4}>
              {this.loginMessage()}
            </Column>
            <Column lg={4} md={4} sm={4}>
              <Section>
                <Formik
                  initialValues={{
                    username: "",
                    password: "",
                  }}
                  onSubmit={(values, actions) => {
                    fetch(config.serverBaseUrl + "/LoginPage", {
                      //includes the browser sessionId in the Header for Authentication on the backend server
                      credentials: 'include',
                      method: "GET",
                    })
                      .then((response) => response.status)
                      .then((status) => {
                        this.doLogin(values);
                        //console.log(JSON.stringify(jsonResp))
                      })
                      .catch((error) => {
                        console.log(error);
                      });
                  }}
                >
                  {({
                    values,
                    isValid,
                    errors,
                    touched,
                    handleChange,
                    handleSubmit,
                  }) => (
                    <Form onSubmit={handleSubmit} onChange={handleChange}>
                       <Stack gap={5}>
                      <FormLabel>
                        <Heading>
                          <FormattedMessage id="login.title" />
                        </Heading>
                      </FormLabel>
                      <div className="errorMessage">
                        {this.state.loginError && (
                          <FormattedMessage id={this.state.loginError} />
                        )}
                      </div>
                      <TextInput
                        className="inputText"
                        id="loginName"
                        invalidText={this.props.intl.formatMessage({
                          id: "login.msg.username.missing",
                        })}
                        labelText={this.props.intl.formatMessage({
                          id: "login.msg.username",
                        })}
                        hideLabel={true}
                        placeholder={this.props.intl.formatMessage({
                          id: "login.msg.username",
                        })}
                        autoComplete="off"
                        ref={this.firstInput}
                      />
                      <TextInput.PasswordInput
                        className="inputText"
                        id="password"
                        invalidText={this.props.intl.formatMessage({
                          id: "login.msg.password.missing",
                        })}
                        labelText={this.props.intl.formatMessage({
                          id: "login.msg.password",
                        })}
                        hideLabel={true}
                        placeholder={this.props.intl.formatMessage({
                          id: "login.msg.password",
                        })}
                      />
                      <Button type="submit" disabled={!isValid}>
                        <FormattedMessage id="label.button.submit" />
                      </Button>
                      </Stack>
                    </Form>
                  )}
                </Formik>
              </Section>
            </Column>
            <Column lg={8} md={4} sm={0}>
              {this.loginMessage()}
            </Column>
          </Grid>
        </div>
      </>
    );
  }
}

export default injectIntl(Login);
