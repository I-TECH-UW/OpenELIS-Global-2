import React, { useEffect, useMemo, useRef, useState } from "react";
import "../../Style.css";
import {
  Button,
  Column,
  DataTable,
  Form,
  Grid,
  Heading,
  Section,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableHeader,
  TableRow,
} from "@carbon/react";
import { FormattedMessage, useIntl } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb";
import { getFromOpenElisServer } from "../../utils/Utils";

function DictionaryManagement() {
  const intl = useIntl();
  const componentMounted = useRef(false);

  const [dictionaryMenu, setDictionaryMenu] = useState([]);
  const [isloading, setIsLoading] = useState(false);

  const fetchedDictionaryMenu = (dictionaryMenus) => {
    if (componentMounted.current) {
      setDictionaryMenu(dictionaryMenus);
    }
  };

  const headers = [
    {
      key: "select",
      header: "Select",
    },
    {
      key: "category",
      header: "Category",
    },
    {
      key: "entry",
      header: "Dictionary Entry",
    },
    {
      key: "abbreviation",
      header: "Local Abbreviation"
    },
    {
      key: "isActive",
      header: "Is Active"
    }
  ];

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/referral-organizations", fetchedDictionaryMenu);
    return () => {
      componentMounted.current = false;
    };
  }, []);

  if (isloading) {
    return (
      <DataTableSkeleton
        className={styles.dataTableSkeleton}
        role="progressbar"
        rowCount={3}
        columnCount={3}
        zebra
      />
    );
  }

  const tableRows = useMemo(
    () =>
      dictionaryMenu?.map((customer) => {
        return {
          id: dictionaryMenu.id,
          name: dictionaryMenu.name,
          email: dictionaryMenu.emailAddress,
          feedback: dictionaryMenu.customerFeedback,
        };
      }),
    [dictionaryMenu]
  );

  return (
    <>
      <PageBreadCrumb
        breadcrumbs={[
          { label: "home.label", link: "/" },
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
      <div className="orderLegendBody">
        <Grid fullWidth={true}>
          <Column lg={16}>
            <Form>
              <Grid fullWidth={true}>
                <Column lg={10}>
                  <div className="orderLegendBody">
                    <TableContainer
                      title="Dictionary Menu"
                      description="Showing Dictionary Menu"
                    >
                      <DataTable
                        rows={tableRows}
                        headers={headers}
                        isSortable={true}
                        size="lg"
                      >
                        {({
                          rows,
                          headers,
                          getHeaderProps,
                          getTableProps,
                          getRowProps,
                        }) => (
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
                                    <TableCell key={cell.id}>
                                      {cell.value?.content ?? cell.value}
                                    </TableCell>
                                  ))}
                                </TableRow>
                              ))}
                            </TableBody>
                          </Table>
                        )}
                      </DataTable>
                    </TableContainer>
                  </div>
                </Column>
              </Grid>
            </Form>
          </Column>
        </Grid>
      </div>
    </>
  );
}

export default DictionaryManagement;
