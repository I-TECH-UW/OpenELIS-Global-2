const BatchOrderEntryFormValues = {
  currentDate: null,
  currentTime: null,
  // ReceptionTime: "",
  // receivedTime:"17:05",
  // referringSiteId: "",
  // referringSiteName: "",

  sampleTypeSelect: "",
  panels: [
    {
      name: "",
      testMaps: "",
      panelId: "1",
      panelOrder: 0,
    },
  ],
  testSectionList: "",

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
  facilityIDCheck:false,
  facilityID: "",
  patientInfoCheck: false,
  patientProperties: {
    currentDate: "",
    patientLastUpdated: "",
    personLastUpdated: "",
    patientUpdateStatus: "ADD",
    patientPK: "",
    // stnumber: null,
    subjectNumber: "",
    nationalId: "",
    guid: "",
    lastName: "",
    firstName: "",
    aka: null,
    gender: "",
    ageYears: null,
    ageMonths: null,
    ageDays: null,
    birthDateForDisplay: "",
  },
  sampleOrderItems: {
    newRequesterName: "",
    labNo: "",
    receivedDateForDisplay: null,
    receivedTime: null,
    referringSiteId: "",
    referringSiteDepartmentId: "",
    referringSiteName: "",

  },
};

export default BatchOrderEntryFormValues;