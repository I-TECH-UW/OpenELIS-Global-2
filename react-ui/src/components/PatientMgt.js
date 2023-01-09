import React from 'react'
import { FormattedMessage, injectIntl } from 'react-intl'
import "./Style.css";

import {

    Heading,
    Form,
    FormLabel,
    Section,
    Grid,
    Column

} from '@carbon/react';
import PatientSearch from './common/PatientSearch';

class PatientMgt extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            selectedPatient: {}
        }
    }

    getSelectedPatient = (patient) => {
        this.setState({ selectedPatient: patient })
    }

    render() {
        return (
            <>
                <Grid fullWidth={true}>
                    <Column lg={16}>
                        <Section>
                            <Section >
                                <Heading >
                                    <FormattedMessage id="patient.label.modify" />
                                </Heading>
                            </Section>
                        </Section>
                    </Column>
                </Grid>
                <br></br>
       
                <PatientSearch getSelectedPatient={this.getSelectedPatient}></PatientSearch>
                <Grid fullWidth={true}>
                    <Column lg={12}>
                        <div> {this.state.selectedPatient.firstName} &nbsp; 
                         {this.state.selectedPatient.lastName} &nbsp; 
                        {this.state.selectedPatient.gender} &nbsp; 
                         {this.state.selectedPatient.dob} &nbsp; 
                         {this.state.selectedPatient.subjectNumber} &nbsp; 
                        {this.state.selectedPatient.nationalId} </div>
                    </Column >
                </Grid>

            </>

        );

    }
}

export default injectIntl(PatientMgt)