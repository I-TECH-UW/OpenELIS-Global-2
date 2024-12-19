import React, { useEffect, useState, useRef } from "react";
import { FormattedMessage, useIntl } from "react-intl";
import {
  Form,
  FormLabel,
  Grid,
  Column,
  Section,
  Button,
  Loading,
  Select,
  SelectItem,
  Row,
} from "@carbon/react";
import CustomDatePicker from "../../common/CustomDatePicker";
import { AlertDialog } from "../../common/CustomNotification";
import TestSelectForm from "../../workplan/TestSelectForm";
import TestSectionSelectForm from "../../workplan/TestSectionSelectForm";
import PanelSelectForm from "../../workplan/PanelSelectForm";
import "../../Style.css";
import { getFromOpenElisServer } from "../../utils/Utils";
import { encodeDate } from "../../utils/Utils";
import config from "../../../config.json";

const ActivityReport = ({ report }) => {
  const intl = useIntl();
  const [loading, setLoading] = useState(true);
  const [notificationVisible, setNotificationVisible] = useState(false);
  const [reportFormValues, setReportFormValues] = useState({
    startDate: null,
    endDate: null,
    value: null,
    error: null,
  });
  const [list, setList] = useState([]);

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
    let reportType = "";
    let additionalParams = "";
    switch (selectedReportType) {
      case "byTest":
        reportType = "activityReportByTest";
        additionalParams = "report=activityReportByTest";
        break;
      case "byPanel":
        reportType = "activityReportByPanel";
        additionalParams = "report=activityReportByPanel";
        break;
      case "byUnit":
        reportType = "activityReportByTestSection";
        additionalParams = "report=activityReportByTestSection";
        break;
      default:
        break;
    }
    const baseParams = `${additionalParams}&type=indicator&report=${reportType}`;
    const baseUrl = `${config.serverBaseUrl}/ReportPrint`;
    const url = `${baseUrl}?${baseParams}&lowerDateRange=${reportFormValues.startDate}&upperDateRange=${reportFormValues.endDate}`;
    window.open(url, "_blank");
    setLoading(false);
    setNotificationVisible(true);
  };

  const setDataList = (data) => {
    setList(data);
    console.log("data: ", data);
    setLoading(false);
  };

  useEffect(() => {
    switch (report) {
      case "activityReportByTest":
        getFromOpenElisServer("/rest/test-list", setDataList);
        console.log("list: ", list);
        break;
      case "activityReportByPanel":
        getFromOpenElisServer("/rest/panels", setDataList);
        break;
      case "activityReportByTestSection":
        getFromOpenElisServer("/rest/test-sections", setDataList);
        break;
      default:
        break;
    }
  }, [report]);

  return (
    <>
      <FormLabel>
        <Section>
          <Section>
            <h1>
              <FormattedMessage id={`sidenav.label.${report}`} />
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
                  <h5>
                    <FormattedMessage id="select date Range" />
                  </h5>
                </Section>
                <Grid>
                  <Column sm={4}>
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
                  </Column>
                  <Column sm={4}>
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
                  </Column>
                </Grid>
              </Column>
              <Row></Row>
              <Column log={15}>
                {list && list.length > 0 && (
                  <Select
                    id="type"
                    labelText={intl.formatMessage({
                      id: "label.form.searchby",
                    })}
                    value={reportFormValues.value}
                    onChange={(e) => {
                      setReportFormValues({
                        ...reportFormValues,
                        value: e.target.value,
                      });
                    }}
                  >
                    <SelectItem key={"emptyselect"} value={""} text={""} />
                    {list.map((statusOption) => (
                      <SelectItem
                        key={statusOption.id}
                        value={statusOption.id}
                        text={statusOption.value}
                      />
                    ))}
                  </Select>
                )}
              </Column>
            </Grid>

            <br />
            <Section>
              <br />
              <Button
                type="button"
                onClick={() =>
                  handleSubmit("activityReportByTest", "RoutineReport")
                }
              >
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

export default ActivityReport;
