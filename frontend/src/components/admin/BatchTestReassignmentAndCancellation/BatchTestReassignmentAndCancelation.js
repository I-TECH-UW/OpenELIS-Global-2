import React, { useContext, useState, useEffect, useRef } from "react";
import {
  Grid,
  Column,
  Section,
  Heading,
  Form,
  TextInput,
  UnorderedList,
  ListItem,
  RadioButton,
  Button,
  Loading,
  Select,
  SelectItem,
  PasswordInput,
  Checkbox,
  FormGroup,
  Tag,
} from "@carbon/react";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb.js";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification.js";
import {
  ConfigurationContext,
  NotificationContext,
} from "../../layout/Layout.js";
import {
  getFromOpenElisServer,
  postToOpenElisServer,
  postToOpenElisServerJsonResponse,
} from "../../utils/Utils.js";
import AutoComplete from "../../common/AutoComplete.js";

const breadcrumbs = [
  { label: "home.label", link: "/" },
  { label: "breadcrums.admin.managment", link: "/MasterListsPage" },
  {
    label: "configuration.batch.test.reassignment",
    link: "/MasterListsPage#batchTestReassignment",
  },
];

function BatchTestReassignmentAndCancelation() {
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);
  const { configurationProperties } = useContext(ConfigurationContext);

  const componentMounted = useRef(false);
  const intl = useIntl();

  const [saveButton, setSaveButton] = useState(true);
  const [isLoading, setIsLoading] = useState(true);
  const [currentTest, setCurrentTest] = useState(true);
  const [replaceWith, setReplaceWith] = useState(false);
  const [batchTestGet, setBatchTestGet] = useState(null);
  const [batchTestPost, setBatchTestPost] = useState(null);
  const [sampleTypeListShow, setSampleTypeListShow] = useState([]);
  const [sampleTypeToGetId, setSampleTypeToGetId] = useState(null);
  const [sampleTypeToGetIdData, setSampleTypeToGetIdData] = useState([]);
  const [sampleTypeTestIdToGetIdPending, setSampleTestTypeToGetPending] =
    useState(null);
  const [
    sampleTypeTestIdToGetIdPendingData,
    setSampleTestTypeToGetPendingData,
  ] = useState({});
  const [sampleTestTypeToGetTagList, setSampleTestTypeToGetTagList] = useState(
    [],
  );
  const [jsonWad, setJsonWad] = useState({});

  useEffect(() => {
    componentMounted.current = true;
    setIsLoading(true);
    getFromOpenElisServer(
      `/rest/BatchTestReassignment`,
      handleBatchTestReassignment,
    );
    return () => {
      componentMounted.current = false;
      setIsLoading(false);
    };
  }, []);

  const handleBatchTestReassignment = (res) => {
    if (!res) {
      setIsLoading(true);
    } else {
      setBatchTestGet(res);
    }
  };

  useEffect(() => {
    const handleBatchTestReassignmentSampleTypeHandle = (res) => {
      if (!res) {
        setIsLoading(true);
      } else {
        setSampleTypeToGetIdData(res);
      }
    };

    getFromOpenElisServer(
      `/rest/AllTestsForSampleTypeProvider?sampleTypeId=${sampleTypeToGetId}`,
      handleBatchTestReassignmentSampleTypeHandle,
    );
  }, [sampleTypeToGetId]);

  useEffect(() => {
    const handleBatchTestReassignmentSampleTypeTestHandle = (res) => {
      if (!res) {
        setIsLoading(true);
      } else {
        setSampleTestTypeToGetPendingData(res);
      }
    };

    getFromOpenElisServer(
      `/rest/getPendingAnalysisForTestProvider?testId=${sampleTypeTestIdToGetIdPending}`,
      handleBatchTestReassignmentSampleTypeTestHandle,
    );
  }, [sampleTypeTestIdToGetIdPending]);

  useEffect(() => {
    if (batchTestGet) {
      const BatchTestReassignmentInfoToPost = {
        formName: batchTestGet.formName,
        formMethod: batchTestGet.formMethod,
        cancelAction: batchTestGet.cancelAction,
        submitOnCancel: batchTestGet.submitOnCancel,
        cancelMethod: batchTestGet.cancelMethod,
        sampleList: batchTestGet.sampleList,
        statusChangedSampleType: batchTestGet.statusChangedSampleType,
        statusChangedCurrentTest: batchTestGet.statusChangedCurrentTest,
        statusChangedNextTest: batchTestGet.statusChangedNextTest,
        jsonWad: batchTestGet.jsonWad,
      };
      setBatchTestPost(BatchTestReassignmentInfoToPost);
      setSampleTypeListShow((prevSampleTypeListShow) => [
        ...prevSampleTypeListShow,
        { id: "0", value: "Select SampleType" },
        ...batchTestGet.sampleList,
      ]);
    }
  }, [batchTestGet]);

  function batchTestReassignmentPostCall() {
    setIsLoading(true);
    postToOpenElisServerJsonResponse(
      `/rest/BatchTestReassignment`,
      JSON.stringify(batchTestPost),
      (res) => {
        sampleTypeListPostCallback(res);
      },
    );
  }

  function sampleTypeListPostCallback(res) {
    if (res) {
      setIsLoading(false);
      addNotification({
        title: intl.formatMessage({
          id: "notification.title",
        }),
        message: intl.formatMessage({
          id: "notification.user.post.save.success",
        }),
        kind: NotificationKinds.success,
      });
      setNotificationVisible(true);
      setTimeout(() => {
        window.location.reload();
      }, 200);
    } else {
      addNotification({
        kind: NotificationKinds.error,
        title: intl.formatMessage({ id: "notification.title" }),
        message: intl.formatMessage({ id: "server.error.msg" }),
      });
      setNotificationVisible(true);
      setTimeout(() => {
        window.location.reload();
      }, 200);
    }
  }

  function handleCheckboxChange(id, index) {
    setSaveButton(false);
    setJsonWad({});
  }

  function handleSampleTypeListSelectId(e) {
    setSaveButton(false);
    setSampleTypeToGetId(e.target.value);
    setBatchTestPost((prevUserDataPost) => ({
      ...prevUserDataPost,
      statusChangedSampleType: e.target.value,
    }));
  }

  function handleSampleTypeListSelectIdTest(e) {
    setSaveButton(false);
    setSampleTestTypeToGetPending(e.target.value);
    setBatchTestPost((prevUserDataPost) => ({
      ...prevUserDataPost,
      statusChangedCurrentTest: e.target.value,
    }));
  }

  const handleSampleTypeListSelectIdTestTag = (e) => {
    const selectedTestId = e.target.value;
    const testName = e.target.options[e.target.selectedIndex].text;

    const existingIndex = sampleTestTypeToGetTagList.findIndex(
      (item) => item.id === selectedTestId,
    );

    if (existingIndex !== -1) {
      const updatedList = [...sampleTestTypeToGetTagList];
      updatedList.splice(existingIndex, 1);
      setSampleTestTypeToGetTagList(updatedList);
    } else {
      const selectedTest = {
        id: selectedTestId,
        name: testName,
      };
      setSampleTestTypeToGetTagList([
        ...sampleTestTypeToGetTagList,
        selectedTest,
      ]);
    }
  };

  function handleRemoveSampleTypeListSelectIdTestTag(indexToRemove) {
    setSampleTestTypeToGetTagList((prevTags) =>
      prevTags.filter((_, index) => index !== indexToRemove),
    );
  }

  if (!isLoading) {
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
        <Grid>
          <Column lg={16} md={8} sm={4}>
            <Section>
              <Section>
                <Heading>
                  <FormattedMessage id="configuration.batch.test.reassignment" />
                </Heading>
              </Section>
            </Section>
          </Column>
        </Grid>
        <div className="orderLegendBody">
          <Grid fullWidth={true}>
            <Column lg={8} md={4} sm={4}>
              <Section>
                <Section>
                  <Section>
                    <Heading>
                      <FormattedMessage id="sample.select.type" />
                    </Heading>
                  </Section>
                </Section>
              </Section>
            </Column>
          </Grid>
          <hr />
          <br />
          <Grid fullWidth={true}>
            <Column lg={4} md={4} sm={4}>
              <Select
                id={`selectSampleType`}
                labelText={intl.formatMessage({ id: "sample.type" })}
                defaultValue={sampleTypeListShow ? sampleTypeListShow[0] : ""}
                onChange={(e) => handleSampleTypeListSelectId(e)}
              >
                {sampleTypeListShow && sampleTypeListShow.length > 0 ? (
                  sampleTypeListShow.map((section) => (
                    <SelectItem
                      key={section.id}
                      value={section.id}
                      text={section.value}
                    />
                  ))
                ) : (
                  <SelectItem key={``} value="" text="No options available" />
                )}
              </Select>
            </Column>
          </Grid>
          <br />
          <Grid fullWidth={true}>
            <Column lg={8} md={4} sm={4}>
              <FormattedMessage id="label.currentTest" />
              <br />
              <Checkbox
                id={`currentTest`}
                value={`0`}
                labelText={intl.formatMessage({
                  id: "label.includeInactiveTests",
                })}
                checked={currentTest}
                onChange={() => {
                  setCurrentTest(!currentTest);
                }}
              />
              <br />
              <Select
                key={`selectSampleType1`}
                id={`selectSampleType1`}
                hideLabel={true}
                defaultValue={
                  sampleTypeToGetIdData ? sampleTypeToGetIdData[0] : ""
                }
                onChange={(e) => handleSampleTypeListSelectIdTest(e)}
              >
                {sampleTypeToGetIdData &&
                sampleTypeToGetIdData.tests &&
                sampleTypeToGetIdData.tests.length > 0 ? (
                  sampleTypeToGetIdData.tests.map((section) => (
                    <SelectItem
                      key={section.id}
                      value={section.id}
                      text={section.name}
                    />
                  ))
                ) : (
                  <SelectItem
                    key="no-option-available-selectSampleType"
                    value=""
                    text="No options available"
                  />
                )}
              </Select>
            </Column>
            <Column lg={8} md={4} sm={4}>
              <FormattedMessage id="label.replaceWith" />
              <br />
              <Checkbox
                id={`replaceWith`}
                value={`1`}
                labelText={intl.formatMessage({
                  id: "label.cancel.test.no.replace",
                })}
                checked={replaceWith}
                onChange={() => {
                  setReplaceWith(!replaceWith);
                }}
              />
              <br />
              <Select
                key={`selectSampleType0`}
                id={`selectSampleType0`}
                hideLabel={true}
                defaultValue={
                  sampleTypeToGetIdData && sampleTypeToGetIdData.tests
                    ? sampleTypeToGetIdData.tests[0]
                    : ""
                }
                disabled={replaceWith}
                onChange={(e) => handleSampleTypeListSelectIdTestTag(e)}
              >
                {sampleTypeToGetIdData &&
                sampleTypeToGetIdData.tests &&
                sampleTypeToGetIdData.tests.length > 0 ? (
                  sampleTypeToGetIdData.tests.map((section) => (
                    <SelectItem
                      key={section.id}
                      value={section.id}
                      text={section.name}
                    />
                  ))
                ) : (
                  <SelectItem
                    key="no-option-available-selectSampleType0"
                    value=""
                    text="No options available"
                  />
                )}
              </Select>
              <div
                className={"searchTestText"}
                style={{ marginBottom: "1.188rem" }}
              >
                {sampleTestTypeToGetTagList &&
                sampleTestTypeToGetTagList.length ? (
                  <>
                    {sampleTestTypeToGetTagList.map((section, index) => (
                      <Tag
                        filter
                        key={`testTags_` + index}
                        onClose={() =>
                          handleRemoveSampleTypeListSelectIdTestTag(index)
                        }
                        style={{ marginRight: "0.5rem" }}
                        type={"red"}
                      >
                        {section.name}
                      </Tag>
                    ))}
                  </>
                ) : (
                  <></>
                )}
              </div>
            </Column>
          </Grid>
          <br />
          <Grid fullWidth={true}>
            <Column lg={8} md={4} sm={4}>
              <FormattedMessage id="label.checkedWillBeModified" />
            </Column>
          </Grid>
          <br />
          <Grid fullWidth={true}>
            <Column lg={4} md={2} sm={1}>
              <FormattedMessage id="label.analysisNotStarted" />
              <br />
              <Checkbox
                id={`selectAll0`}
                value={`1`}
                labelText={intl.formatMessage({
                  id: "referral.print.selected.patient.reports.selectall.button",
                })}
                checked={false}
                // onChange={() => {
                //   handleCheckboxChange(section.roleId);
                // }}
              />
              {sampleTypeTestIdToGetIdPendingData &&
                sampleTypeTestIdToGetIdPendingData.notStarted &&
                sampleTypeTestIdToGetIdPendingData.notStarted.map(
                  (item, index) => (
                    <div key={index}>
                      <Checkbox
                        id={`notStartedCheckbox_${index}`}
                        value={item.id}
                        labelText={intl.formatMessage({
                          id: item.labNo,
                        })}
                        checked={false}
                        onChange={() => {
                          handleCheckboxChange(item.id, index);
                        }}
                      />
                    </div>
                  ),
                )}
              <br />
            </Column>
            <Column lg={4} md={2} sm={1}>
              <FormattedMessage id="label.rejectedByTechnician" />
              <br />
              <Checkbox
                id={`selectAll2`}
                value={`1`}
                labelText={intl.formatMessage({
                  id: "referral.print.selected.patient.reports.selectall.button",
                })}
                checked={false}
                // onChange={() => {
                //   handleCheckboxChange(section.roleId);
                // }}
              />
              {sampleTypeTestIdToGetIdPendingData &&
                sampleTypeTestIdToGetIdPendingData.technicianRejection &&
                sampleTypeTestIdToGetIdPendingData.technicianRejection.map(
                  (item, index) => (
                    <div key={index}>
                      <Checkbox
                        id={`technicianRejectionCheckbox_${index}`}
                        value={item.id}
                        labelText={intl.formatMessage({
                          id: item.labNo,
                        })}
                        checked={false}
                        onChange={() => {
                          handleCheckboxChange(item.id, index);
                        }}
                      />
                    </div>
                  ),
                )}
              <br />
            </Column>
            <Column lg={4} md={2} sm={1}>
              <FormattedMessage id="label.rejectedByBiologist" />
              <br />
              <Checkbox
                id={`selectAll1`}
                value={`1`}
                labelText={intl.formatMessage({
                  id: "referral.print.selected.patient.reports.selectall.button",
                })}
                checked={false}
                // onChange={() => {
                //   handleCheckboxChange(section.roleId);
                // }}
              />
              {sampleTypeTestIdToGetIdPendingData &&
                sampleTypeTestIdToGetIdPendingData.biologistRejection &&
                sampleTypeTestIdToGetIdPendingData.biologistRejection.map(
                  (item, index) => (
                    <div key={index}>
                      <Checkbox
                        id={`biologistRejectionCheckbox_${index}`}
                        value={item.id}
                        labelText={intl.formatMessage({
                          id: item.labNo,
                        })}
                        checked={false}
                        onChange={() => {
                          handleCheckboxChange(item.id, index);
                        }}
                      />
                    </div>
                  ),
                )}
              <br />
            </Column>
            <Column lg={4} md={2} sm={1}>
              <FormattedMessage id="label.notValidated" />
              <br />
              <Checkbox
                id={`selectAll3`}
                value={`1`}
                labelText={intl.formatMessage({
                  id: "referral.print.selected.patient.reports.selectall.button",
                })}
                checked={false}
                // onChange={() => {
                //   handleCheckboxChange(section.roleId);
                // }}
              />
              {sampleTypeTestIdToGetIdPendingData &&
                sampleTypeTestIdToGetIdPendingData.notValidated &&
                sampleTypeTestIdToGetIdPendingData.notValidated.map(
                  (item, index) => (
                    <div key={index}>
                      <Checkbox
                        id={`notValidatedCheckbox_${index}`}
                        value={item.id}
                        labelText={intl.formatMessage({
                          id: item.labNo,
                        })}
                        checked={false}
                        onChange={() => {
                          handleCheckboxChange(item.id, index);
                        }}
                      />
                    </div>
                  ),
                )}
              <br />
            </Column>
          </Grid>
          <br />
          <hr />
          <br />
          <Grid fullWidth={true}>
            <Column lg={16} md={8} sm={4}>
              <Button
                disabled={saveButton}
                onClick={batchTestReassignmentPostCall}
                type="button"
              >
                <FormattedMessage id="label.button.ok" />
              </Button>{" "}
              <Button
                onClick={() =>
                  window.location.assign("/MasterListsPage#userManagement")
                }
                kind="tertiary"
                type="button"
              >
                <FormattedMessage id="label.button.cancel" />
              </Button>
            </Column>
          </Grid>
        </div>
        <button
          onClick={() => {
            console.log(batchTestGet.sampleList);
          }}
        >
          batchTestGet.sampleList
        </button>
        <button
          onClick={() => {
            console.log(batchTestPost);
          }}
        >
          batchTestPost
        </button>
        <button
          onClick={() => {
            console.log(sampleTypeToGetId);
          }}
        >
          sampleTypeToGetId
        </button>
        <button
          onClick={() => {
            console.log(sampleTypeToGetIdData);
          }}
        >
          sampleTypeToGetIdData
        </button>
        <button
          onClick={() => {
            console.log(sampleTypeTestIdToGetIdPendingData);
          }}
        >
          sampleTypeTestIdToGetIdPendingData
        </button>
        <button
          onClick={() => {
            console.log(jsonWad);
          }}
        >
          jsonWad
        </button>
      </div>
    </>
  );
}

export default injectIntl(BatchTestReassignmentAndCancelation);

// selectAll CheckBox fix needed
// single labNo selection fix needed
// SelectAll function fix
// post call checkup
// multi-select need to implement
