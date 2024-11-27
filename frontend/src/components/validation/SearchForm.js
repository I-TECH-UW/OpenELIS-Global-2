import React, { useState, useEffect, useContext } from "react";
import {
  Button,
  Column,
  Form,
  FormLabel,
  Heading,
  Row,
  Section,
  Stack,
  TextInput,
  SelectItem,
  Select,
  DatePicker,
  DatePickerInput,
  Loading,
  Grid,
} from "@carbon/react";
import CustomLabNumberInput from "../common/CustomLabNumberInput";
import { FormattedMessage, useIntl } from "react-intl";
import { Formik, Field } from "formik";
import ValidationSearchFormValues from "../formModel/innitialValues/ValidationSearchFormValues";
import { getFromOpenElisServer } from "../utils/Utils";
import { NotificationContext } from "../layout/Layout";
import { NotificationKinds } from "../common/CustomNotification";
import { format } from "date-fns";

const SearchForm = (props) => {
  const { setNotificationVisible, setNotificationBody } =
    useContext(NotificationContext);
  const [searchResults, setSearchResults] = useState();
  const [searchBy, setSearchBy] = useState();
  const [doRange, setDoRagnge] = useState(true);
  const [testSections, setTestSections] = useState([]);
  const [testDate, setTestDate] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [nextPage, setNextPage] = useState(null);
  const [previousPage, setPreviousPage] = useState(null);
  const [pagination, setPagination] = useState(false);
  const [url, setUrl] = useState("");
  const intl = useIntl();

  const validationResults = (data) => {
    if (data) {
      setSearchResults(data);
      setIsLoading(false);
      if (data.paging) {
        var { totalPages, currentPage } = data.paging;
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
      if (data.resultList.length > 0) {
        const newResultsList = data.resultList.map((data, id) => {
          let tempData = { ...data };
          tempData.id = id;
          return tempData;
        });
        setSearchResults((prevState) => ({
          ...prevState,
          resultList: newResultsList,
        }));
      } else {
        setIsLoading(false);
        setSearchResults((prevState) => ({
          ...prevState,
          resultList: [],
        }));

        setNotificationBody({
          kind: NotificationKinds.warning,
          title: <FormattedMessage id="notification.title" />,
          message: "No Results found to be validated",
        });
        setNotificationVisible(true);
      }
    }
  };

  useEffect(() => {
    props.setResults(searchResults);
  }, [searchResults]);

  const handleSubmit = (values) => {
    setNextPage(null);
    setPreviousPage(null);
    setPagination(false);
    setIsLoading(true);
    var accessionNumber = values.accessionNumber
      ? values.accessionNumber.split("-")[0]
      : "";
    var unitType = values.unitType ? values.unitType : "";
    var date = testDate ? testDate : "";
    let searchEndPoint =
      "/rest/accessionValidation?" +
      "accessionNumber=" +
      accessionNumber +
      "&unitType=" +
      unitType +
      "&date=" +
      date +
      "&doRange=" +
      doRange;
    setUrl(searchEndPoint);
    getFromOpenElisServer(searchEndPoint, validationResults);
  };

  const handleChange = () => {};

  const loadNextResultsPage = () => {
    setIsLoading(true);
    getFromOpenElisServer(url + "&page=" + nextPage, validationResults);
  };

  const loadPreviousResultsPage = () => {
    setIsLoading(true);
    getFromOpenElisServer(url + "&page=" + previousPage, validationResults);
  };
  const fetchTestSections = (response) => {
    setTestSections(response);
  };

  const submitOnSelect = (e) => {
    setNextPage(null);
    setPreviousPage(null);
    setPagination(false);
    var values = { unitType: e.target.value };
    handleSubmit(values);
  };

  function handleDatePickerChange(e) {
    let date = new Date(e[0]);
    const formatDate = format(new Date(date), "dd/MM/yyyy");
    setTestDate(formatDate);
  }

  useEffect(() => {
    let param = new URLSearchParams(window.location.search).get("type");
    setSearchBy(param);
    if (param === "order") {
      setDoRagnge(false);
    }
    getFromOpenElisServer("/rest/user-test-sections", fetchTestSections);
    setNextPage(null);
    setPreviousPage(null);
    setPagination(false);
  }, []);
  return (
    <>
      {isLoading && <Loading></Loading>}
      <Formik
        initialValues={ValidationSearchFormValues}
        //validationSchema={}
        onSubmit={handleSubmit}
        onChange
      >
        {({
          values,
          errors,
          touched,
          setFieldValue,
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
              <Grid>
                <Column lg={16}>
                  <h4>
                    <FormattedMessage id="label.button.search" />
                  </h4>
                </Column>

                {(searchBy === "order" || searchBy === "range") && (
                  <>
                    <Column lg={6}>
                      <Field name="accessionNumber">
                        {({ field }) => (
                          <CustomLabNumberInput
                            placeholder={"Enter Lab No"}
                            name={field.name}
                            id={field.name}
                            value={values[field.name]}
                            onChange={(e, rawValue) => {
                              setFieldValue(field.name, rawValue);
                            }}
                            labelText={
                              searchBy == "order" ? (
                                <FormattedMessage id="search.label.accession" />
                              ) : (
                                <FormattedMessage id="search.label.loadnext" />
                              )
                            }
                          />
                        )}
                      </Field>
                    </Column>
                    <Column lg={10} />
                  </>
                )}

                {searchBy === "testDate" && (
                  <>
                    <Column lg={6}>
                      <Field name="date">
                        {({ field }) => (
                          <DatePicker
                            name={field.name}
                            id={field.id}
                            dateFormat="d/m/Y"
                            datePickerType="single"
                            value={testDate}
                            onChange={(e) => {
                              handleDatePickerChange(e);
                            }}
                          >
                            <DatePickerInput
                              name={field.name}
                              id={field.id}
                              placeholder="dd/mm/yyyy"
                              type="text"
                              labelText={
                                <FormattedMessage id="search.label.testdate" />
                              }
                            />
                          </DatePicker>
                        )}
                      </Field>
                    </Column>
                    <Column lg={10} />
                  </>
                )}
                {searchBy !== "routine" && (
                  <Column lg={16}>
                    <Button
                      type="submit"
                      id="submit"
                      style={{ marginTop: "16px" }}
                    >
                      <FormattedMessage id="label.button.search" />
                    </Button>
                  </Column>
                )}
              </Grid>
            </Stack>
          </Form>
        )}
      </Formik>

      {searchBy === "routine" && (
        <>
          <Grid>
            <Column lg={6}>
              <Select
                labelText={<FormattedMessage id="search.label.testunit" />}
                name="unitType"
                id="unitType"
                onChange={submitOnSelect}
              >
                <SelectItem text="" value="" />
                {testSections.map((test, index) => {
                  return (
                    <SelectItem key={index} text={test.value} value={test.id} />
                  );
                })}
              </Select>
            </Column>
            <Column lg={10} />
          </Grid>
        </>
      )}

      <>
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
      </>
    </>
  );
};

export default SearchForm;
