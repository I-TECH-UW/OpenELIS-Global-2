import React from 'react'
import { FormattedMessage, injectIntl } from 'react-intl'
import { Breadcrumb, BreadcrumbItem } from '@carbon/react';

class Home extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
        }
    }
    render() {
        return (
            <>
                <Breadcrumb>
                    <BreadcrumbItem href="/">Home</BreadcrumbItem>
                </Breadcrumb>
                <b><FormattedMessage id="language.select.label" /></b>
            </>
        );

    }
}

export default injectIntl(Home)