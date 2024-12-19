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
import PageBreadCrumb from "../common/PageBreadCrumb.js";

let breadcrumbs = [{ label: "home.label", link: "/" }];

import { getFromOpenElisServer } from "../utils/Utils";
import { FormattedMessage, useIntl } from "react-intl";

const { TableContainer, Table, TableHead, TableRow, TableHeader, TableBody, TableCell } = DataTable;

const ViewPage = () => {
  const [searchBy, setSearchBy] = useState(" ");
  const [query, setQuery] = useState("");
  const [data, setData] = useState([]);
  const [scan,setScan] = useState(false)

  const intl = useIntl();

  const headers = [
    { id: 0, header: " " },
    { id: 1, header: intl.formatMessage({ id: "patient.first.name" }), key: "lastName" },
    { id: 2, header: intl.formatMessage({ id: "patient.last.name" }), key: "firstName" },
    { id: 3, header: intl.formatMessage({ id: "patient.gender" }), key: "gender" },
    { id: 4, header: intl.formatMessage({ id: "patient.dob" }), key: "birthdate" },
    { id: 5, header: intl.formatMessage({ id: "patient.subject.number" }), key: "subjectNumber" },
    { id: 6, header: intl.formatMessage({ id: "patient.natioanalid" }), key: "nationalId" },
  ];

  const handleSearch = async () => {
    if (!query.trim()) {
      console.error("Search query is empty.");
      alert("Please enter a search query.");
      return;
    }

    const searchCriteriaMap = {
      "patient.last.name": "lastName",
      "patient.first.name": "firstName",
      "patient.subject.number": "subjectNumber",
      "patient.lab.no": "labNo",
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
      <PageBreadCrumb breadcrumbs={breadcrumbs} />
      <h1><b>
        <FormattedMessage id="banner.menu.patientConsult" />{" "}
        <FormattedMessage id="banner.menu.patient" />
        </b>
      </h1>
<h2><b><FormattedMessage id="patient.label.info" /> </b></h2>
      <Form>
        <Grid>
          <Column lg={4} md={8} sm={4}>
            <Select
            style={{margin:"10px"}} 
              id="search-criteria"
              labelText={intl.formatMessage({ id: "order.legend.selectMethod" })}
              
              onChange={(e) => {
                const selectedValue = e.target.value;
                setSearchBy(selectedValue);
                setScan(selectedValue === "patient.lab.no")}}
            >
              <SelectItem
              
                text=""
              />
              <SelectItem
                value="patient.last.name"
                text={intl.formatMessage({ id: "patient.last.name" })}
              />
              <SelectItem
                value="patient.first.name"
                text={intl.formatMessage({ id: "patient.first.name" })}
              />
              <SelectItem
                value="patient.subject.number"
                text={intl.formatMessage({ id: "patient.subject.number" })}
              />
              <SelectItem
                value="patient.lab.no"
                text={intl.formatMessage({ id: "label.audittrail" })}
              />
            </Select>
          </Column>
          <Column lg={6} md={4} sm={2}>
            <TextInput
              id="search-query"
              labelText={intl.formatMessage({ id: "search.patient.label" }, { criteria: intl.formatMessage({ id: searchBy }) })}
              placeholder={intl.formatMessage({ id: "label.form.searchby" }, { criteria: intl.formatMessage({ id: searchBy }) })}
              value={query}
              onChange={(e) => setQuery(e.target.value)}
            />
          </Column>
          <Column lg={2} md={2} sm={1}>
            <Button onClick={handleSearch}>
              <FormattedMessage id="label.button.search" />
            </Button>
          </Column>
          {scan && (
             <Column lg={3} md={2} sm={1}>
             <p  style={{margin:"10px"}} ><b><FormattedMessage id="referral.input" /> </b></p>
           </Column>

          )}
         
        </Grid>
      </Form>

      <div style={{ marginTop: "2rem" }}>
        <DataTable
          rows={data}
          headers={headers}
          render={({ rows, headers, getHeaderProps }) => (
            <TableContainer >
              <Table>
              <TableHead>
                    <TableRow>
                      {headers.map((header) => (
                        <TableHeader key={header.key}>{header.header}</TableHeader>
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
      <Grid>
        <Column  lg={4} >
    
        <Select
              id="search-criteria"
              labelText=""
              
              
            >
              <SelectItem
                text=""
              />
              <SelectItem
                
                text={intl.formatMessage({ id: "project.ARVStudy.name" })}
              />
              <SelectItem
                
                text={intl.formatMessage({ id: "project.ARVFollowupStudy.name" })}
              />
              <SelectItem
                
                text={intl.formatMessage({ id: "project.RTNStudy.name" })}
              />
              <SelectItem
                
                text={intl.formatMessage({ id: "banner.menu.resultvalidation.viralload" })}
              />
              <SelectItem
                
                text={intl.formatMessage({ id: "project.EIDStudy.name" })}
              />
               <SelectItem
                
                text={intl.formatMessage({ id: "project.Recency.name" })}
              />
            </Select>
        </Column>
      </Grid>
    </div>
  );
};

export default ViewPage;
