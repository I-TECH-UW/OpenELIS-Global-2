import {useContext, useState, useEffect, useRef } from "react";
import {useParams} from "react-router-dom";
import { 
    Checkbox, Heading, TextInput, Select, FilterableMultiSelect, SelectItem, Button, Grid, Column,
    DataTable, TableContainer, Table, TableHead, TableRow, TableHeader, TableBody, TableCell,
    FileUploader, Tag, TextArea} from '@carbon/react';
    import { Search} from '@carbon/react';
import { getFromOpenElisServer, postToOpenElisServerFullResponse } from "../utils/Utils";
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

  const save = (e) => {
    let submitValues = {
      "status": pathologySampleInfo.status, 
      "blocks": pathologySampleInfo.blocks, 
      "slides": pathologySampleInfo.slides, 
      "grossExam": pathologySampleInfo.grossExam, 
      "microscopyExam": pathologySampleInfo.microscopyExam, 
      "conclusionText": pathologySampleInfo.conclusionText
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
            Pathology - {pathologySampleId}
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
                    value={pathologySampleInfo.assignedTechnician}
                    onChange={(event) => {
                      setPathologySampleInfo({...pathologySampleInfo, assignedTechnician: event.target.value});
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
        <div>Block</div>

        </Column >
        {pathologySampleInfo.blocks && pathologySampleInfo.blocks.map((block, index) => {
          return (
            <>
            <Column lg={8} md={4} sm={2} key={index}>
              <TextInput 
              id="blockNumber"
              labelText="block number"
              hideLabel={true}
              placeholder="Block Number" 
              value={pathologySampleInfo.blocks[index].blockNumber} 
              onChange={e => { 
                var newBlocks = [...pathologySampleInfo.blocks]; 
                newBlocks[index].blockNumber = e.target.value;
                setPathologySampleInfo({...pathologySampleInfo, blocks: newBlocks});
              }}/>
                    </Column>
        <Column lg={4} md={2} sm={2}>
              <Button>Print Label</Button>
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
        Slide
        </Column>
        {pathologySampleInfo.slides && pathologySampleInfo.slides.map((slide, index) => {
          return (
            <>
              <Column lg={8} md={4} sm={2} key={index}>
                <TextInput 
                  id="slideNumber"
                  labelText="slide number"
                  hideLabel={true}
                  placeholder="Slide Number"
                  value={pathologySampleInfo.slides[index].slideNumber} 
                  onChange={e => { 
                    var newSlides = [...pathologySampleInfo.slides]; 
                    newSlides[index].slideNumber = e.target.value;
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
                  onChange={function noRefCheck(){}}
                  onClick={function noRefCheck(){}}
                  onDelete={function noRefCheck(){}}
                />
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
                    value={pathologySampleInfo.assignedPathologist}
                    onChange={e => {
                      setPathologySampleInfo({...pathologySampleInfo, assignedPathologist: e.target.value});
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
                  <Column>
        <Button onClick={(e) => {e.preventDefault();save(e)}}>Save</Button></Column>

</Grid>

        
    </>
)
}

export default PathologyCaseView;