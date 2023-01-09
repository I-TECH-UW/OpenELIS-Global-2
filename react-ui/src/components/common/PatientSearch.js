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
    ContentSwitcher,
    Switch,
    Stack,
    DataTable, TableContainer, Table, TableHead, TableRow, TableHeader, TableBody, TableCell,
    Checkbox,
    Section

} from '@carbon/react';

import { patientSearchHeaderData } from '../data/PatientTableData';
import { Formik, Field, FieldArray, ErrorMessage } from "formik";
import PatientSearchFormValues from '../formModel/innitialValues/PatientSearchFormValues';

class PatientSearch extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            dob: "",
            patientSearchResults: []
        }
    }

    handleSubmit = (values) => {
        values.dateOfBirth = this.state.dob
        var searchURL = "/rest/patientsearch?" + "lastName=" + values.lastName + "&firstName=" + values.firstName + "&STNumber=" + values.patientId + "&subjectNumber=" + values.patientId + "&nationalID=" + values.patientId + "&labNumber=" + values.labNumber + "&dateOfBirth=" + values.dateOfBirth + "&gender=" + values.gender
        getFromOpenElisServer(searchURL, this.fetchPatientResults)
    };

    fetchPatientResults = (patientsResults) => {
        patientsResults.forEach(item => item.id = item.patientID);
        // console.log(JSON.stringify(patientsResults))
        this.setState({ patientSearchResults: patientsResults })
    }

    handleDatePickerChange = (...e) => {
        this.setState({
            dob: e[1],
        });
    }

    patientSelected = (e) => {
        const patientSelected = this.state.patientSearchResults.find((patient) => {
            return patient.id == e.target.id;
        });
        this.props.getSelectedPatient(patientSelected)
    }

    render() {
        return (
            <>
                <Grid fullWidth={true}>
                    <Column lg={3}>
                        <Formik
                            initialValues={PatientSearchFormValues}
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
                                                            <FormattedMessage id="patient.label.search" />
                                                        </Heading>
                                                    </Section>
                                                </Section>
                                            </Section>
                                        </FormLabel>
                                        <Field name="labNumber"
                                        >
                                            {({ field }) =>
                                                <TextInput name={field.name} labelText="Lab Number" id="test" className="inputText" />
                                            }
                                        </Field>
                                        <Field name="patientId"
                                        >
                                            {({ field }) =>
                                                <TextInput name={field.name} labelText="Patient Id" id="test" className="inputText" />
                                            }
                                        </Field>
                                        <Field name="lastName"
                                        >
                                            {({ field }) =>
                                                <TextInput name={field.name} labelText="Last Name" id="test" className="inputText" />
                                            }
                                        </Field>
                                        <Field name="firstName"
                                        >
                                            {({ field }) =>
                                                <TextInput name={field.name} labelText="First Name" id="test" className="inputText" />
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
                                                >
                                                    <RadioButton
                                                        id="radio-1"
                                                        labelText="Male"
                                                        value="M"
                                                    />
                                                    <RadioButton
                                                        id="radio-2"
                                                        labelText="Female"
                                                        value="F"
                                                    />
                                                </RadioButtonGroup>
                                            }
                                        </Field>
                                        <Button type="submit" id="submit">
                                            <FormattedMessage id="patient.label.search" />
                                        </Button>
                                    </Stack>
                                </Form>
                            )}
                        </Formik>
                    </Column>
                    <Column></Column>
                    <Column lg={12} >
                        <DataTable rows={this.state.patientSearchResults} headers={patientSearchHeaderData}>
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
                                            {rows.length > 0 ? (
                                                <>
                                                    {rows.map((row) => (
                                                        <TableRow key={row.id}>
                                                            <TableCell > <RadioButton name="radio-group" onClick={this.patientSelected} labelText="" id={row.id} /></TableCell>
                                                            {row.cells.map((cell) => (
                                                                <TableCell key={cell.id}>{cell.value}</TableCell>
                                                            ))}
                                                        </TableRow>
                                                    ))}
                                                </>
                                            ) : (
                                                <TableRow>
                                                    <TableCell colSpan={7} >
                                                        No patients found matching search terms
                                                    </TableCell>
                                                </TableRow>

                                            )}

                                        </TableBody>
                                    </Table>
                                </TableContainer>
                            )}
                        </DataTable>
                    </Column>
                </Grid>

            </>

        );

    }
}

export default injectIntl(PatientSearch)