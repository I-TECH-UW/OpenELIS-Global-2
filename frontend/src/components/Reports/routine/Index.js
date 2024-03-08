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

const RoutineIndex = () => {
  const intl = useIntl();
  const { setNotificationVisible, addNotification, notificationVisible } =
    useContext(NotificationContext);

  const [type, setType] = useState("");
  const [report, setReport] = useState("");
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
            (<PatientStatusReport />)}

            {type === "indicator" &&
              report === "statisticsReport" &&
              (<StatisticsReport />)}

            {type === "indicator" &&
              report === "indicatorHaitiLNSPAllTests" &&
              ( <SummaryOfAllTest /> )}

            {type === "indicator" &&
              report === "indicatorCDILNSPHIV" &&
              (<HIVTestSummary/>)}
          </>
        )}
      </div>
    </>
  );
};

export default injectIntl(RoutineIndex);