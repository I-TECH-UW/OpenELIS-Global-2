import React, { useContext, useEffect, useState } from "react";
import {
  Button,
  ProgressIndicator,
  ProgressStep,
  Stack,
  ToastNotification,
} from "@carbon/react";
import PatientInfo from "./PatientInfo";
import AddSample from "./AddSample";
import AddOrder from "./AddOrder";
import "./add-order.scss";
import { SampleOrderFormValues } from "../formModel/innitialValues/OrderEntryFormValues";
import { NotificationContext } from "../layout/Layout";
import { AlertDialog, NotificationKinds } from "../common/CustomNotification";
import { postToOpenElisServer } from "../utils/Utils";
import OrderEntryAdditionalQuestions from "./OrderEntryAdditionalQuestions";
import OrderSuccessMessage from "./OrderSuccessMessage";
import { FormattedMessage } from "react-intl";
import OrderEntryValidationSchema from "../formModel/validationSchema/OrderEntryValidationSchema";
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
const Index = () => {
  const firstPageNumber = 0;
  const lastPageNumber = 4;
  const patientInfoPageNumber = firstPageNumber;
  const programPageNumber = firstPageNumber + 1;
  const samplePageNumber = firstPageNumber + 2;
  const orderPageNumber = firstPageNumber + 3;
  const successMsgPageNumber = lastPageNumber;

  const [page, setPage] = useState(firstPageNumber);
  const [orderFormValues, setOrderFormValues] = useState(SampleOrderFormValues);
  const [samples, setSamples] = useState([sampleObject]);
  const [errors, setErrors] = useState([]);

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
      setPage(page + 1);
    } else {
      showAlertMessage(
        <FormattedMessage id="server.error.msg" />,
        NotificationKinds.error,
      );
    }
  };
  const elementError = (path) => {
    if (errors?.errors?.length > 0) {
      let error = errors.inner?.find((e) => e.path === path);
      if (error) {
        return error.message;
      } else {
        return null;
      }
    }
  };

  const handleSubmitOrderForm = (e) => {
    e.preventDefault();
    if ("years" in orderFormValues.patientProperties) {
      delete orderFormValues.patientProperties.years;
    }
    if ("months" in orderFormValues.patientProperties) {
      delete orderFormValues.patientProperties.months;
    }
    if ("days" in orderFormValues.patientProperties) {
      delete orderFormValues.patientProperties.days;
    }
    if ("questionnaire" in orderFormValues.sampleOrderItems) {
      delete orderFormValues.sampleOrderItems.questionnaire;
    }
    console.log(JSON.stringify(orderFormValues));
    postToOpenElisServer(
      "/rest/SamplePatientEntry",
      JSON.stringify(orderFormValues),
      handlePost,
    );
  };
  useEffect(() => {
    if (page === samplePageNumber + 1) {
      attacheSamplesToFormValues();
    }
  }, [page]);

  useEffect(() => {
    OrderEntryValidationSchema.validate(orderFormValues, { abortEarly: false })
      .then((validData) => {
        setErrors([]);
        console.log("Valid Data:", validData);
      })
      .catch((errors) => {
        setErrors(errors);
        console.error("Validation Errors:", errors.errors);
      });
  }, [orderFormValues]);

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
      useReferral: true,
      sampleXML: sampleXmlString,
      referralItems: referralItems,
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

  return (
    <>
      <Stack gap={10}>
        <div className="pageContent">
          {notificationVisible === true ? <AlertDialog /> : ""}
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
                  complete
                  label={<FormattedMessage id="order.step.patient.info" />}
                />
                <ProgressStep
                  label={<FormattedMessage id="order.step.program.selection" />}
                />
                <ProgressStep
                  label={<FormattedMessage id="sample.add.action" />}
                />
                <ProgressStep
                  label={<FormattedMessage id="order.label.add" />}
                />
              </ProgressIndicator>
            )}

            {page === patientInfoPageNumber && (
              <PatientInfo
                orderFormValues={orderFormValues}
                setOrderFormValues={setOrderFormValues}
                error={elementError}
              />
            )}
            {page === programPageNumber && (
              <OrderEntryAdditionalQuestions
                orderFormValues={orderFormValues}
                setOrderFormValues={setOrderFormValues}
              />
            )}
            {page === samplePageNumber && (
              <AddSample
                error={elementError}
                setSamples={setSamples}
                samples={samples}
              />
            )}
            {page === orderPageNumber && (
              <AddOrder
                orderFormValues={orderFormValues}
                setOrderFormValues={setOrderFormValues}
                samples={samples}
                error={elementError}
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
                  disabled={errors?.errors?.length > 0 ? true : false}
                  onClick={handleSubmitOrderForm}
                >
                  {<FormattedMessage id="label.button.submit" />}
                </Button>
              )}
            </div>
          </div>
        </div>
      </Stack>
    </>
  );
};

export default Index;
