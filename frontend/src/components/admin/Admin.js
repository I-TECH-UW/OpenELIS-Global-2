import React from 'react'
import config from "../../config.json";
import { FormattedMessage, injectIntl } from 'react-intl'
import '../Style.css'
import ReflexTestManagement from './reflexTests/ReflexTestManagement'
import ProgramManagement from './program/ProgramManagement'
import PathRoute from "../utils/PathRoute"
import CalculatedValue from "./calculatedValue/CalculatedValueForm"
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
                        <SideNavMenu title={<FormattedMessage id="sidenav.label.admin.testmgt"/>} >
                            <SideNavMenuItem href="#reflex" > <FormattedMessage id="sidenav.label.admin.testmgt.reflex"/> </SideNavMenuItem>
                            <SideNavMenuItem href="#calculatedValue" > <FormattedMessage id="sidenav.label.admin.testmgt.calculated"/> </SideNavMenuItem>
                            <SideNavMenuItem href="#1"> Link</SideNavMenuItem>
                            <SideNavMenuItem href="#2">Link</SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu title={<FormattedMessage id="sidenav.label.admin.usermgt"/>}>
                            <SideNavMenuItem href="#3">Link</SideNavMenuItem>
                            <SideNavMenuItem href="#4">Link</SideNavMenuItem>
                            <SideNavMenuItem href="#5">Link</SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu title={<FormattedMessage id="sidenav.label.admin.organizationmgt"/>} >
                            <SideNavMenuItem href="#6">Link</SideNavMenuItem>
                            <SideNavMenuItem href="#7">Link</SideNavMenuItem>
                            <SideNavMenuItem href="#8">Link</SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenuItem href="#program" > <FormattedMessage id="sidenav.label.admin.program"/> </SideNavMenuItem>
                        <SideNavMenuItem target="_blank" href={config.serverBaseUrl + "/MasterListsPage"}><FormattedMessage id="admin.legacy"/></SideNavMenuItem>
                    </SideNavItems>
                </SideNav>

                <PathRoute path="#reflex">
                    <ReflexTestManagement />
                </PathRoute>
                <PathRoute path="#program">
                    <ProgramManagement />
                </PathRoute>
                <PathRoute path="#calculatedValue">
                    <CalculatedValue></CalculatedValue>
                </PathRoute>
            </>

        );

    }
}

export default injectIntl(Admin)