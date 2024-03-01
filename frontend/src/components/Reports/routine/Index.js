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
import config from "../../../config.json";
import StatisticsReport from "./statisticsReport";
import SummaryOfAllTest from "./summaryOfAllTest";
import HIVTestSummary from "./hivTestSummary";

const RoutineIndex = () => {
  const intl = useIntl();
  const { setNotificationVisible, addNotification, notificationVisible } =
    useContext(NotificationContext);

  const [type, setType] = useState("");
  const [report, setReport] = useState("");
  const [source, setSource] = useState("");
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
      <Grid fullWidth={true}>
        <Column lg={16}>
          <Breadcrumb>
            <BreadcrumbItem href="/">
              {intl.formatMessage({ id: "home.label" })}
            </BreadcrumbItem>
          
          </Breadcrumb>
        </Column>
      </Grid>
      <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Section>
              <Heading>
                <FormattedMessage id="selectReportValues.title" />
              </Heading>
            </Section>
          </Section>
        </Column>
      </Grid>
      <div className="orderLegendBody">
        {notificationVisible === true && <AlertDialog />}
        {isLoading && <Loading />}
        {!isLoading && (
          <>
            {type === "patient" && report === "patientCILNSP_vreduit" &&   
            (window.location.href = `${config.serverBaseUrl}/Report?type=${type}&report=${report}`)}

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
              (window.location.href = `${config.serverBaseUrl}/Report?type=${type}&report=${report}`)}

            {type === "indicator" &&
              report === "activityReportByTest" &&
              (window.location.href = `${config.serverBaseUrl}/Report?type=${type}&report=${report}`)}

            {type === "indicator" &&
              report === "activityReportByPanel" &&
              (window.location.href = `${config.serverBaseUrl}/Report?type=${type}&report=${report}`)}

            {type === "indicator" &&
              report === "activityReportByTestSection" &&
              (window.location.href = `${config.serverBaseUrl}/Report?type=${type}&report=${report}`)}

            {type === "patient" &&
              report === "referredOut" &&
              (window.location.href = `${config.serverBaseUrl}/Report?type=${type}&report=${report}`)}
            {type === "patient" &&
              report === "haitiNonConformityByDate" &&
              (window.location.href = `${config.serverBaseUrl}/Report?type=${type}&report=${report}`)}

            {type === "patient" &&
              report === "CISampleRoutineExport" &&
              (window.location.href = `${config.serverBaseUrl}/Report?type=${type}&report=${report}`)}

            {type === "indicator" &&
              report === "validationBacklog" &&
              (window.location.href = `${config.serverBaseUrl}/ReportPrint?type=${type}&report=${report}`)}

          </>
        )}
      </div>
    </>
  );
};

export default injectIntl(RoutineIndex);