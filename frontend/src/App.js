import React, {useState} from "react";
import {
  BrowserRouter as Router,
  Route,
  Switch,
} from "react-router-dom";
import { IntlProvider } from "react-intl";
import Layout from "./components/layout/Layout";
import Home from "./components/Home";
import Login from "./components/Login";
import { Admin, Result } from "./components";
import UserSessionDetailsContext from "./UserSessionDetailsContext"
import "./App.css";
import messages_en from "./languages/en.json";
import messages_fr from "./languages/fr.json";
import config from "./config.json";
import { SecureRoute } from "./components/security";
import "./index.scss";
import PatientManagement from "./components/patient/PatientManagement";
import PatientHistory from "./components/patient/PatientHistory";
import Workplan from "./components/workplan/Workplan";
//import "./components/patient/resultsViewer/results-viewer.styles.scss"
import AddOrder from "./components/addOrder/Index";
import ModifyOrder from "./components/modifyOrder/Index";
import RoutineReports from "./components/Reports/Routine";
import StudyReports from "./components/Reports/Study";
import StudyValidation from "./components/validation/Study";
import Validation from "./components/validation/Index";
// import Validation from "./components/resultValidation/ResultValidation";
import PathologyDashboard from "./components/pathology/PathologyDashboard";
import PathologyCaseView from "./components/pathology/PathologyCaseView";
import ImmunohistochemistryDashboard from "./components/immunohistochemistry/ImmunohistochemistryDashboard"
import ImmunohistochemistryCaseView from "./components/immunohistochemistry/ImmunohistochemistryCaseView"
import RoutedResultsViewer  from './components/patient/resultsViewer/results-viewer.tsx'

