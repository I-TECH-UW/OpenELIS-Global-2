import React, { useContext, useState, useEffect } from "react";
import { AlertDialog } from "../../common/CustomNotification";
import { NotificationContext } from "../../layout/Layout";
import {
  Heading,
  Grid,
  Column,
  Section
} from "@carbon/react";
import { FormattedMessage} from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb";
import AuditTrailReport from "./AuditTrailReport";

const AuditTrail = () => {
    return(
        <>
            <br />
            <PageBreadCrumb breadcrumbs={breadcrumbs} />
            <Grid fullWidth={true}>
            <Column lg={16}>
                <Section>
                <Section>
                    <Heading>
                    <FormattedMessage id="selectReportValues.title" />
                    </Heading>
                </Section>
                </Section>
            </Column>
            </Grid>
            </>
    )
}