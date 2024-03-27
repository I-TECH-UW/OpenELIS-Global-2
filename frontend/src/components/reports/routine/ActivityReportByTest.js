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

  const handleChangeDatePicker = (datePicker, date) => {
    setReportFormValues(prevState => ({
      ...prevState,
      [datePicker]: date
    }));
  };

  const handleSubmit = async (reportType) => {
    setLoading(true);
    try {
      const baseParams = getBaseParams(reportType);
      const url = `${config.serverBaseUrl}/ReportPrint?${baseParams}`;
      window.open(url, "_blank");
      setNotificationVisible(true);
    } catch (error) {
      console.error("Error generating report:", error);
    }
    setLoading(false);
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
  const getBaseParams = (reportType) => {
    const { startDate, endDate } = reportFormValues;
    const formattedStartDate = startDate ? formatDate(startDate) : '';
    const formattedEndDate = endDate ? formatDate(endDate) : '';
    let baseParams = '';
    switch (reportType) {
      case "test":
        baseParams = `Routinereport=activityReportByTest&type=indicator&lowerDateRange=${formattedStartDate}&upperDateRange=${formattedEndDate}`;
        break;
      case "panel":
        baseParams = `RoutineReport?type=indicator&report=activityReportByPanel&lowerDateRange=${formattedStartDate}&upperDateRange=${formattedEndDate}`;
        break;
      case "unit":
        baseParams = `RoutineReport?type=indicator&report=activityReportByTestSection&lowerDateRange=${formattedStartDate}&upperDateRange=${formattedEndDate}`;
        break;
      default:
        throw new Error(`Invalid report type: ${reportType}`);
    }
    return baseParams;
  };

  const formatDate = (date) => {
    return date.toISOString().split('T')[0];
  };

  const fetchData = async () => {
    try {
      const [testData, panelData, unitData] = await Promise.all([
        getFromOpenElisServer("/rest/test-list"),
        getFromOpenElisServer("/rest/panels"),
        getFromOpenElisServer("/rest/test-sections")
      ]);
      setTestList(testData);
      setPanelList(panelData);
      setUnitList(unitData);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  useEffect(() => {
    mounted.current = true;
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
                  <div className="inlineDiv">
                    <CustomDatePicker
                      id={"startDate"}
                      labelText={intl.formatMessage({
                        id: "select.start.date.referredTests",
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
                        id: "select.end.date.referredTests",
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
                </Section>
              </Column>
            </Grid>
            <Column lg={6}>
              <Form className="container-form">
                <TestSelectForm testList={testList} value={handleSelectedValue} />
                <PanelSelectForm panelList={panelList} value={handleSetValue} />
                <TestSectionSelectForm unitList={unitList} value={handleSelectedValue} />
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
