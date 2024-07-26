import React, { useContext, useState, useEffect, useRef } from "react";
import {
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
  Modal,
  TextInput,
  Dropdown,
  Checkbox,
} from "@carbon/react";
import {
  getFromOpenElisServer,
  postToOpenElisServerFullResponse,
} from "../../utils/Utils.js";
import {
  ConfigurationContext,
  NotificationContext,
} from "../../layout/Layout.js";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification.js";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb.js";
import { Settings } from "@carbon/icons-react";
import ActionPaginationButtonType from "../../common/ActionPaginationButtonType.js";

let breadcrumbs = [
  { label: "home.label", link: "/" },
  { label: "breadcrums.admin.managment", link: "/MasterListsPage" },
  {
    label: "testnotificationconfig.browse.title",
    link: "/MasterListsPage#testNotificationConfigMenu",
  },
];

function TestNotificationConfigMenu() {
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  const intl = useIntl();

  const componentMounted = useRef(false);
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);
  const [loading, setLoading] = useState(true);
  const [testNamesList, setTestNamesList] = useState([]);
  const [testNotificationConfigMenuData, setTestNotificationConfigMenuData] =
    useState({});
  const [
    testNotificationConfigMenuDataPost,
    setTestNotificationConfigMenuDataPost,
  ] = useState({});
  const [
    testNotificationConfigMenuDataMenuList,
    setTestNotificationConfigMenuDataMenuList,
  ] = useState([]);
  const [testNamesMap, setTestNamesMap] = useState({});

  const handleMenuItems = (res) => {
    if (!res) {
      setLoading(true);
    } else {
      setTestNotificationConfigMenuData(res);
    }
  };

  const handleTestNamesList = (res) => {
    if (!res) {
      setLoading(true);
    } else {
      setTestNamesList(res);
    }
  };

  useEffect(() => {
    componentMounted.current = true;
    setLoading(true);
    getFromOpenElisServer(`/rest/TestNotificationConfigMenu`, handleMenuItems);
    getFromOpenElisServer(`/rest/test-list`, handleTestNamesList);
    return () => {
      componentMounted.current = false;
      setLoading(false);
    };
  }, []);

  useEffect(() => {
    if (
      testNotificationConfigMenuData &&
      testNotificationConfigMenuData.menuList
    ) {
      setTestNotificationConfigMenuDataMenuList(
        testNotificationConfigMenuData.menuList,
      );
      setTestNotificationConfigMenuDataPost((prevTestNotificationDataPost) => ({
        ...prevTestNotificationDataPost,
        formMethod: testNotificationConfigMenuData.formMethod,
        cancelAction: testNotificationConfigMenuData.cancelAction,
        submitOnCancel: testNotificationConfigMenuData.submitOnCancel,
        cancelMethod: testNotificationConfigMenuData.cancelMethod,
        adminMenuItems: testNotificationConfigMenuData.adminMenuItems,
        totalRecordCount: testNotificationConfigMenuData.totalRecordCount,
        fromRecordCount: testNotificationConfigMenuData.fromRecordCount,
        toRecordCount: testNotificationConfigMenuData.toRecordCount,
        selectedIDs: testNotificationConfigMenuData.selectedIDs,
        menuList: testNotificationConfigMenuData.menuList,
      }));
    }
  }, [testNotificationConfigMenuData]);

  useEffect(() => {
    const map = testNamesList.reduce((acc, item) => {
      acc[item.id] = item.value;
      return acc;
    }, {});
    setTestNamesMap(map);
  }, [testNamesList]);

  const handleEditButtonClick = (id) => {
    window.location.assign(
      `/MasterListsPage#testNotificationConfig?testId=${id}`,
    );
  };

  const renderCell = (cell, row) => {
    // if (cell.info.header === "id") {
    //   return (
    //     <TableSelectRow
    //       key={cell.id}
    //       id={cell.id}
    //       // checked={selectedRowIds.includes(row.id)}
    //       name="selectRowCheckbox"
    //       ariaLabel="selectRows"
    //       // onSelect={() => {
    //       //   setDeactivateButton(false);
    //       //   if (selectedRowIds.includes(row.id)) {
    //       //     setSelectedRowIds(selectedRowIds.filter((id) => id !== row.id));
    //       //   } else {
    //       //     setSelectedRowIds([...selectedRowIds, row.id]);
    //       //   }
    //       // }}
    //     />
    //   );
    // } else

    if (cell.info.header === "testId") {
      return <TableCell key={cell.id}>{cell.value}</TableCell>;
    } else if (cell.info.header === "testName") {
      return <TableCell key={cell.id}>{cell.value}</TableCell>;
    } else if (
      cell.info.header === "patientEmail" ||
      cell.info.header === "patientSMS" ||
      cell.info.header === "providerEmail" ||
      cell.info.header === "providerSMS"
    ) {
      return (
        <TableCell key={cell.id}>
          <Checkbox id={cell.id} labelText="" checked={cell.value === "true"} />
        </TableCell>
      );
    } else if (cell.info.header === "edit") {
      return (
        <TableCell key={cell.id}>
          <Button
            hasIconOnly
            iconDescription="Edit Test Notification"
            onClick={() => handleEditButtonClick(row.cells[0].value)}
            renderIcon={Settings}
            kind="tertiary"
          />
        </TableCell>
      );
    } else {
      return <TableCell key={cell.id}>{cell.value}</TableCell>;
    }
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
      <div className="adminPageContent">
        <PageBreadCrumb breadcrumbs={breadcrumbs} />
        <Grid fullWidth={true}>
          <Column lg={16} md={8} sm={4}>
            <Section>
              <Section>
                <Heading>
                  <FormattedMessage id="testnotificationconfig.browse.title" />
                </Heading>
              </Section>
            </Section>
          </Column>
        </Grid>
        <div className="orderLegendBody">
          <Grid fullWidth={true}>
            <Column lg={16} md={8} sm={4}>
              <br />
              <DataTable
                // rows={testNamesList.slice(
                //   (page - 1) * pageSize,
                //   page * pageSize,
                // )}
                rows={testNotificationConfigMenuDataMenuList.map((item) => ({
                  id: item.id,
                  testId: item.testId,
                  patientEmail: item.patientEmail.active ? "true" : "false",
                  patientSMS: item.patientSMS.active ? "true" : "false",
                  providerEmail: item.providerEmail.active ? "true" : "false",
                  providerSMS: item.providerSMS.active ? "true" : "false",
                  testName: testNamesMap[item.testId] || item.testId,
                }))}
                headers={[
                  {
                    key: "testId",
                    header: intl.formatMessage({
                      id: "column.name.testId",
                    }),
                  },
                  {
                    key: "testName",
                    header: intl.formatMessage({
                      id: "label.testName",
                    }),
                  },
                  {
                    key: "patientEmail",
                    header: intl.formatMessage({
                      id: "testnotification.patient.email",
                    }),
                  },
                  {
                    key: "patientSMS",
                    header: intl.formatMessage({
                      id: "testnotification.patient.sms",
                    }),
                  },
                  {
                    key: "providerEmail",
                    header: intl.formatMessage({
                      id: "testnotification.provider.email",
                    }),
                  },
                  {
                    key: "providerSMS",
                    header: intl.formatMessage({
                      id: "testnotification.provider.sms",
                    }),
                  },
                  {
                    key: "edit",
                    header: intl.formatMessage({
                      id: "banner.menu.patientEdit",
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
                          {/* <TableSelectAll
                            id="table-select-all"
                            {...getSelectionProps()}
                            checked={
                              selectedRowIds.length === pageSize &&
                              searchedUserManagementListShow
                                .slice((page - 1) * pageSize, page * pageSize)
                                .filter(
                                  (row) =>
                                    !row.disabled &&
                                    selectedRowIds.includes(row.id),
                                ).length === pageSize
                            }
                            indeterminate={
                              selectedRowIds.length > 0 &&
                              selectedRowIds.length <
                                searchedUserManagementListShow
                                  .slice((page - 1) * pageSize, page * pageSize)
                                  .filter((row) => !row.disabled).length
                            }
                            onSelect={() => {
                              setDeactivateButton(false);
                              const currentPageIds =
                                searchedUserManagementListShow
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
                          /> */}
                          {headers.map((header) => (
                            // header.key !== "id" &&
                            <TableHeader
                              key={header.key}
                              {...getHeaderProps({ header })}
                            >
                              {header.header}
                            </TableHeader>
                          ))}
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        <>
                          {rows.map((row) => (
                            <TableRow
                              key={row.id}
                              // onClick={() => {
                              //   const id = row.id;
                              //   const CombinedUserID = row.combinedUserID;
                              //   const isSelected = selectedRowIds.includes(id);
                              //   if (isSelected) {
                              //     setSelectedRowIds(
                              //       selectedRowIds.filter(
                              //         (selectedId) => selectedId !== id,
                              //       ),
                              //     );
                              //   } else {
                              //     setSelectedRowIds([...selectedRowIds, id]);
                              //   }
                              // }}
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
              {/* <Pagination
                onChange={handlePageChange}
                page={page}
                pageSize={pageSize}
                pageSizes={[5, 10, 15, 20]}
                totalItems={testNamesList.length}
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
              /> */}
              <br />
            </Column>
          </Grid>
          <button
            onClick={() => {
              console.log(testNotificationConfigMenuData);
            }}
          >
            testNotificationConfigMenuData
          </button>
          <button
            onClick={() => {
              console.log(testNotificationConfigMenuDataPost);
            }}
          >
            testNotificationConfigMenuDataPost
          </button>
          <button
            onClick={() => {
              console.log(testNotificationConfigMenuDataMenuList);
            }}
          >
            testNotificationConfigMenuDataMenuList
          </button>
          <button
            onClick={() => {
              console.log(testNamesList);
            }}
          >
            testNamesList
          </button>
        </div>
      </div>
    </>
  );
}

export default injectIntl(TestNotificationConfigMenu);
