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
  const [blocks, setBlocks ] = useState([]);
  const [slides, setSlides ] = useState([]);
  const [statuses, setStatuses ] = useState([]);
  const [pathologyTechniques, setPathologyTechniques ] = useState([]);
  const [pathologyTechniquesActive, setPathologyTechniquesActive ] = useState([]);
  const [pathologyRequests, setPathologyRequests ] = useState([]);
  const [pathologyRequestsActive, setPathologyRequestsActive ] = useState([]);

  const setStatus = (event) => {
    setPathologySampleInfo({...pathologySampleInfo, status: event.target.value});
    console.log(JSON.stringify(pathologySampleInfo));
  }

  const removeTechnique = (technique) => {
    setPathologyTechniquesActive(pathologyTechniquesActive.filter(item => item.id !== technique.id))
  }

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/displayList/PATHOLOGY_STATUS", setStatuses);
    getFromOpenElisServer("/rest/displayList/PATHOLOGY_TECHNIQUES", setPathologyTechniques);
    getFromOpenElisServer("/rest/displayList/PATHOLOGIST_REQUESTS", setPathologyRequests);
    getFromOpenElisServer("/rest/pathology/caseView/" + pathologySampleId, setPathologySampleInfo);

    return () => {
      componentMounted.current = false
    }
  }, []);

  useEffect(() => {
    componentMounted.current = true;

    return () => {
      componentMounted.current = false;
    }
  }, []);
  
  return (
    <>
    <Grid fullWidth={true} className="gridBoundary">
        {notificationVisible === true ? <AlertDialog/> : ""}

        <Column md={8} sm={4}>
        <Heading>
            Pathology - {pathologySampleId}
        </Heading>
        </Column>

        <Column md={8} sm={4}>
        Order Date {pathologySampleInfo.requestDate}
        Name: {pathologySampleInfo.lastName}, {pathologySampleInfo.firstName}
        Age: {pathologySampleInfo.age} Sex: {pathologySampleInfo.sex}
        </Column>
        <Column md={8} sm={4}>
        Referring Facility: Ward/Dept/Unit: Requester:
        <QuestionnaireResponse questionnaireResponse={pathologySampleInfo.programQuestionnaireResponse}/>
        </Column>

        <Column md={8} sm={4}>
        <Button>Save</Button>
        </Column>
        <Column  md={2} sm={2} >
                <Select id="status"
                    name="status"
                    labelText="Status"
                    value={pathologySampleInfo.status}
                    onChange={setStatus}>   
                                            <SelectItem value="placeholder" text="Status"/>

        {statuses.map((status, index) => {
                        return (<SelectItem key={index}
                                            text={status.value}
                                            value={status.id}
                        />);
                    })}
        </Select>
        </Column>
        <Column  md={1} sm={2} style={{"marginBottom": "1rem" ,"marginTop": "1rem"}}>
        <Button>Send to Cutting</Button>
        </Column>
        <Column  md={1} sm={2} style={{"marginBottom": "1rem" ,"marginTop": "1rem"}}>
        Tecnician Assigned 
                    </Column>
        <Column md={2} sm={3}/>
        <Column md={8} sm={4}>
        <div>Block</div>

        </Column >
        {blocks.map((block, index) => {
          return (
            <>
            <Column  md={4} sm={2} key={index}>
              <TextInput placeholder="Block Number"/>
                    </Column>
        <Column  md={2} sm={2}>
              <Button>Print Label</Button>
              </Column>
        <Column  md={2} sm={0}/>
              </>
          )         
        })}
        <Column md={8} sm={4}>
                  <Button onClick={() => {
          setBlocks([...blocks, {}]);
        }}>
          Add Block
          </Button>
          </Column>
        <Column md={8} sm={4}>
        Slide
        </Column>
        {slides.map((slide, index) => {
          return (
            <>
            <Column  md={2} sm={2} key={index}>
              <TextInput placeholder="Slide Number"/>
                    </Column>
        <Column  md={1} sm={2}>
        <FileUploader
    buttonLabel="Upload Image"
    multiple={false}
    accept={['image/jpeg', 'image/png', 'application/pdf']}
    disabled={false}
    name=""
    buttonKind="primary"
    size="lg"
  />
              </Column>
            <Column  md={5} sm={3}/>
              </>
          )         
        })}
        
        <Column md={8} sm={4}>
        <Button onClick={() => {
          setSlides([...slides, {}]);
        }}>
          Add Slide
          </Button>
        </Column>
        <Column  md={2} sm={2}>
        Pathologist Assigned
        </Column>
        <Column  md={2} sm={2}>
        <Select id="pathologist"/> clear
        </Column>
        <Column md={4} sm={0}>
        </Column>
        <Column lg={16} md={8} sm={4}>HIDE THIS FOR DEFUALT VIEW vvvv</Column>
        <Column  lg={8} md={4} sm={2}>
        <FilterableMultiSelect
                id="techniques"
                titleText="Techniques Used"
                label="Techniques Used"
                items={pathologyTechniques}
                itemToString={(item) => (item ? item.value : '')} 
                onChange={(changes) => {console.log(changes);
                  setPathologyTechniquesActive(changes.selectedItems);}}         
                selectionFeedback="top-after-reopen"
                />
                </Column>
                <Column  lg={8} md={4} sm={2}>
                {pathologyTechniquesActive.map((technique, index) => (
                                        <Tag
                                            key={index}
                                            onClose={() => removeTechnique(technique)}
                                        >
                                            {technique.value}
                                        </Tag>
                                    ))}
                </Column>
                <Column lg={16} md={8} sm={4}>HIDE THIS FOR DEFUALT VIEW vvvv</Column>
        <Column lg={8} md={4} sm={2}>
        <FilterableMultiSelect
                id="requests"
                titleText="Add Request"
                label="Add Request"
                items={pathologyRequests}
                itemToString={(item) => (item ? item.value : '')} 
                onChange={(changes) => {console.log(changes)
                  setPathologyRequestsActive(changes.selectedItems)}}         
                selectionFeedback="top-after-reopen"
                />
                </Column>
                <Column  lg={8} md={4} sm={2}>
                {pathologyRequestsActive.map((technique, index) => (
                                        <Tag
                                            key={index}
                                            onClose={() => removeTechnique(technique)}
                                        >
                                            {technique.value}
                                        </Tag>
                                    ))}
                </Column>
                <Column  lg={16} md={8} sm={4}>
                  Gross Exam <TextArea/>
                </Column>
                <Column  lg={16} md={8} sm={4}>
                  Microscopy Exam <TextArea/>
                </Column>

</Grid>

        
    </>
)
}

export default PathologyCaseView;