import React, { useState, useEffect } from "react";
import { Grid, Column, TextInput, Button, Checkbox, Tile } from "@carbon/react";
import "./LandingPage.css"; // Import a CSS file for custom styles
import { getFromOpenElisServer } from "../utils/Utils";

const LandingPage: React.FC = () => {
  const [departments, setDepartments] = useState([]);
  const [filteredDepartments, setFilteredDepartments] = useState([]);
  const [selectedDepartment, setSelectedDepartment] = useState("");
  const [rememberChoice, setRememberChoice] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");

  useEffect(() => {
    // Fetch the list of departments from the server
    getFromOpenElisServer("/rest/user-test-sections", (response) => {
      setDepartments(response);
      setFilteredDepartments(response);
    });
  }, []);

  const handleSearch = (event) => {
    const term = event.target.value.toLowerCase();
    setSearchTerm(term);
    setFilteredDepartments(
      departments.filter((dept) =>
        dept.value.toLowerCase().includes(term)
      )
    );
  };


  const handleDepartmentSelect = (department) => {
    setSelectedDepartment(department);
  };

  const handleContinue = () => {
    if (rememberChoice) {
      localStorage.setItem("selectedDepartment", selectedDepartment);
    }
    // Navigate to the dashboard or the next page
    window.location.href = "/dashboard";
  };

  return (
    <div className="landing-page">
      <Column lg={8} md={6} sm={4} className="landing-page-column">
        <Tile className="landing-page-card">
          <h2>Welcome!</h2>
          <p>Please select a unit to continue</p>
          <TextInput
            id="department-search"
            labelText="Search for a department"
            value={searchTerm}
            onChange={handleSearch}
            light
          />
          <div className="department-list">
            {filteredDepartments.map((dept) => (
              <div
                key={dept.id}
                className={`department-item ${
                  (selectedDepartment === dept.id) ? "selected" : ""
                }`}
                onClick={() => handleDepartmentSelect(dept.id)}
              >
                {dept.value}
              </div>
            ))}
            
            {/* 
            TODO: Check if the backend can accept multiple values for the department
            <div
                className={`department-item ${
                  selectedDepartment === 'all' ? "selected" : ""
                }`}
                onClick={() => handleDepartmentSelect('all')}
              >
                All labs
              </div> */}
          </div>
          <Checkbox
            id="remember-choice"
            labelText="Remember my choice"
            checked={rememberChoice}
            onChange={(e) => setRememberChoice(e.target.checked)}
          />
          <Button
            onClick={handleContinue}
            disabled={!selectedDepartment}
            className="continue-button"
          >
            Continue
          </Button>
        </Tile>
      </Column>
    </div>
  );
};

export default LandingPage;
