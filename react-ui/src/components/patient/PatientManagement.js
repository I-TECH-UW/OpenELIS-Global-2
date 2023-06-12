import React from 'react'
import { FormattedMessage, injectIntl } from 'react-intl'
import "../Style.css";

import {
    Heading,
    Grid,
    Column,
    Section 

} from '@carbon/react';
import SearchPatientForm from '../common/SearchPatientForm';
import CreatePatientForm from '../common/CreatePatientForm';


class PatientManagement extends React.Component {

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
       
                <SearchPatientForm getSelectedPatient={this.getSelectedPatient}></SearchPatientForm>
                
                <br></br>
                {/* {JSON.stringify(this.state.selectedPatient)} */}
                <CreatePatientForm showActionsButton={true} selectedPatient={this.state.selectedPatient}></CreatePatientForm>
            </>

        );

    }
}

export default injectIntl(PatientManagement)