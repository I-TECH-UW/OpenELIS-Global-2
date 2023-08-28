import React, {useEffect, useRef, useState} from 'react'
import {
    FilterableMultiSelect,
    Select,
    SelectItem,
    TextInput,
    Stack
} from '@carbon/react';

import '../../index.css';
import '../../App.css';
import "../Style.css";

import {getFromOpenElisServer} from '../utils/Utils';

export const Questionnaire = ({questionnaire, onAnswerChange = () => {}}) => {

    const getSelectOption = (answerOption, index) => {
        if ('valueString' in answerOption) {
            return <SelectItem key={index} value={answerOption.valueString} text={answerOption.valueString}/>
        } else if ('valueCoding' in answerOption) {
            return <SelectItem key={index} value={answerOption.valueCoding.code} text={answerOption.valueCoding.display}/>
        }
        else {
            return <></>
        }
    }

    const renderQuestion = (item) => {

        var options = [];
        if (item.type == "choice" && item.repeats === true && 'answerOption' in item) {
            item.answerOption.map( (answerOption, index) => 
            {
                if ('valueString' in answerOption) {
                    options.push({"value":answerOption.valueString, "text": answerOption.valueString});
                }
                if ('valueCoding' in answerOption) {
                    options.push({"value":answerOption.valueCoding.code, "text": answerOption.valueCoding.display});
                }
            })
        };

        return <>
          <div className="inputText">
        {item.type == "boolean" && <Select
                id={item.linkId}
                className="inputText"
                labelText={item.text}
                onChange={onAnswerChange} 
                >
                <SelectItem disabled value="" text="Choose an option"/>
                <SelectItem value="" text=""/>
                <SelectItem value="true" text="Yes"/>
                <SelectItem value="false" text="No"/>
                </Select>}
        {item.type == "choice" && item.repeats !== true && <Select
                id={item.linkId}
                labelText={item.text}
                
                defaultValue={'inital' in item ? 
                    'valueString' in item.initial[0] ? 
                        item.initial[0].valueString :  
                        'valueCoding' in item.initial[0] ? 
                            item.initial[0].valueCoding : 
                            '' :
                        ''}
                onChange={onAnswerChange} 
                >
                <SelectItem disabled value="" text="Choose an option"/>
                <SelectItem value="" text=""/>
                {'answerOption' in item && item.answerOption.map( (answerOption, index) => 
                    getSelectOption(answerOption, index)
                )}
                </Select>}
        {item.type == "choice" && item.repeats === true && <FilterableMultiSelect
                id={item.linkId}
                titleText={item.text}
                label=""
                items={options}
                itemToString={(item) => (item ? item.text : '')} 
                onChange={(changes) => {var e = {"target": {}}; e.target.id = item.linkId; e.target.value = changes.selectedItems; onAnswerChange(e);}}         
                selectionFeedback="top-after-reopen"
                />}
        {item.type == "integer" && <TextInput id={item.linkId} labelText={item.text} onChange={onAnswerChange} type="number" step="1" pattern="\d+"/>}
        {item.type == "decimal" && <TextInput id={item.linkId} labelText={item.text} onChange={onAnswerChange} type="number" step="0.01"/>}
        {item.type == "date" && <TextInput id={item.linkId} labelText={item.text} onChange={onAnswerChange} type="date"/>}
        {item.type == "time" && <TextInput id={item.linkId} labelText={item.text} onChange={onAnswerChange} type="time"/>}
        {item.type == "string" && <TextInput id={item.linkId} labelText={item.text} onChange={onAnswerChange} type="text"/>}
        {item.type == "text" && <TextInput id={item.linkId} labelText={item.text} onChange={onAnswerChange} type="text"/>}
        {item.type == "quantity" && <TextInput id={item.linkId} labelText={item.text} onChange={onAnswerChange} type="number"/>}
        </div>
        </>
    }
        if (questionnaire) {
          var inputs = 'item' in questionnaire && questionnaire.item.map( (item, index) => {
              return <span key={index}>{renderQuestion(item)}</span>
          });

          var groups = [];
          var children = [];
          var i = 0;
          for (; i < inputs.length; i++) {
              children.push(inputs[i]);
              if (children.length === 2) {
                  groups.push(<div className="formInlineDiv" key={"group_" + i}>{children}</div>);
                  children = [];
              }
          }
          if (children.length > 0 ) {
              groups.push(<div className="formInlineDiv" key={"group_" + i}>{children}</div>);
          }

          return <div className='extraQuestions'>
              {groups}
          </div>
        } else {
          return <></>
        }
}

