import React from "react"
import { FormattedMessage, injectIntl } from 'react-intl'
import { withRouter } from "react-router-dom"
import './Style.css'
import { faLanguage, faSignOutAlt } from "@fortawesome/free-solid-svg-icons"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { Button } from '@carbon/react';
import { Add } from '@carbon/react/icons';

class Header extends React.Component {

  constructor(props) {
    super(props);
  }

  logo = () => {
    return (<>
      <picture>
        <img className="logo" src={`images/openelis_logo.png`} alt="logo" />
      </picture>
    </>
    )
  }

  render() {
    return (
      <>
        <div id='header'>
          <div id="oe-logo">
            {this.logo()}
          </div>
          <div id='title'>
            <div id='titleblock'>
              <div id="oe-title">{this.props.config.tittle}</div>
            </div>
          </div>

          {this.props.isLoggedIn() &&
            <>
              <div className="logoutDiv">
                <button type="button" className="logout-button" onClick={this.props.logout}>
                  <FontAwesomeIcon id="sign-out" icon={faSignOutAlt} size="1x" />
                </button>
                {/* {this.props.user.loginName}  */}
                {this.props.user.firstName}  {this.props.user.lastName}
                
              </div>
            </>
          }
        </div>
        <div className="lang">
          <button className="lang-button"
            lang="en"
            onClick={(e) => {
              this.props.onChangeLanguage(e)
            }}
          > English </button>
          <>&nbsp; &nbsp;</>
          <button className="lang-button"
            lang="fr"
            onClick={(e) => {
              this.props.onChangeLanguage(e)
            }}
          > Français</button>
        </div>
      </>
    )
  }
}
export default withRouter(injectIntl(Header))