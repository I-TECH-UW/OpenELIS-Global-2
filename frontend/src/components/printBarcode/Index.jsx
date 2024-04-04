import { FormattedMessage } from "react-intl";
import { Column, Grid, Heading, Section } from "@carbon/react";
import ExistingOrder from "./ExistingOrder.jsx";
import PrePrint from "./PrePrint";
import PageBreadCrumb from "../common/PageBreadCrumb.jsx";

let breadcrumbs = [{ label: "home.label", link: "/" }];
const PrintBarcode = () => {
  return (
    <div>
      <PageBreadCrumb breadcrumbs={breadcrumbs} />
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