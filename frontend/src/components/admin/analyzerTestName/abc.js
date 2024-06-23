import React, { useEffect, useState } from "react";
import {
  DataTable,
  TableContainer,
  Table,
  TableHead,
  TableRow,
  TableHeader,
  TableBody,
  TableCell,
  TableSelectAll,
  Pagination,
  Grid,
  Column,
  Search,
  Button,
  Section,
  Heading,
} from "@carbon/react";
import { FormattedMessage, injectIntl } from "react-intl";
import {
  getFromOpenElisServer,
  postToOpenElisServerJsonResponse,
} from "../../utils/Utils.js";
import { NotificationContext } from "../../layout/Layout.js";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification.js";
import PageBreadCrumb from "../../common/PageBreadCrumb.js";


const AnalyzerTestName = ({ intl }) => {
  const [menuList, setMenuList] = useState([]);
  const [filteredMenuList, setFilteredMenuList] = useState([]);
  const [notificationVisible, setNotificationVisible] = useState(false);
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);
  const [selectedRowIds, setSelectedRowIds] = useState([]);
  const [panelSearchTerm, setPanelSearchTerm] = useState("");
  const [isSearching, setIsSearching] = useState(false);
  const [isAddModifyOpen, setIsAddModifyOpen] = useState(false);
  const [formData, setFormData] = useState(null);

  useEffect(() => {
    getFromOpenElisServer("/rest/AnalyzerTestNameMenu",handle)
     
    
  }, []);

  function handle(response){
    setMenuList(response.menuList);
    setFilteredMenuList(response.menuList);
  }

  useEffect(() => {
    if (panelSearchTerm) {
      setIsSearching(true);
      setFilteredMenuList(
        menuList.filter((item) =>
          `${item.analyzerName} - ${item.analyzerTestName}`
            .toLowerCase()
            .includes(panelSearchTerm.toLowerCase())
        )
      );
    } else {
      setIsSearching(false);
      setFilteredMenuList(menuList);
    }
  }, [panelSearchTerm, menuList]);

  const handlePanelSearchChange = (event) => {
    setPanelSearchTerm(event.target.value);
  };

  const handlePageChange = ({ page, pageSize }) => {
    setPage(page);
    setPageSize(pageSize);
  };

  const handleAddModifyClick = (data = null) => {
    setFormData(data);
    setIsAddModifyOpen(true);
  };

  const handleFormSubmit = () => {
    // Logic for form submission (add or modify) goes here.
    setIsAddModifyOpen(false);
  };

  const renderCell = (cell, row) => {
    return <TableCell key={cell.id}>{cell.value}</TableCell>;
  };

  const breadcrumbs = [
    {
      href: "/",
      label: intl.formatMessage({ id: "Home" }),
    },
    {
      label: intl.formatMessage({ id: "Analyzer Test Name Menu" }),
    },
  ];

  const headers = [
    {
      key: "combinedName",
      header: intl.formatMessage({ id: "Combined Name" }),
    },
    {
      key: "actualTestName",
      header: intl.formatMessage({ id: "Actual Test Name" }),
    },
    {
      key: "actions",
      header: intl.formatMessage({ id: "Actions" }),
    },
  ];

  const rows = filteredMenuList.map((item) => ({
    id: item.uniqueId,
    combinedName: `${item.analyzerName} - ${item.analyzerTestName}`,
    actualTestName: item.actualTestName,
    actions: (
      <Button
        size="small"
        onClick={() => handleAddModifyClick(item)}
      >
        {intl.formatMessage({ id: "Modify" })}
      </Button>
    ),
  }));

  return (
    <>
      {notificationVisible === true ? <AlertDialog /> : ""}
      <div className="adminPageContent">
        <PageBreadCrumb breadcrumbs={breadcrumbs} />
        <Grid fullWidth={true}>
          <Column lg={16} md={8} sm={4}>
            <Section>
              <Heading>
                <FormattedMessage id="analyzerTestNameMenu.browse.title" />
              </Heading>
            </Section>
          </Column>
        </Grid>
        <br />
        <div className="orderLegendBody">
          <Grid>
            <Column lg={16} md={8} sm={4}>
              <Section>
                <Search
                  size="lg"
                  id="analyzer-test-name-search-bar"
                  labelText={<FormattedMessage id="analyzerTestName.search" />}
                  placeholder={intl.formatMessage({
                    id: "analyzerTestName.search.placeholder",
                  })}
                  onChange={handlePanelSearchChange}
                  value={panelSearchTerm || ""}
                />
              </Section>
            </Column>
          </Grid>
          <br />
          <Grid fullWidth={true} className="gridBoundary">
            <Column lg={16} md={8} sm={4}>
              <Section>
                <Button onClick={() => handleAddModifyClick()}>{intl.formatMessage({ id: "Add" })}</Button>
              </Section>
            </Column>
          </Grid>
          <Grid fullWidth={true} className="gridBoundary">
            <Column lg={16} md={8} sm={4}>
              <DataTable
                rows={rows.slice((page - 1) * pageSize, page * pageSize)}
                headers={headers}
              >
                {({ rows, headers, getHeaderProps, getTableProps, getSelectionProps }) => (
                  <TableContainer>
                    <Table {...getTableProps()}>
                      <TableHead>
                        <TableRow>
                          <TableSelectAll
                            id="table-select-all"
                            {...getSelectionProps()}
                            checked={
                              selectedRowIds.length === pageSize &&
                              filteredMenuList
                                .slice((page - 1) * pageSize, page * pageSize)
                                .filter((row) => !row.disabled && selectedRowIds.includes(row.id))
                                .length === pageSize
                            }
                            indeterminate={
                              selectedRowIds.length > 0 &&
                              selectedRowIds.length <
                                filteredMenuList
                                  .slice((page - 1) * pageSize, page * pageSize)
                                  .filter((row) => !row.disabled).length
                            }
                            onSelect={() => {
                              const currentPageIds = filteredMenuList
                                .slice((page - 1) * pageSize, page * pageSize)
                                .filter((row) => !row.disabled)
                                .map((row) => row.id);
                              if (
                                selectedRowIds.length === pageSize &&
                                currentPageIds.every((id) => selectedRowIds.includes(id))
                              ) {
                                setSelectedRowIds([]);
                              } else {
                                setSelectedRowIds(
                                  currentPageIds.filter((id) => !selectedRowIds.includes(id))
                                );
                              }
                            }}
                          />
                          {headers.map((header) => (
                            <TableHeader key={header.key} {...getHeaderProps({ header })}>
                              {header.header}
                            </TableHeader>
                          ))}
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {rows.map((row) => (
                          <TableRow
                            key={row.id}
                            onClick={() => {
                              const id = row.id;
                              const isSelected = selectedRowIds.includes(id);
                              if (isSelected) {
                                setSelectedRowIds(selectedRowIds.filter((selectedId) => selectedId !== id));
                              } else {
                                setSelectedRowIds([...selectedRowIds, id]);
                              }
                            }}
                          >
                            {row.cells.map((cell) => renderCell(cell, row))}
                          </TableRow>
                        ))}
                      </TableBody>
                    </Table>
                  </TableContainer>
                )}
              </DataTable>
              <Pagination
                onChange={handlePageChange}
                page={page}
                pageSize={pageSize}
                pageSizes={[10, 20]}
                totalItems={filteredMenuList.length}
                forwardText={intl.formatMessage({ id: "pagination.forward" })}
                backwardText={intl.formatMessage({ id: "pagination.backward" })}
                itemRangeText={(min, max, total) =>
                  intl.formatMessage(
                    { id: "pagination.item-range" },
                    { min, max, total }
                  )
                }
                itemsPerPageText={intl.formatMessage({
                  id: "pagination.items-per-page",
                })}
                itemText={(min, max) =>
                  intl.formatMessage(
                    { id: "pagination.item" },
                    { min, max }
                  )
                }
                pageNumberText={intl.formatMessage({
                  id: "pagination.page-number",
                })}
                pageRangeText={(_current, total) =>
                  intl.formatMessage({ id: "pagination.page-range" }, { total })
                }
                pageText={(page, pagesUnknown) =>
                  intl.formatMessage(
                    { id: "pagination.page" },
                    { page: pagesUnknown ? "" : page }
                  )
                }
              />
            </Column>
          </Grid>
        </div>
      </div>
      {/* {isAddModifyOpen && (
        <AddModifyAnalyzerTestNameForm
          data={formData}
          onClose={() => setIsAddModifyOpen(false)}
          onSubmit={handleFormSubmit}
        />
      )} */}
    </>
  );
};

export default injectIntl(AnalyzerTestName);
