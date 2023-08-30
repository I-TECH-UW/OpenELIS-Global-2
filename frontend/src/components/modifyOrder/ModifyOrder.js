import React, { useState } from "react";
import SearchPatientForm from "../common/SearchPatientForm";

function ModifyOrder() {
  const [selectedPatient, setSelectedPatient] = useState({});

  const getSelectedPatient = (patient) => {
    setSelectedPatient(patient);
    console.log("selectedPatient:" + selectedPatient);
  };

  return (
    <>
      <SearchPatientForm
        getSelectedPatient={getSelectedPatient}
      ></SearchPatientForm>
    </>
  );
}

export default ModifyOrder;
