import React, { useContext, useEffect, useRef, useState } from "react";

import { FormattedMessage, injectIntl } from "react-intl";
import "../Style.css";
import { jp } from "jsonpath";

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
} from "@carbon/react";
import DataTable from "react-data-table-component";
import { Formik, Field } from "formik";
//import SearchResultFormValues from '../formModel/innitialValues/SearchResultFormValues';
import SearchResultValidationFormValues from "../formModel/innitialValues/SearchResultValidationFormValues";

import { AlertDialog, NotificationKinds } from "../common/CustomNotification";
import { NotificationContext } from "../layout/Layout";

function SearchResultValidationForm() {
  const { notificationVisible, setNotificationBody, setNotificationVisible } =
    useContext(NotificationContext);

  const [resultForm, setResultForm] = useState({ testResult: [] });
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(100);
  const [doRange, setDoRange] = useState(true);
  const [finished, setFinished] = useState(true);
  const [acceptAsIs, setAcceptAsIs] = useState([]);
  const [saveStatus, setSaveStatus] = useState("");

  const columns = [
    {
      name: "Sample V Info",
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
      cell: (row, index, column, id, resultForm) => {
        return renderCell(row, index, column, id);
      },
      width: "8rem",
    },
    {
      name: "Result",
      cell: (row, index, column, id, resultForm) => {
        return renderCell(row, index, column, id);
      },
      width: "8rem",
    },
    {
      name: "Current Result",
      cell: (row, index, column, id, resultForm) => {
        return renderCell(row, index, column, id);
      },
      width: "8rem",
    },
    {
      name: "Notes",
      cell: (row, index, column, id, resultForm) => {
        return renderCell(row, index, column, id);
      },
      width: "16rem",
    },
  ];

  const renderCell = (row, index, column, id) => {
    // console.log("renderCell:" + column.name + ":" + row.resultType);
    switch (column.name) {
      case "Sample V Info":
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
                disabled={false}
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
              {({ field }) => (
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
        // let aNote;
        // if (!resultForm.testResult[row.id].note) {
        //     aNote = ""
        // } else {
        //     aNote = resultForm.testResult[row.id].note
        // }

        return (
          <>
            <div className="note">
              <TextArea
                id={"testResult" + row.id + ".note"}
                name={"testResult[" + row.id + "].note"}
                value={resultForm.testResult[row.id].note}
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
            // return <input
            //     id={"Result" + row.id}
            //     name={"testResult[" + row.id + "].resultValue"}
            //     value={resultForm.testResult[row.id].resultVaule}
            //     onChange={(e) => validateResults(e, row.id, row)}
            // />

            return (
              <TextInput
                id={"ResultValue" + row.id}
                name={"testResult[" + row.id + "].resultValue"}
                //type="text"
                // value={resultForm.testResult[row.id].resultValue}
                labelText=""
                // helperText="Optional help text"
                onChange={(e) => handleChange(e, row.id)}
              />
            );

          // <input id={"results_" + param.rowId} type="text" size="6"></input>

          //         <input id="results_0" name="testResult[0].resultValue" class="resultValue"
          // style="background: rgb(255, 255, 255);" onchange="validateResults( this, 0, 7.0, 40.0, 7.0, 350.0, 0, 'XXXX' );
          // 		   			 markUpdated(0);
          // 		   			 updateShadowResult(this, 0);" type="text" value="" size="6" title=""></input>
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
                value={resultForm.testResult[row.id].resultValue}
                // labelText="Text input label"
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

      // case "Methods":
      //     return <Select
      //         id={"testMethod" + row.id}
      //         name={"testResult[" + row.id + "].testMethod"}
      //         noLabel={true}
      //         onChange={(e) => handleChange(e, row.id)}
      //         value={row.method}
      //     >
      //         <SelectItem
      //             text=""
      //             value=""
      //         />
      //         {row.methods.map((method, method_index) => (
      //             <SelectItem
      //                 text={method.value}
      //                 value={method.id}
      //                 key={method_index}
      //             />
      //         ))}
      //     </Select>
    }
    return row.resultValue;
  };

  const renderReferral = ({ data }) => {
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
                {data.methods.map((method, method_index) => (
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
                {data.referralReasons.map((method, method_index) => (
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
                {data.referralOrganizations.map((method, method_index) => (
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
    </pre>;
  };

  const validateResults = (e, rowId, row) => {
    console.log("validateResults:" + e.target.value);
    // e.target.value;
    handleChange(e, rowId);
  };

  const handleChange = (e, rowId) => {
    const { name, id, value } = e.target;
    console.log("handleChange:" + id + ":" + name + ":" + value + ":" + rowId);
    // console.log('State updated to ', e.target.value);
    var form = resultForm;
    var jp = require("jsonpath");
    jp.value(form, name, value);
    var isModified = "testResult[" + rowId + "].isModified";
    jp.value(form, isModified, "true");
  };

  const handleDatePickerChange = (date, rowId) => {
    console.log("handleDatePickerChange:" + date);
    const d = new Date(date).toLocaleDateString("fr-FR");
    var form = resultForm;
    var jp = require("jsonpath");
    jp.value(form, "testResult[" + rowId + "].sentDate_", d);
    var isModified = "testResult[" + rowId + "].isModified";
    jp.value(form, isModified, "true");
  };

  const handleDoRangeChange = () => {
    console.log("handleDoRangeChange:");
    setDoRange(!doRange);
  };

  const handleFinishedChange = () => {
    console.log("handleFinishedChange:");
    setFinished(!finished);
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
        title: <FormattedMessage id="notification.title" />,
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
    postToOpenElisServer(searchEndPoint, JSON.stringify(resultForm), setStatus);
  };

  const handleSubmit = (values) => {
    //console.log("handleSubmit:" + doRange)
    setResultForm({ testResult: [] });

    var searchEndPoint =
      "/rest/ReactLogbookResultsByRange?" +
      "&labNumber=" +
      values.labNumber +
      "&doRange=" +
      doRange +
      "&finished=" +
      finished;
    getFromOpenElisServer(searchEndPoint, setResults);
  };

  const setResults = (resultForm) => {
    //console.log("setResults")
    var i = 0;
    resultForm.testResult.forEach((item) => (item.id = "" + i++));
    setResultForm(resultForm);
  };

  const setStatus = (status) => {
    //console.log("setStatus" + status)
    if (status != 200) {
      setNotificationBody({
        title: <FormattedMessage id="notification.title" />,
        message: "Error: " + status,
        kind: NotificationKinds.error,
      });
    } else {
      setNotificationBody({
        title: <FormattedMessage id="notification.title" />,
        message: "Success: " + status,
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

      <Grid fullWidth={true}>
        <Column lg={16}>
          <Formik
            initialValues={SearchResultValidationFormValues}
            //validationSchema={}
            onSubmit={handleSubmit}
            onChange
          >
            {({
              values,
              errors,
              touched,
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
                            <FormattedMessage id="label.button.resultValidationSearch" />
                          </Heading>
                        </Section>
                      </Section>
                    </Section>
                  </FormLabel>
                  <Field name="labNumber">
                    {({ field }) => (
                      <TextInput
                        className="searchLabNumber"
                        name={field.name}
                        id={field.name}
                      />
                    )}
                  </Field>
                  <Grid>
                    <Column lg={2}>
                      <Field name="doRange">
                        {({ field }) => (
                          <Checkbox
                            defaultChecked={doRange}
                            onChange={handleDoRangeChange}
                            name={field.name}
                            labelText="Show Entire Results"
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
                            labelText="Show Next 99 Orders"
                            id={field.name}
                          />
                        )}
                      </Field>
                    </Column>
                  </Grid>
                  <Button type="submit" id="submit">
                    <FormattedMessage id="label.button.search" />
                  </Button>
                </Stack>
              </Form>
            )}
          </Formik>
          {/* </Column> */}
          {/* <Column></Column> */}
          {/* <Column  lg={12} > */}

          {/* {myComponent()} */}

          <>
            <Formik
              initialValues={SearchResultValidationFormValues}
              //validationSchema={}
              onSubmit={handleSave}
              onChange
            >
              {({
                values,
                errors,
                touched,
                handleChange,
                //handleBlur,
                handleSubmit,
              }) => (
                <Form
                  onSubmit={handleSubmit}
                  onChange={handleChange}
                  //onBlur={handleBlur}
                >
                  <DataTable
                    data={resultForm.testResult}
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
                    totalItems={resultForm.testResult.length}
                  ></Pagination>

                  <Button type="submit" id="submit">
                    <FormattedMessage id="label.button.save" />
                  </Button>
                </Form>
              )}
            </Formik>
          </>
        </Column>
      </Grid>
    </>
  );
}

export default injectIntl(SearchResultValidationForm);
