import { useContext, useState, useEffect, useRef } from "react";
import { useParams } from "react-router-dom";
import config from "../../config.json";
import {
  IconButton, Heading, TextInput, Select, FilterableMultiSelect, SelectItem, Button, Grid, Column,
  Checkbox, Section, FileUploader, Tag, TextArea ,Breadcrumb ,BreadcrumbItem ,Loading
} from '@carbon/react';
import { Launch, Subtract } from '@carbon/react/icons';
import { getFromOpenElisServer, postToOpenElisServerFullResponse, hasRole } from "../utils/Utils";
import UserSessionDetailsContext from "../../UserSessionDetailsContext"
import { NotificationContext } from "../layout/Layout";
import { AlertDialog ,NotificationKinds } from "../common/CustomNotification";
import { FormattedMessage} from 'react-intl'
import "./PathologyDashboard.css"


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


function PathologyCaseView() {

  const componentMounted = useRef(false);

  const { pathologySampleId } = useParams()

  const { notificationVisible, setNotificationVisible, setNotificationBody } = useContext(NotificationContext);
  const { userSessionDetails, setUserSessionDetails } = useContext(UserSessionDetailsContext);

  const [pathologySampleInfo, setPathologySampleInfo] = useState({});

  const [initialMount, setInitialMount] = useState(false);

  const [statuses, setStatuses] = useState([]);
  const [techniques, setTechniques] = useState([]);
  const [requests, setRequests] = useState([]);
  const [conclusions, setConclusions] = useState([]);
  const [immunoHistoChemistryTests, setImmunoHistoChemistryTests] = useState([]);
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
    let submitValues = {
      "assignedTechnicianId": pathologySampleInfo.assignedTechnicianId,
      "assignedPathologistId": pathologySampleInfo.assignedPathologistId,
      "status": pathologySampleInfo.status,
      "blocks": pathologySampleInfo.blocks,
      "slides": pathologySampleInfo.slides,
      "reports": pathologySampleInfo.reports,
      "grossExam": pathologySampleInfo.grossExam,
      "microscopyExam": pathologySampleInfo.microscopyExam,
      "conclusionText": pathologySampleInfo.conclusionText,
      "release": pathologySampleInfo.release != undefined ? pathologySampleInfo.release : false,
      "referToImmunoHistoChemistry": pathologySampleInfo.referToImmunoHistoChemistry,
      "immunoHistoChemistryTestId": pathologySampleInfo.immunoHistoChemistryTestId
    }
    if (pathologySampleInfo.techniques) {
      submitValues = {
        ...submitValues, "techniques": pathologySampleInfo.techniques.map(e => {
          return e.id;
        })
      }
    }
    if (pathologySampleInfo.requests) {
      submitValues = {
        ...submitValues, "requests": pathologySampleInfo.requests.map(e => {
          return e.id;
        })
      }
    }
    if (pathologySampleInfo.conclusions) {
      submitValues = {
        ...submitValues, "conclusions": pathologySampleInfo.conclusions.map(e => {
          return e.id;
        })
      }
    }

    console.log(" ..submit....")
    console.log(JSON.stringify(submitValues))
    postToOpenElisServerFullResponse("/rest/pathology/caseView/" + pathologySampleId, JSON.stringify(submitValues), displayStatus);
  }


  const setInitialPathologySampleInfo = (e) => {
    if(hasRole(userSessionDetails ,"Pathologist") && !e.assignedPathologistId && e.status === "READY_PATHOLOGIST"){
       e.assignedPathologistId = userSessionDetails.userId
       e.assignedPathologist = userSessionDetails.lastName +" "+userSessionDetails.firstName
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
    getFromOpenElisServer("/rest/displayList/PATHOLOGY_STATUS", setStatuses);
    getFromOpenElisServer("/rest/displayList/PATHOLOGY_TECHNIQUES", setTechniques);
    getFromOpenElisServer("/rest/displayList/PATHOLOGIST_REQUESTS", setRequests);
    //TODO make conclusions list instead of reusing pathrequest
    getFromOpenElisServer("/rest/displayList/IMMUNOHISTOCHEMISTRY_MARKERS_TESTS", setImmunoHistoChemistryTests);
    getFromOpenElisServer("/rest/displayList/PATHOLOGIST_CONCLUSIONS", setConclusions);
    getFromOpenElisServer("/rest/users", setTechnicianUsers);
    getFromOpenElisServer("/rest/users/Pathologist", setPathologistUsers);
    getFromOpenElisServer("/rest/pathology/caseView/" + pathologySampleId, setInitialPathologySampleInfo);

    return () => {
      componentMounted.current = false
    }
  }, []);

  return (
    <>
     <Breadcrumb>
        <BreadcrumbItem href="/"> Home </BreadcrumbItem> 
        <BreadcrumbItem href="/PathologyDashboard"> Pathology DashBoard</BreadcrumbItem>
      </Breadcrumb>

      <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Section >
              <Heading >
                <FormattedMessage id="pathology.label.title" />
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
                <div className="patient-name"><Tag type="blue"><FormattedMessage id="patient.label.name" /> :</Tag>{pathologySampleInfo.lastName}  {pathologySampleInfo.firstName}</div>
                <div className="patient-dob"> <Tag type="blue"><FormattedMessage id="patient.label.sex" /> :</Tag>{pathologySampleInfo.sex === 'M' ? "Male" : "Female"}<Tag type="blue"><FormattedMessage id="patient.label.age" /> :</Tag>{pathologySampleInfo.age} </div>
                <div className="patient-id"><Tag type="blue"><FormattedMessage id="sample.label.orderdate" />  :</Tag>{pathologySampleInfo.requestDate}</div>
                <div className="patient-id"><Tag type="blue"> <FormattedMessage id="sample.label.labnumber" /> :</Tag>{pathologySampleInfo.labNumber}</div>
                <div className="patient-id"><Tag type="blue">  <FormattedMessage id="sample.label.facility" /> :</Tag> {pathologySampleInfo.referringFacility}<Tag type="blue"> <FormattedMessage id="sample.label.requester" />: </Tag>{pathologySampleInfo.department}</div>
                <div className="patient-id"><Tag type="blue"> <FormattedMessage id="sample.label.requester" />: </Tag>{pathologySampleInfo.requester}</div>
              </div>) : (<div className="patient-header">
                <div className="patient-name"> <FormattedMessage id="patient.label.nopatientid" /> </div>
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
        <Column lg={16} md={8} sm={4}>
            <hr style={{ width: '100%', margin: '1rem 0', border: '1px solid #ccc' }} />
        </Column>

        <Column lg={16} md={8} sm={4}>
        <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>    
        </Column> 
          <Column  lg={16} md={8} sm={4}>  
           <hr style={{width:'100%' , margin: '', border: '1px solid #ccc' }} />
           <h5> <FormattedMessage id="immunohistochemistry.label.reports" /></h5>
          </Column>
          <Column lg={16} md={8} sm={4}>
             <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>    
          </Column> 
          {pathologySampleInfo.reports && pathologySampleInfo.reports.map((report, index) => {
          return (
            <>
           <Column lg={2} md={8} sm={4}>
                <IconButton label="Remove Report" onClick={() => {
                  var info = {...pathologySampleInfo};
                  info["reports"].splice(index, 1);
                  setPathologySampleInfo(info);
                }} kind='tertiary' size='sm'>
                  <Subtract size={18} /> <FormattedMessage id="immunohistochemistry.label.report" />
                </IconButton>
            </Column>

            
            <Column lg={3} md={1} sm={2} > 
                <FileUploader
                  style={{marginTop: '-30px'}}
                  buttonLabel={<FormattedMessage id="label.button.uploadfile" />}
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
                    var newReports = [...pathologySampleInfo.reports];
                    let encodedFile = await toBase64(file);
                    newReports[index].base64Image = encodedFile;
                    setPathologySampleInfo({ ...pathologySampleInfo, reports: newReports });
                  }}
                  onClick={function noRefCheck() { }}
                  onDelete={(e) => {
                    e.preventDefault();
                  }}
                />
              </Column>  
              <Column lg={4}>
                Pathology Report
              </Column>
              <Column lg={2} md={1} sm={2}>
                {pathologySampleInfo.reports[index].image &&
                  <>
                    <Button onClick={() => {
                      var win = window.open();
                      win.document.write('<iframe src="' + report.fileType + ";base64," + report.image + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
                    }}>
                      <Launch />  <FormattedMessage id="pathology.label.view" />
                    </Button>
                  </>
                }
              </Column>
             <Column lg={3} md={5} sm={3} /> 
             <Column lg={16} md={8} sm={4}>
               <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>    
             </Column>

            </>
          )
        })}
        
        <Column lg={16} md={8} sm={4}>
          <Button onClick={() => {
            setPathologySampleInfo({...pathologySampleInfo, reports: [...(pathologySampleInfo.reports || []), {id: '', reportType: "PATHOLOGY"}]});
         }}>
            Add Report
          </Button>
        </Column>  
        <Column lg={16} md={8} sm={4}>
            <hr style={{ width: '100%', margin: '1rem 0', border: '1px solid #ccc' }} />
        </Column>
        <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>    
        <Column  lg={16} md={8} sm={4}>  
          <hr style={{width:'100%' , margin: '1rem 0', border: '1px solid #ccc' }} />
           <h5><FormattedMessage id="pathology.label.blocks" /></h5>
           <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>    
        </Column>
        {pathologySampleInfo.blocks && pathologySampleInfo.blocks.map((block, index) => {
          return (
            <>
              <Column lg={2} md={8} sm={4}>
                <IconButton label="remove block" onClick={() => {
                  var info = {...pathologySampleInfo};
                  info["blocks"].splice(index, 1);
                  setPathologySampleInfo(info);
                }} kind='tertiary' size='sm'>
                  <Subtract size={18} />  <FormattedMessage id="pathology.label.block" />
                </IconButton>
              
              </Column>
              <Column lg={3} md={2} sm={1} key={index}>
                <TextInput
                  id="blockNumber"
                  labelText="block number"
                  hideLabel={true}
                  placeholder="Block Number"
                  value={block.blockNumber}
                  type="number"
                  onChange={e => {
                    var newBlocks = [...pathologySampleInfo.blocks];
                    newBlocks[index].blockNumber = e.target.value;
                    setPathologySampleInfo({ ...pathologySampleInfo, blocks: newBlocks });
                  }} />
              </Column>
              <Column lg={3} md={2} sm={1}>
                <TextInput
                  id="location"
                  labelText="location"
                  hideLabel={true}
                  placeholder="Location"
                  value={block.location}
                  onChange={e => {
                    var newBlocks = [...pathologySampleInfo.blocks];
                    newBlocks[index].location = e.target.value;
                    setPathologySampleInfo({ ...pathologySampleInfo, blocks: newBlocks });
                  }}
                />
              </Column>
              <Column lg={3} md={2} sm={2}>
                <Button onClick={(e) => {
                  window.open(config.serverBaseUrl + '/LabelMakerServlet?labelType=block&code=' + block.blockNumber, '_blank')
                }}> <FormattedMessage id="pathology.label.printlabel" /></Button>
              </Column>
              <Column lg={5} md={2} sm={0} />
              <Column  lg={16} md={8} sm={4}>  
                    <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>    
               </Column>
            </>
          )
        })}
        <Column lg={16} md={8} sm={4}>
          <Button onClick={() => {
            setPathologySampleInfo({ ...pathologySampleInfo, blocks: [...(pathologySampleInfo.blocks || []), { id: '', blockNumber: '' }] });
          }}>
            <FormattedMessage id="pathology.label.addblock" />
          </Button>
        </Column>
        <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>    
        <Column  lg={16} md={8} sm={4}>  
          <hr style={{width:'100%' , margin: '1rem 0', border: '1px solid #ccc' }} />
           <h5><FormattedMessage id="pathology.label.slides" /></h5>
           <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>    
        </Column>
        
        {pathologySampleInfo.slides && pathologySampleInfo.slides.map((slide, index) => {
          return (
            <>
              <Column lg={2} md={8} sm={4}>
                <IconButton label="remove slide" onClick={() => {
                   var info = {...pathologySampleInfo};
                   info["slides"].splice(index, 1);
                   setPathologySampleInfo(info);
                }} kind='tertiary' size='sm'>
                  <Subtract size={18} />  <FormattedMessage id="pathology.label.slide" />
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
                  buttonLabel={<FormattedMessage id="label.button.uploadfile" />}
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
                  }}
                />
              </Column>
              <Column lg={3} md={1} sm={2}>
              {pathologySampleInfo.slides[index].image &&
                  <>
                    <Button onClick={() => {
                      var win = window.open();
                      win.document.write('<iframe src="' + slide.fileType + ";base64," + slide.image + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
                    }}>
                      <Launch /> <FormattedMessage id="pathology.label.view" />
                    </Button>
                  </>
                }
                 </Column>
                <Column lg={2} md={1} sm={2}>
                <Button onClick={(e) => {
                  window.open(config.serverBaseUrl + '/LabelMakerServlet?labelType=slide&code=' + slide.slideNumber, '_blank')
                }}><FormattedMessage id="pathology.label.printlabel" /></Button>
                 </Column>
            </>
          )
        })}

        <Column lg={16} md={8} sm={4}>
          <Button onClick={() => {
            setPathologySampleInfo({ ...pathologySampleInfo, slides: [...(pathologySampleInfo.slides || []), { id: '', slideNumber: '' }] });
          }}>
            <FormattedMessage id="pathology.label.addslide" />
          </Button>
          <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>    
        <Column  lg={16} md={8} sm={4}>  
          <hr style={{width:'100%' , margin: '1rem 0', border: '1px solid #ccc' }} />
        </Column>  
        <div > &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;</div>   
        </Column>
       
        <Column lg={12} md={6} sm={0}>
        </Column>
        <Column lg={16} md={8} sm={4}></Column>
        {hasRole( userSessionDetails ,"Pathologist") &&
          <>
            <Column lg={4} md={4} sm={2}>
              {initialMount && <FilterableMultiSelect
                id="techniques"
                titleText={<FormattedMessage id="pathology.label.techniques" />}
                items={techniques}
                itemToString={(item) => (item ? item.value : '')}
                initialSelectedItems={pathologySampleInfo.techniques}
                onChange={(changes) => {
                  setPathologySampleInfo({ ...pathologySampleInfo, techniques: changes.selectedItems });
                }}
                selectionFeedback="top-after-reopen"
              />
              }
            </Column>
            <Column lg={12} md={4} sm={2}>
              {pathologySampleInfo.techniques && pathologySampleInfo.techniques.map((technique, index) => (
                <Tag
                  key={index}
                  onClose={() => { }}
                >
                  {technique.value}
                </Tag>
              ))}
            </Column>
            <Column lg={16} md={8} sm={4}></Column>
            <Column lg={4} md={4} sm={2}>
              {initialMount && <FilterableMultiSelect
                id="requests"
                titleText={<FormattedMessage id="pathology.label.request" />}
                items={requests}
                itemToString={(item) => (item ? item.value : '')}
                initialSelectedItems={pathologySampleInfo.requests}
                onChange={(changes) => {
                  setPathologySampleInfo({ ...pathologySampleInfo, "requests": changes.selectedItems });
                }}
                selectionFeedback="top-after-reopen"
              />
              }
            </Column>
            <Column lg={12} md={4} sm={2}>
              {pathologySampleInfo.requests && pathologySampleInfo.requests.map((technique, index) => (
                <Tag
                  key={index}
                  onClose={() => { }}
                >
                  {technique.value}
                </Tag>
              ))}
            </Column>
            <Column lg={16} md={8} sm={4}>
              <TextArea labelText={<FormattedMessage id="pathology.label.grossexam" />} value={pathologySampleInfo.grossExam} onChange={e => {
                setPathologySampleInfo({ ...pathologySampleInfo, grossExam: e.target.value });
              }} />
            </Column>
            <Column lg={16} md={8} sm={4}>
              <TextArea labelText={<FormattedMessage id="pathology.label.microexam" />} value={pathologySampleInfo.microscopyExam} onChange={e => {
                setPathologySampleInfo({ ...pathologySampleInfo, microscopyExam: e.target.value });
              }} />
            </Column>
            <Column lg={4} md={4} sm={2}>
              {initialMount && <FilterableMultiSelect
                id="conclusion"
                titleText={<FormattedMessage id="pathology.label.conclusion" />}
                items={conclusions}
                itemToString={(item) => (item ? item.value : '')}
                initialSelectedItems={pathologySampleInfo.conclusions}
                onChange={(changes) => {
                  setPathologySampleInfo({ ...pathologySampleInfo, "conclusions": changes.selectedItems });
                }}
                selectionFeedback="top-after-reopen"
              />
              }
            </Column>
            <Column lg={12} md={4} sm={2}>
              {pathologySampleInfo.conclusions && pathologySampleInfo.conclusions.map((conclusion, index) => (
                <Tag
                  key={index}
                >
                  {conclusion.value}
                </Tag>
              ))}
            </Column>
            <Column lg={16} md={8} sm={4}>
              <TextArea labelText={<FormattedMessage id="pathology.label.textconclusion" />} value={pathologySampleInfo.conclusionText} onChange={e => {
                setPathologySampleInfo({ ...pathologySampleInfo, conclusionText: e.target.value });
              }} />

            </Column>
          </>}
        <Column lg={16}>
          <Checkbox labelText={<FormattedMessage id="pathology.label.release" />} id="release"
            onChange={() => {
              setPathologySampleInfo({ ...pathologySampleInfo, release: !pathologySampleInfo.release });
            }} />
        </Column>
        <Column lg={8}>
          <Checkbox labelText={<FormattedMessage id="pathology.label.refer" />} id="referToImmunoHistoChemistry"
            onChange={() => {
              setPathologySampleInfo({ ...pathologySampleInfo, referToImmunoHistoChemistry: !pathologySampleInfo.referToImmunoHistoChemistry });
            }} />
        </Column>
        {pathologySampleInfo.referToImmunoHistoChemistry && (
          <Column lg={8}>
            <Select id="immunoHistoChemistryTest"
              name="immunoHistoChemistryTest"
              labelText="immunoHistoChemistryTest"
              onChange={(event) => {
                setPathologySampleInfo({ ...pathologySampleInfo, immunoHistoChemistryTestId: event.target.value });
              }}>
              <SelectItem />
              {immunoHistoChemistryTests.map((test, index) => {
                return (<SelectItem key={index}
                  text={test.value}
                  value={test.id}
                />);
              })}
            </Select>
          </Column>)}
        <Column lg={16}><Button id ="pathology_save2"onClick={(e) => { e.preventDefault(); save(e) }}>Save</Button></Column>

      </Grid>


    </>
  )
}

export default PathologyCaseView