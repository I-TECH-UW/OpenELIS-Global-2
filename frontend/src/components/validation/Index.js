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
<<<<<<< HEAD
import GenericHomeBreadCrumb from "../common/GenericBreadCrumb";
=======
import PageBreadCrumb from "../common/PageBreadCrumb";

let breadcrumbs = [{ label: "home.label", link: "/" }];
>>>>>>> 581e2fdfa93911db7d7c2dd04ae493dcdc54cab7

const Index = () => {
  const { notificationVisible } = useContext(NotificationContext);
  const [results, setResults] = useState({ resultList: [] });
  return (
    <>
   <PageBreadCrumb breadcrumbs={breadcrumbs} />
    <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Section>
              <Heading>
                <GenericHomeBreadCrumb/>
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
