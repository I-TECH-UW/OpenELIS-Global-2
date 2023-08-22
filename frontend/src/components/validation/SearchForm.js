import React, {useState, useEffect, useContext} from 'react'
import {Button, Column, Form, FormLabel, Heading, Row, Section, Stack, TextInput} from "@carbon/react";
import {FormattedMessage} from "react-intl";
import {Formik, Field} from "formik";
import ValidationSearchFormValues from "../formModel/innitialValues/ValidationSearchFormValues";
import {getFromOpenElisServer} from "../utils/Utils";
import {NotificationContext} from "../layout/Layout";
import {NotificationKinds} from "../common/CustomNotification";

const SearchForm = (props) => {
    const {setNotificationVisible, setNotificationBody} = useContext(NotificationContext);
    const [searchResults, setSearchResults] = useState();
    const validationResults = (data) => {
        if (data) {
            setSearchResults(data);
            if (data.resultList.length > 0) {
                const newResultsList = data.resultList.map((data, idx) => {
                    let tempData = {...data}
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
                    title: "Notification Message",
                    message: "No Results found to be validated"
                });
                setNotificationVisible(true);
            }
        }
    }

    useEffect(() => {
        props.setResults(searchResults)
    }, [searchResults]);

    const handleSubmit = (values) => {
        let searchEndPoint = "/rest/accessionValidationByRange?" +
            "accessionNumber=" + values.accessionNumber
        getFromOpenElisServer(searchEndPoint, validationResults);
    }

    const handleChange = () => {

    }


    return (
        <>
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

                            <FormLabel>
                                <Section>
                                    <Section>
                                        <Section>
                                            <Heading>
                                                <FormattedMessage id="label.button.search"/>
                                            </Heading>
                                        </Section>
                                    </Section>
                                </Section>
                            </FormLabel>
                            <Row lg={12}>
                                <div className="inlineDiv">

                                    <Field name="accessionNumber"
                                    >
                                        {({field}) =>
                                            <TextInput
                                                placeholder={"Enter LabNo"}
                                                className="searchLabNumber inputText"
                                                name={field.name} id={field.name} labelText=""/>
                                        }
                                    </Field>
                                </div>

                            </Row>

                            <Column lg={6}>
                                <Button type="submit" id="submit" className="searchResultsBtn">
                                    <FormattedMessage id="label.button.search"/>
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
