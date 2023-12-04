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
import config from "../../config.json";

const Validation = (props) => {
  const { setNotificationVisible, setNotificationBody } =
    useContext(NotificationContext);
  const { configurationProperties } = useContext(ConfigurationContext);
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(20);

  const componentMounted = useRef(false);

  useEffect(() => {
    componentMounted.current = true;
    return () => {
      componentMounted.current = false;
    };
  }, []);

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
      width: "10rem",
    },
    {
      name: "Past Notes",
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

  const handlePageChange = (pageInfo) => {
    if (page != pageInfo.page) {
      setPage(pageInfo.page);
    }
    if (pageSize != pageInfo.pageSize) {
      setPageSize(pageInfo.pageSize);
    }
  };

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
    const { name, id, checked } = e.target;
    let form = props.results;
    var jp = require("jsonpath");
    jp.value(form, name, checked);
  };

  const handleAutomatedCheck = (checked, name) => {
    let form = props.results;
    var jp = require("jsonpath");
    jp.value(form, name, checked);
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
            <TextArea
              value={
                formatLabNum
                  ? convertAlphaNumLabNumForDisplay(row.accessionNumber)
                  : row.accessionNumber
              }
              disabled={true}
              type="text"
              labelText=""
              rows={1}
            ></TextArea>
            {row.nonconforming && (
              <picture>
                <img
                  src={config.serverBaseUrl + "/images/nonconforming.gif"}
                  alt="nonconforming"
                  width="20"
                  height="15"
                />
              </picture>
            )}
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
                disabled={false}
                type="text"
                labelText=""
                rows={3}
                onChange={(e) => handleChange(e, row.id)}
              ></TextArea>
            </div>
          </>
        );

      case "Past Notes":
        return (
          <>
            <div className="note">
              <TextArea
                id={"resultList" + row.id + ".pastNotes"}
                name={"resultList[" + row.id + "].pastNotes"}
                value={row.pastNotes}
                disabled={true}
                type="text"
                labelText=""
                rows={3}
              ></TextArea>
            </div>
          </>
        );

      case "Result":
        switch (row.resultType) {
          case "M":
          case "C":
          case "D":
            return (
              <>
                {
                  row.dictionaryResults.find(
                    (result) => result.id == row.result,
                  )?.value
                }
              </>
            )
          default:
            return row.result;
        }

      default:
    }
    return row.result;
  };

  return (
    <>
      {props.results?.resultList?.length > 0 && (
        <Grid style={{ marginTop: "20px" }} className="gridBoundary">
          <Column lg={7}>
            <picture>
              <img
                src={config.serverBaseUrl + "/images/nonconforming.gif"}
                alt="nonconforming"
                width="25" // Set your desired width
                height="20" // Set your desired height
              />
            </picture>
            <b>
              {" "}
              <FormattedMessage id="validation.label.nonconform" />
            </b>
          </Column>
          <Column lg={3}>
            <Checkbox
              id={"saveallnormal"}
              name={"autochecks"}
              labelText="Savel All normal"
              onChange={(e) => {
                const nomalResults = props.results.resultList?.filter(
                  (result) => result.normal == true,
                );
                nomalResults.forEach((result) => {
                  const checkbox = document.getElementById(
                    "resultList" + result.id + ".isAccepted",
                  );
                  checkbox.checked = e.target.checked;
                  handleAutomatedCheck(e.target.checked, checkbox.name);
                });
              }}
            />
          </Column>
          <Column lg={3}>
            <Checkbox
              id={"saveallresults"}
              name={"autochecks"}
              labelText="Savel All Results"
              onChange={(e) => {
                const nomalResults = props.results.resultList;
                nomalResults.forEach((result) => {
                  const checkbox = document.getElementById(
                    "resultList" + result.id + ".isAccepted",
                  );
                  checkbox.checked = e.target.checked;
                  handleAutomatedCheck(e.target.checked, checkbox.name);
                });
              }}
            />
          </Column>
          <Column lg={3}>
            <Checkbox
              id={"retestalltests"}
              name={"autochecks"}
              labelText="Retest All Tests"
              onChange={(e) => {
                const nomalResults = props.results.resultList;
                nomalResults.forEach((result) => {
                  const checkbox = document.getElementById(
                    "resultList" + result.id + ".isRejected",
                  );
                  checkbox.checked = e.target.checked;
                  handleAutomatedCheck(e.target.checked, checkbox.name);
                });
              }}
            />
          </Column>
        </Grid>
      )}
      <Formik
        initialValues={ValidationSearchFormValues}
        //validationSchema={}
        onSubmit
        onChange
      >
        {({ values, errors, touched, handleChange }) => (
          <Form onChange={handleChange}>
            <DataTable
              data={
                props.results
                  ? props.results.resultList.slice(
                      (page - 1) * pageSize,
                      page * pageSize,
                    )
                  : []
              }
              columns={columns}
              isSortable
            ></DataTable>
            <Pagination
              onChange={handlePageChange}
              page={page}
              pageSize={pageSize}
              pageSizes={[10, 20, 50, 100]}
              totalItems={
                props.results
                  ? props.results.resultList
                    ? props.results.resultList.length
                    : 0
                  : 0
              }
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
