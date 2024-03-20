import React, { useContext, useState, useEffect } from "react";
import { AlertDialog } from "../../common/CustomNotification";
import { NotificationContext } from "../../layout/Layout";
import {
  Heading,
  Grid,
  Column,
  Section,
  Loading,
} from "@carbon/react";
import { injectIntl, FormattedMessage, useIntl } from "react-intl";
import PatientStatusReport from "./PatientStatusReport";
import StatisticsReport from "./StatisticsReport";

import SummaryOfAllTest from "./SummaryOfAllTest";
import HIVTestSummary from "./HivTestSummary";
import NonconformityReportsByUnit from "./Nonconformity-Unit";
import ReferredOut from "./ReferredOut";
import ReportByDate from "../study/common/ReportByDate";
import PageBreadCrumb from "../../common/PageBreadCrumb";
const RoutineIndex = () => {
  const intl = useIntl();
  const { setNotificationVisible, addNotification, notificationVisible } =
    useContext(NotificationContext);

  const [type, setType] = useState("");
  const [report, setReport] = useState("");
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const params = new URLSearchParams(window.location.search);
    const paramType = params.get("type");
    const paramReport = params.get("report");
    setType(paramType);
    setReport(paramReport);

    if (paramType && paramReport) {
      setIsLoading(false);
    } else {
      window.location.href = "/RoutineReports";
    }
  }, []);

  return (
      <>
      <br/>
      <PageBreadCrumb breadcrumbs={[{ label: "home.label", link: "/" },{ label: "routine.reports", link: "/RoutineReports" },]}/>
      <div className="orderLegendBody">
      
        {notificationVisible === true && <AlertDialog />}
        {isLoading && <Loading />}
        {!isLoading && (
          <>
            {type === "patient" && report === "patientCILNSP_vreduit" && 
            (<PatientStatusReport />)}
            
            {type === "patient" && 
             report === "referredOut" && 
             (<ReferredOut />)}
            
            {type === "patient" &&
              report === "haitiNonConformityBySectionReason" && (
                <ReportByDate
                  report={"haitiNonConformityBySectionReason"}
                  id={"openreports.mgt.nonconformity.section"}
                />
              )}

            {type === "patient" && report === "haitiNonConformityByDate" && (
              <ReportByDate
                report={"haitiNonConformityByDate"}
                id={"openreports.mgt.nonconformity.date"}
              />
            )}

            {type === "patient" && report === "CISampleRoutineExport" && (
              <ReportByDate
                report={"CISampleRoutineExport"}
                id={"sideNav.label.exportcsvfile"}
              />
            )}

            {type === "indicator" &&
              report === "statisticsReport" &&
              (<StatisticsReport />)}

            {type === "indicator" &&
              report === "indicatorHaitiLNSPAllTests" &&
              (<ReportByDate
                  report={"indicatorHaitiLNSPAllTests"}
                  id={"openreports.all.test.summary.title"}
                />)}

            {type === "indicator" &&
              report === "indicatorCDILNSPHIV" &&
               (
                <ReportByDate
                  report={"indicatorHaitiLNSPAllTests"}
                  id={"openreports.all.test.summary.title"}
                />
              )}

              {type === "indicator" &&
              report === "sampleRejectionReport" &&
              (<ReportByDate
                report={"sampleRejectionReport"}
                id={"openreports.mgt.rejection"}
              />)}

            {type === "patient" &&
              report === "haitiNonConformityBySectionReason" && 
                (<NonconformityReportsByUnit />)}

          </>
        )}

     
      </div>
      </>
      
  );
};

export default injectIntl(RoutineIndex);
