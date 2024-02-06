import React, { useContext, useState, useEffect } from "react";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import "../Style.css";
import { getFromOpenElisServer } from "../utils/Utils";
import {
  Form,
  FormLabel,
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
  DataTable,
  TableContainer,
  Table,
  TableHead,
  TableRow,
  TableHeader,
  TableBody,
  TableCell,
  Pagination,
  Loading,
  Select,
  SelectItem,
} from "@carbon/react";
import CustomLabNumberInput from "../common/CustomLabNumberInput";
import CustomDatePicker from "../common/CustomDatePicker";
import AutoComplete from "../common/AutoComplete";
import { ConfigurationContext } from "../layout/Layout";
import { patientSearchHeaderData } from "../data/PatientResultsTableHeaders";
import { Formik, Field } from "formik";
import SearchPatientFormValues from "../formModel/innitialValues/SearchPatientFormValues";
import { NotificationContext } from "../layout/Layout";
import { AlertDialog, NotificationKinds } from "../common/CustomNotification";

function SelectReportValues(props) {
  const [orderFormValues, setOrderFormValues] = useState({
    sampleOrderItems: {},
    // other state properties
  });
  // const { orderFormValues, setOrderFormValues, samples } = props;
  const { configurationProperties } = useContext(ConfigurationContext);
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  const intl = useIntl();
  const items = [
    {
      id: "option-0",
      text: "Select Date Type",
    },
    {
      id: "option-1",
      text: "Result Date",
    },
    {
      id: "option-2",
      text: "Order Date",
    },
  ];

  const [dob, setDob] = useState("");
  const [patientSearchResults, setPatientSearchResults] = useState([]);
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(5);
  const [loading, setLoading] = useState(false);
  const [nextPage, setNextPage] = useState(null);
  const [previousPage, setPreviousPage] = useState(null);
  const [pagination, setPagination] = useState(false);
  const [url, setUrl] = useState("");
  const [searchFormValues, setSearchFormValues] = useState(
    SearchPatientFormValues
  );
  const [innitialized, setInnitialized] = useState(false);
  const [allowSiteNameOptions, setAllowSiteNameOptions] = useState("false");
  const [departments, setDepartments] = useState([]);
  const [allowRequesterOptions, setAllowRequesterOptions] = useState("false");

  function handleSiteName(e) {
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        referringSiteName: e.target.value,
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
      loadDepartments
    );
  }
  const loadDepartments = (data) => {
    setDepartments(data);
  };

  const handleSubmit = (values) => {
    setLoading(true);
    values.dateOfBirth = dob;
    const searchEndPoint =
      "/rest/patient-search-results?" +
      "lastName=" +
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
    setDob(e[1]);
  };

  const handleDatePickerChangeDate = (datePicker, date) => {
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

  const patientSelected = (e) => {
    const patientSelected = patientSearchResults.find((patient) => {
      return patient.patientID == e.target.id;
    });
    const searchEndPoint =
      "/rest/patient-details?patientID=" + patientSelected.patientID;
    getFromOpenElisServer(searchEndPoint, fetchPatientDetails);
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
    let patientId = new URLSearchParams(window.location.search).get(
      "patientId"
    );
    if (patientId) {
      let searchValues = {
        ...searchFormValues,
        patientId: patientId,
      };
      setAllowSiteNameOptions(
        configurationProperties.restrictFreeTextRefSiteEntry
      );
      setSearchFormValues(searchValues);
      handleSubmit(searchValues);
    }
  }, []);

  useEffect(() => {
    if (!innitialized) {
      setOrderFormValues({
        ...orderFormValues,
        sampleOrderItems: {
          ...orderFormValues.sampleOrderItems,
          startDate: configurationProperties.currentDateAsText,
          endDate: configurationProperties.currentDateAsText,
        },
      });
      setAllowSiteNameOptions(
        configurationProperties.restrictFreeTextRefSiteEntry
      );
      setAllowRequesterOptions(
        configurationProperties.restrictFreeTextProviderEntry
      );
    }
    if (orderFormValues.sampleOrderItems.requestDate != "") {
      setInnitialized(true);
    }
  }, [orderFormValues]);

  return (
    <>
      <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Section>
              <Heading>
                <FormattedMessage id="selectReportValues.title" />
              </Heading>
            </Section>
          </Section>
        </Column>
      </Grid>
      <div className="orderLegendBody">
        <FormLabel>
          <Section>
            <Section>
              <Section>
                <Heading>
                  <FormattedMessage id="openreports.patientTestStatus" />
                </Heading>
              </Section>
            </Section>
          </Section>
        </FormLabel>

        {notificationVisible === true ? <AlertDialog /> : ""}
        {loading && <Loading />}
        <Formik
          initialValues={searchFormValues}
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
              <div>
                <h5>
                  <FormattedMessage id="report.enter.labNumber.headline" />
                </h5>
                <h6>
                  <span>
                    <FormattedMessage id="sample.search.scanner.instructions" />
                    <FormattedMessage id="sample.search.scanner.instructions.highaccession" />
                  </span>
                </h6>
              </div>
              {/* css edit needed */}
              <div className="inlineDiv">
                <Field name="From">
                  {({ field }) => (
                    <TextInput
                      name={field.name}
                      value={values[field.name]}
                      labelText={intl.formatMessage({
                        id: "from.title",
                        defaultMessage: "From",
                      })}
                      id={field.name}
                      className="inputText"
                    />
                  )}
                </Field>
                <Field name="To">
                  {({ field }) => (
                    <CustomLabNumberInput
                      name={field.name}
                      labelText={intl.formatMessage({
                        id: "to.title",
                        defaultMessage: "To",
                      })}
                      id={field.name}
                      className="inputText"
                      value={values[field.name]}
                      onChange={(e, rawValue) => {
                        setFieldValue(field.name, rawValue);
                      }}
                    />
                  )}
                </Field>
              </div>
              <div>
                <h5>
                  <FormattedMessage id="report.enter.patient.headline" />
                </h5>
                <h6>
                  <FormattedMessage id="report.enter.patient.headline.description" />
                </h6>
              </div>
              <span className="inlineDiv">
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
                    />
                  )}
                </Field>
                <Field name="labNumber">
                  {({ field }) => (
                    <CustomLabNumberInput
                      name={field.name}
                      labelText={intl.formatMessage({
                        id: "patient.prev.lab.no",
                        defaultMessage: "Previous Lab Number",
                      })}
                      id={field.name}
                      className="inputText"
                      value={values[field.name]}
                      onChange={(e, rawValue) => {
                        setFieldValue(field.name, rawValue);
                      }}
                    />
                  )}
                </Field>
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
                    />
                  )}
                </Field>
              </span>
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
              <div>
                <h5>
                  <FormattedMessage id="report.enter.site.headline" />
                </h5>
              </div>
              <div className="inlineDiv">
                {allowSiteNameOptions === "false" ? (
                  <TextInput
                    name="siteName"
                    labelText={intl.formatMessage({ id: "order.site.name" })}
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
                    label={intl.formatMessage({ id: "order.search.site.name" })}
                    class="inputText"
                    invalidText={intl.formatMessage({
                      id: "order.invalid.site.name",
                    })}
                    style={{ width: "!important 100%" }}
                    suggestions={siteNames.length > 0 ? siteNames : []}
                    required
                  />
                )}

                <Select
                  className="inputText"
                  id="requesterDepartmentId"
                  name="requesterDepartmentId"
                  labelText={intl.formatMessage({
                    id: "order.department.label",
                  })}
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
                    label={intl.formatMessage({
                      id: "order.search.requester.label",
                    })}
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
              <h6>
                <FormattedMessage id="report.patient.site.description" />
              </h6>
              <div>
                <Checkbox
                  onChange={(data) => {}}
                  labelText={intl.formatMessage({
                    id: "report.label.site.onlyResults",
                  })}
                  id="checkbox-label-1"
                />
              </div>
              <div className="inlineDiv">
                <Dropdown
                  id="default"
                  titleText="Date Type"
                  initialSelectedItem={items[0]}
                  label="Option 1"
                  items={items}
                  itemToString={(item) => (item ? item.text : "")}
                />
              </div>
              <div className="inlineDiv">
                <CustomDatePicker
                  id={"order_requestDate"}
                  labelText={intl.formatMessage({ id: "eorder.date.start" })}
                  autofillDate={true}
                  value={orderFormValues.sampleOrderItems.startDate}
                  className="inputDate"
                  onChange={(date) =>
                    handleDatePickerChangeDate("startDate", date)
                  }
                />
                <CustomDatePicker
                  id={"order_receivedDate"}
                  labelText={intl.formatMessage({ id: "eorder.date.end" })}
                  className="inputDate"
                  autofillDate={true}
                  value={orderFormValues.sampleOrderItems.endDate}
                  onChange={(date) =>
                    handleDatePickerChangeDate("endDate", date)
                  }
                />
              </div>
              {/* <Column lg={16}>
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
          <Column lg={16}>
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
        </div> */}
              <div className="formInlineDiv">
                <div className="searchActionButtons">
                  <Button type="submit">
                    <FormattedMessage id="label.button.generatePrintableVersion" />
                  </Button>
                </div>
              </div>
            </Form>
          )}
        </Formik>
      </div>
    </>
  );
}

export default injectIntl(SelectReportValues);
