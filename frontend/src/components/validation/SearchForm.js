import React, { useState, useEffect, useContext } from 'react'
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
  Loading
} from "@carbon/react";
import { FormattedMessage } from "react-intl";
import { Formik, Field } from "formik";
import ValidationSearchFormValues from "../formModel/innitialValues/ValidationSearchFormValues";
import { getFromOpenElisServer } from "../utils/Utils";
import { NotificationContext } from "../layout/Layout";
import { NotificationKinds } from "../common/CustomNotification";
import { format } from 'date-fns';

const SearchForm = (props) => {
  const { setNotificationVisible, setNotificationBody } = useContext(NotificationContext);
  const [searchResults, setSearchResults] = useState();
  const [searchBy, setSearchBy] = useState();
  const [testSections, setTestSections] = useState([]);
  const [testDate, setTestDate] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const validationResults = (data) => {
    if (data) {
      setSearchResults(data);
      if (data.resultList.length > 0) {
        const newResultsList = data.resultList.map((data, idx) => {
          let tempData = { ...data }
          tempData.id = idx
          return tempData
        });
        setSearchResults(prevState => ({
          ...prevState,
          resultList: newResultsList
        }));
      } else {
        setSearchResults(prevState => ({
          ...prevState,
          resultList: []
        }));

        setNotificationBody({
          kind: NotificationKinds.warning,
          title: <FormattedMessage id="notification.title" />,
          message: "No Results found to be validated"
        });
        setNotificationVisible(true);
      }
    }
    setIsLoading(false)
  }

  useEffect(() => {
    props.setResults(searchResults)
  }, [searchResults]);

  const handleSubmit = (values) => {
    setIsLoading(true)
    var accessionNumber = values.accessionNumber ? values.accessionNumber : "";
    var unitType = values.unitType ? values.unitType : ""
    var date = testDate ? testDate : ""
    let searchEndPoint = "/rest/accessionValidationByRange?" +
      "accessionNumber=" + accessionNumber +
      "&unitType=" + unitType +
      "&date=" + date
    getFromOpenElisServer(searchEndPoint, validationResults);
  }

  const handleChange = () => {

  }
  const fetchTestSections = (response) => {
    setTestSections(response);
  }

  function handleDatePickerChange(e) {
    let date = new Date(e[0]);
    const formatDate = format(new Date(date), 'dd/MM/yyyy')
    setTestDate(formatDate);
  }

  useEffect(() => {
    let param = (new URLSearchParams(window.location.search)).get("type")
    setSearchBy(param);
    getFromOpenElisServer('/rest/user-test-sections', fetchTestSections);
  }, []);
  return (
    <>
      {isLoading && (
        <Loading></Loading>
      )}
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
          handleChange,
          //handleBlur,
          handleSubmit
        }) => (

          <Form
            onSubmit={handleSubmit}
            onChange={handleChange}
          //onBlur={handleBlur}
          >
            <Stack gap={2}>

                  <FormattedMessage id="label.button.search" />

              <Row lg={12}>
                <div className="inlineDiv">

                  {searchBy === "order" && <Field name="accessionNumber"
                  >
                    {({ field }) =>
                      <TextInput
                        placeholder={"Enter LabNo"}
                        className="searchLabNumber inputText"
                        name={field.name} id={field.name} labelText="Enter LabNo:" />
                    }
                  </Field>}
                </div>
                <div className="inlineDiv">
                  {searchBy === "routine" && <Field name="unitType">
                    {({ field }) =>
                      <Select
                        className="inputText"
                        labelText="Select Unit Type"
                        name={field.name} id={field.name}>
                        <SelectItem
                          text=""
                          value="" />
                        {
                          testSections.map((test, index) => {
                            return (
                              <SelectItem
                                key={index}
                                text={test.value}
                                value={test.id} />
                            )
                          })
                        }
                      </Select>
                    }
                  </Field>}
                </div>

                <div className="inlineDiv">
                  {searchBy === "testDate" &&
                    <Field name="date">
                      {({ field }) =>
                        <DatePicker name={field.name} id={field.id}
                          dateFormat="d/m/Y" className="inputText"
                          datePickerType="single"
                          value={testDate}
                          onChange={(e) => {
                            handleDatePickerChange(e)
                          }}>
                          <DatePickerInput name={field.name} id={field.id}
                            placeholder="dd/mm/yyyy" type="text"
                            labelText="Enter Test date" />
                        </DatePicker>
                      }
                    </Field>}
                </div>

              </Row>

              <Column lg={6}>
                <Button type="submit" id="submit" className="searchResultsBtn">
                  <FormattedMessage id="label.button.search" />
                </Button>
              </Column>

            </Stack>

          </Form>
        )}
      </Formik>
    </>

  );

}

export default SearchForm;