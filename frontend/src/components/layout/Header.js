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

  const generateMenuItems = (menuItem, index, level) => {
    if (menuItem.menu.isActive) {
      if (level === 0 && menuItem.childMenus.length > 0) {
        return (
          <SideNavMenu
            aria-label={intl.formatMessage({
              id: menuItem.menu.displayKey,
            })}
            title={intl.formatMessage({
              id: menuItem.menu.displayKey,
            })}
            key={"menu_" + index + "_" + level}
          >
            {menuItem.childMenus.map((childMenuItem, index) => {
              return generateMenuItems(childMenuItem, index, level + 1);
            })}
          </SideNavMenu>
        );
      } else {
        return (
          <React.Fragment key={"menu_" + index + "_" + level}>
            <SideNavMenuItem
              href={menuItem.menu.actionURL}
              target={menuItem.menu.openInNewWindow ? "_blank" : ""}
            >
              {level > 1 &&
                "\xA0\xA0\xA0".repeat(level - 2 < 0 ? 0 : level - 2) +
                  "-\xA0\xA0\xA0"}
              <FormattedMessage id={menuItem.menu.displayKey} />
            </SideNavMenuItem>
            {menuItem.childMenus.map((childMenuItem, index) => {
              return generateMenuItems(childMenuItem, index, level + 1);
            })}
          </React.Fragment>
        );
      }
    } else {
      return (
        <React.Fragment key={"menu_" + index + "_" + level}></React.Fragment>
      );
    }
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
                            return generateMenuItems(childMenuItem, index, 0);
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
