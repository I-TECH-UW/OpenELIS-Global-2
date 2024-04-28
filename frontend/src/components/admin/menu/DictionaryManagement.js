import React, { useContext, useEffect, useRef, useState } from "react";
import "../../Style.css";
import {
  Button,
  Column,
  DataTable,
  Grid,
  Heading,
  Modal,
  OverflowMenu,
  OverflowMenuItem,
  Pagination,
  Section,
  Select,
  SelectItem,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableHeader,
  TableRow,
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

  const {
    notificationVisible,
    setNotificationVisible,
    addNotification,
  } = useContext(NotificationContext);
  const { reloadConfiguration } = useContext(ConfigurationContext);
  const [dictionaryMenuz, setDictionaryMenuz] = useState([]);

  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);
  const [open, setOpen] = useState(false);

  const [categoryDescription, setCategoryDescription] = useState([]);

  const [category, setCategory] = useState("");
  const [dictionaryNumber, setDictionaryNumber] = useState("");
  const [dictionaryEntry, setDictionaryEntry] = useState("");
  const [localAbbreviation, setLocalAbbreviation] = useState("");
  const [isActive, setIsActive] = useState("");

  const handlePageChange = (pageInfo) => {
    if (page != pageInfo.page) {
      setPage(pageInfo.page);
    }

    if (pageSize != pageInfo.pageSize) {
      setPageSize(pageInfo.pageSize);
    }
  };

  const fetchedDictionaryMenu = (dictionaryMenus) => {
    if (componentMounted.current) {
      setDictionaryMenuz(dictionaryMenus);
    }
  };

  const fetchedDictionaryCategory = (category) => {
    if (componentMounted.current) {
      console.log("Fetched Dictionary Category Data:", category);
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
      "/rest/dictionary-categories/descriptions",
      fetchedDictionaryCategory
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
      "/rest/create-dictionary",
      JSON.stringify(postData),
      displayStatus
    );
    setOpen(false);
  };

  return (
    <>
      {notificationVisible === true ? <AlertDialog /> : ""}
      <PageBreadCrumb
        breadcrumbs={[
          { label: "home.label", link: "/" },
          { label: "master.lists.page", link: "/MasterListsPage" },
          { label: "dictionary.label.modify", link: "/DictionaryManagement" },
        ]}
      />

      <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Section>
              <Heading>
                <FormattedMessage id="dictionary.label.modify" />
              </Heading>
            </Section>
          </Section>
        </Column>
      </Grid>

      <div className="adminPageContent">
        <Grid fullWidth={true} className="gridBoundary">
          <Column lg={16} md={8} sm={4}>
            <Button onClick={() => setOpen(true)} kind="tertiary">
              {intl.formatMessage({ id: "add.new.dictionary.menu" })}
            </Button>
            <Modal
              open={open}
              size="sm"
              onRequestClose={() => setOpen(false)}
              modalHeading="Add Dictionary"
              primaryButtonText="Add"
              secondaryButtonText="Cancel"
              onRequestSubmit={handleSubmitModal}
            >
              <TextInput
                data-modal-primary-focus
                id="dictNumber"
                labelText="Dictionary Number"
                onChange={(e) => setDictionaryNumber(e.target.value)}
                style={{
                  marginBottom: "1rem",
                }}
              />
              <Select
                id="description"
                labelText="Category"
                onChange={(e) => {
                  console.log("Selected category:", e.target.value);
                  setCategory(e.target.value);
                }}
              >
                <SelectItem text="" />
                {categoryDescription.map((description) => (
                  <SelectItem
                    key={description.id}
                    value={description.id}
                    text={description.description}
                  />
                ))}
              </Select>
              <TextInput
                id="dictEntry"
                labelText="Dictionary Entry"
                onChange={(e) => setDictionaryEntry(e.target.value)}
                style={{
                  marginBottom: "1rem",
                }}
              />
              <TextInput
                data-modal-primary-focus
                id="isActive"
                labelText="Is Active"
                onChange={(e) => setIsActive(e.target.value)}
                style={{
                  marginBottom: "1rem",
                }}
              />
              <TextInput
                id="localAbbrev"
                labelText="Local Abbreviation"
                onChange={(e) => setLocalAbbreviation(e.target.value)}
                style={{
                  marginBottom: "1rem",
                }}
              />
            </Modal>
            <DataTable
              rows={dictionaryMenuz.slice(
                (page - 1) * pageSize,
                page * pageSize
              )}
              headers={[
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
                      {rows.map((row, index) => (
                        <TableRow key={index} {...getRowProps({ row })}>
                          {row.cells.map((cell) => (
                            <TableCell key={cell.id}>{cell.value}</TableCell>
                          ))}
                          <TableCell className="cds--table-column-x">
                            <OverflowMenu ariaLabel="modify menu" size="sm" flipped>
                              <OverflowMenuItem itemText="Edit" />
                              <OverflowMenuItem itemText="Delete" />
                            </OverflowMenu>
                          </TableCell>
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
              pageSizes={[10, 20, 30]}
              totalItems={dictionaryMenuz.length}
              forwardText={intl.formatMessage({ id: "pagination.forward" })}
              backwardText={intl.formatMessage({ id: "pagination.backward" })}
              itemRangeText={(min, max, total) =>
                intl.formatMessage(
                  { id: "pagination.item-range" },
                  { min: min, max: max, total: total }
                )
              }
              itemsPerPageText={intl.formatMessage({
                id: "pagination.items-per-page",
              })}
              itemText={(min, max) =>
                intl.formatMessage(
                  { id: "pagination.item" },
                  { min: min, max: max }
                )
              }
              pageNumberText={intl.formatMessage({
                id: "pagination.page-number",
              })}
              pageRangeText={(_current, total) =>
                intl.formatMessage(
                  { id: "pagination.page-range" },
                  { total: total }
                )
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
    </>
  );
}

export default DictionaryManagement;
