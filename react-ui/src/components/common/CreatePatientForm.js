import React from 'react'
import { FormattedMessage, injectIntl } from 'react-intl'
import "../Style.css";
import { getFromOpenElisServer ,postToOpenElisServer} from '../utils/Utils';
import config from '../../config.json';
import { nationalityList } from '../data/countries';

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
    Select,
    SelectItem

} from '@carbon/react';

import { Formik, Field } from "formik";
import CreatePatientFormValues from '../formModel/innitialValues/CreatePatientFormValues';

class CreatePatientForm extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            patientDetails: CreatePatientFormValues,
            showForm: false,
            healthRegions: [],
            healthDistricts: [],
            educationList : [] ,
            maritalStatuses : [],
            nationalities : [],
            showActionsButton: props.showActionsButton
        }
    }
    _isMounted = false;
    _healthDistricts = [];

    handleDatePickerChange = (values, ...e) => {
        var patient = values
        patient.dob = e[1];

        this.setState({
            patientDetails: patient
        });
    }

    handleRegionSelection = (e, values) => {
        var patient = values
        patient.healthDistrict = ""
        this.setState({
            patientDetails: patient
        });
        const { value } = e.target;
        getFromOpenElisServer("/rest/health-districts-for-region?regionId=" + value, this.fetchHeathDistricts)
    }


    shouldComponentUpdate(nextProps, nextState) {
        if (nextProps.selectedPatient.id) {
            if (nextProps.selectedPatient.healthRegion != 0) {
                // getFromOpenElisServer("/rest/health-districts-for-region?regionId=" + nextProps.selectedPatient.healthRegion, (districts) => this.fetchDefaultHeathDistricts(districts, update))
                // used XMLHttpRequest instead of Fecth ,in order to make synchronous calls here
                const request = new XMLHttpRequest()
                request.open('GET', config.serverBaseUrl + '/rest/health-districts-for-region?regionId=' + nextProps.selectedPatient.healthRegion, false);
                request.setRequestHeader("credentials", "include");
                request.send();
                //nextState.healthDistricts = JSON.parse(request.response);
                this._healthDistricts = JSON.parse(request.response);
            } else {
                //nextState.healthDistricts = [];
                this._healthDistricts = [];
            }
            nextState.patientDetails = nextProps.selectedPatient;
            nextState.showForm = true;

        }

        return true;
    }

    componentDidMount() {
        this._isMounted = true;
        getFromOpenElisServer("/rest/health-regions", this.fetchHeathRegions);
        getFromOpenElisServer("/rest/education-list", this.fetchEducationList);
        getFromOpenElisServer("/rest/marital-statuses", this.fetchMaritalStatuses);
       // getFromOpenElisServer("/rest/nationalities", this.fetchNationalities);
    }

    componentWillUnmount() {
        this._isMounted = false;
    }

    addNewPatient = () => {
        this.setState({
            showForm: true,
        })
    }

    fetchHeathRegions = (regions) => {
        if (this._isMounted) {
            this.setState({ healthRegions: regions })
        }
    }

    fetchNationalities = (nationalities) => {
        if (this._isMounted) {
            this.setState({ nationalities: nationalities })
        }
    }

    fetchMaritalStatuses = (statuses) => {
        if (this._isMounted) {
            this.setState({ maritalStatuses: statuses })
        }
    }

    fetchEducationList = (eductationList) => {
        if (this._isMounted) {
            this.setState({ educationList: eductationList })
        }
    }

    fetchHeathDistricts = (districts) => {
        //this.setState({ healthDistricts: districts })
        this._healthDistricts = districts
    }

    handleSubmit = (values) => {
        console.log(JSON.stringify(values))
        postToOpenElisServer("/rest/patient-management" ,JSON.stringify(values) , this.handlePost)
    };

    handlePost = (status) => {
        alert(status)
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
                                resetForm,
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
                                                    <TextInput value={values.subjectNumber} name={field.name} labelText="Unique Health ID number" id={field.name} className="" />
                                                }
                                            </Field>
                                            <Field name="nationalID"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.nationalID} name={field.name} labelText="National Id" id={field.name} className="" />
                                                }
                                            </Field>
                                        </div>
                                        <div className="formInlineDiv">

                                            <Field name="lastName"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.lastName} name={field.name} labelText="Last Name" id={field.name} className="" />
                                                }
                                            </Field>
                                            <Field name="firstName"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.firstName} name={field.name} labelText="First Name" id={field.name} className="" />
                                                }
                                            </Field>
                                        </div>
                                        <div className="formInlineDiv">

                                            <Field name="contactLastName"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.contactLastName} name={field.name} labelText="Contact Last Name" id={field.name} className="" />
                                                }
                                            </Field>
                                            <Field name="contactFirstName"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.contactFirstName} name={field.name} labelText="Contact First Name" id={field.name} className="" />
                                                }
                                            </Field>
                                        </div>
                                        <div className="formInlineDiv">

                                            <Field name="contactEmail"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.contactEmail} name={field.name} labelText="Contact Email" id={field.name} className="" />
                                                }
                                            </Field>
                                            <Field name="contactPhone"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.contactPhone} name={field.name} labelText="Contact Phone" id={field.name} className="" />
                                                }
                                            </Field>
                                        </div>
                                        <div className="formInlineDiv">

                                            <Field name="street"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.street} name={field.name} labelText="Street" id={field.name} className="" />
                                                }
                                            </Field>
                                            <Field name="commune"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.commune} name={field.name} labelText="Camp/Commune" id={field.name} className="" />
                                                }
                                            </Field>
                                        </div>
                                        <div className="formInlineDiv">

                                            <Field name="city"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.city} name={field.name} labelText="Town" id={field.name} className="" />
                                                }
                                            </Field>
                                            <Field name="phoneNumber"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.phoneNumber} name={field.name} labelText="Phone" id={field.name} className="" />
                                                }
                                            </Field>
                                        </div>
                                        <div className="formInlineDiv">

                                            <Field name="healthRegion"
                                            >
                                                {({ field }) =>
                                                    <Select
                                                        id="health_region"
                                                        value={values.healthRegion}
                                                        name={field.name}
                                                        labelText="Region"
                                                        className=""
                                                        onChange={(e) => this.handleRegionSelection(e, values)}
                                                    >
                                                        <SelectItem
                                                            text=""
                                                            value=""
                                                        />
                                                        {this.state.healthRegions.map((region, index) => (
                                                            <SelectItem
                                                                text={region.value}
                                                                value={region.id}
                                                                key={index}
                                                            />
                                                        ))}
                                                    </Select>
                                                }
                                            </Field>
                                            <Field name="healthDistrict"
                                            >
                                                {({ field }) =>
                                                    <Select
                                                        id="health_district"
                                                        value={values.healthDistrict}
                                                        name={field.name}
                                                        labelText="District"
                                                        className=""
                                                        onChange={()=> {}}
                                                    >
                                                        <SelectItem
                                                            text=""
                                                            value=""
                                                        />
                                                        {this._healthDistricts.map((district, index) => (
                                                            <SelectItem
                                                                text={district.value}
                                                                value={district.value}
                                                                key={index}
                                                            />
                                                        ))}
                                                    </Select>
                                                }
                                            </Field>
                                        </div>
                                        <div className="formInlineDiv">
                                            <Field name="dob"
                                            >
                                                {({ field }) =>
                                                    <DatePicker value={values.dob} onChange={(...e) => this.handleDatePickerChange(values, ...e)} name={field.name} dateFormat="d/m/Y" datePickerType="single" light={true} className="">
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
                                                        id="create_patient_gender"
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
                                        </div>
                                        <div className="formInlineDiv">

                                            <Field name="education"
                                            >
                                                {({ field }) =>
                                                    <Select
                                                        id="education"
                                                        value={values.education}
                                                        name={field.name}
                                                        labelText="Education"
                                                        className=""
                                                        onChange={()=> {}}
                                                    >
                                                        <SelectItem
                                                            text=""
                                                            value=""
                                                        />
                                                        {this.state.educationList.map((education, index) => (
                                                            <SelectItem
                                                                text={education.value}
                                                                value={education.value}
                                                                key={index}
                                                            />
                                                        ))}
                                                    </Select>
                                                }
                                            </Field>
                                            <Field name="maritialStatus"
                                            >
                                                {({ field }) =>
                                                    <Select
                                                        id="maritialStatus"
                                                        value={values.maritialStatus}
                                                        name={field.name}
                                                        labelText="Marital Status"
                                                        className=""
                                                        onChange={()=> {}}
                                                    >
                                                        <SelectItem
                                                            text=""
                                                            value=""
                                                        />
                                                        {this.state.maritalStatuses.map((status, index) => (
                                                            <SelectItem
                                                                text={status.value}
                                                                value={status.value}
                                                                key={index}
                                                            />
                                                        ))}
                                                    </Select>
                                                }
                                            </Field>
                                        </div>
                                        <div className="formInlineDiv">

                                            <Field name="nationality"
                                            >
                                                {({ field }) =>
                                                    <Select
                                                        id="nationality"
                                                        value={values.nationality}
                                                        name={field.name}
                                                        labelText="Nationality"
                                                        className=""
                                                        onChange={()=> {}}
                                                    >
                                                        <SelectItem
                                                            text=""
                                                            value=""
                                                        />
                                                        {nationalityList.map((nationality, index) => (
                                                            <SelectItem
                                                                text={nationality.label}
                                                                value={nationality.value}
                                                                key={index}
                                                            />
                                                        ))}
                                                    </Select>
                                                }
                                            </Field>
                                            <Field name="otherNationality"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.otherNationality} name={field.name} labelText="Specify Other nationality" id={field.name} className="" />
                                                }
                                            </Field>
                                        </div>
                                        {this.state.showActionsButton && <div className="formInlineDiv">
                                            <Button type="submit" id="submit">
                                                <FormattedMessage id="label.button.save" />
                                            </Button>
                                            <Button id="clear" kind='danger' onClick={() => { resetForm({ values: CreatePatientFormValues }); this._healthDistricts = [] }}>
                                                <FormattedMessage id="label.button.clear" />
                                            </Button>

                                        </div>
                                        }
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

export default injectIntl(CreatePatientForm);
