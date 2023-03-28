import React, { useState } from "react";
import { Column, Form, Grid, Heading, Section } from "@carbon/react";
import { FormattedMessage } from "react-intl";
import "../Style.css";
import TestUnitSelectForm from "./TestUnitSelectForm";

export default function WorkplanSearchForm(props) {
  const [article, setArticle] = useState({
    title: "",
    body: "",
  });

  const type = props.type
  const handleForm = (e) => {

  };

  const handleInputs = (e) => {

  };

  return (
    <>
    <Column lg={16}>
        <Section>
            <h5><FormattedMessage id="label.form.searchby" /></h5>
        </Section>
    </Column>
    <Form className="container-form" onSubmit={handleForm}>
        <TestUnitSelectForm />
    </Form>
    </>
  );
}