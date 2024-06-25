import React from "react";
import { SkeletonPlaceholder, SkeletonText } from "@carbon/react";
import PatientHeader from "../../common/PatientHeader"; // Ensure this path is correct
import { openPatientResults } from "./searchService";
import "./searchBar.css";

function SearchOutput({ loading, patientData }) {
  return (
    <div>
      {loading ? (
        <div>
          <SkeletonText heading />
          <SkeletonText width="400px" />
          <SkeletonText width="300px" />
        </div>
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
                accessionNumber={patient.accessionNumber}
                isOrderPage={true}
                className="patientHead"
              />
            </div>
          ) : (
            <div key={index}></div>
          ),
        )
      ) : (
        <div>
          <SkeletonText heading />
          <SkeletonText width="400px" />
          <SkeletonText width="300px" />
        </div>
      )}
    </div>
  );
}

export default SearchOutput;
