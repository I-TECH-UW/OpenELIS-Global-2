import React, { useContext, useState, useEffect, useRef } from "react";
import { useHistory } from "react-router-dom";
import CustomDatePicker from "../common/CustomDatePicker";
import { ConfigurationContext } from "../layout/Layout";
import {
  Select,
  SelectItem,
  Checkbox,
  Button,
  Grid,
  Column,
  Section,
  TimePicker,
  Loading,
  Heading,
  TextInput,
  FluidForm,
  Tag,
  Link,
  Accordion,
  AccordionItem,
} from "@carbon/react";
import { FormattedMessage, useIntl } from "react-intl";
import { NotificationContext } from "../layout/Layout";
import { AlertDialog } from "../common/CustomNotification";
import AutoComplete from "../common/AutoComplete";
import "../Style.css";
import PageBreadCrumb from "../common/PageBreadCrumb";
import {
  getFromOpenElisServer,
  postToOpenElisServerFullResponse,
} from "../utils/Utils";
import CustomLabNumberInput from "../common/CustomLabNumberInput";
import PatientInfo from "../addOrder/PatientInfo";


const SampleBatchEntry = (props) => {

  const { orderFormValues, setOrderFormValues } = props;
  const [notificationVisible, setNotificationVisible] = useState(false);
  const [status, setStatus] = useState("");
  const { configurationProperties } = useContext(ConfigurationContext);
  // const { notificationVisible } = useContext(NotificationContext);
  const intl = useIntl();
  const componentMounted = useRef(false);
  const history = useHistory();
  const [siteNames, setSiteNames] = useState([]);
  const [departments, setDepartments] = useState([]);
  const [facilityChecked, setFacilityChecked] = useState(false);
  const [patientChecked, setPatientChecked] = useState(false);
  const [selectedMethod, setSelectedMethod] = useState("");
  const [selectedForm, setSelectedForm] = useState("");
  const [innitialized, setInnitialized] = useState(false);
  const [loading, setLoading] = useState(false);
  const [showSampleComponent, setShowSampleComponent] = useState(false);
  const [generatedLabNos, setGeneratedLabNos] = useState([]);
  const [renderBarcode, setRenderBarcode] = useState(false);
  const [source, setSource] = useState("about:blank");

  let breadcrumbs = [{ label: "home.label", link: "/" }];

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/SamplePatientEntry", getSampleEntryPreform);
    window.scrollTo(0, 0);
    return () => {
      componentMounted.current = false;
    };
  }, []);

  useEffect(() => {
    getFromOpenElisServer(
      "/rest/departments-for-site?refferingSiteId=" +
        (orderFormValues.sampleOrderItems.referringSiteId || ""),
      loadDepartments,
    );
  }, [orderFormValues.referringSiteId]);

  const getSampleEntryPreform = (response) => {
    if (componentMounted.current) {
      setSiteNames(response.sampleOrderItems.referringSiteList);
    }
  };

  const loadDepartments = (data) => {
    setDepartments(data);
  };

  const handleLabNoValidation = () => {
    if (orderFormValues.sampleOrderItems.labNo !== "") {
      getFromOpenElisServer(
        "/rest/SampleEntryAccessionNumberValidation?ignoreYear=false&ignoreUsage=false&field=labNo&accessionNumber=" +
          orderFormValues.sampleOrderItems.labNo,
        accessionNumberValidationResults,
      );
    }
  };

  function handleLabNo(e, rawVal) {
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        labNo: rawVal ? rawVal : e?.target?.value,
      },
    });
    setNotificationVisible(false);
  }

  const handleKeyPress = (event) => {
    if (event.key === "Enter") {
      handleLabNoGeneration(event);
    }
  };
  function fetchGeneratedAccessionNo(res) {
    if (res.status) {
      setOrderFormValues({
        ...orderFormValues,
        sampleOrderItems: {
          ...orderFormValues.sampleOrderItems,
          labNo: res.body,
        },
      });
      setGeneratedLabNos((prevLabNos) => [...prevLabNos, res.body]);
      setNotificationVisible(false);
      post();
    }
  }

  function post(){
    const body = JSON.stringify(orderFormValues);
    postToOpenElisServerFullResponse(
      "/rest/SamplePatientEntry",
      body,displayStatus
    );
  }

 function displayStatus(res) {
    if (res.status === 200) {
      printLabelSets(orderFormValues.sampleOrderItems
        .labNo);

    } else {
      console.log("Response from server:", res);
      setStatus("error");
    }
  }

  const handleLabNoGeneration = (e) => {
    if (e) {
      e.preventDefault();
    }
    getFromOpenElisServer(
      "/rest/SampleEntryGenerateScanProvider",
      fetchGeneratedAccessionNo,
    );
  };

  const printLabelSets = (labNo) => {
    const params = new URLSearchParams({
      labNo: "",
    });
    const url = `/LabelMakerServlet?${params.toString()}`;
    setSource(url);
    setRenderBarcode(true);
  };

  const elementError = (path) => {
    if (errors?.errors?.length > 0) {
      let error = errors.inner?.find((e) => e.path === path);
      if (error) {
        return error.message;
      } else {
        return null;
      }
    }
  };

  return (
    <>
      {notificationVisible === true ? <AlertDialog /> : ""}
      {loading && <Loading description="Loading Dasboard..." />}
      <PageBreadCrumb breadcrumbs={breadcrumbs} />
      {!showSampleComponent && (
        <>
          <Grid fullWidth={true}>
            <Column lg={16} md={8} sm={4}>
              <Section>
                <Heading>
                  <FormattedMessage
                    id="order.entry.setup.batch"
                    defaultMessage="Batch Order Entry"
                  />
                </Heading>
              </Section>
            </Column>
          </Grid>
          <Grid fullWidth={true}>
            <Column lg={16} md={8} sm={4}>
              <div className="orderLegendBody">
                <h3>
                  {/* <FormattedMessage id="order.title" defaultMessage="Order" /> */}
                  Common Fields
                </h3>
                <Section>
                  <div className="inlineDiv">
                    <TextInput
                      labelText="Current Date"
                      value={orderFormValues.currentDate}
                      readOnly
                      id="current-date"
                      className="inputText"
                    />

                    <TextInput
                      labelText="Current Time"
                      value={orderFormValues.currentTime}
                      readOnly
                      id="current-time"
                      className="inputText"
                    />
                  </div>
                </Section>

                <Section>
                  <div className="inlineDiv">
                    <TextInput
                      labelText="Recieved Date"
                      value={
                        orderFormValues.sampleOrderItems.receivedDateForDisplay
                      }
                      readOnly
                      id="recieved-date"
                      className="inputText"
                    />

                    <TextInput
                      labelText="Recieved Time"
                      value={orderFormValues.sampleOrderItems.receivedTime}
                      readOnly
                      id="recieved-time"
                      className="inputText"
                    />
                  </div>
                </Section>
                <Section>
                  <div className="inlineDiv">
                    <h4 style={{ marginRight: "20px" }}>Sample Type</h4>
                    <Tag key="1" type="cyan">
                      {orderFormValues.sampleTypeSelect}
                    </Tag>
                  </div>
                  <div className="inlineDiv">
                    <h4 style={{ marginRight: "20px" }}>Test Name</h4>
                    {orderFormValues.testSectionList.map((test) => (
                      <Tag key={test.id} type="cyan">
                        {test.value}
                      </Tag>
                    ))}
                    
                  </div>
                </Section>
              </div>
              <PatientInfo
                orderFormValues={orderFormValues}
                setOrderFormValues={setOrderFormValues}
                error={elementError}
              />
              <div className="orderLegendBody">
                <h3>Generate Barcode and Save</h3>
                <div className="formInlineDiv">
                  <div className="inlineDiv">
                    <div className="inputText">
                      <CustomLabNumberInput
                        name="labNo"
                        placeholder={intl.formatMessage({
                          id: "input.placeholder.labNo",
                        })}
                        value={orderFormValues.sampleOrderItems.labNo}
                        onMouseLeave={handleLabNoValidation}
                        onChange={handleLabNo}
                        onKeyPress={handleKeyPress}
                        labelText={
                          <>
                            <FormattedMessage id="sample.label.labnumber" />{" "}
                            <span className="requiredlabel">*</span>
                          </>
                        }
                        id="labNo"
                        className="inputText"
                        // invalid={error("sampleOrderItems.labNo") ? true : false}
                        // invalidText={error("sampleOrderItems.labNo")}
                      />
                      <div className="inputText">
                        {/* <FormattedMessage id="label.order.scan.text" />{" "} */}
                        <Link
                          href="#"
                          onClick={(e) => handleLabNoGeneration(e)}
                        >
                          <p>Generate and save</p>
                          {/* <FormattedMessage id="sample.label.labnumber.generate" /> */}
                        </Link>
                      </div>
                    </div>
                    </div>
                    <Accordion >
                      <AccordionItem title="Previously used Accession Numbers">
                        {generatedLabNos.map((labNo, index) => (
                          <h6 key={index}> {labNo}</h6>
                        ))}
                      </AccordionItem>
                    </Accordion>
                  </div>
                {renderBarcode && (
                  <div className="orderLegendBody">
                    <Grid>
                      <Column lg={16} md={8} sm={4}>
                        <h4>
                          <FormattedMessage id="barcode.header" />
                        </h4>
                      </Column>
                    </Grid>
                    <iframe src={source} width="100%" height="500px" />
                  </div>
                )}
              </div>
            </Column>
          </Grid>
        </>
      )}
    </>
  );
};

export default SampleBatchEntry;