import React, { useContext, useState, useRef, useEffect, useCallback } from "react";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import { withRouter } from "react-router-dom";
import { ConfigurationContext } from "../layout/Layout";
import UserSessionDetailsContext from "../../UserSessionDetailsContext";
import { Select, SelectItem, Button, Search } from "@carbon/react";
import { Notification, Language, UserAvatarFilledAlt, Logout, Close } from "@carbon/icons-react";
import { HeaderContainer, Header, HeaderMenuButton, HeaderName, HeaderGlobalAction, HeaderGlobalBar, SideNavMenu, SideNavMenuItem, SideNav, SideNavItems, Theme, HeaderPanel } from "@carbon/react";
import { getFromOpenElisServer } from "../utils/Utils";
import config from "../../config.json";
import "../Style.css";
import { Modal } from "@carbon/react";

function PatientOverlay({ searchTerm, onClose }) {
  const [patients, setPatients] = useState([]);

  useEffect(() => {
    const fetchPatients = async () => {
      try {
        /// for testing purposes, i will first use dammy patients
        const dummyPatients = [
          { id: 1, name: "Patient 1" },
          { id: 2, name: "Patient 2" },
          { id: 3, name: "Patient 3" },
          { id: 4, name: "Patient 4" },
        ];
        await new Promise((resolve) => setTimeout(resolve, 1000));
        setPatients(dummyPatients);
      } catch (error) {
        console.error("Error fetching patients:", error);
      }
    };

    if (searchTerm !== "") {
      fetchPatients();
    }
  }, [searchTerm]);

  return (
    <Modal
      open={searchTerm !== ""}
      modalHeading="Patients"
      onRequestClose={onClose}
    >
      <ul>
        {patients.map((patient) => (
          <li key={patient.id}>{patient.name}</li>
        ))}
      </ul>
    </Modal>
  );
}

function OEHeader(props) {
  const { configurationProperties } = useContext(ConfigurationContext);
  const { userSessionDetails, logout } = useContext(UserSessionDetailsContext);
  const userSwitchRef = useRef(null);
  const headerPanelRef = useRef(null);
  const intl = useIntl();

  const [switchCollapsed, setSwitchCollapsed] = useState(true);
  const [menus, setMenus] = useState({
    menu: [],
    menu_billing: { menu: {}, childMenus: [] },
    menu_nonconformity: { menu: {}, childMenus: [] },
  });
  const [searchTerm, setSearchTerm] = useState("");
  const [showOverlay, setShowOverlay] = useState(false);

  useEffect(() => {
    getFromOpenElisServer("/rest/menu", handleMenuItems("menu"));
  }, []);

  const handleMenuItems = (tag) => (res) => {
    if (res) {
      setMenus(prevMenus => ({ ...prevMenus, [tag]: res }));
    }
  };

  const toggleSwitchCollapsed = () => {
    setSwitchCollapsed(prevState => !prevState);
  };

  const handleSubmit = (evt) => {
    evt.preventDefault();
    const term = evt.target.searchTerm.value;
    setSearchTerm(term);
    setShowOverlay(true);
    console.log("Search term submitted:", term);
  };

  const handleChange = useCallback((val) => {
    setSearchTerm(val);
  }, []);

  const panelSwitchLabel = () => userSessionDetails.authenticated ? "User" : "Lang";

  const panelSwitchIcon = () => {
    const Icon = userSessionDetails.authenticated ? (switchCollapsed ? UserAvatarFilledAlt : Close) : (switchCollapsed ? Language : Close);
    return <Icon size={20} />;
  };

  const logo = () => (
    <img className="logo" src="../images/openelis_logo.png" alt="logo" />
  );

  const generateMenuItems = (menuItem, index, level) => {
    if (!menuItem.menu.isActive) return null;

    const indent = Array.from({ length: level > 1 ? level - 2 : 0 }, () => "\xA0\xA0\xA0").join("-\xA0\xA0\xA0");

    return (
      <React.Fragment key={`menu_${index}_${level}`}>
        {level === 0 && menuItem.childMenus.length > 0 ? (
          <SideNavMenu
            aria-label={intl.formatMessage({ id: menuItem.menu.displayKey })}
            title={intl.formatMessage({ id: menuItem.menu.displayKey })}
          >
            {menuItem.childMenus.map((childMenuItem, index) =>
              generateMenuItems(childMenuItem, index, level + 1)
            )}
          </SideNavMenu>
        ) : (
          <>
            <SideNavMenuItem
              href={menuItem.menu.actionURL}
              target={menuItem.menu.openInNewWindow ? "_blank" : ""}
            >
              {indent}<FormattedMessage id={menuItem.menu.displayKey} />
            </SideNavMenuItem>
            {menuItem.childMenus.map((childMenuItem, index) =>
              generateMenuItems(childMenuItem, index, level + 1)
            )}
          </>
        )}
      </React.Fragment>
    );
  };

  return (
    <div className="container overlay-container">
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
                  <h5>{configurationProperties?.BANNER_TEXT}</h5>
                  <p>
                    <FormattedMessage id="header.label.version" /> &nbsp;
                    {configurationProperties?.releaseNumber}
                  </p>
                </div>
              </HeaderName>
              {userSessionDetails.authenticated && (
                <HeaderGlobalBar className="cds--header__global">
                  <div className="search-container">
                  <form onSubmit={handleSubmit} className="search">
                  <Search
                    labelText=""
                    placeholder="search for Patient by name"
                    value={searchTerm}
                    onChange={(event) => handleChange(event.target.value)}
                    id="searchTerm"
                  />
                  <Button type="submit" kind="secondary" className="btn-search">
                    Search
                  </Button>
                  {showOverlay && (
                    <div className="patient-overlay">
                      <PatientOverlay
                        searchTerm={searchTerm}
                        onClose={() => setShowOverlay(false)}
                      />
                    </div>
                  )}
                </form>
                  </div>
                  <HeaderGlobalAction
                    aria-label="Notifications"
                    onClick={() => {/*TODO: add notification functionality*/}}
                  >
                    <Notification size={20} />
                  </HeaderGlobalAction>
                  <HeaderGlobalAction
                    aria-label={panelSwitchLabel()}
                    onClick={toggleSwitchCollapsed}
                    ref={userSwitchRef}
                  >
                    {panelSwitchIcon()}
                  </HeaderGlobalAction>
                </HeaderGlobalBar>
              )}
              {userSessionDetails.authenticated && (
                <HeaderPanel
                  aria-label="Header Panel"
                  expanded={!switchCollapsed}
                  className="headerPanel"
                  ref={headerPanelRef}
                >
                  {/* User details and language selector code */}
                </HeaderPanel>
              )}
              {userSessionDetails.authenticated && (
                <SideNav
                  aria-label="Side navigation"
                  expanded={isSideNavExpanded}
                  isPersistent={false}
                >
                  <SideNavItems>
                    {menus["menu"].map((childMenuItem, index) =>
                      childMenuItem.menu.elementId !== "menu_home" &&
                      generateMenuItems(childMenuItem, index, 0)
                    )}
                  </SideNavItems>
                </SideNav>
              )}
            </Header>
          )}
        />
      </Theme>
    </div>
  );
}

export default withRouter(injectIntl(OEHeader));
