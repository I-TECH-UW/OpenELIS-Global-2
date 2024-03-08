import React, { useState } from "react";
import { Grid, Column, Section, Button, Form, FormLabel } from "@carbon/react";
import { FormattedMessage, useIntl } from "react-intl";
import CustomDatePicker from "../../common/CustomDatePicker";
import config from "../../../config.json"

const ReferredOutTestSummary = () => {

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
    function encodeDate(dateString) {
      if (typeof dateString === "string" && dateString.trim() !== "") {
        return dateString.split("/").map(encodeURIComponent).join("%2F");
      } else {
        return "";
      }
    }
  
    const handleSubmit = () => {
      let url = config.serverBaseUrl + `/ReportPrint?report=CISampleRoutineExport&type=patient&lowerDateRange=${reportFormValues.startDate}&upperDateRange=${reportFormValues.endDate}`;
      window.open(url, "_blank");
    }
    return (
        <>
         <FormLabel>
        <Section>
          <Section>
            <h1>
            <FormattedMessage id="externalreports.title" />

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
    )
}

export default ReferredOutTestSummary;