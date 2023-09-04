import React from 'react'
import { FormattedMessage, injectIntl } from 'react-intl'
import "../Style.css";

import {
    Heading,
    Grid,
    Column,
    Section, Button

} from '@carbon/react';
import SearchPatientForm from './SearchPatientForm';
import CreatePatientForm from './CreatePatientForm';


class PatientManagement extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            selectedPatient: {},
            searchPatientTab: {kind: "primary", active: true},
            newPatientTab: {kind: "tertiary", active: false},
        }
    }
     handleSearchPatientTab = () => {
        this.setState({
            searchPatientTab: { kind: "primary", active: true },
            newPatientTab: {kind: "tertiary", active: false}
        });
    }

     handleNewPatientTab = () => {
         this.setState({
             newPatientTab: {kind: "primary", active: true},
             searchPatientTab: { kind: "tertiary", active: false },
         });
    }

    getSelectedPatient = (patient) => {
        this.setState({ selectedPatient: patient });
        this.setState({
            newPatientTab: {kind: "primary", active: true},
            searchPatientTab: { kind: "tertiary", active: false },
        });
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
                <div className="orderLegendBody">
                    <div className="tabsLayout">
                        <Button kind={this.state.searchPatientTab.kind} onClick={this.handleSearchPatientTab}>Search for
                            Patient</Button>
                        <Button kind={this.state.newPatientTab.kind} onClick={this.handleNewPatientTab}>New Patient</Button>
                    </div>
                    {this.state.searchPatientTab.active &&  <SearchPatientForm getSelectedPatient={this.getSelectedPatient}></SearchPatientForm> }
                
                <br></br>
                {/* {JSON.stringify(this.state.selectedPatient)} */}
                    {this.state.newPatientTab.active && <CreatePatientForm showActionsButton={true} selectedPatient={this.state.selectedPatient}></CreatePatientForm> }
                </div>
                </>

        );

    }
}

export default injectIntl(PatientManagement)
