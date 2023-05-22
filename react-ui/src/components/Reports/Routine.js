import React from 'react'
import GlobalSideBar from "../common/GlobalSideBar";
import {IbmWatsonDiscovery, IbmWatsonNaturalLanguageUnderstanding, Microscope} from "@carbon/icons-react";
import config from "../../config.json";
export const RoutineReportsMenu = { className: "resultSideNav",
    sideNavMenuItems: [
        {
            title: "Patient Status Report",
            icon: IbmWatsonDiscovery,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl + "/Report?type=patient&report=patientCILNSP_vreduit",
                    label: "Patient Status Report"
                }
            ]
        },
        {
            title: "Aggregate Reports",
            icon: Microscope,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl + "/Report?type=indicator&report=statisticsReport",
                    label: "Statistics Report"
                },{
                    link: config.serverBaseUrl + "/Report?type=indicator&report=indicatorHaitiLNSPAllTests",
                    label: "Summary of All Tests"
                },{
                    link: config.serverBaseUrl + "/Report?type=indicator&report=indicatorCDILNSPHIV",
                    label: "HIV Test Summary"
                }
            ]
        },
        {
            title: "Rejection Report",
            icon: IbmWatsonNaturalLanguageUnderstanding,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl + "/Report?type=indicator&report=sampleRejectionReport",
                    label: "Rejection Report"
                }
            ]
        },
        {
            title: "Activity Report",
            icon: IbmWatsonNaturalLanguageUnderstanding,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl +"/Report?type=indicator&report=activityReportByTest",
                    label: "By Test Type"
                }, {
                    link: config.serverBaseUrl + "/Report?type=indicator&report=activityReportByPanel",
                    label: "By Panel Type"
                }, {
                    link: config.serverBaseUrl + "/Report?type=indicator&report=activityReportByTestSection",
                    label: "By Unit"
                }
            ]
        },
        {
            title: "Referred Tests Report",
            icon: IbmWatsonNaturalLanguageUnderstanding,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl +"/Report?type=patient&report=referredOut",
                    label: "Referred Tests Report"
                }
            ]
        }, {
            title: "Non conformity Reports",
            icon: IbmWatsonNaturalLanguageUnderstanding,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl +"/Report?type=patient&report=haitiNonConformityByDate",
                    label: "By Date"
                }, {
                    link: config.serverBaseUrl +"/Report?type=patient&report=haitiNonConformityBySectionReason",
                    label: "By unit and Reason"
                }
            ]
        }, {
            title: "Delayed Validation",
            icon: IbmWatsonNaturalLanguageUnderstanding,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl +"/ReportPrint?type=indicator&report=validationBacklog",
                    label: "Delayed Validation"
                }
            ]
        },{
            title: "Audit Trail",
            icon: IbmWatsonNaturalLanguageUnderstanding,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl +"/AuditTrailReport",
                    label: "Audit Trail"
                }
            ]
        },{
            title: "Export Routine CSV file",
            icon: IbmWatsonNaturalLanguageUnderstanding,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl +"/Report?type=patient&report=CISampleRoutineExport",
                    label: "Export Routine CSV file"
                }
            ]
        },
    ],
    contentRoutes: []
}
const Routine = () => {

  return (
    <>
     <GlobalSideBar sideNav={RoutineReportsMenu}/>
    </>
  )
}

export default Routine;
