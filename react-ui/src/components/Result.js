import React from 'react'
import { injectIntl } from 'react-intl'
import './Style.css'
import ResultSearch from './resultPage/ResultSearch';
import ResultRangeSearch from './resultPage/ResultRangeSearch';
import CodeTest from './resultPage/CodeTest';

import {
    Microscope,
    IbmWatsonDiscovery,
    IbmWatsonNaturalLanguageUnderstanding
}
    from '@carbon/icons-react';
import GlobalSideBar from "./common/GlobalSideBar";
export const resultsSideMenu = { className: "resultSideNav",
    sideNavMenuItems: [
        {
            title: "Result Search",
            icon: IbmWatsonDiscovery,
            SideNavMenuItem: [
                {
                    link: "#result",
                    label: "By Accession Number"
                }
            ]
        },
        {
            title: "Patient Search",
            icon: Microscope,
            SideNavMenuItem: [
                {
                    link: "#1",
                    label: "Link"
                }
            ]
        },
        {
            title: "Lab Unit",
            icon: IbmWatsonNaturalLanguageUnderstanding,
            SideNavMenuItem: [
                {
                    link: "#1",
                    label: "Link"
                },
                {
                    link: "#1",
                    label: "Link"
                }
            ]
        },
    ],
    contentRoutes: [ {
        path: "#result",
        pageComponent: <ResultSearch/>
    },{
        path: "#rangeResults",
        pageComponent: <ResultRangeSearch/>
    },{
        path: "#codeTest",
        pageComponent: <CodeTest/>
    }]
}
class Result extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            sideNav: resultsSideMenu
        }
    }

    render() {
        return (
            <>
                <GlobalSideBar sideNav={this.state.sideNav}/>
            </>

        );

    }
}


export default injectIntl(Result)