import React from "react";
import { SearchSkeleton } from "@carbon/react";
import PatientHeader from "../../common/PatientHeader"; // Ensure this path is correct
import { FormattedMessage } from "react-intl";
import { openPatientResults } from "./searchService";
import "./searchBar.css";

function SearchOutput({ loading, patientData }) {
  return (
    <div>
      {loading ? (
        <SearchSkeleton />
      ) : patientData.length > 0 ? (
        patientData.map((patient, index) =>
          patient && patient.patientID ? (
            <div
              key={patient.patientID}
              className="heading"
              onClick={() => openPatientResults(patient.patientID)}
            >
              <PatientHeader
                id={patient.patientID}
                lastName={patient.lastName}
                firstName={patient.firstName}
                gender={patient.gender}
                dob={patient.dob}
                nationalId={patient.nationalId}
                accessionNumber={patient.accessionNumber} // Corrected typo here
                isOrderPage={true}
                className="patientHead"
              />
            </div>
          ) : (
            <div key={index}></div>
          ),
        )
      ) : (
        <div></div>
      )}
    </div>
  );
}

export default SearchOutput;
