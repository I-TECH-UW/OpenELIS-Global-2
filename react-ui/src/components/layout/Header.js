import React from "react"
import { FormattedMessage, injectIntl } from 'react-intl'
import { withRouter } from "react-router-dom"
import '../Style.css'
import { faLanguage, faSignOutAlt } from "@fortawesome/free-solid-svg-icons"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { Button, Select, SelectItem } from '@carbon/react';
import { Search, Notification, Switcher, User, Language, UserAvatarFilledAlt, Fade, Menu, Logout } from "@carbon/icons-react";
import {
  Header,
  HeaderName,
  HeaderGlobalAction,
  HeaderGlobalBar,
  HeaderNavigation,
  HeaderMenu,
  HeaderMenuItem,
  Theme,
  Content,
  HeaderPanel
} from "@carbon/react";

class OEHeader extends React.Component {

  constructor(props) {
    super(props);
    this.toggleSwitch = this.toggleSwitch.bind(this);
    this.state = {
      switchCollapsed: true
    };
  }

  toggleSwitch() {
    this.setState(state => ({
      switchCollapsed: !state.switchCollapsed
    }));
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
        <div className="container">
          <Theme >
            <Header aria-label="IBM Platform Name">
              <HeaderName href="/" prefix="">
                {this.logo()} {this.props.config.tittle}
              </HeaderName>
              <HeaderNavigation aria-label="nav">
                <HeaderMenu aria-label="Order" menuLinkName="Order">
                  <HeaderMenuItem href="#">Sub-link 1</HeaderMenuItem>
                  <HeaderMenuItem href="#">Sub-link 2</HeaderMenuItem>
                  <HeaderMenuItem href="#">Sub-link 3</HeaderMenuItem>
                </HeaderMenu>
                <HeaderMenu aria-label="Patient" menuLinkName="Patient">
                  <HeaderMenuItem href="#">Sub-link 1</HeaderMenuItem>
                  <HeaderMenuItem href="#">Sub-link 2</HeaderMenuItem>
                  <HeaderMenuItem href="#">Sub-link 3</HeaderMenuItem>
                </HeaderMenu>
                <HeaderMenu aria-label="Non-Conforming Events" menuLinkName="Non-Conform">
                  <HeaderMenuItem href="#">Sub-link 1</HeaderMenuItem>
                  <HeaderMenuItem href="#">Sub-link 2</HeaderMenuItem>
                  <HeaderMenuItem href="#">Sub-link 3</HeaderMenuItem>
                </HeaderMenu>
                <HeaderMenu aria-label="Workplan" menuLinkName="Workplan">
                  <HeaderMenuItem href="#">Sub-link 1</HeaderMenuItem>
                  <HeaderMenuItem href="#">Sub-link 2</HeaderMenuItem>
                  <HeaderMenuItem href="#">Sub-link 3</HeaderMenuItem>
                </HeaderMenu>
                <HeaderMenu aria-label="Results" menuLinkName="Results">
                  <HeaderMenuItem href="#">Sub-link 1</HeaderMenuItem>
                  <HeaderMenuItem href="#">Sub-link 2</HeaderMenuItem>
                  <HeaderMenuItem href="#">Sub-link 3</HeaderMenuItem>
                </HeaderMenu>
                <HeaderMenu aria-label="Validation" menuLinkName="Validation">
                  <HeaderMenuItem href="#">Sub-link 1</HeaderMenuItem>
                  <HeaderMenuItem href="#">Sub-link 2</HeaderMenuItem>
                  <HeaderMenuItem href="#">Sub-link 3</HeaderMenuItem>
                </HeaderMenu>
                <HeaderMenu aria-label="Reports" menuLinkName="Reports">
                  <HeaderMenuItem href="#">Sub-link 1</HeaderMenuItem>
                  <HeaderMenuItem href="#">Sub-link 2</HeaderMenuItem>
                  <HeaderMenuItem href="#">Sub-link 3</HeaderMenuItem>
                </HeaderMenu>
                <HeaderMenuItem href="/admin">Admin</HeaderMenuItem>
              </HeaderNavigation>
              <HeaderGlobalBar>
                <HeaderGlobalAction aria-label="Search" onClick={() => { }}>
                  <Search size={20} />
                </HeaderGlobalAction>
                <HeaderGlobalAction aria-label="Notifications" onClick={() => { }}>
                  <Notification size={20} />
                </HeaderGlobalAction>
                <HeaderGlobalAction aria-label="User" onClick={this.toggleSwitch}>
                  <UserAvatarFilledAlt size={20} />
                </HeaderGlobalAction>
              </HeaderGlobalBar>
              <HeaderPanel
                aria-label="Header Panel"
                expanded={!this.state.switchCollapsed}
                className="headerPanel"
              >
                {this.props.isLoggedIn() &&
                  <>
                    <div className="Div">
                      <div className="userDetails">
                        <UserAvatarFilledAlt size={18} /> {this.props.user.firstName} {this.props.user.lastName}
                      </div>
                      <div className="userDetails" onClick={this.props.logout}>
                        <FontAwesomeIcon id="sign-out" icon={faSignOutAlt} size="1x" /> Logout
                      </div>
                      <div className="userDetails">
                        <Select
                          id="selector"
                          name="selectLocale"
                          className="selectLocale"
                          invalidText="A valid locale value is required"
                          labelText="Select locale"
                          style={{ color: 'green' }}
                          onChange={(event) =>
                            this.props.onChangeLanguage(event.target.value)
                          }
                          onClick={(event) => event.stopPropagation()}
                        >
                          <SelectItem text='English' value='en' />
                          <SelectItem text='French' value='fr' />
                        </Select>
                      </div>
                    </div>
                  </>
                }
              </HeaderPanel>
            </Header>
          </Theme>
          < Content>
          </ Content>
        </div>
      </>
    )
  }
}
export default withRouter(injectIntl(OEHeader))