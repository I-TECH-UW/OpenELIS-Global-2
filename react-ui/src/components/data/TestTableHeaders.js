
export const columns = [
  {
    name: 'Sample Info',
    selector: row => row.sampleInfo,
    sortable: true,
  },
  {
    name: 'Test Date',
    selector: row => row.testDate,
    sortable: true,
  },
  {
    name: 'Methods',
    selector: (testResult) => {
        renderMethods(testResult);
    },
    sortable: true,
},
  {
    name: 'Result from analyzer',
    selector: row => row.analysisMethod,
    sortable: true,
  },
  {
    name: 'Test Name',
    selector: row => row.testName,
    sortable: true,
  },
  {
    name: 'Normal Range',
    selector: row => row.normalRange,
    sortable: true,
  },
  {
    name: 'Accept as is',
    selector: row => row.forceTechApproval,
    sortable: true,
  },
  {
    name: 'Shadow Result',
    selector: row => row.shadowResultValue,
    sortable: true,
  },
  {
    name: 'Current Result',
    selector: row => row.resultValue,
    sortable: true,
  },
];