export default function App() {

  let i18nConfig = {
    locale: navigator.language.split(/[-_]/)[0],
    defaultLocale: "en",
    messages: messages_en,
  };

  const [authenticated, setAuthenticated] = useState(false);
  const [user, setUser] = useState({});
  const [appConfig, setAppConfig] = useState(config);
  const [userSessionDetails, setUserSessionDetails] = useState({});


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

  const logout = () => {
    fetch(appConfig.serverBaseUrl + "/Logout", {
      //includes the browser sessionId in the Header for Authentication on the backend server
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "X-CSRF-Token": localStorage.getItem("CSRF"),
      },
    })
      .then((response) => response.status)
      .then((status) => {
        setAuthenticated(false);
        window.location.href = appConfig.loginRedirect;
        //console.log(JSON.stringify(jsonResp))
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const onAuth = (user) => {
    setAuthenticated(true);
    setUser(user);
  };

  const isLoggedIn = () => {
    return authenticated;
  };

  const changeLanguage = (lang) => {
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
    localStorage.setItem("locale", lang);
  };

  const onChangeLanguage = (lang) => {
    changeLanguage(lang);
  };

    return (
      <IntlProvider
        locale={i18nConfig.locale}
        key={i18nConfig.locale}
        defaultLocale={i18nConfig.defaultLocale}
        messages={i18nConfig.messages}
      >
        <UserSessionDetailsContext.Provider value={{userSessionDetails, setUserSessionDetails}}>
          <>
          <Router>
            <Layout
              config={appConfig}
              onChangeLanguage={onChangeLanguage}
              logout={logout}
              isLoggedIn={isLoggedIn}
              user={user}
            >
              <Switch>
                <Route
                  path="/login"
                  exact
                  component={() => (
                    <Login onAuth={onAuth} />
                  )}
                />
                <SecureRoute
                  path="/"
                  exact
                  component={() => <Home />}
                  role=""
                  config={appConfig}
                  onAuth={onAuth}
                  logout={logout}
                  isLoggedIn={isLoggedIn}
                />
                <SecureRoute
                  path="/admin"
                  exact
                  component={() => <Admin />}
                  role="Global Administrator"
                  config={appConfig}
                  onAuth={onAuth}
                  logout={logout}
                  isLoggedIn={isLoggedIn}
                />
                <SecureRoute
                  path="/PathologyDashboard"
                  exact
                  component={() => <PathologyDashboard />}
                  role={["Pathologist", "Reception"]}
                  config={appConfig}
                  onAuth={onAuth}
                  logout={logout}
                  isLoggedIn={isLoggedIn}
                />
                <SecureRoute
                  path="/PathologyCaseView/:pathologySampleId"
                  exact
                  component={() => <PathologyCaseView />}
                  role={["Pathologist", "Reception"]}
                  config={appConfig}
                  onAuth={onAuth}
                  logout={logout}
                  isLoggedIn={isLoggedIn}
                />
                <SecureRoute
                  path="/ImmunohistochemistryDashboard"
                  exact
                  component={() => <ImmunohistochemistryDashboard />}
                  role={["Pathologist", "Reception"]}
                  config={appConfig}
                  onAuth={onAuth}
                  logout={logout}
                  isLoggedIn={isLoggedIn}
                />
                <SecureRoute
                  path="/ImmunohistochemistryCaseView/:immunohistochemistrySampleId"
                  exact
                  component={() => <ImmunohistochemistryCaseView />}
                  role={["Pathologist", "Reception"]}
                  config={appConfig}
                  onAuth={onAuth}
                  logout={logout}
                  isLoggedIn={isLoggedIn}
                />
                <SecureRoute
                    path="/AddOrder"
                    exact
                    component={() => <AddOrder />}
                    role={["Reception"]}
                    config={appConfig}
                    onAuth={onAuth}
                    logout={logout}
                    isLoggedIn={isLoggedIn}
                />
                 <SecureRoute
                    path="/ModifyOrder"
                    exact
                    component={() => <ModifyOrder />}
                    role="Reception"
                    config={appConfig}
                    onAuth={onAuth}
                    logout={logout}
                    isLoggedIn={isLoggedIn}
                />
                <SecureRoute
                  path="/PatientManagement"
                  exact
                  component={() => <PatientManagement />}
                  role="Reception"
                  config={appConfig}
                  onAuth={onAuth}
                  logout={logout}
                  isLoggedIn={isLoggedIn}
                />
                <SecureRoute
                  path="/PatientHistory"
                  exact
                  component={() => <PatientHistory />}
                  role="Reception"
                  config={appConfig}
                  onAuth={onAuth}
                  logout={logout}
                  isLoggedIn={isLoggedIn}
                />
                <SecureRoute
                  path="/PatientResults/:patientId"
                  exact
                  component={() => <RoutedResultsViewer />}
                  role="Reception"
                  config={appConfig}
                  onAuth={onAuth}
                  logout={logout}
                  isLoggedIn={isLoggedIn}
                />
                <SecureRoute
                  path="/WorkPlanByTestSection"
                  exact
                  component={() => <Workplan type="unit"/>}
                  role="Results"
                  config={appConfig}
                  onAuth={onAuth}
                  logout={logout}
                  isLoggedIn={isLoggedIn}
                />
                <SecureRoute
                  path="/WorkplanByTest"
                  exact
                  component={() => <Workplan type="test"/>}
                  role="Results"
                  config={appConfig}
                  onAuth={onAuth}
                  logout={logout}
                  isLoggedIn={isLoggedIn}
                />
                <SecureRoute
                  path="/WorkplanByPanel"
                  exact
                  component={() => <Workplan type="panel"/>}
                  role="Results"
                  config={appConfig}
                  onAuth={onAuth}
                  logout={logout}
                  isLoggedIn={isLoggedIn}
                />
                <SecureRoute
                  path="/WorkplanByPriority"
                  exact
                  component={() => <Workplan type="priority"/>}
                  role="Results"
                  config={appConfig}
                  onAuth={onAuth}
                  logout={logout}
                  isLoggedIn={isLoggedIn}
                />
                  <SecureRoute path="/result" exact component={() => <Result />} role="Global Administrator" config={appConfig} onAuth={onAuth} logout={logout} isLoggedIn={isLoggedIn} />
                  <SecureRoute path="/AccessionResults" exact component={() => <Admin />} role="Global Administrator" config={appConfig} onAuth={onAuth} logout={logout} isLoggedIn={isLoggedIn} />
                  <SecureRoute path="/RoutineReports" exact component={() => <RoutineReports />} role="Global Administrator" config={appConfig} onAuth={onAuth} logout={logout} isLoggedIn={isLoggedIn} />
                  <SecureRoute path="/StudyReports" exact component={() => <StudyReports />} role="Global Administrator" config={appConfig} onAuth={onAuth} logout={logout} isLoggedIn={isLoggedIn} />
                  <SecureRoute path="/validationStudy" exact component={() => <StudyValidation />} role="Global Administrator" config={appConfig} onAuth={onAuth} logout={logout} isLoggedIn={isLoggedIn} />
                <SecureRoute path="/validation" exact component={() => <Validation />} role="Global Administrator" config={appConfig} onAuth={onAuth} logout={logout} isLoggedIn={isLoggedIn} />
              </Switch>
            </Layout>
          </Router>
        </>
        </UserSessionDetailsContext.Provider>
      </IntlProvider>
    );
  }

