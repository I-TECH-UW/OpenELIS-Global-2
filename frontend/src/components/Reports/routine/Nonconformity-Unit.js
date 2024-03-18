import React, { useState } from "react";
import { Grid, Column, Section, Button, Form, FormLabel } from "@carbon/react";
import { FormattedMessage, useIntl } from "react-intl";
import CustomDatePicker from "../../common/CustomDatePicker";
import config from "../../../config.json";

const NonconformityReportsByUnit = () => {
  const intl = useIntl();
  const [reportFormValues, setReportFormValues] = useState({
    startDate: null,
    endDate: null,
  });

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
        obj = reportFormValues;
    }
    setReportFormValues(obj);
  };

  // Function to encode date
  const encodeDate = (dateString) => {
    if (typeof dateString === "string" && dateString.trim() !== "") {
      return dateString.split("/").map(encodeURIComponent).join("%2F");
    } else {
      return "";
    }
  };

  const handleSubmit = () => {
    const url =
      config.serverBaseUrl +
      `/rest/ReportPrint`;

    const requestBody = {
      report: "NonconformityByUnitAndReason",
      type: "nonconformity",
      lowerDateRange: reportFormValues.startDate,
      upperDateRange: reportFormValues.endDate
    };

    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(requestBody),
    })
      .then(response => {
        if (response.ok) {
          return response.blob();
        } else {
          throw new Error("Network response was not ok.");
        }
      })
      .then(blob => {
        const url = window.URL.createObjectURL(new Blob([blob]));
        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", "report.pdf");
        document.body.appendChild(link);
        link.click();
        link.parentNode.removeChild(link);
      })
      .catch(error => {
        console.error("Error generating report:", error);
        // Handle error (e.g., display error message)
      });
  };

  return (
    <>
      <FormLabel>
        <Section>
          <Section>
            <h1>
              <FormattedMessage id="reports.nonConformity.bySectionReason.title" />
            </h1>
          </Section>
        </Section>
      </FormLabel>

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
                      id: "label.startDate",
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
                      id: "label.endDate",
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
    </>
  );
};

export default NonconformityReportsByUnit;
