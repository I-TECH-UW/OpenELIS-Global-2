import React from "react";
import { injectIntl } from "react-intl";
import ReflexRule from "./ReflexRuleForm";
import { Grid, Column, Section, Heading } from "@carbon/react";
import { FormattedMessage } from "react-intl";

function ReflexTestManagement() {
  return (
    <>
      <div className="adminPageContent">
      <Grid >
          <Column lg={16}>
            <Section>
              <Section>
                <Heading>
                  <FormattedMessage id="sidenav.label.admin.testmgt.reflex" />
                </Heading>
              </Section>
            </Section>
          </Column>
        </Grid>
        <ReflexRule />
      </div>
    </>
  );
}

export default injectIntl(ReflexTestManagement);