export const ProgramSelect = ({programChange = () => {}}) => {
  const componentMounted = useRef(true);

  const [programs, setPrograms] = useState([]);

  const fetchPrograms = (programsList) => {
    if (componentMounted.current) {
        setPrograms(programsList);
    }
  }

  useEffect(() => {
    componentMounted.current = true;
      getFromOpenElisServer("/rest/user-programs", fetchPrograms)
      return () => {
          componentMounted.current = false;
      }
  }, []);

  return <>
    <div className="formInlineDiv">
    {programs.length > 0 && 
      <div className="inputText">
        <Select
        id="additionalQuestionsSelect"
        labelText="program"
        onChange={programChange}
        defaultValue={
          programs.find(program => {
              return program.value === 'Routine Testing'              
          })?.id}
        >
            <SelectItem value="" text=""/>
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
        </div>
    }
  
    </div>
  </>
}

const OrderEntryAdditionalQuestions = ({orderFormValues, setOrderFormValues = () => {}}) => {

    const [questionnaire, setQuestionnaire] = useState({});
    const [questionnaireResponse, setQuestionnaireResponse] = useState({});

    const handleProgramSelection = (event) => {
        if (event.target.value === "") {
          setAdditionalQuestions(null);
          setOrderFormValues({
              ...orderFormValues, sampleOrderItems: {
                  ...orderFormValues.sampleOrderItems, programId: null
              }
          });
        } else  {
          getFromOpenElisServer('/program/' + event.target.value + '/questionnaire', setAdditionalQuestions);
          setOrderFormValues({
              ...orderFormValues, sampleOrderItems: {
                  ...orderFormValues.sampleOrderItems, programId: event.target.value
              }
          });
        }
    }

    function convertQuestionnaireToResponse(questionnaire) {
        var items = [];
        if (questionnaire && 'item' in questionnaire) {
            for (let i = 0; i < questionnaire.item.length; i++) {
                let currentItem = questionnaire.item[i];
                items.push({
                  "linkId": currentItem.linkId,
                  "definition": currentItem.definition,
                  "text": currentItem.text,
                  "answer": []
                })
            }
    
            var convertedQuestionnaireResponse = {
                "resourceType" : "QuestionnaireResponse",
                "id" : "",
                "questionnaire" : "Questionnaire/" + questionnaire.id,
                "status" : "in-progress",
                "item" : items
              };
              return convertedQuestionnaireResponse;
        }
        return null;

    }

    function setAdditionalQuestions(res) {
        console.log(res)
        setQuestionnaire(res);
        var convertedQuestionnaireResponse = convertQuestionnaireToResponse(res);
        setQuestionnaireResponse(convertedQuestionnaireResponse);
    }

    const answerChange = (e) => {
      const { id, value } = e.target;

      var updatedQuestionnaireResponse = { ...questionnaireResponse };
      var responseItem = updatedQuestionnaireResponse.item.find(
        (item) => item.linkId === id
      );
      var questionnaireItem = questionnaire.item.find(
        (item) => item.linkId === id
      );
      responseItem.answer = [];
      if (value !== "") {
        switch (questionnaireItem.type) {
          case "boolean":
            responseItem.answer.push({ valueBoolean: value });
            break;
          case "decimal":
            responseItem.answer.push({ valueDecimal: value });
            break;
          case "integer":
            responseItem.answer.push({ valueInteger: value });
            break;
          case "date":
            responseItem.answer.push({ valueDate: value });
            break;
          case "time":
            responseItem.answer.push({ valueTime: value });
            break;
          case "string":
          case "text":
            responseItem.answer.push({ valueString: value });
            break;
          case "quantity":
            responseItem.answer.push({ valueQuantity: value });
            break;
          case "choice":
            //make single select and multiselect have the same shape to reuse code
            var items = value;
            if (!Array.isArray(items)) {
                items = [{"value": value}];
            }
            for (var i = 0; i < items.length; i++) {
                var curValue = items[i].value;
              var option = questionnaireItem?.answerOption?.find(
                (option) => option?.valueCoding?.code === curValue
              );
              if (option) {
                responseItem.answer.push({ valueCoding: option.valueCoding });
              } else {
                var option = questionnaireItem?.answerOption?.find(
                  (option) => option.valueString === curValue
                );
                if (option) {
                  responseItem.answer.push({ valueString: option.valueString });
                } else {
                  console.error(
                    "couldn't find a matching questionnaire answer for '" +
                    curValue +
                      "'"
                  );
                }
              }
            }
            break;
        }
      }
      setQuestionnaireResponse(updatedQuestionnaireResponse);
      setOrderFormValues({
          ...orderFormValues, sampleOrderItems: {
              ...orderFormValues.sampleOrderItems, additionalQuestions: updatedQuestionnaireResponse
          }
      });
    }


    return (
        <>
        <Stack gap={10}>
            <div className="orderLegendBody">
                <h3>Program</h3>
            <ProgramSelect programChange={handleProgramSelection}/>
            <Questionnaire questionnaire={questionnaire} onAnswerChange={answerChange}/>
            {questionnaireResponse && 
              <input type="hidden" name="additionalQuestions" value={questionnaireResponse}/>
            }
            </div>
            </Stack>
        </>
    )
}

export default OrderEntryAdditionalQuestions
