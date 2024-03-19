import React, { useContext, useState, useEffect } from "react";
import { AlertDialog } from "../../common/CustomNotification";
import { NotificationContext } from "../../layout/Layout";
import {
  Heading,
  Grid,
  Column,
  Section,
  Breadcrumb,
  BreadcrumbItem,
  Loading,
} from "@carbon/react";
import { injectIntl, FormattedMessage, useIntl } from "react-intl";
import PatientStatusReport from "./PatientStatusReport";
import StatisticsReport from "./StatisticsReport";
import SummaryOfAllTest from "./SummaryOfAllTest";
import HIVTestSummary from "./HivTestSummary";
import RejectionReport from "./RejectionReport";
import ReferredOut from "./ReferredOut";

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
        {notificationVisible === true && <AlertDialog />}
        {isLoading && <Loading />}
        {!isLoading && (
          <>
            {type === "patient" && report === "patientCILNSP_vreduit" && 
            (<PatientStatusReport />)}
            
            {type === "patient" && 
             report === "referredOut" && 
             (<ReferredOut />)}

            {type === "indicator" &&
              report === "statisticsReport" &&
              (<StatisticsReport />)}

            {type === "indicator" &&
              report === "indicatorHaitiLNSPAllTests" &&
              ( <SummaryOfAllTest /> )}

            {type === "indicator" &&
              report === "indicatorCDILNSPHIV" &&
              (<HIVTestSummary/>)}

              {type === "indicator" &&
              report === "sampleRejectionReport" &&
              (<RejectionReport/>)}

          </>
        )}
      </>
  );
};

export default injectIntl(RoutineIndex);
