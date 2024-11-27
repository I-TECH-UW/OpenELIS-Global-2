import React, { useContext, useState, createRef, useEffect } from "react";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import { withRouter } from "react-router-dom";
import { ConfigurationContext } from "../layout/Layout";
import UserSessionDetailsContext from "../../UserSessionDetailsContext";
import "../Style.css";
import { Select, SelectItem } from "@carbon/react";
import config from "../../config.json";
import {
  Search,
  Notification,
  Language,
  UserAvatarFilledAlt,
  Logout,
  Close,
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
import { getFromOpenElisServer } from "../utils/Utils";

function OEHeader(props) {
  const { configurationProperties } = useContext(ConfigurationContext);
  const { userSessionDetails, logout } = useContext(UserSessionDetailsContext);

  const userSwitchRef = createRef();
  const headerPanelRef = createRef();
  const intl = useIntl();

  const [switchCollapsed, setSwitchCollapsed] = useState(true);
  const [menus, setMenus] = useState({
    menu_billing: { menu: {}, childMenus: [] },
    menu_nonconformity: { menu: {}, childMenus: [] },
  });

  const handleMenuItems = (tag, res) => {
    if (res) {
      let newMenus = menus;
      newMenus[tag] = res;
      setMenus(newMenus);
    }
  };

  useEffect(() => {
    getFromOpenElisServer("/rest/menu/menu_billing", (res) => {
      handleMenuItems("menu_billing", res);
    });
    getFromOpenElisServer("/rest/menu/menu_nonconformity", (res) => {
      handleMenuItems("menu_nonconformity", res);
    });
    getFromOpenElisServer("/rest/menu/menu_patient", (res) => {
      handleMenuItems("menu_patient", res);
    });
  }, []);

  const panelSwitchLabel = () => {
    return userSessionDetails.authenticated ? "User" : "Language";
  };

  const clickPanelSwitch = () => {
    setSwitchCollapsed(!switchCollapsed);
  };

  const panelSwitchIcon = () => {
    return userSessionDetails.authenticated ? (
      switchCollapsed ? (
        <UserAvatarFilledAlt size={30} />
      ) : (
        <Close size={20} />
      )
    ) : switchCollapsed ? (
      <Language size={30} className="outer-icon-fill" />
    ) : (
      <Close size={20} />
    );
  };

  const logo = () => {
    return (
      <>
        <picture>
          <img
            className="header-logo"
            src={`../images/kapsiki-lab-logo.svg`}
            alt="logo"
          />
        </picture>
      </>
    );
  };

  return (
    <>
      {/* TODO make this generate from Menu table like it did before */}
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
                  {/*
                  <div className="banner">
                    <h5>{configurationProperties?.BANNER_TEXT}</h5>
                    <p>
                      <FormattedMessage id="header.label.version" /> &nbsp;{" "}
                      {configurationProperties?.releaseNumber}
                    </p>
                  </div>
                */}
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
                        <SelectItem text="Français" value="fr" />
                      </Select>
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
                          <SideNavMenuItem href="/FindOrder">
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
                        {menus?.menu_patient?.menu.isActive && (
                          <SideNavMenu
                            aria-label="Patient"
                            title={intl.formatMessage({
                              id: menus?.menu_patient?.menu.displayKey,
                            })}
                          >
                            {menus?.menu_patient?.childMenus.map(
                              (childMenuItem, index) => {
                                return !childMenuItem?.menu.isActive ? (
                                  <React.Fragment
                                    key={"patient_" + index}
                                  ></React.Fragment>
                                ) : childMenuItem?.childMenus.length > 0 ? (
                                  <React.Fragment key={"patient_" + index}>
                                    <SideNavMenuItem>
                                      <FormattedMessage
                                        id={childMenuItem?.menu.displayKey}
                                      />
                                    </SideNavMenuItem>
                                    {childMenuItem?.childMenus.map(
                                      (childMenuItem2, index2) => {
                                        return (
                                          <React.Fragment
                                            key={
                                              "patient_" + index + "_" + index2
                                            }
                                          >
                                            {childMenuItem2?.menu.isActive && (
                                              <SideNavMenuItem
                                                href={
                                                  childMenuItem2?.menu.actionURL
                                                }
                                                target={
                                                  childMenuItem2?.menu
                                                    .openInNewWindow
                                                    ? "_blank"
                                                    : ""
                                                }
                                                key={index + "_" + index2}
                                              >
                                                -&nbsp;&nbsp;&nbsp;
                                                <FormattedMessage
                                                  id={
                                                    childMenuItem2?.menu
                                                      .displayKey
                                                  }
                                                />
                                              </SideNavMenuItem>
                                            )}
                                          </React.Fragment>
                                        );
                                      },
                                    )}
                                  </React.Fragment>
                                ) : (
                                  <SideNavMenuItem
                                    href={childMenuItem?.menu.actionURL}
                                    target={
                                      childMenuItem?.menu.openInNewWindow
                                        ? "_blank"
                                        : ""
                                    }
                                    key={index}
                                  >
                                    <FormattedMessage
                                      id={childMenuItem?.menu.displayKey}
                                    />
                                  </SideNavMenuItem>
                                );
                              },
                            )}
                            {/* <SideNavMenuItem href="/PatientManagement">
                              <FormattedMessage id="sidenav.label.editpatient" />
                            </SideNavMenuItem>
                            <SideNavMenuItem href="/PatientHistory">
                              <FormattedMessage id="sidenav.label.patientHistory" />
                            </SideNavMenuItem> */}
                          </SideNavMenu>
                        )}
                        {menus?.menu_nonconformity?.menu.isActive && (
                          <SideNavMenu
                            aria-label="Non-Conforming Events"
                            title={intl.formatMessage({
                              id: menus?.menu_nonconformity?.menu.displayKey,
                            })}
                          >
                            {menus?.menu_nonconformity?.childMenus.map(
                              (childMenuItem, index) => {
                                return !childMenuItem?.menu.isActive ? (
                                  <React.Fragment
                                    key={"nonConform_" + index}
                                  ></React.Fragment>
                                ) : (
                                  <SideNavMenuItem
                                    href={childMenuItem?.menu.actionURL}
                                    target={
                                      childMenuItem?.menu.openInNewWindow
                                        ? "_blank"
                                        : ""
                                    }
                                    key={index}
                                  >
                                    <FormattedMessage
                                      id={childMenuItem?.menu.displayKey}
                                    />
                                  </SideNavMenuItem>
                                );
                              },
                            )}
                          </SideNavMenu>
                        )}
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
                          <SideNavMenuItem href="/result?type=range&doRange=true">
                            <FormattedMessage id="sidenav.label.results.byrange" />
                          </SideNavMenuItem>
                          <SideNavMenuItem href="/result?type=date&doRange=false">
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
                          {configurationProperties?.studyManagementTab ==
                            "true" && (
                            <SideNavMenuItem
                              href={
                                config.serverBaseUrl +
                                "/ResultValidationRetroC?type=Immunology"
                              }
                            >
                              <FormattedMessage id="sidenav.label.validation.study" />
                            </SideNavMenuItem>
                          )}
                          <SideNavMenuItem href="/validation?type=order">
                            <FormattedMessage id="sidenav.label.validation.order" />
                          </SideNavMenuItem>
                          <SideNavMenuItem href="/validation?type=testDate">
                            <FormattedMessage id="sidenav.label.validation.testdate" />
                          </SideNavMenuItem>
                          <SideNavMenuItem href="/validation?type=range">
                            <FormattedMessage id="sidenav.label.results.byrange" />
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

                        {menus?.menu_billing?.menu.isActive && (
                          <SideNavMenuItem
                            target={
                              menus?.menu_billing?.menu.openInNewWindow
                                ? "_blank"
                                : ""
                            }
                            href={menus?.menu_billing?.menu.actionURL}
                          >
                            <FormattedMessage
                              id={menus?.menu_billing?.menu.displayKey}
                            />
                          </SideNavMenuItem>
                        )}
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
