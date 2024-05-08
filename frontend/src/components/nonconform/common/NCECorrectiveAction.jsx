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
  TableHeader,
  TableCell,
} from "@carbon/react";
import { AlertDialog } from "../../common/CustomNotification";
import { useEffect, useState } from "react";
import { FormattedMessage, useIntl } from "react-intl";
import { initialReportFormValues, selectOptions } from "./ViewNonConforming";
import { getFromOpenElisServer } from "../../utils/Utils";
import { headers } from "./ViewNonConforming";

export const NCECorrectiveAction = () => {
  const [reportFormValues, setReportFormValues] = useState(
    initialReportFormValues,
  );

  const [notificationVisible, setNotificationVisible] = useState(false);
  const [selected, setSelected] = useState(null);

  const [tData, setTData] = useState(null);
  const [data, setData] = useState(null);

  const intl = useIntl();

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

  const handleNCEFormSubmit = () => {};

  return (
    <div>
      {notificationVisible === true ? <AlertDialog /> : ""}
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
              </Column>
              <Column lg={4}>
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
                />
              </Column>
              <Column lg={16}>
                <br></br>
              </Column>
              <Column lg={16}>
                <Button type="button" onClick={handleSubmit}>
                  <FormattedMessage id="label.button.search" />
                </Button>
              </Column>
              <br />
            </Grid>
            <br />
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
          <br></br>
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
                              onClick={() => {
                                setSelected(row.nceNumber);
                                console.log(row);
                              }}
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
                  {data.nceCategories.find(
                    (obj) => obj.id === data.nceCategory,
                  ).name ?? ""}
                </div>
              </Column>

               <Column lg={3} style={{ marginBottom: "20px" }}>
                <div style={{ marginBottom: "10px" }}>
                  <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                    <FormattedMessage id="nonconform.view.nce.type" />
                  </span>
                </div>
                <div style={{ marginBottom: "10px" }}>
                  {data.nceTypes.find(
                    (obj) => obj.id === data.nceType,
                  ).name ?? ""}
                </div>
              </Column>

              <Column lg={16}>
                <br></br>
              </Column>

              {/* <Column lg={8} md={4} sm={4}>
                <TextArea
                  labelText={
                    <FormattedMessage id="nonconform.view.preventive.description" />
                  }
                  value={formData.controlAction}
                  onChange={(e) => {
                    setFormData({
                      ...formData,
                      controlAction: e.target.value,
                    });
                  }}
                  rows={2}
                  id="text-area-3"
                  invalid={formData.controlAction?.length > 200}
                  invalidText={<FormattedMessage id="text.length.max" />}
                />
              </Column> */}

              {/* <Column lg={8} md={4} sm={4}>
                <TextArea
                  labelText={<FormattedMessage id="nonconform.view.comments" />}
                  value={formData.comments}
                  onChange={(e) => {
                    setFormData({
                      ...formData,
                      comments: e.target.value,
                    });
                  }}
                  rows={2}
                  id="text-area-2"
                  invalid={formData.comments?.length > 200}
                  invalidText={<FormattedMessage id="text.length.max" />}
                />
              </Column> */}
              <Column lg={16}>
                <br></br>
              </Column>

              <Column lg={16}>
                {/* {false && (
                  <div style={{ color: "#c62828", margin: 4 }}>
                    {formData.error}
                  </div>
                )} */}
                <Button type="button" onClick={() => handleNCEFormSubmit()}>
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
