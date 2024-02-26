import React from "react";
import {
    Breadcrumb,
    BreadcrumbItem,
    Grid,
    Column,
  } from "@carbon/react";
  import { useIntl } from "react-intl";

  const GenericBreadCrumb = () => {
    const intl = useIntl();

    const items = [
      { id: 1, label: intl.formatMessage({ id: "home.label" }), url: "/" },
      { id: 2, label: intl.formatMessage({ id: "sidenav.label.reports.routine" }), url: '/routineReports' },
    ];

    return(
      <>
       <Grid fullWidth={true}>
        <Column lg={16}>
          <Breadcrumb>
            <BreadcrumbItem href="/">
              {intl.formatMessage({ id: "home.label" })}
            </BreadcrumbItem>
          </Breadcrumb>
        </Column>
       </Grid>
      </>
    );
  };

  export default GenericBreadCrumb;