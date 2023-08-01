import {useContext, useState, useEffect, useRef } from "react";
import { createPortal } from 'react-dom'
import { Form, FormLabel, Heading, TextArea, TextInput, Select, SelectItem, Button, IconButton, Toggle,  Loading, RadioButtonGroup, RadioButton ,ModalWrapper} from '@carbon/react';
import ProgramFormValues from "./ProgramFormValues";
import { getFromOpenElisServer, postToOpenElisServerFullResponse, getFromOpeElisServerSync } from "../../utils/Utils";
import { Questionnaire } from '../../addOrder/OrderEntryAdditionalQuestions'
import { NotificationContext } from "../../layout/Layout";
import {AlertDialog, NotificationKinds} from "../../common/CustomNotification";
import { FormattedMessage} from "react-intl";

function ProgramManagement() {

  const componentMounted = useRef(true);
  const [programs, setPrograms] = useState([]);
  const [loading, setLoading] = useState(false);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [programValues, setProgramValues] = useState(ProgramFormValues);
  const { notificationVisible ,setNotificationVisible,setNotificationBody} = useContext(NotificationContext);

  const fetchPrograms = (programsList) => {
      if (componentMounted.current) {
          setPrograms(programsList);
      }
  }

  const handleProgramSelection = (event) => {
    if (event.target.value === "") {
        setProgramValues(ProgramFormValues);
    } else  {
        setLoading(true);
        getFromOpenElisServer('/program/' + event.target.value, setAdditionalQuestions);
    }
}

function setAdditionalQuestions(res) {
    console.log(res)
    if (res.additionalOrderEntryQuestions) {
        res.additionalOrderEntryQuestions = JSON.stringify(res.additionalOrderEntryQuestions, null, 4);
    }
    setProgramValues(res);
    setLoading(false);
}

const handleFieldChange = (e) => {
    const { name, value } = e.target;
    //TODO use better strategy developed with greg
    var names = name.split(".")
    const updatedValues = {...programValues};
    if (names.length === 1) {
        updatedValues[name] = value;
    } else if (names.length === 2) {
        updatedValues[names[0]][names[1]] = value;
    }
    setProgramValues(updatedValues);
}

async function  displayStatus(res) {
    setNotificationVisible(true);
    setIsSubmitting(false);
    if(res.status == "200"){
        setNotificationBody({kind: NotificationKinds.success, title: "Notification Message", message: "Succesfuly Added/Edited"});
        getFromOpenElisServer("/rest/displayList/PROGRAM", fetchPrograms)
        var body = await res.json();
        if (body.additionalOrderEntryQuestions) {
            body.additionalOrderEntryQuestions = JSON.stringify(body.additionalOrderEntryQuestions, null, 4);
        }
        setProgramValues(body);
    }else{
      setNotificationBody({kind: NotificationKinds.error, title: "Notification Message", message: "Error while Editing/Adding"});
    }
}

function isJson(item) {
    let value = typeof item !== "string" ? JSON.stringify(item) : item;    
    try {
      value = JSON.parse(value);
    } catch (e) {
      return false;
    }
      
    return typeof value === "object" && value !== null;
  }

function handleSubmit(event) {
    event.preventDefault();
    setIsSubmitting(true);
    var submitValues = {...programValues};
    if (submitValues.additionalOrderEntryQuestions) {
        submitValues.additionalOrderEntryQuestions = JSON.parse(submitValues.additionalOrderEntryQuestions);
    } else {
        delete submitValues['additionalOrderEntryQuestions'];
    }
    postToOpenElisServerFullResponse("/program", JSON.stringify(submitValues), displayStatus);
} 

  useEffect(() => {

    getFromOpenElisServer("/rest/displayList/PROGRAM", fetchPrograms)

      return () => {
          componentMounted.current = false
      }
  }, []);

  return (
    <>
        {notificationVisible === true ? <AlertDialog/> : ""}
        <div className='adminPageContent'>
        <Form onSubmit={handleSubmit} >
            <FormLabel>
                <Heading>
                    Add/Edit Program
                </Heading>
            </FormLabel>
            <div className="formInlineDiv">
            <Select
                id="additionalQuestionsSelect"
                labelText="program"
                onChange={handleProgramSelection}>
                <SelectItem value="" text="New Program"/>
                {
                    programs.map(program => {
                        return (
                            <SelectItem key={program.id}
                                        value={program.id}
                                        text={program.value}/>
                        )
                    })
                }
            </Select>
            {loading && <Loading/>}
            </div>
            
            <div className="formInlineDiv">
                <input type="hidden" 
                    name="program.id" 
                    value={programValues.program.id}
                    onChange={handleFieldChange}/>
                <TextInput type="text" name="program.programName" id="program.programName" labelText="Program Name" 
                    value={programValues.program.programName}
                    onChange={handleFieldChange}/>
            </div>
            <div className="formInlineDiv">
                <TextInput type="text" name="program.questionnaireUUID" id="program.questionnaireUUID" labelText="UUID"
                        value={programValues.program.questionnaireUUID}
                    onChange={handleFieldChange}/>
                    <TextInput type="text" name="program.code" id="program.code" labelText="Code" maxLength="10" 
                        value={programValues.program.code}
                        onChange={handleFieldChange}/>
            </div>
            <div className="formInlineDiv">
                <TextArea name="additionalOrderEntryQuestions" id="additionalOrderEntryQuestions" labelText="Questionnaire" 
                    value={programValues.additionalOrderEntryQuestions} 
                    onChange={handleFieldChange}/>
                    {isJson(programValues.additionalOrderEntryQuestions) &&
                <div>
                <FormLabel>
                    Example
                </FormLabel>
                <div className="exampleDiv">
                    <Questionnaire questionnaire={JSON.parse(programValues.additionalOrderEntryQuestions)}/>
                </div>
                    </div>
}
            </div>

            <div>
                <Button type="submit" >
                    <FormattedMessage id="label.button.submit" />
                    {isSubmitting &&
                    <Loading small={true}/>}
                </Button>
            </div>
            </Form>

        </div>
    </>
)
}

export default ProgramManagement;