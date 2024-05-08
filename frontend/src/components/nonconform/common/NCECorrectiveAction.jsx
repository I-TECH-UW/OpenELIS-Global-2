import React, { useState, useEffect } from "react";
import {
  Button,
  Column,
  Form,
  Grid,
  Select,
  SelectItem,
  TextInput,
  Section,
  Table,
  TableHead,
  TableRow,
  TableBody,
  RadioButton,
  TextArea,
  TableHeader,
  TableCell,
} from "@carbon/react";
import { AlertDialog } from "../../common/CustomNotification";
import { FormattedMessage, useIntl } from "react-intl";
import { initialReportFormValues, selectOptions } from "./ViewNonConforming";
import { getFromOpenElisServer } from "../../utils/Utils";
import CustomDatePicker from "../../common/CustomDatePicker";

const initialFormData = {
  "correctiveAction-log-0": null,
  "responsiblePerson-0": null,
  "dateActionCompleted-0": null,
  "turnAroundTime-0": null,
  dateCompleted: null,
  "action-type-0": null,
  discussionDate: "",
};

export const NCECorrectiveAction = () => {
  const [reportFormValues, setReportFormValues] = useState(
    initialReportFormValues,
  );

  const [notificationVisible, setNotificationVisible] = useState(false);
  const [selected, setSelected] = useState(null);

  const [tdiscussionDate, setTDiscussionDate] = useState(null);

  const [tData, setTData] = useState(null);
  const [data, setData] = useState(null);
  const [formData, setFormData] = useState(initialFormData);

  const intl = useIntl();

  useEffect(() => {
    if (selected) {
      try {
        getFromOpenElisServer(
          `/rest/NCECorrectiveAction?nceNumber=${selected}`,
          (data) => {
            setData(data);
          },
        );
      } catch (error) {}
    }
  }, [selected]);

  const handleSubmit = () => {
    let other =
      reportFormValues.type === "labNumber" ? "nceNumber" : "labNumber";

    if (reportFormValues.type === "labNumber") {
      getFromOpenElisServer(
        `/rest/nonconformingcorrectiveaction?status=CAPA&${reportFormValues.type}=${reportFormValues.value}&uppressExternalSearch=false&${other}=undefined`,
        (data) => {
          if (data.nceEventsSearchResults.length > 1) {
            setTData(data);
          }
        },
      );
    } else {
      setSelected(reportFormValues.value);
    }
  };

  const handleNCEFormSubmit = () => {};

  const handleCorrectiveActionChange = (e) => {
    setFormData({
      ...formData,
      "correctiveAction-log-0": e.target.value,
    });
  };

  const handleResponsiblePersonChange = (e) => {
    setFormData({
      ...formData,
      "responsiblePerson-0": e.target.value,
    });
  };

  const handleDiscussionDateChange = (date) => {
    setTDiscussionDate(date);
  };

  const handleAddDiscussionDate = () => {
    setFormData({
      ...formData,
      discussionDate: formData.discussionDate + tdiscussionDate,
    });
  };

  return (
    <div>
      {notificationVisible && <AlertDialog />}
      <Grid fullWidth={true}>
        <Column lg={16}>
          <h2>
            <FormattedMessage id={`nonconform.corrective.title`} />
          </h2>
        </Column>
        <Column lg={16} md={10} sm={8}>
          <Form>
            <Grid fullWidth={true}>
              <Column lg={4} md={8}>
                <Select
                  id="type"
                  labelText={intl.formatMessage({
                    id: "label.form.searchby",
                  })}
                  value={reportFormValues.type}
                  onChange={(e) =>
                    setReportFormValues({
                      ...reportFormValues,
                      type: e.target.value,
                    })
                  }
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
              </Column>
              <Column lg={4}>
                <TextInput
                  labelText={intl.formatMessage({
                    id: "testcalculation.label.textValue",
                  })}
                  value={reportFormValues.value}
                  onChange={(e) =>
                    setReportFormValues({
                      ...reportFormValues,
                      value: e.target.value,
                    })
                  }
                  id={`field.name`}
                />
              </Column>
              <Column lg={16}>
                <br />
              </Column>
              <Column lg={16}>
                <Button type="button" onClick={handleSubmit}>
                  <FormattedMessage id="label.button.search" />
                </Button>
              </Column>
            </Grid>
            <Section>
              <br />
              {!!reportFormValues.error && (
                <div style={{ color: "#c62828", margin: 4 }}>
                  {reportFormValues.error}
                </div>
              )}
            </Section>
          </Form>
        </Column>
        <Column lg={16}>
          <br />
        </Column>
        <Column lg={16}>
          {tData && (
            <div>
              <Grid>
                <Column lg={16} md={16} sm={16}>
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
                      {tData.nceEventsSearchResults.map((row) => (
                        <TableRow key={row.nceNumber}>
                          <TableCell key={`${row}-checkbox`}>
                            <RadioButton
                              name="radio-group"
                              onClick={() => setSelected(row.nceNumber)}
                              labelText=""
                              id={row.id}
                            />
                          </TableCell>
                          <TableCell key={row.key + "date"}>
                            {new Date(row.reportDate).toDateString()}
                          </TableCell>
                          <TableCell key={row.key + "1"}>
                            {row.nceNumber}
                          </TableCell>
                          <TableCell key={row.key + "2"}>
                            {
                              tData.reportingUnits.find(
                                (obj) =>
                                  parseInt(obj.id) === row.reportingUnitId,
                              ).value
                            }
                          </TableCell>
                        </TableRow>
                      ))}
                    </TableBody>
                  </Table>
                </Column>
              </Grid>
            </div>
          )}

          {data && (
            <Grid fullWidth={true}>
              <Column lg={3}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <b>
                      <FormattedMessage id="nonconform.nce.number" />
                    </b>
                  </span>
                </div>
                <div style={{ marginBottom: "10px", color: "#555" }}>
                  {data.nceNumber}
                </div>
              </Column>
              <Column lg={3} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.field.date" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>{data.dateOfEvent}</div>
              </Column>

              <Column lg={3} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.view.nce.severity" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  {
                    data.severityConsequencesList.find(
                      (obj) => obj.id === data.consequences,
                    ).value
                  }
                </div>
              </Column>

              <Column lg={3} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.label.reportingunit" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  {
                    data.reportingUnits.find(
                      (obj) => obj.id == data.reportingUnit,
                    ).value
                  }
                </div>
              </Column>
              <Column lg={3} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.view.specimen" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  {data.specimens[0].typeOfSample.description}
                </div>
              </Column>
              <Column lg={1}></Column>
              <Column lg={3} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="sample.label.labnumber" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  {data.labOrderNumber}
                </div>
              </Column>
              <Column lg={3} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.field.reporting.data" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>{data.reportDate}</div>
              </Column>
              <Column lg={3} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.label.prescibernamesite" />
                  </span>
                </div>
                <div
                  style={{ marginBottom: "10px" }}
                >{`${data.prescriberName}-${data.site}`}</div>
              </Column>
              <Column lg={3} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.view.event.description" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  {data.description ?? ""}
                </div>
              </Column>
              <Column lg={3} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.label.suspected.cause.nce" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  {data.suspectedCauses ?? ""}
                </div>
              </Column>
              <Column lg={3} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.label.proposed.action" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  {data.proposedAction ?? ""}
                </div>
              </Column>

              <Column lg={3} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.lab.componenet.nce" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  {data.labComponentList.find(
                    (obj) => obj.id === data.laboratoryComponent,
                  ).value ?? ""}
                </div>
              </Column>

              <Column lg={3} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.view.nce.category" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  {data.nceCategories.find((obj) => obj.id === data.nceCategory)
                    .name ?? ""}
                </div>
              </Column>

              <Column lg={3} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.view.nce.type" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  {data.nceTypes.find((obj) => obj.id === data.nceType).name ??
                    ""}
                </div>
              </Column>

              <Column lg={16}>
                <br></br>
              </Column>

              <Column lg={8}>
                <TextArea
                  labelText={
                    <FormattedMessage id="banner.menu.nonconformity.correctiveActions" />
                  }
                  value={formData[`correctiveAction-log-0`]}
                  onChange={handleCorrectiveActionChange}
                  rows={2}
                  id="text-area-3"
                />
              </Column>
              <Column lg={8}>
                <TextArea
                  labelText={
                    <FormattedMessage id="nonconform.person.responsible" />
                  }
                  value={formData[`responsiblePerson-0`]}
                  onChange={handleResponsiblePersonChange}
                  rows={2}
                  id="text-area-4"
                />
              </Column>
              <Column lg={8}>
                <CustomDatePicker
                  key="tdiscussionDate"
                  id={"tdiscussionDate"}
                  labelText={
                    <FormattedMessage id="nonconform.date.discussion.nce" />
                  }
                  autofillDate={true}
                  value={tdiscussionDate}
                  onChange={handleDiscussionDateChange}
                />
                <button
                  disabled={!!tdiscussionDate}
                  onClick={handleAddDiscussionDate}
                  style={{ margin: "5px" }}
                >
                  <FormattedMessage id="nonconform.add.new.date" />
                </button>
              </Column>

              <Column lg={8} >
                <CustomDatePicker
                  key="dateCompleted-0"
                  id={"dateCompleted-0"}
                  labelText={
                    <FormattedMessage id="nonconform.nce.resolution.dateCompleted" />
                  }
                  autofillDate={true}
                  value={formData[`dateCompleted`]}
                  onChange={(e) => {
                    setFormData({
                      ...formData,
                      dateCompleted: e,
                    });
                  }}
                />

                
              </Column>

              <Column>
              <CustomDatePicker
                  key="dateActionCompleted-0"
                  id={"dateActionCompleted-0"}
                  labelText={
                    <FormattedMessage id="nonconform.date.completed" />
                  }
                  autofillDate={true}
                  value={formData[`dateActionCompleted-0`]}
                  onChange={(e) => {
                    setFormData({
                      ...formData,
                      "dateActionCompleted-0": e,
                    });
                  }}
                /></Column>

              <Column lg={16}>
                <br />
                <Button type="button" onClick={handleNCEFormSubmit}>
                  <FormattedMessage id="label.button.submit" />
                </Button>
              </Column>
            </Grid>
          )}
        </Column>
      </Grid>
    </div>
  );
};
