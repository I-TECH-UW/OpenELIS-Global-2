import React from "react";
import { FormattedMessage } from "react-intl";
import { Column, Grid, Heading, Section } from "@carbon/react";
import ExistingOrder from "./ExistingOrder";
import PrePrint from "./PrePrint";

const PrintBarcode = () => {
  return (
    <div>
      <Grid fullWidth={true}>
        <Column lg={16} md={8} sm={4}>
          <Section>
            <Section>
              <Heading>
                <FormattedMessage id="barcode.print.title" />
              </Heading>
            </Section>
          </Section>
        </Column>
      </Grid>
      <PrePrint />
      <ExistingOrder />
    </div>
  );
};
export default PrintBarcode;
