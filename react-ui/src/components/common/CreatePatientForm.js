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
import CreatePatientFormValues from '../formModel/innitialValues/CreatePatientFormValues';

class CreatePatientForm extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            patientDetails: CreatePatientFormValues,
            showForm: false
        }
    }

    handleDatePickerChange = (values, ...e) => {
        var patient = values
        patient.dob = e[1];

        this.setState({
            patientDetails: patient,
        });
    }


    shouldComponentUpdate(nextProps, nextState) {
        if (nextProps.selectedPatient.id) {
            nextState.patientDetails = nextProps.selectedPatient;
        }
        return true;
    }

    addNewPatient = () => {
        this.setState({ showForm: true })
    }


    handleSubmit = (values) => {
        console.log(JSON.stringify(values))
    };

    render() {

        return (
            <Grid fullWidth={true} className="gridBoundary" >
                <Column lg={10} >
                    <Button onClick={this.addNewPatient} kind='tertiary'>New Patient</Button>
                    {this.state.showForm && (
                        <Formik
                            initialValues={this.state.patientDetails}
                            enableReinitialize
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
                                            <Field name="subjectNumber"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.subjectNumber} name={field.name} labelText="Unique Health ID number" id={field.name} className="inputText" />
                                                }
                                            </Field>
                                            <Field name="nationalID"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.nationalID} name={field.name} labelText="National Id" id={field.name} className="inputText" />
                                                }
                                            </Field>
                                        </div>
                                        <div className="formInlineDiv">

                                            <Field name="lastName"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.lastName} name={field.name} labelText="Last Name" id={field.name} className="inputText" />
                                                }
                                            </Field>
                                            <Field name="firstName"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.firstName} name={field.name} labelText="First Name" id={field.name} className="inputText" />
                                                }
                                            </Field>
                                        </div>
                                        <div className="formInlineDiv">

                                            <Field name="contactLastName"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.contactLastName} name={field.name} labelText="Contact Last Name" id={field.name} className="inputText" />
                                                }
                                            </Field>
                                            <Field name="contactFirstName"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.contactFirstName} name={field.name} labelText="Contact First Name" id={field.name} className="inputText" />
                                                }
                                            </Field>
                                        </div>
                                        <div className="formInlineDiv">

                                            <Field name="contactEmail"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.contactEmail} name={field.name} labelText="Contact Email" id={field.name} className="inputText" />
                                                }
                                            </Field>
                                            <Field name="contactPhone"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.contactPhone} name={field.name} labelText="Contact Phone" id={field.name} className="inputText" />
                                                }
                                            </Field>
                                        </div>
                                        <div className="formInlineDiv">

                                            <Field name="street"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.street} name={field.name} labelText="Street" id={field.name} className="inputText" />
                                                }
                                            </Field>
                                            <Field name="commune"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.commune} name={field.name} labelText="Camp/Commune" id={field.name} className="inputText" />
                                                }
                                            </Field>
                                        </div>
                                        <div className="formInlineDiv">

                                            <Field name="city"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.city} name={field.name} labelText="Town" id={field.name} className="inputText" />
                                                }
                                            </Field>
                                            <Field name="phoneNumber"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.phoneNumber} name={field.name} labelText="Phone" id={field.name} className="inputText" />
                                                }
                                            </Field>
                                        </div>
                                        <div className="formInlineDiv">

                                            <Field name="healthRegion"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.healthRegion} name={field.name} labelText="Region" id={field.name} className="inputText" />
                                                }
                                            </Field>
                                            <Field name="healthDistrict"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.healthDistrict} name={field.name} labelText="District" id={field.name} className="inputText" />
                                                }
                                            </Field>
                                        </div>
                                        <Field name="dob"
                                        >
                                            {({ field }) =>
                                                <DatePicker value={values.dob} onChange={(...e) => this.handleDatePickerChange(values, ...e)} name={field.name} dateFormat="d/m/Y" datePickerType="single" light={true} className="inputText">
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
                                                    valueSelected={values.gender}
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
                    )}

                </Column>
            </Grid >
        )
    }
}

export default injectIntl(CreatePatientForm)