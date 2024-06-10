import React, { useContext, useState, useEffect, useRef } from "react";
import {
  Form,
  Heading,
  Button,
  Loading,
  Grid,
  Column,
  Section,
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
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
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
  const [loading, setLoading] = useState(true);
  const [isSearching, setIsSearching] = useState(false);
  const [panelSearchTerm, setPanelSearchTerm] = useState("");
  const [searchedProviderMenuList, setSearchedProviderMenuList] = useState([]);
  const [serachedProviderMenuListShow, setSearchedProviderMenuListShow] =
    useState([]);
  // const [startingRecNo, setStartingRecNo] = useState(1);
  const [providerMenuList, setProviderMenuList] = useState([]);
  const [providerMenuListShow, setProviderMenuListShow] = useState([]);

  function deleteDeactivateProvider(event) {
    event.preventDefault();
    setLoading(true);
    postToOpenElisServerFullResponse(
      `/DeleteProvider?ID=${selectedRowIds.join(",")}&startingRecNo=1`,
      serachedProviderMenuListShow || providerMenuListShow, // need to check against the form of restController [mentor]
      setLoading(false),
      setTimeout(() => {
        window.location.reload();
      }, 1000),
    );
  }

  const handlePageChange = ({ page, pageSize }) => {
    setPage(page);
    setPageSize(pageSize);
    setSelectedRowIds([]);
  };

  const handleMenuItems = (res) => {
    if (!res) {
      setLoading(true);
    } else {
      setProviderMenuList(res);
    }
  };

  useEffect(() => {
    componentMounted.current = true;
    setLoading(true);
    getFromOpenElisServer(`/rest/ProviderMenu`, handleMenuItems);
    return () => {
      componentMounted.current = false;
      setLoading(false);
    };
  }, []);

  const handleSearchedProviderMenuList = (res) => {
    if (res) {
      setSearchedProviderMenuList(res);
    }
  };

  useEffect(() => {
    getFromOpenElisServer(
      `/rest/SearchProviderMenu?search=Y&startingRecNo=1&searchString=${panelSearchTerm}`,
      handleSearchedProviderMenuList,
    );
  }, [panelSearchTerm]);

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
    if (searchedProviderMenuList) {
      const newProviderMenuList = searchedProviderMenuList.map((item) => {
        return {
          id: item.id,
          lastName: item.person.lastName,
          firstName: item.person.firstName,
          active: item.active,
          telephone: item.person.telephone,
          fax: item.person.fax,
        };
      });
      setSearchedProviderMenuListShow(newProviderMenuList);
    }
  }, [searchedProviderMenuList]);

  useEffect(() => {
    let currentPageIds;
    if (searchedProviderMenuList) {
      currentPageIds = searchedProviderMenuList
        .slice((page - 1) * pageSize, page * pageSize)
        .filter((row) => !row.disabled)
        .map((row) => row.id);
    } else {
      currentPageIds = providerMenuListShow
        .slice((page - 1) * pageSize, page * pageSize)
        .filter((row) => !row.disabled)
        .map((row) => row.id);
    }

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
  }, [
    selectedRowIds,
    page,
    pageSize,
    providerMenuListShow,
    serachedProviderMenuListShow,
  ]);

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
    setIsSearching(true);
    const query = event.target.value.toLowerCase();
    setPanelSearchTerm(query);
  };

  if (!loading) {
    return (
      <>
        <Loading />
      </>
    );
  }

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
              <Form onSubmit={deleteDeactivateProvider}>
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
          {isSearching ? (
            <>
              <Grid fullWidth={true} className="gridBoundary">
                <Column lg={16} md={8} sm={4}>
                  <DataTable
                    rows={serachedProviderMenuListShow.slice(
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
                                //   serachedProviderMenuListShow
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
                                //     serachedProviderMenuListShow
                                //       .slice((page - 1) * pageSize, page * pageSize)
                                //       .filter((row) => !row.disabled).length
                                // }
                                indeterminate={rowsIsPartiallyChecked}
                                onSelect={() => {
                                  setModifyButton(false);
                                  const currentPageIds =
                                    serachedProviderMenuListShow
                                      .slice(
                                        (page - 1) * pageSize,
                                        page * pageSize,
                                      )
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
                                    const isSelected =
                                      selectedRowIds.includes(id);
                                    if (isSelected) {
                                      setSelectedRowIds(
                                        selectedRowIds.filter(
                                          (selectedId) => selectedId !== id,
                                        ),
                                      );
                                    } else {
                                      setSelectedRowIds([
                                        ...selectedRowIds,
                                        id,
                                      ]);
                                    }
                                  }}
                                >
                                  {row.cells.map((cell) =>
                                    renderCell(cell, row),
                                  )}
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
                    totalItems={serachedProviderMenuListShow.length}
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
            </>
          ) : (
            <>
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
                                    .slice(
                                      (page - 1) * pageSize,
                                      page * pageSize,
                                    )
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
                                    const isSelected =
                                      selectedRowIds.includes(id);
                                    if (isSelected) {
                                      setSelectedRowIds(
                                        selectedRowIds.filter(
                                          (selectedId) => selectedId !== id,
                                        ),
                                      );
                                    } else {
                                      setSelectedRowIds([
                                        ...selectedRowIds,
                                        id,
                                      ]);
                                    }
                                  }}
                                >
                                  {row.cells.map((cell) =>
                                    renderCell(cell, row),
                                  )}
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
            </>
          )}
        </div>
      </div>
    </>
  );
}

export default injectIntl(ProviderMenu);
