import React from "react";
import {
  BrowserRouter as Router,
  Route,
  Switch,
  Redirectx,
} from "react-router-dom";
import { IntlProvider } from "react-intl";
import Layout from "./components/layout/Layout";
import Home from "./components/Home";
import Login from "./components/Login";
import Admin from "./components/admin/Admin";
import {
  getFromOpenElisServer,
  postToOpenElisServer,
} from "./components/utils/Utils";
import "./App.css";
import messages_en from "./languages/en.json";
import messages_fr from "./languages/fr.json";
import config from "./config.json";
import { SecureRoute } from "./components/security";
import "./index.scss";
import PatientManagement from "./components/patient/PatientManagement";
import PatientHistory from "./components/patient/PatientHistory";
//import "./components/patient/resultsViewer/results-viewer.styles.scss"
import AddOrder from "./components/addOrder/Index";

let i18nConfig = {
  locale: navigator.language.split(/[-_]/)[0],
  defaultLocale: "en",
  messages: messages_en,
};

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      authenticated: false,
      user: {},
      config: config,
    };
    i18nConfig.locale =
      localStorage.getItem("locale") || navigator.language.split(/[-_]/)[0];
    switch (i18nConfig.locale) {
      case "en":
        i18nConfig.messages = messages_en;
        break;
      case "fr":
        i18nConfig.messages = messages_fr;
        break;
      default:
        i18nConfig.messages = messages_en;
        break;
    }
  }

  logout = () => {
    fetch(config.serverBaseUrl + "/Logout", {
      //includes the browser sessionId in the Header for Authentication on the backend server
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "X-CSRF-Token": localStorage.getItem("CSRF"),
      },
    })
      .then((response) => response.status)
      .then((status) => {
        this.setState({ authenticated: false });
        window.location.href = config.loginRedirect;
        //console.log(JSON.stringify(jsonResp))
      })
      .catch((error) => {
        console.log(error);
      });
  };

  onAuth = (user) => {
    this.setState({ authenticated: true });
    this.setState({ user: user });
  };

  isLoggedIn = () => {
    return this.state.authenticated;
  };

  changeLanguage = (lang) => {
    switch (lang) {
      case "en":
        i18nConfig.messages = messages_en;
        break;
      case "fr":
        i18nConfig.messages = messages_fr;
        break;
      default:
        i18nConfig.messages = messages_en;
        break;
    }
    i18nConfig.locale = lang;
    this.setState({ locale: lang });
    localStorage.setItem("locale", lang);
  };

  getLocale = () => {
    return this.state.locale;
  };

  onChangeLanguage = (lang) => {
    this.changeLanguage(lang);
  };

  render() {
    return (
      <IntlProvider
        locale={i18nConfig.locale}
        key={i18nConfig.locale}
        defaultLocale={i18nConfig.defaultLocale}
        messages={i18nConfig.messages}
      >
        <>
          <Router>
            <Layout
              config={this.state.config}
              onChangeLanguage={this.onChangeLanguage}
              logout={this.logout}
              isLoggedIn={this.isLoggedIn}
              user={this.state.user}
            >
              <Switch>
                <Route
                  path="/login"
                  exact
                  component={() => (
                    <Login onAuth={this.onAuth} />
                  )}
                />
                <SecureRoute
                  path="/"
                  exact
                  component={() => <Home />}
                  role=""
                  config={this.state.config}
                  onAuth={this.onAuth}
                  logout={this.logout}
                  isLoggedIn={this.isLoggedIn}
                />
                <SecureRoute
                  path="/admin"
                  exact
                  component={() => <Admin />}
                  role="Global Administrator"
                  config={this.state.config}
                  onAuth={this.onAuth}
                  logout={this.logout}
                  isLoggedIn={this.isLoggedIn}
                />
                <SecureRoute
                    path="/AddOrder"
                    exact
                    component={() => <AddOrder />}
                    role="Reception"
                    config={this.state.config}
                    onAuth={this.onAuth}
                    logout={this.logout}
                    isLoggedIn={this.isLoggedIn}
                />
                <SecureRoute
                  path="/PatientManagement"
                  exact
                  component={() => <PatientManagement />}
                  role="Reception"
                  config={this.state.config}
                  onAuth={this.onAuth}
                  logout={this.logout}
                  isLoggedIn={this.isLoggedIn}
                />
                <SecureRoute
                  path="/PatientHistory"
                  exact
                  component={() => <PatientHistory />}
                  role="Reception"
                  config={this.state.config}
                  onAuth={this.onAuth}
                  logout={this.logout}
                  isLoggedIn={this.isLoggedIn}
                />
              </Switch>
            </Layout>
          </Router>
        </>
      </IntlProvider>
    );
  }
}

export default App;
