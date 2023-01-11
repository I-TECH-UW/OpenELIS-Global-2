import { useState, useEffect, useRef } from "react";
import { Form, Stack, TextInput, Select, SelectItem, Button, InlineLoading, IconButton, Search, Toggle, Switch } from '@carbon/react';
import { Add, Subtract } from '@carbon/react/icons';
import Autocomplete from "../inputComponents/AutoComplete";
import { Formik, Field, FieldArray, ErrorMessage } from "formik";
import config from '../../config.json'
import RuleBuilderFormValues from "../formModel/innitialValues/RuleBuilderFormValues";
import RuleBuilderValidationSchema from "../formModel/ValidationScheme/RuleBuilderValidationSchema";
import * as Yup from 'yup';

function ReflexRule() {
  const componentMounted = useRef(true);
  const FIELD = {
    conditions: "conditions",
    actions: "actions"
  }
  const conditionsObj = {
    sample: "",
    test: "",
    testId: "",
    relation: "",
    value: ""
  }
  const actionObj = {
    action: "",
    reflexResult: "",
    reflexResultTestId: ""
  }
  const ruleObj = {
    ruleName: "",
    overall: "",
    toggled: true,
    conditions: [conditionsObj],
    actions: [actionObj]
  }

  const [ruleList, setRuleList] = useState([RuleBuilderFormValues]);

  const [testList, setTestList] = useState([]);

  const [sampleList, setSampleList] = useState([]);

  useEffect(() => {
    fetch(config.serverBaseUrl + "/rest/tests",
      //includes the browser sessionId in the Header for Authentication on the backend server
      { credentials: "include" }
    )
      .then(response => response.json()).then(jsonResp => {
        if (componentMounted.current) {
          setTestList(jsonResp);
          //console.log(JSON.stringify(jsonResp))
        }
      }).catch(error => {
        console.log(error)
      })

    fetch(config.serverBaseUrl + "/rest/samples",
      //includes the browser sessionId in the Header for Authentication on the backend server
      { credentials: "include" }
    )
      .then(response => response.json()).then(jsonResp => {
        if (componentMounted.current) {
          setSampleList(jsonResp);
        }
      }).catch(error => {
        console.log(error)
      })

    return () => { // This code runs when component is unmounted
      componentMounted.current = false;
    }

  }, []);

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

  const handleRuleRemove = (index) => {
    const list = [...ruleList];
    list.splice(index, 1);
    setRuleList(list);
  };

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

  const handleSubmit = (values) => {
    // event.preventDefault();
    //setRuleList([...ruleList, { rule: "" }]);
    //console.log(JSON.stringify(ruleList))
    console.log(JSON.stringify(values))
  };


  return (
    <>
      {ruleList.map((rule, index) => (

        <div key={index} className="rules">
          <div className="first-division">
            <Formik
              initialValues={rule}
              validationSchema={RuleBuilderValidationSchema}
              onSubmit={handleSubmit}
              onChange
            >
              {({ values,
                errors,
                touched,
                handleChange,
                handleBlur,
                handleSubmit }) => (
                <Form
                  onSubmit={handleSubmit}
                  onChange={handleChange}
                  onBlur={handleBlur}
                >
                  <Stack gap={7}>
                    <div className="ruleBody">
                      <div className="inlineDiv">
                        <div>
                          <Field name="ruleName"
                          >
                            {({ field }) =>
                       
                              <TextInput
                                name={field.name}
                                className="inputText"
                                type="text"
                                invalidText="Invalid error message."
                                id={index + "_rulename"}
                                labelText="Rule Name"
                                value={field.value}
                                onChange={(e) => handleRuleFieldChange(e, index)}
                                required
                              />
                           
                            }
                          </Field>
                          <div className="errorMessage">
                            <ErrorMessage name="ruleName" />
                          </div>
                        </div>
                        <div >
                          &nbsp;  &nbsp;
                        </div>
                        <div >
                          <Toggle
                            toggled={rule.toggled}
                            aria-label="toggle button"
                            id={index + "_toggle"}
                            labelText="Label text"
                            onToggle={(e) => toggleRule(e, index)}
                          />
                        </div>
                      </div>
                      {rule.toggled && (

                        <>
                          <div className="section">
                            <div className="inlineDiv">
                              <div >
                                If &nbsp;
                              </div>
                              <div >
                                <Field name="overall"
                                >
                                  {({ field }) =>
                                    <Select
                                      value={field.value}
                                      id={index + "_overall"}
                                      name={field.name}
                                      labelText=""
                                      className="inputSelect"
                                      onChange={(e) => handleRuleFieldChange(e, index)}
                                    >
                                      <SelectItem
                                        text="Any"
                                        value="any"
                                      />
                                      <SelectItem
                                        text="All"
                                        value="all"
                                      />
                                    </Select>
                                  }
                                </Field>
                                <div className="errorMessage">
                                  <ErrorMessage name="overall" />
                                </div>
                              </div>
                              <div >
                                &nbsp; of the following conditions are met
                              </div>
                            </div>
                            <FieldArray name="conditions">
                              {() => rule.conditions.map((condition, condition_index) => (
                                <div key={index + "_" + condition_index} className="inlineDiv">
                                  <div>
                                    If a Sample is &nbsp;
                                  </div>
                                  <div>
                                    <Field name={`conditions.${condition_index}.sample`}>
                                      {({ field }) =>
                                        <Select
                                          id={index + "_" + condition_index + "_sample"}
                                          name={field.name}
                                          labelText=""
                                          value={field.value}
                                          className="inputSelect"
                                          onChange={(e) => handleRuleFieldItemChange(e, index, condition_index, FIELD.conditions)}
                                        >
                                          {sampleList.map((sample, sample_index) => (
                                            <SelectItem
                                              text={sample.value}
                                              value={sample.id}
                                              key={sample_index}
                                            />
                                          ))}
                                        </Select>
                                      }
                                    </Field>
                                    <div className="errorMessage">
                                      <ErrorMessage name={`conditions.${condition_index}.sample`} />
                                    </div>
                                  </div>
                                  <div>
                                    &nbsp; And the Test &nbsp;
                                  </div>
                                  <div>
                                    <Field name={`conditions.${condition_index}.test`}>
                                      {({ field }) =>
                                        <Autocomplete
                                          stateValue={field.value}
                                          handleChange={handleRuleFieldItemChange}
                                          index={index}
                                          name={field.name}
                                          idField="testId"
                                          class="autocomplete1"
                                          item_index={condition_index}
                                          field={FIELD.conditions}
                                          suggestions={testList} />
                                      }
                                    </Field>
                                    <div className="errorMessage">
                                      <ErrorMessage name={`conditions.${condition_index}.test`} />
                                    </div>
                                  </div>
                                  <div>
                                    &nbsp;  &nbsp;
                                  </div>
                                  <div >
                                    <Field name={`conditions.${condition_index}.relation`}>
                                      {({ field }) =>
                                        <Select
                                          value={field.value}
                                          id={index + "_" + condition_index + "_relation"}
                                          name={field.name}
                                          labelText=""
                                          className="inputSelect"
                                          onChange={(e) => handleRuleFieldItemChange(e, index, condition_index, FIELD.conditions)}
                                        >
                                          <SelectItem
                                            text="Equlas"
                                            value="equals"
                                          />
                                          <SelectItem
                                            text="NotEqual"
                                            value="not_equals"
                                          />
                                        </Select>
                                      }
                                    </Field>
                                    <div className="errorMessage">
                                      <ErrorMessage name={`conditions.${condition_index}.relation`} />
                                    </div>
                                  </div>
                                  <div>
                                    &nbsp;  &nbsp;
                                  </div>
                                  <div >
                                    <Field name={`conditions.${condition_index}.value`}>
                                      {({ field }) =>
                                        <Select
                                          value={field.value}
                                          id={index + "_" + condition_index + "_value"}
                                          name={field.name}
                                          labelText=""
                                          className="inputSelect"
                                          onChange={(e) => handleRuleFieldItemChange(e, index, condition_index, FIELD.conditions)}
                                        >
                                          <SelectItem
                                            text="Postive"
                                            value="postive"
                                          />
                                          <SelectItem
                                            text="Negative"
                                            value="negative"
                                          />
                                        </Select>
                                      }
                                    </Field>
                                    <div className="errorMessage">
                                      <ErrorMessage name={`conditions.${condition_index}.value`} />
                                    </div>
                                  </div>
                                  <div>
                                    &nbsp;  &nbsp;
                                  </div>
                                  {rule.conditions.length - 1 === condition_index && (
                                    <div >
                                      <IconButton label="" onClick={() => handleRuleFieldItemAdd(index, FIELD.conditions, conditionsObj)} kind='tertiary' size='sm'>  <Add size={18} /></IconButton>
                                    </div>
                                  )}
                                  <div>
                                    &nbsp;  &nbsp;
                                  </div>
                                  {rule.conditions.length !== 1 && (
                                    <div >
                                      <IconButton label="" onClick={() => handleRuleFieldItemRemove(index, condition_index, FIELD.conditions)} kind='tertiary' size='sm'>  <Subtract size={18} /></IconButton>
                                    </div>
                                  )}
                                </div>
                              ))}
                            </FieldArray>
                          </div>
                          <div className="section">
                            <div className="inlineDiv">
                              <div >
                                <h5>Perform the following actions </h5> &nbsp;
                              </div>
                            </div>
                            <FieldArray name="actions">
                              {() => rule.actions.map((action, action_index) => (
                                <div key={index + "_" + action_index} className="inlineDiv">
                                  <div >
                                    <Field name={`actions.${action_index}.action`}>
                                      {({ field }) =>
                                        <Select
                                         value={field.value}
                                          id={index + "_" + action_index + "_value"}
                                          name={field.name}
                                          labelText=""
                                          className="inputSelect"
                                          onChange={(e) => handleRuleFieldItemChange(e, index, action_index, FIELD.actions)}
                                        >
                                          <SelectItem
                                            text="Add Test To Order"
                                            value="add_test"
                                          />
                                          <SelectItem
                                            text="Add Notification"
                                            value="notifocation"
                                          />
                                        </Select>
                                      }
                                    </Field>
                                    <div className="errorMessage">
                                      <ErrorMessage name={`actions.${action_index}.action`} />
                                    </div>
                                  </div>
                                  <div>
                                    &nbsp;  &nbsp;
                                  </div>
                                  <div>
                                    <Field name={`actions.${action_index}.reflexResult`}>
                                      {({ field }) =>
                                        <Autocomplete
                                          stateValue={field.value}
                                          handleChange={handleRuleFieldItemChange}
                                          index={index}
                                          name={field.name}
                                          idField="reflexResultTestId"
                                          item_index={action_index}
                                          field={FIELD.actions}
                                          class="autocomplete2"
                                          suggestions={testList} />
                                      }
      
                                    </Field>
                                    <div className="errorMessage">
                                      <ErrorMessage name={`actions.${action_index}.reflexResult`} />
                                    </div>
                                  </div>
                                  <div>
                                    &nbsp;  &nbsp;
                                  </div>
                                  {rule.actions.length - 1 === action_index && (
                                    <div >
                                      <IconButton label="" onClick={() => handleRuleFieldItemAdd(index, FIELD.actions, actionObj)} kind='tertiary' size='sm'>  <Add size={18} /></IconButton>
                                    </div>
                                  )}
                                  <div>
                                    &nbsp;  &nbsp;
                                  </div>
                                  {rule.actions.length !== 1 && (
                                    <div >
                                      <IconButton label="" onClick={() => handleRuleFieldItemRemove(index, action_index, FIELD.actions)} kind='tertiary' size='sm'>  <Subtract size={18} /></IconButton>
                                    </div>
                                  )}
                                </div>
                              ))}
                            </FieldArray>
                          </div>
                          <Button type="submit" kind='tertiary' size='sm'>
                            Submit
                          </Button>
                        </>
                      )}
                    </div>
                  </Stack>
                </Form >
              )}
            </Formik>
            {ruleList.length - 1 === index && (
              <button
                onClick={handleRuleAdd}
                className="add_button"
              >
                <Add size={16} />
                <span>Rule</span>
              </button>
            )}

          </div>
          <div className="second-division">
            {ruleList.length !== 1 && (
              <button
                type="button"
                onClick={() => handleRuleRemove(index)}
                className="remove-btn">
                <Subtract size={16} />
              </button>
            )}
          </div>
        </div>

      ))}

    </>
  );
}

export default ReflexRule;