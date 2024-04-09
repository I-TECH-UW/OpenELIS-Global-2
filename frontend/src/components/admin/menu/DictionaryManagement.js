import React, { useContext, useEffect, useMemo, useRef, useState } from "react";
import "../../Style.css";
import {
  Button,
  Column,
  DataTable,
  DataTableSkeleton,
  Dropdown,
  Form,
  Grid,
  Heading,
  Modal,
  MultiSelect,
  Pagination,
  Search,
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
  TableToolbar,
  TableToolbarContent,
  TableToolbarSearch,
  TextInput,
} from "@carbon/react";
import { FormattedMessage, useIntl } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb";
import { getFromOpenElisServer, postToOpenElisServer } from "../../utils/Utils";
import { NotificationContext } from "../../layout/Layout";
import { AlertDialog } from "../../common/CustomNotification";

function DictionaryManagement() {
  const intl = useIntl();
  const componentMounted = useRef(false);

  const { notificationVisible } = useContext(NotificationContext);
  const [dictionaryMenuz, setDictionaryMenuz] = useState([]);

  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);
  const [open, setOpen] = useState(false);

  const [categoryDescription, setCategoryDescription] = useState([]);

  //for posting data
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
      setCategoryDescription(category);
    }
  };

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/get-dictionary-menu", fetchedDictionaryMenu);
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

  const handleSubmitModal = (e) => {
    e.preventDefault();

    const postData = {
      dictionaryNumber: dictionaryNumber,
      category: category,
      dictionaryEntry: dictionaryEntry,
      localAbbreviation: localAbbreviation,
      isActive: isActive,
    };
    console.log(JSON.stringify(orderFormValues));
    postToOpenElisServer(
      "/rest/create-dictionary-menu",
      JSON.stringify(postData)
    );
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
      <div className="orderLegendBody">
        <Grid fullWidth={true} className="gridBoundary">
          <Column lg={16} md={8} sm={4}>
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
                  <TableToolbar>
                    <TableToolbarContent>
                      <TableToolbarSearch
                        onChange={onInputChange}
                        placeholder={intl.formatMessage({
                          id: "search.by.dictionary.entry",
                        })}
                      />
                      <Button onClick={() => setOpen(true)}>
                        {intl.formatMessage({ id: "add.new.dictionary.menu" })}
                      </Button>
                      <Modal
                        open={open}
                        onRequestClose={() => setOpen(false)}
                        modalHeading="Add Dictionary"
                        primaryButtonText="Add"
                        secondaryButtonText="Cancel"
                      >
                        <TextInput
                          id="dictNumber"
                          labelText="Dictionary Number"
                          style={{
                            marginBottom: "1rem",
                          }}
                        />
                        <Select id="description" labelText="Category">
                          {categoryDescription.map((description) => (
                            <SelectItem
                              value={description}
                              text={description}
                            />
                          ))}
                        </Select>
                        <TextInput
                          id="dictEntry"
                          labelText="Dictionary Entry"
                          style={{
                            marginBottom: "1rem",
                          }}
                        />
                        <TextInput
                          data-modal-primary-focus
                          id="isActive"
                          labelText="Is Active"
                          style={{
                            marginBottom: "1rem",
                          }}
                        />
                        <TextInput
                          data-modal-primary-focus
                          id="localAbbrev"
                          labelText="Local Abbreviation"
                          style={{
                            marginBottom: "1rem",
                          }}
                        />
                      </Modal>
                    </TableToolbarContent>
                  </TableToolbar>
                  <Table {...getTableProps()}>
                    <TableHead>
                      <TableRow>
                        {headers.map((header) => (
                          <TableHeader
                            {...getHeaderProps({
                              header,
                              isSortable: header.isSortable,
                            })}
                          >
                            {header.header?.content ?? header.header}
                          </TableHeader>
                        ))}
                      </TableRow>
                    </TableHead>
                    <TableBody>
                      {rows.map((row, index) => (
                        <TableRow {...getRowProps({ row })}>
                          {row.cells.map((cell) => (
                            <TableCell key={cell.id}>{cell.value}</TableCell>
                          ))}
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
