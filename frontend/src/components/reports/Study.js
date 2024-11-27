import React from "react";
import GlobalSideBar from "../common/GlobalSideBar";
import { FormattedMessage, injectIntl} from "react-intl";
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
      title:<FormattedMessage id="sideNav.title.arvreports"/>,
      icon: IbmWatsonDiscovery,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientARVInitial1",
          label: <FormattedMessage id="sideNav.label.arvreports1"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientARVInitial2",
          label:<FormattedMessage id="sideNav.label.arvreports2"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientARVFollowup1",
          label: <FormattedMessage id="sideNav.label.arvfollowup1"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientARVFollowup2",
          label: <FormattedMessage id="sideNav.label.arvfollowup2"/>,
        },
        {
          link:
            config.serverBaseUrl + "/Report?type=patient&report=patientARV1",
          label: <FormattedMessage id="sideNav.label.arvversion1"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.eidreports"/>,
      icon: Microscope,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl + "/Report?type=patient&report=patientEID1",
          label: <FormattedMessage id="sideNav.label.eidreports1"/>,
        },
        {
          link:
            config.serverBaseUrl + "/Report?type=patient&report=patientEID2",
          label: <FormattedMessage id="sideNav.label.eidreports2"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.vlreport"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link: config.serverBaseUrl + "/Report?type=patient&report=patientVL1",
          label: <FormattedMessage id="sideNav.label.vlversionnational"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.intermediatereport"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientIndeterminate1",
          label: <FormattedMessage id="sideNav.label.intermediatereport1"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientIndeterminate2",
          label: <FormattedMessage id="sideNav.label.intermediatereport2"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientIndeterminateByLocation",
          label: <FormattedMessage id="sideNav.label.intermediatebyservice"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.specialrequest"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientSpecialReport",
          label: <FormattedMessage id="sideNav.label.specialrequest"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.collectedarvpatientreports"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientCollection",
          label:  <FormattedMessage id="sideNav.label.collectedarvpatientreports"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.associatedpatientreport"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientAssociated",
          label: <FormattedMessage id="sideNav.label.associatedpatientreport"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.indicator"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/ReportPrint?type=indicator&report=indicatorSectionPerformance",
          label: <FormattedMessage id="sideNav.label.sectionperformance"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/ReportPrint?type=indicator&report=validationBacklog",
          label: <FormattedMessage id="sideNav.label.delayedvalidation"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.noncomformityreports"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=retroCINonConformityByDate",
          label: <FormattedMessage id="sideNav.label.noncomformityreportsbydate"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=retroCInonConformityBySectionReason",
          label: config.serverBaseUrl + <FormattedMessage id="sideNav.label.noncomformityreportsbyunit"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=retroCINonConformityByLabno",
          label: <FormattedMessage id="sideNav.label.noncomformityreportsbylabno"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=retroCInonConformityNotification",
          label: <FormattedMessage id="sideNav.label.noncomformitynotification"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=retroCIFollowupRequiredByLocation",
          label:<FormattedMessage id="sideNav.label.followuprequired"/>,
        },
      ],
    },
    {
      title:  <FormattedMessage id="sideNav.title.exportbydate"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl + "/Report?type=patient&report=CIStudyExport",
          label: <FormattedMessage id="sideNav.label.generalreport"/>,
        },
        {
          link: config.serverBaseUrl + "/Report?type=patient&report=Trends",
          label: <FormattedMessage id="sideNav.label.viralloaddataexport"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.audittrail"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link: config.serverBaseUrl + "/AuditTrailReport",
          label:<FormattedMessage id="sideNav.label.audittrail"/>,
        },
      ],
    },
  ],
  contentRoutes: [],
};
const Study = () => {
  return (
    <>
      <GlobalSideBar sideNav={RoutineReportsMenu} />
    </>
  );
};

export default injectIntl(Study);
