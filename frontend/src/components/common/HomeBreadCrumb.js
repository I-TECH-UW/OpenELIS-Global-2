import React from "react";
import {Grid, Column, Breadcrumb, BreadcrumbItem} from "@carbon/react";
import { useIntl } from "react-intl";

export default function HomeBreadCrumb()  {
    const intl = useIntl();
    return(
    <>
      <Grid fullWidth={true}>
        <Column lg={16}>
        <Breadcrumb>
          <BreadcrumbItem href="/">
            {intl.formatMessage({id:"home.label"})}
          </BreadcrumbItem>
        </Breadcrumb>
        </Column>
      </Grid>
    </>
    );
}
