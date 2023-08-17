import React, {useContext, useEffect, useRef, useState} from 'react'
import {Checkbox, Link, Select, SelectItem, Stack, TextInput, TimePicker} from "@carbon/react";
import CustomDatePicker from "../common/CustomDatePicker";
import {getFromOpenElisServer} from "../utils/Utils";
import {NotificationContext} from "../layout/Layout";
import {priorities} from "../data/orderOptions";
import {NotificationKinds} from "../common/CustomNotification";
import AutoComplete from "../common/AutoComplete";
import OrderResultReporting from "./OrderResultReporting";

const AddOrder = (props) => {
    const {orderFormValues, setOrderFormValues, samples} = props;
    const componentMounted = useRef(true); 
    const [otherSamplingVisible, setOtherSamplingVisible] = useState(false);
    const [providers, setProviders] = useState([]);
    const [paymentOptions, setPaymentOptions] = useState([]);
    const [samplingPerformed, setSamplingPerformed] = useState([]);
    const [allowSiteNameOptions, setAllowSiteNameOptions] = useState("false");
    const [allowRequesterOptions, setAllowRequesterOptions] = useState("false");
    const {setNotificationVisible, setNotificationBody} = useContext(NotificationContext);
    const [siteNames, setSiteNames] = useState([]);
    const [configurationProperties, setConfigurationProperties] = useState([{id: "", value: ""}]);
    const [phoneFormat, setPhoneFormat] = useState("");


    const handleDatePickerChange = (datePicker, date) => {
        let obj = null;
        switch (datePicker) {
            case "requestDate":
                obj = {...orderFormValues.sampleOrderItems, requestDate: date}
                break;
            case "receivedDate":
                obj = {...orderFormValues.sampleOrderItems, receivedDateForDisplay: date}
                break;
            case "nextVisitDate":
                obj = {...orderFormValues.sampleOrderItems, nextVisitDate: date}
                break;
        }
        setOrderFormValues({
            ...orderFormValues, sampleOrderItems: obj
        });
    }

    function handlePaymentStatus(e) {
        setOrderFormValues({
            ...orderFormValues, sampleOrderItems: {
                ...orderFormValues.sampleOrderItems, paymentOptionSelection: e.target.value
            }
        });
    }

    function handleBillReferenceNo(e) {
        setOrderFormValues({
            ...orderFormValues, sampleOrderItems: {
                ...orderFormValues.sampleOrderItems, billingReferenceNumber: e.target.value
            }
        });
    }

    function handleRequesterFax(e) {
        setOrderFormValues({
            ...orderFormValues, sampleOrderItems: {
                ...orderFormValues.sampleOrderItems, providerFax: e.target.value
            }
        });
    }

    function handleRequesterEmail(e) {
        setOrderFormValues({
            ...orderFormValues, sampleOrderItems: {
                ...orderFormValues.sampleOrderItems, providerEmail: e.target.value
            }
        });
    }

    function handleRequesterWorkPhone(e) {
        setOrderFormValues({
            ...orderFormValues, sampleOrderItems: {
                ...orderFormValues.sampleOrderItems, providerWorkPhone: e.target.value
            }
        });
        setNotificationVisible(false);
    }

    function handleRequesterFirstName(e) {
        setOrderFormValues({
            ...orderFormValues, sampleOrderItems: {
                ...orderFormValues.sampleOrderItems, providerFirstName: e.target.value
            }
        });
    }

    function handleRequesterLastName(e) {
        setOrderFormValues({
            ...orderFormValues, sampleOrderItems: {
                ...orderFormValues.sampleOrderItems, providerLastName: e.target.value
            }
        });
    }


    const handleSamplingPerformed = (e) => {
        const {value} = e.target;
        if (value === "1310") {
            setOtherSamplingVisible(!otherSamplingVisible);
        } else {
            setOtherSamplingVisible(false);
        }
        setOrderFormValues({
            ...orderFormValues, sampleOrderItems: {
                ...orderFormValues.sampleOrderItems, testLocationCode: value
            }
        });
    };

    function handleOtherLocationCode(e) {
        setOrderFormValues({
            ...orderFormValues, sampleOrderItems: {
                ...orderFormValues.sampleOrderItems, otherLocationCode: e.target.value
            }
        });
    }

    function handleReceivedTime(e) {
        setOrderFormValues({
            ...orderFormValues, sampleOrderItems: {...orderFormValues.sampleOrderItems, receivedTime: e.target.value}
        });
    }


    const handleLabNoGeneration = (e) => {
        e.preventDefault();
        getFromOpenElisServer('/rest/SampleEntryGenerateScanProvider', fetchGeneratedAccessionNo);
    }

    function accessionNumberValidationResults(res) {
        if (res.status === false) {
            setNotificationVisible(true);
            setNotificationBody({kind: NotificationKinds.error, title: "Notification Message", message: res.body});
        }

    }

    function handleProviderSelectOptions(providerId) {
        setOrderFormValues({
            ...orderFormValues, sampleOrderItems: {...orderFormValues.sampleOrderItems, providerId: providerId}
        });

        getFromOpenElisServer('/rest/practitioner?providerId=' + providerId, fetchPractitioner);
    }

    function fetchPractitioner(data) {
        setOrderFormValues({
            ...orderFormValues, sampleOrderItems: {
                ...orderFormValues.sampleOrderItems,
                providerFirstName: data.person.firstName,
                providerLastName: data.person.lastName,
                providerWorkPhone: data.person.workPhone,
                providerEmail: data.person.email,
                providerFax: data.person.fax
            }
        });
    }

    function handleRequesterDept(e) {

    }

    function handleSiteName(e) {
        setOrderFormValues({
            ...orderFormValues,
            sampleOrderItems: {...orderFormValues.sampleOrderItems, referringSiteName: e.target.value}
        });
    }

    function handleAutoCompleteSiteName(siteId) {
        setOrderFormValues({
            ...orderFormValues, sampleOrderItems: {...orderFormValues.sampleOrderItems, referringSiteId: siteId}
        });
    }

    function handleLabNo(e) {
        setOrderFormValues({
            ...orderFormValues, sampleOrderItems: {...orderFormValues.sampleOrderItems, labNo: e.target.value}
        });
        setNotificationVisible(false);
    }

    const handleLabNoValidation = () => {
        if (orderFormValues.sampleOrderItems.labNo !== "") {
            getFromOpenElisServer('/rest/SampleEntryAccessionNumberValidation?ignoreYear=false&ignoreUsage=false&field=labNo&accessionNumber=' + orderFormValues.sampleOrderItems.labNo, accessionNumberValidationResults);
        }
    }

    function fetchPhoneNoValidation(res) {
        if (res.status === false) {
            setNotificationBody({title: "Notification Message", message: res.body, kind: NotificationKinds.error});
            setNotificationVisible(true);
        }
    }

    const handlePhoneNoValidation = () => {
        if (orderFormValues.sampleOrderItems.providerWorkPhone !== "") {
            const providerPhoneNo = orderFormValues.sampleOrderItems.providerWorkPhone.replace(/\+/g, '%2B');
            getFromOpenElisServer("/rest/PhoneNumberValidationProvider?fieldId=providerWorkPhoneID&value=" + providerPhoneNo, fetchPhoneNoValidation);
        }
    }


    function handleRememberCheckBox(e) {
        let checked = false;
        if (e.currentTarget.checked) {
            checked = true;
        }
        setOrderFormValues({
            ...orderFormValues, rememberSiteAndRequester: checked
        });
    }

    function findConfigurationProperty(property) {
        if (configurationProperties.length > 0) {
            const filterProperty = configurationProperties.find((config) => config.id === property);
            if (filterProperty !== undefined) {
                return filterProperty.value
            }
        }
    }

    const fetchConfigurationProperties = (res) => {
        if (componentMounted.current) {
            setConfigurationProperties(res);
        }
    }

    useEffect(() => {

        const currentDate = findConfigurationProperty("currentDateAsText");
        const currentTime = findConfigurationProperty("currentTimeAsText");
        const siteNameConfig = findConfigurationProperty("restrictFreeTextRefSiteEntry");
        const requesterConfig = findConfigurationProperty("restrictFreeTextProviderEntry");

        setPhoneFormat(findConfigurationProperty("phoneFormat"));

        setOrderFormValues({
            ...orderFormValues, currentDate: currentDate, sampleOrderItems: {
                ...orderFormValues.sampleOrderItems,
                requestDate: currentDate,
                receivedDateForDisplay: currentDate,
                nextVisitDate: currentDate,
                receivedTime: currentTime
            }
        });
        setAllowSiteNameOptions(siteNameConfig);
        setAllowRequesterOptions(requesterConfig);

    }, [configurationProperties]);


    function handleRequesterDept(e) {

    }

    function handlePriority(e) {
        setOrderFormValues({
            ...orderFormValues, sampleOrderItems: {...orderFormValues.sampleOrderItems, priority: e.target.value}
        });
    }

    function fetchGeneratedAccessionNo(res) {
        if (res.status) {
            setOrderFormValues({
                ...orderFormValues, sampleOrderItems: {...orderFormValues.sampleOrderItems, labNo: res.body}
            });
            setNotificationVisible(false);
        }
    }

    const reportingNotifications = (object) => {
        setOrderFormValues({
            ...orderFormValues,
            customNotificationLogic: true,
            patientSMSNotificationTestIds: object.patientSMSNotificationTestIds,
            patientEmailNotificationTestIds: object.patientEmailNotificationTestIds,
            providerSMSNotificationTestIds: object.providerSMSNotificationTestIds,
            providerEmailNotificationTestIds: object.providerEmailNotificationTestIds,
        });
    }


    const getSampleEntryPreform = (response) => {
        if (componentMounted.current) {
            setSiteNames(response.sampleOrderItems.referringSiteList)
            setPaymentOptions(response.sampleOrderItems.paymentOptions);
            setSamplingPerformed(response.sampleOrderItems.testLocationCodeList);
            setProviders(response.sampleOrderItems.providersList);
        }
    }

    useEffect(() => {
        getFromOpenElisServer("/rest/SamplePatientEntry", getSampleEntryPreform);
        getFromOpenElisServer("/rest/configuration-properties", fetchConfigurationProperties)
        window.scrollTo(0, 0)
        return () => {
            componentMounted.current = false
        }
    }, []);

    return (<>
        <Stack gap={10}>
            <div className="orderLegendBody">
                <h3>ORDER</h3>
                <div className="formInlineDiv">
                    <div className="inputText">
                        <TextInput name="labNo" value={orderFormValues.sampleOrderItems.labNo}
                                   onMouseLeave={handleLabNoValidation}
                                   onChange={(e) => handleLabNo(e)}
                                   labelText="Lab No: "
                                   id="labNo" className="inputText"/>
                        <div className="inputText">
                            Scan OR Enter Manually OR <Link href="#"
                                                            onClick={(e) => handleLabNoGeneration(e)}>Generate</Link>
                        </div>
                    </div>
                    <Select
                        className="inputText"
                        id="priorityId"
                        name="priority"
                        labelText="priority"
                        value={orderFormValues.sampleOrderItems.priority}
                        onChange={handlePriority}
                        required>
                        {priorities.map((priority, index) => {
                            return (<SelectItem key={index}
                                                text={priority.label}
                                                value={priority.value}
                            />);
                        })}
                    </Select>

                </div>
                <div className="inlineDiv">
                    <CustomDatePicker id={"requestDate"} labelText={"Request Date"} autofillDate={true}
                                      value={orderFormValues.sampleOrderItems.requestDate} className="inputText"
                                      onChange={(date) => handleDatePickerChange("requestDate", date)}/>

                    <CustomDatePicker id={"receivedDate"} labelText={"Received Date"} className="inputText" autofillDate={true}
                                      value={orderFormValues.sampleOrderItems.receivedDateForDisplay}
                                      onChange={(date) => handleDatePickerChange("receivedDate", date)}/>
                </div>
                <div className="inlineDiv">
                    <TimePicker id="receivedTime" className="inputText" labelText="Reception Time (hh:mm):"
                                onChange={handleReceivedTime}
                                value={orderFormValues.sampleOrderItems.receivedTime == null ? '' : orderFormValues.sampleOrderItems.receivedTime}
                    />

                    <CustomDatePicker id={"nextVisitDate"} className="inputText" labelText={"Next Visit Date"}
                                      value={orderFormValues.sampleOrderItems.nextVisitDate} autofillDate={false}
                                      onChange={(date) => handleDatePickerChange("nextVisitDate", date)}/>
                </div>
                <div className="inlineDiv">
                    {allowSiteNameOptions === "false" ? <TextInput name="siteName" labelText="Site Name: "
                                                                   onChange={handleSiteName}
                                                                   value={orderFormValues.sampleOrderItems.referringSiteName == null ? '' : orderFormValues.sampleOrderItems.referringSiteName}
                                                                   id="siteName" className="inputText"/> : <AutoComplete
                        name="siteName"
                        id="siteName"
                        className="inputText"
                        onSelect={handleAutoCompleteSiteName}
                        label="Search site Name"
                        class="inputText"
                        invalidText="Invalid site name"
                        style={{width: "!important 100%"}}
                        suggestions={siteNames.length > 0 ? siteNames : []}
                        required
                    />}

                    <Select
                        className="inputText"
                        id="requesterDepartmentId"
                        name="requesterDepartmentId"
                        labelText="ward/dept/unit:"
                        onChange={handleRequesterDept}
                        required>
                        <SelectItem
                            value=""
                            text=""
                        />
                    </Select>

                </div>
                {allowRequesterOptions === "false" ? '' : <div className="inlineDiv">
                    <AutoComplete
                        name="requesterId"
                        id="requesterId"
                        onSelect={handleProviderSelectOptions}
                        label="Search Requester"
                        class="inputText"
                        style={{width: "!important 100%"}}
                        invalidText="invalid requester name"
                        suggestions={providers.length > 0 ? providers : []}
                        required
                    />
                </div>}
                <div className="inlineDiv">

                    <TextInput name="requesterFirstName" labelText="Requester's FirstName: "
                               disabled={allowRequesterOptions !== "false"}
                               onChange={handleRequesterFirstName}
                               value={orderFormValues.sampleOrderItems.providerFirstName}
                               id="requesterFirstName" className="inputText"/>

                    <TextInput name="requesterLastName" labelText="Requesters' LastName: "
                               disabled={allowRequesterOptions !== "false"}
                               value={orderFormValues.sampleOrderItems.providerLastName}
                               onChange={handleRequesterLastName}
                               id="requesterLastName" className="inputText"/>
                </div>
                <div className="inlineDiv">
                    <TextInput name="providerWorkPhone"
                               disabled={allowRequesterOptions !== "false"}
                               onChange={handleRequesterWorkPhone}
                               value={orderFormValues.sampleOrderItems.providerWorkPhone}
                               onMouseLeave={handlePhoneNoValidation} labelText={`Requester Phone: ${phoneFormat}`}
                               id="providerWorkPhoneId" className="inputText"/>

                    <TextInput name="providerFax" labelText="Requester's Fax Number: "
                               disabled={allowRequesterOptions !== "false"}
                               onChange={handleRequesterFax}
                               value={orderFormValues.sampleOrderItems.providerFax}
                               id="providerFaxId" className="inputText"/>


                </div>
                <div className="inlineDiv">
                    <TextInput name="providerEmail" labelText="Requester's Email: "
                               disabled={allowRequesterOptions !== "false"}
                               onChange={handleRequesterEmail}
                               value={orderFormValues.sampleOrderItems.providerEmail}
                               id="providerEmailId" className="inputText"/>

                    <Select
                        className="inputText"
                        id="paymentOptionSelectionId"
                        name="paymentOptionSelections"
                        value={orderFormValues.sampleOrderItems.paymentOptionSelection}
                        labelText="Patient payment status:"
                        onChange={handlePaymentStatus}
                        required>

                        <SelectItem
                            value=""
                            text=""
                        />
                        {paymentOptions.map(option => {
                            return (<SelectItem key={option.id}
                                                value={option.id}
                                                text={option.value}
                            />)
                        })}

                    </Select>
                </div>
                <div className="inlineDiv">
                    <Select
                        className="inputText"
                        id="testLocationCodeId"
                        name="testLocationCode"
                        value={orderFormValues.sampleOrderItems.testLocationCode}
                        labelText="Sampling performed for analysis::"
                        onChange={(e) => handleSamplingPerformed(e)}
                        required>
                        <SelectItem
                            value=""
                            text=""
                        />
                        {samplingPerformed.map(option => {
                            return (<SelectItem key={option.id}
                                                value={option.id}
                                                text={option.value}
                            />)
                        })}
                    </Select>
                    <TextInput name="testLocationCodeOther" labelText="if Other specify:"
                               onChange={handleOtherLocationCode}
                               className="inputText"
                               value={orderFormValues.sampleOrderItems.otherLocationCode}
                               disabled={!otherSamplingVisible}
                               id="testLocationCodeOtherId" />
                </div>
                <div className="inlineDiv">
                    <Checkbox labelText="Remember site and requester" className="inputText"
                              id="rememberSiteAndRequester"
                              onChange={handleRememberCheckBox}/>
                </div>
            </div>
            <div className="orderLegendBody">
                <h3>RESULT REPORTING</h3>
                {samples.map((sample, index) => {
                    if (sample.tests.length > 0) {
                        return (<div key={index}>
                            <h4>Sample {index + 1}</h4>
                            <OrderResultReporting selectedTests={sample.tests}
                                                  reportingNotifications={reportingNotifications}/>
                        </div>)
                    }
                })}
            </div>
        </Stack>
    </>)
}

export default AddOrder;
