import React, { useContext, useEffect, useRef, useState } from "react";
import "../../Style.css";
import {
  Button,
  Column,
  DataTable,
  Dropdown,
  Form,
  Grid,
  Heading,
  Link,
  Modal,
  Pagination,
  Section,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableHeader,
  TableRow,
  TableSelectRow,
  TableToolbar,
  TableToolbarContent,
  TableToolbarSearch,
  TextInput,
} from "@carbon/react";
import { FormattedMessage, useIntl } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb";
import {
  getFromOpenElisServer,
  postToOpenElisServer,
  postToOpenElisServerFullResponse,
} from "../../utils/Utils";
import { ConfigurationContext, NotificationContext } from "../../layout/Layout";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification";
import { ArrowLeft, ArrowRight } from "@carbon/icons-react";

function DictionaryManagement() {
  const intl = useIntl();
  const componentMounted = useRef(false);

  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);
  const { reloadConfiguration } = useContext(ConfigurationContext);
  const [dictionaryMenuz, setDictionaryMenuz] = useState([]);
  const [dictionaryMenuList, setDictionaryMenuList] = useState([]);

  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);
  const [open, setOpen] = useState(false);

  const [categoryDescription, setCategoryDescription] = useState([]);

  const [category, setCategory] = useState("");
  const [dictionaryNumber, setDictionaryNumber] = useState("");
  const [dictionaryEntry, setDictionaryEntry] = useState("");
  const [localAbbreviation, setLocalAbbreviation] = useState("");
  const [isActive, setIsActive] = useState("");
  const [lastupdated, setLastUpdated] = useState("");

  const [fromRecordCount, setFromRecordCount] = useState("");
  const [toRecordCount, setToRecordCount] = useState("");
  const [totalRecordCount, setTotalRecordCount] = useState("");

  const [selectedRowId, setSelectedRowId] = useState(null);
  const [modifyButton, setModifyButton] = useState(true);
  const [editMode, setEditMode] = useState(true);

  const [paging, setPaging] = useState(2);
  const [startingRecNo, setStartingRecNo] = useState(1);

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer(
      `/rest/dictionary-menu?paging=${paging}&startingRecNo=${startingRecNo}`,
      fetchedDictionaryMenu,
    );
    return () => {
      componentMounted.current = false;
    };
  }, [paging, startingRecNo]);

  const handleNextPage = () => {
    setPaging((pager) => Math.max(pager, 2));
    setStartingRecNo(toRecordCount);
  };

  const handlePreviousPage = () => {
    setPaging((pager) => Math.max(pager - 1, 1));
    setStartingRecNo(Math.max(toRecordCount, 1));
  };

  const yesOrNo = [
    {
      id: "Y",
      value: "Y",
    },
    {
      id: "N",
      value: "N",
    },
  ];

  const handlePageChange = (pageInfo) => {
    if (page != pageInfo.page) {
      setPage(pageInfo.page);
    }

    if (pageSize != pageInfo.pageSize) {
      setPageSize(pageInfo.pageSize);
    }
  };

  const fetchedDictionaryMenu = (res) => {
    if (componentMounted.current) {
      if (res) {
        if (
          res.toRecordCount !== undefined &&
          res.fromRecordCount !== undefined &&
          res.totalRecordCount !== undefined
        ) {
          setToRecordCount(res.fromRecordCount);
          setFromRecordCount(res.toRecordCount);
          setTotalRecordCount(res.totalRecordCount);
        }
        if (res.menuList) {
          const menuList = res.menuList.map((item) => ({
            id: item.id,
            dictEntry: item.dictEntry,
            localAbbreviation: item.localAbbreviation,
            isActive: item.isActive,
            categoryName: item.dictionaryCategory
              ? item.dictionaryCategory.categoryName
              : "not available",
            lastupdated: item.lastupdated,
          }));
          console.log("new menu list: " + JSON.stringify(menuList));
          setDictionaryMenuList(menuList);
        }
      }
    }
  };

  const fetchedDictionaryCategory = (category) => {
    if (componentMounted.current) {
      setCategoryDescription(category);
    }
  };

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/dictionary-menu", fetchedDictionaryMenu);
    return () => {
      componentMounted.current = false;
    };
  }, []);

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer(
      "/rest/dictionary-categories",
      fetchedDictionaryCategory,
    );
    return () => {
      componentMounted.current = false;
    };
  }, []);

  const postData = {
    id: dictionaryNumber,
    selectedDictionaryCategoryId: category.id,
    dictEntry: dictionaryEntry,
    localAbbreviation: localAbbreviation,
    isActive: isActive.id,
  };

  async function displayStatus(res) {
    setNotificationVisible(true);
    if (res.status == "201" || res.status == "200") {
      addNotification({
        kind: NotificationKinds.success,
        title: intl.formatMessage({ id: "notification.title" }),
        message: intl.formatMessage({ id: "success.add.edited.msg" }),
      });
    } else {
      addNotification({
        kind: NotificationKinds.error,
        title: intl.formatMessage({ id: "notification.title" }),
        message: intl.formatMessage({ id: "error.add.edited.msg" }),
      });
    }
    reloadConfiguration();
  }

  const handleSubmitModal = (e) => {
    e.preventDefault();
    postToOpenElisServerFullResponse(
      "/rest/dictionary",
      JSON.stringify(postData),
      displayStatus,
    );
    setOpen(false);
  };

  const handleUpdateModal = (e) => {
    e.preventDefault();

    const updateData = {
      id: dictionaryNumber,
      selectedDictionaryCategoryId: category.id,
      dictEntry: dictionaryEntry,
      localAbbreviation: localAbbreviation,
      isActive: isActive.id,
      lastupdated: lastupdated,
    };

    console.log("Updating with data: " + JSON.stringify(updateData));

    console.log("Updating with data:", {
      dictionaryNumber,
      category,
      dictionaryEntry,
      localAbbreviation,
      isActive,
      lastupdated,
    });

    postToOpenElisServerFullResponse(
      `/rest/Dictionary?ID=${selectedRowId}&startingRecNo=${startingRecNo}`,
      JSON.stringify(updateData),
      console.log("postdata:" + JSON.stringify(updateData)),
      displayStatus,
    );
    setOpen(false);
  };

  const renderCell = (cell, row) => {
    if (cell.info.header === "select") {
      return (
        <TableSelectRow
          key={cell.id}
          id={cell.id}
          radio={true}
          checked={selectedRowId === row.id}
          name="selectRowRadio"
          ariaLabel="selectRow"
          onSelect={() => {
            setModifyButton(false);
            setSelectedRowId(row.id);
          }}
        />
      );
    } else if (
      cell.info.header === "value" &&
      typeof cell.value === "string" &&
      cell.value.startsWith("data:image")
    ) {
      return (
        <TableCell key={cell.id}>
          <img
            src={cell.value}
            alt="Config Image"
            style={{ maxWidth: "50px" }}
          />
        </TableCell>
      );
    }
    return <TableCell key={cell.id}>{cell.value}</TableCell>;
  };

  const handleDictionaryMenuItems = (res) => {
    if (componentMounted.current) {
      setDictionaryNumber(res.id);
      setCategory(res.dictionaryCategory);
      setDictionaryEntry(res.dictEntry);
      setIsActive(yesOrNo.find((item) => item.id === res.isActive));
      setLocalAbbreviation(res.localAbbreviation);
      setLastUpdated(res.lastupdated);
    }
  };

  const handleOnClickOnModification = async (event) => {
    event.preventDefault();
    if (selectedRowId) {
      getFromOpenElisServer(
        `/rest/Dictionary?ID=${selectedRowId}&startingRecNo=${startingRecNo}`,
        handleDictionaryMenuItems,
      );

      const selectedItem = dictionaryMenuList.find(
        (item) => item.id === selectedRowId,
      );
      if (selectedItem) {
        setDictionaryNumber(selectedItem.id);
        setCategory(selectedItem.categoryName);
        setDictionaryEntry(selectedItem.dictEntry);
        setLocalAbbreviation(selectedItem.localAbbreviation);
        setIsActive(yesOrNo.find((item) => item.id === selectedItem.isActive));
        setLastUpdated(selectedItem.lastupdated); // Set the lastupdated state
        setOpen(true);
        setEditMode(false);
      }
      setOpen(true);
      setEditMode(false);
    }
  };

  const handleDeactivation = async (event) => {
    event.preventDefault();
    const list = [...dictionaryMenuList];
    list.splice(selectedRowId, 1);
    setDictionaryMenuList(list);
    if (selectedRowId) {
      postToOpenElisServer(
        `/rest/delete-dictionary?selectedIDs=${selectedRowId}`,
        {},
        handleDelete,
      );
    }
    reloadConfiguration();
  };

  const handleDelete = (status) => {
    setNotificationVisible(true);
    if (status == "200") {
      addNotification({
        kind: NotificationKinds.success,
        title: intl.formatMessage({ id: "notification.title" }),
        message: intl.formatMessage({
          id: "dictionary.menu.deactivate.success",
        }),
      });
    } else {
      addNotification({
        kind: NotificationKinds.error,
        title: intl.formatMessage({ id: "notification.title" }),
        message: intl.formatMessage({ id: "dictionary.menu.deactivate.fail" }),
      });
    }
  };

  return (
    <div className="adminPageContent">
      {notificationVisible === true ? <AlertDialog /> : ""}
      <PageBreadCrumb
        breadcrumbs={[
          { label: "home.label", link: "/" },
          { label: "dictionary.label.modify", link: "/DictionaryManagement" },
        ]}
      />
      <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Heading>
              <FormattedMessage id="dictionary.label.modify" />
            </Heading>
          </Section>
          <br />
          <Section>
            <Form
              style={{
                display: "flex",
                justifyContent: "space-between",
                alignItems: "center",
              }}
            >
              <Column
                lg={16}
                md={8}
                sm={4}
                style={{ display: "flex", gap: "10px" }}
              >
                <Button disabled={!editMode} onClick={() => setOpen(true)}>
                  {intl.formatMessage({
                    id: "admin.page.configuration.formEntryConfigMenu.button.add",
                  })}
                </Button>{" "}
                <Button
                  disabled={modifyButton}
                  type="submit"
                  onClick={handleOnClickOnModification}
                >
                  <FormattedMessage id="admin.page.configuration.formEntryConfigMenu.button.modify" />
                </Button>{" "}
                <Modal
                  open={open}
                  size="sm"
                  onRequestClose={() => setOpen(false)}
                  modalHeading={editMode ? "Add Dictionary" : "Edit Dictionary"}
                  primaryButtonText={editMode ? "Add" : "Update"}
                  secondaryButtonText="Cancel"
                  onRequestSubmit={
                    editMode ? handleSubmitModal : handleUpdateModal
                  }
                >
                  <TextInput
                    data-modal-primary-focus
                    id="dictNumber"
                    labelText="Dictionary Number"
                    disabled
                    onChange={(e) => setDictionaryNumber(e.target.value)}
                    style={{
                      marginBottom: "1rem",
                    }}
                  />
                  <Dropdown
                    id="description"
                    label=""
                    type="default"
                    items={categoryDescription}
                    titleText="Dictionary Category"
                    itemToString={(item) => (item ? item.description : "")}
                    onChange={({ selectedItem }) => {
                      setCategory(selectedItem);
                    }}
                    selectedItem={category}
                    size="md"
                    style={{
                      marginBottom: "1rem",
                    }}
                  />
                  <TextInput
                    id="dictEntry"
                    labelText="Dictionary Entry"
                    value={dictionaryEntry}
                    onChange={(e) => setDictionaryEntry(e.target.value)}
                    style={{
                      marginBottom: "1rem",
                    }}
                  />
                  <Dropdown
                    id="isActive"
                    type="default"
                    label=""
                    items={yesOrNo}
                    titleText="Is Active"
                    itemToString={(item) => (item ? item.id : "")}
                    onChange={({ selectedItem }) => {
                      setIsActive(selectedItem);
                    }}
                    selectedItem={isActive}
                    size="md"
                    style={{
                      marginBottom: "1rem",
                    }}
                  />
                  <TextInput
                    id="localAbbrev"
                    labelText="Local Abbreviation"
                    value={localAbbreviation}
                    onChange={(e) => setLocalAbbreviation(e.target.value)}
                    style={{
                      marginBottom: "1rem",
                    }}
                  />
                </Modal>
                <Button
                  disabled={modifyButton}
                  onClick={handleDeactivation}
                  type="submit"
                >
                  <FormattedMessage id="admin.page.configuration.formEntryConfigMenu.button.deactivate" />
                </Button>
              </Column>
              <Column
                lg={16}
                md={8}
                sm={4}
                style={{
                  display: "flex",
                  flexDirection: "column",
                  alignItems: "center",
                  gap: "10px",
                }}
              >
                <Link>
                  Showing {toRecordCount} - {fromRecordCount} of{" "}
                  {totalRecordCount}
                </Link>
                <div style={{ display: "flex", gap: "10px" }}>
                  <Button
                    hasIconOnly
                    disabled={
                      (paging === 1 && startingRecNo <= 21) ||
                      startingRecNo <= 1
                    }
                    onClick={handlePreviousPage}
                    renderIcon={ArrowLeft}
                  />
                  <Button
                    hasIconOnly
                    renderIcon={ArrowRight}
                    onClick={handleNextPage}
                  />
                </div>
              </Column>
            </Form>
          </Section>
        </Column>
      </Grid>
      <div className="orderLegendBody">
        <Grid fullWidth={true} className="gridBoundary">
          <Column lg={16} md={8} sm={4}>
            <DataTable
              size="sm"
              rows={dictionaryMenuList.slice(
                (page - 1) * pageSize,
                page * pageSize,
              )}
              headers={[
                {
                  key: "select",
                  header: intl.formatMessage({
                    id: "admin.page.configuration.formEntryConfigMenu.select",
                  }),
                },
                {
                  key: "categoryName",
                  header: intl.formatMessage({
                    id: "dictionary.category.name",
                  }),
                },
                {
                  key: "dictEntry",
                  header: intl.formatMessage({ id: "dictionary.dictEntry" }),
                },
                {
                  key: "localAbbreviation",
                  header: intl.formatMessage({
                    id: "dictionary.category.localAbbreviation",
                  }),
                },
                {
                  key: "isActive",
                  header: intl.formatMessage({
                    id: "dictionary.category.isActive",
                  }),
                },
              ]}
              isSortable
            >
              {({
                rows,
                headers,
                getHeaderProps,
                getTableProps,
                getToolbarProps,
                getBatchActionProps,
                onInputChange,
                selectRow,
              }) => {
                const batchActionProps = {
                  ...getBatchActionProps({
                    onSelectAll: () => {
                      rows.map((row) => {
                        if (!row.isSelected) {
                          selectRow(row.id);
                        }
                      });
                    },
                  }),
                };

                return (
                  <TableContainer title="" description="">
                    <TableToolbar {...getToolbarProps()}>
                      <TableToolbarContent
                        aria-hidden={batchActionProps.shouldShowBatchActions}
                      >
                        <TableToolbarSearch
                          tabIndex={
                            batchActionProps.shouldShowBatchActions ? -1 : 0
                          }
                          onChange={onInputChange}
                        />
                      </TableToolbarContent>
                    </TableToolbar>
                    <Table {...getTableProps()}>
                      <TableHead>
                        <TableRow>
                          {headers.map((header) => (
                            <TableHeader
                              key={header.key}
                              {...getHeaderProps({ header })}
                            >
                              {header.header}
                            </TableHeader>
                          ))}
                          <TableHeader />
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {rows.map((row) => (
                          <TableRow
                            key={row.id}
                            onClick={() => {
                              setSelectedRowId(row.id);
                            }}
                          >
                            {row.cells.map((cell) => renderCell(cell, row))}
                          </TableRow>
                        ))}
                      </TableBody>
                    </Table>
                  </TableContainer>
                );
              }}
            </DataTable>
            <Pagination
              onChange={handlePageChange}
              page={page}
              pageSize={pageSize}
              pageSizes={[10, 20]}
              totalItems={dictionaryMenuList.length}
              forwardText={intl.formatMessage({ id: "pagination.forward" })}
              backwardText={intl.formatMessage({ id: "pagination.backward" })}
              size="sm"
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
  );
}

export default DictionaryManagement;
