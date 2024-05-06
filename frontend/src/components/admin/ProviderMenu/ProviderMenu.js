import React, { useContext, useState, useEffect, useRef } from "react";
import {
  Form,
  Heading,
  Toggle,
  Button,
  Loading,
  Grid,
  Column,
  Section,
  Checkbox,
  DataTable,
  Table,
  TableHead,
  TableRow,
  TableBody,
  TableHeader,
  TableCell,
  TableSelectRow,
  TableSelectAll,
  TableContainer,
  Pagination,
  Search,
} from "@carbon/react";
import {
  getFromOpenElisServer,
  postToOpenElisServerFullResponse,
} from "../../utils/Utils.js";
import { NotificationContext } from "../../layout/Layout.js";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification.js";
import { FormattedMessage, useIntl } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb.js";

let breadcrumbs = [
  { label: "home.label", link: "/" },
  // { label: "breadcrums.admin.managment", link: "/MasterListsPage" },
];
function ProviderMenu() {
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  const intl = useIntl();

  const componentMounted = useRef(false);
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(5);
  const [modifyButton, setModifyButton] = useState(true);
  const [selectedRowIds, setSelectedRowIds] = useState([]);
  const [isEveryRowIsChecked, setIsEveryRowIsChecked] = useState(false);
  const [rowsIsPartiallyChecked, setRowsIsPartiallyChecked] = useState(false);
  const [panelSearchTerm, setPanelSearchTerm] = useState("");
  const [searchBoxPanels, setSearchBoxPanels] = useState([]);
  const [startingRecNo, setStartingRecNo] = useState(1);
  const [providerMenuList, setProviderMenuList] = useState([]);
  const [providerMenuListShow, setProviderMenuListShow] = useState([]);

  const [ConfigEdit, setConfigEdit] = useState(false);

  function handleModify(event) {
    event.preventDefault();
    setConfigEdit(true);
  }

  const handlePageChange = ({ page, pageSize }) => {
    setPage(page);
    setPageSize(pageSize);
    setSelectedRowIds([]);
  };

  const handleMenuItems = (res) => {
    if (res) {
      setProviderMenuList(res);
    }
  };

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer(`/rest/ProviderMenu`, handleMenuItems);
    return () => {
      componentMounted.current = false;
    };
  }, []);

  useEffect(() => {
    if (providerMenuList) {
      const newProviderMenuList = providerMenuList.map((item) => {
        return {
          id: item.id,
          lastName: item.person.lastName,
          firstName: item.person.firstName,
          active: item.active,
          telephone: item.person.telephone,
          fax: item.person.fax,
        };
      });
      setProviderMenuListShow(newProviderMenuList);
    }
  }, [providerMenuList]);

  useEffect(() => {
    const currentPageIds = providerMenuListShow
      .slice((page - 1) * pageSize, page * pageSize)
      .filter((row) => !row.disabled)
      .map((row) => row.id);

    const currentPageSelectedIds = selectedRowIds.filter((id) =>
      currentPageIds.includes(id),
    );

    setIsEveryRowIsChecked(
      currentPageSelectedIds.length === currentPageIds.length,
    );

    setRowsIsPartiallyChecked(
      currentPageSelectedIds.length > 0 &&
        currentPageSelectedIds.length < currentPageIds.length,
    );
  }, [selectedRowIds, page, pageSize, providerMenuListShow]);

  const renderCell = (cell, row) => {
    if (cell.info.header === "select") {
      return (
        <TableSelectRow
          key={cell.id}
          id={cell.id}
          checked={selectedRowIds.includes(row.id)}
          name="selectRowCheckbox"
          ariaLabel="selectRows"
          onSelect={() => {
            setModifyButton(false);
            if (selectedRowIds.includes(row.id)) {
              setSelectedRowIds(selectedRowIds.filter((id) => id !== row.id));
            } else {
              setSelectedRowIds([...selectedRowIds, row.id]);
            }
          }}
        />
      );
    } else if (cell.info.header === "active") {
      return <TableCell key={cell.id}>{cell.value.toString()}</TableCell>;
    } else {
      return <TableCell key={cell.id}>{cell.value}</TableCell>;
    }
  };

  const handlePanelSearchChange = (event) => {
    const query = event.target.value.toLowerCase();
    setPanelSearchTerm(query);
    const results = providerMenuListShow.filter(
      (items) =>
        items.lastName.toLowerCase().includes(query) ||
        items.firstName.toLowerCase().includes(query),
    );
    setSearchBoxPanels(results);
  };

  return (
    <>
      {notificationVisible === true ? <AlertDialog /> : ""}
      <div className="adminPageContent">
        <PageBreadCrumb breadcrumbs={breadcrumbs} />
        <Grid>
          <Column lg={16} md={8} sm={4}>
            <Section>
              <Heading>
                <FormattedMessage id="provider.browse.title" />
              </Heading>
            </Section>
            <br />
            <Section>
              <Form onSubmit={handleModify}>
                <Column lg={16} md={8} sm={4}>
                  <Button kind="tertiary" disabled={true} type="submit">
                    <FormattedMessage id="admin.page.configuration.formEntryConfigMenu.button.modify" />
                  </Button>{" "}
                  <Button disabled={modifyButton} type="submit">
                    <FormattedMessage id="admin.page.configuration.formEntryConfigMenu.button.deactivate" />
                  </Button>{" "}
                  <Button kind="tertiary" disabled={true} type="submit">
                    <FormattedMessage id="admin.page.configuration.formEntryConfigMenu.button.add" />
                  </Button>
                </Column>
              </Form>
            </Section>
          </Column>
        </Grid>
        <div className="orderLegendBody">
          <Grid>
            <Column lg={16} md={8} sm={4}>
              <Section>
                <Search
                  size="lg"
                  id="provider-search-bar"
                  labelText={<FormattedMessage id="provider.search" />}
                  placeholder={intl.formatMessage({
                    id: "provider.search.placeholder",
                  })}
                  onChange={handlePanelSearchChange}
                  value={(() => {
                    if (panelSearchTerm) {
                      return panelSearchTerm;
                    }
                    return "";
                  })()}
                ></Search>
              </Section>
            </Column>
          </Grid>
          <br />
          <Grid fullWidth={true} className="gridBoundary">
            <Column lg={16} md={8} sm={4}>
              <DataTable
                rows={providerMenuListShow.slice(
                  (page - 1) * pageSize,
                  page * pageSize,
                )}
                headers={[
                  {
                    key: "select",
                    header: intl.formatMessage({
                      id: "provider.select",
                    }),
                  },
                  {
                    key: "lastName",
                    header: intl.formatMessage({
                      id: "provider.providerLastName",
                    }),
                  },

                  {
                    key: "firstName",
                    header: intl.formatMessage({
                      id: "provider.providerFirstName",
                    }),
                  },
                  {
                    key: "active",
                    header: intl.formatMessage({
                      id: "provider.isActive",
                    }),
                  },
                  {
                    key: "telephone",
                    header: intl.formatMessage({
                      id: "provider.telephone",
                    }),
                  },
                  {
                    key: "fax",
                    header: intl.formatMessage({
                      id: "provider.fax",
                    }),
                  },
                ]}
              >
                {({
                  rows,
                  headers,
                  getHeaderProps,
                  getTableProps,
                  getSelectionProps,
                }) => (
                  <TableContainer>
                    <Table {...getTableProps()}>
                      <TableHead>
                        <TableRow>
                          <TableSelectAll
                            id="table-select-all"
                            {...getSelectionProps()}
                            // checked={
                            //   selectedRowIds.length === pageSize &&
                            //   providerMenuListShow
                            //     .slice((page - 1) * pageSize, page * pageSize)
                            //     .filter(
                            //       (row) =>
                            //         !row.disabled &&
                            //         selectedRowIds.includes(row.id),
                            //     ).length === pageSize
                            // }
                            checked={isEveryRowIsChecked}
                            // indeterminate={
                            //   selectedRowIds.length > 0 &&
                            //   selectedRowIds.length <
                            //     providerMenuListShow
                            //       .slice((page - 1) * pageSize, page * pageSize)
                            //       .filter((row) => !row.disabled).length
                            // }
                            indeterminate={rowsIsPartiallyChecked}
                            onSelect={() => {
                              setModifyButton(false);
                              const currentPageIds = providerMenuListShow
                                .slice((page - 1) * pageSize, page * pageSize)
                                .filter((row) => !row.disabled)
                                .map((row) => row.id);
                              if (
                                selectedRowIds.length === pageSize &&
                                currentPageIds.every((id) =>
                                  selectedRowIds.includes(id),
                                )
                              ) {
                                setSelectedRowIds([]);
                              } else {
                                setSelectedRowIds(
                                  currentPageIds.filter(
                                    (id) => !selectedRowIds.includes(id),
                                  ),
                                );
                              }
                            }}
                          />
                          {headers.map(
                            (header) =>
                              header.key !== "select" && (
                                <TableHeader
                                  key={header.key}
                                  {...getHeaderProps({ header })}
                                >
                                  {header.header}
                                </TableHeader>
                              ),
                          )}
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        <>
                          {rows.map((row) => (
                            <TableRow
                              key={row.id}
                              onClick={() => {
                                const id = row.id;
                                const isSelected = selectedRowIds.includes(id);
                                if (isSelected) {
                                  setSelectedRowIds(
                                    selectedRowIds.filter(
                                      (selectedId) => selectedId !== id,
                                    ),
                                  );
                                } else {
                                  setSelectedRowIds([...selectedRowIds, id]);
                                }
                              }}
                            >
                              {row.cells.map((cell) => renderCell(cell, row))}
                            </TableRow>
                          ))}
                        </>
                      </TableBody>
                    </Table>
                  </TableContainer>
                )}
              </DataTable>
              <Pagination
                onChange={handlePageChange}
                page={page}
                pageSize={pageSize}
                pageSizes={[5, 10, 15, 20, 25, 30]}
                totalItems={providerMenuListShow.length}
                forwardText={intl.formatMessage({
                  id: "pagination.forward",
                })}
                backwardText={intl.formatMessage({
                  id: "pagination.backward",
                })}
                itemRangeText={(min, max, total) =>
                  intl.formatMessage(
                    { id: "pagination.item-range" },
                    { min: min, max: max, total: total },
                  )
                }
                itemsPerPageText={intl.formatMessage({
                  id: "pagination.items-per-page",
                })}
                itemText={(min, max) =>
                  intl.formatMessage(
                    { id: "pagination.item" },
                    { min: min, max: max },
                  )
                }
                pageNumberText={intl.formatMessage({
                  id: "pagination.page-number",
                })}
                pageRangeText={(_current, total) =>
                  intl.formatMessage(
                    { id: "pagination.page-range" },
                    { total: total },
                  )
                }
                pageText={(page, pagesUnknown) =>
                  intl.formatMessage(
                    { id: "pagination.page" },
                    { page: pagesUnknown ? "" : page },
                  )
                }
              />
            </Column>
          </Grid>
        </div>
      </div>

      <div>
        <button
          onClick={() => {
            console.log(providerMenuList);
          }}
        >
          gotData
        </button>
        <button
          onClick={() => {
            console.log(providerMenuListShow);
          }}
        >
          postData
        </button>
        <button
          onClick={() => {
            console.log(selectedRowIds);
          }}
        >
          rowData
        </button>
        <button
          onClick={() => {
            console.log(searchBoxPanels);
          }}
        >
          searchedData
        </button>
      </div>
    </>
  );
}

export default ProviderMenu;
