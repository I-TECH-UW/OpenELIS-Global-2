import React from "react";
import "../Style.css";
import { injectIntl ,FormattedMessage} from "react-intl";
import ResultSearchPage from "./SearchResultForm";
import {

  Heading,
  Grid,
  Column,
  Section,

} from "@carbon/react";

function ResultSearch() {
  return (
    <>
    <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Section>
              <Heading>
                <FormattedMessage id="sidenav.label.results" />
              </Heading>
            </Section>
          </Section>
        </Column>
      </Grid>
      <div className="orderLegendBody">
        <ResultSearchPage />
      </div>
    </>
  );
}

export default injectIntl(ResultSearch);
