import React, {useState,useEffect,useContext} from 'react'
import {Button, Column, Form, Grid} from '@carbon/react';

import AddSample from './AddSample'
import AddOrderTopForm from "./AddOrderTopForm";
import SearchPatientForm from "../common/SearchPatientForm";
import CreatePatientForm from "../common/CreatePatientForm";
import OrderResultReporting from "./OrderResultReporting";
import {FormattedMessage} from "react-intl";
import {NotificationKinds} from "../common/CustomNotification";
import {NotificationContext} from "../layout/Layout";

import {SampleOrderFormValues} from "../formModel/innitialValues/OrderEntryFormValues";
import {postToOpenElisServer} from "../utils/Utils";
import OrderEntryAdditionalQuestions from "./OrderEntryAdditionalQuestions";

const CreateOrder = () => {
    const [selectedPatient, setSelectedPatient] = useState({
        id: "", healthRegion: []
    });
    const [orderFormValues, setOrderFormValues] = useState(SampleOrderFormValues);

    const [samples, setSamples] = useState([]);
    const [useReferral,setUseReferral] = useState(false);

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
    const showAlertMessage = (msg,kind) =>{
        setNotificationVisible(true);
        setNotificationBody({kind: kind, title: "Notification Message", message: msg});
    }

  const  handlePost = (status) => {
        if (status === 200){
            showAlertMessage("Sample Order Entry has been saved successfully",NotificationKinds.success);
        }else{
            showAlertMessage("Oops, Server error please contact administrator",NotificationKinds.error);
        }
    }
    const handleSubmitOrderForm = (e) => {
        e.preventDefault();
        postToOpenElisServer("/rest/SamplePatientEntry" ,JSON.stringify(orderFormValues
        ) , handlePost)
    }

    useEffect(()=>{
        let sampleXmlString = null;
        let referralItems = [];
        if(samples.length > 0 ) {
            if (samples[0].tests.length > 0) {
                sampleXmlString = '<?xml version="1.0" encoding="utf-8"?>';
                sampleXmlString += "<samples>"
                let tests = null;
                samples.map(sampleItem => {
                    if (sampleItem.tests.length > 0) {
                        tests = Object.keys(sampleItem.tests).map(function(i){
                            return sampleItem.tests[i].id
                        }).join(",");
                        sampleXmlString += `<sample sampleID='${sampleItem.sampleTypeId}' date='${sampleItem.sampleXML.collectionDate}' time='${sampleItem.sampleXML.collectionTime}' collector='${sampleItem.sampleXML.collector}' tests='${tests}' testSectionMap='' testSampleTypeMap='' panels='' rejected='${sampleItem.sampleXML.rejected}' rejectReasonId='${sampleItem.sampleXML.rejectionReason}' initialConditionIds=''/>`
                    }
                    if(sampleItem.referralItems.length > 0){
                        const referredInstitutes =  Object.keys(sampleItem.referralItems).map(function(i){
                            return sampleItem.referralItems[i].institute
                        }).join(",");

                        const sentDates =  Object.keys(sampleItem.referralItems).map(function(i){
                            return sampleItem.referralItems[i].sentDate
                        }).join(",");

                        const referralReasonIds =  Object.keys(sampleItem.referralItems).map(function(i){
                            return sampleItem.referralItems[i].reasonForReferral
                        }).join(",");

                        const referrers =  Object.keys(sampleItem.referralItems).map(function(i){
                            return sampleItem.referralItems[i].referrer
                        }).join(",");
                        referralItems.push({
                            referrer:referrers,
                            referredInstituteId: referredInstitutes,
                            referredTestId: tests,
                            referredSendDate:sentDates,
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
    },[samples]);



    return (<>
        <Grid fullWidth={true} className="gridBoundary">
            <Column lg={16}>
                <Form>
                <OrderEntryAdditionalQuestions orderFormValues={orderFormValues} setOrderFormValues={setOrderFormValues}/>
                    <AddOrderTopForm orderFormValues={orderFormValues} setOrderFormValues={setOrderFormValues}/>
                    <AddSample setSamples={setSamples} orderFormValues={orderFormValues} setOrderFormValues={setOrderFormValues}/>
                </Form>
                <SearchPatientForm getSelectedPatient={getSelectedPatient}></SearchPatientForm>
                <CreatePatientForm showActionsButton={false} selectedPatient={selectedPatient}
                                   orderFormValues={orderFormValues} setOrderFormValues={setOrderFormValues}
                                   getSelectedPatient={getSelectedPatient}/>
                {samples.length > 0 && <h3>Result Reporting</h3>}

                {samples.map((sample, index) => {
                    if (sample.tests.length > 0) {
                        return (<div key={index}>
                            <h4>Sample {index + 1}</h4>
                            <OrderResultReporting selectedTests={sample.tests}
                                                  reportingNotifications={reportingNotifications}/>
                        </div>)
                    }
                })}
                <div>
                    <Button type="button" id="submit" onClick={handleSubmitOrderForm}>
                        <FormattedMessage id="label.button.save"/>
                    </Button>
                    <Button id="clear" kind='danger'>
                        <FormattedMessage id="label.button.clear"/>
                    </Button>
                    {JSON.stringify(orderFormValues)}
                </div>
            </Column>

        </Grid>
    </>)
}

export default CreateOrder;
