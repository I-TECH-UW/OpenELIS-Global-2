import React from "react";
import { FormattedMessage, injectIntl } from "react-intl";
import "../Style.css";
import { Heading, Grid, Column, Section } from "@carbon/react";
import PageBreadCrumb from "../common/PageBreadCrumb";

const breadcrumbs = [
  { label: "home.label", link: "/" },
  { label: "dictionary.label.modify", link: "/DictionaryManagement" },
];

function DictionaryManagement() {
  return (
    <>
      <PageBreadCrumb breadcrumbs={breadcrumbs} />
      <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Section>
              <Heading>
                <FormattedMessage id="dictionary.label.modify" />
              </Heading>
            </Section>
          </Section>
        </Column>
      </Grid>
      <br></br>
      <div className="orderLegendBody">
        <h1>Dictionary Management</h1>
      </div>
    </>
  );
}

export default injectIntl(DictionaryManagement);
