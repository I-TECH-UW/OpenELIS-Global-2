import React from "react";
import { FormattedMessage, injectIntl } from "react-intl";
import { withRouter } from "react-router-dom";
import "../Style.css";
import { Select, SelectItem } from "@carbon/react";
import config from "../../config.json";
import {
  Search,
  Notification,
  Language,
  UserAvatarFilledAlt,
  Logout,
  Query,
  TableOfContents,
  WarningAlt,
  Microscope,
  Fade
} from "@carbon/icons-react";

import {
  HeaderContainer,
  Header,
  HeaderMenuButton,
  HeaderName,
  HeaderGlobalAction,
  HeaderGlobalBar,
  HeaderNavigation,
  SideNavMenu,
  SideNavMenuItem,
  SideNav,
  HeaderSideNavItems,
  SideNavItems,
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
    const { formatMessage } = this.props.intl;
    return (
      <>
        <div className="container">
          <Theme>
            <HeaderContainer
              render={({ isSideNavExpanded, onClickSideNavExpand }) => (
                <Header id="mainHeader" className="mainHeader" aria-label="">

                  {this.props.isLoggedIn() &&
                    <HeaderMenuButton
                      aria-label={isSideNavExpanded ? 'Close menu' : 'Open menu'}
                      onClick={onClickSideNavExpand}
                      isActive={isSideNavExpanded}
                      isCollapsible={true}
                    />}
                  <HeaderName href="/" prefix="">
                    <span id="header-logo">{this.logo()}</span>
                    <span id="header-title">{this.props.config.title}</span>
                  </HeaderName>

                  <HeaderGlobalBar>
                    {this.props.isLoggedIn() && (
                      <>
                        <HeaderGlobalAction aria-label="Search" onClick={() => { }}>
                          <Search size={20} />
                        </HeaderGlobalAction>
                        <HeaderGlobalAction
                          aria-label="Notifications"
                          onClick={() => { }}
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
                  {this.props.isLoggedIn() && (
                    <>
                      <SideNav aria-label="Side navigation"
                        expanded={isSideNavExpanded}
                        isPersistent={false}>
                        <SideNavItems>
                          <SideNavMenu aria-label="Order" title="Order">
                            <SideNavMenuItem href="/AddOrder">Add Order</SideNavMenuItem>
                            <SideNavMenuItem href="/ModifyOrder">Modify Order</SideNavMenuItem>
                            <SideNavMenuItem href={config.serverBaseUrl + "/ElectronicOrders"}>Incoming Orders</SideNavMenuItem>
                            <SideNavMenuItem href={config.serverBaseUrl + "/SampleBatchEntrySetup"}>Batch Order Entry</SideNavMenuItem>
                            <SideNavMenuItem href={config.serverBaseUrl + "/PrintBarcode"}>Barcode</SideNavMenuItem>
                          </SideNavMenu>
                          <SideNavMenu aria-label="Patient" title="Patient">
                            <SideNavMenuItem href="/PatientManagement">Add/Edit Patient</SideNavMenuItem>
                            <SideNavMenuItem href="/PatientHistory">Patient History</SideNavMenuItem>
                          </SideNavMenu>
                          <SideNavMenu
                            aria-label="Non-Conforming Events"
                            title="Non-Conform"
                          >
                            <SideNavMenuItem href={config.serverBaseUrl + "/ReportNonConformingEvent"}>Report Non-Conforming Event</SideNavMenuItem>
                            <SideNavMenuItem href={config.serverBaseUrl + "/ViewNonConformingEvent"}>View New Non-Conforming Events</SideNavMenuItem>
                            <SideNavMenuItem href={config.serverBaseUrl + "/NCECorrectiveAction"}>Corrective actions</SideNavMenuItem>
                          </SideNavMenu>
                          <SideNavMenu aria-label="Workplan" title={formatMessage({id:"banner.menu.workplan"})}>
                            <SideNavMenuItem href="/WorkplanByTest"><Query /> &nbsp;<FormattedMessage id="banner.menu.workplan.test"/></SideNavMenuItem>
                            <SideNavMenuItem href="/WorkplanByPanel"><TableOfContents />&nbsp;<FormattedMessage id="banner.menu.workplan.panel"/></SideNavMenuItem>
                            <SideNavMenuItem href="/WorkplanByUnit"><Microscope />&nbsp;<FormattedMessage id="banner.menu.workplan.unit"/></SideNavMenuItem>
                            <SideNavMenuItem href="/WorkplanByPriority"><WarningAlt />&nbsp;<FormattedMessage id="banner.menu.workplan.priority"/></SideNavMenuItem>
                          </SideNavMenu>
                          <SideNavMenu
                            aria-label="Pathology" title="Pathology"                    >
                            <SideNavMenuItem href={"/PathologyDashboard"}>Dashboard</SideNavMenuItem>
                          </SideNavMenu>
                          <SideNavMenu aria-label="Results" title="Results">
                            <SideNavMenuItem href={config.serverBaseUrl + "/LogbookResults?type="}>Enter by Unit</SideNavMenuItem>
                            <SideNavMenuItem href="/result">Search</SideNavMenuItem>
                            <SideNavMenuItem href={config.serverBaseUrl + "/ReferredOutTests"}>Referred Tests</SideNavMenuItem>
                          </SideNavMenu>
                          <SideNavMenu
                            aria-label="Validation" title="Validation"                    >
                            <SideNavMenuItem href={config.serverBaseUrl + "/ResultValidation?type=&test="}>Routine</SideNavMenuItem>
                            <SideNavMenuItem href="/validationStudy">Study</SideNavMenuItem>
                            <SideNavMenuItem href="/validation">Search</SideNavMenuItem>
                          </SideNavMenu>

                          <SideNavMenu aria-label="Reports" title="Reports">
                            <SideNavMenuItem href="/RoutineReports">Routine</SideNavMenuItem>
                            <SideNavMenuItem href="/StudyReports">Study</SideNavMenuItem>
                          </SideNavMenu>
                          <SideNavMenuItem href="/admin">Admin</SideNavMenuItem>

                          <SideNavMenuItem target="_blank" href={"http://ozone.uwdigi.org:8069/"}><FormattedMessage id="admin.billing" /></SideNavMenuItem>

                        </SideNavItems>
                      </SideNav>
                    </>
                  )}
                </Header>
              )}
            />
          </Theme>
        </div>
      </>
    );
  }
}
export default withRouter(injectIntl(OEHeader));
