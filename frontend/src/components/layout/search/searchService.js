import { getFromOpenElisServer } from "../../utils/Utils";

export const fetchPatientData = (query, callback) => {
  const [firstName, lastName] = query.split(" ");
  const dob = query; 
  const guid = query; 
  const nationalID = query; 
  const patientID = query; 
  const subjectNumber = query; 

  const endpoints = [
    `/rest/patient-search-results?dateOfBirth=${dob}`,
    `/rest/patient-search-results?guid=${guid}`,
    `/rest/patient-search-results?nationalID=${nationalID}`,
    `/rest/patient-search-results?patientID=${patientID}`,
    `/rest/patient-search-results?subjectNumber=${subjectNumber}`,
    `/rest/patient-search-results?firstName=${firstName}`,
    `/rest/patient-search-results?lastName=${lastName || query}`,
    `/rest/patient-search-results?gender=${query}`,
    lastName ? `/rest/patient-search-results?firstName=${firstName}&lastName=${lastName}` : null
  ].filter(Boolean); // Filter out null values

  let isMounted = true;

 

  Promise.all(endpoints.map(endpoint =>
    new Promise((resolve) => {
      getFromOpenElisServer(endpoint, (response) => {
        
        if (response && response.patientSearchResults) {
          resolve(response.patientSearchResults);
        } else {
          resolve([]);
        }
      });
    })
  )).then(results => {
    if (isMounted) {
      const combinedResults = [].concat(...results);
      const uniqueResults = combinedResults.filter((value, index, self) =>
        index === self.findIndex((t) => (
          t.patientID === value.patientID
        ))
      );
      
      
      callback(uniqueResults);
    }
  });

  return () => {
    isMounted = false;
  };
};


import React from "react";
import PropTypes from "prop-types";

export const SuggestionList = ({ suggestions, onSelect }) => {
  return (
    <ul className="suggestions">
      {suggestions.map((suggestion, index) => (
        <li key={index} onClick={() => onSelect(suggestion)}>
          {suggestion}
        </li>
      ))}
    </ul>
  );
};

SuggestionList.propTypes = {
  suggestions: PropTypes.arrayOf(PropTypes.string).isRequired,
  onSelect: PropTypes.func.isRequired,
};
