import LoginPage from "../pages/LoginPage";
import HomePage from "../pages/HomePage";
import Result from "../pages/ResultsPage";
import PatientEntryPage from "../pages/PatientEntryPage";

let homePage = null;
let loginPage = null;
let result = null;
let patientPage = new PatientEntryPage();

before("login", () => {
  loginPage = new LoginPage();
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
      cy.get("#unitType").select(order.unitType);
    });
  });

  it("should accept the sample , refer the sample and save the result", function () {
    //   result.acceptSample();
    cy.get(".cds--checkbox-label").click();
    cy.get('[data-testid="expander-button-0"]').click();
    cy.get("#testMethod0").select("PCR");
    cy.get(":nth-child(3) > .cds--form-item > .cds--checkbox-label").click(); //refer test
    cy.get("#referralReason0").select("Test not performed");
    cy.get("#institute0").select("CEDRES");
    cy.get("#resultValue0").select("Positive HIV1");
    cy.get("#submit").click();
  });
});

describe("Result By Patient", function () {
  before("navigate to Result By Unit", function () {
    homePage = loginPage.goToHomePage();
    result = homePage.goToResultsByPatient();
  });

  it("User visits Results Page", function () {
    result.getResultTitle().should("contain.text", "Results");
  });
  it("Should search Patient By First and LastName and validate", function () {
    cy.wait(1000);
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
    cy.wait(200).reload();
  });

  it("should search patient By PatientId and validate", function () {
    cy.wait(1000);
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
    cy.wait(1000);
    cy.get(
      "tbody > :nth-child(1) > :nth-child(1) > .cds--radio-button-wrapper > .cds--radio-button__label > .cds--radio-button__appearance",
    ).click();
    cy.get("#cell-accept-0 > .cds--form-item > .cds--checkbox-label").click();
    cy.get('[data-testid="expander-button-0"]').click();
    cy.get("#testMethod0").select("STAIN");
    cy.get("#submit").click();
  });
});

describe("Result By Order", function () {
  before("navigate to Result By Unit", function () {
    homePage = loginPage.goToHomePage();
    result = homePage.goToResultsByOrder();
  });

  it("User visits Results Page", function () {
    result.getResultTitle().should("contain.text", "Results");
  });

  it("Should Search by Unit", function () {
    cy.fixture("EnteredOrder").then((order) => {
      cy.get("#accessionNumber").type(order.labNo);
    });
    cy.get(":nth-child(4) > #submit").click();
  });

  it("should accept the sample and save the result", function () {
    //   result.acceptSample();
    cy.get("#cell-accept-0 > .cds--form-item > .cds--checkbox-label").click();
    cy.get('[data-testid="expander-button-0"]').click();
    cy.get("#testMethod0").select("STAIN");
    cy.get(":nth-child(5) > #submit").click();
  });
});

describe("Result By Reffered Out Tests", function () {
  before("navigate to Result By Unit", function () {
    homePage = loginPage.goToHomePage();
    result = homePage.goToResultsForRefferedOut();
  });

  it("User visits Results Page", function () {
    result.getResultTitle().should("contain.text", "Referrals");
  });

  it("should search Referrals By PatientId and validate", function () {
    cy.wait(1000);
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
    cy.wait(1000);
    cy.get(
      "tbody > :nth-child(1) > :nth-child(1) > .cds--radio-button-wrapper > .cds--radio-button__label > .cds--radio-button__appearance",
    ).click();
    cy.get(":nth-child(1) > :nth-child(5) > .cds--btn").click();
  });

  it("should validate the results", function () {
    cy.fixture("Patient").then((patient) => {
      cy.get(
        ".gridBoundary > .cds--sm\\:col-span-4 > .cds--data-table-container > .cds--data-table-content > .cds--data-table > tbody > tr > :nth-child(6)",
      ).should("contain.text", patient.lastName);
      cy.get(
        ".gridBoundary > .cds--sm\\:col-span-4 > .cds--data-table-container > .cds--data-table-content > .cds--data-table > tbody > tr > :nth-child(7)",
      ).should("contain.text", patient.firstName);
    });
    cy.reload();
  });

  it("should search Referrals By Test Unit and validate", function () {
    cy.wait(1000);
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
    cy.wait(1000);
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

  it("should select the respecting reffered test and print the selected patient reports", function () {
    cy.get(
      "tbody > tr > .cds--table-column-checkbox > .cds--checkbox--inline > .cds--checkbox-label",
    ).click();
    cy.get(":nth-child(6) > :nth-child(2) > .cds--btn")
      .should("be.visible")
      .click();
  });
});

describe("Result By Range Of Order", function () {
  before("navigate to Result By Unit", function () {
    homePage = loginPage.goToHomePage();
    result = homePage.goToResultsByRangeOrder();
  });

  it("User visits Results Page", function () {
    result.getResultTitle().should("contain.text", "Results");
  });

  it("Should Enter Lab Number Number and perform Search", function () {
    cy.fixture("EnteredOrder").then((order) => {
      cy.get("#startLabNo").type(order.labNo);
    });
    cy.get(":nth-child(5) > #submit").click();
  });

  it("Should Accept And Save the result", function () {
    cy.get("#cell-accept-0 > .cds--form-item > .cds--checkbox-label").click();
    cy.get("#cell-accept-0 > .cds--form-item > .cds--checkbox-label").select(
      "Invalid",
    );
    cy.get(".orderLegendBody > :nth-child(5) > #submit").click();
  });
});

describe("Result By Test And Status", function () {
  before("navigate to Result By Unit", function () {
    homePage = loginPage.goToHomePage();
    result = homePage.goToResultsByTestAndStatus();
  });

  it("User visits Results Page", function () {
    result.getResultTitle().should("contain.text", "Results");
  });

  it("Should select testName, analysis status perform Search", function () {
    cy.fixture("workplan").then((order) => {
      cy.get("#testName").select(order.testName);
    });
    cy.get("#analysisStatus").select("Accepted by technician");
    cy.get(":nth-child(8) > #submit").click();
  });

  it("Should Validate And accept the result", function () {
    cy.fixture("workplan").then((order) => {
      cy.get("#cell-testName-0 > .sampleInfo").should(
        "contain.text",
        order.testName,
      );
    });
    cy.get(".cds--checkbox-label").click();
    cy.get('[data-testid="expander-button-0"]').click();
    cy.get("#testMethod0").select("EIA");
    cy.get(":nth-child(5) > #submit").click();
  });
});
