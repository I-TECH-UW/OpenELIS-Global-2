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
    if (pathologySampleInfo.satisfaction) {
      specimenAdequacy = {
        "satisfaction": pathologySampleInfo.satisfaction,
        "resultType": "DICTIONARY",
      }

      if (pathologySampleInfo.adequacies) {
        specimenAdequacy = {
          ...specimenAdequacy, "values": pathologySampleInfo.adequacies.map(e => {
            return e.id;
          })
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


    console.log(" ..submit....")
    console.log(JSON.stringify(submitValues))
     postToOpenElisServerFullResponse("/rest/cytology/caseView/" + cytologySampleId, JSON.stringify(submitValues), displayStatus);
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

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/displayList/CYTOLOGY_STATUS", setStatuses);
    getFromOpenElisServer("/rest/displayList/CYTOLOGY_SATISFACTORY_FOR_EVALUATION", setSatisfactoryForEvaluation);
    getFromOpenElisServer("/rest/displayList/CYTOLOGY_UN_SATISFACTORY_FOR_EVALUATION", setUnSatisfactoryForEvaluation);
    getFromOpenElisServer("/rest/displayList/CYTOLOGY_SPECIMEN_ADEQUACY_SATISFACTION", setAdequacySatisfactionList);
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
        </Column>
        <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>
        {pathologySampleInfo.slides && pathologySampleInfo.slides.map((slide, index) => {
          return (
            <>
              <Column lg={16} md={8} sm={4}>
                <IconButton label="remove slide" onClick={() => {
                  var newSlides = [...pathologySampleInfo.slides];
                  setPathologySampleInfo({ ...pathologySampleInfo, slides: newSlides.splice(index, 1) });
                }} kind='tertiary' size='sm'>
                  <Subtract size={18} />  Slide
                </IconButton>

              </Column>
              <Column lg={2} md={2} sm={1} key={index}>
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
              <Column lg={2} md={2} sm={1}>
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
              <Column lg={4} md={1} sm={2}>
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
                <Button onClick={(e) => {
                  window.open(config.serverBaseUrl + '/LabelMakerServlet?labelType=slide&code=' + slide.slideNumber, '_blank')
                }}>Print Label</Button>
                <FileUploader
                  buttonLabel="Upload Image"
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
              <Column lg={8} md={5} sm={3} />
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
        {hasRole(userSessionDetails, "Cytopathologist") &&
          <>
            <Column lg={4} md={1} sm={2} >

              <Select id="specimenAdequacy"
                name="specimenAdequacy"
                labelText="Specimen Adequacy"
                value={pathologySampleInfo.satisfaction}
                onChange={(event) => {
                 
                  setPathologySampleInfo({ ...pathologySampleInfo, satisfaction: event.target.value , adequacies : []});
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
            {pathologySampleInfo.satisfaction && pathologySampleInfo.satisfaction === 'UN_SATISFACTORY_FOR_EVALUATION' && (
              <>
                <Column lg={4} md={4} sm={2}>
                  {initialMount && <FilterableMultiSelect
                    id="adequacy"
                    titleText="Specimen Adequacy"
                    items={unSatisfactoryForEvaluation}
                    itemToString={(item) => (item ? item.value : '')}
                    initialSelectedItems={pathologySampleInfo.adequacies}
                    onChange={(changes) => {
                      setPathologySampleInfo({ ...pathologySampleInfo, adequacies: changes.selectedItems });
                    }}
                    selectionFeedback="top-after-reopen"
                  />
                  }
                </Column>
                <Column lg={8} md={4} sm={2}>
                  {pathologySampleInfo.adequacies && pathologySampleInfo.adequacies.map((adequacy, index) => (
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
            {pathologySampleInfo.satisfaction && pathologySampleInfo.satisfaction === 'SATISFACTORY_FOR_EVALUATION' && (
              <Column lg={8}>
                <RadioButtonGroup
                  valueSelected={pathologySampleInfo.adequacies ? pathologySampleInfo.adequacies[0]?.id : null}
                  legendText={"Select Adequacy"}
                  name="adequacy"
                  id="adequacy"
                  onChange={(value) => {
                    setPathologySampleInfo({ ...pathologySampleInfo, adequacies: [{"id" : value}] });
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

            <Column lg={16} md={8} sm={4}></Column>

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