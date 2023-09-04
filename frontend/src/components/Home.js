import React from 'react'
import { injectIntl,FormattedMessage } from 'react-intl'
import { Breadcrumb, BreadcrumbItem, Grid } from '@carbon/react';
import HomeDashBoard from './home/Dashboard.tsx'



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
                    <BreadcrumbItem href="/"><FormattedMessage id="home.label"/></BreadcrumbItem>
                </Breadcrumb>
            </Grid>
             <div> 
                <HomeDashBoard/>
            </div> 
            </>
        );

    }
}

export default injectIntl(Home)