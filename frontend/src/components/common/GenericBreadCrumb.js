import React from "react";
import {
    Breadcrumb,
    BreadcrumbItem,
    Grid,
    Column,
  } from "@carbon/react";

  const GenericBreadCrumb = () => {
    const intl = useIntl();

    return(
      <>
       <Grid fullWidth={true}>
        <Column lg={16}>
          <Breadcrumb>
            <BreadcrumbItem href="/">
              {intl.formatMessage({ id: "home.label" })}
            </BreadcrumbItem>
            <BreadcrumbItem href="/SampleEdit">
              {/* {intl.formatMessage({ id: "sample.label.search.Order" })} */}
            </BreadcrumbItem>
          </Breadcrumb>
        </Column>
       </Grid>
      </>
    );
  };

  export default GenericBreadCrumb;