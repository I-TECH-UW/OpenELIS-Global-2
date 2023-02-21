import React, {useEffect, useRef, useState} from 'react'
import {Add, Subtract} from '@carbon/react/icons';
import {
    TextInput,
    Checkbox,
    Link,
    Select,
    IconButton,
    SelectItem,
    DatePicker,
    DatePickerInput,
    TimePicker
} from '@carbon/react';

import {getFromOpenElisServer, getFromOpenElisServerWithTokenHeader, postToOpenElisServer} from '../utils/Utils';
import {priorities} from "../data/orderOptions";
import {orderValues} from "../formModel/innitialValues/CreateOrderFormValues";
import {NotificationKinds, useShowNotification} from "../common/useShowNotification";


const AddOrderTopForm = () => {

    const [formValues, setFormValues] = useState(orderValues);
    const componentMounted = useRef(true);
    const [otherSamplingVisible, setOtherSamplingVisible] = useState(false);
    const [orderIconToggled, setOrderIconToggled] = useState(false);
    const [programs, setPrograms] = useState([]);
    const [paymentOptions, setPaymentOptions] = useState([]);
    const [samplingPerformed, setSamplingPerformed] = useState([]);
    const [labNo, setLabNo] = useState("");
    const [notificationVisible, setNotificationVisible, setNotificationBody, AlertDialog] = useShowNotification();

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
        const {name, value} = e.target;
        if (value === "1310") {
            setOtherSamplingVisible(!otherSamplingVisible);
        } else {
            setOtherSamplingVisible(false);
        }
    };


    const formatServerResponse = (serverResponse, neededText) => {
        const regex = /(<([^>]+)>)/gi;
        let removedTags = serverResponse.replace(regex, "");
        return removedTags.replace(neededText, "");
    }

    const handleLabNoGeneration = () => {
        const serverResponse = getFromOpenElisServerWithTokenHeader('/ajaxQueryXML?provider=SampleEntryGenerateScanProvider');
        const generateLabNo = formatServerResponse(serverResponse, "valid");
        setLabNo(generateLabNo);
        setNotificationVisible(false);
    }


    const handleLabNoValidation = () => {
        const serverResponse = getFromOpenElisServerWithTokenHeader('/ajaxXML?provider=SampleEntryAccessionNumberValidationProvider&ignoreYear=false&ignoreUsage=false&field=labNo&accessionNumber=' + labNo);
        const message = formatServerResponse(serverResponse, "labNo");
        if (message !== "valid") {
            setNotificationVisible(true);
            setNotificationBody({kind: NotificationKinds.error, title: "Notification Message", message: message});
        }
    }


    const handlePhoneNoValidation = () => {
        if (formValues.requesterPhone !== "") {
            const providerPhoneNo = formValues.requesterPhone.replace(/\+/g, '%2B');

            const serverResponse = getFromOpenElisServerWithTokenHeader("/ajaxXML?provider=PhoneNumberValidationProvider&fieldId=providerWorkPhoneID&value=" + providerPhoneNo);
            const message = formatServerResponse(serverResponse, "providerWorkPhoneID");

            if (message !== "valid") {
                setNotificationVisible(true);
                setNotificationBody({title: "Notification Message", message: message, kind: NotificationKinds.error});
            }
        }
    }

    useEffect(() => {

        getFromOpenElisServer("/rest/programs", fetchPrograms)
        getFromOpenElisServer("/rest/patientPaymentsOptions", getPaymentOptions)
        getFromOpenElisServer("/rest/testLocationCodes", getSamplingPerformedOptions)

        return () => {
            componentMounted.current = false
        }
    }, []);

    return (
        <>
            {notificationVisible === true ? <AlertDialog/> : ""}
            <div className="inlineDiv">
                <Checkbox labelText="Remember site and requester" id="rememberSiteAndRequester"/>
            </div>

            <div className="inlineDiv">
                <IconButton label="" onClick={() => setOrderIconToggled(!orderIconToggled)} kind='tertiary' size='sm'>
                    {orderIconToggled ? <Add size={18}/> : <Subtract size={18}/>}
                </IconButton>
                &nbsp; &nbsp;
                <div>Order <span className="requiredFieldIndicator"> *</span></div>
            </div>

            <div className="formInlineDiv">
                <TextInput name="labNo" value={labNo} onMouseLeave={handleLabNoValidation} onChange={(e) => {
                    setLabNo(e.target.value);
                    setNotificationVisible(false);
                }} labelText="Lab No: "
                           id="labNo" className="inputText"/>
                <div>
                    Scan Or Enter Manually Or  &nbsp;
                    <Link href="#" onClick={handleLabNoGeneration}>Generate</Link>
                </div>

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
            </div>
            <div className="formInlineDiv">
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
                <TimePicker id="time-picker" labelText="Reception Time (hh:mm):"/>
                <br/><br/>
            </div>
            <div className="formInlineDiv">
                <div>
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
                <div>
                    <TextInput name="siteName" labelText="Site Name: "
                               id="siteName" className="inputText"/>
                </div>
            </div>
            <div className="formInlineDiv">
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
                                            text={program.value}/>
                            )
                        })
                    }
                </Select>
            </div>

            <div className="formInlineDiv">
                <br/>
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

                <TextInput name="requesterLastName" labelText="Requesters' LastName: "
                           id="requesterLastName" className="inputText"/>

            </div>
            <div className='formInlineDiv'>
                <br/>
                <TextInput name="requesterFirstName" labelText="Requester's FirstName: "
                           id="requesterFirstName" className="inputText"/>

                <TextInput name="providerWorkPhone" value={formValues.requesterPhone} onChange={(e) => {
                    setFormValues({
                        ...formValues,
                        requesterPhone: e.target.value
                    });
                    setNotificationVisible(false);
                }} onMouseLeave={handlePhoneNoValidation} labelText="Requester Phone: +225-xx-xx-xx-xx: "
                           id="providerWorkPhoneId" className="inputText"/>
            </div>

            <div className='formInlineDiv'>
                <br/>
                <TextInput name="providerFax" labelText="Requester's Fax Number: "
                           id="providerFaxId" className="inputText"/>

                <TextInput name="providerEmail" labelText="Requester's Email: "
                           id="providerEmailId" className="inputText"/>
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
                           id="billingReferenceNumberId" className="inputText"/>

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
                                                    id="testLocationCodeOtherId" className="inputText"/>}

            </div>
        </>
    )
}

export default AddOrderTopForm
