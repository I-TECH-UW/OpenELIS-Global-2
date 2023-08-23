import { useContext, useState, useEffect, useRef } from "react";
import { useParams } from "react-router-dom";
import config from "../../config.json";
import {
  IconButton, Heading, TextInput, Select, FilterableMultiSelect, SelectItem, Button, Grid, Column,
  Checkbox, Section, FileUploader, Tag, TextArea, Breadcrumb, BreadcrumbItem, Loading, RadioButtonGroup, RadioButton
} from '@carbon/react';
import { Launch, Subtract } from '@carbon/react/icons';
import { getFromOpenElisServer, postToOpenElisServerFullResponse, hasRole } from "../utils/Utils";
import UserSessionDetailsContext from "../../UserSessionDetailsContext"
import { NotificationContext } from "../layout/Layout";
import { AlertDialog, NotificationKinds } from "../common/CustomNotification";
import "../pathology/PathologyDashboard.css"


export const QuestionnaireResponse = ({ questionnaireResponse }) => {

  const renderQuestionResponse = (item) => {
    console.log(JSON.stringify(item))
    return <>
      <div className="questionnaireResponseItem">
        {item.text}:
        {item.answer && item.answer.map((answer, index) => {
          return <span key={index}>{renderAnswer(answer)}</span>
        })}
      </div>
    </>
  }

  const renderAnswer = (answer) => {
    console.log(JSON.stringify(answer))

    var display = '';
    if ('valueString' in answer) {
      display = answer.valueString
    } else if ('valueBoolean' in answer) {
      display = answer.valueBoolean
    } else if ('valueCoding' in answer) {
      display = answer.valueCoding.display
    } else if ('valueDate' in answer) {
      display = answer.valueDate
    } else if ('valueDecimal' in answer) {
      display = answer.valueDecimal
    } else if ('valueInteger' in answer) {
      display = answer.valueInteger
    } else if ('valueQuantity' in answer) {
      display = answer.valueQuantity.value + answer.valueQuantity.unit
    } else if ('valueTime' in answer) {
      display = answer.valueTime
    }
    return <>
      <span className="questionnaireResponseAnswer">
        {display}</span>
    </>
  }

  return <>
    {questionnaireResponse && questionnaireResponse.item.map((item, index) => {
      return <span key={index}>{renderQuestionResponse(item)}</span>
    })}

  </>

}

