import React, { useEffect, useState }from "react";
import "../Style.css";
import { injectIntl ,FormattedMessage, useIntl } from "react-intl";
import ResultSearchPage from "./SearchResultForm";
import {

  Heading,
  Grid,
  Column,
  Section,
  Breadcrumb,
  BreadcrumbItem,
} from "@carbon/react";

function ResultSearch() {
  const intl = useIntl();
  const [source, setSource] = useState("");
  useEffect(() => {
    let sourceFromUrl = new URLSearchParams(window.location.search).get("source");
    let sources = ["WorkPlanByTest", "WorkPlanByPanel", "WorkPlanByTestSection", "WorkPlanByPriority"];
    sourceFromUrl = sources.includes(sourceFromUrl) ? sourceFromUrl : "";
    setSource(sourceFromUrl);
  }, []);
  return (
    <>
    <Grid fullWidth={true}>
      <Column lg={16}>
        <Breadcrumb>
          <BreadcrumbItem href="/">
            {intl.formatMessage({ id: "home.label" })}
          </BreadcrumbItem>
          {source && (
            <BreadcrumbItem href={`/${source}`}>
              {intl.formatMessage({
                id: "banner.menu.workplan",
              })}
            </BreadcrumbItem>
          )}
        </Breadcrumb>
      </Column>
    </Grid>
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
