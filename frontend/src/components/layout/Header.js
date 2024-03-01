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
  ChevronDown,
  ChevronUp,
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
    menu: [{ menu: {}, childMenus: [] }],
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
    getFromOpenElisServer("/rest/menu", (res) => {
      handleMenuItems("menu", res);
    });
  }, []);

  const panelSwitchLabel = () => {
    return userSessionDetails.authenticated ? "User" : "Lang";
  };

  const clickPanelSwitch = () => {
    setSwitchCollapsed(!switchCollapsed);
  };

  const panelSwitchIcon = () => {
    return userSessionDetails.authenticated ? (
      switchCollapsed ? (
        <UserAvatarFilledAlt size={20} />
      ) : (
        <Close size={20} />
      )
    ) : switchCollapsed ? (
      <Language size={20} />
    ) : (
      <Close size={20} />
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

  const generateMenuItems = (menuItem, index, level, path) => {
    if (menuItem.menu.isActive) {
      if (level === 0 && menuItem.childMenus.length > 0) {
        return (
          <React.Fragment key={path}>
            <SideNavMenu
              aria-label={intl.formatMessage({
                id: menuItem.menu.displayKey,
              })}
              title={intl.formatMessage({
                id: menuItem.menu.displayKey,
              })}
              key={"menu_" + index + "_" + level}
              defaultExpanded={menuItem.expanded}
            >
              {menuItem.childMenus.map((childMenuItem, index) => {
                return generateMenuItems(
                  childMenuItem,
                  index,
                  level + 1,
                  path + ".childMenus[" + index + "]",
                );
              })}
            </SideNavMenu>
          </React.Fragment>
        );
      } else if (level === 0) {
        return (
          <React.Fragment key={path}>
            <SideNavMenuItem
              href={menuItem.menu.actionURL}
              target={menuItem.menu.openInNewWindow ? "_blank" : ""}
            >
              {renderSideNavMenuItemLabel(menuItem, level)}
            </SideNavMenuItem>
          </React.Fragment>
        );
      } else {
        return (
          <React.Fragment key={path}>
            <SideNavMenuItem className="reduced-padding-nav-menu-item">
              <span style={{ display: "flex", width: "100%" }}>
                {!menuItem.menu.actionURL &&
                  menuItem.childMenus.length < 1 &&
                  console.warn("menu entry has no action url and no child")}
                {menuItem.childMenus.length < 1 &&
                  renderSingleNavButton(menuItem, index, level, path)}
                {!menuItem.menu.actionURL &&
                  menuItem.childMenus.length >= 1 &&
                  renderSingleDropdownButton(menuItem, index, level, path)}
                {menuItem.menu.actionURL &&
                  menuItem.childMenus.length >= 1 &&
                  renderDualNavDropdownButton(menuItem, index, level, path)}
              </span>
            </SideNavMenuItem>
            {menuItem.expanded &&
              menuItem.childMenus.map((childMenuItem, index) => {
                return generateMenuItems(
                  childMenuItem,
                  index,
                  level + 1,
                  path + ".childMenus[" + index + "]",
                );
              })}
          </React.Fragment>
        );
      }
    } else {
      return <React.Fragment key={path}></React.Fragment>;
    }
  };

  const renderSingleNavButton = (menuItem, index, level, path) => {
    const marginValue = (level - 1) * 0.5 + "rem";
    return (
      <button
        className={"custom-sidenav-button"}
        style={{ "margin-left": marginValue }}
        onClick={() => {
          if (menuItem.menu.openInNewWindow) {
            window.open(menuItem.menu.actionURL);
          } else {
            window.location.href = menuItem.menu.actionURL;
          }
        }}
      >
        {renderSideNavMenuItemLabel(menuItem, level)}
      </button>
    );
  };

  const renderSingleDropdownButton = (menuItem, index, level, path) => {
    const marginValue = (level - 1) * 0.5 + "rem";
    return (
      <button
        className={"custom-sidenav-button"}
        style={{ "margin-left": marginValue }}
        onClick={(e) => {
          onClickSideNavItem(e, menuItem, path);
        }}
      >
        {renderSideNavMenuItemLabel(menuItem, level)}
        {renderSideNavChevron(menuItem)}
      </button>
    );
  };

  const renderDualNavDropdownButton = (menuItem, index, level, path) => {
    const marginValue = (level - 1) * 0.5 + "rem";
    return (
      <>
        <button
          className={
            menuItem.menu.actionURL
              ? "custom-sidenav-button"
              : "custom-sidenav-button-unclickable"
          }
          style={{ "margin-left": marginValue }}
          onClick={() => {
            if (menuItem.menu.openInNewWindow) {
              window.open(menuItem.menu.actionURL);
            } else {
              window.location.href = menuItem.menu.actionURL;
            }
          }}
        >
          {renderSideNavMenuItemLabel(menuItem, level)}
        </button>
        {menuItem.childMenus.length > 0 && (
          <button
            className="custom-sidenav-button"
            onClick={(e) => {
              onClickSideNavItem(e, menuItem, path);
            }}
          >
            {renderSideNavChevron(menuItem)}
          </button>
        )}
      </>
    );
  };

  const renderSideNavChevron = (menuItem) => {
    return (
      <>
        {menuItem.expanded && (
          <div className="cds--side-nav__icon cds--side-nav__icon--small cds--side-nav__submenu-chevron">
            <ChevronUp />
          </div>
        )}
        {!menuItem.expanded && (
          <div className="cds--side-nav__icon cds--side-nav__icon--small cds--side-nav__submenu-chevron">
            <ChevronDown />
          </div>
        )}
      </>
    );
  };

  const renderSideNavMenuItemLabel = (menuItem, level) => {
    const fontPercent = 100 - 5 * (level - 1) + "%";
    return (
      <span style={{ "font-size": fontPercent }}>
        <FormattedMessage id={menuItem.menu.displayKey} />
      </span>
    );
  };

  const onClickSideNavItem = (e, menuItem, path) => {
    e.preventDefault();
    setMenuItemExpanded(e, menuItem, path);
  };

  const setMenuItemExpanded = (e, menuItem, path) => {
    const newMenus = { ...menus };
    const newMenuItem = { ...menuItem };
    newMenuItem.expanded = !newMenuItem.expanded;
    var jp = require("jsonpath");
    jp.value(newMenus, path, newMenuItem);
    // fixes bug where top level parent closes when no children are expanded
    const parentPath = path.substring(0, path.lastIndexOf("."));
    jp.value(newMenus, parentPath + ".expanded", true);
    setMenus(newMenus);
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
                  <div className="banner">
                    <h5>{configurationProperties?.BANNER_TEXT}</h5>
                    <p>
                      <FormattedMessage id="header.label.version" /> &nbsp;{" "}
                      {configurationProperties?.releaseNumber}
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
                      <label className="cds--label">
                        {" "}
                        <FormattedMessage id="header.label.version" />:{" "}
                        {configurationProperties?.releaseNumber}
                      </label>
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
                        {menus["menu"].map((childMenuItem, index) => {
                          // ignore the Home Menu in the new UI
                          if (childMenuItem.menu.elementId != "menu_home") {
                            return generateMenuItems(
                              childMenuItem,
                              index,
                              0,
                              "$.menu[" + index + "]",
                            );
                          }
                        })}
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
