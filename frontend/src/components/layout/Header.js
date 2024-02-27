import React, { useContext, useState, useRef, useEffect } from "react";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import { withRouter } from "react-router-dom";
import { ConfigurationContext } from "../layout/Layout";
import UserSessionDetailsContext from "../../UserSessionDetailsContext";
import { Select, SelectItem } from "@carbon/react";
import { Search, Notification, Language, UserAvatarFilledAlt, Logout, Close } from "@carbon/icons-react";
import { HeaderContainer, Header, HeaderMenuButton, HeaderName, HeaderGlobalAction, HeaderGlobalBar, SideNavMenu, SideNavMenuItem, SideNav, SideNavItems, Theme, HeaderPanel } from "@carbon/react";
import { getFromOpenElisServer } from "../utils/Utils";
import config from "../../config.json";
import "../Style.css";

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
  const [searchVisible, setSearchVisible] = useState(false); // State to manage search input visibility

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

  const toggleSearch = () => {
    setSearchVisible(prevState => !prevState);
    
  };

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
                  <h5>{configurationProperties?.BANNER_TEXT}</h5>
                  <p>
                    <FormattedMessage id="header.label.version" /> &nbsp;
                    {configurationProperties?.releaseNumber}
                  </p>
                </div>
              </HeaderName>
              {userSessionDetails.authenticated && (
                <HeaderGlobalBar>
                  <HeaderGlobalAction
                    aria-label="Search"
                    onClick={toggleSearch}
                  >
                    <Search size={20} />
                  </HeaderGlobalAction>
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
              {searchVisible && (
                <HeaderGlobalBar>
                  <input
                    type="text"
                    placeholder="Search Patients"
                    className="search-input"
                  />
                </HeaderGlobalBar>
              )}
              {userSessionDetails.authenticated && (
                <HeaderPanel
                  aria-label="Header Panel"
                  expanded={!switchCollapsed}
                  className="headerPanel"
                  ref={headerPanelRef}
                >
                  <ul>
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
                    <li className="userDetails">
                      <Select
                        id="selector"
                        name="selectLocale"
                        className="selectLocale"
                        invalidText="A valid locale value is required"
                        labelText={<FormattedMessage id="header.label.selectlocale" />}
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
                      <label className="cds--label">
                        <FormattedMessage id="header.label.version" />:{" "}
                        {configurationProperties?.releaseNumber}
                      </label>
                    </li>
                  </ul>
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