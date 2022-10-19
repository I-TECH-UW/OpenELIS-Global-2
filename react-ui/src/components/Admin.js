import React from 'react'
import { FormattedMessage, injectIntl } from 'react-intl'

class Admin extends React.Component {

    constructor(props) {
		super(props)
		this.state = {
		}
	}
    render() {
        return(
            <b> <FormattedMessage id="language.select.admin" /></b>
        );

    }
}

export default injectIntl(Admin)