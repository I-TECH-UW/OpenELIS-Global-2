import React, { useState, useEffect, useCallback } from "react";
import { Search, Button, SearchSkeleton, Grid, Row, Column } from "@carbon/react";
import { fetchMenuData, filterMenuItems, fetchPatientData, fetchPatientDetails, openPatientResults, handleSearchByLabNo } from "./searchService";
import { FormattedMessage, useIntl } from "react-intl";
import debounce from "lodash.debounce";
import PatientHeader from "../../common/PatientHeader";

const SearchBar = () => {
  const [searchInput, setSearchInput] = useState("");
  const [menuData, setMenuData] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [filteredMenuItems, setFilteredMenuItems] = useState([]);
  const [patientData, setPatientData] = useState([]);
  const [searchHistory, setSearchHistory] = useState([]);
  const [filteredHistory, setFilteredHistory] = useState([]);
  const [loading, setLoading] = useState(false);
  const [remove, setRemove] = useState(false);
  const [historys, setHistorys] = useState(true);
  const intl = useIntl();

  useEffect(() => {
    fetchMenuData((data) => {
      setMenuData(data);
    });
  }, []);

  useEffect(() => {
    const savedHistory = JSON.parse(localStorage.getItem("searchHistory")) || [];
    setSearchHistory(savedHistory);
  }, []);

  useEffect(() => {
    if (searchQuery.trim()) {
      setLoading(true);
      const filteredItems = filterMenuItems(menuData, searchQuery);
      setFilteredMenuItems(filteredItems);
      const fetchData = /^\d+$/.test(searchQuery) ? fetchPatientDetails : fetchPatientData;
      fetchData(searchQuery, (response) => {
        setPatientData(response);
        setLoading(false);
      });
    } else {
      setPatientData([]);
      setFilteredMenuItems([]);
    }
  }, [searchQuery, menuData]);

  useEffect(() => {
    const newFilteredHistory = searchHistory.filter((item) =>
      item.toLowerCase().includes(searchInput.toLowerCase())
    );
    setFilteredHistory(newFilteredHistory);
  }, [searchInput, searchHistory]);

  const debouncedSearchChange = useCallback(
    debounce((value) => setSearchQuery(value), 300),
    []
  );

  const handleSearchChange = (event) => {
    const value = event.target.value;
    setSearchInput(value);
    debouncedSearchChange(value);
    
  };

  const handleHistoryClick = (historyItem) => {
    setSearchInput(historyItem);
    setSearchQuery(historyItem);
  };

  const handleClearHistory = () => {
    setSearchHistory([]);
    localStorage.removeItem("searchHistory");
  };

  const handleSearch = () => {
    const isAccessionNumber = /^\d+$/.test(searchInput.trim());
    if (isAccessionNumber) {
      handleSearchByLabNo(searchInput.trim(), (response) => {
        // Handle the response as needed
        console.log(response);
      });
    } else {
      // Your existing search logic for non-accession number search
      const filteredItems = filterMenuItems(menuData, searchInput);
      setFilteredMenuItems(filteredItems);
      if (
        searchInput.trim() &&
        !searchHistory.includes(searchInput) &&
        (filteredItems.length > 0 || patientData.length > 0)
      ) {
        setSearchHistory((prevHistory) => {
          const newHistory = [searchInput, ...prevHistory.filter((item) => item !== searchInput)];
          localStorage.setItem("searchHistory", JSON.stringify(newHistory));
          return newHistory.length > 5 ? newHistory.slice(0, 5) : newHistory;
        });
        setSearchInput("");
      }
    }
  };
  
  
  const handleRemoveMenu = () => {
    setHistorys(!historys);
    setRemove(!remove);
  };

  const handleOrder = (patientId) => {
    openPatientResults(patientId);
  
  };

  return (
    <>
      <div className="searchB" style={{ display: "flex", alignItems: "center" }}>
        <Search
          className="inputSearch"
          placeholder={intl.formatMessage({ id: "label.button.search" })}
          size="sm"
          value={searchInput}
          onChange={handleSearchChange}
        />
        <button className="button" style={{ marginLeft: "10px" }} onClick={handleSearch}>
          <FormattedMessage id="label.button.search" />
        </button>
      </div>
      <div
        style={{
          position: "absolute",
          padding: "5px",
          top: "50px",
          right: "5px",
          backgroundColor: "white",
          border: "1px solid #ddd",
          overflow: "auto",
          maxHeight: "400px",
          maxWidth: "600px",
          width: "auto",
          display: "flex",
          flexDirection: "column"
        }}
      >
        <div style={{ padding: "10px" }}>
          {historys && (
            <>
              <Button className="button" onClick={handleClearHistory}>
                <b>
                  <FormattedMessage id="label.button.clear" />
                </b>
              </Button>
              <Grid>
                <Row>
                  {filteredHistory.map((historyItem, index) => (
                    <Column key={index}>
                      <div
                        style={{
                          cursor: "pointer",
                          padding: "5px",
                          borderBottom: "1px solid #ddd"
                        }}
                        onClick={() => handleHistoryClick(historyItem)}
                      >
                        {historyItem}
                      </div>
                    </Column>
                  ))}
                </Row>
              </Grid>
              <Button className="button" onClick={handleRemoveMenu} style={{ marginTop: "10px" }}>
                <b>
                  <FormattedMessage id="header.results.recorded" />
                </b>
              </Button>
            </>
          )}
        </div>
        <hr style={{ width: "100%", margin: "0" }} />
        <div style={{ padding: "10px" }}>
          {remove && (
            <div>
              {loading ? (
                <SearchSkeleton />
              ) : (
                <div>
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
                            backgroundColor: "#f9f9f9",
                            marginBottom: "0.5rem"
                          }}
                        >
                          <FormattedMessage id={item.menu.displayKey} />
                          {item.childMenus && item.childMenus.length > 0 && (
                            <div
                              style={{
                                marginTop: "0.5rem",
                                display: "grid",
                                gridTemplateColumns: "repeat(auto-fit, minmax(150px, 1fr))",
                                gap: "0.5rem"
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
                                    backgroundColor: "#f9f9f9"
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
                      <h3>
                        <FormattedMessage id="patient.label" /> <FormattedMessage id="sidenav.label.results" />
                      </h3>
                      {patientData.map((patient) => (
                        <div
                          key={patient.patientID}
                          style={{
                            cursor: "pointer",
                            padding: "10px",
                            borderBottom: "1px solid #ccc",
                            borderTop: "1px solid #ccc",
                            width: "auto",
                            display: "flex",
                            backgroundColor: "#f9f9f9",
                            marginBottom: "0.5rem"
                          }}
                        >
                          <PatientHeader
                            id={patient.patientID}
                            patientName={`${patient.lastName} ${patient.firstName}`}
                            gender={patient.gender}
                            dob={patient.dob}
                            nationalId={patient.nationalId}
                            accessionNumber={patient.accessionNumber}
                            className="patientHeader"
                            isOrderPage={true}
                          />
                          <Button onClick={() => handleOrder(patient.patientID, patient.accessionNumber)} style={{ position:"absolute" , right: 10  }} >
                            <FormattedMessage id="admin.page.configuration.formEntryConfigMenu.button.modify" />
                          </Button>
                        </div>
                      ))}
                    </div>
                  )}
                </div>
              )}
            </div>
          )}
        </div>
      </div>
    </>
  );
};

export default SearchBar
