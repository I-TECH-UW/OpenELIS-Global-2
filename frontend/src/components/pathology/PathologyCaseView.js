import {useContext, useState, useEffect, useRef } from "react";
import {useParams} from "react-router-dom";
import config from "../../config.json";
import { 
  IconButton, Heading, TextInput, Select, FilterableMultiSelect, SelectItem, Button, Grid, Column,
    Checkbox, TableContainer, Table, TableHead, TableRow, TableHeader, TableBody, TableCell,
    FileUploader, Tag, TextArea} from '@carbon/react';
    import { Launch, Subtract} from '@carbon/react/icons';
import { getFromOpenElisServer, postToOpenElisServerFullResponse, hasRole } from "../utils/Utils";
import UserSessionDetailsContext from "../../UserSessionDetailsContext"
import { NotificationContext } from "../layout/Layout";
import {AlertDialog} from "../common/CustomNotification";


export const QuestionnaireResponse = ({questionnaireResponse})=> {


  const renderQuestionResponse = (item) => {
    console.log(JSON.stringify(item))
    return <>
    <div className="questionnaireResponseItem">
    {item.text}:
    {item.answer && item.answer.map( (answer, index) => {
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
    }  else if ('valueDecimal' in answer) {
      display = answer.valueDecimal
    }   else if ('valueInteger' in answer) {
      display = answer.valueInteger
    }   else if ('valueQuantity' in answer) {
      display = answer.valueQuantity.value + answer.valueQuantity.unit
    }    else if ('valueTime' in answer) {
      display = answer.valueTime
    } 
    return <>
    <span className="questionnaireResponseAnswer">
    {display}</span>
    </>
    }

  return <>
  {questionnaireResponse && questionnaireResponse.item.map( (item, index) => {
      return <span key={index}>{renderQuestionResponse(item)}</span>
  })}
  
  </>

}


function PathologyCaseView() {

  const componentMounted = useRef(false);

  const { pathologySampleId } = useParams()

  const { notificationVisible ,setNotificationVisible,setNotificationBody} = useContext(NotificationContext);
  const { userSessionDetails, setUserSessionDetails } = useContext(UserSessionDetailsContext);

  const [pathologySampleInfo, setPathologySampleInfo ] = useState({});

  const [initialMount, setInitialMount ] = useState(false);

  const [statuses, setStatuses ] = useState([]);
  const [techniques, setTechniques ] = useState([]);
  const [requests, setRequests ] = useState([]);
  const [conclusions, setConclusions ] = useState([]);
  const [technicianUsers, setTechnicianUsers] = useState([]);
  const [pathologistUsers, setPathologistUsers] = useState([]);

  async function displayStatus (response) {
    var body = await response.json();
    console.log(body)
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
      "grossExam": pathologySampleInfo.grossExam, 
      "microscopyExam": pathologySampleInfo.microscopyExam, 
      "conclusionText": pathologySampleInfo.conclusionText,
      "release": pathologySampleInfo.release,
      "referToImmunoHistoChemistry": pathologySampleInfo.referToImmunoHistoChemistry
    }
    if (pathologySampleInfo.techniques) {
      submitValues = {...submitValues, "techniques": pathologySampleInfo.techniques.map(e => {
        return e.id;
      })}
    }
    if (pathologySampleInfo.requests) {
      submitValues = {...submitValues, "requests": pathologySampleInfo.requests.map(e => {
        return e.id;
      })}
    }
    if (pathologySampleInfo.conclusions) {
      submitValues = {...submitValues, "conclusions": pathologySampleInfo.conclusions.map(e => {
        return e.id;
      })}
    }
    
    postToOpenElisServerFullResponse("/rest/pathology/caseView/" + pathologySampleId, JSON.stringify(submitValues), displayStatus);
  }


  const setInitialPathologySampleInfo = (e) => {
    setPathologySampleInfo(e);
    setInitialMount(true);
  }

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/displayList/PATHOLOGY_STATUS", setStatuses);
    getFromOpenElisServer("/rest/displayList/PATHOLOGY_TECHNIQUES", setTechniques);
    getFromOpenElisServer("/rest/displayList/PATHOLOGIST_REQUESTS", setRequests);
    //TODO make conclusions list instead of reusing pathrequest
    getFromOpenElisServer("/rest/displayList/PATHOLOGIST_CONCLUSIONS", setConclusions);
    getFromOpenElisServer("/rest/users/", setTechnicianUsers);
    getFromOpenElisServer("/rest/users/Pathologist", setPathologistUsers);
    getFromOpenElisServer("/rest/pathology/caseView/" + pathologySampleId, setInitialPathologySampleInfo );

    return () => {
      componentMounted.current = false
    }
  }, []);
  
  return (
    <>
    <Grid fullWidth={true} className="gridBoundary">
        {notificationVisible === true ? <AlertDialog/> : ""}

        <Column lg={16} md={8} sm={4}>
        <Heading>
            Pathology - {pathologySampleInfo.labNumber}
        </Heading>
        </Column>

        <Column lg={16} md={8} sm={4}>
        Order Date {pathologySampleInfo.requestDate}
        Name: {pathologySampleInfo.lastName}, {pathologySampleInfo.firstName}
        Age: {pathologySampleInfo.age} Sex: {pathologySampleInfo.sex}
        </Column>
        <Column lg={16} md={8} sm={4}>
        Referring Facility: Ward/Dept/Unit: Requester:
        <QuestionnaireResponse questionnaireResponse={pathologySampleInfo.programQuestionnaireResponse}/>
        </Column>

        <Column lg={16}  md={8} sm={4}>
        <Button onClick={(e) => {e.preventDefault();save(e)}}>Save</Button>
        </Column>
        <Column lg={4}  md={2} sm={2} >
                <Select id="status"
                    name="status"
                    labelText="Status"
                    value={pathologySampleInfo.status}
                    onChange={(event) => {
                      setPathologySampleInfo({...pathologySampleInfo, status: event.target.value});
                    }}>   
                                            <SelectItem disabled value="placeholder" text="Status"/>

        {statuses.map((status, index) => {
                        return (<SelectItem key={index}
                                            text={status.value}
                                            value={status.id}
                        />);
                    })}
        </Select>
        </Column>
        <Column lg={2}  md={1} sm={2} style={{"marginBottom": "1rem" ,"marginTop": "1rem"}}>
        </Column>
        <Column  lg={2} md={1} sm={2} style={{"marginBottom": "1rem" ,"marginTop": "1rem"}}>
        
        <Select id="assignedTechnician"
                    name="assignedTechnician"
                    labelText="Technician Assigned"
                    value={pathologySampleInfo.assignedTechnicianId}
                    onChange={(event) => {
                      setPathologySampleInfo({...pathologySampleInfo, assignedTechnicianId: event.target.value});
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
        <Column lg={8} md={4} sm={2}/>
        <Column lg={16} md={8} sm={4}>
        </Column >
        {pathologySampleInfo.blocks && pathologySampleInfo.blocks.map((block, index) => {
          return (
            <>

            <Column lg={16} md={8} sm={4}>
            <IconButton label="remove block" onClick={() => { 
              var newBlocks = [...pathologySampleInfo.blocks]; 
              newBlocks = newBlocks.splice(index, 1);
              setPathologySampleInfo({...pathologySampleInfo, blocks: newBlocks});
              }} kind='tertiary' size='sm'>
                <Subtract size={18}/>
            </IconButton>
              Block
              </Column>
            <Column lg={4} md={2} sm={1} key={index}>
              <TextInput 
              id="blockNumber"
              labelText="block number"
              hideLabel={true}
              placeholder="Block Number" 
              value={block.blockNumber} 
              onChange={e => { 
                var newBlocks = [...pathologySampleInfo.blocks]; 
                newBlocks[index].blockNumber = e.target.value;
                setPathologySampleInfo({...pathologySampleInfo, blocks: newBlocks});
              }}/>
                    </Column>
                <Column lg={4} md={2} sm={1}>
                  <TextInput 
                    id="location"
                    labelText="location"
                    hideLabel={true}
                    placeholder="Location"
                    value={block.location} 
                    onChange={e => { 
                      var newBlocks = [...pathologySampleInfo.blocks]; 
                      newBlocks[index].location = e.target.value;
                      setPathologySampleInfo({...pathologySampleInfo, blocks: newBlocks});
                    }}
                  />
              </Column>
        <Column lg={4} md={2} sm={2}>
              <Button onClick={(e) => {
                window.open(config.serverBaseUrl + '/LabelMakerServlet?labelType=block&code=' + block.blockNumber, '_blank')
              }}>Print Label</Button>
              </Column>
        <Column  lg={4} md={2} sm={0}/>
              </>
          )         
        })}
        <Column lg={16} md={8} sm={4}>
                  <Button onClick={() => {
          setPathologySampleInfo({...pathologySampleInfo, blocks: [...(pathologySampleInfo.blocks || []), {id: '', blockNumber: ''}]});
        }}>
          Add Block
          </Button>
          </Column>
        <Column lg={16} md={8} sm={4}>
        </Column>
        {pathologySampleInfo.slides && pathologySampleInfo.slides.map((slide, index) => {
          return (
            <>
            <Column lg={16} md={8} sm={4}>
              <IconButton label="remove slide" onClick={() => { 
                var newSlides = [...pathologySampleInfo.slides]; 
                setPathologySampleInfo({...pathologySampleInfo, slides: newSlides.splice(index, 1)});}} kind='tertiary' size='sm'>
                  <Subtract size={18}/>
              </IconButton>
              Slide
              </Column>
              <Column lg={4} md={2} sm={1} key={index}>
                <TextInput 
                  id="slideNumber"
                  labelText="slide number"
                  hideLabel={true}
                  placeholder="Slide Number"
                  value={slide.slideNumber} 
                  onChange={e => { 
                    var newSlides = [...pathologySampleInfo.slides]; 
                    newSlides[index].slideNumber = e.target.value;
                    setPathologySampleInfo({...pathologySampleInfo, slides: newSlides});
                  }}
                />
                </Column>
                <Column lg={4} md={2} sm={1}>
                  <TextInput 
                    id="location"
                    labelText="location"
                    hideLabel={true}
                    placeholder="Location"
                    value={slide.location} 
                    onChange={e => { 
                      var newSlides = [...pathologySampleInfo.slides]; 
                      newSlides[index].location = e.target.value;
                      setPathologySampleInfo({...pathologySampleInfo, slides: newSlides});
                    }}
                  />
              </Column>
              <Column lg={4} md={1} sm={2}>
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
                    setPathologySampleInfo({...pathologySampleInfo, slides: newSlides});
                  }}
                  onClick={function noRefCheck(){}}
                  onDelete={(e) => {
                    e.preventDefault();
                    var newSlides = [...pathologySampleInfo.slides]; 
                    newSlides[index].base64Image = '';
                    setPathologySampleInfo({...pathologySampleInfo, slides: newSlides});
                  }}
                />
                {pathologySampleInfo.slides[index].image &&
                <>
                  <Button onClick={() => {
                      var win = window.open();
                      win.document.write('<iframe src="' + slide.fileType + ";base64," + slide.image  + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
                  }}>
                     <Launch/> View
                  </Button>
                  </>
                }
                <Button onClick={(e) => {
                  window.open(config.serverBaseUrl + '/LabelMakerServlet?labelType=slide&code=' + slide.slideNumber, '_blank')
                }}>Print Label</Button>
              </Column>
              <Column lg={4} md={5} sm={3}/>
            </>
          )         
        })}
        
        <Column lg={16} md={8} sm={4}>
        <Button onClick={() => {
            setPathologySampleInfo({...pathologySampleInfo, slides: [...(pathologySampleInfo.slides || []), {id: '', slideNumber: ''}]});
        }}>
          Add Slide
          </Button>
        </Column>
        <Column  lg={4} md={2} sm={2}>
        <Select id="assignedPathologist"
                    name="assignedPathologist"
                    labelText="Pathologist Assigned"
                    value={pathologySampleInfo.assignedPathologistId}
                    onChange={e => {
                      setPathologySampleInfo({...pathologySampleInfo, assignedPathologistId: e.target.value});
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
        <Column lg={12} md={6} sm={0}>
        </Column>
        <Column lg={16} md={8} sm={4}></Column>
          {hasRole("Pathologist") &&
          <>
        <Column  lg={8} md={4} sm={2}>
        {initialMount && <FilterableMultiSelect
                id="techniques"
                titleText="Techniques Used"
                items={techniques}
                itemToString={(item) => (item ? item.value : '')} 
                initialSelectedItems={pathologySampleInfo.techniques}
                onChange={(changes) => {
                  setPathologySampleInfo({...pathologySampleInfo, techniques : changes.selectedItems});
                }}         
                selectionFeedback="top-after-reopen"
                />
              }
                </Column>
                <Column  lg={8} md={4} sm={2}>
                {pathologySampleInfo.techniques && pathologySampleInfo.techniques.map((technique, index) => (
                                        <Tag
                                            key={index}
                                            onClose={() => {}}
                                        >
                                            {technique.value}
                                        </Tag>
                                    ))}
                </Column>
                <Column lg={16} md={8} sm={4}></Column>
        <Column lg={8} md={4} sm={2}>
        {initialMount && <FilterableMultiSelect
                id="requests"
                titleText="Add Request"
                items={requests}
                itemToString={(item) => (item ? item.value : '')} 
                initialSelectedItems={pathologySampleInfo.requests}
                onChange={(changes) => {
                  setPathologySampleInfo({...pathologySampleInfo, "requests" : changes.selectedItems});
                }}       
                selectionFeedback="top-after-reopen"
                />
              }
                </Column>
                <Column  lg={8} md={4} sm={2}>
                {pathologySampleInfo.requests && pathologySampleInfo.requests.map((technique, index) => (
                                        <Tag
                                            key={index}
                                            onClose={() => {}}
                                        >
                                            {technique.value}
                                        </Tag>
                                    ))}
                </Column>
                <Column  lg={16} md={8} sm={4}>
                  <TextArea labelText="Gross Exam" value={pathologySampleInfo.grossExam} onChange={e => {
                      setPathologySampleInfo({...pathologySampleInfo, grossExam: e.target.value});
                    }}/>
                </Column>
                <Column  lg={16} md={8} sm={4}>
                  <TextArea labelText="Microscopy Exam" value={pathologySampleInfo.microscopyExam} onChange={e => {
                      setPathologySampleInfo({...pathologySampleInfo, microscopyExam: e.target.value});
                    }}/>
                </Column>
                <Column lg={8} md={4} sm={2}>
                  {initialMount && <FilterableMultiSelect
                id="conclusion"
                titleText="Conclusion"
                items={conclusions}
                itemToString={(item) => (item ? item.value : '')} 
                initialSelectedItems={pathologySampleInfo.conclusions}
                onChange={(changes) => {
                  setPathologySampleInfo({...pathologySampleInfo, "conclusions" : changes.selectedItems});
                }}        
                selectionFeedback="top-after-reopen"
                />
              }
                </Column>
                <Column  lg={8} md={4} sm={2}>
                {pathologySampleInfo.conclusions && pathologySampleInfo.conclusions.map((conclusion, index) => (
                                        <Tag
                                            key={index}
                                        >
                                            {conclusion.value}
                                        </Tag>
                                    ))}
                </Column>
                <Column  lg={16} md={8} sm={4}>
                <TextArea labelText="Conclusion" value={pathologySampleInfo.conclusionText} onChange={e => {
                      setPathologySampleInfo({...pathologySampleInfo, conclusionText: e.target.value});
                    }}/>

                  </Column>
                  </>}
                  <Column lg={16}>
                    <Checkbox labelText="Ready for Release" id="release"
                      onChange={() => {
                        setPathologySampleInfo({...pathologySampleInfo, release: !pathologySampleInfo.release});
                      }}/>
                  </Column>
                  <Column lg={16}>
                    <Checkbox labelText="Refer to ImmunoHistoChemistry" id="referToImmunoHistoChemistry"
                      onChange={() => {
                        setPathologySampleInfo({...pathologySampleInfo, referToImmunoHistoChemistry: !pathologySampleInfo.referToImmunoHistoChemistry});
                      }}/>
                  </Column>
                  <Column><Button onClick={(e) => {e.preventDefault();save(e)}}>Save</Button></Column>

</Grid>

        
    </>
)
}

export default PathologyCaseView;