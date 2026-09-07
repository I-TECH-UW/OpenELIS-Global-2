import React, { useContext, useState, useEffect } from "react";
import { Heading, Button, Loading, Grid, Column, Section } from "@carbon/react";
import { postToOpenElisServerJsonResponse } from "../../utils/Utils";
import {
  useServerData,
  useInvalidateServerData,
} from "../../utils/useServerData";
import { NotificationContext } from "../../layout/Layout";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb";
import { CustomCommonSortableOrderList } from "./sortableListComponent/SortableList";

let breadcrumbs = [
  { label: "home.label", link: "/" },
  { label: "breadcrums.admin.managment", link: "/MasterListsPage" },
  {
    label: "master.lists.page.test.management",
    link: "/MasterListsPage/testManagementConfigMenu",
  },
  {
    label: "configuration.sampleType.manage",
    link: "/MasterListsPage/SampleTypeManagement",
  },
  {
    label: "configuration.sampleType.order",
    link: "/MasterListsPage/SampleTypeOrder",
  },
];

function SampleTypeOrder() {
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  const intl = useIntl();
  const [confirmSelection, setConfirmSelection] = useState(false);
  const [sampleTypeOrderList, setSampleTypeOrderList] = useState({});
  const [sampleTypeOrderListPost, setSampleTypeOrderListPost] = useState([]);

  const handleSampleTypeOrderListCall = () => {
    if (!sampleTypeOrderListPost) {
      // Nothing to save: read the order again rather than post an empty change.
      refreshSampleTypeOrderList("/rest/SampleTypeOrder");
      return;
    }
    postToOpenElisServerJsonResponse(
      "/rest/SampleTypeOrder",
      JSON.stringify({
        jsonChangeList: JSON.stringify({
          sampleTypes: JSON.stringify(sampleTypeOrderListPost),
        }),
      }),
      (res) => {
        handlePostSampleTypeOrderListCallBack(res);
      },
    );
  };

  const handlePostSampleTypeOrderListCallBack = (res) => {
    if (res) {
      if (res) {
        addNotification({
          title: intl.formatMessage({
            id: "notification.title",
          }),
          message: intl.formatMessage({
            id: "notification.user.post.delete.success",
          }),
          kind: NotificationKinds.success,
        });
        refreshSampleTypeOrderList("/rest/SampleTypeOrder");
        setNotificationVisible(true);
      }
    } else {
      addNotification({
        kind: NotificationKinds.error,
        title: intl.formatMessage({ id: "notification.title" }),
        message: intl.formatMessage({ id: "server.error.msg" }),
      });
      setNotificationVisible(true);
    }
  };

  // The order shown is a read of /rest/SampleTypeOrder; a save marks it out of date
  // and the screen reads it again, which is what reloading used to do.
  const {
    data: fetchedSampleTypeOrderList,
    isFetching: sampleTypeOrderListFetching,
  } = useServerData("/rest/SampleTypeOrder");
  const refreshSampleTypeOrderList = useInvalidateServerData();

  useEffect(() => {
    if (fetchedSampleTypeOrderList) {
      setSampleTypeOrderList(fetchedSampleTypeOrderList);
    }
  }, [fetchedSampleTypeOrderList]);

  if (sampleTypeOrderListFetching && !fetchedSampleTypeOrderList) {
    return (
      <>
        <Loading />
      </>
    );
  }

  return (
    <>
      {notificationVisible === true ? <AlertDialog /> : ""}
      <div className="adminPageContent">
        <PageBreadCrumb breadcrumbs={breadcrumbs} />
        <div className="orderLegendBody">
          <Grid fullWidth={true}>
            <Column lg={16} md={8} sm={4}>
              <Section>
                <Heading>
                  <FormattedMessage id="banner.menu.patientEdit" />
                </Heading>
              </Section>
            </Column>
          </Grid>
          <br />
          <hr />
          <br />
          <Grid fullWidth={true}>
            <Column lg={16} md={8} sm={4}>
              <Section>
                <Section>
                  <Section>
                    <Heading>
                      <FormattedMessage id="configuration.sampleType.order.explain" />
                    </Heading>
                  </Section>
                </Section>
              </Section>
            </Column>
          </Grid>
          <br />
          <hr />
          <br />
          <Grid fullWidth={true}>
            <Column lg={16} md={8} sm={4}>
              <Section>
                <Section>
                  <Section>
                    <Section>
                      <Heading>
                        <FormattedMessage id="configuration.sampleType.order.explain.limits" />
                      </Heading>
                    </Section>
                  </Section>
                </Section>
              </Section>
            </Column>
          </Grid>
          <br />
          <Grid fullWidth={true}>
            <Column lg={16} md={8} sm={4}>
              {sampleTypeOrderList &&
                sampleTypeOrderList?.sampleTypeList &&
                sampleTypeOrderList?.sampleTypeList?.length > 0 && (
                  <CustomCommonSortableOrderList
                    test={sampleTypeOrderList?.sampleTypeList}
                    onSort={(updatedList) => {
                      setSampleTypeOrderList((prev) => ({
                        ...prev,
                        sampleTypeList: updatedList,
                      }));
                      setSampleTypeOrderListPost(
                        updatedList.map(({ id, sortOrder }) => ({
                          id: Number(id),
                          sortOrder,
                        })),
                      );
                    }}
                    disableSorting={confirmSelection}
                  />
                )}
            </Column>
          </Grid>
          {confirmSelection && (
            <>
              <br />
              <Grid fullWidth={true}>
                <Column lg={16} md={8} sm={4}>
                  <Section>
                    <Section>
                      <Heading>
                        <FormattedMessage id="uom.create.heading.confirmation" />
                      </Heading>
                    </Section>
                  </Section>
                </Column>
              </Grid>
            </>
          )}
          <br />
          <Grid fullWidth={true}>
            <Column lg={8} md={8} sm={4}>
              <Button
                onClick={() => {
                  if (confirmSelection) {
                    handleSampleTypeOrderListCall();
                  }
                  setConfirmSelection(true);
                }}
                type="button"
                kind="primary"
              >
                {confirmSelection ? (
                  <FormattedMessage id="accept.action.button" />
                ) : (
                  <FormattedMessage id="next.action.button" />
                )}
              </Button>{" "}
              <Button
                type="button"
                kind="tertiary"
                onClick={() => {
                  // Discard the pending reordering and show what is stored.
                  setSampleTypeOrderListPost([]);
                  setConfirmSelection(false);
                  refreshSampleTypeOrderList("/rest/SampleTypeOrder");
                }}
              >
                {confirmSelection ? (
                  <FormattedMessage id="reject.action.button" />
                ) : (
                  <FormattedMessage id="label.button.previous" />
                )}
              </Button>
            </Column>
          </Grid>
        </div>
      </div>
    </>
  );
}

export default injectIntl(SampleTypeOrder);
