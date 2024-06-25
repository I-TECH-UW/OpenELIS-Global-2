import React, { useState, useEffect } from "react";
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
  DictionaryManagement,
} from "./menu";
import {
  Microscope,
  CharacterWholeNumber,
  TableOfContents,
  ChartBubble,
  Catalog,
  Settings,
  ListDropdown,
  CicsSystemGroup,
  QrCode,
  ContainerSoftware,
  BootVolumeAlt
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
import ConfigMenuDisplay from "./formEntry/common/ConfigMenuDisplay";
import ProviderMenu from "./ProviderMenu/ProviderMenu";
import BarcodeConfiguration from "./barcodeConfiguration/BarcodeConfiguration";
import OrganizationManagament from "./OrganizationManagement/OrganizationManagement";
import OrganizationAddModify from "./OrganizationManagement/OrganizationAddModify.js";
import AnalyzerTestName from "./analyzerTestName/AnalyzerTestName.js";
import PluginList from "./pluginFile/PluginFile.js";

function Admin() {
  const intl = useIntl();
  const [isSmallScreen, setIsSmallScreen] = useState(false);

  useEffect(() => {
    const mediaQuery = window.matchMedia("(max-width: 1024px)"); //applicable for medium screen and below  for only small screen set max-width: 768px
    const handleMediaQueryChange = () => setIsSmallScreen(mediaQuery.matches);

    handleMediaQueryChange();
    mediaQuery.addEventListener("change", handleMediaQueryChange);

    return () =>
      mediaQuery.removeEventListener("change", handleMediaQueryChange);
  }, []);

  return (
    <>
      <SideNav
        aria-label="Side navigation"
        defaultExpanded={true}
        isRail={isSmallScreen}
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
          <SideNavLink href="#AnalyzerTestName" renderIcon={ListDropdown}>
            <FormattedMessage id="sidenav.label.admin.analyzerTest" />
          </SideNavLink>
          <SideNavLink href="#labNumber" renderIcon={CharacterWholeNumber}>
            <FormattedMessage id="sidenav.label.admin.labNumber" />
          </SideNavLink>
          <SideNavLink renderIcon={ChartBubble} href="#program">
            <FormattedMessage id="sidenav.label.admin.program" />
          </SideNavLink>
          <SideNavLink renderIcon={CicsSystemGroup} href="#providerMenu">
            <FormattedMessage id="provider.browse.title" />
          </SideNavLink>
          <SideNavLink renderIcon={QrCode} href="#barcodeConfiguration">
            <FormattedMessage id="sidenav.label.admin.barcodeconfiguration" />
          </SideNavLink>
          <SideNavLink href="#PluginFile" renderIcon={BootVolumeAlt}>
            <FormattedMessage id="sidenav.label.admin.pluginFile" />
          </SideNavLink>
          <SideNavLink
            renderIcon={ContainerSoftware}
            href="#organizationManagement"
          >
            <FormattedMessage id="organization.main.title" />
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

          <SideNavMenu
            title={intl.formatMessage({ id: "admin.formEntryConfig" })}
            renderIcon={ListDropdown}
          >
            <SideNavMenuItem href="#WorkPlanConfigurationMenu">
              <FormattedMessage id="sidenav.label.admin.formEntry.Workplanconfig" />
            </SideNavMenuItem>
            <SideNavMenuItem href="#SiteInformationMenu">
              <FormattedMessage id="sidenav.label.admin.formEntry.siteInfoconfig" />
            </SideNavMenuItem>
            <SideNavMenuItem href="#ResultConfigurationMenu">
              <FormattedMessage id="sidenav.label.admin.formEntry.resultConfig" />
            </SideNavMenuItem>
            <SideNavMenuItem href="#PatientConfigurationMenu">
              <FormattedMessage id="sidenav.label.admin.formEntry.patientconfig" />
            </SideNavMenuItem>
            <SideNavMenuItem href="#PrintedReportsConfigurationMenu">
              <FormattedMessage id="sidenav.label.admin.formEntry.PrintedReportsconfig" />
            </SideNavMenuItem>
            <SideNavMenuItem href="#SampleEntryConfigurationMenu">
              <FormattedMessage id="sidenav.label.admin.formEntry.sampleEntryconfig" />
            </SideNavMenuItem>
            <SideNavMenuItem href="#ValidationConfigurationMenu">
              <FormattedMessage id="sidenav.label.admin.formEntry.validationconfig" />
            </SideNavMenuItem>
          </SideNavMenu>

          <SideNavLink href="#commonproperties" renderIcon={Settings}>
            <FormattedMessage
              id="sidenav.label.admin.commonproperties"
              defaultMessage={"Common Properties"}
            />
          </SideNavLink>
          <SideNavLink href="#DictionaryMenu" renderIcon={CharacterWholeNumber}>
            <FormattedMessage id="dictionary.label.modify" />
          </SideNavLink>
          <SideNavLink
            renderIcon={Catalog}
            target="_blank"
            href={config.serverBaseUrl + "/MasterListsPage"}
          >
            <FormattedMessage id="admin.legacy" />
          </SideNavLink>
        </SideNavItems>
      </SideNav>

      <PathRoute path="#reflex">
        <ReflexTestManagement />
      </PathRoute>
      <PathRoute path="#calculatedValue">
        <CalculatedValue />
      </PathRoute>
      <PathRoute path="#AnalyzerTestName">
        <AnalyzerTestName />
      </PathRoute>
      <PathRoute path="#labNumber">
        <LabNumberManagement />
      </PathRoute>
      <PathRoute path="#program">
        <ProgramManagement />
      </PathRoute>
      <PathRoute path="#providerMenu">
        <ProviderMenu />
      </PathRoute>
      <PathRoute path="#barcodeConfiguration">
        <BarcodeConfiguration />
      </PathRoute>
      <PathRoute path="#organizationManagement">
        <OrganizationManagament />
      </PathRoute>
      <PathRoute path="#organizationEdit">
        <OrganizationAddModify />
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

      <PathRoute path="#ValidationConfigurationMenu">
        <ConfigMenuDisplay
          menuType="ValidationConfigurationMenu"
          id="sidenav.label.admin.formEntry.validationconfig"
        />
      </PathRoute>
      <PathRoute path="#SampleEntryConfigurationMenu">
        <ConfigMenuDisplay
          menuType="SampleEntryConfigMenu"
          id="sidenav.label.admin.formEntry.sampleEntryconfig"
        />
      </PathRoute>
      <PathRoute path="#WorkPlanConfigurationMenu">
        <ConfigMenuDisplay
          menuType="WorkplanConfigurationMenu"
          id="sidenav.label.admin.formEntry.Workplanconfig"
        />
      </PathRoute>
      <PathRoute path="#SiteInformationMenu">
        <ConfigMenuDisplay
          menuType="SiteInformationMenu"
          id="sidenav.label.admin.formEntry.siteInfoconfig"
        />
      </PathRoute>
      <PathRoute path="#ResultConfigurationMenu">
        <ConfigMenuDisplay
          menuType="ResultConfigurationMenu"
          id="sidenav.label.admin.formEntry.resultConfig"
        />
      </PathRoute>
      <PathRoute path="#PatientConfigurationMenu">
        <ConfigMenuDisplay
          menuType="PatientConfigurationMenu"
          id="sidenav.label.admin.formEntry.patientconfig"
        />
      </PathRoute>
      <PathRoute path="#PrintedReportsConfigurationMenu">
        <ConfigMenuDisplay
          menuType="PrintedReportsConfigurationMenu"
          id="sidenav.label.admin.formEntry.PrintedReportsconfig"
        />
      </PathRoute>
      <PathRoute path="#DictionaryMenu">
        <DictionaryManagement />
      </PathRoute>
      <PathRoute path="#PluginFile">
        <PluginList />
      </PathRoute>
    </>
  );
}

export default injectIntl(Admin);
