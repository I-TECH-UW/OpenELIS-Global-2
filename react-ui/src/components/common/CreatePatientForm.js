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
    Section,

} from '@carbon/react';

import { Formik, Field, FieldArray, ErrorMessage } from "formik";
import PatientSearchFormValues from '../formModel/innitialValues/PatientSearchFormValues';

class CreatePatientForm extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            dob: "",
            patientSearchResults: [],
            page: 1,
            pageSize: 5,
        }
    }

    render() {

        return (
            <Grid fullWidth={true} className="gridBoundary" >
                <Column lg={10} >
                    <Button kind='tertiary'>New Patient</Button>
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
                                                        <FormattedMessage id="patient.label.info" />
                                                    </Heading>
                                                </Section>
                                            </Section>
                                        </Section>
                                    </FormLabel>
                                    <div className="formInlineDiv">
                                        <Field name="healthID"
                                        >
                                            {({ field }) =>
                                                <TextInput name={field.name} labelText="Unique Health ID number" id={field.name} className="inputText" />
                                            }
                                        </Field>
                                        <Field name="patientId"
                                        >
                                            {({ field }) =>
                                                <TextInput name={field.name} labelText="Patient Id" id={field.name} className="inputText" />
                                            }
                                        </Field>
                                    </div>
                                    <div className="formInlineDiv">

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
                                    </div>
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
                                        <FormattedMessage id="label.button.save" />
                                    </Button>
                                </Stack>
                            </Form>

                        )}
                    </Formik>

                </Column>
            </Grid >
        )
    }
}

export default injectIntl(CreatePatientForm)