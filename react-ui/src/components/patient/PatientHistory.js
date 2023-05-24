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
import RoutedResultsViewer from './resultsViewer/results-viewer.tsx'
import config from '../../config.json';


class PatientHistory extends React.Component {
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
                                    <FormattedMessage id="label.page.patientHistory" />
                                </Heading>
                            </Section>
                        </Section>
                    </Column>
                </Grid>
                <br></br>
       
                <SearchPatientForm getSelectedPatient={this.getSelectedPatient}></SearchPatientForm>
                
                <br></br>
                <Grid fullWidth={true}>
                    <Column lg={16}>
                   {/* {JSON.stringify(this.state.selectedPatient.fhirUuid)}          */}
                 <RoutedResultsViewer  patientUuid={this.state.selectedPatient.patientPK} basePath={config.serverBaseUrl}/>
                 </Column>
                </Grid>

            </>

        );

    }

}
export default injectIntl(PatientHistory)