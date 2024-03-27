import React from "react";
import config from "../../../config.json";
import { FormattedMessage, useIntl, injectIntl } from "react-intl";
import "../../Style.css";
import {
  SideNav,
  SideNavItems,
  SideNavMenuItem,
} from "@carbon/react";
import PathRoute from "../../utils/PathRoute";
import DictionaryManagement from "./Dictionary/DictionaryManagement";

function MasterListsPage() {
  const intl = useIntl();
  return (
    <>
      <SideNav
        aria-label="Side navigation"
        defaultExpanded={true}
        isRail={true}
      >
        <SideNavItems className="adminSideNav">
            <SideNavMenuItem href={config.serverBaseUrl + "/AnalyzerTestNameMenu"}>
              <FormattedMessage id="master.lists.page.analyzer" />
            </SideNavMenuItem>
            <SideNavMenuItem href={config.serverBaseUrl + "/BarcodeConfiguration"}>
              <FormattedMessage id="master.lists.page.barcode" />
            </SideNavMenuItem>
            <SideNavMenuItem href={config.serverBaseUrl + "/BatchTestReassignment"}>
              <FormattedMessage id="master.lists.page.batchtest" />
            </SideNavMenuItem>
            <SideNavMenuItem href="#DictionaryMenu">
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
        </SideNavItems>
      </SideNav>

      <PathRoute path="#DictionaryMenu">
        <DictionaryManagement />
      </PathRoute>
    </>
  );
}

export default injectIntl(MasterListsPage);
