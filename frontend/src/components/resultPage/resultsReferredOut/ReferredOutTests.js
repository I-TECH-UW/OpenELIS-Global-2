import React, { useContext, useState, useRef, useEffect } from "react";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import "../../Style.css";
import { getFromOpenElisServer } from "../../utils/Utils";
import {
  Form,
  Dropdown,
  Heading,
  Grid,
  FilterableMultiSelect,
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
  Tag,
} from "@carbon/react";
import CustomLabNumberInput from "../../common/CustomLabNumberInput";
import config from "../../../config.json";
import { patientSearchHeaderData } from "../../data/PatientResultsTableHeaders";
import CustomDatePicker from "../../common/CustomDatePicker";
import PageBreadCrumb from "../../common/PageBreadCrumb";
import { ConfigurationContext } from "../../layout/Layout";
import { Formik, Field } from "formik";
import ReferredOutTestsFormValues from "../../formModel/innitialValues/ReferredOutTestsFormValues";
import { NotificationContext } from "../../layout/Layout";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification";

let breadcrumbs = [
  { label: "home.label", link: "/" },
  { label: "referral.label.referredOutTests", link: "/ReferredOutTests" },
];

function ReferredOutTests(props) {
  const [referredOutTestsFormValues, setReferredOutTestsFormValues] = useState(
    ReferredOutTestsFormValues
  );
  // const { referredOutTestsFormValues, setReferredOutTestsFormValues, getSelectedPatient, samples, error } = props;
  const { configurationProperties } = useContext(ConfigurationContext);
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  const intl = useIntl();
  const dateTypeList = [
    {
      id: "option-0",
      text: "Sent Date",
      tag: "SENT",
    },
    {
      id: "option-1",
      text: "Result Date",
      tag: "RESULT",
    },
  ];

  const componentMounted = useRef(false);
  const [testUnits, setTestUnits] = useState([]);
  const [testUnitsIdList, setTestUnitsIdList] = useState([]);
  const [testNames, setTestNames] = useState([]);
  const [testNamesIdList, setTestNamesIdList] = useState([]);
  const [dateType, setDateType] = useState(dateTypeList[0].tag);
  const [dob, setDob] = useState("");
  const [patientSearchResults, setPatientSearchResults] = useState([]);
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(5);
  const [loading, setLoading] = useState(false);
  const [nextPage, setNextPage] = useState(null);
  const [previousPage, setPreviousPage] = useState(null);
  const [pagination, setPagination] = useState(false);
  const [url, setUrl] = useState("");
  const [searchType, setSearchType] = useState("");
  const [innitialized, setInnitialized] = useState(false);
  const [tests, setTests] = useState([]);
  const [testSections, setTestSections] = useState([]);

  const handleReferredOutPatient = () => {
    let referredOutTestsOutPut =
      config.serverBaseUrl +
      `/rest/ReferredOutTests?searchType=${searchType}&dateType=${dateType}&startDate=${referredOutTestsFormValues.startDate}&endDate=${referredOutTestsFormValues.endDate}&${testUnitsIdList}_testUnitIds=1&${testNamesIdList}_testIds=1&labNumber=${referredOutTestsFormValues.labNumberInput}&dateOfBirthSearchValue=${referredOutTestsFormValues.dateOfBirth}&selPatient=${referredOutTestsFormValues.selectedPatientId}&_csrf=${localStorage.getItem("CSRF")}`;

    window.open(referredOutTestsOutPut);
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

  function handleLabNumberSearch(e) {
    setReferredOutTestsFormValues({
      ...referredOutTestsFormValues,
      labNumberInput: e.target.value,
    });
    setSearchType(referredOutTestsFormValues.searchTypeValues[1])
  }

  function handleLabNumber(e) {
    setReferredOutTestsFormValues({
      ...referredOutTestsFormValues,
      labNumber: e.target.value,
    });
    setSearchType(referredOutTestsFormValues.searchTypeValues[2])
  }

  function handlePatientId(e) {
    setReferredOutTestsFormValues({
      ...referredOutTestsFormValues,
      patientId: e.target.value,
    });
    setSearchType(referredOutTestsFormValues.searchTypeValues[2])
  }

  function handleLastName(e) {
    setReferredOutTestsFormValues({
      ...referredOutTestsFormValues,
      lastName: e.target.value,
    });
    setSearchType(referredOutTestsFormValues.searchTypeValues[2])
  }

  function handleFirstName(e) {
    setReferredOutTestsFormValues({
      ...referredOutTestsFormValues,
      firstName: e.target.value,
    });
    setSearchType(referredOutTestsFormValues.searchTypeValues[2])
  }

  function handleGender(e) {
    setReferredOutTestsFormValues({
      ...referredOutTestsFormValues,
      gender: e,
    });
    setSearchType(referredOutTestsFormValues.searchTypeValues[2])
  }

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

    setReferredOutTestsFormValues({
      ...referredOutTestsFormValues,
      dateOfBirth: updatedDate,
    });

    setDob(e[1]);
    setSearchType(referredOutTestsFormValues.searchTypeValues[2])
  };

  const handleDatePickerChangeDate = (datePicker, date) => {
    let updatedDate = encodeDate(date);
    let obj = null;
    switch (datePicker) {
      case "startDate":
        obj = {
          ...referredOutTestsFormValues,
          startDate: updatedDate,
        };
        break;
      case "endDate":
        obj = {
          ...referredOutTestsFormValues,
          endDate: updatedDate,
        };
        break;
      default:
        obj = {
          startDate: encodeDate(configurationProperties.currentDateAsText),
          endDate: encodeDate(configurationProperties.currentDateAsText),
        };
    }
    setReferredOutTestsFormValues({
      ...referredOutTestsFormValues,
      PatientStatusReportFormValues: obj,
    });
    setSearchType(referredOutTestsFormValues.searchTypeValues[0])
  };

  const patientSelected = (e) => {
    const patientSelected = patientSearchResults.find((patient) => {
      return patient.patientID == e.target.id;
    });

    setReferredOutTestsFormValues({
      ...referredOutTestsFormValues,
      selectedPatientId: e.target.id,
    });
    setSearchType(referredOutTestsFormValues.searchTypeValues[2])

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

  const fetchTestSections = (response) => {
    setTestSections(response);
  };

  const getTests = (tests) => {
    if (componentMounted.current) {
      setTests(tests);
    }
  };

  useEffect(() =>{
    if(testNames.testNames){
      var testNamesIdList = testNames.testNames.map((test) => ("testIds="+test.id+"&")).join("");
      setTestNamesIdList(testNamesIdList);
    }
    if(testUnits.testUnits){
      var testUnitsIdList = testUnits.testUnits.map((test) => ("testUnitIds="+test.id+"&")).join("");
      setTestUnitsIdList(testUnitsIdList);
    }
  },[testNames,testUnits])

  useEffect(() => {
    componentMounted.current = true;
    let testId = new URLSearchParams(window.location.search).get(
      "selectedTest"
    );
    testId = testId ? testId : "";
    getFromOpenElisServer("/rest/test-list", (fetchedTests) => {
      let test = fetchedTests.find((test) => test.id === testId);
      let testLabel = test ? test.value : "";
      setTestUnits(testLabel);
      getTests(fetchedTests);
    });

    let testSectionId = new URLSearchParams(window.location.search).get(
      "testSectionId"
    );
    testSectionId = testSectionId ? testSectionId : "";
    getFromOpenElisServer("/rest/user-test-sections", (fetchedTestSections) => {
      let testSection = fetchedTestSections.find(
        (testSection) => testSection.id === testSectionId
      );
      let testSectionLabel = testSection ? testSection.value : "";
      setTestNames(testSectionLabel);
      fetchTestSections(fetchedTestSections);
    });
    if (testSectionId) {
      let values = { unitType: testSectionId };
      querySearch(values);
    }
  }, []);

  useEffect(() => {
    let patientId = new URLSearchParams(window.location.search).get(
      "patientId"
    );
    if (patientId) {
      let searchValues = {
        ...referredOutTestsFormValues,
        patientId: patientId,
      };
      setReferredOutTestsFormValues(searchValues);
      handleSubmit(searchValues);
    }
  }, [referredOutTestsFormValues]);

  useEffect(() => {
    if (!innitialized) {
      let updatedDate = encodeDate(configurationProperties.currentDateAsText);
      setReferredOutTestsFormValues({
        ...referredOutTestsFormValues,
        dateOfBirth: updatedDate,
        startDate: updatedDate,
        endDate: updatedDate,
      });
    }
    if (referredOutTestsFormValues.dateOfBirth != "") {
      setInnitialized(true);
    }
  }, [
    referredOutTestsFormValues,
    innitialized,
    configurationProperties.currentDateAsText,
  ]);

  return (
    <>
      <PageBreadCrumb breadcrumbs={breadcrumbs} />
      <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Section>
              <Heading>
                <FormattedMessage id="referral.out.head" />
              </Heading>
            </Section>
          </Section>
        </Column>
      </Grid>

      {notificationVisible === true ? <AlertDialog /> : ""}
      {loading && <Loading />}
      <div className="orderLegendBody">
        <Formik
          initialValues={referredOutTestsFormValues}
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
                    <div className="inlineDiv">
                      <h5 style={{ paddingTop: "10px", paddingRight: "6px" }}>
                        <FormattedMessage id="referral.out.request" />
                      </h5>
                      <Dropdown
                        id="dateType"
                        name="dateType"
                        initialSelectedItem={dateTypeList.find(
                          (item) => item.tag === dateType
                        )}
                        items={dateTypeList}
                        itemToString={(item) => (item ? item.text : "")}
                        onChange={(item) => {
                          setSearchType(referredOutTestsFormValues.searchTypeValues[0]);
                          setDateType(item.selectedItem.tag);
                        }}
                      />
                      <h5 style={{ paddingTop: "10px", paddingLeft: "6px" }}>
                        <FormattedMessage id="referral.out.note" />
                      </h5>
                    </div>
                    <div className="formInlineDiv">
                      <CustomDatePicker
                        id={"startDate"}
                        labelText={intl.formatMessage({
                          id: "eorder.date.start",
                          defaultMessage: "Start Date",
                        })}
                        autofillDate={true}
                        value={referredOutTestsFormValues.startDate}
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
                        value={referredOutTestsFormValues.endDate}
                        onChange={(date) =>
                          handleDatePickerChangeDate("endDate", date)
                        }
                      />
                    </div>
                    <br/>
                    <div className="formInlineDiv">
                      <Grid fullWidth={true}>
                        <Column lg={16} md={8} sm={4}>
                          <FilterableMultiSelect
                            id="testunits"
                            titleText={
                              <FormattedMessage id="search.label.testunit" />
                            }
                            items={testSections}
                            itemToString={(item) => (item ? item.value : "")}
                            onChange={(changes) => {
                              setTestUnits({
                                ...testUnits,
                                testUnits: changes.selectedItems,
                              });
                            }}
                            selectionFeedback="top-after-reopen"
                          />
                        </Column>
                        <br/>
                        <Column lg={16} md={8} sm={4}>
                          {testUnits.testUnits &&
                            testUnits.testUnits.map((test, index) => (
                              <Tag
                                key={index}
                                filter
                                onClose={() => {
                                  var info = { ...testUnits };
                                  info["testUnits"].splice(index, 1);
                                  setTestUnits(info);
                                }}
                              >
                                {test.value}
                              </Tag>
                            ))}
                        </Column>
                      </Grid>
                      <Grid fullWidth={true}>
                        <Column lg={16} md={8} sm={4}>
                          <FilterableMultiSelect
                            id="testnames"
                            titleText={
                              <FormattedMessage id="search.label.test" />
                            }
                            items={tests}
                            itemToString={(item) => (item ? item.value : "")}
                            onChange={(changes) => {
                              setTestNames({
                                ...testNames,
                                testNames: changes.selectedItems,
                              });
                            }}
                            selectionFeedback="top-after-reopen"
                          />
                        </Column>
                        <br/>
                        <Column lg={16} md={8} sm={4}>
                          {testNames.testNames &&
                            testNames.testNames.map((test, index) => (
                              <Tag
                                key={index}
                                filter
                                onClose={() => {
                                  var info = { ...testNames };
                                  info["testNames"].splice(index, 1);
                                  setTestNames(info);
                                }}
                              >
                                {test.value}
                              </Tag>
                            ))}
                        </Column>
                      </Grid>
                    </div>
                    <div className="formInlineDiv">
                      <div className="searchActionButtons">
                        <Button
                          type="button"
                          onClick={handleReferredOutPatient}
                        >
                          <FormattedMessage
                            id="referral.button.unitTestSearch"
                            defaultMessage="Search Referrals By Unit(s) & Test(s)"
                          />
                        </Button>
                      </div>
                    </div>
                  </Section>
                </Column>
              </Grid>
              <hr />
              <Grid fullWidth={true}>
                <Column lg={16} md={8} sm={4}>
                  <Section>
                    <div className="formInlineDiv">
                      <h5>
                        <FormattedMessage id="referral.result.labNumber" />
                      </h5>
                    </div>
                    <br />
                    <Field name="labNumberInput">
                      {({ field }) => (
                        <CustomLabNumberInput
                          name={field.name}
                          labelText={intl.formatMessage({
                            id: "referral.input",
                            defaultMessage: "Scan OR Enter Manually",
                          })}
                          id={field.name}
                          className="inputText"
                          value={values[field.name]}
                          onChange={(e, rawValue) => {
                            setFieldValue(field.name, rawValue);
                            setSearchType(referredOutTestsFormValues.searchTypeValues[1]);
                            handleLabNumberSearch(e);
                          }}
                        />
                      )}
                    </Field>
                    <br />
                    <div className="formInlineDiv">
                      <div className="searchActionButtons">
                        <Button
                          type="button"
                          onClick={handleReferredOutPatient}
                        >
                          <FormattedMessage
                            id="referral.button.labSearch"
                            defaultMessage="Search Referrals By Lab Number"
                          />
                        </Button>
                      </div>
                    </div>
                  </Section>
                </Column>
              </Grid>
              <hr />
              <Grid fullWidth={true}>
                <Column lg={16} md={8} sm={4}>
                  <Section>
                    <h5>
                      <FormattedMessage id="referral.search" />
                    </h5>
                    <div className="formInlineDiv">
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
                        <Button type="submit">
                          <FormattedMessage
                            id="referral.button.patientSearch"
                            defaultMessage="Search Patient"
                          />
                        </Button>
                      </div>
                    </div>
                  </Section>
                </Column>
              </Grid>
              <Column lg={16}>
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
              </div>
              <div className="formInlineDiv">
                <div className="searchActionButtons">
                  <Button type="button" onClick={handleReferredOutPatient}>
                    <FormattedMessage
                      id="referral.main.button"
                      defaultMessage="Search Referrals By Patient"
                    />
                  </Button>
                </div>
              </div>
              <hr />
            </Form>
          )}
        </Formik>
      </div>
    </>
  );
}

export default injectIntl(ReferredOutTests);
