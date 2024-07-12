import LoginPage from "../pages/LoginPage";
import HomePage from "../pages/HomePage";
import NonConform from "../pages/NonConformPage";

let homePage = null;
let loginPage = null;
let nonConform = null;
let skipBeforeEach = false;

before("login", () => {
  loginPage = new LoginPage();
  loginPage.visit();
});

describe("Report Non-Conforming Event", function () {
  beforeEach("navigate to Report Non-Conforming Event Page", function () {
    if (!skipBeforeEach) {
      homePage = loginPage.goToHomePage();
      nonConform = homePage.goToReportNCE();
    }
  });

  it("User visits Report Non-Conforming Event Page", function () {
    nonConform
      .getReportNonConformTitle()
      .should("contain.text", "Report Non-Conforming Event (NCE)");
  });

  it("Should Search by Last Name and Validate the results", function () {
    cy.fixture("Patient").then((patient) => {
      nonConform.selectSearchType("Last Name");
      nonConform.enterSearchField(patient.lastName);
      nonConform.clickSearchButton();
      cy.fixture("EnteredOrder").then((order) => {
        nonConform.validateSearchResult(order.labNo);
      });
    });
  });

  it("Should Search by First Name and Validate the results", function () {
    cy.fixture("Patient").then((patient) => {
      nonConform.selectSearchType("First Name");
      nonConform.enterSearchField(patient.firstName);
      nonConform.clickSearchButton();
      cy.fixture("EnteredOrder").then((order) => {
        nonConform.validateSearchResult(order.labNo);
      });
    });
  });

  it("Should Search by PatientID and Validate the results", function () {
    cy.fixture("Patient").then((patient) => {
      nonConform.selectSearchType("Patient Identification Code");
      nonConform.enterSearchField(patient.nationalId);
      nonConform.clickSearchButton();
      cy.fixture("EnteredOrder").then((order) => {
        nonConform.validateSearchResult(order.labNo);
      });
    });
  });

  it("Should Search by Lab Number ", function () {
    cy.fixture("EnteredOrder").then((order) => {
      nonConform.selectSearchType("Lab Number");
      nonConform.enterSearchField(order.labNo);
      nonConform.clickSearchButton();
      nonConform.validateSearchResult(order.labNo);
      nonConform.clickCheckbox();
      nonConform.clickGoToNceFormButton();
    });

    nonConform.getAndSaveNceNumber();
  });

  it("Should enter the details and Submit the NCE Reporting Form", function () {
    skipBeforeEach = true; 
    cy.fixture("NonConform").then((nonConformData) => {
      nonConform.enterStartDate(nonConformData.dateOfEvent);
      nonConform.enterReportingUnit(nonConformData.reportingUnit);
      nonConform.enterDescription(nonConformData.description);
      nonConform.enterSuspectedCause(nonConformData.suspectedCause);
      nonConform.enterCorrectiveAction(nonConformData.proposedCorrectiveAction);
      nonConform.submitForm();
    });
  });
});
