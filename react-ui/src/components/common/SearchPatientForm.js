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
    Section ,
    Pagination

} from '@carbon/react';

import { patientSearchHeaderData} from '../data/PatientResultsTableHeaders';
import { Formik, Field, FieldArray, ErrorMessage } from "formik";
import SearchPatientFormValues from '../formModel/innitialValues/SearchPatientFormValues';

class SearchPatientForm extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            dob: "",
            patientSearchResults: [],
            page: 1,
            pageSize: 5,
        }
    }

    handleSubmit = (values) => {
        values.dateOfBirth = this.state.dob
        var searchEndPoint = "/rest/patient-search-results?" + "lastName=" + values.lastName + "&firstName=" + values.firstName + "&STNumber=" + values.patientId + "&subjectNumber=" + values.patientId + "&nationalID=" + values.patientId + "&labNumber=" + values.labNumber + "&dateOfBirth=" + values.dateOfBirth + "&gender=" + values.gender
        getFromOpenElisServer(searchEndPoint, this.fetchPatientResults);
    };

    fetchPatientResults = (patientsResults) => {
        patientsResults.forEach(item => item.id = item.patientID);
        //console.log(JSON.stringify(patientsResults))
        this.setState({ patientSearchResults: patientsResults })
    }

    fetchPatientDetails = (patientDetails) => {
        console.log(JSON.stringify(patientDetails))
        this.props.getSelectedPatient(patientDetails)
    }

    handleDatePickerChange = (...e) => {
        this.setState({
            dob: e[1],
        });
    }

    patientSelected = (e) => {
        const patientSelected = this.state.patientSearchResults.find((patient) => {
            return patient.patientID == e.target.id;
        });
        var searchEndPoint = "/rest/patient-details?patientID=" + patientSelected.patientID
        getFromOpenElisServer(searchEndPoint, this.fetchPatientDetails);
    }

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
                <Grid  fullWidth={true} className="gridBoundary">
                    <Column  lg={3}>
                        <Formik
                            initialValues={SearchPatientFormValues}
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
                                        <Field name="patientId"
                                        >
                                            {({ field }) =>
                                                <TextInput name={field.name} labelText="Patient Id" id={field.name} className="inputText" />
                                            }
                                        </Field>
                                        <Field name="lastName"
                                        >
                                            {({ field }) =>
                                                <TextInput name={field.name} labelText="Last Name" id={field.name} className="inputText" />
                                            }
                                        </Field>
                                        <Field name="firstName"
                                        >
                                            {({ field }) =>
                                                <TextInput name={field.name} labelText="First Name" id={field.name} className="inputText" />
                                            }
                                        </Field>
                                        <Field name="dateOfBirth"
                                        >
                                            {({ field }) =>
                                                <DatePicker onChange={this.handleDatePickerChange} name={field.name} dateFormat="d/m/Y" datePickerType="single" light={true} className="inputText">
                                                    <DatePickerInput
                                                        id="date-picker-default-id"
                                                        placeholder="dd/mm/yyyy"
                                                        labelText="Date of Birth"
                                                        type="text"
                                                        name={field.name}

                                                    />
                                                </DatePicker>
                                            }
                                        </Field>
                                        <Field name="gender"
                                        >
                                            {({ field }) =>
                                                <RadioButtonGroup
                                                    defaultSelected=""
                                                    legendText="Gender"
                                                    name={field.name}
                                                    id="search_patient_gender"
                                                >
                                                    <RadioButton
                                                        id="search-radio-1"
                                                        labelText="Male"
                                                        value="M"
                                                    />
                                                    <RadioButton
                                                        id="search-radio-2"
                                                        labelText="Female"
                                                        value="F"
                                                    />
                                                </RadioButtonGroup>
                                            }
                                        </Field>
                                        <Button type="submit" id="submit">
                                            <FormattedMessage id="label.button.search" />
                                        </Button>
                                    </Stack>
                                </Form>
                            )}
                        </Formik>
                    </Column>
                    <Column></Column>
                    <Column  lg={12} >
                        <DataTable rows={this.state.patientSearchResults} headers={patientSearchHeaderData} isSortable >
                            {({ rows, headers, getHeaderProps, getTableProps }) => (
                                <TableContainer title="Patient Results">
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
                        <Pagination onChange={this.handlePageChange} page={this.state.page}  pageSize ={this.state.pageSize} pageSizes={[5,10,20,30]} totalItems={this.state.patientSearchResults.length}></Pagination>
                    </Column>
                </Grid>
            </>

        );

    }
}

export default injectIntl(SearchPatientForm)