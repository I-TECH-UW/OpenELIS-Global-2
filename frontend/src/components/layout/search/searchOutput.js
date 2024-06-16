import React from "react";
import { SearchSkeleton } from "@carbon/react";
import PatientHeader from "../../common/PatientHeader"; // Ensure this path is correct
import "./searchBar.css";
function SearchOutput({ loading, patientData }) {
  return (
    <div  >
          {loading ? (
        <SearchSkeleton />
      ) : (
        patientData.map((patient) => (
          <div key={patient.patientID} >
            <PatientHeader
              id={patient.patientID}
              lastName={patient.lastName}
              firstName={patient.firstName}
              gender={patient.gender}
              dob={patient.dob}
              nationalId={patient.nationalId}
              accesionNumber={patient.accesionNumber}
              isOrderPage={true}
             className="patientHeader"
            />
          </div>
        ))
      )}
    </div>
  );
}

export default SearchOutput;
