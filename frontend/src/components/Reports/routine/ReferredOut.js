import React, { useEffect, useState, useRef } from 'react';
import {
  Form,
  Heading,
  Select,
  SelectItem,
  Grid,
  Column,
  Section,
  Button,
  Loading,
} from "@carbon/react";
import { FormattedMessage, useIntl } from 'react-intl';
import "../../Style.css";
import { getFromOpenElisServer } from "../../utils/Utils";
import { AlertDialog } from "../../common/CustomNotification";
import CustomDatePicker from "../../common/CustomDatePicker";
import PageBreadCrumb from "../../common/PageBreadCrumb";
import config from "../../../config.json";

const ReferredOut = () => {
  const intl = useIntl();
  const [loading, setLoading] = useState(false);
  const [notificationVisible, setNotificationVisible] = useState(false);
  const [reportFormValues, setReportFormValues] = useState({ startDate: null, endDate: null });
  const componentMounted = useRef(false);
  const [locationCodes, setLocationCodes] = useState([]);
  const [selectedLocationCode, setSelectedLocationCode] = useState('');

  const handleSelectionChange = (event) => {
    const selectedValue = event.target.value;
    setSelectedLocationCode(selectedValue);
  };
  
  const fetchLocationCodes = (locationCodez) => {
    if (componentMounted.current) {
      setLocationCodes(locationCodez);
    }
  };

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/referral-organizations", fetchLocationCodes);
    return () => {
      componentMounted.current = false;
    };
  }, []);


  const breadcrumbs = [
    { label: "home.label", link: "/" },
    { label: "routine.reports", link: "/RoutineReports" },
    { label: "external.referrals.report", link: "/RoutineReport?type=patient&report=referredOut" },
  ];

  function encodeDate(dateString) {
    if (typeof dateString === "string" && dateString.trim() !== "") {
      return dateString.split("/").map(encodeURIComponent).join("%2F");
    } else {
      return "";
    }
  }

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

  const handlePrinting = () => {
    setLoading(true);
    const baseParams = 'report=referredOut&type=patient';
    const baseUrl = `${config.serverBaseUrl}/ReportPrint`;
    const url = `${baseUrl}?${baseParams}&upperDateRange=${reportFormValues.endDate}&lowerDateRange=${reportFormValues.startDate}&locationCode=${selectedLocationCode}`;

    const check = window.open(url, '_blank');
    if (check) {
      setLoading(false);
      setNotificationVisible(true);
    } else {
      setLoading(false);
      <AlertDialog />
    }
  };

  return (
    <>
      
        <Grid fullWidth={true}>
          <Column lg={16}>
            <Section>
              <Section>
                <Heading>
                  <FormattedMessage id="externalReferredOutTests.title" />
                </Heading>
              </Section>
            </Section>
          </Column>
        </Grid>
        <Grid fullWidth={true}>
          <Column lg={16}>
            <Form>
              <Grid fullWidth={true}>
                <Column lg={10}>
                  <Section>
                    <br />
                    <br />
                    <h5>
                      <FormattedMessage id="select.datarange.label" />
                    </h5>
                  </Section>
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
                        handleDatePickerChangeDate("startDate", date)
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
                        handleDatePickerChangeDate("endDate", date)
                      }
                    />
                  </div>
                </Column>
              </Grid>
              <Grid fullWidth={true}>
                <Column lg={10}>
                  <Section>
                    <br />
                    <br />
                    <h5>
                      <FormattedMessage id="select.referral.centre.is.required" />
                    </h5>
                  </Section>
                  {locationCodes.length > 0 && (
                    <div className="inputText">
                      <Select
                        id="locationcode"
                        value={selectedLocationCode}
                        onChange={handleSelectionChange}
                        labelText={intl.formatMessage({
                          id: "select.referral.centre",
                          defaultMessage: "Laboratory",
                        })}
                      >
                        <SelectItem value="" text="" />
                        {locationCodes.map((locationcode) => (
                          <SelectItem key={locationcode.id} value={locationcode.id} text={locationcode.value} />
                        ))}
                      </Select>
                    </div>)}
                </Column>
              </Grid>
              <br />
              <Section>
                <br />
                <Button type="button" onClick={handlePrinting}>
                  <FormattedMessage id="label.button.generatePrintableVersion" defaultMessage="Generate printable version" />
                </Button>
              </Section>
            </Form>
          </Column>
        </Grid>
    
    </>
  );
};

export default ReferredOut;
