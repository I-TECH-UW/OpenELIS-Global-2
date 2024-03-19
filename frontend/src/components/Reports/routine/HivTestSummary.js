import React, { useEffect, useState } from "react";
import {
  Form,
  Grid,
  Column,
  Section,
  Button,
  Loading,
  Heading,
} from "@carbon/react";
import { FormattedMessage, useIntl } from "react-intl";
import "../../Style.css";
import { AlertDialog } from "../../common/CustomNotification";
import CustomDatePicker from "../../common/CustomDatePicker";
import config from "../../../config.json";
import PageBreadCrumb from "../../common/PageBreadCrumb";

const HivTestSummary = () => {
  const intl = useIntl();
  const [loading, setLoading] = useState(false);
  const [notificationVisible, setNotificationVisible] = useState(false);
  const [reportFormValues, setReportFormValues] = useState({
    startDate: null,
    endDate: null,
  });

  function encodeDate(dateString) {
    if (typeof dateString === "string" && dateString.trim() !== "") {
      return dateString.split("/").map(encodeURIComponent).join("%2F");
    } else {
      return "";
    }
  }

  const handleDatePickerChangeDate = (datePicker, date) => {
    let updatedDate = encodeDate(date);
    let obj = null;
    switch (datePicker) {
      case "startDate":
        obj = {
          ...reportFormValues,
          startDate: updatedDate,
        };
        break;
      case "endDate":
        obj = {
          ...reportFormValues,
          endDate: updatedDate,
        };
        break;
      default:
    }
    setReportFormValues(obj);
  };

  const handleSubmit = () => {
    setLoading(true);

    const baseParams = "report=indicatorCDILNSPHIV&type=indicator";

    const baseUrl = `${config.serverBaseUrl}/ReportPrint`;
    const url = `${baseUrl}?${baseParams}&upperDateRange=${reportFormValues.startDate}&lowerDateRange=${reportFormValues.endDate}`;

    window.open(url, "_blank");

    setLoading(false);
    setNotificationVisible(true);
  };

  const breadcrumbs = [
    { label: "home.label", link: "/" },
    { label: "routine.reports", link: "/RoutineReports" },
    {
      label: "openreports.hiv.aggregate",
      link: "/RoutineReport?type=indicator&report=indicatorCDILNSPHIV",
    },
  ];

  return (
    <>
      <br />
      <PageBreadCrumb breadcrumbs={breadcrumbs} />
      <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Section>
              <Heading>
                <FormattedMessage id="openreports.hiv.aggregate" />
              </Heading>
            </Section>
          </Section>
        </Column>
      </Grid>
      <div className="orderLegendBody">
        {notificationVisible && <AlertDialog />}
        {loading && <Loading />}
        <Grid fullWidth={true}>
          <Column lg={16}>
            <Form>
              <Grid fullWidth={true}>
                <Column lg={10}>
                  <Section>
                    <br />
                    <br />
                    <h5>
                      <FormattedMessage id="select.dateRange" />
                    </h5>
                  </Section>
                  <div className="inlineDiv">
                    <CustomDatePicker
                      id={"startDate"}
                      labelText={intl.formatMessage({
                        id: "eorder.date.start",
                        defaultMessage: "Start Date",
                      })}
                      autofillDate={true}
                      value={reportFormValues.startDate}
                      className="inputDate"
                      onChange={(date) =>
                        handleDatePickerChangeDate("startDate", date)
                      }
                    />
                    <CustomDatePicker
                      id={"endDate"}
                      labelText={intl.formatMessage({
                        id: "eorder.date.end",
                        defaultMessage: "End Date",
                      })}
                      className="inputDate"
                      autofillDate={true}
                      value={reportFormValues.endDate}
                      onChange={(date) =>
                        handleDatePickerChangeDate("endDate", date)
                      }
                    />
                  </div>
                </Column>
              </Grid>
              <br />
              <Section>
                <br />
                <Button type="button" onClick={handleSubmit}>
                  <FormattedMessage
                    id="label.button.generatePrintableVersion"
                    defaultMessage="Generate printable version"
                  />
                </Button>
              </Section>
            </Form>
          </Column>
        </Grid>
      </div>
    </>
  );
};

export default HivTestSummary;
