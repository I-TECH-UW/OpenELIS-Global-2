import React from "react";
import config from "../../config.json";
import { FormattedMessage, useIntl, injectIntl } from "react-intl";
import "../Style.css";
import ReflexTestManagement from "./reflexTests/ReflexTestManagement";
import ProgramManagement from "./program/ProgramManagement";
import LabNumberManagement from "./labNumber/LabNumberManagement";
import BillingMenuManagement from "./menu/BillingMenuManagement";
import PathRoute from "../utils/PathRoute";
import CalculatedValue from "./calculatedValue/CalculatedValueForm";
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
          <SideNavMenuItem href="#labNumber">
            <FormattedMessage id="sidenav.label.admin.labNumber" />
          </SideNavMenuItem>
          <SideNavMenuItem href="#program">
            <FormattedMessage id="sidenav.label.admin.program" />
          </SideNavMenuItem>
          <SideNavMenu
            title={intl.formatMessage({ id: "sidenav.label.admin.menu" })}
          >
            <SideNavMenuItem href="#billingMenuManagement">
              <FormattedMessage id="sidenav.label.admin.menu.billing" />
            </SideNavMenuItem>
          </SideNavMenu>
          <SideNavMenuItem
            target="_blank"
            href={config.serverBaseUrl + "/MasterListsPage"}
          >
            <FormattedMessage id="admin.legacy" />
          </SideNavMenuItem>
        </SideNavItems>
      </SideNav>

      <PathRoute path="#labNumber">
        <LabNumberManagement />
      </PathRoute>
      <PathRoute path="#reflex">
        <ReflexTestManagement />
      </PathRoute>
      <PathRoute path="#program">
        <ProgramManagement />
      </PathRoute>
      <PathRoute path="#calculatedValue">
        <CalculatedValue />
      </PathRoute>
      <PathRoute path="#billingMenuManagement">
        <BillingMenuManagement />
      </PathRoute>
    </>
  );
}

export default injectIntl(Admin);
