import React, { useState, useRef, useEffect, useContext } from "react";
import { FormattedMessage, injectIntl } from "react-intl";
import "../Style.css";
import { getFromOpenElisServer, postToOpenElisServer } from "../utils/Utils";
import { nationalityList } from "../data/countries";

import {
  Heading,
  Form,
  FormLabel,
  TextInput,
  Button,
  DatePicker,
  DatePickerInput,
  RadioButton,
  RadioButtonGroup,
  Section,
  Select,
  SelectItem,
  Accordion,
  AccordionItem,
} from "@carbon/react";

import { Formik, Field, ErrorMessage } from "formik";
import CreatePatientFormValues from "../formModel/innitialValues/CreatePatientFormValues";
import PatientFormObserver from "./PatientFormObserver";
import { AlertDialog, NotificationKinds } from "../common/CustomNotification";
import { NotificationContext, ConfigurationContext } from "../layout/Layout";
import CreatePatientValidationSchema from "../formModel/validationSchema/CreatePatientValidationShema";
function CreatePatientForm(props) {
  const { notificationVisible, setNotificationVisible, setNotificationBody } =
    useContext(NotificationContext);
  const configurationProperties = useContext(ConfigurationContext);

  const [patientDetails, setPatientDetails] = useState(CreatePatientFormValues);
  const [healthRegions, setHealthRegions] = useState([]);
  const [healthDistricts, setHealthDistricts] = useState([]);
  const [educationList, setEducationList] = useState([]);
  const [maritalStatuses, setMaritalStatuses] = useState([]);
  const [formAction, setFormAction] = useState("ADD");
  const componentMounted = useRef(false);

  const handleDatePickerChange = (values, ...e) => {
    var patient = values;
    patient.birthDateForDisplay = e[1];
    setPatientDetails(patient);
  };

  const handleRegionSelection = (e, values) => {
    var patient = values;
    patient.healthDistrict = "";
    setPatientDetails(patient);
    const { value } = e.target;
    getFromOpenElisServer(
      "/rest/health-districts-for-region?regionId=" + value,
      fetchHeathDistricts,
    );
  };

  function fethchHealthDistrictsCallback(res) {
    setHealthDistricts(res);
  }

  useEffect(() => {
    if (props.selectedPatient.patientPK) {
      if (props.selectedPatient.healthRegion != null) {
        getFromOpenElisServer(
          "/rest/health-districts-for-region?regionId=" +
            props.selectedPatient.healthRegion,
          fethchHealthDistrictsCallback,
        );
      } else {
        //nextState.healthDistricts = [];
        setHealthDistricts([]);
      }
      const patient = props.selectedPatient;
      patient.patientUpdateStatus = "UPDATE";
      setPatientDetails(patient);
      setFormAction("UPDATE");
    }
  }, [props.selectedPatient]);

  const repopulatePatientInfo = () => {
    if (props.orderFormValues != null) {
      if (
        props.orderFormValues.patientProperties.firstName !== "" ||
        props.orderFormValues.patientProperties.guid !== ""
      ) {
        setPatientDetails(props.orderFormValues.patientProperties);
      }
    }
  };

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/health-regions", fetchHeathRegions);
    getFromOpenElisServer("/rest/education-list", fetchEducationList);
    getFromOpenElisServer("/rest/marital-statuses", fetchMaritalStatuses);
    // getFromOpenElisServer("/rest/nationalities", fetchNationalities);
    repopulatePatientInfo();
    return () => {
      componentMounted.current = false;
    };
  }, []);

  const fetchHeathRegions = (regions) => {
    if (componentMounted.current) {
      setHealthRegions(regions);
    }
  };

  const accessionNumberValidationResponse = (res) => {
    if (res.status === false) {
      setNotificationVisible(true);
      setNotificationBody({
        kind: NotificationKinds.error,
        title: <FormattedMessage id="notification.title" />,
        message: res.body,
      });
    }
  };

  const handleSubjectNoValidation = (numberType, fieldId, numberValue) => {
    if (numberValue !== "") {
      getFromOpenElisServer(
        `/rest/subjectNumberValidationProvider?fieldId=${fieldId}&numberType=${numberType}&subjectNumber=${numberValue}`,
        accessionNumberValidationResponse,
      );
    }
  };

  const fetchMaritalStatuses = (statuses) => {
    if (componentMounted.current) {
      setMaritalStatuses(statuses);
    }
  };

  const fetchEducationList = (eductationList) => {
    if (componentMounted.current) {
      setEducationList(eductationList);
    }
  };

  const fetchHeathDistricts = (districts) => {
    setHealthDistricts(districts);
  };

  const handleSubmit = (values) => {
    console.log(JSON.stringify(values));
    postToOpenElisServer(
      "/rest/patient-management",
      JSON.stringify(values),
      handlePost,
    );
  };

  const handlePost = (status) => {
    setNotificationVisible(true);
    if (status === 200) {
      setNotificationBody({
        title: <FormattedMessage id="notification.title" />,
        message: "Patient Saved Succsfuly",
        kind: NotificationKinds.success,
      });
    } else {
      setNotificationBody({
        title: <FormattedMessage id="notification.title" />,
        message: "Error While Saving Patient",
        kind: NotificationKinds.error,
      });
    }
  };

  return (
    <>
      {notificationVisible === true ? <AlertDialog /> : ""}
      <Formik
        initialValues={patientDetails}
        enableReinitialize
        validationSchema={CreatePatientValidationSchema}
        onSubmit={handleSubmit}
        onChange
      >
        {({
          values,
          //errors,
          //touched,
          resetForm,
          handleChange,
          handleBlur,
          handleSubmit,
        }) => (
          <Form
            onSubmit={handleSubmit}
            onChange={handleChange}
            onBlur={handleBlur}
          >
            {props.orderFormValues && (
              <PatientFormObserver
                orderFormValues={props.orderFormValues}
                setOrderFormValues={props.setOrderFormValues}
                formAction={formAction}
              />
            )}
            <FormLabel>
              <Section>
                <Section>
                  <Section>
                    <Heading>
                      <FormattedMessage id="patient.label.info" />
                    </Heading>
                  </Section>
                </Section>
              </Section>
            </FormLabel>
            <div className="inlineDiv">
              <Field name="subjectNumber">
                {({ field }) => (
                  <TextInput
                    value={values.subjectNumber}
                    name={field.name}
                    labelText="Unique Health ID number"
                    id={field.name}
                    className="inputText"
                    onMouseOut={() => {
                      handleSubjectNoValidation(
                        "subjectNumber",
                        "subjectNumberID",
                        values.subjectNumber,
                      );
                    }}
                  />
                )}
              </Field>
              <Field name="nationalId">
                {({ field }) => (
                  <TextInput
                    value={values.nationalId}
                    name={field.name}
                    labelText="National Id"
                    id={field.name}
                    className="inputText"
                    onMouseOut={() => {
                      handleSubjectNoValidation(
                        "nationalId",
                        "nationalID",
                        values.nationalId,
                      );
                    }}
                  />
                )}
              </Field>
              <div className="error"></div>
              <div className="error">
                <ErrorMessage name="nationalId"></ErrorMessage>
              </div>
            </div>
            <div className="inlineDiv">
              <Field name="lastName">
                {({ field }) => (
                  <TextInput
                    value={values.lastName}
                    name={field.name}
                    labelText="Last Name"
                    id={field.name}
                    className="inputText"
                  />
                )}
              </Field>
              <Field name="firstName">
                {({ field }) => (
                  <TextInput
                    value={values.firstName}
                    name={field.name}
                    labelText="First Name"
                    id={field.name}
                    className="inputText"
                  />
                )}
              </Field>
            </div>
            <div className="inlineDiv">
              <Field name="patientContact.person.lastName">
                {({ field }) => (
                  <TextInput
                    value={
                      values.patientContact?.person.lastName === undefined
                        ? ""
                        : values.patientContact?.person.lastName
                    }
                    name={field.name}
                    labelText="Contact Last Name"
                    id={field.name}
                    className="inputText"
                  />
                )}
              </Field>
              <Field name="patientContact.person.firstName">
                {({ field }) => (
                  <TextInput
                    value={
                      values.patientContact?.person.firstName === undefined
                        ? ""
                        : values.patientContact?.person.firstName
                    }
                    name={field.name}
                    labelText="Contact First Name"
                    id={field.name}
                    className="inputText"
                  />
                )}
              </Field>
            </div>
            <div className="inlineDiv">
              <Field name="patientContact.person.email">
                {({ field }) => (
                  <TextInput
                    value={
                      values.patientContact?.person.email === undefined
                        ? ""
                        : values.patientContact?.person.email
                    }
                    name={field.name}
                    labelText="Contact Email"
                    id={field.name}
                    className="inputText"
                  />
                )}
              </Field>
              <Field name="patientContact.person.primaryPhone">
                {({ field }) => (
                  <TextInput
                    value={
                      values.patientContact?.person.primaryPhone === undefined
                        ? ""
                        : values.patientContact?.person.primaryPhone
                    }
                    name={field.name}
                    labelText={`Contact Phone: ${configurationProperties.PHONE_FORMAT}`}
                    id={field.name}
                    className="inputText"
                  />
                )}
              </Field>
              <div className="error">
                <ErrorMessage name="patientContact.person.email"></ErrorMessage>
              </div>
              <div className="error"></div>
            </div>
            <div className="inlineDiv">
              <Field name="city">
                {({ field }) => (
                  <TextInput
                    value={values.city}
                    name={field.name}
                    labelText="Town"
                    id={field.name}
                    className="inputText"
                  />
                )}
              </Field>
              <Field name="primaryPhone">
                {({ field }) => (
                  <TextInput
                    value={values.primaryPhone}
                    name={field.name}
                    labelText={`Phone: ${configurationProperties.PHONE_FORMAT}`}
                    id={field.name}
                    className="inputText"
                  />
                )}
              </Field>
            </div>
            <div className="inlineDiv">
              <Field name="birthDateForDisplay">
                {({ field }) => (
                  <DatePicker
                    value={values.birthDateForDisplay}
                    onChange={(...e) => handleDatePickerChange(values, ...e)}
                    name={field.name}
                    dateFormat="d/m/Y"
                    datePickerType="single"
                    light={true}
                    className="inputText"
                  >
                    <DatePickerInput
                      id="date-picker-default-id"
                      placeholder="dd/mm/yyyy"
                      labelText="Date of Birth"
                      type="text"
                      name={field.name}
                    />
                  </DatePicker>
                )}
              </Field>
              <Field name="gender">
                {({ field }) => (
                  <RadioButtonGroup
                    valueSelected={values.gender}
                    legendText="Gender"
                    name={field.name}
                    className="inputText"
                    id="create_patient_gender"
                  >
                    <RadioButton id="radio-1" labelText="Male" value="M" />
                    <RadioButton id="radio-2" labelText="Female" value="F" />
                  </RadioButtonGroup>
                )}
              </Field>
              <div className="error">
                <ErrorMessage name="birthDateForDisplay"></ErrorMessage>
              </div>
              <div className="error">
                <ErrorMessage name="gender"></ErrorMessage>
              </div>
            </div>
            <Accordion>
              <AccordionItem title="Additional Information">
                <div className="inlineDiv">
                  <Field name="streetAddress">
                    {({ field }) => (
                      <TextInput
                        value={values.streetAddress}
                        name={field.name}
                        labelText="Street"
                        id={field.name}
                        className="inputText"
                      />
                    )}
                  </Field>
                  <Field name="commune">
                    {({ field }) => (
                      <TextInput
                        value={values.commune}
                        name={field.name}
                        labelText="Camp/Commune"
                        id={field.name}
                        className="inputText"
                      />
                    )}
                  </Field>
                </div>
                <div className="inlineDiv">
                  <Field name="healthRegion">
                    {({ field }) => (
                      <Select
                        id="health_region"
                        value={values.healthRegion}
                        name={field.name}
                        labelText="Region"
                        className="inputText"
                        onChange={(e) => handleRegionSelection(e, values)}
                      >
                        <SelectItem text="" value="" />
                        {healthRegions.map((region, index) => (
                          <SelectItem
                            text={region.value}
                            value={region.id}
                            key={index}
                          />
                        ))}
                      </Select>
                    )}
                  </Field>
                  <Field name="healthDistrict">
                    {({ field }) => (
                      <Select
                        id="health_district"
                        value={values.healthDistrict}
                        name={field.name}
                        labelText="District"
                        className="inputText"
                        onChange={() => {}}
                      >
                        <SelectItem text="" value="" />
                        {healthDistricts.map((district, index) => (
                          <SelectItem
                            text={district.value}
                            value={district.value}
                            key={index}
                          />
                        ))}
                      </Select>
                    )}
                  </Field>
                </div>
                <div className="inlineDiv">
                  <Field name="education">
                    {({ field }) => (
                      <Select
                        id="education"
                        value={values.education}
                        name={field.name}
                        labelText="Education"
                        className="inputText"
                        onChange={() => {}}
                      >
                        <SelectItem text="" value="" />
                        {educationList.map((education, index) => (
                          <SelectItem
                            text={education.value}
                            value={education.value}
                            key={index}
                          />
                        ))}
                      </Select>
                    )}
                  </Field>
                  <Field name="maritialStatus">
                    {({ field }) => (
                      <Select
                        id="maritialStatus"
                        value={values.maritialStatus}
                        name={field.name}
                        labelText="Marital Status"
                        className="inputText"
                        onChange={() => {}}
                      >
                        <SelectItem text="" value="" />
                        {maritalStatuses.map((status, index) => (
                          <SelectItem
                            text={status.value}
                            value={status.value}
                            key={index}
                          />
                        ))}
                      </Select>
                    )}
                  </Field>
                </div>
                <div className="inlineDiv">
                  <Field name="nationality">
                    {({ field }) => (
                      <Select
                        id="nationality"
                        value={values.nationality}
                        name={field.name}
                        labelText="Nationality"
                        className="inputText"
                        onChange={() => {}}
                      >
                        <SelectItem text="" value="" />
                        {nationalityList.map((nationality, index) => (
                          <SelectItem
                            text={nationality.label}
                            value={nationality.value}
                            key={index}
                          />
                        ))}
                      </Select>
                    )}
                  </Field>
                  <Field name="otherNationality">
                    {({ field }) => (
                      <TextInput
                        value={values.otherNationality}
                        name={field.name}
                        labelText="Specify Other nationality"
                        id={field.name}
                        className="inputText"
                      />
                    )}
                  </Field>
                </div>
              </AccordionItem>
            </Accordion>
            {props.showActionsButton && (
              <div className="inlineDiv">
                <Button type="submit" id="submit">
                  <FormattedMessage id="label.button.save" />
                </Button>
                <Button
                  id="clear"
                  kind="danger"
                  onClick={() => {
                    resetForm({ values: CreatePatientFormValues });
                    setHealthDistricts([]);
                  }}
                >
                  <FormattedMessage id="label.button.clear" />
                </Button>
              </div>
            )}
          </Form>
        )}
      </Formik>
    </>
  );
}

export default injectIntl(CreatePatientForm);
