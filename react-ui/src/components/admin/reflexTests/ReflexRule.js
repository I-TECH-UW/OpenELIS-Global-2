import {useContext, useState, useEffect, useRef } from "react";
import { Form, Stack, TextInput, Select, SelectItem, Button, InlineLoading, IconButton, Search, Toggle, Switch, Loading, RadioButtonGroup, RadioButton } from '@carbon/react';
import { Add, Subtract } from '@carbon/react/icons';
import Autocomplete from "./AutoComplete";
import RuleBuilderFormValues from "../../formModel/innitialValues/RuleBuilderFormValues";
import { getFromOpenElisServer, postToOpenElisServer, getFromOpeElisServerSync } from "../../utils/Utils";
import { NotificationContext } from "../../layout/Layout";
import { AlertDialog,  NotificationKinds} from "../../common/CustomNotification";
import { FormattedMessage} from "react-intl";
import "./ReflexStyles.css"


function ReflexRule() {
  const componentMounted = useRef(true);
  const FIELD = {
    conditions: "conditions",
    actions: "actions"
  }
  const conditionsObj = {
    id: null,
    sampleId: "",
    testName: "",
    testId: "",
    relation: "",
    value: ""
  }
  const actionObj = {
    id: null,
    sampleId: "",
    reflexTestName: "",
    reflexTestId: "",
    internalNote: "",
    externalNote: "",
    addNotification: "Y"
  }

  const ruleObj = {
    id: null,
    ruleName: "",
    overall: "",
    toggled: true,
    active : true,
    conditions: [conditionsObj],
    actions: [actionObj]
  }


  const [ruleList, setRuleList] = useState([RuleBuilderFormValues]);
  const [sampleList, setSampleList] = useState([]);
  const [generalRelationOptions, setGeneralRelationOptions] = useState([]);
  const [numericRelationOptions, setNumericRelationOptions] = useState([]);
  const [overallOptions, setOverallOptions] = useState([]);
  const [testResultList, setTestResultList] = useState({ 0: { 0: { type: "N", list: [] } } }); //{index :{field_index:{type : "T" ,list : []}}}
  const [sampleTestList, setSampleTestList] = useState({ "conditions": {}, "actions": {} }); //{field :{index :{field_index:[]}}}
  const [counter, setCounter] = useState(0);
  const [loaded, setLoaded] = useState(false);
  const [errors, setErrors] = useState({});
  const { notificationVisible ,setNotificationVisible,setNotificationBody} = useContext(NotificationContext);
  var defaultTestResultList = {};
  var defaultSampleTests = { "conditions": {}, "actions": {} };

  useEffect(() => {
    getFromOpenElisServer("/rest/samples", fetchSamples)
    getFromOpenElisServer("/rest/reflexrule-options", fetchRuleOptions)
    getFromOpenElisServer("/rest/reflexrules", fetchReflexRules)


    return () => { // This code runs when component is unmounted
      componentMounted.current = false;
    }

  }, []);

  const loadDefaultTestResultList = () => {

    ruleList.forEach(function (rule, index) {
      if (rule.conditions) {
        rule.conditions.forEach(function (condition, conditionIndex) {
          if (condition.sampleId) {
            getFromOpeElisServerSync("/rest/test-details?sampleType=" + condition.sampleId, (resp) => fetchDeafultTests(resp, index, conditionIndex, FIELD.conditions));
          }
          if (condition.value) {
            const test = defaultSampleTests.conditions[index][conditionIndex].find(test => {
              if (test.value.trim() === condition.testId) {
                return true
              }
            })

            if (test) {
              loadDefaultResultList(index, conditionIndex, test);
            }
          }
        });
      }
      if (rule.actions) {
        rule.actions.forEach(function (action, actionIndex) {
          if (action.sampleId) {
            getFromOpeElisServerSync("/rest/test-details?sampleType=" + action.sampleId, (resp) => fetchDeafultTests(resp, index, actionIndex, FIELD.actions));
          }
        });

      }
    });

    setTestResultList(defaultTestResultList);
    setSampleTestList(defaultSampleTests);
  }

  const addError = (errorObj) => {
    const error = { ...errors }
    error[errorObj.name] = errorObj.error
    setErrors(error)
  }

  const clearError = (field) => {
    const error = { ...errors }
    delete error[field]
    setErrors(error)
  }

  const fetchDeafultTests = (testList, index, item_index, field) => {
    loadDeafultSampleTestList(field, index, item_index, testList);
  }

  const loadDeafultSampleTestList = (field, index, item_index, resulList) => {
    if (!defaultSampleTests[field][index]) {
      defaultSampleTests[field][index] = {}
    }
    defaultSampleTests[field][index][item_index] = resulList
  }

  const loadDefaultResultList = (index, item_index, test) => {
    if (!defaultTestResultList[index]) {
      defaultTestResultList[index] = {}
    }
    if (!defaultTestResultList[index][item_index]) {
      defaultTestResultList[index][item_index] = {}
    }

    defaultTestResultList[index][item_index]["list"] = test.resultList
    defaultTestResultList[index][item_index]["type"] = test.resultType
  }

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
  }

  const handleAddNotificationChange = (value, index, itemIndex, field) => {
    const e = { target: { name: "addNotification", value: value } };
    handleRuleFieldItemChange(e, index, itemIndex, field);
  }

  const handleTestSelected = (index, item_index, testDetails) => {
    const results = { ...testResultList }
    if (!results[index]) {
      results[index] = {}
    }
    if (!results[index][item_index]) {
      results[index][item_index] = {}
    }
    results[index][item_index]["list"] = testDetails.resultList
    results[index][item_index]["type"] = testDetails.resultType
    setTestResultList(results)
  }

  const loadSampleTestList = (field, index, item_index, resulList) => {
    const results = { ...sampleTestList }
    if (!results[field][index]) {
      results[field][index] = {}
    }
    results[field][index][item_index] = resulList
    setSampleTestList(results);
  }

  const handleSampleSelected = (e, index, item_index, field) => {
    const { value } = e.target;
    getFromOpenElisServer("/rest/test-details?sampleType=" + value, (resp) => fetchTests(resp, index, item_index, field));
  }


  const handleRuleRemove = (index, id) => {
    const list = [...ruleList];
    list.splice(index, 1);
    setRuleList(list);
    if (id) {
      postToOpenElisServer("/rest/deactivate-reflexrule/" + id, {}, handleDelete);
    }
  };

  const handleDelete = (status) => {
   // alert(status)
    setNotificationVisible(true);
    if(status == "200"){
      setNotificationBody({kind: NotificationKinds.success, title: "Notification Message", message: "Succesfuly Deleted"});
    }else{
      setNotificationBody({kind: NotificationKinds.error, title: "Notification Message", message: "Error while Deleting"});
    }
  }

  const handleRuleAdd = () => {
    setRuleList([...ruleList, ruleObj]);
  };

  const toggleRule = (e, index) => {
    const list = [...ruleList];
    list[index]["toggled"] = e;
    setRuleList(list);
  }

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

  const handleSubmited = (status , index) => {
    //alert(status)
    setNotificationVisible(true);
    if(status == "200"){
      const element = document.getElementById("submit_"+index)
      element.disabled = true;
      setNotificationBody({kind: NotificationKinds.success, title: "Notification Message", message: "Succesfuly saved"});
    }else{
      setNotificationBody({kind: NotificationKinds.error, title: "Notification Message", message: "Error while saving"});
    }
  };

  const handleSubmit = (event, index) => {
    event.preventDefault();
    console.log(JSON.stringify(ruleList[index]))
    postToOpenElisServer("/rest/reflexrule", JSON.stringify(ruleList[index]), (status) => handleSubmited(status ,index))
  };

  const fetchTests = (testList, index, item_index, field) => {
    loadSampleTestList(field, index, item_index, testList);
    setLoaded(true)
  }

  const fetchSamples = (sampleList) => {
    if (componentMounted.current) {
      setSampleList(sampleList);
    }
  }

  const fetchReflexRules = (reflexRuleList) => {
    if (componentMounted.current) {
      // console.log(JSON.stringify(reflexRuleList))
      if (reflexRuleList.length > 0) {
        setRuleList(reflexRuleList);
      }
    }
  }

  const fetchRuleOptions = (options) => {
    if (componentMounted.current) {
      console.log(JSON.stringify(options))
      if (options) {
        setGeneralRelationOptions(options.generalRelationOptions);
        setNumericRelationOptions(options.numericRelationOptions);
        setOverallOptions(options.overallOptions)
      }
      setLoaded(true)
    }
  }

  const handleClick = () => {
    var count = counter + 1;
    if (count == 1) {
      loadDefaultTestResultList();
    }
    setCounter(count);
  };

  const validateTextInPut = (value, type ) => {
    if (type === "N") {
      if (value.match(/^-?\d+$/)) {
        //valid integer (positive or negative)
        return false;
      } else if (value.match(/^\d+\.\d+$/)) {
        //valid float
        return false;
      } else {
        console.log("invalid value")
        return true;
      }
    }
  }

  const addTextInPutError = (value, type ,fieldName) => {
    if (type === "N") {
      if (value.match(/^-?\d+$/)) {
        //valid integer (positive or negative)
        clearError(fieldName);
      } else if (value.match(/^\d+\.\d+$/)) {
        //valid float
        clearError(fieldName);
      } else {
        console.log("invalid value")
        addError({name : fieldName, error : "Invaid Numeric Value"})
      }
    }
  }


  return (
    <>
     {notificationVisible === true ? <AlertDialog/> : ""}
      {!loaded && (
        <Loading></Loading>
      )}
      {ruleList.map((rule, index) => (
        <div key={index} className="rules" >
          <div className="first-division">
            <Form
              onSubmit={(e) => handleSubmit(e, index)}
            >
              <Stack gap={7}>
                <div className="ruleBody">
                  <div className="inlineDiv">
                    <div>
                      <TextInput
                        name="ruleName"
                        className="reflexInputText"
                        type="text"
                        id={index + "_rulename"}
                        labelText="Rule Name"
                        value={rule.ruleName}
                        onChange={(e) => handleRuleFieldChange(e, index)}
                        required
                      />
                    </div>
                    <div >
                      &nbsp;  &nbsp;
                    </div>
                    <div >
                      <Toggle
                        toggled={rule.toggled}
                        aria-label="toggle button"
                        id={index + "_toggle"}
                        labelText="Toggle Rule"
                        onToggle={(e) => toggleRule(e, index)}
                        onClick={handleClick}
                      />
                    </div>
                  </div>
                  {rule.toggled && (

                    <>
                      <div className="section">
                        <div className="inlineDiv">
                          <div >
                            <h5>Add Reflex Rule Conditions </h5>
                          </div>
                        </div>
                        <div className="inlineDiv">
                          <div >
                            <Select
                              value={rule.overall}
                              id={index + "_overall"}
                              name="overall"
                              labelText="Over All Option"
                              className="reflexInputSelect"
                              onChange={(e) => handleRuleFieldChange(e, index)}
                              required
                            >
                              <SelectItem
                                text=""
                                value=""
                              />
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
                          <div key={index + "_" + condition_index} className="inlineDiv">

                            <div >
                              <Select
                                id={index + "_" + condition_index + "_sample"}
                                name="sampleId"
                                labelText="Select Sample"
                                value={condition.sampleId}
                                className="reflexInputSelect"
                                onChange={(e) => { handleRuleFieldItemChange(e, index, condition_index, FIELD.conditions); handleSampleSelected(e, index, condition_index, FIELD.conditions) }}
                                required
                              >
                                <SelectItem
                                  text=""
                                  value=""
                                />
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
                              <Autocomplete
                                stateValue={condition.testName}
                                handleChange={handleRuleFieldItemChange}
                                onSelect={handleTestSelected}
                                index={index}
                                name="testName"
                                idField="testId"
                                label="Search Test"
                                class="autocomplete"
                                item_index={condition_index}
                                field={FIELD.conditions}
                                suggestions={sampleTestList[FIELD.conditions][index] ? sampleTestList[FIELD.conditions][index][condition_index] : []}
                                required
                                addError={addError}
                                clearError={clearError}
                              />
                            </div>
                            <div>
                              &nbsp;  &nbsp;
                            </div>
                            <div >
                              <Select
                                value={condition.relation}
                                id={index + "_" + condition_index + "_relation"}
                                name="relation"
                                labelText="Relation"
                                className="reflexInputSelect"
                                onChange={(e) => handleRuleFieldItemChange(e, index, condition_index, FIELD.conditions)}
                                required
                              >
                                <SelectItem
                                  text=""
                                  value=""
                                />
                                {testResultList[index] && testResultList[index][condition_index] && testResultList[index][condition_index]["type"] && (
                                  <>
                                    {testResultList[index][condition_index]["type"] === 'N' ? (
                                      <>
                                        {numericRelationOptions.map((relation, relation_index) => (
                                          <SelectItem
                                            text={relation.label}
                                            value={relation.value}
                                            key={relation_index}
                                          />
                                        ))}
                                      </>
                                    ) : (<>
                                      {generalRelationOptions.map((relation, relation_index) => (
                                        <SelectItem
                                          text={relation.label}
                                          value={relation.value}
                                          key={relation_index}
                                        />
                                      ))}
                                    </>)}
                                  </>
                                )}
                              </Select>

                            </div>
                            <div>
                              &nbsp;  &nbsp;
                            </div>
                            <div >

                              {testResultList[index] && testResultList[index][condition_index] && testResultList[index][condition_index]["type"] ? (
                                <>
                                  {testResultList[index][condition_index]["type"] === 'D' ? (
                                    <Select
                                      value={condition.value}
                                      id={index + "_" + condition_index + "_value"}
                                      name="value"
                                      labelText="Dictionaly Result"
                                      className="reflexInputSelect"
                                      onChange={(e) => handleRuleFieldItemChange(e, index, condition_index, FIELD.conditions)}
                                      required
                                    >
                                      <SelectItem
                                        text=""
                                        value=""
                                      />
                                      <>
                                        {testResultList[index][condition_index]["list"] && (
                                          <>
                                            {testResultList[index][condition_index]["list"].map((result, condition_value_index) => (
                                              <SelectItem
                                                text={result.label}
                                                value={result.value}
                                                key={condition_value_index}
                                              />
                                            ))}
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
                                        id={index + "_" + condition_index + "_value"}
                                        labelText={testResultList[index][condition_index]["type"] === "N" ? "Numeric Result" : "Text Result"}
                                        value={condition.value}
                                        onChange={(e) => {handleRuleFieldItemChange(e, index, condition_index, FIELD.conditions); addTextInPutError(condition.value , testResultList[index][condition_index]["type"] ,"condition-value_" + index + "_" + condition_index )}}
                                        invalid={validateTextInPut(condition.value , testResultList[index][condition_index]["type"])}
                                        invalidText="Invalid Numeric Value"
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
                                    id={index + "_" + condition_index + "_value"}
                                    labelText="Numeric Result"
                                    value={condition.value}
                                    onChange={(e) => handleRuleFieldItemChange(e, index, condition_index, FIELD.conditions)}
                                    required
                                  />
                                </>
                              )}
                            </div>
                            <div>
                              &nbsp;  &nbsp;
                            </div>
                            {rule.conditions.length - 1 === condition_index && (
                              <div >
                                <IconButton label="Add Condition" className="ruleFieldButton" onClick={() => handleRuleFieldItemAdd(index, FIELD.conditions, conditionsObj)} kind='tertiary' size='sm'>  <Add size={18} /></IconButton>
                              </div>
                            )}
                            <div>
                              &nbsp;  &nbsp;
                            </div>
                            {rule.conditions.length !== 1 && (
                              <div >
                                <IconButton label="Remove Condition" className="ruleFieldButton" onClick={() => handleRuleFieldItemRemove(index, condition_index, FIELD.conditions)} kind='danger' size='sm'>  <Subtract size={18} /></IconButton>
                              </div>
                            )}
                          </div>
                        ))}
                      </div>
                      <div className="section">
                        <div className="inlineDiv">
                          <div >
                            <h5>Perform the following actions </h5>
                          </div>
                        </div>
                        {rule.actions.map((action, action_index) => (
                          <div key={index + "_" + action_index} className="inlineDiv">
                            <div >
                              <Select
                                id={index + "_" + action_index + "_sample"}
                                name="sampleId"
                                labelText="Select Sample"
                                value={action.sampleId}
                                className="reflexInputSelect"
                                onChange={(e) => { handleRuleFieldItemChange(e, index, action_index, FIELD.actions); handleSampleSelected(e, index, action_index, FIELD.actions) }}
                                required
                              >
                                <SelectItem
                                  text=""
                                  value=""
                                />
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
                              &nbsp;  &nbsp;
                            </div>
                            <div>
                              <Autocomplete
                                stateValue={action.reflexTestName}
                                handleChange={handleRuleFieldItemChange}
                                index={index}
                                label="Search Test"
                                name="reflexTestName"
                                idField="reflexTestId"
                                item_index={action_index}
                                field={FIELD.actions}
                                class="autocomplete"
                                addError={addError}
                                clearError={clearError}
                                suggestions={sampleTestList[FIELD.actions][index] ? sampleTestList[FIELD.actions][index][action_index] : []} />
                            </div>
                            <div>
                              &nbsp;  &nbsp;
                            </div>
                            <div>
                              <TextInput
                                name="internalNote"
                                className="reflexInputText"
                                type="text"
                                id={index + "_" + action_index + "_inote"}
                                labelText="Add Internal Note"
                                value={action.internalNote}
                                onChange={(e) => handleRuleFieldItemChange(e, index, action_index, FIELD.actions)}

                              />
                            </div>
                            <div>
                              &nbsp;  &nbsp;
                            </div>
                            <div>
                              <TextInput
                                name="externalNote"
                                className="reflexInputText"
                                type="text"
                                id={index + "_" + action_index + "_xnote"}
                                labelText="Add External Note"
                                value={action.externalNote}
                                onChange={(e) => handleRuleFieldItemChange(e, index, action_index, FIELD.actions)}

                              />
                            </div>
                            <div>
                              &nbsp;  &nbsp;
                            </div>
                            <div>
                              <RadioButtonGroup
                                valueSelected={action.addNotification}
                                legendText="Add Pop Up"
                                name={index + "_" + action_index + "_add_notofocation"}
                                id={index + "_" + action_index + "_popup"}
                                onChange={(value) => handleAddNotificationChange(value, index, action_index, FIELD.actions)}
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
                            </div>
                            <div>
                              &nbsp;  &nbsp;
                            </div>
                            {rule.actions.length - 1 === action_index && (
                              <div >
                                <IconButton label="Add Action" className="ruleFieldButton" onClick={() => handleRuleFieldItemAdd(index, FIELD.actions, actionObj)} kind='tertiary' size='sm'>  <Add size={18} /></IconButton>
                              </div>
                            )}
                            <div>
                              &nbsp;  &nbsp;
                            </div>
                            {rule.actions.length !== 1 && (
                              <div >
                                <IconButton label="Remove Action" className="ruleFieldButton" kind='danger' onClick={() => handleRuleFieldItemRemove(index, action_index, FIELD.actions)} size='sm'>  <Subtract size={18} /></IconButton>
                              </div>
                            )}
                          </div>
                        ))}
                      </div>
                      <Button id={"submit_"+ index} disabled={(Object.keys(errors).length === 0 ? false : true)} type="submit" kind='tertiary' size='sm'>
                         <FormattedMessage id="label.button.submit" />
                      </Button>
                    </>
                  )}
                </div>
              </Stack>
            </Form >
            {ruleList.length - 1 === index && (
              <IconButton onClick={handleRuleAdd} label="Add Rule" size='md' kind='tertiary' >
                <Add size={16} />
                <span>Rule</span>
              </IconButton>
            )}

          </div>
          <div className="second-division">
            {ruleList.length !== 1 && (
              <IconButton kind='danger' label="Remove Rule" size='md' onClick={() => handleRuleRemove(index, rule.id)}>
                <Subtract size={16} />
              </IconButton>
            )}
          </div>
        </div>

      ))}

    </>
  );
}

export default ReflexRule;