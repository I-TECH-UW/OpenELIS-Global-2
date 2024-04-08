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

  const [dictionaryMenuz, setDictionaryMenuz] = useState([]);
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);
  const [isloading, setIsLoading] = useState(false);

  const fetchedDictionaryMenu = (dictionaryMenus) => {
    if (componentMounted.current) {
      setDictionaryMenuz(dictionaryMenus);
    }
  };

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/get-dictionary-menu", fetchedDictionaryMenu);
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
      dictionaryMenuz?.map((menu) => {
        return {
          id: menu.id,
          categoryName: menu.categoryName,
          dictEntry: menu.dictEntry,
          localAbbreviation: menu.localAbbreviation,
          isActive: menu.isActive,
        };
      }),
    [dictionaryMenuz]
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
        <Grid fullWidth={true} className="gridBoundary">
          <Column lg={16} md={8} sm={4}>
            <DataTable
              rows={tableRows}
              headers={[
                {
                  key: "categoryName",
                  header: "Category",
                },
                {
                  key: "dictEntry",
                  header: "Dictionary Entry",
                },
                {
                  key: "localAbbreviation",
                  header: "Local Abbreviation",
                },
                {
                  key: "isActive",
                  header: "Is Active",
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
              }) => (
                <TableContainer title="" description="">
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
          </Column>
        </Grid>
      </div>
    </>
  );
}

export default DictionaryManagement;
