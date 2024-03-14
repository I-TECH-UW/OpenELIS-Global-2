import React, { useEffect, useState, useRef } from 'react';
import { FormattedMessage, useIntl } from 'react-intl';
import { Form, FormLabel, Grid, Column, Section, Button, Loading } from "@carbon/react";
import CustomDatePicker from "../../common/CustomDatePicker";
import { AlertDialog } from "../../common/CustomNotification";
import config from "../../../config.json";
import TestSelectForm from "../../workplan/TestSelectForm";
import TestSectionSelectForm from "../../workplan/TestSectionSelectForm";
import "../../Style.css";
import { getFromOpenElisServer } from "../../utils/Utils";

const activityReportByUnit = () => {
  const intl = useIntl();
  const mounted = useRef(false);
  const [loading, setLoading] = useState(false);
  const [notificationVisible, setNotificationVisible] = useState(false);
  const [SelectedValue, setSelectedValue] = useState(false);
  const [reportFormValues, setReportFormValues] = useState({
    startDate: null,
    endDate: null
  });
  const [unitList, setUnitList] = useState([]);
  
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
    const baseParams = 'report=activityReportByPanel&type=indicator';
    const baseUrl = `${config.serverBaseUrl}/ReportPrint`;
    const url = `${baseUrl}?${baseParams}&upperDateRange=${reportFormValues.startDate}&lowerDateRange=${reportFormValues.endDate}`;
    window.open(url, '_blank');
    setLoading(false);
    setNotificationVisible(true);
  };

  const handleSelectedValue = (v) => {
    if (mounted.current) {
      setSelectedValue(v);
    }
  };

  useEffect(() => {
    mounted.current = true;
    const fetchUnitList = async () => {
      try {
        const data = getFromOpenElisServer('/rest/test-sections');
        console.log(data);
        setUnitList(data);
      } catch (error) {
        throw new Error('Error fetching units list:', error);
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
              <FormattedMessage id="Activity.report.By.unit"/>
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
                Unit type: <TestSectionSelectForm unitList={unitList} value={handleSelectedValue}/>
              </Form>
            </Column>
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

export default activityReportByUnit;
