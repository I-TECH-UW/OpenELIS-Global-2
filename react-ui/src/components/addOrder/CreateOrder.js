import React, { useEffect, useRef, useState } from 'react'
import {
    Grid,
    Column,
    Form,
    TextInput,
    Checkbox,
    Link,
    Select,
    SelectItem,
    DatePicker,
    DatePickerInput,
    TimePicker
} from '@carbon/react';

import AddSample from '../sample/AddSample'

import { getFromOpenElisServer, postToOpenElisServer } from '../utils/Utils';

import config from '../../config.json';
import { priorities } from "../data/orderOptions";
import { orderValues } from "../formModel/innitialValues/CreateOrderFormValues";

const CreateOrder = () => {
    const componentMounted = useRef(true);

    const [formValues, setFormValues] = useState(orderValues);
    const [programs, setPrograms] = useState([]);
    const [paymentOptions, setPaymentOptions] = useState([]);
    const [samplingPerformed, setSamplingPerformed] = useState([]);
    const [otherSamplingVisible, setOtherSamplingVisible] = useState(false);



    const handleDatePickerChange = (date, value) => {
        setFormValues({
            ...formValues,
            date: value
        });
    }

    const fetchPrograms = (programsList) => {
        if (componentMounted.current) {
            setPrograms(programsList);
        }
    }

    const getPaymentOptions = (options) => {
        if (componentMounted.current) {
            setPaymentOptions(options);
        }
    }

    const getSamplingPerformedOptions = (performedSamples) => {
        if (componentMounted.current) {
            setSamplingPerformed(performedSamples);
        }

    }

    const handleSamplingPerformed = (e) => {
        const { name, value } = e.target;
        if (value === "1310") {
            setOtherSamplingVisible(!otherSamplingVisible);
        } else {
            setOtherSamplingVisible(false);
        }
    };

    useEffect(() => {

        getFromOpenElisServer("/rest/dictionary?category=programs", fetchPrograms)
        getFromOpenElisServer("/rest/dictionary?category=patientPayment", getPaymentOptions)
        getFromOpenElisServer("/rest/dictionary?category=testLocationCode", getSamplingPerformedOptions)

        return () => componentMounted.current = false
    }, []);


    return (<>
        <Grid fullWidth={true} className="">
            <Column lg={16}>
                <Form>
                    <div className="section">
                        <div className="formInlineDiv">
                            <Checkbox labelText="Remember site and requester" id="rememberSiteAndRequester" />
                        </div>

                        <div className="formInlineDiv">
                            <Grid>
                                <Column lg={12}>
                                    <input type="button" value="-" />
                                    Order <span className="requiredFieldIndicator"> *</span>
                                </Column>
                            </Grid>

                        </div>

                        <div className="formInlineDiv">
                            <Grid>
                                <Column md={6}>
                                    <TextInput name="labNo" labelText="Lab No: "
                                        id="labNo" className="inputText" />
                                </Column>
                                <Column md={6}>
                                    Scan Or Enter Manually Or
                                    <Link href="#">Generate</Link>
                                </Column>
                            </Grid>

                        </div>

                        <div className="formInlineDiv">
                            <Select
                                id="priorityId"
                                name="priority"
                                labelText="priority"
                                required>
                                {priorities.map((priority, index) => {
                                    return (<SelectItem key={index}
                                        text={priority.label}
                                        value={priority.value}
                                    />);
                                })}
                            </Select>
                        </div>
                        <div className="formInlineDiv">
                            <DatePicker value={formValues.requestDate}
                                name="requestDate" onChange={(e) => handleDatePickerChange("requestDate", e)}
                                dateFormat="d/m/Y" datePickerType="single" light={true} className="">
                                <DatePickerInput
                                    id="date-picker-default-id"
                                    placeholder="dd/mm/yyyy"
                                    labelText="Request Date"
                                    type="text"
                                    name="requestDate"
                                />
                            </DatePicker>

                            <DatePicker value={formValues.receivedDate}
                                name="requestDate" onChange={(e) => handleDatePickerChange("receivedDate", e)}
                                dateFormat="d/m/Y" datePickerType="single" light={true} className="">
                                <DatePickerInput
                                    id="date-picker-default-id"
                                    placeholder="dd/mm/yyyy"
                                    labelText="Received Date"
                                    type="text"
                                    name="receivedDate"
                                />
                            </DatePicker>
                        </div>

                        <div className="formInlineDiv">
                            <TimePicker id="time-picker" labelText="Reception Time (hh:mm):" />

                            <DatePicker value={formValues.nextVisitDate}
                                name="nextVisitDate" onChange={(e) => handleDatePickerChange("nextVisitDate", e)}
                                dateFormat="d/m/Y" datePickerType="single" light={true} className="">
                                <DatePickerInput
                                    id="date-picker-default-id"
                                    placeholder="dd/mm/yyyy"
                                    labelText="Date Of Next Visit"
                                    type="text"
                                    name="nextVisitDate"
                                />
                            </DatePicker>
                        </div>
                        <div className="formInlineDiv">
                            <TextInput name="siteName" labelText="Site Name: "
                                id="siteName" className="inputText" />
                            <Select
                                id="requesterDepartmentId"
                                name="requesterDepartmentId"
                                labelText="ward/dept/unit:"
                                required>
                                <SelectItem
                                    value=""
                                    text=""
                                />
                            </Select>
                        </div>

                        <div className="formInlineDiv">

                            <Select
                                id="programId"
                                name="program"
                                labelText="program:"
                                required>
                                {
                                    programs.map(program => {
                                        return (
                                            <SelectItem key={program.id}
                                                value={program.id}
                                                text={program.value} />
                                        )
                                    })
                                }
                            </Select>
                        </div>
                    </div>
                    <div className="section">
                        <div className="section">
                            <div className="formInlineDiv">

                                <Select
                                    id="requesterId"
                                    name="requester"
                                    labelText="Requester"
                                    required>
                                    <SelectItem
                                        value=""
                                        text=""
                                    />
                                </Select>

                                <TextInput name="requesterLastName" labelText="Requester's LastName: "
                                    id="requesterLastName" className="inputText" />

                            </div>
                            <div className='formInlineDiv'>

                                <TextInput name="requesterFirstName" labelText="Requester's FirstName: "
                                    id="requesterFirstName" className="inputText" />

                                <TextInput name="providerWorkPhone" labelText="Requester Phone: +225-xx-xx-xx-xx: "
                                    id="providerWorkPhoneId" className="inputText" />
                            </div>

                            <div className='formInlineDiv'>

                                <TextInput name="providerFax" labelText="Requester's Fax Number: "
                                    id="providerFaxId" className="inputText" />

                                <TextInput name="providerEmail" labelText="Requester's Email: "
                                    id="providerEmailId" className="inputText" />
                            </div>
                        </div>
                        <div className="formInlineDiv">

                            <Select
                                id="paymentOptionSelectionId"
                                name="paymentOptionSelections"
                                labelText="Patient payment status:"
                                required>

                                <SelectItem
                                    value=""
                                    text=""
                                />
                                {
                                    paymentOptions.map(option => {
                                        return (
                                            <SelectItem key={option.id}
                                                value={option.id}
                                                text={option.value}
                                            />
                                        )
                                    })
                                }

                            </Select>

                            <TextInput name="billingReferenceNumber" labelText="URAP Number:"
                                id="billingReferenceNumberId" className="inputText" />

                        </div>

                        <div className="formInlineDiv">

                            <Select
                                id="testLocationCodeId"
                                name="testLocationCode"
                                labelText="Sampling performed for analysis::"
                                onChange={(e) => handleSamplingPerformed(e)}
                                required>
                                <SelectItem
                                    value=""
                                    text=""
                                />
                                {
                                    samplingPerformed.map(option => {
                                        return (
                                            <SelectItem key={option.id}
                                                value={option.id}
                                                text={option.value}
                                            />
                                        )
                                    })
                                }

                            </Select>

                            {otherSamplingVisible && <TextInput name="testLocationCodeOther" labelText="Other specify:"
                                id="testLocationCodeOtherId" className="inputText" />}

                        </div>
                    </div>
                    <div className='section'>
                        <AddSample />
                    </div>
                </Form>
            </Column>
        </Grid>
    </>)
}

export default CreateOrder;
