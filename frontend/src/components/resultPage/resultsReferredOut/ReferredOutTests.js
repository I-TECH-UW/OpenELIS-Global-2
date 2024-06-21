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
  Button,
  Loading,
  Tag,
} from "@carbon/react";
import CustomLabNumberInput from "../../common/CustomLabNumberInput";
import config from "../../../config.json";
import CustomDatePicker from "../../common/CustomDatePicker";
import PageBreadCrumb from "../../common/PageBreadCrumb";
import { ConfigurationContext } from "../../layout/Layout";
import { Formik, Field } from "formik";
import ReferredOutTestsFormValues from "../../formModel/innitialValues/ReferredOutTestsFormValues";
import { NotificationContext } from "../../layout/Layout";
import SearchPatientForm from "../../patient/SearchPatientForm";
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
    ReferredOutTestsFormValues,
  );
  const { configurationProperties } = useContext(ConfigurationContext);
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  const intl = useIntl();
  const dateTypeList = [
    {
      id: "option-0",
      text: "Sent Date",
      value: "SENT",
    },
    {
      id: "option-1",
      text: "Result Date",
      value: "RESULT",
    },
  ];

  const componentMounted = useRef(false);
  const [testUnits, setTestUnits] = useState([]);
  const [testUnitsIdList, setTestUnitsIdList] = useState([]);
  const [testUnitsValuesList, setTestUnitsValuesList] = useState([]);
  const [testUnitsPair, setTestUnitsPair] = useState([]);
  const [testNames, setTestNames] = useState([]);
  const [testNamesIdList, setTestNamesIdList] = useState([]);
  const [testNamesValuesList, setTestNamesValuesList] = useState([]);
  const [testNamesPair, setTestNamesPair] = useState([]);
  const [dateType, setDateType] = useState(dateTypeList[0].value);
  const [loading, setLoading] = useState(false);
  const [searchType, setSearchType] = useState("");
  const [innitialized, setInnitialized] = useState(false);
  const [tests, setTests] = useState([]);
  const [testSections, setTestSections] = useState([]);
  const [responseData, setResponseData] = useState({});
  const [responseDataShow, setResponseDataShow] = useState([]);

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer(
      `/rest/ReferredOutTests?searchType=${searchType}&dateType=${dateType}&startDate=${referredOutTestsFormValues.startDate}&endDate=${referredOutTestsFormValues.endDate}&testUnitIds=${testUnitsIdList}&_testUnitIds=1&testIds=${testNamesIdList}&_testIds=1&labNumber=${referredOutTestsFormValues.labNumberInput}&dateOfBirthSearchValue=&_analysisIds=on`,
      handleResponseData,
    );
    return () => {
      componentMounted.current = false;
    };
  }, [
    searchType,
    dateType,
    referredOutTestsFormValues.startDate,
    referredOutTestsFormValues.endDate,
    testUnitsIdList,
    testNamesIdList,
    referredOutTestsFormValues.labNumberInput,
  ]);

  const handleReferredOutPatient = () => {
    setLoading(true);
    getFromOpenElisServer(
      `/rest/ReferredOutTests?searchType=${searchType}&dateType=${dateType}&startDate=${referredOutTestsFormValues.startDate}&endDate=${referredOutTestsFormValues.endDate}&testUnitIds=${testUnitsIdList}&_testUnitIds=1&testIds=${testNamesIdList}&_testIds=1&labNumber=${referredOutTestsFormValues.labNumberInput}&dateOfBirthSearchValue=&_analysisIds=on`,
      handleResponseData,
    );
    setLoading(false);
  };

  const handleResponseData = (res) => {
    if (!res) {
      setLoading(true);
    } else {
      setResponseData(res);
    }
  };

  useEffect(() => {
    if (responseData) {
      setResponseDataShow(responseData.referralDisplayItems);
    }
  }, [responseData]);

  const handleSubmit = () => {
    setLoading(true);
    handleReferredOutPatient();
    setLoading(false);
  };

  function handleLabNumberSearch(e) {
    setReferredOutTestsFormValues({
      ...referredOutTestsFormValues,
      labNumberInput: e.target.value,
    });
    setSearchType(referredOutTestsFormValues.searchTypeValues[1]);
  }

  const getSelectedPatient = (patient) => {
    setSearchType(referredOutTestsFormValues.searchTypeValues[2]);
    setReferredOutTestsFormValues({
      ...referredOutTestsFormValues,
      selectedPatientId: patient.patientPK,
    });
  };

  const getDataOfBirth = (patient) => {
    searchType(referredOutTestsFormValues.searchTypeValues[2]);
    setReferredOutTestsFormValues({
      ...referredOutTestsFormValues,
      dateOfBirth: patient.dateOfBirth,
    });
  };

  const handleDatePickerChangeDate = (datePicker, date) => {
    let updatedDate = date;
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
          // startDate: configurationProperties.currentDateAsText,
          // endDate: configurationProperties.currentDateAsText,
          startDate: "",
          endDate: "",
        };
    }
    setReferredOutTestsFormValues({
      ...referredOutTestsFormValues,
      PatientStatusReportFormValues: obj,
    });
    setSearchType(referredOutTestsFormValues.searchTypeValues[0]);
  };

  const fetchTestSections = (response) => {
    setTestSections(response);
  };

  const getTests = (tests) => {
    if (componentMounted.current) {
      setTests(tests);
    }
  };

  useEffect(() => {
    if (testNames.testNames) {
      var testNamesIdList = testNames.testNames.map((test) => test.id);
      setTestNamesIdList(testNamesIdList);
      var testNamesValueList = testNames.testNames.map((test) => test.value);
      setTestNamesValuesList(testNamesValueList);
      var testNamesPair = testNames.testNames.map((test) => ({
        id: test.id,
        value: test.value,
      }));
      setTestNamesPair(testNamesPair);
    }
    if (testUnits.testUnits) {
      var testUnitsIdList = testUnits.testUnits.map((test) => test.id);
      setTestUnitsIdList(testUnitsIdList);
      var testUnitsValueList = testUnits.testUnits.map((test) => test.value);
      setTestUnitsValuesList(testUnitsValueList);
      var testUnitsPair = testUnits.testUnits.map((test) => ({
        id: test.id,
        value: test.value,
      }));
      setTestUnitsPair(testUnitsPair);
    }
  }, [testNames, testUnits]);

  useEffect(() => {
    componentMounted.current = true;
    let testId = new URLSearchParams(window.location.search).get(
      "selectedTest",
    );
    testId = testId ? testId : "";
    getFromOpenElisServer("/rest/test-list", (fetchedTests) => {
      let test = fetchedTests.find((test) => test.id === testId);
      let testLabel = test ? test.value : "";
      setTestUnits(testLabel);
      getTests(fetchedTests);
    });

    let testSectionId = new URLSearchParams(window.location.search).get(
      "testSectionId",
    );
    testSectionId = testSectionId ? testSectionId : "";
    getFromOpenElisServer("/rest/user-test-sections", (fetchedTestSections) => {
      let testSection = fetchedTestSections.find(
        (testSection) => testSection.id === testSectionId,
      );
      let testSectionLabel = testSection ? testSection.value : "";
      setTestNames(testSectionLabel);
      fetchTestSections(fetchedTestSections);
    });
    // if (testSectionId) {
    //   let values = { unitType: testSectionId };
    //   querySearch(values);
    // }
  }, []);

  useEffect(() => {
    let patientId = new URLSearchParams(window.location.search).get(
      "patientId",
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
      // let updatedDate = encodeDate(configurationProperties.currentDateAsText);
      let updatedDate = configurationProperties.currentDateAsText;
      setReferredOutTestsFormValues({
        ...referredOutTestsFormValues,
        dateOfBirth: updatedDate,
        startDate: "",
        endDate: "",
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
        <Grid fullWidth={true}>
          <Column lg={16} md={8} sm={4}>
            <Section>
              <div className="formInlineDiv">
                <h5>
                  <FormattedMessage id="referral.main.button" />
                </h5>
              </div>
              <br />
              <SearchPatientForm
                getSelectedPatient={getSelectedPatient}
                getDataOfBirth={getDataOfBirth}
              ></SearchPatientForm>
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
            </Section>
          </Column>
        </Grid>
        <hr />
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
                        id={"dateType"}
                        name="dateType"
                        label="Date Type"
                        initialSelectedItem={dateTypeList.find(
                          (item) => item.tag === dateType,
                        )}
                        items={dateTypeList}
                        itemToString={(item) => (item ? item.text : "")}
                        onChange={(item) => {
                          setSearchType(
                            referredOutTestsFormValues.searchTypeValues[0],
                          );
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
                    <br />
                    <div className="formInlineDiv">
                      <Grid fullWidth={true}>
                        <Column lg={16} md={8} sm={4}>
                          <FilterableMultiSelect
                            id="testunits"
                            titleText={intl.formatMessage({
                              id: "search.label.testunit",
                              defaultMessage: "Select Test Unit",
                            })}
                            items={testSections}
                            itemToString={(item) => (item ? item.value : "")}
                            onChange={(changes) => {
                              setTestUnits({
                                ...testUnits,
                                testUnits: changes.selectedItems,
                              });
                              setSearchType(
                                referredOutTestsFormValues.searchTypeValues[0],
                              );
                            }}
                            selectionFeedback="top-after-reopen"
                          />
                        </Column>
                        <br />
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
                            titleText={intl.formatMessage({
                              id: "search.label.test",
                              defaultMessage: "Select Test Name",
                            })}
                            items={tests}
                            itemToString={(item) => (item ? item.value : "")}
                            onChange={(changes) => {
                              setTestNames({
                                ...testNames,
                                testNames: changes.selectedItems,
                              });
                              setSearchType(
                                referredOutTestsFormValues.searchTypeValues[0],
                              );
                            }}
                            selectionFeedback="top-after-reopen"
                          />
                        </Column>
                        <br />
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
                            setSearchType(
                              referredOutTestsFormValues.searchTypeValues[1],
                            );
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
            </Form>
          )}
        </Formik>
        <br />
        <Grid fullWidth={true}>
          <Column lg={16} md={8} sm={4}>
            <span>
              <FormattedMessage id="referral.matching.search" /> :
            </span>{" "}
            <Button kind="tertiary" type="button">
              <FormattedMessage
                id="referral.print.selected.patient.reports"
                defaultMessage="Print Selected Patient Reports"
              />
            </Button>{" "}
            <Button kind="tertiary" type="button">
              <FormattedMessage
                id="referral.print.selected.patient.reports.selectnone.button"
                defaultMessage="Select None"
              />
            </Button>{" "}
            <Button kind="tertiary" type="button">
              <FormattedMessage
                id="referral.print.selected.patient.reports.selectall.button"
                defaultMessage="Select All"
              />
            </Button>
          </Column>
        </Grid>
        <br />
        <Grid fullWidth={true} className="gridBoundary">
          <Column lg={16} md={8} sm={4}>
            <br />
            {/* <DataTable
              rows={typeOfActivityShow.slice(
                (page - 1) * pageSize,
                page * pageSize,
              )}
              headers={[
                {
                  key: "select",
                  header: intl.formatMessage({
                    id: "organization.type.CI.select",
                  }),
                },
                {
                  key: "resultDate",
                  header: intl.formatMessage({
                    id: "referral.search.column.resultDate",
                  }),
                },
                {
                  key: "labNumber",
                  header: intl.formatMessage({
                    id: "sample.label.labnumber",
                  }),
                },
                {
                  key: "sentDate",
                  header: intl.formatMessage({
                    id: "referral.search.column.sentDate",
                  }),
                },
                {
                  key: "status",
                  header: intl.formatMessage({
                    id: "label.filters.status",
                  }),
                },
                {
                  key: "lastName",
                  header: intl.formatMessage({
                    id: "eorder.name.last",
                  }),
                },
                {
                  key: "firstName",
                  header: intl.formatMessage({
                    id: "eorder.name.first",
                  }),
                },
                {
                  key: "testName",
                  header: intl.formatMessage({
                    id: "eorder.test.name",
                  }),
                },
                {
                  key: "result",
                  header: intl.formatMessage({
                    id: "column.name.result",
                  }),
                },
                {
                  key: "referenceLab",
                  header: intl.formatMessage({
                    id: "referral.search.column.referenceLab",
                  }),
                },
                {
                  key: "notes",
                  header: intl.formatMessage({
                    id: "column.name.notes",
                  }),
                },
              ]}
            >
              {({
                rows,
                headers,
                getHeaderProps,
                getTableProps,
                getSelectionProps,
              }) => (
                <TableContainer>
                  <Table {...getTableProps()}>
                    <TableHead>
                      <TableRow>
                        <TableSelectAll
                          id="table-select-all"
                          {...getSelectionProps()}
                          checked={
                            selectedRowIds.length === pageSize &&
                            typeOfActivityShow
                              .slice((page - 1) * pageSize, page * pageSize)
                              .filter(
                                (row) =>
                                  !row.disabled &&
                                  selectedRowIds.includes(row.id),
                              ).length === pageSize
                          }
                          indeterminate={
                            selectedRowIds.length > 0 &&
                            selectedRowIds.length <
                              typeOfActivityShow
                                .slice((page - 1) * pageSize, page * pageSize)
                                .filter((row) => !row.disabled).length
                          }
                          onSelect={() => {
                            setSaveButton(false);
                            const currentPageIds = typeOfActivityShow
                              .slice((page - 1) * pageSize, page * pageSize)
                              .filter((row) => !row.disabled)
                              .map((row) => row.id);
                            if (
                              selectedRowIds.length === pageSize &&
                              currentPageIds.every((id) =>
                                selectedRowIds.includes(id),
                              )
                            ) {
                              setSelectedRowIds([]);
                            } else {
                              setSelectedRowIds(
                                currentPageIds.filter(
                                  (id) => !selectedRowIds.includes(id),
                                ),
                              );
                            }
                          }}
                        />
                        {headers.map(
                          (header) =>
                            header.key !== "select" && (
                              <TableHeader
                                key={header.key}
                                {...getHeaderProps({ header })}
                              >
                                {header.header}
                              </TableHeader>
                            ),
                        )}
                      </TableRow>
                    </TableHead>
                    <TableBody>
                      <>
                        {rows.map((row) => (
                          <TableRow
                            key={row.id}
                            onClick={() => {
                              const id = row.id;
                              const isSelected = selectedRowIds.includes(id);
                              if (isSelected) {
                                setSelectedRowIds(
                                  selectedRowIds.filter(
                                    (selectedId) => selectedId !== id,
                                  ),
                                );
                              } else {
                                setSelectedRowIds([...selectedRowIds, id]);
                              }
                            }}
                          >
                            {row.cells.map((cell) => renderCell(cell, row))}
                          </TableRow>
                        ))}
                      </>
                    </TableBody>
                  </Table>
                </TableContainer>
              )}
            </DataTable> */}
            {/* <Pagination
                onChange={handlePageChange}
                page={page}
                pageSize={pageSize}
                pageSizes={[5, 10, 20]}
                totalItems={typeOfActivityShow.length}
                forwardText={intl.formatMessage({
                  id: "pagination.forward",
                })}
                backwardText={intl.formatMessage({
                  id: "pagination.backward",
                })}
                itemRangeText={(min, max, total) =>
                  intl.formatMessage(
                    { id: "pagination.item-range" },
                    { min: min, max: max, total: total },
                  )
                }
                itemsPerPageText={intl.formatMessage({
                  id: "pagination.items-per-page",
                })}
                itemText={(min, max) =>
                  intl.formatMessage(
                    { id: "pagination.item" },
                    { min: min, max: max },
                  )
                }
                pageNumberText={intl.formatMessage({
                  id: "pagination.page-number",
                })}
                pageRangeText={(_current, total) =>
                  intl.formatMessage(
                    { id: "pagination.page-range" },
                    { total: total },
                  )
                }
                pageText={(page, pagesUnknown) =>
                  intl.formatMessage(
                    { id: "pagination.page" },
                    { page: pagesUnknown ? "" : page },
                  )
                }
              /> */}
            <br />
          </Column>
        </Grid>
      </div>
      <div>
        <button
          onClick={() => {
            console.error(responseData);
          }}
        >
          responseData
        </button>
      </div>
    </>
  );
}

export default injectIntl(ReferredOutTests);
