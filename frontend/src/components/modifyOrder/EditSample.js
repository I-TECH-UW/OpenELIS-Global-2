import React, { useEffect, useRef, useState } from "react";
import {
  Button,
  Link,
  Row,
  Stack,
  DataTable,
  TableContainer,
  Table,
  TableHead,
  TableRow,
  TableHeader,
  TableBody,
  TableCell,
  Pagination,
  Column,
  TextInput,
  Checkbox,
} from "@carbon/react";
import { Add } from "@carbon/react/icons";
import { getFromOpenElisServer } from "../utils/Utils";
import EditSampleType from "./EditSampleType";
import { FormattedMessage } from "react-intl";
import { OrderCurrentTestsHeaders } from "../data/orderCurrentTestsHeaders";
const EditSample = (props) => {
  const { samples, setSamples, orderFormValues } = props;
  const componentMounted = useRef(true);
  const [elementsCounter, setElementsCounter] = useState(0);
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(5);

  const [rejectSampleReasons, setRejectSampleReasons] = useState([]);

  const handleAddNewSample = () => {
    let updateSamples = [...samples];
    let count = elementsCounter + 1;
    updateSamples.push({
      index: count,
      sampleRejected: false,
      rejectionReason: "",
      requestReferralEnabled: false,
      referralItems: [],
      sampleTypeId: "",
      sampleXML: null,
      panels: [],
      tests: [],
    });
    setSamples(updateSamples);
    setElementsCounter(count);
  };

  const sampleTypeObject = (object) => {
    let newState = [...samples];
    switch (true) {
      case object.sampleTypeId !== undefined && object.sampleTypeId !== "":
        newState[object.sampleObjectIndex].sampleTypeId = object.sampleTypeId;
        break;
      case object.sampleRejected:
        newState[object.sampleObjectIndex].sampleRejected =
          object.sampleRejected;
        break;
      case object.rejectionReason !== undefined &&
        object.rejectionReason !== null:
        newState[object.sampleObjectIndex].rejectionReason =
          object.rejectionReason;
        break;
      case object.selectedTests !== undefined &&
        object.selectedTests.length > 0:
        newState[object.sampleObjectIndex].tests = object.selectedTests;
        break;
      case object.selectedPanels !== undefined &&
        object.selectedPanels.length > 0:
        newState[object.sampleObjectIndex].panels = object.selectedPanels;
        break;
      case object.sampleXML !== undefined && object.sampleXML !== null:
        newState[object.sampleObjectIndex].sampleXML = object.sampleXML;
        break;
      case object.requestReferralEnabled:
        newState[object.sampleObjectIndex].requestReferralEnabled =
          object.requestReferralEnabled;
        break;
      case object.referralItems !== undefined &&
        object.referralItems.length > 0:
        newState[object.sampleObjectIndex].referralItems = object.referralItems;
        break;
      default:
        props.setSamples(newState);
    }
  };

  const handlePageChange = (pageInfo) => {
    if (page != pageInfo.page) {
      setPage(pageInfo.page);
    }

    if (pageSize != pageInfo.pageSize) {
      setPageSize(pageInfo.pageSize);
    }
  };
  const removeSample = (index) => {
    let updateSamples = samples.splice(index, 1);
    setSamples(updateSamples);
  };

  const fetchRejectSampleReasons = (res) => {
    if (componentMounted.current) {
      setRejectSampleReasons(res);
    }
  };

  const handleRemoveSample = (e, sample) => {
    e.preventDefault();
    let filtered = samples.filter(function (element) {
      return element !== sample;
    });
    setSamples(filtered);
  };

  useEffect(() => {
    getFromOpenElisServer(
      "/rest/test-rejection-reasons",
      fetchRejectSampleReasons,
    );
    window.scrollTo(0, 0);
    return () => {
      componentMounted.current = false;
    };
  }, []);

  useEffect(() => {
    getFromOpenElisServer(
      "/rest/test-rejection-reasons",
      fetchRejectSampleReasons,
    );
    window.scrollTo(0, 0);
    return () => {
      componentMounted.current = false;
    };
  }, []);

  const renderCell = (cell) => {
    if (cell.info.header === "accessionNumber") {
      return <TableCell key={cell.id}>{cell.value}</TableCell>;
    } else if (cell.info.header === "sampleType") {
      return <TableCell key={cell.id}>{cell.value}</TableCell>;
    } else if (cell.info.header === "collectionDate") {
      return (
        <TableCell key={cell.id}>
          <TextInput
            id={cell.id + cell.info.header}
            labelText=""
            value={cell.value}
          ></TextInput>
        </TableCell>
      );
    } else if (cell.info.header === "collectionTime") {
      return (
        <TableCell key={cell.id}>
          <TextInput
            id={cell.id + cell.info.header}
            labelText=""
            value={cell.value}
          ></TextInput>
        </TableCell>
      );
    } else if (cell.info.header === "removeSample") {
      return (
        <TableCell key={cell.id}>
          <Checkbox
            id={cell.id + cell.info.header}
            labelText=""
            checked={cell.value}
          ></Checkbox>
        </TableCell>
      );
    } else if (cell.info.header === "testName") {
      return <TableCell key={cell.id}>{cell.value}</TableCell>;
    } else if (cell.info.header === "hasResults") {
      return (
        <TableCell key={cell.id}>
          <Checkbox
            id={cell.id + cell.info.header}
            labelText=""
            checked={cell.value}
          ></Checkbox>
        </TableCell>
      );
    } else if (cell.info.header === "canceled") {
      return (
        <TableCell key={cell.id}>
          <Checkbox
            id={cell.id + cell.info.header}
            labelText=""
            checked={cell.value}
          ></Checkbox>
        </TableCell>
      );
    } else {
      return <TableCell key={cell.id}></TableCell>;
    }
  };

  return (
    <>
      <div className="orderLegendBody">
        <Column lg={16}>
          <DataTable
            rows={orderFormValues.existingTests}
            headers={OrderCurrentTestsHeaders}
            isSortable
          >
            {({ rows, headers, getHeaderProps, getTableProps }) => (
              <TableContainer title="Current Tests">
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
                      {rows
                        .slice((page - 1) * pageSize)
                        .slice(0, pageSize)
                        .map((row ) => (
                          <TableRow key={row.id}>
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
            pageSizes={[5, 10, 20, 30]}
            totalItems={orderFormValues.existingTests.length}
          ></Pagination>
        </Column>
      </div>
      <div className="orderLegendBody">
        <Column lg={16}>
          <DataTable
            rows={orderFormValues.possibleTests}
            headers={OrderCurrentTestsHeaders}
            isSortable
          >
            {({ rows, headers, getHeaderProps, getTableProps }) => (
              <TableContainer title="Available Tests">
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
                      {rows
                        .slice((page - 1) * pageSize)
                        .slice(0, pageSize)
                        .map((row) => (
                          <TableRow key={row.id}>
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
            pageSizes={[5, 10, 20, 30]}
            totalItems={orderFormValues.existingTests.length}
          ></Pagination>
        </Column>
      </div>
      <Stack gap={10}>
        <div className="orderLegendBody">
          <h3>
            <FormattedMessage id="label.button.sample" />
          </h3>
          {samples.map((sample, i) => {
            return (
              <div className="sampleType" key={i}>
                <h4>
                  <FormattedMessage id="label.button.sample" /> {i + 1}
                </h4>
                <Link href="#" onClick={(e) => handleRemoveSample(e, sample)}>
                  {<FormattedMessage id="sample.remove.action" />}
                </Link>
                <EditSampleType
                  index={i}
                  rejectSampleReasons={rejectSampleReasons}
                  removeSample={removeSample}
                  sample={sample}
                  sampleTypeObject={sampleTypeObject}
                />
              </div>
            );
          })}
          <Row>
            <div className="inlineDiv">
              <Button onClick={handleAddNewSample}>
                {<FormattedMessage id="sample.add.action" />}
                &nbsp; &nbsp;
                <Add size={16} />
              </Button>
            </div>
          </Row>
        </div>
      </Stack>
    </>
  );
};

export default EditSample;
