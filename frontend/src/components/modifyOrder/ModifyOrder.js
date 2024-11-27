import React, { useContext, useEffect, useState, useRef } from "react";
import { useParams } from "react-router-dom";
import {
  Button,
  ProgressIndicator,
  ProgressStep,
  Stack,
  Breadcrumb,
  BreadcrumbItem,
  Grid,
  Column,
  Section,
  Tag,
} from "@carbon/react";
import EditSample from "./EditSample";
import EditOrder from "./EditOrder";
import "../addOrder/add-order.scss";
import { ModifyOrderFormValues } from "../formModel/innitialValues/OrderEntryFormValues";
import { NotificationContext } from "../layout/Layout";
import { AlertDialog, NotificationKinds } from "../common/CustomNotification";
import { postToOpenElisServer, getFromOpenElisServer } from "../utils/Utils";
import EditOrderEntryAdditionalQuestions from "./EditOrderEntryAdditionalQuestions";
import OrderSuccessMessage from "../addOrder/OrderSuccessMessage";
import { FormattedMessage, useIntl } from "react-intl";

export let sampleObject = {
  index: 0,
  sampleRejected: false,
  rejectionReason: "",
  sampleTypeId: "",
  sampleXML: null,
  panels: [],
  tests: [],
  requestReferralEnabled: false,
  referralItems: [],
};
const ModifyOrder = () => {
  const firstPageNumber = 0;
  const lastPageNumber = 3;
  const programPageNumber = firstPageNumber + 0;
  const samplePageNumber = firstPageNumber + 1;
  const orderPageNumber = firstPageNumber + 2;
  const successMsgPageNumber = lastPageNumber;

  const [page, setPage] = useState(firstPageNumber);
  const [orderFormValues, setOrderFormValues] = useState(ModifyOrderFormValues);
  const [samples, setSamples] = useState([sampleObject]);

  const componentMounted = useRef(false);

  useEffect(() => {
    componentMounted.current = true;
    let patientId = new URLSearchParams(window.location.search).get(
      "patientId",
    );
    let accessionNumber = new URLSearchParams(window.location.search).get(
      "accessionNumber",
    );
    accessionNumber = accessionNumber ? accessionNumber : "";
    patientId = patientId ? patientId : "";
    getFromOpenElisServer(
      "/rest/sample-edit?patientId=" +
        patientId +
        "&accessionNumber=" +
        accessionNumber,
      loadOrderValues,
    );
    return () => {
      componentMounted.current = false;
    };
  }, []);

  const loadOrderValues = (data) => {
    if (componentMounted.current) {
      setOrderFormValues(data);
    }
  };

  const { notificationVisible, setNotificationVisible, setNotificationBody } =
    useContext(NotificationContext);

  const showAlertMessage = (msg, kind) => {
    setNotificationVisible(true);
    setNotificationBody({
      kind: kind,
      title: <FormattedMessage id="notification.title" />,
      message: msg,
    });
  };

  const handlePost = (status) => {
    if (status === 200) {
      showAlertMessage(
        <FormattedMessage id="save.order.success.msg" />,
        NotificationKinds.success,
      );
    } else {
      showAlertMessage(
        <FormattedMessage id="server.error.msg" />,
        NotificationKinds.error,
      );
    }
  };
  const handleSubmitOrderForm = (e) => {
    e.preventDefault();
    setPage(page + 1);
    console.log(JSON.stringify(orderFormValues));
    postToOpenElisServer(
      "/rest/sample-edit",
      JSON.stringify(orderFormValues),
      handlePost,
    );
  };
  useEffect(() => {
    if (page === samplePageNumber + 1) {
      attacheSamplesToFormValues();
    }
  }, [page]);

  const attacheSamplesToFormValues = () => {
    let sampleXmlString = "";
    let referralItems = [];
    if (samples.length > 0) {
      if (samples[0].tests.length > 0) {
        sampleXmlString = '<?xml version="1.0" encoding="utf-8"?>';
        sampleXmlString += "<samples>";
        let tests = null;
        samples.map((sampleItem) => {
          if (sampleItem.tests.length > 0) {
            tests = Object.keys(sampleItem.tests)
              .map(function (i) {
                return sampleItem.tests[i].id;
              })
              .join(",");
            sampleXmlString += `<sample sampleID='${sampleItem.sampleTypeId}' date='${sampleItem.sampleXML.collectionDate}' time='${sampleItem.sampleXML.collectionTime}' collector='${sampleItem.sampleXML.collector}' tests='${tests}' testSectionMap='' testSampleTypeMap='' panels='' rejected='${sampleItem.sampleXML.rejected}' rejectReasonId='${sampleItem.sampleXML.rejectionReason}' initialConditionIds=''/>`;
          }
          if (sampleItem.referralItems.length > 0) {
            const referredInstitutes = Object.keys(sampleItem.referralItems)
              .map(function (i) {
                return sampleItem.referralItems[i].institute;
              })
              .join(",");

            const sentDates = Object.keys(sampleItem.referralItems)
              .map(function (i) {
                return sampleItem.referralItems[i].sentDate;
              })
              .join(",");

            const referralReasonIds = Object.keys(sampleItem.referralItems)
              .map(function (i) {
                return sampleItem.referralItems[i].reasonForReferral;
              })
              .join(",");

            const referrers = Object.keys(sampleItem.referralItems)
              .map(function (i) {
                return sampleItem.referralItems[i].referrer;
              })
              .join(",");
            referralItems.push({
              referrer: referrers,
              referredInstituteId: referredInstitutes,
              referredTestId: tests,
              referredSendDate: sentDates,
              referralReasonId: referralReasonIds,
            });
          }
        });
        sampleXmlString += "</samples>";
      }
    }
    setOrderFormValues({
      ...orderFormValues,
      // useReferral: true,
      sampleXML: sampleXmlString,
      // referralItems: referralItems,
    });
  };

  const navigateForward = () => {
    if (page <= lastPageNumber && page >= firstPageNumber) {
      setPage(page + 1);
    }
  };

  const navigateBackWards = () => {
    if (page > firstPageNumber) {
      setPage(page + -1);
    }
  };
  const handleTabClickHandler = (e) => {
    setPage(e);
  };
  const intl = useIntl();

  return (
    <>
      <Grid fullWidth={true}>
        <Column lg={16}>
          <Breadcrumb>
            <BreadcrumbItem href="/">
              {intl.formatMessage({ id: "home.label" })}
            </BreadcrumbItem>
            <BreadcrumbItem href="/FindOrder">
              {intl.formatMessage({ id: "sample.label.search.Order" })}
            </BreadcrumbItem>
          </Breadcrumb>
        </Column>
      </Grid>

      <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Section>
              {orderFormValues?.sampleOrderItems ? (
                <div className="patient-header">
                  <div className="patient-name">
                    <Tag type="blue">
                      <FormattedMessage id="patient.label.name" /> :
                    </Tag>
                    {orderFormValues.patientName}
                  </div>
                  <div className="patient-dob">
                    {" "}
                    <Tag type="blue">
                      <FormattedMessage id="patient.label.sex" /> :
                    </Tag>
                    {orderFormValues.gender === "M" ? (
                      <FormattedMessage id="patient.male" />
                    ) : (
                      <FormattedMessage id="patient.female" />
                    )}{" "}
                    <Tag type="blue">
                      <FormattedMessage id="patient.dob" /> :
                    </Tag>{" "}
                    {orderFormValues.dob}
                  </div>
                  <div className="patient-id">
                    <Tag type="blue">
                      <FormattedMessage id="quick.entry.accession.number" /> :
                    </Tag>
                    {orderFormValues.accessionNumber}{" "}
                  </div>
                  <div className="patient-id">
                    <Tag type="blue">
                      <FormattedMessage id="patient.natioanalid" /> :
                    </Tag>
                    {orderFormValues.nationalId}
                  </div>
                </div>
              ) : (
                <div className="patient-header">
                  <div className="patient-name">
                    {" "}
                    <FormattedMessage id="sample.label.noorder" />{" "}
                  </div>
                </div>
              )}
            </Section>
          </Section>
        </Column>
      </Grid>
      <Stack gap={10}>
        <div className="pageContent">
          {notificationVisible === true ? <AlertDialog /> : ""}
          {orderFormValues?.sampleOrderItems && (
            <div className="orderWorkFlowDiv">
              <h2>
                <FormattedMessage id="order.test.request.heading" />
              </h2>
              {page <= orderPageNumber && (
                <ProgressIndicator
                  currentIndex={page}
                  className="ProgressIndicator"
                  spaceEqually={true}
                  onChange={(e) => handleTabClickHandler(e)}
                >
                  <ProgressStep
                    label={
                      <FormattedMessage id="order.step.program.selection" />
                    }
                  />
                  <ProgressStep
                    label={<FormattedMessage id="sample.add.action" />}
                  />
                  <ProgressStep
                    label={<FormattedMessage id="order.label.add" />}
                  />
                </ProgressIndicator>
              )}
              {page === programPageNumber && (
                <EditOrderEntryAdditionalQuestions
                  orderFormValues={orderFormValues}
                  setOrderFormValues={setOrderFormValues}
                />
              )}
              {page === samplePageNumber && (
                <EditSample
                  orderFormValues={orderFormValues}
                  setOrderFormValues={setOrderFormValues}
                  setSamples={setSamples}
                  samples={samples}
                />
              )}
              {page === orderPageNumber && (
                <EditOrder
                  orderFormValues={orderFormValues}
                  setOrderFormValues={setOrderFormValues}
                  samples={samples}
                />
              )}

              {page === successMsgPageNumber && (
                <OrderSuccessMessage
                  orderFormValues={orderFormValues}
                  setOrderFormValues={setOrderFormValues}
                  setSamples={setSamples}
                  setPage={setPage}
                />
              )}
              <div className="navigationButtonsLayout">
                {page !== firstPageNumber && page <= orderPageNumber && (
                  <Button kind="tertiary" onClick={() => navigateBackWards()}>
                    <FormattedMessage id="back.action.button" />
                  </Button>
                )}

                {page < orderPageNumber && (
                  <Button
                    kind="primary"
                    className="forwardButton"
                    onClick={() => navigateForward()}
                  >
                    {<FormattedMessage id="next.action.button" />}
                  </Button>
                )}

                {page === orderPageNumber && (
                  <Button
                    kind="primary"
                    className="forwardButton"
                    onClick={handleSubmitOrderForm}
                  >
                    {<FormattedMessage id="label.button.submit" />}
                  </Button>
                )}
              </div>
            </div>
          )}
        </div>
      </Stack>
    </>
  );
};

export default ModifyOrder;
