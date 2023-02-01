import React from 'react'
import { FormattedMessage, injectIntl } from 'react-intl'
import "../Style.css";
import { getFromOpenElisServer } from '../utils/Utils';

import {
    Heading,
    Form,
    FormLabel,
    TextInput,
    Button,
    Grid,
    Column,
    DatePicker,
    DatePickerInput,
    RadioButton,
    RadioButtonGroup,
    Stack,
    DataTable, TableContainer, Table, TableHead, TableRow, TableHeader, TableBody, TableCell,
    Section,
    Pagination

} from '@carbon/react';

import { resultSearchHeaderData } from '../data/ResultsTableHeaders';
import { Formik, Field, FieldArray, ErrorMessage } from "formik";
import SearchResultFormValues from '../formModel/innitialValues/SearchResultFormValues';

class SearchResultForm extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            dob: "",
            testResult: [],
            page: 1,
            pageSize: 5,
        }
    }

    handleSubmit = (values) => {
        values.dateOfBirth = this.state.dob
        console.log("handleSubmit:values.labNumber:" + values.labNumber)
        var searchEndPoint = "/rest/results?" + "&labNumber=" + values.labNumber
        getFromOpenElisServer(searchEndPoint, this.fetchResults);
    };

    fetchResults = (result) => {
        console.log(JSON.stringify(result.testResult))
        result.testResult.forEach(item => item.id = item.testId);

        this.setState({ testResult: result.testResult })
        
        

        //setResultList(result);
    }

    // fetchPatientDetails = (patientDetails) => {
    //     console.log(JSON.stringify(patientDetails))
    //     this.props.getSelectedPatient(patientDetails)
    // }

    handleDatePickerChange = (...e) => {
        this.setState({
            dob: e[1],
        });
    }

    // patientSelected = (e) => {
    //     const resultSelected = this.state.searchResults.find((patient) => {
    //         return patient.patientID == e.target.id;
    //     });
    //     var searchEndPoint = "/rest/patient-details?patientID=" + patientSelected.patientID
    //     getFromOpenElisServer(searchEndPoint, this.fetchPatientDetails);
    // }

    handlePageChange = (pageInfo) => {
        if (this.state.page != pageInfo.page) {
            this.setState({ page: pageInfo.page });
        }

        if (this.state.pageSize != pageInfo.pageSize) {
            this.setState({ pageSize: pageInfo.pageSize });
        }

    };

    handlePerPageChange = (newPerPage) => {
        this.setState({ perPage: newPerPage });
    };

    render() {
        const { page, pageSize } = this.state;
        return (

            <>
                {/* <Grid  fullWidth={true} className="gridBoundary"> */}
                {/* <Column  lg={3}> */}
                <Formik
                    initialValues={SearchResultFormValues}
                    //validationSchema={}
                    onSubmit={this.handleSubmit}
                    onChange
                >
                    {({ values,
                        errors,
                        touched,
                        handleChange,
                        handleBlur,
                        handleSubmit }) => (
                        <Form
                            onSubmit={handleSubmit}
                            onChange={handleChange}
                            onBlur={handleBlur}>
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
                                <Field name="labNumber"
                                >
                                    {({ field }) =>
                                        <TextInput name={field.name} labelText="Lab Number" id={field.name} className="inputText" />
                                    }
                                </Field>

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
                <DataTable rows={this.state.testResult} headers={resultSearchHeaderData} isSortable >
                    {({ rows, headers, getHeaderProps, getTableProps }) => (
                        <TableContainer title="Results">
                            <Table {...getTableProps()}>
                                <TableHead>
                                    <TableRow>
                                        <TableHeader>
                                        </TableHeader>
                                        {headers.map((header) => (
                                            <TableHeader {...getHeaderProps({ header })}>
                                                {header.header}
                                            </TableHeader>
                                        ))}
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    <>
                                        {rows.slice((page - 1) * pageSize).slice(0, pageSize).map((row) => (
                                            <TableRow key={row.id}>
                                                <TableCell > <RadioButton name="radio-group" onClick={this.patientSelected} labelText="" id={row.id} /></TableCell>
                                                {row.cells.map((cell) => (
                                                    <TableCell key={cell.id}>{cell.value}</TableCell>
                                                ))}
                                            </TableRow>
                                        ))}
                                    </>

                                </TableBody>
                            </Table>
                        </TableContainer>
                    )}
                </DataTable>
                <Pagination onChange={this.handlePageChange} page={this.state.page} pageSize={this.state.pageSize} pageSizes={[5, 10, 20, 30]} totalItems={this.state.testResult.length}></Pagination>
                {/* </Column> */}
                {/* </Grid> */}
            </>

        );

    }
}

export default injectIntl(SearchResultForm)