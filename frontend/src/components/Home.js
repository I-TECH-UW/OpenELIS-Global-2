import React from 'react'
import { injectIntl } from 'react-intl'
import { Breadcrumb, BreadcrumbItem, Grid } from '@carbon/react';



class Home extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
        }
    }

    render() {
        return (
            <><Grid>
                <Breadcrumb>
                    <BreadcrumbItem href="/">Home</BreadcrumbItem>
                </Breadcrumb>
            </Grid>
            </>
        );

    }
}

export default injectIntl(Home)