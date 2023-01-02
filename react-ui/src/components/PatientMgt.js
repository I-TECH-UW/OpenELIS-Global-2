import React from 'react'
import { FormattedMessage, injectIntl } from 'react-intl'

import {

    Heading,
    Form,
    FormLabel

} from '@carbon/react';
import PatientSearch from './common/PatientSearch';

class PatientMgt extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
        }
    }

    render() {
        return (
            <>
                <Form>
                    <FormLabel>
                        <Heading size="sm">
                            <FormattedMessage id="patient.label.modify" />
                        </Heading>
                    </FormLabel>
                </Form>
                <br></br>
                <PatientSearch></PatientSearch>
            </>

        );

    }
}

export default injectIntl(PatientMgt)