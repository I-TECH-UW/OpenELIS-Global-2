import React, { useContext, useState, useEffect, useRef } from "react";
import {
  Form,
  Stack,
  TextInput,
  TextArea,
  Select,
  SelectItem,
  Button,
  Checkbox,
  IconButton,
  Toggle,
  Loading,
  RadioButtonGroup,
  RadioButton,
  ModalWrapper,
} from "@carbon/react";
import { Add, Subtract } from "@carbon/react/icons";
import AutoComplete from "../../common/AutoComplete.js";
import RuleBuilderFormValues from "../../formModel/innitialValues/RuleBuilderFormValues";
import { getFromOpenElisServer, postToOpenElisServer } from "../../utils/Utils";
import { NotificationContext } from "../../layout/Layout";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification";
import { FormattedMessage } from "react-intl";
import "./ReflexStyles.css";

var defaultTestResultList = {};
var defaultSampleTests = { conditions: {}, actions: {} };

function ReflexRule() {
  const componentMounted = useRef(true);
  const FIELD = {
    conditions: "conditions",
    actions: "actions",
  };
  const conditionsObj = {
    id: null,
    sampleId: "",
    testName: "",
    testId: "",
    relation: "",
    value: "0",
    value2: "0",
    testAnalyteId: null,
  };
  const actionObj = {
    id: null,
    sampleId: "",
    reflexTestName: "",
    reflexTestId: "",
    internalNote: "",
    externalNote: "",
    addNotification: "Y",
    testReflexId: null,
  };

  const ruleObj = {
    id: null,
    ruleName: "",
    overall: "",
    toggled: true,
    active: true,
    analyteId: null,
    conditions: [conditionsObj],
    actions: [actionObj],
  };

  const [ruleList, setRuleList] = useState([RuleBuilderFormValues]);
  const [sampleList, setSampleList] = useState([]);
  const [generalRelationOptions, setGeneralRelationOptions] = useState([]);
  const [numericRelationOptions, setNumericRelationOptions] = useState([]);
  const [overallOptions, setOverallOptions] = useState([]);
  const [testResultList, setTestResultList] = useState({
    0: { 0: { type: "N", list: [] } },
  }); //{index :{field_index:{type : "T" ,list : []}}}
  const [sampleTestList, setSampleTestList] = useState({
    conditions: {},
    actions: {},
  }); //{field :{index :{field_index:[]}}}
  const [counter, setCounter] = useState(0);
  const [loading, setLoading] = useState(true);
  const [errors, setErrors] = useState({});
  const { notificationVisible, setNotificationVisible, setNotificationBody } =
    useContext(NotificationContext);

  useEffect(() => {
    getFromOpenElisServer("/rest/samples", fetchSamples);
    getFromOpenElisServer("/rest/reflexrule-options", fetchRuleOptions);
    getFromOpenElisServer("/rest/reflexrules", fetchReflexRules);

    return () => {
      // This code runs when component is unmounted
      componentMounted.current = false;
    };
  }, []);

  useEffect(() => {
    if (loading) {
      loadDefaultTestResultList();
    }
  }, [ruleList]);

  const loadDefaultTestResultList = () => {
    ruleList.forEach(function (rule, index) {
      if (rule.conditions) {
        rule.conditions.forEach(function (condition, conditionIndex) {
          if (condition.sampleId) {
            getFromOpenElisServer(
              "/rest/test-display-beans?sampleType=" + condition.sampleId,
              (resp) =>
                fetchDeafultTests(
                  resp,
                  index,
                  conditionIndex,
                  FIELD.conditions,
                  condition,
                ),
            );
          }
        });
      }
      if (rule.actions) {
        rule.actions.forEach(function (action, actionIndex) {
          if (action.sampleId) {
            getFromOpenElisServer(
              "/rest/test-display-beans?sampleType=" + action.sampleId,
              (resp) =>
                fetchDeafultTests(
                  resp,
                  index,
                  actionIndex,
                  FIELD.actions,
                  null,
                ),
            );
          }
        });
      }
    });
  };

  const addError = (errorObj) => {
    const error = { ...errors };
    error[errorObj.name] = errorObj.error;
    setErrors(error);
  };

  const clearError = (field) => {
    const error = { ...errors };
    delete error[field];
    setErrors(error);
  };

  const fetchDeafultTests = (testList, index, item_index, field, condition) => {
    loadDeafultSampleTestList(field, index, item_index, testList);

    if (field == FIELD.conditions) {
      if (condition.value) {
        const test = defaultSampleTests.conditions[index][item_index].find(
          (test) => {
            if (test.id.trim() === condition.testId) {
              return true;
            }
          },
        );

        if (test) {
          loadDefaultResultList(index, item_index, test);
        }
      }
    }

    if (
      Object.keys(defaultSampleTests["conditions"]).length == ruleList.length
    ) {
      setLoading(false);
    }
  };

  const loadDeafultSampleTestList = (field, index, item_index, resulList) => {
    if (!defaultSampleTests[field][index]) {
      defaultSampleTests[field][index] = {};
    }
    defaultSampleTests[field][index][item_index] = resulList;
  };

  const loadDefaultResultList = (index, item_index, test) => {
    if (!defaultTestResultList[index]) {
      defaultTestResultList[index] = {};
    }
    if (!defaultTestResultList[index][item_index]) {
      defaultTestResultList[index][item_index] = {};
    }

    defaultTestResultList[index][item_index]["list"] = test.resultList;
    defaultTestResultList[index][item_index]["type"] = test.resultType;
  };

  const handleRuleFieldChange = (e, index) => {
    const { name, value } = e.target;
    const list = [...ruleList];
    list[index][name] = value;
    setRuleList(list);
  };

  const handleRuleFieldItemChange = (e, index, itemIndex, field) => {
    const { name, value } = e.target;
    const list = [...ruleList];
    list[index][field][itemIndex][name] = value;
    setRuleList(list);
  };

  const handleAutoCompleteRuleFieldItemChange = (
    value,
    name,
    index,
    itemIndex,
    field,
  ) => {
    const list = [...ruleList];
    list[index][field][itemIndex][name] = value;
    setRuleList(list);
  };

  const handleAddNotificationChange = (value, index, itemIndex, field) => {
    const e = { target: { name: "addNotification", value: value } };
    handleRuleFieldItemChange(e, index, itemIndex, field);
  };

  const handleTestSelected = (id, index, item_index, field) => {
    var testDetails = {resultList : [] ,resultType :"N"};
    if (sampleTestList[field]) {
      testDetails = sampleTestList[field][index][item_index].find(
        (test) => test.id == id)
      }
    const results = { ...testResultList };
    if (!results[index]) {
      results[index] = {};
    }
    if (!results[index][item_index]) {
      results[index][item_index] = {};
    }
    results[index][item_index]["list"] = testDetails.resultList;
    results[index][item_index]["type"] = testDetails.resultType;
    setTestResultList(results);
  };

  const loadSampleTestList = (field, index, item_index, resulList) => {
    const results = { ...sampleTestList };
    if (!results[field][index]) {
      results[field][index] = {};
    }
    results[field][index][item_index] = resulList;
    setSampleTestList(results);
  };

  const handleSampleSelected = (e, index, item_index, field) => {
    const { value } = e.target;
    getFromOpenElisServer(
      "/rest/test-display-beans?sampleType=" + value,
      (resp) => fetchTests(resp, index, item_index, field),
    );
  };


  const handleRuleRemove = (index, id) => {
    const list = [...ruleList];
    list.splice(index, 1);
    setRuleList(list);
    if (id) {
      postToOpenElisServer(
        "/rest/deactivate-reflexrule/" + id,
        {},
        handleDelete,
      );
    }
  };

  const handleDelete = (status) => {
    setNotificationVisible(true);
    if (status == "200") {
      setNotificationBody({
        kind: NotificationKinds.success,
        title: <FormattedMessage id="notification.title" />,
        message: "Succesfuly Deleted",
      });
    } else {
      setNotificationBody({
        kind: NotificationKinds.error,
        title: <FormattedMessage id="notification.title" />,
        message: "Error while Deleting",
      });
    }
  };

  const handleRuleAdd = () => {
    setRuleList([...ruleList, ruleObj]);
  };

  const toggleRule = (e, index) => {
    const list = [...ruleList];
    list[index]["toggled"] = e;
    setRuleList(list);
  };

  const handleRuleFieldItemAdd = (index, field, fieldObj) => {
    const list = [...ruleList];
    list[index][field].push(fieldObj);
    setRuleList(list);
  };

  const handleRuleFieldItemRemove = (index, itemIndex, field) => {
    const list = [...ruleList];
    list[index][field].splice(itemIndex, 1);
    setRuleList(list);
  };

  const handleSubmited = (status, index) => {
    setNotificationVisible(true);
    if (status == "200") {
      const element = document.getElementById("submit_" + index);
      element.disabled = true;
      setNotificationBody({
        kind: NotificationKinds.success,
        title: <FormattedMessage id="notification.title" />,
        message: "Succesfuly saved",
      });
    } else {
      setNotificationBody({
        kind: NotificationKinds.error,
        title: <FormattedMessage id="notification.title" />,
        message: "Duplicate Calculation Name or Error while saving",
      });
    }
  };

  const handleSubmit = (event, index) => {
    event.preventDefault();
    console.log(JSON.stringify(ruleList[index]));
    postToOpenElisServer(
      "/rest/reflexrule",
      JSON.stringify(ruleList[index]),
      (status) => handleSubmited(status, index),
    );
  };

  const fetchTests = (testList, index, item_index, field) => {
    loadSampleTestList(field, index, item_index, testList);
  };

  const fetchSamples = (sampleList) => {
    if (componentMounted.current) {
      setSampleList(sampleList);
    }
  };

  const fetchReflexRules = (reflexRuleList) => {
    if (componentMounted.current) {
      // console.log(JSON.stringify(reflexRuleList))
      if (reflexRuleList.length > 0) {
        setRuleList(reflexRuleList);
      } else {
        setLoading(false);
      }
    }
  };

  const fetchRuleOptions = (options) => {
    if (componentMounted.current) {
      console.log(JSON.stringify(options));
      if (options) {
        setGeneralRelationOptions(options.generalRelationOptions);
        setNumericRelationOptions(options.numericRelationOptions);
        setOverallOptions(options.overallOptions);
      }
    }
  };

  const handleClick = () => {
    var count = counter + 1;
    if (count == 1) {
      setTestResultList(defaultTestResultList);
      setSampleTestList(defaultSampleTests);
    }
    setCounter(count);
  };

  const validateTextInPut = (value, type) => {
    if (type === "N") {
      if (value.match(/^-?\d+$/)) {
        //valid integer (positive or negative)
        return false;
      } else if (value.match(/^\d+\.\d+$/)) {
        //valid float
        return false;
      } else {
        console.log("invalid value");
        return true;
      }
    }
  };

  const addTextInPutError = (value, type, fieldName) => {
    if (type === "N") {
      if (value.match(/^-?\d+$/)) {
        //valid integer (positive or negative)
        clearError(fieldName);
      } else if (value.match(/^\d+\.\d+$/)) {
        //valid float
        clearError(fieldName);
      } else {
        console.log("invalid value");
        addError({ name: fieldName, error: "Invaid Numeric Value" });
      }
    }
  };

  const normalRangeSelected = (relation) => {
    if (
      relation === "OUTSIDE_NORMAL_RANGE" ||
      relation === "INSIDE_NORMAL_RANGE"
    ) {
      return true;
    }
    return false;
  };

  return (
    <>
      {notificationVisible === true ? <AlertDialog /> : ""}
      {loading && <Loading></Loading>}
      {ruleList.map((rule, index) => (
        <div key={index} className="rules">
          <div className="first-division">
            <Form onSubmit={(e) => handleSubmit(e, index)}>
              <Stack gap={7}>
                <div className="ruleBody">
                  <div className="inlineDiv">
                    <div>
                      <TextInput
                        name="ruleName"
                        className="reflexInputText"
                        type="text"
                        id={index + "_rulename"}
                        labelText={
                          <FormattedMessage id="rulebuilder.label.ruleName" />
                        }
                        value={rule.ruleName}
                        onChange={(e) => handleRuleFieldChange(e, index)}
                        required
                      />
                    </div>
                    <div>&nbsp; &nbsp;</div>
                    <div>
                      <Toggle
                        toggled={rule.toggled}
                        aria-label="toggle button"
                        id={index + "_toggle"}
                        labelText={
                          <FormattedMessage id="rulebuilder.label.toggleRule" />
                        }
                        onToggle={(e) => toggleRule(e, index)}
                        onClick={handleClick}
                      />
                    </div>
                    <div>&nbsp; &nbsp; &nbsp; &nbsp;</div>
                    <div>
                      <Checkbox
                        labelText={"Active: " + rule.active}
                        name="active"
                        id={index + "_active"}
                        checked={rule.active}
                        disabled={rule.active}
                        onChange={(e) => {
                          const list = [...ruleList];
                          list[index]["active"] = e.target.checked;
                          setRuleList(list);
                        }}
                      />
                    </div>
                  </div>
                  {rule.toggled && (
                    <>
                      <div className="section">
                        <div className="inlineDiv">
                          <div>
                            <h5>
                              <FormattedMessage id="rulebuilder.label.addRuleConditions" />
                            </h5>
                          </div>
                        </div>
                        <div className="inlineDiv">
                          <div>
                            <Select
                              value={rule.overall}
                              id={index + "_overall"}
                              name="overall"
                              labelText={
                                <FormattedMessage id="rulebuilder.label.overallOptions" />
                              }
                              className="reflexInputSelect"
                              onChange={(e) => handleRuleFieldChange(e, index)}
                              required
                            >
                              <SelectItem text="" value="" />
                              {overallOptions.map((overall, overall_index) => (
                                <SelectItem
                                  text={overall.label}
                                  value={overall.value}
                                  key={overall_index}
                                />
                              ))}
                            </Select>
                          </div>
                        </div>
                        {rule.conditions.map((condition, condition_index) => (
                          <div
                            key={index + "_" + condition_index}
                            className="inlineDiv"
                          >
                            <div>
                              <Select
                                id={index + "_" + condition_index + "_sample"}
                                name="sampleId"
                                labelText={
                                  <FormattedMessage id="rulebuilder.label.selectSample" />
                                }
                                value={condition.sampleId}
                                className="reflexInputSelect"
                                onChange={(e) => {
                                  handleRuleFieldItemChange(
                                    e,
                                    index,
                                    condition_index,
                                    FIELD.conditions,
                                  );
                                  handleSampleSelected(
                                    e,
                                    index,
                                    condition_index,
                                    FIELD.conditions,
                                  );
                                }}
                                required
                              >
                                <SelectItem text="" value="" />
                                {sampleList.map((sample, sample_index) => (
                                  <SelectItem
                                    text={sample.value}
                                    value={sample.id}
                                    key={sample_index}
                                  />
                                ))}
                              </Select>
                            </div>

                            <div>
                              <AutoComplete
                                id={
                                  index +
                                  "_" +
                                  condition_index +
                                  "_conditionTestId"
                              }
                               value={condition.testId}
                                onSelect={(id) => {
                                  handleAutoCompleteRuleFieldItemChange(
                                    id,
                                    "testId",
                                    index,
                                    condition_index,
                                    FIELD.conditions,
                                  );
                                  handleTestSelected(
                                    id,
                                    index,
                                    condition_index,
                                    FIELD.conditions,
                                  );
                                }}
                                name="testName"
                                label={
                                  <FormattedMessage id="rulebuilder.label.searchTest" />
                                }
                                class="autocomplete"
                                suggestions={
                                  sampleTestList[FIELD.conditions][index]
                                    ? sampleTestList[FIELD.conditions][index][
                                        condition_index
                                      ]
                                    : []
                                }
                                required
                              />
                            </div>
                            <div>&nbsp; &nbsp;</div>
                            <div>
                              <Select
                                value={condition.relation}
                                id={index + "_" + condition_index + "_relation"}
                                name="relation"
                                labelText={
                                  <FormattedMessage id="rulebuilder.label.relation" />
                                }
                                className="reflexInputSelect"
                                onChange={(e) =>
                                  handleRuleFieldItemChange(
                                    e,
                                    index,
                                    condition_index,
                                    FIELD.conditions,
                                  )
                                }
                                required
                              >
                                <SelectItem text="" value="" />
                                {testResultList[index] &&
                                  testResultList[index][condition_index] &&
                                  testResultList[index][condition_index][
                                    "type"
                                  ] && (
                                    <>
                                      {testResultList[index][condition_index][
                                        "type"
                                      ] === "N" ? (
                                        <>
                                          {numericRelationOptions.map(
                                            (relation, relation_index) => (
                                              <SelectItem
                                                text={relation.label}
                                                value={relation.value}
                                                key={relation_index}
                                              />
                                            ),
                                          )}
                                        </>
                                      ) : (
                                        <>
                                          {generalRelationOptions.map(
                                            (relation, relation_index) => (
                                              <SelectItem
                                                text={relation.label}
                                                value={relation.value}
                                                key={relation_index}
                                              />
                                            ),
                                          )}
                                        </>
                                      )}
                                    </>
                                  )}
                              </Select>
                            </div>
                            <div>&nbsp; &nbsp;</div>
                            <div>
                              {testResultList[index] &&
                              testResultList[index][condition_index] &&
                              testResultList[index][condition_index]["type"] ? (
                                <>
                                  {testResultList[index][condition_index][
                                    "type"
                                  ] === "D" ? (
                                    <Select
                                      value={condition.value}
                                      id={
                                        index + "_" + condition_index + "_value"
                                      }
                                      name="value"
                                      labelText={
                                        <FormattedMessage id="rulebuilder.label.dictValue" />
                                      }
                                      className="reflexInputSelect"
                                      onChange={(e) =>
                                        handleRuleFieldItemChange(
                                          e,
                                          index,
                                          condition_index,
                                          FIELD.conditions,
                                        )
                                      }
                                      disabled={normalRangeSelected(
                                        condition.relation,
                                      )}
                                      required
                                    >
                                      <SelectItem text="" value="" />
                                      <>
                                        {testResultList[index][condition_index][
                                          "list"
                                        ] && (
                                          <>
                                            {testResultList[index][
                                              condition_index
                                            ]["list"].map(
                                              (
                                                result,
                                                condition_value_index,
                                              ) => (
                                                <SelectItem
                                                  text={result.value}
                                                  value={result.id}
                                                  key={condition_value_index}
                                                />
                                              ),
                                            )}
                                          </>
                                        )}
                                      </>
                                    </Select>
                                  ) : (
                                    <>
                                      <TextInput
                                        name="value"
                                        className="reflexInputText"
                                        type="text"
                                        id={
                                          index +
                                          "_" +
                                          condition_index +
                                          "_value"
                                        }
                                        labelText={
                                          testResultList[index][
                                            condition_index
                                          ]["type"] === "N" ? (
                                            <FormattedMessage id="rulebuilder.label.numericValue" />
                                          ) : (
                                            <FormattedMessage id="rulebuilder.label.textValue" />
                                          )
                                        }
                                        value={condition.value}
                                        onChange={(e) => {
                                          handleRuleFieldItemChange(
                                            e,
                                            index,
                                            condition_index,
                                            FIELD.conditions,
                                          );
                                          addTextInPutError(
                                            condition.value,
                                            testResultList[index][
                                              condition_index
                                            ]["type"],
                                            "condition-value_" +
                                              index +
                                              "_" +
                                              condition_index,
                                          );
                                        }}
                                        invalid={validateTextInPut(
                                          condition.value,
                                          testResultList[index][
                                            condition_index
                                          ]["type"],
                                        )}
                                        invalidText={
                                          <FormattedMessage id="rulebuilder.error.invalidNumeric" />
                                        }
                                        disabled={normalRangeSelected(
                                          condition.relation,
                                        )}
                                        required
                                      />
                                    </>
                                  )}
                                </>
                              ) : (
                                <>
                                  <TextInput
                                    name="value"
                                    className="reflexInputText"
                                    type="text"
                                    id={
                                      index + "_" + condition_index + "_value"
                                    }
                                    labelText={
                                      <FormattedMessage id="rulebuilder.label.numericValue" />
                                    }
                                    value={condition.value}
                                    onChange={(e) =>
                                      handleRuleFieldItemChange(
                                        e,
                                        index,
                                        condition_index,
                                        FIELD.conditions,
                                      )
                                    }
                                    required
                                  />
                                </>
                              )}
                            </div>
                            <div>&nbsp; &nbsp;</div>
                            <div>
                              {testResultList[index] &&
                                testResultList[index][condition_index] &&
                                testResultList[index][condition_index][
                                  "type"
                                ] && (
                                  <>
                                    {testResultList[index][condition_index][
                                      "type"
                                    ] === "N" &&
                                      condition.relation === "BETWEEN" && (
                                        <TextInput
                                          name="value2"
                                          className="reflexInputText"
                                          type="text"
                                          id={
                                            index +
                                            "_" +
                                            condition_index +
                                            "_value"
                                          }
                                          labelText={
                                            <FormattedMessage id="rulebuilder.label.numericValue2" />
                                          }
                                          value={condition.value2}
                                          onChange={(e) => {
                                            handleRuleFieldItemChange(
                                              e,
                                              index,
                                              condition_index,
                                              FIELD.conditions,
                                            );
                                            addTextInPutError(
                                              condition.value2,
                                              testResultList[index][
                                                condition_index
                                              ]["type"],
                                              "condition-value2_" +
                                                index +
                                                "_" +
                                                condition_index,
                                            );
                                          }}
                                          invalid={validateTextInPut(
                                            condition.value2,
                                            testResultList[index][
                                              condition_index
                                            ]["type"],
                                          )}
                                          invalidText={
                                            <FormattedMessage id="rulebuilder.error.invalidNumeric" />
                                          }
                                          required
                                        />
                                      )}
                                  </>
                                )}
                            </div>
                            <div>&nbsp; &nbsp;</div>
                            {rule.conditions.length - 1 === condition_index && (
                              <div className="second-row">
                                <IconButton
                                  label={
                                    <FormattedMessage id="rulebuilder.label.addCondition" />
                                  }
                                  className="ruleFieldButton"
                                  onClick={() =>
                                    handleRuleFieldItemAdd(
                                      index,
                                      FIELD.conditions,
                                      conditionsObj,
                                    )
                                  }
                                  kind="tertiary"
                                  size="sm"
                                >
                                  {" "}
                                  <Add size={18} />
                                </IconButton>
                              </div>
                            )}
                            <div>&nbsp; &nbsp;</div>
                            {rule.conditions.length !== 1 && (
                              <div className="second-row">
                                <IconButton
                                  label={
                                    <FormattedMessage id="rulebuilder.label.removeCondition" />
                                  }
                                  className="ruleFieldButton"
                                  onClick={() =>
                                    handleRuleFieldItemRemove(
                                      index,
                                      condition_index,
                                      FIELD.conditions,
                                    )
                                  }
                                  kind="danger"
                                  size="sm"
                                >
                                  {" "}
                                  <Subtract size={18} />
                                </IconButton>
                              </div>
                            )}
                          </div>
                        ))}
                      </div>
                      <div className="section">
                        <div className="inlineDiv">
                          <div>
                            <h5>
                              <FormattedMessage id="rulebuilder.label.perfomActions" />
                            </h5>
                          </div>
                        </div>
                        {rule.actions.map((action, action_index) => (
                          <div
                            key={index + "_" + action_index}
                            className="inlineDiv"
                          >
                            <div>
                              <Select
                                id={index + "_" + action_index + "_sample"}
                                name="sampleId"
                                labelText={
                                  <FormattedMessage id="rulebuilder.label.selectSample" />
                                }
                                value={action.sampleId}
                                className="reflexInputSelect"
                                onChange={(e) => {
                                  handleRuleFieldItemChange(
                                    e,
                                    index,
                                    action_index,
                                    FIELD.actions,
                                  );
                                  handleSampleSelected(
                                    e,
                                    index,
                                    action_index,
                                    FIELD.actions,
                                  );
                                }}
                                required
                              >
                                <SelectItem text="" value="" />
                                {sampleList.map((sample, sample_index) => (
                                  <SelectItem
                                    text={sample.value}
                                    value={sample.id}
                                    key={sample_index}
                                  />
                                ))}
                              </Select>
                            </div>
                            <div>&nbsp; &nbsp;</div>
                            <div>
                              <AutoComplete
                                id={
                                  index + "_" + action_index + "_reflexTestId"
                                }
                                onSelect={(id) => {
                                  handleAutoCompleteRuleFieldItemChange(
                                    id,
                                    "reflexTestId",
                                    index,
                                    action_index,
                                    FIELD.actions,
                                  );
                                }}
                                label={
                                  <FormattedMessage id="rulebuilder.label.searchTest" />
                                }
                                name="reflexTestName"
                                value={action.reflexTestId}
                                class="autocomplete"
                                suggestions={
                                  sampleTestList[FIELD.actions][index]
                                    ? sampleTestList[FIELD.actions][index][
                                        action_index
                                      ]
                                    : []
                                }
                              />
                            </div>
                            <div>&nbsp; &nbsp;</div>
                            <div>
                              <TextArea
                                style={{
                                  width: "100%",
                                  height: "1px",
                                }}
                                name="internalNote"
                                type="text"
                                id={index + "_" + action_index + "_inote"}
                                labelText={
                                  <FormattedMessage id="rulebuilder.label.addInternalNote" />
                                }
                                value={action.internalNote}
                                onChange={(e) =>
                                  handleRuleFieldItemChange(
                                    e,
                                    index,
                                    action_index,
                                    FIELD.actions,
                                  )
                                }
                              />
                            </div>
                            <div>&nbsp; &nbsp;</div>
                            <div>
                              <TextArea
                                name="externalNote"
                                style={{
                                  width: "100%",
                                  height: "1px",
                                }}
                                type="text"
                                id={index + "_" + action_index + "_xnote"}
                                labelText={
                                  <FormattedMessage id="rulebuilder.label.addExternalNote" />
                                }
                                value={action.externalNote}
                                onChange={(e) =>
                                  handleRuleFieldItemChange(
                                    e,
                                    index,
                                    action_index,
                                    FIELD.actions,
                                  )
                                }
                              />
                            </div>
                            {/* <div>&nbsp; &nbsp;</div>
                            <div>
                              <RadioButtonGroup
                                valueSelected={action.addNotification}
                                legendText={
                                  <FormattedMessage id="rulebuilder.label.addPopup" />
                                }
                                name={
                                  index +
                                  "_" +
                                  action_index +
                                  "_add_notofocation"
                                }
                                id={index + "_" + action_index + "_popup"}
                                onChange={(value) =>
                                  handleAddNotificationChange(
                                    value,
                                    index,
                                    action_index,
                                    FIELD.actions,
                                  )
                                }
                              >
                                <RadioButton
                                  id={index + "_" + action_index + "_no"}
                                  labelText="Yes"
                                  value="Y"
                                />
                                <RadioButton
                                  id={index + "_" + action_index + "_yes"}
                                  labelText="No"
                                  value="N"
                                />
                              </RadioButtonGroup>
                            </div> */}
                            <div>&nbsp; &nbsp;</div>
                            {rule.actions.length - 1 === action_index && (
                              <div className="second-row">
                                <IconButton
                                  label={
                                    <FormattedMessage id="rulebuilder.label.addAction" />
                                  }
                                  className="ruleFieldButton"
                                  onClick={() =>
                                    handleRuleFieldItemAdd(
                                      index,
                                      FIELD.actions,
                                      actionObj,
                                    )
                                  }
                                  kind="tertiary"
                                  size="sm"
                                >
                                  {" "}
                                  <Add size={18} />
                                </IconButton>
                              </div>
                            )}
                            <div>&nbsp; &nbsp;</div>
                            {rule.actions.length !== 1 && (
                              <div className="second-row">
                                <IconButton
                                  label={
                                    <FormattedMessage id="rulebuilder.label.removeAction" />
                                  }
                                  className="ruleFieldButton"
                                  kind="danger"
                                  onClick={() =>
                                    handleRuleFieldItemRemove(
                                      index,
                                      action_index,
                                      FIELD.actions,
                                    )
                                  }
                                  size="sm"
                                >
                                  {" "}
                                  <Subtract size={18} />
                                </IconButton>
                              </div>
                            )}
                          </div>
                        ))}
                      </div>
                      <Button
                        id={"submit_" + index}
                        disabled={
                          Object.keys(errors).length === 0 ? false : true
                        }
                        type="submit"
                        kind="tertiary"
                        size="sm"
                      >
                        <FormattedMessage id="label.button.submit" />
                      </Button>
                    </>
                  )}
                </div>
              </Stack>
            </Form>
            {ruleList.length - 1 === index && (
              <IconButton
                onClick={handleRuleAdd}
                label={<FormattedMessage id="rulebuilder.label.addRule" />}
                size="md"
                kind="tertiary"
              >
                <Add size={16} />
                <span>
                  <FormattedMessage id="rulebuilder.label.rule" />
                </span>
              </IconButton>
            )}
          </div>
          <div className="second-division">
            {ruleList.length !== 1 && (
              <ModalWrapper
                modalLabel={
                  <FormattedMessage id="label.button.confirmDelete" />
                }
                handleSubmit={() => handleRuleRemove(index, rule.id)}
                primaryButtonText={
                  <FormattedMessage id="label.button.confirm" />
                }
                secondaryButtonText={
                  <FormattedMessage id="label.button.cancel" />
                }
                modalHeading={
                  <FormattedMessage id="rulebuilder.label.confirmDelete" />
                }
                buttonTriggerText={
                  <FormattedMessage id="rulebuilder.label.removeRule" />
                }
                size="md"
              ></ModalWrapper>
            )}
          </div>
        </div>
      ))}
    </>
  );
}

export default ReflexRule;
