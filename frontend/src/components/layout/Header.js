import React from "react";
import { FormattedMessage, injectIntl } from "react-intl";
import { withRouter } from "react-router-dom";
import "../Style.css";
import { Select, SelectItem } from "@carbon/react";
import config from "../../config.json";
import { getFromOpenElisServer } from '../utils/Utils';
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

function FormattedMessageBy(props) {
  return null;
}

class OEHeader extends React.Component {
  constructor(props) {
    super(props);
    this.userSwitchRef = React.createRef();
    this.headerPanelRef = React.createRef();
    this.state = {
      switchCollapsed: true,
      header : {}
    };
  }
  _isMounted = false;

  loadHeader = (header) => {
    if (this._isMounted) {
        this.setState({ header: header });
    }
}

  componentDidMount() {
    this._isMounted = true;
    getFromOpenElisServer("/header", this.loadHeader);

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
          <img className="logo" src={`../images/openelis_logo.png`} alt="logo" />
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
                    <div className="banner">
                      <h5>{this.state.header.title}</h5>
                      <p> <FormattedMessage id="header.label.version"/> &nbsp; {this.state.header.version}</p>
                    </div>
                  </HeaderName>
                  {this.props.isLoggedIn() && true && (
                    <>

                      {/* <HeaderMenuItem target="_blank" href={config.serverBaseUrl + "/MasterListsPage"}><FormattedMessage id="admin.billing"/></HeaderMenuItem> */}

                    </>
                  )}
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
                            <FormattedMessage id="header.label.logout"/>
                          </li>
                        </>
                      )}
                      <li className="userDetails">
                        <Select
                          id="selector"
                          name="selectLocale"
                          className="selectLocale"
                          invalidText="A valid locale value is required"
                          labelText={<FormattedMessage id="header.label.selectlocale" />}
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
                          <SideNavMenu aria-label="Order" title={<FormattedMessage id="sidenav.label.order"/>}>
                            <SideNavMenuItem href="/AddOrder"><FormattedMessage id="sidenav.label.addorder"/></SideNavMenuItem>
                            <SideNavMenuItem href={config.serverBaseUrl + "/SampleEdit?type=readwrite"}><FormattedMessage id="sidenav.label.editorder"/></SideNavMenuItem>
                            <SideNavMenuItem href={config.serverBaseUrl + "/ElectronicOrders"}><FormattedMessage id="sidenav.label.incomingorder"/></SideNavMenuItem>
                            <SideNavMenuItem href={config.serverBaseUrl + "/SampleBatchEntrySetup"}><FormattedMessage id="sidenav.label.batchorder"/></SideNavMenuItem>
                            <SideNavMenuItem href={config.serverBaseUrl + "/PrintBarcode"}><FormattedMessage id="sidenav.label.barcode"/></SideNavMenuItem>
                          </SideNavMenu>
                          <SideNavMenu aria-label="Patient" title={<FormattedMessage id="sidenav.label.patient"/>}>
                            <SideNavMenuItem href="/PatientManagement"><FormattedMessage id="sidenav.label.editpatient"/></SideNavMenuItem>
                            <SideNavMenuItem href="/PatientHistory"><FormattedMessage id="sidenav.label.patientHistory"/></SideNavMenuItem>
                          </SideNavMenu>
                          <SideNavMenu
                            aria-label="Non-Conforming Events"
                            title={<FormattedMessage id="sidenav.label.nonConform"/>}
                          >
                            <SideNavMenuItem href={config.serverBaseUrl + "/ReportNonConformingEvent"}> <FormattedMessage id="sidenav.label.nonConform.report"/> </SideNavMenuItem>
                            <SideNavMenuItem href={config.serverBaseUrl + "/ViewNonConformingEvent"}> <FormattedMessage id="sidenav.label.nonConform.view"/></SideNavMenuItem>
                            <SideNavMenuItem href={config.serverBaseUrl + "/NCECorrectiveAction"}> <FormattedMessage id="sidenav.label.nonConform.actions"/> </SideNavMenuItem>
                          </SideNavMenu>
                          <SideNavMenu aria-label="Workplan" title={<FormattedMessage id="sidenav.label.workplan"/>}>
                            <SideNavMenuItem href={"/WorkplanByTest"}> <FormattedMessage id="sidenav.label.workplan.test"/> </SideNavMenuItem>
                            <SideNavMenuItem href={ "/WorkPlanByPanel"}> <FormattedMessage id="sidenav.label.workplan.panel"/> </SideNavMenuItem>
                            <SideNavMenuItem href={ "/WorkPlanByTestSection"}> <FormattedMessage id="sidenav.label.workplan.unit"/> </SideNavMenuItem>
                            <SideNavMenuItem href={ "/WorkplanByPriority"}> <FormattedMessage id="sidenav.label.workplan.priority"/> </SideNavMenuItem>
                          </SideNavMenu>
                          <SideNavMenu
                            aria-label="Pathology" title={<FormattedMessage id="sidenav.label.pathology"/>}                   >
                            <SideNavMenuItem href={"/PathologyDashboard"}> <FormattedMessage id="sidenav.label.pathology.dashboard"/> </SideNavMenuItem>
                          </SideNavMenu>
                          <SideNavMenu
                            aria-label="Immunohistochemistry" title={<FormattedMessage id="sidenav.label.immunochem"/>}                   >
                            <SideNavMenuItem href={"/ImmunohistochemistryDashboard"}> <FormattedMessage id="sidenav.label.pathology.dashboard"/> </SideNavMenuItem>
                          </SideNavMenu>
                          <SideNavMenu
                            aria-label="Cytology" title={<FormattedMessage id="sidenav.label.cytology"/>}                    >
                            <SideNavMenuItem href={"/CytologyDashboard"}> <FormattedMessage id="sidenav.label.pathology.dashboard"/> </SideNavMenuItem>
                          </SideNavMenu>
                          <SideNavMenu aria-label="Results" title={<FormattedMessage id="sidenav.label.results"/>}  >
                            <SideNavMenuItem href="/result?type=unit&doRange=false"> <FormattedMessage id="sidenav.label.results.unit"/> </SideNavMenuItem>
                            <SideNavMenuItem href="/result?type=patient&doRange=false"> <FormattedMessage id="sidenav.label.results.patient"/> </SideNavMenuItem>
                            <SideNavMenuItem href="/result?type=order&doRange=false"> <FormattedMessage id="sidenav.label.results.order"/> </SideNavMenuItem>
                            <SideNavMenuItem href="/result?type=range&doRange=true"> <FormattedMessage id="sidenav.label.results.byrange"/></SideNavMenuItem>
                            <SideNavMenuItem href="/result?type=date&doRange=false"> <FormattedMessage id="sidenav.label.results.testdate"/> </SideNavMenuItem>
                          </SideNavMenu>
                            <SideNavMenu
                            aria-label="Validation" title={<FormattedMessage id="sidenav.label.validation"/>}                    >
                            <SideNavMenuItem href="/validation?type=routine">  <FormattedMessage id="sidenav.label.validation.routine"/> </SideNavMenuItem>
                            <SideNavMenuItem href="/validationStudy"> <FormattedMessage id="sidenav.label.validation.study"/> </SideNavMenuItem>
                            <SideNavMenuItem href="/validation?type=order"> <FormattedMessage id="sidenav.label.validation.order"/> </SideNavMenuItem>
                            <SideNavMenuItem href="/validation?type=testDate"> <FormattedMessage id="sidenav.label.validation.testdate"/> </SideNavMenuItem>
                          </SideNavMenu>

                          <SideNavMenu aria-label="Reports" title={<FormattedMessage id="sidenav.label.reports"/>}  >
                            <SideNavMenuItem href="/RoutineReports"> <FormattedMessage id="sidenav.label.reports.routine"/> </SideNavMenuItem>
                            <SideNavMenuItem href="/StudyReports"> <FormattedMessage id="sidenav.label.reports.study"/> </SideNavMenuItem>
                          </SideNavMenu>
                          <SideNavMenuItem href="/admin"> <FormattedMessage id="sidenav.label.admin"/> </SideNavMenuItem>

                          <SideNavMenuItem
                            target="_blank"
                            href={"http://ozone.uwdigi.org:8069/"}><FormattedMessage
                              id="admin.billing" />
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
}
export default withRouter(injectIntl(OEHeader));
