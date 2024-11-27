import React from "react";
import { injectIntl ,useIntl } from "react-intl";
import { Breadcrumb, BreadcrumbItem, Grid } from "@carbon/react";
import HomeDashBoard from "./home/Dashboard.tsx";

function Home() {
  const intl = useIntl();
  return (
    <>
      <Grid>
        <Breadcrumb>
          <BreadcrumbItem href="/">{intl.formatMessage({ id: "home.label" })}</BreadcrumbItem>
        </Breadcrumb>
      </Grid>
      <div>
        <HomeDashBoard />
      </div>
    </>
  );
}

export default injectIntl(Home);
