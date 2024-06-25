import React, {
  useContext,
  useState,
  createRef,
  useRef,
  useEffect,
  useLayoutEffect,
} from "react";
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
import SearchBar from "./search/searchBar";

function OEHeader(props) {
  const { configurationProperties } = useContext(ConfigurationContext);
  const { userSessionDetails, logout } = useContext(UserSessionDetailsContext);

  const userSwitchRef = createRef();
  const headerPanelRef = createRef();
  const scrollRef = useRef(window.scrollY);

  const intl = useIntl();

  const [switchCollapsed, setSwitchCollapsed] = useState(true);
  const [menus, setMenus] = useState({
    menu: [{ menu: {}, childMenus: [] }],
    menu_billing: { menu: {}, childMenus: [] },
    menu_nonconformity: { menu: {}, childMenus: [] },
  });
  const [searchBar, setSearchBar] = useState(false);
  scrollRef.current = window.scrollY;
  useLayoutEffect(() => {
    window.scrollTo(0, scrollRef.current);
  }, []);

  useEffect(() => {
    getFromOpenElisServer("/rest/menu", (res) => {
      handleMenuItems("menu", res);
    });
  }, []);

  const panelSwitchLabel = () => {
    return userSessionDetails.authenticated ? "User" : "Lang";
  };

  const handleMenuItems = (tag, res) => {
    if (res) {
      let newMenus = menus;
      newMenus[tag] = res;
      setMenus(newMenus);
    }
  };

  const clickPanelSwitch = () => {
    setSwitchCollapsed(!switchCollapsed);
  };
  const handleSearch = () => {
    setSearchBar(!searchBar);
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
            <span
              onClick={(e) => {
                setMenuItemExpanded(e, menuItem, path);
              }}
            >
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
            </span>
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
                  !hasActiveChildMenu(menuItem) &&
                  console.warn("menu entry has no action url and no child")}
                {!hasActiveChildMenu(menuItem) &&
                  renderSingleNavButton(menuItem, index, level, path)}
                {!menuItem.menu.actionURL &&
                  hasActiveChildMenu(menuItem) &&
                  renderSingleDropdownButton(menuItem, index, level, path)}
                {menuItem.menu.actionURL &&
                  hasActiveChildMenu(menuItem) &&
                  renderDualNavDropdownButton(menuItem, index, level, path)}
              </span>
            </SideNavMenuItem>
            {menuItem.childMenus.map((childMenuItem, index) => {
              return (
                <span
                  key={path + ".childMenus[" + index + "].span"}
                  style={{ display: menuItem.expanded ? "" : "none" }}
                >
                  {generateMenuItems(
                    childMenuItem,
                    index,
                    level + 1,
                    path + ".childMenus[" + index + "]",
                  )}
                </span>
              );
            })}
          </React.Fragment>
        );
      }
    } else {
      return <React.Fragment key={path}></React.Fragment>;
    }
  };

  const hasActiveChildMenu = (menuItem) => {
    if (menuItem.menu.elementId === "menu_reports_routine") {
      console.log("reports");
    }
    return (
      menuItem.childMenus.length >= 1 &&
      menuItem.childMenus.some((element) => {
        return element.menu.isActive;
      })
    );
  };

  const renderSingleNavButton = (menuItem, index, level, path) => {
    const marginValue = (level - 1) * 0.5 + "rem";
    return (
      <button
        className={"custom-sidenav-button"}
        style={{ marginLeft: marginValue }}
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
        style={{ marginLeft: marginValue }}
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
          style={{ marginLeft: marginValue }}
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
      <span style={{ fontSize: fontPercent }}>
        <FormattedMessage id={menuItem.menu.displayKey} />
      </span>
    );
  };

  const onClickSideNavItem = (e, menuItem, path) => {
    e.preventDefault();
    e.stopPropagation();
    setMenuItemExpanded(e, menuItem, path);
  };

  const setMenuItemExpanded = (e, menuItem, path) => {
    const newMenus = { ...menus };
    const newMenuItem = { ...menuItem };
    newMenuItem.expanded = !newMenuItem.expanded;
    var jp = require("jsonpath");
    jp.value(newMenus, path, newMenuItem);
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
                <HeaderName href="/" prefix="" style={{ padding: "0px" }}>
                  <span id="header-logo">{logo()}</span>
                  <div className="banner">
                    <h5>{configurationProperties?.BANNER_TEXT}</h5>
                    <p>
                      <FormattedMessage id="header.label.version" /> &nbsp;{" "}
                      {configurationProperties?.releaseNumber}
                    </p>
                  </div>
                </HeaderName>
                <HeaderGlobalBar>
                  {userSessionDetails.authenticated && (
                    <>
                      {searchBar && <SearchBar />}

                      <HeaderGlobalAction
                        aria-label="Search"
                        onClick={handleSearch}
                      >
                        {!searchBar ? (
                          <Search size={20} />
                        ) : (
                          <Close size={20} />
                        )}
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
                          <UserAvatarFilledAlt
                            size={18}
                            style={{ marginRight: "4px" }}
                          />
                          {userSessionDetails.firstName}{" "}
                          {userSessionDetails.lastName}
                        </li>
                        <li
                          className="userDetails clickableUserDetails"
                          onClick={logout}
                        >
                          <Logout
                            id="sign-out"
                            style={{ marginRight: "3px" }}
                          />
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
