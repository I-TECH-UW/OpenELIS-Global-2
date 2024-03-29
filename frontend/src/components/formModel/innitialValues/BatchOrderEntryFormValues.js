const BatchOrderEntryFormValues = {
  currentDate: "",
  currentTime: "",
  receivedDateForDisplay: "",
  ReceptionTime: "",
  referringSiteId: "",
  referringSiteName: "",

  sampleType: "",
  panels: [
    {
      name: "",
      testMaps: "",
      panelId: "1",
      panelOrder: 0,
    },
  ],
  tests: [
    {
      id: "",
      name: "",
      userBenchChoice: false,
    },
  ],

  _ProjectDataVL: {
    dryTubeTaken: "",
    edtaTubeTaken: "",
    dbsTaken: "",
    viralLoadTest: ""
  },
  _ProjectDataEID: {
    dryTubeTaken: "",
    dbsTaken: "",
    dnaPCR: ""
  },

  method: "",
  facilityID: "",
  PatientInfo: ""
};

export default BatchOrderEntryFormValues;
