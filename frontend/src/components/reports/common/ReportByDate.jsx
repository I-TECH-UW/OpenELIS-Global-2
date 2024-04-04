import { useState } from "react";
import {
  Form,
  FormLabel,
  Grid,
  Column,
  Section,
  Button,
  Loading,
} from "@carbon/react";
import { FormattedMessage, useIntl } from "react-intl";
import "../../Style.css";
import { AlertDialog } from "../../common/CustomNotification";
import CustomDatePicker from "../../common/CustomDatePicker";
import config from "../../../config.json";
import { encodeDate } from "../../utils/Utils";

const ReportByDate = (props) => {
  const intl = useIntl();
  const [loading, setLoading] = useState(false);
  const [notificationVisible, setNotificationVisible] = useState(false);

  const [reportFormValues, setReportFormValues] = useState({
    startDate: null,
    endDate: null,
    error: null,
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
    }
    setReportFormValues(obj);
  };

  const handleSubmit = () => {
    if (!reportFormValues.startDate || !reportFormValues.endDate) {
      setReportFormValues({
        ...reportFormValues,
        error: intl.formatMessage({
          id: "error.dateRange.start",
          defaultMessage: "Please select Start and end date.",
        }),
      });
      return;
    }

    setReportFormValues({
      ...reportFormValues,
      error: "",
    });

    setLoading(true);

    const baseParams = `report=${props.report}&type=patient`;
    const baseUrl = `${config.serverBaseUrl}/ReportPrint`;
    const url = `${baseUrl}?${baseParams}&upperDateRange=${reportFormValues.endDate}&lowerDateRange=${reportFormValues.startDate}`;

    window.open(url, "_blank");
    setLoading(false);
    setNotificationVisible(true);
  };

  return (
    <>
      <FormLabel>
        <Section>
          <Section>
            <h1>
              <FormattedMessage id={props.id} />
            </h1>
          </Section>
        </Section>
      </FormLabel>
      {notificationVisible && <AlertDialog />}
      {loading && <Loading />}
      <Grid fullWidth={true}>
        <Column lg={16} md={8} sm={4}>
          <Form>
            <Grid fullWidth={true}>
              <Column lg={10} md={8} sm={4}>
                <Section>
                  <br />
                  <br />
                  <h5>
                    <FormattedMessage id="label.select.dateRange" />
                  </h5>
                </Section>
                <div className="inlineDiv">
                  <CustomDatePicker
                    key="startDate"
                    id={"startDate"}
                    labelText={intl.formatMessage({
                      id: "eorder.date.start",
                      defaultMessage: "Start Date",
                    })}
                    disallowFutureDate={true}
                    autofillDate={true}
                    value={reportFormValues.startDate}
                    className="inputDate"
                    onChange={(date) =>
                      handleDatePickerChangeDate("startDate", date)
                    }
                  />
                  <CustomDatePicker
                    key="endDate"
                    id={"endDate"}
                    labelText={intl.formatMessage({
                      id: "eorder.date.end",
                      defaultMessage: "End Date",
                    })}
                    disallowFutureDate={true}
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
              {reportFormValues.error !== "" && (
                <div style={{ color: "#c62828", margin: 4 }}>
                  {reportFormValues.error}
                </div>
              )}

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

export default ReportByDate;
