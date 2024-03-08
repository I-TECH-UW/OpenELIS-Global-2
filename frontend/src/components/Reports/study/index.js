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
import ReportByLabNo from "./common/ReportByLabNo";
import GenericReport from "./common/GenericReport";


const StudyIndex = () => {
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
      window.location.href = "/StudyReports";
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
            {type === "patient" && report === "patientARVInitial1" &&   
             (<ReportByLabNo report="patientARVInitial1"  id="header.label.ARV"/>)}


             {type === "patient" && report === "patientARVInitial2" &&   
             (<ReportByLabNo report="patientARVInitial2"  id="header.label.ARV"/>)}


             {type === "patient" && report === "patientARVFollowup1" &&   
             (<ReportByLabNo report="patientARVFollowup1"  id="header.label.followup"/>)}


             {type === "patient" && report === "patientARVFollowup2" &&   
             (<ReportByLabNo report="patientARVFollowup2"  id="header.label.followup"/>)}


             {type === "patient" && report === "patientARV1" &&   
             (<ReportByLabNo report="patientARV1"  id="header.label.intialFollowup"/>)}


             {type === "patient" && report === "patientEID1" &&   
             (<GenericReport report="patientEID1"  id="header.label.EID"/>)}

             {type === "patient" && report === "patientEID2" &&   
             (<GenericReport report="patientEID2"  id="header.label.EID"/>)}


             {type === "patient" && report === "patientVL1" &&   
             (<GenericReport report="patientVL1"  id="banner.menu.resultvalidation.viralload"/>)}


             {type === "patient" && report === "patientIndeterminate1" &&   
             (<ReportByLabNo report="patientIndeterminate1"  id="project.IndeterminateStudy.name"/>)}

             {type === "patient" && report === "patientIndeterminate2" &&   
             (<ReportByLabNo report="patientIndeterminate2"  id="project.IndeterminateStudy.name"/>)}


             {type === "patient" && report === "patientSpecialReport" &&   
             (<ReportByLabNo report="patientSpecialReport"  id="header.label.specialRequest"/>)}


          </>
        )}
      </div>
    </>
  );
};

export default injectIntl(StudyIndex);