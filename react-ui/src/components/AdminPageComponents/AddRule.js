import { useState } from "react";
import "./test.css"

function AddRule() {
  const [ruleList, setRuleList] = useState([{ rule: "" }]);

  const handleRuleChange = (e, index) => {
    const { name, value } = e.target;
    const list = [...ruleList];
    list[index][name] = value;
    setRuleList(list);
  };

  const handleRuleRemove = (index) => {
    const list = [...ruleList];
    list.splice(index, 1);
    setRuleList(list);
  };

  const handleRuleAdd = () => {
    setRuleList([...ruleList, { rule: "" }]);
  };

  return (
    <form className="App" autoComplete="off">
      <div className="form-field">
        <label htmlFor="rule">Service(s)</label>
        {ruleList.map((singleRule, index) => (
          <div key={index} className="rules">
            <div className="first-division">
              <input
                name="rule"
                type="text"
                id="rule"
                value={singleRule.rule}
                onChange={(e) => handleRuleChange(e, index)}
                required
              />
              {ruleList.length - 1 === index && ruleList.length < 4 && (
                <button
                  type="button"
                  onClick={handleRuleAdd}
                  className="add-btn"
                >
                  <span>Add a Service</span>
                </button>
              )}
            </div>
            <div className="second-division">
              {ruleList.length !== 1 && (
                <button
                  type="button"
                  onClick={() => handleRuleRemove(index)}
                  className="remove-btn"
                >
                  <span>Remove</span>
                </button>
              )}
            </div>
          </div>
        ))}
      </div>
      <div className="output">
        <h2>Output</h2>
        {ruleList &&
          ruleList.map((singleRule, index) => (
            <ul key={index}>
              {singleRule.rule && <li>{singleRule.rule}</li>}
            </ul>
          ))}
      </div>
    </form>
  );
}

export default AddRule;