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
} from "@carbon/react";
import { Save } from "@carbon/icons-react";
import {
  getFromOpenElisServer,
  postToOpenElisServerFullResponse,
} from "../../utils/Utils";
import { MenuCheckBox } from "./MenuUtil";
import { NotificationContext } from "../../layout/Layout";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification";
import config from "../../../config.json";
import { FormattedMessage, useIntl } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb.js";

let breadcrumbs = [{ label: "home.label", link: "/" }];
function SampleEntryConfigMenu() {
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  const intl = useIntl();

  const componentMounted = useRef(false);
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(5);
  const [modifyButton, setModifyButton] = useState(true);
  const [selectedRowId, setSelectedRowId] = useState(null);
  const [startingRecNo, setStartingRecNo] = useState(1);
  const [sampleEntryConfigMenuList, setSampleEntryConfigMenuList] = useState(
    [],
  );
  const [orderEntryConfigurationList, setOrderEntryConfigurationList] =
    useState([]);

  // async function displayStatus(res) {
  //   setNotificationVisible(true);
  //   setIsSubmitting(false);
  //   if (res.status == "200") {
  //     addNotification({
  //       kind: NotificationKinds.success,
  //       title: intl.formatMessage({ id: "notification.title" }),
  //       message: intl.formatMessage({ id: "success.add.edited.msg" }),
  //     });
  //     var body = await res.json();
  //     setMenuItem(body);
  //   } else {
  //     addNotification({
  //       kind: NotificationKinds.error,
  //       title: intl.formatMessage({ id: "notification.title" }),
  //       message: intl.formatMessage({ id: "error.add.edited.msg" }),
  //     });
  //   }
  // }

  // function handleSubmit(event) {
  //   event.preventDefault();
  //   setIsSubmitting(true);
  //   postToOpenElisServerFullResponse(
  //     "/rest/menu/menu_patient",
  //     JSON.stringify(menuItem),
  //     displayStatus,
  //   );
  // }

  function handleModify(event) {
    event.preventDefault();
    let sampleEntryConfigEditRedirect =
      config.serverBaseUrl +
      `/SampleEntryConfig?ID=${selectedRowId}&startingRecNo=${startingRecNo}`;

    window.open(sampleEntryConfigEditRedirect);
  }

  const handlePageChange = ({ page, pageSize }) => {
    setPage(page);
    setPageSize(pageSize);
  };

  const handleMenuItems = (res) => {
    if (res) {
      setSampleEntryConfigMenuList(res);
    }
  };

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/SampleEntryConfigMenu", handleMenuItems);
    return () => {
      componentMounted.current = false;
    };
  }, []);

  useEffect(() => {
    if (sampleEntryConfigMenuList && sampleEntryConfigMenuList.menuList) {
      const newConfigList = sampleEntryConfigMenuList.menuList.map((item) => ({
        id: item.id,
        startingRecNo: startingRecNo,
        name: item.name,
        description: item.description,
        value: item.value,
        valueType: item.valueType,
      }));
      setOrderEntryConfigurationList(newConfigList);
    }
  }, [sampleEntryConfigMenuList]);

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
            // setStartingRecNo(
            //   orderEntryConfigurationList[row.id - 1].startingRecNo,
            // );
          }}
        />
      );
    }
    return <TableCell key={cell.id}>{cell.value}</TableCell>;
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
                <FormattedMessage id="admin.orderEntryConfigMenu" />
              </Heading>
            </Section>
            <br />
            <br />
            <Section>
              <Form onSubmit={handleModify}>
                <div style={{ margin: "1rem", display: "flex", gap: "1rem" }}>
                  <Button disabled={modifyButton} type="submit">
                    <FormattedMessage id="admin.page.configuration.sampleEntryConfigMenu.button.modify" />
                  </Button>
                  <Button kind="tertiary" disabled={true} type="submit">
                    <FormattedMessage id="admin.page.configuration.sampleEntryConfigMenu.button.deactivate" />
                  </Button>
                  <Button kind="tertiary" disabled={true} type="submit">
                    <FormattedMessage id="admin.page.configuration.sampleEntryConfigMenu.button.add" />
                  </Button>
                </div>
              </Form>
            </Section>
          </Column>

          <Column lg={16} md={8} sm={4}>
            <DataTable
              rows={orderEntryConfigurationList.slice(
                (page - 1) * pageSize,
                page * pageSize,
              )}
              headers={[
                {
                  key: "select",
                  header: intl.formatMessage({
                    id: "admin.page.configuration.sampleEntryConfigMenu.select",
                  }),
                },
                {
                  key: "name",
                  header: intl.formatMessage({
                    id: "admin.page.configuration.sampleEntryConfigMenu.name",
                  }),
                },

                {
                  key: "description",
                  header: intl.formatMessage({
                    id: "admin.page.configuration.sampleEntryConfigMenu.description",
                  }),
                },
                {
                  key: "value",
                  header: intl.formatMessage({
                    id: "admin.page.configuration.sampleEntryConfigMenu.value",
                  }),
                },
              ]}
            >
              {({ rows, headers, getHeaderProps, getTableProps }) => (
                <TableContainer>
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
                      </TableRow>
                    </TableHead>
                    <TableBody>
                      <>
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
              totalItems={orderEntryConfigurationList.length}
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
    </>
  );
}

export default SampleEntryConfigMenu;
