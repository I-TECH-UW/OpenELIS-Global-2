import React, { useContext, useState, createRef, useEffect } from "react";
import { FormattedMessage, injectIntl } from "react-intl";
import { withRouter } from "react-router-dom";
import { ConfigurationContext } from "../layout/Layout";
import UserSessionDetailsContext from "../../UserSessionDetailsContext";
import "../Style.css";
import { Select, SelectItem } from "@carbon/react";
import config from "../../config.json";
import { getFromOpenElisServer } from "../utils/Utils";
import {
  Search,
  Notification,
  Language,
  UserAvatarFilledAlt,
  Logout,
} from "@carbon/icons-react";

import {
  HeaderContainer,
  Header,
  HeaderMenuButton,
  HeaderName,
  HeaderGlobalAction,
  HeaderGlobalBar,
  SideNavMenu,
  SideNavMenuItem,
  SideNav,
  SideNavItems,
  Theme,
  HeaderPanel,
} from "@carbon/react";

function OEHeader(props) {
  const { releaseNumber } = useContext(ConfigurationContext);
  const { userSessionDetails, logout } = useContext(UserSessionDetailsContext);
  const [switchCollapsed, setSwitchCollapsed] = useState(true);
  const [header, setHeader] = useState({});
  const userSwitchRef = createRef();
  const headerPanelRef = createRef();
  const componentMounted = createRef(false);

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/header", loadHeader);

    return () => {
      componentMounted.current = false;
    };
  }, []);

  const loadHeader = (value) => {
    if (componentMounted.current) {
      setHeader(value);
    }
  };

  const panelSwitchLabel = () => {
    return userSessionDetails.authenticated ? "User" : "Lang";
  };

  const clickPanelSwitch = () => {
    setSwitchCollapsed(!switchCollapsed);
  };

  const panelSwitchIcon = () => {
    return userSessionDetails.authenticated ? (
      <UserAvatarFilledAlt size={20} />
    ) : (
      <Language size={20} />
    );
  };

  const logo = () => {
    return (
      <>
        <picture>
          <img
            className="logo"
            src={`../images/openelis_logo.png`}
            alt="logo"
          />
        </picture>
      </>
    );
  };

  return (
    <>
      <div className="container">
        <Theme>
          <HeaderContainer
            render={({ isSideNavExpanded, onClickSideNavExpand }) => (
              <Header id="mainHeader" className="mainHeader" aria-label="">
                {userSessionDetails.authenticated && (
                  <HeaderMenuButton
                    aria-label={isSideNavExpanded ? "Close menu" : "Open menu"}
                    onClick={onClickSideNavExpand}
                    isActive={isSideNavExpanded}
                    isCollapsible={true}
                  />
                )}
                <HeaderName href="/" prefix="">
                  <span id="header-logo">{logo()}</span>
                  <div className="banner">
                    <h5>{header.title}</h5>
                    <p> Version: {header.version}</p>
                  </div>
                </HeaderName>
                {userSessionDetails.authenticated && true && (
                  <>
                    {/* <HeaderMenuItem target="_blank" href={config.serverBaseUrl + "/MasterListsPage"}><FormattedMessage id="admin.billing"/></HeaderMenuItem> */}
                  </>
                )}
                <HeaderGlobalBar>
                  {userSessionDetails.authenticated && (
                    <>
                      <HeaderGlobalAction
                        aria-label="Search"
                        onClick={() => {
                          /*TODO add search functionality*/
                        }}
                      >
                        <Search size={20} />
                      </HeaderGlobalAction>
                      <HeaderGlobalAction
                        aria-label="Notifications"
                        onClick={() => {
                          /*TODO add notification functionality*/
                        }}
                      >
                        <Notification size={20} />
                      </HeaderGlobalAction>
                    </>
                  )}
                  <HeaderGlobalAction
                    aria-label={panelSwitchLabel()}
                    onClick={clickPanelSwitch}
                    ref={userSwitchRef}
                  >
                    {panelSwitchIcon()}
                  </HeaderGlobalAction>
                </HeaderGlobalBar>
                <HeaderPanel
                  aria-label="Header Panel"
                  expanded={!switchCollapsed}
                  className="headerPanel"
                  ref={headerPanelRef}
                >
                  <ul>
                    {userSessionDetails.authenticated && (
                      <>
                        <li className="userDetails">
                          <UserAvatarFilledAlt size={18} />{" "}
                          {userSessionDetails.firstName}{" "}
                          {userSessionDetails.lastName}
                        </li>
                        <li
                          className="userDetails clickableUserDetails"
                          onClick={logout}
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
                          props.onChangeLanguage(event.target.value);
                        }}
                        value={props.intl.locale}
                      >
                        <SelectItem text="English" value="en" />
                        <SelectItem text="French" value="fr" />
                      </Select>
                    </li>
                    <li className="userDetails">
                      <label className="cds--label">Ver: {releaseNumber}</label>
                    </li>
                  </ul>
                </HeaderPanel>
                {userSessionDetails.authenticated && (
                  <>
                    <SideNav
                      aria-label="Side navigation"
                      expanded={isSideNavExpanded}
                      isPersistent={false}
                    >
                      <SideNavItems>
                        <SideNavMenu aria-label="Order" title="Order">
                          <SideNavMenuItem href="/AddOrder">
                            Add Order
                          </SideNavMenuItem>
                          <SideNavMenuItem
                            href={
                              config.serverBaseUrl +
                              "/SampleEdit?type=readwrite"
                            }
                          >
                            Modify Order
                          </SideNavMenuItem>
                          <SideNavMenuItem
                            href={config.serverBaseUrl + "/ElectronicOrders"}
                          >
                            Incoming Orders
                          </SideNavMenuItem>
                          <SideNavMenuItem
                            href={
                              config.serverBaseUrl + "/SampleBatchEntrySetup"
                            }
                          >
                            Batch Order Entry
                          </SideNavMenuItem>
                          <SideNavMenuItem
                            href={config.serverBaseUrl + "/PrintBarcode"}
                          >
                            Barcode
                          </SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu aria-label="Patient" title="Patient">
                          <SideNavMenuItem href="/PatientManagement">
                            Add/Edit Patient
                          </SideNavMenuItem>
                          <SideNavMenuItem href="/PatientHistory">
                            Patient History
                          </SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu
                          aria-label="Non-Conforming Events"
                          title="Non-Conform"
                        >
                          <SideNavMenuItem
                            href={
                              config.serverBaseUrl + "/ReportNonConformingEvent"
                            }
                          >
                            Report Non-Conforming Event
                          </SideNavMenuItem>
                          <SideNavMenuItem
                            href={
                              config.serverBaseUrl + "/ViewNonConformingEvent"
                            }
                          >
                            View New Non-Conforming Events
                          </SideNavMenuItem>
                          <SideNavMenuItem
                            href={config.serverBaseUrl + "/NCECorrectiveAction"}
                          >
                            Corrective actions
                          </SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu aria-label="Workplan" title="Workplan">
                          <SideNavMenuItem href={"/WorkplanByTest"}>
                            By Test Type
                          </SideNavMenuItem>
                          <SideNavMenuItem href={"/WorkPlanByPanel"}>
                            By Panel Type
                          </SideNavMenuItem>
                          <SideNavMenuItem href={"/WorkPlanByTestSection"}>
                            By Unit
                          </SideNavMenuItem>
                          <SideNavMenuItem href={"/WorkplanByPriority"}>
                            By Priority
                          </SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu aria-label="Pathology" title="Pathology">
                          <SideNavMenuItem href={"/PathologyDashboard"}>
                            Dashboard
                          </SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu
                          aria-label="Immunohistochemistry"
                          title="Immunohistochemistry"
                        >
                          <SideNavMenuItem
                            href={"/ImmunohistochemistryDashboard"}
                          >
                            Dashboard
                          </SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu aria-label="Cytology" title="Cytology">
                          <SideNavMenuItem href={"/CytologyDashboard"}>
                            Dashboard
                          </SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu aria-label="Results" title="Results">
                          <SideNavMenuItem href="/result?type=unit">
                            By Unit
                          </SideNavMenuItem>
                          <SideNavMenuItem href="/result?type=patient">
                            By Patient
                          </SideNavMenuItem>
                          <SideNavMenuItem href="/result?type=order">
                            By Order
                          </SideNavMenuItem>
                          <SideNavMenuItem href="/result?type=date">
                            By Test Date
                          </SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu aria-label="Validation" title="Validation">
                          <SideNavMenuItem href="/validation?type=routine">
                            {" "}
                            Search By Routine
                          </SideNavMenuItem>
                          <SideNavMenuItem href="/validationStudy">
                            Search By Study
                          </SideNavMenuItem>
                          <SideNavMenuItem href="/validation?type=order">
                            Search By Order
                          </SideNavMenuItem>
                          <SideNavMenuItem href="/validation?type=testDate">
                            Search By Test Date
                          </SideNavMenuItem>
                        </SideNavMenu>

                        <SideNavMenu aria-label="Reports" title="Reports">
                          <SideNavMenuItem href="/RoutineReports">
                            Routine
                          </SideNavMenuItem>
                          <SideNavMenuItem href="/StudyReports">
                            Study
                          </SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenuItem href="/admin">Admin</SideNavMenuItem>

                        <SideNavMenuItem
                          target="_blank"
                          href={"http://ozone.uwdigi.org:8069/"}
                        >
                          <FormattedMessage id="admin.billing" />
                        </SideNavMenuItem>
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

export default withRouter(injectIntl(OEHeader));
