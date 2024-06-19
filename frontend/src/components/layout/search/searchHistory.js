import React from "react";
import { TrashCan } from "@carbon/icons-react";
import "./searchBar.css";

const SearchHistory = ({ searchHistory, setSearchInput, handleSearch, removeHistoryItem }) => {
  const handleHistoryClick = (item) => {
    setSearchInput(item);
    handleSearch();
  };

  return (
    <div className="search-history">
      
      <ul>
        {searchHistory.map((item, index) => (
          <li key={index} className="history-item">
            <span onClick={() => handleHistoryClick(item)} className="history-text">
              {item}
            </span>
            <button onClick={() => removeHistoryItem(index)} className="remove-button">
              <TrashCan size={16} />
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default SearchHistory;
