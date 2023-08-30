import React from "react";
import { injectIntl } from "react-intl";
import { Breadcrumb, BreadcrumbItem, Grid } from "@carbon/react";
import HomeDashBoard from "./home/Dashboard.tsx";

function Home() {
  return (
    <>
      <Grid>
        <Breadcrumb>
          <BreadcrumbItem href="/">Home</BreadcrumbItem>
        </Breadcrumb>
      </Grid>
      <div>
        <HomeDashBoard />
      </div>
    </>
  );
}

export default injectIntl(Home);
