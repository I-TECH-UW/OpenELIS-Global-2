import React from "react";
import config from "../../config.json";
import { FormattedMessage, useIntl, injectIntl } from "react-intl";
import "../Style.css";
import ReflexTestManagement from "./reflexTests/ReflexTestManagement";
import ProgramManagement from "./program/ProgramManagement";
import LabNumberManagement from "./labNumber/LabNumberManagement";
import {
  GlobalMenuManagement,
  BillingMenuManagement,
  NonConformityMenuManagement,
  PatientMenuManagement,
  StudyMenuManagement,
} from "./menu";
import {
  Microscope,
  CharacterWholeNumber,
  TableOfContents,
  ChartBubble,
  Catalog,
  Settings,
} from "@carbon/icons-react";
import PathRoute from "../utils/PathRoute";
import CalculatedValue from "./calculatedValue/CalculatedValueForm";
import {
  SideNav,
  SideNavItems,
  SideNavLink,
  SideNavMenu,
  SideNavMenuItem,
} from "@carbon/react";
import { CommonProperties } from "./menu/CommonProperties";

function Admin() {
  const intl = useIntl();
  return (
    <>
      <SideNav
        aria-label="Side navigation"
        defaultExpanded={true}
        isRail={true}
      >
        <SideNavItems className="adminSideNav">
          <SideNavMenu
            renderIcon={Microscope}
            title={intl.formatMessage({ id: "sidenav.label.admin.testmgt" })}
          >
            <SideNavMenuItem href="#reflex">
              <FormattedMessage id="sidenav.label.admin.testmgt.reflex" />
            </SideNavMenuItem>
            <SideNavMenuItem href="#calculatedValue">
              <FormattedMessage id="sidenav.label.admin.testmgt.calculated" />
            </SideNavMenuItem>
          </SideNavMenu>
          <SideNavLink href="#labNumber" renderIcon={CharacterWholeNumber}>
            <FormattedMessage id="sidenav.label.admin.labNumber" />
          </SideNavLink>
          <SideNavLink renderIcon={ChartBubble} href="#program">
            <FormattedMessage id="sidenav.label.admin.program" />
          </SideNavLink>
          <SideNavMenu
            title={intl.formatMessage({ id: "sidenav.label.admin.menu" })}
            renderIcon={TableOfContents}
          >
            <SideNavMenuItem href="#globalMenuManagement">
              <FormattedMessage id="sidenav.label.admin.menu.global" />
            </SideNavMenuItem>
            <SideNavMenuItem href="#billingMenuManagement">
              <FormattedMessage id="sidenav.label.admin.menu.billing" />
            </SideNavMenuItem>
            <SideNavMenuItem href="#nonConformityMenuManagement">
              <FormattedMessage id="sidenav.label.admin.menu.nonconform" />
            </SideNavMenuItem>
            <SideNavMenuItem href="#patientMenuManagement">
              <FormattedMessage id="sidenav.label.admin.menu.patient" />
            </SideNavMenuItem>
            <SideNavMenuItem href="#studyMenuManagement">
              <FormattedMessage id="sidenav.label.admin.menu.study" />
            </SideNavMenuItem>
          </SideNavMenu>
          <SideNavLink href="#commonproperties" renderIcon={Settings}>
            <FormattedMessage
              id="sidenav.label.admin.commonproperties"
              defaultMessage={"Common Properties"}
            />
          </SideNavLink>
          {/* <SideNavLink
            renderIcon={Catalog}
            target="_blank"
            href={config.serverBaseUrl + "/MasterListsPage"}
          >
            <FormattedMessage id="admin.legacy" />
          </SideNavLink> */}
          <SideNavMenu
            title={intl.formatMessage({ id: "master.lists.page" })}
            renderIcon={Catalog}
          >
            <SideNavMenuItem href={config.serverBaseUrl + "/AnalyzerTestNameMenu"}>
              <FormattedMessage id="master.lists.page.analyzer" />
            </SideNavMenuItem>
            <SideNavMenuItem href={config.serverBaseUrl + "/BarcodeConfiguration"}>
              <FormattedMessage id="master.lists.page.barcode" />
            </SideNavMenuItem>
            <SideNavMenuItem href={config.serverBaseUrl + "/BatchTestReassignment"}>
              <FormattedMessage id="master.lists.page.batchtest" />
            </SideNavMenuItem>
            <SideNavMenuItem href={config.serverBaseUrl + "/DictionaryMenu"}>
              <FormattedMessage id="dictionary.label.modify" />
            </SideNavMenuItem>
            <SideNavMenuItem href={config.serverBaseUrl + "/ExternalConnectionsMenu"}>
              <FormattedMessage id="master.lists.page.external.connections" />
            </SideNavMenuItem>

            <SideNavMenuItem href={config.serverBaseUrl + "/ValidationConfigurationMenu"}>
              <FormattedMessage id="master.lists.page.field" />
            </SideNavMenuItem>
            <SideNavMenuItem href={config.serverBaseUrl + "/ListPlugins"}>
              <FormattedMessage id="master.lists.page.list" />
            </SideNavMenuItem>
            <SideNavMenuItem href={config.serverBaseUrl + "/MenuStatementConfigMenu"}>
              <FormattedMessage id="master.lists.page.menu" />
            </SideNavMenuItem>
            <SideNavMenuItem href={config.serverBaseUrl + "/SampleEntryConfigMenu"}>
              <FormattedMessage id="master.lists.page.order.entry" />
            </SideNavMenuItem>
            <SideNavMenuItem href={config.serverBaseUrl + "/OrganizationMenu"}>
              <FormattedMessage id="master.lists.page.org" />
            </SideNavMenuItem>

            <SideNavMenuItem href={config.serverBaseUrl + "/PatientConfigurationMenu"}>
              <FormattedMessage id="master.lists.page.patient" />
            </SideNavMenuItem>
            <SideNavMenuItem href={config.serverBaseUrl + "/PrintedReportsConfigurationMenu"}>
              <FormattedMessage id="master.lists.page.printed" />
            </SideNavMenuItem>
            <SideNavMenuItem href={config.serverBaseUrl + "/ProviderMenu"}>
              <FormattedMessage id="master.lists.page.provider" />
            </SideNavMenuItem>
            <SideNavMenuItem href={config.serverBaseUrl + "/ResultConfigurationMenu"}>
              <FormattedMessage id="master.lists.page.result" />
            </SideNavMenuItem>
            <SideNavMenuItem href={config.serverBaseUrl + "/ResultReportingConfiguration"}>
              <FormattedMessage id="master.lists.page.report.result" />
            </SideNavMenuItem>

            <SideNavMenuItem href={config.serverBaseUrl + "/SiteInformationMenu"}>
              <FormattedMessage id="master.lists.page.site" />
            </SideNavMenuItem>
            <SideNavMenuItem href={config.serverBaseUrl + "/TestManagementConfigMenu"}>
              <FormattedMessage id="master.lists.page.test.management" />
            </SideNavMenuItem>
            <SideNavMenuItem href={config.serverBaseUrl + "/TestNotificationConfigMenu"}>
              <FormattedMessage id="master.lists.page.notification" />
            </SideNavMenuItem>
            <SideNavMenuItem href={config.serverBaseUrl + "/UnifiedSystemUserMenu"}>
              <FormattedMessage id="master.lists.page.user" />
            </SideNavMenuItem>
            <SideNavMenuItem href={config.serverBaseUrl + "/WorkplanConfigurationMenu"}>
              <FormattedMessage id="master.lists.page.work.plan" />
            </SideNavMenuItem>
          </SideNavMenu>
        </SideNavItems>
      </SideNav>

      <PathRoute path="#reflex">
        <ReflexTestManagement />
      </PathRoute>
      <PathRoute path="#calculatedValue">
        <CalculatedValue />
      </PathRoute>
      <PathRoute path="#labNumber">
        <LabNumberManagement />
      </PathRoute>
      <PathRoute path="#program">
        <ProgramManagement />
      </PathRoute>
      <PathRoute path="#globalMenuManagement">
        <GlobalMenuManagement />
      </PathRoute>
      <PathRoute path="#billingMenuManagement">
        <BillingMenuManagement />
      </PathRoute>
      <PathRoute path="#nonConformityMenuManagement">
        <NonConformityMenuManagement />
      </PathRoute>
      <PathRoute path="#patientMenuManagement">
        <PatientMenuManagement />
      </PathRoute>
      <PathRoute path="#studyMenuManagement">
        <StudyMenuManagement />
      </PathRoute>
      <PathRoute path="#commonproperties">
        <CommonProperties />
      </PathRoute>
    </>
  );
}

export default injectIntl(Admin);
