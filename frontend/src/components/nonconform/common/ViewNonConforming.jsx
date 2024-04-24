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
  UnorderedList,
  ListItem,
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

const initialReportFormValues = {
  type: undefined,
  value: "",
  error: undefined,
};

const selectOptions = [
  {
    value: "nceNumber",
    text: "NCE Number",
  },
  {
    text: "Lab Number",
    value: "labNumber",
  },
];

export const ViewNonConformingEvent = () => {
  const [reportFormValues, setReportFormValues] = useState(
    initialReportFormValues,
  );

  const [data, setData] = useState(null);

  const [formData, setFormData] = useState({
    nceCategory: undefined,
    nceType: undefined,
    severity: undefined,
    likelyToRecur: undefined,
  });

  const [nceTypes, setNceTypes] = useState([]);

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
        `/rest/viewNonConformEvents?${reportFormValues.type}=${reportFormValues.value}&nceNumber=&status=Pending`,
        (data) => {
          console.log("viewNonData", data);
          if (!data.res) {
            setReportFormValues({
              ...reportFormValues,
              error: `no.data.found`,
            });
          } else {
            setData(data);
            setNceTypes(data.nceTypes);
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

  useEffect(() => {
    if (data) {
      setNceTypes(
        data.nceTypes.filter((obj) => {
          let bol = Number(obj.categoryId) === Number(formData.nceCategory);
          return bol;
        }),
      );
    }

  }, [formData.nceCategory]);

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
        setSelectedSample(initialSelected);
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
    <>
      {notificationVisible === true ? <AlertDialog /> : ""}
      <Grid fullWidth={true}>
        <Column lg={16}>
          <h2>
            <FormattedMessage id={`nonconform.view.report`} />
          </h2>
        </Column>
        <Column lg={16} md={10} sm={8}>
          <Form>
            <Grid fullWidth={true}>
              <Column lg={4}>
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
      </Grid>

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
              {data.res[0].nceNumber}
            </div>
          </Column>
          <Column lg={3} style={{ marginBottom: "20px" }}>
            <div style={{ marginBottom: "10px" }}>
              <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                <FormattedMessage id="nonconform.view.event.date" />
              </span>
            </div>
            <div style={{ marginBottom: "10px" }}>{data.dateOfEvent}</div>
          </Column>

          <Column lg={3} style={{ marginBottom: "20px" }}>
            <div style={{ marginBottom: "10px" }}>
              <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                <FormattedMessage id="nonconform.view.reporting.person" />
              </span>
            </div>
            <div style={{ marginBottom: "10px" }}>
              {data.res[0].nameOfReporter}
            </div>
          </Column>

          <Column lg={3} style={{ marginBottom: "20px" }}>
            <div style={{ marginBottom: "10px" }}>
              <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                <FormattedMessage id="nonconform.label.reportingunit" />
              </span>
            </div>
            <div style={{ marginBottom: "10px" }}>
              {data.reportingUnits.find((obj) => obj.id == data.repoUnit).value}
            </div>
          </Column>
          <Column lg={3} style={{ marginBottom: "20px" }}>
            <div style={{ marginBottom: "10px" }}>
              <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                <FormattedMessage id="nonconform.view.specimen" />
              </span>
            </div>
            <div style={{ marginBottom: "10px" }}>
              {data.specimen[0].typeOfSample.description}
            </div>
          </Column>
          <Column lg={3} style={{ marginBottom: "20px" }}>
            <div style={{ marginBottom: "10px" }}>
              <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                <FormattedMessage id="sample.label.labnumber" />
              </span>
            </div>
            <div style={{ marginBottom: "10px" }}>
              {data.res[0].labOrderNumber}
            </div>
          </Column>
          <Column lg={3} style={{ marginBottom: "20px" }}>
            <div style={{ marginBottom: "10px" }}>
              <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                <FormattedMessage id="nonconform.label.prescibernamesite" />
              </span>
            </div>
            <div
              style={{ marginBottom: "10px" }}
            >{`${data.res[0].prescriberName}-${data.res[0].site}`}</div>
          </Column>
          <Column lg={3} style={{ marginBottom: "20px" }}>
            <div style={{ marginBottom: "10px" }}>
              <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                <FormattedMessage id="nonconform.view.event.description" />
              </span>
            </div>
            <div style={{ marginBottom: "10px" }}>
              {data.res[0].description ?? ""}
            </div>
          </Column>
          <Column lg={3} style={{ marginBottom: "20px" }}>
            <div style={{ marginBottom: "10px" }}>
              <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                <FormattedMessage id="nonconform.label.suspected.cause.nce" />
              </span>
            </div>
            <div style={{ marginBottom: "10px" }}>
              {data.res[0].suspectedCauses ?? ""}
            </div>
          </Column>
          <Column lg={3} style={{ marginBottom: "20px" }}>
            <div style={{ marginBottom: "10px" }}>
              <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                <FormattedMessage id="nonconform.label.proposed.action" />
              </span>
            </div>
            <div style={{ marginBottom: "10px" }}>
              {data.res[0].proposedAction ?? ""}
            </div>
          </Column>
          <Column lg={16}>
            <br></br>
          </Column>

          <Column lg={16}>
            <br></br>
          </Column>
          <Column lg={8}>
            <Select
              labelText={<FormattedMessage id="nonconform.view.nce.category" />}
              id="nceCategory"
              value={formData.nceCategory}
              onChange={(e) => {
                setFormData({
                  ...formData,
                  nceCategory: e.target.value,
                });
              }}
            >
              <SelectItem key={"emptyselect"} value={""} text={""} />
              {data.nceCat.map((option) => (
                <SelectItem
                  key={option.id}
                  value={option.id}
                  text={option.name}
                />
              ))}
            </Select>
          </Column>
          <Column lg={8}>
            <Select
              labelText={<FormattedMessage id="nonconform.view.nce.type" />}
              id="nceType"
              value={formData.nceType}
              onChange={(e) => {
                setFormData({
                  ...formData,
                  nceType: e.target.value,
                });

               
              }}
            >
              <SelectItem key={"emptyselect"} value={""} text={""} />
              {nceTypes.map((option) => (
                <SelectItem
                  key={option.id}
                  value={option.id}
                  text={option.name}
                />
              ))}
            </Select>
          </Column>

          <Column lg={16}>
            <br></br>
          </Column>

          <Column lg={8}>
            <Select
              labelText={<FormattedMessage id="nonconform.view.severe.consequences" />}
              id="severity"
              value={formData.severity}
              onChange={(e) => {
                setFormData({
                  ...formData,
                  severity: e.target.value,
                });
              }}
            >
              {data.severityConsequenceList.map((option) => (
                <SelectItem
                  key={option.id}
                  value={option.id}
                  text={option.value}
                />
              ))}
            </Select>
          </Column>
          <Column lg={8}>
            <Select
              labelText={<FormattedMessage id="nonconform.view.nce.likely.occur" />}
              id="likelyToRecur"
              value={formData.likelyToRecur}
              onChange={(e) => {
                setFormData({
                  ...formData,
                  likelyToRecur: e.target.value,
                });
              }}
            >
              {data.severityRecurs.map((option) => (
                <SelectItem
                  key={option.id}
                  value={option.id}
                  text={option.value}
                />
              ))}
            </Select>
          </Column>

          <Column lg={16}>
            <br></br>
          </Column>
          <Column lg={16}>
            {false && (
              <div style={{ color: "#c62828", margin: 4 }}>{nceForm.error}</div>
            )}
            <Button type="button" onClick={() => handleNCEFormSubmit()}>
              <FormattedMessage id="label.button.submit" />
            </Button>
          </Column>
        </Grid>
      )}
    </>
  );
};
