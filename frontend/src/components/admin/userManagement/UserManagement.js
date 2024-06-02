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
  Select,
  SelectItem,
  Stack,
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
import CustomCheckBox from "../../common/CustomCheckBox.js";

let breadcrumbs = [
  { label: "home.label", link: "/" },
  // { label: "breadcrums.admin.managment", link: "/MasterListsPage" },
];

function UserManagement() {
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  const intl = useIntl();

  const componentMounted = useRef(false);
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(5);
  const [deactivateButton, setDeactivateButton] = useState(true);
  const [modifyButton, setModifyButton] = useState(true);
  const [selectedRowIds, setSelectedRowIds] = useState([]);
  const [selectedRowCombinedUserID, setSelectedRowCombinedUserID] = useState(
    [],
  );
  const [selectedRowIdsPost, setSelectedRowIdsPost] = useState();
  const [isEveryRowIsChecked, setIsEveryRowIsChecked] = useState(false);
  const [rowsIsPartiallyChecked, setRowsIsPartiallyChecked] = useState(false);
  const [loading, setLoading] = useState(true);
  const [isSearching, setIsSearching] = useState(false);
  const [panelSearchTerm, setPanelSearchTerm] = useState("");
  const [roleFilter, setRoleFilter] = useState(null);
  const [filter, setFilter] = useState([]);
  const [startingRecNo, setStartingRecNo] = useState(21);
  const [paging, setPaging] = useState(1);
  const [searchedUserManagementList, setSearchedUserManagementList] =
    useState();
  const [searchedUserManagementListShow, setSearchedUserManagementListShow] =
    useState([]);
  const [userManagementList, setUserManagementList] = useState();
  const [userManagementListShow, setUserManagementListShow] = useState([]);

  function deleteDeactivateUserManagement(event) {
    event.preventDefault();
    setLoading(true);
    postToOpenElisServerJsonResponse(
      `/rest/DeleteOrganization?ID=${selectedRowIds.join(",")}&startingRecNo=1`,
      JSON.stringify(selectedRowIdsPost),
      deleteDeactivateUserManagementCallback(),
    );
  }

  useEffect(() => {
    const selectedIDsObject = {
      selectedIDs: selectedRowIds,
    };

    setSelectedRowIdsPost(selectedIDsObject);
  }, [selectedRowIds, userManagementListShow]);

  function deleteDeactivateUserManagementCallback() {
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
    setSelectedRowCombinedUserID([]);
  };

  const handleMenuItems = (res) => {
    if (!res) {
      setLoading(true);
    } else {
      setUserManagementList(res);
    }
  };

  useEffect(() => {
    componentMounted.current = true;
    setLoading(true);
    getFromOpenElisServer(`/rest/UnifiedSystemUserMenu`, handleMenuItems);
    return () => {
      componentMounted.current = false;
      setLoading(false);
    };
  }, []);

  const handleSearchedProviderMenuList = (res) => {
    if (!res) {
      setLoading(true);
    } else {
      setSearchedUserManagementList(res);
    }
  };

  useEffect(() => {
    getFromOpenElisServer(
      `/rest/SearchUnifiedSystemUserMenu?search=Y&startingRecNo=1&searchString=${panelSearchTerm}&filter=${filter}&roleFilter=${roleFilter}`,
      handleSearchedProviderMenuList,
    );
  }, [panelSearchTerm]);

  useEffect(() => {
    if (userManagementList) {
      const newUserManagementList = userManagementList.menuList.map((item) => {
        return {
          id: item.systemUserId,
          combinedUserID: item.combinedUserID,
          firstName: item.firstName || "",
          lastName: item.lastName || "",
          loginName: item.loginName || "",
          expDate: item.expDate || "",
          locked: item.locked || "",
          disabled: item.disabled || "",
          active: item.active || "",
          timeout: item.timeout || "",
        };
      });
      const newUserManagementListArray = Object.values(newUserManagementList);
      setUserManagementListShow(newUserManagementListArray);
    }
  }, [userManagementList]);

  useEffect(() => {
    if (searchedUserManagementList) {
      const newUserManagementList = searchedUserManagementList.menuList.map(
        (item) => {
          return {
            id: item.systemUserId,
            combinedUserID: item.combinedUserID,
            firstName: item.firstName || "",
            lastName: item.lastName || "",
            loginName: item.loginName || "",
            expDate: item.expDate || "",
            locked: item.locked || "",
            disabled: item.disabled || "",
            active: item.active || "",
            timeout: item.timeout || "",
          };
        },
      );
      const newUserManagementListArray = Object.values(newUserManagementList);
      setSearchedUserManagementListShow(newUserManagementListArray);
    }
  }, [searchedUserManagementList]);

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
  //     searchedUserManagementListShow &&
  //     userManagementListShow
  //   ) {
  //     currentPageIds = searchedUserManagementListShow
  //       .slice((page - 1) * pageSize, page * pageSize)
  //       .filter((row) => !row.disabled)
  //       .map((row) => row.id);
  //   } else {
  //     currentPageIds = userManagementListShow
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
  //   userManagementListShow,
  //   searchedUserManagementListShow,
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
              setSelectedRowCombinedUserID((prevIds) =>
                prevIds.filter(
                  (selectedId) => selectedId !== row.combinedUserID,
                ),
              );
            } else {
              setSelectedRowIds([...selectedRowIds, row.id]);
              setSelectedRowCombinedUserID((prevIds) => [
                ...prevIds,
                row.combinedUserID,
              ]);
            }
          }}
        />
      );
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
                <FormattedMessage id="unifiedSystemUser.browser.title" />
              </Heading>
            </Section>
            <br />
            <Section>
              <Section>
                <Section>
                  <Section>
                    <Heading>
                      <FormattedMessage id="user.select.instruction" />
                    </Heading>
                  </Section>
                </Section>
              </Section>
            </Section>
            <br />
            <Section>
              <Column lg={16} md={8} sm={4}>
                <Button
                  onClick={() => {
                    if (selectedRowIds.length === 1) {
                      const url = `/UnifiedSystemUser?ID=${selectedRowIds[0]}&startingRecNo=1&roleFilter=`;
                      window.location.href = url;
                    }
                  }}
                  disabled={modifyButton}
                  type="button"
                >
                  <FormattedMessage id="admin.page.configuration.formEntryConfigMenu.button.modify" />
                </Button>{" "}
                <Button
                  onClick={deleteDeactivateUserManagement}
                  disabled={deactivateButton}
                  type="button"
                >
                  <FormattedMessage id="admin.page.configuration.formEntryConfigMenu.button.deactivate" />
                </Button>{" "}
                <Button
                  // UnifiedSystemUser?ID=0&startingRecNo=1&roleFilter=
                  onClick={() => {
                    window.location.href = "/AddUser";
                  }}
                  type="button"
                >
                  <FormattedMessage id="unifiedSystemUser.browser.button.add" />
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
                  id="user-name-search-bar"
                  labelText={
                    <FormattedMessage id="unifiedSystemUser.browser.search" />
                  }
                  placeholder={intl.formatMessage({
                    id: "unifiedSystemUser.browser.search.placeholder",
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
          <Grid fullWidth={true}>
            <Column lg={2} md={2} sm={1}>
              <FormattedMessage id="menu.label.filter" />
            </Column>
            <Column lg={6} md={6} sm={3}>
              <Select id={`select-3`} noLabel={true} defaultValue="option-3">
                <SelectItem value="option-1" text="Option 1" />
                <SelectItem value="option-2" text="Option 2" />
                <SelectItem value="option-3" text="Option 3" />
              </Select>
              <br />
              <FormattedMessage id="menu.label.filter.role" />
            </Column>
            <Column lg={8} md={8} sm={4}>
              <CustomCheckBox
                id="only-active"
                label={<FormattedMessage id="menu.label.filter.active" />}
                onChange={(data) => {
                  setFilter(...data, "isActive");
                }}
              />
              <br />
              <CustomCheckBox
                id="only-administrator"
                label={<FormattedMessage id="menu.label.filter.admin" />}
                onChange={(data) => {
                  setFilter(...data, "isAdmin");
                }}
              />
            </Column>
          </Grid>
          <br />
          {isSearching ? (
            <>
              <Grid fullWidth={true} className="gridBoundary">
                <Column lg={16} md={8} sm={4}>
                  <br />
                  <DataTable
                    rows={searchedUserManagementListShow.slice(
                      (page - 1) * pageSize,
                      page * pageSize,
                    )}
                    headers={[
                      {
                        key: "select",
                        header: intl.formatMessage({
                          id: "unifiedSystemUser.select",
                        }),
                      },
                      {
                        key: "firstName",
                        header: intl.formatMessage({
                          id: "systemuser.firstName",
                        }),
                      },
                      {
                        key: "lastName",
                        header: intl.formatMessage({
                          id: "systemuser.lastName",
                        }),
                      },
                      {
                        key: "loginName",
                        header: intl.formatMessage({
                          id: "systemuser.loginName",
                        }),
                      },
                      {
                        key: "expDate",
                        header: intl.formatMessage({
                          id: "login.password.expired.date",
                        }),
                      },
                      {
                        key: "locked",
                        header: intl.formatMessage({
                          id: "login.account.locked",
                        }),
                      },
                      {
                        key: "disabled",
                        header: intl.formatMessage({
                          id: "login.account.disabled",
                        }),
                      },
                      {
                        key: "active",
                        header: intl.formatMessage({
                          id: "systemuser.isActive",
                        }),
                      },
                      {
                        key: "timeout",
                        header: intl.formatMessage({
                          id: "login.timeout",
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
                                  searchedUserManagementListShow
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
                                    searchedUserManagementListShow
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
                                    searchedUserManagementListShow
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
                                    const CombinedUserID = row.combinedUserID;
                                    const isSelected =
                                      selectedRowIds.includes(id);
                                    if (isSelected) {
                                      setSelectedRowIds(
                                        selectedRowIds.filter(
                                          (selectedId) => selectedId !== id,
                                        ),
                                      );
                                      setSelectedRowCombinedUserID(
                                        selectedRowCombinedUserID.filter(
                                          (selectedId) =>
                                            selectedId !== CombinedUserID,
                                        ),
                                      );
                                    } else {
                                      setSelectedRowIds([
                                        ...selectedRowIds,
                                        id,
                                      ]);
                                      setSelectedRowCombinedUserID([
                                        ...selectedRowCombinedUserID,
                                        CombinedUserID,
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
                    totalItems={searchedUserManagementListShow.length}
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
                    rows={userManagementListShow.slice(
                      (page - 1) * pageSize,
                      page * pageSize,
                    )}
                    headers={[
                      {
                        key: "select",
                        header: intl.formatMessage({
                          id: "unifiedSystemUser.select",
                        }),
                      },
                      {
                        key: "firstName",
                        header: intl.formatMessage({
                          id: "systemuser.firstName",
                        }),
                      },
                      {
                        key: "lastName",
                        header: intl.formatMessage({
                          id: "systemuser.lastName",
                        }),
                      },
                      {
                        key: "loginName",
                        header: intl.formatMessage({
                          id: "systemuser.loginName",
                        }),
                      },
                      {
                        key: "expDate",
                        header: intl.formatMessage({
                          id: "login.password.expired.date",
                        }),
                      },
                      {
                        key: "locked",
                        header: intl.formatMessage({
                          id: "login.account.locked",
                        }),
                      },
                      {
                        key: "disabled",
                        header: intl.formatMessage({
                          id: "login.account.disabled",
                        }),
                      },
                      {
                        key: "active",
                        header: intl.formatMessage({
                          id: "systemuser.isActive",
                        }),
                      },
                      {
                        key: "timeout",
                        header: intl.formatMessage({
                          id: "login.timeout",
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
                                  userManagementListShow
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
                                    userManagementListShow
                                      .slice(
                                        (page - 1) * pageSize,
                                        page * pageSize,
                                      )
                                      .filter((row) => !row.disabled).length
                                }
                                // indeterminate={rowsIsPartiallyChecked}
                                onSelect={() => {
                                  setDeactivateButton(false);
                                  const currentPageIds = userManagementListShow
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
                                    const CombinedUserID = row.combinedUserID;
                                    const isSelected =
                                      selectedRowIds.includes(id);
                                    if (isSelected) {
                                      setSelectedRowIds(
                                        selectedRowIds.filter(
                                          (selectedId) => selectedId !== id,
                                        ),
                                      );
                                      setSelectedRowCombinedUserID(
                                        selectedRowCombinedUserID.filter(
                                          (selectedId) =>
                                            selectedId !== CombinedUserID,
                                        ),
                                      );
                                    } else {
                                      setSelectedRowIds([
                                        ...selectedRowIds,
                                        id,
                                      ]);
                                      setSelectedRowCombinedUserID([
                                        ...selectedRowCombinedUserID,
                                        CombinedUserID,
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
                    totalItems={userManagementListShow.length}
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
            console.error(userManagementList);
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
            console.error(selectedRowCombinedUserID);
          }}
        >
          selectedRowIdCustomeIds
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

export default injectIntl(UserManagement);

// combinedUserID need to handle at modify [ new array on select and give him ]
// addUser and Modify page
// main app need to handel there route
// route should send configuration with ID and perfect route
