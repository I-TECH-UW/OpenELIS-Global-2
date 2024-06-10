import React from "react";
import { injectIntl } from "react-intl";
import ReflexRule from "./ReflexRuleForm";
import { Grid, Column, Section, Heading } from "@carbon/react";
import { FormattedMessage } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb";

const breadcrumbs = [{ label: "home.label", link: "/" }];

function ReflexTestManagement() {
  return (
    <>
      <div className="adminPageContent">
        <PageBreadCrumb breadcrumbs={breadcrumbs} />
        <Grid>
          <Column lg={16}>
            <Section>
              <Heading>
                <FormattedMessage id="sidenav.label.admin.testmgt.reflex" />
              </Heading>
            </Section>
          </Column>
        </Grid>
        <ReflexRule />
      </div>
    </>
  );
}

export default injectIntl(ReflexTestManagement);
