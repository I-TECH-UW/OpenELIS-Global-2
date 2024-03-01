import React, { useEffect, useRef, useState } from "react";
import { Column, Grid, Select, SelectItem } from "@carbon/react";
import { FormattedMessage, injectIntl } from "react-intl";
import "../Style.css";
import { getFromOpenElisServer } from "../utils/Utils";

function TestSelectForm(props) {
  const mounted = useRef(false);
  const [tests, setTests] = useState([]);
  const [selectedTestId, setSelectedTestId] = useState("");

  const handleChange = (e) => {
    setSelectedTestId(e.target.value); // Update the selected test ID state
    if (e.target.value !== "") {
      const selectedOptionText = e.target.selectedOptions[0].text;
      props.value(e.target.value, selectedOptionText);
    }
  };

  useEffect(() => {
    mounted.current = true;
    getFromOpenElisServer("/rest/tests", (fetchedTests) => {
      setTests(fetchedTests);
      const urlParams = new URLSearchParams(window.location.search);
      const testIdFromURL = urlParams.get("testId") || "";
      if (testIdFromURL) {
        const matchingTest = fetchedTests.find((test) => test.id === testIdFromURL);
        if (matchingTest) {
          setSelectedTestId(testIdFromURL); // Set the selected test ID if found
          props.value(testIdFromURL, matchingTest.value); // Update the parent component
        }
      }
    });
    return () => {
      mounted.current = false;
    };
  }, []);

  return (
    <>
      <Grid fullWidth={true}>
        <Column lg={16}>
          <Select
            id="select-1"
            invalidText={<FormattedMessage id="workplan.panel.selection.error.msg" />}
            helperText={props.title
            }
            labelText=""
            onChange={handleChange}
            value={selectedTestId} // Control the selected value with state
          >
            {/* The placeholder item, not selectable once others are available */}
            {selectedTestId === "" && <SelectItem text={`Select Test Type`} value="" />}
            {tests.map((item, idx) => (
              <SelectItem key={idx} text={item.value} value={item.id} />
            ))}
          </Select>
        </Column>
      </Grid>
    </>
  );
}

export default injectIntl(TestSelectForm);
