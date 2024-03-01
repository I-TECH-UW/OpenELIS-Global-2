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
import { SampleOrderFormValues } from "../../formModel/innitialValues/OrderEntryFormValues";
// import PatientStatusReport from "./PatientStatusReport";
import Aggregate from "./aggregate";
import config from "../../../config.json";

const RoutineIndex = () => {
  const intl = useIntl();
  const { setNotificationVisible, addNotification, notificationVisible } =
    useContext(NotificationContext);

  const [type, setType] = useState("");
  const [report, setReport] = useState("");
  const [source, setSource] = useState("");
  const [isLoading, setIsLoading] = useState(true);
  const [orderFormValues, setOrderFormValues] = useState(SampleOrderFormValues);
  const [samples, setSamples] = useState([]);
  const [errors, setErrors] = useState([]);

  const elementError = (path) => {
    if (errors?.errors?.length > 0) {
      let error = errors.inner?.find((e) => e.path === path);
      if (error) {
        return error.message;
      } else {
        return null;
      }
    }
  };

  useEffect(() => {
    let sourceFromUrl = new URLSearchParams(window.location.search).get(
      "source"
    );
    let sources = ["PatientStatusReport"];
    sourceFromUrl = sources.includes(sourceFromUrl) ? sourceFromUrl : "";
    setSource(sourceFromUrl);

    const params = new URLSearchParams(window.location.search);
    const paramType = params.get("type");
    const paramReport = params.get("report");

    setType(paramType);
    setReport(paramReport);

    const path = window.location.pathname;
    const pathParts = path.split("/");
    const source = pathParts[pathParts.length - 1];

    if (source === "AuditTrailReport") {
      setIsLoading(false);
      return (window.location.href = `${config.serverBaseUrl}/${source}`);
    }

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
            {source && (
              <BreadcrumbItem href={`/${source}`}>
                {intl.formatMessage({
                  id: "banner.menu.reports",
                })}
              </BreadcrumbItem>
            )}
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
              (<Aggregate />)}

            {type === "indicator" &&
              report === "indicatorHaitiLNSPAllTests" &&
              (window.location.href = `${config.serverBaseUrl}/Report?type=${type}&report=${report}`)}

            {type === "indicator" &&
              report === "indicatorCDILNSPHIV" &&
              (window.location.href = `${config.serverBaseUrl}/Report?type=${type}&report=${report}`)}

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

            {type === "indicator" &&
              report === "indicatorHaitiLNSPAllTests" &&
              (window.location.href = `${config.serverBaseUrl}/Report?type=${type}&report=${report}`)}
          </>
        )}
      </div>
    </>
  );
};

export default injectIntl(RoutineIndex);