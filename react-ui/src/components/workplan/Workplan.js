import { Column, Grid, Heading, Section } from "@carbon/react";
import React from "react";
import "../Style.css";
import { FormattedMessage } from "react-intl";
import WorkplanSearchForm from "./WorkplanSearchForm";

export default function Workplan(props) {

    let title = ""
    const type = props.type
    switch(type){
        case 'test': title= <FormattedMessage id="workplan.test.title" />
        break;
        case 'panel': title= <FormattedMessage id="workplan.panel.title" />
        break;
        case 'unit': title= <FormattedMessage id="workplan.unit.title" />
        break;
        case 'priority': title= <FormattedMessage id="workplan.priority.title" />
        break;
        default: title=""
    }

  return (
    <>
        <Grid fullWidth={true} >
            <Column lg={16}>
                <Section>
                    <Heading >
                        {title}
                    </Heading>
                </Section>
            </Column>
            <br/>
            <WorkplanSearchForm  type={type}/>
        </Grid>
        <hr/>
     <br/>
    </>
  );
}