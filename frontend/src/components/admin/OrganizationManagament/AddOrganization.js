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
  TextInput,
} from "@carbon/react";
import {
  getFromOpenElisServer,
  postToOpenElisServerFullResponse,
  postToOpenElisServerJsonResponse,
} from "../../utils/Utils.js";
import { NotificationContext } from "../../layout/Layout.js";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification.js";
import { Field, Formik } from "formik";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb.js";

let breadcrumbs = [
  { label: "home.label", link: "/" },
  { label: "breadcrums.admin.managment", link: "/MasterListsPage" },
];

function AddOrganization() {
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  const componentMounted = useRef(false);
  const intl = useIntl();
  const [loading, setLoading] = useState(true);
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(20);
  const [selectedRowIds, setSelectedRowIds] = useState([]);
  const [saveButton, setSaveButton] = useState(true);
  const [newOrganizationsData, setNewOrganizationsData] = useState(null);
  const [typeOfActivity, setTypeOfActivity] = useState(null);
  const [typeOfActivityShow, setTypeOfActivityShow] = useState([]);

  // const handlePageChange = ({ page, pageSize }) => {
  //   setPage(page);
  //   setPageSize(pageSize);
  //   setSelectedRowIds([]);
  // };

  function handleOrgNameChange(e) {
    setSaveButton(false);
    setNewOrganizationsData((alreadyOrgInfoToPost) => ({
      ...alreadyOrgInfoToPost,
      organizationName: e.target.value,
    }));
  }

  function handleOrgPrefixChange(e) {
    setSaveButton(false);
    setNewOrganizationsData((alreadyOrgInfoToPost) => ({
      ...alreadyOrgInfoToPost,
      shortName: e.target.value,
    }));
  }

  function handleIsActiveChange(e) {
    setSaveButton(false);
    setNewOrganizationsData((alreadyOrgInfoToPost) => ({
      ...alreadyOrgInfoToPost,
      isActive: e.target.value,
    }));
  }

  function handleInternetAddressChange(e) {
    setSaveButton(false);
    setNewOrganizationsData((alreadyOrgInfoToPost) => ({
      ...alreadyOrgInfoToPost,
      internetAddress: e.target.value,
    }));
  }

  useEffect(() => {
    setNewOrganizationsData((alreadyOrgInfoToPost) => ({
      ...alreadyOrgInfoToPost,
      selectedTypes: selectedRowIds,
    }));
  }, [selectedRowIds, typeOfActivity, typeOfActivityShow]);

  useEffect(() => {
    if (typeOfActivity) {
      const newOrganizationsManagementList = typeOfActivity.orgTypes.map(
        (item) => {
          return {
            id: item.id,
            name: item.name || "",
            description: item.description || "",
          };
        },
      );
      const newOrganizationsManagementListArray = Object.values(
        newOrganizationsManagementList,
      );
      setTypeOfActivityShow(newOrganizationsManagementListArray);

      const newOrganizationsManagementDataPost = {
        id: typeOfActivity.id,
        organizationName: typeOfActivity.organizationName || "",
        shortName: typeOfActivity.shortName || "",
        isActive: typeOfActivity.isActive || "",
        internetAddress: typeOfActivity.internetAddress || "",
      };
      setNewOrganizationsData(newOrganizationsManagementDataPost);
    }
  }, [typeOfActivity]);

  const handleMenuItems = (res) => {
    if (!res) {
      setLoading(true);
    } else {
      setTypeOfActivity(res);
    }
  };

  useEffect(() => {
    componentMounted.current = true;
    setLoading(true);
    getFromOpenElisServer(
      `/rest/Organization?ID=0&startingRecNo=1`,
      handleMenuItems,
    );
    return () => {
      componentMounted.current = false;
      setLoading(false);
    };
  }, []);

  function newOrganizationsSavePost(event) {
    event.preventDefault();
    setLoading(true);
    postToOpenElisServerJsonResponse(
      `/rest/Organization?ID=0&startingRecNo=1`,
      JSON.stringify(newOrganizationsData),
      newOrganizationsSavePostCallback(),
    );
  }

  function newOrganizationsSavePostCallback() {
    setLoading(false);
    setNotificationVisible(true);
    addNotification({
      title: intl.formatMessage({
        id: "notification.title",
      }),
      message: intl.formatMessage({
        id: "notification.organization.post.save.success",
      }),
      kind: NotificationKinds.success,
    });
    setTimeout(() => {
      window.location.reload();
    }, 2000);
  }

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
            setSaveButton(false);
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
        <Grid fullWidth={true}>
          <Column lg={16} md={8} sm={4}>
            <Section>
              <Heading>
                <FormattedMessage id="organization.add.title" />
              </Heading>
            </Section>
          </Column>
        </Grid>
        <br />
        <div className="orderLegendBody">
          <Grid fullWidth={true} className="gridBoundary">
            <Column lg={16} md={8} sm={4}>
              <Form
              // onSubmit={handleSubmit}
              // onChange={setSaveButton(false)}
              // onBlur={handleBlur}
              >
                <Grid fullWidth={true}>
                  <Column lg={8} md={4} sm={4}>
                    <>
                      <FormattedMessage id="organization.organizationName" />
                      <span className="requiredlabel">*</span> :
                    </>
                  </Column>
                  <Column lg={8} md={4} sm={4}>
                    <TextInput
                      id="org-name"
                      className="defalut"
                      type="text"
                      labelText=""
                      placeholder={intl.formatMessage({
                        id: "organization.add.placeholder",
                      })}
                      required
                      onChange={(e) => handleOrgNameChange(e)}
                    />
                  </Column>
                </Grid>
                <Grid fullWidth={true}>
                  <Column lg={8} md={4} sm={4}>
                    <>
                      <FormattedMessage id="organization.short.CI" /> :
                    </>
                  </Column>
                  <Column lg={8} md={4} sm={4}>
                    <TextInput
                      id="org-prefix"
                      className="defalut"
                      type="text"
                      labelText=""
                      placeholder={intl.formatMessage({
                        id: "organization.add.placeholder",
                      })}
                      onChange={(e) => handleOrgPrefixChange(e)}
                    />
                  </Column>
                </Grid>
                <Grid fullWidth={true}>
                  <Column lg={8} md={4} sm={4}>
                    <>
                      <FormattedMessage id="organization.isActive" />
                      <span className="requiredlabel">*</span> :
                    </>
                  </Column>
                  <Column lg={8} md={4} sm={4}>
                    <TextInput
                      id="is-active"
                      className="defalut"
                      type="text"
                      labelText=""
                      placeholder={intl.formatMessage({
                        id: "organization.add.placeholder.active",
                      })}
                      required
                      onChange={(e) => handleIsActiveChange(e)}
                    />
                  </Column>
                </Grid>
                <Grid fullWidth={true}>
                  <Column lg={8} md={4} sm={4}>
                    <>
                      <FormattedMessage id="organization.internetaddress" /> :
                    </>
                  </Column>

                  <Column lg={8} md={4} sm={4}>
                    <TextInput
                      id="internet-address"
                      className="defalut"
                      type="text"
                      labelText=""
                      placeholder={intl.formatMessage({
                        id: "organization.add.placeholder",
                      })}
                      onChange={(e) => handleInternetAddressChange(e)}
                    />
                  </Column>
                </Grid>
              </Form>
            </Column>
          </Grid>
          <br />
          <Grid fullWidth={true}>
            <Column lg={16} md={8} sm={4}>
              <Section>
                <Section>
                  <Section>
                    <Heading>
                      <>
                        <FormattedMessage id="organization.type.CI" />
                        <span className="requiredlabel">*</span>
                      </>
                    </Heading>
                  </Section>
                </Section>
              </Section>
            </Column>
          </Grid>
          <br />
          <Grid fullWidth={true} className="gridBoundary">
            <Column lg={16} md={8} sm={4}>
              <br />
              <DataTable
                rows={typeOfActivityShow.slice(
                  // need a change
                  (page - 1) * pageSize,
                  page * pageSize,
                )}
                headers={[
                  {
                    key: "select",
                    header: intl.formatMessage({
                      id: "organization.type.CI.select",
                    }),
                  },
                  {
                    key: "name",
                    header: intl.formatMessage({
                      id: "organization.type.CI.name",
                    }),
                  },

                  {
                    key: "description",
                    header: intl.formatMessage({
                      id: "organization.type.CI.description",
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
                              typeOfActivityShow
                                .slice((page - 1) * pageSize, page * pageSize)
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
                                typeOfActivityShow
                                  .slice((page - 1) * pageSize, page * pageSize)
                                  .filter((row) => !row.disabled).length
                            }
                            // indeterminate={rowsIsPartiallyChecked}
                            onSelect={() => {
                              setSaveButton(false);
                              const currentPageIds = typeOfActivityShow // need a change
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
              {/* <Pagination
                onChange={handlePageChange}
                page={page}
                pageSize={pageSize}
                pageSizes={[5, 10, 20]}
                totalItems={typeOfActivityShow.length}
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
          <br />
          <Grid fullWidth={true}>
            <Column lg={16} md={8} sm={4}>
              <Button
                onClick={newOrganizationsSavePost}
                disabled={saveButton}
                type="submit"
              >
                Save
              </Button>{" "}
              <Button
                onClick={() => window.location.assign("/MasterListsPage")}
                kind="tertiary"
                type="button"
              >
                Exit
              </Button>
            </Column>
          </Grid>
        </div>
      </div>
      <div>
        <button
          onClick={() => {
            console.error(selectedRowIds);
          }}
        >
          sdf
        </button>
        <button
          onClick={() => {
            console.error(newOrganizationsData);
          }}
        >
          postData
        </button>
      </div>
    </>
  );
}

export default injectIntl(AddOrganization);
