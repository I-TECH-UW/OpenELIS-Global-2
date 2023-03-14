import { useState, useEffect, useRef } from "react";
import { Form, Stack, Button } from '@carbon/react';
import ResultFormValues from "../formModel/result/ResultFormValues";
import { getFromOpenElisServer } from "../utils/Utils";



function ResultSearchAccession() {

  const componentMounted = useRef(true);

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

  function SampleHeader({ currentSample }) {
    if (currentSample.showSampleDetails == true)
      return <div className="resultBody">
        Lab No. : {currentSample.sequenceAccessionNumber} Condition: {currentSample.initialSampleCondition}  Sample Type: {currentSample.sampleType}
      </div>
    return <div></div>
  };

  return (
    <>
      <Stack gap={4}>
        <div className="resultBody">
          {resultList.lastName} {resultList.firstName} {resultList.gender} {resultList.dob} {resultList.nationalId} {resultList.subjectNumber}
        </div>
      </Stack>
      {resultList.testResult.map((test, index) => (
        <div className="results">
          <div className="first-division">
            <Form onSubmit={handleSubmit} >
              <SampleHeader
                currentSample={{
                  sampleType: test.sampleType,
                  sequenceAccessionNumber: test.sequenceAccessionNumber,
                  showSampleDetails: test.showSampleDetails
                }} />
              <Stack gap={7}>
                <div className="result">
                  {test.testDate} {test.analysisMethod} {test.testName}
                </div>
              </Stack>
            </Form >
          </div>
        </div>
      ))}
      <Button type="submit" kind='tertiary' size='sm'>
        Submit
      </Button>
    </>
  );
}

export default ResultSearchAccession;