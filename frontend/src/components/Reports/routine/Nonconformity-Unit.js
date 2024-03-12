import React, { useState, useEffect } from "react";
import config from "../../../config.json"; // Assuming config.json is in the same directory
import {
  DataTable,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  DatePicker,
  Button, // Added Button component
} from "@carbon/react";

const NonconformityReportsByUnit = () => {
  const [reports, setReports] = useState([]);
  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);
  const [printableDataUrl, setPrintableDataUrl] = useState(null); // Added state for printable data URL

  useEffect(() => {
    fetchReports();
    fetchPrintableDataUrl(); // Fetch the printable data URL when the component mounts
  }, [startDate, endDate]);

  const fetchReports = async () => {
    try {
      const url = `${config.serverBaseUrl}/api/reports`;
      const params = new URLSearchParams({
        type: "nonConformity",
        report: "nonConformityByUnit",
        lowerDateRange: startDate ? startDate.toISOString() : null,
        upperDateRange: endDate ? endDate.toISOString() : null,
      });
      const response = await fetch(`${url}?${params}`);
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      const data = await response.json();
      setReports(data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const fetchPrintableDataUrl = async () => {
    try {
      const response = await fetch(`${config.serverBaseUrl}/api/getPrintableDataUrl`);
      if (!response.ok) {
        throw new Error("Failed to fetch printable data URL");
      }
      const url = await response.text();
      setPrintableDataUrl(url);
    } catch (error) {
      console.error("Error fetching printable data URL:", error);
    }
  };

  const handleGeneratePrintableData = async () => {
    try {
      const response = await fetch(printableDataUrl); // Use the printable data URL
      if (!response.ok) {
        throw new Error("Failed to generate printable data");
      }
      const printableData = await response.json();
      // Handle the printable data, e.g., open in a new window or initiate download
    } catch (error) {
      console.error("Error generating printable data:", error);
    }
  };

  return (
    <div>
      <h2>Nonconformity Reports by Unit</h2>
      <div>
        <DatePicker
          id="start-date"
          datePickerType="single"
          dateFormat="m/d/Y"
          onChange={(date) => setStartDate(date)}
          placeholder="mm/dd/yyyy"
          labelText="Start Date"
        />
        <DatePicker
          id="end-date"
          datePickerType="single"
          dateFormat="m/d/Y"
          onChange={(date) => setEndDate(date)}
          placeholder="mm/dd/yyyy"
          labelText="End Date"
        />
        <Button onClick={handleGeneratePrintableData}>Generate Printable Data</Button>
      </div>
      <DataTable
        rows={reports}
        headers={[
          { key: "id", header: "ID" },
          { key: "title", header: "Title" },
          // Add more headers for additional report details
        ]}
        render={({ rows, headers, getTableProps }) => (
          <TableContainer title="Nonconformity Reports">
            <Table {...getTableProps()}>
              <TableHead>
                <TableRow>
                  {headers.map((header) => (
                    <TableCell key={header.key}>{header.header}</TableCell>
                  ))}
                </TableRow>
              </TableHead>
              <TableBody>
                {rows.map((row) => (
                  <TableRow key={row.id}>
                    {headers.map((header) => (
                      <TableCell key={header.key}>{row[header.key]}</TableCell>
                    ))}
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        )}
      />
    </div>
  );
};

export default NonconformityReportsByUnit;
