import React, { useContext, useState, useEffect, useRef } from "react";
import {
  Heading,
  Button,
  Loading,
  Grid,
  Column,
  Section,
  DataTable,
  Table,
  TableHead,
  TableRow,
  TableBody,
  TableHeader,
  TableCell,
  TableSelectRow,
  TableSelectAll,
  TableContainer,
  Pagination,
  Search,
  Modal,
  TextInput,
  Dropdown,
} from "@carbon/react";
import {
  getFromOpenElisServer,
  postToOpenElisServerFullResponse,
} from "../../utils/Utils.js";
import {
  ConfigurationContext,
  NotificationContext,
} from "../../layout/Layout.js";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification.js";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb.js";
import { ArrowLeft, ArrowRight } from "@carbon/icons-react";
import ActionPaginationButtonType from "../../common/ActionPaginationButtonType.js";

let breadcrumbs = [
  { label: "home.label", link: "/" },
  { label: "breadcrums.admin.managment", link: "/MasterListsPage" },
  {
    label: "testnotificationconfig.browse.title",
    link: "/MasterListsPage#testNotificationConfigMenu",
  },
];

function GenricTestNotificationConfigEdit() {
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  const intl = useIntl();

  const componentMounted = useRef(false);

  return (
    <>
      <div className="adminPageContent">
        <PageBreadCrumb breadcrumbs={breadcrumbs} />
        <Grid fullWidth={true}>
          <Column lg={16} md={8} sm={4}>
            <Section>
              <Section>
                <Heading>
                  <FormattedMessage id="testnotificationconfig.browse.title" />
                </Heading>
              </Section>
            </Section>
          </Column>
        </Grid>
        <div className="orderLegendBody">
          <Grid fullWidth={true} className="gridBoundary">
            <Column lg={16} md={8} sm={4}>
              <Section>
                <Section>
                  <Heading>
                    <FormattedMessage id="testnotification.instructions.header" />
                  </Heading>
                </Section>
              </Section>
              <br />
              <FormattedMessage id="testnotification.instructions.body" />
              <br />
              <FormattedMessage id="testnotification.instructions.body.0" />
              <br />
              <FormattedMessage id="testnotification.instructions.body.1" />
              <br />
              <FormattedMessage id="testnotification.instructions.body.2" />
              <br />
              <FormattedMessage id="testnotification.instructions.body.3" />
              <br />
              <Section>
                <Section>
                  <Section>
                    <Heading id="testnotification.instructionis.variables.header" />
                  </Section>
                </Section>
              </Section>
              <br />
              <FormattedMessage id="testnotification.instructionis.variables.body" />
              <br />
              <FormattedMessage id="testnotification.instructionis.variables.body.0" />
              <br />
              <FormattedMessage id="testnotification.instructionis.variables.body.1" />
              <br />
              <FormattedMessage id="testnotification.instructionis.variables.body.2" />
              <br />
            </Column>
          </Grid>
        </div>
      </div>
    </>
  );
}

export default injectIntl(GenricTestNotificationConfigEdit);
