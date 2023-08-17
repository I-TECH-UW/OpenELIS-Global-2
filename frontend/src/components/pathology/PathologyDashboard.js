import { useContext, useState, useEffect, useRef } from "react";
import {
  Checkbox, Heading, TextInput, Select, SelectItem, Button, Grid, Column, Section,
  DataTable, TableContainer, Table, TableHead, TableRow, TableHeader, TableBody, TableCell, Tile
} from '@carbon/react';
import UserSessionDetailsContext from "../../UserSessionDetailsContext"
import { Search } from '@carbon/react';
import { getFromOpenElisServer, postToOpenElisServerFullResponse, hasRole } from "../utils/Utils";
import { NotificationContext } from "../layout/Layout";
import { AlertDialog } from "../common/CustomNotification";
import { FormattedMessage, injectIntl } from 'react-intl'
import "./PathologyDashboard.css"

function PathologyDashboard() {

  const componentMounted = useRef(false);

  const { notificationVisible, setNotificationVisible, setNotificationBody } = useContext(NotificationContext);
  const { userSessionDetails, setUserSessionDetails } = useContext(UserSessionDetailsContext);
  const [statuses, setStatuses] = useState([]);
  const [pathologyEntries, setPathologyEntries] = useState([])
  const [filters, setFilters] = useState({ searchTerm: "", myCases: false, statuses: [] });
  const [counts ,setCounts] = useState({ inProgress: 0, awaitingReview: 0, additionalRequests: 0, complete: 0});

  const setStatusList = (statusList) => {
    if (componentMounted.current) {
      setStatuses(statusList);
    }
  }

  const assignCurrentUserAsTechnician = (event, pathologySampleId) => {
    postToOpenElisServerFullResponse("/rest/pathology/assignTechnician?pathologySampleId=" + pathologySampleId, {}, refreshItems)
  }

  const assignCurrentUserAsPathologist = (event, pathologySampleId) => {
    postToOpenElisServerFullResponse("/rest/pathology/assignPathologist?pathologySampleId=" + pathologySampleId, {}, refreshItems)
  }

  const renderCell = (cell, row) => {
    var status = row.cells.find(
      (e) => e.info.header === 'status'
    ).value;
    var pathologySampleId = row.id;

    if (cell.info.header === 'assignedTechnician' && !cell.value) {
      return <TableCell key={cell.id}>
        <Button type="button" onClick={(e) => { assignCurrentUserAsTechnician(e, pathologySampleId) }}>Start</Button>
      </TableCell>
    }
    if (cell.info.header === 'assignedPathologist' && !cell.value && status === 'READY_PATHOLOGIST' && hasRole(userSessionDetails ,"Pathologist")) {
      return <TableCell key={cell.id}>
        <Button type="button" onClick={(e) => { assignCurrentUserAsPathologist(e, pathologySampleId) }}>Start</Button>
      </TableCell>
    } else {
      return <TableCell key={cell.id}>{cell.value}</TableCell>
    }
  }

  const setPathologyEntriesWithIds = (entries) => {
    if (componentMounted.current && entries && entries.length > 0) {
      var i = 0;
      setPathologyEntries(entries.map((entry) => {
        return { ...entry, id: '' + entry.pathologySampleId };
      }));
    }

  }

  const setStatusFilter = (event) => {
    if (event.target.value === 'All') {
      setFilters({ ...filters, statuses: statuses });
    } else {
      setFilters({ ...filters, statuses: [{ "id": event.target.value }] });
    }
  }

  const filtersToParameters = () => {
    return "statuses=" + filters.statuses.map((entry) => {
      return entry.id;
    }).join(",") + "&searchTerm="+filters.searchTerm;
  }

  const refreshItems = () => {
    getFromOpenElisServer("/rest/pathology/dashboard?" + filtersToParameters(), setPathologyEntriesWithIds);
  }

  const openCaseView = (id) => {
    window.location.href = "/PathologyCaseView/" + id;
  }

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/displayList/PATHOLOGY_STATUS", setStatusList);
    getFromOpenElisServer("/rest/pathology/dashboard/count",  loadCounts);

    return () => {
      componentMounted.current = false;
    }
  }, []);

  const loadCounts = (data) => {
    setCounts(data);
  }

  function formatDateToDDMMYYYY(date) {
    var day = date.getDate();
    var month = date.getMonth() + 1; // Month is zero-based
    var year = date.getFullYear();

    // Ensure leading zeros for single-digit day and month
    var formattedDay = (day < 10 ? '0' : '') + day;
    var formattedMonth = (month < 10 ? '0' : '') + month;

    // Construct the formatted string
    var formattedDate = formattedDay + '/' + formattedMonth + '/' + year;
    return formattedDate;
  }

  const getPastWeek = () => {
    // Get the current date
    var currentDate = new Date();

    // Calculate the date of the past week
    var pastWeekDate = new Date(currentDate);
    pastWeekDate.setDate(currentDate.getDate() - 7);

    return formatDateToDDMMYYYY(currentDate) + " - " + formatDateToDDMMYYYY(pastWeekDate);
  }

  const tileList = [
    {"title" : "Cases in Progress"  , "count" : counts.inProgress} ,
    {"title" : "Awaiting Pathology Review"  , "count" : counts.awaitingReview},
    {"title" : "Additional Pathology Requests"  , "count" : counts.additionalRequests},
    {"title" : "Complete (Week " + getPastWeek() + " )"   , "count" : counts.complete}
  ]

  useEffect(() => {
    componentMounted.current = true;
    setFilters({ ...filters, statuses: statuses });

    return () => {
      componentMounted.current = false
    }
  }, [statuses]);



  useEffect(() => {
    componentMounted.current = true;
    refreshItems();
    return () => {
      componentMounted.current = false
    }
  }, [filters]);

  return (
    <>
      {notificationVisible === true ? <AlertDialog /> : ""}

      <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Section >
              <Heading >
                <FormattedMessage id="pathology.label.title" />
              </Heading>
            </Section>
          </Section>
        </Column>
      </Grid>
      <div className="dashboard-container">
      {tileList.map((tile, index) => (
          <Tile  key={index} className="dashboard-tile">
            <h3 className="tile-title">{tile.title}</h3>
            <p className="tile-value">{tile.count}</p>
          </Tile>
          ))}
      </div>
      <Grid fullWidth={true} className="gridBoundary">
        <Column lg={8} md={4} sm={2}>
          <Search
            size="sm"
            value={filters.searchTerm}
            onChange={(e) => setFilters({ ...filters, searchTerm: e.target.value })}
            placeholder='Search by LabNo or Family Name'
            labelText="Search by LabNo or Family Name"
          />
        </Column>
        <Column lg={8} md={4} sm={2}>
          <div className="inlineDivBlock">
            <div >Filters:</div>
            <Checkbox labelText="My cases"
              id="filterMyCases"
              value={filters.myCases}
              onChange={(e) => setFilters({ ...filters, myCases: e.target.checked })} />
            <Select id="statusFilter"
              name="statusFilter"
              labelText="Status"
              defaultValue="placeholder"
              onChange={setStatusFilter}
              noLabel
            >
              <SelectItem disabled hidden value="placeholder" text="Status" />
              <SelectItem text="All" value="All"
              />
              {statuses.map((status, index) => {
                return (<SelectItem key={index}
                  text={status.value}
                  value={status.id}
                />);
              })}</Select></div>
        </Column>

        <Column lg={16} md={8} sm={4}>
          <DataTable rows={pathologyEntries} headers={[
            {
              key: 'requestDate',
              header: 'Request Date',
            },
            {
              key: 'status',
              header: 'Stage',
            },
            {
              key: 'lastName',
              header: 'Last Name',
            },
            {
              key: 'firstName',
              header: 'First Name',
            },
            {
              key: 'assignedTechnician',
              header: 'Assigned Technician',
            },
            {
              key: 'assignedPathologist',
              header: 'Assigned Pathologist',
            },
            {
              key: 'labNumber',
              header: 'Lab Number',
            },
          ]} isSortable >
            {({ rows, headers, getHeaderProps, getTableProps }) => (
              <TableContainer title="" description="">
                <Table {...getTableProps()}>
                  <TableHead>
                    <TableRow>
                      {headers.map((header) => (
                        <TableHeader {...getHeaderProps({ header })}>
                          {header.header}
                        </TableHeader>
                      ))}
                    </TableRow>

                  </TableHead>
                  <TableBody>
                    <>
                      {rows.map((row) => (
                        <TableRow key={row.id} onClick={() => { openCaseView(row.id) }}>
                          {row.cells.map((cell) => (
                            renderCell(cell, row)
                          ))}
                        </TableRow>
                      ))}
                    </>
                  </TableBody>
                </Table>
              </TableContainer>
            )}
          </DataTable>
        </Column>
      </Grid>
    </>
  )
}

export default PathologyDashboard;