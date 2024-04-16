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
} from "@carbon/react";
import { FormattedMessage, useIntl } from "react-intl";
import SampleType from "./SampleType";
import BatchOrderEntryFormValues from "../formModel/innitialValues/BatchOrderEntryFormValues";
import { NotificationContext } from "../layout/Layout";
import { AlertDialog } from "../common/CustomNotification";
import AutoComplete from "../common/AutoComplete";
import "../Style.css";
import {
  getFromOpenElisServer,
  postToOpenElisServerFullResponse,
} from "../utils/Utils";
import PageBreadCrumb from "../common/PageBreadCrumb";
import SampleBatchEntry from "./SampleBatchEntry";

const SampleBatchEntrySetup = () => {
  const [orderFormValues, setOrderFormValues] = useState(
    BatchOrderEntryFormValues,
  );
  const [status, setStatus] = useState("");
  const { configurationProperties } = useContext(ConfigurationContext);
  const { notificationVisible } = useContext(NotificationContext);
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
  const [postRequestMade, setPostRequestMade] = useState(false);

  let breadcrumbs = [{ label: "home.label", link: "/" }];

  function handleFacilityCheckboxChange() {
    setFacilityChecked(!facilityChecked);
    setOrderFormValues({
      ...orderFormValues,
      facilityIDCheck: !facilityChecked,
    });
  }

  function handlePatientCheckboxChange() {
    setPatientChecked(!patientChecked);
    setOrderFormValues({
      ...orderFormValues,
      PatientInfoCheck: !patientChecked,
    });
  }

  function handleMethodChange(event) {
    const selectedOption = event.target.value;
    setSelectedMethod(selectedOption);
    setOrderFormValues({
      ...orderFormValues,
      method: selectedOption,
    });
  }

  function handleRequesterDept(e) {
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        referringSiteDepartmentId: e.target.value,
      },
    });
  }

  function handleSiteName(e) {
    setOrderFormValues({
      ...orderFormValues,
      facilityIDCheck: true,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        referringSiteName: e.target.value,
        referringSiteId: "",
      },
    });
    setFacilityChecked(true);
  }

  function handleDatePickerChange(datePicker, date) {
    setOrderFormValues((prevOrderFormValues) => {
      let updatedBatchOrderEntry = { ...prevOrderFormValues };
      switch (datePicker) {
        case "currentDate":
          updatedBatchOrderEntry = {
            ...updatedBatchOrderEntry,
            currentDate: date,
          };
          break;
        case "receivedDate":
          updatedBatchOrderEntry = {
            ...updatedBatchOrderEntry,
            sampleOrderItems: {
              ...updatedBatchOrderEntry.sampleOrderItems,
              receivedDateForDisplay: date,
            },
          };
          break;
        default:
          break;
      }
      return updatedBatchOrderEntry;
    });
  }

  useEffect(() => {
    if (!innitialized) {
      setOrderFormValues({
        ...orderFormValues,
        currentDate: configurationProperties.currentDateAsText,
        sampleOrderItems: {
          ...orderFormValues.sampleOrderItems,
          receivedDateForDisplay: configurationProperties.currentDateAsText,
          receivedTime: configurationProperties.currentTimeAsText,
        },
        currentTime: configurationProperties.currentTimeAsText,
      });
    }
    if (orderFormValues.currentDate != "") {
      setInnitialized(true);
    }
  }, [orderFormValues]);

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
  }, [orderFormValues.sampleOrderItems.referringSiteId]);

  const getSampleEntryPreform = (response) => {
    if (componentMounted.current) {
      setSiteNames(response.sampleOrderItems.referringSiteList);
    }
  };

  const loadDepartments = (data) => {
    setDepartments(data);
  };

  const updateFormValues = (updatedValues) => {
    setOrderFormValues({
      ...orderFormValues,
      testSectionList: updatedValues.selectedTests,
      panels: updatedValues.selectedPanels,
      sampleTypeSelect: updatedValues.sampleId,
    });
  };

  const handleCheckboxChange = (event) => {
    const { id, checked } = event.target;

    let updatedOrderFormValues = { ...orderFormValues };

    switch (selectedForm) {
      case "EID":
        updatedOrderFormValues.testSectionList = checked
          ? [{ value: "DNA PCR", id: "eid_dnaPCR" }]
          : [];
        break;
      case "viralLoad":
        updatedOrderFormValues.testSectionList = checked
          ? [{ id: "vl_viralLoadTest", value: "Viral Load Test" }]
          : [];
        break;
      default:
        break;
    }

    if (selectedForm === "EID") {
      switch (id) {
        case "eid_dryTubeTaken":
          updatedOrderFormValues._ProjectDataEID.dryTubeTaken = checked;
          break;
        case "eid_dbsTaken":
          updatedOrderFormValues._ProjectDataEID.dbsTaken = checked;
          break;
        case "eid_dnaPCR":
          updatedOrderFormValues._ProjectDataEID.dnaPCR = checked;
          break;
        default:
          break;
      }
    } else if (selectedForm === "viralLoad") {
      switch (id) {
        case "vl_dryTubeTaken":
          updatedOrderFormValues._ProjectDataVL.dryTubeTaken = checked;
          break;
        case "vl_edtaTubeTaken":
          updatedOrderFormValues._ProjectDataVL.edtaTubeTaken = checked;
          break;
        case "vl_dbsTaken":
          updatedOrderFormValues._ProjectDataVL.dbsTaken = checked;
          break;
        case "vl_viralLoadTest":
          updatedOrderFormValues._ProjectDataVL.viralLoadTest = checked;
          break;
        default:
          break;
      }
    }

    setOrderFormValues(updatedOrderFormValues);
  };

  function handleReceptionTime(e) {
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        receivedTime: e.target.value,
      },
    });
  }
  function handleCurrentTime(e) {
    setOrderFormValues({
      ...orderFormValues,
      currentTime: e.target.value,
    });
  }

  function handleAutoCompleteSiteName(siteId) {
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        referringSiteId: siteId,
      },
      facilityID: siteId,
    });
  }

  async function handleSubmitButton1() {
    try {
      const body = JSON.stringify(orderFormValues);
      const response = await postToOpenElisServerFullResponse(
        "/rest/SampleBatchEntry",
        body,
        displayStatus,
      );
    } catch (error) {
      console.error("Error occurred:", error);
    }
  }

  async function displayStatus(res) {
    if (res.status === 200) {
      setPostRequestMade(true);
    } else {
      console.log("Response from server:", res);
      setStatus("error");
    }
  }

  function handleFormChange(event) {
    const selectedForm = event.target.value;
    setSelectedForm(selectedForm);
  }

  return (
    <>
      {postRequestMade ? (
        <SampleBatchEntry
          orderFormValues={orderFormValues}
          setOrderFormValues={setOrderFormValues}
        />
      ) : (
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
                        id="order.entry.setup"
                        defaultMessage="Batch Order Entry Setup"
                      />
                    </Heading>
                  </Section>
                </Column>
              </Grid>
              <Grid fullWidth={true}>
                <Column lg={16} md={8} sm={4}>
                  <div className="orderLegendBody">
                    <h3>
                      <FormattedMessage
                        id="order.title"
                        defaultMessage="Order"
                      />
                    </h3>
                    <Section>
                      <div className="inlineDiv">
                        <CustomDatePicker
                          id={"order_currentDate"}
                          labelText={intl.formatMessage({
                            id: "sample.currentDate",
                            defaultMessage: "Current Date",
                          })}
                          autofillDate={true}
                          value={
                            orderFormValues.currentDate
                              ? orderFormValues.currentDate
                              : configurationProperties.currentDateAsText
                          }
                          disallowFutureDate={true}
                          onChange={(date) =>
                            handleDatePickerChange("currentDate", date)
                          }
                        />
                        <CustomDatePicker
                          id={"order_receivedDate"}
                          labelText={intl.formatMessage({
                            id: "sample.receivedDate",
                            defaultMessage: "Received Date",
                          })}
                          autofillDate={true}
                          value={
                            orderFormValues.sampleOrderItems
                              .receivedDateForDisplay
                              ? orderFormValues.sampleOrderItems
                                  .receivedDateForDisplay
                              : configurationProperties.currentDateAsText
                          }
                          disallowFutureDate={true}
                          onChange={(date) =>
                            handleDatePickerChange("receivedDate", date)
                          }
                        />
                      </div>
                    </Section>
                    <Section>
                      <div className="inlineDiv">
                        <TimePicker
                          id="order_CurrentTime"
                          labelText={intl.formatMessage({
                            id: "order.current.time",
                            defaultMessage: "Current Time",
                          })}
                          onChange={handleCurrentTime}
                          value={
                            orderFormValues.currentTime
                              ? orderFormValues.currentTime
                              : configurationProperties.currentTimeAsText
                          }
                        />
                        <TimePicker
                          id="order_ReceptionTime"
                          labelText={intl.formatMessage({
                            id: "order.reception.time",
                            defaultMessage: "Reception Time",
                          })}
                          onChange={handleReceptionTime}
                          value={
                            orderFormValues.sampleOrderItems.receivedTime
                              ? orderFormValues.sampleOrderItems.receivedTime
                              : configurationProperties.currentTimeAsText
                          }
                        />
                      </div>
                    </Section>
                    <Section>
                      <Select
                        className="inputText"
                        id="form-dropdown"
                        labelText={
                          <>
                            <FormattedMessage id="order.form.label" />
                            <span className="requiredlabel">*</span>
                          </>
                        }
                        onChange={handleFormChange}
                        defaultValue=""
                      >
                        <SelectItem
                          value=""
                          text={intl.formatMessage({ id: "order.form.select" })}
                        />
                        <SelectItem
                          value="routine"
                          text={intl.formatMessage({
                            id: "banner.menu.resultvalidation_routine",
                          })}
                        />
                        <SelectItem
                          value="EID"
                          text={intl.formatMessage({
                            id: "project.EIDStudy.name",
                          })}
                        />
                        <SelectItem
                          value="viralLoad"
                          text={intl.formatMessage({
                            id: "banner.menu.resultvalidation.viralload",
                          })}
                        />
                      </Select>
                    </Section>
                  </div>
                  <div>
                    {selectedForm === "routine" && (
                      <>
                        <SampleType updateFormValues={updateFormValues} />
                      </>
                    )}{" "}
                    {selectedForm == "EID" && selectedForm && (
                      <div className="orderLegendBody">
                        <h3>
                          <FormattedMessage id="order.legend.sample" />
                        </h3>
                        <Section>
                          <h3>
                            <FormattedMessage id="order.legend.specimen.collected" />
                          </h3>
                          <Checkbox
                            labelText={
                              <FormattedMessage id="order.legend.dryTube" />
                            }
                            id="eid_dryTubeTaken"
                            checked={
                              orderFormValues._ProjectDataEID.dryTubeTaken
                            }
                            onChange={handleCheckboxChange}
                          />
                          <Checkbox
                            labelText={
                              <FormattedMessage id="order.legend.dryBloodSpot" />
                            }
                            id="eid_dbsTaken"
                            checked={orderFormValues._ProjectDataEID.dbsTaken}
                            onChange={handleCheckboxChange}
                          />
                          <h3>
                            <FormattedMessage id="order.legend.tests" />
                          </h3>
                          <Checkbox
                            labelText={
                              <FormattedMessage id="banner.menu.resultvalidation.dnapcr" />
                            }
                            id="eid_dnaPCR"
                            checked={orderFormValues._ProjectDataEID.dnaPCR}
                            onChange={handleCheckboxChange}
                          />
                        </Section>
                      </div>
                    )}{" "}
                    {selectedForm == "viralLoad" && selectedForm && (
                      <div className="orderLegendBody">
                        <h3>
                          <FormattedMessage id="order.legend.sample" />
                        </h3>
                        <Section>
                          <h3>
                            <FormattedMessage id="order.legend.specimen.collected" />
                          </h3>
                          <Checkbox
                            labelText={
                              <FormattedMessage id="order.legend.dryTube" />
                            }
                            id="vl_dryTubeTaken"
                            checked={
                              orderFormValues._ProjectDataVL.dryTubeTaken
                            }
                            onChange={handleCheckboxChange}
                          />
                          <Checkbox
                            labelText={
                              <FormattedMessage id="order.legend.EDTATube" />
                            }
                            id="vl_edtaTubeTaken"
                            checked={
                              orderFormValues._ProjectDataVL.edtaTubeTaken
                            }
                            onChange={handleCheckboxChange}
                          />
                          <Checkbox
                            labelText={
                              <FormattedMessage id="order.legend.dryBloodSpot" />
                            }
                            id="vl_dbsTaken"
                            checked={orderFormValues._ProjectDataVL.dbsTaken}
                            onChange={handleCheckboxChange}
                          />
                          <h3>
                            <FormattedMessage id="order.legend.tests" />
                          </h3>
                          <Checkbox
                            labelText={
                              <FormattedMessage id="order.legend.viralLoadTest" />
                            }
                            id="vl_viralLoadTest"
                            checked={
                              orderFormValues._ProjectDataVL.viralLoadTest
                            }
                            onChange={handleCheckboxChange}
                          />
                        </Section>
                      </div>
                    )}
                  </div>
                  <div className="orderLegendBody">
                    <h3>
                      <FormattedMessage id="order.legend.configureBarcode" />
                    </h3>
                    <Section>
                      <Select
                        className="inputText"
                        id="method-dropdown"
                        labelText={
                          <FormattedMessage id="referral.label.testmethod" />
                        }
                        onChange={handleMethodChange}
                        defaultValue=""
                      >
                        <SelectItem
                          value=""
                          text={intl.formatMessage({
                            id: "order.legend.selectMethod",
                          })}
                        />
                        <SelectItem
                          value="On Demand"
                          text={intl.formatMessage({
                            id: "order.legend.onDemand",
                          })}
                        />
                        <SelectItem
                          value="Pre-Printed"
                          text={intl.formatMessage({
                            id: "order.legend.preDemand",
                          })}
                        />
                      </Select>
                    </Section>
                    <Section>
                      <p>
                        <FormattedMessage id="order.legend.optionalFields" />
                      </p>
                      <div className="inlineDiv">
                        <Checkbox
                          labelText={
                            <FormattedMessage id="order.legend.facility" />
                          }
                          id="facility-checkbox"
                          checked={facilityChecked}
                          onChange={handleFacilityCheckboxChange}
                        />
                        <Checkbox
                          labelText={
                            <FormattedMessage
                              id="order.legend.patient1"
                              defaultMessage={"Patient Info"}
                            />
                          }
                          id="patient-checkbox"
                          checked={patientChecked}
                          onChange={handlePatientCheckboxChange}
                        />
                      </div>
                    </Section>
                    <Section>
                      <div className="inlineDiv">
                        <AutoComplete
                          name="siteName"
                          id="siteName"
                          className="inputText"
                          allowFreeText={
                            !(
                              configurationProperties.restrictFreeTextRefSiteEntry ===
                              "true"
                            )
                          }
                          value={
                            orderFormValues.sampleOrderItems.referringSiteId !=
                            ""
                              ? orderFormValues.sampleOrderItems.referringSiteId
                              : orderFormValues.sampleOrderItems
                                  .referringSiteName
                          }
                          onChange={handleSiteName}
                          onSelect={handleAutoCompleteSiteName}
                          label={
                            <FormattedMessage id="order.legend.siteName" />
                          }
                          class="inputText"
                          style={{ width: "!important 100%" }}
                          suggestions={siteNames.length > 0 ? siteNames : []}
                        />

                        <Select
                          className="inputText"
                          id="requesterDepartmentId"
                          name="requesterDepartmentId"
                          labelText={
                            <FormattedMessage id="sample.label.dept" />
                          }
                          onChange={handleRequesterDept}
                        >
                          <SelectItem value="" text="" />
                          {departments.map((department, index) => (
                            <SelectItem key={index} text={department.value} />
                          ))}
                        </Select>
                      </div>
                    </Section>
                    <Section>
                      <div className="inlineDiv">
                        <Button
                          onClick={handleSubmitButton1}
                          disabled={
                            (!orderFormValues.testSectionList &&
                              !orderFormValues.panels.id) ||
                            !orderFormValues.method
                          }
                        >
                          <FormattedMessage id="next.action.button" />
                        </Button>

                        <Button
                          onClick={() => history.push("/")}
                          kind="secondary"
                        >
                          <FormattedMessage id="label.button.cancel" />
                        </Button>
                      </div>
                    </Section>
                  </div>
                </Column>
              </Grid>
            </>
          )}
        </>
      )}
    </>
  );
};

export default SampleBatchEntrySetup;
