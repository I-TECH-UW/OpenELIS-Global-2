import React, { useState, useEffect } from "react";
import { getFromOpenElisServer } from "../utils/Utils";
import { TextInput } from "@carbon/react"; // Import Carbon components
import { FormattedMessage } from "react-intl";

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

const GlobalSearch = () => {
  const [menuData, setMenuData] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [filteredMenuItems, setFilteredMenuItems] = useState([]);

  useEffect(() => {
    fetchMenuData((data) => {
      setMenuData(data);
    });
  }, []);

  useEffect(() => {
    const filteredItems = filterMenuItems(menuData, searchQuery);
    setFilteredMenuItems(filteredItems);
  }, [searchQuery, menuData]);

  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };

  return (
    <div style={{ position: "relative", right: 20, top: 40 }}>
      <div style={{ width: 300 }}>
        <TextInput
          id="global-search-input"
          labelText={<FormattedMessage id="advanced.search" />}
          
          value={searchQuery}
          onChange={handleSearchChange}
        />
      </div>
      <div
        style={{
          display: searchQuery ? "block" : "none",
          position: "absolute",
          top: "70px",
          right: "0",
          zIndex: 2,
          backgroundColor: "blue",
          border: "1px solid #ccc",
          boxShadow: "0 2px 4px rgba(0,0,0,0.1)",
          borderRadius: "4px",
          padding: "10px",
          maxHeight: "300px",
          overflowY: "auto",
        }}
      >
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
    </div>
  );
};

export default GlobalSearch;
