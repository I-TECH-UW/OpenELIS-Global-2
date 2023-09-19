import React, { useState } from "react";
import SearchPatientForm from "../patient/SearchPatientForm";

function ModifyOrder() {
  const [selectedPatient, setSelectedPatient] = useState({});

  const getSelectedPatient = (patient) => {
    setSelectedPatient(patient);
    console.log("selectedPatient:" + selectedPatient);
  };

  return (
    <>
     <div className="orderLegendBody">
      <SearchPatientForm
        getSelectedPatient={getSelectedPatient}
      ></SearchPatientForm>
      </div>
    </>
  );
}

export default ModifyOrder;
