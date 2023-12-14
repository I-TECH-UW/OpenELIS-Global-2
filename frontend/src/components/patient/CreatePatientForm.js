import React, { useState, useRef, useEffect, useContext } from "react";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import "../Style.css";
import { getFromOpenElisServer, postToOpenElisServer } from "../utils/Utils";
import { nationalityList } from "../data/countries";
import format from "date-fns/format";

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
  const { configurationProperties } = useContext(ConfigurationContext);

  const [patientDetails, setPatientDetails] = useState(CreatePatientFormValues);
  const [healthRegions, setHealthRegions] = useState([]);
  const [healthDistricts, setHealthDistricts] = useState([]);
  const [educationList, setEducationList] = useState([]);
  const [maritalStatuses, setMaritalStatuses] = useState([]);
  const [formAction, setFormAction] = useState("ADD");
  const componentMounted = useRef(false);
  const intl = useIntl();
  const [dateOfBirthFormatter, setDateOfBirthFormatter] = useState({
    years: "",
    months: "",
    days: "",
  });
  const handleDatePickerChange = (values, ...e) => {
    var patient = values;
    patient.birthDateForDisplay = e[1];
    setPatientDetails(patient);
    if (patient.birthDateForDisplay !== "") {
      getYearsMonthsDaysFromDOB(patient.birthDateForDisplay);
    }
  };

  function getYearsMonthsDaysFromDOB(date) {
    const selectedDate = date.split("/");
    let today = new Date();

    let year = today.getFullYear();
    let month = today.getMonth() + 1;
    let day = today.getDate();

    let yy = parseInt(selectedDate[2]);
    let mm = parseInt(selectedDate[1]);
    let dd = parseInt(selectedDate[0]);

    let years, months, days;
    months = month - mm;
    if (day < dd) {
      months = months - 1;
    }
    years = year - yy;
    if (month * 100 + day < mm * 100 + dd) {
      years = years - 1;
      months = months + 12;
    }
    days = Math.floor(
      (today.getTime() - new Date(yy + years, mm + months - 1, dd).getTime()) /
        (24 * 60 * 60 * 1000),
    );

    setDateOfBirthFormatter({
      ...dateOfBirthFormatter,
      years: years,
      months: months,
      days: days,
    });
  }

  const getDOBByYearMonthsDays = () => {
    const currentDate = new Date();
    const pastDate = new Date();

    pastDate.setFullYear(
      currentDate.getFullYear() - dateOfBirthFormatter.years,
    );
    pastDate.setMonth(currentDate.getMonth() - dateOfBirthFormatter.months);
    pastDate.setDate(currentDate.getDate() - dateOfBirthFormatter.days);
    const dob = format(new Date(pastDate), "dd/MM/yyyy");
    setPatientDetails((prevState) => ({
      ...prevState,
      birthDateForDisplay: dob,
    }));
  };

  function handleYearsChange(e, values) {
    setPatientDetails(values);
    let years = e.target.value;
    setDateOfBirthFormatter({
      ...dateOfBirthFormatter,
      years: years,
    });
  }

  function handleMonthsChange(e, values) {
    setPatientDetails(values);
    let months = e.target.value;
    setDateOfBirthFormatter({
      ...dateOfBirthFormatter,
      months: months,
    });
  }

  function handleDaysChange(e, values) {
    setPatientDetails(values);
    let days = e.target.value;
    setDateOfBirthFormatter({
      ...dateOfBirthFormatter,
      days: days,
    });
  }
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
    getDOBByYearMonthsDays();
  }, [dateOfBirthFormatter]);

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
      getYearsMonthsDaysFromDOB(patient.birthDateForDisplay);
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
        getYearsMonthsDaysFromDOB(
          props.orderFormValues.patientProperties.birthDateForDisplay,
        );
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
    if ("years" in values) {
      delete values.years;
    }
    if ("months" in values) {
      delete values.months;
    }
    if ("days" in values) {
      delete values.days;
    }
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
                    labelText={intl.formatMessage({
                      id: "patient.subject.number",
                    })}
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
                    labelText={
                      <>
                        {intl.formatMessage({
                          id: "patient.natioanalid",
                        })}
                        <span className="requiredlabel">*</span>
                      </>
                    }
                    id={field.name}
                    className="inputText"
                    invalid={
                      props.error
                        ? props.error("patientProperties.nationalId")
                          ? true
                          : false
                        : false
                    }
                    invalidText={
                      props.error
                        ? props.error("patientProperties.nationalId")
                        : ""
                    }
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
                    labelText={intl.formatMessage({
                      id: "patient.last.name",
                    })}
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
                    labelText={intl.formatMessage({
                      id: "patient.first.name",
                    })}
                    id={field.name}
                    className="inputText"
                  />
                )}
              </Field>
            </div>

            <div className="inlineDiv">
              <Field name="primaryPhone">
                {({ field }) => (
                  <TextInput
                    value={values.primaryPhone}
                    name={field.name}
                    labelText={intl.formatMessage(
                      {
                        id: "patient.label.primaryphone",
                        defaultMessage: "Phone: {PHONE_FORMAT}",
                      },
                      { PHONE_FORMAT: configurationProperties.PHONE_FORMAT },
                    )}
                    id={field.name}
                    className="inputText"
                  />
                )}
              </Field>
              <Field name="gender">
                {({ field }) => (
                  <RadioButtonGroup
                    valueSelected={values.gender}
                    legendText={
                      <>
                        {intl.formatMessage({ id: "patient.gender" })}{" "}
                        <span className="requiredlabel">*</span>
                      </>
                    }
                    name={field.name}
                    className="inputText"
                    id="create_patient_gender"
                  >
                    <RadioButton
                      id="radio-1"
                      labelText={intl.formatMessage({ id: "patient.male" })}
                      value="M"
                    />
                    <RadioButton
                      id="radio-2"
                      labelText={intl.formatMessage({ id: "patient.female" })}
                      value="F"
                    />
                  </RadioButtonGroup>
                )}
              </Field>
              <div className="error">
                <ErrorMessage name="gender"></ErrorMessage>
              </div>
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
                      labelText={
                        <>
                          {intl.formatMessage({
                            id: "patient.dob",
                          })}
                          <span className="requiredlabel">*</span>
                        </>
                      }
                      type="text"
                      name={field.name}
                    />
                  </DatePicker>
                )}
              </Field>
              <TextInput
                value={dateOfBirthFormatter.years}
                name="years"
                labelText={intl.formatMessage({
                  id: "patient.age.years",
                })}
                id="years"
                type="number"
                onChange={(e) => handleYearsChange(e, values)}
                className="inputText"
              />

              <TextInput
                value={dateOfBirthFormatter.months}
                name="months"
                labelText={intl.formatMessage({ id: "patient.age.months" })}
                type="number"
                onChange={(e) => handleMonthsChange(e, values)}
                id="months"
                className="inputText"
              />

              <TextInput
                value={dateOfBirthFormatter.days}
                name="days"
                type="number"
                onChange={(e) => handleDaysChange(e, values)}
                labelText={intl.formatMessage({ id: "patient.age.days" })}
                id="days"
                className="inputText"
              />
              <div className="error">
                <ErrorMessage name="birthDateForDisplay"></ErrorMessage>
              </div>
            </div>
            <Accordion>
              <AccordionItem
                title={intl.formatMessage({ id: "emergencyContactInfo.title" })}
              >
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
                        labelText={intl.formatMessage({
                          id: "patientcontact.person.lastname",
                        })}
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
                        labelText={intl.formatMessage({
                          id: "patientcontact.person.firstname",
                        })}
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
                        labelText={intl.formatMessage({
                          id: "patientcontact.person.email",
                        })}
                        id={field.name}
                        className="inputText"
                      />
                    )}
                  </Field>
                  <Field name="patientContact.person.primaryPhone">
                    {({ field }) => (
                      <TextInput
                        value={
                          values.patientContact?.person.primaryPhone ===
                          undefined
                            ? ""
                            : values.patientContact?.person.primaryPhone
                        }
                        name={field.name}
                        labelText={intl.formatMessage(
                          {
                            id: "patient.label.contactphone",
                            defaultMessage: "Contact Phone: {PHONE_FORMAT}",
                          },
                          {
                            PHONE_FORMAT: configurationProperties.PHONE_FORMAT,
                          },
                        )}
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
              </AccordionItem>
              <AccordionItem
                title={intl.formatMessage({
                  id: "patient.label.additionalInfo",
                })}
              >
                <div className="inlineDiv">
                  <Field name="city">
                    {({ field }) => (
                      <TextInput
                        value={values.city}
                        name={field.name}
                        labelText={intl.formatMessage({
                          id: "patient.address.town",
                        })}
                        id={field.name}
                        className="inputText"
                      />
                    )}
                  </Field>
                  <Field name="streetAddress">
                    {({ field }) => (
                      <TextInput
                        value={values.streetAddress}
                        name={field.name}
                        labelText={intl.formatMessage({
                          id: "patient.address.street",
                        })}
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
                        labelText={intl.formatMessage({
                          id: "patient.address.camp",
                        })}
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
                        labelText={intl.formatMessage({
                          id: "patient.address.healthregion",
                        })}
                        className="inputText"
                        onChange={(e) => handleRegionSelection(e, values)}
                      >
                        <SelectItem text="" value="" />
                        {healthRegions?.map((region, index) => (
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
                        labelText={intl.formatMessage({
                          id: "patient.address.healthdistrict",
                        })}
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
                        labelText={intl.formatMessage({
                          id: "pateint.eduction",
                        })}
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
                        labelText={intl.formatMessage({
                          id: "patient.maritalstatus",
                        })}
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
                        labelText={intl.formatMessage({
                          id: "patient.nationality",
                        })}
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
                        labelText={intl.formatMessage({
                          id: "patient.nationality.other",
                        })}
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
