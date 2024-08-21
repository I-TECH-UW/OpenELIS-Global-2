import React, { useContext, useState, useEffect, useRef } from "react";
import {
  Form,
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
  Select,
  SelectItem,
  Stack,
  TextInput,
  Checkbox,
  Row,
  FlexGrid,
  Tag,
  UnorderedList,
  ListItem,
} from "@carbon/react";
import {
  getFromOpenElisServer,
  postToOpenElisServer,
  postToOpenElisServerFormData,
  postToOpenElisServerFullResponse,
  postToOpenElisServerJsonResponse,
} from "../../utils/Utils.js";
import { NotificationContext } from "../../layout/Layout.js";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification.js";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb.js";
import CustomCheckBox from "../../common/CustomCheckBox.js";
import ActionPaginationButtonType from "../../common/ActionPaginationButtonType.js";
import { id } from "date-fns/locale";
import { value } from "jsonpath";

let breadcrumbs = [
  { label: "home.label", link: "/" },
  { label: "breadcrums.admin.managment", link: "/MasterListsPage" },
  {
    label: "master.lists.page.test.management",
    link: "/MasterListsPage#testManagementConfigMenu",
  },
  {
    label: "configuration.test.add",
    link: "/MasterListsPage#TestAdd",
  },
];

function TestAdd() {
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  const intl = useIntl();

  const componentMounted = useRef(false);
  const [isLoading, setIsLoading] = useState(false);
  const [lonic, setLonic] = useState("");
  const [testAdd, setTestAdd] = useState({});
  const [labUnitList, setLabUnitList] = useState([]);
  const [selectedLabUnitList, setSelectedLabUnitList] = useState({
    id: "",
    value: "",
  });
  const [panelList, setPanelList] = useState([]);
  const [selectedPanelList, setSelectedPanelList] = useState({
    id: "",
    value: "",
  });
  const [panelListTag, setPanelListTag] = useState([]);
  const [selectedPanelListTag, setSelectedPanelListTag] = useState({
    id: "",
    value: "",
  });
  const [uomList, setUomList] = useState([]);
  const [selectedUomList, setSelectedUomList] = useState({
    id: "",
    value: "",
  });
  const [resultTypeList, setResultTypeList] = useState([]);
  const [selectedResultTypeList, setSelectedResultTypeList] = useState({
    id: "",
    value: "",
  });
  const [sampleTypeList, setSampleTypeList] = useState([]);
  const [selectedSampleTypeList, setSelectedSampleTypeList] = useState([
    {
      id: "",
      value: "",
    },
  ]);
  const [sampleTestTypeToGetTagList, setSampleTestTypeToGetTagList] = useState(
    [],
  );
  const [selectedSampleType, setSelectedSampleType] = useState([{}]);
  const [selectedSampleTypeResp, setSelectedSampleTypeResp] = useState([{}]);
  const [groupedDictionaryList, setGroupedDictionaryList] = useState([]);
  const [dictionaryList, setDictionaryList] = useState([]);
  const [sampleTypeSetupPage, setSampleTypeSetupPage] = useState(true);
  const [rangeSetupPage, setRangeSetupPage] = useState(true);
  const [existingTestSetupPage, setExistingTestSetupPage] = useState(true);
  const [finalSaveConfirmation, setFinalSaveConfirmation] = useState(true);
  const [jsonWad, setJsonWad] = useState(
    // {
    //   testNameEnglish: "aasdf",
    //   testNameFrench: "asdf",
    //   testReportNameEnglish: "aasdf",
    //   testReportNameFrench: "asdf",
    //   testSection: "56",
    //   panels: [{ id: "1" }, { id: "2" }],
    //   uom: "1",
    //   loinc: "asdf",
    //   resultType: "4",
    //   orderable: "Y",
    //   notifyResults: "Y",
    //   inLabOnly: "Y",
    //   antimicrobialResistance: "Y",
    //   active: "Y",
    //   sampleTypes:
    //     '[{"typeId": "31", "tests": [{"id": 301}, {"id": 0}]}, {"typeId": "3", "tests": [{"id": 306}, {"id": 304}, {"id": 308}, {"id": 319}, {"id": 317}, {"id": 311}, {"id": 314}, {"id": 3}, {"id": 32}, {"id": 40}, {"id": 41}, {"id": 56}, {"id": 47}, {"id": 49}, {"id": 51}, {"id": 0}]}]',
    //   lowValid: "-Infinity",
    //   highValid: "Infinity",
    //   lowReportingRange: "-Infinity",
    //   highReportingRange: "Infinity",
    //   lowCritical: "-Infinity",
    //   highCritical: "-Infinity",
    //   significantDigits: "",
    //   resultLimits:
    //     '[{"highAgeRange": "30", "gender": false, "lowNormal": "-Infinity", "highNormal": "Infinity"}, {"highAgeRange": "365", "gender": false, "lowNormal": "-Infinity", "highNormal": "Infinity"}, {"highAgeRange": "1825", "gender": false, "lowNormal": "-Infinity", "highNormal": "Infinity"}, {"highAgeRange": "5110", "gender": false, "lowNormal": "-Infinity", "highNormal": "Infinity"}, {"highAgeRange": "Infinity", "gender": false, "lowNormal": "-Infinity", "highNormal": "Infinity"}]',
    //   dictionary:
    //     '[{"value": "824", "qualified": "N"}, {"value": "826", "qualified": "N"}, {"value": "825", "qualified": "N"}, {"value": "822", "qualified": "N"}, {"value": "829", "qualified": "N"}, {"value": "821", "qualified": "N"}]',
    //   dictionaryReference: "824",
    //   defaultTestResult: "825",
    // },
    {
      testNameEnglish: "",
      testNameFrench: "",
      testReportNameEnglish: "",
      testReportNameFrench: "",
      testSection: "",
      panels: [],
      uom: "",
      loinc: "",
      resultType: "",
      orderable: "Y",
      notifyResults: "",
      inLabOnly: "",
      antimicrobialResistance: "",
      active: "Y",
      sampleTypes: [],
      lowValid: "",
      highValid: "",
      lowReportingRange: "",
      highReportingRange: "",
      lowCritical: "",
      highCritical: "",
      significantDigits: "",
      resultLimits:
        '[{"highAgeRange": "30", "gender": false, "lowNormal": "-Infinity", "highNormal": "Infinity"}, {"highAgeRange": "365", "gender": false, "lowNormal": "-Infinity", "highNormal": "Infinity"}, {"highAgeRange": "1825", "gender": false, "lowNormal": "-Infinity", "highNormal": "Infinity"}, {"highAgeRange": "5110", "gender": false, "lowNormal": "-Infinity", "highNormal": "Infinity"}, {"highAgeRange": "Infinity", "gender": false, "lowNormal": "-Infinity", "highNormal": "Infinity"}]',
    },
  );

  useEffect(() => {
    componentMounted.current = true;
    setIsLoading(true);
    getFromOpenElisServer(`/rest/TestAdd`, (res) => {
      handleTestAddData(res);
    });
    return () => {
      componentMounted.current = false;
      setIsLoading(false);
    };
  }, []);

  function handleTestAddData(res) {
    if (!res) {
      setIsLoading(true);
    } else {
      setTestAdd(res);
    }
  }

  useEffect(() => {
    if (testAdd) {
      setLabUnitList([{ id: "0", value: "" }, ...(testAdd.labUnitList || [])]);
      setPanelList([
        { id: "0", value: "Select Multiple" },
        ...(testAdd.panelList || []),
      ]);
      setUomList([{ id: "0", value: "" }, ...(testAdd.uomList || [])]);
      setResultTypeList([
        { id: "0", value: "" },
        ...(testAdd.resultTypeList || []),
      ]);
      setSampleTypeList([
        { id: "0", value: "Select Multiple" },
        ...(testAdd.sampleTypeList || []),
      ]);
      setGroupedDictionaryList([
        // { id: "0", value: "Select Multiple" },
        ...(testAdd.groupedDictionaryList || []),
      ]);
      setDictionaryList([
        // { id: "0", value: "Select Multiple" },
        ...(testAdd.dictionaryList || []),
      ]);
    }
  }, [testAdd]);

  useEffect(() => {
    getFromOpenElisServer(
      `rest/sample-type-tests?sampleType=${selectedSampleType}`,
      (res) => {
        handleSampleType(res);
      },
    );
  }, [selectedSampleType]);

  function handleSampleType(res) {
    if (!res) {
      setIsLoading(true);
    } else {
      setSelectedSampleTypeResp(res);
    }
  }

  function testNameEn(e) {
    setJsonWad((prev) => ({
      ...prev,
      testNameEnglish: e.target.value,
    }));
  }

  function testNameFr(e) {
    setJsonWad((prev) => ({
      ...prev,
      testNameFrench: e.target.value,
    }));
  }

  function reportingTestNameEn(e) {
    setJsonWad((prev) => ({
      ...prev,
      reportingTestNameEn: e.target.value,
    }));
  }

  function reportingTestNameFr(e) {
    setJsonWad((prev) => ({
      ...prev,
      reportingTestNameFr: e.target.value,
    }));
  }

  function copyInputValuesFromTestNameEnFr() {
    setJsonWad((prev) => ({
      ...prev,
      reportingTestNameEn: prev.testNameEnglish,
      reportingTestNameFr: prev.testNameFrench,
    }));
  }

  function handelTestSectionSelect(e) {
    setJsonWad((prev) => ({
      ...prev,
      testSection: e.target.value,
    }));
  }

  function handelPanelListSelect(e) {
    setJsonWad((prev) => ({
      ...prev,
      panels: [...prev.panels, e.target.value],
    }));
  }

  function handelUomSelect(e) {
    setJsonWad((prev) => ({ ...prev, uom: e.target.value }));
  }

  function handelLonicChange(e) {
    setJsonWad((prev) => ({ ...prev, uom: e.target.value }));
  }

  function handelResultType(e) {
    setJsonWad((prev) => ({ ...prev, resultType: e.target.value }));
  }

  function handleAntimicrobialResistance(e) {
    setJsonWad((prev) => ({
      ...prev,
      antimicrobialResistance: e.target.checked ? "Y" : "N",
    }));
  }
  function handleIsActive(e) {
    setJsonWad((prev) => ({ ...prev, active: e.target.checked ? "Y" : "N" }));
  }
  function handleOrderable(e) {
    setJsonWad((prev) => ({
      ...prev,
      orderable: e.target.checked ? "Y" : "N",
    }));
  }
  function handleNotifyPatientofResults(e) {
    setJsonWad((prev) => ({
      ...prev,
      notifyResults: e.target.checked ? "Y" : "N",
    }));
  }
  function handleInLabOnly(e) {
    setJsonWad((prev) => ({
      ...prev,
      inLabOnly: e.target.checked ? "Y" : "N",
    }));
  }

  function nextButton() {
    setSampleTypeSetupPage(true);
  }

  function finalButton() {
    setRangeSetupPage(true);
  }

  const handelPanelSelectSetTag = (e) => {
    const selectedId = e.target.value;
    const selectedValue = e.target.options[e.target.selectedIndex].text;

    setPanelListTag((prevTags) => {
      const isTagPresent = prevTags.some((tag) => tag.id === selectedId);
      if (isTagPresent) return prevTags;

      const newTag = { id: selectedId, value: selectedValue };
      const updatedTags = [...prevTags, newTag];

      const updatedPanels = [...updatedTags.map((tag) => ({ id: tag.id }))];
      setJsonWad((prevJsonWad) => ({
        ...prevJsonWad,
        panels: updatedPanels,
      }));

      return updatedTags;
    });
  };

  const handlePanelRemoveTag = (idToRemove) => {
    setPanelListTag((prevTags) => {
      const updatedTags = prevTags.filter((tag) => tag.id !== idToRemove);

      const updatedPanels = [...updatedTags.map((tag) => ({ id: tag.id }))];
      setJsonWad((prevJsonWad) => ({
        ...prevJsonWad,
        panels: updatedPanels,
      }));

      return updatedTags;
    });
  };

  // const handleSampleTypeListSelectIdTestTag = (e) => {
  //   const selectedTestId = e.target.value;
  //   const testName = e.target.options[e.target.selectedIndex].text;

  //   const existingIndex = sampleTestTypeToGetTagList.findIndex(
  //     (item) => item.id === selectedTestId,
  //   );

  //   let updatedList;
  //   if (existingIndex !== -1) {
  //     updatedList = [...sampleTestTypeToGetTagList];
  //     updatedList.splice(existingIndex, 1);
  //     setSampleTestTypeToGetTagList(updatedList);
  //   } else {
  //     const selectedTest = {
  //       id: selectedTestId,
  //       name: testName,
  //     };
  //     updatedList = [...sampleTestTypeToGetTagList, selectedTest];
  //     setSampleTestTypeToGetTagList(updatedList);
  //   }

  //   const updatedReplace = updatedList.map((item) => item.id);
  //   setJsonWad((prevJsonWad) => ({
  //     ...prevJsonWad,
  //     replace: updatedReplace,
  //   }));
  // };

  const handleSampleTypeListSelectIdTestTag = (e) => {
    const selectedId = e.target.value;
    const selectedSampleType = sampleTypeList.find(
      (type) => type.id === selectedId,
    );

    if (selectedSampleType) {
      setSelectedSampleTypeList([
        ...selectedSampleTypeList,
        selectedSampleType,
      ]);

      setSampleTestTypeToGetTagList([
        ...sampleTestTypeToGetTagList,
        selectedSampleType,
      ]);
    }
  };

  function handleRemoveSampleTypeListSelectIdTestTag(indexToRemove) {
    setSampleTestTypeToGetTagList((prevTags) => {
      const updatedTags = prevTags.filter(
        (_, index) => index !== indexToRemove,
      );

      const updatedReplace = updatedTags.map((item) => item.id);
      setJsonWad((prevJsonWad) => ({
        ...prevJsonWad,
        replace: updatedReplace,
      }));

      return updatedTags;
    });
  }

  function testAddPostCall() {
    setIsLoading(true);
    postToOpenElisServerJsonResponse(
      `/rest/TestAdd`,
      JSON.stringify(jsonWad),
      (res) => {
        testAddPostCallback(res);
      },
    );
  }

  function testAddPostCallback(res) {
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
        <div className="orderLegendBody">
          <Grid fullWidth={true}>
            <Column lg={16} md={8} sm={4}>
              <Section>
                <Heading>
                  <FormattedMessage id="configuration.test.add" />
                </Heading>
              </Section>
            </Column>
          </Grid>
          <br />
          <hr />
          <br />
          <Grid fullWidth={true}>
            <Column lg={8} md={4} sm={4}>
              <div>
                <>
                  <FormattedMessage id="test.section.label" />
                  <span style={{ color: "red" }}>*</span>
                </>
                <br />
                <Select id={`select-test-section`} hideLabel required>
                  {labUnitList?.map((test) => (
                    <SelectItem
                      key={test.id}
                      value={test.id}
                      text={`${test.value}`}
                    />
                  ))}
                </Select>
              </div>
              <br />
              <div>
                <>
                  <FormattedMessage id="sample.entry.project.testName" />
                  <span style={{ color: "red" }}>*</span>
                </>
                <br />
                <br />
                <FormattedMessage id="english.label" />
                <br />
                <TextInput
                  labelText=""
                  id="testNameEn"
                  value={jsonWad?.testNameEnglish}
                  onChange={testNameEn}
                  required
                />
                <br />
                <FormattedMessage id="french.label" />
                <br />
                <TextInput
                  labelText=""
                  id="testNameFr"
                  value={jsonWad?.testNameFrench}
                  onChange={testNameFr}
                  required
                />
              </div>
              <br />
              <div>
                <>
                  <FormattedMessage id="reporting.label.testName" />
                  <span style={{ color: "red" }}>*</span>
                </>
                <br />
                <br />
                <Button
                  kind="tertiary"
                  onClick={copyInputValuesFromTestNameEnFr}
                  type="button"
                >
                  <FormattedMessage id="test.add.copy.name" />
                </Button>
                <br />
                <br />
                <FormattedMessage id="english.label" />
                <br />
                <TextInput
                  labelText=""
                  id="reportingTestNameEn"
                  value={jsonWad?.reportingTestNameEn}
                  required
                  onChange={reportingTestNameEn}
                />
                <br />
                <FormattedMessage id="french.label" />
                <br />
                <TextInput
                  labelText=""
                  id="reportingTestNameFr"
                  value={jsonWad?.reportingTestNameFr}
                  required
                  onChange={reportingTestNameFr}
                />
              </div>
            </Column>
            <Column lg={4} md={4} sm={4}>
              <FormattedMessage id="field.panel" />
              <Select
                id={`select-panel`}
                onChange={(e) => {
                  handelPanelSelectSetTag(e);
                }}
                hideLabel
                required
              >
                {panelList?.map((test) => (
                  <SelectItem
                    key={test.id}
                    value={test.id}
                    text={`${test.value}`}
                  />
                ))}
              </Select>
              <div
                className={"select-panel"}
                style={{ marginBottom: "1.188rem" }}
              >
                {panelListTag && panelListTag.length ? (
                  <>
                    {panelListTag.map((panel) => (
                      <Tag
                        filter
                        key={`panelTags_${panel.id}`}
                        onClose={() => handlePanelRemoveTag(panel.id)}
                        style={{ marginRight: "0.5rem" }}
                        type={"red"}
                      >
                        {panel.value}
                      </Tag>
                    ))}
                  </>
                ) : (
                  <></>
                )}
              </div>
              <br />
              <FormattedMessage id="field.uom" />
              <Select
                // onChange={}
                id={`select-uom`}
                hideLabel
                required
              >
                {uomList?.map((test) => (
                  <SelectItem
                    key={test.id}
                    value={test.id}
                    text={`${test.value}`}
                  />
                ))}
              </Select>
            </Column>
            <Column lg={4} md={4} sm={4}>
              <div>
                <>
                  <FormattedMessage id="field.resultType" />
                  <span style={{ color: "red" }}>*</span>
                </>
                <br />
                <Select id={`select-result-type`} hideLabel required>
                  {resultTypeList?.map((test) => (
                    <SelectItem
                      key={test.id}
                      value={test.id}
                      text={`${test.value}`}
                    />
                  ))}
                </Select>
              </div>
              <br />
              <div>
                <FormattedMessage id="label.loinc" />
                <br />
                <TextInput
                  labelText=""
                  required
                  id="loinc"
                  onChange={handelLonicChange}
                />
              </div>
              <br />
              <div>
                <Checkbox
                  labelText={
                    <FormattedMessage id="test.antimicrobialResistance" />
                  }
                  id="antimicrobial-resistance"
                  onChange={handleAntimicrobialResistance}
                  checked={jsonWad?.antimicrobialResistance === "Y"}
                />
                <Checkbox
                  labelText={
                    <FormattedMessage id="dictionary.category.isActive" />
                  }
                  id="is-active"
                  onChange={handleIsActive}
                  checked={jsonWad?.active === "Y"}
                />
                <Checkbox
                  labelText={<FormattedMessage id="label.orderable" />}
                  id="orderable"
                  onChange={handleOrderable}
                  checked={jsonWad?.orderable === "Y"}
                />
                <Checkbox
                  labelText={<FormattedMessage id="test.notifyResults" />}
                  id="notify-patient-of-results"
                  onChange={handleNotifyPatientofResults}
                  checked={jsonWad?.notifyResults === "Y"}
                />
                <Checkbox
                  labelText={<FormattedMessage id="test.inLabOnly" />}
                  id="in-lab-only"
                  onChange={handleInLabOnly}
                  checked={jsonWad?.inLabOnly === "Y"}
                />
              </div>
            </Column>
            <br />
            <br />
            <Column lg={16} md={8} sm={4}>
              <Button onClick={nextButton} type="button">
                <FormattedMessage id="next.action.button" />
              </Button>{" "}
              <Button
                onClick={() => {
                  window.location.assign(
                    "/MasterListsPage#testManagementConfigMenu",
                  );
                }}
                kind="tertiary"
                type="button"
              >
                <FormattedMessage id="back.action.button" />
              </Button>
            </Column>
          </Grid>
          <br />
          <hr />
          <br />
          {sampleTypeSetupPage ? (
            <>
              <Grid fullWidth={true}>
                <Column lg={6} md={8} sm={4}>
                  <FormattedMessage id="sample.type" />
                  <br />
                  <Select
                    id={`select-sample-type`}
                    hideLabel
                    required
                    onChange={(e) => handleSampleTypeListSelectIdTestTag(e)}
                  >
                    {sampleTypeList?.map((test) => (
                      <SelectItem
                        key={test.id}
                        value={test.id}
                        text={`${test.value}`}
                      />
                    ))}
                  </Select>
                  <br />
                  <div
                    className={"select-sample-type"}
                    style={{ marginBottom: "1.188rem" }}
                  >
                    {sampleTestTypeToGetTagList &&
                    sampleTestTypeToGetTagList.length ? (
                      <>
                        {sampleTestTypeToGetTagList.map((section, index) => (
                          <Tag
                            filter
                            key={`testTags_${index}`}
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
                <Column lg={10} md={8} sm={4}>
                  <Section>
                    <Section>
                      <Section>
                        <Heading>
                          <FormattedMessage id="label.test.display.order" />
                        </Heading>
                      </Section>
                    </Section>
                  </Section>
                  <br />
                  {/* map the selectedSampleTypeResp[0].value */}
                </Column>
                <br />
                <br />
                <Column lg={16} md={8} sm={4}>
                  <Button onClick={finalButton} type="button">
                    <FormattedMessage id="next.action.button" />
                  </Button>{" "}
                  <Button
                    onClick={() => {
                      window.location.reload();
                    }}
                    kind="tertiary"
                    type="button"
                  >
                    <FormattedMessage id="label.button.cancel" />
                  </Button>
                </Column>
              </Grid>
              <br />
              <hr />
              <br />
            </>
          ) : (
            <></>
          )}
          {rangeSetupPage ? (
            <>
              <Grid fullWidth={true}>
                <Column lg={16} md={8} sm={4}>
                  <Section>
                    <Section>
                      <Section>
                        <Heading>
                          <FormattedMessage id="label.button.range" />
                        </Heading>
                      </Section>
                    </Section>
                  </Section>
                </Column>
              </Grid>
              <br />
              <hr />
              <br />
              <Grid fullWidth={true} className="gridBoundary">
                <Column lg={16} md={8} sm={4}>
                  <FormattedMessage id="field.ageRange" />
                  <hr />
                </Column>
                <Column lg={8} md={4} sm={4}>
                  <FormattedMessage id="field.normalRange" />
                  <hr />
                  <div style={{ display: "flex", gap: "4px" }}>
                    <TextInput
                      id="field.normalRange0"
                      labelText=""
                      hideLabel
                      required
                    />
                    <TextInput
                      id="field.normalRange1"
                      labelText=""
                      hideLabel
                      required
                    />
                  </div>
                </Column>
                <Column lg={8} md={4} sm={4}>
                  <FormattedMessage id="label.reporting.range" />
                  <hr />
                  <div style={{ display: "flex", gap: "4px" }}>
                    <TextInput
                      id="label.reporting.range0"
                      labelText=""
                      hideLabel
                      required
                    />
                    <TextInput
                      id="label.reporting.range1"
                      labelText=""
                      hideLabel
                      required
                    />
                  </div>
                </Column>
                <Column lg={8} md={4} sm={4}>
                  <FormattedMessage id="field.validRange" />
                  <hr />
                  <div style={{ display: "flex", gap: "4px" }}>
                    <TextInput
                      id="field.validRange0"
                      labelText=""
                      hideLabel
                      required
                    />
                    <TextInput
                      id="field.validRange1"
                      labelText=""
                      hideLabel
                      required
                    />
                  </div>
                </Column>
                <Column lg={8} md={4} sm={4}>
                  <FormattedMessage id="label.critical.range" />
                  <hr />
                  <div style={{ display: "flex", gap: "4px" }}>
                    <TextInput
                      id="label.critical.range0"
                      labelText=""
                      hideLabel
                      required
                    />
                    <TextInput
                      id="label.critical.range1"
                      labelText=""
                      hideLabel
                      required
                    />
                  </div>
                </Column>
              </Grid>
              <br />
              <hr />
              <br />
            </>
          ) : (
            <></>
          )}
          {existingTestSetupPage ? (
            <>
              <Grid fullWidth={true}>
                <Column lg={16} md={8} sm={4}>
                  <Section>
                    <Section>
                      <Section>
                        <Heading>
                          <FormattedMessage id="label.existing.test.sets" />
                        </Heading>
                      </Section>
                    </Section>
                  </Section>
                  <br />
                  <hr />
                  <br />
                </Column>
                <Column lg={16} md={8} sm={4}>
                
                </Column>
              </Grid>
            </>
          ) : (
            <></>
          )}
          {finalSaveConfirmation ? (
            <>
              <Grid fullWidth={true}>
                <Column lg={6} md={8} sm={4}>
                  <FormattedMessage id="sample.entry.project.testName" />
                  <br />
                  <FormattedMessage id="english.label" />
                  {" : "}
                  {jsonWad?.testNameEnglish}
                  <br />
                  <FormattedMessage id="french.label" />
                  {" : "}
                  {jsonWad?.testNameFrench}
                  <br />
                  <FormattedMessage id="reporting.label.testName" />
                  <br />
                  <FormattedMessage id="english.label" />
                  {" : "}
                  {jsonWad?.reportingTestNameEn}
                  <br />
                  <FormattedMessage id="french.label" />
                  {" : "}
                  {jsonWad?.reportingTestNameFr}
                  <br />
                  <FormattedMessage id="test.section.label" />
                  {" : "}
                  {selectedLabUnitList?.value}
                  <br />
                  <FormattedMessage id="field.panel" />
                  {" : "}
                  {/* map the  {panelList[0].value} in and there values in line*/}
                  {panelListTag.length > 0 ? (
                    <UnorderedList>
                      {panelListTag.map((tag) => (
                        <div
                          key={tag.id}
                          //  style={{ marginRight: "0.5rem" }}
                        >
                          <ListItem>{tag.value}</ListItem>
                        </div>
                      ))}
                    </UnorderedList>
                  ) : (
                    <></>
                  )}
                  <br />
                  <FormattedMessage id="field.uom" />
                  {" : "}
                  {selectedUomList?.value}
                  <br />
                  <FormattedMessage id="label.loinc" />
                  {" : "}
                  {jsonWad?.loinc}
                  <br />
                  <FormattedMessage id="field.resultType" />
                  {" : "}
                  {selectedResultTypeList.value}
                  <br />
                  <FormattedMessage id="test.antimicrobialResistance" />
                  {" : "}
                  {jsonWad?.antimicrobialResistance}
                  <br />
                  <FormattedMessage id="dictionary.category.isActive" />
                  {" : "}
                  {jsonWad?.active}
                  <br />
                  <FormattedMessage id="label.orderable" />
                  {" : "}
                  {jsonWad?.orderable}
                  <br />
                  <FormattedMessage id="test.notifyResults" />
                  {" : "}
                  {jsonWad?.notifyResults}
                  <br />
                  <FormattedMessage id="test.inLabOnly" />
                  {" : "}
                  {jsonWad?.inLabOnly}
                </Column>
                <Column lg={10} md={8} sm={4}>
                  <FormattedMessage id="sample.type.and.test.sort.order" />
                  {/* Mapp the combbination of the selecte[sampleType] & tests of [sampleType] in sorted order */}
                  <br />
                  {selectedSampleTypeList.length > 0 ? (
                    <UnorderedList>
                      {selectedSampleTypeList.map((type, index) => (
                        <div key={`selectedSampleType_${index}`}>
                          <ListItem>{type.value}</ListItem>
                        </div>
                      ))}
                    </UnorderedList>
                  ) : (
                    <></>
                  )}
                </Column>
              </Grid>
              <br />
              <hr />
              <br />
            </>
          ) : (
            <></>
          )}
          <Grid fullWidth={true}>
            <Column lg={16} md={8} sm={4}>
              <Button
                disabled={!finalSaveConfirmation}
                onClick={() => {
                  setJsonWad(JSON.stringify(jsonWad));
                  testAddPostCall();
                }}
                type="button"
              >
                <FormattedMessage id="label.button.submit" />
              </Button>{" "}
              <Button
                onClick={() =>
                  window.location.assign(
                    "/MasterListsPage#testManagementConfigMenu",
                  )
                }
                kind="tertiary"
                type="button"
              >
                <FormattedMessage id="label.button.cancel" />
              </Button>
            </Column>
          </Grid>
          <button
            onClick={() => {
              console.log(testAdd);
            }}
          >
            testAdd
          </button>
          <button
            onClick={() => {
              console.log(jsonWad);
            }}
          >
            jsonWad
          </button>
        </div>
      </div>
    </>
  );
}

export default injectIntl(TestAdd);
