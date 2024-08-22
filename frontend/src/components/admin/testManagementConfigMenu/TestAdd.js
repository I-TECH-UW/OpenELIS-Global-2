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
  const [selectedSampleType, setSelectedSampleType] = useState([{}]);
  const [selectedSampleTypeResp, setSelectedSampleTypeResp] = useState([{}]);
  const [sampleTypeConfirmationPage, setSampleTypeConfirmationPage] =
    useState(true);
  const [rangeSetupPage, setRangeSetupPage] = useState(true);
  const [jsonWad, setJsonWad] = useState(
    // {
    //   testNameEnglish: "aasdf",
    //   testNameFrench: "asdf",
    //   testReportNameEnglish: "aasdf",
    //   testReportNameFrench: "asdf",
    //   testSection: "56",
    //   panels: "[]",
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
      orderable: "",
      notifyResults: "",
      inLabOnly: "",
      antimicrobialResistance: "",
      active: "",
      sampleTypes: [],
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
    setJsonWad((prev) => ({ ...prev, antimicrobialResistance: "" }));
  }
  function handleIsActive(e) {
    setJsonWad((prev) => ({ ...prev, active: "" }));
  }
  function handleOrderable(e) {
    setJsonWad((prev) => ({ ...prev, orderable: "" }));
  }
  function handleNotifyPatientofResults(e) {
    setJsonWad((prev) => ({ ...prev, notifyResults: "" }));
  }
  function handleInLabOnly(e) {
    setJsonWad((prev) => ({ ...prev, inLabOnly: "" }));
  }

  function nextButton() {
    setSampleTypeConfirmationPage(true);
  }

  function finalButton() {
    setRangeSetupPage(true);
  }

  // const handelPanelSelectSetTag = (e) => {
  //   setPanelListTag((prev)=>([..prev,
  //     index : e.target.value
  //   ]))
  // }

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
                  id="testNameEn"
                  value={jsonWad?.testNameEnglish}
                  onChange={testNameEn}
                  required
                />
                <br />
                <FormattedMessage id="french.label" />
                <br />
                <TextInput
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
                  id="reportingTestNameEn"
                  value={jsonWad?.reportingTestNameEn}
                  required
                  onChange={reportingTestNameEn}
                />
                <br />
                <FormattedMessage id="french.label" />
                <br />
                <TextInput
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
                onClick={() => {
                  handelPanelSelectSetTag;
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
              {/* <div
                className={"select-panel"}
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
              </div> */}
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
                <TextInput required id="loinc" onChange={handelLonicChange} />
              </div>
              <br />
              <div>
                <Checkbox
                  labelText={
                    <FormattedMessage id="test.antimicrobialResistance" />
                  }
                  id="antimicrobial-resistance"
                />
                <Checkbox
                  labelText={
                    <FormattedMessage id="dictionary.category.isActive" />
                  }
                  id="is-active"
                />
                <Checkbox
                  labelText={<FormattedMessage id="label.orderable" />}
                  id="orderable"
                />
                <Checkbox
                  labelText={<FormattedMessage id="test.notifyResults" />}
                  id="notify-patient-of-results"
                />
                <Checkbox
                  labelText={<FormattedMessage id="test.inLabOnly" />}
                  id="in-lab-only"
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
          {sampleTypeConfirmationPage ? (
            <>
              <Grid fullWidth={true}>
                <Column lg={4} md={8} sm={4}>
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
                  {selectedLabUnitList?.value}
                  <br />
                  <FormattedMessage id="field.panel" />
                  {/* map the  {panelList[0].value} in and there values in line*/}
                  <br />
                  <FormattedMessage id="field.uom" />
                  {selectedUomList?.value}
                  <br />
                  <FormattedMessage id="label.loinc" />
                  {jsonWad?.loinc}
                  <br />
                  <FormattedMessage id="field.resultType" />
                  {selectedResultTypeList.value}
                  <br />
                  <FormattedMessage id="test.antimicrobialResistance" />
                  {jsonWad?.antimicrobialResistance}
                  <br />
                  <FormattedMessage id="dictionary.category.isActive" />
                  {jsonWad?.active}
                  <br />
                  <FormattedMessage id="label.orderable" />
                  {jsonWad?.orderable}
                  <br />
                  <FormattedMessage id="test.notifyResults" />
                  {jsonWad?.notifyResults}
                  <br />
                  <FormattedMessage id="test.inLabOnly" />
                  {jsonWad?.inLabOnly}
                </Column>
                <Column lg={4} md={8} sm={4}>
                  <FormattedMessage id="sample.type" />
                  <br />
                  <Select id={`select-sample-type`} hideLabel required>
                    {sampleTypeList?.map((test) => (
                      <SelectItem
                        key={test.id}
                        value={test.id}
                        text={`${test.value}`}
                      />
                    ))}
                  </Select>
                  <br />
                  {/* <div
                    className={"select-sample-type"}
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
                  </div> */}
                </Column>
                <Column lg={8} md={8} sm={4}>
                  <Section>
                    <Section>
                      <Heading>
                        <FormattedMessage id="label.test.display.order" />
                      </Heading>
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
            </>
          ) : (
            <></>
          )}
          <br />
          <hr />
          <br />
          {rangeSetupPage ? (
            <>
              <Grid fullWidth={true}>
                <Column lg={16} md={8} sm={4}></Column>
              </Grid>
            </>
          ) : (
            <></>
          )}
          <br />
          <button
            onClick={() => {
              console.log(testAdd);
            }}
          >
            testAdd
          </button>
        </div>
      </div>
    </>
  );
}

export default injectIntl(TestAdd);
