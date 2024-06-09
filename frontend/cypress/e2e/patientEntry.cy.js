import LoginPage from "../pages/LoginPage";

let homePage = null;
let loginPage = null;
let patientPage = null;

before("login", () => {
  loginPage = new LoginPage();
  loginPage.visit();
});

describe("Patient Search", function () {
  it("User Visits Home Page and goes to Add|Modify Patient Page", () => {
    homePage = loginPage.goToHomePage();
    patientPage = homePage.goToPatientEntry();
  });

  it("Add|Modify Patient page should appear with search field", function () {
    patientPage
      .getPatientEntryPageTitle()
      .should("contain.text", "Add Or Modify Patient");
  });

  it("External search button should be deactivated", function () {
    patientPage.getExternalSearchButton();
  });

  it("User should be able to navigate to create Patient tab", function () {
    patientPage.clickNewPatientTab();
    patientPage.getSubmitButton().should("be.visible");
  });

  it("User should enter patient Information", function () {
    cy.fixture("Patient").then((patient) => {
      patientPage.enterPatientInfo(
        patient.firstName,
        patient.lastName,
        patient.subjectNumber,
        patient.nationalId,
        patient.DOB,
      );
    });
  });

  it("User should click save new patient information button", function () {
    patientPage.clickSavePatientButton();
    cy.wait(1000);
    cy.get("div[role='status']").should("be.visible");
    cy.wait(200).reload();
  });
  it("Should search patient By Date of Birth", function () {
    cy.wait(1000);
    cy.fixture("Patient").then((patient) => {
      patientPage.searchPatientByField(patientPage.dateOfBirth, patient.DOB);
      patientPage.validatePatientSearchTableForField("dob", patient.DOB);
    });
    cy.wait(200).reload();
  });

  it("Should be able to search patients By gender", function () {
    cy.wait(1000);
    patientPage.getMaleGenderRadioButton().should("be.visible");
    patientPage.getMaleGenderRadioButton().click();
    cy.wait(200);
    patientPage.clickSearchPatientButton();
    cy.fixture("Patient").then((patient) => {
      patientPage.validatePatientByGender("M");
    });
    cy.wait(200).reload();
  });

  it("Should search Patient By FirstName", function () {
    cy.wait(1000);
    cy.fixture("Patient").then((patient) => {
      patientPage.searchPatientByField(
        patientPage.firstNameSelector,
        patient.firstName,
      );
      patientPage.validatePatientSearchTableForField(
        "firstName",
        patient.firstName,
      );
    });
    cy.wait(200).reload();
  });

  it("Should search Patient By LastName", function () {
    cy.wait(1000);
    cy.fixture("Patient").then((patient) => {
      patientPage.searchPatientByField(
        patientPage.lastNameSelector,
        patient.lastName,
      );
      patientPage.validatePatientSearchTableForField(
        "lastName",
        patient.lastName,
      );
    });
    cy.wait(200).reload();
  });

  it("Should search Patient By First and LastName", function () {
    cy.wait(1000);
    cy.fixture("Patient").then((patient) => {
      patientPage.searchPatientByField(
        patientPage.firstNameSelector,
        patient.firstName,
      );
      patientPage.searchPatientByField(
        patientPage.lastNameSelector,
        patient.lastName,
      );
      patientPage.getLastName().should("not.have.value", patient.inValidName);
      patientPage.clickSearchPatientButton();
      patientPage.validatePatientSearchTable(
        patient.firstName,
        patient.inValidName,
      );
    });
    cy.wait(200).reload();
  });

  it("should search patient By PatientId", function () {
    cy.wait(1000);
    cy.fixture("Patient").then((patient) => {
      patientPage.searchPatientByField(
        patientPage.patientIdSelector,
        patient.nationalId,
      );
      patientPage.validatePatientSearchTable(
        patient.firstName,
        patient.inValidName,
      );
    });
  });
});
