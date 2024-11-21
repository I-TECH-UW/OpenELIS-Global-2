import React, { useState } from "react";
import { 
  TextInput, 
  Select, 
  SelectItem, 
  Button, 
  Form, 
  DataTable, 
  Grid,
  Column
} from "@carbon/react";
import { getFromOpenElisServer } from "../utils/Utils";

const { TableContainer, Table, TableHead, TableRow, TableHeader, TableBody, TableCell } = DataTable;

const ViewPage = () => {
  const [searchBy, setSearchBy] = useState("Last Name");
  const [query, setQuery] = useState("");
  const [data, setData] = useState([]);

  const headers = [
    { id: 1, header: "Last Name", key: "lastName" },
    { id: 2, header: "First Name", key: "firstName" },
    { id: 3, header: "Gender", key: "gender" },
    { id: 4, header: "Date Of Birth", key: "birthdate" },
    { id: 5, header: "Unique Health ID", key: "subjectNumber" },
    { id: 6, header: "National ID", key: "nationalId" },
  ];

  const handleSearch = async () => {
    if (!query.trim()) {
      console.error("Search query is empty.");
      alert("Please enter a search query.");
      return;
    }
  
    const searchCriteriaMap = {
      "Last Name": "lastName",
      "First Name": "firstName",
      "Patient Identification Code": "patientID",
      "Lab No": "labNo",
    };
  
    const searchKey = searchCriteriaMap[searchBy];
    if (!searchKey) {
      console.error("Invalid search criteria selected.");
      return;
    }
  
    const endpoint = `/rest/patient-search?${searchKey}=${encodeURIComponent(query)}`;
    console.log(`Fetching data with endpoint: ${endpoint}`);
  
    getFromOpenElisServer(endpoint, (response) => {
      if (response && Array.isArray(response)) {
        setData(
          response.map((patient, index) => ({
            id: index + 1,
            lastName: patient.lastName,
            firstName: patient.firstName,
            gender: patient.gender,
            birthdate: patient.birthdate,
            subjectNumber: patient.subjectNumber,
            nationalId: patient.nationalId,
          }))
        );
      } else {
        console.error("No data or invalid response format.");
        setData([]);
      }
    });
  };
  

  return (
    <div style={{ padding: "1rem" }}>
      <h1>View Patient</h1>

      <Form>
        <Grid>
          <Column lg={4} md={2} sm={1}>
            <Select
              id="search-criteria"
              labelText="Select Search Criteria"
              value={searchBy}
              defaultValue="Search by.."
              onChange={(e) => setSearchBy(e.target.value)}
            >
              <SelectItem value="Last Name" text="Last Name" />
              <SelectItem value="First Name" text="First Name" />
              <SelectItem
                value="Patient Identification Code"
                text="Patient Identification Code"
              />
              <SelectItem value="Lab No" text="Lab No" />
            </Select>
          </Column>
          <Column lg={6} md={2} sm={1}>
            <TextInput
              id="search-query"
              labelText={`Enter ${searchBy}`}
              placeholder={`Type ${searchBy}`}
              value={query}
              onChange={(e) => setQuery(e.target.value)}
            />
          </Column>
          <Column lg={2} md={1} sm={1}>
            <Button onClick={handleSearch}>Search</Button>
          </Column>
        </Grid>
      </Form>

      <div style={{ marginTop: "2rem" }}>
        <DataTable
          rows={data}
          headers={headers}
          render={({ rows, headers, getHeaderProps }) => (
            <TableContainer title="Search Results">
              <Table>
                <TableHead>
                  <TableRow>
                    {headers.map((header) => (
                      <TableHeader key={header.key} {...getHeaderProps({ header })}>
                        {header.header}
                      </TableHeader>
                    ))}
                  </TableRow>
                </TableHead>
                <TableBody>
                  {rows.map((row) => (
                    <TableRow key={row.id}>
                      {row.cells.map((cell) => (
                        <TableCell key={cell.id}>{cell.value}</TableCell>
                      ))}
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          )}
        />
      </div>
    </div>
  );
};

export default ViewPage;
