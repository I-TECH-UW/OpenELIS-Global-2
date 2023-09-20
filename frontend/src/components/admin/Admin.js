import React from "react";
import config from "../../config.json";
import { FormattedMessage, useIntl, injectIntl } from "react-intl";
import "../Style.css";
import ReflexTestManagement from "./reflexTests/ReflexTestManagement";
import ProgramManagement from "./program/ProgramManagement";
import PathRoute from "../utils/PathRoute";
import CalculatedValue from "./calculatedValue/CalculatedValueForm";
import LocationManagement from "./location/LocationManagement";
import {
  SideNav,
  SideNavItems,
  SideNavMenu,
  SideNavMenuItem,
} from "@carbon/react";

function Admin() {
  const intl = useIntl();
  return (
    <>
      <SideNav aria-label="Side navigation" expanded={true}>
        <SideNavItems className="adminSideNav">
          <SideNavMenu
            title={intl.formatMessage({ id: "sidenav.label.admin.testmgt" })}
          >
            <SideNavMenuItem href="#reflex">
              <FormattedMessage id="sidenav.label.admin.testmgt.reflex" />
            </SideNavMenuItem>
            <SideNavMenuItem href="#calculatedValue">
              <FormattedMessage id="sidenav.label.admin.testmgt.calculated" />
            </SideNavMenuItem>
          </SideNavMenu>
          {/* <SideNavMenu
            title={intl.formatMessage({ id: "sidenav.label.admin.usermgt" })}
          >
            <SideNavMenuItem
              href={config.serverBaseUrl + "/UnifiedSystemUserMenu"}
            >
              <FormattedMessage id="sidenav.label.admin.usermgt" />
            </SideNavMenuItem>
          </SideNavMenu> */}
          {/* <SideNavMenu
            title={intl.formatMessage({
              id: "sidenav.label.admin.organizationmgt",
            })}
          >
            <SideNavMenuItem href={config.serverBaseUrl + "/OrganizationMenu"}>
              <FormattedMessage id="sidenav.label.admin.organizationmgt" />
            </SideNavMenuItem>
          </SideNavMenu> */}
          <SideNavMenuItem href="#program">
            <FormattedMessage id="sidenav.label.admin.program" />
          </SideNavMenuItem>
          <SideNavMenuItem
            target="_blank"
            href={config.serverBaseUrl + "/MasterListsPage"}
          >
            <FormattedMessage id="admin.legacy" />
          </SideNavMenuItem>
        </SideNavItems>
      </SideNav>

      <PathRoute path="#reflex">
        <ReflexTestManagement />
      </PathRoute>
      <PathRoute path="#program">
        <ProgramManagement />
      </PathRoute>
      <PathRoute path="#calculatedValue">
        <CalculatedValue />
      </PathRoute>
      <PathRoute path="#locationManagement">
        <LocationManagement />
      </PathRoute>
    </>
  );
}

export default injectIntl(Admin);
