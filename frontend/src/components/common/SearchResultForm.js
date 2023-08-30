import React, { useContext, useEffect, useState, useRef } from "react";
import { FormattedMessage, injectIntl } from "react-intl";
import "../Style.css";
import { getFromOpenElisServer, postToOpenElisServer } from "../utils/Utils";
import {
  Heading,
  Form,
  FormLabel,
  TextInput,
  TextArea,
  Checkbox,
  Button,
  Grid,
  Column,
  DatePicker,
  DatePickerInput,
  Stack,
  Section,
  Pagination,
  Select,
  SelectItem,
  Row,
  Loading,
} from "@carbon/react";
import DataTable from "react-data-table-component";
import { Formik, Field } from "formik";
import SearchResultFormValues from "../formModel/innitialValues/SearchResultFormValues";
import { AlertDialog, NotificationKinds } from "../common/CustomNotification";
import { NotificationContext } from "../layout/Layout";

function ResultSearchPage() {
  const [resultForm, setResultForm] = useState({ testResult: [] });

  return (
    <>
      <SearchResultForm setResults={setResultForm} />
      <SearchResults results={resultForm} />
    </>
  );
}

export function SearchResultForm(props) {
  const { notificationVisible, setNotificationVisible, setNotificationBody } =
    useContext(NotificationContext);

  const [doRange, setDoRange] = useState(true);
  const [finished, setFinished] = useState(true);
  const [tests, setTests] = useState([]);
  const [analysisStatusTypes, setAnalysisStatusTypes] = useState([]);
  const [sampleStatusTypes, setSampleStatusTypes] = useState([]);
  const [loading, setLoading] = useState(false);
  const [searchBy, setSearchBy] = useState("");

  const componentMounted = useRef(false);

  const setResultsWithId = (results) => {
    if (results) {
      var i = 0;
      if (results.testResult) {
        results.testResult.forEach((item) => (item.id = "" + i++));
      }
      props.setResults?.(results);
      setLoading(false);
    } else {
      props.setResults?.({ testResult: [] });
      setNotificationBody({
        title: "Notification Message",
        message: "No results found!",
        kind: NotificationKinds.warning,
      });
      setNotificationVisible(true);
      setLoading(false);
    }
  };

  const handleAdvancedSearch = () => {};

  const handleDoRangeChange = () => {
    console.log("handleDoRangeChange:");
    setDoRange(!doRange);
  };

  const handleFinishedChange = () => {
    console.log("handleFinishedChange:");
    setFinished(!finished);
  };

  const handleSubmit = (values) => {
    setLoading(true);
    props.setResults?.({ testResult: [] });
    var searchEndPoint =
      "/rest/ReactLogbookResultsByRange?" +
      "labNumber=" +
      values.accessionNumber +
      "&nationalId=" +
      values.nationalId +
      "&firstName=" +
      values.firstName +
      "&lastName=" +
      values.lastName +
      "&collectionDate=" +
      values.collectionDate +
      "&recievedDate=" +
      values.recievedDate +
      "&selectedTest=" +
      values.testName +
      "&selectedSampleStatus=" +
      values.sampleStatusType +
      "&selectedAnalysisStatus=" +
      values.analysisStatus +
      "&doRange=" +
      doRange +
      "&finished=" +
      finished;
    getFromOpenElisServer(searchEndPoint, setResultsWithId);
  };

  const getTests = (tests) => {
    if (componentMounted.current) {
      setTests(tests);
    }
  };

  const getAnalysisStatusTypes = (analysisStatusTypes) => {
    if (componentMounted.current) {
      setAnalysisStatusTypes(analysisStatusTypes);
    }
  };

  const getSampleStatusTypes = (sampleStatusTypes) => {
    if (componentMounted.current) {
      setSampleStatusTypes(sampleStatusTypes);
    }
  };

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/test-list", getTests);
    getFromOpenElisServer(
      "/rest/analysis-status-types",
      getAnalysisStatusTypes,
    );
    getFromOpenElisServer("/rest/sample-status-types", getSampleStatusTypes);
    let param = new URLSearchParams(window.location.search).get("type");
    setSearchBy(param);
    return () => {
      componentMounted.current = false;
    };
  }, []);

  return (
    <>
      {notificationVisible === true ? <AlertDialog /> : ""}
      {loading && <Loading></Loading>}

      {/* <Grid  fullWidth={true} className="gridBoundary"> */}
      {/* <Column  lg={3}> */}
      <Formik
        initialValues={SearchResultFormValues}
        //validationSchema={}
        onSubmit={handleSubmit}
        onChange
      >
        {({
          //values,
          //errors,
          //touched,
          handleChange,
          //handleBlur,
          handleSubmit,
        }) => (
          <Form
            onSubmit={handleSubmit}
            onChange={handleChange}
            //onBlur={handleBlur}
          >
            <Stack gap={2}>
              <FormLabel>
                <Section>
                  <Section>
                    <Section>
                      <Heading>
                        <FormattedMessage id="label.button.search" />
                      </Heading>
                    </Section>
                  </Section>
                </Section>
              </FormLabel>

              <Row lg={12}>
                <div className="inlineDiv">
                  {searchBy === "order" && (
                    <Field name="accessionNumber">
                      {({ field }) => (
                        <TextInput
                          placeholder={"Enter LabNo"}
                          className="searchLabNumber inputText"
                          name={field.name}
                          id={field.name}
                          labelText=""
                        />
                      )}
                    </Field>
                  )}
                </div>
                {searchBy === "patient" && (
                  <div className="inlineDiv">
                    <Field name="nationalId">
                      {({ field }) => (
                        <TextInput
                          placeholder={"Enter Patient National Id"}
                          className="inputText"
                          name={field.name}
                          id={field.name}
                          labelText=""
                        />
                      )}
                    </Field>
                    <Field name="firstName">
                      {({ field }) => (
                        <TextInput
                          placeholder={"Enter Patient First name"}
                          className="searchFirstName inputText"
                          name={field.name}
                          id={field.name}
                          labelText=""
                        />
                      )}
                    </Field>

                    <Field name="lastName">
                      {({ field }) => (
                        <TextInput
                          placeholder={"Enter Patient last name"}
                          className="searchLastName inputText"
                          name={field.name}
                          id={field.name}
                          labelText=""
                        />
                      )}
                    </Field>
                  </div>
                )}
              </Row>
              {searchBy === "date" && (
                <div>
                  <div className="inlineDiv">
                    <Field name="collectionDate">
                      {({ field }) => (
                        <TextInput
                          placeholder={"Collection Date(dd/mm/yyyy)"}
                          className="collectionDate inputText"
                          name={field.name}
                          id={field.name}
                          labelText=""
                        />
                      )}
                    </Field>
                    <Field name="recievedDate">
                      {({ field }) => (
                        <TextInput
                          placeholder={"Received Date(dd/mm/yyyy)"}
                          className="receivedDate inputText"
                          name={field.name}
                          id={field.name}
                          labelText=""
                        />
                      )}
                    </Field>
                  </div>
                  <div className="inlineDiv">
                    <Field name="testName">
                      {({ field }) => (
                        <Select
                          className="analysisStatus inputText"
                          labelText="Select Test Name"
                          name={field.name}
                          id={field.name}
                        >
                          <SelectItem text="" value="" />
                          {tests.map((test, index) => {
                            return (
                              <SelectItem
                                key={index}
                                text={test.value}
                                value={test.id}
                              />
                            );
                          })}
                        </Select>
                      )}
                    </Field>
                    <Field name="analysisStatus">
                      {({ field }) => (
                        <Select
                          className="analysisStatus inputText"
                          labelText="Select Analysis Status"
                          name={field.name}
                          id={field.name}
                        >
                          <SelectItem text="" value="" />
                          {analysisStatusTypes.map((test, index) => {
                            return (
                              <SelectItem
                                key={index}
                                text={test.value}
                                value={test.id}
                              />
                            );
                          })}
                        </Select>
                      )}
                    </Field>

                    <Field name="sampleStatusType">
                      {({ field }) => (
                        <Select
                          className="sampleStatus inputText"
                          labelText="Select Sample Status"
                          name={field.name}
                          id={field.name}
                        >
                          <SelectItem text="" value="" />
                          {sampleStatusTypes.map((test, index) => {
                            return (
                              <SelectItem
                                key={index}
                                text={test.value}
                                value={test.id}
                              />
                            );
                          })}
                        </Select>
                      )}
                    </Field>
                  </div>
                </div>
              )}
              {searchBy === "order" && (
                <Grid>
                  <Column lg={2}>
                    <Field name="doRange">
                      {({ field }) => (
                        <Checkbox
                          defaultChecked={doRange}
                          onChange={handleDoRangeChange}
                          name={field.name}
                          labelText="Do Range"
                          id={field.name}
                        />
                      )}
                    </Field>
                  </Column>
                  <Column lg={2}>
                    <Field name="finished">
                      {({ field }) => (
                        <Checkbox
                          defaultChecked={finished}
                          onChange={handleFinishedChange}
                          //onClick={() => (doRange = false)}
                          name={field.name}
                          labelText="Display All"
                          id={field.name}
                        />
                      )}
                    </Field>
                  </Column>
                </Grid>
              )}

              <Column lg={6}>
                <Button type="submit" id="submit" className="searchResultsBtn">
                  <FormattedMessage id="label.button.search" />
                </Button>

                <Button
                  kind="secondary"
                  className="advancedSearchResultsBtn"
                  onClick={handleAdvancedSearch}
                >
                  <FormattedMessage id="advanced.search" />
                </Button>
              </Column>
            </Stack>
          </Form>
        )}
      </Formik>
    </>
  );
}

