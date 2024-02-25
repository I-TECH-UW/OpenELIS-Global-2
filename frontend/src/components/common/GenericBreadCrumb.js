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