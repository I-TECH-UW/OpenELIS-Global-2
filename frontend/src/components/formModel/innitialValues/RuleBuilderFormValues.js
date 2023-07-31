// eslint-disable-next-line import/no-anonymous-default-export
export default {
  id: null,
  ruleName: "",
  overall: "",
  toggled: true,
  active : true,
  analyteId : null,
  conditions: [{
    id: null,
    sampleId: "",
    testName: "",
    testId: "",
    relation: "",
    value: "0",
    value2 : "0",
    testAnalyteId : null 
  }],
  actions: [{
    id: null,
    sampleId : "",
    reflexTestName: "",
    reflexTestId: "",
    internalNote : "" ,
    externalNote : "" ,
    addNotification : "Y",
    testReflexId : null 
  }]
};