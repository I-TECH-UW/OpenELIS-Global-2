import React, { useContext } from "react";
import { FormattedMessage, useIntl } from "react-intl";

import { Column, Grid, Heading, Section, Breadcrumb, BreadcrumbItem } from "@carbon/react";
import SearchOrder from "./SearchOrder";
import { AlertDialog } from "../common/CustomNotification";
import { NotificationContext } from "../layout/Layout";

const Index = () => {
  const intl = useIntl();
  const { notificationVisible } = useContext(NotificationContext);
  return (
    <div className="pageContent">
      {notificationVisible === true ? <AlertDialog /> : ""}
      <Grid fullWidth={true}>
      <Column lg={16}>
        <Breadcrumb>
          <BreadcrumbItem href="/">
            {intl.formatMessage({id:"home.label"})}
          </BreadcrumbItem>
        </Breadcrumb>
      </Column>
    </Grid>
      <Grid fullWidth={true}>
        <Column lg={12}>
          <Section>
            <Section>
              <Heading>
                <FormattedMessage id="order.label.modify" />
              </Heading>
            </Section>
          </Section>
        </Column>
      </Grid>
      <SearchOrder />
    </div>
  );
};

export default Index;
