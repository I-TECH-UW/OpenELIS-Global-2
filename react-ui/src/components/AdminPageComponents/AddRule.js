import { useState } from "react";
import { Form, Stack, TextInput, Select, SelectItem, Button, InlineLoading, IconButton ,Search } from '@carbon/react';
import { Add, Subtract } from '@carbon/react/icons';
import Autocomplete from "../inputComponents/AutoComplete";
function AddRule() {
  const [ruleList, setRuleList] = useState([{
    rule: "",
    condition: ""
  }]);

  const handleRuleChange = (e, index) => {
    const { name, value } = e.target;
    const list = [...ruleList];
    list[index][name] = value;
    setRuleList(list);
    console.log(JSON.stringify(list))
  };

  const handleRuleRemove = (index) => {
    const list = [...ruleList];
    list.splice(index, 1);
    setRuleList(list);
  };

  const handleRuleAdd = () => {
    setRuleList([...ruleList, { rule: "" }]);
  };

  const handleSubmit = () => {
    setRuleList([...ruleList, { rule: "" }]);
  };


  return (
    <Form
      onSubmit={handleSubmit}
    >
      <Stack gap={7}>
        {ruleList.map((singleRule, index) => (
          <div key={index} className="rules">
            <div className="first-division">
              <TextInput
                name="rule"
                className="inputText"
                type="text"
                id="rule"
                labelText="Rule Name"
                value={singleRule.rule}
                onChange={(e) => handleRuleChange(e, index)}
                required
              />
              <div className="ruleBody">
                <div className="section">
                  <div className="inlineDiv">
                    <div >
                      If &nbsp;
                    </div>
                    <div >
                      <Select
                        defaultValue={singleRule.condition}
                        id="select"
                        name="condition"
                        labelText=""
                        className="inputSelect"
                        onChange={(e) => handleRuleChange(e, index)}
                      // disabled={this.state.hasSubmitted}
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
                  <div className="inlineDiv">
                    <div>
                      If a Sample is &nbsp;
                    </div>
                    <div >
                      <Select
                        defaultValue={singleRule.condition}
                        id="select"
                        name="sample"
                        labelText=""
                        className="inputSelect"
                        onChange={(e) => handleRuleChange(e, index)}
                      // disabled={this.state.hasSubmitted}
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
                      <Autocomplete suggestions={["CD4Count", "CD4Percent", "CD3Count", "CD3Absolute"]}/>
                    </div>
                    <div>
                      &nbsp;  &nbsp;
                    </div>
                    <div >
                      <Select
                        defaultValue={singleRule.condition}
                        id="select"
                        name="sample"
                        labelText=""
                        className="inputSelect"
                        onChange={(e) => handleRuleChange(e, index)}
                      // disabled={this.state.hasSubmitted}
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
                  </div>
                </div>
              </div>
              {ruleList.length - 1 === index && ruleList.length < 4 && (
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
      </Stack>
    </Form >

  );
}

export default AddRule;