import LoginPage from "../pages/LoginPage";
import HomePage from "../pages/HomePage";
import Result from "../pages/ResultsPage";
import PatientEntryPage from "../pages/PatientEntryPage";

let homePage = null;
let loginPage = new LoginPage();
let result = null;
let patientPage = new PatientEntryPage();

before("login", () => {
  loginPage.visit();
});

describe("Result By Unit", function () {
  before("navigate to Result By Unit", function () {
    homePage = loginPage.goToHomePage();
    result = homePage.goToResultsByUnit();
  });

  it("User visits Results Page", function () {
    result.getResultTitle().should("contain.text", "Results");
  });

  it("Should Search by Unit", function () {
    cy.fixture("workplan").then((order) => {
      result.selectUnitType(order.unitType);
    });
  });

  it("should accept the sample, refer the sample, and save the result", function () {
    result.acceptSample();
    result.expandSampleDetails();
    result.selectTestMethod(0, "PCR");
    result.referSample(0, "Test not performed", "CEDRES");
    result.setResultValue(0, "Positive HIV1");
    result.submitResults();
  });
});

describe("Result By Patient", function () {
  before("navigate to Result By Patient", function () {
    homePage = loginPage.goToHomePage();
    result = homePage.goToResultsByPatient();
  });

  it("User visits Results Page", function () {
    result.getResultTitle().should("contain.text", "Results");
  });

  it("Should search Patient By First and LastName and validate", function () {
    cy.fixture("Patient").then((patient) => {
      patientPage.searchPatientByFirstAndLastName(
        patient.firstName,
        patient.lastName,
      );
      patientPage.getFirstName().should("have.value", patient.firstName);
      patientPage.getLastName().should("have.value", patient.lastName);

      patientPage.getLastName().should("not.have.value", patient.inValidName);

      patientPage.clickSearchPatientButton();
      patientPage.validatePatientSearchTable(
        patient.firstName,
        patient.inValidName,
      );
    });
    cy.reload();
  });

  it("should search patient By PatientId and validate", function () {
    cy.fixture("Patient").then((patient) => {
      patientPage.searchPatientByPatientId(patient.nationalId);
      patientPage.clickSearchPatientButton();
      patientPage.validatePatientSearchTable(
        patient.firstName,
        patient.inValidName,
      );
    });
  });

  it("Should be able to search by respective patient and accept the result", function () {
    result.selectPatient();
    result.acceptResult();

    result.expandSampleDetails();
    result.selectTestMethod(0, "STAIN");
    result.submitResults();
  });
});

describe("Result By Order", function () {
  before("navigate to Result By Order", function () {
    homePage = loginPage.goToHomePage();
    result = homePage.goToResultsByOrder();
  });

  it("User visits Results Page", function () {
    result.getResultTitle().should("contain.text", "Results");
  });

  it("Should Search by Accession Number", function () {
    cy.fixture("EnteredOrder").then((order) => {
      cy.get("#accessionNumber").type(order.labNo);
    });
    cy.get(":nth-child(4) > #submit").click();
  });

  it("should accept the sample and save the result", function () {
    result.acceptSample();
    result.expandSampleDetails();
    result.selectTestMethod(0, "STAIN");
    result.submitResults();
  });
});

describe("Result By Referred Out Tests", function () {
  before("navigate to Result By Referred Out Tests", function () {
    homePage = loginPage.goToHomePage();
    result = homePage.goToResultsForRefferedOut();
  });

  it("User visits Results Page", function () {
    result.getResultTitle().should("contain.text", "Referrals");
  });

  it("should search Referrals By PatientId and validate", function () {
    cy.fixture("Patient").then((patient) => {
      patientPage.searchPatientByPatientId(patient.nationalId);
      patientPage.clickSearchPatientButton();
      patientPage.validatePatientSearchTable(
        patient.firstName,
        patient.inValidName,
      );
    });
  });

  it("should click respective patient and search for referred out tests", function () {
    result.selectPatient();
    result.search();
  });

  it("should validate the results", function () {
    cy.fixture("Patient").then((patient) => {
      result.validatePatientResult(patient);
    });
    cy.reload();
  });

  it("should search Referrals By Test Unit and validate", function () {
    cy.fixture("workplan").then((result) => {
      cy.get("#testnames-input").type(result.testName);
      cy.get("#testnames-item-0-item").click();
      cy.get(":nth-child(15) > .cds--btn").click({ force: true });
    });

    cy.get("tbody > tr > :nth-child(8)").should(
      "contain.text",
      "Western blot HIV",
    );
    cy.reload();
  });

  it("should search Referrals By LabNumber and validate", function () {
    cy.fixture("EnteredOrder").then((order) => {
      cy.get("#labNumberInput").type(order.labNo);
    });
    cy.get(":nth-child(4) > .cds--lg\\:col-span-4 > .cds--btn")
      .should("be.visible")
      .click();
    cy.fixture("EnteredOrder").then((patient) => {
      cy.get("tbody > tr > :nth-child(3)").should(
        "contain.text",
        patient.labNo,
      );
    });
  });

  it("should select the respecting referred test and print the selected patient reports", function () {
    result.selectRefferedTest();
    result.printReport();
  });
});

describe("Result By Range Of Order", function () {
  before("navigate to Result By Range Of Order", function () {
    result = homePage.goToResultsByRangeOrder();
  });

  it("User visits Results Page", function () {
    result.getResultTitle().should("contain.text", "Results");
  });

  it("Should Enter Lab Number and perform Search", function () {
    cy.fixture("EnteredOrder").then((order) => {
      cy.get("#startLabNo").type(order.labNo);
    });
    cy.get(":nth-child(5) > #submit").click();
  });

  it("Should Accept And Save the result", function () {
    result.acceptSample();
    result.expandSampleDetails();
    result.selectTestMethod(0, "Invalid");
    result.submitResults();
  });
});

describe("Result By Test And Status", function () {
  before("navigate to Result By Test And Status", function () {
    result = homePage.goToResultsByTestAndStatus();
  });

  it("User visits Results Page", function () {
    result.getResultTitle().should("contain.text", "Results");
  });

  it("Should select testName, analysis status, and perform Search", function () {
    cy.fixture("workplan").then((order) => {
      result.selectTestName(order.testName);
    });
    result.selectAnalysisStatus("Accepted by technician");
    result.searchByTest();
  });

  it("Should Validate And accept the result", function () {
    cy.fixture("workplan").then((order) => {
      cy.get("#cell-testName-0 > .sampleInfo").should(
        "contain.text",
        order.testName,
      );
    });
    result.acceptSample();
    result.expandSampleDetails();
    result.selectTestMethod(0, "EIA");
    result.submitResults();
  });
});
