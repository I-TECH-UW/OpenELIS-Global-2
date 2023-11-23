import React from "react";
import GlobalSideBar from "../common/GlobalSideBar";
import { FormattedMessage, injectIntl } from "react-intl";
import {
  IbmWatsonDiscovery,
  IbmWatsonNaturalLanguageUnderstanding,
  Microscope,
} from "@carbon/icons-react";
import config from "../../config.json";
export const RoutineReportsMenu = {
  className: "resultSideNav",
  sideNavMenuItems: [
    {
      title: <FormattedMessage id="sidenav.title.statusreport" defaultMessage="Patient Status Report"/>,
      icon: IbmWatsonDiscovery,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientCILNSP_vreduit",
          label: <FormattedMessage id="sidenav.label.statusreport" defaultMessage="Patient Status Report"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sidenav.title.aggregatereport" defaultMessage="Aggregate Reports"/>,
      icon: Microscope,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/Report?type=indicator&report=statisticsReport",
          label: <FormattedMessage id= "sidenav.label.statisticsreport" defaultMessage="Statistics Report"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=indicator&report=indicatorHaitiLNSPAllTests",
          label: <FormattedMessage id= "sidenav.label.testsummary" defaultMessage="Summary of All Tests"/>
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=indicator&report=indicatorCDILNSPHIV",
          label: <FormattedMessage id="sideNav.label.hivtestsummary" defaultMessage="HIV Test Summary"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.rejectionreport" defaultMessage="Rejection Report"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/Report?type=indicator&report=sampleRejectionReport",
          label: <FormattedMessage id="sideNav.label.rejectionreport" defaultMessage="Rejection Report"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.activityreport" defaultMessage="Activity Report"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/Report?type=indicator&report=activityReportByTest",
          label: <FormattedMessage id="sideNav.label.bytesttype" defaultMessage="By Test Type"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=indicator&report=activityReportByPanel",
          label:  <FormattedMessage id="sideNav.label.bypaneltype" defaultMessage="By Panel Type"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=indicator&report=activityReportByTestSection",
          label: <FormattedMessage id="sideNav.label.byunit" defaultMessage="By Unit"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.referredtestreport" defaultMessage="Referred Tests Report"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl + "/Report?type=patient&report=referredOut",
          label: <FormattedMessage id="sideNav.label.referredtestreport" defaultMessage="Referred Tests Report"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.noncomformityreports" defaultMessage="Non conformity Reports"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=haitiNonConformityByDate",
          label:  <FormattedMessage id="sideNav.label.noncomformityreportsbydate" defaultMessage="By Date"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=haitiNonConformityBySectionReason",
          label: <FormattedMessage id="sideNav.label.noncomformityreportsbyunit" defaultMessage="By Unit and Reason"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.delayedvalidation" defaultMessage="Delayed Validation"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/ReportPrint?type=indicator&report=validationBacklog",
          label: <FormattedMessage id="sideNav.label.delayedvalidation" defaultMessage="Delayed Validation"/>,
          icon: IbmWatsonNaturalLanguageUnderstanding,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.audittrail" defaultMessage="Audit Trail"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link: config.serverBaseUrl + "/AuditTrailReport",
          label:  <FormattedMessage id="sideNav.label.audittrail" defaultMessage="Audit Trail"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.exportcsvfile" defaultMessage="Export Routine CSV file"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=CISampleRoutineExport",
          label: <FormattedMessage id="sideNav.label.exportcsvfile" defaultMessage="Export Routine CSV file"/>,
        },
      ],
    },
  ],
  contentRoutes: [],
};
const Routine = () => {
  return (
    <>
      <GlobalSideBar sideNav={RoutineReportsMenu} />
    </>
  );
};

export default injectIntl(Routine);
