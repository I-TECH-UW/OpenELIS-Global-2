import React, { useState, useEffect } from "react";
import { Button, Search } from "@carbon/react";
import { FormattedMessage, useIntl } from "react-intl";
import SearchHistory from "./searchHistory";
import SearchOutput from "./searchOutput";
import { fetchPatientData, SuggestionList } from "./searchService";
import "./searchBar.css";

const SearchBar = (props) => {
  const [searchInput, setSearchInput] = useState("");
  const [searchHistory, setSearchHistory] = useState([]);
  const [loading, setLoading] = useState(false);
  const [patientData, setPatientData] = useState([]);
  const [filteredSuggestions, setFilteredSuggestions] = useState([]);
  const intl = useIntl();
  const [history, setHistory] =useState(true);
  const [suggesrList,setSuggestList] = useState(true);

  useEffect(() => {
    const history = JSON.parse(localStorage.getItem("searchHistory")) || [];
    setSearchHistory(history);
  }, []);

  const handleSearch = () => {
    if (searchInput.trim()) {
      setLoading(true);

      fetchPatientData(searchInput.trim(), (results) => {
        setPatientData(results);
        setLoading(false);
      });

      if (!searchHistory.includes(searchInput.trim())) {
        const newHistory = [searchInput.trim(), ...searchHistory];
        localStorage.setItem("searchHistory", JSON.stringify(newHistory));
        setSearchHistory(newHistory.length > 5 ? newHistory.slice(0, 5) : newHistory);
      }

      setSearchInput("");
    }
    setHistory(!history)
  };

  const handleSelect = (suggestion) => {
    setSearchInput(suggestion);
    setFilteredSuggestions([]);
  };

  const handleChange = (e) => {
    const userInput = e.target.value;
    setSearchInput(userInput);

    const filtered = searchHistory.filter((item) =>
      item.toLowerCase().includes(userInput.toLowerCase())
    );

    setFilteredSuggestions(filtered);
  };

  const removeHistoryItem = (index) => {
    const updatedHistory = [...searchHistory];
    updatedHistory.splice(index, 1);
    setSearchHistory(updatedHistory);
    localStorage.setItem("searchHistory", JSON.stringify(updatedHistory));
  };
const handleHistory = ()=>{
  setHistory(!history)
}
const handleSugest = ()=>{
  setSuggestList(!suggesrList)
}
  return (
    <div className="main">
      <div className="search-bar-container">
        <Search
          labelText={intl.formatMessage({ id: "label.search" })}
          className="search-input"
          value={searchInput}
          onChange={handleChange}
          placeHolder={intl.formatMessage({ id: "label.search.placeholder" })}
          onClick={handleSugest}
        />
        {filteredSuggestions.length > 0 && (
          <SuggestionList
            suggestions={filteredSuggestions}
            onSelect={handleSelect}
          />
        )}
        <Button className="button" onClick={handleSearch}>
          <FormattedMessage id="label.button.search" />
        </Button>
      </div>
      {history && <div>
      <SearchHistory
        searchInput={searchInput}
        searchHistory={searchHistory}
        setSearchHistory={setSearchHistory}
        setSearchInput={setSearchInput}
        handleSearch={handleSearch}
        removeHistoryItem={removeHistoryItem}
      /> </div> }
      <div className="patients" onMouseEnter={handleHistory}>
       <SearchOutput 
        loading={loading} 
        patientData={patientData}
        className="patientHeader"
        
         />
      </div>
    </div>
  );
};

export default SearchBar;
