import React from 'react'
import { FormattedMessage, injectIntl } from 'react-intl'
import './Style.css'
import ResultSearch from './resultPage/ResultSearch';
import ResultRangeSearch from './resultPage/ResultRangeSearch';
import CodeTest from './resultPage/CodeTest';

import { NavLink } from "react-router-dom";
import {
    Microscope,
    IbmWatsonDiscovery,
    IbmWatsonNaturalLanguageUnderstanding,
    WatsonHealthCrossReference,
    Query
}
    from '@carbon/icons-react';

import { useState } from "react";

import { BrowserRouter as Router, Route, Switch, useMatch } from "react-router-dom";
import PathRoute from "./utils/PathRoute"
import {
    Content,
    StoryContent,
    SideNav,
    SideNavItems,
    SideNavMenu,
    SideNavMenuItem
} from '@carbon/react';

class Result extends React.Component {

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
                    isPersistent={true}
                    defaultExpanded={true}
                    isRail
                >
                    <SideNavItems className="resultSideNav">
                        <SideNavMenu renderIcon={IbmWatsonDiscovery} title="Result Search">
                            <SideNavMenuItem href="#result" >by Accession Number</SideNavMenuItem>
                            <SideNavMenuItem href="#rangeResults">Range Results</SideNavMenuItem>
                            <SideNavMenuItem href="#codeTest">Test</SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu renderIcon={Microscope} title="Patient Search">
                            <SideNavMenuItem href="#3">Link</SideNavMenuItem>
                            <SideNavMenuItem href="#4">Link</SideNavMenuItem>
                            <SideNavMenuItem href="#5">Link</SideNavMenuItem>
                        </SideNavMenu>
                        <SideNavMenu renderIcon={IbmWatsonNaturalLanguageUnderstanding} title="Lab Unit">
                            <SideNavMenuItem href="#6">Link</SideNavMenuItem>
                            <SideNavMenuItem href="#7">Link</SideNavMenuItem>
                            <SideNavMenuItem href="#8">Link</SideNavMenuItem>
                        </SideNavMenu>
                    </SideNavItems>
                </SideNav>
                <Content >

                    <PathRoute path="#result">
                        <ResultSearch />
                    </PathRoute>
                    <PathRoute path="#rangeResults">
                        <ResultRangeSearch />
                    </PathRoute>
                    <PathRoute path="#codeTest">
                        <CodeTest />
                    </PathRoute>
                </Content>
            </>

        );

    }
}


export default injectIntl(Result)