import React, { useState, useContext, useEffect, useRef } from "react";
import { Field, Formik } from "formik";
import {
  Button,
  Checkbox,
  Column,
  DatePicker,
  DatePickerInput,
  Form,
  Grid,
  Pagination,
  Select,
  SelectItem,
  TextArea,
  TextInput,
} from "@carbon/react";
import DataTable from "react-data-table-component";
import { FormattedMessage } from "react-intl";
import ValidationSearchFormValues from "../formModel/innitialValues/ValidationSearchFormValues";
import { NotificationKinds } from "../common/CustomNotification";
import { postToOpenElisServer } from "../utils/Utils";
import { NotificationContext } from "../layout/Layout";
import { getFromOpenElisServer } from "../utils/Utils";
import { ConfigurationContext } from "../layout/Layout";
import { convertAlphaNumLabNumForDisplay } from "../utils/Utils";

const Validation = (props) => {
  const { setNotificationVisible, setNotificationBody } =
    useContext(NotificationContext);
  const { configurationProperties } = useContext(ConfigurationContext);
  const [page, setPage] = useState(0);
  const [pageSize, setPageSize] = useState(0);
  const [referalOrganizations, setReferalOrganizations] = useState([]);
  const [methods, setMethods] = useState([]);
  const [referralReasons, setReferralReasons] = useState([]);

  const componentMounted = useRef(false);

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer(
      "/rest/displayList/REFERRAL_ORGANIZATIONS",
      loadReferalOrganizations,
    );
    getFromOpenElisServer("/rest/displayList/METHODS", loadMehtods);
    getFromOpenElisServer(
      "/rest/displayList/REFERRAL_REASONS",
      loadReferalReasons,
    );
    return () => {
      componentMounted.current = false;
    };
  }, []);

  const loadReferalOrganizations = (results) => {
    if (componentMounted.current) {
      setReferalOrganizations(results);
    }
  };

  const loadMehtods = (results) => {
    if (componentMounted.current) {
      setMethods(results);
    }
  };

  const loadReferalReasons = (results) => {
    if (componentMounted.current) {
      setReferralReasons(results);
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
      name: "Result",
      cell: (row, index, column, id) => {
        return renderCell(row, index, column, id);
      },
      width: "8rem",
    },
    {
      name: "Save",
      cell: (row, index, column, id) => {
        return renderCell(row, index, column, id);
      },
      width: "8rem",
    },
    {
      name: "Retest",
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

  const handleSave = (values) => {
    postToOpenElisServer(
      "/rest/accessionValidationByRangeUpdate",
      JSON.stringify(props.results),
      handleResponse,
    );
  };
  const handleResponse = (status) => {
    let message = "Oops, try gain";
    let kind = NotificationKinds.error;
    if (status == 200) {
      message = "Results have been validated successfully";
      kind = NotificationKinds.success;
    }
    setNotificationBody({
      kind: kind,
      title: <FormattedMessage id="notification.title" />,
      message: message,
    });
    setNotificationVisible(true);
  };

  const handlePageChange = () => {};

  const handleChange = (e, rowId) => {
    const { name, id, value } = e.target;
    let form = props.results;
    var jp = require("jsonpath");
    jp.value(form, name, value);
  };

  const handleDatePickerChange = (date, rowId) => {
    console.log("handleDatePickerChange:" + date);
    const d = new Date(date).toLocaleDateString("fr-FR");
    var form = props.results;
    var jp = require("jsonpath");
    jp.value(form, "resultList[" + rowId + "].sentDate_", d);
  };
  const handleCheckBox = (e, rowId) => {
    handleChange(e, rowId);
  };
  const validateResults = (e, rowId) => {
    handleChange(e, rowId);
  };

  const renderCell = (row, index, column, id) => {
    let formatLabNum = configurationProperties.AccessionFormat === "ALPHANUM";
    switch (column.name) {
      case "Sample Info":
        return (
          <>
            <div className="sampleInfo">
              <TextArea
                value={
                  formatLabNum
                    ? convertAlphaNumLabNumForDisplay(row.accessionNumber)
                    : row.accessionNumber
                }
                disabled={true}
                type="text"
                labelText=""
                rows={3}
              ></TextArea>
            </div>
          </>
        );

      case "Save":
        return (
          <>
            <Field name="isAccepted">
              {({ field }) => (
                <Checkbox
                  id={"resultList" + row.id + ".isAccepted"}
                  name={"resultList[" + row.id + "].isAccepted"}
                  labelText=""
                  value={true}
                  onChange={(e) => handleCheckBox(e, row.id)}
                />
              )}
            </Field>
          </>
        );

      case "Retest":
        return (
          <>
            <Field name="isRejected">
              {({ field }) => (
                <Checkbox
                  id={"resultList" + row.id + ".isRejected"}
                  name={"resultList[" + row.id + "].isRejected"}
                  labelText=""
                  value={true}
                  onChange={(e) => handleCheckBox(e, row.id)}
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
                id={"resultList" + row.id + ".note"}
                name={"resultList[" + row.id + "].note"}
                // value={this.props.results.resultList[row.id].note}
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
                id={"result" + row.id}
                name={"resultList[" + row.id + "].result"}
                noLabel={true}
                value={row.result}
                onChange={(e) => validateResults(e, row.id)}
              >
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
                name={"resultList[" + row.id + "].result"}
                labelText=""
                type="number"
                defaultValue={
                  props.results ? props.results.resultList[row.id]?.result : ""
                }
                onChange={(e) => handleChange(e, row.id)}
              />
            );
          default:
            return row.result;
        }

      default:
    }
    return row.result;
  };
  const renderReferral = ({ data }) => {
    return (
      <div className="referralRow">
        <Grid>
          <Column lg={3}>
            <div>
              <Select
                id={"testMethod" + data.id}
                name={"resultList[" + data.id + "].testMethod"}
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
                name={"resultList[" + data.id + "].referralReason"}
                labelText={"Referral Reason"}
                onChange={(e) => handleChange(e, data.id)}
              >
                <SelectItem text="" value="" />
                {referralReasons.map((method, method_index) => (
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
                name={"resultList[" + data.id + "].institute"}
                labelText={"Institute"}
                onChange={(e) => handleChange(e, data.id)}
              >
                <SelectItem text="" value="" />
                {referalOrganizations.map((method, method_index) => (
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
                name={"resultList[" + data.id + "].testToPerform"}
                labelText={"Test to Perform"}
                onChange={(e) => handleChange(e, data.id)}
              >
                <SelectItem text={data.testName} value={data.id} />
              </Select>
            </div>
          </Column>
          <Column lg={3}>
            <DatePicker
              datePickerType="single"
              id={"sentDate_" + data.id}
              name={"resultList[" + data.id + "].sentDate_"}
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
    );
  };
  return (
    <>
      <Formik
        initialValues={ValidationSearchFormValues}
        //validationSchema={}
        onSubmit
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
            onChange={handleChange}
            //onBlur={handleBlur}
          >
            <DataTable
              data={props.results ? props.results.resultList : []}
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
              totalItems={props.results ? props.results.resultList.length : 0}
            ></Pagination>

            <Button
              type="button"
              onClick={() => handleSave(values)}
              id="submit"
            >
              <FormattedMessage id="label.button.save" />
            </Button>
          </Form>
        )}
      </Formik>
    </>
  );
};

export default Validation;
