import React, { useEffect, useState, useRef } from "react";
import { FormattedMessage, useIntl } from "react-intl";
import { Form, FormLabel, Grid, Column, Section, Button, Loading } from "@carbon/react";
import CustomDatePicker from "../../common/CustomDatePicker";
import { AlertDialog } from "../../common/CustomNotification";
import TestSelectForm from "../../workplan/TestSelectForm";
import TestSectionSelectForm from "../../workplan/TestSectionSelectForm";
import PanelSelectForm from "../../workplan/PanelSelectForm";
import "../../Style.css";
import { getFromOpenElisServer } from "../../utils/Utils";
import { encodeDate } from "../../utils/Utils";
import config from "../../../config.json";

const ActivityReportByTest = () => {
  const intl = useIntl();
  const mounted = useRef(false);
  const [loading, setLoading] = useState(false);
  const [notificationVisible, setNotificationVisible] = useState(false);
  const [SelectedValue, setSelectedValue] = useState(false);
  const [reportFormValues, setReportFormValues] = useState({
    startDate: null,
    endDate: null
  });
  const [testList, setTestList] = useState([]);
  const [unitList, setUnitList] = useState([]);
  const [panelList, setPanelList] = useState([]);
  const [selectedReportType, setSelectedReportType] = useState("byTest");

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

  const handleReportTypeChange = (event) => {
    setSelectedReportType(event.target.value);
  };

  const handleSelectedValue = (v) => {
    if (mounted.current) {
      setSelectedValue(v);
    }
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

  useEffect(() => {
    mounted.current = true;
    const fetchTestList = async () => {
      try {
        const data = await getFromOpenElisServer("/rest/test-list");
        setTestList(data);
      } catch (error) {
        throw new Error("Error fetching test list:", error);
      }
    };

    fetchTestList();
    return () => {
      mounted.current = false;
    };
  }, []);

  useEffect(() => {
    mounted.current = true;
    const fetchPanelList = async () => {
      try {
        const data = await getFromOpenElisServer("/rest/panels");
        setPanelList(data);
      } catch (error) {
        throw new Error("Error fetching panel list:", error);
      }
    };

    fetchPanelList();

    return () => {
      mounted.current = false;
    };
  }, []);

  useEffect(() => {
    mounted.current = true;
    const fetchUnitList = async () => {
      try {
        const data = await getFromOpenElisServer('/rest/test-sections');
        setUnitList(data);
      } catch (error) {
        throw new Error("Error fetching units list:", error);
      }
    };

    fetchUnitList();

    return () => {
      mounted.current = false;
    };
  }, []);

  return (
    <>
      <FormLabel>
        <Section>
          <Section>
            <h1>
              <FormattedMessage id="sidenav.label.workplan.test"/>
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
            <Column lg={6}>
              <Form className="container-form">
                <label>
                  <FormattedMessage id="sidenav.label.workplan.selectReportType" />
                </label>
                <select value={selectedReportType} onChange={handleReportTypeChange}>
                  <option value="byTest">By Test</option>
                  <option value="byPanel">By Panel</option>
                  <option value="byUnit">By Unit</option>
                </select>
              </Form>
            </Column>
            {selectedReportType === "byTest" && (
              <Column lg={6}>
                <Form className="container-form">
                  Test type: <TestSelectForm testList={testList} value={handleSelectedValue}/>
                </Form>
              </Column>
            )}
            {selectedReportType === "byPanel" && (
              <Column lg={6}>
                <Form className="container-form">
                  Panel type: <PanelSelectForm panelList={panelList} value={handleSelectedValue}/>
                </Form>
              </Column>
            )}
            {selectedReportType === "byUnit" && (
              <Column lg={6}>
                <Form className="container-form">
                  Unit type: <TestSectionSelectForm unitList={unitList} value={handleSelectedValue}/>
                </Form>
              </Column>
            )}
            <br /> 
            <Section>
              <br />
              <Button type="button" onClick={() => handleSubmit("activityReportByTest", "RoutineReport")}>
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
