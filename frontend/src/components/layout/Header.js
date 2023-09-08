import React, { useContext, useState, createRef, useEffect } from "react";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
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
  Close 
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
  const {BANNER_TEXT} = useContext(ConfigurationContext);
  const { userSessionDetails, logout } = useContext(UserSessionDetailsContext);
  const [switchCollapsed, setSwitchCollapsed] = useState(true);
  const userSwitchRef = createRef();
  const headerPanelRef = createRef();
  const componentMounted = createRef(false);
  const intl = useIntl();

  const panelSwitchLabel = () => {
    return userSessionDetails.authenticated ? "User" : "Lang";
  };

  const clickPanelSwitch = () => {
    setSwitchCollapsed(!switchCollapsed);
  };

  const panelSwitchIcon = () => {
    return userSessionDetails.authenticated ? (
       switchCollapsed?<UserAvatarFilledAlt size={20} />:<Close size={20}/>
    ) : (
      switchCollapsed? <Language size={20} />:<Close size={20}/>
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
            render={({ isSideNavExpanded, onClickSideNavExpand}) => (
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
                    <h5>{BANNER_TEXT}</h5>
                    <p>
                      {" "}
                      <FormattedMessage id="header.label.version" /> &nbsp;{" "}
                      {releaseNumber}
                    </p>
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
                          <FormattedMessage id="header.label.logout" />
                        </li>
                      </>
                    )}
                    <li className="userDetails">
                      <Select
                        id="selector"
                        name="selectLocale"
                        className="selectLocale"
                        invalidText="A valid locale value is required"
                        labelText={
                          <FormattedMessage id="header.label.selectlocale" />
                        }
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
                      <label className="cds--label"> <FormattedMessage id="header.label.version" />: {releaseNumber}</label>
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
                        <SideNavMenu
                          aria-label="Order"
                          title={intl.formatMessage({
                            id: "sidenav.label.order",
                          })}
                        >
                          <SideNavMenuItem href="/AddOrder">
                            <FormattedMessage id="sidenav.label.addorder" />
                          </SideNavMenuItem>
                          <SideNavMenuItem
                            href={
                              config.serverBaseUrl +
                              "/SampleEdit?type=readwrite"
                            }
                          >
                            <FormattedMessage id="sidenav.label.editorder" />
                          </SideNavMenuItem>
                          <SideNavMenuItem
                            href={config.serverBaseUrl + "/ElectronicOrders"}
                          >
                            <FormattedMessage id="sidenav.label.incomingorder" />
                          </SideNavMenuItem>
                          <SideNavMenuItem
                            href={
                              config.serverBaseUrl + "/SampleBatchEntrySetup"
                            }
                          >
                            <FormattedMessage id="sidenav.label.batchorder" />
                          </SideNavMenuItem>
                          <SideNavMenuItem
                            href={config.serverBaseUrl + "/PrintBarcode"}
                          >
                            <FormattedMessage id="sidenav.label.barcode" />
                          </SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu aria-label="Patient" title="Patient">
                          <SideNavMenuItem href="/PatientManagement">
                            <FormattedMessage id="sidenav.label.editpatient" />
                          </SideNavMenuItem>
                          <SideNavMenuItem href="/PatientHistory">
                            <FormattedMessage id="sidenav.label.patientHistory" />
                          </SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu
                          aria-label="Non-Conforming Events"
                          title={intl.formatMessage({
                            id: "sidenav.label.nonConform",
                          })}
                        >
                          <SideNavMenuItem
                            href={
                              config.serverBaseUrl + "/ReportNonConformingEvent"
                            }
                          >
                            <FormattedMessage id="sidenav.label.nonConform.report" />
                          </SideNavMenuItem>
                          <SideNavMenuItem
                            href={
                              config.serverBaseUrl + "/ViewNonConformingEvent"
                            }
                          >
                            <FormattedMessage id="sidenav.label.nonConform.view" />
                          </SideNavMenuItem>
                          <SideNavMenuItem
                            href={config.serverBaseUrl + "/NCECorrectiveAction"}
                          >
                            <FormattedMessage id="sidenav.label.nonConform.actions" />
                          </SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu aria-label="Workplan" title="Workplan">
                          <SideNavMenuItem href={"/WorkplanByTest"}>
                            <FormattedMessage id="sidenav.label.workplan.test" />
                          </SideNavMenuItem>
                          <SideNavMenuItem href={"/WorkPlanByPanel"}>
                            <FormattedMessage id="sidenav.label.workplan.panel" />
                          </SideNavMenuItem>
                          <SideNavMenuItem href={"/WorkPlanByTestSection"}>
                            <FormattedMessage id="sidenav.label.workplan.unit" />
                          </SideNavMenuItem>
                          <SideNavMenuItem href={"/WorkplanByPriority"}>
                            <FormattedMessage id="sidenav.label.workplan.priority" />
                          </SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu aria-label="Pathology" title="Pathology">
                          <SideNavMenuItem href={"/PathologyDashboard"}>
                            <FormattedMessage id="sidenav.label.pathology.dashboard" />
                          </SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu
                          aria-label="Immunohistochemistry"
                          title={intl.formatMessage({
                            id: "sidenav.label.immunochem",
                          })}
                        >
                          <SideNavMenuItem
                            href={"/ImmunohistochemistryDashboard"}
                          >
                            <FormattedMessage id="sidenav.label.pathology.dashboard" />
                          </SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu
                          aria-label="Cytology"
                          title={intl.formatMessage({
                            id: "sidenav.label.cytology",
                          })}
                        >
                          <SideNavMenuItem href={"/CytologyDashboard"}>
                            <FormattedMessage id="sidenav.label.pathology.dashboard" />
                          </SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu aria-label="Results" title="Results">
                          <SideNavMenuItem href="/result?type=unit&doRange=false">
                            <FormattedMessage id="sidenav.label.results.unit" />
                          </SideNavMenuItem>
                          <SideNavMenuItem href="/result?type=patient&doRange=false">
                            <FormattedMessage id="sidenav.label.results.patient" />
                          </SideNavMenuItem>
                          <SideNavMenuItem href="/result?type=order&doRange=false">
                            <FormattedMessage id="sidenav.label.results.order" />
                          </SideNavMenuItem>
                          <SideNavMenuItem href="/result?type=order">
                            <FormattedMessage id="sidenav.label.results.byrange" />
                          </SideNavMenuItem>
                          <SideNavMenuItem href="/result?type=date">
                            <FormattedMessage id="sidenav.label.results.testdate" />
                          </SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu
                          aria-label="Validation"
                          title={intl.formatMessage({
                            id: "sidenav.label.validation",
                          })}
                        >
                          <SideNavMenuItem href="/validation?type=routine">
                            <FormattedMessage id="sidenav.label.validation.routine" />
                          </SideNavMenuItem>
                          <SideNavMenuItem href={config.serverBaseUrl + "/ResultValidationRetroC?type=Immunology"}>
                            <FormattedMessage id="sidenav.label.validation.study" />
                          </SideNavMenuItem>
                          <SideNavMenuItem href="/validation?type=order">
                            <FormattedMessage id="sidenav.label.validation.order" />
                          </SideNavMenuItem>
                          <SideNavMenuItem href="/validation?type=testDate">
                            <FormattedMessage id="sidenav.label.validation.testdate" />
                          </SideNavMenuItem>
                        </SideNavMenu>

                        <SideNavMenu aria-label="Reports" title="Reports">
                          <SideNavMenuItem href="/RoutineReports">
                            <FormattedMessage id="sidenav.label.reports.routine" />
                          </SideNavMenuItem>
                          <SideNavMenuItem href="/StudyReports">
                            <FormattedMessage id="sidenav.label.reports.study" />
                          </SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenuItem href="/admin">
                          <FormattedMessage id="sidenav.label.admin" />
                        </SideNavMenuItem>

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
