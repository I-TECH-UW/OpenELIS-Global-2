import React, {useState} from 'react'
import {Column, Form, Grid,} from '@carbon/react';

import AddSample from './AddSample'
import AddOrderTopForm from "./AddOrderTopForm";
import SearchPatientForm from "../common/SearchPatientForm";
import CreatePatientForm from "../common/CreatePatientForm";
import OrderResultReporting from "./OrderResultReporting";

const CreateOrder = () => {
    const [selectedPatient, setSelectedPatient] = useState({
        id: "",
        healthRegion: []
    });

    const [samples, setSamples] = useState([]);

    const getSelectedPatient = (patient) => {
        setSelectedPatient(patient);
    }
    return (
        <>
            <Grid fullWidth={true} className="gridBoundary">
                <Column lg={16}>
                    <Form>
                        <AddOrderTopForm/>
                        <br/>
                        <AddSample setSamples={setSamples}/>
                    </Form>
                    <SearchPatientForm getSelectedPatient={getSelectedPatient}></SearchPatientForm>
                    <CreatePatientForm showActionsButton={false} selectedPatient={selectedPatient}
                                       getSelectedPatient={getSelectedPatient}/>
                    {samples.length > 0 && <h3>Result Reporting</h3>}
                    {
                        samples.map((sample, index) => {
                            if (sample.tests.length > 0) {
                                return (
                                    <div key={index}>
                                        <h4>Sample {index + 1}</h4>
                                        <OrderResultReporting selectedTests={sample.tests}/>
                                    </div>)
                            }
                        })
                    }
                </Column>
            </Grid>
        </>
    )
}

export default CreateOrder;
