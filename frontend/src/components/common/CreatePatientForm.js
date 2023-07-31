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
    DatePicker,
    DatePickerInput,
    RadioButton,
    RadioButtonGroup,
    Section,
    Select,
    SelectItem, Accordion, AccordionItem

} from '@carbon/react';

import {Formik, Field, ErrorMessage, useFormikContext} from "formik";
import CreatePatientFormValues from '../formModel/innitialValues/CreatePatientFormValues';
import PatientFormObserver from "./PatientFormObserver";
import {AlertDialog, NotificationKinds} from "./CustomNotification";
import {NotificationContext} from "../layout/Layout";
import CreatePatientValidationSchema from '../formModel/validationSchema/CreatePatientValidationShema';

class CreatePatientForm extends React.Component {
    static contextType = NotificationContext;
    constructor(props) {
        super(props)
        this.state = {
            patientDetails: CreatePatientFormValues,
            healthRegions: [],
            healthDistricts: [],
            educationList : [] ,
            maritalStatuses : [],
            nationalities : [],
            phoneFormat: "",
            showActionsButton: props.showActionsButton,
            formAction: "ADD"
        }
    }
    _isMounted = false;
    _healthDistricts = [];

    handleDatePickerChange = (values, ...e) => {
        var patient = values
        patient.birthDateForDisplay = e[1];

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
        if (nextProps.selectedPatient.patientPK) {
            if (nextProps.selectedPatient.healthRegion != null) {
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
            nextState.patientDetails.patientUpdateStatus="UPDATE";
           // this.setState({ healthRegions: nextProps.selectedPatient.healthRegions })
            this.state.formAction = "UPDATE";
        }

        return true;
    }

    repopulatePatientInfo = ()=>{
        if(this.props.orderFormValues != null) {
            if (this.props.orderFormValues.patientProperties.firstName !== "" || this.props.orderFormValues.patientProperties.guid !== "") {
                this.setState({
                    patientDetails: this.props.orderFormValues.patientProperties
                });
            }
        }
    }

    componentDidMount() {
        this._isMounted = true;
        getFromOpenElisServer("/rest/health-regions", this.fetchHeathRegions);
        getFromOpenElisServer("/rest/education-list", this.fetchEducationList);
        getFromOpenElisServer("/rest/marital-statuses", this.fetchMaritalStatuses);
        getFromOpenElisServer("/rest/configuration-properties", this.fetchConfigurationProperties)
       // getFromOpenElisServer("/rest/nationalities", this.fetchNationalities);
        this.repopulatePatientInfo();
    }

    componentWillUnmount() {
        this._isMounted = false;
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

    accessionNumberValidationResponse = (res) => {
        if (res.status === false) {
            this.context.setNotificationVisible(true);
            this.context.setNotificationBody({kind: NotificationKinds.error, title: "Notification Message", message: res.body});
        }

    }

    handleSubjectNoValidation = (numberType,fieldId,numberValue) =>{
        if(numberValue !== ""){
            getFromOpenElisServer(`/rest/subjectNumberValidationProvider?fieldId=${fieldId}&numberType=${numberType}&subjectNumber=${numberValue}`, this.accessionNumberValidationResponse);
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
        this.setState({ healthDistricts: districts })
        this._healthDistricts = districts
    }

     fetchConfigurationProperties = (res) => {
         if (res.length > 0) {
             const filterProperty = res.find((config) => config.id === "phoneFormat");
             console.log(filterProperty)
             if (filterProperty !== undefined) {
                 this.setState({phoneFormat: filterProperty.value});
             }
         }
    }

    handleSubmit = (values) => {
        console.log(JSON.stringify(values))
       postToOpenElisServer("/rest/patient-management" ,JSON.stringify(values) , this.handlePost)
    };

    handlePost = (status) => {
        this.context.setNotificationVisible(true);
        if(status === 200){
            this.context.setNotificationBody({title: "Notification Message", message: "Patient Saved Succsfuly", kind: NotificationKinds.success})
        }else{
            this.context.setNotificationBody({title: "Notification Message", message: "Error While Saving Patient", kind: NotificationKinds.error})
        }
      };

    render() {

        return (

            <>
                 {/* {JSON.stringify(this.state.patientDetails)} */}
                 {this.context.notificationVisible === true ? <AlertDialog/> : ""}
                        <Formik
                            initialValues={this.state.patientDetails}
                            enableReinitialize
                            validationSchema={CreatePatientValidationSchema}
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
                                    {this.props.orderFormValues && <PatientFormObserver orderFormValues={this.props.orderFormValues} setOrderFormValues={this.props.setOrderFormValues} formAction={this.state.formAction}/> }
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
                                        <div className="inlineDiv">
                                            <Field name="subjectNumber"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.subjectNumber} name={field.name} labelText="Unique Health ID number" id={field.name} className="inputText"
                                                               onMouseOut={()=>{this.handleSubjectNoValidation("subjectNumber","subjectNumberID",values.subjectNumber)}}/>
                                                }
                                            </Field>
                                            <Field name="nationalId"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.nationalId} name={field.name} labelText="National Id" id={field.name} className="inputText"
                                                               onMouseOut={()=>{this.handleSubjectNoValidation("nationalId","nationalID",values.nationalId)}}/>
                                                                                         
                                                }
                                            </Field>
                                            <div className="error"></div>
                                            <div className="error">
                                                <ErrorMessage name = "nationalId"></ErrorMessage>
                                           </div>                     
                                        </div>
                                        <div className="inlineDiv">

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
                                        <div className="inlineDiv">

                                            <Field name="patientContact.person.lastName"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.patientContact?.person.lastName} name={field.name} labelText="Contact Last Name" id={field.name} className="inputText" />
                                                }
                                            </Field>
                                            <Field name="patientContact.person.firstName"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.patientContact?.person.firstName} name={field.name} labelText="Contact First Name" id={field.name} className="inputText" />
                                                }
                                            </Field>
                                        </div>
                                        <div className="inlineDiv">

                                            <Field name="patientContact.person.email"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.patientContact?.person.email} name={field.name} labelText="Contact Email" id={field.name} className="inputText" />
                                                }
                                            </Field>
                                            <Field name="patientContact.person.primaryPhone"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.patientContact?.person.primaryPhone} name={field.name}  labelText={`Contact Phone: ${this.state.phoneFormat}`} id={field.name} className="inputText" />
                                                }
                                            </Field>
                                            <div className="error">
                                                <ErrorMessage name = "patientContact.person.email"></ErrorMessage>
                                           </div> 
                                           <div className="error"></div>  
                                        </div>
                                        <div className="inlineDiv">

                                            <Field name="city"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.city} name={field.name} labelText="Town" id={field.name} className="inputText" />
                                                }
                                            </Field>
                                            <Field name="primaryPhone"
                                            >
                                                {({ field }) =>
                                                    <TextInput value={values.primaryPhone} name={field.name} labelText={`Phone: ${this.state.phoneFormat}`} id={field.name} className="inputText" />
                                                }
                                            </Field>
                                        </div>
                                        <div className="inlineDiv">
                                            <Field name="birthDateForDisplay"
                                            >
                                                {({ field }) =>
                                                    <DatePicker value={values.birthDateForDisplay} onChange={(...e) => this.handleDatePickerChange(values, ...e)} name={field.name} dateFormat="d/m/Y" datePickerType="single" light={true} className="inputText">
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
                                                        className="inputText"
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
                                            <div className="error">
                                                <ErrorMessage name = "birthDateForDisplay"></ErrorMessage>
                                           </div> 
                                           <div className="error">
                                             <ErrorMessage name = "gender"></ErrorMessage>
                                            </div>   
                                        </div>
                                    <Accordion>
                                        <AccordionItem title="Additional Information">
                                            <div className="inlineDiv">

                                                <Field name="streetAddress"
                                                >
                                                    {({ field }) =>
                                                        <TextInput value={values.streetAddress} name={field.name} labelText="Street" id={field.name} className="inputText" />
                                                    }
                                                </Field>
                                                <Field name="commune"
                                                >
                                                    {({ field }) =>
                                                        <TextInput value={values.commune} name={field.name} labelText="Camp/Commune" id={field.name} className="inputText" />
                                                    }
                                                </Field>
                                            </div>
                                            <div className="inlineDiv">

                                                <Field name="healthRegion"
                                                >
                                                    {({ field }) =>
                                                        <Select
                                                            id="health_region"
                                                            value={values.healthRegion}
                                                            name={field.name}
                                                            labelText="Region"
                                                            className="inputText"
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
                                                            className="inputText"
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
                                            <div className="inlineDiv">

                                                <Field name="education"
                                                >
                                                    {({ field }) =>
                                                        <Select
                                                            id="education"
                                                            value={values.education}
                                                            name={field.name}
                                                            labelText="Education"
                                                            className="inputText"
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
                                                            className="inputText"
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
                                            <div className="inlineDiv">

                                                <Field name="nationality"
                                                >
                                                    {({ field }) =>
                                                        <Select
                                                            id="nationality"
                                                            value={values.nationality}
                                                            name={field.name}
                                                            labelText="Nationality"
                                                            className="inputText"
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
                                                        <TextInput value={values.otherNationality} name={field.name} labelText="Specify Other nationality" id={field.name} className="inputText" />
                                                    }
                                                </Field>
                                            </div>
                                        </AccordionItem>
                                    </Accordion>
                                        {this.state.showActionsButton && <div className="inlineDiv">
                                            <Button type="submit" id="submit">
                                                <FormattedMessage id="label.button.save" />
                                            </Button>
                                            <Button id="clear" kind='danger' onClick={() => { resetForm({ values: CreatePatientFormValues }); this._healthDistricts = [] }}>
                                                <FormattedMessage id="label.button.clear" />
                                            </Button>

                                        </div>
                                        }
                                </Form>

                            )}
                        </Formik>
            </>
        )
    }
}

export default injectIntl(CreatePatientForm);
