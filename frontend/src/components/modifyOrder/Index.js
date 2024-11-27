import React, { useContext } from "react";
import { FormattedMessage } from "react-intl";

import { Column, Grid, Heading, Section } from "@carbon/react";
import SearchOrder from "./SearchOrder";
import { AlertDialog } from "../common/CustomNotification";
import { NotificationContext } from "../layout/Layout";

const Index = () => {
  const { notificationVisible } = useContext(NotificationContext);
  return (
    <div className="pageContent">
      {notificationVisible === true ? <AlertDialog /> : ""}
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
