import React, { useContext, useEffect, useRef, useState } from "react";
import {
  Checkbox,
  Link,
  Select,
  SelectItem,
  Stack,
  TextInput,
  TimePicker,
} from "@carbon/react";
import CustomLabNumberInput from "../common/CustomLabNumberInput";
import CustomDatePicker from "../common/CustomDatePicker";
import { getFromOpenElisServer } from "../utils/Utils";
import { NotificationContext } from "../layout/Layout";
import { priorities } from "../data/orderOptions";
import { NotificationKinds } from "../common/CustomNotification";
import AutoComplete from "../common/AutoComplete";
import OrderResultReporting from "../addOrder/OrderResultReporting";
import { FormattedMessage } from "react-intl";
import { ConfigurationContext } from "../layout/Layout";
const AddOrder = (props) => {
  const { orderFormValues, setOrderFormValues, samples } = props;
  const componentMounted = useRef(true);
  const [otherSamplingVisible, setOtherSamplingVisible] = useState(false);
  const [providers, setProviders] = useState([]);
  const [paymentOptions, setPaymentOptions] = useState([]);
  const [samplingPerformed, setSamplingPerformed] = useState([]);
  const [allowSiteNameOptions, setAllowSiteNameOptions] = useState("false");
  const [allowRequesterOptions, setAllowRequesterOptions] = useState("false");
  const { setNotificationVisible, setNotificationBody } =
    useContext(NotificationContext);
  const [siteNames, setSiteNames] = useState([]);
  const [innitialized, setInnitialized] = useState(false);
  const [phoneFormat, setPhoneFormat] = useState("");
  const { configurationProperties } = useContext(ConfigurationContext);
  const [departments, setDepartments] = useState([]);

  const handleDatePickerChange = (datePicker, date) => {
    let obj = null;
    switch (datePicker) {
      case "requestDate":
        obj = { ...orderFormValues.sampleOrderItems, requestDate: date };
        break;
      case "receivedDate":
        obj = {
          ...orderFormValues.sampleOrderItems,
          receivedDateForDisplay: date,
        };
        break;
      case "nextVisitDate":
        obj = { ...orderFormValues.sampleOrderItems, nextVisitDate: date };
        break;
      default:
    }
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: obj,
    });
  };

  function handlePaymentStatus(e) {
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        paymentOptionSelection: e.target.value,
      },
    });
  }

  function handleBillReferenceNo(e) {
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        billingReferenceNumber: e.target.value,
      },
    });
  }

  function handleRequesterFax(e) {
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        providerFax: e.target.value,
      },
    });
  }

  function handleRequesterEmail(e) {
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        providerEmail: e.target.value,
      },
    });
  }

  function handleRequesterWorkPhone(e) {
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        providerWorkPhone: e.target.value,
      },
    });
    setNotificationVisible(false);
  }

  function handleRequesterFirstName(e) {
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        providerFirstName: e.target.value,
      },
    });
  }

  function handleRequesterLastName(e) {
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        providerLastName: e.target.value,
      },
    });
  }

  const handleSamplingPerformed = (e) => {
    const { value } = e.target;
    if (value === "1310") {
      setOtherSamplingVisible(!otherSamplingVisible);
    } else {
      setOtherSamplingVisible(false);
    }
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        testLocationCode: value,
      },
    });
  };

  function handleOtherLocationCode(e) {
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        otherLocationCode: e.target.value,
      },
    });
  }

  function handleReceivedTime(e) {
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        receivedTime: e.target.value,
      },
    });
  }

  const handleLabNoGeneration = (e) => {
    e.preventDefault();
    getFromOpenElisServer(
      "/rest/SampleEntryGenerateScanProvider",
      fetchGeneratedAccessionNo,
    );
  };

  function accessionNumberValidationResults(res) {
    if (res.status === false) {
      setNotificationVisible(true);
      setNotificationBody({
        kind: NotificationKinds.error,
        title: <FormattedMessage id="notification.title" />,
        message: res.body,
      });
    }
  }

  function handleProviderSelectOptions(providerId) {
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        providerId: providerId,
      },
    });

    getFromOpenElisServer(
      "/rest/practitioner?providerId=" + providerId,
      fetchPractitioner,
    );
  }

  function fetchPractitioner(data) {
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        providerFirstName: data.person.firstName,
        providerLastName: data.person.lastName,
        providerWorkPhone: data.person.workPhone,
        providerEmail: data.person.email,
        providerFax: data.person.fax,
      },
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
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        referringSiteName: e.target.value,
      },
    });
  }

  function handleAutoCompleteSiteName(siteId) {
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        referringSiteId: siteId,
      },
    });
    getFromOpenElisServer(
      "/rest/departments-for-site?refferingSiteId=" + siteId,
      loadDepartments,
    );
  }
  const loadDepartments = (data) => {
    setDepartments(data);
  };

  function handleLabNo(e) {
    setOrderFormValues({
      ...orderFormValues,
      newAccessionNumber: e.target.value,
    });
    setNotificationVisible(false);
  }

  const handleLabNoValidation = () => {
    if (orderFormValues.sampleOrderItems.labNo !== "") {
      getFromOpenElisServer(
        "/rest/SampleEntryAccessionNumberValidation?ignoreYear=false&ignoreUsage=false&field=labNo&accessionNumber=" +
          orderFormValues.sampleOrderItems.labNo,
        accessionNumberValidationResults,
      );
    }
  };

  function fetchPhoneNoValidation(res) {
    if (res.status === false) {
      setNotificationBody({
        title: <FormattedMessage id="notification.title" />,
        message: res.body,
        kind: NotificationKinds.error,
      });
      setNotificationVisible(true);
    }
  }

  const handlePhoneNoValidation = () => {
    if (orderFormValues.sampleOrderItems.providerWorkPhone !== "") {
      const providerPhoneNo =
        orderFormValues.sampleOrderItems.providerWorkPhone.replace(
          /\+/g,
          "%2B",
        );
      getFromOpenElisServer(
        "/rest/PhoneNumberValidationProvider?fieldId=providerWorkPhoneID&value=" +
          providerPhoneNo,
        fetchPhoneNoValidation,
      );
    }
  };

  function handleRememberCheckBox(e) {
    let checked = false;
    if (e.currentTarget.checked) {
      checked = true;
    }
    setOrderFormValues({
      ...orderFormValues,
      rememberSiteAndRequester: checked,
    });
  }

  useEffect(() => {
    if (!innitialized) {
      setPhoneFormat(configurationProperties.phoneFormat);
      setOrderFormValues({
        ...orderFormValues,
        sampleOrderItems: {
          ...orderFormValues.sampleOrderItems,
          requestDate: configurationProperties.currentDateAsText,
          receivedDateForDisplay: configurationProperties.currentDateAsText,
          nextVisitDate: configurationProperties.currentDateAsText,
          receivedTime: configurationProperties.currentTimeAsText,
        },
      });
      setAllowSiteNameOptions(
        configurationProperties.restrictFreeTextRefSiteEntry,
      );
      setAllowRequesterOptions(
        configurationProperties.restrictFreeTextProviderEntry,
      );
    }
    if (orderFormValues.sampleOrderItems.requestDate != "") {
      setInnitialized(true);
    }
  }, [orderFormValues]);

  function handlePriority(e) {
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        priority: e.target.value,
      },
    });
  }

  function fetchGeneratedAccessionNo(res) {
    if (res.status) {
      setOrderFormValues({
        ...orderFormValues,
        newAccessionNumber: res.body,
      });
      setNotificationVisible(false);
    }
  }

  const reportingNotifications = (object) => {
    setOrderFormValues({
      ...orderFormValues,
      customNotificationLogic: true,
      patientSMSNotificationTestIds: object.patientSMSNotificationTestIds,
      patientEmailNotificationTestIds: object.patientEmailNotificationTestIds,
      providerSMSNotificationTestIds: object.providerSMSNotificationTestIds,
      providerEmailNotificationTestIds: object.providerEmailNotificationTestIds,
    });
  };

  const getSampleEntryPreform = (response) => {
    if (componentMounted.current) {
      setSiteNames(response.sampleOrderItems.referringSiteList);
      setPaymentOptions(response.sampleOrderItems.paymentOptions);
      setSamplingPerformed(response.sampleOrderItems.testLocationCodeList);
      setProviders(response.sampleOrderItems.providersList);
    }
  };

  const handleKeyPress = (event) => {
    if (event.key === "Enter") {
      handleLabNoGeneration(event);
    }
  };

  useEffect(() => {
    getFromOpenElisServer("/rest/SamplePatientEntry", getSampleEntryPreform);
    window.scrollTo(0, 0);
    return () => {
      componentMounted.current = false;
    };
  }, []);

  return (
    <>
      <Stack gap={10}>
        <div className="orderLegendBody">
          <h3>
            <FormattedMessage id="order.title" />
          </h3>
          <h5>
            {" "}
            <FormattedMessage id="sample.label.labnumber" />:{" "}
            {orderFormValues.accessionNumber}
          </h5>
          <div className="formInlineDiv">
            <div className="inputText">
              <CustomLabNumberInput
                name="labNo"
                value={orderFormValues.newAccessionNumber}
                onMouseLeave={handleLabNoValidation}
                onChange={handleLabNo}
                onKeyPress={handleKeyPress}
                labelText={<FormattedMessage id="sample.label.labnumber.new" />}
                id="labNo"
                className="inputText"
              />
              <div className="inputText">
                <FormattedMessage id="label.order.scan.text" />{" "}
                <Link href="#" onClick={(e) => handleLabNoGeneration(e)}>
                  <FormattedMessage id="sample.label.labnumber.generate" />
                </Link>
              </div>
            </div>
            <Select
              className="inputText"
              id="priorityId"
              name="priority"
              labelText={<FormattedMessage id="workplan.priority.list" />}
              value={orderFormValues.sampleOrderItems.priority}
              onChange={handlePriority}
              required
            >
              {priorities.map((priority, index) => {
                return (
                  <SelectItem
                    key={index}
                    text={priority.label}
                    value={priority.value}
                  />
                );
              })}
            </Select>
          </div>
          <div className="inlineDiv">
            <CustomDatePicker
              id={"order_requestDate"}
              labelText={<FormattedMessage id="sample.requestDate" />}
              autofillDate={true}
              value={orderFormValues.sampleOrderItems.requestDate}
              className="inputDate"
              onChange={(date) => handleDatePickerChange("requestDate", date)}
            />

            <CustomDatePicker
              id={"order_receivedDate"}
              labelText={<FormattedMessage id="sample.receivedDate" />}
              className="inputDate"
              autofillDate={true}
              value={orderFormValues.sampleOrderItems.receivedDateForDisplay}
              onChange={(date) => handleDatePickerChange("receivedDate", date)}
            />
          </div>
          <div className="inlineDiv">
            <TimePicker
              id="order_receivedTime"
              className="inputTime"
              labelText={<FormattedMessage id="order.reception.time" />}
              onChange={handleReceivedTime}
              value={
                orderFormValues.sampleOrderItems.receivedTime
                  ? orderFormValues.sampleOrderItems.receivedTime
                  : configurationProperties.receivedTime
              }
            />

            <CustomDatePicker
              id={"order_nextVisitDate"}
              className="inputDate"
              labelText={<FormattedMessage id="sample.entry.nextVisit.date" />}
              value={orderFormValues.sampleOrderItems.nextVisitDate}
              autofillDate={false}
              onChange={(date) => handleDatePickerChange("nextVisitDate", date)}
            />
          </div>
          <div className="inlineDiv">
            {allowSiteNameOptions === "false" ? (
              <TextInput
                name="siteName"
                labelText={<FormattedMessage id="order.site.name" />}
                onChange={handleSiteName}
                value={
                  orderFormValues.sampleOrderItems.referringSiteName == null
                    ? ""
                    : orderFormValues.sampleOrderItems.referringSiteName
                }
                id="siteName"
                className="inputText"
              />
            ) : (
              <AutoComplete
                name="siteName"
                id="siteName"
                className="inputText"
                onSelect={handleAutoCompleteSiteName}
                label={<FormattedMessage id="order.search.site.name" />}
                class="inputText"
                invalidText={<FormattedMessage id="order.invalid.site.name" />}
                style={{ width: "!important 100%" }}
                suggestions={siteNames.length > 0 ? siteNames : []}
                required
              />
            )}

            <Select
              className="inputText"
              id="requesterDepartmentId"
              name="requesterDepartmentId"
              labelText={<FormattedMessage id="order.department.label" />}
              onChange={handleRequesterDept}
              required
            >
              <SelectItem value="" text="" />
              {departments.map((department, index) => (
                <SelectItem
                  key={index}
                  text={department.value}
                  value={department.id}
                />
              ))}
            </Select>
          </div>
          {allowRequesterOptions === "false" ? (
            ""
          ) : (
            <div className="inlineDiv">
              <AutoComplete
                name="requesterId"
                id="requesterId"
                onSelect={handleProviderSelectOptions}
                label={<FormattedMessage id="order.search.requester.label" />}
                class="inputText"
                style={{ width: "!important 100%" }}
                invalidText={
                  <FormattedMessage id="order.invalid.requester.name.label" />
                }
                suggestions={providers.length > 0 ? providers : []}
                required
              />
            </div>
          )}
          <div className="inlineDiv">
            <TextInput
              name="requesterFirstName"
              labelText={
                <FormattedMessage id="order.requester.firstName.label" />
              }
              disabled={allowRequesterOptions !== "false"}
              onChange={handleRequesterFirstName}
              value={orderFormValues.sampleOrderItems.providerFirstName}
              id="requesterFirstName"
              className="inputText"
            />

            <TextInput
              name="requesterLastName"
              labelText={
                <FormattedMessage id="order.requester.lastName.label" />
              }
              disabled={allowRequesterOptions !== "false"}
              value={orderFormValues.sampleOrderItems.providerLastName}
              onChange={handleRequesterLastName}
              id="requesterLastName"
              className="inputText"
            />
          </div>
          <div className="inlineDiv">
            <TextInput
              name="providerWorkPhone"
              disabled={allowRequesterOptions !== "false"}
              onChange={handleRequesterWorkPhone}
              value={orderFormValues.sampleOrderItems.providerWorkPhone}
              onMouseLeave={handlePhoneNoValidation}
              labelText={<FormattedMessage id="order.requester.phone.label" />}
              id="providerWorkPhoneId"
              className="inputText"
            />

            <TextInput
              name="providerFax"
              labelText={<FormattedMessage id="order.requester.fax.label" />}
              disabled={allowRequesterOptions !== "false"}
              onChange={handleRequesterFax}
              value={orderFormValues.sampleOrderItems.providerFax}
              id="providerFaxId"
              className="inputText"
            />
          </div>
          <div className="inlineDiv">
            <TextInput
              name="providerEmail"
              labelText={<FormattedMessage id="order.requester.email.label" />}
              disabled={allowRequesterOptions !== "false"}
              onChange={handleRequesterEmail}
              value={orderFormValues.sampleOrderItems.providerEmail}
              id="providerEmailId"
              className="inputText"
            />

            <Select
              className="inputText"
              id="paymentOptionSelectionId"
              name="paymentOptionSelections"
              value={orderFormValues.sampleOrderItems.paymentOptionSelection}
              labelText={<FormattedMessage id="order.payment.status.label" />}
              onChange={handlePaymentStatus}
              required
            >
              <SelectItem value="" text="" />
              {paymentOptions?.map((option) => {
                return (
                  <SelectItem
                    key={option.id}
                    value={option.id}
                    text={option.value}
                  />
                );
              })}
            </Select>
          </div>
          <div className="inlineDiv">
            <Select
              className="inputText"
              id="testLocationCodeId"
              name="testLocationCode"
              value={orderFormValues.sampleOrderItems.testLocationCode}
              labelText={
                <FormattedMessage id="order.sampling.performed.label" />
              }
              onChange={(e) => handleSamplingPerformed(e)}
              required
            >
              <SelectItem value="" text="" />
              {samplingPerformed.map((option) => {
                return (
                  <SelectItem
                    key={option.id}
                    value={option.id}
                    text={option.value}
                  />
                );
              })}
            </Select>
            <TextInput
              name="testLocationCodeOther"
              labelText={<FormattedMessage id="order.if.other.label" />}
              onChange={handleOtherLocationCode}
              className="inputText"
              value={orderFormValues.sampleOrderItems.otherLocationCode}
              disabled={!otherSamplingVisible}
              id="testLocationCodeOtherId"
            />
          </div>
          <div className="inlineDiv">
            <Checkbox
              labelText={
                <FormattedMessage id="order.remember.site.and.requester.label" />
              }
              className="inputText"
              id="rememberSiteAndRequester"
              onChange={handleRememberCheckBox}
            />
          </div>
        </div>
        <div className="orderLegendBody">
          <h3>{<FormattedMessage id="order.result.reporting.heading" />}</h3>
          {samples?.map((sample, index) => {
            if (sample.tests.length > 0) {
              return (
                <div key={index}>
                  <h4>
                    {" "}
                    <FormattedMessage id="label.button.sample" /> {index + 1}
                  </h4>
                  <OrderResultReporting
                    selectedTests={sample.tests}
                    reportingNotifications={reportingNotifications}
                  />
                </div>
              );
            }
          })}
        </div>
      </Stack>
    </>
  );
};

export default AddOrder;
