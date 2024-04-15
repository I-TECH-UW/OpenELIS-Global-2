const BatchOrderEntryFormValues = {
  currentDate: null,
  currentTime: null,
  sampleTypeSelect: "",
  panels: [
    {
      name: "",
      testMaps: "",
      panelId: "",
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