export function SearchResults(props) {
  const jp = require("jsonpath");

  const { notificationVisible, setNotificationBody, setNotificationVisible } =
    useContext(NotificationContext);

  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(100);
  const [acceptAsIs, setAcceptAsIs] = useState([]);
  const [referalOrganizations, setReferalOrganizations] = useState([]);
  const [methods, setMethods] = useState([]);
  const [referralReasons, setReferralReasons] = useState([]);
  const saveStatus = "";

  const componentMounted = useRef(true);

  useEffect(() => {
    componentMounted.current = true;

    getFromOpenElisServer(
      "/rest/displayList/REFERRAL_ORGANIZATIONS",
      loadReferalOrganizations,
    );
    getFromOpenElisServer("/rest/displayList/METHODS", loadMethods);
    getFromOpenElisServer(
      "/rest/displayList/REFERRAL_REASONS",
      loadReferalReasons,
    );
    return () => {
      componentMounted.current = false;
    };
  }, []);

  const loadReferalOrganizations = (values) => {
    if (componentMounted.current) {
      setReferalOrganizations(values);
    }
  };

  const loadMethods = (values) => {
    if (componentMounted.current) {
      setMethods(values);
    }
  };

  const loadReferalReasons = (values) => {
    if (componentMounted.current) {
      setReferralReasons(values);
    }
  };

  const columns = [
    {
      name: "Sample Info",
      cell: (row, index, column, id) => {
        return renderCell(row, index, column, id);
      },
      sortable: true,
      width: "19rem",
    },
    {
      name: "Test Date",
      selector: (row) => row.testDate,
      sortable: true,
      width: "7rem",
    },
    // {
    //     name: 'Methods',
    //     cell: (row, index, column, id) => {
    //         return renderCell(row, index, column, id);
    //     },
    //     width: "12rem",
    //     sortable: true,
    // },
    {
      name: "Analyzer Result",
      selector: (row) => row.analysisMethod,
      sortable: true,
      width: "7rem",
    },
    {
      name: "Test Name",
      selector: (row) => row.testName,
      sortable: true,
      width: "10rem",
    },
    {
      name: "Normal Range",
      selector: (row) => row.normalRange,
      sortable: true,
      width: "7rem",
    },
    {
      name: "Accept",
      cell: (row, index, column, id) => {
        return renderCell(row, index, column, id);
      },
      width: "8rem",
    },
    {
      name: "Result",
      cell: (row, index, column, id) => {
        return renderCell(row, index, column, id);
      },
      width: "8rem",
    },
    {
      name: "Current Result",
      cell: (row, index, column, id) => {
        return renderCell(row, index, column, id);
      },
      width: "8rem",
    },
    {
      name: "Notes",
      cell: (row, index, column, id) => {
        return renderCell(row, index, column, id);
      },
      width: "16rem",
    },
  ];

  const renderCell = (row, index, column, id) => {
    console.log("renderCell: index: " + index + ", id: " + id);
    switch (column.name) {
      case "Sample Info":
        // return <input id={"results_" + id} type="text" size="6"></input>
        return (
          <>
            <div className="sampleInfo">
              <TextArea
                value={
                  row.accessionNumber +
                  "-" +
                  row.sequenceNumber +
                  "\n" +
                  row.patientName +
                  "\n" +
                  row.patientInfo
                }
                disabled={true}
                type="text"
                labelText=""
                rows={3}
              ></TextArea>
            </div>
          </>
        );

      case "Accept":
        return (
          <>
            <Field name="forceTechApproval">
              {() => (
                <Checkbox
                  id={"testResult" + row.id + ".forceTechApproval"}
                  name={"testResult[" + row.id + "].forceTechApproval"}
                  labelText=""
                  //defaultChecked={acceptAsIs}
                  onChange={(e) => handleAcceptAsIsChange(e, row.id)}
                />
              )}
            </Field>
          </>
        );

      case "Notes":
        return (
          <>
            <div className="note">
              <TextArea
                id={"testResult" + row.id + ".note"}
                name={"testResult[" + row.id + "].note"}
                value={props.results.testResult[row.id].pastNotes}
                disabled={false}
                type="text"
                labelText=""
                rows={3}
                onChange={(e) => handleChange(e, row.id)}
              ></TextArea>
            </div>
          </>
        );

      case "Result":
        switch (row.resultType) {
          case "D":
            return (
              <Select
                className="result"
                id={"resultValue" + row.id}
                name={"testResult[" + row.id + "].resultValue"}
                noLabel={true}
                onChange={(e) => validateResults(e, row.id)}
              >
                {/* {...updateShadowResult(e, this, param.rowId)} */}
                <SelectItem text="" value="" />
                {row.dictionaryResults.map(
                  (dictionaryResult, dictionaryResult_index) => (
                    <SelectItem
                      text={dictionaryResult.value}
                      value={dictionaryResult.id}
                      key={dictionaryResult_index}
                    />
                  ),
                )}
              </Select>
            );

          case "N":
            return (
              <TextInput
                id={"ResultValue" + row.id}
                name={"testResult[" + row.id + "].resultValue"}
                //type="text"
                // value={.resultForm.testResult[row.id].resultValue}
                labelText=""
                // helperText="Optional help text"
                onChange={(e) => handleChange(e, row.id)}
              />
            );

          case "R":
            return (
              <TextArea
                id={"ResultValue" + row.id}
                name={"testResult[" + row.id + "].resultValue"}
                //type="text"
                // value={.resultForm.testResult[row.id].resultValue}
                labelText=""
                // helperText="Optional help text"
                onChange={(e) => handleChange(e, row.id)}
              />
            );

          case "A":
            return (
              <TextArea
                id={"ResultValue" + row.id}
                name={"testResult[" + row.id + "].resultValue"}
                //type="text"
                // value={resultForm.testResult[row.id].resultValue}
                labelText=""
                // helperText="Optional help text"
                onChange={(e) => handleChange(e, row.id)}
              />
            );

          default:
            return row.resultValue;
        }

      case "Current Result":
        switch (row.resultType) {
          case "D":
            return (
              <Select
                className="currentResult"
                id={"currentResultValue" + row.id}
                name={"testResult[" + row.id + "].resultValue"}
                noLabel={true}
                onChange={(e) => validateResults(e, row.id)}
                value={row.resultValue}
              >
                {/* {...updateShadowResult(e, this, param.rowId)} */}
                <SelectItem text="" value="" />
                {row.dictionaryResults.map(
                  (dictionaryResult, dictionaryResult_index) => (
                    <SelectItem
                      text={dictionaryResult.value}
                      value={dictionaryResult.id}
                      key={dictionaryResult_index}
                    />
                  ),
                )}
              </Select>
            );

          case "N":
            //return
            // <input id={"currentResult" + row.id}
            //     name={"testResult[" + row.id + "].resultValue"}
            //     onChange={(e) => validateResults(e, row.id)}
            // //onChange={(e) => markUpdated(e)} sb. disabled and setto value
            // />

            return (
              <TextInput
                id={"currentResultValue" + row.id}
                name={"testResult[" + row.id + "].resultValue"}
                //type="text"
                value={row.resultValue}
                labelText=""
                // helperText="Optional help text"
                onChange={(e) => handleChange(e, row.id)}
              />
            );

          // return <TextInput
          //     id={"testResult[" + row.id + "].resultValue"}
          //     name={"testResult[" + row.id + "].resultValue"}
          //     type="text"
          //     value={resultForm.testResult[row.id].resultValue}
          //     // labelText="Text input label"
          //     // helperText="Optional help text"
          //     onChange={(e) => validateResults(e, row.id, row)}
          // />

          // <input id={"results_" + param.rowId} type="text" size="6"></input>

          //         <input id="results_0" name="testResult[0].resultValue" class="resultValue"
          // style="background: rgb(255, 255, 255);" onchange="validateResults( this, 0, 7.0, 40.0, 7.0, 350.0, 0, 'XXXX' );
          // 		   			 markUpdated(0);
          // 		   			 updateShadowResult(this, 0);" type="text" value="" size="6" title=""></input>
          default:
            return row.resultValue;
        }
      default:
        return row.resultValue;
    }
  };

  const renderReferral = ({ data }) => (
    <pre>
      <div className="referralRow">
        <Grid>
          <Column lg={3}>
            <div>
              <Select
                id={"testMethod" + data.id}
                name={"testResult[" + data.id + "].testMethod"}
                labelText={"Methods"}
                onChange={(e) => handleChange(e, data.id)}
                value={data.method}
              >
                <SelectItem text="" value="" />
                {methods.map((method, method_index) => (
                  <SelectItem
                    text={method.value}
                    value={method.id}
                    key={method_index}
                  />
                ))}
              </Select>
            </div>
          </Column>
          <Column lg={3}>
            <div>
              <Select
                className="referralReason"
                id={"referralReason" + data.id}
                name={"testResult[" + data.id + "].referralReason"}
                // noLabel={true}
                labelText={"Referral Reason"}
                onChange={(e) => handleChange(e, data.id)}
              >
                {/* {...updateShadowResult(e, this, param.rowId)} */}
                <SelectItem text="" value="" />
                {referralReasons.map((reason, reason_index) => (
                  <SelectItem
                    text={reason.value}
                    value={reason.id}
                    key={reason_index}
                  />
                ))}
              </Select>
            </div>
          </Column>
          <Column lg={3}>
            <div className="institute">
              <Select
                id={"institute" + data.id}
                name={"testResult[" + data.id + "].institute"}
                // noLabel={true}
                labelText={"Institute"}
                onChange={(e) => handleChange(e, data.id)}
              >
                {/* {...updateShadowResult(e, this, param.rowId)} */}

                <SelectItem text="" value="" />
                {referalOrganizations.map((org, org_index) => (
                  <SelectItem text={org.value} value={org.id} key={org_index} />
                ))}
              </Select>
            </div>
          </Column>
          <Column lg={3}>
            <div className="testToPerform">
              <Select
                id={"testToPerform" + data.id}
                name={"testResult[" + data.id + "].testToPerform"}
                // noLabel={true}
                labelText={"Test to Perform"}
                onChange={(e) => handleChange(e, data.id)}
              >
                {/* {...updateShadowResult(e, this, param.rowId)} */}

                <SelectItem text={data.testName} value={data.id} />
              </Select>
            </div>
          </Column>
          <Column lg={3}>
            <DatePicker
              datePickerType="single"
              id={"sentDate_" + data.id}
              name={"testResult[" + data.id + "].sentDate_"}
              onChange={(date) => handleDatePickerChange(date, data.id)}
            >
              <DatePickerInput
                placeholder="mm/dd/yyyy"
                labelText="Sent Date"
                id="date-picker-single"
                size="md"
              />
            </DatePicker>
          </Column>
        </Grid>
      </div>
    </pre>
  );

  const validateResults = (e, rowId) => {
    console.log("validateResults:" + e.target.value);
    handleChange(e, rowId);
  };

  const handleChange = (e, rowId) => {
    const { name, id, value } = e.target;
    console.log("handleChange:" + id + ":" + name + ":" + value + ":" + rowId);
    // console.log('State updated to ', e.target.value);
    var form = props.results;
    jp.value(form, name, value);
    var isModified = "testResult[" + rowId + "].isModified";
    jp.value(form, isModified, "true");
  };

  const handleDatePickerChange = (date, rowId) => {
    console.log("handleDatePickerChange:" + date);
    const d = new Date(date).toLocaleDateString("fr-FR");
    var form = props.results;
    jp.value(form, "testResult[" + rowId + "].sentDate_", d);
    var isModified = "testResult[" + rowId + "].isModified";
    jp.value(form, isModified, "true");
  };

  const handleAcceptAsIsChange = (e, rowId) => {
    console.log("handleAcceptAsIsChange:" + acceptAsIs[rowId]);
    handleChange(e, rowId);
    if (acceptAsIs[rowId] == undefined) {
      var message =
        `Checking this box will indicate that you accept the results unconditionally.\n` +
        `Expected uses:\n` +
        `1. The test has been redone and the result is the same.\n` +
        `2. There is no result for the test but you do not want to cancel it.\n` +
        `3. The result was changed and the technician wants to give the biologist the option to add a note during the validation step explaining the reason of the change.\n` +
        `In  either case, leave a note explaining why you are taking this action.\n`;

      // message=`Incorrect Username/Password Used \n Please try again…`

      alert(message);

      setNotificationBody({
        title: "Notification Message",
        message: message,
        kind: NotificationKinds.warning,
      });
      setNotificationVisible(true);
    }
    var newAcceptAsIs = acceptAsIs;
    newAcceptAsIs[rowId] = !acceptAsIs[rowId];
    setAcceptAsIs(newAcceptAsIs);
  };

  const handleSave = (values) => {
    //console.log("handleSave:" + values);
    values.status = saveStatus;
    var searchEndPoint = "/rest/ReactLogbookResultsUpdate";
    props.results.testResult.forEach((result) => {
      result.reportable = result.reportable === "N" ? false : true;
      delete result.result;
    });
    console.log(props.results);
    postToOpenElisServer(
      searchEndPoint,
      JSON.stringify(props.results),
      setStatus,
    );
  };

  const setStatus = (status) => {
    console.log(status);
    if (status != 200) {
      setNotificationBody({
        title: "Notification Message",
        message: "Error: " + status,
        kind: NotificationKinds.error,
      });
    } else {
      setNotificationBody({
        title: "Notification Message",
        message: "Test Results have been saved successfully",
        kind: NotificationKinds.success,
      });
    }
    setNotificationVisible(true);
  };

  const handlePageChange = (pageInfo) => {
    if (page != pageInfo.page) {
      setPage(pageInfo.page);
    }
    if (pageSize != pageInfo.pageSize) {
      setPageSize(pageInfo.pageSize);
    }
  };

  return (
    <>
      {notificationVisible === true ? <AlertDialog /> : ""}
      <>
        <Formik
          initialValues={SearchResultFormValues}
          //validationSchema={}
          onSubmit
          onChange
        >
          {({
            //values,
            //errors,
            //touched,
            handleChange,
            //handleBlur,
            //handleSubmit
          }) => (
            <Form
              onChange={handleChange}
              //onBlur={handleBlur}
            >
              <DataTable
                data={props.results.testResult}
                columns={columns}
                isSortable
                expandableRows
                expandableRowsComponent={renderReferral}
              ></DataTable>
              <Pagination
                onChange={handlePageChange}
                page={page}
                pageSize={pageSize}
                pageSizes={[100]}
                totalItems={props.results.testResult.length}
              ></Pagination>

              <Button type="button" id="submit" onClick={handleSave}>
                <FormattedMessage id="label.button.save" />
              </Button>
            </Form>
          )}
        </Formik>
      </>
      {/* </Column> */}
      {/* </Grid> */}
    </>
  );
}

export default injectIntl(ResultSearchPage);
