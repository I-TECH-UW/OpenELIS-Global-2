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
  TextInput,
} from "@carbon/react";
import { FormattedMessage, useIntl } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb";
import {
  getFromOpenElisServer,
  postToOpenElisServerFullResponse,
} from "../../utils/Utils";
import { ConfigurationContext, NotificationContext } from "../../layout/Layout";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification";

function DictionaryManagement() {
  const intl = useIntl();
  const componentMounted = useRef(false);

  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);
  const { reloadConfiguration } = useContext(ConfigurationContext);
  const [dictionaryMenuz, setDictionaryMenuz] = useState([]);
  const [dictionaryMenuList, setDictionaryMenuList] = useState([]);

  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(7);
  const [open, setOpen] = useState(false);

  const [categoryDescription, setCategoryDescription] = useState([]);

  const [category, setCategory] = useState("");
  const [dictionaryNumber, setDictionaryNumber] = useState("");
  const [dictionaryEntry, setDictionaryEntry] = useState("");
  const [localAbbreviation, setLocalAbbreviation] = useState("");
  const [isActive, setIsActive] = useState("");

  const [selectedRowId, setSelectedRowId] = useState(null);
  const [modifyButton, setModifyButton] = useState(true);
  const [editMode, setEditMode] = useState(true);

  const [dictionaryItem, setDictionaryItem] = useState({
    id: null,
    dictEntry: "",
    category: "",
    isActive: "",
    localAbbreviation: "",
  });

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
      setDictionaryMenuz(res);
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
    if (dictionaryMenuz && dictionaryMenuz.menuList) {
      const newMenuList = dictionaryMenuz.menuList.map((item) => {
        let value = item.value;
        if (item.valueType === "text" && item.tag === "localization") {
          value =
            item.localization.localesAndValuesOfLocalesWithValues || value;
        }
        return {
          id: item.id,
          isActive: item.isActive,
          dictEntry: item.dictEntry,
          localAbbreviation: localAbbreviation,
          categoryName: item.dictionaryCategory
            ? item.dictionaryCategory.categoryName
            : "not available",
          value: value,
        };
      });
      setDictionaryMenuList(newMenuList);
    }
  }, [dictionaryMenuz]);

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
    selectedDictionaryCategoryId: category,
    dictEntry: dictionaryEntry,
    localAbbreviation: localAbbreviation,
    isActive: isActive,
  };

  async function displayStatus(res) {
    setNotificationVisible(true);
    if (res.status == "201") {
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
    console.log(JSON.stringify(postData));
    postToOpenElisServerFullResponse(
      "/rest/dictionary",
      JSON.stringify(postData),
      displayStatus,
    );
    setOpen(false);
  };

  const renderCell = (cell, row) => {
    if (cell.info.header === "select") {
      return (
        <TableSelectRow
          radio
          key={cell.id}
          id={cell.id}
          checked={selectedRowId === row.id}
          name="selectRowRadio"
          ariaLabel="selectRow"
          onSelect={() => {
            setModifyButton(false);
            setSelectedRowId(row.id);
            console.log("row id " + row.id);
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
      console.log("res: " + res.dictionaryCategory.description);
      setDictionaryItem({
        id: res.id,
        category: res.dictionaryCategory.description,
        dictEntry: res.dictEntry,
        isActive: res.isActive,
        localAbbreviation: res.localAbbreviation,
      });
    }
  };

  const handleOnClickOnModification = async (event) => {
    event.preventDefault();
    if (selectedRowId) {
      getFromOpenElisServer(
        `/rest/Dictionary?ID=${selectedRowId}`,
        handleDictionaryMenuItems,
      );
      setOpen(true);
      setEditMode(false);
    }
  };

  const handleEditModalSubmission = async (e) => {
    e.preventDefault();
    if (dictionaryItem) {
      postData.id = dictionaryItem.id;
      postData.selectedDictionaryCategoryId =
        dictionaryItem.category.description;
      postData.dictEntry = dictionaryItem.dictEntry;
      postData.localAbbreviation = dictionaryItem.localAbbreviation;
      postData.isActive = dictionaryItem.isActive;

      const res = postToOpenElisServerFullResponse(
        `/rest/dictionary`,
        JSON.stringify(postData),
        displayStatus,
      );

      if (res.status === "200") {
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
      setOpen(false);
    }
  };

  return (
    <div className="adminPageContent">
      {notificationVisible === true ? <AlertDialog /> : ""}
      <PageBreadCrumb
        breadcrumbs={[
          { label: "home.label", link: "/" },
          { label: "dictionary.label.modify", link: "/DictionaryMenu" },
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
            <Form>
              <Column lg={16} md={8} sm={4}>
                <Button onClick={() => setOpen(true)}>
                  {intl.formatMessage({
                    id: "admin.page.configuration.formEntryConfigMenu.button.add",
                  })}
                </Button>{" "}
                <Button
                  disabled={modifyButton}
                  onClick={handleOnClickOnModification}
                  type="submit"
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
                    editMode ? handleSubmitModal : handleEditModalSubmission
                  }
                >
                  <TextInput
                    data-modal-primary-focus
                    id="dictNumber"
                    labelText="Dictionary Number"
                    value={dictionaryItem?.id}
                    disabled
                    onChange={(e) => setDictionaryNumber(e.target.value)}
                    style={{
                      marginBottom: "1rem",
                    }}
                  />
                  <p>testing testing</p>
                  <p>{dictionaryItem.category}</p>
                  <Dropdown
                    id="description"
                    items={categoryDescription}
                    titleText="Dictionary Category"
                    itemToString={(item) => (item ? item.description : "")}
                  />
                  <TextInput
                    id="dictEntry"
                    labelText="Dictionary Entry"
                    value={dictionaryItem?.dictEntry}
                    onChange={(e) => setDictionaryEntry(e.target.value)}
                    style={{
                      marginBottom: "1rem",
                    }}
                  />
                  <TextInput
                    data-modal-primary-focus
                    id="isActive"
                    labelText="Is Active"
                    value={dictionaryItem?.isActive}
                    onChange={(e) => setIsActive(e.target.value)}
                    style={{
                      marginBottom: "1rem",
                    }}
                  />
                  <TextInput
                    id="localAbbrev"
                    labelText="Local Abbreviation"
                    value={dictionaryItem?.localAbbreviation}
                    onChange={(e) => setLocalAbbreviation(e.target.value)}
                    style={{
                      marginBottom: "1rem",
                    }}
                  />
                </Modal>
                <Button kind="tertiary" disabled={true} type="submit">
                  <FormattedMessage id="admin.page.configuration.formEntryConfigMenu.button.deactivate" />
                </Button>
              </Column>
            </Form>
          </Section>
        </Column>
      </Grid>
      <div className="orderLegendBody">
        <Grid fullWidth={true} className="gridBoundary">
          <Column lg={16} md={8} sm={4}>
            <DataTable
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
                getRowProps,
                onInputChange,
              }) => (
                <TableContainer title="" description="">
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
              )}
            </DataTable>
            <Pagination
              onChange={handlePageChange}
              page={page}
              pageSize={pageSize}
              pageSizes={[7, 10, 20, 30, 40, 50]}
              totalItems={dictionaryMenuList.length}
              forwardText={intl.formatMessage({ id: "pagination.forward" })}
              backwardText={intl.formatMessage({ id: "pagination.backward" })}
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
