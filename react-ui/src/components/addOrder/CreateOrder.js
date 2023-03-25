import React from 'react'
import {
    Grid,
    Column,
    Form,
} from '@carbon/react';

import AddSample from './AddSample'
import AddOrderTopForm from "./AddOrderTopForm";

const CreateOrder = () => {
    return (
        <>
            <Grid fullWidth={true} className="gridBoundary">
                <Column lg={16}>
                    <Form>
                        <AddOrderTopForm/>
                        <br/>
                        <AddSample/>
                    </Form>
                </Column>
            </Grid>
        </>
    )
}

export default CreateOrder;
