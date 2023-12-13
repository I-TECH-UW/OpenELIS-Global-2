import React, { useEffect, useRef, useState } from "react";
import { Button, Stack } from "@carbon/react";
import SearchPatientForm from "../patient/SearchPatientForm";
import CreatePatientForm from "../patient/CreatePatientForm";
import { FormattedMessage } from "react-intl";

const PatientInfo = (props) => {
  const { orderFormValues, setOrderFormValues ,error } = props;
  const componentMounted = useRef(false);
  const [searchPatientTab, setSearchPatientTab] = useState({
    kind: "primary",
    active: true,
  });
  const [newPatientTab, setNewPatientTab] = useState({
    kind: "tertiary",
    active: false,
  });
  const [selectedPatient, setSelectedPatient] = useState({
    id: "",
    healthRegion: [],
  });

  const getSelectedPatient = (patient) => {
    setSelectedPatient(patient);
    if (orderFormValues) {
      setOrderFormValues({
        ...orderFormValues,
        patientUpdateStatus: "UPDATE",
        patientProperties: patient,
      });

    }
    handleNewPatientTab();
  };

  const handleSearchPatientTab = () => {
    setSearchPatientTab({ kind: "primary", active: true });
    setNewPatientTab({ kind: "tertiary", active: false });
  };

  const handleNewPatientTab = () => {
    setNewPatientTab({ kind: "primary", active: true });
    setSearchPatientTab({ kind: "tertiary", active: false });
  };

  useEffect(() => {
    componentMounted.current = true;

    if (
      orderFormValues.patientProperties.firstName !== "" ||
      orderFormValues.patientProperties.guid !== ""
    ) {
      handleNewPatientTab();
    }
    window.scrollTo(0, 0);
    return () => {
      componentMounted.current = false;
    };
  }, []);

  return (
    <>
      <Stack gap={10}>
        <div className="orderLegendBody">
          <h3>
            <FormattedMessage id="banner.menu.patient" />
          </h3>
          <div className="tabsLayout">
            <Button
              kind={searchPatientTab.kind}
              onClick={handleSearchPatientTab}
            >
              <FormattedMessage id="search.patient.label" />
            </Button>
            <Button kind={newPatientTab.kind} onClick={handleNewPatientTab}>
              <FormattedMessage id="new.patient.label" />
            </Button>
          </div>
          <div className="container">
            {searchPatientTab.active && (
              <SearchPatientForm getSelectedPatient={getSelectedPatient} />
            )}
            {newPatientTab.active && (
              <CreatePatientForm
                showActionsButton={false}
                selectedPatient={selectedPatient}
                orderFormValues={orderFormValues}
                setOrderFormValues={setOrderFormValues}
                error={error}
              />
            )}
          </div>
        </div>
      </Stack>
    </>
  );
};

export default PatientInfo;
