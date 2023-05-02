import React, {useState} from 'react'
import {Button, Column, Form, Grid} from '@carbon/react';

import AddSample from './AddSample'
import AddOrderTopForm from "./AddOrderTopForm";
import SearchPatientForm from "../common/SearchPatientForm";
import CreatePatientForm from "../common/CreatePatientForm";
import OrderResultReporting from "./OrderResultReporting";
import {FormattedMessage} from "react-intl";

import {OrderFormValues} from "../formModel/innitialValues/OrderEntryFormValues";

const CreateOrder = () => {
    const [selectedPatient, setSelectedPatient] = useState({
        id: "", healthRegion: []
    });
    const [orderFormValues, setOrderFormValues] = useState(OrderFormValues);

    const [samples, setSamples] = useState([]);

    const getSelectedPatient = (patient) => {
        setSelectedPatient(patient);
    }

    function reportingNotifications(object) {
        setOrderFormValues({
            ...orderFormValues,
            patientSMSNotificationTestIds: object.patientSMSNotificationTestIds,
            patientEmailNotificationTestIds: object.patientEmailNotificationTestIds,
            providerSMSNotificationTestIds: object.providerSMSNotificationTestIds,
            providerEmailNotificationTestIds: object.providerEmailNotificationTestIds,
        });
    }

    const handleSubmitOrderForm = (e) => {
        e.preventDefault();
        // console.log(samples);
        console.log(orderFormValues);
    }
    return (<>
        <Grid fullWidth={true} className="gridBoundary">
            <Column lg={16}>
                <Form>
                    <AddOrderTopForm orderFormValues={orderFormValues} setOrderFormValues={setOrderFormValues}/>
                    <br/>
                    <AddSample setSamples={setSamples}/>
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
            </Column>
            <Column lg={6}>
                <div>
                    <Button type="button" id="submit" onClick={handleSubmitOrderForm}>
                        <FormattedMessage id="label.button.save"/>
                    </Button>
                    <Button id="clear" kind='danger'>
                        <FormattedMessage id="label.button.clear"/>
                    </Button>
                </div>
            </Column>

        </Grid>
    </>)
}

export default CreateOrder;
