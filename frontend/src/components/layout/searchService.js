import React, { useState, useEffect, useCallback } from "react";
import { getFromOpenElisServer } from "../utils/Utils";
import { TextInput } from "@carbon/react";
import { FormattedMessage } from "react-intl";
import debounce from "lodash.debounce";

const fetchMenuData = (callback) => {
  getFromOpenElisServer("/rest/menu", callback);
};

const flattenMenuItems = (menuItems) => {
  return menuItems.reduce((acc, item) => {
    acc.push(item);
    if (item.childMenus && item.childMenus.length > 0) {
      acc = acc.concat(flattenMenuItems(item.childMenus));
    }
    return acc;
  }, []);
};

const filterMenuItems = (menuItems, query) => {
  if (!query) return menuItems;

  const lowerCaseQuery = query.toLowerCase();

  return flattenMenuItems(menuItems).filter((item) => {
    return (
      item.menu.displayKey.toLowerCase().includes(lowerCaseQuery) ||
      item.menu.toolTipKey.toLowerCase().includes(lowerCaseQuery)
    );
  });
};

const fetchPatientDetails = (patientID, callback) => {
  const endpoint = `/rest/patient-details?patientID=${patientID}`;
  let isMounted = true;

  getFromOpenElisServer(endpoint, (response) => {
    if (isMounted) {
      if (response) {
        callback([response]); // Wrap the response in an array for consistency
      } else {
        callback([]);
      }
    }
  });

  return () => {
    isMounted = false;
  };
};

