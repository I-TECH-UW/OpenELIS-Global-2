import React, {useContext} from 'react'
import {FormattedMessage} from 'react-intl';

import {Column, Grid, Heading, Section} from '@carbon/react';
import CreateOrder from './CreateOrder';
import {AlertDialog} from "../common/CustomNotification";
import {NotificationContext} from "../layout/Layout";

const Index = () => {
    const { notificationVisible } = useContext(NotificationContext);
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
        </div>
    )
}

export default Index;
