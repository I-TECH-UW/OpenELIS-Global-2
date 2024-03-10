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
import ReportByID from "./common/ReportByID";
import PageBreadCrumb from "../../common/PageBreadCrumb";

const StudyIndex = () => {
  const intl = useIntl();
  const { setNotificationVisible, addNotification, notificationVisible } = useContext(NotificationContext);

  const [type, setType] = useState("");
  const [report, setReport] = useState("");
  const [source, setSource] = useState("");
  const [isLoading, setIsLoading] = useState(true);
  const [breadcrumbs, setBreadcrumbs] = useState([]);
  const breadcrumbMap = {
    "patient_patientCollection": "patient.report.collection.name",
    "patient_patientAssociated": "patient.report.associated.name"
  };

  useEffect(() => {
    const params = new URLSearchParams(window.location.search);
    const paramType = params.get("type");
    const paramReport = params.get("report");

    setType(paramType);
    setReport(paramReport);

    // Updating breadcrumbs based on paramType and paramReport
    if (paramType && paramReport) {
      const breadcrumbId = breadcrumbMap[`${paramType}_${paramReport}`];
      if (breadcrumbId) {
        const breadcrumbLabel = intl.formatMessage({ id: breadcrumbId });
        setBreadcrumbs([
          { label: intl.formatMessage({ id: "home.label" }), link: "/" },
          { label: intl.formatMessage({ id: "label.study.Reports" }), link: "/StudyReports" },
          {
            label: breadcrumbLabel,
            link: `/StudyReport?type=${paramType}&report=${paramReport}`,
          },
        ]);
      }
      setIsLoading(false);
    } else {
      window.location.href = "/StudyReports";
    }
  }, [type, report]);

  return (
    <>
      <br />
      <PageBreadCrumb breadcrumbs={breadcrumbs} />
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
            {type === "patient" && report === "patientCollection" && (
              <ReportByID report="patientCollection" id="patient.report.collection.name" />
            )}
            {type === "patient" && report === "patientAssociated" && (
              <ReportByID report="patientAssociated" id="patient.report.associated.name" />
            )}
          </>
        )}
      </div>
    </>
  );
};

export default injectIntl(StudyIndex);
