import React from "react";
import { FormattedMessage, injectIntl } from "react-intl";
import { withRouter } from "react-router-dom";
import "../Style.css";
import { faLanguage, faSignOutAlt } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Button, Select, SelectItem } from "@carbon/react";
import {
  Search,
  Notification,
  Switcher,
  User,
  Language,
  UserAvatarFilledAlt,
  Fade,
  Menu,
  Logout,
} from "@carbon/icons-react";
import {
  Header,
  HeaderName,
  HeaderGlobalAction,
  HeaderGlobalBar,
  HeaderNavigation,
  HeaderMenu,
  HeaderMenuButton,
  HeaderMenuItem,
  Theme,
  Content,
  HeaderPanel,
} from "@carbon/react";

class OEHeader extends React.Component {
  constructor(props) {
    super(props);
    this.userSwitchRef = React.createRef();
    this.headerPanelRef = React.createRef();
    this.state = {
      switchCollapsed: true,
      language: "en",
    };
  }

  outsideClickListener = (event) => {
    if (
      !this.userSwitchRef.current.contains(event.target) &&
      !this.headerPanelRef.current.contains(event.target)
    ) {
      this.dismissPanel();
      window.removeEventListener("click", this.outsideClickListener);
    }
  };

  componentWillUnmount() {
    window.removeEventListener("click", this.outsideClickListener);
  }

  clickUser = () => {
    const userSwitchCollapsed = this.state.switchCollapsed;
    this.setState((state) => ({
      switchCollapsed: !userSwitchCollapsed,
    }));
    if (userSwitchCollapsed) {
      window.addEventListener("click", this.outsideClickListener);
    }
  };

  dismissPanel = (event) => {
    this.setState((state) => ({
      switchCollapsed: true,
    }));
  };

  logo = () => {
    return (
      <>
        <picture>
          <img className="logo" src={`images/openelis_logo.png`} alt="logo" />
        </picture>
      </>
    );
  };

