import React, {useContext, useEffect, useState} from 'react'
import {Button, ProgressIndicator, ProgressStep, Stack} from '@carbon/react';
import PatientInfo from "./PatientInfo";
import AddSample from "./AddSample";
import AddOrder from "./AddOrder";
import "./add-order.scss"
import {SampleOrderFormValues} from "../formModel/innitialValues/OrderEntryFormValues";
import {NotificationContext} from "../layout/Layout";
import {AlertDialog, NotificationKinds} from "../common/CustomNotification";
import {postToOpenElisServer} from "../utils/Utils";

export let sampleObject = {
    index: 0,
    sampleRejected: false,
    rejectionReason: "",
    sampleTypeId: "",
    sampleXML: null,
    panels: [],
    tests: [],
    requestReferralEnabled: false,
    referralItems: [],
}
const Index = () => {
    const {notificationVisible} = useContext(NotificationContext);
    const [page, setPage] = useState(0);
    const [selectedPatient, setSelectedPatient] = useState({
        id: "", healthRegion: []
    });
    const [orderFormValues, setOrderFormValues] = useState(SampleOrderFormValues);

    const [samples, setSamples] = useState([sampleObject]);
    const [useReferral, setUseReferral] = useState(false);

    const {setNotificationVisible, setNotificationBody} = useContext(NotificationContext);

    const getSelectedPatient = (patient) => {
        setSelectedPatient(patient);
    }

    function reportingNotifications(object) {
        setOrderFormValues({
            ...orderFormValues,
            customNotificationLogic: true,
            patientSMSNotificationTestIds: object.patientSMSNotificationTestIds,
            patientEmailNotificationTestIds: object.patientEmailNotificationTestIds,
            providerSMSNotificationTestIds: object.providerSMSNotificationTestIds,
            providerEmailNotificationTestIds: object.providerEmailNotificationTestIds,
        });
    }

    const showAlertMessage = (msg, kind) => {
        setNotificationVisible(true);
        setNotificationBody({kind: kind, title: "Notification Message", message: msg});
    }

    const handlePost = (status) => {
        if (status === 200) {
            showAlertMessage("Sample Order Entry has been saved successfully", NotificationKinds.success);
        } else {
            showAlertMessage("Oops, Server error please contact administrator", NotificationKinds.error);
        }
    }
    const handleSubmitOrderForm = (e) => {
        e.preventDefault();
        postToOpenElisServer("/rest/SamplePatientEntry", JSON.stringify(orderFormValues
        ), handlePost)
    }

    useEffect(() => {
        let sampleXmlString = null;
        let referralItems = [];
        if (samples.length > 0) {
            if (samples[0].tests.length > 0) {
                sampleXmlString = '<?xml version="1.0" encoding="utf-8"?>';
                sampleXmlString += "<samples>"
                let tests = null;
                samples.map(sampleItem => {
                    if (sampleItem.tests.length > 0) {
                        tests = Object.keys(sampleItem.tests).map(function (i) {
                            return sampleItem.tests[i].id
                        }).join(",");
                        sampleXmlString += `<sample sampleID='${sampleItem.sampleTypeId}' date='${sampleItem.sampleXML.collectionDate}' time='${sampleItem.sampleXML.collectionTime}' collector='${sampleItem.sampleXML.collector}' tests='${tests}' testSectionMap='' testSampleTypeMap='' panels='' rejected='${sampleItem.sampleXML.rejected}' rejectReasonId='${sampleItem.sampleXML.rejectionReason}' initialConditionIds=''/>`
                    }
                    if (sampleItem.referralItems.length > 0) {
                        const referredInstitutes = Object.keys(sampleItem.referralItems).map(function (i) {
                            return sampleItem.referralItems[i].institute
                        }).join(",");

                        const sentDates = Object.keys(sampleItem.referralItems).map(function (i) {
                            return sampleItem.referralItems[i].sentDate
                        }).join(",");

                        const referralReasonIds = Object.keys(sampleItem.referralItems).map(function (i) {
                            return sampleItem.referralItems[i].reasonForReferral
                        }).join(",");

                        const referrers = Object.keys(sampleItem.referralItems).map(function (i) {
                            return sampleItem.referralItems[i].referrer
                        }).join(",");
                        referralItems.push({
                            referrer: referrers,
                            referredInstituteId: referredInstitutes,
                            referredTestId: tests,
                            referredSendDate: sentDates,
                            referralReasonId: referralReasonIds
                        });
                        setUseReferral(true);
                    }

                });
                sampleXmlString += "</samples>"
            }
        }
        setOrderFormValues({
            ...orderFormValues,
            useReferral: useReferral,
            sampleXML: sampleXmlString,
            referralItems: referralItems
        });
    }, [samples]);


    const navigateForward = () => {
        if (page < 3 && page >= 0) {
            setPage(page + 1);
        }
    }

    const navigateBackWards = () => {
        if (page > -1) {
            setPage(page + -1);
        }
    }
    const handleTabClickHandler = (e) => {
        setPage(e);
    }

    return (
        <>
            <Stack gap={10}>
                <div className='pageContent'>
                    {notificationVisible === true ? <AlertDialog/> : ""}
                    <div className="orderLegendBody">
                        <h2>Test Request</h2>
                        <ProgressIndicator currentIndex={page} className="ProgressIndicator" spaceEqually={true}
                                           onChange={(e) => handleTabClickHandler(e)}>
                            <ProgressStep
                                complete
                                label="Patient Info"
                            />
                            <ProgressStep
                                label="Add Sample"
                            />
                            <ProgressStep
                                label="Add Order"
                            />
                        </ProgressIndicator>

                        {page === 0 &&
                            <PatientInfo orderFormValues={orderFormValues} setOrderFormValues={setOrderFormValues}/>}
                        {page === 1 &&
                            <AddSample setSamples={setSamples} samples={samples}/>}
                        {page === 2 &&
                            <AddOrder orderFormValues={orderFormValues} setOrderFormValues={setOrderFormValues}
                                      samples={samples}/>}
                        <div className="navigationButtonsLayout">
                            {page !== 0 && <Button kind="tertiary" onClick={() => navigateBackWards()}>Back</Button>}

                            {page !== 2 ? <Button kind="primary" className="forwardButton"
                                                  onClick={() => navigateForward()}>Next</Button> :

                                <Button kind="primary" className="forwardButton"
                                        onClick={() => console.log("submit data")}>Submit</Button>}

                        </div>
                    </div>
                </div>
            </Stack>

        </>
    )
}

export default Index;
