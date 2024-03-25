import React, { useState } from 'react';
import { FormattedMessage, useIntl } from 'react-intl';
import { Form, FormLabel, Grid, Column, Section, Button } from "@carbon/react";
import CustomDatePicker from "../../common/CustomDatePicker";
import Loading from "../../common/Loading";
import AlertDialog from "../../common/AlertDialog";
import config from "../../../config.json";
import "../../Style.css";


const ActivityReportByTest = () => {
  const intl = useIntl();

  const [loading, setLoading] = useState(false);
  const [notificationVisible, setNotificationVisible] = useState(false);
  const [reportFormValues, setReportFormValues] = useState({
    startDate: null,
    endDate: null
  });

  const encodeDate = (dateString) => {
    if (typeof dateString === "string" && dateString.trim() !== "") {
      return dateString.split("/").map(encodeURIComponent).join("%2F");
    } else {
      return "";
    }
  };

  const handleDatePickerChangeDate = (datePicker, date) => {
    const updatedDate = encodeDate(date);
    setReportFormValues(prevState => ({
      ...prevState,
      [datePicker]: updatedDate
    }));
  };

  const handleSubmit = () => {
    setLoading(true);
    const baseParams = 'report=indicator&report=activityReportByTest';
    const baseUrl = `${config.serverBaseUrl}/ReportPrint`;
    const url = `${baseUrl}?${baseParams}&lowerDateRange=${reportFormValues.startDate}&upperDateRange=${reportFormValues.endDate}`;
    window.open(url, '_blank');
    setLoading(false);
    setNotificationVisible(true);
  };

  return (
    <>
      <FormLabel>
        <Section>
          <Section>
            <h1>
              <FormattedMessage id="openreports.activity.title"/>
            </h1>
          </Section>
        </Section>
      </FormLabel>
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
                <FormattedMessage id="label.button.generatePrintableVersion" defaultMessage="Generate printable version" />
              </Button>
            </Section>
          </Form>
        </Column>
      </Grid>
    </>
  );
};

export default ActivityReportByTest;