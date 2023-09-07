import React, { useEffect, useRef, useState } from "react";
import { Column, Form, Grid, Section } from "@carbon/react";
import { FormattedMessage } from "react-intl";
import "../Style.css";
import TestSectionSelectForm from "./TestSectionSelectForm";
import TestSelectForm from "./TestSelectForm";
import PanelSelectForm from "./PanelSelectForm";
import PrioritySelectForm from "./PrioritySelectForm";
import { getFromOpenElisServer } from "../utils/Utils";

export default function WorkplanSearchForm(props) {
  const mounted = useRef(false);
  const [selectedValue, setSelectedValue] = useState("");
  const [selectedLabel, setSelectedLabel] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  let title = "";
  let urlToPost = "";
  const type = props.type;
  switch (type) {
    case "test":
      title = <FormattedMessage id="workplan.test.types" />;
      urlToPost = "/rest/workplan-by-test?test_id=";
      break;
    case "panel":
      title = <FormattedMessage id="workplan.panel.types" />;
      urlToPost = "/rest/workplan-by-panel?panel_id=";
      break;
    case "unit":
      title = <FormattedMessage id="workplan.unit.types" />;
      urlToPost = "/rest/workplan-by-test-section?test_section_id=";
      break;
    case "priority":
      title = <FormattedMessage id="workplan.priority.list" />;
      urlToPost = "/rest/workplan-by-priority?priority=";
      break;
    default:
      title = "";
  }

  const handleSelectedValue = (v, l) => {
    if (mounted.current) {
      setIsLoading(true);
      setSelectedValue(v);
      setSelectedLabel(l);
      props.selectedValue(v);
      props.selectedLabel(l);
    }
  };

  const getTestsList = (res) => {
    if (mounted.current) {
      props.createTestsList(res);
      setIsLoading(false);
    }
  };

  useEffect(() => {
    mounted.current = true;
    getFromOpenElisServer(urlToPost + selectedValue, getTestsList);
    return () => {
      mounted.current = false;
    };
  }, [selectedValue]);

  return (
    <>
      <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <h5 className="contentHeader2">
              <FormattedMessage id="label.form.searchby" />
              &nbsp; {title}{" "}
            </h5>
          </Section>
        </Column>
      </Grid>
      <Grid fullWidth={true}>
        <Column lg={6}>
          <Form className="container-form">
            {type === "test" && (
              <TestSelectForm title={title} value={handleSelectedValue} />
            )}
            {type === "panel" && (
              <PanelSelectForm title={title} value={handleSelectedValue} />
            )}
            {type === "unit" && (
              <TestSectionSelectForm
                title={title}
                value={handleSelectedValue}
              />
            )}
            {type === "priority" && (
              <PrioritySelectForm title={title} value={handleSelectedValue} />
            )}
          </Form>
        </Column>
        <Column lg={4}>
          {isLoading && (
            <img
              src={`images/loading.gif`}
              alt="Loading ..."
              width="60"
              height="60"
            />
          )}
        </Column>
      </Grid>
      <hr />
      <br />
      <Grid fullWidth={true}>
        <Column lg={16}>
          {selectedLabel && (
            <Section>
              <h4 className="contentHeader1">&nbsp; {selectedLabel} </h4>
            </Section>
          )}
        </Column>
      </Grid>
    </>
  );
}
