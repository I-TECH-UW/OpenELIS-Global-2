import React, { useState } from "react";
import { Button, Search } from "@carbon/react";
import { FormattedMessage, useIntl } from "react-intl";
import SearchOutput from "./searchOutput";
import { fetchPatientData, useAutocomplete } from "./searchService";
import "./searchBar.css";

const SearchBar = (props) => {
  const [searchInput, setSearchInput] = useState("");
  const [loading, setLoading] = useState(false);
  const [patientData, setPatientData] = useState([]);
  const intl = useIntl();

  const validPatients = patientData.filter(patient => patient.patientID && patient.firstName && patient.lastName);

  const {
    textValue,
    filteredSuggestions,
    showSuggestions,
    onChange: handleAutocompleteChange,
    onClick: handleAutocompleteClick,
    onKeyDown: handleAutocompleteKeyDown,
    activeSuggestion,
    onDelete: handleAutocompleteDelete,
    setTextValue,
  } = useAutocomplete({
    value: searchInput,
    suggestions: validPatients.map((patient) => ({
      id: patient.patientID,
      value: `${patient.firstName} ${patient.lastName}`,
    })),
    allowFreeText: true,
    onDelete: (id) => {
      setPatientData((prevData) =>
        prevData.filter((patient) => patient.patientID !== id)
      );
    },
  });

  const handleClearSearch = () => {
    setSearchInput("");
    setTextValue("");
    setPatientData([]);
  };

  const handleSearch = () => {
    if (searchInput.trim()) {
      setLoading(true);
      fetchPatientData(searchInput.trim(), (results) => {
        setPatientData(results);
        setLoading(false);
      });
    }
  };

  const handleChange = (e) => {
    const userInput = e?.target?.value || "";
    setSearchInput(userInput);
    handleAutocompleteChange(e);
    setLoading(true);

    if (userInput.trim()) {
      fetchPatientData(userInput.trim(), (results) => {
        setPatientData(results);
        setLoading(false);
      });
    } else {
      setLoading(false);
    }
  };

  const handleSuggestionClick = (e, id, suggestion) => {
    setSearchInput(suggestion.value);
    handleAutocompleteClick(e, id, suggestion);
  };

  const handleSuggestionDelete = (id) => {
    handleAutocompleteDelete(id);
  };

  return (
    <div className="main">
      <div className="search-bar-container">
        <Search
          size="lg"
          placeholder={intl.formatMessage({ id: "label.button.search" })}
          labelText={intl.formatMessage({ id: "label.button.search" })}
          closeButtonLabelText={intl.formatMessage({ id: "label.button.clear" })}
          id="search-1"
          value={textValue}
          onChange={handleChange}
          onKeyDown={handleAutocompleteKeyDown}
          onClear={handleClearSearch}
          className="search-input"
        />
        <Button className="button" onClick={handleSearch}>
          <FormattedMessage id="label.button.search" />
        </Button>
        {showSuggestions && (
          <ul className="suggestions">
            {filteredSuggestions.map((suggestion, index) => (
              <li
                key={suggestion.id}
                className={index === activeSuggestion ? "suggestion-active" : ""}
              >
                <span onClick={(e) => handleSuggestionClick(e, suggestion.id, suggestion)}>
                  {suggestion.value}
                </span>
                <button className="delete-button" onClick={() => handleSuggestionDelete(suggestion.id)}>x</button>
              </li>
            ))}
          </ul>
        )}
      </div>
      <div className="patients">
        <SearchOutput loading={loading} patientData={patientData} className="patientHeader" />
      </div>
    </div>
  );
};

export default SearchBar;
