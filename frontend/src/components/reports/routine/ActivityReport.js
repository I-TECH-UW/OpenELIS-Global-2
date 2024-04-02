import React, { useEffect, useState, useRef } from "react";
import { FormattedMessage, useIntl } from "react-intl";
import { Form, FormLabel, Grid, Column, Section, Button, Loading } from "@carbon/react";
import CustomDatePicker from "../../common/CustomDatePicker";
import { AlertDialog } from "../../common/CustomNotification";
import config from "../../../config.json";
import "../../Style.css";
import { getFromOpenElisServer } from "../../utils/Utils";
import PanelSelectForm from "../../workplan/PanelSelectForm";
import TestSelectForm from "../../workplan/TestSelectForm";
import TestSectionSelectForm from "../../workplan/TestSectionSelectForm";
import PropTypes from 'prop-types';

const ActivityReport = ({ reportType }) => {
  const intl = useIntl();
  const mounted = useRef(false);
  const [loading, setLoading] = useState(false);
  const [notificationVisible, setNotificationVisible] = useState(false);
  const [reportFormValues, setReportFormValues] = useState({
    startDate: null,
    endDate: null
  });
  const [dynamicComponent, setDynamicComponent] = useState(null);
  const [dynamicProps, setDynamicProps] = useState({});
  const [optionsList, setOptionsList] = useState([]);

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

  const handleSubmit = () => {
    setLoading(true);
    const baseUrl = `${config.serverBaseUrl}/ReportPrint`;
    const params = getReportParams(reportType);
    const url = `${baseUrl}?${params}`;
    window.open(url, '_blank');
    setLoading(false);
    setNotificationVisible(true);
  };

  ActivityReport.propTypes = {
    reportType: PropTypes.string.isRequired,
    optionsList: PropTypes.array.isRequired,
  };

  const getReportParams = (type) => {
    switch (type) {
      case 'panel':
        return `RoutineReport=indicator&report=activityReportByPanel&lowerDateRange=${reportFormValues.startDate}&upperDateRange=${reportFormValues.endDate}`;
      case 'test':
        return `RoutineReport?type=indicator&report=activityReportByTest&lowerDateRange=${reportFormValues.startDate}&upperDateRange=${reportFormValues.endDate}`;
      case 'testSection':
        return `RoutineReport?type=indicator&report=activityReportByTestSection&lowerDateRange=${reportFormValues.startDate}&=upperDateRange${reportFormValues.endDate}`;
      default:
        return '';
    }
  };

  useEffect(() => {
    mounted.current = true;
    const fetchOptionsList = async () => {
      try {
        let data;
        switch (reportType) {
          case 'panel':
            data = await getFromOpenElisServer("/rest/panels");
            setDynamicComponent(PanelSelectForm);
            break;
          case 'test':
            data = await getFromOpenElisServer("/rest/test-list");
            setDynamicComponent(TestSelectForm);
            break;
          case 'testSection':
            data = await getFromOpenElisServer('/rest/test-sections');
            setDynamicComponent(TestSectionSelectForm);
            break;
          default:
            data = [];
        }
        setOptionsList(data);
      } catch (error) {
        throw new Error(`Error fetching ${reportType} list:`, error);
      }
    };

    fetchOptionsList();

    return () => {
      mounted.current = false;
    };
  }, [reportType]);

  const handleDynamicValue = (value) => {
    if (mounted.current) {
      setDynamicProps({ value });
    }
  };

  return (
    <>
      <FormLabel>
        <Section>
          <Section>
            <h1>
              <FormattedMessage id={`Activity report By ${reportType}`} />
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
                {dynamicComponent && (
                  <dynamicComponent
                    {...dynamicProps}
                    optionsList={optionsList}
                    value={handleDynamicValue}
                  />
                )}
              </Form>
            </Column>
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

export default ActivityReport;
