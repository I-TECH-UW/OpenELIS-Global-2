import React, { useState } from "react";
import {
  Button,
  Search,
  Tile,
  Layer,
  Grid,
  Column,
  Loading,
  Tag,
} from "@carbon/react";
import { FormattedMessage, useIntl } from "react-intl";
import SearchOutput from "./searchOutput";
import { fetchPatientData, useAutocomplete } from "./searchService";
import "./searchBar.css";

const SearchBar = (props) => {
  const [searchInput, setSearchInput] = useState("");
  const [loading, setLoading] = useState(false);
  const [patientData, setPatientData] = useState([]);
  const intl = useIntl();

  const validPatients = patientData.filter(
    (patient) => patient.patientID && patient.firstName && patient.lastName,
  );

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
        prevData.filter((patient) => patient.patientID !== id),
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
        const uniqueResults = results.filter(
          (result, index, self) =>
            result.patientID &&
            result.firstName &&
            result.lastName &&
            index === self.findIndex((t) => t.patientID === result.patientID),
        );
        setPatientData(uniqueResults);
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
        const uniqueResults = results.filter(
          (result, index, self) =>
            result.patientID &&
            result.firstName &&
            result.lastName &&
            index === self.findIndex((t) => t.patientID === result.patientID),
        );
        setPatientData(uniqueResults);
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
    <Layer className="main">
      <Grid>
        <Column sm={4} md={6} lg={8} xlg={12}>
          <div className="search-bar-container">
            <Search
              size="sm"
              placeholder={intl.formatMessage({ id: "label.button.search" })}
              labelText={intl.formatMessage({ id: "label.button.search" })}
              closeButtonLabelText={intl.formatMessage({
                id: "label.button.clear",
              })}
              id="searchItem"
              value={textValue}
              onChange={handleChange}
              onKeyDown={handleAutocompleteKeyDown}
              onClear={handleClearSearch}
              className="search-input"
              autoComplete
            />
            <Button
              size="sm"
              style={{ width: 50 }}
              onClick={handleSearch}
              aria-label={intl.formatMessage({ id: "label.button.search" })}
            >
              <FormattedMessage id="label.button.search" />
            </Button>
          </div>
          {showSuggestions && (
            <ul className="suggestions">
              {filteredSuggestions.map((suggestion, index) => (
                <p
                  key={suggestion.id}
                  className={
                    index === activeSuggestion ? "suggestion-active" : ""
                  }
                >
                  <span
                    onClick={(e) =>
                      handleSuggestionClick(e, suggestion.id, suggestion)
                    }
                  >
                    {suggestion.value}
                  </span>
                </p>
              ))}
            </ul>
          )}
        </Column>

        <Column sm={4} md={6} lg={8} xlg={12}>
          {(loading || patientData.length > 0) && (
            <div className="patients">
              {loading ? (
                <Loading
                  description={intl.formatMessage({ id: "label.loading" })}
                  withOverlay={false}
                />
              ) : (
                <>
                  <div>
                    <em
                      style={{
                        fontFamily: "serif",
                        color: "#000",
                        marginLeft: "10px",
                      }}
                    >
                      <FormattedMessage id="sidenav.label.results" />:
                    </em>{" "}
                    <Tag size="sm" type="blue">
                      {patientData.length}
                    </Tag>
                  </div>
                  <SearchOutput loading={loading} patientData={patientData} />
                </>
              )}
            </div>
          )}
        </Column>
      </Grid>
    </Layer>
  );
};

export default SearchBar;
