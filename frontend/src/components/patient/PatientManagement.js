import React, { useState } from "react";
import { FormattedMessage, useIntl, injectIntl } from "react-intl";
import "../Style.css";
import { Heading, Grid, Column, Section, Button, Breadcrumb, BreadcrumbItem, } from "@carbon/react";
import SearchPatientForm from "./SearchPatientForm";
import CreatePatientForm from "./CreatePatientForm";

function PatientManagement() {
  const intl = useIntl();
  const [selectedPatient, setSelectedPatient] = useState({});
  const [searchPatientTab, setSearchPatientTab] = useState({
    kind: "primary",
    active: true,
  });
  const [newPatientTab, setNewPatientTab] = useState({
    kind: "tertiary",
    active: false,
  });

  const handleSearchPatientTab = () => {
    setNewPatientTab({ kind: "tertiary", active: false });
    setSearchPatientTab({ kind: "primary", active: true });
  };

  const handleNewPatientTab = () => {
    setNewPatientTab({ kind: "primary", active: true });
    setSearchPatientTab({ kind: "tertiary", active: false });
  };

  const getSelectedPatient = (patient) => {
    setSelectedPatient(patient);
    setNewPatientTab({ kind: "primary", active: true });
    setSearchPatientTab({ kind: "tertiary", active: false });
  };

  return (
    <>
      <Grid fullWidth={true}>
        <Column lg={16}>
          <Breadcrumb>
            <BreadcrumbItem href="/">
              {intl.formatMessage({ id: "home.label" })}
            </BreadcrumbItem>
            {newPatientTab.active && (
              <BreadcrumbItem href="/PatientManagement">
                {intl.formatMessage({
                  id: "patient.label.modify",
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
                <FormattedMessage id="patient.label.modify" />
              </Heading>
            </Section>
          </Section>
        </Column>
      </Grid>
      <br></br>
      <div className="orderLegendBody">
        <div className="tabsLayout">
          <Button kind={searchPatientTab.kind} onClick={handleSearchPatientTab}>
            <FormattedMessage
              id="search.patient.label"
              defaultMessage="Search for Patient"
            />
          </Button>
          <Button kind={newPatientTab.kind} onClick={handleNewPatientTab}>
            <FormattedMessage
              id="new.patient.label"
              defaultMessage="New Patient"
            />
          </Button>
        </div>
        {searchPatientTab.active && (
          <SearchPatientForm
            getSelectedPatient={getSelectedPatient}
          ></SearchPatientForm>
        )}

        <br></br>
        {newPatientTab.active && (
          <CreatePatientForm
            showActionsButton={true}
            selectedPatient={selectedPatient}
          ></CreatePatientForm>
        )}
      </div>
    </>
  );
}

export default injectIntl(PatientManagement);