function CytologyCaseView() {

  const componentMounted = useRef(false);

  const { cytologySampleId } = useParams()

  const { notificationVisible, setNotificationVisible, setNotificationBody } = useContext(NotificationContext);
  const { userSessionDetails, setUserSessionDetails } = useContext(UserSessionDetailsContext);

  const [pathologySampleInfo, setPathologySampleInfo] = useState({});

  const [initialMount, setInitialMount] = useState(false);

  const [statuses, setStatuses] = useState([]);
  const [satisfactoryForEvaluation, setSatisfactoryForEvaluation] = useState([]);
  const [unSatisfactoryForEvaluation, setUnSatisfactoryForEvaluation] = useState([]);
  const [adequacySatisfactionList, setAdequacySatisfactionList] = useState([]);
  const [diagnosisResultEpithelialCellSquamous, setDiagnosisResultEpithelialCellSquamous] = useState([]);
  const [diagnosisResultEpithelialCellGlandular, setDiagnosisResultEpithelialCellGlandular] = useState([]);
  const [diagnosisResultNonNeoPlasticCellular, setDiagnosisResultNonNeoPlasticCellular] = useState([]);
  const [diagnosisResultReactiveCellular, setDiagnosisResultReactiveCellular] = useState([]);
  const [diagnosisResultOrganisms, setDiagnosisResultOrganisms] = useState([]);
  const [diagnosisResultOther, setDiagnosisResultOther] = useState([]);
  const [combinedDiagnoses, setcombinedDiagnoses] = useState(true);
  const [technicianUsers, setTechnicianUsers] = useState([]);
  const [pathologistUsers, setPathologistUsers] = useState([]);
  const [loading, setLoading] = useState(true);


  async function displayStatus(response) {
    var body = await response.json();
    console.log(body)
    var status = response.status;
    setNotificationVisible(true);
    if (status == "200") {
      const save1 = document.getElementById("pathology_save");
      const save2 = document.getElementById("pathology_save2");
      save1.disabled = true;
      save2.disabled = true;
      setNotificationBody({ kind: NotificationKinds.success, title: "Notification Message", message: "Succesfuly saved" });
    } else {
      setNotificationBody({ kind: NotificationKinds.error, title: "Notification Message", message: "Error while saving" });
    }
  }

  const toBase64 = file => new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = reject;
  });

  const save = (e) => {
    let specimenAdequacy = null;
    if (pathologySampleInfo.specimenAdequacy) {
      specimenAdequacy = pathologySampleInfo.specimenAdequacy
      if (pathologySampleInfo.specimenAdequacy.values) {
        specimenAdequacy = {
          ...specimenAdequacy, values: pathologySampleInfo.specimenAdequacy.values.map(e => {
            return e.id;
          })
        }
      }

    }

    let diagnosis = null;
    if (pathologySampleInfo.diagnosis) {
      diagnosis = pathologySampleInfo.diagnosis
      if (!pathologySampleInfo.diagnosis.negativeDiagnosis && pathologySampleInfo.diagnosis.diagnosisResultsMaps) {
        var newDiagnosisResultsMaps = []
        pathologySampleInfo.diagnosis.diagnosisResultsMaps.forEach(resultMap => {
          var newResultMap = resultMap;
          var results = filterDiagnosisResultsByCategory(resultMap.category).results.map(e => {
            return e.id;
          })
          newResultMap.results = results;
          newDiagnosisResultsMaps.push(newResultMap)
        });
        diagnosis = {
          ...diagnosis, diagnosisResultsMaps: newDiagnosisResultsMaps
        }
      }
    }

    let submitValues = {
      "assignedTechnicianId": pathologySampleInfo.assignedTechnicianId,
      "assignedCytoPathologistId": pathologySampleInfo.assignedPathologistId,
      "status": pathologySampleInfo.status,
      "slides": pathologySampleInfo.slides,
      "release": pathologySampleInfo.release != undefined ? pathologySampleInfo.release : false,
    }

    if (specimenAdequacy) {
      submitValues = {
        ...submitValues, "specimenAdequacy": specimenAdequacy
      }
    }

    if (diagnosis) {
      submitValues = {
        ...submitValues, "diagnosis": diagnosis
      }
    }

    console.log(" ..submit....")
    console.log(JSON.stringify(submitValues))
    postToOpenElisServerFullResponse("/rest/cytology/caseView/" + cytologySampleId, JSON.stringify(submitValues), displayStatus);
  }
  const filterDiagnosisResultsByCategory = (category) => {
    return pathologySampleInfo.diagnosis?.diagnosisResultsMaps?.find(r => r.category === category)
  }

  const setInitialPathologySampleInfo = (e) => {
    if (hasRole(userSessionDetails, "CytoPathologist") && !e.assignedPathologistId && e.status === "READY_FOR_CYTOPATHOLOGIST") {
      e.assignedPathologistId = userSessionDetails.userId
      e.assignedPathologist = userSessionDetails.lastName + " " + userSessionDetails.firstName
    }
    if (!e.assignedTechnicianId) {
      e.assignedTechnicianId = userSessionDetails.userId
      e.assignedTechnician = userSessionDetails.lastName + " " + userSessionDetails.firstName
    }
    setPathologySampleInfo(e);
    setLoading(false);
    setInitialMount(true);
  }

  const combineDiagnoses = () => {
    if (diagnosisResultEpithelialCellGlandular && diagnosisResultEpithelialCellSquamous) {
      var diagnoses = diagnosisResultEpithelialCellGlandular.concat(diagnosisResultEpithelialCellSquamous);
      setcombinedDiagnoses(diagnoses);
    }
  }

  useEffect(() => {
    combineDiagnoses();
  }, [diagnosisResultEpithelialCellGlandular, diagnosisResultEpithelialCellSquamous]);


  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/displayList/CYTOLOGY_STATUS", setStatuses);
    getFromOpenElisServer("/rest/displayList/CYTOLOGY_SATISFACTORY_FOR_EVALUATION", setSatisfactoryForEvaluation);
    getFromOpenElisServer("/rest/displayList/CYTOLOGY_UN_SATISFACTORY_FOR_EVALUATION", setUnSatisfactoryForEvaluation);
    getFromOpenElisServer("/rest/displayList/CYTOLOGY_SPECIMEN_ADEQUACY_SATISFACTION", setAdequacySatisfactionList);
    getFromOpenElisServer("/rest/displayList/CYTOLOGY_DIAGNOSIS_RESULT_EPITHELIAL_CELL_SQUAMOUS", setDiagnosisResultEpithelialCellSquamous);
    getFromOpenElisServer("/rest/displayList/CYTOLOGY_DIAGNOSIS_RESULT_EPITHELIAL_CELL_GLANDULAR", setDiagnosisResultEpithelialCellGlandular);
    getFromOpenElisServer("/rest/displayList/CYTOLOGY_DIAGNOSIS_RESULT_NON_NEO_PLASTIC_CELLULAR", setDiagnosisResultNonNeoPlasticCellular);
    getFromOpenElisServer("/rest/displayList/CYTOLOGY_DIAGNOSIS_RESULT_REACTIVE_CELLULAR", setDiagnosisResultReactiveCellular);
    getFromOpenElisServer("/rest/displayList/CYTOLOGY_DIAGNOSIS_RESULT_ORGANISMS", setDiagnosisResultOrganisms);
    getFromOpenElisServer("/rest/displayList/CYTOLOGY_DIAGNOSIS_RESULT_OTHER", setDiagnosisResultOther);
    //TODO make conclusions list instead of reusing pathrequest
    getFromOpenElisServer("/rest/users", setTechnicianUsers);
    getFromOpenElisServer("/rest/users/Cytopathologist", setPathologistUsers);
    getFromOpenElisServer("/rest/cytology/caseView/" + cytologySampleId, setInitialPathologySampleInfo);

    return () => {
      componentMounted.current = false
    }
  }, []);

  return (
    <>
      <Breadcrumb>
        <BreadcrumbItem href="/">Home</BreadcrumbItem>
        <BreadcrumbItem href="/CytologyDashboard">Cytology DashBoard</BreadcrumbItem>
      </Breadcrumb>

      <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Section >
              <Heading >
                Cytology
                {/* <FormattedMessage id="label.page.patientHistory" /> */}
              </Heading>
            </Section>
          </Section>
        </Column>
      </Grid>
      <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Section>
              {pathologySampleInfo ? (<div className="patient-header">
                <div className="patient-name"><Tag type="blue">Name :</Tag>{pathologySampleInfo.lastName}  {pathologySampleInfo.firstName}</div>
                <div className="patient-dob"> <Tag type="blue">Sex :</Tag>{pathologySampleInfo.sex === 'M' ? "Male" : "Female"}<Tag type="blue">Age :</Tag>{pathologySampleInfo.age} </div>
                <div className="patient-id"><Tag type="blue">Order Date :</Tag>{pathologySampleInfo.requestDate}</div>
                <div className="patient-id"><Tag type="blue">Lab Number :</Tag>{pathologySampleInfo.labNumber}</div>
                <div className="patient-id"><Tag type="blue">Referring Facility:</Tag> {pathologySampleInfo.referringFacility}<Tag type="blue">Ward/Dept/Unit: </Tag>{pathologySampleInfo.department}</div>
                <div className="patient-id"><Tag type="blue">Requester: </Tag>{pathologySampleInfo.requester}</div>
              </div>) : (<div className="patient-header">
                <div className="patient-name">Patient Id Doest Exist</div>
              </div>)}
            </Section>
          </Section>
          <Section>
            <Section>
              <div className="patient-header">
                <QuestionnaireResponse questionnaireResponse={pathologySampleInfo.programQuestionnaireResponse} />
              </div>
            </Section>
          </Section>
        </Column>
      </Grid>
      <Grid fullWidth={true} className="gridBoundary">
        {notificationVisible === true ? <AlertDialog /> : ""}
        {loading && (
          <Loading description="Loading Dasboard..." />
        )}
        <Column lg={16} md={8} sm={4}>
          <Button id="pathology_save" onClick={(e) => { e.preventDefault(); save(e) }}>Save</Button>
        </Column>
        <Column lg={16} md={8} sm={4}>
          <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>
        </Column>
        <Column lg={4} md={2} sm={2} >
          <Select id="status"
            name="status"
            labelText="Status"
            value={pathologySampleInfo.status}
            onChange={(event) => {
              setPathologySampleInfo({ ...pathologySampleInfo, status: event.target.value });
            }}>
            <SelectItem disabled value="placeholder" text="Status" />

            {statuses.map((status, index) => {
              return (<SelectItem key={index}
                text={status.value}
                value={status.id}
              />);
            })}
          </Select>
        </Column>
        <Column lg={2} md={1} sm={2} >
        </Column>
        <Column lg={2} md={1} sm={2} >

          <Select id="assignedTechnician"
            name="assignedTechnician"
            labelText="Technician Assigned"
            value={pathologySampleInfo.assignedTechnicianId}
            onChange={(event) => {
              setPathologySampleInfo({ ...pathologySampleInfo, assignedTechnicianId: event.target.value });
            }}>
            <SelectItem />
            {technicianUsers.map((user, index) => {
              return (<SelectItem key={index}
                text={user.value}
                value={user.id}
              />);
            })}
          </Select>
        </Column>
        <Column lg={2} md={4} sm={2} />
        <Column lg={4} md={2} sm={2}>
          <Select id="assignedPathologist"
            name="assignedPathologist"
            labelText="Pathologist Assigned"
            value={pathologySampleInfo.assignedPathologistId}
            onChange={e => {
              setPathologySampleInfo({ ...pathologySampleInfo, assignedPathologistId: e.target.value });
            }}>
            <SelectItem />
            {pathologistUsers.map((user, index) => {
              return (<SelectItem key={index}
                text={user.value}
                value={user.id}
              />);
            })}
          </Select>
        </Column>
        <Column lg={16} md={8} sm={4}>
        </Column >

        <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>
        <Column lg={16} md={8} sm={4}>
          <hr style={{ width: '100%', margin: '1rem 0', border: '1px solid #ccc' }} />
          <h5>Slides</h5>
          <hr style={{ width: '100%', margin: '1rem 0', border: '1px solid #ccc' }} />
        </Column>
        {pathologySampleInfo.slides && pathologySampleInfo.slides.map((slide, index) => {
          return (
            <>
              <Column lg={2} md={8} sm={4}>
                <IconButton label="remove slide" onClick={() => {
                  var info = { ...pathologySampleInfo };
                  info["slides"].splice(index, 1);
                  setPathologySampleInfo(info);
                }} kind='tertiary' size='sm'>
                  <Subtract size={18} />  Slide
                </IconButton>

              </Column>
              <Column lg={3} md={2} sm={1} key={index}>
                <TextInput
                  id="slideNumber"
                  labelText="slide number"
                  hideLabel={true}
                  placeholder="Slide Number"
                  value={slide.slideNumber}
                  type="number"
                  onChange={e => {
                    var newSlides = [...pathologySampleInfo.slides];
                    newSlides[index].slideNumber = e.target.value;
                    setPathologySampleInfo({ ...pathologySampleInfo, slides: newSlides });
                  }}
                />
              </Column>
              <Column lg={3} md={2} sm={1}>
                <TextInput
                  id="location"
                  labelText="location"
                  hideLabel={true}
                  placeholder="Location"
                  value={slide.location}
                  onChange={e => {
                    var newSlides = [...pathologySampleInfo.slides];
                    newSlides[index].location = e.target.value;
                    setPathologySampleInfo({ ...pathologySampleInfo, slides: newSlides });
                  }}
                />
              </Column>
              <Column lg={3} md={1} sm={2}>
                <FileUploader
                  style={{ marginTop: '-10px' }}
                  buttonLabel="Upload File"
                  iconDescription="file upload"
                  multiple={false}
                  accept={['image/jpeg', 'image/png', 'application/pdf']}
                  disabled={false}
                  name=""
                  buttonKind="primary"
                  size="lg"
                  filenameStatus="edit"
                  onChange={async (e) => {
                    e.preventDefault();
                    let file = e.target.files[0];
                    var newSlides = [...pathologySampleInfo.slides];
                    let encodedFile = await toBase64(file);
                    newSlides[index].base64Image = encodedFile;
                    setPathologySampleInfo({ ...pathologySampleInfo, slides: newSlides });
                  }}
                  onClick={function noRefCheck() { }}
                  onDelete={(e) => {
                    e.preventDefault();
                    var newSlides = [...pathologySampleInfo.slides];
                    newSlides[index].base64Image = '';
                    setPathologySampleInfo({ ...pathologySampleInfo, slides: newSlides });
                  }}
                />
              </Column>
              <Column lg={2} md={1} sm={2}>
                {pathologySampleInfo.slides[index].image &&
                  <>
                    <Button onClick={() => {
                      var win = window.open();
                      win.document.write('<iframe src="' + slide.fileType + ";base64," + slide.image + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
                    }}>
                      <Launch /> View
                    </Button>
                  </>
                }
              </Column>
              <Column lg={2} md={1} sm={2}>
                <Button onClick={(e) => {
                  window.open(config.serverBaseUrl + '/LabelMakerServlet?labelType=slide&code=' + slide.slideNumber, '_blank')
                }}>Print Label</Button>
              </Column>

            </>
          )
        })}

        <Column lg={16} md={8} sm={4}>
          <Button onClick={() => {
            setPathologySampleInfo({ ...pathologySampleInfo, slides: [...(pathologySampleInfo.slides || []), { id: '', slideNumber: '' }] });
          }}>
            Add Slide
          </Button>
          <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>
          <Column lg={16} md={8} sm={4}>
            <hr style={{ width: '100%', margin: '1rem 0', border: '1px solid #ccc' }} />
          </Column>
          <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>
        </Column>

        <Column lg={12} md={6} sm={0}>
        </Column>
        <Column lg={16} md={8} sm={4}></Column>
        {hasRole(userSessionDetails, "Cytopathologist") && initialMount &&
          <>
            <Column lg={4} md={1} sm={2} >
              <Select id="specimenAdequacy"
                name="specimenAdequacy"
                labelText="Specimen Adequacy"
                value={pathologySampleInfo.specimenAdequacy?.satisfaction}
                onChange={(event) => {
                  var specimenAdequacy = { ...pathologySampleInfo.specimenAdequacy };
                  specimenAdequacy.satisfaction = event.target.value
                  specimenAdequacy.resultType = "DICTIONARY"
                  specimenAdequacy.values = []
                  setPathologySampleInfo({ ...pathologySampleInfo, specimenAdequacy: specimenAdequacy });
                }}>
                <SelectItem />
                {adequacySatisfactionList.map((user, index) => {
                  return (<SelectItem key={index}
                    text={user.value}
                    value={user.id}
                  />);
                })}
              </Select>

            </Column>
            {pathologySampleInfo.specimenAdequacy && pathologySampleInfo.specimenAdequacy.satisfaction === 'UN_SATISFACTORY_FOR_EVALUATION' && (
              <>
                <Column lg={4} md={4} sm={2}>
                  {initialMount && <FilterableMultiSelect
                    id="adequacy"
                    titleText="Specimen Adequacy"
                    items={unSatisfactoryForEvaluation}
                    itemToString={(item) => (item ? item.value : '')}
                    initialSelectedItems={pathologySampleInfo.specimenAdequacy?.values}
                    onChange={(changes) => {
                      var specimenAdequacy = { ...pathologySampleInfo.specimenAdequacy };
                      specimenAdequacy.values = changes.selectedItems
                      setPathologySampleInfo({ ...pathologySampleInfo, specimenAdequacy: specimenAdequacy });
                    }}
                    selectionFeedback="top-after-reopen"
                  />
                  }
                </Column>
                <Column lg={8} md={4} sm={2}>
                  {pathologySampleInfo.specimenAdequacy && pathologySampleInfo.specimenAdequacy.values.map((adequacy, index) => (
                    <Tag
                      key={index}
                      onClose={() => { }}
                    >
                      {adequacy.value}
                    </Tag>
                  ))}
                </Column>
              </>
            )}
            {pathologySampleInfo.specimenAdequacy?.satisfaction === 'SATISFACTORY_FOR_EVALUATION' && (
              <Column lg={8}>
                <RadioButtonGroup
                  valueSelected={pathologySampleInfo.specimenAdequacy?.values[0]?.id}
                  legendText={"Select Adequacy"}
                  name="adequacy"
                  id="adequacy"
                  onChange={(value) => {
                    var specimenAdequacy = { ...pathologySampleInfo.specimenAdequacy };
                    specimenAdequacy.values = [{ "id": value }]
                    setPathologySampleInfo({ ...pathologySampleInfo, specimenAdequacy: specimenAdequacy });
                  }}
                >
                  {satisfactoryForEvaluation.map((adequacy, index) => (
                    <RadioButton
                      index={index}
                      id={"adquacy" + index}
                      labelText={adequacy.value}
                      value={adequacy.id}
                    />
                  ))}
                </RadioButtonGroup>
              </Column>
            )}

            <Column lg={16} md={8} sm={4}>
              <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>
            </Column>

            <Column lg={16} md={8} sm={4}>
              <Checkbox
                checked={pathologySampleInfo.diagnosis ? pathologySampleInfo.diagnosis.negativeDiagnosis : true}
                labelText="NEGATIVE FOR INTRAEPITHELIAL LESION OR MALIGNANCY"
                id="checked"
                onChange={(e) => {
                  var diagnosis = { ...pathologySampleInfo.diagnosis };
                  diagnosis.negativeDiagnosis = e.target.checked
                  diagnosis.diagnosisResultsMaps = []
                  setPathologySampleInfo({ ...pathologySampleInfo, diagnosis: diagnosis });
                }}
              />
            </Column>
            {pathologySampleInfo.diagnosis && !pathologySampleInfo.diagnosis.negativeDiagnosis &&
              <>
                <Column lg={16} md={8} sm={4}>
                  <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>
                  Epithelial Cell Abnomality
                </Column>
                <Column lg={4} md={8} sm={4}>
                  <FilterableMultiSelect
                    id="cellAbnomality"
                    titleText="Select Result"
                    items={combinedDiagnoses}
                    itemToString={(item) => (item ? item.value : '')}
                    initialSelectedItems={filterDiagnosisResultsByCategory("EPITHELIAL_CELL_ABNORMALITY")?.results}
                    onChange={(changes) => {

                      var diagnosis = { ...pathologySampleInfo.diagnosis }
                      var diagnosisResultsMaps = diagnosis.diagnosisResultsMaps;
                      var filteredMapIndex = diagnosisResultsMaps?.findIndex(r => r.category === "EPITHELIAL_CELL_ABNORMALITY");
                      var diagnosisResultMap = {};
                      var newDiagnosisResultMaps = []
                      diagnosisResultMap.category = "EPITHELIAL_CELL_ABNORMALITY";
                      diagnosisResultMap.resultType = "DICTIONARY";
                      diagnosisResultMap.results = changes.selectedItems

                      if (filteredMapIndex != -1) {
                        diagnosisResultsMaps[filteredMapIndex] = diagnosisResultMap;
                        newDiagnosisResultMaps = diagnosisResultsMaps;
                      } else {
                        if (!diagnosisResultsMaps) {
                          diagnosisResultsMaps = []
                        }
                        newDiagnosisResultMaps = [...diagnosisResultsMaps, diagnosisResultMap]
                      }
                      diagnosis.diagnosisResultsMaps = newDiagnosisResultMaps
                      setPathologySampleInfo({ ...pathologySampleInfo, diagnosis: diagnosis });

                    }}
                    selectionFeedback="top-after-reopen"
                  />
                </Column>
                {diagnosisResultEpithelialCellSquamous && pathologySampleInfo &&
                  <Column lg={6} md={4} sm={2}>
                    Squamous :
                    {
                      filterDiagnosisResultsByCategory("EPITHELIAL_CELL_ABNORMALITY")?.results.filter(result => diagnosisResultEpithelialCellSquamous?.some(item => item.id == result.id))?.map((result, index) => (
                        <Tag
                          key={index}
                          onClose={() => { }}
                        >
                          {result.value}
                        </Tag>
                      ))}
                  </Column>
                }

                {diagnosisResultEpithelialCellGlandular && pathologySampleInfo &&
                  <Column lg={6} md={4} sm={2}>
                    Glandular :
                    {
                      filterDiagnosisResultsByCategory("EPITHELIAL_CELL_ABNORMALITY")?.results.filter(result => diagnosisResultEpithelialCellGlandular?.some(item => item.id == result.id))?.map((result, index) => (
                        <Tag
                          key={index}
                          onClose={() => { }}
                        >
                          {result.value}
                        </Tag>
                      ))}

                  </Column>
                }

                <Column lg={16} md={8} sm={4}>
                  <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>
                  Non-neoplastic cellular variations
                </Column>
                <Column lg={4} md={8} sm={4}>
                  <FilterableMultiSelect
                    id="nonNeoPlastic"
                    titleText="Select Result"
                    items={diagnosisResultNonNeoPlasticCellular}
                    itemToString={(item) => (item ? item.value : '')}
                    initialSelectedItems={filterDiagnosisResultsByCategory("NON_NEOPLASTIC_CELLULAR_VARIATIONS")?.results}
                    onChange={(changes) => {

                      var diagnosis = { ...pathologySampleInfo.diagnosis }
                      var diagnosisResultsMaps = diagnosis.diagnosisResultsMaps;
                      var filteredMapIndex = diagnosisResultsMaps?.findIndex(r => r.category === "NON_NEOPLASTIC_CELLULAR_VARIATIONS");
                      var diagnosisResultMap = {};
                      var newDiagnosisResultMaps = []
                      diagnosisResultMap.category = "NON_NEOPLASTIC_CELLULAR_VARIATIONS";
                      diagnosisResultMap.resultType = "DICTIONARY";
                      diagnosisResultMap.results = changes.selectedItems

                      if (filteredMapIndex != -1) {
                        diagnosisResultsMaps[filteredMapIndex] = diagnosisResultMap;
                        newDiagnosisResultMaps = diagnosisResultsMaps;
                      } else {
                        if (!diagnosisResultsMaps) {
                          diagnosisResultsMaps = []
                        }
                        newDiagnosisResultMaps = [...diagnosisResultsMaps, diagnosisResultMap]
                      }
                      diagnosis.diagnosisResultsMaps = newDiagnosisResultMaps
                      setPathologySampleInfo({ ...pathologySampleInfo, diagnosis: diagnosis });

                    }}
                    selectionFeedback="top-after-reopen"
                  />
                </Column>
                <Column lg={12} md={4} sm={2}>
                  {
                    filterDiagnosisResultsByCategory("NON_NEOPLASTIC_CELLULAR_VARIATIONS")?.results.map((result, index) => (
                      <Tag
                        key={index}
                        onClose={() => { }}
                      >
                        {result.value}
                      </Tag>
                    ))}
                </Column>

                <Column lg={16} md={8} sm={4}>
                  <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>
                  Reactive cellular changes
                </Column>
                <Column lg={4} md={8} sm={4}>
                  <FilterableMultiSelect
                    id="reactiveChanges"
                    titleText="Select Result"
                    items={diagnosisResultReactiveCellular}
                    itemToString={(item) => (item ? item.value : '')}
                    initialSelectedItems={filterDiagnosisResultsByCategory("REACTIVE_CELLULAR_CHANGES")?.results}
                    onChange={(changes) => {

                      var diagnosis = { ...pathologySampleInfo.diagnosis }
                      var diagnosisResultsMaps = diagnosis.diagnosisResultsMaps;
                      var filteredMapIndex = diagnosisResultsMaps?.findIndex(r => r.category === "REACTIVE_CELLULAR_CHANGES");
                      var diagnosisResultMap = {};
                      var newDiagnosisResultMaps = []
                      diagnosisResultMap.category = "REACTIVE_CELLULAR_CHANGES";
                      diagnosisResultMap.resultType = "DICTIONARY";
                      diagnosisResultMap.results = changes.selectedItems

                      if (filteredMapIndex != -1) {
                        diagnosisResultsMaps[filteredMapIndex] = diagnosisResultMap;
                        newDiagnosisResultMaps = diagnosisResultsMaps;
                      } else {
                        if (!diagnosisResultsMaps) {
                          diagnosisResultsMaps = []
                        }
                        newDiagnosisResultMaps = [...diagnosisResultsMaps, diagnosisResultMap]
                      }
                      diagnosis.diagnosisResultsMaps = newDiagnosisResultMaps
                      setPathologySampleInfo({ ...pathologySampleInfo, diagnosis: diagnosis });

                    }}
                    selectionFeedback="top-after-reopen"
                  />
                </Column>
                <Column lg={12} md={4} sm={2}>
                  {
                    filterDiagnosisResultsByCategory("REACTIVE_CELLULAR_CHANGES")?.results.map((result, index) => (
                      <Tag
                        key={index}
                        onClose={() => { }}
                      >
                        {result.value}
                      </Tag>
                    ))}
                </Column>

                <Column lg={16} md={8} sm={4}>
                  <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>
                  Organisms
                </Column>
                <Column lg={4} md={8} sm={4}>
                  <FilterableMultiSelect
                    id="organisms"
                    titleText="Select Result"
                    items={diagnosisResultOrganisms}
                    itemToString={(item) => (item ? item.value : '')}
                    initialSelectedItems={filterDiagnosisResultsByCategory("ORGANISMS")?.results}
                    onChange={(changes) => {

                      var diagnosis = { ...pathologySampleInfo.diagnosis }
                      var diagnosisResultsMaps = diagnosis.diagnosisResultsMaps;
                      var filteredMapIndex = diagnosisResultsMaps?.findIndex(r => r.category === "ORGANISMS");
                      var diagnosisResultMap = {};
                      var newDiagnosisResultMaps = []
                      diagnosisResultMap.category = "ORGANISMS";
                      diagnosisResultMap.resultType = "DICTIONARY";
                      diagnosisResultMap.results = changes.selectedItems

                      if (filteredMapIndex != -1) {
                        diagnosisResultsMaps[filteredMapIndex] = diagnosisResultMap;
                        newDiagnosisResultMaps = diagnosisResultsMaps;
                      } else {
                        if (!diagnosisResultsMaps) {
                          diagnosisResultsMaps = []
                        }
                        newDiagnosisResultMaps = [...diagnosisResultsMaps, diagnosisResultMap]
                      }
                      diagnosis.diagnosisResultsMaps = newDiagnosisResultMaps
                      setPathologySampleInfo({ ...pathologySampleInfo, diagnosis: diagnosis });

                    }}
                    selectionFeedback="top-after-reopen"
                  />
                </Column>
                <Column lg={12} md={4} sm={2}>
                  {
                    filterDiagnosisResultsByCategory("ORGANISMS")?.results.map((result, index) => (
                      <Tag
                        key={index}
                        onClose={() => { }}
                      >
                        {result.value}
                      </Tag>
                    ))}
                </Column>
                <Column lg={16} md={8} sm={4}>
                  <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>
                  Other Diagnosis Result
                </Column>
                <Column lg={4} md={8} sm={4}>
                  <FilterableMultiSelect
                    id="OTHER"
                    titleText="Select Result"
                    items={diagnosisResultOther}
                    itemToString={(item) => (item ? item.value : '')}
                    initialSelectedItems={filterDiagnosisResultsByCategory("OTHER")?.results}
                    onChange={(changes) => {

                      var diagnosis = { ...pathologySampleInfo.diagnosis }
                      var diagnosisResultsMaps = diagnosis.diagnosisResultsMaps;
                      var filteredMapIndex = diagnosisResultsMaps?.findIndex(r => r.category === "OTHER");
                      var diagnosisResultMap = {};
                      var newDiagnosisResultMaps = []
                      diagnosisResultMap.category = "OTHER";
                      diagnosisResultMap.resultType = "DICTIONARY";
                      diagnosisResultMap.results = changes.selectedItems

                      if (filteredMapIndex != -1) {
                        diagnosisResultsMaps[filteredMapIndex] = diagnosisResultMap;
                        newDiagnosisResultMaps = diagnosisResultsMaps;
                      } else {
                        if (!diagnosisResultsMaps) {
                          diagnosisResultsMaps = []
                        }
                        newDiagnosisResultMaps = [...diagnosisResultsMaps, diagnosisResultMap]
                      }
                      diagnosis.diagnosisResultsMaps = newDiagnosisResultMaps
                      setPathologySampleInfo({ ...pathologySampleInfo, diagnosis: diagnosis });

                    }}
                    selectionFeedback="top-after-reopen"
                  />
                </Column>
                <Column lg={12} md={4} sm={2}>
                  {
                    filterDiagnosisResultsByCategory("OTHER")?.results.map((result, index) => (
                      <Tag
                        key={index}
                        onClose={() => { }}
                      >
                        {result.value}
                      </Tag>
                    ))}
                </Column>

              </>
            }
          </>}
        <Column lg={16}>
          <Checkbox labelText="Ready for Release" id="release"
            onChange={() => {
              setPathologySampleInfo({ ...pathologySampleInfo, release: !pathologySampleInfo.release });
            }} />
        </Column>
        <Column lg={16}><Button id="pathology_save2" onClick={(e) => { e.preventDefault(); save(e) }}>Save</Button></Column>

      </Grid>
    </>
  )
}

export default CytologyCaseView;