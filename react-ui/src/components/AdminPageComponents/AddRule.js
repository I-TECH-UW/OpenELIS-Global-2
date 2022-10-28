import { useState } from "react";
import "./test.css"
import { Form, Stack, TextInput, Select, SelectItem, Button, InlineLoading, IconButton } from '@carbon/react';
import { Add, Subtract } from '@carbon/react/icons';
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
                value={singleRule.rule}
                onChange={(e) => handleRuleChange(e, index)}
                required
              />

              <Select
                defaultValue={singleRule.condition}
                id="select"
                name="condition"
                labelText="Select"
                className="inputText"
                onChange={(e) => handleRuleChange(e, index)}
              // disabled={this.state.hasSubmitted}
              >
                <SelectItem
                  text=""
                  value=""
                />
                <SelectItem
                  text="Equals"
                  value="equal"
                />
                <SelectItem
                  text="Not Equla"
                  value="not_equal"
                />
              </Select>
              {ruleList.length - 1 === index && ruleList.length < 4 && (
                <button
                  onClick={handleRuleAdd}
                  className="add-btn"
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