const fetchPatientData = (query, callback) => {
  const endpoints = [
    `/rest/patient-search-results?firstName=${query}`,
    `/rest/patient-search-results?lastName=${query}`,
    `/rest/patient-search-results?gender=${query}`
  ];

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

const GlobalSearch = () => {
  const [menuData, setMenuData] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [filteredMenuItems, setFilteredMenuItems] = useState([]);
  const [patientData, setPatientData] = useState([]);

  useEffect(() => {
    fetchMenuData((data) => {
      setMenuData(data);
    });
  }, []);

  useEffect(() => {
    const filteredItems = filterMenuItems(menuData, searchQuery);
    setFilteredMenuItems(filteredItems);

    if (searchQuery) {
      if (/^\d+$/.test(searchQuery)) {
        // If the search query is a number, treat it as a patient ID
        fetchPatientDetails(searchQuery, (response) => {
          setPatientData(response);
        });
      } else {
        fetchPatientData(searchQuery, (response) => {
          setPatientData(response);
        });
      }
    } else {
      setPatientData([]);
    }
  }, [searchQuery, menuData]);

  const debouncedSearchChange = useCallback(
    debounce((value) => setSearchQuery(value), 300),
    []
  );

  const handleSearchChange = (event) => {
    debouncedSearchChange(event.target.value);
  };
  const handleFocus = (event) => {
    event.target.select();
  };

  return (
    <div style={{ position: "relative", padding: "20px",top:50 }}>
      <div style={{ width: 300 }}>
        <TextInput
          id="global-search-input"
          labelText={<FormattedMessage id="advanced.search" />}
          value={searchQuery}
          onChange={handleSearchChange}
          onFocus={handleFocus}
        />
      </div>
      <div
        style={{
          display: searchQuery ? "block" : "none",
          position: "absolute",
          top: "90px",
          left: "0",
          zIndex: 2,
          backgroundColor: "blue",
          border: "1px solid #ccc",
          boxShadow: "0 2px 4px rgba(0,0,0,0.1)",
          borderRadius: "4px",
          padding: "10px",
          maxHeight: "500px",
          overflowY: "auto",
          width: "100%",
        }}
      >
        {filteredMenuItems.length > 0 && (
          <div>
            
            {filteredMenuItems.map((item) => (
              <div
                key={item.menu.elementId}
                onClick={() => (window.location.href = item.menu.actionURL)}
                style={{
                  cursor: "pointer",
                  padding: "0.5rem",
                  border: "1px solid #ccc",
                  borderRadius: "4px",
                  backgroundColor: "#f9f9f9",
                  marginBottom: "0.5rem",
                }}
              >
                <FormattedMessage id={item.menu.displayKey} />
                {item.childMenus && item.childMenus.length > 0 && (
                  <div
                    style={{
                      marginTop: "0.5rem",
                      display: "grid",
                      gridTemplateColumns: "repeat(auto-fit, minmax(150px, 1fr))",
                      gap: "0.5rem",
                    }}
                  >
                    {item.childMenus.map((child) => (
                      <div
                        key={child.menu.elementId}
                        onClick={() => (window.location.href = child.menu.actionURL)}
                        style={{
                          cursor: "pointer",
                          padding: "0.5rem",
                          border: "1px solid #ccc",
                          borderRadius: "4px",
                          backgroundColor: "#f9f9f9",
                        }}
                      >
                        <FormattedMessage id={child.menu.displayKey} />
                      </div>
                    ))}
                  </div>
                )}
              </div>
            ))}
          </div>
        )}
        {patientData.length > 0 && (
          <div>
            <h3><FormattedMessage id="patient.label" /> <FormattedMessage id ="sidenav.label.results"/> </h3>
            {patientData.map((patient) => (
              <div
                key={patient.patientID}
                style={{
                  cursor: "pointer",
                  padding: "0.5rem",
                  border: "1px solid #ccc",
                  borderRadius: "4px",
                  backgroundColor: "#f9f9f9",
                  marginBottom: "0.5rem",
                }}
              >
                <strong>{patient.firstName} {patient.lastName}</strong><br />
                <FormattedMessage id="patient.gender" />: {patient.gender}<br />
                <FormattedMessage id="patient.dob" />: {patient.birthDateForDisplay}<br />
                <FormattedMessage id="eorder.id.national" />: {patient.nationalId}<br />
                <FormattedMessage id="eorder.id.subjectNumber" />: {patient.subjectNumber}<br />
                <FormattedMessage id="patient.id" />: {patient.patientID}<br />
                {patient.patientPK && <div>Patient PK: {patient.patientPK}</div>}
                {patient.primaryPhone && <div> <FormattedMessage id="patient.label.contactphone" />: {patient.primaryPhone}</div>}
                {patient.healthDistrict && <div><FormattedMessage id="patient.address.healthdistrict" />: {patient.healthDistrict}</div>}
                {patient.city && <div> <FormattedMessage id="patient.address.town" /> : {patient.city}</div>}
                {patient.addressDepartment && <div> <FormattedMessage id="order.department.label" />: {patient.addressDepartment}</div>}
                {patient.mothersName && <div>Mother's Name: {patient.mothersName}</div>}
                {/* Additional patient details */}
                {patient.guid && <div>GUID: {patient.guid}</div>}
                {patient.aka && <div>AKA: {patient.aka}</div>}
                {patient.mothersInitial && <div>Mother's Initial: {patient.mothersInitial}</div>}
                {patient.streetAddress && <div> <FormattedMessage id="patient.address.street" /> : {patient.streetAddress}</div>}
                {patient.commune && <div> <FormattedMessage id="patient.address.camp" /> : {patient.commune}</div>}
                
                
                {patient.insuranceNumber && <div>Insurance Number: {patient.insuranceNumber}</div>}
                {patient.occupation && <div>Occupation: {patient.occupation}</div>}
                {patient.healthRegion && <div>Health Region: {patient.healthRegion}</div>}
                {patient.education && <div> <FormattedMessage id="pateint.eduction" />: {patient.education}</div>}
                {patient.maritialStatus && <div> Marital Status: {patient.maritialStatus}</div>}
                {patient.nationality && <div><FormattedMessage id="patient.nationality" />: {patient.nationality}</div>}
                {patient.otherNationality && <div>Other Nationality: {patient.otherNationality}</div>}
                {patient.suspectedCase && <div> <FormattedMessage id="nonconform.label.suspected.cause.nce" />: {patient.suspectedCase}</div>}
                {patient.emergencyContact && <div> <FormattedMessage id="emergencyContactInfo.title" />: {patient.emergencyContact}</div>}
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default GlobalSearch;
