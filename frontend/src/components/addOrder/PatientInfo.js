import React, { useEffect, useRef, useState } from "react";
import { Button, Stack } from "@carbon/react";
import SearchPatientForm from "../common/SearchPatientForm";
import CreatePatientForm from "../common/CreatePatientForm";

const PatientInfo = (props) => {
  const { orderFormValues, setOrderFormValues } = props;
  const componentMounted = useRef(true);
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
          <h3>PATIENT</h3>
          <div className="tabsLayout">
            <Button
              kind={searchPatientTab.kind}
              onClick={handleSearchPatientTab}
            >
              Search for Patient
            </Button>
            <Button kind={newPatientTab.kind} onClick={handleNewPatientTab}>
              New Patient
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
                getSelectedPatient={getSelectedPatient}
              />
            )}
          </div>
        </div>
      </Stack>
    </>
  );
};

export default PatientInfo;
