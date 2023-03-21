import React from "react";
import { injectIntl } from "react-intl";
import { withRouter } from "react-router-dom";
import "../Style.css";
import { Select, SelectItem } from "@carbon/react";
import {
  Search,
  Notification,
  Language,
  UserAvatarFilledAlt,
  Logout
} from "@carbon/icons-react";

import {
  Header,
  HeaderName,
  HeaderGlobalAction,
  HeaderGlobalBar,
  HeaderNavigation,
  HeaderMenu,
  HeaderMenuItem,
  Theme,
  HeaderPanel,
} from "@carbon/react";

class OEHeader extends React.Component {
  constructor(props) {
    super(props);
    this.userSwitchRef = React.createRef();
    this.headerPanelRef = React.createRef();
    this.state = {
      switchCollapsed: true,
    };
  }

  panelSwitchLabel = () => {
    return this.props.isLoggedIn() ? "User" : "Lang";
  };

  clickPanelSwitch = () => {
    this.setState((state) => ({
      switchCollapsed: !state.switchCollapsed
    }));
  }


  panelSwitchIcon = () => {
    return this.props.isLoggedIn() ? (
      <UserAvatarFilledAlt size={20} />
    ) : (
      <Language size={20} />
    );
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
            <Header className="" aria-label="">
              <HeaderName href="/" prefix="">
                <span id="header-logo">{this.logo()}</span>
                <span id="header-title">{this.props.config.title}</span>
              </HeaderName>
              {this.props.isLoggedIn() && (
                <>
                  <HeaderNavigation aria-label="nav">
                    <HeaderMenu aria-label="Order" menuLinkName="Order">
                      <HeaderMenuItem href="/AddOrder">Add Order</HeaderMenuItem>
                      <HeaderMenuItem href="#">Modify Order</HeaderMenuItem>
                      <HeaderMenuItem href="#">Incoming Orders</HeaderMenuItem>
                      <HeaderMenuItem href="#">Batch Order Entry</HeaderMenuItem>
                      <HeaderMenuItem href="#">Barcode</HeaderMenuItem>
                    </HeaderMenu>
                    <HeaderMenu aria-label="Patient" menuLinkName="Patient">
                      <HeaderMenuItem href="/PatientManagement">Add/Edit Patient</HeaderMenuItem>
                      <HeaderMenuItem href="/PatientHistory">Patient History</HeaderMenuItem>
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
                      <HeaderMenuItem href="/result">Result Search</HeaderMenuItem>
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
                </>
              )}
              <HeaderGlobalBar>
                {this.props.isLoggedIn() && (
                  <>
                    <HeaderGlobalAction aria-label="Search" onClick={() => {}}>
                      <Search size={20} />
                    </HeaderGlobalAction>
                    <HeaderGlobalAction
                      aria-label="Notifications"
                      onClick={() => {}}
                    >
                      <Notification size={20} />
                    </HeaderGlobalAction>
                  </>
                )}
                <HeaderGlobalAction
                  aria-label={this.panelSwitchLabel()}
                  onClick={this.clickPanelSwitch}
                  ref={this.userSwitchRef}
                >
                  {this.panelSwitchIcon()}
                </HeaderGlobalAction>
              </HeaderGlobalBar>
              <HeaderPanel
                aria-label="Header Panel"
                expanded={!this.state.switchCollapsed}
                className="headerPanel"
                ref={this.headerPanelRef}
              >
                <ul>
                  {this.props.isLoggedIn() && (
                    <>
                      <li className="userDetails">
                        <UserAvatarFilledAlt size={18} />{" "}
                        {this.props.user.firstName} {this.props.user.lastName}
                      </li>
                      <li
                        className="userDetails clickableUserDetails"
                        onClick={this.props.logout}
                      >
                        <Logout id="sign-out" />
                        Logout
                      </li>
                    </>
                  )}
                  <li className="userDetails">
                    <Select
                      id="selector"
                      name="selectLocale"
                      className="selectLocale"
                      invalidText="A valid locale value is required"
                      labelText="Select locale"
                      onChange={(event) => {
                        this.props.onChangeLanguage(event.target.value);
                      }}
                      value={this.props.intl.locale}
                    >
                      <SelectItem text="English" value="en" />
                      <SelectItem text="French" value="fr" />
                    </Select>
                  </li>
                </ul>
              </HeaderPanel>
            </Header>
          </Theme>
        </div>
      </>
    );
  }
}
export default withRouter(injectIntl(OEHeader));
