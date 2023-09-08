import React, { useContext, useState, useEffect } from "react";
import SearchForm from "./SearchForm";
import Validation from "./Validation";
import { AlertDialog } from "../common/CustomNotification";
import { NotificationContext } from "../layout/Layout";
import {

  Heading,
  Grid,
  Column,
  Section,

} from "@carbon/react";
import { injectIntl ,FormattedMessage} from "react-intl";

const Index = () => {
  const { notificationVisible } = useContext(NotificationContext);
  const [results, setResults] = useState({ resultList: [] });
  return (
    <>
    <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Section>
              <Heading>
                <FormattedMessage id="sidenav.label.validation" />
              </Heading>
            </Section>
          </Section>
        </Column>
      </Grid>
    <div className="orderLegendBody">
      {notificationVisible === true ? <AlertDialog /> : ""}
      <SearchForm setResults={setResults} />
      <Validation results={results} />
    </div>
    </>
  );
};

export default Index;
