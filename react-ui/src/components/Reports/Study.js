import React from 'react'
import GlobalSideBar from "../common/GlobalSideBar";
import {IbmWatsonDiscovery, IbmWatsonNaturalLanguageUnderstanding, Microscope} from "@carbon/icons-react";
import config from "../../config.json";
export const RoutineReportsMenu = { className: "resultSideNav",
    sideNavMenuItems: [
        {
            title: "ARV Reports",
            icon: IbmWatsonDiscovery,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl + "/Report?type=patient&report=patientARVInitial1",
                    label: "ARV Initial Version 1"
                },{
                    link: config.serverBaseUrl + "/Report?type=patient&report=patientARVInitial2",
                    label: "ARV Initial Version 2"
                },{
                    link: config.serverBaseUrl + "/Report?type=patient&report=patientARVFollowup1",
                    label: "ARV Follow-up Version 1"
                },{
                    link: config.serverBaseUrl + "/Report?type=patient&report=patientARVFollowup2",
                    label: "ARV Follow-up Version 2"
                },{
                    link: config.serverBaseUrl + "/Report?type=patient&report=patientARV1",
                    label: "ARV-Version 1"
                }
            ]
        },
        {
            title: "EID Reports",
            icon: Microscope,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl + "/Report?type=patient&report=patientEID1",
                    label: "EID Version 1"
                }, {
                    link: config.serverBaseUrl + "/Report?type=patient&report=patientEID2",
                    label: "EID Version 2"
                }
            ]
        },
        {
            title: "VL Report",
            icon: IbmWatsonNaturalLanguageUnderstanding,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl + "/Report?type=patient&report=patientVL1",
                    label: "VL version Nationale"
                }
            ]
        },
        {
            title: "Intermediate Report",
            icon: IbmWatsonNaturalLanguageUnderstanding,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl + "/Report?type=patient&report=patientIndeterminate1",
                    label: "Intermediate version 1"
                }, {
                    link: config.serverBaseUrl + "/Report?type=patient&report=patientIndeterminate2",
                    label: "Intermediate version 2"
                }, {
                    link: config.serverBaseUrl + "/Report?type=patient&report=patientIndeterminateByLocation",
                    label: "Intermediate By Service"
                }
            ]
        },
        {
            title: "Special Request ",
            icon: IbmWatsonNaturalLanguageUnderstanding,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl + "/Report?type=patient&report=patientSpecialReport",
                    label: "Special Request"
                }
            ]
        }, {
            title: "Collected ARV patient Report",
            icon: IbmWatsonNaturalLanguageUnderstanding,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl + "/Report?type=patient&report=patientCollection",
                    label: "Collected ARV patient Report"
                }
            ]
        }, {
            title: "Associated Patient Report",
            icon: IbmWatsonNaturalLanguageUnderstanding,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl + "/Report?type=patient&report=patientAssociated",
                    label: "Associated Patient Report"
                }
            ]
        },{
            title: "Indicator",
            icon: IbmWatsonNaturalLanguageUnderstanding,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl + "/ReportPrint?type=indicator&report=indicatorSectionPerformance",
                    label: "Section Performance"
                }, {
                    link: config.serverBaseUrl + "/ReportPrint?type=indicator&report=validationBacklog",
                    label: "Delayed Validation"
                }
            ]
        },{
            title: "Non conformity Reports",
            icon: IbmWatsonNaturalLanguageUnderstanding,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl + "/Report?type=patient&report=retroCINonConformityByDate",
                    label: "By date"
                }, {
                    link: config.serverBaseUrl + "/Report?type=patient&report=retroCInonConformityBySectionReason",
                    label: config.serverBaseUrl + "By Unit and Reason"
                }, {
                    link: config.serverBaseUrl + "/Report?type=patient&report=retroCINonConformityByLabno",
                    label: "By LabNo"
                }, {
                    link: config.serverBaseUrl + "/Report?type=patient&report=retroCInonConformityNotification",
                    label: "Non-conformity notification"
                }, {
                    link: config.serverBaseUrl + "/Report?type=patient&report=retroCIFollowupRequiredByLocation",
                    label: "Follow-up required"
                }
            ]
        },{
            title: "Export By Date",
            icon: IbmWatsonNaturalLanguageUnderstanding,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl + "/Report?type=patient&report=CIStudyExport",
                    label: "General Report"
                },{
                    link: config.serverBaseUrl + "/Report?type=patient&report=Trends",
                    label: "Viral Load Data Export"
                }
            ]
        },{
            title: "Audit Trail",
            icon: IbmWatsonNaturalLanguageUnderstanding,
            SideNavMenuItem: [
                {
                    link: config.serverBaseUrl + "/AuditTrailReport",
                    label: "Audit Trail"
                }
            ]
        },
    ],
    contentRoutes: []
}
const Study = () => {

  return (
    <>
     <GlobalSideBar sideNav={RoutineReportsMenu}/>
    </>
  )
}

export default Study;
