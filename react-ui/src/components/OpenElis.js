import React from 'react'
import { FormattedMessage, injectIntl } from 'react-intl'

class OpenElis extends React.Component {

    constructor(props) {
		super(props)
		this.state = {
		}
	}
    render() {
        return(
            <b> <FormattedMessage id="language.select.label" /></b>
        );

    }
}

export default injectIntl(OpenElis)