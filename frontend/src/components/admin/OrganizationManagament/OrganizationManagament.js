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
  postToOpenElisServer,
  postToOpenElisServerFormData,
  postToOpenElisServerFullResponse,
  postToOpenElisServerJsonResponse,
} from "../../utils/Utils.js";
import { NotificationContext } from "../../layout/Layout.js";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification.js";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb.js";
import AddOrganization from "./AddOrganization.js";
import ModifyOrganization from "./ModifyOrganization.js";

let breadcrumbs = [
  { label: "home.label", link: "/" },
  // { label: "breadcrums.admin.managment", link: "/MasterListsPage" },
];

function OrganizationManagament() {
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  const intl = useIntl();

  const componentMounted = useRef(false);
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(5);
  const [deactivateButton, setDeactivateButton] = useState(true);
  const [modifyButton, setModifyButton] = useState(true);
  const [selectedRowIds, setSelectedRowIds] = useState([]);
  const [selectedRowIdsPost, setSelectedRowIdsPost] = useState();
  const [isEveryRowIsChecked, setIsEveryRowIsChecked] = useState(false);
  const [rowsIsPartiallyChecked, setRowsIsPartiallyChecked] = useState(false);
  const [loading, setLoading] = useState(true);
  const [isSearching, setIsSearching] = useState(false);
  const [panelSearchTerm, setPanelSearchTerm] = useState("");
  const [startingRecNo, setStartingRecNo] = useState(21);
  const [paging, setPaging] = useState(1);
  const [
    searchedOrganizationManagamentList,
    setSearchedOrganizationManagamentList,
  ] = useState();
  const [
    searchedOrganizationManagamentListShow,
    setSearchedOrganizationManagamentListShow,
  ] = useState([]);
  const [organizationsManagmentList, setOrganizationsManagmentList] =
    useState();
  const [organizationsManagmentListShow, setOrganizationsManagmentListShow] =
    useState([]);

  function deleteDeactivateOrganizationManagament(event) {
    event.preventDefault();
    setLoading(true);
    postToOpenElisServerJsonResponse(
      `/rest/DeleteOrganization?ID=${selectedRowIds.join(",")}&startingRecNo=1`,
      JSON.stringify(selectedRowIdsPost),
      deleteDeactivateOrganizationManagamentCallback(),
    );
  }

  useEffect(() => {
    const selectedIDsObject = {
      selectedIDs: selectedRowIds,
    };

    setSelectedRowIdsPost(selectedIDsObject);
  }, [selectedRowIds, organizationsManagmentListShow]);

  function deleteDeactivateOrganizationManagamentCallback() {
    setLoading(false);
    setNotificationVisible(true);
    addNotification({
      title: intl.formatMessage({
        id: "notification.title",
      }),
      message: intl.formatMessage({
        id: "notification.organization.post.delete.success",
      }),
      kind: NotificationKinds.success,
    });
    // setTimeout(() => {
    //   window.location.reload();
    // }, 2000);
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
      setOrganizationsManagmentList(res);
    }
  };

  useEffect(() => {
    componentMounted.current = true;
    setLoading(true);
    getFromOpenElisServer(
      `/rest/OrganizationMenu?paging=${paging}&startingRecNo=${startingRecNo}`,
      handleMenuItems,
    );
    return () => {
      componentMounted.current = false;
      setLoading(false);
    };
  }, []);

  const handleSearchedProviderMenuList = (res) => {
    if (!res) {
      setLoading(true);
    } else {
      setSearchedOrganizationManagamentList(res);
    }
  };

  useEffect(() => {
    getFromOpenElisServer(
      `/rest/SearchOrganizationMenu?search=Y&startingRecNo=1&searchString=${panelSearchTerm}`,
      handleSearchedProviderMenuList,
    );
  }, [panelSearchTerm]);

  useEffect(() => {
    if (organizationsManagmentList) {
      const newOrganizationsManagementList =
        organizationsManagmentList.modelMap.form.menuList.map((item) => {
          return {
            id: item.id,
            orgName:
              item.organizationName ||
              (item.organization && item.organization.organizationName) ||
              "",
            parentOrg:
              item.organizationName ||
              (item.organization && item.organization.organizationName) ||
              "",
            orgPrefix: item.shortName || "",
            active: item.isActive || "",
            streetAddress: item.internetAddress || "",
            city: item.state || "",
            cliaNumber: item.cliaNumber || "",
          };
        });
      const newOrganizationsManagementListArray = Object.values(
        newOrganizationsManagementList,
      );
      setOrganizationsManagmentListShow(newOrganizationsManagementListArray);
    }
  }, [organizationsManagmentList]);

  useEffect(() => {
    if (searchedOrganizationManagamentList) {
      const newOrganizationsManagementList =
        searchedOrganizationManagamentList.modelMap.form.menuList.map(
          (item) => {
            return {
              id: item.id,
              orgName:
                item.organizationName ||
                (item.organization && item.organization.organizationName) ||
                "",
              parentOrg:
                item.organizationName ||
                (item.organization && item.organization.organizationName) ||
                "",
              orgPrefix: item.shortName || "",
              active: item.isActive || "",
              streetAddress: item.internetAddress || "",
              city: item.state || "",
              cliaNumber: item.cliaNumber || "",
            };
          },
        );
      const newOrganizationsManagementListArray = Object.values(
        newOrganizationsManagementList,
      );
      setSearchedOrganizationManagamentListShow(
        newOrganizationsManagementListArray,
      );
    }
  }, [searchedOrganizationManagamentList]);

  useEffect(() => {
    if (selectedRowIds.length === 1) {
      setModifyButton(false);
    } else {
      setModifyButton(true);
    }
  }, [selectedRowIds]);

  // useEffect(() => {
  //   let currentPageIds;
  //   if (
  //     searchedOrganizationManagamentListShow &&
  //     organizationsManagmentListShow
  //   ) {
  //     currentPageIds = searchedOrganizationManagamentListShow
  //       .slice((page - 1) * pageSize, page * pageSize)
  //       .filter((row) => !row.disabled)
  //       .map((row) => row.id);
  //   } else {
  //     currentPageIds = organizationsManagmentListShow
  //       .slice((page - 1) * pageSize, page * pageSize)
  //       .filter((row) => !row.disabled)
  //       .map((row) => row.id);
  //   }

  //   const currentPageSelectedIds = selectedRowIds.filter((id) =>
  //     currentPageIds.includes(id),
  //   );

  //   setIsEveryRowIsChecked(
  //     currentPageSelectedIds.length === currentPageIds.length,
  //   );

  //   setRowsIsPartiallyChecked(
  //     currentPageSelectedIds.length > 0 &&
  //       currentPageSelectedIds.length < currentPageIds.length,
  //   );
  // }, [
  //   selectedRowIds,
  //   page,
  //   pageSize,
  //   organizationsManagmentListShow,
  //   searchedOrganizationManagamentListShow,
  // ]);

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
            setDeactivateButton(false);
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
    setSelectedRowIds([]);
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
                <FormattedMessage id="organization.main.title" />
              </Heading>
            </Section>
            <br />
            <Section>
              <Column lg={16} md={8} sm={4}>
                <Button
                  onClick={() => {
                    if (selectedRowIds.length === 1) {
                      const url = `/ModifyOrganization?ID=${selectedRowIds[0]}`;
                      window.location.href = url;
                    }
                  }}
                  disabled={modifyButton}
                  type="button"
                >
                  <FormattedMessage id="admin.page.configuration.formEntryConfigMenu.button.modify" />
                </Button>{" "}
                <Button
                  onClick={deleteDeactivateOrganizationManagament}
                  disabled={deactivateButton}
                  type="button"
                >
                  <FormattedMessage id="admin.page.configuration.formEntryConfigMenu.button.deactivate" />
                </Button>{" "}
                <Button
                  onClick={() => {
                    window.location.href = "/AddOrganization";
                  }}
                  type="button"
                >
                  <FormattedMessage id="admin.page.configuration.formEntryConfigMenu.button.add" />
                </Button>
              </Column>
            </Section>
          </Column>
        </Grid>
        <div className="orderLegendBody">
          <Grid>
            <Column lg={16} md={8} sm={4}>
              <Section>
                <Search
                  size="lg"
                  id="org-name-search-bar"
                  labelText={
                    <FormattedMessage id="organization.search.byorgname" />
                  }
                  placeholder={intl.formatMessage({
                    id: "organization.search.placeHolder",
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
                  <br />
                  <DataTable
                    rows={searchedOrganizationManagamentListShow.slice(
                      (page - 1) * pageSize,
                      page * pageSize,
                    )}
                    headers={[
                      {
                        key: "select",
                        header: intl.formatMessage({
                          id: "organization.select",
                        }),
                      },
                      {
                        key: "orgName",
                        header: intl.formatMessage({
                          id: "organization.organizationName",
                        }),
                      },

                      {
                        key: "parentOrg",
                        header: intl.formatMessage({
                          id: "organization.parent",
                        }),
                      },

                      {
                        key: "orgPrefix",
                        header: intl.formatMessage({
                          id: "organization.short.CI",
                        }),
                      },
                      {
                        key: "active",
                        header: intl.formatMessage({
                          id: "organization.isActive",
                        }),
                      },
                      {
                        key: "streetAddress",
                        header: intl.formatMessage({
                          id: "organization.streetAddress",
                        }),
                      },
                      {
                        key: "city",
                        header: intl.formatMessage({
                          id: "organization.city",
                        }),
                      },
                      {
                        key: "cliaNumber",
                        header: intl.formatMessage({
                          id: "organization.clia.number",
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
                                checked={
                                  selectedRowIds.length === pageSize &&
                                  searchedOrganizationManagamentListShow
                                    .slice(
                                      (page - 1) * pageSize,
                                      page * pageSize,
                                    )
                                    .filter(
                                      (row) =>
                                        !row.disabled &&
                                        selectedRowIds.includes(row.id),
                                    ).length === pageSize
                                }
                                // checked={isEveryRowIsChecked}
                                indeterminate={
                                  selectedRowIds.length > 0 &&
                                  selectedRowIds.length <
                                    searchedOrganizationManagamentListShow
                                      .slice(
                                        (page - 1) * pageSize,
                                        page * pageSize,
                                      )
                                      .filter((row) => !row.disabled).length
                                }
                                // indeterminate={rowsIsPartiallyChecked}
                                onSelect={() => {
                                  setDeactivateButton(false);
                                  const currentPageIds =
                                    searchedOrganizationManagamentListShow
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
                    pageSizes={[5, 10, 15, 20]}
                    totalItems={searchedOrganizationManagamentListShow.length}
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
                  <br />
                </Column>
              </Grid>
            </>
          ) : (
            <>
              <Grid fullWidth={true} className="gridBoundary">
                <Column lg={16} md={8} sm={4}>
                  <DataTable
                    rows={organizationsManagmentListShow.slice(
                      (page - 1) * pageSize,
                      page * pageSize,
                    )}
                    headers={[
                      {
                        key: "select",
                        header: intl.formatMessage({
                          id: "organization.select",
                        }),
                      },
                      {
                        key: "orgName",
                        header: intl.formatMessage({
                          id: "organization.organizationName",
                        }),
                      },

                      {
                        key: "parentOrg",
                        header: intl.formatMessage({
                          id: "organization.parent",
                        }),
                      },

                      {
                        key: "orgPrefix",
                        header: intl.formatMessage({
                          id: "organization.short.CI",
                        }),
                      },
                      {
                        key: "active",
                        header: intl.formatMessage({
                          id: "organization.isActive",
                        }),
                      },
                      {
                        key: "streetAddress",
                        header: intl.formatMessage({
                          id: "organization.streetAddress",
                        }),
                      },
                      {
                        key: "city",
                        header: intl.formatMessage({
                          id: "organization.city",
                        }),
                      },
                      {
                        key: "cliaNumber",
                        header: intl.formatMessage({
                          id: "organization.clia.number",
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
                                checked={
                                  selectedRowIds.length === pageSize &&
                                  organizationsManagmentListShow
                                    .slice(
                                      (page - 1) * pageSize,
                                      page * pageSize,
                                    )
                                    .filter(
                                      (row) =>
                                        !row.disabled &&
                                        selectedRowIds.includes(row.id),
                                    ).length === pageSize
                                }
                                // checked={isEveryRowIsChecked}
                                indeterminate={
                                  selectedRowIds.length > 0 &&
                                  selectedRowIds.length <
                                    organizationsManagmentListShow
                                      .slice(
                                        (page - 1) * pageSize,
                                        page * pageSize,
                                      )
                                      .filter((row) => !row.disabled).length
                                }
                                // indeterminate={rowsIsPartiallyChecked}
                                onSelect={() => {
                                  setDeactivateButton(false);
                                  const currentPageIds =
                                    organizationsManagmentListShow
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
                    pageSizes={[5, 10, 15, 20]}
                    totalItems={organizationsManagmentListShow.length}
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
      <div>
        <button
          onClick={() => {
            console.error(organizationsManagmentList);
          }}
        >
          gotData
        </button>
        <button
          onClick={() => {
            console.error(selectedRowIds);
          }}
        >
          selectedRowIds
        </button>
        <button
          onClick={() => {
            console.error(selectedRowIdsPost);
          }}
        >
          selectedRowIdsPost
        </button>
      </div>
    </>
  );
}

export default injectIntl(OrganizationManagament);
