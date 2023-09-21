import React, { useState, useEffect, useRef } from "react";
import SearchPatientForm from "../patient/SearchPatientForm";
import { Button, Column, TextInput ,Grid  ,Form} from "@carbon/react";

function SearchOrder() {
  const [selectedPatient, setSelectedPatient] = useState({});
  const componentMounted = useRef(false);
  const [accessionNumber, setAccessionNumber] = useState("");

  const getSelectedPatient = (patient) => {
    setSelectedPatient(patient);
    console.log("selectedPatient:" + selectedPatient);
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
    window.location.href = "/ModifyOrder?accessionNumber=" + accessionNumber;
  };

  return (
    <>
      <div className="orderLegendBody">
        <Form onSubmit={handleSearch}>
          <Grid>
            <Column  lg={16}>
            <h4>Search By Accesion</h4>
            </Column>
            <Column lg={4}>
              <TextInput
                type="text"
                value={accessionNumber}
                onChange={(e) => setAccessionNumber(e.target.value)}
              />
            </Column>
            <Column lg={2}>
              <Button type="submit">Search</Button>
            </Column>
          </Grid>
        </Form>
      </div>
      <div className="orderLegendBody">
        <h4>Search By Patient</h4>
        <SearchPatientForm
          getSelectedPatient={getSelectedPatient}
        ></SearchPatientForm>
      </div>
    </>
  );
}

export default SearchOrder;