  render() {
    return (
      <>
        <div className="container">
          <Theme>
            <Header className="header" aria-label="">
              <HeaderName href="/" prefix="">
                <span id="header-logo">{this.logo()}</span>
                <span id="header-title">{this.props.config.title}</span>
              </HeaderName>
              {this.props.isLoggedIn() && (
                <>
                  <HeaderNavigation aria-label="nav">
                    <HeaderMenu aria-label="Order" menuLinkName="Order">
                      <HeaderMenuItem href="#">Sub-link 1</HeaderMenuItem>
                      <HeaderMenuItem href="#">Sub-link 2</HeaderMenuItem>
                      <HeaderMenuItem href="#">Sub-link 3</HeaderMenuItem>
                    </HeaderMenu>
                    <HeaderMenu aria-label="Patient" menuLinkName="Patient">
                      <HeaderMenuItem href="/PatientManagement">Add/Edit Patient</HeaderMenuItem>
                      <HeaderMenuItem href="#">Sub-link 2</HeaderMenuItem>
                      <HeaderMenuItem href="#">Sub-link 3</HeaderMenuItem>
                    </HeaderMenu>
                    <HeaderMenu
                      aria-label="Non-Conforming Events"
                      menuLinkName="Non-Conform"
                    >
                      <HeaderMenuItem href="#">Sub-link 1</HeaderMenuItem>
                      <HeaderMenuItem href="#">Sub-link 2</HeaderMenuItem>
                      <HeaderMenuItem href="#">Sub-link 3</HeaderMenuItem>
                    </HeaderMenu>
                    <HeaderMenu aria-label="Workplan" menuLinkName="Workplan">
                      <HeaderMenuItem href="#">Sub-link 1</HeaderMenuItem>
                      <HeaderMenuItem href="#">Sub-link 2</HeaderMenuItem>
                      <HeaderMenuItem href="#">Sub-link 3</HeaderMenuItem>
                    </HeaderMenu>
                    <HeaderMenu aria-label="Results" menuLinkName="Results">
                      <HeaderMenuItem href="#">Sub-link 1</HeaderMenuItem>
                      <HeaderMenuItem href="#">Sub-link 2</HeaderMenuItem>
                      <HeaderMenuItem href="#">Sub-link 3</HeaderMenuItem>
                    </HeaderMenu>
                    <HeaderMenu
                      aria-label="Validation"
                      menuLinkName="Validation"
                    >
                      <HeaderMenuItem href="#">Sub-link 1</HeaderMenuItem>
                      <HeaderMenuItem href="#">Sub-link 2</HeaderMenuItem>
                      <HeaderMenuItem href="#">Sub-link 3</HeaderMenuItem>
                    </HeaderMenu>
                    <HeaderMenu aria-label="Reports" menuLinkName="Reports">
                      <HeaderMenuItem href="#">Sub-link 1</HeaderMenuItem>
                      <HeaderMenuItem href="#">Sub-link 2</HeaderMenuItem>
                      <HeaderMenuItem href="#">Sub-link 3</HeaderMenuItem>
                    </HeaderMenu>
                    <HeaderMenuItem href="/admin">Admin</HeaderMenuItem>
                  </HeaderNavigation>
                  <HeaderGlobalBar>
                    <HeaderGlobalAction aria-label="Search" onClick={() => {}}>
                      <Search size={20} />
                    </HeaderGlobalAction>
                    <HeaderGlobalAction
                      aria-label="Notifications"
                      onClick={() => {}}
                    >
                      <Notification size={20} />
                    </HeaderGlobalAction>
                    <HeaderGlobalAction
                      aria-label="User"
                      onClick={this.clickUser}
                      ref={this.userSwitchRef}
                    >
                      <UserAvatarFilledAlt size={20} />
                    </HeaderGlobalAction>
                  </HeaderGlobalBar>
                  <HeaderPanel
                    aria-label="Header Panel"
                    expanded={!this.state.switchCollapsed}
                    className="headerPanel"
                    ref={this.headerPanelRef}
                  >
                    <ul>
                      <li className="userDetails">
                        <UserAvatarFilledAlt size={18} />{" "}
                        {this.props.user.firstName} {this.props.user.lastName}
                      </li>
                      <li
                        className="userDetails clickableUserDetails"
                        onClick={this.props.logout}
                      >
                        <FontAwesomeIcon
                          id="sign-out"
                          icon={faSignOutAlt}
                          size="1x"
                        />{" "}
                        Logout
                      </li>
                      <li className="userDetails">
                        <Select
                          id="selector"
                          name="selectLocale"
                          className="selectLocale"
                          invalidText="A valid locale value is required"
                          labelText="Select locale"
                          onChange={(event) =>
                            this.props.onChangeLanguage(event.target.value)
                          }
                          onClick={(event) => event.stopPropagation()}
                        >
                          <SelectItem text="English" value="en" />
                          <SelectItem text="French" value="fr" />
                        </Select>
                      </li>
                    </ul>
                  </HeaderPanel>
                </>
              )}
              {!this.props.isLoggedIn() && (
                <>
                  <HeaderGlobalBar>
                    <HeaderGlobalAction
                      aria-label="Lang"
                      onClick={this.toggleSwitch}
                    >
                      <Language size={20} />
                    </HeaderGlobalAction>
                  </HeaderGlobalBar>
                  <HeaderPanel
                    aria-label="Header Panel"
                    expanded={!this.state.switchCollapsed}
                    className="headerPanel"
                  >
                    <ul>
                      <li className="userDetails">
                        <Select
                          id="selector"
                          name="selectLocale"
                          className="selectLocale"
                          invalidText="A valid locale value is required"
                          labelText="Select locale"
                          onChange={(event) =>
                            this.props.onChangeLanguage(event.target.value)
                          }
                          value={this.props.intl.locale}
                          onClick={(event) => event.stopPropagation()}
                        >
                          <SelectItem text="English" value="en" />
                          <SelectItem text="French" value="fr" />
                        </Select>
                      </li>
                    </ul>
                  </HeaderPanel>
                </>
              )}
            </Header>
          </Theme>
        </div>
      </>
    );
  }
}
export default withRouter(injectIntl(OEHeader));
