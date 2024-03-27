import React, { useContext, useState, useRef, useEffect } from "react";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import "../../Style.css";
import { getFromOpenElisServer } from "../../utils/Utils";
import {
  Form,
  Checkbox,
  Dropdown,
  Heading,
  Grid,
  Column,
  Section,
  TextInput,
  Button,
  DatePicker,
  DatePickerInput,
  RadioButton,
  RadioButtonGroup,
  Loading,
  DataTable,
  Pagination,
  TableContainer,
  Table,
  TableHead,
  TableRow,
  TableHeader,
  TableBody,
  TableCell,
  Select,
  SelectItem,
} from "@carbon/react";
import CustomLabNumberInput from "../../common/CustomLabNumberInput";
import config from "../../../config.json";
import { patientSearchHeaderData } from "../../data/PatientResultsTableHeaders";
import CustomDatePicker from "../../common/CustomDatePicker";
import AutoComplete from "../../common/AutoComplete";
import { ConfigurationContext } from "../../layout/Layout";
import { Formik, Field } from "formik";
import PatientStatusReportFormValues from "../../formModel/innitialValues/PatientStatusReportFormValues";
import { NotificationContext } from "../../layout/Layout";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification";

function PatientStatusReport(props) {
  const [reportFormValues, setReportFormValues] = useState(
    PatientStatusReportFormValues,
  );
  // const { reportFormValues, setReportFormValues, getSelectedPatient, samples, error } = props;
  const { configurationProperties } = useContext(ConfigurationContext);
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  const intl = useIntl();
  const itemList = [
    {
      id: "option-0",
      text: "Result Date",
      tag: "RESULT_DATE",
    },
    {
      id: "option-1",
      text: "Order Date",
      tag: "ORDER_DATE",
    },
  ];

  const componentMounted = useRef(false);
  const [checkbox, setCheckbox] = useState("on");
  const [result, setResult] = useState("false");
  const [items, setItems] = useState(itemList[0].tag);
  const [dob, setDob] = useState("");
  const [patientSearchResults, setPatientSearchResults] = useState([]);
  const [page, setPage] = useState(1);
  const [siteNames, setSiteNames] = useState([]);
  const [pageSize, setPageSize] = useState(5);
  const [loading, setLoading] = useState(false);
  const [nextPage, setNextPage] = useState(null);
  const [previousPage, setPreviousPage] = useState(null);
  const [pagination, setPagination] = useState(false);
  const [url, setUrl] = useState("");
  const [innitialized, setInnitialized] = useState(false);
  const [departments, setDepartments] = useState([]);

  const handleReportPrint = () => {
    let barcodesPdf =
      config.serverBaseUrl +
      `/ReportPrint?report=patientCILNSP_vreduit&type=patient&accessionDirect=${reportFormValues.form}&highAccessionDirect=${reportFormValues.to}&dateOfBirthSearchValue=${reportFormValues.dateOfBirth}&selPatient=${reportFormValues.selectedPatientId}&referringSiteId=${reportFormValues.referringSiteId}&referringSiteDepartmentId=${reportFormValues.referringSiteName}&onlyResults=${result}&_onlyResults=${checkbox}&dateType=${items}&lowerDateRange=${reportFormValues.startDate}&upperDateRange=${reportFormValues.endDate}`;
    window.open(barcodesPdf);
  };

  const handleSubmit = (values) => {
    setLoading(true);
    values.dateOfBirth = dob;
    const searchEndPoint =
      "/rest/patient-search-results?" +
      "&lastName=" +
      values.lastName +
      "&firstName=" +
      values.firstName +
      "&STNumber=" +
      values.patientId +
      "&subjectNumber=" +
      values.patientId +
      "&nationalID=" +
      values.patientId +
      "&labNumber=" +
      values.labNumber +
      "&guid=" +
      values.guid +
      "&dateOfBirth=" +
      values.dateOfBirth +
      "&gender=" +
      values.gender;
    getFromOpenElisServer(searchEndPoint, fetchPatientResults);
    setUrl(searchEndPoint);
  };

  function encodeDate(dateString) {
    if (typeof dateString === "string" && dateString.trim() !== "") {
      return dateString.split("/").map(encodeURIComponent).join("%2F");
    } else {
      return "";
    }
  }

  function handlePatientIdFrom(e) {
    setReportFormValues({
      ...reportFormValues,
      form: e.target.value,
    });
  }

  function handlePatientIdTo(e) {
    setReportFormValues({
      ...reportFormValues,
      to: e.target.value,
    });
  }

  function handleLabNumber(e) {
    setReportFormValues({
      ...reportFormValues,
      labNumber: e.target.value,
    });
  }
  function handlePatientId(e) {
    setReportFormValues({
      ...reportFormValues,
      patientId: e.target.value,
    });
  }

  function handleLastName(e) {
    setReportFormValues({
      ...reportFormValues,
      lastName: e.target.value,
    });
  }

  function handleFirstName(e) {
    setReportFormValues({
      ...reportFormValues,
      firstName: e.target.value,
    });
  }

  function handleGender(e) {
    setReportFormValues({
      ...reportFormValues,
      gender: e,
    });
  }

  function handleSiteName(e) {
    setReportFormValues({
      ...reportFormValues,
      referringSiteName: e.target.value,
    });
  }

  function handleRequesterDept(e) {
    setReportFormValues({
      ...reportFormValues,
      referringSiteDepartmentId: e.target.value,
    });
  }

  function handleAutoCompleteSiteName(siteId) {
    setReportFormValues({
      ...reportFormValues,
      referringSiteId: siteId,
      referringSiteName: "",
    });
  }
  const loadDepartments = (data) => {
    setDepartments(data);
  };

  const loadNextResultsPage = () => {
    setLoading(true);
    getFromOpenElisServer(url + "&page=" + nextPage, fetchPatientResults);
  };

  const loadPreviousResultsPage = () => {
    setLoading(true);
    getFromOpenElisServer(url + "&page=" + previousPage, fetchPatientResults);
  };

  const fetchPatientResults = (res) => {
    let patientsResults = res.patientSearchResults;
    if (patientsResults.length > 0) {
      patientsResults.forEach((item) => (item.id = item.patientID));
      setPatientSearchResults(patientsResults);
    } else {
      setPatientSearchResults([]);
      addNotification({
        title: intl.formatMessage({ id: "notification.title" }),
        message: intl.formatMessage({ id: "patient.search.nopatient" }),
        kind: NotificationKinds.warning,
      });
      setNotificationVisible(true);
    }
    if (res.paging) {
      var { totalPages, currentPage } = res.paging;
      if (totalPages > 1) {
        setPagination(true);
        if (parseInt(currentPage) < parseInt(totalPages)) {
          setNextPage(parseInt(currentPage) + 1);
        } else {
          setNextPage(null);
        }
        if (parseInt(currentPage) > 1) {
          setPreviousPage(parseInt(currentPage) - 1);
        } else {
          setPreviousPage(null);
        }
      }
    }
    setLoading(false);
  };

  const fetchPatientDetails = (patientDetails) => {
    props.getSelectedPatient(patientDetails);
  };

  const handleDatePickerChange = (...e) => {
    let updatedDate = encodeDate(e[1]);

    setReportFormValues({
      ...reportFormValues,
      dateOfBirth: updatedDate,
    });

    setDob(e[1]);
  };

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
        obj = {
          startDate: encodeDate(configurationProperties.currentDateAsText),
          endDate: encodeDate(configurationProperties.currentDateAsText),
        };
    }
    setReportFormValues({
      ...reportFormValues,
      PatientStatusReportFormValues: obj,
    });
  };

  const patientSelected = (e) => {
    const patientSelected = patientSearchResults.find((patient) => {
      return patient.patientID == e.target.id;
    });

    setReportFormValues({
      ...reportFormValues,
      selectedPatientId: e.target.id,
    });

    const searchEndPoint =
      "/rest/patient-details?patientID=" + patientSelected.patientID;
    getFromOpenElisServer(searchEndPoint, fetchPatientDetails);
  };

  const getSampleEntryPreform = (response) => {
    if (componentMounted.current) {
      setSiteNames(response.sampleOrderItems.referringSiteList);
    }
  };

  const handlePageChange = (pageInfo) => {
    if (page != pageInfo.page) {
      setPage(pageInfo.page);
    }

    if (pageSize != pageInfo.pageSize) {
      setPageSize(pageInfo.pageSize);
    }
  };

  useEffect(() => {
    getFromOpenElisServer(
      "/rest/departments-for-site?refferingSiteId=" +
        (reportFormValues.referringSiteId || ""),
      loadDepartments,
    );
  }, [reportFormValues.referringSiteId]);

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/SamplePatientEntry", getSampleEntryPreform);
    window.scrollTo(0, 0);
    return () => {
      componentMounted.current = false;
    };
  }, []);

  useEffect(() => {
    let patientId = new URLSearchParams(window.location.search).get(
      "patientId",
    );
    if (patientId) {
      let searchValues = {
        ...reportFormValues,
        patientId: patientId,
      };
      setReportFormValues(searchValues);
      handleSubmit(searchValues);
    }
  }, [reportFormValues]);

  useEffect(() => {
    if (!innitialized) {
      let updatedDate = encodeDate(configurationProperties.currentDateAsText);
      setReportFormValues({
        ...reportFormValues,
        dateOfBirth: updatedDate,
        startDate: updatedDate,
        endDate: updatedDate,
      });
    }
    if (reportFormValues.dateOfBirth != "") {
      setInnitialized(true);
    }
  }, [
    reportFormValues,
    innitialized,
    configurationProperties.currentDateAsText,
  ]);

  const breadcrumbs = [
    { label: "home.label", link: "/" },
    { label: "routine.reports", link: "/RoutineReports" },
    {
      label: "openreports.patientTestStatus",
      link: "/RoutineReport?type=patient&report=patientCILNSP_vreduit",
    },
  ];

  return (
    <>
      <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Section>
              <Heading>
                <FormattedMessage id="openreports.patientTestStatus" />
              </Heading>
            </Section>
          </Section>
        </Column>
      </Grid>
      <Formik
        initialValues={reportFormValues}
        enableReinitialize={true}
        // validationSchema={}
        onSubmit={handleSubmit}
        onChange
      >
        {({
          values,
          //errors,
          //touched,
          setFieldValue,
          handleChange,
          handleBlur,
          handleSubmit,
        }) => (
          <Form
            onSubmit={handleSubmit}
            onChange={handleChange}
            onBlur={handleBlur}
          >
            <Field name="guid">
              {({ field }) => (
                <input type="hidden" name={field.name} id={field.name} />
              )}
            </Field>
            <Grid fullWidth={true}>
              <Column lg={16} md={8} sm={4}>
                <Section>
                  <br />
                  <br />
                  <h5>
                    <FormattedMessage id="report.enter.labNumber.headline" />
                  </h5>
                  <br />
                  <h6>
                    <FormattedMessage id="sample.search.scanner.instructions" />
                    <br />
                    <FormattedMessage id="sample.search.scanner.instructions.highaccession" />
                  </h6>
                </Section>
              </Column>
            </Grid>
            <div className="inlineDiv">
              <Field name="from">
                {({ field }) => (
                  <CustomLabNumberInput
                    name={field.name}
                    value={values[field.name]}
                    labelText={intl.formatMessage({
                      id: "from.title",
                      defaultMessage: "From",
                    })}
                    id={field.name}
                    className="inputText"
                    onChange={(e, rawValue) => {
                      setFieldValue(field.name, rawValue);
                      handlePatientIdFrom(e);
                    }}
                  />
                )}
              </Field>
              <Field name="to">
                {({ field }) => (
                  <CustomLabNumberInput
                    name={field.name}
                    value={values[field.name]}
                    labelText={intl.formatMessage({
                      id: "to.title",
                      defaultMessage: "To",
                    })}
                    id={field.name}
                    className="inputText"
                    onChange={(e, rawValue) => {
                      setFieldValue(field.name, rawValue);
                      handlePatientIdTo(e);
                    }}
                  />
                )}
              </Field>
            </div>
            <Grid fullWidth={true}>
              <Column lg={16} md={8} sm={4}>
                <Section>
                  <h5>
                    <FormattedMessage id="report.enter.patient.headline" />
                  </h5>
                  <br />
                  <h6>
                    <FormattedMessage id="report.enter.patient.headline.description" />
                  </h6>
                </Section>
              </Column>
            </Grid>
            <div className="inlineDiv">
              <Field name="labNumber">
                {({ field }) => (
                  <CustomLabNumberInput
                    name={field.name}
                    labelText={intl.formatMessage({
                      id: "eorder.labNumber",
                      defaultMessage: "Lab Number",
                    })}
                    id={field.name}
                    className="inputText"
                    value={values[field.name]}
                    onChange={(e, rawValue) => {
                      setFieldValue(field.name, rawValue);
                      handleLabNumber(e);
                    }}
                  />
                )}
              </Field>
              <Field name="patientId">
                {({ field }) => (
                  <TextInput
                    name={field.name}
                    value={values[field.name]}
                    labelText={intl.formatMessage({
                      id: "patient.id",
                      defaultMessage: "Patient Id",
                    })}
                    id={field.name}
                    className="inputText"
                    onChange={handlePatientId}
                  />
                )}
              </Field>
            </div>
            <div className="inlineDiv">
              <Field name="lastName">
                {({ field }) => (
                  <TextInput
                    name={field.name}
                    labelText={intl.formatMessage({
                      id: "patient.last.name",
                      defaultMessage: "Last Name",
                    })}
                    id={field.name}
                    className="inputText"
                    onChange={handleLastName}
                  />
                )}
              </Field>
              <Field name="firstName">
                {({ field }) => (
                  <TextInput
                    name={field.name}
                    labelText={intl.formatMessage({
                      id: "patient.first.name",
                      defaultMessage: "First Name",
                    })}
                    id={field.name}
                    className="inputText"
                    onChange={handleFirstName}
                  />
                )}
              </Field>
            </div>
            <div className="inlineDiv">
              <Field name="dateOfBirth">
                {({ field }) => (
                  <DatePicker
                    onChange={handleDatePickerChange}
                    name={field.name}
                    dateFormat="d/m/Y"
                    datePickerType="single"
                    light={true}
                    className="inputText"
                  >
                    <DatePickerInput
                      id="date-picker-default-id"
                      placeholder="dd/mm/yyyy"
                      labelText={intl.formatMessage({
                        id: "patient.dob",
                        defaultMessage: "Date of Birth",
                      })}
                      type="text"
                      name={field.name}
                    />
                  </DatePicker>
                )}
              </Field>
              <Field name="gender">
                {({ field }) => (
                  <RadioButtonGroup
                    className="inputText"
                    defaultSelected=""
                    legendText={intl.formatMessage({
                      id: "patient.gender",
                      defaultMessage: "Gender",
                    })}
                    name={field.name}
                    id="search_patient_gender"
                    onChange={handleGender}
                  >
                    <RadioButton
                      id="search-radio-1"
                      labelText={intl.formatMessage({
                        id: "patient.male",
                        defaultMessage: "Male",
                      })}
                      value="M"
                    />
                    <RadioButton
                      id="search-radio-2"
                      labelText={intl.formatMessage({
                        id: "patient.female",
                        defaultMessage: "Female",
                      })}
                      value="F"
                    />
                  </RadioButtonGroup>
                )}
              </Field>
            </div>
            <div className="formInlineDiv">
              <div className="searchActionButtons">
                <Button kind="tertiary">
                  <FormattedMessage
                    id="label.button.externalsearch"
                    defaultMessage="External Search"
                  />
                </Button>
                <Button type="submit">
                  <FormattedMessage
                    id="label.button.search"
                    defaultMessage="Search"
                  />
                </Button>
              </div>
            </div>
            <Column lg={16} md={8} sm={4}>
              {pagination && (
                <Grid>
                  <Column lg={11} />
                  <Column lg={2}>
                    <Button
                      type=""
                      id="loadpreviousresults"
                      onClick={loadPreviousResultsPage}
                      disabled={previousPage != null ? false : true}
                    >
                      <FormattedMessage id="button.label.loadprevious" />
                    </Button>
                  </Column>
                  <Column lg={2}>
                    <Button
                      type=""
                      id="loadnextresults"
                      disabled={nextPage != null ? false : true}
                      onClick={loadNextResultsPage}
                    >
                      <FormattedMessage id="button.label.loadnext" />
                    </Button>
                  </Column>
                </Grid>
              )}
            </Column>
            <div>
              <Column lg={16} md={8} sm={4}>
                <DataTable
                  rows={patientSearchResults}
                  headers={patientSearchHeaderData}
                  isSortable
                >
                  {({ rows, headers, getHeaderProps, getTableProps }) => (
                    <TableContainer title="Patient Results">
                      <Table {...getTableProps()}>
                        <TableHead>
                          <TableRow>
                            <TableHeader></TableHeader>
                            {headers.map((header) => (
                              <TableHeader
                                key={header.key}
                                {...getHeaderProps({ header })}
                              >
                                {header.header}
                              </TableHeader>
                            ))}
                          </TableRow>
                        </TableHead>
                        <TableBody>
                          <>
                            {rows
                              .slice((page - 1) * pageSize)
                              .slice(0, pageSize)
                              .map((row) => (
                                <TableRow key={row.id}>
                                  <TableCell>
                                    {" "}
                                    <RadioButton
                                      name="radio-group"
                                      onClick={patientSelected}
                                      labelText=""
                                      id={row.id}
                                    />
                                  </TableCell>
                                  {row.cells.map((cell) => (
                                    <TableCell key={cell.id}>
                                      {cell.value}
                                    </TableCell>
                                  ))}
                                </TableRow>
                              ))}
                          </>
                        </TableBody>
                      </Table>
                    </TableContainer>
                  )}
                </DataTable>
                <Pagination
                  onChange={handlePageChange}
                  page={page}
                  pageSize={pageSize}
                  pageSizes={[5, 10, 20, 30]}
                  totalItems={patientSearchResults.length}
                ></Pagination>
              </Column>
            </div>
            <br />
            <Grid fullWidth={true}>
              <Column lg={16} md={8} sm={4}>
                <h5>
                  <FormattedMessage id="report.enter.site.headline" />
                </h5>
              </Column>
            </Grid>
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
                  reportFormValues.referringSiteId != ""
                    ? reportFormValues.referringSiteId
                    : reportFormValues.referringSiteName
                }
                onChange={handleSiteName}
                onSelect={handleAutoCompleteSiteName}
                label={
                  <>
                    <FormattedMessage id="order.site.name" />
                  </>
                }
                class="inputText"
                style={{ width: "!important 100%" }}
                suggestions={siteNames.length > 0 ? siteNames : []}
              />

              <Select
                className="inputText"
                id="requesterDepartmentId"
                name="requesterDepartmentId"
                labelText={intl.formatMessage({
                  id: "order.department.label",
                  defaultMessage: "ward/dept/unit",
                })}
                onChange={handleRequesterDept}
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
            <Grid fullWidth={true}>
              <Column lg={16} md={8} sm={4}>
                <h6>
                  <FormattedMessage id="report.patient.site.description" />
                </h6>
              </Column>
            </Grid>
            <br />
            <Grid fullWidth={true}>
              <Column lg={8} md={8} sm={4}>
                <Checkbox
                  onChange={() => {
                    if (checkbox === "on") {
                      setResult("true");
                      setCheckbox("off");
                    } else {
                      setResult("false");
                      setCheckbox("on");
                    }
                  }}
                  labelText={intl.formatMessage({
                    id: "report.label.site.onlyResults",
                    defaultMessage: "Only Reports with results",
                  })}
                  id="checkbox-label-1"
                />
                <div className="inlineDiv">
                  <Dropdown
                    id="dateType"
                    name="dateType"
                    titleText="Date Type"
                    initialSelectedItem={itemList.find(
                      (item) => item.tag === items,
                    )}
                    label="Date Type"
                    items={itemList}
                    itemToString={(item) => (item ? item.text : "")}
                    onChange={(item) => {
                      setItems(item.selectedItem.tag);
                    }}
                  />
                </div>
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
                      handleDatePickerChangeDate("startDate", date)
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
                      handleDatePickerChangeDate("endDate", date)
                    }
                  />
                </div>
              </Column>
            </Grid>
            <div className="formInlineDiv">
              <div className="searchActionButtons">
                <Button type="button" onClick={handleReportPrint}>
                  <FormattedMessage id="label.button.generatePrintableVersion" />
                </Button>
              </div>
            </div>
          </Form>
        )}
      </Formik>
    </>
  );
}

export default injectIntl(PatientStatusReport);
