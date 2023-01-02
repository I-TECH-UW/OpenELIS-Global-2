import React from 'react'
import { FormattedMessage, injectIntl } from 'react-intl'
import "../Style.css";

import {
    Heading,
    Form,
    FormLabel,
    TextInput,
    Button,
    Grid,
    Column,
    DatePicker,
    DatePickerInput,
    RadioButton,
    RadioButtonGroup ,
    ContentSwitcher ,
    Switch

} from '@carbon/react';

class PatientSearch extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
        }
    }

    render() {
        return (
            <>
                <Grid fullWidth={true}>
                    <Column lg={3} md={4} sm={4}>
                        <Form>
                            <FormLabel>
                                <Heading size="sm">
                                    <FormattedMessage id="patient.label.search" />
                                </Heading>
                            </FormLabel>
                            <TextInput labelText="Lab Number" id="test" className="inputText" />
                            <TextInput labelText="Patient Id" id="test" className="inputText" />
                            <TextInput labelText="Last Name" id="test" className="inputText" />
                            <TextInput labelText="First Name" id="test" className="inputText" />
                            <DatePicker dateFormat="d/m/Y" datePickerType="single" light={false}>
                                <DatePickerInput
                                    id="date-picker-default-id"
                                    placeholder="dd/mm/yyyy"
                                    labelText="Date of Birth"
                                    type="text" />
                            </DatePicker>
                            <ContentSwitcher  onChange={console.log}>
                                <Switch name={'first'} text='Male' />
                                <Switch name={'second'} text='Female' />
                            </ContentSwitcher>
                            <Button type="submit" >
                                <FormattedMessage id="label.button.submit" />
                            </Button>
                        </Form>
                    </Column>
                </Grid>
            </>

        );

    }
}

export default injectIntl(PatientSearch)