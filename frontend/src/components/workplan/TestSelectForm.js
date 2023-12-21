import React, { useEffect, useRef, useState } from "react";
import { Column, Grid, Select, SelectItem } from "@carbon/react";
import { FormattedMessage, injectIntl } from "react-intl";
import "../Style.css";
import { getFromOpenElisServer } from "../utils/Utils";

function TestSelectForm(props) {
  const mounted = useRef(false);
  const [tests, setTests] = useState([]);
  const [defaultTestId, setDefaultTestId] = useState("");
  const [defaultTestLabel, setDefaultTestLabel] = useState("");

  const handleChange = (e) => {
    props.value(e.target.value, e.target.selectedOptions[0].text);
  };

  const getTests = (res) => {
    if (mounted.current) {
      setTests(res);
    }
  };

  useEffect(() => {
    mounted.current = true;
    let testId = new URLSearchParams(window.location.search).get(
      "test_id"
    );
    testId = testId ? testId : ""; 
    getFromOpenElisServer("/rest/tests", (fetchedTests) => {
      let test = fetchedTests.find(test => test.id === testId);
      let testLabel = test ? test.value : "";
      setDefaultTestId(testId);
      setDefaultTestLabel(testLabel);
      props.value(testId, testLabel);
      getTests(fetchedTests);
    })
    return () => {
      mounted.current = false;
    };
  }, []);

  return (
    <>
      <Grid fullWidth={true}>
        <Column lg={16}>
          <Select
            defaultValue="placeholder-item"
            id="select-1"
            invalidText={
              <FormattedMessage id="workplan.panel.selection.error.msg" />
            }
            helperText={props.title}
            labelText=""
            onChange={handleChange}
          >
            <SelectItem text={defaultTestLabel} value={defaultTestId} />
            {tests
              .filter(item => item.id !== defaultTestId)
              .map((item, idx) => {
                return <SelectItem key={idx} text={item.value} value={item.id} />;
            })}
          </Select>
        </Column>
      </Grid>
    </>
  );
}

export default injectIntl(TestSelectForm);
