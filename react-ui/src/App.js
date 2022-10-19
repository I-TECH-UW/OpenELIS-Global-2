import React from 'react';
import { BrowserRouter as Router, Route, Switch, Redirectx } from "react-router-dom";
import { IntlProvider } from 'react-intl';
import Layout from './components/layout/Layout';
import OpenElis from './components/OpenElis';
import Admin from './components/Admin';
import './App.css';
import messages_en from './languages/en.json';
import messages_fr from './languages/fr.json';
import config from './config.json';
import { SecureRoute } from "./components/security";

let i18nConfig = {
  locale: navigator.language.split(/[-_]/)[0],
  defaultLocale: 'en',
  messages: messages_en,
};

class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      authenticated: false,
      config: config
    }
    i18nConfig.locale = localStorage.getItem('locale') || navigator.language.split(/[-_]/)[0];
    switch (i18nConfig.locale) {
      case 'en': i18nConfig.messages = messages_en; break;
      case 'fr': i18nConfig.messages = messages_fr; break;
      default: i18nConfig.messages = messages_en; break;
    }
  }

  logout = () => {
    window.location.href = config.loginRedirect;
    this.setState({ authenticated: false });

  }

  onAuth = () => {
    this.setState({ authenticated: true });
  }

  isLoggedIn = () => {
    return this.state.authenticated;
  }

  changeLanguage = (lang) => {
    switch (lang) {
      case 'en': i18nConfig.messages = messages_en; break;
      case 'fr': i18nConfig.messages = messages_fr; break;
      default: i18nConfig.messages = messages_en; break;
    }
    i18nConfig.locale = lang;
    this.setState({ locale: lang });
    localStorage.setItem('locale', lang);
  }

  onChangeLanguage = (e) => {
    e.preventDefault();
    let lang = e.target.lang;
    this.changeLanguage(lang);
  }

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
            <Layout config={this.state.config} onChangeLanguage={this.onChangeLanguage} logout={this.logout} isLoggedIn={this.isLoggedIn} >
              <Switch>
                <Route path="/" exact component={OpenElis} />
                <SecureRoute path="/admin" exact component={Admin} config={this.state.config} onAuth={this.onAuth} logout={this.logout} isLoggedIn={this.isLoggedIn}/>
              </Switch>
            </Layout>
          </Router>
        </>
      </IntlProvider>
    );
  }
}


export default App;
