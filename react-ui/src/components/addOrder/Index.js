import React, {useContext, useState} from 'react'
import { FormattedMessage } from 'react-intl';

import { Grid, Column, Section, Heading } from '@carbon/react';
import CreatePatientForm from '../common/CreatePatientForm';

import SearchPatientForm from '../common/SearchPatientForm';
import CreateOrder from './CreateOrder';
import {AlertDialog} from "../common/CustomNotification";
import {NotificationContext} from "../layout/Layout";

const Index = () => {

    const { notificationVisible } = useContext(NotificationContext);
    const [selectedPatient, setSelectedPatient] = useState({
        id: "",
        healthRegion: []
    });

    const getSelectedPatient = (patient) => {
        setSelectedPatient(patient);
    }

    return (
        <div className='pageContent'>
            {notificationVisible === true ? <AlertDialog/> : ""}
            <Grid fullWidth={true}>
                <Column lg={16}>
                    <Section>
                        <Section >
                            <Heading >
                                <FormattedMessage id="order.label.add" />
                            </Heading>
                        </Section>
                    </Section>
                </Column>
            </Grid>
            <CreateOrder />
            <SearchPatientForm getSelectedPatient={getSelectedPatient}></SearchPatientForm>

            <CreatePatientForm selectedPatient={selectedPatient} getSelectedPatient={getSelectedPatient} />
        </div>
    )
}

export default Index;
