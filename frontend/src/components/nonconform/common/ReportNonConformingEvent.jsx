import React, { useState, useEffect, useContext } from "react";
import {
  Button,
  Column,
  Form,
  Grid,
  Section,
  Select,
  SelectItem,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
  TableBody,
  TextArea,
  TextInput,
  Table,
  RadioButton,
} from "@carbon/react";
import { FormattedMessage, useIntl } from "react-intl";
import {
  NotificationKinds,
  AlertDialog,
} from "../../common/CustomNotification";
import { NotificationContext } from "../../layout/Layout";
import {
  getFromOpenElisServer,
  postToOpenElisServerJsonResponse,
} from "../../utils/Utils";
import CustomDatePicker from "../../common/CustomDatePicker";

const initialReportFormValues = {
  type: undefined,
  value: "",
  error: undefined,
  data: undefined,
};

const initialSelected = {
  specimenId: undefined,
  selected: false,
  labOrderNumber: undefined,
};

const initialNCEForm = {
  show: false,
  data: undefined,
  error: undefined,
};

export const ReportNonConformingEvent = () => {
  const [reportFormValues, setReportFormValues] = useState(
    initialReportFormValues,
  );
  const [ldata, setLData] = useState(null);
  const [selected, setSelected] = useState(initialSelected);

  const [nceForm, setnceForm] = useState(initialNCEForm);

  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  const intl = useIntl();

  const handleSubmit = () => {
    if (reportFormValues.type === undefined || reportFormValues.value === "") {
      setReportFormValues({
        ...reportFormValues,
        error: intl.formatMessage({
          id: "error.nonconform.report",
        }),
      });
      return;
    }

    setReportFormValues({
      ...reportFormValues,
      error: undefined,
    });

    try {
      getFromOpenElisServer(
        `/rest/nonconformevents?${reportFormValues.type}=${reportFormValues.value}`,
        (data) => {
          if (data.results) {
            setLData(data.results);
          } else {
            setReportFormValues({
              ...reportFormValues,
              error: intl.formatMessage({
                id: "error.nonconform.report.data.found",
                defaultMessage: "No data found",
              }),
            });
          }
        },
      );
    } catch (error) {
      setReportFormValues({
        ...reportFormValues,
        error: intl.formatMessage({
          id: "error.nonconform.report.data.found",
          defaultMessage: "No data found",
        }),
      });
    }
  };

  const selectOptions = [
    {
      text: "Last Name",
      value: "lastName",
    },
    {
      text: "First Name",
      value: "firstName",
    },
    {
      value: "STNumber",
      text: "Patient Identification Code",
    },
    {
      text: "Lab Number",
      value: "labNumber",
    },
  ];

  const headers = [
    {
      key: "labOrderNumber",
      value: "Lab Number",
    },
    {
      key: "type",
      value: "Specimen type",
    },
  ];

  useEffect(() => {
    if (selected.labOrderNumber && selected.specimenId) {
      getFromOpenElisServer(
        `/rest/reportnonconformingevent?labOrderNumber=${selected.labOrderNumber}&specimenId=${selected.specimenId}`,
        (data) => {
          console.log("data from server for selected", data);
          setLData(undefined);
          setnceForm({
            ...nceForm,
            show: true,
            data: data,
          });
        },
      );
    }
  }, [selected]);

  const handleNCEFormSubmit = () => {
    console.log("nceForm.data", nceForm.data);

    if (!nceForm.data.dateOfEvent) {
      setnceForm({
        ...nceForm,
        error: intl.formatMessage({
          id: "error.nonconform.report.data.found",
          defaultMessage: "Please select a date",
        }),
      });
      return;
    }

    let body = {
      currentUserId: parseInt(nceForm.data.currentUserId),
      id: parseInt(nceForm.data.id),
      reportDate: nceForm.data.reportDate,
      name: nceForm.data.name,
      reporterName: nceForm.data.reporterName,
      dateOfEvent: nceForm.data.dateOfEvent,
      labOrderNumber: nceForm.data.labOrderNumber,
      prescriberName: nceForm.data.prescriberName,
      site: nceForm.data.site,
      reportingUnit: nceForm.data.reportingUnit,
      description: nceForm.data.description,
      suspectedCauses: nceForm.data.suspectedCauses,
      proposedAction: nceForm.data.proposedAction,
    };
    postToOpenElisServerJsonResponse(
      "/rest/reportnonconformingevent",
      JSON.stringify(body),
      (data) => {
        setNotificationVisible(true);
        setReportFormValues(initialReportFormValues);
        setLData(null);
        setSelected(initialSelected);
        setnceForm(initialNCEForm);

        if (data.success) {
          addNotification({
            kind: NotificationKinds.success,
            title: intl.formatMessage({ id: "notification.title" }),
            message: intl.formatMessage({
              id: "nonconform.order.save.success",
            }),
          });
        } else {
          addNotification({
            kind: NotificationKinds.error,
            title: intl.formatMessage({ id: "notification.title" }),
            message: intl.formatMessage({ id: "nonconform.order.save.fail" }),
          });
        }
      },
    );
  };

  return (
    <div>
      {notificationVisible === true ? <AlertDialog /> : ""}
      <h2>
        <FormattedMessage id={`nonconform.report`} />
      </h2>
      <Grid fullWidth={true}>
        <Column lg={10} md={10} sm={8}>
          <Form>
            <Grid fullWidth={true}>
              <Column style={{ marginTop: "50px" }} lg={6} md={8} sm={4}>
                <div className="inlineDiv">
                  <Select
                    id="type"
                    labelText={intl.formatMessage({
                      id: "label.form.searchby",
                    })}
                    value={reportFormValues.type}
                    onChange={(e) => {
                      setReportFormValues({
                        ...reportFormValues,
                        type: e.target.value,
                      });
                    }}
                  >
                    <SelectItem key={"emptyselect"} value={""} text={""} />
                    {selectOptions.map((statusOption) => (
                      <SelectItem
                        key={statusOption.value}
                        value={statusOption.value}
                        text={statusOption.text}
                      />
                    ))}
                  </Select>
                  <TextInput
                    labelText={intl.formatMessage({
                      id: "testcalculation.label.textValue",
                    })}
                    value={reportFormValues.value}
                    onChange={(e) => {
                      setReportFormValues({
                        ...reportFormValues,
                        value: e.target.value,
                      });
                    }}
                    id={`field.name`}
                    className="inputText"
                  />
                </div>
              </Column>
            </Grid>
            <br />
            <Section>
              <br />
              {!!reportFormValues.error && (
                <div style={{ color: "#c62828", margin: 4 }}>
                  {reportFormValues.error}
                </div>
              )}

              <Button type="button" onClick={handleSubmit}>
                <FormattedMessage id="label.button.submit" />
              </Button>
            </Section>
          </Form>
        </Column>
      </Grid>
      {ldata && ldata.length > 0 && (
        <Table style={{ marginTop: "1em" }}>
          <TableHead>
            <TableRow>
              <TableHeader key="checkbox" />
              {headers.map((header) => (
                <TableHeader id={header.key} key={header.key}>
                  {header.value}
                </TableHeader>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {ldata.map((row) => (
              <TableRow key={row.id}>
                <TableCell key={`${row.id}-checkbox`}>
                  <RadioButton
                    name="radio-group"
                    onClick={() => {
                      console.log("row", row);
                      console.log("row.id", row.id);
                      setSelected({
                        specimenId: row.id,
                        selected: true,
                        labOrderNumber: row.labOrderNumber,
                      });
                      console.log(selected);
                    }}
                    labelText=""
                    id={row.id}
                  />
                </TableCell>
                {headers.map((header) => (
                  <TableCell key={header.key}>
                    {header.key === "type"
                      ? row.sampleItems.map((item) => item.type).join(",")
                      : row[header.key]}
                  </TableCell>
                ))}
              </TableRow>
            ))}
          </TableBody>
        </Table>
      )}
      {nceForm.show && nceForm.data && (
        <div>
          <div
            style={{
              display: "flex",
              justifyContent: "center",
              margin: "20px",
            }}
          >
            <Grid
              fullWidth={true}
              style={{
                padding: "2px",
                border: "1px solid #ccc",
                borderRadius: "5px",
                boxShadow: "0px 2px 4px rgba(0, 0, 0, 0.1)",
              }}
            >
              <Column lg={16} style={{ marginBottom: "20px" }}></Column>
              <Column lg={8} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.report.field.date" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px", color: "#555" }}>
                  {nceForm.data.reportDate}
                </div>
              </Column>
              <Column lg={8} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="patient.label.name" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>{nceForm.data.name}</div>
              </Column>
              <Column lg={8} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.person.reporting.different" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  <TextInput
                    value={nceForm.data.reporterName}
                    onChange={(e) => {
                      setnceForm({
                        ...nceForm,
                        data: {
                          ...nceForm.data,
                          reporterName: e.target.value,
                        },
                      });
                    }}
                    id={`field.name`}
                    className="inputText"
                  />
                </div>
              </Column>
              <Column lg={8} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.nce.number" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  {nceForm.data.nceNumber}
                </div>
              </Column>
              <Column lg={8} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.date.event" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  <CustomDatePicker
                    key="startDate"
                    id={"startDate"}
                    disallowFutureDate={true}
                    autofillDate={true}
                    value={nceForm.data.dateOfEvent}
                    className="inputDate"
                    onChange={(date) =>
                      setnceForm({
                        ...nceForm,
                        data: {
                          ...nceForm.data,
                          dateOfEvent: date,
                        },
                      })
                    }
                  />
                </div>
              </Column>

              <Column lg={8} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="sample.label.labnumber" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  {nceForm.data.labOrderNumber}
                </div>
              </Column>

              <Column lg={8} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.label.prescibernamesite" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  {`${nceForm.data.prescriberName}-${nceForm.data.site}`}
                </div>
              </Column>
              <Column lg={8} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.label.reportingunit" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  <Select
                    id="reportUnits"
                    value={nceForm.data.reportingUnit}
                    onChange={(e) => {
                      setnceForm({
                        ...nceForm,
                        data: {
                          ...nceForm.data,
                          reportingUnit: e.target.value,
                        },
                      });
                    }}
                  >
                    <SelectItem key={"emptyselect"} value={""} text={""} />
                    {nceForm.data.reportUnits.map((option) => (
                      <SelectItem
                        key={option.value}
                        value={option.id}
                        text={option.value}
                      />
                    ))}
                  </Select>
                </div>
              </Column>
              <Column lg={8} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.description.nce" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  <TextArea
                    value={nceForm.data.description}
                    onChange={(e) => {
                      setnceForm({
                        ...nceForm,
                        data: {
                          ...nceForm.data,
                          description: e.target.value,
                        },
                      });
                    }}
                    rows={2}
                    id="text-area-1"
                  />
                </div>
              </Column>
              <Column lg={8} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.label.suspected.cause.nce" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  <TextArea
                    value={nceForm.data.suspectedCauses}
                    onChange={(e) => {
                      setnceForm({
                        ...nceForm,
                        data: {
                          ...nceForm.data,
                          suspectedCauses: e.target.value,
                        },
                      });
                    }}
                    rows={2}
                    id="text-area-1"
                  />
                </div>
              </Column>
              <Column lg={8} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.label.proposed.action" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  <TextArea
                    value={nceForm.data.proposedAction}
                    onChange={(e) => {
                      setnceForm({
                        ...nceForm,
                        data: {
                          ...nceForm.data,
                          proposedAction: e.target.value,
                        },
                      });
                    }}
                    rows={2}
                    id="text-area-1"
                  />
                </div>
                {!!nceForm.error && (
                  <div style={{ color: "#c62828", margin: 4 }}>
                    {nceForm.error}
                  </div>
                )}
                <Button type="button" onClick={() => handleNCEFormSubmit()}>
                  <FormattedMessage id="label.button.submit" />
                </Button>
              </Column>
            </Grid>
          </div>
        </div>
      )}
    </div>
  );
};

export default ReportNonConformingEvent;
