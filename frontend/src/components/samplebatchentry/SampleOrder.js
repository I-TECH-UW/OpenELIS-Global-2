import React, { useRef, useState, useContext, useEffect } from "react";
import { injectIntl, FormattedMessage, useIntl } from "react-intl";
import { getFromOpenElisServer } from "../utils/Utils";
import { Stack, Form, Grid, Column } from "@carbon/react";
import CustomDatePicker from "../common/CustomDatePicker";
import CustomTimePicker from "../common/CustomTimePicker";
import CustomSelect from "../common/CustomSelect";
import { NotificationContext, ConfigurationContext } from "../layout/Layout";

const SampleOrder = (props) => {
  const { index, sample } = props;
  const { configurationProperties } = useContext(ConfigurationContext);
  const intl = useIntl();
  const [study, setStudy] = useState("");
  const [studyDisabled, setStudyDisabled] = useState(false);
  const defaultSelect = { id: "", value: "routine" };
  const studyChanged = (res) => {
    const studyValue = res.target.value;
    setStudy(studyValue);

    if (studyValue === "routine") {
      document.getElementById("psuedoPatientInfo").removeAttribute("disabled");
      document.getElementById("facility-combobox").style.display = "block";
      document.getElementById("routineSampleAdd").style.display = "block";
      document.getElementById("viralLoadSampleAdd").style.display = "none";
      document.getElementById("EIDSampleAdd").style.display = "none";
    } else if (studyValue === "viralLoad") {
      document.getElementById("psuedoPatientInfo").checked = false;
      document
        .getElementById("psuedoPatientInfo")
        .setAttribute("disabled", "disabled");
      document.getElementsByName("patientInfoCheck")[0].disabled = true;
      document.getElementById("requesterId").selectedIndex = 0;
      document.getElementById("facility-combobox").style.display = "none";
      document.getElementById("routineSampleAdd").style.display = "none";
      document.getElementById("viralLoadSampleAdd").style.display = "block";
      document.getElementById("EIDSampleAdd").style.display = "none";
    } else if (studyValue === "EID") {
      document.getElementById("psuedoPatientInfo").checked = false;
      document
        .getElementById("psuedoPatientInfo")
        .setAttribute("disabled", "disabled");
      document.getElementsByName("patientInfoCheck")[0].disabled = true;
      document.getElementById("requesterId").selectedIndex = 0;
      document.getElementById("facility-combobox").style.display = "none";
      document.getElementById("routineSampleAdd").style.display = "none";
      document.getElementById("viralLoadSampleAdd").style.display = "none";
      document.getElementById("EIDSampleAdd").style.display = "block";
    }
  };

  const displayReferralReasonsOptions = (res) => {
    if (componentMounted.current) {
      setReferralReasons(res);
    }
  };

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/ReactSampleBatchEntrySetup", studyChanged);
    return () => {
      componentMounted.current = false;
    };
  }, []);

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer(
      "/rest/ReactSampleBatchEntrySetup",
      displayReferralReasonsOptions
    );
    return () => {
      componentMounted.current = false;
    };
  }, []);

  const [sampleXml, setSampleXml] = useState(
    sample?.sampleXML != null
      ? sample.sampleXML
      : {
          collectionDate:
            configurationProperties?.AUTOFILL_COLLECTION_DATE === "true"
              ? configurationProperties.currentDateAsText
              : "",
          collector: "",
          study: "",
          collectionTime:
            configurationProperties?.AUTOFILL_COLLECTION_DATE === "true"
              ? configurationProperties.currentTimeAsText
              : "",
        }
  );

  const componentMounted = useRef(false);

  function handleCollectionDate(date) {
    setSampleXml({
      ...sampleXml,
      collectionDate: date,
    });
  }
  function handleCollectionTime(time) {
    setSampleXml({
      ...sampleXml,
      collectionTime: time,
    });
  }
  function HandleStudy(value) {
    setSampleXml({
      ...sampleXml,
      study: value,
    });
  }
  return (
    <>
      <Stack gap={10}>
        <Column lg={16}>
          <h4>
            <FormattedMessage id="sidenav.label.order" />
          </h4>
        </Column>
        <div className="inlineDiv">
          <Column lg={4}>
            <CustomDatePicker
              id={"collectionDate_" + index}
              autofillDate={
                configurationProperties?.AUTOFILL_COLLECTION_DATE === "true"
              }
              onChange={(date) => handleCollectionDate(date)}
              value={sampleXml.collectionDate}
              labelText={intl.formatMessage({
                id: "sampleorder.label.currentDate",
              })}
              className="inputText"
              disallowFutureDate={true}
            />
          </Column>
          <Column lg={4}>
            <CustomTimePicker
              id={"collectionTime_" + index}
              autofillTime={
                configurationProperties?.AUTOFILL_COLLECTION_DATE === "true"
              }
              onChange={(time) => handleCollectionTime(time)}
              value={sampleXml.collectionTime}
              className="inputText"
              labelText={intl.formatMessage({
                id: "sampleorder.label.currentTime",
              })}
            />
          </Column>
        </div>

        <div className="inlineDiv">
          <Column lg={4}>
            <CustomDatePicker
              id={"collectionDate_" + index}
              autofillDate={
                configurationProperties?.AUTOFILL_COLLECTION_DATE === "true"
              }
              onChange={(date) => handleCollectionDate(date)}
              value={sampleXml.collectionDate}
              labelText={intl.formatMessage({
                id: "sampleorder.label.recievedDate",
              })}
              className="inputText"
              disallowFutureDate={true}
            />
          </Column>
          <Column lg={4}>
            <CustomTimePicker
              id={"collectionTime_" + index}
              autofillTime={
                configurationProperties?.AUTOFILL_COLLECTION_DATE === "true"
              }
              onChange={(time) => handleCollectionTime(time)}
              value={sampleXml.collectionTime}
              className="inputText"
              labelText={intl.formatMessage({
                id: "sampleorder.label.receptionTime",
              })}
            />
          </Column>
        </div>
        <div>
          <Column lg={2}>
            <div>
              <select value={study} onChange={studyChanged}>
                <option value="">Select Study</option>
                <option value="routine">Routine</option>
                <option value="viralLoad">Viral Load</option>
                <option value="EID">EID</option>
              </select>
            </div>
          </Column>
        </div>
      </Stack>
    </>
  );
};
export default SampleOrder;
