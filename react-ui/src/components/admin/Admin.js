import React from 'react'
import config from "../../config.json";
import { FormattedMessage, injectIntl } from 'react-intl'
import '../Style.css'
import ReflexTestManagement from './reflexTests/ReflexTestManagement'
import PathRoute from "../utils/PathRoute"
import {
    SideNav,
    SideNavItems,
    SideNavMenu,
    SideNavMenuItem
} from '@carbon/react';

class Admin extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
        }
    }

    render() {
        return (
            <>
                <SideNav
                    aria-label="Side navigation"
                    expanded={true}
                    
                >
                    <SideNavItems className="adminSideNav">
                        <SideNavMenu title="Test Management">
                            <SideNavMenuItem href="#reflex" >Manage Testing Algorithms and Reflex tests</SideNavMenuItem>
                            <SideNavMenuItem href="#1"> Link</SideNavMenuItem>
                            <SideNavMenuItem href="#2">Link</SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu title="User Management">
                            <SideNavMenuItem href="#3">Link</SideNavMenuItem>
                            <SideNavMenuItem href="#4">Link</SideNavMenuItem>
                            <SideNavMenuItem href="#5">Link</SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu title="Organization Management">
                            <SideNavMenuItem href="#6">Link</SideNavMenuItem>
                            <SideNavMenuItem href="#7">Link</SideNavMenuItem>
                            <SideNavMenuItem href="#8">Link</SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenuItem target="_blank" href={config.serverBaseUrl + "/MasterListsPage"}><FormattedMessage id="admin.legacy"/></SideNavMenuItem>
                    </SideNavItems>
                </SideNav>

                <PathRoute path="#reflex">
                    <ReflexTestManagement />
                </PathRoute>
            </>

        );

    }
}

export default injectIntl(Admin)