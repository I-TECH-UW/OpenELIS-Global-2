import { React, useState, useEffect, useRef, useContext } from "react";
import { FormattedMessage, useIntl, injectIntl } from "react-intl";
import {
  Grid,
  Column,
  Form,
  Button,
  NumberInput,
  DataTable,
  TableContainer,
  Table,
  TableHead,
  TableRow,
  TableHeader,
  TableBody,
  TableCell,
} from "@carbon/react";
import CustomLabNumberInput from "../common/CustomLabNumberInput";
import { NotificationContext } from "../layout/Layout";
import { AlertDialog, NotificationKinds } from "../common/CustomNotification";
import { getFromOpenElisServer } from "../utils/Utils";

const ExistingOrder = () => {
  const intl = useIntl();
  const componentMounted = useRef(false);
  const [accessionNumber, setAccessionNumber] = useState("");
  const [labelSets, setLabelSets] = useState(1);
  const [patientSearchResults, setPatientSearchResults] = useState(null);
  const [orderResults, setOrderResults] = useState(null);
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);
  useEffect(() => {
    componentMounted.current = true;
    return () => {
      componentMounted.current = false;
    };
  }, [accessionNumber]);
  const fetchPatientData = (res) => {
    if (componentMounted.current) {
      let patientsResults = res.patientSearchResults;
      if (patientsResults.length > 0) {
        setPatientSearchResults(patientsResults[0]);
      } else {
        setPatientSearchResults(null);
        addNotification({
          title: intl.formatMessage({ id: "notification.title" }),
          message: intl.formatMessage({ id: "patient.search.nopatient" }),
          kind: NotificationKinds.warning,
        });
        setNotificationVisible(true);
      }
    }
  };

  const fetchOrderData = (res) => {
    if (componentMounted.current) {
      let orderResults = res.existingTests;
      setOrderResults(orderResults);
    }
  };

  const handleSearch = (e) => {
    e.preventDefault();
    getFromOpenElisServer(
      `/rest/patient-search-results?labNumber=${accessionNumber}`,
      fetchPatientData
    );
    getFromOpenElisServer(
      `/rest/sample-edit?accessionNumber=${accessionNumber}`,
      fetchOrderData
    );
  };

  return (
    <>
      {notificationVisible === true ? <AlertDialog /> : ""}
      <div className="orderLegendBody">
        <Form onSubmit={handleSearch}>
          <Grid>
            <Column lg={16}>
              <h4>
                <FormattedMessage id="sample.entry.search.barcode" />
              </h4>
            </Column>
            <Column lg={4}>
              <CustomLabNumberInput
                placeholder={"Enter Lab No"}
                id="labNumber"
                name="labNumber"
                value={accessionNumber}
                onChange={(e, rawVal) => {
                  setOrderResults(null);
                  setAccessionNumber(rawVal ? rawVal : e?.target?.value);
                }}
                labelText={<FormattedMessage id="search.label.accession" />}
              />
            </Column>
            <Column lg={2}>
              <Button type="submit">
                <FormattedMessage id="label.button.submit" />
              </Button>
            </Column>
          </Grid>
        </Form>
        {patientSearchResults !== null && orderResults !== null && (
          <Grid>
            <Column lg={4}>
              <h4>
                <FormattedMessage id="patient.label.name" />
              </h4>
            </Column>
            <Column lg={4}>
              <h4>
                <FormattedMessage id="patient.dob" />
              </h4>
            </Column>
            <Column lg={4}>
              <h4>
                <FormattedMessage id="patient.gender" />
              </h4>
            </Column>
            <Column lg={4}>
              <h4>
                <FormattedMessage id="patient.natioanalid" />
              </h4>
            </Column>
            <Column lg={4}>
              {patientSearchResults.firstName +
                " " +
                patientSearchResults.lastName}
            </Column>
            <Column lg={4}>{patientSearchResults.birthdate}</Column>
            <Column lg={4}>{patientSearchResults.gender}</Column>
            <Column lg={4}>{patientSearchResults.nationalId}</Column>
          </Grid>
        )}
      </div>
      {patientSearchResults !== null && orderResults !== null && (
        <div className="orderLegendBody">
          <Grid>
            <Column lg={16}>
              <h4>
                <FormattedMessage id="barcode.print.section.set" />
              </h4>
            </Column>
            <Column lg={6}>
              <FormattedMessage id="barcode.print.set.instruction" />
            </Column>
            <Column>
              <Button>
                <FormattedMessage id="barcode.print.set.button" />
              </Button>
            </Column>
          </Grid>
        </div>
      )}
      {patientSearchResults !== null && orderResults !== null && (
        <div className="orderLegendBody">
          <DataTable
            headers={[
              { key: "labelType", header: "Label Type" },
              { key: "accessionNumber", header: "Accession Number" },
              { key: "additionalInfo", header: "Additional Info" },
              { key: "numberToPrint", header: "Number to Print" },
              { key: "button", header: "" },
            ]}
            rows={[
              {
                id: "row1",
                labelType: "Order",
                accessionNumber: accessionNumber,
                additionalInfo: "",
                numberToPrint: (
                  <NumberInput
                    min={1}
                    max={100}
                    defaultValue={1}
                    onChange={(_, state) => setLabelSets(state.value)}
                    id="numberToPrint"
                    className="inputText"
                  />
                ),
                button: (
                  <Button>
                    <FormattedMessage id="barcode.print.individual.button" />
                  </Button>
                ),
              },
              {
                id: "row2",
                labelType: "Specimen",
                accessionNumber: orderResults[0].accessionNumber,
                additionalInfo: orderResults[0].sampleType,
                numberToPrint: 1,
                button: (
                  <Button>
                    <FormattedMessage id="barcode.print.individual.button" />
                  </Button>
                ),
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
                    {rows.map((row) => (
                      <TableRow key={row.id}>
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
        </div>
      )}
    </>
  );
};
export default injectIntl(ExistingOrder);
