import React, { useEffect, useState, useRef } from "react";
import "../Style.css";
import { injectIntl, FormattedMessage, useIntl } from "react-intl";
import SearchPatientForm from "../patient/SearchPatientForm";
import SampleOrder from "./SampleOrder";
import AddSample from "./AddSample";
import {
  Button,
  Form,
  Heading,
  Grid,
  Column,
  Section,
  Breadcrumb,
  BreadcrumbItem,
} from "@carbon/react";

function SampleBatchEntrySetup() {
  const intl = useIntl();
  const [source, setSource] = useState("");
  useEffect(() => {
    let sourceFromUrl = new URLSearchParams(window.location.search).get(
      "source"
    );
    let sources = [
      "WorkPlanByTest",
      "WorkPlanByPanel",
      "WorkPlanByTestSection",
      "WorkPlanByPriority",
    ];
    sourceFromUrl = sources.includes(sourceFromUrl) ? sourceFromUrl : "";
    setSource(sourceFromUrl);
  }, []);
  const [selectedPatient, setSelectedPatient] = useState({});
  const componentMounted = useRef(false);
  const [accessionNumber, setAccessionNumber] = useState("");

  const getSelectedPatient = (patient) => {
    setSelectedPatient(patient);
    console.debug("selectedPatient:" + selectedPatient);
  };

  useEffect(() => {
    componentMounted.current = true;
    openPatientResults(selectedPatient.patientPK);

    return () => {
      componentMounted.current = false;
    };
  }, [selectedPatient]);

  const openPatientResults = (patientId) => {
    if (patientId) {
      window.location.href = "/ModifyOrder?patientId=" + patientId;
    }
  };

  const handleSearch = (e) => {
    e.preventDefault();

    window.location.href = "/ModifyOrder?accessionNumber=";
  };
  return (
    <div>
      <>
        <Grid fullWidth={true}>
          <Column lg={16}>
            <Breadcrumb>
              <BreadcrumbItem href="/">
                {intl.formatMessage({ id: "home.label" })}
              </BreadcrumbItem>
              {source && (
                <BreadcrumbItem href={`/${source}`}>
                  {intl.formatMessage({
                    id: "banner.menu.workplan",
                  })}
                </BreadcrumbItem>
              )}
            </Breadcrumb>
          </Column>
        </Grid>
        <Grid fullWidth={true}>
          <Column lg={16}>
            <Section>
              <Section>
                <Heading>
                  <FormattedMessage id="sidenav.label.SampleBatchEntrySetup" />
                </Heading>
              </Section>
            </Section>
          </Column>
        </Grid>
      </>
      <>
        <div className="orderLegendBody">
          <SampleOrder />
        </div>
        <div className="orderLegendBody">
          <Grid>
            <Column lg={16}>
              <h4>
                {" "}
                <FormattedMessage id= "label.button.sample" />
              </h4>
            </Column>
            <Column lg={16}>
              <AddSample />
            </Column>
          </Grid>
        </div>
      </>
    </div>
  );
}

export default SampleBatchEntrySetup;
