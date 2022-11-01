import { useState } from "react";
import { Form, Stack, TextInput, Select, SelectItem, Button, InlineLoading, IconButton, Search, Toggle, Switch } from '@carbon/react';
import { Add, Subtract } from '@carbon/react/icons';
import Autocomplete from "../inputComponents/AutoComplete";
function AddRule() {
  const [ruleList, setRuleList] = useState([{
    ruleName: "",
    overall: "",
    conditions: [{
      sample: "",
      test: "",
      relation: "",
      value: ""
    }]
  }]);

  const [toggleList, setToggleList] = useState([]);

  const handleRuleChange = (e, index) => {
    const { name, value } = e.target;
    const list = [...ruleList];
    list[index][name] = value;
    setRuleList(list);
  };

  const handleRuleFieldChange = (e, index, condition_index, field) => {
    const { name, value } = e.target;
    const list = [...ruleList];
    list[index][field][condition_index][name] = value;
    setRuleList(list);
  }

  const handleRuleRemove = (index) => {
    const list = [...ruleList];
    list.splice(index, 1);
    setRuleList(list);
  };

  const handleRuleAdd = () => {
    setRuleList([...ruleList, {
      ruleName: "",
      overall: "",
      conditions: [{
        sample: "",
        test: "",
        relation: "",
        value: ""
      }]
    }]);
  };

  const toggleRule = (e, index) => {
    const list = [...toggleList];
    if (!e) {
      if (!list.includes(index)) {
        list.push(index)
        setToggleList(list)
      }
    } else {
      if (list.includes(index)) {
        const i = list.indexOf(index);
        list.splice(i, 1)
        setToggleList(list)
      }
    }
  }

  const handleRuleConditionAdd = (index) => {
    const list = [...ruleList];
    list[index]["conditions"].push({
      sample: "",
      test: "",
      relation: "",
      value: ""
    });
    setRuleList(list);
  };

  const handleRuleConditionRemove = (index, condition_index) => {
    const list = [...ruleList];
    list[index]["conditions"].splice(condition_index, 1);
    setRuleList(list);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    //setRuleList([...ruleList, { rule: "" }]);
    console.log(JSON.stringify(ruleList))
  };


  return (
    <>
      {ruleList.map((rule, index) => (

        <div key={index} className="rules">
          <div className="first-division">
            <Form
              onSubmit={handleSubmit}
            >
              <Stack gap={7}>
                <div className="ruleBody">
                  <div className="inlineDiv">
                    <div>
                      <TextInput
                        name="ruleName"
                        className="inputText"
                        type="text"
                        id="rule"
                        labelText="Rule Name"
                        value={rule.ruleName}
                        onChange={(e) => handleRuleChange(e, index)}
                        required
                      />
                    </div>
                    <div >
                      &nbsp;  &nbsp;
                    </div>
                    <div >
                      <Toggle
                        defaultToggled
                        aria-label="toggle button"
                        id={index + "_toglge"}
                        labelText="Label text"
                        onToggle={(e) => toggleRule(e, index)}
                      />
                    </div>
                  </div>
                  {(!toggleList.includes(index)) && (
                   
                    <>
                      <div className="section">
                        <div className="inlineDiv">
                          <div >
                            If &nbsp;
                          </div>
                          <div >
                            <Select
                              value={rule.overall}
                              id="select"
                              name="overall"
                              labelText=""
                              className="inputSelect"
                              onChange={(e) => handleRuleChange(e, index)}
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
                          </div>
                          <div >
                            &nbsp; of the following conditions are met
                          </div>
                        </div>
                        {rule.conditions.map((condition, condition_index) => (
                          <div key={index + "_" + condition_index} className="inlineDiv">
                            <div>
                              If a Sample is &nbsp;
                            </div>
                            <div >
                              <Select
                                id="select"
                                name="sample"
                                labelText=""
                                value={condition.sample}
                                className="inputSelect"
                                onChange={(e) => handleRuleFieldChange(e, index, condition_index, "conditions")}
                              >
                                <SelectItem
                                  text="Blood"
                                  value="blood"
                                />
                                <SelectItem
                                  text="Fluid"
                                  value="fluid"
                                />
                              </Select>
                            </div>
                            <div>
                              &nbsp; And the Test &nbsp;
                            </div>
                            <div>
                              <Autocomplete
                                stateValue={condition.test}
                                handleChange={handleRuleFieldChange}
                                index={index}
                                condition_index={condition_index}
                                suggestions={["CD4Count", "CD4Percent", "CD3Count", "CD3Absolute"]} />
                            </div>
                            <div>
                              &nbsp;  &nbsp;
                            </div>
                            <div >
                              <Select
                                value={condition.relation}
                                id="relation"
                                name="relation"
                                labelText=""
                                className="inputSelect"
                                onChange={(e) => handleRuleFieldChange(e, index, condition_index, "conditions")}
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
                            </div>
                            <div>
                              &nbsp;  &nbsp;
                            </div>
                            <div >
                              <Select
                                value={condition.value}
                                id="value"
                                name="value"
                                labelText=""
                                className="inputSelect"
                                onChange={(e) => handleRuleFieldChange(e, index, condition_index, "conditions")}
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
                            </div>
                            <div>
                              &nbsp;  &nbsp;
                            </div>
                            {rule.conditions.length - 1 === condition_index && (
                              <div className="conditionsAddDiv">
                                <IconButton label="" onClick={() => handleRuleConditionAdd(index)} kind='tertiary' size='sm'>  <Add size={18} /></IconButton>
                              </div>
                            )}
                            <div>
                              &nbsp;  &nbsp;
                            </div>
                            {rule.conditions.length !== 1 && (
                              <div className="conditionsAddDiv">
                                <IconButton label="" onClick={() => handleRuleConditionRemove(index, condition_index)} kind='tertiary' size='sm'>  <Subtract size={18} /></IconButton>
                              </div>
                            )}
                          </div>
                        ))}
                      </div>
                      <Button type="submit" kind='tertiary' size='sm'>
                        Submit
                      </Button>
                    </>
                  )}
                </div>
              </Stack>
            </Form >
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

export default AddRule;