import React from "react";
import config from "../config.json";
import "./Style.css";
import qs from "qs";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
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
import { Formik, Field } from "formik";
import { useNavigate } from "react-router-dom";
import { getFromOpenElisServer, postToOpenElisServer } from "./utils/Utils";
import { Add } from "@carbon/react/icons";

class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = { formValid: false };
  }

  validate = (values) => {
    let errors = {
      items: [],
    };

    if (values.items.length > 0) {
      values.items.forEach((item) => {
        const error = {};
        if (item.value.length < 0) {
          error.value = "Too few characters!";
        }
        errors.items.push(error);
      });
    }

    return errors;
  };

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
    fetch(config.serverBaseUrl + "/ValidateLogin", {
      //includes the browser sessionId in the Header for Authentication on the backend server
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: qs.stringify(data),
    })
      .then((response) => response.status)
      .then((status) => {
        window.location.href = "/";
        //console.log(JSON.stringify(jsonResp))
      })
      .catch((error) => {
        console.log(error);
      });
  };

  render() {
    return (
      <>
        <div className="loginPageContent">
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
