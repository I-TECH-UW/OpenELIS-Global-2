import { useState, useEffect, useRef } from "react";
import { Form, Stack, TextInput, Select, SelectItem, Button, InlineLoading, IconButton, Search, Toggle, Switch } from '@carbon/react';
import { Add, Subtract } from '@carbon/react/icons';
import Autocomplete from "../inputComponents/AutoComplete";
import ResultFormValues from "../formModel/result/ResultFormValues";
import { getFromOpenElisServer, postToOpenElisServer } from "../utils/Utils";
import config from '../../config.json';



function ResultSearchAccession() {

  const componentMounted = useRef(true);
  
  
  // const FIELD = {
  //   conditions: "conditions",
  //   actions: "actions",
  //   results: "results"
  // }
  // const conditionsObj = {
  //   sample: "",
  //   test: "",
  //   testId: "",
  //   relation: "",
  //   value: ""
  // }
  // const actionObj = {
  //   action: "",
  //   reflexResult: "",
  //   reflexResultTestId: ""
  // }

  // class dictionaryResultObj {
  //   constructor(id, value) {
  //     this.id = id;
  //     this.value = value;
  //   }
  // }

  // const dictionaryResultObj = {
  //   id: "",
  //   value: "",
  // }

  // const resultObj = {
  //   formName: "default_AccessionResultsForm",
  //   accessionNumber: "",
  //   lastName: "default_lastName",
  //   firstName: "default_firstName",
  //   gender: "",
  //   dob: "",
  //   nationalId: "",
  //   subjectNumber: "",
  //   sequenceAccessionNumber: "",
  //   initialSampleCondition: "",
  //   sampleType: "",
  //   testDate: "",
  //   analysisMethod: "",
  //   testName: "",
  //   dictionaryResults: [dictionaryResultObj],
  // }

  useEffect(() => {
    // getFromOpenElisServer("/rest/tests", fetchTests)
    // getFromOpenElisServer("/rest/samples", fetchSamples)
    getFromOpenElisServer("/rest/results", fetchResults)

    return () => { // This code runs when component is unmounted
      componentMounted.current = false;
    }

  }, []);

  const [resultList, setResultList] = useState(ResultFormValues);

  // const [testList, setTestList] = useState([]);

  // const [sampleList, setSampleList] = useState([]);

  const handleResultFieldChange = (e, index) => {
    const { name, value } = e.target;
    const list = [...resultList];
    list[index][name] = value;
    setResultList(list);
  };

  const handleResultFieldItemChange = (e, index, itemIndex, field) => {
    const { name, value } = e.target;
    const list = [...resultList];
    list[index][field][itemIndex][name] = value;
    setResultList(list);
  }

  const handleResultRemove = (index) => {
    const list = [...resultList];
    list.splice(index, 1);
    setResultList(list);
  };

  const toggleResult = (e, index) => {
    const list = [...resultList];
    list[index]["toggled"] = e;
    setResultList(list);
  }

  const handleResultFieldItemAdd = (index, field, fieldObj) => {
    const list = [...resultList];
    list[index][field].push(fieldObj);
    setResultList(list);
  };

  const handleResultFieldItemRemove = (index, itemIndex, field) => {
    const list = [...resultList];
    list[index][field].splice(itemIndex, 1);
    setResultList(list);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    //setResultList([...resultList, { result: "" }]);
    // console.log("submit:", JSON.stringify(resultList))
    console.log("handleSubmit:");
  };

  const handleResultSearch = (event) => {
    console.log("handleResultSearch:")
  };

  const handleChange = (event) => {
    console.log("handleChange:")
  };

  const handleDelete = (event) => {
    console.log("handleDelete:")
  };

  const handleResultAdd = () => {
    // setResultList([...resultList, resultObj]);
  };

  // const fetchTests = (testList) => {
  //   if (componentMounted.current) {
  //     setTestList(testList);
  //   }
  // }

  const fetchSamples = (sampleList) => {
    if (componentMounted.current) {
      setSampleList(sampleList);
    }
  }

  const fetchResults = (resultList) => {
    if (componentMounted.current) {
      setResultList(resultList);
    }
  }

  const saveSample = {
    sampleType: "init ",
  }
  
  function SampleHeader({ currentSample}) {
    
    if (saveSample.sampleType !== currentSample.sampleType ) 
    //if (true) 
      return <div> "s " {saveSample.sampleType} "c " {currentSample.sampleType} </div>;
    
    saveSample.sampleType = currentSample.sampleType;  
    return <div>"old" </div>
  };

  return (
    <>
      <div className="inlineDiv">
        <div>
          <TextInput
            name="resultName"
            className="inputText"
            type="text"
            id={"resultname"}
            labelText="Result Name"
            value={""}
            onChange={(e) => handleResultFieldChange(e, 0)}
            required
          />
        </div>
      </div>

      {resultList.lastName} {resultList.firstName} {resultList.gender} {resultList.dob} {resultList.nationalId} {resultList.subjectNumber}
      <br></br>
      Lab No. : {resultList.testResult[0].sequenceAccessionNumber} {resultList.testResult[0].initialSampleCondition} {resultList.testResult[0].sampleType}
      {resultList.testResult.map((test, index) => (
        <div className="results">
          <div className="first-division">
            <Form onSubmit={handleSubmit} >
            
            <SampleHeader currentSample={{sampleType: resultList.testResult[index].sampleType}} />
              <Stack gap={7}>
                <div className="resultBody">
                  {test.testDate} {test.analysisMethod} {test.testName}
                  {true && (
                    <>
                      {/* <div className="section">
                        <div className="inlineDiv">
                         
                        <div >
                              <Select
                                id={"sample"}
                                name="sample"
                                labelText=""
                                value={""}
                                className="inputSelect"
                                onChange={(e) => handleResultFieldItemChange(e, 0, 0, "")}
                                required
                             >
                               <SelectItem
                                  text=""
                                  value=""
                                />
                                {resultList.dictionaryResults.map((sample, sample_index) => (
                                  <SelectItem
                                    text={sample.value}
                                    value={sample.id}
                                    key={sample_index}
                                  />
                                ))}
                              </Select>
                            </div>
                        </div>
                      </div> */}
                    </>
                  )}
                </div>
              </Stack>
            </Form >
            {/* {resultList.length - 1 === index && (
              <button
                onClick={handleResultAdd}
                className="add_button"
              >
                <Add size={16} />
                <span>Result</span>
              </button>
            )} */}

          </div>
          {/* <div className="second-division">
            {resultList.length !== 1 && (
              <button
                type="button"
                onClick={() => handleResultRemove(index)}
                className="remove-btn">
                <Subtract size={16} />
              </button>
            )}
          </div> */}
        </div>
      ))}
      <Button type="submit" kind='tertiary' size='sm'>
        Submit
      </Button>
    </>
  );
}

export default ResultSearchAccession;