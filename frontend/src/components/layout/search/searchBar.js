import React, { useState, useEffect, useCallback } from "react";
import { Search, Tag, Button, Layer, SearchSkeleton, Tile, Grid, Row, Column } from "@carbon/react";
import { fetchMenuData, filterMenuItems, fetchPatientData, fetchPatientDetails } from "./searchService";
import { FormattedMessage, useIntl } from "react-intl";
import debounce from "lodash.debounce";

const SearchBar = () => {
  const [searchInput, setSearchInput] = useState("");
  const [tags, setTags] = useState([]);
  const [menuData, setMenuData] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [filteredMenuItems, setFilteredMenuItems] = useState([]);
  const [patientData, setPatientData] = useState([]);
  const [searchHistory, setSearchHistory] = useState([]);
  const [filteredHistory, setFilteredHistory] = useState([]);
  const [loading, setLoading] = useState(true);
  const [remove, setRemove] = useState(false);
  const [historys,setHistorys] =useState(true)
  const intl = useIntl();

  useEffect(() => {
    fetchMenuData((data) => {
      setMenuData(data);
    });
  }, []);

  useEffect(() => {
    const savedHistory = JSON.parse(localStorage.getItem("searchHistory")) || [];
    setSearchHistory(savedHistory);
    setTags(savedHistory);
  }, []);

  useEffect(() => {
    if (searchQuery) {
      const filteredItems = filterMenuItems(menuData, searchQuery);
      setFilteredMenuItems(filteredItems);
      if (/^\d+$/.test(searchQuery)) {
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

    const filteredItems = filterMenuItems(menuData, value);
    setFilteredMenuItems(filteredItems);
    setLoading(false);
  };

  const handleTagClick = (tag) => {
    setSearchInput(tag);
    setSearchQuery(tag);
  };

  const handleClearHistory = () => {
    setTags([]);
    setSearchHistory([]);
    localStorage.removeItem("searchHistory");
  };

  const handleSearch = () => {
    const filteredItems = filterMenuItems(menuData, searchInput);
    setFilteredMenuItems(filteredItems);
    if (
      searchInput.trim() &&
      !tags.includes(searchInput) &&
      (filteredItems.length > 0 || patientData.length > 0)
    ) {
      setTags([...tags, searchInput]);
      setSearchHistory((prevHistory) => {
        const newHistory = [searchInput, ...prevHistory.filter((item) => item !== searchInput)];
        localStorage.setItem("searchHistory", JSON.stringify(newHistory));
        return newHistory.length > 5 ? newHistory.slice(0, 5) : newHistory;
      });
      setSearchInput("");
    }
  };

  const handleRemoveMenu = () => {
setHistorys(!historys)
    setRemove(!remove);
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
      {(tags.length > 0 || searchInput.trim() === "") && (
        <div style={{ position: "absolute", top: 50, right: 100, width: 400 }}>
        { historys && <div>
          <Button className="button" onClick={handleClearHistory} >
            <b>
              <FormattedMessage id="label.button.clear" />
            </b>
          </Button>
          <Grid>
            <Row>
              {filteredHistory.map((tag, index) => (
                <Column key={index}>
                  <Tag className="tag" onClick={() => handleTagClick(tag)}>
                    {tag}
                  </Tag>
                </Column>
              ))}
            </Row>
          </Grid>
          </div>}
          <Button className="button" onClick={handleRemoveMenu}   style={{ position:"absolute", right: -150 , top: 10 }} >
            <b>
              <FormattedMessage id="header.results.recorded" />
            </b>
          </Button>
        </div>
      )}
      {remove && (
        <div style={{ position: "absolute", top: 45, right: 3 }}>
          {loading ? (
            <SearchSkeleton />
          ) : (
            <Layer style={{ position: "absolute", padding: "20px", top: "70px", right: "2px", zIndex: 2, backgroundColor: "white", border: "1px solid #ddd", boxShadow: "0 2px 4px rgba(0,0,0,0.1)", maxHeight: "500px", overflowY: "auto", width: "400px" }}>
            {filteredMenuItems.length > 0 && (
              <Tile>
                {filteredMenuItems.map((item) => (
                  <Tile
                    key={item.menu.elementId}
                    onClick={() => (window.location.href = item.menu.actionURL)}
                    style={{ cursor: "pointer", padding: "0.5rem", border: "1px solid #ccc", borderRadius: "4px", backgroundColor: "#f9f9f9", marginBottom: "0.5rem" }}
                  >
                    <FormattedMessage id={item.menu.displayKey} />
                    {item.childMenus && item.childMenus.length > 0 && (
                      <Layer style={{ marginTop: "0.5rem", display: "grid", gridTemplateColumns: "repeat(auto-fit, minmax(150px, 1fr))", gap: "0.5rem" }}>
                        {item.childMenus.map((child) => (
                          <Tile
                            key={child.menu.elementId}
                            onClick={() => (window.location.href = child.menu.actionURL)}
                            style={{ cursor: "pointer", padding: "0.5rem", border: "1px solid #ccc", borderRadius: "4px", backgroundColor: "#f9f9f9" }}
                          >
                            <FormattedMessage id={child.menu.displayKey} />
                          </Tile>
                        ))}
                      </Layer>
                    )}
                  </Tile>
                ))}
              </Tile>
            )}
            {patientData.length > 0 && (
              <Tile>
                <h3><FormattedMessage id="patient.label" /> <FormattedMessage id="sidenav.label.results" /></h3>
                {patientData.map((patient) => (
                  <Layer key={patient.patientID} style={{ cursor: "pointer", padding: "0.5rem", border: "1px solid #ccc", borderRadius: "4px", backgroundColor: "#f9f9f9", marginBottom: "0.5rem" }}>
                    <strong>{patient.firstName} {patient.lastName}</strong><br />
                    <FormattedMessage id="patient.gender" />: {patient.gender}<br />
                    <FormattedMessage id="patient.dob" />: {patient.birthdate}<br />
                    <FormattedMessage id="eorder.id.national" />: {patient.nationalId}<br />
                    <FormattedMessage id="patient.id" />: {patient.patientID}<br />
  
                  {/* <FormattedMessage id="" /> "patientLastUpdated": {patient.patientLastUpdated}
                  <FormattedMessage id="" /> "personLastUpdated": {patient.personLastUpdated}
                  <FormattedMessage id="" /> "patientPK": {patient.patientPK}
                  <FormattedMessage id="" /> "subjectNumber": {patient.subjectNumber} */}
                  <FormattedMessage id="patient.natioanalid" /> : {patient.nationalId}
                  {/* <FormattedMessage id="" /> "guid": {patient.guid}
                  <FormattedMessage id="" /> "aka": {patient.aka}
                  <FormattedMessage id="" /> "mothersName": {patient.mothersName}
                  <FormattedMessage id="" /> "mothersInitial": {patient.mothersInitial} */}
                  <FormattedMessage id="patient.address.street" /> : {patient.streetAddress}
                  <FormattedMessage id="patient.address.town" /> : {patient.city}
                  <FormattedMessage id="patient.emergency.additional.camp" /> : {patient.commune}
                  <FormattedMessage id="order.department.label" /> : {patient.addressDepartment}
                  {/* <FormattedMessage id="" /> "insuranceNumber": {patient.insuranceNumber}
                  <FormattedMessage id="" /> "occupation": {patient.occupation} */}
                  <FormattedMessage id="patient.label.primaryphone" /> : {patient.primaryPhone}
                  <FormattedMessage id="patient.address.healthregion" /> : {patient.healthRegion}
                  <FormattedMessage id="pateint.eduction" /> : {patient.education}
                  <FormattedMessage id="patient.maritalstatus" /> : {patient.maritalStatus}
                  <FormattedMessage id="patient.nationality" /> : {patient.nationality}
                  <FormattedMessage id="patient.address.healthdistrict" /> : {patient.healthDistrict}
                  <FormattedMessage id="patient.nationality.other" />  : {patient.otherNationality}
                  {/* <FormattedMessage id="" /> "readOnly": {patient.readOnly}
                  <FormattedMessage id="" /> "stnumber": {patient.stnumber}
  } */}
                  </Layer>
                ))}
              </Tile>
            )}
            {filteredMenuItems.length === 0 && patientData.length === 0 && (
              <p><FormattedMessage id="result.search.noresult" /></p>
            )}
          </Layer>
          )}
        </div>
      )}
    </>
  );
};

export default SearchBar;
