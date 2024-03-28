import React, { useEffect, useState, useRef } from "react";
import { FormattedMessage, useIntl } from "react-intl";
import { Form, FormLabel, Grid, Column, Section, Button, Loading } from "@carbon/react";
import CustomDatePicker from "../../common/CustomDatePicker";
import { AlertDialog } from "../../common/CustomNotification";
import config from "../../../config.json";
import "../../Style.css";
import { getFromOpenElisServer } from "../../utils/Utils";
import PanelSelectForm from "../../workplan/PanelSelectForm";

const ActivityReportByPanel = () => {
  const intl = useIntl();
  const mounted = useRef(false);
  const [panels, setPanels] = useState([]);
  const [loading, setLoading] = useState(false);
  const [notificationVisible, setNotificationVisible] = useState(false);
  const [setValue, setSetValue] = useState("");
  const [reportFormValues, setReportFormValues] = useState({
    startDate: null,
    endDate: null
  });
  const [panelList, setPanelList] = useState([]);

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
    const baseParams = "report=activityReportByPanel&type=indicator";
    const baseUrl = `${config.serverBaseUrl}/ReportPrint`;
    const url = `${baseUrl}?${baseParams}&lowerDateRangeupperDateRange=${reportFormValues.startDate}&upperDateRange=${reportFormValues.endDate}`;
    window.open(url, '_blank');
    setLoading(false);
    setNotificationVisible(true);
  };

  const handleSetValue = (v) => {
    if (mounted.current) {
      setSetValue(v);
    }
  };

  useEffect(() => {
    mounted.current = true;
    const fetchPanelList = async () => {
      try {
        const data = await getFromOpenElisServer("/rest/panels");
        console.log("Panel list:", data); 
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

  return (
    <>
      <FormLabel>
        <Section>
          <Section>
            <h1>
              <FormattedMessage id="Activity report By panel"/>
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
                Panel type: <PanelSelectForm panelList={panelList} value={handleSetValue}/>
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

export default ActivityReportByPanel;