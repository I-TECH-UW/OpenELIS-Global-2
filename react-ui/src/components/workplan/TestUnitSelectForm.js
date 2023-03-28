import React, { useState } from "react";
import { Column, Grid, Heading, Section, Select, SelectItem } from "@carbon/react";
import { injectIntl,FormattedMessage } from "react-intl";
import "../Style.css";

  function TestUnitSelectForm(props) {
  const [article, setArticle] = useState({
    title: "",
    body: "",
  });

  const { formatMessage } = props.intl;

  const type = props.type
  const handleForm = (e) => {

  };

  const handleInputs = (e) => {

  };

  return (
    <>
    <Column lg={16}>
        <Section>
            <h5><FormattedMessage id="workplan.unit.types" /></h5>
        </Section>
    </Column>
    <Select
      defaultValue="placeholder-item"
      id="select-1"
      invalidText="This is an invalid error message."
      labelText={formatMessage({id:"workplan.unit.types"})}
    >
      <SelectItem
        text="Option 1"
        value="option-1"
      />
      <SelectItem
        text="Option 2"
        value="option-2"
      />
      <SelectItem
        text="Option 3"
        value="option-3"
      />
    </Select>
    </>
  );
}

export default injectIntl(TestUnitSelectForm);