import { useContext, useState, useRef, useEffect } from "react";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import "../../Style.css";
import { getFromOpenElisServer } from "../../utils/Utils";
import {
  Form,
  Checkbox,
  Dropdown,
  Heading,
  Grid,
  Column,
  Section,
  Button,
  Select,
  SelectItem,
} from "@carbon/react";
import CustomLabNumberInput from "../../common/CustomLabNumberInput";
import config from "../../../config.json";
import CustomDatePicker from "../../common/CustomDatePicker";
import AutoComplete from "../../common/AutoComplete";
import { ConfigurationContext } from "../../layout/Layout";
import { Formik, Field } from "formik";
import PatientStatusReportFormValues from "../../formModel/innitialValues/PatientStatusReportFormValues";
import SearchPatientForm from "../../patient/SearchPatientForm";

import { encodeDate } from "../../utils/Utils";

function PatientStatusReport(props) {
  
  const [reportFormValues, setReportFormValues] = useState(
    PatientStatusReportFormValues,
  );
  const { configurationProperties } = useContext(ConfigurationContext);
  
  const intl = useIntl();
  const itemList = [
    {
      id: "option-0",
      text: "Result Date",
      tag: "RESULT_DATE",
    },
    {
      id: "option-1",
      text: "Order Date",
      tag: "ORDER_DATE",
    },
  ];

  const componentMounted = useRef(false);
  const [checkbox, setCheckbox] = useState("on");
  const [result, setResult] = useState("false");
  const [items, setItems] = useState(itemList[0].tag);
  const [siteNames, setSiteNames] = useState([]);
  const [departments, setDepartments] = useState([]);


  const getSelectedPatient = (patient) => {
    setReportFormValues({
      ...reportFormValues,
      selectedPatientId: patient.patientPK,
    });
    
  };

  const handleReportPrint = () => {
    let barcodesPdf =
      config.serverBaseUrl +
      `/ReportPrint?report=${props.report}&type=patient&accessionDirect=${reportFormValues.form}&highAccessionDirect=${reportFormValues.to}&dateOfBirthSearchValue=&selPatient=${reportFormValues.selectedPatientId}&referringSiteId=${reportFormValues.referringSiteId}&referringSiteDepartmentId=${reportFormValues.referringSiteName}&onlyResults=${result}&_onlyResults=${checkbox}&dateType=${items}&lowerDateRange=${reportFormValues.startDate}&upperDateRange=${reportFormValues.endDate}`;
    window.open(barcodesPdf);
  };

 

  function handlePatientIdFrom(e) {
    setReportFormValues({
      ...reportFormValues,
      form: e.target.value,
    });
  }

  function handlePatientIdTo(e) {
    setReportFormValues({
      ...reportFormValues,
      to: e.target.value,
    });
  }


  function handleSiteName(e) {
    setReportFormValues({
      ...reportFormValues,
      referringSiteName: e.target.value,
    });
  }

  function handleRequesterDept(e) {
    setReportFormValues({
      ...reportFormValues,
      referringSiteDepartmentId: e.target.value,
    });
  }

  function handleAutoCompleteSiteName(siteId) {
    setReportFormValues({
      ...reportFormValues,
      referringSiteId: siteId,
      referringSiteName: "",
    });
  }
  const loadDepartments = (data) => {
    setDepartments(data);
  };



  const handleStartDatePickerChangeDate = (date) => {
    let startDate = encodeDate(date);
    setReportFormValues({
      ...reportFormValues,
      startDate: startDate,
      })
  };

  const handleEndDatePickerChangeDate = (date) => {
    let endDate = encodeDate(date);  
    setReportFormValues({
      ...reportFormValues,
      endDate : endDate,
      })
  };



  const getSiteList = (response) => {
    if (componentMounted.current) {
      setSiteNames(response);
    }
  };


  useEffect(() => {
    getFromOpenElisServer(
      "/rest/departments-for-site?refferingSiteId=" +
        (reportFormValues.referringSiteId || ""),
      loadDepartments,
    );
  }, [reportFormValues.referringSiteId]);

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/site-names", getSiteList);
    window.scrollTo(0, 0);
    return () => {
      componentMounted.current = false;
    };
  }, []);

 


  const breadcrumbs = [
    { label: "home.label", link: "/" },
    { label: "routine.reports", link: "/RoutineReports" },
    {
      label: "openreports.patientTestStatus",
      link: "/RoutineReport?type=patient&report=patientCILNSP_vreduit",
    },
  ];

  return (
    <>
      <Grid fullWidth={true}>
        <Column lg={16} md={8} sm={4}>
          <Section>
            <Section>
              <Heading>
                <FormattedMessage id={props.id}/>
              </Heading>
            </Section>
          </Section>
        </Column>
      </Grid>
      <Grid fullWidth={true}>
          <Column lg={16} md={8} sm={4}>
            <Section>
              <h5>
                <FormattedMessage id="report.enter.patient.headline" />
              </h5>
              <br />
              <h6>
                <FormattedMessage id="report.enter.patient.headline.description" />
              </h6>
            </Section>
          </Column>
          <Column lg={16} md={8} sm={4}>
            <SearchPatientForm
              getSelectedPatient={getSelectedPatient}
            >
            </SearchPatientForm>
          </Column>
        </Grid>
      <Formik
        initialValues={reportFormValues}
        enableReinitialize={true}
        // validationSchema={}
        onSubmit
        onChange
      >
        {({
          values,
          //errors,
          //touched,
          setFieldValue,
          handleChange,
          handleBlur,
          handleSubmit,
        }) => (
          <Form
            onSubmit={handleSubmit}
            onChange={handleChange}
            onBlur={handleBlur}
          >
            <Field name="guid">
              {({ field }) => (
                <input type="hidden" name={field.name} id={field.name} />
              )}
            </Field>
            <Grid fullWidth={true}>
              <Column lg={16} md={8} sm={4}>
                <Section>
                  <br />
                  <br />
                  <h5>
                    <FormattedMessage id="report.enter.labNumber.headline" />
                  </h5>
                  <br />
                  <h6>
                    <FormattedMessage id="sample.search.scanner.instructions" />
                    <br />
                    <FormattedMessage id="sample.search.scanner.instructions.highaccession" />
                  </h6>
                </Section>
              </Column>
            </Grid>
            <Grid>
            <Column lg={8} md={8} sm={4}>
              <Field name="from">
                {({ field }) => (
                  <CustomLabNumberInput
                    name={field.name}
                    value={values[field.name]}
                    labelText={intl.formatMessage({
                      id: "from.title",
                      defaultMessage: "From",
                    })}
                    id={field.name}
                    className="inputText"
                    onChange={(e, rawValue) => {
                      setFieldValue(field.name, rawValue);
                      handlePatientIdFrom(e);
                    }}
                  />
                )}
              </Field>
              </Column>
              <Column lg={8} md={8} sm={4}>
              <Field name="to">
                {({ field }) => (
                  <CustomLabNumberInput
                    name={field.name}
                    value={values[field.name]}
                    labelText={intl.formatMessage({
                      id: "to.title",
                      defaultMessage: "To",
                    })}
                    id={field.name}
                    className="inputText"
                    onChange={(e, rawValue) => {
                      setFieldValue(field.name, rawValue);
                      handlePatientIdTo(e);
                    }}
                  />
                )}
              </Field>
              </Column>
            </Grid>
            
     
            <br />
            <Grid fullWidth={true}>
              <Column lg={16} md={8} sm={4}>
                <h5>
                  <FormattedMessage id="report.enter.site.headline" />
                </h5>
              </Column>
            </Grid>
            <Grid>
            <Column lg={8} md={8} sm={4}>
              <AutoComplete
                name="siteName"
                id="siteName"
                allowFreeText={
                  !(
                    configurationProperties.restrictFreeTextRefSiteEntry ===
                    "true"
                  )
                }
                value={
                  reportFormValues.referringSiteId != ""
                    ? reportFormValues.referringSiteId
                    : reportFormValues.referringSiteName
                }
                onChange={handleSiteName}
                onSelect={handleAutoCompleteSiteName}
                label={
                  <>
                    <FormattedMessage id="order.site.name" />
                  </>
                }
                class="inputText"
                style={{ width: "!important 100%" }}
                suggestions={siteNames.length > 0 ? siteNames : []}
              />
              </Column>
             <Column lg={8} md={8} sm={4}>
              <Select
                className="inputText"
                id="requesterDepartmentId"
                name="requesterDepartmentId"
                labelText={intl.formatMessage({
                  id: "order.department.label",
                  defaultMessage: "ward/dept/unit",
                })}
                onChange={handleRequesterDept}
              >
                <SelectItem value="" text="" />
                {departments.map((department, index) => (
                  <SelectItem
                    key={index}
                    text={department.value}
                    value={department.id}
                  />
                ))}
              </Select>
              </Column>
            </Grid>
            <Grid fullWidth={true}>
              <Column lg={16} md={8} sm={4}>
                <h6>
                  <FormattedMessage id="report.patient.site.description" />
                </h6>
              </Column>
            </Grid>
            <br />
            <Grid fullWidth={true}>
              <Column lg={4} md={8} sm={4}>
                <Checkbox
                  onChange={() => {
                    if (checkbox === "on") {
                      setResult("true");
                      setCheckbox("off");
                    } else {
                      setResult("false");
                      setCheckbox("on");
                    }
                  }}
                  labelText={intl.formatMessage({
                    id: "report.label.site.onlyResults",
                    defaultMessage: "Only Reports with results",
                  })}
                  id="checkbox-label-1"
                />
               </Column>
               <Column lg={12} md={8} sm={4}></Column>
               <Column lg={4} md={8} sm={4}> 
                  <Dropdown
                    id="dateType"
                    name="dateType"
                    titleText="Date Type"
                    initialSelectedItem={itemList.find(
                      (item) => item.tag === items,
                    )}
                    label="Date Type"
                    items={itemList}
                    itemToString={(item) => (item ? item.text : "")}
                    onChange={(item) => {
                      setItems(item.selectedItem.tag);
                    }}
                  />
                </Column>
                <Column lg={12} md={8} sm={4}></Column>
                <Column lg={4} md={8} sm={4}> 
                  <CustomDatePicker
                    id={"startDate"}
                    labelText={intl.formatMessage({
                      id: "eorder.date.start",
                      defaultMessage: "Start Date",
                    })}
                    autofillDate={true}
                    value={reportFormValues.startDate}
                    className="inputDate"
                    onChange={(date) =>
                      handleStartDatePickerChangeDate(date)
                    }
                  />
                  </Column>
                  <Column lg={4} md={8} sm={4}>
                  <CustomDatePicker
                    id={"endDate"}
                    labelText={intl.formatMessage({
                      id: "eorder.date.end",
                      defaultMessage: "End Date",
                    })}
                    className="inputDate"
                    autofillDate={true}
                    value={reportFormValues.endDate}
                    onChange={(date) =>
                      handleEndDatePickerChangeDate(date)
                    }
                  />
              </Column>
              <Column lg={8} md={8} sm={4}> </Column>
            </Grid>
            <div className="formInlineDiv">
              <div className="searchActionButtons">
                <Button type="button" onClick={handleReportPrint}>
                  <FormattedMessage id="label.button.generatePrintableVersion" />
                </Button>
              </div>
            </div>
          </Form>
        )}
      </Formik>
    </>
  );
}

export default injectIntl(PatientStatusReport);
