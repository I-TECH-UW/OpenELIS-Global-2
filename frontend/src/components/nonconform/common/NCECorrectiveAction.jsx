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
  TableCell
} from "@carbon/react";
import { AlertDialog } from "../../common/CustomNotification";
import { useState } from "react";
import { FormattedMessage, useIntl } from "react-intl";
import { initialReportFormValues, selectOptions } from "./ViewNonConforming";
import { getFromOpenElisServer } from "../../utils/Utils";
import { headers } from "./ViewNonConforming";

export const NCECorrectiveAction = () => {
  const [reportFormValues, setReportFormValues] = useState(
    initialReportFormValues,
  );

  const [notificationVisible, setNotificationVisible] = useState(false);

  const [tData,setTData] = useState(null);

  const intl = useIntl();

  const handleSubmit = () => 
    {

      let other = reportFormValues.type==="labNumber" ? "nceNumber" : "labNumber";

    getFromOpenElisServer(`/rest/nonconformingcorrectiveaction?status=CAPA&${reportFormValues.type}=${reportFormValues.value}&uppressExternalSearch=false&${other}=undefined`,data=>{

      if (data.nceEventsSearchResults.length > 1){
        setTData(data);
      }
    
    })
  };

  const handleSubmit2 = () => {
    getFromOpenElisServer("/rest/NCECorrectiveAction?nceNumber=1714007832348",data=>{
      console.log("data from nce server");
    })
  }

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
       {
        tData && (
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

                      <TableCell key={row.key + "1"}>{row.nceNumber}</TableCell>

                      <TableCell key={row.key + "2"}>
                        {
                          tData.reportingUnits.find(
                            (obj) => parseInt(obj.id) === row.reportingUnitId,
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
        )
       }


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
              {data.results[0].nceNumber}
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
              {data.results[0].nameOfReporter}
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
          <Column lg={1}></Column>
          <Column lg={3} style={{ marginBottom: "20px" }}>
            <div style={{ marginBottom: "10px" }}>
              <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                <FormattedMessage id="sample.label.labnumber" />
              </span>
            </div>
            <div style={{ marginBottom: "10px" }}>
              {data.results[0].labOrderNumber}
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
            >{`${data.results[0].prescriberName}-${data.results[0].site}`}</div>
          </Column>
          <Column lg={3} style={{ marginBottom: "20px" }}>
            <div style={{ marginBottom: "10px" }}>
              <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                <FormattedMessage id="nonconform.view.event.description" />
              </span>
            </div>
            <div style={{ marginBottom: "10px" }}>
              {data.results[0].description ?? ""}
            </div>
          </Column>
          <Column lg={3} style={{ marginBottom: "20px" }}>
            <div style={{ marginBottom: "10px" }}>
              <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                <FormattedMessage id="nonconform.label.suspected.cause.nce" />
              </span>
            </div>
            <div style={{ marginBottom: "10px" }}>
              {data.results[0].suspectedCauses ?? ""}
            </div>
          </Column>
          <Column lg={3} style={{ marginBottom: "20px" }}>
            <div style={{ marginBottom: "10px" }}>
              <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                <FormattedMessage id="nonconform.label.proposed.action" />
              </span>
            </div>
            <div style={{ marginBottom: "10px" }}>
              {data.results[0].proposedAction ?? ""}
            </div>
          </Column>
          <Column lg={1}></Column>
          <Column lg={3} style={{ marginBottom: "20px" }}>
            <div style={{ marginBottom: "10px" }}>
              <span style={{ color: "#3366B3", fontWeight: "bold" }}>
                <FormattedMessage id="nonconform.severity.score" />
              </span>
            </div>
            <div style={{ marginBottom: "10px" }}>{formData.severityScore}</div>
          </Column>
          <pre>
            <p>
              {" "}
              <FormattedMessage id="nonconform.label.lowseverity" />{" "}
            </p>
            <p>
              {" "}
              <FormattedMessage id="nonconform.label.highseverity" />
            </p>
          </pre>
          <Column lg={13}></Column>

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
              {data?.nceCategories?.map((option) => (
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
              labelText={
                <FormattedMessage id="nonconform.view.severe.consequences" />
              }
              id="consequences"
              value={formData.consequences}
              onChange={(e) => {
                setFormData({
                  ...formData,
                  consequences: e.target.value,
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
              labelText={
                <FormattedMessage id="nonconform.view.nce.likely.occur" />
              }
              id="recurrence"
              value={formData.recurrence}
              onChange={(e) => {
                setFormData({
                  ...formData,
                  recurrence: e.target.value,
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
          <Column lg={8}>
            <Select
              labelText={
                <FormattedMessage id="nonconform.view.lab.component" />
              }
              id="labComponent"
              value={formData.labComponent}
              onChange={(e) => {
                setFormData({
                  ...formData,
                  labComponent: e.target.value,
                });
              }}
            >
              <SelectItem value="" text="" />
              {data.labComponentList.map((option) => (
                <SelectItem
                  key={option.id}
                  value={option.id}
                  text={option.value}
                />
              ))}
            </Select>
          </Column>

          <Column lg={8} md={4} sm={4}>
            <TextArea
              labelText={
                <FormattedMessage id="nonconform.view.corrective.action.description" />
              }
              value={formData.correctiveAction}
              onChange={(e) => {
                setFormData({
                  ...formData,
                  correctiveAction: e.target.value,
                });
              }}
              invalid={formData.correctiveAction?.length > 200}
              invalidText={<FormattedMessage id="text.length.max" />}
              rows={2}
              id="text-area-10"
            />
          </Column>

          <Column lg={8} md={4} sm={4}>
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
          </Column>

          <Column lg={8} md={4} sm={4}>
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
          </Column>
          <Column lg={16}>
            <br></br>
          </Column>

          <Column lg={16}>
            {false && (
              <div style={{ color: "#c62828", margin: 4 }}>
                {formData.error}
              </div>
            )}
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
