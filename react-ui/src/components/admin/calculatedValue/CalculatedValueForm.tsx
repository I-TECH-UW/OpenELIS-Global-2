import React from "react";
import { useContext, useState, useEffect, useRef } from "react";
import { Form, Stack, TextInput, Select, SelectItem, Button, IconButton, Toggle, Loading, RadioButtonGroup, RadioButton, ModalWrapper } from '@carbon/react';
import AutoComplete from '../../common/AutoComplete.js'
import { Add, Subtract, Save } from '@carbon/react/icons';
import { FormattedMessage } from "react-intl";
import { CalculatedValueFormValues, CalculatedValueFormModel, OperationType, Operation } from '../../formModel/innitialValues/CalculatedValueFormSchema'
import { getFromOpenElisServer, postToOpenElisServer, getFromOpeElisServerSync } from '../../utils/Utils.js';

interface CalculatedValueProps {
}
//<WatsonHealthSaveImage />


const handleSubmit = (event) => {
  event.preventDefault();
  //alert("yeye")
  //console.log(JSON.stringify(ruleList[index]))
  // postToOpenElisServer("/rest/reflexrule", JSON.stringify(ruleList[index]), (status) => handleSubmited(status ,index))
};

const CalculatedValue: React.FC<CalculatedValueProps> = () => {
  const componentMounted = useRef(true);
  const [ruleList, setRuleList] = useState([CalculatedValueFormValues]);
  const [showConfirmBox, setShowConfirmBox] = useState(true);
  const [sampleList, setSampleList] = useState([]);
  const [sampleTestList, setSampleTestList] = useState({ "testResult": {}, "finalResult": {} }); //{field :{index :{field_index:[]}}}
  const [loaded, setLoaded] = useState(false);

  useEffect(() => {
    getFromOpenElisServer("/rest/samples", fetchSamples)

    return () => { // This code runs when component is unmounted
      componentMounted.current = false;
    }

  }, []);

  const fetchSamples = (sampleList) => {
    if (componentMounted.current) {
      setSampleList(sampleList);
    }
  }

  const CalculatedValueObj: CalculatedValueFormModel = {
    id: null,
    ruleName: "",
    operations: [
      {
        type: 'TEST_RESULT',
        value: ""
      }
    ]
  };

  const handleRuleAdd = () => {
    setRuleList([...ruleList, CalculatedValueObj]);
  };

  const handleRuleRemove = (index, id) => {
    const list = [...ruleList];
    list.splice(index, 1);
    setRuleList(list);
    if (id) {
      // postToOpenElisServer("/rest/deactivate-reflexrule/" + id, {}, handleDelete);
    }
    setShowConfirmBox(false)
  };

  const handleCancelDelete = () => {
    setShowConfirmBox(false)
  };

  const addOperation = (index: number, type: OperationType) => {

    var operation: Operation = {
      type: type,
      value: ''
    }
    const list = [...ruleList];
    list[index]['operations'].push(operation);
    console.log(JSON.stringify(list[index]['operations']))
    setRuleList(list);
  };

  const removeOperation = (index: number, operationIndex: number) => {
    const list = [...ruleList];
    list[index]['operations'].splice(operationIndex, 1);
    setRuleList(list);
  }

  const handleSampleSelected = (e, index, item_index) => {
    const { value } = e.target;
    getFromOpenElisServer("/rest/tests-by-sample?sampleType=" + value, (resp) => fetchTests(resp, index, item_index));
  }

  const loadSampleTestList = (field, index, item_index, resultList) => {
    const results = { ...sampleTestList }
    if (!results[field][index]) {
      results[field][index] = {}
    }
    results[field][index][item_index] = resultList
    console.log(JSON.stringify(results))
    setSampleTestList(results);
  }

  const fetchTests = (testList, index, item_index) => {
    loadSampleTestList("testResult",index, item_index, testList);
    setLoaded(true)
  }

  function getOperationInputByType(index: number, operationIndex: number, type: OperationType) {
    switch (type) {
      case "TEST_RESULT":
        return (<>
          <div className="first-row">
            <Select
              id={index + "_"+ operationIndex+ "_sample"}
              name="sampleId"
              labelText={<FormattedMessage id="rulebuilder.label.selectSample" />}
              // value={condition.sampleId}
              className="inputSelect"
              onChange={(e) => {handleSampleSelected(e, index, operationIndex) }}
              required
            >
              <SelectItem
                text=""
                value=""
              />
              {sampleList.map((sample, sample_index) => (
                <SelectItem
                  text={sample.value}
                  value={sample.id}
                  key={sample_index}
                />
              ))}
            </Select>
          </div>
          <div className="first-row">
            <AutoComplete 
            idField={index + "_" + operationIndex + "_testresult"} 
            label="Test Result" 
            class="inputText" 
            suggestions={sampleTestList["testResult"][index] ? sampleTestList["testResult"][index][operationIndex] : []}>

            </AutoComplete>
          </div>
        </>);
      case "MATH_FUNCTION":
        return (<div className="first-row">
          <Select
            id={index + "_" + operationIndex + "_mathfunction"}
            name="sampleId"
            labelText={<FormattedMessage id="Mathematical Function" />}
            // value=''
            className="inputSelect2"
            //onChange={(e) => { }}
            required
          >
            <SelectItem
              text=""
              value=""
            />
            <SelectItem
              text="rrrrvvvvvvvvvv"
              value="ddd"
            />
          </Select>
        </div>);
      case "INTEGER":
        return (<div className="first-row">
          <TextInput
            name=""
            className="inputText2"
            type="number"
            id={index + "_" + operationIndex + "_integer"}
            labelText={<FormattedMessage id="Integer" />}
          // value=''
          // onChange={(e) => handleRuleFieldItemChange(e, index, action_index, FIELD.actions)}
          />
        </div>);
      case "PATIENT_ATTRIBUTE":
        return (<div className="first-row">
          <Select
            id={index + "_" + operationIndex + "_patientattribute"}
            name="sampleId"
            labelText={<FormattedMessage id="Patient attribute" />}
            // value=''
            className="inputSelect2"
            //onChange={(e) => { }}
            required
          >
            <SelectItem
              text=""
              value=""
            />
            <SelectItem
              text="rrrrgggggggggggg"
              value="sss"
            />
          </Select>
        </div>);
      // default:
      //   return "Unknown";
    }
  }

  const addOperationBySelect = (e, index) => {
    const { name, value } = e.target;
    addOperation(index, value)
  }

  return (
    <div className='adminPageContent'>
      {ruleList.map((rule, index) => (
        <div key={index} className="rules" >
          <div className="first-division">
            <Form onSubmit={(e) => handleSubmit(e)}>
              <Stack gap={7}>
                <div className="ruleBody">
                  <div className="inlineDiv">
                    <div>
                      <TextInput
                        name=""
                        className="reflexInputText"
                        type="text"
                        id=''
                        labelText={<FormattedMessage id="Calculation Name" />}
                      //value=''
                      // onChange={(e) => handleRuleFieldItemChange(e, index, action_index, FIELD.actions)}

                      />
                    </div>
                  </div>
                  <div className="inlineDiv">
                    Insert   &nbsp;  &nbsp;
                    <div>
                      <Button renderIcon={Add} id={index + "_testresult"} kind='tertiary' size='sm' onClick={() => addOperation(index, 'TEST_RESULT')}>
                        <FormattedMessage id="Test Result" />
                      </Button>
                    </div>
                    <div >
                      &nbsp;  &nbsp;
                    </div>
                    <div>
                      <Button renderIcon={Add} id={index + "_mathfunction"} kind='tertiary' size='sm' onClick={() => addOperation(index, 'MATH_FUNCTION')}>
                        <FormattedMessage id="Mathematical Function" />
                      </Button>
                    </div>
                    <div >
                      &nbsp;  &nbsp;
                    </div>
                    <div>
                      <Button renderIcon={Add} id={index + "_integer"} kind='tertiary' size='sm' onClick={() => addOperation(index, 'INTEGER')}>
                        <FormattedMessage id="Integer" />
                      </Button>
                    </div>
                    <div >
                      &nbsp;  &nbsp;
                    </div>
                    <div>
                      <Button renderIcon={Add} id={index + "_patientattribute"} kind='tertiary' size='sm' onClick={() => addOperation(index, 'PATIENT_ATTRIBUTE')}>
                        <FormattedMessage id="Patient Attribute" />
                      </Button>
                    </div>
                  </div>
                  <div className="section">
                    <div >
                      <h5><FormattedMessage id="Calculations" /></h5>
                    </div>
                    {rule.operations.map((operation, operation_index) => (
                      <div key={index + "_" + operation_index} className="inlineDiv">
                        {getOperationInputByType(index, operation_index, operation.type)}
                        <div >
                          &nbsp;  &nbsp;
                        </div>
                        <div className="second-row">
                          {operation.type !== '' && (
                            <IconButton renderIcon={Subtract} id={index + "_removeoperation"} kind='danger' label='' size='sm' onClick={() => removeOperation(index, operation_index)} />
                          )}
                        </div>
                        <div >
                          &nbsp;  &nbsp;
                        </div>
                        <div >
                          {rule.operations.length - 1 === operation_index && (
                            <Select
                              id={index + "_" + operation_index + "_addopeartion"}
                              name="sampleId"
                              labelText={<FormattedMessage id="Add Operation" />}
                              // value=''
                              className="inputSelect"
                              onChange={(e) => { addOperationBySelect(e, index) }}
                              required
                            >
                              <SelectItem
                                text=""
                                value=""
                              />
                              <SelectItem
                                text="Test Result"
                                value="TEST_RESULT"
                              />
                              <SelectItem
                                text="Mathematical Function"
                                value="MATH_FUNCTION"
                              />
                              <SelectItem
                                text="Integer"
                                value="INTEGER"
                              />
                              <SelectItem
                                text="Patient Attribute"
                                value="PATIENT_ATTRIBUTE"
                              />
                            </Select>
                          )}
                        </div>
                      </div>
                    ))}

                    <div className="inlineDiv">
                      <div >
                        <Select
                          id={index + "_sample"}
                          name="sampleId"
                          labelText={<FormattedMessage id="rulebuilder.label.selectSample" />}
                          // value={condition.sampleId}
                          className="inputSelect"
                          //onChange={(e) => { handleSampleSelected(e, index, operation_index) }}
                          required
                        >
                          <SelectItem
                            text=""
                            value=""
                          />
                          {sampleList.map((sample, sample_index) => (
                            <SelectItem
                              text={sample.value}
                              value={sample.id}
                              key={sample_index}
                            />
                          ))}
                        </Select>
                      </div>
                      <div>
                        <AutoComplete idField={index + "_finalresult"} class="inputText" label="Final Result"></AutoComplete>
                      </div>
                    </div>

                  </div>
                  <Button renderIcon={Save} id={index + "_submit"} type="submit" kind='primary' size='sm'>
                    <FormattedMessage id="label.button.submit" />
                  </Button>
                </div>
              </Stack>
            </Form >
            {ruleList.length - 1 === index && (
              <IconButton onClick={handleRuleAdd} label={<FormattedMessage id="rulebuilder.label.addRule" />} size='md' kind='tertiary' >
                <Add size={16} />
                <span><FormattedMessage id="rulebuilder.label.rule" /></span>
              </IconButton>
            )}
          </div>
          <div className="second-division">
            {ruleList.length !== 1 && (
              <ModalWrapper
                modalLabel={<FormattedMessage id="label.button.confirmDelete" />}
                open={showConfirmBox}
                onRequestClose={() => setShowConfirmBox(false)}
                handleSubmit={() => handleRuleRemove(index, rule.id)}
                onSecondarySubmit={handleCancelDelete}
                primaryButtonText={<FormattedMessage id="label.button.confirm" />}
                secondaryButtonText={<FormattedMessage id="label.button.cancel" />}
                modalHeading={<FormattedMessage id="rulebuilder.label.confirmDelete" />}
                buttonTriggerText={<FormattedMessage id="rulebuilder.label.removeRule" />}
                size='md'
              >
              </ModalWrapper>
            )}

          </div>
        </div>
      ))}
    </div>
  );
}

export default CalculatedValue;