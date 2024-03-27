import React, { useEffect, useState, useRef } from "react";
import { FormattedMessage, useIntl } from "react-intl";
import { Form, FormLabel, Grid, Column, Section, Button, Loading } from "@carbon/react";
import CustomDatePicker from "../../common/CustomDatePicker";
import { AlertDialog } from "../../common/CustomNotification";
import config from "../../../config.json";
import TestSelectForm from "../../workplan/TestSelectForm";
import PanelSelectForm from "../../workplan/PanelSelectForm";
import TestSectionSelectForm from "../../workplan/TestSectionSelectForm";
import "../../Style.css";
import { getFromOpenElisServer } from "../../utils/Utils";

const ActivityReport = () => {
  const intl = useIntl();
  const mounted = useRef(false);
  const [loading, setLoading] = useState(false);
  const [notificationVisible, setNotificationVisible] = useState(false);
  const [reportFormValues, setReportFormValues] = useState({
    startDate: null,
    endDate: null
  });
  const [testList, setTestList] = useState([]);
  const [panelList, setPanelList] = useState([]);
  const [unitList, setUnitList] = useState([]);
  const [selectedValue, setSelectedValue] = useState("");
  const [setValue, setSetValue] = useState("");

  const encodeDate = (dateString) => {
    if (typeof dateString === "string" && dateString.trim() !== "") {
      return dateString.split("/").map(encodeURIComponent).join("%2F");
    } else {
      return "";
    }
  };

  const handleChangeDatePicker = (datePicker, date) => {
    const updatedDate = encodeDate(date);
    setReportFormValues(prevState => ({
      ...prevState,
      [datePicker]: updatedDate
    }));
  };

  const handleSubmit = (reportType) => {
    setLoading(true);
    let baseParams = '';
    if (reportType === "test") {
      baseParams = "Routinereport=activityReportByTest&type=indicator";
    } else if (reportType === "panel") {
      baseParams = "RoutineReport?type=indicator&report=activityReportByPanel";
    } else if (reportType === "unit") {
      baseParams = "RoutineReport?type=indicator&report=activityReportByTestSection";
    }
    const baseUrl = `${config.serverBaseUrl}/ReportPrint`;
    const url = `${baseUrl}?${baseParams}&lowerDateRange=${reportFormValues.startDate}&upperDateRange=${reportFormValues.endDate}`;
    window.open(url, "_blank");
    setLoading(false);
    setNotificationVisible(true);
  };

  const handleSelectedValue = (v, l) => {
    if (mounted.current) {
      setSelectedValue(v);
    }
  };

  const handleSetValue = (v) => {
    if (mounted.current) {
      setSetValue(v);
    }
  };

  useEffect(() => {
    mounted.current = true;

    const fetchData = async () => {
      try {
        const testData = await getFromOpenElisServer("/rest/test-list");
        const panelData = await getFromOpenElisServer("/rest/panels");
        const unitData = await getFromOpenElisServer("/rest/test-sections");
        setTestList(testData);
        setPanelList(panelData);
        setUnitList(unitData);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();

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
                      handleChangeDatePicker("startDate", date)
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
                      handleChangeDatePicker("endDate", date)
                    }
                  />
                </div> 
              </Column>
            </Grid>
            <Column lg={6}>
              <Form className="container-form">
                Test type: <TestSelectForm testList={testList} value={handleSelectedValue}/>
                Panel type: <PanelSelectForm panelList={panelList} value={handleSetValue}/>
                Unit type: <TestSectionSelectForm unitList={unitList} value={handleSelectedValue}/>
              </Form>
            </Column>
            <br /> 
            <Section>
              <br />
              <Button type="button" onClick={() => handleSubmit("test")}>
                <FormattedMessage id="label.button.generateTestReport" defaultMessage="Generate Test Report" />
              </Button>
              <Button type="button" onClick={() => handleSubmit("panel")}>
                <FormattedMessage id="label.button.generatePanelReport" defaultMessage="Generate Panel Report" />
              </Button>
              <Button type="button" onClick={() => handleSubmit("unit")}>
                <FormattedMessage id="label.button.generatePrintableVersion" defaultMessage="Generate Test Section Report" />
              </Button>
            </Section>
          </Form>
        </Column>
      </Grid>
    </>
  );
};

export default ActivityReport;
