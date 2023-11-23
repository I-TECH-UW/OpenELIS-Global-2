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
      title:<FormattedMessage id="sideNav.title.arvreports" defaultMessage="ARV Reports"/>,
      icon: IbmWatsonDiscovery,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientARVInitial1",
          label: <FormattedMessage id="sideNav.label.arvreports1" defaultMessage="ARV Initial Version 1"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientARVInitial2",
          label:<FormattedMessage id="sideNav.label.arvreports2" defaultMessage="ARV Initial Version 2"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientARVFollowup1",
          label: <FormattedMessage id="sideNav.label.arvfollowup1" defaultMessage="ARV Follow-up Version 1"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientARVFollowup2",
          label: <FormattedMessage id="sideNav.label.arvfollowup2" defaultMessage="ARV Follow-up Version 2"/>,
        },
        {
          link:
            config.serverBaseUrl + "/Report?type=patient&report=patientARV1",
          label: <FormattedMessage id="sideNav.label.arvversion1" defaultMessage="ARV-Version 1"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.eidreports" defaultMessage="EID Reports"/>,
      icon: Microscope,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl + "/Report?type=patient&report=patientEID1",
          label: <FormattedMessage id="sideNav.label.eidreports1" defaultMessage="EID Version 1"/>,
        },
        {
          link:
            config.serverBaseUrl + "/Report?type=patient&report=patientEID2",
          label: <FormattedMessage id="sideNav.label.eidreports2" defaultMessage="EID Version 2"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.vlreport" defaultMessage="VL Report"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link: config.serverBaseUrl + "/Report?type=patient&report=patientVL1",
          label: <FormattedMessage id="sideNav.label.vlversionnational" defaultMessage="VL version Nationale"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.intermediatereport" defaultMessage="Intermediate Report"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientIndeterminate1",
          label: <FormattedMessage id="sideNav.label.intermediatereport1" defaultMessage="Intermediate Version 1"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientIndeterminate2",
          label: <FormattedMessage id="sideNav.label.intermediatereport2" defaultMessage="Intermediate Version 2"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientIndeterminateByLocation",
          label: <FormattedMessage id="sideNav.label.intermediatebyservice" defaultMessage="Intermediate By Service"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.specialrequest" defaultMessage="Special Request "/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientSpecialReport",
          label: <FormattedMessage id="sideNav.label.specialrequest" defaultMessage="Special Request "/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.collectedarvpatientreports" defaultMessage="Collected ARV patient Report"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientCollection",
          label:  <FormattedMessage id="sideNav.label.collectedarvpatientreports" defaultMessage="Collected ARV patient Report"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.associatedpatientreport" defaultMessage="Associated Patient Report"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=patientAssociated",
          label: <FormattedMessage id="sideNav.label.associatedpatientreport" defaultMessage="Associated Patient Report"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.indicator" defaultMessage="Indicator"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl +
            "/ReportPrint?type=indicator&report=indicatorSectionPerformance",
          label: <FormattedMessage id="sideNav.label.sectionperformance" defaultMessage="Section Performance"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/ReportPrint?type=indicator&report=validationBacklog",
          label: <FormattedMessage id="sideNav.label.delayedvalidation" defaultMessage="Delayed Validation"/>,
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
            "/Report?type=patient&report=retroCINonConformityByDate",
          label: <FormattedMessage id="sideNav.label.noncomformityreportsbydate" defaultMessage="By Date"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=retroCInonConformityBySectionReason",
          label: config.serverBaseUrl + <FormattedMessage id="sideNav.label.noncomformityreportsbyunit" defaultMessage="By Unit and Reason"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=retroCINonConformityByLabno",
          label: <FormattedMessage id="sideNav.label.noncomformityreportsbylabno" defaultMessage="By Lab No"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=retroCInonConformityNotification",
          label: <FormattedMessage id="sideNav.label.noncomformitynotification" defaultMessage="Non-conformity Notification"/>,
        },
        {
          link:
            config.serverBaseUrl +
            "/Report?type=patient&report=retroCIFollowupRequiredByLocation",
          label:<FormattedMessage id="sideNav.label.followuprequired" defaultMessage="Follow-up Required"/>,
        },
      ],
    },
    {
      title:  <FormattedMessage id="sideNav.title.exportbydate" defaultMessage="Export By Date"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link:
            config.serverBaseUrl + "/Report?type=patient&report=CIStudyExport",
          label: <FormattedMessage id="sideNav.label.generalreport" defaultMessage="General Report"/>,
        },
        {
          link: config.serverBaseUrl + "/Report?type=patient&report=Trends",
          label: <FormattedMessage id="sideNav.label.viralloaddataexport" defaultMessage="Viral Load Data Export"/>,
        },
      ],
    },
    {
      title: <FormattedMessage id="sideNav.title.audittrail" defaultMessage="Audit Trail"/>,
      icon: IbmWatsonNaturalLanguageUnderstanding,
      SideNavMenuItem: [
        {
          link: config.serverBaseUrl + "/AuditTrailReport",
          label:<FormattedMessage id="sideNav.label.audittrail" defaultMessage="Audit Trail"/>,
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
